package cl.laaraucana.simulacion.ibatis.vo;

import java.util.Date;

public class SolicitudCotizacionVO {
	private int tipoForm;
	private String nombre;
	private String rut;
	private String fono;
	private String celular;
	private String email;
	private String comuna;
	private String direccion;
	private String region;

	private int numCuotas;
	private long montoSoli;
	private Date fechaCreacion;

	public int getTipoForm() {
		return tipoForm;
	}

	public void setTipoForm(int tipoForm) {
		this.tipoForm = tipoForm;
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

	public String getFono() {
		return fono;
	}

	public void setFono(String fono) {
		this.fono = fono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getNumCuotas() {
		return numCuotas;
	}

	public void setNumCuotas(int numCuotas) {
		this.numCuotas = numCuotas;
	}

	public long getMontoSoli() {
		return montoSoli;
	}

	public void setMontoSoli(long montoSoli) {
		this.montoSoli = montoSoli;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}
