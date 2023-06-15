package cl.araucana.tupla2.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

public class PrepareValidacionAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse arg3) throws Exception {

		ActionForward forward = new ActionForward();
		ActionMessages errors = new ActionMessages();
		String mensaje = null;

		System.out.println("aqui");

		if (!errors.isEmpty()) {

			request.setAttribute("mensaje", mensaje);
			forward = mapping.findForward("onError");
		} else {

			forward = mapping.findForward("onSuccess");
		}

		return forward;

	}

}
