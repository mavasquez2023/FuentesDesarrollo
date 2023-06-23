package cl.araucana.ws;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import cl.araucana.to.ParamTO;
import cl.laaraucana.sms.ws.MessageOutput;

public class ServicioSMSInterno {
	/*
	 * public boolean enviarSMS(String telefono, String mensaje) {
	 * System.out.println("ServicioSMSInterno: enviando SMS a telefono: " +
	 * telefono + ", mensaje: " + mensaje); return enviarSMS(telefono, mensaje,
	 * "", ""); }
	 */
	Logger log = Logger.getLogger(this.getClass());
	
	public boolean enviarSMS(String telefono, String mensaje, String servicio, String cod_negocio) {
		boolean resultado = false;
		String prefijo="";
		log.info("Enviar SMS, celular:" + telefono + ", codigo_negocio=" + cod_negocio + ", servicio=" + servicio);
		if (telefono != null && mensaje != null) {
			telefono= telefono.replace("+", "");
			if (!telefono.equals("") && !mensaje.equals("") && telefono.length()>=8 ) {
				try {
					String rut="0-0";
					if(telefono.length()==8){
						prefijo="569";
					}
					if(telefono.length()==9){
						prefijo="56";
					}
					ClienteSMSService clientesms= new ClienteSMSService();
					MessageOutput output= clientesms.sendMesage(rut, prefijo+telefono, mensaje);
					if(output.getStatusCode().equals("SENT")){
						resultado=true;
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.error("Error General, mensaje=" + e.getMessage());
				}
			} else {
				log.warn("ServicioSMSInterno enviarSMS no se han definido correctamente los parametros de entrada: celular, mensaje, servicio. (sin datos)");
			}
		} else {
			log.warn("ServicioSMSInterno enviarSMS no se han definido correctamente los parametros de entrada: celular, mensaje, servicio. (datos nulos)");
		}
		return resultado;
	}
}
