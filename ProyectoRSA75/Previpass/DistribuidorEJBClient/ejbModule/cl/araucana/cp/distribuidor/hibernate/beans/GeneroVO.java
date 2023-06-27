package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class GeneroVO extends EntidadVO
{
	private static final long serialVersionUID = 1L;

	public GeneroVO()
	{
		super();
	}

	public GeneroVO(int id, String nombre)
	{
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public GeneroVO(int id)
	{
		super();
		this.id = id;
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getNombre()
	{
		return this.nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.id));
		parametros.put("2", String.valueOf(this.nombre));
		return parametros;
	}
}
