package cl.araucana.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cl.araucana.manager.RespuestaEquifax;


/**
 * @author microsystem
 *
 */
public class ProcesoEquifaxJob implements Job {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	 public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		try {			
			RespuestaEquifax mgr = new RespuestaEquifax();
			mgr.procesarRespuestaEquifax();
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e.getClass() + "; "+ e.getMessage());
		}
    }
}
