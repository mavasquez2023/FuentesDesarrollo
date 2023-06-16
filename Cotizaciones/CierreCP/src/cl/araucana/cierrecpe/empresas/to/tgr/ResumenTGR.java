

/*
 * @(#) ResumenTGR.java    1.0 03/03/2010
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */


package cl.araucana.cierrecpe.empresas.to.tgr;


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
 *            <TD> 03/03/2010 </TD>
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
public class ResumenTGR {
private int numComprobantesTGR=0;
private int numComprobantesNP=0;
private int cierre;
/**
 * @return el numComprobantesNP
 */
public int getNumComprobantesNP() {
	return numComprobantesNP;
}
/**
 * @param numComprobantesNP el numComprobantesNP a establecer
 */
public void setNumComprobantesNP(int numComprobantesNP) {
	this.numComprobantesNP = numComprobantesNP;
}
/**
 * @return el numComprobantesTGR
 */
public int getNumComprobantesTGR() {
	return numComprobantesTGR;
}
/**
 * @param numComprobantesTGR el numComprobantesTGR a establecer
 */
public void setNumComprobantesTGR(int numComprobantesTGR) {
	this.numComprobantesTGR = numComprobantesTGR;
}
/**
 * @return el cierre
 */
public int getCierre() {
	return cierre;
}
/**
 * @param cierre el cierre a establecer
 */
public void setCierre(int cierre) {
	this.cierre = cierre;
}
}

