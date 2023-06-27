package cl.araucana.adminCpe.presentation.struts.forms.parametro;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.action.ActionForm;
/*
* @(#) EdicionParametrosActionForm.java 1.1 10/05/2009
*
* Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
* La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
* restringido a los sistemas de informaci�n propios de la instituci�n.
*/
/**
 * @author jdelgado
 * 
 * @version 1.1
 */
public class EdicionParametrosActionForm extends ActionForm implements Serializable
{
	private static final long serialVersionUID = -1L;

	private String tipoParametro;
	private List listaParametros;
	/**
	 * lista parametros
	 * @return
	 */
	public List getListaParametros() {
		return this.listaParametros;
	}
	/**
	 * lista parametros
	 * @param listaParametros
	 */
	public void setListaParametros(List listaParametros) {
		this.listaParametros = listaParametros;
	}
	/**
	 * tipo parametro
	 * @return
	 */
	public String getTipoParametro() {
		return this.tipoParametro;
	}
	/**
	 * tipo parametro
	 * @param tipoParametro
	 */
	public void setTipoParametro(String tipoParametro) {
		this.tipoParametro = tipoParametro;
	}
	
	

	}
