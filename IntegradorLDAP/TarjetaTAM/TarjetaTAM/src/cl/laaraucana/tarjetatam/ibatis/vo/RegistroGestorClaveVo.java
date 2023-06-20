package cl.laaraucana.tarjetatam.ibatis.vo;

import java.util.Date;



public class RegistroGestorClaveVo {

	private long rut;
	private int dv;
	private String nombre;
	private long celular;
	private long telefono;
	private String email;
	private Date fecha_creacion;
	private Date hora_creacion;
	private Date fecha_modificacion;
	private Date hora_modificacion;
	private int num_modificacion;

	public long getRut() {
		return rut;
	}

	public void setRut(long rut) {
		this.rut = rut;
	}

	public int getDv() {
		return dv;
	}

	public void setDv(int dv) {
		this.dv = dv;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getCelular() {
		return celular;
	}

	public void setCelular(long celular) {
		this.celular = celular;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public Date getHora_creacion() {
		return hora_creacion;
	}

	public void setHora_creacion(Date hora_creacion) {
		this.hora_creacion = hora_creacion;
	}

	public Date getFecha_modificacion() {
		return fecha_modificacion;
	}

	public void setFecha_modificacion(Date fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}

	public Date getHora_modificacion() {
		return hora_modificacion;
	}

	public void setHora_modificacion(Date hora_modificacion) {
		this.hora_modificacion = hora_modificacion;
	}

	public int getNum_modificacion() {
		return num_modificacion;
	}

	public void setNum_modificacion(int num_modificacion) {
		this.num_modificacion = num_modificacion;
	}

}
