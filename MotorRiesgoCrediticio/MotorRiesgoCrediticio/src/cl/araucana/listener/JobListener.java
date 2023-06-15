package cl.araucana.listener;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.SchedulerException;

import cl.araucana.job.EquifaxTrigger;

public class JobListener implements ServletContextListener{
	private static Logger logger = Logger.getLogger(JobListener.class.getName());
	
	public static void main(String[] args) throws SchedulerException, ParseException {
		EquifaxTrigger.getInstance().start();
	}
	
	
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			EquifaxTrigger.getInstance().start();
			System.out.print("Estado del Job: '");
			System.out.print(EquifaxTrigger.getInstance().getStatus());
			System.out.print("'");
		} catch (Exception e) {
			//logger.log(Level.SEVERE, "Error al iniciar Job: ", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Termina la ejecución del job cuando se detiene la aplicación
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			System.out.println("Se terminará la ejecución del job");
			EquifaxTrigger.getInstance().stop();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error al terminar Job: ", e);
		}
	}
}