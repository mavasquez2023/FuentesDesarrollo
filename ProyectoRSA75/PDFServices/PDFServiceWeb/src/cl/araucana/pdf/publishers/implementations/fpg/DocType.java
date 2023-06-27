

/*
 * @(#) DocType.java    1.0 30-06-2008
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
 * <b>document type</b> like is required by the documental repository.
 * 
 * <p> Document type <b>ID</b> and <b>name</b> properties must be unique in the
 * documental repository. Each document type has associated four physical tables
 * which names share as prefix the value specified in the <b>baseName</b>
 * property (unique too). These tables mean:
 * </p>
 * 
 * <BR>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="80%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Physical Table</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Description</strong></font>
 *        </th>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <b>&laquo;baseName&raquo;</b>_DOCUMENT
 *        </td>
 *        
 *        <td>
 *            Contains field values (<b>docIndex</b>), Variable PDF Objects
 *            (<b>VOC</b>) and PDF Metadata (<b>VMC</b>) Content Lengths and
 *            finally a reference to the corresponding parent publication for
 *            each published PDF document instance. 
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <b>&laquo;baseName&raquo;</b>_CONTENT
 *        </td>
 *        
 *        <td>
 *            Contains the Variable PDF Objects (<b>VOC</b>) and PDF Metadata
 *            (<b>VMC</b>) Contents for each published PDF document instance.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <b>&laquo;baseName&raquo;</b>_VERSION
 *        </td>
 *        
 *        <td>
 *            Document type registered versions. Every version has a version
 *            number and its Fixed PDF Objects (<b>FOC</b>) and PDF Metadata
 *            (<b>FMC</b>) Contents.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *            <b>&laquo;baseName&raquo;</b>_PUBLICATION
 *        </td>
 *        
 *        <td>
 *            Registered publications to this document type. Every publication
 *            references to the corresponding document version, source system
 *            and publisher agent that did it.
 *        </td>
 *     </tr>
 * </TABLE>
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
public class DocType implements Serializable, Comparable, DocTypeConstants {

	private static final long serialVersionUID = -4268204992481594577L;
	
	private int id;
	private String name;
	private String baseName;
	private String sourceSystemName;
	private String publisherName;
	private long created;
	private String remark;
	private int[] maxPartitionSizes;

	/**
	 * Constructs an empty document type.
	 */
	public DocType() {
	}

	/**
	 * Sets internal document type id.
	 * 
	 * @param id Internal document type id.
	 * 
	 * @see #getID()
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Gets internal document type id.
	 * 
	 * @return Internal document type id.
	 * 
	 * @see #setID(int)
	 */
	public int getID() {
		return id;
	}

	/**
	 * Sets document type name considering a maximum length of
	 * {@link DocTypeConstants#DOC_TYPE_NAME_MAX_LENGTH} characters.
	 * 
	 * @param name Document type name.
	 * 
	 * @see #getName()
	 */
	public void setName(String name) {
		this.name = StringUtils.truncate(name, DOC_TYPE_NAME_MAX_LENGTH);
	}

	/**
	 * Gets document type name.
	 * 
	 * @return Document type name.
	 * 
	 * @see #setName(String)
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets document type base name considering a maximum length of
	 * {@link DocTypeConstants#DOC_TYPE_BASE_NAME_MAX_LENGTH} characters.
	 * 
	 * @param name Document type base name.
	 * 
	 * @see #getBaseName()
	 */
	public void setBaseName(String name) {
		baseName = StringUtils.truncate(name, DOC_TYPE_BASE_NAME_MAX_LENGTH);
	}

	/**
	 * Gets document type base name.
	 * 
	 * @return Document type base name.
	 * 
	 * @see #setBaseName(String)
	 */
	public String getBaseName() {
		return baseName;
	}

	/**
	 * Sets document type source system name considering a maximum length of
	 * {@link DocTypeConstants#SS_NAME_MAX_LENGTH} characters.
	 * 
	 * @param name Document type source system name.
	 * 
	 * @see #getSourceSystemName()
	 */
	public void setSourceSystemName(String name) {
		this.sourceSystemName = StringUtils.truncate(name, SS_NAME_MAX_LENGTH);
	}

	/**
	 * Gets document type source system name.
	 * 
	 * @return Document type source system name.
	 * 
	 * @see #setSourceSystemName(String)
	 */
	public String getSourceSystemName() {
		return sourceSystemName;
	}

	/**
	 * Sets document type publisher agent name considering a maximum length of
	 * {@link DocTypeConstants#PUB_NAME_MAX_LENGTH} characters.
	 * 
	 * @param name Document type publisher agent name.
	 * 
	 * @see #getPublisherName()
	 */
	public void setPublisherName(String name) {
		publisherName = StringUtils.truncate(name, PUB_NAME_MAX_LENGTH);
	}

	/**
	 * Gets document type publisher agent name.
	 * 
	 * @return Document type publisher agent name.
	 * 
	 * @see #setPublisherName(String)
	 */	
	public String getPublisherName() {
		return publisherName;
	}

	/**
	 * Sets document type creation time.
	 * 
	 * @param time Document type creation time.
	 * 
	 * @see #getCreated()
	 */
	public void setCreated(long time) {
		created = time;
	}

	/**
	 *  Gets document type creation time.
	 *  
	 * @return Document type creation time.
	 * 
	 * @see #setCreated(long)
	 */
	public long getCreated() {
		return created;
	}

	/**
	 * Sets document type remark considering a maximum length of
	 * {@link DocTypeConstants#DOC_TYPE_REMARK_MAX_LENGTH} characters.
	 * 
	 * @param remark Document type remark.
	 * 
	 * @see #getRemark()
	 */	
	public void setRemark(String remark) {
		this.remark = StringUtils.truncate(remark, DOC_TYPE_REMARK_MAX_LENGTH);
	}

	/**
	 * Gets document type remark.
	 * 
	 * @return Document type remark.
	 * 
	 * @see #setRemark(String)
	 */		
	public String getRemark() {
		return remark;
	}

	/**
	 * Sets maximum partition sizes in <u>kilobytes</u> to the four partitions
	 * of this document type.
	 * 
	 * @param maxPartitionSizes Array of maximum partition sizes.
	 * 
	 * @see #getMaxPartitionSizes()
	 */
	public void setMaxPartitionSizes(int[] maxPartitionSizes) {
		this.maxPartitionSizes = maxPartitionSizes;
	}

	/**
	 * Gets maximum partition sizes in <u>kilobytes</u> to the four partitions
	 * of this document type.
	 * 
	 * @return Array of maximum partition sizes.
	 * 
	 * @see #setMaxPartitionSizes(int[])
	 */
	public int[] getMaxPartitionSizes() {
		return maxPartitionSizes;
	}

	/**
	 * Compares this document type with the specified object for order. Returns
	 * a negative integer, zero, or a positive integer as this object is
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

		DocType docType = (DocType) o;

		return name.compareTo(docType.name);
	}

	/**
	 * Compares two document types for equality. The result is <code>true</code>
	 * if and only if the argument is not <code>null</code> and is a
	 * <b>DocType</b> instance with the same name.
	 *  
	 * @param obj {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */	
	public boolean equals(Object obj) {
		return compareTo(obj) == 0;
	}
}
