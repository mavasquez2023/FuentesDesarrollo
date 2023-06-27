

package cl.araucana.pdf.publishers;


/*
 * @(#) Index.java    1.0 26-05-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


import java.io.Serializable;

import java.util.List;

import cl.araucana.core.util.Property;
import cl.araucana.core.util.UnRepeatedArrayList;


/**
 * This class implements a <i>Transfer Object</i> to represent the
 * metadata of a publishable document type's index. An index is a set of fields
 * that composes the document type (<b>docIndex</b>). Additionally one or more
 * of these fields must compose the corresponding document ID (<b>docID</b>)
 * too. Finally an index have a set of cutomized properties to be
 * used with a particular {@link PDFPublisher}. 
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
public class Index implements Serializable {

	private static final long serialVersionUID = -7981464612852697691L;
	
	private String name;
	private String description = "";
	private List properties = new UnRepeatedArrayList();
	private List fields = new UnRepeatedArrayList();
	private int[] docIDFieldIndexes = new int[0];
	private String[] docIDFieldNames = new String[0];

	/**
	 * Constructs an empty index.
	 * 
	 * @see #Index(String)
	 */	
	public Index() {
	}

	/**
	 * Constructs an empty index with the specified name.
	 * 
	 *  @param name Index name.
	 *  
	 *  @see #Index()
	 */
	public Index(String name) {
		this.name = name;
	}

	/**
	 * Sets index name.
	 * 
	 * @param name Index name.
	 * 
	 * @see #getName()
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets index name.
	 * 
	 * @return Index name.
	 * 
	 * @see #setName(String)
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets index description.
	 * 
	 * @param description Index description.
	 * 
	 * @see #getDescription()
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets index description.
	 * 
	 * @return Index description.
	 * 
	 * @see #setDescription(String)
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets index list of properties.
	 * 
	 * @return List of properties.
	 * 
	 * @see #addProperty(Property)
	 */
	public List getProperties() {
		return properties;
	}

	/**
	 * Adds to the index the specified <code>property</code>.
	 * 
	 * @param property Property to be added.
	 * 
	 * @see #removeProperty(Property)
	 */
	public void addProperty(Property property) {
		properties.add(property);
	}

	/**
	 * Removes from the index the specified <code>property</code>.
	 * 
	 * @param property Property to be removed.
	 * 
	 * @see #addProperty(Property)
	 */
	public void removeProperty(Property property) {
		properties.remove(property);
	}

	/**
	 * Gets a index property from the specified property <code>name</code>.
	 * 
	 * @param name Property name.
	 * 
	 * @return Index property or <code>null</code> if property
	 *         <code>name</code> is unknown.
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
	 * Gets index list of fields.
	 * 
	 * @return List of fields.
	 * 
	 * @see #addField(Field)
	 */
	public List getFields() {
		return fields;
	}

	/**
	 * Adds to the index the specified <code>field</code>.
	 * 
	 * @param field Field to be added.
	 * 
	 * @see #removeField(Field)
	 */	
	public void addField(Field field) {
		fields.add(field);
	}

	/**
	 * Removes from the index the specified <code>field</code>.
	 * 
	 * @param field Field to be removed.
	 * 
	 * @see #addField(Field)
	 */		
	public void removeField(Field field) {
		fields.remove(field);
	}

	/**
	 * Gets a index field from the specified field <code>name</code>.
	 * 
	 * @param name Field name.
	 * 
	 * @return Index field or <code>null</code> if field
	 *         <code>name</code> is unknown.
	 */	
	public Field getField(String name) {
		Field field = new Field(name);
		int index = fields.indexOf(field);

		if (index == -1) {
			return null;
		}

		return (Field) fields.get(index);
	}

	/**
	 * Checks a valid set of fields for the corresponding document ID.
	 * 
	 * @throws IllegalArgumentException If set of fields for document ID
	 *         is invalid.
	 */
	public void checkDocIDFields() {
		int nIndexes = 0;

		for (int i = 0; i < fields.size(); i++) {
			Field field = (Field) fields.get(i);
			int docID = field.getDocID();

			if (docID == 0) {
				continue;
			}

			if (1 <= docID && docID <= fields.size()) {
				nIndexes++;
			} else {
				throw new IllegalArgumentException(
						"docID '" + docID + "' out of range.");
			}
		}

		docIDFieldIndexes = new int[nIndexes];

		for (int i = 0; i < fields.size(); i++) {
			Field field = (Field) fields.get(i);
			int docID = field.getDocID();

			if (docID == 0) {
				continue;
			}

			if (1 <= docID && docID <= nIndexes) {
				if (docIDFieldIndexes[docID - 1] == 0) {
					docIDFieldIndexes[docID - 1] = i;
				} else {
					throw new IllegalArgumentException(
							"docID '" + docID + "' duplicated.");
				}
			} else {
				throw new IllegalArgumentException(
						"docID '" + docID + "' out of sequence.");
			}
		}

		docIDFieldNames = new String[docIDFieldIndexes.length];

		for (int i = 0; i < docIDFieldIndexes.length; i++) {
			Field field = (Field) fields.get(docIDFieldIndexes[i]);

			docIDFieldNames[i] = field.getName();
		}
	}

	/**
	 * Gets indexes for each field in the set of fields for the document ID.
	 * Indexes starts from 0.
	 * 
	 * <p> <b>Note:</b> {@link #checkDocIDFields()} must be called previously
	 * to this one, otherwise returned array will be empty.
	 * </p>
	 *  
	 * @return Array of field indexes.
	 */
	public int[] getDocIDFieldIndexes() {
		return docIDFieldIndexes;
	}

	/**
	 * Gets name for each field in the set of fields for the document ID.
	 * 
	 * <p> <b>Note:</b> {@link #checkDocIDFields()} must be called previously
	 * to this one, otherwise returned array will be empty.
	 * </p>
	 *  
	 * @return Array of field names.
	 */	
	public String[] getDocIDFieldNames() {
		return docIDFieldNames;
	}

	/**
	 * Compares two index for equality. The result is <code>true</code> if
	 * and only if the argument is not <code>null</code> and is a
	 * <b>Index</b> instance with the same name.
	 *  
	 * @param obj {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */		
	public boolean equals(Object obj) {
		if (!(obj instanceof Index) || name == null || obj == null) {
			return false;
		}

		Index other = (Index) obj;

		return name.equals(other.name);
	}

	/**
	 * Returns a string representation of the index as multiple lines
	 * one per each property indicating its name and value.
	 * 
	 * @return String representation.
	 */	
	public String toString() {
		return
				  "name=" + name + "\n"
				+ "description=" + description + "\n"
				+ "properties=\n" + properties + "\n"
				+ "fields=\n" + fields;
	}
}
