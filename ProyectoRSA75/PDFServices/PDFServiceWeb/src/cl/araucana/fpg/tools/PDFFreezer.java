

/*
 * @(#) PDFFreezer.java    1.0 03-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg.tools;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
 *            <TD> 03-04-2008 </TD>
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
public class PDFFreezer {

	private static boolean pdfFreezerDebug = true;

	public static void help() {
		usage();
	}

	public static void usage() {
		System.err.println("freeze <templateDir> <templateName>");
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
		template.freeze();

		byte[] fixedObjectsContent = template.getFixedObjectsContent();

		byte[] startxrefContent =
				("xref\n0 " + template.getSize() + " \n").getBytes();

		byte[] fixedXRefContent = template.getFixedXRefContent();

		byte[] trailerContent = template.trailerToByteArray();

		String freezedFileName = templateName + ".freezed.pdf";
		FileOutputStream output = null;

		try {
			output = new FileOutputStream(freezedFileName);

			output.write(fixedObjectsContent);
			output.write(startxrefContent);
			output.write(fixedXRefContent);
			output.write(trailerContent);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {}
			}
		}

		log();
		log("Freeze for template '" + templateName + "' was successful.");
		log("Freezed document was saved as '" + freezedFileName + "'.");
	}

	private static void log() {
		log("");
	}

	private static void log(String message) {
		if (pdfFreezerDebug) {
			System.err.println("PDFFreezer: " + message);
		}
	}
}
