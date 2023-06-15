/**
 * 
 */
package cl.laaraucana.resultadonrp.threads;

import org.apache.log4j.Logger;

import cl.laaraucana.resultadonrp.business.EnviarConsolidacion;
import cl.laaraucana.resultadonrp.business.GenerarConsolidacion;

/**
 * @author IBM Software Factory
 *
 */
public class ConsolidacionThread extends Thread {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	public void run() {
		GenerarConsolidacion generar= new GenerarConsolidacion();
		if(generar.generarResumenDatabase()){
			EnviarConsolidacion enviar= new EnviarConsolidacion();
			enviar.enviarArchivoConsolidacion();
		}
	}
}
