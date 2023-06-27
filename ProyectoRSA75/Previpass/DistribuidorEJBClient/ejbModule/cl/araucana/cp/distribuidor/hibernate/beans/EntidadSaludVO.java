package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class EntidadSaludVO extends EntidadVO
{
	private static final long serialVersionUID = 8112701390457508984L;
	private float tasaSalud;

	public EntidadSaludVO()
	{
		super();
	}

	public EntidadSaludVO(int id)
	{
		super(id);
	}

	public EntidadSaludVO(int id, int idEntPagadora)
	{
		super(id, idEntPagadora);
	}

	public EntidadSaludVO(int id, int idEntPagadora, float tasaSalud)
	{
		super(id, idEntPagadora);
		this.tasaSalud = tasaSalud;
	}

	public EntidadSaludVO(Integer id, Integer idEntPagadora, Float tasaSalud)
	{
		super(id, idEntPagadora);
		this.id = id.intValue();
		this.idEntPagadora = idEntPagadora.intValue();
		this.tasaSalud = tasaSalud.floatValue();
	}

	public float getTasaSalud()
	{
		return this.tasaSalud;
	}

	public void setTasaSalud(float tasaSalud)
	{
		this.tasaSalud = tasaSalud;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.id));
		parametros.put("2", String.valueOf(this.idEntPagadora));
		parametros.put("3", String.valueOf(this.tasaSalud));
		return parametros;
	}

	public String getClave()
	{
		return "ISAPRE";
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
		return true;
	}

	public boolean isTipoDetRefered()
	{
		return true;
	}

	public Class getMapeoAsociado()
	{
		return MapeoSaludVO.class;
	}
}
