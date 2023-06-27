

/*
 * @(#) BoletaTO.java    1.0 05-09-2014
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.to;

import java.util.List;

import cl.araucana.core.util.Rut;


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
 *            <TD> 05-09-2014 </TD>
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
public class BoletaTO {
private int periodo;
private Rut rutEmpresa;
private int idBoleta;
private int idBanco;
private String idCuenta;
private long monto;
private String estado;
private String fecha;
private String hora;
private String[] folios;
private String tipoAbono="A";
private int codOperacion=90;
private String descripcionEstado;
private List no_cursados;
/**
 * @return el estado
 */
public String getEstado() {
	return estado;
}
/**
 * @param estado el estado a establecer
 */
public void setEstado(String estado) {
	this.estado = estado;
}
/**
 * @return el fecha
 */
public String getFecha() {
	return fecha;
}
/**
 * @param fecha el fecha a establecer
 */
public void setFecha(String fecha) {
	this.fecha = fecha;
}
/**
 * @return el hora
 */
public String getHora() {
	return hora;
}
/**
 * @param hora el hora a establecer
 */
public void setHora(String hora) {
	this.hora = hora;
}
/**
 * @return el idBanco
 */
public int getIdBanco() {
	return idBanco;
}
/**
 * @param idBanco el idBanco a establecer
 */
public void setIdBanco(int idBanco) {
	this.idBanco = idBanco;
}
/**
 * @return el idCuenta
 */
public String getIdCuenta() {
	return idCuenta;
}
/**
 * @param idCuenta el idCuenta a establecer
 */
public void setIdCuenta(String idCuenta) {
	this.idCuenta = idCuenta;
}
/**
 * @return el monto
 */
public long getMonto() {
	return monto;
}
/**
 * @param monto el monto a establecer
 */
public void setMonto(long monto) {
	this.monto = monto;
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
 * @return el rutempresa
 */
public Rut getRutEmpresa() {
	return rutEmpresa;
}
/**
 * @param rutempresa el rutempresa a establecer
 */
public void setRutEmpresa(Rut rutEmpresa) {
	this.rutEmpresa = rutEmpresa;
}
/**
 * @return el idBoleta
 */
public int getIdBoleta() {
	return idBoleta;
}
/**
 * @param idBoleta el idBoleta a establecer
 */
public void setIdBoleta(int idBoleta) {
	this.idBoleta = idBoleta;
}
/**
 * @return el folios
 */
public String[] getFolios() {
	return folios;
}
/**
 * @param folios el folios a establecer
 */
public void setFolios(String[] folios) {
	this.folios = folios;
}
/**
 * @return el codOperacion
 */
public int getCodOperacion() {
	return codOperacion;
}
/**
 * @param codOperacion el codOperacion a establecer
 */
public void setCodOperacion(int codOperacion) {
	this.codOperacion = codOperacion;
}
/**
 * @return el tipoAbono
 */
public String getTipoAbono() {
	return tipoAbono;
}
/**
 * @param tipoAbono el tipoAbono a establecer
 */
public void setTipoAbono(String tipoAbono) {
	this.tipoAbono = tipoAbono;
}
/**
 * @return el descripcionEstado
 */
public String getDescripcionEstado() {
	return descripcionEstado;
}
/**
 * @param descripcionEstado el descripcionEstado a establecer
 */
public void setDescripcionEstado(String descripcionEstado) {
	this.descripcionEstado = descripcionEstado;
}
public List getNo_cursados() {
	return no_cursados;
}
public void setNo_cursados(List no_cursados) {
	this.no_cursados = no_cursados;
}


}

