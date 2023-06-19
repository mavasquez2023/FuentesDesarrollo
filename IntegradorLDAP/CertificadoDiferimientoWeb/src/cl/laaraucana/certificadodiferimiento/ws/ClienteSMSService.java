/**
 * 
 */
package cl.laaraucana.certificadodiferimiento.ws;

import org.apache.log4j.Logger;
import cl.laaraucana.certificadodiferimiento.util.Configuraciones;
import cl.laaraucana.sms.ws.AraucanaSMSPortBindingStub;
import cl.laaraucana.sms.ws.MessageInput;
import cl.laaraucana.sms.ws.MessageOutput;

/**
 * @author IBM Software Factory
 *
 */
public class ClienteSMSService {
	Logger logger = Logger.getLogger(this.getClass());
	
public MessageOutput sendMesage(String rut, String phone, String mensaje) { 
	try { 

		String ep = Configuraciones.getConfig("sms.url");
		String usuario = Configuraciones.getConfig("sms.usuario");
		String clave = Configuraciones.getConfig("sms.clave");

		AraucanaSMSPortBindingStub stub= new AraucanaSMSPortBindingStub();
		stub._setProperty(AraucanaSMSPortBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);

		MessageInput input= new MessageInput();
		input.setUsername(usuario);
		input.setPassword(clave);
		input.setRut(rut.split("-")[0]);
		input.setDv(rut.split("-")[1]);
		input.setPhone(phone);
		input.setMessage(mensaje);
		input.setUrlCondition("");
		input.setUrlText("");
		
		MessageOutput output= stub.sendSMS(input);
		return output;
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		logger.warn("Error en servicio SMS, mensaje: " + e1.getMessage());
		e1.printStackTrace();
	}
	
	return null;

	}
	
}
