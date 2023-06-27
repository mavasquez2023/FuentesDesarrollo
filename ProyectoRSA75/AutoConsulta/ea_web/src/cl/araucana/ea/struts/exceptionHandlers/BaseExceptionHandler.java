/*
 * @(#)BaseExceptionHandler	0.9	2005/07/10	
 *
 * Derecho reservado 2005 C.C.A.F. La Araucana
 * Propiedad de C.C.A.F. La Araucana
 */
package cl.araucana.ea.struts.exceptionHandlers;

import org.apache.log4j.Logger;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;
import org.apache.struts.util.MessageResources;


/**
 * La clase <strong>BaseExceptionHandler</strong> es la planilla para otras clases.
 * Reemplazar la implementacion del metodo execute..
 * 
 * Seleccione una de las impelmentaciones de logger. 
 * 
 * @author Jin K. Lee
 * @version $Revision: 0.9 $ $Date: 2005/07/10 06:16:34 $
 */
public class BaseExceptionHandler extends ExceptionHandler {

	/*
	 *	Application logger.
	 */
	protected static Logger log = Logger.getLogger(ServiceUnavailableExceptionHandler.class);

	/**
	 * 
	 */
	public BaseExceptionHandler() {
		super();
	}

	
	/* 
	 * @see org.apache.struts.action.ExceptionHandler#execute(java.lang.Exception, org.apache.struts.config.ExceptionConfig, org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(java.lang.Exception ex,
				 ExceptionConfig ae,
				 ActionMapping mapping,
				 ActionForm formInstance,
				 javax.servlet.http.HttpServletRequest request,
				 javax.servlet.http.HttpServletResponse response)
		  throws javax.servlet.ServletException {
	
		/*
		 * 	1. log error message
		 * 	2. get error handler page
		 * 	3. redispatch
		 */

		log.fatal(print(ex));
		if(log.isDebugEnabled()) {
			log.debug("", ex);
		}

		// alternative message resource is used (declared in servlet context), 
		// message is identified by key, an property of global exception.
		MessageResources mr = (MessageResources) request.getSession().getServletContext().getAttribute("ExceptionResources");
		String msg = mr.getMessage(ae.getKey());
		
		// default message resource may be used
		// error message can be added in action error and referenced with <html:errors />
		ActionErrors errors = new ActionErrors();
		errors.add(ae.getBundle(), new ActionError(ae.getKey(), "arg1", "arg2"));
		request.setAttribute(Globals.ERROR_KEY, errors);
		
		// get forward defined in global exception with path property.
		// the value od getPath() should be defined either in the global forward or in the action forward 
		ActionForward forward = new ActionForward();
		forward = mapping.findForward(ae.getPath());

		// if failed, use the application default error page, defined in global forward
		// the defaultError must have value..
		if (forward == null) {
			forward = mapping.findForward("defaultError");
		}

		return forward;
	}
	
	/**
	 * print formatted exception message.
	 * 
	 * @param ex Exception
	 * @return formatted message
	 */
	public String print(java.lang.Exception ex) {
		int level = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("\r\nHA OCURRIDO UN ERROR CRITICO...");
		sb.append("\r\n").append(level++).append(":");
		sb.append("\r\n\texception: ").append(ex.getClass().getName());
		sb.append(ex.getMessage());

		Throwable _t = ex.getCause();
		
		if(_t != null) {
			while (_t.getCause() != null) {
				sb.append("\r\n").append(level++).append(":");
				sb.append("\r\n\texception: ").append(_t.getClass().getName());
				sb.append(_t.getMessage());
				_t = _t.getCause();
			}
			sb.append("\r\n");
			sb.append("\r\nError original").append(":");
			sb.append("\r\n\texception: ").append(_t.getClass().getName());
			sb.append("\r\n\t").append(_t.getMessage());

			sb.append("\r\n\r\n======= Stack Trace ==========");
			StackTraceElement[] st = _t.getStackTrace();
			for (int i = 0; i < st.length; i++) {
				sb.append("\r\n").append(st[i]);
			}
			sb.append("\r\n======= END ==========");
			sb.append("\r\n");	
		}
		
		return sb.toString();	
	}	
}
