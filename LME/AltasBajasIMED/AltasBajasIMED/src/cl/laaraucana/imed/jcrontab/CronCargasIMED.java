package cl.laaraucana.imed.jcrontab;


import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import cl.laaraucana.imed.business.ServiciosImed;



public class CronCargasIMED implements Job{
	private static Logger log = Logger.getLogger(CronCargasIMED.class);
	public static void main(String[] args) throws Exception {
		
	}
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
try {
			
			log.info("iniciando cronta Envio Cargas IMED");
			ServiciosImed servicio= new ServiciosImed();
			//null indica que se ejecutarán altas y bajas
			servicio.serviciosImed(null);

		} catch (Exception e) {
			log.error("Problemas en ejecución proceso CronEnvioNominaCargas, mensaje:", e);
			
		}
		
	}
}