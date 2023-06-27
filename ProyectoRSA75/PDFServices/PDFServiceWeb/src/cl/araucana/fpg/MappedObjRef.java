

/*
 * @(#) MappedObjRef.java    1.0 10-03-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg;


/**
 * Mapped PDF Object Reference. This class permits to associate a name to a
 * PDF Object reference. For example, the following PDF code shows a mapped
 * PDF Object Reference:
 * 
 * <pre>
 *                                /F22 22 0 R
 * </pre>
 *
 * <p>
 * where <b>/F22</b> is its name and <b>22 0 R</b> the reference to the
 * <b>22</b> PDF Object.
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
 *            <TD> 10-03-2008 </TD>
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
public class MappedObjRef implements Cloneable, Comparable {

	private String name;
	private int objID;

	/**
	 * Constructs a new instance with the specified name and
	 * PDF Object ID.
	 * 
	 * @param name Name.
	 * 
	 * @param objID PDF Object ID.
	 */
	public MappedObjRef(String name, int objID) {
		this.name = name;
		this.objID = objID;
	}

	/**
	 * Clones a mapped object reference.
	 */
	public Object clone() {
		return new MappedObjRef(name, objID);
	}

	/**
	 * Gets name.
	 * 
	 * @return Name.
	 */
	public String getName() {
		return name;
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
	 * Compares two mapped object references for equality. The result is
	 * <code>true</code> if and only if the argument is not <code>null</code>
	 * and is a <b>MappedObjRef</b> instance with the same name and PDF
	 * object ID.
	 *  
	 * @param obj {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */		
	public boolean equals(Object obj) {
		if (!(obj instanceof MappedObjRef)) {
			return false;
		}

		MappedObjRef objRef = (MappedObjRef) obj;

		return name.equals(objRef.name) || objID == objRef.objID;
	}

	/**
	 * Compares this mapped object reference with the specified object for
	 * order. Returns a negative integer, zero, or a positive integer as this
	 * object is less than, equal to, or greater than the specified object,
	 * based on their names.
	 * 
	 * @param o {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */		
	public int compareTo(Object o) {
		if (!(o instanceof MappedObjRef)) {
			return +1;
		}

		MappedObjRef objRef = (MappedObjRef) o;
		int id1 = getID();
		int id2 = objRef.getID();

		if (id1 == -1 || id2 == -1) {
			return name.compareTo(objRef.name);
		}

		return id1 - id2;
	}

	private int getID() {
		try {
			return Integer.parseInt(name.substring(2));
		} catch (Exception e) {}

		return -1;
	}

	/**
	 * Represents a Mapped PDF Object reference with the following syntax:
	 * <b>&lt;name&gt; &lt;object ID&gt;</b>.
	 * 
	 * @return String representation.
	 */	
	public String toString() {
		return name + " " + objID;
	}
}
