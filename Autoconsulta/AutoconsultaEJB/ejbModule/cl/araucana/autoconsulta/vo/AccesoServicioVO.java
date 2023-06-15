package cl.araucana.autoconsulta.vo;

import java.io.Serializable;

public class AccesoServicioVO implements Serializable {
	
	private String codigo;
	private String nombre;
	private String descripcion;
	private boolean habilitado;
	
	public AccesoServicioVO() {
	}
	
	public AccesoServicioVO(String lCodigo, String lNombre) {
		this.codigo = lCodigo;
		this.nombre = lNombre;
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}
