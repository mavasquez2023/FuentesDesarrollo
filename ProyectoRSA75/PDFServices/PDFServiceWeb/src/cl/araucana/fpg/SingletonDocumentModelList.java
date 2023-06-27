

/*
 * @(#) SingletonDocumentModelList.java    1.0 25-06-2009
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
 *            <TD> 25-06-2009 </TD>
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
public class SingletonDocumentModelList implements DocumentModelList {

	private DocumentModel docModel;
	private int next = -1;
	
	/**
	 * Constructs a singleton document model list.
	 */
	public SingletonDocumentModelList(DocumentModel docModel) {
		this.docModel = docModel;
	}
	
    /**
     * {@inheritDoc}
     * 
     * @return <code>1</code> always.
     */	
    public int size() {
		return 1;
	}

    /**
     * {@inheritDoc}
     */    
    public boolean next() throws IOException {

		return ++next == 0;
	}

    /**
     * {@inheritDoc}
     */    
    public DocumentModel getDocumentModel() throws IOException  {

    	if (next > 0) {
    		throw new IOException("No more document models");
    	}
    	
    	return docModel;
	}

    /**
     * {@inheritDoc}
     */
	public final void close() {
	}
}
