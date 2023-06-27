package cl.araucana.cp.presentation.struts.forms;

import org.apache.struts.action.ActionForm;
/*
* @(#) ProcesarNominaForm.java 1.1 10/05/2009
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
public class ProcesarNominaForm extends ActionForm 
{
	private static final long serialVersionUID = 363281107746661848L;
	private String lineas;
	/**
	 * lineas
	 * @return
	 */
	public String getLineas() {
		return this.lineas;
	}
	/**
	 * lineas
	 * @param lineas
	 */
	public void setLineas(String lineas) {
		this.lineas = lineas;
	}
}
