package cl.araucana.autoconsulta.ws.pagoenexceso;

import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class PagoEnExcesoClient {

	/**
	 * Implementa cliente de servicio ConsultaPagoEnExceso
	 * @param rut
	 * @return
	 */
	//private static Logger logger = Logger.getLogger(GetConsultaPagoEnExceso.class);
	
	public ConsultaPagoEnExcesoOut consultarPagoEnExceso(String rut) throws RemoteException{
		ConsultaPagoEnExcesoOut salida = new ConsultaPagoEnExcesoOut();
		ResourceBundle messages =  ResourceBundle.getBundle("resources.application"); 
		try {
			ConsultaPagoEnExcesoDelegateProxy proxy = new  ConsultaPagoEnExcesoDelegateProxy();
			//Leer desde properties
			proxy.setEndpoint(messages.getString("ws.consulta.pagoenexceso"));
			salida = proxy.consultarPagoEnExceso(rut);
		} catch (Exception e) {
			//logger.error("Error en servicio ConsultaPagoEnExceso", e);
			salida.setCodRespuesta("5");
			salida.setMensaje("Error al consultar pagos en exceso");
			throw new RemoteException("Error al consultar pagos en exceso"); 
		}
		return salida;
	}
	
}
