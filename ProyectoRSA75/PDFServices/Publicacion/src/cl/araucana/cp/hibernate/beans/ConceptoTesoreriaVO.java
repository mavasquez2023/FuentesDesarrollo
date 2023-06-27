package cl.araucana.cp.hibernate.beans;

import java.io.Serializable;

public class ConceptoTesoreriaVO implements Serializable
{
	private static final long serialVersionUID = -4629623202915784978L;

	private int id;
	private String descripcion;

	public ConceptoTesoreriaVO(int id, String descripcion)
	{
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public ConceptoTesoreriaVO()
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

	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}
