

/*
 * @(#) PDFPages.java    1.0 10-03-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * PDF Pages object.
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
 *            <TD> 10-03-2008 </TD>
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
public class PDFPages implements Cloneable {

	private static final int REFERENCE_FONT_COUNT = 16;
	private static final int REFERENCE_XOBJECT_COUNT = 8;
	private static final int REFERENCE_KID_COUNT = 8;

	private int objID;

	private List fontObjRefs;
	private List xObjectObjRefs;
	private List kidObjIDs;

	private int count;

	/**
	 * Clones a PDF Pages.
	 *
	 * @param pdfPages PDF Pages to be cloned.
	 *
	 * @see #clone()
	 */
	public PDFPages(PDFPages pdfPages) {
		objID = pdfPages.objID;
		count = pdfPages.count;

		fontObjRefs =
				copyMappedObjRefList(
						pdfPages.fontObjRefs, REFERENCE_FONT_COUNT);

		xObjectObjRefs =
				copyMappedObjRefList(
						pdfPages.xObjectObjRefs, REFERENCE_XOBJECT_COUNT);

		kidObjIDs =
				new ArrayList(
						Math.max(
								pdfPages.kidObjIDs.size(),
								REFERENCE_KID_COUNT));

		kidObjIDs.addAll(pdfPages.kidObjIDs);
	}

	/**
	 * Constructs a new PDF Pages instance with the specified PDF Object ID.
	 *
	 * @param objID PDF Object ID.
	 */
	public PDFPages(int objID) {
		this.objID = objID;

		fontObjRefs = new ArrayList(REFERENCE_FONT_COUNT);
		xObjectObjRefs = new ArrayList(REFERENCE_XOBJECT_COUNT);
		kidObjIDs = new ArrayList(REFERENCE_KID_COUNT);
	}

	/**
	 * Clones this PDF Pages.
	 *
	 * see {@link #PDFPages(PDFPages)}
	 */
	public Object clone() {
		return new PDFPages(this);
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
	 * Adds a PDF Font reference to this PDF Pages.
	 *
	 * @param objRef PDF Font reference to be added.
	 *
	 * @see #removeFontObjRef(MappedObjRef)
	 */
	public void addFontObjRef(MappedObjRef objRef) {
		if (!fontObjRefs.contains(objRef)) {
			fontObjRefs.add(objRef);
		}
	}

	/**
	 * Adds a PDF XObject reference to this PDF Pages.
	 *
	 * @param objRef PDF XObject reference to be added.
	 *
	 * @see #removeXObjectObjRef(MappedObjRef)
	 */
	public void addXObjectObjRef(MappedObjRef objRef) {
		if (!xObjectObjRefs.contains(objRef)) {
			xObjectObjRefs.add(objRef);
		}
	}

	/**
	 * Adds a kid with the specified PDF Object ID.
	 *
	 * @param objID Kid PDF Object ID.
	 *
	 * @see #removeKidObjID(int)
	 */
	public void addKidObjID(int objID) {
		Integer newObjID = new Integer(objID);

		if (!kidObjIDs.contains(newObjID)) {
			kidObjIDs.add(newObjID);
		}
	}

	/**
	 * Removes a PDF Font reference from this PDF Pages.
	 *
	 * @param objRef PDF Font reference.
	 *
	 * @see #addFontObjRef(MappedObjRef)
	 */
	public void removeFontObjRef(MappedObjRef objRef) {
		fontObjRefs.remove(objRef);
	}

	/**
	 * Removes a PDF XObject reference from this PDF Pages.
	 *
	 * @param objRef PDF XObject reference.
	 *
	 * @see #addXObjectObjRef(MappedObjRef)
	 */
	public void removeXObjectObjRef(MappedObjRef objRef) {
		xObjectObjRefs.remove(objRef);
	}

	/**
	 * Removes a kPDF Object ID from this PDF Pages.
	 *
	 * @param objID Kid PDF Object ID.
	 *
	 * @see #addKidObjID(int)
	 */
	public void removeKidObjID(int objID) {
		kidObjIDs.remove(new Integer(objID));
	}

	/**
	 * Gets collection of PDF Font references.
	 *
	 * @return Collection of PDF Font references.
	 */
	public List getFontObjRefs() {
		return fontObjRefs;
	}

	/**
	 * Gets collection of PDF XObject references.
	 *
	 * @return Collection of PDF XObject references.
	 */
	public List getXObjectObjRefs() {
		return xObjectObjRefs;
	}

	/**
	 * Gets collection of Kid PDF Object IDs.
	 *
	 * @return Collection of Kid PDF Object IDs.
	 */
	public List getKidObjIDs() {
		return kidObjIDs;
	}

	/**
	 * Gets PDF Font name to a PDF Font ID.
	 *
	 * @param objID PDF Font ID.
	 *
	 * @return PDF Font name or <code>null</code> if it is unknown.
	 *
	 * @see #getFontObjID(String)
	 */
	public String getFontName(int objID) {
		return getMappedObjRefName(fontObjRefs, objID);
	}

	/**
	 * Gets PDF XObject name to a PDF XObject ID.
	 *
	 * @param objID PDF XObject ID.
	 *
	 * @return PDF XObject name or <code>null</code> if it is unknown.
	 *
	 * @see #getXObjectObjID(String)
	 */
	public String getXObjectName(int objID) {
		return getMappedObjRefName(xObjectObjRefs, objID);
	}

	/**
	 * Gets PDF Font ID to a PDF Font name.
	 *
	 * @param name PDF Font name.
	 *
	 * @return PDF Font ID or <code>-1</code> if it is unknown.
	 *
	 * @see #getFontName(int)
	 */
	public int getFontObjID(String name) {
		return getMappedObjRefObjID(fontObjRefs, name);
	}

	/**
	 * Gets PDF XObject ID to a PDF XObject name.
	 *
	 * @param name PDF XObject name.
	 *
	 * @return PDF XObject ID or <code>-1</code> if it is unknown.
	 *
	 * @see #getXObjectName(int)
	 */
	public int getXObjectObjID(String name) {
		return getMappedObjRefObjID(xObjectObjRefs, name);
	}

	/**
	 * Adjusts PDF Pages count from number of kids.
	 *
	 * @see #setCount(int)
	 */
	public void adjustCount() {
		count = kidObjIDs.size();
	}

	/**
	 * Sets PDF Pages count property.
	 *
	 * @param count PDF Pages count property.
	 *
	 * @see #adjustCount()
	 * @see #getCount()
	 */
	public void setCount(int count) {
		if (count < 0) {
			throw new IllegalArgumentException(
					"count must be non negative number");
		}

		if (count > kidObjIDs.size()) {
			throw new IllegalArgumentException(
					"count cannot be great than number of kids");
		}

		this.count = count;
	}

	/**
	 * Gets PDF Pages count property.
	 *
	 * @return PDF Pages count property.
	 *
	 * @see #setCount(int)
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Increments by 1 PDF Pages count property.
	 *
	 * @see #decrementCount()
	 * @see #setCount(int)
	 */
	public void incrementCount() {
		setCount(count + 1);
	}

	/**
	 * Decrements by 1 PDF Pages count property.
	 *
	 * @see #incrementCount()
	 * @see #setCount(int)
	 */
	public void decrementCount() {
		setCount(count - 1);
	}

	/**
	 * Assembles PDF Pages content.
	 *
	 * @return PDF Pages content.
	 */
	public byte[] assemble() {
		Collections.sort(fontObjRefs);
		Collections.sort(xObjectObjRefs);

		String aux =
				  "<<\n"
				+ "/Type /Pages\n"
				+ "/Resources <<\n"
				+ "/ProcSet [/PDF /Text /ImageB ]\n";

		aux += "/Font <<\n";

		for (int i = 0; i < fontObjRefs.size(); i++) {
			aux += fontObjRefs.get(i) + " 0 R\n";
		}

		aux += ">>\n";

		aux += "/XObject <<\n";

		for (int i = 0; i < xObjectObjRefs.size(); i++) {
			aux += xObjectObjRefs.get(i) + " 0 R\n";
		}

		aux += ">>\n";

		aux +=
				  "/ColorSpace <<\n"
				+ "/CS1 [/Pattern /DeviceRGB ]\n"
				+ ">>\n";

		aux += ">>\n";

		aux += "/Kids [";

		for (int i = 0; i < kidObjIDs.size(); i++) {
			aux += kidObjIDs.get(i) + " 0 R\n";
		}

		aux += "]\n";

		aux +=
				  "/Count " + count + "\n"
				+ ">>\n"
				+ "endobj\n";

		return aux.getBytes();
	}

	/**
	 * Represents a PDF Pages with its PDF Object ID.
	 *
	 * @return String representation.
	 */
	public String toString() {
		return objID + "";
	}

	private String getMappedObjRefName(List mappedObjRefs, int objID) {
		for (int i = 0; i < mappedObjRefs.size(); i++) {
			MappedObjRef objRef = (MappedObjRef) mappedObjRefs.get(i);

			if (objRef.getObjID() == objID) {
				return objRef.getName();
			}
		}

		return null;
	}

	private int getMappedObjRefObjID(List mappedObjRefs, String name) {
		for (int i = 0; i < mappedObjRefs.size(); i++) {
			MappedObjRef objRef = (MappedObjRef) mappedObjRefs.get(i);

			if (objRef.getName() == name) {
				return objRef.getObjID();
			}
		}

		return -1;
	}

	private ArrayList copyMappedObjRefList(List mappedObjRefList,
			int referenceSize) {

		ArrayList copiedList =
			new ArrayList(Math.max(mappedObjRefList.size(), referenceSize));

		for (int i = 0; i < mappedObjRefList.size(); i++) {
			MappedObjRef mappedObjRef = (MappedObjRef) mappedObjRefList.get(i);
			MappedObjRef _mappedObjRef = (MappedObjRef) mappedObjRef.clone();

			copiedList.add(_mappedObjRef);
		}

		return copiedList;
	}
}
