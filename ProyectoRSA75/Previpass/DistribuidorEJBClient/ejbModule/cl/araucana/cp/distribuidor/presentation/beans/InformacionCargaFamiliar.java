package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;
import java.util.Date;

public class InformacionCargaFamiliar implements Serializable
{
	private int rutTrabajador;
	private String rutTrabajadorFmt;
	private String nombreTrabajador;
	private int rutCarga;
	private String rutCargaFmt;
	private String nombreCarga;
	private Date fecNacCarga;
	private Date fecIniVigencia;
	private Date fecFinVigencia;
	private String parentesco;
	private String tipo;
	private int rutEmpresa;	
	private String rutEmpresaFmt;
	private String razonSocialEmpresa;
	private String casaMatrizEmpresa;
	private String valorCargaFamiliar;
	private String nombreCaja;
	private String tramoAsigFam;

	public String getParentesco()
	{
		return this.parentesco;
	}
	public void setParentesco(String parentesco)
	{
		this.parentesco = parentesco;
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
	public String getNombreCarga()
	{
		return this.nombreCarga;
	}
	public void setNombreCarga(String nombreCarga)
	{
		this.nombreCarga = nombreCarga;
	}
	public String getNombreTrabajador()
	{
		return this.nombreTrabajador;
	}
	public void setNombreTrabajador(String nombreTrabajador)
	{
		this.nombreTrabajador = nombreTrabajador;
	}
	public int getRutCarga()
	{
		return this.rutCarga;
	}
	public void setRutCarga(int rutCarga)
	{
		this.rutCarga = rutCarga;
	}
	public String getRutCargaFmt()
	{
		return this.rutCargaFmt;
	}
	public void setRutCargaFmt(String rutCargaFmt)
	{
		this.rutCargaFmt = rutCargaFmt;
	}
	public int getRutTrabajador()
	{
		return this.rutTrabajador;
	}
	public void setRutTrabajador(int rutTrabajador)
	{
		this.rutTrabajador = rutTrabajador;
	}
	public String getRutTrabajadorFmt()
	{
		return this.rutTrabajadorFmt;
	}
	public void setRutTrabajadorFmt(String rutTrabajadorFmt)
	{
		this.rutTrabajadorFmt = rutTrabajadorFmt;
	}
	public String getTipo()
	{
		return this.tipo;
	}
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}
	public String getRazonSocialEmpresa()
	{
		return this.razonSocialEmpresa;
	}
	public void setRazonSocialEmpresa(String razonSocialEmpresa)
	{
		this.razonSocialEmpresa = razonSocialEmpresa;
	}
	public String getRutEmpresaFmt()
	{
		return this.rutEmpresaFmt;
	}
	public void setRutEmpresaFmt(String rutEmpresaFmt)
	{
		this.rutEmpresaFmt = rutEmpresaFmt;
	}
	public String getCasaMatrizEmpresa()
	{
		return this.casaMatrizEmpresa;
	}
	public void setCasaMatrizEmpresa(String casaMatrizEmpresa)
	{
		this.casaMatrizEmpresa = casaMatrizEmpresa;
	}
	public String getValorCargaFamiliar()
	{
		return this.valorCargaFamiliar;
	}
	public void setValorCargaFamiliar(String valorCargaFamiliar)
	{
		this.valorCargaFamiliar = valorCargaFamiliar;
	}
	public int getRutEmpresa()
	{
		return this.rutEmpresa;
	}
	public void setRutEmpresa(int rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}
	public String getNombreCaja()
	{
		return this.nombreCaja;
	}
	public void setNombreCaja(String nombreCaja)
	{
		this.nombreCaja = nombreCaja;
	}
	public String getTramoAsigFam()
	{
		return this.tramoAsigFam;
	}
	public void setTramoAsigFam(String tramoAsigFam)
	{
		this.tramoAsigFam = tramoAsigFam;
	}
}