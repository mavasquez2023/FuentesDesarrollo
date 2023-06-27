

package cl.araucana.fpg.compiled;


/*
 * @(#) CompiledPDFObject.java    1.0 12-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */

import java.awt.Image;

import java.lang.reflect.Method;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.LinkedList;
import java.util.List;

import cl.araucana.core.util.ByteArray;
import cl.araucana.core.util.Padder;

import cl.araucana.fpg.DocumentModel;
import cl.araucana.fpg.FPGException;
import cl.araucana.fpg.PDFDocument;
import cl.araucana.fpg.PDFObjectCompiler;


import cl.araucana.fpg.compiled.instructions.DupInstruction;
import cl.araucana.fpg.compiled.instructions.Instruction;
import cl.araucana.fpg.compiled.instructions.InstructionFactory;
import cl.araucana.fpg.compiled.instructions.JumpableInstruction;


/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 12-04-2008 </TD>
 *            <TD align="center">  2.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class CompiledPDFObject implements FPGLanguage {

	public static final int MAX_LINE_SIZE = 64 * 1024;				 // 64KB.
	public static final int REFERENCE_DUP_BLOCK_SIZE = 4 * 1024;	 // 4KB.
	public static final int REFERENCE_OUTPUT_SIZE = 16 * 1024;		 // 16KB.
	public static final int REFERENCE_CACHED_OUTPUT_SIZE = 8 * 1024; // 8KB.

	/*
	 * End Of File indicator.
	 */
	public static final int EOF = -1;

	/*
	 * Data segment errors.
	 */
	public static final int ERROR_MISSED_FPG_VERSION       = 101;
	public static final int ERROR_UNEXPECTED_VERSION       = 102;
	public static final int ERROR_MISSED_DATA_SEGMENT      = 103;
	public static final int ERROR_UNEXPECTED_DATA_SEGMENT  = 104;
	public static final int ERROR_MISSED_TABLE_HEADER      = 105;
	public static final int ERROR_UNEXPECTED_TABLE_HEADER  = 106;
	public static final int ERROR_UNEXPECTED_TABLE_NAME    = 107;
	public static final int ERROR_UNEXPECTED_ENTRIES_COUNT = 108;
	public static final int ERROR_MISSED_TABLE_ENTRY       = 109;
	public static final int ERROR_UNEXPECTED_TABLE_ENTRY   = 110;

	/*
	 * Code segment errors.
	 */
	public static final int ERROR_MISSED_CODE_SEGMENT      = 111;
	public static final int ERROR_UNEXPECTED_CODE_SEGMENT  = 112;
	public static final int ERROR_MISSED_INSTRUCTION_CODE  = 113;

	/*
	 * FPG Data type classes.
	 */
	private static final Class[] typeClasses = {
		boolean.class,
		java.awt.Image.class,
		int.class,
		String.class
	};

	/*
	 * Default values to initialize variables.
	 */
	private static final Object[] defaultValues = {
		new Boolean(false),
		null,
		new Integer(0),
		""
	};

	private static final Class[] parameterTypes = new Class[0];
	private static final Object[] args = new Object[0];

	private static final String DEFAULT_XYBASE = "0 1 1 0 ";

	public CodeSegment cs;
	public DataSegment ds;

	public PDFDocument document;
	public DocumentModel documentModel;

	private static InstructionFactory instructionFactory =
			new InstructionFactory();

	private ByteArrayOutputStream normalOutput =
			new ByteArrayOutputStream(REFERENCE_OUTPUT_SIZE);

	private ByteArrayOutputStream cachedOutput =
			new ByteArrayOutputStream(REFERENCE_CACHED_OUTPUT_SIZE);

	private ByteArrayOutputStream output;

	private String objectName;
	private Class documentModelClass;
	private String inProgressCacheName;
	private boolean debug;

	private ByteArrayOutputStream dupBlock =
			new ByteArrayOutputStream(REFERENCE_DUP_BLOCK_SIZE);

	private ByteArrayInputStream input;

 	private byte[] line = new byte[MAX_LINE_SIZE];
 	private int lineLength;
 	private int lineNo;

	private String xyBase = DEFAULT_XYBASE;

	public static String normalizeName(String name) {
		if (name.length() <= 1) {
			return name.toUpperCase();
		}

		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	public static void checkProperty(String name, int type,
			Class documentModelClass) throws FPGException {

		getPropertyMethod(name, type, documentModelClass);
	}

	public CompiledPDFObject(String objectName,	byte[] compiledData,
			Class documentModelClass) throws FPGException {

		this(objectName, compiledData, documentModelClass, false);
	}

	public CompiledPDFObject(String objectName,	byte[] compiledData,
			Class documentModelClass, boolean debug) throws FPGException {

		this.objectName = objectName;
		this.documentModelClass = documentModelClass;
		this.debug = debug;

		instructionFactory.setDebug(debug);
		loadSegments(compiledData);
	}

	public String getObjectName() {
		return objectName;
	}

	public void setXYBase(String xyBase) {
		if (!xyBase.endsWith(" ")) {
			xyBase += " ";
		}

		this.xyBase = xyBase;
	}

	public String getXYBase() {
		return xyBase;
	}

	public byte[] execute(PDFDocument document) throws FPGException {

		this.document = document;
		this.documentModel = document.getDocumentModel();

		normalOutput.reset();
		cachedOutput.reset();

		inProgressCacheName = null;
		output = normalOutput;

		initDataSegment();
		cs.execute(this);

		/*
		 * Unlinks document and document model objects.
		 */
		this.document = null;
		this.documentModel = null;

		return output.toByteArray();
	}

	public boolean beginCache(String cacheName) throws FPGException {

		if (inProgressCacheName != null) {
			throw new FPGException(
					  "Cache '" + inProgressCacheName + "' is in progress. "
					+ "Failed begin cache '" + cacheName + "'.");
		}

		inProgressCacheName = cacheName;

		if (document.getCache(cacheName) != null) {
			return false;
		}

		cachedOutput.reset();

		output = cachedOutput;

		return true;
	}

	public void endCache(String cacheName) throws FPGException {

		if (inProgressCacheName == null) {
			throw new FPGException(
					    "No cache is in progress. "
					  + "Failed end cache '" + cacheName + "'.");
		}

		inProgressCacheName = null;

		document.putCache(cacheName, output.toByteArray());

		output = normalOutput;
	}

	public void includeCache(String cacheName) throws FPGException {

		byte[] cachedData = document.getCache(cacheName);

		if (cachedData == null) {
			throw new FPGException(
					"Not found cache '" + cacheName + "' to be included.");
		}

		write(cachedData);
	}

	public boolean getBooleanVariable(int index) throws FPGException {

		Boolean b =	(Boolean) getVariable(TYPE_BOOLEAN, index);

		return b.booleanValue();
	}

	public Image getImageVariable(int index) throws FPGException {

		return (Image) getVariable(TYPE_IMAGE, index);
	}

	public int getIntVariable(int index) throws FPGException {

		Integer i =	(Integer) getVariable(TYPE_INT, index);

		return i.intValue();
	}

	public String getStringVariable(int index) throws FPGException {

		return (String) getVariable(TYPE_STRING, index);
	}

	public Object getVariable(int type,	int index) throws FPGException {

		Variable variable = ds.vars[type][index];

		try {
			return variable.value;
		} catch (Exception e) {
			throw new FPGException(
					  "Cannot get variable. "
					+ "name='" + variable.name + "', "
					+ "type='" + FPGCode.getTypeName(type) + "'.");
		}
	}

	public void setBooleanVariable(int index, boolean value)
			throws FPGException {

		setVariable(TYPE_BOOLEAN, index, new Boolean(value));
	}

	public void setImageVariable(int index, Image value) throws FPGException {

		setVariable(TYPE_IMAGE, index, value);
	}

	public void setIntVariable(int index, int value) throws FPGException {

		setVariable(TYPE_INT, index, new Integer(value));
	}

	public void setStringVariable(int index, String value) throws FPGException {

		setVariable(TYPE_STRING, index, value);
	}

	public void setVariable(int type, int index, Object value)
			throws FPGException {

		Variable variable = ds.vars[type][index];

		variable.value = value;
	}

	public boolean getBooleanProperty(int index) throws FPGException {

		Boolean b =	(Boolean) getProperty(TYPE_BOOLEAN, index);

		return b.booleanValue();
	}

	public Image getImageProperty(int index) throws FPGException {

		return (Image) getProperty(TYPE_IMAGE, index);
	}

	public int getIntProperty(int index) throws FPGException {

		Integer i =	(Integer) getProperty(TYPE_INT, index);

		return i.intValue();
	}

	public String getStringProperty(int index) throws FPGException {

		return (String) getProperty(TYPE_STRING, index);
	}

	public Object getProperty(int type,	int index) throws FPGException {

		Property property = ds.props[type][index];

		try {
			return property.method.invoke(documentModel, args);
		} catch (Exception e) {
			throw new FPGException(
					  "Cannot get property. "
					+ "name='" + property.name + "', "
					+ "type='" + FPGCode.getTypeName(type) + "'.", e);
		}
	}

	public boolean getBooleanValue(Expression expr) throws FPGException {

		switch (expr.type) {
			case EXP_TYPE_VARIABLE:
				return getBooleanVariable(expr.index);

			case EXP_TYPE_PROPERTY:
				return getBooleanProperty(expr.index);

			default: // EXP_TYPE_LITERAL
				return expr.getBoolean();
		}
	}

	public Image getImageValue(Expression expr) throws FPGException {

		switch (expr.type) {
			case EXP_TYPE_VARIABLE:
				return getImageVariable(expr.index);

			case EXP_TYPE_PROPERTY:
				return getImageProperty(expr.index);

			default: // EXP_TYPE_LITERAL
				return expr.getImage();
		}
	}

	public int getIntValue(Expression expr) throws FPGException {

		switch (expr.type) {
			case EXP_TYPE_VARIABLE:
				return getIntVariable(expr.index);

			case EXP_TYPE_PROPERTY:
				return getIntProperty(expr.index);

			default: // EXP_TYPE_LITERAL
				return expr.getInt();
		}
	}

	public String getStringValue(Expression expr) throws FPGException {

		switch (expr.type) {
			case EXP_TYPE_VARIABLE:
				return getStringVariable(expr.index);

			case EXP_TYPE_PROPERTY:
				return getStringProperty(expr.index);

			default: // EXP_TYPE_LITERAL
				return expr.getString();
		}
	}

	public void write(String s) {
		byte[] b = s.getBytes();

		output.write(b, 0, b.length);
	}

	public void write(byte[] b) {
		output.write(b, 0, b.length);
	}

	public void write(byte[] b, int offset, int length) {
		output.write(b, offset, length);
	}

	private void loadSegments(byte[] compiledData) throws FPGException {

		log("Linking compiled PDF object '" + objectName + "'.");

		input = new ByteArrayInputStream(compiledData);

		/*
		 *  Compiled objects header.
		 */
		if (readLine() == 0) {
			invalidate(ERROR_MISSED_FPG_VERSION);
		}

		String version = getStringLine();

		log("version=" + version);

		if (version.equals(PDFObjectCompiler.VERSION_1_0)) {
			version_1_0();
		} else {
			invalidate(ERROR_UNEXPECTED_VERSION);
		}
	}

	private void version_1_0() throws FPGException {

		log("version_1_0()");

		/*
		 *  Data segment header.
		 */
		if (readLine() == 0) {
			invalidate(ERROR_MISSED_DATA_SEGMENT);
		}

		String dataSegment = getStringLine();

		if (!dataSegment.equals("data_segment")) {
			invalidate(ERROR_UNEXPECTED_DATA_SEGMENT);
		}

		ds = new DataSegment();

		/*
		 *  Variables Tables.
		 *
		 *  table_<typeName>_vars <#entries>
		 */
		for (int type = 0; type < FPGCode.NTYPES; type++) {
			if (readLine() == 0) {
				invalidate(ERROR_MISSED_TABLE_HEADER);
			}

			String tableHeader = getStringLine();
			String[] tokens = tableHeader.split("[ ]+");

			if (tokens.length != 2) {
				invalidate(ERROR_UNEXPECTED_TABLE_HEADER);
			}

			String expectedTableName =
					"table_" + FPGCode.getTypeName(type) + "_vars";

			String tableName = tokens[0];
			String sEntriesCount = tokens[1];

			if (!tableName.equals(expectedTableName)) {
				invalidate(ERROR_UNEXPECTED_TABLE_NAME);
			}

			int entriesCount = 0;

			try {
				entriesCount = Integer.parseInt(sEntriesCount);

				if (entriesCount < 0) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				invalidate(ERROR_UNEXPECTED_ENTRIES_COUNT);
			}

			log("table: " + expectedTableName + " #entries=" + entriesCount);

			ds.vars[type] = new Variable[entriesCount];

			for (int entry = 0; entry < entriesCount; entry++) {
				if (readLine() == 0) {
					invalidate(ERROR_MISSED_TABLE_ENTRY);
				}

				String tableEntry = getStringLine();

				tokens = tableEntry.split("[ ]+");

				if (tokens.length != 2) {
					invalidate(ERROR_UNEXPECTED_TABLE_ENTRY);
				}

				String entryName = tokens[0];
				String sEntryIndex = tokens[1];

				String expectedEntryIndex = "#" + entry;

				if (!sEntryIndex.equals(expectedEntryIndex)) {
					invalidate(ERROR_UNEXPECTED_TABLE_ENTRY);
				}

				if (!FPGCode.isID(entryName)) {
					invalidate(ERROR_UNEXPECTED_TABLE_ENTRY);
				}

				log(
						  "    entry: name=" + entryName + " "
						+ "index=" + expectedEntryIndex);

				ds.vars[type][entry] = new Variable(entryName, null);
			}
		}

		/*
		 *  Properties Tables.
		 *
		 *  table_<typeName>_props <#entries>
		 */
		for (int type = 0; type < FPGCode.NTYPES; type++) {
			if (readLine() == 0) {
				invalidate(ERROR_MISSED_TABLE_HEADER);
			}

			String tableHeader = getStringLine();
			String[] tokens = tableHeader.split("[ ]+");

			if (tokens.length != 2) {
				invalidate(ERROR_UNEXPECTED_TABLE_HEADER);
			}

			String expectedTableName =
					"table_" + FPGCode.getTypeName(type) + "_props";

			String tableName = tokens[0];
			String sEntriesCount = tokens[1];

			if (!tableName.equals(expectedTableName)) {
				invalidate(ERROR_UNEXPECTED_TABLE_NAME);
			}

			int entriesCount = 0;

			try {
				entriesCount = Integer.parseInt(sEntriesCount);

				if (entriesCount < 0) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				invalidate(ERROR_UNEXPECTED_ENTRIES_COUNT);
			}

			log("table: " + expectedTableName + " #entries=" + entriesCount);

			ds.props[type] = new Property[entriesCount];

			for (int entry = 0; entry < entriesCount; entry++) {
				if (readLine() == 0) {
					invalidate(ERROR_MISSED_TABLE_ENTRY);
				}

				String tableEntry = getStringLine();

				tokens = tableEntry.split("[ ]+");

				if (tokens.length != 2) {
					invalidate(ERROR_UNEXPECTED_TABLE_ENTRY);
				}

				String entryName = tokens[0];
				String sEntryIndex = tokens[1];

				String expectedEntryIndex = "#" + entry;

				if (!sEntryIndex.equals(expectedEntryIndex)) {
					invalidate(ERROR_UNEXPECTED_TABLE_ENTRY);
				}

				if (!FPGCode.isID(entryName)) {
					invalidate(ERROR_UNEXPECTED_TABLE_ENTRY);
				}

				log(
						  "    entry: name=" + entryName + " "
						+ "index=" + expectedEntryIndex);

				Method propertyMethod =
						getPropertyMethod(entryName, type, documentModelClass);

				ds.props[type][entry] = new Property(entryName, propertyMethod);
			}
		}

		/*
		 *  Code segment header.
		 */
		if (readLine() == 0) {
			invalidate(ERROR_MISSED_CODE_SEGMENT);
		}

		String codeSegment = getStringLine();

		if (!codeSegment.equals("code_segment")) {
			invalidate(ERROR_UNEXPECTED_CODE_SEGMENT);
		}

		List instructionsList = new LinkedList();
		DupInstruction $firstDupInBlock = null;

		while (readLine() > 1) {
			ByteArray array = new ByteArray(line, 0, lineLength);
			int beginIndex = array.indexOf(" ");

			if (beginIndex < 1) {
				invalidate(ERROR_MISSED_INSTRUCTION_CODE);
			}

			String name = array.getString(0, beginIndex);

			try {
				Instruction $instruction =
						instructionFactory.decode(name, array, beginIndex + 1);

				/*
				 * Optimization for DUP block instructions.
				 */
				if ($instruction.code == FPGCode.DUP) {
					DupInstruction $dup = (DupInstruction) $instruction;
					byte[] data = $dup.getData();

					if ($firstDupInBlock == null) {
						$firstDupInBlock = $dup;

						dupBlock.reset();
						dupBlock.write(data, 0, data.length);
					} else if ($instruction.label == null) {
						dupBlock.write(data, 0, data.length);
					} else {
						DupInstruction $genDup =
								new DupInstruction($firstDupInBlock.label);

						byte[] dupData = dupBlock.toByteArray();

						$genDup.setData(dupData, 0, dupData.length);
						instructionsList.add($genDup);

						$firstDupInBlock = $dup;

						dupBlock.reset();
						dupBlock.write(data, 0, data.length);
					}
				} else {
					if ($firstDupInBlock != null) {
						DupInstruction $genDup =
								new DupInstruction($firstDupInBlock.label);

						byte[] dupData = dupBlock.toByteArray();

						$genDup.setData(dupData, 0, dupData.length);
						instructionsList.add($genDup);

						$firstDupInBlock = null;
					}

					instructionsList.add($instruction);
				}
			} catch (FPGException e) {
				throw new FPGException(
						  "Compiled PDF object '" + objectName + "' "
						+ "cannot be linked. " + e.getMessage(), e.getCause());
			}
		}

		Instruction[] instructions =
				(Instruction[]) instructionsList.toArray(new Instruction[0]);

		/*
		 * Resolves addresses for jumped instructions.
		 */
		resolve:
		for (int i = 0; i < instructions.length; i++) {
			Instruction $instruction = instructions[i];

			if ($instruction instanceof JumpableInstruction) {
				JumpableInstruction $jumpableInstruction =
						(JumpableInstruction) $instruction;

				String jumpLabel = $jumpableInstruction.jumpLabel;

				if (jumpLabel == null) {
					throw new FPGException(
							  "Compiled PDF object '" + objectName + "' "
							+ "cannot be linked. Null jump label found.");
				}

				for (int j = 0; j < instructions.length; j++) {
					if (i != j && jumpLabel.equals(instructions[j].label)) {
						$jumpableInstruction.address = j;

						continue resolve;
					}
				}

				throw new FPGException(
						  "Compiled PDF object '" + objectName + "' "
						+ "cannot be linked. Jump label "
						+ "'" + jumpLabel + "' not found.");
			}
		}

		/*
		 * Checks END instruction to the end of compiled code.
		 */
		if (instructions.length < 1
				|| instructions[instructions.length - 1].code != FPGCode.END) {

			throw new FPGException(
					  "Compiled PDF object '" + objectName + "' "
					+ "cannot be linked. Missed 'end' instruction.");
		}

		/*
		 * Dumps linked instructions in debug mode.
		 */
		if (debug) {
			System.err.println("Linked + dup optimization:");

			for (int i = 0; i < instructions.length; i++) {
				Instruction $instruction = instructions[i];

				System.err.print("    " + Padder.lpad(i, 5, '0') + " ");

				if ($instruction instanceof JumpableInstruction) {
					JumpableInstruction $jumpableInstruction =
							(JumpableInstruction) $instruction;

					String address =
							Padder.lpad($jumpableInstruction.address, 5, '0');

					System.err.print("[" + address + "] ");
				}

				System.err.print(instructions[i]);
			}
		}

		/*
		 * Initializes code segment.
		 */
		cs = new CodeSegment();

		cs.instructions = instructions;
	}

	private void initDataSegment() {
		for (int type = 0; type < ds.vars.length; type++) {
			Object defaultValue = defaultValues[type];
			Variable[] vars = ds.vars[type];

			for (int entry = 0; entry < vars.length; entry++) {
				vars[entry].value = defaultValue;
			}
		}
	}

	private static Method getPropertyMethod(String name, int type,
			Class documentModelClass) throws FPGException {

		String methodName =
				(type != FPGCode.TYPE_BOOLEAN)
						? "get" + normalizeName(name) : name;

		Method method = null;

		try {
			method = documentModelClass.getMethod(methodName, parameterTypes);
		} catch (Exception e) {
			throw new FPGException("Property '" + name + "' not found.");
		}

		Class returnType = method.getReturnType();

		if (returnType != typeClasses[type]) {
			String sReturnType = returnType.getName();
			String typeName = FPGCode.getTypeName(type);

			throw new FPGException(
					  "Return type mismatch for property "
					+ "'" + name + "'. "
					+ "'" + typeName + "' was expected but "
					+ "'" + sReturnType + "' was found.");
		}

		return method;
	}

	private void invalidate(int error) throws FPGException {

		try {
			input.close();
		} catch(IOException e) {}

		input = null;

		throw new FPGException(
				  "Invalid compiled PDF object '" + objectName + "' "
				+ "[error=" + error + ", line=" + lineNo + "]");
	}

	private String getStringLine() {

		int length = 0;

		if (lineLength >= 2) {
			if (line[lineLength - 2] == '\r') {
				length = lineLength - 2;
			} else {
				length = lineLength - 1;
			}
		}

		return new String(line, 0, length);
	}

	private int readLine() throws FPGException {

		lineLength = 0;

		lineNo++;

		int ch;

		while ((ch = input.read()) != EOF && lineLength < MAX_LINE_SIZE) {
			line[lineLength++] = (byte) ch;

			if (ch == '\n') {
				break;
			}
		}

		if (ch != '\n') {
			if (lineLength == MAX_LINE_SIZE) {
				throw new FPGException(
						  "Line exceed " + MAX_LINE_SIZE + " bytes.");
			} else if (lineLength > 0) {
				throw new FPGException(
						"Unexpected EOF. Missing end of line character.");
			}
		}

		return lineLength;
	}

	private void log(String message) {
		if (debug) {
			System.err.println("FPGLinker: " + message);
		}
	}

	/* UNUSED.
	private void logn(String message) {
		if (debug) {
			System.err.print("FPGLinker: " + message);
		}
	}
	*/

	/* UNUSED.
	private void log(byte[] array) {
		if (debug) {
			logn("\n" + new String(array));
		}
	}
	*/
}
