/**
 * 
 */
package cl.laaraucana.monitoreoLogLME.business;


import java.io.File;
import java.util.Date;

import org.apache.log4j.Logger;

import cl.laaraucana.monitoreoLogLME.config.Configuraciones;
import cl.laaraucana.monitoreoLogLME.services.FormatoCorreo;
import cl.laaraucana.monitoreoLogLME.services.MailService;
import cl.laaraucana.monitoreoLogLME.services.MailServiceImpl;

/**
 * @author J-Factory
 *
 */
public class ServiciosMonitoreoLogLME {
	protected Logger logger = Logger.getLogger(this.getClass());
	private MailService mailService;
	
	public void serviciosMonitoreoLogLME(){
		
		try {
			//Revisar archivo.
			String file_path = Configuraciones.getConfig("crontab.lme.filePath");
			if(file_path != null) {
				long lastModifiedInMillis = new File(file_path).lastModified();

				long diffInMillis = new Date().getTime() - lastModifiedInMillis;

				System.out.println(diffInMillis);
				int MINUTES = 5;
				if(diffInMillis / (MINUTES * 60000) >= 1){
					String correo_to= Configuraciones.getConfig("lme.correo.to.usuario");
					String asunto= Configuraciones.getConfig("lme.asunto.correo");
					logger.info("Enviando mail sobre problema en app LTE a " + correo_to);
					MailService mailService= new MailServiceImpl();
					mailService.sendEmail(correo_to, asunto,
							FormatoCorreo.getMailbody());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.warn("Error, mensaje" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
}
