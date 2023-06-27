

/*
 * @(#) PDFHeaderDetailPagesFixer.java    1.0 15-03-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg.tools;


import java.io.IOException;

import cl.araucana.fpg.PDFPage;
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
 *            <TD> 15-03-2008 </TD>
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
public class PDFHeaderDetailPagesFixer {

	private static boolean pdfFixerDebug;

	/*
	 *  fpg> show pages $isapres banmedica
	 *
	 *		Page #0 268: 217 218 222
	 *		Page #1 307: 217 286 289
	 *
	 *	fpg> show pages $isapres ferrosalud
	 *
	 *		Page #0 265: 217 218 221
	 *		Page #1 304: 282 283 285
     */

	public static void help() {
		usage();
	}

	public static void usage() {
		System.err.println(
				  "fixhdpages [-d] "
				+ "<srcTemplateDir> <srcTemplateName> "
				+ "<destTemplateDir> <destTemplateName>");
	}

	public static void main(String[] args) throws Exception {

		if (args.length < 4 || args.length > 5) {
			usage();

			return;
		}

		pdfFixerDebug = false;

		String srcTemplateDir = null;
		String srcTemplateName = null;
		String destTemplateDir = null;
		String destTemplateName = null;

		int index = 0;

		if (args[0].equals("-d")) {
			if (args.length != 5) {
				usage();

				return;
			}

			pdfFixerDebug = true;
			index = 1;
		}

		srcTemplateDir = args[index++];
		srcTemplateName = args[index++];
		destTemplateDir = args[index++];
		destTemplateName = args[index];

		PDFTemplate srcTemplate =
				new PDFTemplate(srcTemplateDir, srcTemplateName);

		srcTemplate.setDebugMode(Boolean.getBoolean("pdf.debug"));
		srcTemplate.load();

		PDFTemplate destTemplate =
				new PDFTemplate(destTemplateDir, destTemplateName);

		destTemplate.setDebugMode(Boolean.getBoolean("pdf.debug"));
		destTemplate.load();

		int srcTemplateNPages = srcTemplate.getPageCount();
		int destTemplateNPages = destTemplate.getPageCount();

		log("Source Template = " + srcTemplate.getPath());
		log("Destin Template = " + destTemplate.getPath());

		if (srcTemplateNPages != 2 || destTemplateNPages != 2) {
			System.err.println("Both PDF documents must have 2 pages.");

			return;
		}

		PDFPage srcHeaderPage = srcTemplate.getPage(0);
		PDFPage srcDetailPage = srcTemplate.getPage(1);

		int srcHeaderForegroundObjID = srcHeaderPage.getContentObjID(2);
		int srcDetailForegroundObjID = srcDetailPage.getContentObjID(2);

		PDFPage destHeaderPage = destTemplate.getPage(0);
		PDFPage destDetailPage = destTemplate.getPage(1);

		int destHeaderPrologObjID = destHeaderPage.getContentObjID(0);
		int destHeaderBackgroundObjID = destHeaderPage.getContentObjID(1);
		int destDetailBackgroundObjID = destDetailPage.getContentObjID(1);
		int destHeaderForegroundObjID = destHeaderPage.getContentObjID(2);
		int destDetailForegroundObjID = destDetailPage.getContentObjID(2);

		destDetailPage.setContentObjID(0, destHeaderPrologObjID);

		destTemplate.linkObject(
				destHeaderForegroundObjID,
				srcTemplate,
				srcHeaderForegroundObjID,
				PDFTemplate.EXTENSION_INFLATED);

		destTemplate.linkObject(
				destDetailForegroundObjID,
				srcTemplate,
				srcDetailForegroundObjID,
				PDFTemplate.EXTENSION_INFLATED);

		try {
			destTemplate.linkObject(
					destHeaderForegroundObjID,
					srcTemplate,
					srcHeaderForegroundObjID,
					PDFTemplate.EXTENSION_CODED);
		} catch (IOException e) {}

		try {
			destTemplate.linkObject(
					destDetailForegroundObjID,
					srcTemplate,
					srcDetailForegroundObjID,
					PDFTemplate.EXTENSION_CODED);
		} catch (IOException e) {}

		try {
			destTemplate.removeData(
					"text/" + destHeaderBackgroundObjID,
					PDFTemplate.EXTENSION_INFLATED);
		} catch (IOException e) {}

		try {
			destTemplate.removeData(
					"text/" + destDetailBackgroundObjID,
					PDFTemplate.EXTENSION_INFLATED);
		} catch (IOException e) {}

		destTemplate.save();

		log();
		log("hdpages mixed successfully.");
	}

	private static void log() {
		log("");
	}

	private static void log(String message) {
		if (pdfFixerDebug) {
			System.err.println("PDFHeaderDetailPagesFixer: " + message);
		}
	}
}
