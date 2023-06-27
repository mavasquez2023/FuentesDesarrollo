package cl.araucana.adminCpe.presentation.struts.forms.mapeo;

import java.util.Collection;
import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) MapeoTesoreriaListarActionForm.java 1.2 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author malvarez
 * @author cmeli
 * 
 * @version 1.2
 */
public class MapeoTesoreriaListarActionForm extends ActionForm
{
	private static final long serialVersionUID = 2853065679225574902L;

	private List lista;
	private Collection numeroFilas; 

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

	/**
	 * numero filas
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
}
