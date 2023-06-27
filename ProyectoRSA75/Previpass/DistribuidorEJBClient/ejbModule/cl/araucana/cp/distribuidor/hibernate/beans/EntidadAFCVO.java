package cl.araucana.cp.distribuidor.hibernate.beans;

public class EntidadAFCVO extends EntidadVO
{
	private static final long serialVersionUID = -114622714435539530L;

	public EntidadAFCVO()
	{
		super();
	}

	public EntidadAFCVO(int id, int idEntPagadora)
	{
		super(id, idEntPagadora);
	}

	public EntidadAFCVO(Integer id, Integer idEntPagadora)
	{
		super();
		this.id = id.intValue();
		this.idEntPagadora = idEntPagadora.intValue();
	}

	public String getClave()
	{
		return "AFC";
	}

	public boolean isCotizanteRefered()
	{
		return true;
	}

	public boolean isAfcRefered()
	{
		return true;
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
