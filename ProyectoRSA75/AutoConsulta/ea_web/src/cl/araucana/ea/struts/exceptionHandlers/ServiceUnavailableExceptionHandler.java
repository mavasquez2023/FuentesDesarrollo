/*
 * @(#)ServiceUnavailableExceptionHandler	0.9	2005/07/10	
 *
 * Derecho reservado 2005 C.C.A.F. La Araucana
 * Propiedad de C.C.A.F. La Araucana
 */
package cl.araucana.ea.struts.exceptionHandlers;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

/**
 * @author jlee
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ServiceUnavailableExceptionHandler extends BaseExceptionHandler {


	public ActionForward execute(
			java.lang.Exception ex,
			ExceptionConfig ae,
			ActionMapping mapping,
			ActionForm formInstance,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
		throws javax.servlet.ServletException {

		log.fatal(print(ex));
		if(log.isDebugEnabled()) {
			log.debug("Debugging information...", ex);
		}
	
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(ae.getPath());
		if (forward == null) {
			forward = mapping.findForward("defaultError");
		}

		return forward;
	}
}