package cl.laaraucana.ventafullweb.vo;

import java.io.Serializable;

public class AgendaVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombreDia;
	private String numDia;
	private String fecha;
	
	public String getNombreDia() {
		return nombreDia;
	}
	public void setNombreDia(String nombreDia) {
		this.nombreDia = nombreDia;
	}
	public String getNumDia() {
		return numDia;
	}
	public void setNumDia(String numDia) {
		this.numDia = numDia;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
}
