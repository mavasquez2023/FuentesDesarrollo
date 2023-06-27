

/*
 * @(#) DocField.java    1.0 30-06-2008
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
 * <b>document field</b> like is required by the documental repository.
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
 *            <TD> 30-06-2008 </TD>
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
public class DocField implements Serializable, Comparable, DocTypeConstants {

	private static final long serialVersionUID = 7348075879765098211L;
	
	private String name;
	private int type;
	private int length;
	private String labelName;
	private String remark;

	/**
	 * Constructs an empty field. 
	 * 
	 * @see #DocField(String, int)
	 * @see #DocField(String, int, int)
	 * @see #DocField(String, String)
	 * @see #DocField(String, String, int) 
	 */		
	public DocField() {
	}

	/**
	 * Constructs an field from the specified <code>name</code> and
	 * <code>type</code>. It assumes that field length is 0. 
	 * 
	 * @param name Field name.
	 * 
	 * @param type Field type.
	 * 
	 * @see #DocField()
	 * @see #DocField(String, int, int)
	 * @see #DocField(String, String)
	 * @see #DocField(String, String, int) 
	 */
	public DocField(String name, int type) {
		this(name, type, 0);
	}

	/**
	 * Constructs an field from the specified <code>name</code>,
	 * <code>type</code> and <code>length</code>. 
	 * 
	 * @param name Field name.
	 * 
	 * @param type Field type.
	 * 
	 * @param length Field length in characters or digits.
	 * 
	 * @see #DocField()
	 * @see #DocField(String, int)
	 * @see #DocField(String, String)
	 * @see #DocField(String, String, int) 
	 */
	public DocField(String name, int type, int length) {
		setName(name);
		setType(type);
		setLength(length);
	}

	/**
	 * Constructs an field from the specified <code>name</code> and
	 * <code>type</code>. It assumes that field length is 0. 
	 * 
	 * @param name Field name.
	 * 
	 * @param type Field type.
	 *
	 * @see #DocField()
	 * @see #DocField(String, int)
	 * @see #DocField(String, int, int)
	 * @see #DocField(String, String, int) 
	 */	
	public DocField(String name, String type) {
		this(name, getType(type), 0);
	}

	/**
	 * Constructs an field from the specified <code>name</code>,
	 * <code>type</code> and <code>length</code>. 
	 * 
	 * @param name Field name.
	 * 
	 * @param type Field type.
	 * 
	 * @param length Field length in characters or digits.
	 * 
	 * @see #DocField()
	 * @see #DocField(String, int)
	 * @see #DocField(String, int, int)
	 * @see #DocField(String, String)
	 */	
	public DocField(String name, String type, int length) {
		this(name, getType(type), length);
	}

	/**
	 * Sets field name considering a maximum length of
	 * {@link DocTypeConstants#DOC_FIELD_NAME_MAX_LENGTH} characters.
	 * 
	 * @param name Field name.
	 * 
	 * @see #getName()
	 */
	public void setName(String name) {
		this.name = StringUtils.truncate(name, DOC_FIELD_NAME_MAX_LENGTH);
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
	 * Sets field type.
	 * 
	 * @param type Field type.
	 * 
	 * @see #getType()
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Gets field type.
	 * 
	 * @return Field type.
	 * 
	 * @see #getType()
	 * @see #getTypeName()
	 */
	public int getType() {
		return type;
	}

	/**
	 * Gets field type name.
	 * 
	 * @return Field type name.
	 * 
	 * @see #getType()
	 */
	public String getTypeName() {
		return fieldTypeNames[type];
	}

	/**
	 * Sets field length in characters or digits.
	 * 
	 * @param length Field length.
	 * 
	 * @see #getLength()
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * Gets field length.
	 * 
	 * @return Field length.
	 * 
	 * @see #setLength(int)
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Gets expected maximum field length in function of its type.
	 * 
	 * @return Expected maximum field length.
	 */
	public int getMaxLength() {
		switch (type) {
			case FIELD_TYPE_STRING:
				return getLength();

			case FIELD_TYPE_CHAR:
				return 1;

			case FIELD_TYPE_INT:
				return 10;		// 1.234.567.890

			default:	// FIELD_TYPE_NDATE
				return 5;
		}
	}

	/**
	 * Sets field label name considering a maximum length of
	 * {@link DocTypeConstants#DOC_FIELD_LABEL_NAME_MAX_LENGTH} characters.
	 * 
	 * @param name Field label name.
	 * 
	 * @see #getLabelName()
	 */
	public void setLabelName(String name) {
		labelName = StringUtils.truncate(name, DOC_FIELD_LABEL_NAME_MAX_LENGTH);
	}

	/**
	 * Gets field label name.
	 * 
	 * @return Field label name.
	 * 
	 * @see #setLabelName(String)
	 */
	public String getLabelName() {
		return labelName;
	}

	/**
	 * Sets field remark or comment considering a maximum length of
	 * {@link DocTypeConstants#DOC_FIELD_REMARK_MAX_LENGTH} characters.
	 * 
	 * @param remark Field remark.
	 * 
	 * @see #getRemark()
	 */	
	public void setRemark(String remark) {
		this.remark = StringUtils.truncate(remark, DOC_FIELD_REMARK_MAX_LENGTH);
	}

	/**
	 * Gets field remark or comment.
	 * 
	 * @return Field remark.
	 * 
	 * @see #setRemark(String)
	 */	
	public String getRemark() {
		return remark;
	}

	/**
	 * Compares this field with the specified object for order. Returns a
	 * negative integer, zero, or a positive integer as this object is
	 * less than, equal to, or greater than the specified object.
	 * 
	 * @param o {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */
	public int compareTo(Object o) {
		if (!(o instanceof DocField)) {
			throw new IllegalArgumentException();
		}

		DocField docField = (DocField) o;

		return name.compareTo(docField.name);
	}

	/**
	 * Compares two fields for equality. The result is <code>true</code> if
	 * and only if the argument is not <code>null</code> and is a
	 * <b>DocField</b> instance with the same name.
	 *  
	 * @param obj {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */		
	public boolean equals(Object obj) {
		return compareTo(obj) == 0;
	}

	/**
	 * Gets field type codefrom the specified field type <code>name</code>.
	 * 
	 * @param name Field type name.
	 * 
	 * @return Field type code.
	 * 
	 * @throws IllegalArgumentException If field type <code>name</code> is
	 *         unknown.
	 */
	public static int getType(String name) {
		for (int i = 0; i < fieldTypeNames.length; i++) {
			if (fieldTypeNames[i].equals(name)) {
				return i;
			}
		}

		throw new IllegalArgumentException(
				"Unknown field type '" + name + "'.");
	}
}
