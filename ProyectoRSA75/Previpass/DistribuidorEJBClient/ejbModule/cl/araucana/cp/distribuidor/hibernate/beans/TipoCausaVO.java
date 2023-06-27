package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class TipoCausaVO extends AuditableVO
{
	private static final long serialVersionUID = 5563218614853300955L;
	private int id;
	private String descripcion;
	private int error;
	private int corregible;
	
	public TipoCausaVO()
	{
		super();
	}

	public TipoCausaVO(int id, String descripcion, int error, int corregible)
	{
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.error = error;
		this.corregible = corregible;
	}
	public int getCorregible()
	{
		return this.corregible;
	}
	public void setCorregible(int corregible)
	{
		this.corregible = corregible;
	}
	public String getDescripcion()
	{
		return this.descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public int getError()
	{
		return this.error;
	}
	public void setError(int error)
	{
		this.error = error;
	}
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.id));
		parametros.put("2", String.valueOf(this.descripcion));
		parametros.put("3", String.valueOf(this.error));
		parametros.put("4", String.valueOf(this.corregible));
		return parametros;
	}
}
