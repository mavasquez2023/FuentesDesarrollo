/**
 * 
 */
package cl.araucana.ldap.ws;


import org.apache.log4j.Logger;
import cl.araucana.ldap.ws.util.Configuraciones;
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
	MessageOutput output=null;
	try { 

		String ep = Configuraciones.getConfig("sms.url");
		String usuario = Configuraciones.getConfig("sms.usuario");
		String clave = Configuraciones.getConfig("sms.clave");
		int timeout= Integer.parseInt(Configuraciones.getConfig("sms.timeout"));
		
		AraucanaSMSPortBindingStub stub= new AraucanaSMSPortBindingStub();
		stub._setProperty(AraucanaSMSPortBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		stub.setTimeout(timeout);
		
		MessageInput input= new MessageInput();
		input.setUsername(usuario);
		input.setPassword(clave);
		input.setRut(rut.split("-")[0]);
		input.setDv(rut.split("-")[1]);
		input.setPhone(phone);
		input.setMessage(mensaje);
		input.setUrlCondition("");
		input.setUrlText("");
		
		output= stub.sendSMS(input);
		return output;
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		logger.warn("Error en servicio SMS, mensaje: " + e1.getMessage());
		//e1.printStackTrace();
	}
	
	return output;

	}
	
}
