package cl.araucana.cp.services.vo;

import java.io.Serializable;

public class ErrorResultVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5636345465886878494L;
	
	private int codigo;
	private String descripcion;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
