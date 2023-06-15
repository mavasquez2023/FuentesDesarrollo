/**
 * 
 */
package cl.laaraucana.resultadonrp.threads;

import org.apache.log4j.Logger;

import cl.laaraucana.resultadonrp.business.EnviarConsolidacion;
import cl.laaraucana.resultadonrp.business.EnviarDisponibilizacion;
import cl.laaraucana.resultadonrp.business.GenerarConsolidacion;
import cl.laaraucana.resultadonrp.business.GenerarDisponibilizacion;

/**
 * @author IBM Software Factory
 *
 */
public class DisponibilizacionThread extends Thread {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	public void run() {
		GenerarDisponibilizacion generar= new GenerarDisponibilizacion();
		if(generar.generarResumenDatabase()){
			EnviarDisponibilizacion enviar= new EnviarDisponibilizacion();
			enviar.enviarArchivoDisponibilizacion();
		}
	}
}
