package cl.laaraucana.simulacion.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * Form bean for a Struts application.
 * 
 * @version 1.0
 * @author
 */
public class SolicitarCotizacionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String rut;

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// Reset values are provided as samples only. Change as appropriate.
		rut = "";
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if ((rut.isEmpty())) {
			errors.add("oficina", new ActionMessage(
					"errors.formalizar.required"));
		}

		return errors;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}
}
