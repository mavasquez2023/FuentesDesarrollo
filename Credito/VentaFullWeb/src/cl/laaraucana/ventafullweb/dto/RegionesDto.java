package cl.laaraucana.ventafullweb.dto;

import java.io.Serializable;

public class RegionesDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String idregion;
	private String nombre;
	
	public String getIdregion() {
		return idregion;
	}
	public void setIdregion(String idregion) {
		this.idregion = idregion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
	
}
