package cl.araucana.cp.distribuidor.hibernate.beans;

import java.io.Serializable;
import java.util.Date;

public class DetalleCargaFamiliarVO implements Serializable
{
	
	private int idDetalleCargaFamiliar;
	private int idCargaFamiliar;
	private int rutCarga;
	private char dvCarga;
	private String nombreCarga;
	private Date fecNacCarga;
	private Date fecIniVigencia;
	private Date fecFinVigencia;
	private int idTipoCarga;
	private int idParentesco;
	
	public char getDvCarga()
	{
		return this.dvCarga;
	}
	public void setDvCarga(char dvCarga)
	{
		this.dvCarga = dvCarga;
	}
	public Date getFecFinVigencia()
	{
		return this.fecFinVigencia;
	}
	public void setFecFinVigencia(Date fecFinVigencia)
	{
		this.fecFinVigencia = fecFinVigencia;
	}
	public Date getFecIniVigencia()
	{
		return this.fecIniVigencia;
	}
	public void setFecIniVigencia(Date fecIniVigencia)
	{
		this.fecIniVigencia = fecIniVigencia;
	}
	public Date getFecNacCarga()
	{
		return this.fecNacCarga;
	}
	public void setFecNacCarga(Date fecNacCarga)
	{
		this.fecNacCarga = fecNacCarga;
	}
	public int getIdCargaFamiliar()
	{
		return this.idCargaFamiliar;
	}
	public void setIdCargaFamiliar(int idCargaFamiliar)
	{
		this.idCargaFamiliar = idCargaFamiliar;
	}
	public int getIdDetalleCargaFamiliar()
	{
		return this.idDetalleCargaFamiliar;
	}
	public void setIdDetalleCargaFamiliar(int idDetalleCargaFamiliar)
	{
		this.idDetalleCargaFamiliar = idDetalleCargaFamiliar;
	}
	public int getIdParentesco()
	{
		return this.idParentesco;
	}
	public void setIdParentesco(int idParentesco)
	{
		this.idParentesco = idParentesco;
	}
	public int getIdTipoCarga()
	{
		return this.idTipoCarga;
	}
	public void setIdTipoCarga(int idTipoCarga)
	{
		this.idTipoCarga = idTipoCarga;
	}
	public String getNombreCarga()
	{
		return this.nombreCarga;
	}
	public void setNombreCarga(String nombreCarga)
	{
		this.nombreCarga = nombreCarga;
	}
	public int getRutCarga()
	{
		return this.rutCarga;
	}
	public void setRutCarga(int rutCarga)
	{
		this.rutCarga = rutCarga;
	}
}