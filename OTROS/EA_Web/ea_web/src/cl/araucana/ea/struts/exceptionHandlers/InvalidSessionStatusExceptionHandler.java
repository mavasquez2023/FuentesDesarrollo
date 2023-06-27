/*
 * @(#)InvalidSessionStatusExceptionHandler	0.9	2005/07/10	
 *
 * Derecho reservado 2005 C.C.A.F. La Araucana
 * Propiedad de C.C.A.F. La Araucana
 */
package cl.araucana.ea.struts.exceptionHandlers;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

/**
 * La clase <strong>InvalidSessionStatusExceptionHandler</strong> procesa la InvalidSessionStatusException.
 * Este error es capturado cuando la session de usuario ya no es valaido.
 * 
 * @author Jin K. Lee
 * @version $Revision: 1.1 $ $Date: 2011/10/11 19:57:44 $
 */
public class InvalidSessionStatusExceptionHandler extends ExceptionHandler {

	public ActionForward execute(java.lang.Exception ex,
								 ExceptionConfig ae,
								 ActionMapping mapping,
								 ActionForm formInstance,
								 javax.servlet.http.HttpServletRequest request,
								 javax.servlet.http.HttpServletResponse response)
						  throws javax.servlet.ServletException {
		
		/*
		 * Not implemented.
		 */
		ActionForward forward = new ActionForward();				
		forward = mapping.findForward(ae.getPath());

		// if forward page is not able to be determined, force to logout						
		if(forward == null) {
			forward = mapping.findForward("profile");
		}
		return forward;					  	
	}
}