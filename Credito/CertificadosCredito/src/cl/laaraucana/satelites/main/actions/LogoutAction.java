package cl.laaraucana.satelites.main.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author
 */
public class LogoutAction extends Action

{
	protected Logger logger = Logger.getLogger(this.getClass());
    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
    	
	ActionForward forward = new ActionForward(); // return value
	try {
		HttpSession sesion = request.getSession(true);		
		sesion.invalidate();
		//session = null;
		response.sendRedirect("ibm_security_logout?logoutExitPage=/main/Welcome.do");
	} catch (Exception e) {
		logger.error(e.getMessage());
		forward = mapping.findForward("error");

	}
	return (forward);

    }
}
