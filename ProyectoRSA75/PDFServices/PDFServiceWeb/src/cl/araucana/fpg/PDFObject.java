

/*
 * @(#) PDFObject.java    1.0 04-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg;


/**
 * Represents a PDF object in general. Instances of this class are persisted in
 * a PDF template where come from.
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
 *            <TD> 04-04-2008 </TD>
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
public class PDFObject {

	/**
	 * Unlinked PDF Object ID. 
	 */
	protected static final int UNLINKED_ID = -1;

	private PDFTemplate parent;

	private int objID;
	private String baseType;
	private int extension;
	private byte[] data;

	private int linkID = UNLINKED_ID;

	private int newObjID;
	private byte[] xData;

	/**
	 * Constructs a new PDF Object instance to the specified PDF Template.
	 * 
	 * @param parent PDF Template.
	 */
	public PDFObject(PDFTemplate parent) {
		this.parent = parent;
	}

	/**
	 * Gets Parent PDF Template.
	 * 
	 * @return Parent PDF Template.
	 */
	public PDFTemplate getParent() {
		return parent;
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
	 * Sets PDF Object base type.
	 * 
	 * @param baseType PDF Object base type.
	 * 
	 * @see #getBaseType()
	 */
	public void setBaseType(String baseType) {
		this.baseType = baseType;
	}

	/**
	 * Gets PDF Object base type.
	 * 
	 * @return PDF Object base type.
	 * 
	 * @see #setBaseType(String)
	 */
	public String getBaseType() {
		return baseType;
	}

	/**
	 * Sets PDF Object extension.
	 * 
	 * @param extension PDF Object extension.
	 * 
	 * See {@link PDFTemplate} to more information about extensions.
	 * 
	 * @see #getExtension()
	 */
	public void setExtension(int extension) {
		this.extension = extension;
	}

	/**
	 * Gets PDF Object extension.
	 * 
	 * @see #setExtension(int)
	 */	
	public int getExtension() {
		return extension;
	}

	/**
	 * Sets PDF object content.
	 * 
	 * @param data PDF object content.
	 * 
	 * @see #getData()
	 */
	public void setData(byte[] data) {
		this.data = data;
	}

	/**
	 * Gets PDF object content.
	 * 
	 * @return PDF object content.
	 * 
	 * @see #setData(byte[])
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * Indicates if this PDF object is linked or not.
	 * 
	 * @return <code>true</code> if this PDF object is linked,
	 *         otherwise <code>false</code>.
	 *         
	 * @see #setLinkID(int)
	 */
	public boolean isLinked() {
		return linkID != UNLINKED_ID;
	}

	/**
	 * Sets new (alternative) PDF object ID.
	 * 
	 * @param objID New PDF object ID.
	 * 
	 * @see #getNewObjID()
	 */
	public void setNewObjID(int objID) {
		newObjID = objID;
	}

	/**
	 * Gets new PDF object ID.
	 * 
	 * @return New PDF object ID.
	 * 
	 * @see #setNewObjID(int)
	 */
	public int getNewObjID() {
		return newObjID;
	}

	/**
	 * Sets extended PDF object content.
	 * 
	 * @param data Extended PDF object content.
	 * 
	 * @see #getXData()
	 */
	public void setXData(byte[] data) {
		xData = data;
	}

	/**
	 * Gets extended PDF object content.
	 * 
	 * @return Extended PDF object content.
	 * 
	 * @see #setXData(byte[])
	 */
	public byte[] getXData() {
		return xData;
	}

	/**
	 * Gets PDF Object name. A PDF Object name has the following syntax:
	 * <b>&lt;base Type&gt;/&lt;object ID&gt;</b>.
	 * 
	 * @return PDF Object name.
	 */
	public String getObjectName() {
		if (baseType.equals("info")) {
			return "info";
		}

		return baseType + "/" + objID;
	}

	/**
	 * Sets linked PDF Object ID.
	 * 
	 * @param linkID Linked PDF Object ID.
	 * 
	 * @see #getLinkID()
	 */
	protected void setLinkID(int linkID) {
		this.linkID = linkID;
	}

	/**
	 * Gets linked PDF Object ID.
	 * 
	 * @return Linked PDF Object ID.
	 * 
	 * @see #setLinkID(int)
	 */
	protected int getLinkID() {
		return linkID;
	}
	
	/**
	 * Returns a string representation of this PDF Object with
	 * each property indicating its name and value.
	 * 
	 * @return String representation.
	 */	
	public String toString() {
		return
				  "parent=" + parent.getName() + " "
				+ "objID=" + objID + " "
				+ "baseType=" + baseType + " "
				+ "extension=" + PDFTemplate.extensionNames[extension];
	}	
}
