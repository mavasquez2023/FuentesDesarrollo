/*
 * Creado el 19-03-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.empresas.to.tgr;

import cl.araucana.core.business.TO.TransferObject;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ResumenCierreTO implements TransferObject{
	private int cierre;
	private long monto;
	private char estado;
	private String tipoSeccion, formaPago;
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
	/**
	 * @return el estado
	 */
	public char getEstado() {
		return estado;
	}
	/**
	 * @return Devuelve estado como String.
	 */
	public String getEstadoStr() {
		return String.valueOf(estado);
	}
	/**
	 * @param estado el estado a establecer
	 */
	public void setEstado(char estado) {
		this.estado = estado;
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
	 * @return el tipoSeccion
	 */
	public String getTipoSeccion() {
		return tipoSeccion;
	}
	/**
	 * @param tipoSeccion el tipoSeccion a establecer
	 */
	public void setTipoSeccion(String tipoSeccion) {
		this.tipoSeccion = tipoSeccion;
	}

}
