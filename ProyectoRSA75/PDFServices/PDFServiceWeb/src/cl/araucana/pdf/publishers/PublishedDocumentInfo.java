

/*
 * @(#) PublishedDocumentInfo.java    1.0 02-06-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers;


import java.io.Serializable;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 * This class implements a <i>Transfer Object</i> to represent the
 * metadata of a published PDF document instance.
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
public class PublishedDocumentInfo implements Serializable {

	private static final long serialVersionUID = -7893533042249022587L;

	private static Pattern pattern;

	private String id;
	private String index;
	private int version;

	static {
		try {
			pattern = Pattern.compile("/");
		} catch (PatternSyntaxException e) {}
	}

	/**
	 * Constructs an empty published document info. 
	 */	
	public PublishedDocumentInfo() {
	}

	/**
	 * Sets document ID.
	 * 
	 * @param id Document ID.
	 * 
	 * @see #getID()
	 */
	public void setID(String id) {
		this.id = id;
	}

	/**
	 * Gets document ID.
	 * 
	 * @return Document ID.
	 * 
	 * @see #setID(String)
	 */
	public String getID() {
		return id;
	}

	/**
	 * Sets document Index.
	 * 
	 * @param index Document Index.
	 * 
	 * @see #getIndex()
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * Gets document Index.
	 * 
	 * @return Document Index.
	 * 
	 * @see #setIndex(String)
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * Sets document version.
	 * 
	 * @param version Document version.
	 * 
	 * @see #getVersion()
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Gets document version.
	 * 
	 * @return Document version.
	 * 
	 * @see #setVersion(int)
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Gets document ID's component parts.
	 * 
	 * @return Array of document ID's component parts.
	 * 
	 * @see #getIndexValues()
	 */
	public String[] getIDValues() {
		return pattern.split(id);
	}

	/**
	 * Gets document Index's component parts.
	 * 
	 * @return Array of document Index's component parts.
	 * 
	 * @see #getIDValues()
	 */
	public String[] getIndexValues() {
		return pattern.split(index);
	}
}
