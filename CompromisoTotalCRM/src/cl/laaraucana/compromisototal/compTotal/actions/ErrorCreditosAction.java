package cl.laaraucana.compromisototal.compTotal.actions;

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
public class ErrorCreditosAction extends Action

{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward(); // return value
		try {
			String colspan = request.getParameter("colspan");
			String msg = request.getParameter("errorMsg");
			if (msg != null && !"".equals(msg)) {
				request.setAttribute("colspan", colspan);
				request.setAttribute("msg", msg);
			} else {
				request.setAttribute("colspan", "1");
				request.setAttribute("msg", "Error Genérico");
			}
		} catch (Exception e) {
			request.setAttribute("colspan", "1");
			request.setAttribute("msg", "Error Genérico");
		}
		forward = mapping.findForward("disp");
		return (forward);
	}
}