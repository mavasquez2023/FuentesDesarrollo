package cl.araucana.adminCpe.hibernate.beans;

import java.io.Serializable;

public class SPLEstadoVO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int idEstado;
	private String descripcion;
	
	public SPLEstadoVO()
	{
		super();
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
}
