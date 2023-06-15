package cl.laaraucana.botonpago.def.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cl.laaraucana.botonpago.web.utils.RutUtil;

/**
 * Form bean for a Struts application.
 * Users may access 3 fields on this form:
 * <ul>
 * <li>rut - [your comment here]
 * <li>email - [your comment here]
 * <li>email2 - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
@Deprecated
public class SolicitaClaveForm extends ActionForm

{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5907867444732742481L;
	private String rut = null;
	private String email = null;
	private String email2 = null;

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		rut = null;
		email = null;
		email2 = null;

	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if ((rut == null) || !RutUtil.IsRutValido(rut)) {
			errors.add("rut", new ActionMessage("error.rut"));
			return errors;
		}

		if ((email == null) || (email.length() == 0)) {
			errors.add("email", new ActionMessage("error.mail"));
			return errors;
		}

		if ((email2 == null) || (email2.length() == 0)) {
			errors.add("email2", new ActionMessage("error.required"));
			return errors;
		}

		if (!(email2.equals(email))) {
			errors.add("email2", new ActionMessage("error.email.confirm"));
			return errors;
		}

		return errors;

	}

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

	/**
	 * Get email
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set email
	 * @param <code>String</code>
	 */
	public void setEmail(String e) {
		this.email = e;
	}

	/**
	 * Get email2
	 * @return String
	 */
	public String getEmail2() {
		return email2;
	}

	/**
	 * Set email2
	 * @param <code>String</code>
	 */
	public void setEmail2(String e) {
		this.email2 = e;
	}
}
