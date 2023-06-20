package cl.laaraucana.silmsil.actions;

import java.security.Principal;

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

import com.sun.java.swing.plaf.windows.resources.windows;

/**
 * @version 	1.0
 * @author
 */
public class LoginOutAction extends Action
{
	private Logger log = Logger.getLogger(this.getClass());
	
	
    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception 
	{
		ActionForward forward = new ActionForward(); // return value
	
		try {
			HttpSession sesion=request.getSession(true);
			String login = (String)sesion.getAttribute("login");
			System.out.println("Login = " + login);
			
			Principal userPrincipal = request.getUserPrincipal();
			String user =  userPrincipal.getName();
			
			if(user.length()!=0){
				userPrincipal=null;
			}
			
			if(sesion!=null){				
				request.getSession().removeAttribute("login");			
				sesion.invalidate();
				log.info("* * session: "+sesion.toString());
				sesion=null;
				log.info("* * session null: "+sesion);
			}else{				
				sesion=null;
			}
			
			//forward = mapping.findForward("successCloseLogin");
			response.sendRedirect("ibm_security_logout");
		} catch (Exception e) {
			e.printStackTrace();
		}
	// Finish with
	return null;
    }
}
