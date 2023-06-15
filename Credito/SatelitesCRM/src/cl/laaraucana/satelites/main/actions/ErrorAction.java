package cl.laaraucana.satelites.main.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ErrorAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward(); // return value

		if (request.getParameter("tipo") != null && request.getParameter("tipo").equals("custom")) {
			request.setAttribute("title", request.getAttribute("title"));
			request.setAttribute("message", request.getAttribute("message"));
			
			return mapping.findForward("custom");
		}

		try {
			String msg = request.getParameter("errorMsg");
			if (msg != null && !"".equals(msg)) {
				request.setAttribute("msg", msg);
			} else {
				request.setAttribute("msg", "Error Genérico");
			}
		} catch (Exception e) {
			request.setAttribute("msg", "Error Genérico");
		}
		forward = mapping.findForward("go");
		return (forward);
	}
}
