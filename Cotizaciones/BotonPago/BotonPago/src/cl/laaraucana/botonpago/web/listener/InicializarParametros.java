package cl.laaraucana.botonpago.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.utils.Configuraciones;
import cl.laaraucana.botonpago.web.utils.Constantes;

/**
 * Application Lifecycle Listener implementation class InicializarParametros
 *
 */
@WebListener
public class InicializarParametros implements ServletContextListener {
	protected Logger logger = Logger.getLogger(this.getClass());

	
	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			logger.debug("******************************************************************************");
			logger.debug("Se inicia la aplicacion con version: " + Configuraciones.getMainConfig("version"));
			logger.debug("******************************************************************************");
			Constantes.getInstancia();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
