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
	private String rutEmpresa = "";
	private String numero = "";
	private String rol;

	/**
	 * Get rut
	 * 
	 * @return String
	 */
	public String getRutEmpresa() {
		return rutEmpresa;
	}

	/**
	 * Set rut
	 * 
	 * @param <code>String</code>
	 */
	public void setRutEmpresa(String r) {
		this.rutEmpresa = r;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	

	/**
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		rutEmpresa = "";
		numero = "";
		rol= "";

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
