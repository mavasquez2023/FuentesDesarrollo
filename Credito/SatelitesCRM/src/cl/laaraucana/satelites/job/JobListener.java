package cl.laaraucana.satelites.job;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.SchedulerException;


public class JobListener implements ServletContextListener{
	private static Logger logger = Logger.getLogger(JobListener.class.getName());
	
	public static void main(String[] args) throws SchedulerException, ParseException {
		Trigger.getInstance().start();
	}
	
	
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			Trigger.getInstance().start();
			System.out.print("Estado del Job: '");
			System.out.print(Trigger.getInstance().getStatus());
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
			Trigger.getInstance().stop();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error al terminar Job: ", e);
		}
	}
}