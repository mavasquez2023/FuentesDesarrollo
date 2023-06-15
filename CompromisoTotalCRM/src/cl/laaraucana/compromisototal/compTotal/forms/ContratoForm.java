package cl.laaraucana.compromisototal.compTotal.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * @version 	1.0
 * @author
 */
public class ContratoForm extends ActionForm

{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6450330479538258639L;
	private String id;
	private String rut;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

	// Reset field values here.

    }

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {

	ActionErrors errors = new ActionErrors();
	// Validate the fields in your form, adding
	// adding each error to this.errors as found, e.g.
	return errors;

    }
}
