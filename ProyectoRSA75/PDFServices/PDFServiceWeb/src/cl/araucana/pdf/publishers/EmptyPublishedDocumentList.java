

package cl.araucana.pdf.publishers;


/*
 * @(#) EmptyPublishedDocumentList.java    1.0 21-08-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


/**
 * This class implements an empty collection of published PDF documents. It
 * can be useful to border conditions to retrieval operations.
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
 *            <TD> 21-08-2008 </TD>
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
public class EmptyPublishedDocumentList implements PublishedDocumentList {

	/**
	 * Constructs an empty collection of documents.
	 */
	public EmptyPublishedDocumentList() {
	}
	
    /**
     * Never will be a next available document in the collection.
     * 
     * @return <code>false</code>.
     */
    public boolean next() {
		return false;
	}

    /**
     * No next document is available in the collection.
     * 
     * @return <b>never return</b>.
     * 
     * @throws PDFPublisherException Always because collection is empty.
     */
    public PublishedDocument getPublishedDocument()
    		throws PDFPublisherException {

		throw new PDFPublisherException("Not more published documents.");
	}

    /**
     * {@inheritDoc}
     */
    public void close() {
	}
}
