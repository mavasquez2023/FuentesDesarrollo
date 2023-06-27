package cl.araucana.clientewsfonasa.common.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cl.araucana.clientewsfonasa.business.services.MonitorCallWSFonasaService;
import cl.araucana.clientewsfonasa.business.services.impl.MonitorCallWSFonasaServiceImpl;

public class MonitorCallWSFonasaListener implements ServletContextListener{
	public void contextInitialized(ServletContextEvent event) {
		MonitorCallWSFonasaService monitor = new MonitorCallWSFonasaServiceImpl("HILO_MONITOR");  
		monitor.start();
	}

    public void contextDestroyed(ServletContextEvent event) {
    	MonitorCallWSFonasaService monitor = new MonitorCallWSFonasaServiceImpl();
    	monitor.stopMonitor();
	}
}
