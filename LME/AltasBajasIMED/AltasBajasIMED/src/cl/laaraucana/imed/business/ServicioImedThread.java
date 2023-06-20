/**
 * 
 */
package cl.laaraucana.imed.business;

import org.apache.log4j.Logger;

/**
 * @author IBM Software Factory
 *
 */
public class ServicioImedThread extends Thread{
	private static Logger log = Logger.getLogger(ServicioImedThread.class);
	private String tipo;
	
	public ServicioImedThread(String tipo) {
		this.tipo= tipo;
	}
	
	public void run() {
	    log.info("Running thread Servicio Imed");
	    ServiciosImed servicio= new ServiciosImed();
		servicio.serviciosImed(tipo);
		log.info("fin Carga IMED");	
	  }
}
