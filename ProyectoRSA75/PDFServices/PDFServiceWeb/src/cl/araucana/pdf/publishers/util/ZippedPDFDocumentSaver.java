

/*
 * @(#) ZippedPDFDocumentSaver.java    1.0 14-10-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers.util;


import java.io.IOException;
import java.io.OutputStream;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import cl.araucana.core.util.AccountedOutputStream;

import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.PublishedDocument;
import cl.araucana.pdf.publishers.PublishedDocumentList;


/**
 * This class implements a PDF documents saver that compresses collection of
 * retrieved PDF documents zipping it to the specified output stream.
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
public class ZippedPDFDocumentSaver extends PDFDocumentSaver {

	private AccountedOutputStream accountedOutput;
	private ZipOutputStream zippedOutput;
	private String prefixDirName;

	/**
	 * Constructs a saver instance to zip <code>publishedDocumentList</code>
	 * collection of PDF document instances generation their pathnames with
	 * <code>generator</code> and saving it to <code>output</code>.
	 * 
	 * <p> <code>prefixDirName</code> will be used like prefix directory for
	 * each generated PDF document pathname in the zipped output.
	 * </p>
	 * 
	 * @param publishedDocumentList Collection of PDF document instances to be
	 *        saved.
	 *        
	 * @param generator PDF document pathname generator.
	 * 
	 * @param output Output stream to save.
	 * 
	 * @param prefixDirName Prefix directory to apply to generated pathnames.
	 */	
	public ZippedPDFDocumentSaver(
			PublishedDocumentList publishedDocumentList,
			PDFDocumentNameGenerator generator, OutputStream output,
			String prefixDirName) throws IOException {

		super(publishedDocumentList, generator);

		accountedOutput = new AccountedOutputStream(output);
		zippedOutput = new ZipOutputStream(accountedOutput);

		if (prefixDirName == null) {
			prefixDirName = "";
		} else if (!prefixDirName.endsWith("/")) {
			prefixDirName += "/";
		}

		this.prefixDirName = prefixDirName;
	}

	/**
	 * {@inheritDoc}
	 */
	public void save(PublishedDocument publishedDocument)
			throws PDFPublisherException, IOException {

		String fileName =
				generator.generateName(publishedDocument.getIndex());

		ZipEntry entry = new ZipEntry(prefixDirName + fileName);

		zippedOutput.putNextEntry(entry);

		publishedDocument.writeTo(zippedOutput);

		zippedOutput.closeEntry();
	}

	/**
	 * Closes this saver.
	 * 
	 * @throws IOException If an I/O error occurs.
	 */
	public void close() throws IOException {

		/*
		 *  Puts a special zip entry to avoid empty zip data set.
		 */
		if (getSavedZippedSize() == 0) {
			ZipEntry entry = new ZipEntry(prefixDirName + "WARNING.txt");
			byte[] message = "No documents were retrieved.\n".getBytes();

			zippedOutput.putNextEntry(entry);
			zippedOutput.write(message, 0, message.length);
			zippedOutput.closeEntry();
		}

		zippedOutput.close();
	}

	/**
	 * Gets saved zipped size in bytes.
	 * 
	 * @return Saved zipped size.
	 */
	public long getSavedZippedSize() {
		return accountedOutput.size();
	}

	/**
	 * Calculates compression percent.
	 * 
	 * @return Compression percent.
	 */
	public double compressionPercent() {
		long savedSize = getSavedSize();
		long savedZippedSize = getSavedZippedSize();

		if (savedSize == 0) {
			return 0.0d;
		}

		return 100.0d * (savedSize - savedZippedSize) / savedSize;
	}
}
