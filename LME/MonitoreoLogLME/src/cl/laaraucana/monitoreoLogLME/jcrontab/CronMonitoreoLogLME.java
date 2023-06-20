package cl.laaraucana.monitoreoLogLME.jcrontab;


import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import cl.laaraucana.monitoreoLogLME.business.ServiciosMonitoreoLogLME;



public class CronMonitoreoLogLME implements Job{
	private static Logger log = Logger.getLogger(CronMonitoreoLogLME.class);
	public static void main(String[] args) throws Exception {
		
	}
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
try {
			
			log.info("iniciando crontab Monitoreo Log LME");
			ServiciosMonitoreoLogLME servicio= new ServiciosMonitoreoLogLME();
			//null indica que se ejecutarán altas y bajas
			servicio.serviciosMonitoreoLogLME();

		} catch (Exception e) {
			log.error("Problemas en ejecución proceso Crontab de Monitoreo Log LME, mensaje:", e);
			
		}
		
	}
}