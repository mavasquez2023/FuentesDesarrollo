

/*
 * @(#) PDFList.java    1.0 15-03-2008
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
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
 *            <TD align="center"> <B>Versi�n</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripci�n</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 15-03-2008 </TD>
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
