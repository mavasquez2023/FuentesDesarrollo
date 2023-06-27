package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class ParentescoCargaVO implements Serializable
{
	private int idParentescoCarga;
	private String codigo;
	private String nombre;
	private String descripcion;

	public String getCodigo()
	{
		return this.codigo;
	}
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}
	public String getDescripcion()
	{
		return this.descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public int getIdParentescoCarga()
	{
		return this.idParentescoCarga;
	}
	public void setIdParentescoCarga(int idParentescoCarga)
	{
		this.idParentescoCarga = idParentescoCarga;
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