package cl.araucana.cp.distribuidor.hibernate.beans;

public class EntidadApvVO extends EntidadVO
{
	private static final long serialVersionUID = 3385547316328968061L;

	public EntidadApvVO()
	{
		super();
	}

	public EntidadApvVO(int id, int idEntPagadora)
	{
		super(id, idEntPagadora);
	}

	public EntidadApvVO(Integer id, Integer idEntPagadora)
	{
		super();
		this.id = id.intValue();
		this.idEntPagadora = idEntPagadora.intValue();
	}

	public String getClave()
	{
		return "APV";
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
		return true;
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
		return MapeoAPVVO.class;
	}
}
