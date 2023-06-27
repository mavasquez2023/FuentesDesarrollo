package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class MapaCodigoVO extends AuditableVO
{
	private static final long serialVersionUID = -5532794917300398520L;

	private int idMapaCodigo;
	private String descripcion;
	private int numBloqueos;
	
	public String getDescripcion()
	{
		return this.descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public int getIdMapaCodigo()
	{
		return this.idMapaCodigo;
	}
	public void setIdMapaCodigo(int idMapaCodigo)
	{
		this.idMapaCodigo = idMapaCodigo;
	}
	public int getNumBloqueos()
	{
		return this.numBloqueos;
	}
	public void setNumBloqueos(int numBloqueos)
	{
		this.numBloqueos = numBloqueos;
	}

	public String toString()
	{
		return "idMapaCodigo:" + this.idMapaCodigo + ":numBloqueos:" + this.numBloqueos + ":descripcion:" + this.descripcion + "::";
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", "" + this.idMapaCodigo);
		parametros.put("2", this.descripcion);
		parametros.put("3", "" + this.numBloqueos);
		return parametros;
	}
}
