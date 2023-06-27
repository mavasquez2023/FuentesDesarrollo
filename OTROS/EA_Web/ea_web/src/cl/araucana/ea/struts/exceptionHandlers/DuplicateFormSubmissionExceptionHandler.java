/*
 * @(#)DuplicateFormSubmissionExceptionHandler	0.9	2005/07/10	
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
 * La clase <strong>DuplicateFormSubmissionExceptionHandler</strong> procesa la DuplicationFormSubmissionException.
 * Este error es capturado cuando se sumite mismo formulario dos veces.
 * 
 * Seleccione una de las impelmentaciones de logger. 
 * 
 * @author Jin K. Lee
 * @version $Revision: 1.1 $ $Date: 2011/10/11 19:57:44 $
 */
public class DuplicateFormSubmissionExceptionHandler extends BaseExceptionHandler {

	public ActionForward execute(java.lang.Exception ex,
				 ExceptionConfig ae,
				 ActionMapping mapping,
				 ActionForm formInstance,
				 javax.servlet.http.HttpServletRequest request,
				 javax.servlet.http.HttpServletResponse response)
		  throws javax.servlet.ServletException {
	
		//	this exception does not fall into fatal error.
		// log.fatal(print(ex));		
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
