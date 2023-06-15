package cl.laaraucana.satelites.certificados.creditovigente.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * 
 * @version 1.0
 * @author
 */
public class SeleccionCreditoForm extends ActionForm

{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2693496146699723173L;
	public String folio;

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

	ActionErrors errors = new ActionErrors();
	/*	
		if ((folio == null) || (folio.length() != 13)) {
			errors.add("folio", new ActionMessage("error.folio.incorrecto"));
		}*/
		return errors;

	}
}
