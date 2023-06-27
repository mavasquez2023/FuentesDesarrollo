/*
 * Creado el 19-03-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.to;

import cl.araucana.core.business.TO.TransferObject;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ResumenCierreTO implements TransferObject{
	private int cierre, numcheques, formaPago;
	private long monto;
	private char estado;

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
	 * @return Devuelve estado.
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
	 * @param estado El estado a establecer.
	 */
	public void setEstado(char estado) {
		this.estado = estado;
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
	 * @return Devuelve numcheques.
	 */
	public int getNumcheques() {
		return numcheques;
	}
	/**
	 * @param numcheques El numcheques a establecer.
	 */
	public void setNumcheques(int numcheques) {
		this.numcheques = numcheques;
	}
	/**
	 * @return el formaPago
	 */
	public int getFormaPago() {
		return formaPago;
	}
	/**
	 * @param formaPago el formaPago a establecer
	 */
	public void setFormaPago(int formaPago) {
		this.formaPago = formaPago;
	}
}
