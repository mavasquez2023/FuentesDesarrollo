

/*
 * @(#) PDFList.java    1.0 15-03-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg.tools;


import java.io.File;


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
public class PDFList {

	public static void help() {
		usage();
	}

	public static void usage() {
		System.err.println("list <templateDir>");
	}

	public static void main(String[] args) throws Exception {

		if (args.length != 1) {
			usage();

			return;
		}

		String templateDir = args[0];
		File dir = new File(templateDir);

		if (!dir.isDirectory() || !dir.exists()) {
			System.err.println("Invalid '" + dir + "' template directory.");

			return;
		}

		File[] entries = dir.listFiles();

		for (int i = 0; i < entries.length; i++) {
			if (entries[i].isDirectory()
					&& new File(entries[i], "source.pdf").exists()) {

				System.out.println(entries[i].getName());
			}
		}
	}
}
