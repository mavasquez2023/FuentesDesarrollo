/**
 * 
 */
package cl.laaraucana.mandato.quartz;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import cl.laaraucana.mandato.services.ArchivoSAPService;
import cl.laaraucana.mandato.services.FTPService;

/**
 * @author J-Factory
 *
 */
public class AsyncEnvioArchivo {
	private static final Logger logger = Logger.getLogger(AsyncEnvioArchivo.class);
	
	@Autowired
	ArchivoSAPService archivoSAP;
	
	@Autowired
	FTPService ftpService;
	

	@Scheduled(cron = "${cronExpression}")
	public void enviarArchivoSAP() {
		logger.info("Cron Archivo SAP Mandato starting...");
		try {

			
			logger.info("Enviando a SAP archivo de Vigentes");
			//Genrar CSV Vigentes
			String fileSAP= archivoSAP.generaArchivoVigentes();
			
			if(fileSAP!=null){
				logger.info("Conectando a ftp");
				ftpService.connectToFTP();
				logger.info("Enviando archivo a ftp");
				ftpService.putFileToFTP(fileSAP);
				logger.info("Desconectando a ftp");
				ftpService.disconnectFTP();
				
			}
		}catch(Exception e){
			logger.error("Error en Cron Archivo SAP Mandato, mensaje=" + e.getMessage());
			e.printStackTrace();
		}
		try{
			logger.info("Enviando a SAP archivo de Revocados");
			//Genrar CSV Revocados
			String fileSAP_Revo= archivoSAP.generaArchivoRevocados();

			if(fileSAP_Revo!=null){
				logger.info("Conectando a ftp");
				ftpService.connectToFTP();
				logger.info("Enviando archivo a ftp");
				ftpService.putFileToFTP(fileSAP_Revo);
				logger.info("Desconectando a ftp");
				ftpService.disconnectFTP();
			}

		}catch(Exception e){
			logger.error("Error en Cron Archivo SAP Mandato, mensaje=" + e.getMessage());
			e.printStackTrace();
		}
		logger.info("Cron Archivo SAP Mandato ends...");
	}
}
