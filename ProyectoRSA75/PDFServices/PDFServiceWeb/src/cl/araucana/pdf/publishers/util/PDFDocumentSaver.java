

/*
 * @(#) PDFDocumentSaver.java    1.0 14-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.util;


import java.io.IOException;

import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.PublishedDocument;
import cl.araucana.pdf.publishers.PublishedDocumentList;


/**
 * This abstract class defines a common base to implement different schemas
 * to save collection of retrieved PDF document instances.
 * 
 * <p> Each retrieved PDF document instance will be checked by a filter to
 * permits a double control level that determines if save it or not finally.
 * </p>
 *
 * <p> To use subclasses of this class there are three main steps:
 * </p>
 * 
 * <ol>
 * <li> Construct a saver instance.
 * <li> Call to saver's <code>save()</code> method, and finally
 * <li> Call to saver's <code>close()</code> method.
 * </ol>
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
 *            <TD> 14-10-2008 </TD>
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
public abstract class PDFDocumentSaver {

	/**
	 * Collection of PDF document instances to be saved.
	 */
	protected PublishedDocumentList publishedDocumentList;
	
	/**
	 * PDF document pathname generator.
	 */
	protected PDFDocumentNameGenerator generator;

	private int nSavedDocuments = 0;
	private long savedSize = 0L;

	private long ti;
	private long tf;

	/**
	 * Constructs a saver instance to save <code>publishedDocumentList</code>
	 * collection of PDF document instances generation their pathnames with
	 * <code>generator</code>.
	 * 
	 * @param publishedDocumentList Collection of PDF document instances to be
	 *        saved.
	 *        
	 * @param generator PDF document pathname generator.
	 */
	public PDFDocumentSaver(PublishedDocumentList publishedDocumentList,
			PDFDocumentNameGenerator generator) {

		this.publishedDocumentList = publishedDocumentList;
		this.generator = generator;
	}

	/**
	 * Saves the collection of retrieved PDF document instances with this saver.
	 * 
	 * @throws PDFPublisherException If cannot save PDF document instances.
	 * 
	 * @throws IOException If an I/O error occurs.
	 * 
	 * @see #filter(PublishedDocument)
	 * @see #save()
	 */
	public final void save() throws PDFPublisherException, IOException {

		ti = System.currentTimeMillis();

		while (publishedDocumentList.next()) {
			PublishedDocument publishedDocument =
					publishedDocumentList.getPublishedDocument();

			if (filter(publishedDocument)) {
				continue;
			}

			save(publishedDocument);

			nSavedDocuments++;
			savedSize += publishedDocument.getSize();
		}

		tf = System.currentTimeMillis();
	}

	/**
	 * Checks if a retrieved PDF document must be saved or not.
	 * 
	 * @param publishedDocument Retrieved PDF document to be checked.
	 * 
	 * @return <code>false</code>, i.e., all documents will be saved.
	 */
	public boolean filter(PublishedDocument publishedDocument) {
		return false;
	}

	/**
	 * Saves a retrieved PDF document instance with this saver.
	 * 
	 * @param publishedDocument Retrieved PDF document instance to be saved.
	 * 
	 * @throws PDFPublisherException If cannot save PDF document instance.
	 * 
	 * @throws IOException If an I/O error occurs.
	 */
	public abstract void save(PublishedDocument publishedDocument)
			throws PDFPublisherException, IOException;

	/**
	 * Closes this saver.
	 * 
	 * @throws PDFPublisherException If cannot close saver normally.
	 * 
	 * @throws IOException If an I/O error occurs.
	 */
	public abstract void close() throws PDFPublisherException, IOException;

	/**
	 * Gets number of saved PDF documents.
	 * 
	 * @return Number of saved PDF documents.
	 */
	public final int getSavedDocumentCount() {
		return nSavedDocuments;
	}

	/**
	 * Gets PDF documents saved size in bytes.
	 * 
	 * @return PDF documents saved size.
	 */
	public final long getSavedSize() {
		return savedSize;
	}

	/**
	 * Gets save time in milliseconds.
	 * 
	 * @return Save time in milliseconds.
	 */
	public final long elapsedTime() {
		return tf - ti;
	}
 
	/**
	 * Calculates save PDF documents rate per seconds.
	 * 
	 * @return Save PDF documents rate.
	 */
	public final double saveRate() {
		double dt = elapsedTime() / 1000.0d;

		return nSavedDocuments / dt;
	}
}
