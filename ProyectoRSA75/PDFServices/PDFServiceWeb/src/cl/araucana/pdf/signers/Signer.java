

/*
 * @(#) Signer.java    1.0 26-05-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.signers;


import java.io.Serializable;

import java.util.List;

import cl.araucana.core.util.Property;
import cl.araucana.core.util.UnRepeatedArrayList;


/**
 * ...
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
 *            <TD> 26-05-2008 </TD>
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
public class Signer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1705624034158853844L;
	
	private String name;
	private String description = "";
	private Class type;
	private boolean defaultOne;
	private List properties = new UnRepeatedArrayList();

	public Signer() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setType(Class type) {
		this.type = type;
	}

	public Class getType() {
		return type;
	}

	public void setDefault(boolean enabled) {
		defaultOne = enabled;
	}

	public boolean isDefault() {
		return defaultOne;
	}

	public List getProperties() {
		return properties;
	}

	public void addProperty(Property property) {
		properties.add(property);
	}

	public void removeProperty(Property property) {
		properties.remove(property);
	}

	public Property getProperty(String name) {
		Property property = new Property(name);
		int index = properties.indexOf(property);

		if (index == -1) {
			return null;
		}

		return (Property) properties.get(index);
	}

	public boolean equals(Object o) {
		if (!(o instanceof Signer) || name == null || o == null) {
			return false;
		}

		Signer other = (Signer) o;

		return name.equals(other.name);
	}

	public String toString() {
		return
				  "name=" + name + "\n"
				+ "description=" + description + "\n"
				+ "type=" + type + "\n"
				+ "default=" + defaultOne + "\n"
				+ "properties=\n" + properties;
	}
}
