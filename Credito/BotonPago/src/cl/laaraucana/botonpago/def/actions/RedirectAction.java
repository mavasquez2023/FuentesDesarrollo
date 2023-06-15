package cl.laaraucana.botonpago.def.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * @author LaAraucana
 * Redirecciona a login
 */
public class RedirectAction extends Action {
	Logger logger = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm _form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//Termina la sesión y redirecciona a sistema invocador
		logger.debug("Cerrando sesion, redireccionando a login" );
		response.sendRedirect("ibm_security_logout?logoutExitPage=login.jsp");
		return null;
	}
}
