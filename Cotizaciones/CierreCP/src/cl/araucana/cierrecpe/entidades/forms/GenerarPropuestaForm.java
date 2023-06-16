package cl.araucana.cierrecpe.entidades.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
/**
 * Form bean for a Struts application.
 * @version 	1.0
 * @author
 */
public class GenerarPropuestaForm extends ActionForm 
{
	private String cierre;
	

	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{

		// Reset field values here.

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) 
	{

		ActionErrors errors = new ActionErrors();
		
		if(cierre.equalsIgnoreCase("") || cierre.equals(null))
			errors.add("cierre", new org.apache.struts.action.ActionError("errors.generico.required","Ingrese Cierre"));

		return errors;

	}
	

	/**
	 * @return
	 */
	public String getCierre() {
		return cierre;
	}

	/**
	 * @param string
	 */
	public void setCierre(String cierre) {
		this.cierre = cierre;
	}

}
