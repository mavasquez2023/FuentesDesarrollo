package cl.laaraucana.satelites.main.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cl.laaraucana.satelites.Utils.RutUtil;

public class LoginForm extends ActionForm

{
	private static final long serialVersionUID = -245035565385424671L;
	private String j_username;
	private String j_password;

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	j_username = "";
    	j_password = "";
    }

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
	
		if (j_username == null || j_username.trim().isEmpty()) {
			   errors.add("j_username", new ActionMessage("error.rut.required"));
			   return errors;
		}
		
		if(j_password == null || j_password.trim().isEmpty()){
			errors.add("j_password", new ActionMessage("error.password.required"));
			return errors;
		}
		
		if (!RutUtil.IsRutValido(j_username)) {
			   errors.add("j_username", new ActionMessage("errors.rut.invalido"));
			   return errors;
		}
		
		return errors;
    }

	public String getJ_username() {
		return j_username;
	}

	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}

	public String getJ_password() {
		return j_password;
	}

	public void setJ_password(String j_password) {
		this.j_password = j_password;
	}
}
