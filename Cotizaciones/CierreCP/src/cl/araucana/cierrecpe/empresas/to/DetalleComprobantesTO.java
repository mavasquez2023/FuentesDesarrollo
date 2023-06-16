

/*
 * @(#) ComprobantesSinPlanillasTO.java    1.0 18-11-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.to;

import cl.araucana.core.util.AbsoluteDate;


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
public class DetalleComprobantesTO {
private long codigoBarra;
private long total;
private int numeroTrabajadores;
private int folioTesoreria;
private String formaPago;
private AbsoluteDate fechaPago;
private short pwf=0;
private short tgr=-1;
private short aia=0;
private short cen=0;
private short ncr=0;

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
public long getTotal() {
	return total;
}
/**
 * @param total el total a establecer
 */
public void setTotal(long total) {
	this.total = total;
}
/**
 * @return el fechaPago
 */
public AbsoluteDate getFechaPago() {
	return fechaPago;
}
/**
 * @param fechaPago el fechaPago a establecer
 */
public void setFechaPago(AbsoluteDate fechaPago) {
	this.fechaPago = fechaPago;
}
/**
 * @return el folioTesoreria
 */
public int getFolioTesoreria() {
	return folioTesoreria;
}
/**
 * @param folioTesoreria el folioTesoreria a establecer
 */
public void setFolioTesoreria(int folioTesoreria) {
	this.folioTesoreria = folioTesoreria;
}
/**
 * @return el formaPago
 */
public String getFormaPago() {
	return formaPago;
}
/**
 * @param formaPago el formaPago a establecer
 */
public void setFormaPago(String formaPago) {
	this.formaPago = formaPago;
}
/**
 * @return el pwf
 */
public short getPwf() {
	return pwf;
}
/**
 * @param pwf el pwf a establecer
 */
public void setPwf(short pwf) {
	this.pwf = pwf;
}
/**
 * @return el tgr
 */
public short getTgr() {
	return tgr;
}
/**
 * @param tgr el tgr a establecer
 */
public void setTgr(short tgr) {
	this.tgr = tgr;
}
/**
 * @return el aia
 */
public short getAia() {
	return aia;
}
/**
 * @param aia el aia a establecer
 */
public void setAia(short aia) {
	this.aia = aia;
}
/**
 * @return el cen
 */
public short getCen() {
	return cen;
}
/**
 * @param cen el cen a establecer
 */
public void setCen(short cen) {
	this.cen = cen;
}
/**
 * @return el ncr
 */
public short getNcr() {
	return ncr;
}
/**
 * @param ncr el ncr a establecer
 */
public void setNcr(short ncr) {
	this.ncr = ncr;
}
}

