package cl.araucana.adminCpe.presentation.struts.forms.informes;

import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) ComprobSplListarActionForm.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author malvarez
 * 
 * @version 1.1
 */
public class ComprobSplListarActionForm extends ActionForm
{
	private static final long serialVersionUID = 2853065679225574902L;

	private List lista;

	/**
	 * lista
	 * @return
	 */
	public List getLista() {
		return this.lista;
	}

	/**
	 * lista
	 * @param lista
	 */
	public void setLista(List lista) {
		this.lista = lista;
	}
	
}
