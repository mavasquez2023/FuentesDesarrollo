package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class EntidadExCajaVO extends EntidadVO
{
	private static final long serialVersionUID = 5745578892336752937L;
	

	public EntidadExCajaVO()
	{
		super();
	}

	public EntidadExCajaVO(int id, int idEntPagadora)
	{
		super(id, idEntPagadora);
	}
	public EntidadExCajaVO(Integer id, Integer idEntPagadora, String nombre)
	{
		super();
		this.id=id.intValue();
		this.idEntPagadora=idEntPagadora.intValue();
		this.nombre=nombre;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.id));
		parametros.put("2", String.valueOf(this.idEntPagadora));
		parametros.put("3", String.valueOf(this.nombre));
		return parametros;
	}

	public String getClave()
	{
		return "EXCAJA";
	}

	public boolean isCotizanteRefered()
	{
		return true;
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
}
