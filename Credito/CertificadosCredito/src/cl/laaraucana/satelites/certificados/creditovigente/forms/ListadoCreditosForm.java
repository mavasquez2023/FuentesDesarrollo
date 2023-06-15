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
public class ListadoCreditosForm extends ActionForm

{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2577380238696034294L;
	private String rut;
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.rut = "";
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
/*
		if ( (this.rut == null) || (rut.length() == 0)) {
			errors.add("rut", new ActionMessage("error.rut.required"));
			return errors;
		}
		
		if(!RutUtil.IsRutValido(this.rut)){
			errors.add("rut", new ActionMessage("error.rut.formatoInvalido"));
			return errors;
		}
	*/	
		return errors;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}
}
