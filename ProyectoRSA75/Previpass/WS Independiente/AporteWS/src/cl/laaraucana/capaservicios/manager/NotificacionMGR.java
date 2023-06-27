package cl.laaraucana.capaservicios.manager;

import org.apache.log4j.Logger;

import cl.laaraucana.capaservicios.util.Constantes;
import cl.laaraucana.capaservicios.webservices.client.ServicioSMSInternoCliente;
//import cl.laaraucana.capaservicios.webservices.client.SolicitudSMS.SolicitudSMSClient;
//import cl.laaraucana.capaservicios.webservices.client.SolicitudSMS.vo.SolicitudSMSEntradaVO;
//import cl.laaraucana.capaservicios.webservices.client.SolicitudSMS.vo.SolicitudSMSSalidaVO;

public class NotificacionMGR {
	private final Logger logger = Logger.getLogger(this.getClass());
	  /**
	   * Envia SMS a cliente notificando que debe ir a retirar cheque a sucursal
	   * @param email
	   */
		public void enviarSMSCliente(String nroCelular){
			if(Constantes.MSG_SMS_ERROR == null || Constantes.MSG_SMS_ERROR.isEmpty()){
				logger.error("Error al enviar SMS de notificacion de Error, el parametro mensaje esta vacio");
				return;
			}
			boolean resp = ServicioSMSInternoCliente.enviarSMS(nroCelular, Constantes.MSG_SMS_ERROR);
			if(!resp){
				logger.error("Error al enviar SMS de notificación de transferencia fallida");
			}
		}
	
//	  public void enviarSMSCliente(String nroCelular){
//		  if(Constantes.MSG_SMS_ERROR == null || Constantes.MSG_SMS_ERROR.isEmpty()){
//			  logger.error("Error al enviar SMS de notificacion de Error, el parametro mensaje esta vacio");
//			  return;
//		  }
//		  
//	    try {
//	      SolicitudSMSClient smsClient = new SolicitudSMSClient();
//	      SolicitudSMSEntradaVO entrada = new SolicitudSMSEntradaVO();
//	      entrada.setNumero(nroCelular);
//	      //Mensaje de error en transferencia
//	      entrada.setMensaje(Constantes.MSG_SMS_ERROR);
//	      SolicitudSMSSalidaVO salida =  (SolicitudSMSSalidaVO) smsClient.callNewService(entrada);
//	      if(salida.getCodigoError().equals(Constantes.COD_RESPUESTA_ERROR)){
//	        logger.error("Error al enviar SMS de notificación de transferencia fallida");
//	      }
//	    } catch (Exception e) {
//	      logger.debug("::Error al enviar SMS de notificación cliente");
//	    }
//	  }
	  
}
