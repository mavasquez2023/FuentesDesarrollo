package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.Date;
import java.sql.Timestamp;

public class LdapVO{
	
	private String userName;
	private String clave;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String sexo;
	private String email;
	private String fono;
	private String origen;
	private String estado;
	private Date fechaCreacion;
	private Timestamp horaCreacion;
	private String usuarioCreacion;
	private Date fechaUltimoCambio;
	private Timestamp horaUltimoCambio;
	private String usuarioUltiCambio;
	
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaUltimoCambio() {
		return fechaUltimoCambio;
	}
	public void setFechaUltimoCambio(Date fechaUltimoCambio) {
		this.fechaUltimoCambio = fechaUltimoCambio;
	}
	public String getFono() {
		return fono;
	}
	public void setFono(String fono) {
		this.fono = fono;
	}
	public Timestamp getHoraCreacion() {
		return horaCreacion;
	}
	public void setHoraCreacion(Timestamp horaCreacion) {
		this.horaCreacion = horaCreacion;
	}
	public Timestamp getHoraUltimoCambio() {
		return horaUltimoCambio;
	}
	public void setHoraUltimoCambio(Timestamp horaUltimoCambio) {
		this.horaUltimoCambio = horaUltimoCambio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public String getUsuarioUltiCambio() {
		return usuarioUltiCambio;
	}
	public void setUsuarioUltiCambio(String usuarioUltiCambio) {
		this.usuarioUltiCambio = usuarioUltiCambio;
	}
	
	
}
