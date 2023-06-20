/**
 * 
 */
package cl.laaraucana.licenciasivr.ws;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

/**
 * @author IBM Software Factory
 *
 */
@XmlType(
		name = "licenciaIVR",
		propOrder = { "numeroLicencia", "fechaInicio", "fechaTermino", "dias", "fechaPago", "rutEmpresa", "razonSocial", "montoSubsidio", "tipoSubsidio", "estado"})
public class LicenciaWS implements Serializable{

	private static final long serialVersionUID = 1L;
	private String numeroLicencia;
	private String fechaInicio;
	private String fechaTermino;
	private Integer dias;
	private String fechaPago;
	private String estado;
	private String rutEmpresa;
	private String razonSocial;
	private Integer montoSubsidio;
	private String tipoSubsidio;
	
	
	public String getRutEmpresa() {
		return rutEmpresa;
	}
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public Integer getMontoSubsidio() {
		return montoSubsidio;
	}
	public void setMontoSubsidio(Integer montoSubsidio) {
		this.montoSubsidio = montoSubsidio;
	}
	public String getTipoSubsidio() {
		return tipoSubsidio;
	}
	public void setTipoSubsidio(String tipoSubsidio) {
		this.tipoSubsidio = tipoSubsidio;
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
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaTermino
	 */
	public String getFechaTermino() {
		return fechaTermino;
	}
	/**
	 * @param fechaTermino the fechaTermino to set
	 */
	public void setFechaTermino(String fechaTermino) {
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
