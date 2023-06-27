package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;
import java.util.Date;

public class InformacionNomina implements Serializable
{

	private long rutEmpresa;
	private String formatRutEmpresa;
	private long idConvenio;
	private Date fechaEnvio;
	private Date fechaAceptada;
	private int totalTrabajadores;
	private int totalOk;
	private int totalErroneos;
	private long rutEncargado;
	private String formatRutEncargado;
	private long normalSize;
	private long comprimidoSize;
	private Date fechaRecibido;
	
		
	public Date getFechaAceptada()
	{
		return this.fechaAceptada;
	}
	public void setFechaAceptada(Date fechaAceptada)
	{
		this.fechaAceptada = fechaAceptada;
	}
	public Date getFechaEnvio()
	{
		return this.fechaEnvio;
	}
	public void setFechaEnvio(Date fechaEnvio)
	{
		this.fechaEnvio = fechaEnvio;
	}
	public Date getFechaRecibido()
	{
		return this.fechaRecibido;
	}
	public void setFechaRecibido(Date fechaRecibido)
	{
		this.fechaRecibido = fechaRecibido;
	}
	public long getIdConvenio()
	{
		return this.idConvenio;
	}
	public void setIdConvenio(long idConvenio)
	{
		this.idConvenio = idConvenio;
	}
	public long getRutEmpresa()
	{
		return this.rutEmpresa;
	}
	public void setRutEmpresa(long rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}
	public long getRutEncargado()
	{
		return this.rutEncargado;
	}
	public void setRutEncargado(long rutEncargado)
	{
		this.rutEncargado = rutEncargado;
	}	
	public int getTotalErroneos()
	{
		return this.totalErroneos;
	}
	public void setTotalErroneos(int totalErroneos)
	{
		this.totalErroneos = totalErroneos;
	}
	public int getTotalOk()
	{
		return this.totalOk;
	}
	public void setTotalOk(int totalOk)
	{
		this.totalOk = totalOk;
	}
	public int getTotalTrabajadores()
	{
		return this.totalTrabajadores;
	}
	public void setTotalTrabajadores(int totalTrabajadores)
	{
		this.totalTrabajadores = totalTrabajadores;
	}
	public long getComprimidoSize()
	{
		return this.comprimidoSize;
	}
	public void setComprimidoSize(long comprimidoSize)
	{
		this.comprimidoSize = comprimidoSize;
	}
	public long getNormalSize()
	{
		return this.normalSize;
	}
	public void setNormalSize(long normalSize)
	{
		this.normalSize = normalSize;
	}
	public String getFormatRutEmpresa()
	{
		return this.formatRutEmpresa;
	}
	public void setFormatRutEmpresa(String formatRutEmpresa)
	{
		this.formatRutEmpresa = formatRutEmpresa;
	}
	public String getFormatRutEncargado()
	{
		return this.formatRutEncargado;
	}
	public void setFormatRutEncargado(String formatRutEncargado)
	{
		this.formatRutEncargado = formatRutEncargado;
	}
	
	
}
