package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;

import cl.araucana.cp.distribuidor.hibernate.beans.CausaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.CotizanteVO;
import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;

public class InformeAvisosError implements Serializable
{
	EmpresaVO empresa;
	
	CotizanteVO cotizante;
	
	CausaVO causa;
	
	private int rutEmpresa;
	
	private String rutFormateado;
	
	private String razonSocial;
	
	private Integer habilitada;
	
	private int idConvenio;

	public CausaVO getCausa()
	{
		return this.causa;
	}

	public void setCausa(CausaVO causa)
	{
		this.causa = causa;
	}

	public CotizanteVO getCotizante()
	{
		return this.cotizante;
	}

	public void setCotizante(CotizanteVO cotizante)
	{
		this.cotizante = cotizante;
	}

	public EmpresaVO getEmpresa()
	{
		return this.empresa;
	}

	public void setEmpresa(EmpresaVO empresa)
	{
		this.empresa = empresa;
	}

	public Integer getHabilitada()
	{
		return this.habilitada;
	}

	public void setHabilitada(Integer habilitada)
	{
		this.habilitada = habilitada;
	}

	public int getIdConvenio()
	{
		return this.idConvenio;
	}

	public void setIdConvenio(int idConvenio)
	{
		this.idConvenio = idConvenio;
	}

	public String getRazonSocial()
	{
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial)
	{
		this.razonSocial = razonSocial;
	}

	public int getRutEmpresa()
	{
		return this.rutEmpresa;
	}

	public void setRutEmpresa(int rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}

	public String getRutFormateado()
	{
		return this.rutFormateado;
	}

	public void setRutFormateado(String rutFormateado)
	{
		this.rutFormateado = rutFormateado;
	}
	
	
}
