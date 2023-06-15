package cl.laaraucana.compromisototal.main.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @version 1.0
 * @author
 */
public class ErrorAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward(); // return value
		try {
			String msg = request.getParameter("errorMsg");
			if (msg != null && !"".equals(msg)) {
				request.setAttribute("msg", msg);
			} else {
				request.setAttribute("msg", "Error Gen�rico");
			}
		} catch (Exception e) {
			request.setAttribute("msg", "Error Gen�rico");
		}
		forward = mapping.findForward("go");
		return (forward);
	}
}