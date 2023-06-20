package cl.laaraucana.imed.jcrontab;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jcrontab.web.loadCrontabServlet;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import cl.laaraucana.imed.config.Configuraciones;
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
	private static final long serialVersionUID = 2005542442272647444L;
	private static Logger log = Logger.getLogger(InitQuartz.class);

	
	private Scheduler scheduler=null;
	
	public void process()
	{
		
		
		//String props = getServletConfig().getInitParameter("props");
		//String path = getServletConfig().getServletContext().getRealPath(props);
		
		try
		{	log.info("Inicializando log4j Cargas Imed");
	    	String prefix =  getServletContext().getRealPath("/");
	    	String file = getInitParameter("log4j-init-file");
	    	// if the log4j-init-file is not set, then no point in trying
	    	if(file != null) {
	    		//BasicConfigurator.configure();
	    		PropertyConfigurator.configure(prefix+"/"+file);
	    	}
	    	
	    	log.info("Iniciando quartz Servicio Cargas IMED");
	    	
			//Job Imed
			JobDetail job = new JobDetail();
	    	job.setName("CorreoCargasIMED");
	    	job.setGroup("Services");
	    	job.setJobClass(CronCargasIMED.class);
	    	
	    	//configure the scheduler time
	    	String cronta=Configuraciones.getConfig("cronta.imed.cargas");
	    	log.info("Cronta Cargas IMED:" + cronta);
	    	Trigger trigger = new CronTrigger("triggerCargasIMED", "Services", cronta );

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
			scheduler.shutdown(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
