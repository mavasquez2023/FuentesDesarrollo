/*
 * Created on 16-10-2011
 *
 */
package cl.laaraucana.satelites.job;

import java.text.ParseException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;


public class Trigger {
	private Logger log = Logger.getLogger(this.getClass());
	
	private static Trigger t = null;
	private static Scheduler scheduler=null;
	private ResourceBundle properties = ResourceBundle.getBundle("quartz-config");
	String programa = properties.getString("programaEjecucion");
	String jobName = properties.getString("jobName");

	private Trigger() {
	}

	public static Trigger getInstance() {
		if (null == t){
			t = new Trigger();
		
//			try {
//				t.start();
//			} catch (SchedulerException e) {
//				// TODO Bloque catch generado automáticamente
//				e.printStackTrace();
//			}

		}		
		return t;
	}

	/**
	 * Inicia la ejecución del Job para una determinada hora
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public void start() throws SchedulerException, ParseException {
		
		if(scheduler == null){
			SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
			scheduler = schedFact.getScheduler();
			scheduler.start();
		}
		
		try {
			scheduler.deleteJob(jobName, Scheduler.DEFAULT_GROUP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JobDetail jobDetail = new JobDetail(jobName , Scheduler.DEFAULT_GROUP, MonitorFtpJob.class);
		CronTrigger crTrigger = new CronTrigger(jobName ,Scheduler.DEFAULT_GROUP, programa);
		scheduler.scheduleJob(jobDetail, crTrigger);
		log.info(jobName+"  START");
	}

	public void stop() throws SchedulerException {
		if(getStatus()){
			scheduler.deleteJob(jobName, Scheduler.DEFAULT_GROUP);
			scheduler.shutdown();
			log.info(jobName+"  STOP");
		}			
		else{
			log.info(jobName+" ALREADY STOP");
		}			
	}
	
	public boolean getStatus() throws SchedulerException{
		boolean status = true;
		if(null==scheduler.getJobDetail(jobName, Scheduler.DEFAULT_GROUP))
			status = false;
		
		return status;		
	}
}

