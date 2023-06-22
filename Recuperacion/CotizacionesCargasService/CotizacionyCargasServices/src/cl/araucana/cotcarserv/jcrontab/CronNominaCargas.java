package cl.araucana.cotcarserv.jcrontab;


import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cl.araucana.cotcarserv.servlets.DescargaMasivaCargas;
import cl.araucana.cotcarserv.servlets.DescargaMasivaCotizaciones;



public class CronNominaCargas implements Job{
	private static Logger log = Logger.getLogger(CronNominaCargas.class);
	public static void main(String[] args) throws Exception {
		
	}
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
try {
			
			log.info("iniciando cronta Envio Nomina Cargas");
			DescargaMasivaCargas envio= new DescargaMasivaCargas();
			envio.enviarCorreos();

		} catch (Exception e) {
			log.error("Problemas en ejecución proceso CronEnvioNominaCargas, mensaje:", e);
			
		}
		
	}
}