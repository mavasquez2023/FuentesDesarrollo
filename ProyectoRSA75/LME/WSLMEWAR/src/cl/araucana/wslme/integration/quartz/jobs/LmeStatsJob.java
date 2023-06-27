package cl.araucana.wslme.integration.quartz.jobs;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cl.araucana.wslme.business.to.Event;
import cl.araucana.wslme.integration.dao.LmeLogDao;
import cl.araucana.wslme.integration.dao.impl.LmeLogDaoImpl;
import cl.araucana.wslme.integration.ehcache.LmeCacheWrapper;

public class LmeStatsJob implements Job {

	private final Logger log = Logger.getLogger(LmeStatsJob.class);
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		LmeLogDao dao = new LmeLogDaoImpl();
		
		List events = LmeCacheWrapper.getInstance().getAllAndRemove();
		for (Iterator iterator = events.iterator(); iterator.hasNext();) {
			Event event = (Event) iterator.next();
			try {
				dao.saveEvent(event);
				dao.saveEventResult(event);
			} catch (Exception e) {
				log.error("No fue posible guardar el evento: " + event.getFechEven(), e);
				LmeCacheWrapper.getInstance().put(event.getFechEven().toString(), event);
			}
		}
	}

}
