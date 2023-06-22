package cl.araucana.cotcarserv.jcrontab;


import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cl.araucana.cotcarserv.servlets.DescargaMasivaCotizaciones;



public class CronNominaCotizaciones implements Job{
	private static Logger log = Logger.getLogger(CronNominaCotizaciones.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
try {
			
			log.info("iniciando cronta Envio Nomina Cotizaciones");
			DescargaMasivaCotizaciones envio= new DescargaMasivaCotizaciones();
			envio.enviarCorreos();

		} catch (Exception e) {
			log.error("Problemas en ejecución proceso CronEnvioNominaCotizaciones, mensaje:", e);
			
		}
		
	}
}