

/*
 * @(#) ParametrosTO.java    1.0 21-08-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.ctasfam.business.to;



import cl.araucana.core.util.AbsoluteDate;

public class ParametrosTO{
	private String periodoProceso;
	private String fechaApertura;
	private String fechaCierre;
	private String fechaEnvio;
	private String initialZipBufferSize="";
	private String zippedDocPrefix="";
	private String rutaZip="";
	private String controlSesiones="";
	private String maxSesiones="";
	private String carpeta_respaldo="";
	private String carpeta_descompres="";
	private String copiaAFP64="";
	private String cpyf_ip="";
	private String cpyf_usuario="";
	private String cpyf_password="";
	private String urlVolver="";
	private String tipoDescarga;
	
	public ParametrosTO(){
		
	}

	public String getPeriodoProceso() {
		return periodoProceso;
	}

	public void setPeriodoProceso(String periodoProceso) {
		this.periodoProceso = periodoProceso;
	}

	public String getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public String getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	
	/**
	 * @return the fechaEnvio
	 */
	public String getFechaEnvio() {
		return fechaEnvio;
	}

	/**
	 * @param fechaEnvio the fechaEnvio to set
	 */
	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public String getInitialZipBufferSize() {
		return initialZipBufferSize;
	}

	public void setInitialZipBufferSize(String initialZipBufferSize) {
		this.initialZipBufferSize = initialZipBufferSize;
	}

	public String getZippedDocPrefix() {
		return zippedDocPrefix;
	}

	public void setZippedDocPrefix(String zippedDocPrefix) {
		this.zippedDocPrefix = zippedDocPrefix;
	}

	public String getRutaZip() {
		return rutaZip;
	}

	public void setRutaZip(String rutaZip) {
		this.rutaZip = rutaZip;
	}

	public String getControlSesiones() {
		return controlSesiones;
	}

	public void setControlSesiones(String controlSesiones) {
		this.controlSesiones = controlSesiones;
	}

	public String getMaxSesiones() {
		return maxSesiones;
	}

	public void setMaxSesiones(String maxSesiones) {
		this.maxSesiones = maxSesiones;
	}

	public String getCarpeta_respaldo() {
		return carpeta_respaldo;
	}

	public void setCarpeta_respaldo(String carpeta_respaldo) {
		this.carpeta_respaldo = carpeta_respaldo;
	}

	public String getCarpeta_descompres() {
		return carpeta_descompres;
	}

	public void setCarpeta_descompres(String carpeta_descompres) {
		this.carpeta_descompres = carpeta_descompres;
	}

	/**
	 * @return the copiaAFP64
	 */
	public String getCopiaAFP64() {
		return copiaAFP64;
	}

	/**
	 * @param copiaAFP64 the copiaAFP64 to set
	 */
	public void setCopiaAFP64(String copiaAFP64) {
		this.copiaAFP64 = copiaAFP64;
	}

	/**
	 * @return the cpyf_ip
	 */
	public String getCpyf_ip() {
		return cpyf_ip;
	}

	/**
	 * @param cpyf_ip the cpyf_ip to set
	 */
	public void setCpyf_ip(String cpyf_ip) {
		this.cpyf_ip = cpyf_ip;
	}

	/**
	 * @return the cpyf_usuario
	 */
	public String getCpyf_usuario() {
		return cpyf_usuario;
	}

	/**
	 * @param cpyf_usuario the cpyf_usuario to set
	 */
	public void setCpyf_usuario(String cpyf_usuario) {
		this.cpyf_usuario = cpyf_usuario;
	}

	/**
	 * @return the cpyf_password
	 */
	public String getCpyf_password() {
		return cpyf_password;
	}

	/**
	 * @param cpyf_password the cpyf_password to set
	 */
	public void setCpyf_password(String cpyf_password) {
		this.cpyf_password = cpyf_password;
	}

	/**
	 * @return the urlVolver
	 */
	public String getUrlVolver() {
		return urlVolver;
	}

	/**
	 * @param urlVolver the urlVolver to set
	 */
	public void setUrlVolver(String urlVolver) {
		this.urlVolver = urlVolver;
	}

	/**
	 * @return the tipoDescarga
	 */
	public String getTipoDescarga() {
		return tipoDescarga;
	}

	/**
	 * @param tipoDescarga the tipoDescarga to set
	 */
	public void setTipoDescarga(String tipoDescarga) {
		this.tipoDescarga = tipoDescarga;
	}

	
	
	
		
}