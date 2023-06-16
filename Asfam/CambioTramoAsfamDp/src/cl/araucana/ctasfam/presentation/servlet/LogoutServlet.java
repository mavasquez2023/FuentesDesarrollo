package cl.araucana.ctasfam.presentation.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogoutServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LogoutServlet.class);

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			log.error("Error: Al salir del aplicativo."
					+ e.getLocalizedMessage(), e);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			log.error("Error: Al salir del aplicativo."
					+ e.getLocalizedMessage(), e);
		}
	}

	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		//String  logoutURL= "ibm_security_logout?logout=Logout&logoutExitPage=" + "http://rasw.laaraucana.cl/sv/router.do?service=DEFAULT";
		String  logoutURL= "ibm_security_logout?logout=Logout&logoutExitPage=/logon.jsp";
		response.sendRedirect(response.encodeURL(logoutURL));
	}
}
