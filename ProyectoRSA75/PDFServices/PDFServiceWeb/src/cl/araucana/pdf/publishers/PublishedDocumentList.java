

/*
 * @(#) PublishedDocumentList.java    1.0 20-08-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers;


/**
 * This interface defines a common mechanism to navigate (iterate) over a
 * collection of published PDF document instances taking care of allocated
 * system resources. Navigation works in forward only mode.
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
 *            <TD> 20-08-2008 </TD>
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
public interface PublishedDocumentList {

    /**
     * Indicates if there is a next document in this collection. If a next
     * document is available this will be accessible through
     * {@link #getPublishedDocument()}.
     * 
     * @return <code>true</code> when there is a next document in this
     *         collection, <code>false</code> otherwise.
     *         
     * @throws PDFPublisherException If cannot access to the collection.
     * 
     * @see #getPublishedDocument()
     */
    public boolean next() throws PDFPublisherException;

    /**
     * Gets the current positioned document in the collection.
     *   
     * @return Current positioned document in the collection.
     * 
     * @throws PDFPublisherException If cannot access to the collection.
     * 
     * @see #next()
     */
    public PublishedDocument getPublishedDocument()
    		throws PDFPublisherException;

    /**
     * Closes and finishes the collection of documents. Allocated system
     * resources must be disposed. Before closed the collection will not
     * be navigatable.
     * 
     * @throws PDFPublisherException If cannot close the collection normally.
     */
    public void close() throws PDFPublisherException;
}
