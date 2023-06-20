package cl.laaraucana.mandatocesantia.model;

import java.util.Date;

public class CesantiaVo {

	private long idBita;

	private String rutCliente;

	private String dvCliente;

	private String celular;

	private String telefono;

	private String email;

	private String autorizado;

	private Date fecha;

	private String emailRechazo;

	private String emailDescarga;
	
	private String nombreCliente;
	
	private String serie;
	
	private String password;

	public long getIdBita() {
		return idBita;
	}

	public void setIdBita(long idBita) {
		this.idBita = idBita;
	}

	public String getRutCliente() {
		return rutCliente;
	}

	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}

	public String getDvCliente() {
		return dvCliente;
	}

	public void setDvCliente(String dvCliente) {
		this.dvCliente = dvCliente;
	}
	
	/**
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * @param celular the celular to set
	 */
	public void setCelular(String celular) {
		this.celular = celular;
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

	public String getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(String autorizado) {
		this.autorizado = autorizado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEmailRechazo() {
		return emailRechazo;
	}

	public void setEmailRechazo(String emailRechazo) {
		this.emailRechazo = emailRechazo;
	}

	public String getEmailDescarga() {
		return emailDescarga;
	}

	public void setEmailDescarga(String emailDescarga) {
		this.emailDescarga = emailDescarga;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return the serie
	 */
	public String getSerie() {
		return serie;
	}

	/**
	 * @param serie the serie to set
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
	

}
