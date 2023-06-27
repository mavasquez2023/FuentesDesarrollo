

/*
 * @(#) DocStat.java    1.0 28-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package  cl.araucana.pdf.publishers.implementations.fpg;


import java.io.Serializable;


/**
 * This class implements a <i>Transfer Object</i> to represent a
 * <b>document statistics record</b> to a specific document type
 * like is reported by <b>FPG PDF Publisher</b>.
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
 *            <TD> 28-10-2008 </TD>
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
public class DocStat implements Serializable {

	private static final long serialVersionUID = -3059277113048537073L;
	
	private String key;
	private int version;

	private int documentCount;
	private int partitionedDocumentCount;
	private int nonpartitionedDocumentCount;
	
	private long vocSize;
	private long vmcSize;
	private long focSize;
	private long fmcSize;

	/**
	 * Constructs an empty document statistics record.
	 * 
	 * @see #DocStat(DocStat)
	 */	
	public DocStat() {
	}

	/**
	 * Constructs a document statistics record cloning <code>stat</code>.
	 * 
	 * @param stat Document statistics record to be cloned.
	 * 
	 * @see #DocStat()
	 */
	public DocStat(DocStat stat) {
		key = stat.key;
		version = stat.version;
		documentCount = stat.documentCount;
		partitionedDocumentCount = stat.partitionedDocumentCount;
		nonpartitionedDocumentCount = stat.nonpartitionedDocumentCount;
		vocSize = stat.vocSize;
		vmcSize = stat.vmcSize;
		focSize = stat.focSize;
		fmcSize = stat.fmcSize;
	}

	/**
	 * Sets key or grouping criteria stat.
	 * 
	 * @param key Key stat.
	 * 
	 * @see #setKey(String)
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets key or grouping criteria stat.
	 * 
	 * @return Key stat.
	 * 
	 * @see #getKey()
	 */
	public String getKey() {
		return key;
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
	 * Sets number of published PDF document instances.
	 * 
	 * @param count Number of published PDF document instances.
	 * 
	 * @see #getDocumentCount()
	 */
	public void setDocumentCount(int count) {
		documentCount = count;
	}

	/**
	 * Gets number of published PDF document instances.
	 * 
	 * @return Number of published PDF document instances.
	 * 
	 * @see #setDocumentCount(int)
	 */
	public int getDocumentCount() {
		return documentCount;
	}

	/**
	 * Sets number of partitioned published PDF document instances.
	 * 
	 * @param count Number of partitioned published PDF document instances.
	 * 
	 * @see #getPartitionedDocumentCount()
	 */	
	public void setPartitionedDocumentCount(int count) {
		partitionedDocumentCount = count;
	}

	/**
	 * Gets number of partitioned published PDF document instances.
	 * 
	 * @return Number of partitioned published PDF document instances.
	 * 
	 * @see #setPartitionedDocumentCount(int)
	 */
	public int getPartitionedDocumentCount() {
		return partitionedDocumentCount;
	}

	/**
	 * Sets number of nonpartitioned published PDF document instances.
	 * 
	 * @param count Number of nonpartitioned published PDF document instances.
	 * 
	 * @see #getNonPartitionedDocumentCount()
	 */
	public void setNonPartitionedDocumentCount(int count) {
		nonpartitionedDocumentCount = count;
	}

	/**
	 * Gets number of nonpartitioned published PDF document instances.
	 * 
	 * @return Number of nonpartitioned published PDF document instances.
	 * 
	 * @see #setNonPartitionedDocumentCount(int)
	 */	
	public int getNonPartitionedDocumentCount() {
		return nonpartitionedDocumentCount;
	}

	/**
	 * Sets Variable PDF Objects Content size.
	 * 
	 * @param size Variable PDF Objects Content size.
	 * 
	 * @see #getVOCSize()
	 */
	public void setVOCSize(long size) {
		vocSize = size;
	}

	/**
	 * Gets Variable PDF Objects Content size.
	 * 
	 * @return Variable PDF Objects Content size.
	 * 
	 * @see #setVOCSize(long)
	 */
	public long getVOCSize() {
		return vocSize;
	}

	/**
	 * Sets Variable PDF Metadata Content size.
	 * 
	 * @param size Variable PDF Metadata Content size.
	 * 
	 * @see #getVMCSize()
	 */
	public void setVMCSize(long size) {
		vmcSize = size;
	}

	/**
	 * Gets Variable PDF Metadata Content size.
	 * 
	 * @return Variable PDF Metadata Content size.
	 * 
	 * @see #setVMCSize(long)
	 */	
	public long getVMCSize() {
		return vmcSize;
	}

	/**
	 * Sets Fixed PDF Objects Content size.
	 * 
	 * @param size Fixed PDF Objects Content size.
	 * 
	 * @see #getFOCSize()
	 */	
	public void setFOCSize(long size) {
		focSize = size;
	}

	/**
	 * Gets Fixed PDF Objects Content size.
	 * 
	 * @return Fixed PDF Objects Content size.
	 * 
	 * @see #setFOCSize(long)
	 */	
	public long getFOCSize() {
		return focSize;
	}

	/**
	 * Sets Fixed PDF Metadata Content size.
	 * 
	 * @param size Fixed PDF Metadata Content size.
	 * 
	 * @see #getFMCSize()
	 */	
	public void setFMCSize(long size) {
		fmcSize = size;
	}

	/**
	 * Gets Fixed PDF Metadata Content size.
	 * 
	 * @return Fixed PDF Metadata Content size.
	 * 
	 * @see #setFMCSize(long)
	 */	
	public long getFMCSize() {
		return fmcSize;
	}
}
