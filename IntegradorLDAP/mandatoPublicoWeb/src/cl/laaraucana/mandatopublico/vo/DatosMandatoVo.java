package cl.laaraucana.mandatopublico.vo;

import java.util.Date;

public class DatosMandatoVo {

	private long idMandato;
	private String nombre;
	private String rut;
	private String celular;
	private String telefono;
	private String email;
	private String confirmarEmail;
	private String banco;
	private String nameBanco;
	private String nameCuenta;
	private String cuenta;
	private String tipoCuenta;
	private String serie;
	private Date fechaRevocacion;
	private Date fechaVigencia;
	private String observaciones;

	public long getIdMandato() {
		return idMandato;
	}

	public void setIdMandato(long idMandato) {
		this.idMandato = idMandato;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmarEmail() {
		return confirmarEmail;
	}

	public void setConfirmarEmail(String confirmarEmail) {
		this.confirmarEmail = confirmarEmail;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getNameBanco() {
		return nameBanco;
	}

	public void setNameBanco(String nameBanco) {
		this.nameBanco = nameBanco;
	}

	public String getNameCuenta() {
		return nameCuenta;
	}

	public void setNameCuenta(String nameCuenta) {
		this.nameCuenta = nameCuenta;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public Date getFechaRevocacion() {
		return fechaRevocacion;
	}

	public void setFechaRevocacion(Date fechaRevocacion) {
		this.fechaRevocacion = fechaRevocacion;
	}

	public Date getFechaVigencia() {
		return fechaVigencia;
	}

	public void setFechaVigencia(Date fechaVigencia) {
		this.fechaVigencia = fechaVigencia;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
}
