package cl.laaraucana.menudinamico.actions;

import java.util.ArrayList;

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
import org.apache.struts.actions.DispatchAction;

/**
 * @version 1.0
 * @author
 */
public class LoginOutAction extends Action {

	private Logger log = Logger.getLogger(this.getClass());
	private String msgListNull = null;

	/**
	 * Actions de validación de usuario.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
		
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.info(" ---- Ingreso a cerrarSesion ---- ");
		ActionForward forward = new ActionForward(); // return value
		try {
			HttpSession sesionActual=request.getSession(true);
						
			if(sesionActual!=null){				
				request.getSession().removeAttribute("claveInicial");
				request.getSession().removeAttribute("claveNueva");	
				request.getSession().removeAttribute("login");	
				
				sesionActual.invalidate();
				log.info("* * session: "+sesionActual.toString());
				sesionActual=null;
				log.info("* * session null: "+sesionActual);
			}else{				
				sesionActual=null;
			}
			
			
			//forward = mapping.findForward("successCloseLogin");
			response.sendRedirect("ibm_security_logout?logoutExitPage=login.jsp");
		} catch (Exception ex) {
			log.error("Error, cerrarSesion : \n " + ex.getMessage());				
			//forward = mapping.findForward("successCloseLogin");
		}
		// Finish with
		//return (forward);
		return null;
	}//end cerrarSesion
	
}
