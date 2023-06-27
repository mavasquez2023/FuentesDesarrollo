

/*
 * @(#) EmptyDocumentModelList.java    1.0 05-05-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.fpg;


import java.io.IOException;


/**
 * Implements an empty {@link DocumentModelList}. It's very useful to
 * construct prototypes or to write dummy code.
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
 *            <TD> 05-05-2008 </TD>
 *            <TD align="center">  2.0 </TD>
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
public class EmptyDocumentModelList implements DocumentModelList {

	/**
	 * Constructs an empty document model list.
	 */
	public EmptyDocumentModelList() {
	}
	
    /**
     * {@inheritDoc}
     * 
     * @return <code>0</code> always.
     */	
    public int size() {
		return 0;
	}

    /**
     * {@inheritDoc}
     * 
     * @return <code>false</code> always.
     */    
    public boolean next() throws IOException {

		return false;
	}

    /**
     * {@inheritDoc}
     * 
     * @return Never returns.
     * 
     * @throws IOException Always because list is empty.
     */    
    public DocumentModel getDocumentModel() throws IOException  {

		throw new IOException("No more document models");
	}

    /**
     * {@inheritDoc}
     */
	public final void close() {
	}
}
