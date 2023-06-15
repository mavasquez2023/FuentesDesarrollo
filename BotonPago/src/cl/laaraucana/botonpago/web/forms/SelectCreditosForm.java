package cl.laaraucana.botonpago.web.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application. Users may access 1 field on this form:
 * <ul>
 * <li>creditos - [your comment here]
 * </ul>
 * 
 * @version 1.0
 * @author
 */
public class SelectCreditosForm extends ActionForm

{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2358375380796032032L;

	private String op = null;
	private String folio = null;
	private String monto = null;
	private String[] creditos = null;

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String[] getCreditos() {
		return creditos;
	}

	public void setCreditos(String[] creditos) {
		this.creditos = creditos;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		op = null;
		folio = null;
		monto = null;
		creditos = null;

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
