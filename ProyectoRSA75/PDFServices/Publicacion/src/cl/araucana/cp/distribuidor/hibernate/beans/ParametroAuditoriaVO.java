package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class ParametroAuditoriaVO implements Serializable
{
	private static final long serialVersionUID = -7413734400248229177L;
	private int idTipoEvento;
	private int idParametro;
	private int idTipoParametro;
	private String nombre;
	private String descripcion;
	private int largo;

	public ParametroAuditoriaVO()
	{
		super();
	}

	public String getDescripcion()
	{
		return this.descripcion;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	public int getIdParametro()
	{
		return this.idParametro;
	}

	public void setIdParametro(int idParametro)
	{
		this.idParametro = idParametro;
	}

	public int getIdTipoEvento()
	{
		return this.idTipoEvento;
	}

	public void setIdTipoEvento(int idTipoEvento)
	{
		this.idTipoEvento = idTipoEvento;
	}

	public int getIdTipoParametro()
	{
		return this.idTipoParametro;
	}

	public void setIdTipoParametro(int idTipoParametro)
	{
		this.idTipoParametro = idTipoParametro;
	}

	public int getLargo()
	{
		return this.largo;
	}

	public void setLargo(int largo)
	{
		this.largo = largo;
	}

	public String getNombre()
	{
		return this.nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String toString()
	{

		return "ParametroAuditoriaVO[idTipoEvento: " + this.getIdTipoEvento() + "idParametro: " + this.getIdParametro() + "idTipoParametro: " + this.getIdTipoParametro() + ", Nombre: "
				+ this.getNombre() + ", Descripcion: " + this.getDescripcion() + "\"]";
	}

	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.idParametro;
		result = PRIME * result + this.idTipoEvento;
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
		final ParametroAuditoriaVO other = (ParametroAuditoriaVO) obj;
		if (this.idParametro != other.idParametro)
			return false;
		if (this.idTipoEvento != other.idTipoEvento)
			return false;
		return true;
	}

}
