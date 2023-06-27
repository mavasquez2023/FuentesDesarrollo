package cl.araucana.cp.distribuidor.hibernate.beans;

import cl.araucana.cp.distribuidor.hibernate.beans.AuditableVO;

public class RelacionTipoCausaVO extends AuditableVO
{
	private static final long serialVersionUID = 1L;
	private String campo;
	private String idTipoCausas;

	public RelacionTipoCausaVO()
	{
		super();
	}

	public RelacionTipoCausaVO(String campo, String idTipoCausas)
	{
		this.campo = campo;
		this.idTipoCausas = idTipoCausas;
	}

	public String getCampo()
	{
		return this.campo;
	}

	public void setCampo(String campo)
	{
		this.campo = campo;
	}

	public String getIdTipoCausas()
	{
		return this.idTipoCausas;
	}

	public void setIdTipoCausas(String idTipoCausas)
	{
		this.idTipoCausas = idTipoCausas;
	}
}
