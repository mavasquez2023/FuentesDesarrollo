

/*
 * @(#) FPGShell.java    1.0 14-03-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.fpg.tools;


import java.util.HashMap;
import java.util.Map;


import cl.araucana.core.util.shell.Shell;


/**
 * This is a specialized shell based on CLI (<b>Command Line Interface</b>)
 * {@link cl.araucana.core.util.shell.Shell} to manage different
 * {@link cl.araucana.fpg.PDFTemplate} instances.
 *
 * <p> This shell supports the following collection of commands:
 * </p>
 *
 * <BR>
 * 
 * <TABLE ALIGN="CENTER" BORDER="1" WIDTH="70%" CELLPADDING="3" CELLSPACING="0">
 *     <tr>
 *        <th bgcolor="black">
 *            <font color="white"><strong>Command</strong></font>
 *        </th>
 *        
 *        <th bgcolor="black">
 *            <font color="white"><strong>Description</strong></font>
 *        </th>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link PDFAssembler ass}
 *        </td>
 *        
 *        <td>
 *            Assembles a PDF Template to produce a reference PDF document.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link PDFDisassembler dis}
 *        </td>
 *        
 *        <td>
 *            Disassembles a PDF Document to create a PDF Template.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link PDFHeaderDetailPagesFixer fixhdpages}
 *        </td>
 *        
 *        <td>
 *            Fixes header and detail pages of a PDF Template.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link PDFInfoFixer fixinfo}
 *        </td>
 *        
 *        <td>
 *            Fixes Info PDF Object of a PDF Template.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link PDFPrologFixer fixprolog}
 *        </td>
 *        
 *        <td>
 *            Fixes Prolog PDF Object of a PDF Template.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link FPGCompiler fpgc}
 *        </td>
 *        
 *        <td>
 *            Compiles a <i>FPG Coded</i> PDF Template.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link PDFImage image2pdf}
 *        </td>
 *        
 *        <td>
 *            Converts a <i>JPG</i> or <i>GIF</i> image to a PDF XObject.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link PDFList list}
 *        </td>
 *        
 *        <td>
 *            Lists PDF Templates stored in a directory.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link PDFFontMerger merge}
 *        </td>
 *        
 *        <td>
 *            Merges PDF Fonts from a source PDF Template to a destination one.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link PDFObjectExtractor objectextract}
 *        </td>
 *        
 *        <td>
 *            Extracts a PDF Object from a PDF File.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link PDFRemove remove}
 *        </td>
 *        
 *        <td>
 *            Removes a PDF Template.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link PDFShow show}
 *        </td>
 *        
 *        <td>
 *            Shows high level descriptions for some PDF Object types.
 *        </td>
 *     </tr>
 *     
 *     <tr>
 *        <td>
 *        {@link PDFViewer view}
 *        </td>
 *        
 *        <td>
 *            Shows a PDF Document.
 *        </td>
 *     </tr>
 * </TABLE>
 *
 * <BR>
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
public class FPGShell extends Shell {

	private static final Map params;

	static {
		params = new HashMap();

		String title =
			  "Fast PDF Generator Shell Version 1.0 14/03/2008.\n"
			+ "              La Araucana C.C.A.F.\n\n";

		params.put(Shell.TITLE, title);
		params.put(Shell.PROMPT, "fpg> ");

		params.put(
				Shell.DEFAULT_PROPERTIES_RESOURCE,
				"/etc/fpg/fpgshell.properties");

		params.put(
				Shell.DEFAULT_SCRIPT,
				System.getProperty("user.home") + "/fpg.ini");

		params.put(Shell.DEBUG_SYSTEM_PROPERTY_NAME, "pdf.debug");
	}

	/**
	 * Constructs a new shell instance with default parameters collection. 
	 */
	public FPGShell() {
		super(params);
	}

	/**
	 * FPG Shell Launcher.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {

		FPGShell shell = new FPGShell();

		try {
			shell.execute();
		} catch (Exception e) {
			System.err.println(
					"Shell was aborted. [cause=" + e.getMessage() + "]");
		}
	}
}
