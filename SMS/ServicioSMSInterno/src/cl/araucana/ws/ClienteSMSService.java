/**
 * 
 */
package cl.araucana.ws;

import java.rmi.RemoteException;
import java.util.Properties;

import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;

import cl.laaraucana.sms.ws.AraucanaSMSPortBindingStub;
import cl.laaraucana.sms.ws.MessageInput;
import cl.laaraucana.sms.ws.MessageOutput;

/**
 * @author IBM Software Factory
 *
 */
public class ClienteSMSService {
	Logger logger = Logger.getLogger(this.getClass());
	
public MessageOutput sendMesage(String rut, String phone, String mensaje) throws AxisFault { 
	try { 
		Properties properties = new Properties();
		properties.load(ClienteSMSService.class.getClassLoader().getResourceAsStream("ws.properties"));
		String ep = properties.getProperty("sms.url");
		String usuario = properties.getProperty("sms.usuario");
		String clave = properties.getProperty("sms.clave");

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
