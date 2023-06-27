

/*
 * @(#) BonifListener.java    1.0 21-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.bienestar.bonificaciones.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import cl.araucana.common.env.AppConfig;


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
 *            <TD> 21-07-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Fabrizio Barisione Biso <BR> fbarisione@laaraucana.cl </TD>
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
 * @author Fabrizio Barisione Biso (fbarisione@laaraucana.cl)
 *
 * @version 1.0
 */
public class BonifListener implements ServletContextListener {
	Logger logger = Logger.getLogger(BonifListener.class);
	
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("<<BonifListener");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		logger.info(">>BonifListener");
		
		AppConfig a = AppConfig.getInstance();
		a.appInit("arau-settings.xml","arau-errors.properties");
		
		//bonifDaemon = new BonifDaemon();
		//bonifDaemon.start();
	}

}

