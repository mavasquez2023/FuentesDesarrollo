package cl.araucana.cp.distribuidor.hibernate.beans;

public class EntidadMutualVO extends EntidadVO
{
	private static final long serialVersionUID = -114622714435539530L;

	public EntidadMutualVO()
	{
		super();
	}

	public EntidadMutualVO(int id)
	{
		super(id);
	}

	public EntidadMutualVO(int id, int idEntPagadora)
	{
		super(id, idEntPagadora);
	}

	public EntidadMutualVO(Integer id, Integer idEntPagadora)
	{
		super();
		this.id = id.intValue();
		this.idEntPagadora = idEntPagadora.intValue();
	}

	public String getClave()
	{
		return "MUTUAL";
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
		return true;
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
