

/*
 * @(#) SPIContext.java    1.0 11-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.implementations.fpg;


import cl.araucana.pdf.publishers.PDFPublisherException;


/**
 * This is an abstract base class to implement different operational contexts
 * within <b>PDF Publisher Service Provider Interface</b> (SPI). The most
 * natural ones are: {@link PublicationSPIContext} to publication operations
 * and {@link SelectionSPIContext} to retrieval operations.
 * 
 * <p> All <b>SPI Contexts</b> shares common data structures to operate over
 * a particular document type and version.
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
 *            <TD> 11-10-2008 </TD>
 *            <TD align="center">  1.0 </TD>
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
public abstract class SPIContext implements DocTypeConstants {

	/**
	 * SPI context ID.
	 */
	protected int contextID;

	/**
	 * Associated SPI instance. 
	 */
	protected FPGIntegratedPDFPublisherSPI spi;

	/**
	 * Document type name.
	 */
	protected String docTypeName;
	
	/**
	 * Document type version. 
	 */
	protected int docVersion;
	
	/**
	 * Document type base bame. 
	 */
	protected String baseName;

	/**
	 * Document type fields.
	 */
	protected DocField[] docFields;
	
	/**
	 * Document type field names that belongs to the document ID.
	 */	
	protected String[] docIDFieldNames;

	/**
	 * Document type field types to all fields.
	 */
	protected int[] docIndexFieldTypes;

	/**
	 * Document type field types to fields that belongs to the document ID.
	 */	
	protected int[] docIDFieldTypes;
	
	/**
	 * Document type field indexes to fields that belongs to the document ID.
	 */	
	protected int[] docIDFieldIndexes;

	/**
	 * Constructs a SPI context associated to the <code>spi</code> to operate
	 * over the specified document type and version.
	 * 
	 * @param spi Associated spi instance.
	 * 
	 * @param docTypeName Document type name.
	 * 
	 * @param docVersion Document type version.
	 * 
	 * @throws PDFPublisherException If cannot construct SPI context.
	 */
	protected SPIContext(FPGIntegratedPDFPublisherSPI spi, String docTypeName,
			int docVersion)	throws PDFPublisherException {

		DocType docType = spi.getDocType(docTypeName);

		if (docType == null) {
			throw new PDFPublisherException(
					"Unknown document type '" + docTypeName + "'");
		}

		this.spi = spi;
		this.docTypeName = docTypeName;
		this.docVersion = docVersion;
		this.baseName = docType.getBaseName();

		docFields = spi.getDocFields(docType.getID());
		docIDFieldNames = spi.getDocIDFieldNames(docType.getID());

		/*
		 *  docIndex's field types.
		 */
		docIndexFieldTypes = new int[docFields.length];

		for (int i = 0; i < docFields.length; i++) {
			DocField field = docFields[i];

			docIndexFieldTypes[i] = field.getType();
		}

		/*
		 *  docID's field indexes and types.
		 */
		docIDFieldIndexes = new int[docIDFieldNames.length];
		docIDFieldTypes = new int[docIDFieldNames.length];

		for (int i = 0; i < docIDFieldNames.length; i++) {
			String docIDFieldName = docIDFieldNames[i];

			for (int j = 0; j < docFields.length; j++) {
				DocField field = docFields[j];

				if (field.getName().equals(docIDFieldName)) {
					docIDFieldIndexes[i] = j;
					docIDFieldTypes[i] = field.getType();

					break;
				}
			}
		}
	}

	/**
	 * Gets SPI context ID.
	 * 
	 * @return SPI context ID.
	 */
	protected int getContextID() {
		return contextID;
	}
	
	/**
	 * Derives a document ID from its document index.
	 * 
	 * @param indexValues Document Index.
	 * 
	 * @return Document ID.
	 */
	protected String getDocID(Object[] indexValues) {
		String docID = "";

		for (int i = 0; i < docIDFieldIndexes.length; i++) {
			docID += indexValues[docIDFieldIndexes[i]];

			if (i + 1 < docIDFieldIndexes.length) {
				docID += "/";
			}
		}

		return docID;
	}

	/**
	 * Logs <code>message</code> if associated spi class has enabled the
	 * debug mode.
	 * 
	 * @param message Message to be logged.
	 */
	protected void log(String message) {
		if (FPGIntegratedPDFPublisherSPI.debug) {
			System.err.println(message);
		}
	}
}
