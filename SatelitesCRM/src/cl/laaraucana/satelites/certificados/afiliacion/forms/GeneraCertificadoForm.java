package cl.laaraucana.satelites.certificados.afiliacion.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application. Users may access 1 field on this form:
 * <ul>
 * <li>rut - [your comment here]
 * </ul>
 * 
 * @version 1.0
 * @author
 */
public class GeneraCertificadoForm extends ActionForm

{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8500724530885421711L;
	private String rut = "";
	private String rutemp = "";
	private String numero = "";

	/**
	 * Get rut
	 * 
	 * @return String
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * Set rut
	 * 
	 * @param <code>String</code>
	 */
	public void setRut(String r) {
		this.rut = r;
	}

	/**
	 * @return the rutemp
	 */
	public String getRutemp() {
		return rutemp;
	}

	/**
	 * @param rutemp the rutemp to set
	 */
	public void setRutemp(String rutemp) {
		this.rutemp = rutemp;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		rut = "";
		numero = "";

	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		//
		// if ((rut.isEmpty()) || (rut.length() == 0)) {
		// errors.add("rut", new
		// ActionMessage("error.certAfiliacion.rut.required"));
		// }
		return errors;

	}
}
