

/*
 * @(#) Publication.java    1.0 19-10-2008
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
 * <b>publication</b> like is required by the documental repository.
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
public class Publication implements Serializable, Comparable, DocTypeConstants {

	private static final long serialVersionUID = -2384585096319443298L;
	
	private int id;
	private String systemName;
	private String publisherName;
	private int version;
	private AbsoluteDateTime aDateTime;
	private String remark;
	private boolean opened;
	private int nDocuments;

	/**
	 * Constructs an empty publication.
	 */		
	public Publication() {
	}

	/**
	 * Sets internal publication id.
	 * 
	 * @param id Internal publication id.
	 * 
	 * @see #getID()
	 */	
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Gets internal publication id.
	 * 
	 * @return Internal publication id.
	 * 
	 * @see #setID(int)
	 */	
	public int getID() {
		return id;
	}

	/**
	 * Sets publication source system name considering a maximum length of
	 * {@link DocTypeConstants#SS_NAME_MAX_LENGTH} characters.
	 * 
	 * @param name Publication source system name.
	 * 
	 * @see #getSystemName()
	 */	
	public void setSystemName(String name) {
		systemName = name;
	}

	/**
	 * Gets publication source system name.
	 * 
	 * @return Publication source system name.
	 * 
	 * @see #setSystemName(String)
	 */	
	public String getSystemName() {
		return systemName;
	}

	/**
	 * Sets publisher agent name considering a maximum length
	 * of {@link DocTypeConstants#PUB_NAME_MAX_LENGTH} characters.
	 * 
	 * @param name Publisher agent name.
	 * 
	 * @see #getPublisherName()
	 */
	public void setPublisherName(String name) {
		publisherName = StringUtils.truncate(name, PUB_NAME_MAX_LENGTH);
	}

	/**
	 * Gets publisher agent name.
	 * 
	 * @return Publisher agent name.
	 * 
	 * @see #setPublisherName(String)
	 */	
	public String getPublisherName() {
		return publisherName;
	}

	/**
	 * Sets publication document version.
	 * 
	 * @param version Document version.
	 * 
	 * @see #getVersion()
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Gets publication document version.
	 * 
	 * @return Document version.
	 * 
	 * @see #setVersion(int)
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Sets publication registration datetime.
	 * 
	 * @param aDateTime Publication registration datetime.
	 * 
	 * @see #getDateTime()
	 */
	public void setDateTime(AbsoluteDateTime aDateTime) {
		this.aDateTime = aDateTime;
	}

	/**
	 * Sets publication registration datetime.
	 * 
	 * @return Publication registration datetime.
	 * 
	 * @see #setDateTime(AbsoluteDateTime)
	 */
	public AbsoluteDateTime getDateTime() {
		return aDateTime;
	}

	/**
	 * Sets publication remark considering a maximum length of
	 * {@link DocTypeConstants#DOC_PUB_REMARK_MAX_LENGTH} characters.
	 * 
	 * @param remark Publication type remark.
	 * 
	 * @see #getRemark()
	 */	
	public void setRemark(String remark) {
		this.remark = StringUtils.truncate(remark, DOC_PUB_REMARK_MAX_LENGTH);
	}

	/**
	 * Gets publication remark.
	 * 
	 * @return Publication remark.
	 * 
	 * @see #setRemark(String)
	 */		
	public String getRemark() {
		return remark;
	}

	/**
	 * Sets publication open control flag.
	 * 
	 * @param opened Open control flag.
	 * 
	 * @see #isOpened()
	 */
	public void setOpened(boolean opened) {
		this.opened = opened;
	}

	/**
	 * Indicates if publication is opened.
	 * 
	 * @return Open control flag, <code>true</code> when is opened,
	 *         <code>false</code> otherwise.
	 */
	public boolean isOpened() {
		return opened;
	}

	/**
	 * Sets number of published documents stored in this publication.
	 * 
	 * @param nDocuments Number of published documents.
	 * 
	 * @see #getNDocuments()
	 */
	public void setNDocuments(int nDocuments) {
		this.nDocuments = nDocuments;
	}

	/**
	 * Gets number of published documents stored in this publication.
	 * 
	 * @return Number of published documents.
	 * 
	 * @see #setNDocuments(int)
	 */
	public int getNDocuments() {
		return nDocuments;
	}

	/**
	 * Compares this publication with the specified object for order.
	 * Returns a negative integer, zero, or a positive integer as this object is
	 * less than, equal to, or greater than the specified object.
	 * 
	 * @param o {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */		
	public int compareTo(Object o) {
		if (!(o instanceof Publication)) {
			throw new IllegalArgumentException();
		}

		Publication publication = (Publication) o;

		return id - publication.id;
	}

	/**
	 * Compares two publications for equality. The result is
	 * <code>true</code> if and only if the argument is not <code>null</code>
	 * and is a <b>Publication</b> instance with the same ID.
	 *  
	 * @param obj {@inheritDoc}
	 * 
	 * @return {@inheritDoc}
	 */		
	public boolean equals(Object obj) {
		return compareTo(obj) == 0;
	}
}
