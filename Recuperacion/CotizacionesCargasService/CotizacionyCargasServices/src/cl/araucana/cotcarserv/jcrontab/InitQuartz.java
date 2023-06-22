package cl.araucana.cotcarserv.jcrontab;


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
	private static final long serialVersionUID = 2005542442272647444L;
	private static Logger log = Logger.getLogger(InitQuartz.class);
	static ResourceBundle config = ResourceBundle.getBundle("etc/config");
	
	private Scheduler scheduler=null;
	private Scheduler scheduler2=null;
	private Scheduler scheduler3=null;
	
	public void process()
	{
		
		
		//String props = getServletConfig().getInitParameter("props");
		//String path = getServletConfig().getServletContext().getRealPath(props);
		
		try
		{	System.out.println("Inicializando Log4j para Servicio Cotizacion Y Cargas");
	    	String prefix =  getServletContext().getRealPath("/");
	    	String file = getInitParameter("log4j-init-file");
	    	// if the log4j-init-file is not set, then no point in trying
	    	if(file != null) {
	    		//BasicConfigurator.configure();
	    		PropertyConfigurator.configure(prefix+"/"+file);
	    	}
	    	
	    	System.out.println("Iniciando quartz Servicio Cotizacion Y Cargas");
	    	
			//Job Cargas
			JobDetail job = new JobDetail();
	    	job.setName("CorreoCargas");
	    	job.setGroup("Services");
	    	job.setJobClass(CronNominaCargas.class);
	    	
	    	//configure the scheduler time
	    	String cronta=config.getString("cronta.nomina.cargas");
	    	log.info("Cronta Cargas:" + cronta);
	    	Trigger trigger = new CronTrigger("triggerCargas", "Services", cronta );

	    	
	    	//schedule it
	    	scheduler = new StdSchedulerFactory().getScheduler();
	    	scheduler.scheduleJob(job, trigger);
	    	scheduler.start();
	    	
	    	//Job Cotizaciones
	    	JobDetail job2 = new JobDetail();
	    	job2.setName("CorreoCotizaciones");
	    	job2.setGroup("Services");
	    	job2.setJobClass(CronNominaCotizaciones.class);
	    	
	    	//configure the scheduler time
	    	String cronta2=config.getString("cronta.nomina.cotizaciones");
	    	log.info("Cronta Cotizaciones:" + cronta2);
	    	Trigger trigger2 = new CronTrigger("triggerCotizaciones", "Services", cronta2 );
	    	
	    	//schedule it
	    	scheduler2 = new StdSchedulerFactory().getScheduler();
	    	scheduler2.scheduleJob(job2, trigger2);
	    	scheduler2.start();
	    	
	    	//Job SAP
			JobDetail job3 = new JobDetail();
	    	job3.setName("ArchivoSAP");
	    	job3.setGroup("Services");
	    	job3.setJobClass(CronInformeSAP.class);
	    	
	    	//configure the scheduler time
	    	String cronta3=config.getString("cronta.archivo.sap");
	    	log.info("Cronta Archivo SAP:" + cronta3);
	    	Trigger trigger3 = new CronTrigger("triggerSAP", "Services", cronta3 );
	    	
	    	/*SimpleTrigger trigger3 = new SimpleTrigger();
	    	trigger3.setName("triggerSAP");
	    	trigger3.setGroup("Services");
	    	trigger3.setStartTime(new Date(System.currentTimeMillis() + 1000));
	    	trigger3.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
	    	trigger3.setRepeatInterval(30000);*/
	    	
	    	//schedule it
	    	scheduler3 = new StdSchedulerFactory().getScheduler();
	    	scheduler3.scheduleJob(job3, trigger3);
	    	scheduler3.start();
			
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
