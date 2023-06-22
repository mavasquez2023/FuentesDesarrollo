package cl.laaraucana.cuotasdup.main.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 1 field on this form:
 * <ul>
 * <li>rut - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class IntegracionForm extends ActionForm

{
	private static final long serialVersionUID = -245035565385424671L;
	private String rut = null;

    /**
     * Get rut
     * @return String
     */
    public String getRut() {
	return rut;
    }

    /**
     * Set rut
     * @param <code>String</code>
     */
    public void setRut(String r) {
	this.rut = r;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {

	// Reset values are provided as samples only. Change as appropriate.

	rut = null;

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
}
