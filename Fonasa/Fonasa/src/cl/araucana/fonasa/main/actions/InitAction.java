package cl.araucana.fonasa.main.actions;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import cl.araucana.fonasa.utils.ProxyLDAP;

public class InitAction extends Action {
	private static Logger logger = Logger.getLogger("InitAction");
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward(); 
		HttpSession sesion = request.getSession();
		sesion = request.getSession();

		Principal principal = request.getUserPrincipal();
		String username= principal.getName();

		sesion.setAttribute("usuario", username);
		String name= ProxyLDAP.getUser(username).getName() + " " + ProxyLDAP.getUser(username).getFirstName();
		sesion.setAttribute("nameuser", name);

		forward = mapping.findForward("inicio");
		return forward;
	}

}
