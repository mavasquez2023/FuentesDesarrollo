/*
 * Created on 16-10-2011
 *
 */
package com.microsystem.lme.trigger;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;

import com.microsystem.lme.job.LmeJob;
import com.microsystem.lme.util.Constants;

/**
 * @author microsystem
 * 
 */
public class LmeTrigger {

	//private LoggerHelper logger = new LoggerHelper();
	private Logger log = Logger.getLogger(this.getClass());

	private static LmeTrigger t = null;
	private static Scheduler scheduler = null;
	private ResourceBundle properties = ResourceBundle.getBundle("lme.resources.ApplicationResources");

	private LmeTrigger() {
	}

	public static LmeTrigger getInstance() {
		if (null == t) {
			t = new LmeTrigger();

			//			try {
			//				t.start();
			//			} catch (SchedulerException e) {
			//				// TODO Bloque catch generado automáticamente
			//				e.printStackTrace();
			//			}

		}

		return t;
	}

	public void start() throws SchedulerException {
		//		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
		//		scheduler = schedFact.getScheduler();
		//
		if (getStatus()) { //(null!=scheduler.getJobDetail(Constants.LME_JOB, Scheduler.DEFAULT_GROUP)) {
			//logger.logInfo(Constants.LME_JOB+" ALREADY EXISTS");
			log.info(Constants.LME_JOB + " ALREADY EXISTS");
			return;
		}
		//
		scheduler.start();

		JobDetail jobDetail = new JobDetail(Constants.LME_JOB, Scheduler.DEFAULT_GROUP, LmeJob.class);

		//el trigger se dispara cada 60 MINUTOS
		int period = Integer.valueOf(properties.getString("minPeriod")).intValue();
		Trigger trigger = TriggerUtils.makeSecondlyTrigger(60 * period);

		trigger.setStartTime(new java.util.Date());
		trigger.setName(Constants.LME_TRIGGER);
		scheduler.scheduleJob(jobDetail, trigger);
		//logger.logInfo(Constants.LME_JOB+"  START");
		log.info(Constants.LME_JOB + "  START");
	}

	public void stop() throws SchedulerException {
		if (getStatus()) {
			scheduler.shutdown();
			//logger.logInfo(Constants.LME_JOB+"  STOP");
			log.info(Constants.LME_JOB + "  STOP");
		} else {
			//logger.logInfo(Constants.LME_JOB+" ALREADY STOP");
			log.info(Constants.LME_JOB + " ALREADY STOP");
		}
	}

	public boolean getStatus() throws SchedulerException {
		boolean status = true;

		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
		scheduler = schedFact.getScheduler();
		if (null == scheduler.getJobDetail(Constants.LME_JOB, Scheduler.DEFAULT_GROUP))
			status = false;

		return status;
	}

}
