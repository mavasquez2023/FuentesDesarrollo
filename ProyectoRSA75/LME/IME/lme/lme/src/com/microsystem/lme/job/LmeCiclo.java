package com.microsystem.lme.job;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.apache.struts.util.MessageResources;

import com.microsystem.lme.svc.exception.SvcException;
import com.microsystem.lme.util.BdConstants;
import com.microsystem.lme.util.EndPointUtil;
import com.microsystem.lme.util.SQLExceptionHandler;
import com.microsystem.lme.util.Util;

public class LmeCiclo extends TimerTask {
	
	private static boolean continuarCiclo = true;// 0 no continuar, 1= continuar
	private static String connErrorMsg = "TERMINO DEL CICLO POR PROBLEMAS DE CONEXION, SE ESPERARA AL SIGUIENTE CICLO";
	
	public LmeCiclo(MessageResources messages) {
		this.messages= messages;
	}

	public void run() {
		//REQ-8000001332 - Cargar parámetros al inicio del ciclo
		continuarCiclo = true;
		SQLExceptionHandler.reiniciarContadorErrores();
		try {
			BdConstants.getInstance().cargarParametros();
		} catch (SvcException e) {
			e.printStackTrace();
		}
		
		//REQ-8000001332 - Verificar problemas de conexion despues de cargar parametros
		if(!continuarCiclo){
			log.info(connErrorMsg);
			return;
		}else{
			procesoCompleto();
			this.cancel();
		}
		
		//REQ-8000001332 - leer tiempo de reposo desde parámetros
		int minutos = 1;//Tiempo de espera (min) en caso de que fallara conexion a BD
		try {
			minutos = BdConstants.getInstance().TIEMPO_REPOSO_JOB;
			System.out.println("Ciclo finalizado Detenido por " + minutos + " minutos");
			Thread.sleep(minutos*60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LmeInicio.IniciarProcesoCompleto();
	}

	private MessageResources messages;
	private Logger log = Logger.getLogger(this.getClass());

	private void procesoCompleto() {
		ResourceBundle properties = ResourceBundle.getBundle("lme.resources.ApplicationResources");
		long inicioTotal = System.currentTimeMillis();
		log.info("INICIO DEL CICLO COMPLETO DE PROCESOS 'LME' [" + (new Date())+ "]");
		try {
			// boolean para imprimir una vez que existe error en todos los
			// operadores
			boolean imprimioErrorTotal = false;

			EndPointUtil instanciaEndPoint = EndPointUtil.getInstance();
			// reinicia errores
			instanciaEndPoint.reCargarParametrosErrores();

			ConsumoOperadorService job = new ConsumoOperadorService(messages);
			job.setDateLcc(null);
			
			//REQ-8000001332 - Verificar problemas de conexion
			if(!continuarCiclo){
				log.info(connErrorMsg);
				return;
			}

			// job.testConexion();
			// LME Mixtas 1
			long inicio1 = System.currentTimeMillis();
			if(properties.getString("ciclo.proceso.1").equals("1")){
				job.lmeMixtas();
				log.info("TERMINO DE licencias mixtas DEMORO ["+ Util.getTiempoRestante(inicio1) + " Milisegundos]");
			}
			
			
			//REQ-8000001332 - Verificar problemas de conexion
			if(!continuarCiclo){
				log.info(connErrorMsg);
				return;
			}

			// Zona C 2
			// valida si existen errores en todos los operadores para no
			// ejecutar el job
			if (instanciaEndPoint.getEstadoErrorTodos()) {
				if (imprimioErrorTotal == false) {
					log.info("Problemas de comunicación, se procederá a congelan las actividades de este ciclo LME de proceso. Se esperará al siguiente ciclo");
					imprimioErrorTotal = true;
				}
			} else {
				long inicio2 = System.currentTimeMillis();
				if(properties.getString("ciclo.proceso.2").equals("1")){
					job.enviarZonasC();
					log.info("TERMINO DE ZONA C DEMORO ["+ Util.getTiempoRestante(inicio2) + " Milisegundos]");
				}
				
			}
			
			//REQ-8000001332 - Verificar problemas de conexion
			if(!continuarCiclo){
				log.info(connErrorMsg);
				return;
			}

			// Validacion 3
			// valida si existen errores en todos los operadores para no
			// ejecutar el job
			if (instanciaEndPoint.getEstadoErrorTodos()) {
				if (imprimioErrorTotal == false) {
					log.info("Problemas de comunicación, se procederá a congelan las actividades de este ciclo LME de proceso. Se esperará al siguiente ciclo");
					imprimioErrorTotal = true;
				}
			} else {
				long inicio3 = System.currentTimeMillis();
				if(properties.getString("ciclo.proceso.3").equals("1")){
					job.validarLicenciasME();
					log.info("TERMINO DE VALIDACION ["+ Util.getTiempoRestante(inicio3) + " Milisegundos]");
				}
				
			}
			
			//REQ-8000001332 - Verificar problemas de conexion
			if(!continuarCiclo){
				log.info(connErrorMsg);
				return;
			}

			// Devolucion 4
			// valida si existen errores en todos los operadores para no
			// ejecutar el job
			if (instanciaEndPoint.getEstadoErrorTodos()) {
				if (imprimioErrorTotal == false) {
					log.info("Problemas de comunicación, se procederá a congelan las actividades de este ciclo LME de proceso. Se esperará al siguiente ciclo");
					imprimioErrorTotal = true;
				}
			} else {
				long inicio4 = System.currentTimeMillis();
				if(properties.getString("ciclo.proceso.4").equals("1")){
					job.devolverLicenciasME();
					log.info("TERMINO DE Devolución 51 ["+ Util.getTiempoRestante(inicio4) + " Milisegundos]");
				}
				
			}
			
			//REQ-8000001332 - Verificar problemas de conexion
			if(!continuarCiclo){
				log.info(connErrorMsg);
				return;
			}

			/* 20120818 */
			// Proceso nuevo DEVOLVER LICENCIAS 5
			// valida si existen errores en todos los operadores para no
			// ejecutar el job
			if (instanciaEndPoint.getEstadoErrorTodos()) {
				if (imprimioErrorTotal == false) {
					log.info("Problemas de comunicación, se procederá a congelan las actividades de este ciclo LME de proceso. Se esperará al siguiente ciclo");
					imprimioErrorTotal = true;
				}
			} else {
				long inicio5 = System.currentTimeMillis();
				if(properties.getString("ciclo.proceso.5").equals("1")){
					job.devolverLicenciasME051R();
					log.info("TERMINO DE Devolución 51R ["+ Util.getTiempoRestante(inicio5) + " Milisegundos]");
				}
				

			}
			
			//REQ-8000001332 - Verificar problemas de conexion
			if(!continuarCiclo){
				log.info(connErrorMsg);
				return;
			}

			// Liquidacion 6
			// valida si existen errores en todos los operadores para no
			// ejecutar el job
			if (instanciaEndPoint.getEstadoErrorTodos()) {
				if (imprimioErrorTotal == false) {
					log.info("Problemas de comunicación, se procederá a congelan las actividades de este ciclo LME de proceso. Se esperará al siguiente ciclo");
					imprimioErrorTotal = true;
				}
			} else {
				long inicio6 = System.currentTimeMillis();
				if(properties.getString("ciclo.proceso.6").equals("1")){
					job.liquidarLicenciasME();
					log.info("TERMINO DE LIQUIDACION ["+ Util.getTiempoRestante(inicio6) + " Milisegundos]");
				}
			}
			
			//REQ-8000001332 - Verificar problemas de conexion
			if(!continuarCiclo){
				log.info(connErrorMsg);
				return;
			}

			// Estados 7
			// valida si existen errores en todos los operadores para no
			// ejecutar el job
			if (instanciaEndPoint.getEstadoErrorTodos()) {
				if (imprimioErrorTotal == false) {
					log.info("Problemas de comunicación, se procederá a congelan las actividades de este ciclo LME de proceso. Se esperará al siguiente ciclo");
					imprimioErrorTotal = true;
				}
			} else {
				long inicio7 = System.currentTimeMillis();
				if(properties.getString("ciclo.proceso.7").equals("1")){
					job.consumirEstadosLME("LMEEventLcc");
					log.info("TERMINO DE NUEVOS ESTADO ["+ Util.getTiempoRestante(inicio7) + " Milisegundos]");
				}
			}
			
			//REQ-8000001332 - Verificar problemas de conexion
			if(!continuarCiclo){
				log.info(connErrorMsg);
				return;
			}

			// Licencias 8
			// valida si existen errores en todos los operadores para no
			// ejecutar el job
			if (instanciaEndPoint.getEstadoErrorTodos()) {
				if (imprimioErrorTotal == false) {
					log.info("Problemas de comunicación, se procederá a congelan las actividades de este ciclo LME de proceso. Se esperará al siguiente ciclo");
					imprimioErrorTotal = true;
				}
			} else {
				long inicio8 = System.currentTimeMillis();
				if(properties.getString("ciclo.proceso.8").equals("1")){
					job.consumirDetallesLME();
					log.info("TERMINO DE licencias ["+ Util.getTiempoRestante(inicio8) + " Milisegundos]");
				}
			}
			
			//REQ-8000001332 - Verificar problemas de conexion
			if(!continuarCiclo){
				log.info(connErrorMsg);
				return;
			}

			// EjecutaCore 9
			long inicio9 = System.currentTimeMillis();
			if(properties.getString("ciclo.proceso.9").equals("1")){
				job.actualizarLmeCCAF();
				log.info("TERMINO DE ACTUALIZAR CICLO ["+ Util.getTiempoRestante(inicio9) + " Milisegundos]");
			}
			//REQ-8000001332 - Verificar problemas de conexion
			if(!continuarCiclo){
				log.info(connErrorMsg);
				return;
			}

			// Particular por licencias 10
			long inicio10 = System.currentTimeMillis();
			if(properties.getString("ciclo.proceso.10").equals("1")){
				job.procesosParticularesPorLicencia();
				log.info("TERMINO DE PROCESO PARTICULAR POR LICENCIAS ["+ Util.getTiempoRestante(inicio10) + " Milisegundos]");
			}
			//REQ-8000001332 - Verificar problemas de conexion
			if(!continuarCiclo){
				log.info(connErrorMsg);
				return;
			}
			
			
			// particular por fecha 11
			long inicio11 = System.currentTimeMillis();
			if(properties.getString("ciclo.proceso.11").equals("1")){
				job.procesosParticularesPorFecha();
				log.info("TERMINO DE PROCESO PARTICULAR POR FECHA ["+ Util.getTiempoRestante(inicio11) + " Milisegundos]");
			}
			//REQ-8000001332 - Verificar problemas de conexion
			if(!continuarCiclo){
				log.info(connErrorMsg);
				return;
			}
			
			//REQ-8000003799 - mejoras Consumos de nuevos estados
			// reconsumo estados nuevos vía LMEEventFec
			long inicio12 = System.currentTimeMillis();
			if(properties.getString("ciclo.proceso.12").equals("1")){
				job.procesoReconsumoEstadosNuevos();
			}
			//REQ-8000001332 - Verificar problemas de conexion
			if(!continuarCiclo){
				log.info(connErrorMsg);
				return;
			}
			
			// eliminación de licencias en estado 00
			long inicio13 = System.currentTimeMillis();
			if(properties.getString("ciclo.proceso.13").equals("1")){
				job.deleteLMECero();
				log.info("TERMINO DE PROCESO ELIMINACIÓN LME 00 ["+ Util.getTiempoRestante(inicio12) + " Milisegundos]");
			}
		} catch (Throwable e) {
			log.error(e.getClass() + "; " + e.getMessage());
		}
		log.info("TERMINO DEL CICLO COMPLETO DE PROCESOS 'LME' ["+ (new Date()) + "] DEMORO ["+ Util.getTiempoRestante(inicioTotal) + " Milisegundos]");
	}

	public MessageResources getMessages() {
		return messages;
	}

	public void setMessages(MessageResources messages) {
		this.messages = messages;
	}

	public static void setContinuarCiclo(boolean continuarCiclo) {
		LmeCiclo.continuarCiclo = continuarCiclo;
	}

	public static boolean isContinuarCiclo() {
		return continuarCiclo;
	}
}
