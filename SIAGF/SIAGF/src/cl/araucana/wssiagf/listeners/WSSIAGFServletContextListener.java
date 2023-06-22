

package cl.araucana.wssiagf.listeners;


import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cl.araucana.core.util.logging.LogManager;

import cl.araucana.wssiagf.WSSIAGFConnectorConfig;
import cl.araucana.wssiagf.WSSIAGFConnectorLauncher;
import cl.araucana.wssiagf.WSSIAGFException;


public class WSSIAGFServletContextListener implements ServletContextListener {

	private static Logger logger = LogManager.getLogger();

	private WSSIAGFConnectorLauncher launcher;

	public void contextInitialized(ServletContextEvent event) {
		
		logger.info("WSSIAGF Connector: *** STARTED ***");

		launcher = new WSSIAGFConnectorLauncher();

		try {
			launcher.loadWSSIAGFConfiguration();
		} catch (WSSIAGFException e) {
			logger.log(
					Level.SEVERE,
					">< WSSIAGF connector cannot be initialized:",
					e);

			launcher = null;

			return;
		}

		WSSIAGFConnectorConfig config = launcher.getConfig();
		ServletContext servletContext = event.getServletContext();

		servletContext.setAttribute("wssiagf.connector.config", config);

		if (config.isDB2InterfaceEnabled()) {
			launcher.startDB2Interface();
		} else {
			logger.warning("DB/2 interface is disabled.");
		}

		if (!config.isHTTPInterfaceEnabled()) {
			logger.warning("HTTP WSInterface disabled.");
		}
	}

    public void contextDestroyed(ServletContextEvent event) {
		if (launcher != null) {
			WSSIAGFConnectorConfig config = launcher.getConfig();

			if (config.isDB2InterfaceEnabled()) {
				launcher.stopDB2Interface();
			}
		}

		logger.info("WSSIAGF Connector: *** ENDED ***");

		LogManager.close();
	}
}
