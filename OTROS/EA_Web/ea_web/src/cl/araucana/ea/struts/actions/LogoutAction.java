

package cl.araucana.ea.struts.actions;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.araucana.core.util.http.Utils;


public class LogoutAction extends Action {

	public ActionForward execute(ActionMapping mapping,	ActionForm form,
			HttpServletRequest request,	HttpServletResponse response)
					throws Exception {

			ActionErrors errors = new ActionErrors();
			ActionForward forward = null;
			HttpSession session = request.getSession();

			/*
			 * Redirects to the source when uc were injected.
			 */
			if (session.getAttribute("uc.injected") == null) {
				forward = mapping.findForward("restart");
			} else if (!Utils.canRedirectToSource(request, response)) {
					forward = mapping.findForward("restart");
			}

			// Invalidates current session.
			try {
				session.invalidate();
			} catch (Exception e) {

				// Report the error using the appropriate name and ID.
				errors.add("name", new ActionError("id"));
			}

			// If a message is required, save the specified key(s)
			// into the request for use by the <struts:errors> tag.
			if (!errors.isEmpty()) {
				saveErrors(request, errors);
			}

			// Finish with
			return forward;
	}
}
