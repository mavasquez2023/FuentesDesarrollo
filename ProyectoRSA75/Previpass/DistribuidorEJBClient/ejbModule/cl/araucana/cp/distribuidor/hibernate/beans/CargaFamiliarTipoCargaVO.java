package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;

public class CargaFamiliarTipoCargaVO implements Serializable 
{

	private int idCargaFamiliarTipoCarga;
	private int idTipoCarga;
	private int idCargaFamiliar;
	private int numeroCargas;
	
	public CargaFamiliarTipoCargaVO()
	{
		super();
	}

	public int getIdCargaFamiliar()
	{
		return this.idCargaFamiliar;
	}

	public void setIdCargaFamiliar(int idCargaFamiliar)
	{
		this.idCargaFamiliar = idCargaFamiliar;
	}

	public int getIdCargaFamiliarTipoCarga()
	{
		return this.idCargaFamiliarTipoCarga;
	}

	public void setIdCargaFamiliarTipoCarga(int idCargaFamiliarTipoCarga)
	{
		this.idCargaFamiliarTipoCarga = idCargaFamiliarTipoCarga;
	}

	public int getIdTipoCarga()
	{
		return this.idTipoCarga;
	}

	public void setIdTipoCarga(int idTipoCarga)
	{
		this.idTipoCarga = idTipoCarga;
	}

	public int getNumeroCargas()
	{
		return this.numeroCargas;
	}

	public void setNumeroCargas(int numeroCargas)
	{
		this.numeroCargas = numeroCargas;
	}

}