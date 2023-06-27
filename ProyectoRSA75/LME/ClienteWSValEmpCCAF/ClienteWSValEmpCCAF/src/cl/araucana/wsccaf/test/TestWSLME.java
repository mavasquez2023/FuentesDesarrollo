/**
 * 
 */
package cl.araucana.wsccaf.test;


import java.rmi.RemoteException;
import lme.cl.gov.lme.www.LMEValEmpCCAFRequest;
import lme.cl.gov.lme.www.LMEValEmpCCAFResponse;
import lme.cl.gov.lme.www.WSLMEValEmpCCAFProxy;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import cl.araucana.wsccaf.mail.EnviaMail;
import cl.araucana.wsccaf.mail.FormatoMail;

/**
 * @author usist199
 *
 */
public class TestWSLME {

	static ResourceBundle paramProperties = ResourceBundle.getBundle("etc/parametros");
	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	private static Logger log = Logger.getLogger(TestWSLME.class);
	/**
	 * @param args
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, Exception {
		
		//Se rescata parámetros de consulta
		String codigoOperador=paramProperties.getString("app.param.codigoOperador");
		String claveOperador=paramProperties.getString("app.param.claveOperador");
		String codigoCCAF=paramProperties.getString("app.param.codigoCCAF");
		String rut= paramProperties.getString("app.param.rutTrabajador");
		String dvTrabajador= paramProperties.getString("app.param.dvTrabajador");
		String endpoint= paramProperties.getString("app.ws.endpoint");
		log.info("Endpoint:" + endpoint);
		String server= endpoint.substring(5);
		Integer rutTrabajador= new Integer(Integer.parseInt(rut));
		
		//Se rescata lista de correo usuarios en caso de error
		String mailerror = mailProperties.getString("app.mail.usuario.error");
		
		LMEValEmpCCAFRequest request= new LMEValEmpCCAFRequest(codigoOperador, claveOperador, codigoCCAF, rutTrabajador, dvTrabajador);
		WSLMEValEmpCCAFProxy proxy= new WSLMEValEmpCCAFProxy();
		proxy.setEndpoint(endpoint);
		log.info("Endpoint: " + proxy.getEndpoint());
		try{
			proxy.wait(Long.parseLong(paramProperties.getString("app.param.timeout")));
		}catch (Exception e) {
			log.info("no se pudo setear timeout a consulta");
		}
		LMEValEmpCCAFResponse respuesta=null;
		try {
			
			respuesta= proxy.LMEValEmpCCAF(request);
			if(respuesta!=null){
				log.info("Estado:" + respuesta.getEstado());
				log.info("Global Estado:" + respuesta.getGloEstado());
				log.info("Número de Empleadores informado:" + respuesta.getListaEmpleadores().length);
				
				if(respuesta.getEstado().intValue()==1 && respuesta.getGloEstado().equals("1")){
					log.info("WS sin conexión a DB2");
					String textomail=FormatoMail.obtenerTextoMailWSError().replaceAll("#server#", server);
					EnviaMail.enviarMail("Error en conexión WSValEmpCCAF. ",mailerror, null,textomail);
				}
			}
			
		} catch (RemoteException e) {
			// TODO Bloque catch generado automáticamente
			log.info("WS sin conexión");
			String textomail=FormatoMail.obtenerTextoMailWSError().replaceAll("#server#", server);
			EnviaMail.enviarMail("Error en conexión WSValEmpCCAF. ",mailerror, null,textomail);
			//e.printStackTrace();
		}
		
		log.info("FIN");
	}

}
