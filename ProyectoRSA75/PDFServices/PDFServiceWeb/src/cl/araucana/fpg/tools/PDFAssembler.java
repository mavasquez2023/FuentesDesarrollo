

/*
 * @(#) PDFAssembler.java    1.0 27-02-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg.tools;


import java.io.FileOutputStream;

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
 *            <TD> 27-02-2008 </TD>
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
public class PDFAssembler {

	public static void help() {
		usage();
	}

	public static void usage() {
		System.err.println("ass <templateDir> <templateName> [<outputDir>]");
	}

	public static void main(String[] args) throws Exception {

		if (args.length < 2 || args.length > 3) {
			usage();

			return;
		}

		String templateDir = args[0];
		String templateName = args[1];
		String outputDir = (args.length == 3) ? args[2] : ".";

		System.err.println(
				"Assembling PDF template '" + templateName + "' ...");

		PDFTemplate template = new PDFTemplate(templateDir, templateName);

		template.setDebugMode(Boolean.getBoolean("pdf.debug"));
		template.load();

		byte[] content = template.assemble();
		String pdfFileName = outputDir + "/" + templateName + ".pdf";

		FileOutputStream output = new FileOutputStream(pdfFileName);

		output.write(content);
		output.close();

		System.err.println(
				  "Assembled PDF document was saved as "
				+ "'" + pdfFileName + "'.");
	}
}
