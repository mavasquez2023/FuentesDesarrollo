

/*
 * @(#) SelectAndGetDocumentsDemo.java    1.0 27-11-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.demos;


import java.io.FileOutputStream;
import java.io.IOException;

import java.text.DecimalFormat;

import java.util.List;
import java.util.Iterator;

import cl.araucana.core.util.Exceptions;

import cl.araucana.pdf.publishers.PublishedDocumentList;
import cl.araucana.pdf.publishers.PDFPublisher;
import cl.araucana.pdf.publishers.PDFPublisherException;
import cl.araucana.pdf.publishers.PublishedDocumentInfo;
import cl.araucana.pdf.publishers.Publisher;
import cl.araucana.pdf.publishers.PublisherManager;
import cl.araucana.pdf.publishers.PublishOptions;
import cl.araucana.pdf.publishers.Scope;

import cl.araucana.pdf.publishers.util.PDFDocumentNameGenerator;
import cl.araucana.pdf.publishers.util.ZippedPDFDocumentSaver;


import cl.araucana.pdf.publishers.implementations.fpg.FPGIntegratedPDFPublisher;


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
 *            <TD> 27-11-2008 </TD>
 *            <TD align="center">  1.0 </TD>
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
public class SelectAndGetDocumentsDemo {

	public static void usage() {
		System.err.println(
				  "<providerName> <docType> <maxHits> "
				+ "<scope> ... [<scope>]\n"
				+ "\n"
				+ "    <providerName> FPG PDFPublisher name.\n"
				+ "    <docType>      Document type name.\n"
				+ "    <maxHits>      Maximum hints. It must be >= 0.\n"
				+ "    <scope>        Documents scope.\n");

		System.exit(1);
	}

	public static void main(String[] args) {

		if (args.length < 4) {
			usage();
		}

		String providerName = args[0];
		String docTypeName = args[1];
		int maxHits = FPGIntegratedPDFPublisher.MAX_MAX_HITS;

		try {
			maxHits = Integer.parseInt(args[2]);

			if (maxHits < 0) {
				throw new NumberFormatException();
			}

			if (maxHits == 0) {
				maxHits = FPGIntegratedPDFPublisher.MAX_MAX_HITS;
			}
		} catch (NumberFormatException e) {
			usage();
		}

		PublisherManager manager = null;
		Publisher publisher = null;
		PDFPublisher pdfPublisher = null;

		try {
			manager = PublisherManager.getInstance();
			publisher = manager.getPublisher(providerName);

			/*
			 * Checks FPG PDFPublisher.
			 */
			if (publisher == null) {
				System.err.println("Unknown provider '" + providerName + "'.");

				usage();
			}

			if (publisher.getType() != FPGIntegratedPDFPublisher.class) {
				usage();
			}

			/*
			 *  Creates FPG PDFPublisher instance.
			 */
			PublishOptions publishOptions = new PublishOptions();

			publishOptions.setPublisher(publisher);
			publishOptions.setBatchMode(true);
			publishOptions.setPartitioned(true);
			publishOptions.setCompressed(true);
			publishOptions.setReadOnly(true);
			publishOptions.setLogged(true);
			publishOptions.setLogID("demo");

			pdfPublisher =
					PDFPublisher.newPDFPublisher(
							docTypeName, 0, publishOptions);

			/*
			 *  High level filter. It could be used for security constraints.
			 */
			// pdfPublisher.setFilter(...);

			/*
			 * A helper object will be used to generate file pathnames.
			 * with a specific pattern to apply to retrieved documents.
			 */
			String[] docIDFieldNames = pdfPublisher.getDocIDFieldNames();
			String pattern = "";

			for (int i = 0; i < docIDFieldNames.length; i++) {
				pattern += docIDFieldNames[i];

				if (i + 1 < docIDFieldNames.length) {
					pattern += "{_}";
				}
			}

			String[] docIndexFieldNames = pdfPublisher.getDocIndexFieldNames();

			PDFDocumentNameGenerator generator =
					new PDFDocumentNameGenerator(docIndexFieldNames, pattern);

			/*
			 *  SelectAndGetDocuments for each specified scope.
			 */
			for (int i = 3; i < args.length; i++) {
				Scope scope = new Scope(args[i]);

				System.out.println(
						  "\nScope -> [" + scope + "] "
						+ "format=docID::docVersion::docIndex\n");

				/*
				 *  Gets documents scope info.
				 */
				List docInfoList =
						pdfPublisher.getScopeInfo(scope, false, maxHits);

				Iterator iterator = docInfoList.iterator();

				while (iterator.hasNext()) {
					PublishedDocumentInfo docInfo =
							(PublishedDocumentInfo) iterator.next();

					System.out.println(
							  "    "
							+ docInfo.getID() + "::"
							+ docInfo.getVersion() + "::"
							+ docInfo.getIndex());
				}

				System.out.println(
						  "\n"
						+ "    *** "
						+ docInfoList.size()
						+ " documents were retrieved.");

				/*
				 *  Gets and saves documents in the scope.
				 */
				PublishedDocumentList publishedDocumentList =
						pdfPublisher.getDocuments(scope, false, maxHits);

				FileOutputStream output = null;

				try {

					/*
					 * Compresses and saves documents.
					 */
					output =
							new FileOutputStream(
									"tmp/scope" + (i - 2) + ".zip");

					ZippedPDFDocumentSaver saver =
							new ZippedPDFDocumentSaver(
									publishedDocumentList,
									generator,
									output,
									"demo");

					saver.save();
					saver.close();
					output.close();

					output = null;

					DecimalFormat df = new DecimalFormat("#.00");

					System.out.println(
							  "    *** "
							+ "#saved documents="
							+ saver.getSavedDocumentCount() + ", "
							+ "saved size="
							+ saver.getSavedSize() + " bytes, "
							+ "zipped size="
							+ saver.getSavedZippedSize() + " bytes, "
							+ "time="
							+ saver.elapsedTime() + " ms, "
							+ "rate="
							+ df.format(saver.saveRate()) + " docs/s, "
							+ "compression="
							+ df.format(saver.compressionPercent())	+ "%.");
				} catch (IOException e) {
					e.printStackTrace();

					if (output != null) {
						try {
							output.close();
						} catch (IOException e2) {}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			System.err.println(
					"Error: " + e.getMessage() + " " + Exceptions.getCauses(e));

			System.exit(3);
		} finally {
			if (pdfPublisher != null) {
				try {
					pdfPublisher.close();
				} catch (PDFPublisherException e) {}
			}
		}

		System.exit(0);
	}
}
