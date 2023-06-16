package cl.araucana.ctasfam.batch.listener;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import cl.araucana.ctasfam.batch.common.dto.PropiedadConfiguracionDto;
import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;
import cl.araucana.ctasfam.batch.common.util.ConfiguracionUtil;
import cl.araucana.ctasfam.batch.common.util.InformacionGeneralUtil;
import cl.araucana.ctasfam.batch.dao.ConfiguracionAppDao;
import cl.araucana.ctasfam.batch.dao.db2.impl.ConfiguracionAppDaoImpl;
import cl.araucana.ctasfam.batch.job.MonitorPeticionesProcesamientoJob;
import cl.araucana.ctasfam.batch.thread.AdministradorHebrasThread;

public class InitAppListener implements ServletContextListener {
	
	private Scheduler scheduler;
	
	final static Logger logger = Logger.getLogger(InitAppListener.class);

	public void contextDestroyed(ServletContextEvent arg0) {
		AdministradorHebrasThread.setRunning(false);
		try {
			this.scheduler.shutdown(false);
		} catch (SchedulerException se) {
			se.printStackTrace();
		}
	}

	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("Iniciando aplicacion batch de cambio de tramo...");
		
		InformacionGeneralUtil.estadoApp = "Iniciando..";
		
//		Carga las configuraciones de la aplicacion
		loadAppConfig();

//   	Si las configuraciones se cargaron correctamente se inicia el administrador del proceso principal 
   		if("Ok".equalsIgnoreCase(InformacionGeneralUtil.estadoConfigInternaApp) 
   			&& "Ok".equalsIgnoreCase(InformacionGeneralUtil.estadoConfigBaseDatoApp)){
   			initProccessManager();
   			
//   		Si el process manager se inicio correctamente se carga el Job monitor de peticiones
   			if("Ok".equalsIgnoreCase(InformacionGeneralUtil.estadoProcessManager)){
   				loadJobMonitoreo();
   				InformacionGeneralUtil.estadoApp = "Ok";
   			}
   		}
	}
	
	private void loadAppConfig(){
		logger.debug("Cargando la configuracion de la aplicacion...");
//		Carga de propiedades desde el archivo de configuracion interno
   		try {
   			Properties configuracionInterna = new Properties();
   			configuracionInterna.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(ConfiguracionUtil.FILE_INTERNAL_APP_CONFIG));
			ConfiguracionUtil.setConfiguracionInterna(configuracionInterna);
			
			InformacionGeneralUtil.estadoConfigInternaApp = "Ok";
		} catch (Exception e) {
			logger.error("Upss!! Ocurrio un error al cargar la configuracion interna", e);
			
			InformacionGeneralUtil.estadoConfigInternaApp = "Error";
			InformacionGeneralUtil.estadoApp = "Iniciada con error";
		}

//	   	Carga configuraciones desde la base de datos
   		try{
   			Properties configuracionBaseDatos = new Properties();
   			ConfiguracionAppDao configDao = new ConfiguracionAppDaoImpl();
   	   		for(PropiedadConfiguracionDto obj : configDao.getAllPropiedadesDeConfiguracion()){
   	   			configuracionBaseDatos.setProperty(obj.getLlave().trim(), obj.getValor().trim());
   	   		}
   	   		ConfiguracionUtil.setConfiguracionBaseDatos(configuracionBaseDatos);
   	   		
   	   		InformacionGeneralUtil.estadoConfigBaseDatoApp = "Ok";
   		}catch(TechnicalException te){
			logger.error("Upss!! Ocurrio un error al cargar la configuracion de la base de datos");
			logger.error(te.getCode() + ": " + te.getDescription(), te);
			
   			InformacionGeneralUtil.estadoConfigBaseDatoApp = "Error";
   			InformacionGeneralUtil.estadoApp = "Iniciada con error";
   		} catch (Exception e) {
			logger.error("Upss!! Ocurrio un error al cargar la configuracion de la base de datos", e);
			
			InformacionGeneralUtil.estadoConfigBaseDatoApp = "Error";
			InformacionGeneralUtil.estadoApp = "Iniciada con error";
		}
	}
	
	private void initProccessManager(){
		logger.debug("Iniciando el administrador de hebras de procesamiento...");
		try {
			Integer qty = Integer.parseInt(ConfiguracionUtil.getValor("BATCH_MAX_HEBRAS"));
			AdministradorHebrasThread process = new AdministradorHebrasThread(qty);
			process.start();
			
			InformacionGeneralUtil.estadoProcessManager = "Ok";
		}catch (Exception e) {
			logger.error("Upss!! Ocurrio un error al iniciar el administrador de hebras de procesamiento", e);
   			
			InformacionGeneralUtil.estadoProcessManager = "Error";
			InformacionGeneralUtil.estadoApp = "Iniciada con error";
		}
	}
	
	private void loadJobMonitoreo(){
		logger.debug("Iniciando el monitor de peticiones de procesamiento...");
		try {
			this.scheduler = StdSchedulerFactory.getDefaultScheduler();
			this.scheduler.start();
			
//			Inicia trigger que ejecuta el Job que obtiene las propuestas pendientes de la base de datos y las encola  
			JobDetail job = new JobDetail("nameJob", "groupJob", MonitorPeticionesProcesamientoJob.class);
			Trigger trigger = new CronTrigger("nameTrigger", "groupTriger", ConfiguracionUtil.getValor("BATCH_INTERVALO_MONITOR_PROPUESTAS"));
			this.scheduler.scheduleJob(job, trigger);
			
			InformacionGeneralUtil.estadoJobMonitoreo = "Ok";
		} catch (Exception e) {
			logger.error("Upss!! Ocurrio un error al iniciar el monitor de peticiones de procesamiento", e);
			
			InformacionGeneralUtil.estadoJobMonitoreo = "Error";
			InformacionGeneralUtil.estadoApp = "Iniciada con error";
		}
	}
}

