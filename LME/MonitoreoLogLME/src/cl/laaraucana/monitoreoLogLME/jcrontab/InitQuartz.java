package cl.laaraucana.monitoreoLogLME.jcrontab;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jcrontab.web.loadCrontabServlet;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import cl.laaraucana.monitoreoLogLME.config.Configuraciones;

/*
* @(#) Initjcrontab.java 1.0 28/05/2014
*
/**
 * @author clillo
 *
 * @version 1.0
 */
public class InitQuartz extends loadCrontabServlet {
	private static final long serialVersionUID = 2005542442272647444L;
	private static Logger log = Logger.getLogger(InitQuartz.class);

	private Scheduler scheduler = null;

	public void process() {

		// String props = getServletConfig().getInitParameter("props");
		// String path = getServletConfig().getServletContext().getRealPath(props);

		try {
			log.info("Inicializando log4j Monitoreo Log LME");
			String prefix = getServletContext().getRealPath("/");
			String file = getInitParameter("log4j-init-file");
			// if the log4j-init-file is not set, then no point in trying
			if (file != null) {
				// BasicConfigurator.configure();
				PropertyConfigurator.configure(prefix + "/" + file);
			}

			log.info("Iniciando quartz Servicio Monitoreo Log LME");

			// Job
			JobDetail job = new JobDetail();
			job.setName("CorreoMonitoreoLogLME");
			job.setGroup("Services");
			job.setJobClass(CronMonitoreoLogLME.class);

			// configure the scheduler time
			String cronta = Configuraciones.getConfig("crontab.lme.monitoreo");
			log.info("Crontab Monitoreo Log LME:" + cronta);
			Trigger trigger = new CronTrigger("triggerMonitoreo", "Services", cronta);

			// schedule it
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.scheduleJob(job, trigger);
			scheduler.start();

		} catch (Exception e) {
			log.error("problemas en carga parametros proceso quartz:" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void doStop() {
		try {
			scheduler.shutdown(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
