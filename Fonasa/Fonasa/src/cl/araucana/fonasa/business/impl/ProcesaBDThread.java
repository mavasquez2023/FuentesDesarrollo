/**
 * 
 */
package cl.araucana.fonasa.business.impl;


import org.apache.log4j.Logger;


/**
 * @author IBM Software Factory
 *
 */
public class ProcesaBDThread extends Thread{
	protected Logger logger = Logger.getLogger(this.getClass());

	public void run(){
		logger.info(getName()+" iniciando Thread.");

		try {
			//Validar Formularios FONASA desde BD
			ConsultaBD consulta= new ConsultaBD();
			consulta.validaBD();
			
			
		}catch (Exception exc){
			logger.error(getName()+ " interrumpido." + exc.getMessage());
			exc.printStackTrace();
		}
		logger.info(getName()+ "finalizando.");
	}
	

}
