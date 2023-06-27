/*
 * Creado el 12-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.empresas.to;

import cl.araucana.core.business.TO.TransferObject;
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
private String tipoNomina;
private long codigoBarra;
private int n_trabajadores;
private int convenio;
private String razonSocial;
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
 * @return el razonSocial
 */
public String getRazonSocial() {
	return razonSocial;
}
/**
 * @param razonSocial el razonSocial a establecer
 */
public void setRazonSocial(String razonSocial) {
	this.razonSocial = razonSocial;
}
/**
 * @return el tipoNomina
 */
public String getTipoNomina() {
	return tipoNomina;
}
/**
 * @param tipoNomina el tipoNomina a establecer
 */
public void setTipoNomina(String tipoNomina) {
	this.tipoNomina = tipoNomina;
}
}
