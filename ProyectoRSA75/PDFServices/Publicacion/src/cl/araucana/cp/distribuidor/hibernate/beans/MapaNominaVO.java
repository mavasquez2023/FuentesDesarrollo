package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class MapaNominaVO extends AuditableVO
{
	private static final long serialVersionUID = 587650084003702299L;
	
	private int idMapaNom;
	private String idTipoNomina;
	private String descripcion;
	private int largoRegistro;
	private int numBloqueos;
	
	public String getDescripcion()
	{
		return this.descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public int getIdMapaNom()
	{
		return this.idMapaNom;
	}
	public void setIdMapaNom(int idMapaNom)
	{
		this.idMapaNom = idMapaNom;
	}
	public String getIdTipoNomina()
	{
		return this.idTipoNomina;
	}
	public void setIdTipoNomina(String idTipoNomina)
	{
		this.idTipoNomina = idTipoNomina;
	}
	public int getLargoRegistro()
	{
		return this.largoRegistro;
	}
	public void setLargoRegistro(int largoRegistro)
	{
		this.largoRegistro = largoRegistro;
	}
	public int getNumBloqueos()
	{
		return this.numBloqueos;
	}
	public void setNumBloqueos(int numBloqueos)
	{
		this.numBloqueos = numBloqueos;
	}
	
	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idMapaNom));
		parametros.put("2", String.valueOf(this.idTipoNomina));
		parametros.put("3", String.valueOf(this.descripcion));
		parametros.put("4", String.valueOf(this.largoRegistro));
		parametros.put("5", String.valueOf(this.numBloqueos));
		return parametros;
	}
}
