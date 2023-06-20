/**
 * 
 */
package cl.laaraucana.licenciascompinemp.ibatis.vo;


import java.io.Serializable;

/**
 * @author @author J-Factory
 *
 */

public class ViewLicenciasPendientes implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String rut;
	private String dvAfi;
	private String folioLicencia;
	private long diasLicencia;
	private String fechaInicio;
	private String fechaTermino;
	private String tipoLicencia;
	private String observacion;
	private String folioInterno;
	private String NUMIMPRE;
	private String licest;
	private String rutemp;
	private String rutempdv;
	private String telefono;
	private String email;
	private String tipoLicCRM;
		
	
	/**
	 * @return the rut
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * @param rut the rut to set
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}

	/**
	 * @return the folioLicencia
	 */
	public String getFolioLicencia() {
		return folioLicencia;
	}

	/**
	 * @param folioLicencia the folioLicencia to set
	 */
	public void setFolioLicencia(String folioLicencia) {
		this.folioLicencia = folioLicencia;
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
	 * @return the tipoLicencia
	 */
	public String getTipoLicencia() {
		return tipoLicencia;
	}

	/**
	 * @param tipoLicencia the tipoLicencia to set
	 */
	public void setTipoLicencia(String tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the diasLicencia
	 */
	public long getDiasLicencia() {
		return diasLicencia;
	}

	/**
	 * @param diasLicencia the diasLicencia to set
	 */
	public void setDiasLicencia(long diasLicencia) {
		this.diasLicencia = diasLicencia;
	}

	/**
	 * @return the dvAfi
	 */
	public String getDvAfi() {
		return dvAfi;
	}

	/**
	 * @param dvAfi the dvAfi to set
	 */
	public void setDvAfi(String dvAfi) {
		this.dvAfi = dvAfi;
	}

	/**
	 * @return the folioInterno
	 */
	public String getFolioInterno() {
		return folioInterno;
	}

	/**
	 * @param folioInterno the folioInterno to set
	 */
	public void setFolioInterno(String folioInterno) {
		this.folioInterno = folioInterno;
	}

	/**
	 * @return the nUMIMPRE
	 */
	public String getNUMIMPRE() {
		return NUMIMPRE;
	}

	/**
	 * @param nUMIMPRE the nUMIMPRE to set
	 */
	public void setNUMIMPRE(String nUMIMPRE) {
		NUMIMPRE = nUMIMPRE;
	}

	/**
	 * @return the licest
	 */
	public String getLicest() {
		return licest;
	}

	/**
	 * @param licest the licest to set
	 */
	public void setLicest(String licest) {
		this.licest = licest;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the rutemp
	 */
	public String getRutemp() {
		return rutemp;
	}

	/**
	 * @param rutemp the rutemp to set
	 */
	public void setRutemp(String rutemp) {
		this.rutemp = rutemp;
	}

	/**
	 * @return the rutempdv
	 */
	public String getRutempdv() {
		return rutempdv;
	}

	/**
	 * @param rutempdv the rutempdv to set
	 */
	public void setRutempdv(String rutempdv) {
		this.rutempdv = rutempdv;
	}


	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the tipoLicCRM
	 */
	public String getTipoLicCRM() {
		return tipoLicCRM;
	}

	/**
	 * @param tipoLicCRM the tipoLicCRM to set
	 */
	public void setTipoLicCRM(String tipoLicCRM) {
		this.tipoLicCRM = tipoLicCRM;
	}
		
		
}
