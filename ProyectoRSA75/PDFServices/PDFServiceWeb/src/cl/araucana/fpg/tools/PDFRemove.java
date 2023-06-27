

/*
 * @(#) PDFRemove.java    1.0 15-03-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg.tools;


import java.io.File;

import cl.araucana.core.util.FileUtils;


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
public class PDFRemove {

	public static void help() {
		usage();
	}

	public static void usage() {
		System.err.println("remove <templateDir> <templateName>");
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

		FileUtils.removeDir(dir);

		System.out.println("template '" + templateName + "' was removed.");
	}
}
