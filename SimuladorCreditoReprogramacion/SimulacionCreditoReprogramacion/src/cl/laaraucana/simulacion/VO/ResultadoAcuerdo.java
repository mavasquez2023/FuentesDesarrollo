package cl.laaraucana.simulacion.VO;


import java.util.Date;
import cl.laaraucana.simulacion.webservices.model.AbstractSalidaVO;

public class ResultadoAcuerdo extends AbstractSalidaVO {
	
	private String capitalAdeudado;
	private String cuotasxPagar;
	private String tasaInteresMensual;
	private String montoAbono;
	private String porcentajeCondonacionCapital;
	private String capitalComprometido;
	private String capitalCondonado;
	private String montoCuota;
	private String cae;
	private String costoTotal;
	private Date fechaPrimerVencimiento;
	private ParametrosSimulacionVO paramEntrada;
	/**
	 * @return the capitalAdeudado
	 */
	public String getCapitalAdeudado() {
		return capitalAdeudado;
	}
	/**
	 * @param capitalAdeudado the capitalAdeudado to set
	 */
	public void setCapitalAdeudado(String capitalAdeudado) {
		this.capitalAdeudado = capitalAdeudado;
	}
	/**
	 * @return the cuotasxPagar
	 */
	public String getCuotasxPagar() {
		return cuotasxPagar;
	}
	/**
	 * @param cuotasxPagar the cuotasxPagar to set
	 */
	public void setCuotasxPagar(String cuotasxPagar) {
		this.cuotasxPagar = cuotasxPagar;
	}
	/**
	 * @return the tasaInteresMensual
	 */
	public String getTasaInteresMensual() {
		return tasaInteresMensual;
	}
	/**
	 * @param tasaInteresMensual the tasaInteresMensual to set
	 */
	public void setTasaInteresMensual(String tasaInteresMensual) {
		this.tasaInteresMensual = tasaInteresMensual;
	}
	/**
	 * @return the montoAbono
	 */
	public String getMontoAbono() {
		return montoAbono;
	}
	/**
	 * @param montoAbono the montoAbono to set
	 */
	public void setMontoAbono(String montoAbono) {
		this.montoAbono = montoAbono;
	}
	/**
	 * @return the porcentajeCondonacionCapital
	 */
	public String getPorcentajeCondonacionCapital() {
		return porcentajeCondonacionCapital;
	}
	/**
	 * @param porcentajeCondonacionCapital the porcentajeCondonacionCapital to set
	 */
	public void setPorcentajeCondonacionCapital(String porcentajeCondonacionCapital) {
		this.porcentajeCondonacionCapital = porcentajeCondonacionCapital;
	}
	/**
	 * @return the capitalComprometido
	 */
	public String getCapitalComprometido() {
		return capitalComprometido;
	}
	/**
	 * @param capitalComprometido the capitalComprometido to set
	 */
	public void setCapitalComprometido(String capitalComprometido) {
		this.capitalComprometido = capitalComprometido;
	}
	/**
	 * @return the capitalCondonado
	 */
	public String getCapitalCondonado() {
		return capitalCondonado;
	}
	/**
	 * @param capitalCondonado the capitalCondonado to set
	 */
	public void setCapitalCondonado(String capitalCondonado) {
		this.capitalCondonado = capitalCondonado;
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

		
}
