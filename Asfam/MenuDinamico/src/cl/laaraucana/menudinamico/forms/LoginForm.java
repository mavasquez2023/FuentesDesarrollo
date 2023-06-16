package cl.laaraucana.menudinamico.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * @version 	1.0
 * @author
 */
public class LoginForm extends ActionForm
{	
	private String claveInicial;
	private String claveNueva;

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	claveInicial = "";
    	claveNueva = "";
    }

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {

	ActionErrors errors = new ActionErrors();
	// Validate the fields in your form, adding
	// adding each error to this.errors as found, e.g.

	// if ((field == null) || (field.length() == 0)) {
	//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
	// }
	return errors;

    }

	public String getClaveInicial() {
		return claveInicial;
	}

	public void setClaveInicial(String claveInicial) {
		this.claveInicial = claveInicial;
	}

	public String getClaveNueva() {
		return claveNueva;
	}

	public void setClaveNueva(String claveNueva) {
		this.claveNueva = claveNueva;
	}
}
