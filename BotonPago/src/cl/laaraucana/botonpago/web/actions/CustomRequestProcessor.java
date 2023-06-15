package cl.laaraucana.botonpago.web.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.RequestProcessor;

public class CustomRequestProcessor extends RequestProcessor {
	@Override
	protected boolean processRoles(HttpServletRequest request, HttpServletResponse response, ActionMapping mapping)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		String rol = (String) session.getAttribute("rol");
		if (mapping.getRoleNames().length == 0) {
			return true;
		}
		for (String as : mapping.getRoleNames()) {
			if (rol != null && as.equals(rol)) {
				return true;
			}
		}
		response.sendRedirect(request.getContextPath() + "/web/accesoDenegado.jsp");
		return false;
	}
}
