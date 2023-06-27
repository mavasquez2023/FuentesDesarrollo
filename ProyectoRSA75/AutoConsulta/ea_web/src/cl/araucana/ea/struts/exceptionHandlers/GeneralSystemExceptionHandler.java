/*
 * Created on Mar 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package cl.araucana.ea.struts.exceptionHandlers;

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
public class GeneralSystemExceptionHandler extends ExceptionHandler {
	public ActionForward execute(java.lang.Exception ex,
								 ExceptionConfig ae,
								 ActionMapping mapping,
								 ActionForm formInstance,
								 javax.servlet.http.HttpServletRequest request,
								 javax.servlet.http.HttpServletResponse response)
						  throws javax.servlet.ServletException {
		ActionForward forward = new ActionForward();
				
		// primero ocupamos destino definido en action
		forward = mapping.findForward("profile");
		
		// si action no tiene pagina de retorno o la definiion no encuentra
		// ocupamos destino predeterminado en Global-Exceptions 
		if(forward == null) {
			String path = ae.getPath();
			forward = mapping.findForward(ae.getPath());
		}
		return forward;					  	
	}
}
