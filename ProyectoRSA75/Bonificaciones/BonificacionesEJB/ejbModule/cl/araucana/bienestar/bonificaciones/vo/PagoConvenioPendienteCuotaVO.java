package cl.araucana.bienestar.bonificaciones.vo;

import java.io.Serializable;

/**
 * @author Alejandro Sepúlveda
 *
 */
public class PagoConvenioPendienteCuotaVO implements Serializable{

	/** Serial */
	private static final long serialVersionUID = 1L;

	private long codigoConvenio=0;
	private int montoTotal=0;
	private int numeroCuotasConvenio=0;
	private int numeroCuotasBienestar=0;
	private int cuotaDescontada=0;
	private int montoCuota=0;
	private String nombreConvenio=null;
	private String rut=null;
	private String dv=null;
	private long area=0;
	private long conceptoEgreso=0;
	private int montoDescuento=0;
	private int aporteBienestar=0;
	private long casid=0;
	

	/**
	 * Retorna el rut compuesto del Convenio
	 * @return String con el rut
	 */
	public String getFullRut() {
		return rut+"-"+dv;
	}


	/**
	 * @return
	 */
	public long getCodigoConvenio() {
		return codigoConvenio;
	}

	/**
	 * @return
	 */
	public int getCuotaDescontada() {
		return cuotaDescontada;
	}

	/**
	 * @return
	 */
	public int getMontoCuota() {
		return montoCuota;
	}

	/**
	 * @return
	 */
	public int getMontoTotal() {
		return montoTotal;
	}

	/**
	 * @return
	 */
	public int getNumeroCuotasBienestar() {
		return numeroCuotasBienestar;
	}

	/**
	 * @return
	 */
	public int getNumeroCuotasConvenio() {
		return numeroCuotasConvenio;
	}

	/**
	 * @param l
	 */
	public void setCodigoConvenio(long l) {
		codigoConvenio = l;
	}

	/**
	 * @param i
	 */
	public void setCuotaDescontada(int i) {
		cuotaDescontada = i;
	}

	/**
	 * @param i
	 */
	public void setMontoCuota(int i) {
		montoCuota = i;
	}

	/**
	 * @param i
	 */
	public void setMontoTotal(int i) {
		montoTotal = i;
	}

	/**
	 * @param i
	 */
	public void setNumeroCuotasBienestar(int i) {
		numeroCuotasBienestar = i;
	}

	/**
	 * @param i
	 */
	public void setNumeroCuotasConvenio(int i) {
		numeroCuotasConvenio = i;
	}

	/**
	 * @return
	 */
	public String getDv() {
		return dv;
	}

	/**
	 * @return
	 */
	public String getNombreConvenio() {
		return nombreConvenio;
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
	public void setDv(String string) {
		dv = string;
	}

	/**
	 * @param string
	 */
	public void setNombreConvenio(String string) {
		nombreConvenio = string;
	}

	/**
	 * @param string
	 */
	public void setRut(String string) {
		rut = string;
	}

	/**
	 * @return
	 */
	public int getMontoDescuento() {
		return montoDescuento;
	}

	/**
	 * @param i
	 */
	public void setMontoDescuento(int i) {
		montoDescuento = i;
	}

	/**
	 * @return
	 */
	public int getAporteBienestar() {
		return aporteBienestar;
	}

	/**
	 * @param i
	 */
	public void setAporteBienestar(int i) {
		aporteBienestar = i;
	}

	/**
	 * @return
	 */
	public long getConceptoEgreso() {
		return conceptoEgreso;
	}

	/**
	 * @param l
	 */
	public void setConceptoEgreso(long l) {
		conceptoEgreso = l;
	}

	/**
	 * @return
	 */
	public long getArea() {
		return area;
	}

	/**
	 * @param l
	 */
	public void setArea(long l) {
		area = l;
	}

	/**
	 * @return
	 */
	public long getCasid() {
		return casid;
	}

	/**
	 * @param l
	 */
	public void setCasid(long l) {
		casid = l;
	}

}
