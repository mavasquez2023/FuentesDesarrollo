package cl.laaraucana.licenciasivr.vo;

import java.io.Serializable;

public class ConsultaVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Integer rut;
	Integer fecha;
	
	public Integer getRut() {
		return rut;
	}
	public void setRut(Integer rut) {
		this.rut = rut;
	}
	public Integer getFecha() {
		return fecha;
	}
	public void setFecha(Integer fecha) {
		this.fecha = fecha;
	}
	
	
}
