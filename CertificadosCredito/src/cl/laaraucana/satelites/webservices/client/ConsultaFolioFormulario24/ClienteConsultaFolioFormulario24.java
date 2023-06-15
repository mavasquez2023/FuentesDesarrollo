package cl.laaraucana.satelites.webservices.client.ConsultaFolioFormulario24;

import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.integracion.ConsultaFolioFormulario24.ConsultaFolioFormulario24EntradaVO;
import cl.laaraucana.satelites.integracion.ConsultaFolioFormulario24.ConsultaFolioFormulario24PortProxy;
import cl.laaraucana.satelites.integracion.ConsultaFolioFormulario24.ConsultaFolioFormulario24SalidaVO;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.ConsultaFolioFormulario24.VO.EntradaConsultaFolioFormulario24;
import cl.laaraucana.satelites.webservices.client.ConsultaFolioFormulario24.VO.SalidaConsultaFolioFormulario24;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;

public class ClienteConsultaFolioFormulario24 implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		
		SalidaConsultaFolioFormulario24 salidaVO = new SalidaConsultaFolioFormulario24();
		
		
		EntradaConsultaFolioFormulario24 entradaVO = (EntradaConsultaFolioFormulario24) entrada;
		logger.debug("<< Ingreso a ClienteConsultaFolioFormulario24 con fecha: "+entradaVO.getFechaOriginacionDeCredito());
		
		ConsultaFolioFormulario24EntradaVO entradaWS = new ConsultaFolioFormulario24EntradaVO();
		entradaWS.setFechaOriginacionDeCredito(entradaVO.getFechaOriginacionDeCredito());
		entradaWS.setFlag1(entradaVO.getFlag1());
		
		ConsultaFolioFormulario24PortProxy ws = new ConsultaFolioFormulario24PortProxy();
		ws._getDescriptor().setEndpoint(Configuraciones.getConfig("ep.FolioFormulario24"));
		
		logger.debug("<< LLamamos al ws ClienteConsultaFolioFormulario24");

		ConsultaFolioFormulario24SalidaVO respuesta = new ConsultaFolioFormulario24SalidaVO();
		try {
			respuesta = ws.consultaFolioFormulario24(entradaWS);
		} catch (Exception e) {
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en servicio ClienteConsultaFolioFormulario24: compruebe el servicio");
			return salidaVO;
		}
		
		
		if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
			salidaVO = mapear(respuesta,entradaVO.getFechaOriginacionDeCredito());
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("Carga de Folio formulario 24 en ClienteConsultaFolioFormulario24 OK");
			logger.debug("Carga de Folio formulario 24 en ClienteConsultaFolioFormulario24 OK, codigo error= "+0);
		}else{
			if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)){
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
				salidaVO.setMensaje("ClienteConsultaFolioFormulario24 OK. Lista vacia"+respuesta.getLog().getId());
				logger.debug("ClienteConsultaFolioFormulario24 Código error= "+0+". "+salidaVO.getMensaje());
			}else{
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje("Error en servicio ConsultaFolioFormulario24: "+respuesta.getLog().getId());
				logger.debug("Código error= "+1+". "+salidaVO.getMensaje());
			}
		}

		logger.debug(">> Salida ClienteConsultaFolioFormulario24");
		return salidaVO;
	}
	
	private static SalidaConsultaFolioFormulario24 mapear (ConsultaFolioFormulario24SalidaVO entrada, String fecha){
		SalidaConsultaFolioFormulario24 salidaVO = new SalidaConsultaFolioFormulario24();;
		salidaVO.setFolioFormulario24(entrada.getFolioFormulario24());
		salidaVO.setFechaOriginacionDeCredito(fecha);
		return salidaVO;
	}

}
