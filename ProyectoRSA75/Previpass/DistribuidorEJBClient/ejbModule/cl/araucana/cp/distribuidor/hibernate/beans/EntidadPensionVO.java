package cl.araucana.cp.distribuidor.hibernate.beans;

import java.util.HashMap;

public class EntidadPensionVO extends EntidadVO
{
	private static final long serialVersionUID = -114622714435539530L;
	private int idBanco;
	private String idCtaCte;
	private int idAfc;
	private float cotizacionObligatoria;
	private float comision;
	private float sis;
	
	public EntidadPensionVO()
	{
		super();
	}

	public EntidadPensionVO(int id, int idEntPagadora)
	{
		super(id, idEntPagadora);
	}

	public EntidadPensionVO(Integer id, Integer idEntPagadora, Float tasaNormal, Float tasaPensionados, Float cotizacionObligatoria, Float comision, Float sis)
	{
		super();
		this.id = id.intValue();
		this.idEntPagadora = idEntPagadora.intValue();
		this.cotizacionObligatoria = cotizacionObligatoria.floatValue();
		this.comision = comision.floatValue();
		this.sis = sis.floatValue();
	}

	/**
	 * Devuelve la tasa total de la AFP sin SIS
	 * @return
	 */
	public float getTasaAFPSinSIS(){
		return this.getCotizacionObligatoria() + this.getComision();
	}
	
	/**
	 * Devuelve la tasa total de la AFP con SIS
	 * @return
	 */
	public float getTasaAFPConSIS(){
		return this.getCotizacionObligatoria() + this.getComision() + this.getSis();
	}	
	
	public int getIdBanco()
	{
		return this.idBanco;
	}

	public void setIdBanco(int idBanco)
	{
		this.idBanco = idBanco;
	}

	public String getIdCtaCte()
	{
		return this.idCtaCte;
	}

	public void setIdCtaCte(String idCtaCte)
	{
		this.idCtaCte = idCtaCte;
	}

	public int getIdAfc()
	{
		return this.idAfc;
	}

	public void setIdAfc(int idAfc)
	{
		this.idAfc = idAfc;
	}

	public HashMap getParametrosOrdenados()
	{
		HashMap parametros = new HashMap();
		parametros.put("1", String.valueOf(this.id));
		parametros.put("2", String.valueOf(this.idEntPagadora));
		parametros.put("3", String.valueOf(this.idBanco));
		parametros.put("4", String.valueOf(this.idCtaCte));
		parametros.put("5", String.valueOf(this.idAfc));
		parametros.put("6", String.valueOf(this.cotizacionObligatoria));
		parametros.put("7", String.valueOf(this.comision));
		parametros.put("8",String.valueOf(this.sis));
		return parametros;
	}

	public String getClave()
	{
		return "AFP";
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

	public Class getMapeoAsociado()
	{
		return MapeoPensionVO.class;
	}

	public boolean isTipoDetRefered()
	{
		return true;
	}

	public float getComision()
	{
		return this.comision;
	}

	public void setComision(float comision)
	{
		this.comision = comision;
	}

	public float getCotizacionObligatoria()
	{
		return this.cotizacionObligatoria;
	}

	public void setCotizacionObligatoria(float cotizacionObligatoria)
	{
		this.cotizacionObligatoria = cotizacionObligatoria;
	}

	public float getSis()
	{
		return this.sis;
	}

	public void setSis(float sis)
	{
		this.sis = sis;
	}
}