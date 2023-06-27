package org.springapp.spring.dto;

public class Usuario {
	
	private String nombre;
	private String apellido;
	
	public Usuario(String _nombre, String _apellido) {
		
		this.nombre = _nombre;
		this.apellido = _apellido;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	

}
