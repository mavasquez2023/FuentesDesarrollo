
/*
 * @(#) EDocsServletContextListener.java    1.0 30-10-2006
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */
 

package cl.araucana.ea.edocs.listeners;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cl.araucana.ea.edocs.DocumentController;
import cl.araucana.ea.edocs.logging.LogManager;
import cl.araucana.ea.edocs.logging.Logger;


/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 30-10-2006 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class EDocsServletContextListener implements ServletContextListener {

	private Logger logger;
	private DocumentController documentController;
	
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		String applicationName =
				servletContext.getInitParameter("applicationName");

		String logDir = servletContext.getInitParameter("edocs.logDir");
				
		LogManager.APP_NAME = applicationName;
		LogManager.LOG_DIR = logDir;
		
		logger = new Logger(applicationName);
		
		logger.log("***  APPLICATION STARTED  ***");
		
		String documentBaseDir =
				servletContext.getInitParameter("edocs.documentBaseDir");
						
		documentController = DocumentController.getInstance(documentBaseDir);				
	}

	public void contextDestroyed(ServletContextEvent event) {
		LogManager logManager = LogManager.getInstance();		

		if (logger != null) {
			logger.log("***  APPLICATION ENDED  ***");
		}
		
		logManager.close();				
	}
}
