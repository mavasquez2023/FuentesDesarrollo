

/*
 * @(#) XRefEntry.java    1.0 26-02-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg;


/**
 * PDF Cross Reference Entry.
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
 *            <TD> 26-02-2008 </TD>
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
public class XRefEntry implements Comparable {

	/**
	 *  Entry size measured in bytes to a PDF document. 
	 */
	public static final int SIZE = 20;
	
	/**
	 *  Value to represent a reserved entry. 
	 */
	public static final int RESERVED = 65535;

	/**
	 * PDF Object ID.
	 */
	public int objID;
	
	/**
	 * Offset in bytes from begining of PDF document to the associated
	 * PDF Object. 
	 */
	public int offset;
	
	/**
	 * Value to indicate if this entry is assigned (<b>0</b>), released
	 * (<b>1</b>) or reserved (<b>65535</b>). (See {@link #mark})
	 */
	public int value;
	
	/**
	 * Mark to indicate if this entry is assigned (<b>'n'</b>) or released
	 * (<b>'f'</b>). (See {@link #value})
	 */
	public char mark;
	
	/**
	 * PDF Object type name. 
	 */
	public String baseType;
	
	/**
	 * Optional data to the associated PDF object.
	 */
	public byte[] objectData;

	/**
	 * Constructs an empty cross reference entry.
	 */
	public XRefEntry() {
	}

	/**
	 * Compares this entry with <code>o</code> one. Comparation is based on
	 * entry offset.
	 */
	public int compareTo(Object o) {
		XRefEntry other = (XRefEntry) o;

		return offset - other.offset;
	}

	/**
	 * Indicates if this entry is assigned.
	 * 
	 * @return <code>true</code> if entry is assigned,
	 *         otherwise <code>false</code>.
	 */
	public boolean isAssigned() {
		return mark == 'n';
	}

	/**
	 * Indicates if this entry is released.
	 * 
	 * @return <code>true</code> if entry is released,
	 *         otherwise <code>false</code>.
	 */	
	public boolean isReleased() {
		return mark == 'f';
	}

	/**
	 * Indicates if this entry is assignable.
	 * 
	 * @return <code>true</code> if entry is assignable,
	 *         otherwise <code>false</code>.
	 */
	public boolean isAssignable() {
		return isReleased() && value != RESERVED;
	}

	/**
	 * Checks if this entry is assigned or not depending of
	 * <code>assigned</code>.
	 * 
	 * @return <code>true</code> if entry assign state is equals
	 *         to <code>assigned</code>.
	 */
	public boolean check(boolean assigned) {
		if (assigned) {
			return isAssigned();
		}

		return isAssignable();
	}

	/**
	 * Releases this entry. It can be reused later. 
	 */
	public void release() {
		offset = objID;
		value = 1;
		mark = 'f';
		baseType = null;
	}

	/**
	 * Assigns this entry to a PDF object of <code>baseType</code> type. PDF
	 * object ID is unknown.
	 * 
	 * @param baseType PDF object type name.
	 * 
	 * @return This cross reference entry.
	 */
	public XRefEntry assign(String baseType) {
		offset = 0;
		value = 0;
		mark = 'n';
		this.baseType = baseType;

		return this;
	}

	/**
	 * Assigns this entry to a PDF object of <code>baseType</code> type with
	 * <code>objID</code> PDF object ID.
	 * 
	 * @param baseType PDF object type name.
	 * 
	 * @return This cross reference entry.
	 */	
	public XRefEntry assign(int objID, String baseType) {
		this.objID = objID;

		return assign(baseType);
	}
	
	/**
	 * Returns a string representation of this entry indicating its property
	 * names and values.
	 * 
	 * @return Entry string representation.
	 */
	public String toString() {
		return objID + " " + offset + " " + value + " " + mark + " " + baseType;
	}
}
