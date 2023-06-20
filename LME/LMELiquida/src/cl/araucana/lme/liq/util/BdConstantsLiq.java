package cl.araucana.lme.liq.util;

import java.util.Map;

import org.apache.log4j.Logger;

import cl.araucana.lme.liq.svc.IAS400Svc_LIQ;
import cl.araucana.lme.liq.svc.SvcFactory_LIQ;
import cl.araucana.lme.liq.svc.exception.SvcException;


public class BdConstantsLiq {
	private static Logger logger = Logger.getLogger(BdConstantsLiq.class);
	public String DESTINO_CORREO_NOTIF;
	public String COPIA_CORREO_NOTIF;
	public String ASUNTO_CORREO_NOTIF;
	public String CUERPO_CORREO_NOTIF;
	public String MAIL_SESSION;
	public String FECHA_CONSUMO_DEV_VAL;
	
	private static BdConstantsLiq instance;
	
	/**
	 * Solamente crea instancia de parametros, la carga se realiza manualmente
	 * @return
	 */
	public static BdConstantsLiq getInstance(){
		if(instance == null)
			instance =  new BdConstantsLiq();
		return instance;
	}
	
	public BdConstantsLiq(){
		//cargarParametros(); //ahora se cargan manualmente
	}
	
	public void cargarParametros() throws SvcException{
		//Obtiene parámetros desde propertie para notificar error
		DESTINO_CORREO_NOTIF = ConfiguracionesLiq.getConfig("email.destinatarios");
		COPIA_CORREO_NOTIF = ConfiguracionesLiq.getConfig("email.copia");
		ASUNTO_CORREO_NOTIF = ConfiguracionesLiq.getConfig("email.asunto");
		CUERPO_CORREO_NOTIF = ConfiguracionesLiq.getConfig("email.cuerpo");
		MAIL_SESSION = ConfiguracionesLiq.getConfig("email.mailSession");
		
		IAS400Svc_LIQ svc_a = SvcFactory_LIQ.getAS400Svc_LME();
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
		
	}
}
