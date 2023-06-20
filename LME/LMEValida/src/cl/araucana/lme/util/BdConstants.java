package cl.araucana.lme.util;

import java.util.Map;

import org.apache.log4j.Logger;

import cl.araucana.lme.svc.IAS400Svc_LME;
import cl.araucana.lme.svc.SvcFactory_LME;
import cl.araucana.lme.svc.exception.SvcException;


public class BdConstants {
	private static Logger logger = Logger.getLogger(BdConstants.class);
	public String DESTINO_CORREO_NOTIF;
	public String COPIA_CORREO_NOTIF;
	public String ASUNTO_CORREO_NOTIF;
	public String CUERPO_CORREO_NOTIF;
	public String MAIL_SESSION;
	public String FECHA_CONSUMO_DEV_VAL;
	
	private static BdConstants instance;
	
	/**
	 * Solamente crea instancia de parametros, la carga se realiza manualmente
	 * @return
	 */
	public static BdConstants getInstance(){
		if(instance == null)
			instance =  new BdConstants();
		return instance;
	}
	
	public BdConstants(){
		//cargarParametros(); //ahora se cargan manualmente
	}
	
	public void cargarParametros() throws SvcException{
		//Obtiene parámetros desde propertie para notificar error
		DESTINO_CORREO_NOTIF = Configuraciones.getConfig("email.destinatarios");
		COPIA_CORREO_NOTIF = Configuraciones.getConfig("email.copia");
		ASUNTO_CORREO_NOTIF = Configuraciones.getConfig("email.asunto");
		CUERPO_CORREO_NOTIF = Configuraciones.getConfig("email.cuerpo");
		MAIL_SESSION = Configuraciones.getConfig("email.mailSession");
		
		IAS400Svc_LME svc_a = SvcFactory_LME.getAS400Svc_LME();
		Map parametros = svc_a.getParametrosBd();
		
		try {
			DESTINO_CORREO_NOTIF = (String) parametros.get("DESTINO_CORREO_NOTIF");
			COPIA_CORREO_NOTIF = (String) parametros.get("COPIA_CORREO_NOTIF");
			ASUNTO_CORREO_NOTIF = (String) parametros.get("ASUNTO_CORREO_VALIDA");
			CUERPO_CORREO_NOTIF = (String) parametros.get("CUERPO_CORREO_VALIDA");
			MAIL_SESSION = (String) parametros.get("MAIL_SESSION");
			
		} catch (Exception e) {
			e.printStackTrace();
			//throw new SvcException("Error al establecer parámetros desde BD");
		}
		
		logger.info("PARAMETROS: ");
		logger.info("DESTINO_CORREO_NOTIF: " + DESTINO_CORREO_NOTIF);
		logger.info("COPIA_CORREO_NOTIF: " + COPIA_CORREO_NOTIF);
		logger.info("ASUNTO_CORREO_NOTIF: " + ASUNTO_CORREO_NOTIF);
		logger.info("CUERPO_CORREO_NOTIF: " + CUERPO_CORREO_NOTIF);
		logger.info("MAIL_SESSION: " + MAIL_SESSION);
		
		
		try {
			FECHA_CONSUMO_DEV_VAL= (String) parametros.get("DATE_CONSUMO_DEV_VAL");
		} catch (Exception e) {
			throw new SvcException("Error al establecer parámetros desde BD");
		}

		logger.info("FECHA_CONSUMO_DEV_VAL: " + FECHA_CONSUMO_DEV_VAL);
	}
}
