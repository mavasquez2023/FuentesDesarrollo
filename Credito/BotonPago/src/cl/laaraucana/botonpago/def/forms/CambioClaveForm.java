package cl.laaraucana.botonpago.def.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * Formulario para el cambio de clave
 * @author LaAraucana
 *
 */
public class CambioClaveForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	private String claveInicial;
	private String claveNueva;
	private String confirmaClave;
	private static String patron = "\\d{4}";

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {

	ActionErrors errors = new ActionErrors();

	if ((claveInicial == null) || (claveInicial.trim().length() == 0)) {
		errors.add("claveInicial", new ActionMessage("error.campo.requerido", "clave actual"));
		return errors;
	}
	
	if ((claveNueva == null) || (claveNueva.trim().length() == 0)) {
		errors.add("claveNueva", new ActionMessage("error.campo.requerido", "clave nueva"));
		return errors;
	}
	

	//Formato de clave
	if(!claveInicial.matches(patron)){
		errors.add("claveInicial", new ActionMessage("error.formato.clave", "Clave actual"));
		return errors;
	}
	
	if(!claveNueva.matches(patron)){
		errors.add("claveNueva", new ActionMessage("error.formato.clave", "Clave nueva"));
		return errors;
	}
	
	//Clave nueva es igual a clave inicial
	if ((claveInicial.equals(claveNueva))) {
		errors.add("confirmaClave", new ActionMessage("error.claves.iguales"));
		return errors;
	}
	
	//Clave nueva y confirmacion
	if ((confirmaClave == null) || (!confirmaClave.equals(claveNueva))) {
		errors.add("confirmaClave", new ActionMessage("error.claves.nocoinciden"));
		return errors;
	}
	
	return errors;

    }
    
	public String getClaveInicial() {
		return claveInicial;
	}

	public void setClaveInicial(String claveInicial) {
		this.claveInicial = claveInicial;
	}

	public String getClaveNueva() {
		return claveNueva;
	}

	public void setClaveNueva(String claveNueva) {
		this.claveNueva = claveNueva;
	}

	public String getConfirmaClave() {
		return confirmaClave;
	}

	public void setConfirmaClave(String confirmaClave) {
		this.confirmaClave = confirmaClave;
	}

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	claveInicial = "";
    	claveNueva = "";
    	confirmaClave = "";
    }
}
