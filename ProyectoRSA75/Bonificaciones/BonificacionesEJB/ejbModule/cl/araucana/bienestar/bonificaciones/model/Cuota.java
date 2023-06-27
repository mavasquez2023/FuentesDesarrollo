package cl.araucana.bienestar.bonificaciones.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class Cuota implements Serializable{
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	//Estados
	public static final String STD_NO_DESCONTADA="NODESCONTADA";
	public static final String STD_DESCONTADA="DESCONTADA";
	public static final String STD_PAGADA="PAGADA";		
	
	private int cuotaNumero = 0;
	private double valorCuota = 0;
	private Date fechaVencimiento = null;
	private String cuotaEstado = STD_NO_DESCONTADA;


	/**
	 * @return
	 */
	public String getCuotaEstado() {
		return cuotaEstado;
	}

	/**
	 * @return
	 */
	public int getCuotaNumero() {
		return cuotaNumero;
	}

	/**
	 * @return
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @return
	 */
	public double getValorCuota() {
		return valorCuota;
	}

	/**
	 * @param string
	 */
	public void setCuotaEstado(String string) {
		cuotaEstado = string;
	}

	/**
	 * @param i
	 */
	public void setCuotaNumero(int i) {
		cuotaNumero = i;
	}

	/**
	 * @param date
	 */
	public void setFechaVencimiento(Date date) {
		fechaVencimiento = date;
	}

	/**
	 * @param d
	 */
	public void setValorCuota(double d) {
		valorCuota = d;
	}

}
