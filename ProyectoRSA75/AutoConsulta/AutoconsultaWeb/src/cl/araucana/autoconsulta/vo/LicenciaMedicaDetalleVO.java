package cl.araucana.autoconsulta.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asepulveda
 *
 */
public class LicenciaMedicaDetalleVO implements Serializable {
		
	private long numeroLicencia=0;
	private String fechaDesde=null;
	private String fechaHasta=null;
	private int diasLicencia=0;
	private String fechaDePago=null;
	private String	diaDePago=null;
	private int montoAPagar=0;
	private String folio = null;


	/**
	 * @return
	 */
	public String getDiaDePago() {
		return diaDePago;
	}

	/**
	 * @return
	 */
	public int getDiasLicencia() {
		return diasLicencia;
	}

	/**
	 * @return
	 */
	public String getFechaDePago() {
		return fechaDePago;
	}

	/**
	 * @return
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @return
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

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
	 * @param string
	 */
	public void setDiaDePago(String string) {
		diaDePago = string;
	}

	/**
	 * @param i
	 */
	public void setDiasLicencia(int i) {
		diasLicencia = i;
	}

	/**
	 * @param string
	 */
	public void setFechaDePago(String string) {
		fechaDePago = string;
	}

	/**
	 * @param string
	 */
	public void setFechaDesde(String string) {
		fechaDesde = string;
	}

	/**
	 * @param string
	 */
	public void setFechaHasta(String string) {
		fechaHasta = string;
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
	 * @param i
	 */
	public void setFolio(String i) {
		folio = i;
	}

}
