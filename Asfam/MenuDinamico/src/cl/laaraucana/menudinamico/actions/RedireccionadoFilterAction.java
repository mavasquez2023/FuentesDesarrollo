package cl.laaraucana.menudinamico.actions;

import java.security.Principal;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import cl.araucana.core.registry.UserRegistryConnection;
import cl.laaraucana.menudinamico.forms.MenuPrincipal_Form;

/**
 * Clase Actions Struts para manejo de redireccionamiento de esta aplicación a otras 
 * aplicaciones que cuentan con la misma configuración de filter.
 * @version 	1.0
 * @author
 */
public class RedireccionadoFilterAction extends Action
{
	private Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * Actions para manejo de redireccionamiento a aplicaciones que estarán conectadas a 
	 * está aplicación.
	 */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception 
	{
    	log.info(" ---- Ingreso a RedireccionadoFilterAction.execute ----");
    	
    	MenuPrincipal_Form forms =  (MenuPrincipal_Form)form;
		ActionForward forward = new ActionForward(); // return value
		try {
			Principal principal = request.getUserPrincipal();
			UserRegistryConnection connection = null;
			connection = new UserRegistryConnection();
			String userID = principal.getName();
			connection.setUserID(userID);
			
			log.info("user id = " + userID);
			
			HttpSession session = request.getSession();
			session.invalidate();
			
			//Obtiene dirección de aplicación ubicada en archivo properties por SV.
			String svURL = ResourceBundle.getBundle("etc.services").getString("DEFAULT");
			
			response.sendRedirect(svURL);
			
		} catch (Exception ex) {
			log.error("Error, RedireccionadoFilterAction.execute: \n " + ex.getMessage());
			ex.printStackTrace();
		}
	// Finish with
	return (forward);
    }
}