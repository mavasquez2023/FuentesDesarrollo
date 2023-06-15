/**
 * 
 */
package cl.laaraucana.resultadonrp.threads;

import org.apache.log4j.Logger;

import cl.laaraucana.resultadonrp.business.EnviarConsolidacion;
import cl.laaraucana.resultadonrp.business.EnviarGeneracion;
import cl.laaraucana.resultadonrp.business.GenerarConsolidacion;
import cl.laaraucana.resultadonrp.business.GenerarGeneracion;

/**
 * @author IBM Software Factory
 *
 */
public class GeneracionThread extends Thread {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	public void run() {
		GenerarGeneracion generar= new GenerarGeneracion();
		EnviarGeneracion enviar= new EnviarGeneracion();
		if(generar.generarResumenDatabase()){
			enviar.enviarArchivoGeneracion();
		}else{
			enviar.informarErrorGeneracion();
		}
	}
}
