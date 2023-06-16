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
public class VerPropuestaForm extends ActionForm 
{
	private String periodo;
	

	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{

		// Reset field values here.

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) 
	{

		ActionErrors errors = new ActionErrors();
		
		if(periodo.equalsIgnoreCase("") || periodo.equals(null))
			errors.add("periodo", new org.apache.struts.action.ActionError("errors.generico.required","Ingrese Periodo"));

		return errors;

	}
	

	/**
	 * @return
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @param string
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
