package cl.laaraucana.satelites.main.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cl.laaraucana.satelites.Utils.RutUtil;

public class IngresoRutForm extends ActionForm

{
	private static final long serialVersionUID = -245035565385424671L;
	private String rut = "";
	private String rutemp = "";

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	rut = "";
    	rutemp = "";
    }

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {

	ActionErrors errors = new ActionErrors();

	if (!rut.equals("") && !RutUtil.IsRutValido(rut)) {
	   errors.add("rut", new ActionMessage("errors.rut.invalido"));
	}
	if (!rutemp.equals("") && !RutUtil.IsRutValido(rutemp)) {
		   errors.add("rutemp", new ActionMessage("errors.rut.invalido"));
		}
	return errors;

    }
    
    public String getRut() {
	return this.rut;
    }

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
    
}
