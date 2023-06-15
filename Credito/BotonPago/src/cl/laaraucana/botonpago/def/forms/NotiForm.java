package cl.laaraucana.botonpago.def.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 2 fields on this form:
 * <ul>
 * <li>trx - [your comment here]
 * <li>vector - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class NotiForm extends ActionForm

{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2601753048083904273L;
	private String trx = null;
	private String vector = null;

	/**
	 * Get trx
	 * @return String
	 */
	public String getTrx() {
		return trx;
	}

	/**
	 * Set trx
	 * @param <code>String</code>
	 */
	public void setTrx(String t) {
		this.trx = t;
	}

	/**
	 * Get vector
	 * @return String
	 */
	public String getVector() {
		return vector;
	}

	/**
	 * Set vector
	 * @param <code>String</code>
	 */
	public void setVector(String v) {
		this.vector = v;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		trx = null;
		vector = null;

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
