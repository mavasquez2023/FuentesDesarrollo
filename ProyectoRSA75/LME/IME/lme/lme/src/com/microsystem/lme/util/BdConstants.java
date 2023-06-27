package com.microsystem.lme.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.microsystem.lme.svc.IAS400Svc_LME;
import com.microsystem.lme.svc.SvcFactory_LME;
import com.microsystem.lme.svc.exception.SvcException;

public class BdConstants {
	
	public int TIEMPO_REPOSO_JOB;
	public int ULTIMO_ESTADO_JOB;
	public int DESPLAZAMIENTO_IMED;
	public int DESPLAZAMIENTO_IMED_RESP;
	public int DESPLAZAMIENTO_MEDIPASS;
	public int DESPLAZAMIENTO_MEDIPASS_RESP;
	public String TIMEZONE_CONFIG;
	public String DESTINO_CORREO_NOTIF;
	public String COPIA_CORREO_NOTIF;
	public String ASUNTO_CORREO_NOTIF;
	public String CUERPO_CORREO_NOTIF;
	public String MAIL_SESSION;

	private static BdConstants instance;
	private Map bitacora_reconsumo= new HashMap();
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
			ASUNTO_CORREO_NOTIF = (String) parametros.get("ASUNTO_CORREO_NOTIF");
			CUERPO_CORREO_NOTIF = (String) parametros.get("CUERPO_CORREO_NOTIF");
			MAIL_SESSION = (String) parametros.get("MAIL_SESSION");
		} catch (Exception e) {
			e.printStackTrace();
			//throw new SvcException("Error al establecer parámetros desde BD");
		}
		
		System.out.println("PARAMETROS: ");
		System.out.println("DESTINO_CORREO_NOTIF: " + DESTINO_CORREO_NOTIF);
		System.out.println("COPIA_CORREO_NOTIF: " + COPIA_CORREO_NOTIF);
		System.out.println("ASUNTO_CORREO_NOTIF: " + ASUNTO_CORREO_NOTIF);
		System.out.println("CUERPO_CORREO_NOTIF: " + CUERPO_CORREO_NOTIF);
		System.out.println("MAIL_SESSION: " + MAIL_SESSION);
		
		try {
			TIEMPO_REPOSO_JOB = Integer.parseInt((String) parametros.get("TIEMPO_REPOSO_JOB"));
			ULTIMO_ESTADO_JOB = Integer.parseInt((String) parametros.get("ULTIMO_ESTADO_JOB"));
			DESPLAZAMIENTO_IMED = Integer.parseInt((String) parametros.get("DESPLAZAMIENTO_IMED"));
			DESPLAZAMIENTO_IMED_RESP = Integer.parseInt((String) parametros.get("DESPLAZAMIENTO_IMED_RESP"));
			DESPLAZAMIENTO_MEDIPASS = Integer.parseInt((String) parametros.get("DESPLAZAMIENTO_MEDIPASS"));
			DESPLAZAMIENTO_MEDIPASS_RESP = Integer.parseInt((String) parametros.get("DESPLAZAMIENTO_MEDIPASS_RESP"));
			TIMEZONE_CONFIG = (String) parametros.get("TIMEZONE_CONFIG");
		} catch (Exception e) {
			throw new SvcException("Error al establecer parámetros desde BD");
		}
		
		System.out.println("TIEMPO_REPOSO_JOB: " + TIEMPO_REPOSO_JOB);
		System.out.println("ULTIMO_ESTADO_JOB: " + ULTIMO_ESTADO_JOB);
		System.out.println("DESPLAZAMIENTO_IMED: " + DESPLAZAMIENTO_IMED);
		System.out.println("DESPLAZAMIENTO_IMED_RESP: " + DESPLAZAMIENTO_IMED_RESP);
		System.out.println("DESPLAZAMIENTO_MEDIPASS: " + DESPLAZAMIENTO_MEDIPASS);
		System.out.println("DESPLAZAMIENTO_MEDIPASS_RESP: " + DESPLAZAMIENTO_MEDIPASS_RESP);
		System.out.println("TIMEZONE_CONFIG: " + TIMEZONE_CONFIG);
	}

	/**
	 * @return the bitacora_reconsumo
	 */
	public Map getBitacora_reconsumo() {
		return bitacora_reconsumo;
	}

	/**
	 * @param bitacora_reconsumo the bitacora_reconsumo to set
	 */
	public void setBitacora_reconsumo(Map bitacora_reconsumo) {
		this.bitacora_reconsumo = bitacora_reconsumo;
	}
	
	public void addBitacora_reconsumo(String fecha, String registros){
		this.bitacora_reconsumo.put(fecha, registros);
	}
	
	public String toString(){
		StringBuffer salida= new StringBuffer();
		for (Iterator iterator = this.bitacora_reconsumo.keySet().iterator(); iterator.hasNext();) {
			String fecha = (String) iterator.next();
			salida.append(fecha + ": " + this.bitacora_reconsumo.get(fecha) + " registros insertados<br>");
		}
		return salida.toString();
	}
}
