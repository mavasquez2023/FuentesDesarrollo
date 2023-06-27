

/*
 * @(#) DocVersion.java    1.0 19-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package  cl.araucana.pdf.publishers.implementations.fpg;


import java.io.Serializable;

import cl.araucana.core.util.AbsoluteDateTime;
import cl.araucana.core.util.StringUtils;


/**
 * This class implements a <i>Transfer Object</i> to represent a
 * <b>document version</b> like is required by the documental repository.
 *
 * <p> A document type can have many versions. Each version represents an
 * instance of fixed content of its corresponding
 * {@link cl.araucana.fpg.PDFTemplate}.
 * </p>
 * 
 * <p> Document version <b>ID</b> property must be unique in the documental
 * repository. Its value is obtained from the corresponding
 * {@link cl.araucana.fpg.PDFTemplate} usually.
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
 *            <TD> 19-10-2008 </TD>
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
public class DocVersion implements Serializable, Comparable, DocTypeConstants {

	private static final long serialVersionUID = -3787726370524873979L;
	
	private int id;
	private String publisherName;
	private AbsoluteDateTime aDateTime;
	private int focSize;
	private int fmcSize;
	private String remark;

	/**
	 * Constructs an empty document version.
	 */	
	public DocVersion() {
	}

	/**
	 * Sets internal document version id.
	 * 
	 * @param id Internal document version id.
	 * 
	 * @see #getID()
	 */	
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Gets internal document version id.
	 * 
	 * @return Internal document version id.
	 * 
	 * @see #setID(int)
	 */	
	public int getID() {
		return id;
	}

	/**
	 * Sets document version publisher agent name considering a maximum length
	 * of {@link DocTypeConstants#PUB_NAME_MAX_LENGTH} characters.
	 * 
	 * @param name Document version publisher agent name.
	 * 
	 * @see #getPublisherName()
	 */
	public void setPublisherName(String name) {
		publisherName = StringUtils.truncate(name, PUB_NAME_MAX_LENGTH);
	}

	/**
	 * Gets document version publisher agent name.
	 * 
	 * @return Document version publisher agent name.
	 * 
	 * @see #setPublisherName(String)
	 */	
	public String getPublisherName() {
		return publisherName;
	}
	
	/**
	 * Sets document version registration datetime.
	 * 
	 * @param aDateTime Document version registration datetime.
	 * 
	 * @see #getDateTime()
	 */
	public void setDateTime(AbsoluteDateTime aDateTime) {
		this.aDateTime = aDateTime;
	}

	/**
	 * Sets document version registration datetime.
	 * 
	 * @return Document version registration datetime.
	 * 
	 * @see #setDateTime(AbsoluteDateTime)
	 */
	public AbsoluteDateTime getDateTime() {
		return aDateTime;
	}

	/**
	 * Sets Fixed PDF Objects Content size in bytes.
	 * 
	 * @param size Fixed PDF Objects Content size.
	 * 
	 * @see #getFOCSize()
	 */
	public void setFOCSize(int size) {
		focSize = size;
	}

	/**
	 * Gets Fixed PDF Objects Content size in bytes.
	 * 
	 * @return Fixed PDF Objects Content size.
	 * 
	 * @see #setFOCSize(int)
	 */
	public int getFOCSize() {
		return focSize;
	}

	/**
	 * Sets Fixed PDF Metadata Content size in bytes.
	 * 
	 * @param size Fixed PDF Metadata Content size.
	 * 
	 * @see #getFMCSize()
	 */
	public void setFMCSize(int size) {
		fmcSize = size;
	}

	/**
	 * Gets Fixed PDF Metadata Content size in bytes.
	 * 
	 * @return Fixed PDF Metadata Content size.
	 * 
	 * @see #setFMCSize(int)
	 */	
	public int getFMCSize() {
		return fmcSize;
	}

	/**
	 * Sets document version remark considering a maximum length of
	 * {@link DocTypeConstants#DOC_VERSION_REMARK_MAX_LENGTH} characters.
	 * 
	 * @param remark Document version remark.
	 * 
	 * @see #getRemark()
	 */	
	public void setRemark(String remark) {
		this.remark =
				StringUtils.truncate(remark, DOC_VERSION_REMARK_MAX_LENGTH);
	}

	/**
	 * Gets document version remark.
	 * 
	 * @return Document version remark.
	 * 
	 * @see #setRemark(String)
	 */		
	public String getRemark() {
		return remark;
	}

	/**
	 * Compares this document version with the specified object for order.
	 * Returns a negative integer, zero, or a positive integer as this object is
	 * less than, equal to, or greater than the specified object.
	 * 
	 * @param o {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */		
	public int compareTo(Object o) {
		if (!(o instanceof DocVersion)) {
			throw new IllegalArgumentException();
		}

		DocVersion docVersion = (DocVersion) o;

		return id - docVersion.id;
	}

	/**
	 * Compares two document versions for equality. The result is
	 * <code>true</code> if and only if the argument is not <code>null</code>
	 * and is a <b>DocVersion</b> instance with the same ID.
	 *  
	 * @param obj {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */	
	public boolean equals(Object obj) {
		return compareTo(obj) == 0;
	}
}
