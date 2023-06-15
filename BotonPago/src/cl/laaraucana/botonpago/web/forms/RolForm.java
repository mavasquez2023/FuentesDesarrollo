package cl.laaraucana.botonpago.web.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 1 field on this form:
 * <ul>
 * <li>rol - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class RolForm extends ActionForm

{

	/**
	 * 
	 */
	private static final long serialVersionUID = 304414477068602825L;
	private String rol = null;

	/**
	 * Get rol
	 * @return String
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * Set rol
	 * @param <code>String</code>
	 */
	public void setRol(String r) {
		this.rol = r;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		rol = null;

	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		return errors;

	}
}
