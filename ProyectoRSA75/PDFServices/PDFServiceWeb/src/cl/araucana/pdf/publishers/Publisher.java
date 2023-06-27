

package cl.araucana.pdf.publishers;


/*
 * @(#) Publisher.java    1.0 26-05-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


import java.io.Serializable;

import java.util.List;

import cl.araucana.core.util.Property;
import cl.araucana.core.util.UnRepeatedArrayList;

import cl.araucana.fpg.PDFTemplate;


/**
 * This class implements a <i>Transfer Object</i> to represent the
 * configuration of a {@link PDFPublisher}.
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
 *            <TD> 26-05-2008 </TD>
 *            <TD align="center">  1.0 </TD>
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
public class Publisher implements Serializable {

	private static final long serialVersionUID = 8678687188744262933L;
	
	private String name;
	private String description = "";
	private Class type;
	private boolean defaultOne;
	private List properties = new UnRepeatedArrayList();
	private List indexes;
	private List mappedIndexes;
	private PDFTemplate template;

	/**
	 * Constructs an empty instance. 
	 */
	public Publisher() {
	}

	/**
	 * Sets publisher name.
	 * 
	 * @param name Publisher Name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets publisher name.
	 * 
	 * @return publisher name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets publisher description.
	 * 
	 * @param description Publisher description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets publisher description.
	 * 
	 * @return Publisher description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets publisher class. This class must be a concrete subclass of
	 * {@link PDFPublisher}.
	 * 
	 * @param type Publisher class.
	 */
	public void setType(Class type) {
		this.type = type;
	}

	/**
	 * Gets publisher class.
	 * 
	 * @return Publisher class.
	 */
	public Class getType() {
		return type;
	}

	/**
	 * Defines if this publisher is the default one. Only one instance of
	 * this class would be default one.
	 * 
	 * @param enabled Default publisher control flag. <code>true</code> means
	 *        default one, otherwise not.
	 */
	public void setDefault(boolean enabled) {
		defaultOne = enabled;
	}

	/**
	 * Indicates is this publisher is the default one.
	 * 
	 * @return <code>true</code> is the default one, <code>false</code>
	 *         otherwise.
	 */
	public boolean isDefault() {
		return defaultOne;
	}

	/**
	 * Gets the properties list of this publisher.
	 * 
	 * @return properties list.
	 */
	public List getProperties() {
		return properties;
	}

	/**
	 * Adds the specified property to the properties list of this publisher.
	 * 
	 * @param property Property instance.
	 */
	public void addProperty(Property property) {
		properties.add(property);
	}

	/**
	 * Removes the specified property from the properties list of this
	 * publisher.
	 * 
	 * @param property Property instance.
	 */
	public void removeProperty(Property property) {
		properties.remove(property);
	}

	/**
	 * Gets a property from its <code>name</code>.
	 * 
	 * @param name Property name.
	 * 
	 * @return Property instance or <code>null</code> is property name is
	 *         not present in the properties list.
	 */
	public Property getProperty(String name) {
		Property property = new Property(name);
		int index = properties.indexOf(property);

		if (index == -1) {
			return null;
		}

		return (Property) properties.get(index);
	}

	/**
	 * Gets a property value from its <code>name</code>.
	 * 
	 * @param name Property name.
	 * 
	 * @return Property value or <code>null</code> is property name is
	 *         not present in the properties list.
	 */	
	public String getPropertyValue(String name) {
		Property property = getProperty(name);

		if (property == null) {
			return null;
		}

		return property.getValue();
	}

	/**
	 * Sets indexes list associated to this publisher.
	 * 
	 * @param indexes List of indexes.
	 */
	public void setIndexes(List indexes) {
		this.indexes = indexes;
	}

	/**
	 * Gets list of indexes of this publisher.
	 * 
	 * @return List of indexes
	 */
	public List getIndexes() {
		return indexes;
	}

	/**
	 * Gets a index from from its <code>name</code>.
	 * 
	 * @param name Index name.
	 * 
	 * @return Index instance or <code>null</code> is index name is
	 *         not present in the indexes list.
	 */
	public Index getIndex(String name) {
		Index _index = new Index(name);
		int index = indexes.indexOf(_index);

		if (index == -1) {
			return null;
		}

		return (Index) indexes.get(index);
	}

	/**
	 * Sets mapped indexes list to this publisher.
	 * 
	 * @param mappedIndexes  mapped indexes list.
	 */
	public void setMappedIndexes(List mappedIndexes) {
		this.mappedIndexes = mappedIndexes;
	}

	/**
	 * Gets mapped indexes list to this publisher.
	 * 
	 * @return Mapped indexes list.
	 */
	public List getMappedIndexes() {
		return mappedIndexes;
	}

	/**
	 * Gets a maped index from from its <code>name</code>.
	 * 
	 * @param name Mapped index name.
	 * 
	 * @return Mapped index instance or <code>null</code> is mapped index name
	 *         is not present in the mapped indexes list.
	 */	
	public MappedIndex getMappedIndex(String name) {
		MappedIndex mappedIndex = new MappedIndex(name);
		int index = mappedIndexes.indexOf(mappedIndex);

		if (index == -1) {
			return null;
		}

		return (MappedIndex) mappedIndexes.get(index);
	}

	/**
	 * Sets the PDF template to be used by this publisher.
	 * 
	 * @param template PDF template.
	 */
	public void setPDFTemplate(PDFTemplate template) {
		this.template = template;
	}

	/**
	 * Gets PDF template.
	 * 
	 * @return PDF template.
	 */
	public PDFTemplate getPDFTemplate() {
		return template;
	}

	/**
	 * Compares two publisher for equality. The result is <code>true</code> if
	 * and only if the argument is not <code>null</code> and is a
	 * <b>Publisher</b> instance with the same name.
	 *  
	 * @param obj {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Publisher) || name == null || obj == null) {
			return false;
		}

		Publisher other = (Publisher) obj;

		return name.equals(other.name);
	}

	/**
	 * Returns a string representation of the publisher as multiple lines
	 * one per each property indicating its name and value.
	 * 
	 * @return String representation.
	 */
	public String toString() {
		String type =
				(this.type == null) ? null : this.type.getClass().getName();

		return
				  "name=" + name + "\n"
				+ "description=" + description + "\n"
				+ "type=" + type + "\n"
				+ "default=" + defaultOne + "\n"
				+ "properties=\n" + properties + "\n"
				+ "indexes=\n" + indexes + "\n"
				+ "mappedIndexes=\n" + mappedIndexes + "\n"
				+ "template=" + template.getFullName() + "\n";
	}
}
