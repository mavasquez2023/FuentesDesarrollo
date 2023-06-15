package cl.laaraucana.botonpago.web.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 2 fields on this form:
 * <ul>
 * <li>folio - [your comment here]
 * <li>origen - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class CuotaForm extends ActionForm

{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1346478887700440986L;
	private String folio = null;
	private String origen = null;
	private String tipoCredito = null;

	/**
	 * Get folio
	 * @return String
	 */
	public String getFolio() {
		return folio;
	}

	/**
	 * Set folio
	 * @param <code>String</code>
	 */
	public void setFolio(String f) {
		this.folio = f;
	}

	/**
	 * Get origen
	 * @return String
	 */
	public String getOrigen() {
		return origen;
	}

	/**
	 * Set origen
	 * @param <code>String</code>
	 */
	public void setOrigen(String o) {
		this.origen = o;
	}

	/**
	 * @return el tipoCredito
	 */
	public String getTipoCredito() {
		return tipoCredito;
	}

	/**
	 * @param tipoCredito el tipoCredito a establecer
	 */
	public void setTipoCredito(String tipoCredito) {
		this.tipoCredito = tipoCredito;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		folio = null;
		origen = null;

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
