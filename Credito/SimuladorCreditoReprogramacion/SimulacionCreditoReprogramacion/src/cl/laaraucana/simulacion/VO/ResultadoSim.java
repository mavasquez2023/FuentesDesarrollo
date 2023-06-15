package cl.laaraucana.simulacion.VO;


import java.util.Date;
import cl.laaraucana.simulacion.webservices.model.AbstractSalidaVO;

public class ResultadoSim extends AbstractSalidaVO {
	
	private String montoAdeudado;
	private String montoCuota;
	private String cae;
	private String tasaMensual;
	private String tasaAnual;
	private String costoTotal;
	private String grvCondonado;
	private String gcCondonado;
	private String intCondonado;
	private Date fechaPrimerVencimiento;
	private String segCes;
	private String segDesg;
	private String porcentajeMaximoEndeudamiento;
	private String porcentajeEndeudamientoSimulado;
	private ParametrosSimulacionVO paramEntrada;

	/**
	 * @return the montoAdeudado
	 */
	public String getMontoAdeudado() {
		return montoAdeudado;
	}

	/**
	 * @param montoAdeudado the montoAdeudado to set
	 */
	public void setMontoAdeudado(String montoAdeudado) {
		this.montoAdeudado = montoAdeudado;
	}

	/**
	 * @return the montoCuota
	 */
	public String getMontoCuota() {
		return montoCuota;
	}

	/**
	 * @param montoCuota the montoCuota to set
	 */
	public void setMontoCuota(String montoCuota) {
		this.montoCuota = montoCuota;
	}

	/**
	 * @return the cae
	 */
	public String getCae() {
		return cae;
	}

	/**
	 * @param cae the cae to set
	 */
	public void setCae(String cae) {
		this.cae = cae;
	}

	/**
	 * @return the costoTotal
	 */
	public String getCostoTotal() {
		return costoTotal;
	}

	/**
	 * @param costoTotal the costoTotal to set
	 */
	public void setCostoTotal(String costoTotal) {
		this.costoTotal = costoTotal;
	}

	/**
	 * @return the grvCondonado
	 */
	public String getGrvCondonado() {
		return grvCondonado;
	}

	/**
	 * @param grvCondonado the grvCondonado to set
	 */
	public void setGrvCondonado(String grvCondonado) {
		this.grvCondonado = grvCondonado;
	}

	/**
	 * @return the gcCondonado
	 */
	public String getGcCondonado() {
		return gcCondonado;
	}

	/**
	 * @param gcCondonado the gcCondonado to set
	 */
	public void setGcCondonado(String gcCondonado) {
		this.gcCondonado = gcCondonado;
	}

	/**
	 * @return the intCondonado
	 */
	public String getIntCondonado() {
		return intCondonado;
	}

	/**
	 * @param intCondonado the intCondonado to set
	 */
	public void setIntCondonado(String intCondonado) {
		this.intCondonado = intCondonado;
	}

	/**
	 * @return the fechaPrimerVencimiento
	 */
	public Date getFechaPrimerVencimiento() {
		return fechaPrimerVencimiento;
	}

	/**
	 * @param fechaPrimerVencimiento the fechaPrimerVencimiento to set
	 */
	public void setFechaPrimerVencimiento(Date fechaPrimerVencimiento) {
		this.fechaPrimerVencimiento = fechaPrimerVencimiento;
	}

	/**
	 * @return the segCes
	 */
	public String getSegCes() {
		return segCes;
	}

	/**
	 * @param segCes the segCes to set
	 */
	public void setSegCes(String segCes) {
		this.segCes = segCes;
	}

	/**
	 * @return the segDesg
	 */
	public String getSegDesg() {
		return segDesg;
	}

	/**
	 * @param segDesg the segDesg to set
	 */
	public void setSegDesg(String segDesg) {
		this.segDesg = segDesg;
	}

	/**
	 * @return the paramEntrada
	 */
	public ParametrosSimulacionVO getParamEntrada() {
		return paramEntrada;
	}

	/**
	 * @param paramEntrada the paramEntrada to set
	 */
	public void setParamEntrada(ParametrosSimulacionVO paramEntrada) {
		this.paramEntrada = paramEntrada;
	}

	/**
	 * @return the tasaMensual
	 */
	public String getTasaMensual() {
		return tasaMensual;
	}

	/**
	 * @param tasaMensual the tasaMensual to set
	 */
	public void setTasaMensual(String tasaMensual) {
		this.tasaMensual = tasaMensual;
	}
	
	
	/**
	 * @return the tasaAnual
	 */
	public String getTasaAnual() {
		return tasaAnual;
	}

	/**
	 * @param tasaAnual the tasaAnual to set
	 */
	public void setTasaAnual(String tasaAnual) {
		this.tasaAnual = tasaAnual;
	}

	/**
	 * @return the porcentajeMaximoEndeudamiento
	 */
	public String getPorcentajeMaximoEndeudamiento() {
		return porcentajeMaximoEndeudamiento;
	}

	/**
	 * @param porcentajeMaximoEndeudamiento the porcentajeMaximoEndeudamiento to set
	 */
	public void setPorcentajeMaximoEndeudamiento(
			String porcentajeMaximoEndeudamiento) {
		this.porcentajeMaximoEndeudamiento = porcentajeMaximoEndeudamiento;
	}

	/**
	 * @return the porcentajeEndeudamientoSimulado
	 */
	public String getPorcentajeEndeudamientoSimulado() {
		return porcentajeEndeudamientoSimulado;
	}

	/**
	 * @param porcentajeEndeudamientoSimulado the porcentajeEndeudamientoSimulado to set
	 */
	public void setPorcentajeEndeudamientoSimulado(
			String porcentajeEndeudamientoSimulado) {
		this.porcentajeEndeudamientoSimulado = porcentajeEndeudamientoSimulado;
	}

	
	
}
