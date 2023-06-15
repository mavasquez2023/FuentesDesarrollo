package cl.araucana.autoconsulta.ui.actions;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.core.util.http.Utils;


public class LogoutAction extends Action {

	private boolean homePageSearched;
	private String homePage;
	
	public ActionForward execute(ActionMapping mapping,	ActionForm form,
			HttpServletRequest request,	HttpServletResponse response)
					throws Exception {

		ActionForward forward = null;
		HttpSession session = request.getSession();

		/*
		 * Tries to redirect to the source when uc were injected.
		 */
		boolean redirected = false;
		 
		if (session.getAttribute("uc.injected") != null) {
		 	redirected = !Utils.canRedirectToSource(request, response);
		}
		
		if (!redirected) {
			if (!homePageSearched) {
				ServletContext servletContext = getServlet().getServletContext();
				
				homePage = servletContext.getInitParameter("homePage");
				homePageSearched = true;
			}

			/*
			 * Redirects to home page or forward to internal logout.
			 */			
			if (homePage != null) {
				response.sendRedirect(homePage);
			} else {
				forward = mapping.findForward("webLogout");					
			}
		}

		session.invalidate();
		
		return forward;
	}
}
