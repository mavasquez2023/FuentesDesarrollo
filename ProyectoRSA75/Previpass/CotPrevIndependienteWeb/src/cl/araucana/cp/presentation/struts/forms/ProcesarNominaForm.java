package cl.araucana.cp.presentation.struts.forms;

import org.apache.struts.action.ActionForm;
/*
* @(#) ProcesarNominaForm.java 1.1 10/05/2009
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
