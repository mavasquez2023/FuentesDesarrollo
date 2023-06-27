

/*
 * @(#) PageType.java    1.0 29-08-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdfservice.producers;


import java.io.IOException;

import cl.araucana.fpg.PDFObject;
import cl.araucana.fpg.PDFPage;
import cl.araucana.fpg.PDFTemplate;

import cl.araucana.pdfservice.Constants;


/**
 * PDF Page Type descriptor to be used by
 * {@link  cl.araucana.pdfservice.PDFProducer}. This class encapsulates PDF
 * objects and other information that describes a PDF page type to be produced.
 * The following figure show structure of a PDF page:
 *
 * <p align="center">
 * <img src="{@docRoot}/extras/PDFPage.gif">
 * </p>
 * 
 * <p> The background and/or foreground PDF objects of a page type can be setted
 * to be executed by <i>FPG</i>'s generator when it be necessary to produce
 * PDF pages of that page type. The correspondig
 * {@link cl.araucana.fpg.PDFTemplate} will provide the compiled PDF objects
 * required in the execution.
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
 *            <TD> 29-08-2008 </TD>
 *            <TD align="center">  2.0 </TD>
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
public class PageType implements Constants {

	private PDFTemplate template;
	private int pageNo;
	private PDFPage page;

	private int[] contentObjIDs = { 0, 0, 0 };

	private PDFObject bg;
	private PDFObject fg;

	private byte[] header;

	/**
	 * Constructs a page type instance from a
	 * {@link cl.araucana.fpg.PDFTemplate}.
	 * 
	 * @param template Page Type PDF Template.
	 * 
	 * @param pageNo Page Type number.
	 */
	public PageType(PDFTemplate template, int pageNo) {
		this.template = template;
		this.pageNo = pageNo;

		page = template.getPage(pageNo);

		setPrologObjID(page.getContentObjID(CONTENT_PROLOG));
		setBGObjID(page.getContentObjID(CONTENT_BACKGROUND));
		setFGObjID(page.getContentObjID(CONTENT_FOREGROUND));
	}

	/**
	 * Gets page type PDF Template.
	 * 
	 * @return Page Type PDF Template.
	 */
	public PDFTemplate getTemplate() {
		return template;
	}

	/**
	 * Gets page type number.
	 * 
	 * @return Page type number.
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * Gets associated PDF page.
	 * 
	 * @return Associated PDF page.
	 */
	public PDFPage getPage() {
		return page;
	}

	/**
	 * Establishes that this PDF page's background will be executed with
	 * <i>FPG</i> to produce PDF documents.
	 *  
	 * @throws IOException If cannot load PDF page's background.
	 * 
	 * @see #getBG()
	 */
	public void setBG() throws IOException {

		bg = template.getObject(getBGObjID(), PDFTemplate.EXTENSION_COMPILED);
	}

	/**
	 * Gets PDF object to be executed as this PDF page's background.
	 * 
	 * @return PDF page's background or <code>null</code> if this page type
	 *         will not execute its background with <i>FPG</i>.
	 *         
	 * @see #setBG()
	 */
	public PDFObject getBG() {
		return bg;
	}

	/**
	 * Establishes that this PDF page's foreground will be executed for
	 * <i>FPG</i> to produce PDF documents.
	 *  
	 * @throws IOException If cannot load PDF page's foreground.
	 * 
	 * @see #getFG()
	 */	
	public void setFG() throws IOException {

		fg = template.getObject(getFGObjID(), PDFTemplate.EXTENSION_COMPILED);
	}

	/**
	 * Gets PDF object to be executed as this PDF page's foreground.
	 * 
	 * @return PDF page's background or <code>null</code> if this page type
	 *         will not execute its foreground with <i>FPG</i>.
	 *         
	 * @see #setFG()
	 */	
	public PDFObject getFG() {
		return fg;
	}

	/**
	 * Sets PDF Object ID to be used as prolog for this page type.
	 * 
	 * @param prologObjID Page type prolog ID. 
	 * 
	 * @see #getPrologObjID()
	 */
	public void setPrologObjID(int prologObjID) {
		contentObjIDs[CONTENT_PROLOG] = prologObjID;
	}

	/**
	 * Gets PDF Object ID to be used as prolog for this page type.
	 * 
	 * @return Page type prolog ID.
	 * 
	 * @see #setPrologObjID(int)
	 */
	public int getPrologObjID() {
		return contentObjIDs[CONTENT_PROLOG];
	}

	/**
	 * Sets PDF Object ID to be used as background for this page type.
	 * 
	 * @param bgObjID Page type background ID. 
	 * 
	 * @see #getBGObjID()
	 */	
	public void setBGObjID(int bgObjID) {
		contentObjIDs[CONTENT_BACKGROUND] = bgObjID;
	}

	/**
	 *  Gets PDF Object ID to be used as background for this page type.
	 *  
	 * @return Page type background ID.
	 * 
	 * @see #setBGObjID(int)
	 */
	public int getBGObjID() {
		return contentObjIDs[CONTENT_BACKGROUND];
	}

	/**
	 * Sets PDF Object ID to be used as foreground to this page type.
	 * 
	 * @param fgObjID Page type foreground ID.
	 * 
	 * @see #getFGObjID()
	 */	
	public void setFGObjID(int fgObjID) {
		contentObjIDs[CONTENT_FOREGROUND] = fgObjID;
	}

	/**
	 *  Gets PDF Object ID to be used as foreground for this page type.
	 *  
	 * @return Page type foreground ID.
	 * 
	 * @see #setFGObjID(int)
	 */	
	public int getFGObjID() {
		return contentObjIDs[CONTENT_FOREGROUND];
	}

	/**
	 * Gets all PDF object IDs for this page type.
	 * 
	 * @return Array of page type object IDs.
	 * 
	 * @see #getPrologObjID()
	 * @see #getBGObjID()
	 * @see #getFGObjID()
	 */
	public int[] getContentObjIDs() {
		return contentObjIDs;
	}

	/**
	 * Sets page type header content.
	 * 
	 * @param header Page type header content.
	 * 
	 * @see #getHeader()
	 */
	public void setHeader(byte[] header) {
		this.header = header;
	}

	/**
	 * Gets page type header content.
	 * 
	 * @return Page type header content.
	 * 
	 * @see #setHeader(byte[])
	 */
	public byte[] getHeader() {
		return header;
	}

	/**
	 * Returns a string representation of the index as multiple lines
	 * one per each property indicating its name and value.
	 * 
	 * @return String representation.
	 */	
	public String toString() {
		return
				  "pageNo=" + pageNo + " "
				+ "prologObjID=" + getPrologObjID() + " "
				+ "bgObjID=" + getBGObjID() + " "
				+ "fgObjID=" + getFGObjID();
	}
}
