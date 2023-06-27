package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class TipoNominaVO extends AuditableVO
{
	private static final long serialVersionUID = -5728277585995960819L;
	private String idTipoNomina;
	private String descripcion;
	private int orden;

	public TipoNominaVO()
	{}

	public String getDescripcion()
	{
		return this.descripcion;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}

	public String getIdTipoNomina()
	{
		return this.idTipoNomina;
	}

	public void setIdTipoNomina(String idTipoNomina)
	{
		this.idTipoNomina = idTipoNomina;
	}

	public int getOrden()
	{
		return this.orden;
	}

	public void setOrden(int orden)
	{
		this.orden = orden;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.idTipoNomina));
		parametros.put("2", String.valueOf(this.descripcion));
		parametros.put("3", String.valueOf(this.orden));
		return parametros;
	}

}
