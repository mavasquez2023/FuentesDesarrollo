package cl.araucana.cp.presentation.struts.forms;

import org.apache.struts.action.ActionForm;

/*
 * @(#) InformeActionForm.java 1.8 10/05/2009
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 */
/**
 * @author jlandero
 * 
 * @version 1.0
 */
public class InformeActionForm extends ActionForm
{
	private String rutEmpresa;
	private String dvRutEmpresa;
	private String razonSocial;
	private String tipoProceso;
	
	public String getRazonSocial()
	{
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial)
	{
		this.razonSocial = razonSocial;
	}
	public String getRutEmpresa()
	{
		return rutEmpresa;
	}
	public void setRutEmpresa(String rutEmpresa)
	{
		this.rutEmpresa = rutEmpresa;
	}
	public String getTipoProceso()
	{
		return tipoProceso;
	}
	public void setTipoProceso(String tipoProceso)
	{
		this.tipoProceso = tipoProceso;
	}
	public String getDvRutEmpresa()
	{
		return dvRutEmpresa;
	}
	public void setDvRutEmpresa(String dvRutEmpresa)
	{
		this.dvRutEmpresa = dvRutEmpresa;
	}
	
	
}
