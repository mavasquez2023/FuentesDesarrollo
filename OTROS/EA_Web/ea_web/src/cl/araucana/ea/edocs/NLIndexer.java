
/*
 * @(#) NLIndexer.java    1.0 30-10-2006
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.ea.edocs;


import java.io.File;
import java.io.IOException;


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
 *            <TD> 30-10-2006 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germ�n Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versi�n inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
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
public class NLIndexer extends DocumentIndexer {

	public NLIndexer(String sourceDir, String indexBaseDir) {
		super("NL", sourceDir, indexBaseDir);
	}

	public NLIndexer(String sourceDir, String indexBaseDir,
			IndexControl control) {
				
		super("NL", sourceDir, indexBaseDir, control);
	}
	
	public Document getDocument(File file) throws IOException {

		String firstLine = getFirstDocumentLine(file);
		
		if (firstLine == null) {
			return null;
		}
				
		int rutEmpresa = Integer.parseInt(getKeyFieldValue(0, firstLine));
		char dvRutEmpresa = getKeyFieldValue(1, firstLine).charAt(0);
		int oficina = Integer.parseInt(getKeyFieldValue(2, firstLine));
		int sucursal = Integer.parseInt(getKeyFieldValue(3, firstLine));		
		
		return new NominaLeasing(rutEmpresa, dvRutEmpresa, oficina, sucursal);
	}
}
