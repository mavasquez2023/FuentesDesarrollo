

package cl.araucana.pdf.publishers;


/*
 * @(#) Field.java    1.0 25-05-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


import java.io.Serializable;


/**
 * This class implements a <i>Transfer Object</i> to represent the
 * metadata of a publishable document type's field.
 *
 * <p> The field's <b>docID</b> property is used to establish its position
 * within the set of fields that composes the corresponding document ID. If
 * field not belong to the document ID then its <b>docID</b> property must be 0.
 * Positions starts from 1.
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
 *            <TD> 25-05-2008 </TD>
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
public class Field implements Serializable {

	private static final long serialVersionUID = 6457807447682013331L;
	
	/**
	 * Integer field type code. 
	 */
	public static final int TYPE_INT    = 0;

	/**
	 * Date field type code. 
	 */
	public static final int TYPE_DATE   = 1;
	
	/**
	 * NDate field type code. 
	 */	
	public static final int TYPE_NDATE  = 2;
	
	/**
	 * String field type code. 
	 */	
	public static final int TYPE_STRING = 3;
	
	/**
	 * Character field type code. 
	 */	
	public static final int TYPE_CHAR   = 4;

	private static final String[] typeNames = {
		"int",
		"date",
		"ndate",
		"string",
		"char"
	};

	/**
	 *  Number of supported field types. 
	 */
	public static final int NTYPES = typeNames.length;

	private String name;
	private int type;
	private int docID;
	private String description = "";

	/**
	 * Gets field type code from its specified name.
	 * 
	 * @param typeName Field type name.
	 * 
	 * @return Field type code or <code>-1</code> is specified name
	 *         is unknown.
	 */
	public static int getType(String typeName) {
		for (int i = 0; i < typeNames.length; i++) {
			if (typeNames[i].equals(typeName)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Constructs an empty field. 
	 * 
	 * @see #Field(String)
	 * @see #Field(String, int)
	 * @see #Field(String, int, int, String)
	 */		
	public Field() {
	}

	/**
	 * Constructs an field with the specified <code>name</code>.
	 * 
	 * @param name Field name.
	 * 
	 * @see #Field()
	 * @see #Field(String, int)
	 * @see #Field(String, int, int, String)
	 */		
	public Field(String name) {
		this(name, 0, 0, "");
	}

	/**
	 * Constructs an field with the specified <code>name</code> and
	 * <code>type</code>.
	 * 
	 * @param name Field name.
	 * 
	 * @param type Field type code.
	 * 
	 * @throws IllegalArgumentException If field type code is invalid.
	 * 
	 * @see #Field()
	 * @see #Field(String)
	 * @see #Field(String, int, int, String)
	 */
	public Field(String name, int type) {
		this(name, type, 0, "");
	}

	/**
	 * Constructs an field with the specified <code>name</code> and
	 * <code>type</code>.
	 * 
	 * @param name Field name.
	 * 
	 * @param type Field type code.
	 * 
	 * @param docID Position in document ID.
	 * 
	 * @param description Description or comment.
	 * 
	 * @throws IllegalArgumentException If field type code is invalid.
	 * 
	 * @see #Field()
	 * @see #Field(String)
	 * @see #Field(String, int)
	 */
	public Field(String name, int type, int docID, String description) {
		if (type < 0 ||type >= NTYPES) {
			throw new IllegalArgumentException("Invalid type '" + type + "'.");
		}

		this.name = name;
		this.type = type;
		this.docID = docID;

		setDescription(description);
	}

	/**
	 * Sets field name.
	 * 
	 * @param name Field name.
	 * 
	 * @see #getName()
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets field name.
	 * 
	 * @return Field name.
	 * 
	 * @see #setName(String)
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets field type code.
	 * 
	 * @param type Field type code.
	 * 
	 * @see #getType()
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Gets field type code.
	 * 
	 * @return Field type code.
	 * 
	 * @see #setType(int)
	 */
	public int getType() {
		return type;
	}

	/**
	 * Sets position in document ID.
	 * 
	 * @param docID Position in document ID.
	 * 
	 * @see #getDocID()
	 */
	public void setDocID(int docID) {
		this.docID = docID;
	}

	/**
	 * Gets position in document ID.
	 * 
	 * @return Position in document ID.
	 * 
	 * @see #setDocID(int)
	 */
	public int getDocID() {
		return docID;
	}

	/**
	 * Sets field description.
	 * 
	 * @param description Field description.
	 * 
	 * @see #getDescription()
	 */
	public void setDescription(String description) {
		if (description == null) {
			this.description = "";
		} else {
			this.description = description;
		}
	}

	/**
	 * Gets field description.
	 * 
	 * @return Field description.
	 * 
	 * @see #setDescription(String)
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets field type name.
	 * 
	 * @return Field type name.
	 * 
	 * @see #getType()
	 */
	public String getTypeName() {
		return typeNames[type];
	}

	/**
	 * Compares two fields for equality. The result is <code>true</code> if
	 * and only if the argument is not <code>null</code> and is a
	 * <b>Field</b> instance with the same name.
	 *  
	 * @param obj {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */	
	public boolean equals(Object obj) {
		if (!(obj instanceof Field) || name == null || obj == null) {
			return false;
		}

		Field other = (Field) obj;

		return name.equals(other.name);
	}

	/**
	 * Returns a string representation of the field as a comma separated list
	 * of properties indicating name and value for everyone.
	 * 
	 * @return String representation.
	 */	
	public String toString() {
		return
				  "(name=" + name + ", "
				+ "type=" + typeNames[type] + ", "
				+ "docID=" + docID + ", "
				+ "description=" + description + ")";
	}
}
