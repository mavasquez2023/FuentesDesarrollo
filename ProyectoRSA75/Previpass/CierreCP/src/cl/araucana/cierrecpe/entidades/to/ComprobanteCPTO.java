/*
 * Creado el 12-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.to;

import cl.araucana.core.business.TO.TransferObject;
import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.Rut;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ComprobanteCPTO implements TransferObject{
private Rut rutEmpresa;
private int folioIngreso;
private long monto;
private String proceso;
private PropuestaPagoTO chequeEntidadTO;
private long codigoBarra;
private AbsoluteDate fecha;
private String time;
private int n_trabajadores;
private int convenio;
private char tipoNomina;
private int id_estado;
/**
 * @return Devuelve folioIngreso.
 */
public int getFolioIngreso() {
	return folioIngreso;
}
/**
 * @param folioIngreso El folioIngreso a establecer.
 */
public void setFolioIngreso(int folioIngreso) {
	this.folioIngreso = folioIngreso;
}
/**
 * @return Devuelve monto.
 */
public long getMonto() {
	return monto;
}
/**
 * @param monto El monto a establecer.
 */
public void setMonto(long monto) {
	this.monto = monto;
}
/**
 * @return Devuelve rutEmpresa.
 */
public Rut getRutEmpresa() {
	return rutEmpresa;
}
/**
 * @return Devuelve rutEmpresa.
 */
public String getRutEmpresaFull() {
	return rutEmpresa.toString();
}
/**
 * @param rutEmpresa El rutEmpresa a establecer.
 */
public void setRutEmpresa(Rut rutEmpresa) {
	this.rutEmpresa = rutEmpresa;
}
/**
 * @return Devuelve proceso.
 */
public String getProceso() {
	return proceso;
}
/**
 * @param proceso El proceso a establecer.
 */
public void setProceso(String proceso) {
	this.proceso = proceso;
}
/**
 * @return Devuelve chequeEntidadTO.
 */
public PropuestaPagoTO getChequeEntidadTO() {
	return chequeEntidadTO;
}
/**
 * @param chequeEntidadTO El chequeEntidadTO a establecer.
 */
public void setChequeEntidadTO(PropuestaPagoTO chequeEntidadTO) {
	this.chequeEntidadTO = chequeEntidadTO;
}
/**
 * @return el codigoBarra
 */
public long getCodigoBarra() {
	return codigoBarra;
}
/**
 * @param codigoBarra el codigoBarra a establecer
 */
public void setCodigoBarra(long codigoBarra) {
	this.codigoBarra = codigoBarra;
}
/**
 * @return el fecha
 */
public AbsoluteDate getFecha() {
	return fecha;
}
/**
 * @param fecha el fecha a establecer
 */
public void setFecha(AbsoluteDate fecha) {
	this.fecha = fecha;
}
/**
 * @return el time
 */
public String getTime() {
	return time;
}
/**
 * @param time el time a establecer
 */
public void setTime(String time) {
	this.time = time;
}
/**
 * @return el n_trabajadores
 */
public int getN_trabajadores() {
	return n_trabajadores;
}
/**
 * @param n_trabajadores el n_trabajadores a establecer
 */
public void setN_trabajadores(int n_trabajadores) {
	this.n_trabajadores = n_trabajadores;
}
/**
 * @return el convenio
 */
public int getConvenio() {
	return convenio;
}
/**
 * @param convenio el convenio a establecer
 */
public void setConvenio(int convenio) {
	this.convenio = convenio;
}
/**
 * @return el tipoNomina
 */
public char getTipoNomina() {
	return tipoNomina;
}
/**
 * @param tipoNomina el tipoNomina a establecer
 */
public void setTipoNomina(char tipoNomina) {
	this.tipoNomina = tipoNomina;
}
/**
 * @return el id_estado
 */
public int getId_estado() {
	return id_estado;
}
/**
 * @param id_estado el id_estado a establecer
 */
public void setId_estado(int id_estado) {
	this.id_estado = id_estado;
}
}
