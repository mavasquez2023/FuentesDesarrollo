package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

import cl.araucana.cp.distribuidor.hibernate.beans.AuditableVO;

public class EstadoVO extends AuditableVO
{
	private static final long serialVersionUID = -5539286601115753558L;
	protected int id;
	protected String descripcion;
	protected String acciones;

	public EstadoVO()
	{
		super();
	}

	public EstadoVO(int id, String descripcion)
	{
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public String getDescripcion()
	{
		return this.descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	public String getAcciones() 
	{
		return this.acciones;
	}

	public void setAcciones(String acciones) 
	{
		this.acciones = acciones;
	}

	public String toString() 
	{
		
		return "EstadoVO[id: " + this.getId() + ", descripcion: \"" + this.getDescripcion() + "\", acciones: \"" + this.getAcciones() + "\"]";
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.id));
		parametros.put("2", String.valueOf(this.descripcion));
		return parametros;
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.id;
		return result;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final EstadoVO other = (EstadoVO) obj;
		if (this.id != other.id)
			return false;
		return true;
	}
}
