package cl.araucana.wslme.presentation.web.listener;

import java.text.ParseException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import cl.araucana.wslme.common.util.ConfigUtil;
import cl.araucana.wslme.integration.quartz.jobs.GeneraReportesJob;
import cl.araucana.wslme.integration.quartz.jobs.LmeStatsJob;

public class InitStatsProcess implements ServletContextListener {

	private Scheduler scheduler;

	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			this.scheduler.shutdown(false);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void contextInitialized(ServletContextEvent arg0) {
		try {
			// Grab the Scheduler instance from the Factory
			this.scheduler = StdSchedulerFactory.getDefaultScheduler();

			// and start it off
			this.scheduler.start();

			// Define job instance
			JobDetail job = new JobDetail("lmeStatsJob", "lmeGroup", LmeStatsJob.class);

			String interEjec = ConfigUtil.getValorRecursosDeAplicacion("araucana.wslme.proceso.intervaloejecucion");
			// Define a Trigger that will fire every 5 minutes.
			Trigger trigger = new CronTrigger("trigger1", "lmeGroup", "0 0/" + interEjec +" * * * ?");

			// Schedule the job with the trigger
			this.scheduler.scheduleJob(job, trigger);
			
			JobDetail jobReport = new JobDetail("generaReportesJob", "lmeGroup2", GeneraReportesJob.class);
			
			String diaEjec = ConfigUtil.getValorConfiguracionesDeNegocio("araucana.wslme.reportes.diageneracion");
			String horaEjec = ConfigUtil.getValorConfiguracionesDeNegocio("araucana.wslme.reportes.horageneracion");
			String hora = horaEjec.split(":")[0];
			String minuto = horaEjec.split(":")[1];
			Trigger triggerReport = new CronTrigger("trigger2", "lmeGroup2", "0 " + minuto + " " + hora + " " + diaEjec + " * ?");
			this.scheduler.scheduleJob(jobReport, triggerReport);
			
		} catch (SchedulerException se) {
			se.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
