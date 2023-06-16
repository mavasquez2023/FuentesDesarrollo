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
public class VerDetallePropuestaForm extends ActionForm 
{
	private String folio;
	

	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{

		// Reset field values here.

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) 
	{

		ActionErrors errors = new ActionErrors();
		
		if(folio.equalsIgnoreCase("") || folio.equals(null))
			errors.add("folio", new org.apache.struts.action.ActionError("errors.generico.required","Ingrese Folio"));

		return errors;

	}
	

	/**
	 * @return
	 */
	public String getFolio() {
		return folio;
	}

	/**
	 * @param string
	 */
	public void setFolio(String folio) {
		this.folio = folio;
	}

}
