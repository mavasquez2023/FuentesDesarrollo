package cl.araucana.autoconsulta.vo;

import java.io.Serializable;

public class PersonaVO implements Serializable {
	private static final long serialVersionUID = 3137704461473706111L;
	private long rut;
	private String dv;
	private String nombre;
	
	public PersonaVO() {}

	public PersonaVO(long rut, String dv, String nombre) {
		super();
		this.rut = rut;
		this.dv = dv;
		this.nombre = nombre;
	}

	
	public String getDv() {
		return dv;
	}
	public void setDv(String dv) {
		this.dv = dv;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getRut() {
		return rut;
	}
	public void setRut(long rut) {
		this.rut = rut;
	}
}
