/**
 * 
 */
package cl.laaraucana.licenciasivr.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author J-Factory
 *
 */
public class LicenciaVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String numeroLicencia;
	private Date fechaInicio;
	private Date fechaTermino;
	private Integer dias;
	private String fechaPago;
	private String estado;
	private String rutEmpresa;
	private String tipoSubsidio;
	private Integer montoSubsidio;
	private String razonSocial;
	
	
	public String getRutEmpresa() {
		return rutEmpresa;
	}
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	public String getTipoSubsidio() {
		return tipoSubsidio;
	}
	public void setTipoSubsidio(String tipoSubsidio) {
		this.tipoSubsidio = tipoSubsidio;
	}
	public Integer getMontoSubsidio() {
		return montoSubsidio;
	}
	public void setMontoSubsidio(Integer montoSubsidio) {
		this.montoSubsidio = montoSubsidio;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	/**
	 * @return the numeroLicencia
	 */
	public String getNumeroLicencia() {
		return numeroLicencia;
	}
	/**
	 * @param numeroLicencia the numeroLicencia to set
	 */
	public void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}
	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaTermino
	 */
	public Date getFechaTermino() {
		return fechaTermino;
	}
	/**
	 * @param fechaTermino the fechaTermino to set
	 */
	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}
	/**
	 * @return the dias
	 */
	public Integer getDias() {
		return dias;
	}
	/**
	 * @param dias the dias to set
	 */
	public void setDias(Integer dias) {
		this.dias = dias;
	}
	/**
	 * @return the fechaPago
	 */
	public String getFechaPago() {
		return fechaPago;
	}
	/**
	 * @param fechaPago the fechaPago to set
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}
