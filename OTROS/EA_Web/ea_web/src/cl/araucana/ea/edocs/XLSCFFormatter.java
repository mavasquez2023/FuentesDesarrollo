
/*
 * @(#) XLSCFFormatter.java    1.0 31-07-2007
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.ea.edocs;


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
 *            <TD> 31-07-2007 </TD>
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
public class XLSCFFormatter extends DocTypeFormatter {
	
	private int FIELD_DV_RUT_EMPRESA = 1;
	private int FIELD_DV_RUT_AFILIADO = 3;
	private int FIELD_DV_RUT_CARGA = 5;	
		
	public XLSCFFormatter(String header, String trailer) {
		super(header, trailer);
	}

	public String formatField(int index, String value) {
		
		/*
		 *  DV must be right aligned.
		 */
		if (index == FIELD_DV_RUT_EMPRESA || index == FIELD_DV_RUT_AFILIADO
				|| index == FIELD_DV_RUT_CARGA) {
			return "<TD align=\"right\">" + value;
		}
				
		return "<TD>" + value;
	}
}
