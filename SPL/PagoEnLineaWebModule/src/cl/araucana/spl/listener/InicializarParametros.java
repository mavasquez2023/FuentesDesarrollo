package cl.araucana.spl.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cl.araucana.spl.util.Constantes;

public class InicializarParametros implements ServletContextListener {
//	protected Logger logger = Logger.getLogger(this.getClass());

	public InicializarParametros() {
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {
//			logger.info("*********************************");
//			logger.info("   Se inicia la aplicacion SPL");
//			logger.info("*********************************");
			Constantes.getInstancia();
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("ERROR AL CARGAR LOS PARAMETROS DE INICIO.");
		}
	}

}
