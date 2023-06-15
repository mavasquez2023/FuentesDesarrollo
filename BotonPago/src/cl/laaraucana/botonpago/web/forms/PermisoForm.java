package cl.laaraucana.botonpago.web.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 2 fields on this form:
 * <ul>
 * <li>id - [your comment here]
 * <li>roles - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class PermisoForm extends ActionForm

{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2481514594801279918L;
	private String id = null;
	private String[] roles = { "" };

	/**
	 * Get id
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set id
	 * @param <code>String</code>
	 */
	public void setId(String i) {
		this.id = i;
	}

	/**
	 * Get roles
	 * @return String[]
	 */
	public String[] getRoles() {
		return roles;
	}

	/**
	 * Set roles
	 * @param <code>String[]</code>
	 */
	public void setRoles(String[] r) {
		this.roles = r;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		id = null;
		roles = null;

	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }
		return errors;

	}
}
