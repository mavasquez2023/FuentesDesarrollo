package cl.laaraucana.capaservicios.webservices.client;

import java.io.IOException;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.log4j.Logger;

import cl.laaraucana.capaservicios.util.Constantes;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

public class ServicioSMSInternoCliente {
	static Logger logger = Logger.getLogger(ServicioSMSInternoCliente.class); 
	
	/**
	 * Implementación del cliente del servicio de envío de SMS
	 * @param telefono
	 * @param mensaje
	 * @return
	 */
	public static boolean enviarSMS(String telefono, String mensaje) {
		boolean respuesta = false;
		try {
			String servicio = Constantes.COD_SERV_SMS;
			String endpoint = Constantes.EP_SOL_SMS;
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName("enviarSMS");
			call.addParameter("telefono", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("mensaje", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("servicio", XMLType.XSD_STRING, ParameterMode.IN);
			call.setReturnType(XMLType.XSD_STRING);
			String r = (String) call.invoke(new Object[] { telefono, mensaje, servicio });
			if(r.equals("true")){
				logger.info("Mensaje SMS enviado correctamente");
				return true;
			}
		} catch (IOException e) {
			logger.error("Error al enviar SMS: ",e);
		} catch (ServiceException e) {
			logger.error("Error al enviar SMS: ",e);
		}
		return respuesta;
	}
	
}
