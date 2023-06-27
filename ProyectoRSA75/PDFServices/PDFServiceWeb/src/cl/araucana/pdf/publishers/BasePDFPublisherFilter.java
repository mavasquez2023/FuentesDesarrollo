

/*
 * @(#) BasePDFPublisherFilter.java    1.0 16-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 * This is an abstract base class that adds to {@link PDFPublisherFilter}
 * facilities to act over a {@link PDFPublisher} instance and to parse the
 * document index of a PDF document instance in its component parts to simplify
 * further concrete implementations.
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
 *            <TD> 16-10-2008 </TD>
 *            <TD align="center"> 1.0 </TD>
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
public abstract class BasePDFPublisherFilter implements PDFPublisherFilter {

	/**
	 * {@link PDFPublisher} instance that requires this filter.
	 */
	protected PDFPublisher pdfPublisher;

	private static Pattern pattern;
	
	static {
		try {
			pattern = Pattern.compile("/");
		} catch (PatternSyntaxException e) {}
	}
	
	/**
	 * Sets the <b>PDF Publisher</b> that requires this filter.
	 * @param pdfPublisher
	 */
	public void setPDFPublisher(PDFPublisher pdfPublisher) {
		this.pdfPublisher = pdfPublisher;
	}

	/**
	 * Parses the document index of the specified
	 * <code>publishedDocumentInfo</code> PDF document instance in its
	 * component parts.
	 * 
	 * @param publishedDocumentInfo PDF document to be parsed.
	 * 
	 * @return Array of component parts to the document index.
	 * 
	 * @throws PDFPublisherException If document index cannot be parsed.
	 */
	protected Object[] parseIndexValues(
			PublishedDocumentInfo publishedDocumentInfo)
			throws PDFPublisherException {

		String docIndex = publishedDocumentInfo.getIndex();
		String[] values = pattern.split(docIndex);
		Field[] docIndexFields = pdfPublisher.getDocIndexFields();

		if (values.length != docIndexFields.length) {
			throw new PDFPublisherException(
					"Invalid document index '" + docIndex + "'");
		}

		Object[] indexValues = new Object[docIndexFields.length];

		for (int i = 0; i < docIndexFields.length; i++) {
			Field field = docIndexFields[i];

			switch (field.getType()) {
				case Field.TYPE_INT:
					try {
						indexValues[i] = new Integer(values[i]);
					} catch (NumberFormatException e) {
						throw new PDFPublisherException(
								"Unexpected integer value '" + values[i] + "'");
					}

					break;

				case Field.TYPE_STRING:
					indexValues[i] = values[i];

					break;

				case Field.TYPE_CHAR:

					if (values[i].length() != 1) {
						throw new PDFPublisherException(
								  "Unexpected character value "
								+ "'" + values[i] + "'");
					}

					indexValues[i] = new Character(values[i].charAt(0));

					break;
			}
		}

		return indexValues;
	}
}
