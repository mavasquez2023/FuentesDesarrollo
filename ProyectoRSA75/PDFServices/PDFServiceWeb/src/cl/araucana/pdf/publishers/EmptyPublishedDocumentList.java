

package cl.araucana.pdf.publishers;


/*
 * @(#) EmptyPublishedDocumentList.java    1.0 21-08-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 21-08-2008 </TD>
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
