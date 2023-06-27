

/*
 * @(#) ComprobantesSinPlanillasTO.java    1.0 18-11-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
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
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 18-11-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZORÍN <BR> clillo@laaraucana.cl </TD>
 *            <TD> Versión inicial. </TD>
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
 * @author CLAUDIO LILLO AZORÍN (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class ComprobantesSinPlanillasTO {
private long codigoBarra;
private int total;
private int numeroTrabajadores;
/**
 * @return el idCodigoBarra
 */
public long getCodigoBarra() {
	return codigoBarra;
}
/**
 * @param idCodigoBarra el idCodigoBarra a establecer
 */
public void setCodigoBarra(long codigoBarra) {
	this.codigoBarra = codigoBarra;
}
/**
 * @return el numeroTrabajadores
 */
public int getNumeroTrabajadores() {
	return numeroTrabajadores;
}
/**
 * @param numeroTrabajadores el numeroTrabajadores a establecer
 */
public void setNumeroTrabajadores(int numeroTrabajadores) {
	this.numeroTrabajadores = numeroTrabajadores;
}
/**
 * @return el total
 */
public int getTotal() {
	return total;
}
/**
 * @param total el total a establecer
 */
public void setTotal(int total) {
	this.total = total;
}
}

