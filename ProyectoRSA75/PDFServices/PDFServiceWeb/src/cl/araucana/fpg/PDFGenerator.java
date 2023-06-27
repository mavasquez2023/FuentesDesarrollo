

/*
 * @(#) PDFGenerator.java    1.0.1 05-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg;


import java.util.zip.Deflater;

import cl.araucana.core.util.ByteArray;
import cl.araucana.core.util.XClass;

import cl.araucana.fpg.compiled.CompiledPDFObject;


/**
 * PDF documents generator based on a {@link cl.araucana.fpg.PDFTemplate}. This
 * is the central <i>FPG</i> component.
 *
 * <p> This generator requires a compiled PDF template
 * (see @{link PDFObjectCompiler}) that provides PDF document structure to
 * produce PDF document instances. Business data that completes content for
 * each PDF document instance are provided by ad-hoc {@link DocumentModel}
 * instances.
 * </p>
 *
 * <BR>
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
 *            <TD> 05-04-2008 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 12-03-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Corrección de memory leak producido por hacer mal
 *                 uso de la clase Deflater </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0.1
 */
public class PDFGenerator {

	/**
	 * PDF Generator's Version.
	 */
	public static final String VERSION = "1.0";

	private PDFTemplate template;
	private Class documentModelClass;

	private int sequenceNumber;

	private boolean debugLinker;
	private boolean debugExecutor;

	private CompiledPDFObject[] compiledObjects = new CompiledPDFObject[0];

	private Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);

	/**
	 * Validates if a class implements {@link DocumentModel}
	 *
	 * @param clazz Class to be validated.
	 *
	 * @return <code>true</code> if specified class is validated one,
	 *         otherwise <code>false</code>.
	 */
	public static boolean isValidDocumentModelClass(Class clazz) {

		XClass xClass = new XClass(clazz);

		return xClass.implementsInterface(DocumentModel.class);
	}

	/**
	 * Constructs a PDF Generator instance to produce PDF document instances
	 * with the specified PDF template and document model class.
	 *
	 * @param template PDF template.
	 *
	 * @param documentModelClass document model class.
	 *
	 * @throws FPGException If PDF Generator cannot be constructed.
	 */
	public PDFGenerator(PDFTemplate template, Class documentModelClass)
			throws FPGException {

		if (!template.isFreezed()) {
			throw new FPGException(
					"Template '" + template.getName() + "' is not freezed");
		}

		if (!isValidDocumentModelClass(documentModelClass)) {
			throw new FPGException(
					  "Invalid document model class "
					+ "'" + documentModelClass + "'.");
		}

		this.template = template;
		this.documentModelClass = documentModelClass;
	}

	/**
	 * Gets associated PDF Template.
	 *
	 * @return Associoated PDF Template.
	 */
	public PDFTemplate getTemplate() {
		return template;
	}

	/**
	 * Gets associated Document model class.
	 *
	 * @return Associated Document model class.
	 */
	public Class getDocumentModelClass() {
		return documentModelClass;
	}

	/**
	 * Controls linker debug mode.
	 *
	 * @param debug Linker debug mode control flag.
	 *
	 * @see #isDebugLinker()
	 */
	public void setDebugLinker(boolean debug) {
		debugLinker = debug;
	}

	/**
	 * Indicates if linker debug mode is enabled.
	 *
	 * @return <code>true</code> if linker debug mode is enabled,
	 *         otherwise <code>false</code>.
	 *
	 * @see #setDebugLinker(boolean)
	 */
	public boolean isDebugLinker() {
		return debugLinker;
	}

	/**
	 * Controls executor debug mode.
	 *
	 * @param debug Control executor debug mode flag.
	 *
	 * @see #isDebugExecutor()
	 */
	public void setDebugExecutor(boolean debug) {
		debugExecutor = debug;
	}

	/**
	 * Indicates if executor debug mode is enabled.
	 *
	 * @return <code>true</code> if executor debug mode is enabled,
	 *         otherwise <code>false</code>.
	 *
	 * @see #setDebugExecutor(boolean)
	 */
	public boolean isDebugExecutor() {
		return debugExecutor;
	}

	/**
	 * Generates a new PDF document instance from the respective document model
	 * instance.
	 *
	 * @param documentModel Document model instance.
	 *
	 * @param referenceSize Reference size in bytes.
	 *
	 * @return PDF document.
	 */
	public PDFDocument newPDFDocument(DocumentModel documentModel,
			int referenceSize) {

		return
				new PDFDocument(
						this, documentModel, ++sequenceNumber, referenceSize);
	}

	/**
	 * Deflates a PDF Object in memory for the current PDF document.
	 *
	 * @param object PDF Object.
	 *
	 * @throws FPGException If cannot deflate PDF object.
	 */
	public void deflate(PDFObject object) throws FPGException {

		if (object.getExtension() == PDFTemplate.EXTENSION_NORMAL) {
			throw new FPGException("Unsupported normal extension");
		}

		/*
		 * <<
		 * /Filter /FlateDecode /Length 118 >>
		 * stream
		 * CDATAendstream
		 * endobj
		 */
		byte[] data = object.getXData();
		byte[] cData = new byte[data.length];

		deflater.setInput(data);
		deflater.finish();

		int deflatedLength = deflater.deflate(cData);

		if (deflatedLength == cData.length) {
			cData = new byte[2 * data.length];

			deflater.reset();
			deflater.setInput(data);
			deflater.finish();

			deflatedLength = deflater.deflate(cData);
		}

		deflater.reset();

		byte[] deflatedHeader =
				(  "<<\n"
				 + "/Filter /FlateDecode /Length " + deflatedLength + " >>\n"
				 + "stream\n").getBytes();

		byte[] deflatedTrailer = ("endstream\nendobj\n").getBytes();

		int totalLength =
				  deflatedHeader.length
				+ deflatedLength
				+ deflatedTrailer.length;

		byte[] xData = new byte[totalLength];
		int offset = 0;

		offset = ByteArray.copy(deflatedHeader, xData, offset);
		offset = ByteArray.copy(cData, 0, xData, offset, deflatedLength);
		offset = ByteArray.copy(deflatedTrailer, xData, offset);

		object.setXData(xData);
	}

	/**
	 * Resets sequence numbering to generated PDF document instances.
	 *
	 * @throws FPGException If cannot reset.
	 */
	public void reset() throws FPGException {

		sequenceNumber = 0;
	}

	/**
	 * Links an array of compiled PDF objects for prepare executor.
	 *
	 * @param objects Compiled PDF objects.
	 *
	 * @throws FPGException If cannot link PDF objects.
	 */
	public void link(PDFObject[] objects) throws FPGException {

		compiledObjects = new CompiledPDFObject[objects.length];

		for (int i = 0; i < compiledObjects.length; i++) {
			objects[i].setLinkID(i);

			compiledObjects[i] =
					new CompiledPDFObject(
							objects[i].getObjectName(),
							objects[i].getData(),
							documentModelClass,
							debugLinker);
		}

		if (debugLinker) {
			System.err.println(
					  "FPGLinker: " + compiledObjects.length + " "
					+ "compiled PDF objects were linked successufully.");
		}
	}

	/**
	 * Executes a linked PDF object in the context of a PDF document instance.
	 *
	 * @param document PDF document.
	 *
	 * @param object Linked PDF object.
	 *
	 * @throws FPGException If execution is failed.
	 */
	public void execute(PDFDocument document, PDFObject object)
			throws FPGException {

		CompiledPDFObject compiledObject = compiledObjects[object.getLinkID()];
		byte[] data = compiledObject.execute(document);

		if (debugExecutor) {
			System.err.println(
					  "PDFGenerator: START Running linked PDF object "
					+ "'" + object.getObjectName() +"' ...");

			System.err.write(data, 0, data.length);

			System.err.println(
					  "PDFGenerator: END Running linked PDF object "
					+ "'" + object.getObjectName() +"'.");
		}

		object.setXData(data);
	}

	/**
	 * Closes this instance. No more PDF documents must be generated.
	 */
	public void close() {
		deflater.end();
	}
}
