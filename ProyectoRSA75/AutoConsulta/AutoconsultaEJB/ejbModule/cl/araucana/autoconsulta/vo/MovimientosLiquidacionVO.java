/*
 * Creado el 18-11-2005
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package cl.araucana.autoconsulta.vo;

import java.io.Serializable;

/**
 * @author USIST15
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class MovimientosLiquidacionVO implements Serializable{
	
	private String numeroDocumento = null;
	private int codigoPrestacion = 0;
	private String descripcionPrestacion = null;
	private double montoBase = 0;
	private double montoBonificado = 0;
	private double montoPendiente = 0;
	private double montoRechazado = 0;
	private int numeroCarga = 0;
	
	/**
	 * @return
	 */
	public int getCodigoPrestacion() {
		return codigoPrestacion;
	}

	/**
	 * @return
	 */
	public String getDescripcionPrestacion() {
		return descripcionPrestacion;
	}

	/**
	 * @return
	 */
	public double getMontoBase() {
		return montoBase;
	}

	/**
	 * @return
	 */
	public double getMontoBonificado() {
		return montoBonificado;
	}

	/**
	 * @return
	 */
	public double getMontoPendiente() {
		return montoPendiente;
	}

	/**
	 * @return
	 */
	public double getMontoRechazado() {
		return montoRechazado;
	}

	/**
	 * @return
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param i
	 */
	public void setCodigoPrestacion(int i) {
		codigoPrestacion = i;
	}

	/**
	 * @param string
	 */
	public void setDescripcionPrestacion(String string) {
		descripcionPrestacion = string;
	}

	/**
	 * @param d
	 */
	public void setMontoBase(double d) {
		montoBase = d;
	}

	/**
	 * @param d
	 */
	public void setMontoBonificado(double d) {
		montoBonificado = d;
	}

	/**
	 * @param d
	 */
	public void setMontoPendiente(double d) {
		montoPendiente = d;
	}

	/**
	 * @param d
	 */
	public void setMontoRechazado(double d) {
		montoRechazado = d;
	}

	/**
	 * @param d
	 */
	public void setNumeroDocumento(String d) {
		numeroDocumento = d;
	}


	/**
	 * @return
	 */
	public int getNumeroCarga() {
		return numeroCarga;
	}

	/**
	 * @param i
	 */
	public void setNumeroCarga(int i) {
		numeroCarga = i;
	}

}
