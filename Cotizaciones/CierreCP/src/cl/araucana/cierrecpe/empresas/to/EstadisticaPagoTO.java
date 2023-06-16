

/*
 * @(#) EstadisticaPagoTO.java    1.0 15-05-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.to;

import cl.araucana.core.business.TO.TransferObject;


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
 *            <TD> 15-05-2009 </TD>
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
public class EstadisticaPagoTO implements TransferObject{
private long totalPagoMixto;
private long totalPagoSPL;
private int periodo;
private int cantidadComprobantesMixto;
private int cantidadComprobantesSPL;
private int numeroTrabajadoresMixto;
private int numeroTrabajadoresSPL;
/**
 * @return el cantidadComprobantesMixto
 */
public int getCantidadComprobantesMixto() {
	return cantidadComprobantesMixto;
}
/**
 * @param cantidadComprobantesMixto el cantidadComprobantesMixto a establecer
 */
public void setCantidadComprobantesMixto(int cantidadComprobantesMixto) {
	this.cantidadComprobantesMixto = cantidadComprobantesMixto;
}
/**
 * @return el cantidadComprobantesSPL
 */
public int getCantidadComprobantesSPL() {
	return cantidadComprobantesSPL;
}
/**
 * @param cantidadComprobantesSPL el cantidadComprobantesSPL a establecer
 */
public void setCantidadComprobantesSPL(int cantidadComprobantesSPL) {
	this.cantidadComprobantesSPL = cantidadComprobantesSPL;
}
/**
 * @return el numeroTrabajadoresMixto
 */
public int getNumeroTrabajadoresMixto() {
	return numeroTrabajadoresMixto;
}
/**
 * @param numeroTrabajadoresMixto el numeroTrabajadoresMixto a establecer
 */
public void setNumeroTrabajadoresMixto(int numeroTrabajadoresMixto) {
	this.numeroTrabajadoresMixto = numeroTrabajadoresMixto;
}
/**
 * @return el numeroTrabajadoresSPL
 */
public int getNumeroTrabajadoresSPL() {
	return numeroTrabajadoresSPL;
}
/**
 * @param numeroTrabajadoresSPL el numeroTrabajadoresSPL a establecer
 */
public void setNumeroTrabajadoresSPL(int numeroTrabajadoresSPL) {
	this.numeroTrabajadoresSPL = numeroTrabajadoresSPL;
}
/**
 * @return el periodo
 */
public int getPeriodo() {
	return periodo;
}
/**
 * @param periodo el periodo a establecer
 */
public void setPeriodo(int periodo) {
	this.periodo = periodo;
}
/**
 * @return el totalPagoMixto
 */
public long getTotalPagoMixto() {
	return totalPagoMixto;
}
/**
 * @param totalPagoMixto el totalPagoMixto a establecer
 */
public void setTotalPagoMixto(long totalPagoMixto) {
	this.totalPagoMixto = totalPagoMixto;
}
/**
 * @return el totalPagoSPL
 */
public long getTotalPagoSPL() {
	return totalPagoSPL;
}
/**
 * @param totalPagoSPL el totalPagoSPL a establecer
 */
public void setTotalPagoSPL(long totalPagoSPL) {
	this.totalPagoSPL = totalPagoSPL;
}

}

