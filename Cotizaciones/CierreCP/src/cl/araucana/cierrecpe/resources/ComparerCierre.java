

/*
 * @(#) Comparer.java    1.0 27-04-2009
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.cierrecpe.resources;

import java.util.Comparator;

import cl.araucana.cierrecpe.entidades.to.PropuestaPagoTO;


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
 *            <TD> 27-04-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZOR�N <BR> clillo@laaraucana.cl </TD>
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
 * @author CLAUDIO LILLO AZOR�N (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class ComparerCierre implements Comparator {

	public int compare(Object o1, Object o2) {
		PropuestaPagoTO cheque1= (PropuestaPagoTO)o1;
		PropuestaPagoTO cheque2= (PropuestaPagoTO)o2;
		int cierre1= cheque1.getCierre();
		int cierre2= cheque2.getCierre();
		return cierre1-cierre2;
	}

}

