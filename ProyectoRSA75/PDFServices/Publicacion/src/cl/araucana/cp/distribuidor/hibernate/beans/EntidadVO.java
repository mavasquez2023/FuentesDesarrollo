package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class EntidadVO extends AuditableVO implements Comparable
{
	private static final long serialVersionUID = -6646072223149291030L;
	protected int id;
	protected int idEntPagadora;
	protected String nombre;

	public EntidadVO(int id, int idEntPagadora)
	{
		super();
		this.id = id;
		this.idEntPagadora = idEntPagadora;
	}

	public EntidadVO(Integer id, Integer idEntPagadora)
	{
		super();
		this.id = id.intValue();
		this.idEntPagadora = idEntPagadora.intValue();
	}

	public EntidadVO(int id)
	{
		super();
		this.id = id;
	}

	public EntidadVO()
	{
		super();
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getIdEntPagadora()
	{
		return this.idEntPagadora;
	}

	public void setIdEntPagadora(int idEntPagadora)
	{
		this.idEntPagadora = idEntPagadora;
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
		return "EntidadVO[id: " + this.id + ", nombre: \"" + this.nombre + "\", idEntPagadora: " + this.idEntPagadora + "]";
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.id));
		parametros.put("2", String.valueOf(this.idEntPagadora));
		return parametros;
	}

	public int compareTo(Object o)
	{
		return this.getNombre().compareTo(((EntidadVO) o).getNombre());
	}

	public String getClave()
	{
		return "";
	}

	public boolean isCotizanteRefered()
	{
		return false;
	}

	public boolean isAfcRefered()
	{
		return false;
	}

	public boolean isApvRefered()
	{
		return false;
	}

	public boolean isConvenioRefered()
	{
		return false;
	}

	public boolean isMapeoRefered()
	{
		return false;
	}

	public boolean isTipoDetRefered()
	{
		return false;
	}

	public Class getMapeoAsociado()
	{
		return null;
	}
}
