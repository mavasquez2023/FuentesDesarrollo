package main.java.com.pruebas.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.JmsException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import main.java.com.pruebas.clases.Persona;

 

public class StartJob extends QuartzJobBean {
	
	private static final Logger logger = LoggerFactory
			.getLogger(StartJob.class);

	private Persona persona;
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		try {
			logger.info("Inicia El Job GedoListenerStartQueueJob...");
			inicializarContexto(context);
			logger.info("Mostrar los datos de la persona: " + persona.toString());
		} catch (JmsException e) {
			logger.error("Error al el job ... "
					+ e);
		}
	}

	/**
	 * Permite instanciar los beans que necesita este job.
	 */
	private void inicializarContexto(JobExecutionContext context) {
		try {
			if (persona == null) {
				persona = (Persona) context.getScheduler().getContext()
						.get("persona");
			}
		} catch (SchedulerException e) {
			logger.error("Error al inicializar los beans en el job : " +e);
		}
	}
}
	
