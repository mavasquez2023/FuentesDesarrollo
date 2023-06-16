package cl.araucana.fonasa.cronta;


import java.util.ResourceBundle;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jcrontab.web.loadCrontabServlet;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
/*
* @(#) Initjcrontab.java 1.0 28/05/2014
*
/**
 * @author clillo
 *
 * @version 1.0
 */
public class InitQuartz extends loadCrontabServlet
{
	
	/**
	 * created by J-Factory
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(InitQuartz.class);
	static ResourceBundle config = ResourceBundle.getBundle("etc/config");
	
	private Scheduler scheduler=null;
	
	public void process()
	{
		
		
		//String props = getServletConfig().getInitParameter("props");
		//String path = getServletConfig().getServletContext().getRealPath(props);
		
		try
		{	System.out.println("Inicializando Log4j para Validación Fonasa");
	    	String prefix =  getServletContext().getRealPath("/");
	    	String file = getInitParameter("log4j-init-file");
	    	// if the log4j-init-file is not set, then no point in trying
	    	if(file != null) {
	    		//BasicConfigurator.configure();
	    		PropertyConfigurator.configure(prefix+"/"+file);
	    	}
	    	
	    	System.out.println("Iniciando quartz Validación Fonasa");
	    	
			//Job Valida Fonasa
			JobDetail job = new JobDetail();
	    	job.setName("CorreoValidacionFonasa");
	    	job.setGroup("Services");
	    	job.setJobClass(CronValidaFonasa.class);
	    	
	    	//configure the scheduler time
	    	String cronta=config.getString("cronta.validacion.fonasa");
	    	log.info("Cronta Validacion Fonasa:" + cronta);
	    	Trigger trigger = new CronTrigger("triggerValidacionFonasa", "Services", cronta );

	    	
	    	//schedule it
	    	scheduler = new StdSchedulerFactory().getScheduler();
	    	scheduler.scheduleJob(job, trigger);
	    	scheduler.start();
	    	
			
		} catch (Exception e)
		{
			log.error("problemas en carga parametros proceso quartz:" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void doStop()
	{
		try {
			//scheduler.shutdown();
			//scheduler2.shutdown();
			//scheduler3.shutdown(waitForJobsToComplete);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
