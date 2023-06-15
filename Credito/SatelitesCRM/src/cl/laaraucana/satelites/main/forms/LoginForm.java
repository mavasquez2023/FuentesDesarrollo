package cl.laaraucana.satelites.main.forms;

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
public class LoginForm extends ActionForm

{
	private static final long serialVersionUID = 1985639417027880071L;
	private String rut;
	private String clave;

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		setClave("");
		setRut("");
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		if ((rut == null) || (rut.length() == 0)) {
			errors.add("rut", new ActionMessage("error.main.rut.required"));
		}
		if ((clave == null) || (clave.length() == 0)) {
			errors.add("clave", new ActionMessage("error.main.clave.required"));
		}
		return errors;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
}
