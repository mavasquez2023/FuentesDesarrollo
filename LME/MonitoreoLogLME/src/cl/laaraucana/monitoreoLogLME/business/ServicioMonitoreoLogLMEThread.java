/**
 * 
 */
package cl.laaraucana.monitoreoLogLME.business;

import org.apache.log4j.Logger;

/**
 * @author IBM Software Factory
 *
 */
public class ServicioMonitoreoLogLMEThread extends Thread{
	private static Logger log = Logger.getLogger(ServicioMonitoreoLogLMEThread.class);
	private String tipo;
	
	public ServicioMonitoreoLogLMEThread(String tipo) {
		this.tipo= tipo;
	}
	
	public void run() {
	    log.info("Running thread Servicio Monitoreo Log LME");
	    ServiciosMonitoreoLogLME servicio= new ServiciosMonitoreoLogLME();
		servicio.serviciosMonitoreoLogLME();
		log.info("fin Monitoreo Log LME");	
	  }
}
