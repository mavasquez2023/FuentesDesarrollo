package cl.araucana.cp.presentation.struts.forms;

import org.apache.struts.action.ActionForm;
/*
* @(#) LofinForm.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
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
