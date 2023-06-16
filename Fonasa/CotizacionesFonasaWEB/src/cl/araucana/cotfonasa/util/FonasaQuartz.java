package cl.araucana.cotfonasa.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import cl.araucana.cotfonasa.impl.ProcesoFonasaImpl;

public class FonasaQuartz implements Job {

	private static final String	JOB_GROUP	= "jobGroup";
	private static final String	CRON_GROUP	= "cronGroup";
	private static String CRON_QUARTZ;

	private static Scheduler	sched;

	public static void main(String[] args) {
		
		Date date = new Date();
		DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
		System.out.println("Hora Main: "+hourFormat.format(date));
		
		init();
		/* {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		//shutdown();
	}

	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Object jobParam = context.getJobDetail().getJobDataMap().get("jobParamKey");
		DateFormat dateFormat2 = new SimpleDateFormat("MMyyyy");
		
		Date date = new Date();
	   	
		String fechaProceso =dateFormat2.format(date);
	
		System.out.println("Quartz Fecha Proceso:"+fechaProceso);
		
		ProcesoFonasaImpl proc = new ProcesoFonasaImpl();
		
		proc.ejecutaProceso(fechaProceso);
		
		
	}

	public static void init() {
		SchedulerFactory sf = new StdSchedulerFactory();
		Properties props = new Properties();
		
		
		try {
			props.load(FonasaQuartz.class.getClassLoader().getResourceAsStream("cl/araucana/cotfonasa/properties/parametros.properties"));
		} catch (IOException e1) {
			// TODO Bloque catch generado automáticamente
			e1.printStackTrace();
		}
		
		CRON_QUARTZ = props.getProperty("CRON_QUARTZ");
		
		try {
			sched = sf.getScheduler();
		} catch (SchedulerException e) {
			System.out.println("Failed to get the default scheduler");
			return;
		}

		// Create the job detail information
		JobDetail jd=new JobDetail("jobname",FonasaQuartz.JOB_GROUP,FonasaQuartz.class);
		// put needed params
		jd.getJobDataMap().put("jobParamKey", new Date());
		// Define cron trigger
		CronTrigger ct;
		try {
		    //ct = new CronTrigger("cronName", TestQuartz.CRON_GROUP, "0/1 * * * * ?");
		    
			
			
			//ct = new CronTrigger("cronName", FonasaQuartz.CRON_GROUP, "0 30 01 2 * ?");
			
			// programado para correr cada madrugada del 23 de cada mes, a las 00:01
			ct = new CronTrigger("cronName", FonasaQuartz.CRON_GROUP, CRON_QUARTZ);
		    
		    // Cron para cada segundo "0/1 * * * * ?"
		    // "s m H dM M dW y"
		    // Schedule the job
		    sched.scheduleJob(jd, ct);

		    // Start scheduler
		    sched.start();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}

	public static Date rescheduleBackup(String jobkey, String newCron, boolean isActive)
			throws SchedulerException, ParseException {

		Trigger trigger = sched.getTrigger(jobkey, FonasaQuartz.CRON_GROUP);
		if (trigger != null) {
		    CronTrigger ct = new CronTrigger(jobkey, FonasaQuartz.CRON_GROUP, newCron);
		    ct.setJobGroup(JOB_GROUP);
		    ct.setJobName(jobkey);
		    Date date = sched.rescheduleJob(jobkey, FonasaQuartz.CRON_GROUP, ct);
		    System.out.println("Job has been modified, has scheduled to " + date);
		    return ct.getNextFireTime();

		} else {
			sched.deleteJob(jobkey, FonasaQuartz.JOB_GROUP);
			return null;
		}
	}

	public static void shutdown() {
		System.out.println("Shutting down the scheduler...");
		try {
			sched.shutdown();
		} catch (SchedulerException e) {
			System.out.println("Error shutting down the backup scheduler.");
		}
		System.out.println("Scheduler is shutdown");
	}

}