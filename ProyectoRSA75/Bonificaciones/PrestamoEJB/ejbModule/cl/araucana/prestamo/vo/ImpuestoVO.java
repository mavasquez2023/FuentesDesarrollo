package cl.araucana.prestamo.vo;

import java.io.Serializable;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class ImpuestoVO  implements Serializable{
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	private int cuotasLT=0;
	private int impuesto=0;
	private int capitalCredito=0;
	

	/**
	 * @return
	 */
	public int getCapitalCredito() {
		return capitalCredito;
	}

	/**
	 * @return
	 */
	public int getCuotasLT() {
		return cuotasLT;
	}

	/**
	 * @return
	 */
	public int getImpuesto() {
		return impuesto;
	}

	/**
	 * @param i
	 */
	public void setCapitalCredito(int i) {
		capitalCredito = i;
	}

	/**
	 * @param i
	 */
	public void setCuotasLT(int i) {
		cuotasLT = i;
	}

	/**
	 * @param i
	 */
	public void setImpuesto(int i) {
		impuesto = i;
	}

}
