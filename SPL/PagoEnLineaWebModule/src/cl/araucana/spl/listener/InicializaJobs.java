package cl.araucana.spl.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

import cl.araucana.spl.cron.JobSPL;
import cl.araucana.spl.util.Constantes;

//aplicación de tipo ServletContextListener, lo cual implica que se ejecuta cuando 
//parte el servidor de aplicaciones
//el objetivo de esta aplicación en dejar schedule o planificada la ejecución interna
//la cual se encarga de contruir los archivos, subirlos via ftp y enviar los email, 
//todo esto a una hora y dia determinado
public class InicializaJobs implements ServletContextListener {
	public static final String QUARTZ_FACTORY_KEY = "org.quartz.impl.StdSchedulerFactory.KEY";
	private ServletContext ctx = null;
	private StdSchedulerFactory factory = null;
	public static final String JOB_SPL = "JOB_SPL";
	public static final String TRIGGER_SPL = "TRIGGER_SPL";
	public static final String nombreClase = InicializaJobs.class.getName();

	// se ejecuta cuando se termina el contexto del servidor ejemplo cuando
	// se manda a detener
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			// detiene la aplicación planificada
			StdSchedulerFactory.getDefaultScheduler().shutdown();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// se ejecuta cuando se inicia el contexto del servidor, ejemplo cuando se
	// inicia,
	// es een este punto donde se lanza la aplicación planificada
	public void contextInitialized(ServletContextEvent sce) {

		// extraigo el contexto del servidor
		ctx = sce.getServletContext();
		try {
			// creo la instancia del planificador
			factory = new StdSchedulerFactory();

			// dejo planificado el proceso interno establecido por el metodo
			// EXECUTE
			// del la clase TRABAJO
			Scheduler sched = factory.getScheduler();
			// Scheduler
			JobDetail job = new JobDetail(JOB_SPL, Scheduler.DEFAULT_GROUP,
					JobSPL.class);
			// establesco el periodo de planificación para la ejecución
			CronTrigger ctAnula = new CronTrigger(TRIGGER_SPL,
					Scheduler.DEFAULT_GROUP,
					Constantes.getInstancia().TIEMPO_JOB);
			sched.scheduleJob(job, ctAnula);
			sched.start();

			ctx.setAttribute(QUARTZ_FACTORY_KEY, factory);
			// despliego un mensaje por consola cuando se inicia el contexto del
			// servidor
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
