/*
 * Created on 28-12-2011
 *
 */
package com.microsystem.lme.trigger;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;

import com.microsystem.lme.job.LmeJob;
import com.microsystem.lme.util.Constants;

/**
 * @author microsystem
 *
 */
public class OneTrigger {

	//	private LoggerHelper logger = new LoggerHelper();
	private Logger log = Logger.getLogger(this.getClass());

	private static OneTrigger t = null;
	private static Scheduler scheduler = null;

	private OneTrigger() {
	}

	public static OneTrigger getInstance() {
		if (null == t)
			t = new OneTrigger();
		return t;
	}

	public void start() throws SchedulerException {
		try {
			SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
			scheduler = schedFact.getScheduler();

			JobDetail jobDetail = new JobDetail(Constants.LME_JOB, Scheduler.DEFAULT_GROUP, LmeJob.class);

			//La tarea será ejecutada todos los días a las 01:00 Hrs. 
			CronTrigger trigger = new CronTrigger("OneTrigger", Scheduler.DEFAULT_GROUP, "0 42 17 * * ?");

			trigger.setStartTime(new java.util.Date());
			trigger.setName(Constants.LME_TRIGGER);
			scheduler.scheduleJob(jobDetail, trigger);

			scheduler.start();
		} catch (SchedulerException e) {
			//logger.logError(e.getMessage());
			log.error(e.getMessage());
		} catch (ParseException e) {
			//logger.logError(e.getMessage());
			log.error(e.getMessage());
		}
	}

	public void stop() throws SchedulerException {
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
		}
	}
}
