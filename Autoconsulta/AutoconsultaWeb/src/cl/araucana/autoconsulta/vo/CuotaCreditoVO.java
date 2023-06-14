package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class CuotaCreditoVO implements Serializable {
	
	/* Estado Cuota
		0,2,3,4,5=Impagas
		1=Vigente 
	 */
	 
	private String folio=null;
	private int estado=0;
	private String vencimiento=null;
	private int cuotaNumero=0;
	private int abono=0;
	


	/**
	 * @return
	 */
	public int getAbono() {
		return abono;
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
	public int getEstado() {
		return estado;
	}

	/**
	 * @return
	 */
	public String getFolio() {
		return folio;
	}

	/**
	 * @return
	 */
	public String getVencimiento() {
		return vencimiento;
	}

	/**
	 * @param i
	 */
	public void setAbono(int i) {
		abono = i;
	}

	/**
	 * @param i
	 */
	public void setCuotaNumero(int i) {
		cuotaNumero = i;
	}

	/**
	 * @param i
	 */
	public void setEstado(int i) {
		estado = i;
	}

	/**
	 * @param string
	 */
	public void setFolio(String string) {
		folio = string;
	}

	/**
	 * @param string
	 */
	public void setVencimiento(String string) {
		vencimiento = string;
	}

}
