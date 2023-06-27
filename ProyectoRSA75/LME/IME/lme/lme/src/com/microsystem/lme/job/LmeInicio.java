/*
 * Created on 16-10-2011
 *
 */
package com.microsystem.lme.job;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.apache.struts.util.MessageResources;

//import com.microsystem.lme.helper.LoggerHelper;

/**
 * @author microsystem
 *
 */
public class LmeInicio {

	//0 no continuar, 1= continuar
	private static int iniciado = 0;
	private static MessageResources messages;
	//	private LoggerHelper logger = new LoggerHelper();
	private static Logger log = Logger.getLogger(LmeInicio.class);

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	
	public static void IniciarProcesoCompleto() {
		//log.info("el valor de iniciado es ================================== "+LmeInicio.iniciado);
		if(LmeInicio.iniciado == 1){
			TimerTask lc  = new LmeCiclo(messages);
			Timer t = new Timer();
			t.schedule(lc,0);
			//t.cancel();
		}else{
			log.info("SE DETUVO EL CICLO COMPLETO DE PROCESOS 'LME' [" + (new Date()) + "]");
		}
	}

	public static void setProperties(MessageResources m) {
		messages = m;
	}
	
	public static int getIniciado() {
		return iniciado;
	}

	public static void setIniciado(int iniciado) {
		LmeInicio.iniciado = iniciado;
	}
}
