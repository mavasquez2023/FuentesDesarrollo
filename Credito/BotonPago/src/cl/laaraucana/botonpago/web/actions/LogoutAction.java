package cl.laaraucana.botonpago.web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.botonpago.web.utils.Configuraciones;

public class LogoutAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		//session.removeAttribute("userInfo");
		//session.invalidate();
		//session = null;
		String origen= (String)request.getSession().getAttribute("origen");
		if(origen== null ){
			session.invalidate();
			String url_logout= Configuraciones.getConfig("url.logout.sv");
			response.sendRedirect(url_logout);
		}else{
			response.sendRedirect("ibm_security_logout?logoutExitPage=welcome.do");
		}
		return null;
	}
}
