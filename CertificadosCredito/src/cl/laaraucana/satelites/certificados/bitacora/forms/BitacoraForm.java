package cl.laaraucana.satelites.certificados.bitacora.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cl.laaraucana.satelites.Utils.RutUtil;

/**
 * Form bean for a Struts application.
 * @version 	1.0
 * @author
 */
public class BitacoraForm extends ActionForm

{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5496336746502184639L;
	private String rutConsulta;
	
	
	/**
	 * @return the rutConsulta
	 */
	public String getRutConsulta() {
		return rutConsulta;
	}

	/**
	 * @param rutConsulta the rutConsulta to set
	 */
	public void setRutConsulta(String rutConsulta) {
		this.rutConsulta = rutConsulta;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		rutConsulta = "";
    }

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {
    	

	ActionErrors errors = new ActionErrors();
	if ((rutConsulta == null) || (rutConsulta.length() == 0)) {
		errors.add("rutEmpleado", new ActionMessage("errors.rut.invalido"));
	}else if(!RutUtil.IsRutValido(rutConsulta)){
		errors.add("rutEmpleado", new ActionMessage("errors.rut.invalido"));
	}
	
	
	return errors;

    }
}
