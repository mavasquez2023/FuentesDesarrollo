package com.microsystem.lme.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.PropertyMessageResources;
import com.microsystem.lme.job.LmeInicio;
import com.microsystem.lme.svc.exception.SvcException;
import com.microsystem.lme.util.BdConstants;

public class CargarEndPoint implements ServletContextListener {

	private Logger log = Logger.getLogger(this.getClass());

	public void contextInitialized(ServletContextEvent arg0) {
		try {
			BdConstants.getInstance().cargarParametros();
			//REQ-8000001332 - Autoiniciar si estaba iniciado antes del reinicio
			if(BdConstants.getInstance().ULTIMO_ESTADO_JOB == 1){
				PropertyMessageResources pmr = (PropertyMessageResources) PropertyMessageResources.getMessageResources("lme.resources.ApplicationResources");
				MessageResources messages = (MessageResources) pmr;
				
				LmeInicio.setProperties(messages);
				LmeInicio.setIniciado(1);
				LmeInicio.IniciarProcesoCompleto();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getClass() + "; " + e.getMessage());
		} catch (SvcException e) {
			log.error(e.getClass() + "; " + e.getMessage());
		}
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		/*try {
			LmeTrigger.getInstance().stop();
		} catch (SchedulerException e) {
			log.error(e.getClass() + "; " + e.getMessage());
		}*/
		//System.out.println("se detiene ============================");
		LmeInicio.setIniciado(0);

	}

}
