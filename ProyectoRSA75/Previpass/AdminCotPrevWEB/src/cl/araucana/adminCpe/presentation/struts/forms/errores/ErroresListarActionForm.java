package cl.araucana.adminCpe.presentation.struts.forms.errores;

import java.util.Collection;
import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) ErroresListarActionForm.java 1.1 10/05/2009
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
public class ErroresListarActionForm extends ActionForm
{
	private static final long serialVersionUID = 2853065679225574902L;

	private List listaErrores;
	
	private Collection numeroFilas; 
	/**
	 * nuemro filas
	 * @return
	 */
	public Collection getNumeroFilas() {
		return this.numeroFilas;
	}

	/**
	 * numero filas
	 * @param numeroFilas
	 */
	public void setNumeroFilas(Collection numeroFilas) {
		this.numeroFilas = numeroFilas;
	}

	/**
	 * lista errores
	 * @return
	 */
	public List getListaErrores() {
		return this.listaErrores;
	}

	/**
	 * lista errores
	 * @param listaErrores
	 */
	public void setListaErrores(List listaErrores) {
		this.listaErrores = listaErrores;
	}
	
	
}
