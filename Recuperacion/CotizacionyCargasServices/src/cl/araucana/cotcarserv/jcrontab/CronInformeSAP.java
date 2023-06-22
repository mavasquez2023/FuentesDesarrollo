package cl.araucana.cotcarserv.jcrontab;


import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cl.araucana.cotcarserv.servlets.ArchivoSAP;



public class CronInformeSAP implements Job{
	private static Logger log = Logger.getLogger(CronInformeSAP.class);
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		String mailerror="";
		try {
			
			log.info("iniciando cronta Envio SAP");
			ArchivoSAP envioSAP= new ArchivoSAP();
			envioSAP.enviarArchivo();

		} catch (Exception e) {
			log.error("Problemas en ejecución proceso CronArchivoSAP, mensaje:", e);
			
		}
		
	}
}