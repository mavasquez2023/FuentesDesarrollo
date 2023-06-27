

/*
 * @(#) UnrestrictedPDFPublisherFilter.java    1.0 16-10-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.pdf.publishers;


import cl.araucana.fpg.PDFDocument;


/**
 * This is a concrete implementation of {@link BasePDFPublisherFilter} that
 * gives access to publish/unpublish and retrieve any PDF document instance
 * independent of the security context. It can be very useful to support
 * unrestricted access by default in a {@link PDFPublisher} implementation.
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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 16-10-2008 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Germ�n Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versi�n inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germ�n Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class UnrestrictedPDFPublisherFilter extends BasePDFPublisherFilter {

	/**
	 * A request to retrieve the specified published PDF document is permitted
	 * always.
	 * 
	 * @param publishedDocumentInfo Published PDF document to be checked.
	 * 
	 * @return <code>false</code>
	 */
	public boolean filter(PublishedDocumentInfo publishedDocumentInfo) {
		return false;
	}

	/**
	 * A request to publish/unpublish the specified published PDF
	 * document is permitted always.
	 * 
	 * @param document PDF document to be checked.
	 * 
	 * @return <code>false</code>.
	 */
	public boolean filter(PDFDocument document) {
		return false;
	}
}
