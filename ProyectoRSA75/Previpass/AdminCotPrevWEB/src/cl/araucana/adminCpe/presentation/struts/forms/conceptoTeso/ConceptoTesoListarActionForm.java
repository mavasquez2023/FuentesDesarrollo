package cl.araucana.adminCpe.presentation.struts.forms.conceptoTeso;

import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) ConceptoTesoListarActionForm.java 1.1 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author malvarez
 * 
 * @version 1.1
 */
public class ConceptoTesoListarActionForm extends ActionForm
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
