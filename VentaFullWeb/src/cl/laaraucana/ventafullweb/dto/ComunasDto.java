package cl.laaraucana.ventafullweb.dto;

import java.io.Serializable;

public class ComunasDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String idcomuna;
	private String nombre;
	private String region;
	
	
	public String getIdcomuna() {
		return idcomuna;
	}
	public void setIdcomuna(String idcomuna) {
		this.idcomuna = idcomuna;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	
	
	
	
}
