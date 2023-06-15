package cl.laaraucana.compromisototal.compTotal.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cl.laaraucana.compromisototal.utils.RutUtil;

/**
 * Form bean for a Struts application.
 * Users may access 2 fields on this form:
 * <ul>
 * <li>rut - [your comment here]
 * <li>pass - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class DatosCompromiso extends ActionForm

{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6484173610266347159L;
	private String rut = null;
    

    /**
     * Get rut
     * @return String
     */
    public String getRut() {
	return rut;
    }

    /**
     * Set rut
     * @param <code>String</code>
     */
    public void setRut(String r) {
	this.rut = r;
    }

    

    public void reset(ActionMapping mapping, HttpServletRequest request) {

	// Reset values are provided as samples only. Change as appropriate.

	rut = null;
	

    }

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {

	ActionErrors errors = new ActionErrors();
	if(rut != null){
		rut = rut.replace(".", "");
		if(RutUtil.IsRutValido(rut)){
			if (!RutUtil.IsRutValido(rut)) {
				   errors.add("rut", new ActionMessage("error.rut.invalido"));
				}
		return errors;
		}
	}

	return errors;

    }
}
