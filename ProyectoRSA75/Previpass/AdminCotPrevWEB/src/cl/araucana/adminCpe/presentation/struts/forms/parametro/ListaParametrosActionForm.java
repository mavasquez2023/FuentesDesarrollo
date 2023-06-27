package cl.araucana.adminCpe.presentation.struts.forms.parametro;

import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) ListaParametrosrActionForm.java 1.1 10/05/2009
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
*/
/**
 * @author jdelgado
 * 
 * @version 1.1
 */
public class ListaParametrosActionForm extends ActionForm
{
	private static final long serialVersionUID = 2853065679225574902L;

	private List listaNegocio;
	private List listaSistema;
	/**
	 * lista negocio
	 * @return
	 */
	public List getListaNegocio() {
		return this.listaNegocio;
	}
	/**
	 * lista negocio
	 * @param listaNegocio
	 */
	public void setListaNegocio(List listaNegocio) {
		this.listaNegocio = listaNegocio;
	}
	/**
	 * lista sistema
	 * @return
	 */
	public List getListaSistema() {
		return this.listaSistema;
	}
	/**
	 * lista sistema
	 * @param listaSistema
	 */
	public void setListaSistema(List listaSistema) {
		this.listaSistema = listaSistema;
	}
}
