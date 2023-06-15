package cl.laaraucana.ventafullweb.vo;

import java.io.Serializable;

public class FeriadoVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String fecha;
	private String descripcion;
	
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
