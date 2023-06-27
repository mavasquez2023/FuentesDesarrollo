

/*
 * @(#) Trailer.java    1.0 26-02-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg;


/**
 * PDF document trailer.
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

public class Trailer implements Cloneable {

	/**
	 * Number of PDF objects in the PDF document. 
	 */
	public int nObjects;
	
	/**
	 * <b>ROOT</b> PDF Object ID. 
	 */
	public int rootObjID;
	
	/**
	 * <b>INFO</b> PDF Object ID. 
	 */	
	public int infoObjID;

	/**
	 * Constructs an empty trailer instance.
	 */
	public Trailer() {
	}

	/**
	 * Constructs a new trailer instance cloning other one.
	 * 
	 * @param trailer Trailer instance to be cloned.
	 */
	public Trailer(Trailer trailer) {
		nObjects = trailer.nObjects;
		rootObjID = trailer.rootObjID;
		infoObjID = trailer.infoObjID;
	}

	/**
	 * Clones the specified trailer.
	 * 
	 * @return Cloned Trailer.
	 */
	public Object clone() {
		return new Trailer(this);
	}
	
	/**
	 * Returns a string representation of this trailer with
	 * each property indicating its name and value.
	 * 
	 * @return String representation.
	 */
	public String toString() {
		return nObjects + " " + rootObjID + " " + infoObjID;
	}
}
