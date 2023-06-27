/*
 * Creado el 12-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.to;

import java.util.Collection;

import cl.araucana.core.business.TO.TransferObject;
import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.Rut;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PropuestaPagoTO implements TransferObject {
private int tipoSeccion, tipoDetalle, cierre, folioEgreso, folioTemporal;
private char tipoNomina;
private int conceptoTesoreria;
private Rut rut;
private int periodo;
private Collection detalle;
private long montoTotal, totalSeccion;
private char estado;
private String razonSocial, descripcionSeccion;
private AbsoluteDate fechaCreacion;
private boolean generaEgreso=true;
private String codigoBanco;
private String cuentaCorriente;
private int cantidadSeccion;
private String deposito;
private String origen;
/**
 * @return Devuelve cierre.
 */
public int getCierre() {
	return cierre;
}
/**
 * @param cierre El cierre a establecer.
 */
public void setCierre(int cierre) {
	this.cierre = cierre;
}
/**
 * @return Devuelve detalle.
 */
public Collection getDetalle() {
	return detalle;
}
/**
 * @param detalle El detalle a establecer.
 */
public void setDetalle(Collection detalle) {
	this.detalle = detalle;
}
/**
 * @return Devuelve folioEgreso.
 */
public int getFolioEgreso() {
	return folioEgreso;
}
/**
 * @param folioEgreso El folioEgreso a establecer.
 */
public void setFolioEgreso(int folioEgreso) {
	this.folioEgreso = folioEgreso;
}
/**
 * @return Devuelve montoTotal.
 */
public long getMontoTotal() {
	return montoTotal;
}
/**
 * @param montoTotal El montoTotal a establecer.
 */
public void setMontoTotal(long montoTotal) {
	this.montoTotal = montoTotal;
}
/**
 * @return Devuelve periodo.
 */
public int getPeriodo() {
	return periodo;
}
/**
 * @param periodo El periodo a establecer.
 */
public void setPeriodo(int periodo) {
	this.periodo = periodo;
}
/**
 * @return Devuelve rut.
 */
public Rut getRut() {
	return rut;
}
/**
 * @param rut El rut a establecer.
 */
public void setRut(Rut rut) {
	this.rut = rut;
}
/**
 * @return Devuelve tipoDetalle.
 */
public int getTipoDetalle() {
	return tipoDetalle;
}
/**
 * @param tipoDetalle El tipoDetalle a establecer.
 */
public void setTipoDetalle(int tipoDetalle) {
	this.tipoDetalle = tipoDetalle;
}
/**
 * @return Devuelve tipoSeccion.
 */
public int getTipoSeccion() {
	return tipoSeccion;
}
/**
 * @param tipoSeccion El tipoSeccion a establecer.
 */
public void setTipoSeccion(int tipoSeccion) {
	this.tipoSeccion = tipoSeccion;
}

/**
 * @return Devuelve tipoNomina.
 */
public char getTipoNomina() {
	return tipoNomina;
}
/**
 * @param tipoNomina El tipoNomina a establecer.
 */
public void setTipoNomina(char tipoNomina) {
	this.tipoNomina = tipoNomina;
}
/**
 * @param monto El monto a agregar al MontoTotal.
 */
public void addMontoTotal(long monto){
	this.montoTotal= this.montoTotal + monto;
}
/**
 * @return Devuelve estado.
 */
public char getEstado() {
	return estado;
}
/**
 * @return Devuelve estado.
 */
public String getEstadoStr() {
	return String.valueOf(estado);
}
/**
 * @param estado El estado a establecer.
 */
public void setEstado(char estado) {
	this.estado = estado;
}
/**
 * @return Devuelve razonSocial.
 */
public String getRazonSocial() {
	return razonSocial;
}
/**
 * @return Devuelve razonSocial.
 */
public String getRazonSocial(int largo) {
	if (razonSocial.length()>largo){
		return razonSocial.substring(0, largo);
	}
	return razonSocial;
}
/**
 * @param razonSocial El razonSocial a establecer.
 */
public void setRazonSocial(String razonSocial) {
	this.razonSocial = razonSocial;
}
/**
 * @return Devuelve conceptoTesoreria.
 */
public int getConceptoTesoreria() {
	return conceptoTesoreria;
}
/**
 * @param conceptoTesoreria El conceptoTesoreria a establecer.
 */
public void setConceptoTesoreria(int conceptoTesoreria) {
	this.conceptoTesoreria = conceptoTesoreria;
}
/**
 * @return Devuelve descripcionSeccion.
 */
public String getDescripcionSeccion() {
	return descripcionSeccion;
}
/**
 * @param descripcionSeccion El descripcionSeccion a establecer.
 */
public void setDescripcionSeccion(String descripcionSeccion) {
	this.descripcionSeccion = descripcionSeccion;
}
/**
 * @return Devuelve folioTemporal.
 */
public int getFolioTemporal() {
	return folioTemporal;
}
/**
 * @param folioTemporal El folioTemporal a establecer.
 */
public void setFolioTemporal(int folioTemporal) {
	this.folioTemporal = folioTemporal;
}
/**
 * @return el fechaCreacion
 */
public AbsoluteDate getFechaCreacion() {
	return fechaCreacion;
}
/**
 * @param fechaCreacion el fechaCreacion a establecer
 */
public void setFechaCreacion(AbsoluteDate fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
}
/**
 * @return el generaEgresoSPL
 */
public boolean isGeneraEgreso() {
	if(this.rut.getNumber()!= 70016160){
		return generaEgreso;
	}else{
		return false;
	}
	
}
/**
 * @param generaEgresoSPL el generaEgresoSPL a establecer
 */
public void setGeneraEgresoSPL(boolean generaEgreso) {
	this.generaEgreso = generaEgreso;
}
/**
 * @return el codigoBanco
 */
public String getCodigoBanco() {
	return codigoBanco;
}
/**
 * @param codigoBanco el codigoBanco a establecer
 */
public void setCodigoBanco(String codigoBanco) {
	this.codigoBanco = codigoBanco;
}
/**
 * @return el cuentaCorriente
 */
public String getCuentaCorriente() {
	return cuentaCorriente;
}
/**
 * @param cuentaCorriente el cuentaCorriente a establecer
 */
public void setCuentaCorriente(String cuentaCorriente) {
	this.cuentaCorriente = cuentaCorriente;
}
/**
 * @return el totalSeccion
 */
public long getTotalSeccion() {
	return totalSeccion;
}
/**
 * @param totalSeccion el totalSeccion a establecer
 */
public void setTotalSeccion(long totalSeccion) {
	this.totalSeccion = totalSeccion;
}
/**
 * @return el cantidadSeccion
 */
public int getCantidadSeccion() {
	return cantidadSeccion;
}
/**
 * @param cantidadSeccion el cantidadSeccion a establecer
 */
public void setCantidadSeccion(int cantidadSeccion) {
	this.cantidadSeccion = cantidadSeccion;
}
/**
 * @return el deposito
 */
public String getDeposito() {
	return deposito;
}
/**
 * @param deposito el deposito a establecer
 */
public void setDeposito(String deposito) {
	this.deposito = deposito;
}
/**
 * @return el origen
 */
public String getOrigen() {
	return origen;
}
/**
 * @param origen el origen a establecer
 */
public void setOrigen(String origen) {
	this.origen = origen;
}
}
