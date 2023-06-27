

/*
 * @(#) PDFInfoFixer.java    1.0 26-03-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg.tools;


import java.io.File;

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
 *            <TD> 26-03-2008 </TD>
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
public class PDFInfoFixer {

	private static boolean pdfFixerDebug = true;
	private static final String FPG_PRODUCER = "FPG 1.0";

	public static void help() {
		usage();
	}

	public static void usage() {
		System.err.println(
				  "fixinfo <templateDir> <templateName> "
				+ "[-a <author>] [-c <creator>] [-t <title>] [-s <subject>] "
				+ "[-k <keywords>] [-d <creationDate>]");
	}

	public static void main(String[] args) throws Exception {

		String author = null;
		String creator = null;
		String title = null;
		String subject = null;
		String keywords = null;
		String creationDate = null;

		if (args.length < 2 || args.length % 2 != 0) {
			usage();

			return;
		}

		for (int i = 2; i < args.length; i += 2) {
			if (args[i].length() != 2 || args[i].charAt(0) != '-') {
				usage();

				return;
			}

			char option = args[i].charAt(1);

			switch (option) {
				case 'a':
					author = args[i + 1];

					break;

				case 'c':
					creator = args[i + 1];

					break;

				case 't':
					title = args[i + 1];

					break;

				case 's':
					subject = args[i + 1];

					break;

				case 'k':
					keywords = args[i + 1];

					break;

				case 'd':
					creationDate = args[i + 1];

					break;

				default:
					usage();

					return;
			}
		}

		String templateDir = args[0];
		String templateName = args[1];
		File dir = new File(templateDir + "/" + templateName);

		if (!dir.isDirectory()
				|| !new File(dir, "source.pdf").exists()) {

			System.err.println("Invalid '" + dir + "' directory.");

			return;
		}

		/*
		 * PDF Info Template:
		 *
		 * /Author (La Araucana C.C.A.F.)
		 * /Creator (CP)
		 * /Producer (FPG 1.0)
		 * /Title (COTIZACIONES)
		 * /Subject (ISAPRE BANMEDICA)
		 * /Keywords (ISAPRE:0:200802:70016160-9:LA ARAUCANA C.C.A.F.:999:1:2)
		 * /CreationDate (2008/03/18 14:50:00 GMT-4:00)
		 *
		 */
		String sInfoData = "<<\n";

		if (author != null) {
			sInfoData += "/Author (" + author + ")\n";
		}

		if (creator != null) {
			sInfoData += "/Creator (" + creator + ")\n";
		}

		sInfoData += "/Producer (" + FPG_PRODUCER + ")\n";

		if (title != null) {
			sInfoData += "/Title (" + title + ")\n";
		}

		if (subject != null) {
			sInfoData += "/Subject (" + subject + ")\n";
		}

		if (keywords != null) {
			sInfoData += "/Keywords (" + keywords + ")\n";
		}

		if (creationDate != null) {
			sInfoData += "/CreationDate (" + creationDate + ")\n";
		}

		sInfoData += ">>\nendobj\n";

		byte[] infoData = sInfoData.getBytes();
		PDFTemplate template = new PDFTemplate(templateDir, templateName);

		template.setDebugMode(Boolean.getBoolean("pdf.debug"));
		template.load();
		template.setInfo(infoData);
		template.saveInfo();
		template.saveData("info", infoData, PDFTemplate.EXTENSION_CODED);

		log();
		log("Info for template '" + templateName + "' was fixed.");
	}


	private static void log() {
		log("");
	}

	private static void log(String message) {
		if (pdfFixerDebug) {
			System.err.println("PDFInfoFixer: " + message);
		}
	}
}
