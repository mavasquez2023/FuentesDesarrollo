/*
 * Creado el 24-11-2005
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
public class TotalesLiquidacionVO{
	public TotalesLiquidacionVO (){
		totalDocumentos = 0;
		totalBase = 0;
		totalBonificado = 0;
		totalPendiente = 0;
		totalRechazado = 0;	
	
	}
	private int totalDocumentos = 0;
	private double totalBase = 0;

	private double totalBonificado = 0;
	private double totalPendiente = 0;
	private double totalRechazado = 0;


	/**
	 * @return
	 */
	public double getTotalBase() {
		return totalBase;
	}

	/**
	 * @return
	 */
	public double getTotalBonificado() {
		return totalBonificado;
	}

	/**
	 * @return
	 */
	public int getTotalDocumentos() {
		return totalDocumentos;
	}

	/**
	 * @return
	 */
	public double getTotalPendiente() {
		return totalPendiente;
	}

	/**
	 * @return
	 */
	public double getTotalRechazado() {
		return totalRechazado;
	}

	/**
	 * @param d
	 */
	public void setTotalBase(double d) {
		totalBase = d;
	}

	/**
	 * @param d
	 */
	public void setTotalBonificado(double d) {
		totalBonificado = d;
	}

	/**
	 * @param i
	 */
	public void setTotalDocumentos(int i) {
		totalDocumentos = i;
	}

	/**
	 * @param d
	 */
	public void setTotalPendiente(double d) {
		totalPendiente = d;
	}

	/**
	 * @param d
	 */
	public void setTotalRechazado(double d) {
		totalRechazado = d;
	}

}
