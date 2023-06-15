package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class LicenciaMedicaResumenVO implements Serializable {
		
	private long numeroLicencia=0;
	private int montoAPagar=0;
	private String folio=null;
	/**
	 * @return
	 */
	public int getMontoAPagar() {
		return montoAPagar;
	}

	/**
	 * @return
	 */
	public long getNumeroLicencia() {
		return numeroLicencia;
	}

	/**
	 * @param i
	 */
	public void setMontoAPagar(int i) {
		montoAPagar = i;
	}

	/**
	 * @param l
	 */
	public void setNumeroLicencia(long l) {
		numeroLicencia = l;
	}

	/**
	 * @return
	 */
	public String getFolio() {
		return folio;
	}

	/**
	 * @param string
	 */
	public void setFolio(String string) {
		folio = string;
	}

}
