package cl.araucana.adminCpe.presentation.struts.forms;

import org.apache.struts.action.ActionForm;
/*
* @(#) EstadisticasActionForm.java 1.1 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author ccostagliola
 * 
 * @version 1.1
 */
public class EstadisticasForm extends ActionForm
{
	private static final long serialVersionUID = 2897919502704207701L;

	private String accion;
	private String subAccion;

	private String[] checkbox;

	/**
	 * check box
	 * @return
	 */
	public String[] getCheckbox()
	{
		return this.checkbox;
	}

	/**
	 * check box
	 * @param checkbox
	 */
	public void setCheckbox(String[] checkbox)
	{
		this.checkbox = checkbox;
	}

	/**
	 * accion
	 * @return
	 */
	public String getAccion()
	{
		return this.accion;
	}

	/**
	 * accion
	 * @param accion
	 */
	public void setAccion(String accion)
	{
		this.accion = accion;
	}

	/**
	 * sub accion
	 * @return
	 */
	public String getSubAccion()
	{
		return this.subAccion;
	}

	/**
	 * sub accion
	 * @param subAccion
	 */
	public void setSubAccion(String subAccion)
	{
		this.subAccion = subAccion;
	}	
}
