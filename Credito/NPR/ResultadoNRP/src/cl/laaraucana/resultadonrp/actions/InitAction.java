package cl.laaraucana.resultadonrp.actions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.Principal;
import java.util.Map;


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
		Map<String, String> listamap=null;
		String rolUsuario="Ejecutivo";
		//if(sesion.getAttribute("empresas")==null){
			
			//Principal principal = request.getUserPrincipal();
			//String username= principal.getName();
			String username="Admin";
			sesion.setAttribute("usuario", username);
			//String name= ProxyLDAP.getUser(username).getName() + " " + ProxyLDAP.getUser(username).getFirstName();
			sesion.setAttribute("nameuser", username);

			logger.info("Rol usuario:" + rolUsuario);
			sesion.setAttribute("rol", rolUsuario);
			request.setAttribute("error", "0");
			
		//}
		forward = mapping.findForward("success");
		return forward;
	}

}
