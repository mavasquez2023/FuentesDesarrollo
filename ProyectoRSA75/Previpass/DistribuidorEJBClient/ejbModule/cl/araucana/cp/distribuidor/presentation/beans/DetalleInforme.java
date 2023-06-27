package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;

import cl.araucana.cp.distribuidor.hibernate.beans.CausaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.TipoCausaVO;

public class DetalleInforme implements Serializable
{

	private CausaVO causa;
	
	private TipoCausaVO tipoCausa;

	public CausaVO getCausa()
	{
		return this.causa;
	}

	public void setCausa(CausaVO causa)
	{
		this.causa = causa;
	}

	public TipoCausaVO getTipoCausa()
	{
		return this.tipoCausa;
	}

	public void setTipoCausa(TipoCausaVO tipoCausa)
	{
		this.tipoCausa = tipoCausa;
	}	
}
