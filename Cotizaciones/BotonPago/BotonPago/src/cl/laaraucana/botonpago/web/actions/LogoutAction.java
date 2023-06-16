package cl.laaraucana.botonpago.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LogoutAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//HttpSession session = request.getSession();
		//session.removeAttribute("userInfo");
		//session.invalidate();
		//session = null;
		response.sendRedirect("ibm_security_logout?logoutExitPage=welcome.do");
		return null;
	}
}
