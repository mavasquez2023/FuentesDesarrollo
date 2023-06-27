package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class EstadoComprobanteVO extends EstadoVO implements Serializable
{
	private static final long serialVersionUID = 5827680426904439288L;
	
	private char idEstado;
	public String getDescripcion() {
		return this.descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public char getIdEstado() {
		return this.idEstado;
	}
	public void setIdEstado(char idEstado) {
		this.idEstado = idEstado;
	}
}
