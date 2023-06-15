package cl.laaraucana.simulacion.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ErrorAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String msg = (String) request.getAttribute("errorMsg");
			if (msg != null && !"".equals(msg)) {
				String array[] = msg.split(";");
				request.setAttribute("titulo", array[0]);
				request.setAttribute("mensaje", array[1]);
			} else {
				request.setAttribute("titulo", "Error Genérico");
			}
		} catch (Exception e) {
			request.setAttribute("titulo", "Error Genérico");
		}
		return mapping.findForward("error");
	}
}
