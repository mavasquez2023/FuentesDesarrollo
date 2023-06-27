

/*
 * @(#) DocumentModel.java    1.0 05-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg;


import java.io.Serializable;


/**
 * Abstraction to represent an indexable and identifiable document
 * to be produced in any format. In particular, <i>FPG</i> framework uses
 * this interface to produce documents in PDF format.
 *
 * <p> A document model must be organized in one or more logical pages and
 * to be prepared to iterate over them in a <u>forward</u> only mode for
 * one only time (see {@link #hasMorePages()}). Additionally, every page has
 * an associated type. A document model is structured based on one or
 * more page types.
 * </p>
 * 
 * <p> A document model is indexable through a string value (<b>docIndex</b>)
 * that represents one or more concepts (business ones, for example) named
 * <u>component parts</u>. This component parts must be separated by
 * <b>'/'</b> character.
 * </p>
 * 
 * <p> Finally, a document model must provides a document ID (<b>docID</b>) as
 * a string value. This ID must be unique within a known business domain in
 * particular. It has the same syntax that <b>docIndex</b> and must be
 * derivable from it.
 * </p>
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
 *            <TD> 05-04-2008 </TD>
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
public interface DocumentModel extends Serializable {

	/**
	 * Gets document ID.
	 * 
	 * @return Document ID.
	 * 
	 * @see #docIndex()
	 */
	public String docID();

	/**
	 * Gets document Index.
	 * 
	 * @return Document Index.
	 * 
	 * @see #docID()
	 */
	public String docIndex();

	/**
	 * Indicates if exists a next logical page for this document.
	 *  
	 * @return <code>true</code> if exist a next page, otherwise
	 *         <code>false</code>.
	 */
	public boolean hasMorePages();

	/**
	 * Gets page type to the current logical page.
	 * 
	 * @return Current page's type.
	 */
	public int pageType();
}
