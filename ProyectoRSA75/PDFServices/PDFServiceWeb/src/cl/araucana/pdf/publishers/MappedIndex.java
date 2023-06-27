

package cl.araucana.pdf.publishers;


/*
 * @(#) MappedIndex.java    1.0 02-06-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


import java.io.Serializable;

import java.util.List;

import cl.araucana.core.util.Mapping;
import cl.araucana.core.util.UnRepeatedArrayList;


/**
 * This class implements a <i>Transfer Object</i> to represent the
 * mappings to a publishable document type's index. Each field name in the
 * index is mapped to logical name. This is very useful to map different
 * name levels, for example physical names in a <b>PDF Publisher</b> and
 * logical names in a specific business.
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
 *            <TD> 02-06-2008 </TD>
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
public class MappedIndex implements Serializable {

	private static final long serialVersionUID = 5963013583846450309L;
	
	private String name;
	private String description = "";
	private List mappings = new UnRepeatedArrayList();

	/**
	 * Constructs an empty mapped index.
	 * 
	 * @see #MappedIndex(String)
	 */	
	public MappedIndex() {
	}
	
	/**
	 * Constructs an empty mapped index with the specified name.
	 * 
	 *  @param name Mapped index name.
	 *  
	 *  @see #MappedIndex()
	 */
	public MappedIndex(String name) {
		this.name = name;
	}

	/**
	 * Sets mapped index name.
	 * 
	 * @param name Mapped index name.
	 * 
	 * @see #getName()
	 */	
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets mapped index name.
	 * 
	 * @return Mapped index name.
	 * 
	 * @see #setName(String)
	 */	
	public String getName() {
		return name;
	}

	/**
	 * Sets mapped index description.
	 * 
	 * @param description Mapped index description.
	 * 
	 * @see #getDescription()
	 */	
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets mapped index description.
	 * 
	 * @return Mapped index description.
	 * 
	 * @see #setDescription(String)
	 */	
	public String getDescription() {
		return description;
	}

	/**
	 * Gets mapped index list of mappings.
	 * 
	 * @return List of mappings.
	 * 
	 * @see #addMapping(Mapping)
	 */	
	public List getMappings() {
		return mappings;
	}

	/**
	 * Adds to the mapped index the specified <code>mapping</code>.
	 * 
	 * @param mapping Mapping to be added.
	 * 
	 * @see #removeMapping(Mapping)
	 */	
	public void addMapping(Mapping mapping) {
		mappings.add(mapping);
	}

	/**
	 * Removes from the mapped index the specified <code>mapping</code>.
	 * 
	 * @param mapping Mapping to be removed.
	 * 
	 * @see #addMapping(Mapping)
	 */	
	public void removeMapping(Mapping mapping) {
		mappings.remove(mapping);
	}

	/**
	 * Gets a mapped index mapping from the specified mapping <code>name</code>.
	 * 
	 * @param name Mapping name.
	 * 
	 * @return Mapped Index mapping or <code>null</code> if mapping
	 *         <code>name</code> is unknown.
	 */	
	public Mapping getMapping(String name) {
		Mapping mapping = new Mapping(name);
		int index = mappings.indexOf(mapping);

		if (index == -1) {
			return null;
		}

		return (Mapping) mappings.get(index);
	}

	/**
	 * Compares two mapped index for equality. The result is <code>true</code>
	 * if and only if the argument is not <code>null</code> and is a
	 * <b>MappedIndex</b> instance with the same name.
	 *  
	 * @param obj {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */		
	public boolean equals(Object obj) {
		if (!(obj instanceof MappedIndex) || name == null || obj == null) {
			return false;
		}

		MappedIndex other = (MappedIndex) obj;

		return name.equals(other.name);
	}

	/**
	 * Returns a string representation of the mapped index as multiple lines
	 * one per each property indicating its name and value.
	 * 
	 * @return String representation.
	 */		
	public String toString() {
		return
				  "name=" + name + "\n"
				+ "description=" + description + "\n"
				+ "mappings=\n" + mappings;
	}
}
