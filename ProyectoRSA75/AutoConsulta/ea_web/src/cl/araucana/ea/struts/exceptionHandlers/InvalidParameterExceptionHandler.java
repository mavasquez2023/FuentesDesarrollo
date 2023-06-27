/*
 * @(#)InvalidParameterExceptionHandler	0.9	2005/07/10	
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
 * La clase <strong>InvalidParameterExceptionHandler</strong> procesa la InvalidParameterException.
 * Se define por la compatibilidad y no tiene efecto en esta aplicacion.
 * Emplear el mecanismo de validacion ofrecida por struts framework
 * 
 * @author Jin K. Lee
 * @version $Revision: 0.9 $ $Date: 2005/07/10 06:16:34 $
 */
public class InvalidParameterExceptionHandler extends BaseExceptionHandler {

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
		if(forward == null) {
			forward = mapping.findForward("defaultError");
		}
		return forward;	  	
	}
}