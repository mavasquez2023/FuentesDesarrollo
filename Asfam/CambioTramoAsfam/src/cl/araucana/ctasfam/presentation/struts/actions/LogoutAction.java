package cl.araucana.ctasfam.presentation.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cl.araucana.core.util.http.Utils;

public class LogoutAction extends Action {
	public ActionForward execute(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest arg2, HttpServletResponse arg3) throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = null;
		HttpSession session = arg2.getSession();
		/*
		 * Redirects to the source when uc were injected.
		 */
		if (session.getAttribute("uc.injected") == null) {
			forward = arg0.findForward("restart");
		} else if (!Utils.canRedirectToSource(arg2, arg3)) {
				forward = arg0.findForward("restart");
		}
		
		// Invalidates current session.
		try {
			session.removeAttribute("ea_user_profile");
			session.invalidate();
			arg3.sendRedirect("ibm_security_logout?logoutExitPage=index.jsp");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionMessage("id"));
		}
		
		return forward;
	}
}