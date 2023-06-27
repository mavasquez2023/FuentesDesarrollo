

/*
 * @(#) PDFHDPagesProducer.java    1.0 05-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg.tools;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import cl.araucana.fpg.DocumentModel;
import cl.araucana.fpg.PDFDocument;
import cl.araucana.fpg.PDFGenerator;
import cl.araucana.fpg.PDFObject;
import cl.araucana.fpg.PDFPage;
import cl.araucana.fpg.PDFPages;
import cl.araucana.fpg.PDFTemplate;


/**
 * ...
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
 *            <TD> 05-04-2008 </TD>
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
public class PDFHDPagesProducer {

	private static boolean pdfProducerDebug = true;

	public static void help() {
		usage();
	}

	public static void usage() {
		System.err.println("hdproduce <templateDir> <templateName>");
	}

	public static void main(String[] args) throws Exception {

		if (args.length != 2) {
			usage();

			return;
		}

		String templateDir = args[0];
		String templateName = args[1];
		File dir = new File(templateDir + "/" + templateName);

		if (!dir.isDirectory()
				|| !new File(dir, "source.pdf").exists()) {

			System.err.println("Invalid '" + dir + "' directory.");

			return;
		}

		PDFTemplate template = new PDFTemplate(templateDir, templateName);

		template.setDebugMode(Boolean.getBoolean("pdf.debug"));
		template.load();

// ============================================================================
		final int HEADER_PAGE_NO = 0;
		final int DETAIL_PAGE_NO = 1;

		final int CONTENT_PROLOG = 0;
		final int CONTENT_BACKGROUND = 1;
		final int CONTENT_FOREGROUND = 2;

		Class docModelClass = null;
		DocumentModel docModel = null;

		/*
		 * Prepares PDF template to be exploited by PDF Generator.
		 */

		int nPages = template.getPageCount();

		// Checks PDF template must be two pages only (header and detail pages).
		if (nPages != 2) {
				throw new Exception("Both PDF documents must have 2 pages.");
		}

		// Gets header and detail PDF pages.
		PDFPage headerPage = template.getPage(HEADER_PAGE_NO);
		PDFPage detailPage = template.getPage(DETAIL_PAGE_NO);

		// Gets objects ids required to generate new pages.
		int infoObjID = template.getInfoObjID();

		int commonPagePrologObjID = headerPage.getContentObjID(CONTENT_PROLOG);

		int headerBackgroundObjID =
				headerPage.getContentObjID(CONTENT_BACKGROUND);

		int headerForegroundObjID =
				headerPage.getContentObjID(CONTENT_FOREGROUND);

		int detailBackgroundObjID =
				detailPage.getContentObjID(CONTENT_BACKGROUND);

		int detailForegroundObjID =
				detailPage.getContentObjID(CONTENT_FOREGROUND);

		// Gets variable PDF objects required.
		PDFObject info = template.getObject(infoObjID);

		PDFObject headerForeground =
				template.getObject(
						headerForegroundObjID, PDFTemplate.EXTENSION_INFLATED);

		PDFObject detailForeground =
				template.getObject(
						detailForegroundObjID, PDFTemplate.EXTENSION_INFLATED);

		// Releases variable PDF objects required from PDF template.
		template.releaseObject(infoObjID);
		template.releaseObject(headerForegroundObjID);
		template.releaseObject(detailForegroundObjID);

		// Releases original header and detail pages from PDF template.
		template.releasePage(headerPage);
		template.releasePage(detailPage);

		// Releases PDFPages object from PDF template.
		PDFPages pdfPages = template.getPDFPages();

		template.releaseObject(pdfPages.getObjID());

		// Release root catalog object from PDF template.
		template.releaseObject(template.getRootObjID());

		// Freezes PDF template.
		template.freeze();

		// Prepares data for new pages.
		byte[] commonPageHeader = headerPage.getHeader();

		int[] headerPageContentObjIDs =
				{ commonPagePrologObjID, headerBackgroundObjID, 0 };

		int[] detailPageContentObjIDs =
				{ commonPagePrologObjID, detailBackgroundObjID, 0 };

		// Creates PDF Generator instance.
		PDFGenerator generator = new PDFGenerator(template, docModelClass);

// VARIABLE COST.
		PDFDocument document = generator.newPDFDocument(docModel, 256 * 1024);

		// PDF info object production.
		document.execute(info, false, true);
		document.setInfoObjID(info.getNewObjID());

		// Header page production.
		document.execute(headerForeground);

		// Adds header page to new PDF document.
		PDFPage newPage = new PDFPage(0);

		headerPageContentObjIDs[CONTENT_FOREGROUND] =
				headerForeground.getNewObjID();

		newPage.setHeader(commonPageHeader);
		newPage.addContentObjIDs(headerPageContentObjIDs);

		document.addNewPage(newPage);

	int times = 1;

	try {
		times = Integer.parseInt(System.getProperty("times"));
	} catch (Exception e) {}

for (int i = 0; i < times; i++) {
		// Adds detail page to new PDF document.
		document.execute(detailForeground);

		newPage = new PDFPage(0);

		detailPageContentObjIDs[CONTENT_FOREGROUND] =
				detailForeground.getNewObjID();

		newPage.setHeader(commonPageHeader);
		newPage.addContentObjIDs(detailPageContentObjIDs);

		document.addNewPage(newPage);
}

		document.close();
// VARIABLE COST.
// ============================================================================

		String pdfFileName = templateName + ".produced.pdf";
		FileOutputStream output = null;

		try {
			output = new FileOutputStream(pdfFileName);
			output.write(document.getContent());
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {}
			}
		}

		log();
		log("PDF document was produced as '" + pdfFileName + "'.");

		int[] stat = document.getContentSizes();

		log("fixed content size = " + stat[0] + " bytes");
		log("variable content size = " + stat[1] + " bytes");
		log("total content size = " + stat[2] + " bytes");
	}

	private static void log() {
		log("");
	}

	private static void log(String message) {
		if (pdfProducerDebug) {
			System.err.println("PDFHDPagesProducer: " + message);
		}
	}
}
