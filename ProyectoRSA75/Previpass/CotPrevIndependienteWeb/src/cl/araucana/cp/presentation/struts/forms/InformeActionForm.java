package cl.araucana.cp.presentation.struts.forms;

import org.apache.struts.action.ActionForm;

/*
 * @(#) InformeActionForm.java 1.8 10/05/2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
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
