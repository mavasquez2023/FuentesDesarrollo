package cl.araucana.cp.presentation.struts.forms;

import org.apache.struts.action.ActionForm;
/*
* @(#) LofinForm.java 1.1 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author cchamblas
 * 
 * @version 1.1
 */
public class LoginForm extends ActionForm 
{
	private static final long serialVersionUID = -8221744747585126298L;
	private String entrar;

	public String getEntrar() 
	{
		return this.entrar;
	}

	public void setEntrar(String entrar) 
	{
		this.entrar = entrar;
	}
}
