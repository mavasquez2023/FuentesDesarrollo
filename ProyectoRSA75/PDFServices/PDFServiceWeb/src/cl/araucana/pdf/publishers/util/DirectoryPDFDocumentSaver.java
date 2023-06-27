

/*
 * @(#) DirectoryPDFDocumentSaver.java    1.0 14-10-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.pdf.publishers.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.PublishedDocument;
import cl.araucana.pdf.publishers.PublishedDocumentList;


/**
 * This class implements a PDF documents saver that saves collection of
 * retrieved PDF documents to the specified output directory.
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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 14-10-2008 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Germ�n Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versi�n inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germ�n Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class DirectoryPDFDocumentSaver extends PDFDocumentSaver {

	private String outputDirName;
	private boolean patternWithSubDirectory;

	/**
	 * Constructs a saver instance to save <code>publishedDocumentList</code>
	 * collection of PDF document instances generation their pathnames with
	 * <code>generator</code>.
	 * 
	 * @param publishedDocumentList Collection of PDF document instances to be
	 *        saved.
	 *        
	 * @param generator PDF document pathname generator.
	 * 
	 * @param outputDirName Output directory.
	 */	
	public DirectoryPDFDocumentSaver(
			PublishedDocumentList publishedDocumentList,
			PDFDocumentNameGenerator generator, String outputDirName)
			throws IOException {

		super(publishedDocumentList, generator);

		File dir = new File(outputDirName);

		if (dir.exists()) {
			if (!dir.isDirectory()) {
				throw new IOException(
						"'" + outputDirName + "' is not a directory");
			}
		} else if (!dir.mkdirs()) {
			throw new IOException(
					"Cannot create directory '" + outputDirName + "'");
		}

		this.outputDirName = outputDirName;

		String[] separators = generator.getSeparators();
		boolean notSlashSeparator = false;

		for (int i = 0; i < separators.length; i++) {
			if (notSlashSeparator) {
				if (separators[i].equals("/")) {
					throw new IOException(
							"Invalid generation pattern to save.");
				}

				notSlashSeparator = true;
			} else if (separators[i].equals("/")) {
				patternWithSubDirectory = true;
			} else {
				notSlashSeparator = true;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */	
	public void save(PublishedDocument publishedDocument)
			throws PDFPublisherException, IOException {

		FileOutputStream output = null;

		try {
			String fileName =
					generator.generateName(publishedDocument.getIndex());

			if (patternWithSubDirectory) {
				int index = fileName.lastIndexOf('/');
				String subDirName = fileName.substring(0, index);

				File subDir = new File(outputDirName + "/" + subDirName);

				if (!subDir.exists() && !subDir.mkdirs()) {
					throw new IOException(
							  "Cannot create subdirectory "
							+ "'"
							+ outputDirName
							+ "/"
							+ subDirName
							+ "'");
				}
			}

			output = new FileOutputStream(outputDirName + "/" + fileName);

			publishedDocument.writeTo(output);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {}
			}
		}
	}

	/**
	 * Closes this saver.
	 */
	public void close() {
	}
}
