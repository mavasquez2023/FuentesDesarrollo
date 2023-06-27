package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class TipoCargaVO implements Serializable
{
	private int idTipoCarga;
	private String codigoTipoCarga;
	private String tipoCarga;
	private String descripcion;

	public String getCodigoTipoCarga()
	{
		return this.codigoTipoCarga;
	}
	public void setCodigoTipoCarga(String codigoTipoCarga)
	{
		this.codigoTipoCarga = codigoTipoCarga;
	}
	public String getDescripcion()
	{
		return this.descripcion;
	}
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
	public int getIdTipoCarga()
	{
		return this.idTipoCarga;
	}
	public void setIdTipoCarga(int idTipoCarga)
	{
		this.idTipoCarga = idTipoCarga;
	}
	public String getTipoCarga()
	{
		return this.tipoCarga;
	}
	public void setTipoCarga(String tipoCarga)
	{
		this.tipoCarga = tipoCarga;
	}
}