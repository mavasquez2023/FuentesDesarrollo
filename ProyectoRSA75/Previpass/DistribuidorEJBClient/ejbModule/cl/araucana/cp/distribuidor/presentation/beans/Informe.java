package cl.araucana.cp.distribuidor.presentation.beans;

import java.io.Serializable;
import java.util.List;

import cl.araucana.cp.distribuidor.hibernate.beans.EmpresaVO;
import cl.araucana.cp.distribuidor.hibernate.beans.NominaVO;

public class Informe implements Serializable
{
	List listadoResumen; //ResumenInforme
	
	EmpresaVO empresa;
	
	String tipoProceso;
	
	NominaVO nomina;

	public EmpresaVO getEmpresa()
	{
		return this.empresa;
	}

	public void setEmpresa(EmpresaVO empresa)
	{
		this.empresa = empresa;
	}

	public List getListadoResumen()
	{
		return this.listadoResumen;
	}

	public void setListadoResumen(List listadoResumen)
	{
		this.listadoResumen = listadoResumen;
	}

	public String getTipoProceso()
	{
		return this.tipoProceso;
	}

	public void setTipoProceso(String tipoProceso)
	{
		this.tipoProceso = tipoProceso;
	}

	public NominaVO getNomina()
	{
		return this.nomina;
	}

	public void setNomina(NominaVO nomina)
	{
		this.nomina = nomina;
	}
	
	
}
