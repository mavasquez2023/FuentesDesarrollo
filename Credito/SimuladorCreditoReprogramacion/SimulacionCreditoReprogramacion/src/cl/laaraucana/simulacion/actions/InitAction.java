package cl.laaraucana.simulacion.actions;

import java.security.Principal;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



public class InitAction extends Action {
	private static Logger logger = Logger.getLogger("InitAction");
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward(); 
		HttpSession sesion = request.getSession();
		sesion = request.getSession();

		//if(sesion.getAttribute("empresas")==null){
			
			Principal principal = request.getUserPrincipal();
			String username= principal.getName();
			sesion.setAttribute("nameuser", username);
			
			//String name= ProxyLDAP.getUser(username).getName() + " " + ProxyLDAP.getUser(username).getFirstName();
			//sesion.setAttribute("nameuser", name);
			
			
		//}
		forward = mapping.findForward("inicio");
		return forward;
	}

}
