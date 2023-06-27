/*
 * Created on 16-12-2011
 *
 */
package com.microsystem.lme.job;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.apache.struts.util.MessageResources;

//import com.microsystem.lme.helper.LoggerHelper;

/**
 * @author microsystem
 *
 */
public class LmeTask {

	//	private LoggerHelper logger = new LoggerHelper();
	private static Logger log = Logger.getLogger(LmeTask.class);

	private static LmeTask task = null;
	private Timer t = null;//new Timer(false);
	private final static int minutes = 10;
	private static MessageResources messages;
	private static boolean jobOn = false;

	//	public void start(){
	//		Calendar c = Calendar.getInstance();
	//        //c.roll(Calendar.MINUTE, true);
	//        t = new Timer(false);
	//        t.schedule(new TimerTasker(), c.getTime(), minutes * 60 * 1000);
	//	}
	//	
	//	public void stop(){
	//		t.cancel();
	//	}
	//	
	private LmeTask() {
	}

	public static LmeTask getInstance() {
		if (null == task)
			task = new LmeTask();
		return task;
	}

	/**
	 * 
	 * @author microsystem
	 */
	private static class TimerTasker extends TimerTask {

		public void run() {
			//            System.out.println("Running ...");
			try {
				ConsumoOperadorService job = new ConsumoOperadorService(messages);
				//LME Mixtas	1
				job.lmeMixtas();

			} catch (Throwable e) {
				//e.printStackTrace();
				log.error(e.getClass() + "; "+ e.getMessage());
			}
		}
	}

	public static void setProperties(MessageResources m) {
		messages = m;
	}

	public void start() {
		if (jobOn) {
			log.info("LME_JOB ALREADY START");
			return;
		}
		log.info("LME_JOB START");
		setJobOn(true);
		while (jobOn) {

			try {
				ConsumoOperadorService job = new ConsumoOperadorService(messages);
				//job.lmeMixtas(); //LME Mixtas	1			
				//job.enviarZonasC(); //Zona C		2			
				job.validarLicenciasME(); //Validacion	3			
				//job.devolverLicenciasME(); //Devolucion	4			 
				//job.liquidarLicenciasME(); //Liquidacion	5			
				//job.consumirEstadosLME(); //Estados		6
				//job.consumirDetallesLME(); //Licencias		7			
				//job.actualizarLmeCCAF(); //EjecutaCore	8
				//job.devolverLicenciasME051R(); //Nueva Devolucion

				Thread.sleep(minutes * 60 * 1000);
			} catch (InterruptedException e) {
				log.error(e.getClass() + "; " + e.getMessage());
				//e.printStackTrace();
			}
		}
	}

	public void stop() {
		if (!jobOn) {
			log.info("LME_JOB ALREADY STOP");
			return;
		}
		log.info("LME_JOB STOP");
		jobOn = false;
	}

	public boolean getStatus() {
		return jobOn;
	}

	public static void setJobOn(boolean b) {
		jobOn = b;
	}

}
