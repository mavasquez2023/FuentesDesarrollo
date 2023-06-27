


/*
 * @(#) PDFObjectCompiler.java    1.0 07-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cl.araucana.core.util.ByteArray;
import cl.araucana.core.util.Padder;

import cl.araucana.fpg.barcodes.EAN128BarCode;
import cl.araucana.fpg.barcodes.PDF417BarCode;

import cl.araucana.fpg.compiled.Cache;
import cl.araucana.fpg.compiled.Expression;
import cl.araucana.fpg.compiled.FPGCode;
import cl.araucana.fpg.compiled.FPGLanguage;
import cl.araucana.fpg.compiled.NamedObject;
import cl.araucana.fpg.compiled.Property;
import cl.araucana.fpg.compiled.Variable;

import cl.araucana.fpg.compiled.instructions.BarCodeEAN128Instruction;
import cl.araucana.fpg.compiled.instructions.BarCodePDF417Instruction;
import cl.araucana.fpg.compiled.instructions.BeginCacheInstruction;
import cl.araucana.fpg.compiled.instructions.DupInstruction;
import cl.araucana.fpg.compiled.instructions.EndCacheInstruction;
import cl.araucana.fpg.compiled.instructions.EndInstruction;
import cl.araucana.fpg.compiled.instructions.ExpInstruction;
import cl.araucana.fpg.compiled.instructions.FontInstruction;
import cl.araucana.fpg.compiled.instructions.ImageInstruction;
import cl.araucana.fpg.compiled.instructions.IncludeCacheInstruction;
import cl.araucana.fpg.compiled.instructions.IndexInstruction;
import cl.araucana.fpg.compiled.instructions.Instruction;
import cl.araucana.fpg.compiled.instructions.JumpableInstruction;
import cl.araucana.fpg.compiled.instructions.JumpInstruction;
import cl.araucana.fpg.compiled.instructions.LocateInstruction;
import cl.araucana.fpg.compiled.instructions.SetInstruction;
import cl.araucana.fpg.compiled.instructions.TestInstruction;
import cl.araucana.fpg.compiled.instructions.TextInstruction;
import cl.araucana.fpg.compiled.instructions.XYBaseInstruction;


/**
 * Implements a PDF objects compiler. A compiled PDF object can be executed for
 * {@link PDFGenerator} to produce PDF document instances.
 * 
 * <p>
 * A compilable PDF object must be coded in function of <i>FPG
 * instructions set</i> and <b>PDF statements</b>. Only PDF objects that
 * belong to a {@link PDFTemplate} can be compiled.
 * </p>
 *
 * <p> This class assumes that all PDF Objects compiled by an instance belongs
 * to a same {@link PDFTemplate}.
 * </p>
 * 
 * <p> {@link cl.araucana.fpg.tools.FPGShell} specialized shell provides a
 * command to compile a {@link PDFTemplate} with this compiler.
 * </p>
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
 *            <TD> 07-04-2008 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 13-03-2010 </TD>
 *            <TD align="center">  1.1 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Agrega soporte para EAN128 (C&oacute;digos de Barra). </TD>
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
public class PDFObjectCompiler implements FPGLanguage {

	/**
	 * Represents version 1.0 for <i>FPG</i>. 
	 */
	public static final String VERSION_1_0 = "fpg-1.0";
	
	/**
	 * Highest <i>FPG</i> version supported by this compiler. 
	 */
	public static final String VERSION = VERSION_1_0;

	private static final int REFERENCE_SIZE = 64 * 1024;
	private static final int MAX_LINE_SIZE = 64 * 1024;

	private static final int EOF = -1;

	private boolean verbose;

	private long ti;
	private long tf;

	private ByteArrayInputStream input;
	private ByteArrayOutputStream output;

 	private byte[] line = new byte[MAX_LINE_SIZE];
 	private int lineLength;
 	private int lineNo;
 	private int offset;
 	private int beginIndex;
 	private int endIndex;
 	private String stmt;
 	private String[] tokens;
 	private String stmtName;
 	private String label;

	private List[] variables = new List[NTYPES];
	private List[] properties = new List[NTYPES];
	private List[] caches = { new LinkedList() };

	private Map images = new HashMap();

	private TreeMap globalProperties = new TreeMap();

	private int intrinsicLabelSeqNo;
	private String openedCacheName;
	private String openedCacheBlockLabel;

	private List instructions;

	/**
	 * Constructs a PDF compiler instance to compile PDF Objects with the
	 * specified options.
	 *  
	 * @param options Compilation options.
	 */
	public PDFObjectCompiler(CompilationOptions options) {
		verbose = options.verbose;
	}

	/**
	 * Reports final compilation time.
	 * 
	 * @return Final compilation time.
	 */
	public long getCompilationTime() {
		return tf - ti;
	}

	/**
	 * Compiles a coded PDF Object.
	 *  
	 * @param object PDF Object to be compiled.
	 * 
	 * @throws FPGException If PDF Object cannot be compiled.
	 */
	public void compile(PDFObject object) throws FPGException {

		compile(object, REFERENCE_SIZE);
	}

	/**
	 * Compiles a coded PDF Object with the specified reference size in bytes
	 * to compiled output.
	 *  
	 * @param object PDF Object to be compiled.
	 * 
	 * @param referenceSize Compiled output reference size.
	 * 
	 * @throws FPGException If PDF Object cannot be compiled.
	 */
	public void compile(PDFObject object, int referenceSize)
			throws FPGException {

		ti = System.currentTimeMillis();

		input = new ByteArrayInputStream(object.getData());

		/*
		 * Clean up lists.
		 */
		for (int i = 0; i < NTYPES; i++) {
			variables[i] = new LinkedList();
			properties[i] = new LinkedList();
		}

		instructions = new LinkedList();

		intrinsicLabelSeqNo = 1;
		lineNo = 0;
		label = null;
		openedCacheBlockLabel = null;
		openedCacheName = null;

		try {
nextLine:
			while (readLine() >= 1) {

				// Discards empty or comment lines.
				if (isDisposableLine()) {
					continue;
				}

				// System.out.print("+++ ");
				// System.out.write(line, 0, lineLength);

				ByteArray byteArray = new ByteArray(line, 0, lineLength);
				offset = 0;

				/*
				 * Compiles current line.
				 */
				while (offset < lineLength) {

					// ${ ...
					beginIndex = byteArray.indexOf("${", offset);

					/*
					 * DUP statement.
					 */
					if (beginIndex == NOT_FOUND) {
						DupInstruction $dup = new DupInstruction(label);

						$dup.setData(line, offset, lineLength - offset);
						instructions.add($dup);

						break;
					}

					// ... }
					endIndex = byteArray.indexOf("}", beginIndex + 2);

					if (endIndex == NOT_FOUND) {
						throw new FPGException("Missed '}'." + lineNo());
					}

					stmt = byteArray.getString(beginIndex + 2, endIndex);
					tokens = getTokens(stmt);

					if (tokens.length == 0) {
						throw new FPGException(
								"Invalid statement '" + stmt + "'." + lineNo());
					}

					/*
					 * Checks general statement type.
					 */
					if (beginIndex > 0) {

						/*
						 *  ${property}
						 */
						if (tokens.length != 1) {
							throw new FPGException(
									  "Invalid statement "
									+ "'" + stmt + "'." + lineNo());
						}

						gen$Exp();

						offset = endIndex + 1;
					} else {
						/*
						 * Multitoken statement.
						 */
						byte nextCh = line[endIndex + 1];

						if (nextCh != '\r' && nextCh != '\n') {
							byte lastPreviousCh = line[lineLength - 2];
							int eol = (lastPreviousCh == '\r') ? 2 : 1;

							String badText =
									new String(
											line,
											endIndex + 1,
											lineLength - endIndex - 1 - eol);

							throw new FPGException(
									  "Unexpected text "
									+ "'" + badText  + "'."	+ lineNo());
						}

						stmtName = tokens[0];

						switch (FPGCode.getStmt(stmtName)) {

							case STMT_DECLARE:
								declare();

								continue nextLine;

							case STMT_TEXT:
								gen$Text();

								break;

							case STMT_LOCATE:
								gen$Locate();

								break;

							case STMT_INDEX:
								gen$Index();

								break;

							case STMT_GOTO:
								gen$Goto();

								break;

							case STMT_SET:
								gen$Set();

								break;

							case STMT_TEST:
								gen$Test();

								break;

							case STMT_BEGIN_CACHE:
								gen$BeginCache();

								break;

							case STMT_END_CACHE:
								gen$EndCache();

								continue nextLine;

							case STMT_INCLUDE_CACHE:
								gen$IncludeCache();

								break;

							case STMT_BARCODE:
								gen$BarCode();

								break;

							case STMT_IMAGE:
								gen$Image();

								break;

							case STMT_FONT:
								gen$Font();

								break;

							case STMT_XYBASE:
								gen$XYBase();

								break;

							case STMT_LABEL:

								if (tokens.length != 2) {
									throw new FPGException(
											  "Invalid label statement."
											+ lineNo());
								}

								label = tokens[1];

								continue nextLine;

							case NOT_FOUND:
								throw new FPGException(
										  "Unknown statement "
										+ "'" + stmtName + "'." + lineNo());

							default:
								throw new FPGException(
										  "Unimplemented statement "
										+ "'" + stmtName + "'."	+ lineNo());
						}

						break;
					}
				}

				label = null;
			}
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {}
			}
		}

	    /*
	     * Checks closed cache block.
	     */
	    if (openedCacheName != null) {
			throw new FPGException(
					"Cache '" + openedCacheName + "' not closed." + lineNo());
		}

		/*
		 * End of code segment.
		 */
		EndInstruction $end = new EndInstruction(label);

		instructions.add($end);

		/*
		 *  Checks labels consistency.
		 */
		checkLabels();

		/*
		 * Saves generated code.
		 */
		byte[] code = getCode(referenceSize);

		object.setXData(code);

		// log(code);

		tf = System.currentTimeMillis();
	}

	/**
	 * Gets global properties map for all PDF Objects compiled for this
	 * compiler. 
	 * 
	 * @return Global properties map.
	 */
	public TreeMap getProperties() {
		return globalProperties;
	}

	private void checkLabels() throws FPGException {

		Map labels = new HashMap();

		Iterator instructionsIT = instructions.iterator();

		while (instructionsIT.hasNext()) {
			Instruction instruction = (Instruction) instructionsIT.next();

			if (instruction.label != null) {
				labels.put(instruction.label, instruction);
			}
		}

		instructionsIT = instructions.iterator();

		while (instructionsIT.hasNext()) {
			Instruction instruction = (Instruction) instructionsIT.next();

			if (!instruction.resolved) {
				if (instruction instanceof JumpableInstruction) {
					JumpableInstruction jump =
							(JumpableInstruction) instruction;

					if (labels.get(jump.jumpLabel) == null) {
						throw new FPGException(
								"Undeclared label '" +  jump.jumpLabel + "'.");
					}
				} else {
					throw new FPGException(
							  "Unexpected internal error. An unresolved "
							+ "instruction was found but it´s not "
							+ "jumpable one.");
				}
			}
		}
	}

	private byte[] getCode(int referenceSize) {
 		output = new ByteArrayOutputStream(referenceSize);

		write(VERSION + "\n");

		writeDataSegment();
		writeCodeSegment();

		try {
			output.close();
		} catch (IOException e) {}

		return output.toByteArray();
	}

	private void writeDataSegment() {
		write("data_segment\n");
		writeNames(variables, "vars");
		writeNames(properties, "props");
	}

	private void writeNames(List[] names, String nameType) {
		for (int type = 0; type < NTYPES; type++) {
			List namesList = names[type];

			write(
					  "table_" + FPGCode.getTypeName(type)
					+ "_" + nameType + " "
					+ namesList.size() + "\n");

			int index = 0;
			Iterator namesIT = namesList.iterator();

			while (namesIT.hasNext()) {
				NamedObject namedObject = (NamedObject) namesIT.next();

				write(Padder.rpad(
						namedObject.name, MAX_ID_LENGTH) + " #" + index + "\n");

				index++;
			}
		}
	}

	private void writeCodeSegment() {
		write("code_segment\n");

		Iterator instructionsIT = instructions.iterator();

		while (instructionsIT.hasNext()) {
			Instruction instruction = (Instruction) instructionsIT.next();

			write(instruction.toString());
		}
	}

	private void write(String s) {
		byte[] array = s.getBytes();

		output.write(array, 0, array.length);
	}

	private void declare() throws FPGException {

		if (tokens.length != 3) {
			throw new FPGException(
					"Invalid declare statement." + lineNo());
		}

		String variableName = tokens[1];
		String typeName = tokens[2];

		if (!FPGCode.isID(variableName)) {
			throw new FPGException(
					  "Invalid variable name '" + variableName + "'."
					+ lineNo());
		}

		int typeIndex = FPGCode.indexOfTypeName(typeName);

		if (typeIndex == NOT_FOUND) {
			throw new FPGException(
					  "Invalid type name '" + typeName + "'."
					+ lineNo());
		}

		int varIndex = getVariableIndex(variableName, typeIndex);

		if (varIndex != NOT_FOUND) {
			throw new FPGException(
					  "Variable name '" + variableName + "' "
					+ "was declared previously." + lineNo());
		}

		addVariable(variableName, typeIndex);
	}

	private void gen$Exp() throws FPGException {

		String propertyName = tokens[0];

		if (propertyName.length() == 0
					|| propertyName.charAt(0) == '$') {

			throw new FPGException(
					  "Unexpected property name "
					+ "'" + propertyName + "'."
					+ lineNo());
		}

		DupInstruction $dup = new DupInstruction(label, true);

		$dup.setData(line, offset, beginIndex - offset);
		instructions.add($dup);

		int propIndex =
				FPGCode.indexOfBuiltInProperty(propertyName);

		ExpInstruction $exp;

		if (propIndex != NOT_FOUND) {		// Builtin property.
			$exp = new ExpInstruction(label, true, propIndex);
		} else {
			propIndex = addProperty(propertyName, TYPE_STRING);

			$exp = new ExpInstruction(label, false, propIndex);
		}

		instructions.add($exp);
	}

	private void gen$Text() throws FPGException {

		if (tokens.length < 2 || tokens.length > 3) {
			throw new FPGException("Invalid text statement." + lineNo());
		}

		String propertyName = tokens[1];
		int propIndex = addProperty(propertyName, TYPE_STRING);

		TextInstruction $text;

		if (tokens.length == 2) {
			 $text = new TextInstruction(label, propIndex);
		} else {
			int spacePaddingWidth;

			try {
				spacePaddingWidth =
						Integer.parseInt(tokens[2]);
			} catch (NumberFormatException e) {
				throw new FPGException(
							"Invalid space padding width "
						  + "'" + tokens[2] + "'." + lineNo());
			}

			$text =
					new TextInstruction(
							label,
							propIndex,
							spacePaddingWidth);
		}

		instructions.add($text);
	}

	private void gen$Locate() throws FPGException {

		if (tokens.length != 3) {
			throw new FPGException("Invalid locate statement." + lineNo());
		}

		String sExprY = tokens[1];
		String sExprX = tokens[2];

		Expression exprY = getExpression(sExprY, TYPE_INT);
		Expression exprX = getExpression(sExprX, TYPE_INT);

		if (exprY.subType != TYPE_INT || exprX.subType != TYPE_INT) {
			throw new FPGException(
					  "Invalid locate statement. "
					+ "Expression types must be 'int'." + lineNo());
		}

		LocateInstruction $locate = new LocateInstruction(label, exprY, exprX);

		instructions.add($locate);
	}

	private void gen$Index() throws FPGException {

		if (tokens.length != 4 || tokens[1].length() == 0) {
			throw new FPGException("Invalid index statement." + lineNo());
		}

		String sExpr = tokens[1];
		String sPositions = tokens[2];
		String sValues = tokens[3];

		Expression indexExpr = getExpression(sExpr, TYPE_INT);

		if (indexExpr.subType != TYPE_INT) {
			throw new FPGException(
					  "Invalid index statement. "
					+ "Expression type must be 'int'." + lineNo());
		}

		String[] aPositions = sPositions.split("[ ]+");
		int[] positions = new int[aPositions.length];

		for (int i = 0; i < positions.length; i++) {
			try {
				positions[i] = Integer.parseInt(aPositions[i]);

				if(positions[i] < 0) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				throw new FPGException(
						  "Invalid position value '" + aPositions[i] + "' "
						+ "in index statement." + lineNo());
			}
		}

		List valuesList = new LinkedList();
		int offset = 0;

		while (offset < sValues.length()) {
			int beginIndex = sValues.indexOf('"', offset);

			if (beginIndex == NOT_FOUND) {
				break;
			}

			int endIndex = sValues.indexOf('"', beginIndex + 1);

			if (endIndex == NOT_FOUND) {
				throw new FPGException(
						"Mismatched '\"' in index statement." + lineNo());
			}

			String value = sValues.substring(beginIndex + 1, endIndex);

			valuesList.add(value);

			offset = endIndex + 1;
		}

		String[] values = (String[]) valuesList.toArray(new String[0]);

		try {
			IndexInstruction $index =
					new IndexInstruction(label, indexExpr, positions, values);

			instructions.add($index);
		} catch (IllegalArgumentException e) {
			throw new FPGException(
					"Invalid index statement. " + e.getMessage() + lineNo());
		}
	}

	private void gen$Goto() throws FPGException {

		if (tokens.length != 2) {
			throw new FPGException("Invalid goto statement." + lineNo());
		}

		String jumpLabel = tokens[1];

		// Checks label must be an ID.
		if (!FPGCode.isID(jumpLabel)) {
			throw new FPGException(
					  "Invalid label '" + jumpLabel +"' "
					+ "in goto statement." + lineNo());
		}

		JumpInstruction $jump = new JumpInstruction(label, jumpLabel);

		instructions.add($jump);
	}

	private void gen$Set() throws FPGException {

		if (tokens.length != 4) {
			throw new FPGException("Invalid set statement." + lineNo());
		}

		String variableName = tokens[1];
		String sAssignOperator = tokens[2];
		String sExpr = tokens[3];

		// Checks variable.
		if (!FPGCode.isID(variableName)) {
			throw new FPGException(
					  "Invalid variable name '" + variableName + "' "
					+ "in set statement." + lineNo());
		}

		int[] variableDescriptor = getVariableDescriptor(variableName);

		if (variableDescriptor == null) {
			throw new FPGException(
					  "Undeclared variable '" + variableName + "' "
					+ "in set statement." + lineNo());
		}

		// Checks assign operator.
		int assignOperator =
				FPGCode.indexOfAssignOperator(sAssignOperator);

		if (assignOperator == NOT_FOUND) {
			throw new FPGException(
					  "Invalid assign operator '" + sAssignOperator + "' "
					+ "in set statement." + lineNo());
		}

		// Checks expression.
		Expression expr = getExpression(sExpr, variableDescriptor[TYPE]);

		if (expr.subType != variableDescriptor[TYPE]) {
			throw new FPGException(
					  "Invalid set statement. "
					+ "Expression types must be same." + lineNo());
		}

		try {
			SetInstruction $set =
					new SetInstruction(
							label,
							variableDescriptor[INDEX],
							variableDescriptor[TYPE],
							assignOperator,
							expr);

			instructions.add($set);
		} catch (IllegalArgumentException e) {
			throw new FPGException(
					"Invalid set statement. " + e.getMessage() + lineNo());
		}
	}

	private void gen$Test() throws FPGException {

		if (tokens.length != 5) {
			throw new FPGException("Invalid test statement." + lineNo());
		}

		String sExpr1 = tokens[1];
		String sOperator = tokens[2];
		String sExpr2 = tokens[3];
		String jumpLabel = tokens[4];

		Expression expr1;
		Expression expr2;

		boolean isProperty1 = FPGCode.isID(sExpr1);
		boolean isProperty2 = FPGCode.isID(sExpr2);

		if (isProperty1 && isProperty2) {
			int[] propertyDescriptor1 = getPropertyDescriptor(sExpr1);
			int[] propertyDescriptor2 = getPropertyDescriptor(sExpr2);
			int propertyType;

			if (propertyDescriptor1 != null) {
				propertyType = propertyDescriptor1[TYPE];
			} else if (propertyDescriptor2 != null) {
				propertyType = propertyDescriptor2[TYPE];
			} else {
				throw new FPGException(
						  "Property types cannot be inferred "
						+ "from test statement." + lineNo());
			}

			expr1 = getExpression(sExpr1, propertyType);
			expr2 = getExpression(sExpr2, propertyType);
		} else if (isProperty1) {
			int[] propertyDescriptor1 = getPropertyDescriptor(sExpr1);

			expr2 = getExpression(sExpr2, 0);

			if (propertyDescriptor1 == null) {
				addProperty(sExpr1, expr2.subType);
			}

			expr1 = getExpression(sExpr1, expr2.subType);
		} else if (isProperty2) {
			int[] propertyDescriptor2 = getPropertyDescriptor(sExpr2);

			expr1 = getExpression(sExpr1, 0);

			if (propertyDescriptor2 == null) {
				addProperty(sExpr2, expr1.subType);
			}

			expr2 = getExpression(sExpr2, expr1.subType);
		} else {
			expr1 = getExpression(sExpr1, 0);
			expr2 = getExpression(sExpr2, 0);
		}

		// Checks comparation operator.
		int operator = FPGCode.indexOfComparationOperator(sOperator);

		if (operator == NOT_FOUND) {
			throw new FPGException(
					  "Invalid comparation operator '" + sOperator + "'"
					+ "in test statement." + lineNo());
		}

		// Checks label must be an ID.
		if (!FPGCode.isID(jumpLabel)) {
			throw new FPGException(
					  "Invalid label '" + jumpLabel +"' "
					+ "in test statement." + lineNo());
		}

		try {
			TestInstruction $test =
					new TestInstruction(
							label, expr1, operator, expr2, jumpLabel);

			instructions.add($test);
		} catch (IllegalArgumentException e) {
			throw new FPGException(
					"Invalid test statement. " + e.getMessage() + lineNo());
		}
	}

	private void gen$BeginCache() throws FPGException {

		if (tokens.length != 2) {
			throw new FPGException("Invalid beginCache statement." + lineNo());
		}

		String cacheName = tokens[1];

		if (openedCacheName != null) {
			throw new FPGException("Unsupported nested caches." + lineNo());
		}

		int index = getCacheIndex(cacheName);

		if (index != NOT_FOUND) {
			throw new FPGException(
					"Duplicate cache name '" + cacheName + "'." + lineNo());
		}

		openedCacheName = cacheName;

		addCache(openedCacheName);

		openedCacheBlockLabel =
				"_bc" + Padder.lpad(intrinsicLabelSeqNo++, 4, '0');

		BeginCacheInstruction $beginCache =
				new BeginCacheInstruction(
						label, openedCacheName, openedCacheBlockLabel);

		instructions.add($beginCache);
	}

	private void gen$EndCache() throws FPGException {

		if (tokens.length != 2) {
			throw new FPGException("Invalid endCache statement." + lineNo());
		}

		String cacheName = tokens[1];

		if (openedCacheName == null) {
			invalidCache(cacheName);
		}

		if (!cacheName.equals(openedCacheName)) {
			invalidCache(cacheName);
		}

		EndCacheInstruction $endCache =
				new EndCacheInstruction(label, cacheName);

		instructions.add($endCache);

		label = openedCacheBlockLabel;
		openedCacheName = null;
		openedCacheBlockLabel = null;
	}

	private void gen$IncludeCache() throws FPGException {

		if (tokens.length != 2) {
			throw new FPGException(
					"Invalid includeCache statement." + lineNo());
		}

		String cacheName = tokens[1];

		int index = getCacheIndex(cacheName);

		if (index == NOT_FOUND) {
			throw new FPGException(
					"Undeclared cache name '" + cacheName + "'." + lineNo());
		}

		IncludeCacheInstruction $includeCache =
				new IncludeCacheInstruction(label, cacheName);

		instructions.add($includeCache);
	}

	private void gen$BarCode() throws FPGException {

		if (tokens.length != 4) {
			throw new FPGException("Invalid barcode statement." + lineNo());
		}

		String sBarCodeType = tokens[1];
		String sBarCodeParameterList = tokens[2];
		String propertyName = tokens[3];

		// Checks property.
		int[] propertyDescriptor = getPropertyDescriptor(propertyName);
		int propIndex;

		if (propertyDescriptor == null) {
			propIndex = addProperty(propertyName, TYPE_STRING);
		} else if (propertyDescriptor[TYPE] == TYPE_STRING) {
			propIndex = propertyDescriptor[INDEX];
		} else {
			throw new FPGException(
					  "Property '" +  propertyName + "' "
					+ "must be 'string' in barcode statement." + lineNo());
		}

		// Checks barcode type.
		int barCodeType = FPGCode.indexOfBarCodeTypeName(sBarCodeType);

		switch (barCodeType) {
			case BARCODE_EAN128:
				String[] parameters = sBarCodeParameterList.split("[ ]+");

				try {
					char charset = parameters[0].charAt(0);
					int width = Integer.parseInt(parameters[1]);
					int height = Integer.parseInt(parameters[2]);
					int y0 = Integer.parseInt(parameters[3]);
					int x0 = Integer.parseInt(parameters[4]);

					if (width < 0) {
						throw new IllegalArgumentException(parameters[1]);
					}

					if (height < 0) {
						throw new IllegalArgumentException(parameters[2]);
					}

					if (y0 < 0) {
						throw new IllegalArgumentException(parameters[3]);
					}

					if (x0 < 0) {
						throw new IllegalArgumentException(parameters[4]);
					}

					if (!parameters[5].equals("true")
								&& !parameters[5].equals("false")) {

						throw new IllegalArgumentException(parameters[5]);
					}

					boolean horizontal =
							Boolean.valueOf(parameters[5]).booleanValue();

					EAN128BarCode barCode =
							new EAN128BarCode(
									charset,
									width,
									height,
									y0,
									x0,
									horizontal);

					BarCodeEAN128Instruction ean128 =
							new BarCodeEAN128Instruction(
									label, barCode, propIndex);

					instructions.add(ean128);
				} catch (Exception e) {
					throw new FPGException(
							   "Invalid parameter list "
							 + "'" +  sBarCodeParameterList + "' "
							 + "for ean128 in barcode statement." + lineNo());
				}
				
				break;

			case BARCODE_PDF417:
				parameters = sBarCodeParameterList.split("[ ]+");

				try {
					int codeColumns = Integer.parseInt(parameters[0]);
					int errorLevel = Integer.parseInt(parameters[1]);
					int y0 = Integer.parseInt(parameters[2]);
					int x0 = Integer.parseInt(parameters[3]);

					if (codeColumns < 1 || codeColumns > 30) {
						throw new IllegalArgumentException(parameters[0]);
					}

					if (errorLevel < 0 || errorLevel > 8) {
						throw new IllegalArgumentException(parameters[1]);
					}

					if (y0 < 0) {
						throw new IllegalArgumentException(parameters[2]);
					}

					if (x0 < 0) {
						throw new IllegalArgumentException(parameters[3]);
					}

					if (!parameters[4].equals("true")
								&& !parameters[4].equals("false")) {

						throw new IllegalArgumentException(parameters[4]);
					}

					boolean horizontal =
							Boolean.valueOf(parameters[4]).booleanValue();

					PDF417BarCode barCode =
							new PDF417BarCode(
									codeColumns,
									errorLevel,
									y0,
									x0,
									horizontal);

					BarCodePDF417Instruction $pdf417 =
							new BarCodePDF417Instruction(
									label, barCode, propIndex);

					instructions.add($pdf417);
				} catch (Exception e) {
					throw new FPGException(
							   "Invalid parameter list "
							 + "'" +  sBarCodeParameterList + "' "
							 + "for pdf417 in barcode statement." + lineNo());
				}

				break;

			default:
				throw new FPGException(
						  "Unknown barcode type "
						+ "'" + sBarCodeType + "'." + lineNo());
		}
	}

	private void gen$Image() throws FPGException {

		if (tokens.length != 3) {
			throw new FPGException("Invalid image statement." + lineNo());
		}

		String sImageType = tokens[1];
		String propertyName = tokens[2];

		// Checks property.
		int[] propertyDescriptor = getPropertyDescriptor(propertyName);
		int propIndex;

		if (propertyDescriptor == null) {
			propIndex = addProperty(propertyName, TYPE_IMAGE);
		} else if (propertyDescriptor[TYPE] == TYPE_IMAGE) {
			propIndex = propertyDescriptor[INDEX];
		} else {
			throw new FPGException(
					  "Property '" +  propertyName + "' "
					+ "must be 'image' in image statement." + lineNo());
		}

		// Checks barcode type.
		int imageType = FPGCode.indexOfImageTypeName(sImageType);

		if (imageType == NOT_FOUND) {
			throw new FPGException(
					"Unknown image type '" + sImageType + "'." + lineNo());
		}

		// Checks image type inconsistency.
		String aImageType = (String) images.get(propertyName);

		if (aImageType != null && !aImageType.equals(sImageType)) {
			throw new FPGException(
					  "Property '" +  propertyName + "' "
					+ "was declared as image type "
					+ "'" + aImageType + "' previously." + lineNo());
		}

		images.put(propertyName, sImageType);

		ImageInstruction $image =
				new ImageInstruction(label, imageType, propIndex);

		instructions.add($image);
	}

	private void gen$Font() throws FPGException {

		if (tokens.length != 2) {
			throw new FPGException("Invalid font statement." + lineNo());
		}

		String sExpr = tokens[1];
		Expression expr = getExpression(sExpr, TYPE_STRING);

		try {
			FontInstruction $font = new FontInstruction(label, expr);

			instructions.add($font);
		} catch (IllegalArgumentException e) {
			throw new FPGException(
					"Invalid font statement. " + e.getMessage() + lineNo());
		}
	}

	/**
	 * Generates a <b>XYBASE</b> <i>FPG</i> statement for the current PDF
	 * Object in process of compilation.
	 * 
	 * <p> <b>IMPORTANT NOTE:</b> This method wouldn't be called from
	 * out of <i>FPG</i> framework.
	 * </p>
	 * 
	 * @throws FPGException If cannot generate xybase statement.
	 */
	public void gen$XYBase() throws FPGException {

		if (tokens.length != 2) {
			throw new FPGException("Invalid xybase statement." + lineNo());
		}

		String xyBase = tokens[1];

		if (xyBase.equals("")) {
			throw new FPGException("Invalid xybase statement." + lineNo());
		}

		XYBaseInstruction $xyBase = new XYBaseInstruction(label, xyBase);

		instructions.add($xyBase);
	}

	private void invalidCache(String cacheName) throws FPGException {

		int index = getCacheIndex(cacheName);

		if (index == NOT_FOUND) {
			throw new FPGException(
					"Undefined cache name '" + cacheName + "'." + lineNo());
		}

		throw new FPGException(
				"Cache block '" + cacheName + "'was closed." + lineNo());
	}

	private Expression getExpression(String token, int propertyType)
			throws FPGException {

		if (token.length() == 0) {
			invalidExpression(token);
		}

		// Checks variable expression type.
		if (token.charAt(0) == '$') {
			if (token.length() == 1) {
				invalidExpression(token);
			}

			String variableName = token.substring(1);

			int[] variableDescriptor = getVariableDescriptor(variableName);

			if (variableDescriptor == null) {
				throw new FPGException(
						  "Undeclared variable '" + variableName + "'."
						+ lineNo());
			}

			return
					new Expression(
							EXP_TYPE_VARIABLE,
							variableDescriptor[TYPE],
							variableDescriptor[INDEX]);
		}

		// Checks property expression type.
		if (FPGCode.isID(token)) {
			String propertyName = token;
			int[] propertyDescriptor = getPropertyDescriptor(propertyName);
			int propIndex;

			if (propertyDescriptor != null) {
				if (propertyDescriptor[TYPE] != propertyType) {
					throw new FPGException(
							  "Expression type "
							+ "'" + FPGCode.getTypeName(propertyType) + "' "
							+ "was expected." + lineNo());
				}

				propIndex = propertyDescriptor[INDEX];
			} else {
				propIndex = addProperty(propertyName, propertyType);
			}

			return
					new Expression(
							EXP_TYPE_PROPERTY,
							propertyType,
							propIndex);
		}

		// Checks literal expression type (BOOLEAN).
		if (token.equals("true") || token.equals("false")) {
			return new Expression(TYPE_BOOLEAN, new Boolean(token));
		}

		// Checks literal expression type (STRING).
		if (token.charAt(0) == '"') {
			if (token.length() < 2) {
				invalidExpression(token);
			}

			int lastIndex = token.length() - 1;

			if (token.charAt(lastIndex) != '"') {
				invalidExpression(token);
			}

			return
					new Expression(
							TYPE_STRING,
							token.substring(1, lastIndex).replace('"', '\''));
		}

		// Checks literal expression type (INT).
		int value = 0;

		try {
			value = Integer.parseInt(token);
		} catch (NumberFormatException e) {
			invalidExpression(token);
		}

		return new Expression(TYPE_INT, new Integer(value));
	}

	private void invalidExpression(String token) throws FPGException {

		throw new FPGException(
				"Invalid expression '" + token + "'." + lineNo());
	}

	private int[] getVariableDescriptor(String name) {
		return getNamedObjectDescriptor(name, variables);
	}

	private int getVariableIndex(String name, int type) {
		return getNamedObjectIndex(name, type, variables);
	}

	private int addVariable(String name, int type) throws FPGException {
		int[] variableDescriptor = getVariableDescriptor(name);

		if (variableDescriptor != null) {
			if (variableDescriptor[TYPE] != type) {
				String sType = FPGCode.getTypeName(variableDescriptor[TYPE]);

				throw new FPGException(
						  "Variable '" + name + "' "
						+ "was defined previously as "
						+ "'" + sType + "'." + lineNo());
			}

			return variableDescriptor[INDEX];
		}

		if (!FPGCode.isID(name)) {
			throw new FPGException(
					"Bad variable name '" + name + "'." + lineNo());
		}

		variables[type].add(new Variable(name));

		return variables[type].size() - 1;
	}

	private int[] getPropertyDescriptor(String name) {
		return getNamedObjectDescriptor(name, properties);
	}

	private int addProperty(String name, int type) throws FPGException {
		int[] propertyDescriptor = getPropertyDescriptor(name);

		if (propertyDescriptor != null) {
			if (propertyDescriptor[TYPE] != type) {
				String typeName = FPGCode.getTypeName(propertyDescriptor[TYPE]);

				throw new FPGException(
						  "Property '" + name + "' "
						+ "was defined  as '" + typeName + "' "
						+ "previously." + lineNo());
			}

			return propertyDescriptor[INDEX];
		}

		if (!FPGCode.isID(name)) {
			throw new FPGException(
					"Bad property name '" + name + "'." + lineNo());
		}

		/*
		 *  Checks property types inconsistency globally.
		 */
		Integer intType = (Integer) globalProperties.get(name);

		if (intType == null) {
			globalProperties.put(name, new Integer(type));
		} else if (intType.intValue() != type) {
			String typeName = FPGCode.getTypeName(intType.intValue());

			throw new FPGException(
					  "Property '" + name + "' was defined as "
					+ "'" + typeName + "' previously." + lineNo());
		}

		properties[type].add(new Property(name));

		return properties[type].size() - 1;
	}

	private int getCacheIndex(String name) {
		return getNamedObjectIndex(name, 0, caches);
	}

	private int addCache(String name) throws FPGException {

		int index = getCacheIndex(name);

		if (index != NOT_FOUND) {
			return index;
		}

		if (!FPGCode.isID(name)) {
			throw new FPGException(
					"Bad cache name '" + name + "'." + lineNo());
		}

		caches[0].add(new Cache(name));

		return caches[0].size() - 1;
	}

	private int[] getNamedObjectDescriptor(String name, List[] names) {
		for (int type = 0; type < names.length; type++) {
			int index = getNamedObjectIndex(name, type, names);

			if (index != NOT_FOUND) {
				return new int[] { type, index };
			}
		}

		return null;
	}

	private int getNamedObjectIndex(String name, int type, List[] names) {
		int index = 0;
		Iterator namedObjectsIT = names[type].iterator();

		while (namedObjectsIT.hasNext()) {
			NamedObject namedObject = (NamedObject) namedObjectsIT.next();

			if (namedObject.name.equals(name)) {
				return index;
			}

			index++;
		}

		return NOT_FOUND;
	}

	/* UNUSED.
	private NamedObject getNamedObject(String name, int type, List[] names) {
		Iterator namedObjectsIT = names[type].iterator();

		while (namedObjectsIT.hasNext()) {
			NamedObject namedObject = (NamedObject) namedObjectsIT.next();

			if (namedObject.name.equals(name)) {
				return namedObject;
			}
		}

		return null;
	}
	*/

	private boolean isDisposableLine() {
		if (lineLength == 1) {
			return true;
		}

		return
				   (line[0] == '%' && line[1] == '%')
				|| (line[0] == '\r' && line[1] == '\n');
	}

	private int readLine() {
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
				log("Line exceed " + MAX_LINE_SIZE + " bytes. Line truncated.");

				while ((ch = input.read()) != EOF && ch != '\n') {}

				if (ch == EOF) {
					log("Unexpected EOF. Missing end of line character.");
				}
			} else if (lineLength > 0) {
				log("Unexpected EOF. Missing end of line character.");
			}
		}

		return lineLength;
	}

	private String[] getTokens(String stmt) {
		String[] tokens = stmt.trim().split(":");

		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = tokens[i].trim();
		}

		return tokens;
	}

	private String lineNo() {
		return " [line=" + lineNo + "]";
	}

	private void log(String message) {
		if (verbose) {
			System.out.println("FPGCompiler: " + message);
		}
	}

	/* UNUSED.
	private void logn(String message) {
		if (verbose) {
			System.out.print("FPGCompiler: " + message);
		}
	}
	*/

	/* UNUSED.
	private void log(byte[] array) {
		if (verbose) {
			logn("\n" + new String(array));
		}
	}
	*/
}
