package cl.laaraucana.cuotasdup.jcrontab;


import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cl.laaraucana.cuotasdup.business.GeneracionCartas;



public class CronCuotasDuplicadas implements Job{
	private static Logger log = Logger.getLogger(CronCuotasDuplicadas.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
try {
			
			log.info("iniciando cronta Envio Cuotas Duplicadas");
			GeneracionCartas envio= new GeneracionCartas();
			envio.generarCartas();

		} catch (Exception e) {
			log.error("Problemas en ejecución proceso CronEnvioNominaCotizaciones, mensaje:", e);
			
		}
		
	}
	
	public static void main(String[] args) {
		try {
			CronCuotasDuplicadas main= new CronCuotasDuplicadas();
			main.execute(null);
		} catch (JobExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}