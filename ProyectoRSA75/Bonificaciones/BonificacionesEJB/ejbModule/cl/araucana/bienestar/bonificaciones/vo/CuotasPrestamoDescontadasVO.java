package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;

/**
 * @author asepulveda
 *
 */
public class CuotasPrestamoDescontadasVO implements Serializable {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	private String rut=null;
	private int tipoPrestamo=0;
	private String descripcionPrestamo=null;
	private int numeroCuotasTotales=0;
	private int cuotaActual=0;
	private int monto=0;
	
	/**
	 * Retorna la cuota en el formato cuotaActual/CuotasTotales
	 * @return String
	 */
	public String getFullCuota() {
		if(numeroCuotasTotales>0)
			return cuotaActual+"/"+numeroCuotasTotales;
		else
			return "";
	}

	/**
	 * @return
	 */
	public int getCuotaActual() {
		return cuotaActual;
	}

	/**
	 * @return
	 */
	public String getDescripcionPrestamo() {
		return descripcionPrestamo;
	}

	/**
	 * @return
	 */
	public int getMonto() {
		return monto;
	}

	/**
	 * @return
	 */
	public int getNumeroCuotasTotales() {
		return numeroCuotasTotales;
	}

	/**
	 * @return
	 */
	public int getTipoPrestamo() {
		return tipoPrestamo;
	}

	/**
	 * @param i
	 */
	public void setCuotaActual(int i) {
		cuotaActual = i;
	}

	/**
	 * @param string
	 */
	public void setDescripcionPrestamo(String string) {
		descripcionPrestamo = string;
	}

	/**
	 * @param i
	 */
	public void setMonto(int i) {
		monto = i;
	}

	/**
	 * @param i
	 */
	public void setNumeroCuotasTotales(int i) {
		numeroCuotasTotales = i;
	}

	/**
	 * @param i
	 */
	public void setTipoPrestamo(int i) {
		tipoPrestamo = i;
	}

	/**
	 * @return
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @param string
	 */
	public void setRut(String string) {
		rut = string;
	}

}
