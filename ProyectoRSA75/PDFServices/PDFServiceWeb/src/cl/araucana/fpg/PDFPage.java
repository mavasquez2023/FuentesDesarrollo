

/*
 * @(#) PDFPage.java    1.0 11-03-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg;


import java.util.ArrayList;
import java.util.List;


/**
 * PDF Page object.
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
 *            <TD> 11-03-2008 </TD>
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
public class PDFPage implements Cloneable {

	private static final int REFERENCE_CONTENT_COUNT = 8;

	private int objID;

	private byte[] header;
	private int parentObjID;
	private List contentObjIDs = new ArrayList(REFERENCE_CONTENT_COUNT);

	/**
	 * Constructs a new PDF Page instance with the specified PDF Object ID.
	 * 
	 * @param objID PDF Object ID.
	 */
	public PDFPage(int objID) {
		this.objID = objID;
	}

	/**
	 * Sets PDF Object ID.
	 * 
	 * @param objID PDF Object ID.
	 * 
	 * @see #getObjID()
	 */
	public void setObjID(int objID) {
		this.objID = objID;
	}

	/**
	 * Gets PDF Object ID.
	 * 
	 * @return PDF Object ID.
	 * 
	 * @see #setObjID(int)
	 */
	public int getObjID() {
		return objID;
	}

	/**
	 * Sets Parent PDF Object ID.
	 * 
	 * @param objID Parent PDF Object ID.
	 * 
	 * @see #getParentObjID()
	 */
	public void setParentObjID(int objID) {
		parentObjID = objID;
	}

	/**
	 * Gets Parent PDF Object ID.
	 * 
	 * @return Parent PDF Object ID.
	 * 
	 * @see #setParentObjID(int)
	 */
	public int getParentObjID() {
		return parentObjID;
	}

	/**
	 * Adds an array of PDF Object IDs for PDF Page content.
	 * 
	 * @param objIDs PDF Object IDs.
	 * 
	 * @see #addContentObjID(int)
	 */
	public void addContentObjIDs(int[] objIDs) {
		for (int i = 0; i < objIDs.length; i++) {
			Integer newObjID = new Integer(objIDs[i]);

			contentObjIDs.add(newObjID);
		}
	}

	/**
	 * Removes a PDF Object ID from PDF Page content.
	 * 
	 * @param objID PDF Object ID to be removed.
	 * 
	 * @see #addContentObjIDs(int[])
	 */
	public void addContentObjID(int objID) {
		Integer newObjID = new Integer(objID);

		if (!contentObjIDs.contains(newObjID)) {
			contentObjIDs.add(newObjID);
		}
	}

	/**
	 * Removes all PDF Object IDs from PDF Page content.
	 */
	public void removeAllContentObjIDs() {
		contentObjIDs.clear();
	}

	/**
	 * Removes a PDF Object ID from PDF Page content.
	 * 
	 * @param objID PDF Object ID to be removed.
	 */
	public void removeContentObjID(int objID) {
		contentObjIDs.remove(new Integer(objID));
	}

	/**
	 * Gets collection of PDF Object IDs from PDF Page content.
	 * 
	 * @return Collection of PDF Object IDs.
	 */
	public List getContentObjIDs() {
		return contentObjIDs;
	}

	/**
	 * Sets PDF Object ID in the specified position in the PDF Page content.
	 * 
	 * @param index Index or position in the PDF Page content. This value must
	 *        be in the expected range 0 to <u>content size</u> - 1.
	 * 
	 * @param objID PDF Object ID.
	 * 
	 * @see #getContentObjID(int)
	 */
	public void setContentObjID(int index, int objID) {
		contentObjIDs.set(index, new Integer(objID));
	}

	/**
	 * Gets PDF Object ID to the PDF Page content in the specified position.
	 * 
	 * @param index Index or position in the PDF Page content. This value must
	 *        be in the expected range 0 to <u>content size</u> - 1.
	 *        
	 * @return PDF Object ID.
	 * 
	 * @see #setContentObjID(int, int)
	 */
	public int getContentObjID(int index) {
		return ((Integer) contentObjIDs.get(index)).intValue();
	}

	/**
	 * Sets PDF Page header content.
	 * 
	 * @param header PDF Page header content.
	 * 
	 * @see #getHeader()
	 */
	public void setHeader(byte[] header) {
		this.header = header;
	}

	/**
	 * Gets PDF Page header content.
	 * 
	 * @return PDF Page header content.
	 * 
	 * @see #setHeader(byte[])
	 */
	public byte[] getHeader() {
		return header;
	}

	/**
	 * Assembles PDF Page content.
	 * 
	 * @return PDF Page content.
	 */
	public byte[] assemble() {
		String aux =
				  "/Parent " + parentObjID + " 0 R\n"
				+ "/Contents [";

		for (int i = 0; i < contentObjIDs.size(); i++) {
			aux += contentObjIDs.get(i) + " 0 R\n";
		}

		aux += "]\n>>\nendobj\n";

		byte[] auxBytes = aux.getBytes();
		byte[] assembled = new byte[header.length + auxBytes.length];

		System.arraycopy(header, 0, assembled, 0, header.length);

		System.arraycopy(
				auxBytes, 0, assembled, header.length, auxBytes.length);

		return assembled;
	}

	/**
	 * Represents a PDF Page with its PDF Object ID.
	 * 
	 * @return String representation.
	 */
	public String toString() {
		return objID + "";
	}
}
