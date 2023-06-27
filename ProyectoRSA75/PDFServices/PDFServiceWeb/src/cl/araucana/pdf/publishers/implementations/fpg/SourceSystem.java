

/*
 * @(#) SourceSystem.java    1.0 02-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package  cl.araucana.pdf.publishers.implementations.fpg;


import java.io.Serializable;

import cl.araucana.core.util.StringUtils;


/**
 * This class implements a <i>Transfer Object</i> to represent a
 * <b>source system</b> like is required by the documental repository.
 *
 * <p> Document version <b>ID</b> property must be unique in the documental
 * repository.
 * </p>
 * 
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
 *            <TD> 02-10-2008 </TD>
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
public class SourceSystem implements Serializable, Comparable,
		DocTypeConstants {

	private static final long serialVersionUID = -2081190300314803431L;
	
	private int id;
	private String name;
	private String description;

	/**
	 * Constructs an empty source system.
	 */		
	public SourceSystem() {
	}

	/**
	 * Sets internal source system id.
	 * 
	 * @param id Internal source system id.
	 * 
	 * @see #getID()
	 */	
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Gets internal source system id.
	 * 
	 * @return Internal source system id.
	 * 
	 * @see #setID(int)
	 */	
	public int getID() {
		return id;
	}

	/**
	 * Sets source system name considering a maximum length of
	 * {@link DocTypeConstants#SS_NAME_MAX_LENGTH} characters.
	 * 
	 * @param name Source system name.
	 * 
	 * @see #getName()
	 */
	public void setName(String name) {
		this.name = StringUtils.truncate(name, SS_NAME_MAX_LENGTH);
	}

	/**
	 * Gets source system name.
	 * 
	 * @return Source system name.
	 * 
	 * @see #setName(String)
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets source system description considering a maximum length of
	 * {@link DocTypeConstants#SS_DESCRIPTION_MAX_LENGTH} characters.
	 * 
	 * @param description Source system description.
	 * 
	 * @see #getDescription()
	 */	
	public void setDescription(String description) {
		this.description =
				StringUtils.truncate(description, SS_DESCRIPTION_MAX_LENGTH);
	}

	/**
	 * Gets source system description.
	 * 
	 * @return Source system description.
	 * 
	 * @see #setDescription(String)
	 */		
	public String getDescription() {
		return description;
	}

	/**
	 * Compares this source system with the specified object for order.
	 * Returns a negative integer, zero, or a positive integer as this object is
	 * less than, equal to, or greater than the specified object.
	 * 
	 * @param o {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */		
	public int compareTo(Object o) {
		if (!(o instanceof SourceSystem)) {
			throw new IllegalArgumentException();
		}

		SourceSystem sourceSystem = (SourceSystem) o;

		return name.compareTo(sourceSystem.name);
	}

	/**
	 * Compares two source systems for equality. The result is
	 * <code>true</code> if and only if the argument is not <code>null</code>
	 * and is a <b>SourceSystem</b> instance with the same ID.
	 *  
	 * @param obj {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */	
	public boolean equals(Object obj) {
		return compareTo(obj) == 0;
	}
}
