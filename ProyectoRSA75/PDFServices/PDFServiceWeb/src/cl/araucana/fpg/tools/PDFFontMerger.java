

/*
 * @(#) PDFFontMerger.java    1.0 08-03-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg.tools;


import java.util.List;

import cl.araucana.fpg.PDFFont;
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
 *            <TD> 08-03-2008 </TD>
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
public class PDFFontMerger {

	private static boolean pdfMergerDebug;

	public static void help() {
		usage();
	}

	public static void usage() {
		System.err.println(
				  "merge [-d] "
				+ "<srcTemplateDir> <srcTemplateName> "
				+ "<destTemplateDir> <destTemplateName>");
	}

	public static void main(String[] args) throws Exception {

		pdfMergerDebug = false;

		String srcTemplateDir = null;
		String srcTemplateName = null;
		String destTemplateDir = null;
		String destTemplateName = null;

		if (args.length < 4 || args.length > 5) {
			usage();

			return;
		}

		int index = 0;

		if (args[0].equals("-d")) {
			pdfMergerDebug = true;
			index = 1;
		}

		srcTemplateDir = args[index++];
		srcTemplateName = args[index++];
		destTemplateDir = args[index++];
		destTemplateName = args[index];

		log(
				  "Merging fonts "
				+ "from '" +  srcTemplateDir + "/" + srcTemplateName + "' "
				+ "to '" +  destTemplateDir + "/" + destTemplateName + "' ...");

		PDFTemplate srcTemplate =
				new PDFTemplate(srcTemplateDir, srcTemplateName);

		PDFTemplate destTemplate =
				new PDFTemplate(destTemplateDir, destTemplateName);

		boolean pdfDebug = Boolean.getBoolean("pdf.debug");

		srcTemplate.setDebugMode(pdfDebug);
		srcTemplate.load();

		destTemplate.setDebugMode(pdfDebug);
		destTemplate.load();

		List srcFonts = srcTemplate.getPDFFonts();
		List destFonts = destTemplate.getPDFFonts();

		log("Source template[" + srcTemplateName + "] fonts list:");

		for (int i = 0; i < srcFonts.size(); i++) {
			log("    " + srcFonts.get(i));
		}

		log("Destination template[" + destTemplateName + "] fonts list:");

		for (int i = 0; i < destFonts.size(); i++) {
			log("    " + destFonts.get(i));
		}

		/*
		 *
		 * Fonts merge.
		 */
		log("Merger actions:");

		for (int i = 0; i < srcFonts.size(); i++) {
			PDFFont srcFont = (PDFFont) srcFonts.get(i);
			PDFFont destFont = destTemplate.getPDFFont(srcFont.getName());

			if (destFont != null) {
				destTemplate.removeFont(destFont);
			}

			destTemplate.addFont(srcTemplate, srcFont);
		}

		destTemplate.save();

		log();
		log("Fonts merged successfully.");
	}

	private static void log() {
		log("");
	}

	private static void log(String message) {
		if (pdfMergerDebug) {
			System.err.println(message);
		}
	}
}
