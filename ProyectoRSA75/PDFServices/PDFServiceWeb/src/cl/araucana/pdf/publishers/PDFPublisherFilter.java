

/*
 * @(#) PDFPublisherFilter.java    1.0 16-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers;


import cl.araucana.fpg.PDFDocument;


/**
 * This interface defines a filter that can be applied with a
 * <b>PDF Publisher</b> to restrict that PDF document instances can be
 * published/unpublished and/or retrieved depending of a specific security
 * context. For example, a filter could be based on the authenticated userID
 * that requests an operation or any combination of required business criteria.
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
public interface PDFPublisherFilter {

	/**
	 * Filters a request to retrieve the specified published PDF document.
	 * 
	 * @param publishedDocumentInfo Published PDF document to be checked.
	 * 
	 * @return <code>true</code> if the published PDF document don't must be
	 *         retrieved, <code>false</code> otherwise.
	 * 
	 * @throws PDFPublisherException If cannot apply the filter.
	 */
	public abstract boolean filter(PublishedDocumentInfo publishedDocumentInfo)
			throws PDFPublisherException;

	/**
	 * Filters a request to publish/unpublish the specified published PDF
	 * document.
	 * 
	 * @param document PDF document to be checked.
	 * 
	 * @return <code>true</code> if the PDF document don't must be
	 *         published/unpublished, <code>false</code> otherwise.
	 *         
	 * @throws PDFPublisherException If cannot apply the filter.
	 */
	public abstract boolean filter(PDFDocument document)
			throws PDFPublisherException;
}
