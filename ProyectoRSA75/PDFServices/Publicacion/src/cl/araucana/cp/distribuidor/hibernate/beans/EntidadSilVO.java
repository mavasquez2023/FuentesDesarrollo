package cl.araucana.cp.distribuidor.hibernate.beans;

public class EntidadSilVO extends EntidadVO
{
	private static final long serialVersionUID = 3846680811974417367L;

	public EntidadSilVO(Integer id, Integer idEntPagadora)
	{
		super();
		this.id = id.intValue();
		this.idEntPagadora = idEntPagadora.intValue();
	}

	public EntidadSilVO()
	{
		super();
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

	public boolean isTipoDetRefered()
	{
		return true;
	}
}
