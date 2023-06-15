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
public class OlvidoClaveForm extends ActionForm

{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4869541596841883962L;
	/**
	 * 
	 */
	private String rut = null;

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		rut = null;

	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if ((rut == null) || !RutUtil.IsRutValido(rut)) {
			errors.add("rut", new ActionMessage("error.rut"));
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

}
