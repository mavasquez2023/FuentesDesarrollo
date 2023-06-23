package cl.araucana.tesoreria.util;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import org.apache.struts.config.ExceptionConfig;


public class KeyExceptionHandler extends
		org.apache.struts.action.ExceptionHandler {

	public static final String TARGET_ERROR = "failure";

	static Logger logger = Logger.getLogger(KeyExceptionHandler.class);
			
	public ActionForward execute(Exception ex, ExceptionConfig config,
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		logger.error(" ****** BEGIN HANDLING EXCEPTION TYPE " + ex.getClass() + " ****** ");
		
		request.setAttribute("error.code", ex.getMessage());
		request.setAttribute("error.stack", com.schema.util.GeneralException.getStackTrace(ex));
		
		/*
		Enumeration enum = request.getAttributeNames();
		while ( enum.hasMoreElements() ) {
			String key = (String) enum.nextElement();
			if ( key.startsWith("error."))
				logger.error(" [" + key +"] " + request.getAttribute( key ));
		}
		*/
		logger.error(" ****** END HANDLING EXCEPTION TYPE " + ex.getClass() + " ****** ");

		return mapping.findForward(TARGET_ERROR);
	}

}
