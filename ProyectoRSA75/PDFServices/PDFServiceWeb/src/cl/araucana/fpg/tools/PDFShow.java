

/*
 * @(#) PDFShow.java    1.0 14-03-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg.tools;


import cl.araucana.fpg.PDFPage;
import cl.araucana.fpg.PDFPages;
import cl.araucana.fpg.PDFTemplate;

import java.util.List;


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
 *            <TD> 14-03-2008 </TD>
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
public class PDFShow {

	public static void help() {
		usage();
	}

	public static void usage() {
		System.err.println(
				"show {<pages> | <info> | <prolog> | <fonts>} "
			  + "<templateDir> <templateName>");
	}

	public static void main(String[] args) throws Exception {

		if (args.length != 3) {
			usage();

			return;
		}

		String itemName = args[0];
		String templateDir = args[1];
		String templateName = args[2];

		PDFTemplate template = new PDFTemplate(templateDir, templateName);

		template.setDebugMode(Boolean.getBoolean("pdf.debug"));
		template.load();

		if (itemName.equals("info") || itemName.equals("prolog")) {
			byte[] data = template.loadData(itemName);

			System.out.write(data, 0, data.length);

			return;
		}

		if (itemName.equals("pages")) {
			int nPages = template.getPageCount();

			for (int i = 0; i < nPages; i++) {
				PDFPage page = template.getPage(i);
				List contentObjIDs = page.getContentObjIDs();
				String s = "Page #" + i + " " + page.getObjID() + ": ";

				for (int j = 0; j < contentObjIDs.size(); j++) {
					s += contentObjIDs.get(j) + " ";
				}

				System.out.println(s);
			}

			return;
		}

		if (itemName.equals("fonts")) {
			PDFPages pages = template.getPDFPages();
			List fontObjRefs = pages.getFontObjRefs();

			for (int i = 0; i < fontObjRefs.size(); i++) {
				System.out.println(fontObjRefs.get(i));
			}

			return;
		}

		usage();
	}
}
