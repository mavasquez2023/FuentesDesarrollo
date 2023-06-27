

/*
 * @(#) DocumentModelList.java    1.0 29-04-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.fpg;


import java.io.IOException;


/**
 * Abstraction to represent an iterable list of {@link DocumentModel}
 * instances. It's ideal to implement lists that aren't totally stored
 * in memory (high volume) or that will be processed partially in an unknown
 * fraction. 
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
 *            <TD> 29-04-2008 </TD>
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
public interface DocumentModelList {

    /**
     * Gets List size.
     * 
     * @return List size.
     * 
     * @throws IOException If an I/O error occurs.
     */
    public int size() throws IOException;
    
	/**
	 * Indicates if exists a next document model.
	 *  
	 * @return <code>true</code> if exist a next document model, otherwise
	 *         <code>false</code>.
	 *         
     * @throws IOException If an I/O error occurs.
	 */    
    public boolean next() throws IOException;
    
    /**
     * Gets current document model.
     * 
     * @return Current document model.
     * 
     * @throws IOException If an I/O error occurs.
     */
    public DocumentModel getDocumentModel() throws IOException;
    
    /**
     * Closes this list. No more document models will be available.
     * 
     * @throws IOException If an I/O error occurs.
     */
    public void close() throws IOException;
}
