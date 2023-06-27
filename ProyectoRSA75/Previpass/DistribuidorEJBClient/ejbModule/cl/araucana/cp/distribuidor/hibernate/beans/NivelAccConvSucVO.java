package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class NivelAccConvSucVO implements Serializable
{
	private static final long serialVersionUID = -5735637186175744842L;

	private int idNivelAcceso;
	private String nombre;
	private String descripcion;
	
	public String getDescripcion()
	{
		return this.descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public int getIdNivelAcceso()
	{
		return this.idNivelAcceso;
	}
	public void setIdNivelAcceso(int idNivelAcceso)
	{
		this.idNivelAcceso = idNivelAcceso;
	}
	public String getNombre()
	{
		return this.nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

}
