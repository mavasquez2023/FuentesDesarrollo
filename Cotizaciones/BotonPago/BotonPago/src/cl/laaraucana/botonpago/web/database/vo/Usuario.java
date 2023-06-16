package cl.laaraucana.botonpago.web.database.vo;

import java.sql.Date;

/*
 * Representa un usuario con información propia del sistema de Recuperación de pagos.
 * */
public class Usuario {

	private long rut;
	private String dv;
	private int primerLogin;
	private int deudorNoAfiliado;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Date creado;
	private int idRol;

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

	public Date getCreado() {
		return creado;
	}

	public void setCreado(Date creado) {
		this.creado = creado;
	}

	public int getDeudorNoAfiliado() {
		return deudorNoAfiliado;
	}

	public void setDeudorNoAfiliado(int deudorNoAfiliado) {
		this.deudorNoAfiliado = deudorNoAfiliado;
	}

	public String getDv() {
		return dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrimerLogin() {
		return primerLogin;
	}

	public void setPrimerLogin(int primerLogin) {
		this.primerLogin = primerLogin;
	}

	public long getRut() {
		return rut;
	}

	public void setRut(long rut) {
		this.rut = rut;
	}

	@Override
	public String toString() {
		return String.format("Evento[rut=%d,dv=%s,primerLogin=%s,deudorNoAfiliado=%s" + ",nombre=%s,apellidoPaterno=%s,apellidoMaterno=%s,idRol=%s]", rut, dv, primerLogin, deudorNoAfiliado, nombre,
				apellidoPaterno, apellidoMaterno, idRol);
	}
}
