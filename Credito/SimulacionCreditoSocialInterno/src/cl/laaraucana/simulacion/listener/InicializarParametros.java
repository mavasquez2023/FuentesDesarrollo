package cl.laaraucana.simulacion.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cl.laaraucana.simulacion.utils.ConstantesFormalizar;

/**
 * Application Lifecycle Listener implementation class InicializarParametros
 *
 */
@WebListener
public class InicializarParametros implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public InicializarParametros() {
    	try {
    		ConstantesFormalizar.getOficinas();
			ConstantesFormalizar.getRegionesComunas();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    }
	
}
