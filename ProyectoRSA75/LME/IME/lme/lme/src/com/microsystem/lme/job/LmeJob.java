/*
 * Created on 16-10-2011
 *
 */
package com.microsystem.lme.job;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts.util.MessageResources;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.microsystem.lme.util.EndPointUtil;
import com.microsystem.lme.util.Util;

//import com.microsystem.lme.helper.LoggerHelper;

/**
 * @author microsystem
 *
 */
public class LmeJob implements Job {

	private static MessageResources messages;
	//	private LoggerHelper logger = new LoggerHelper();
	private Logger log = Logger.getLogger(this.getClass());

	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

		long inicioTotal = System.currentTimeMillis();
		log.info("INICIO DEL CICLO COMPLETO DE PROCESOS 'LME' [" + (new Date()) + "]");
		try {
			//boolean para imprimir una vez que existe error en todos los operadores
			boolean imprimioErrorTotal = false;
			EndPointUtil instanciaEndPoint = EndPointUtil.getInstance();
			//reinicia errores
			instanciaEndPoint.reCargarParametrosErrores();

			ConsumoOperadorService job = new ConsumoOperadorService(messages);

			//LME Mixtas	1
			long inicio1 = System.currentTimeMillis();

			//job.lmeMixtas();

			log.info("TERMINO DE licencias mixtas DEMORO [" + Util.getTiempoRestante(inicio1) + " Milisegundos]");

			//Zona C		2
			//valida si existen errores en todos los operadores para no ejecutar el job
			if (instanciaEndPoint.getEstadoErrorTodos()) {
				if (imprimioErrorTotal == false) {
					log.info("Problemas de comunicación, se procederá a congelan las actividades de este ciclo LME de proceso. Se esperará al siguiente ciclo");
					imprimioErrorTotal = true;
				}
			} else {
				long inicio2 = System.currentTimeMillis();

				//job.enviarZonasC();

				log.info("TERMINO DE ZONA C DEMORO [" + Util.getTiempoRestante(inicio2) + " Milisegundos]");
			}

			//Validacion	3
			//valida si existen errores en todos los operadores para no ejecutar el job
			if (instanciaEndPoint.getEstadoErrorTodos()) {
				if (imprimioErrorTotal == false) {
					log.info("Problemas de comunicación, se procederá a congelan las actividades de este ciclo LME de proceso. Se esperará al siguiente ciclo");
					imprimioErrorTotal = true;
				}
			} else {
				long inicio3 = System.currentTimeMillis();

				//job.validarLicenciasME();

				log.info("TERMINO DE VALIDACION [" + Util.getTiempoRestante(inicio3) + " Milisegundos]");
			}

			//Devolucion	4
			//valida si existen errores en todos los operadores para no ejecutar el job
			if (instanciaEndPoint.getEstadoErrorTodos()) {
				if (imprimioErrorTotal == false) {
					log.info("Problemas de comunicación, se procederá a congelan las actividades de este ciclo LME de proceso. Se esperará al siguiente ciclo");
					imprimioErrorTotal = true;
				}
			} else {
				long inicio4 = System.currentTimeMillis();

				//job.devolverLicenciasME();

				log.info("TERMINO DE Devolución 51 [" + Util.getTiempoRestante(inicio4) + " Milisegundos]");

			}

			/*20120818*/
			//Proceso nuevo DEVOLVER LICENCIAS 5
			//valida si existen errores en todos los operadores para no ejecutar el job
			if (instanciaEndPoint.getEstadoErrorTodos()) {
				if (imprimioErrorTotal == false) {
					log.info("Problemas de comunicación, se procederá a congelan las actividades de este ciclo LME de proceso. Se esperará al siguiente ciclo");
					imprimioErrorTotal = true;
				}
			} else {
				long inicio5 = System.currentTimeMillis();

				//job.devolverLicenciasME051R();

				log.info("TERMINO DE Devolución 51R [" + Util.getTiempoRestante(inicio5) + " Milisegundos]");

			}

			//Liquidacion	6
			//System.out.println("------------>job.liquidarLicenciasME();");
			//valida si existen errores en todos los operadores para no ejecutar el job
			if (instanciaEndPoint.getEstadoErrorTodos()) {
				if (imprimioErrorTotal == false) {
					log.info("Problemas de comunicación, se procederá a congelan las actividades de este ciclo LME de proceso. Se esperará al siguiente ciclo");
					imprimioErrorTotal = true;
				}
			} else {
				long inicio6 = System.currentTimeMillis();

				//job.liquidarLicenciasME();

				log.info("TERMINO DE LIQUIDACION [" + Util.getTiempoRestante(inicio6) + " Milisegundos]");

			}

			//Estados		7
			//System.o  ut.println("------------>job.consumirEstadosLME();");
			//valida si existen errores en todos los operadores para no ejecutar el job
			if (instanciaEndPoint.getEstadoErrorTodos()) {
				if (imprimioErrorTotal == false) {
					log.info("Problemas de comunicación, se procederá a congelan las actividades de este ciclo LME de proceso. Se esperará al siguiente ciclo");
					imprimioErrorTotal = true;
				}
			} else {
				long inicio7 = System.currentTimeMillis();

				//job.consumirEstadosLME();

				log.info("TERMINO DE NUEVOS ESTADO [" + Util.getTiempoRestante(inicio7) + " Milisegundos]");

			}

			//Licencias		8
			//System.out.println("------------>job.consumirDetallesLME();");	
			//valida si existen errores en todos los operadores para no ejecutar el job
			if (instanciaEndPoint.getEstadoErrorTodos()) {
				if (imprimioErrorTotal == false) {
					log.info("Problemas de comunicación, se procederá a congelan las actividades de este ciclo LME de proceso. Se esperará al siguiente ciclo");
					imprimioErrorTotal = true;
				}
			} else {
				long inicio8 = System.currentTimeMillis();

				//job.consumirDetallesLME();

				log.info("TERMINO DE licencias [" + Util.getTiempoRestante(inicio8) + " Milisegundos]");

			}

			//EjecutaCore	9		
			long inicio9 = System.currentTimeMillis();
			
			//job.actualizarLmeCCAF();
			
			log.info("TERMINO DE ACTUALIZAR [" + Util.getTiempoRestante(inicio9) + " Milisegundos]");

		} catch (Throwable e) {
			//e.printStackTrace();
			log.error(e.getClass() + "; " + e.getMessage());
		}
		log.info("TERMINO DEL CICLO COMPLETO DE PROCESOS 'LME' Y COMIENZO DEL REPOSO [" + (new Date()) + "] DEMORO [" + Util.getTiempoRestante(inicioTotal) + " Milisegundos]");
	}

	public static void setProperties(MessageResources m) {
		messages = m;
	}

}
