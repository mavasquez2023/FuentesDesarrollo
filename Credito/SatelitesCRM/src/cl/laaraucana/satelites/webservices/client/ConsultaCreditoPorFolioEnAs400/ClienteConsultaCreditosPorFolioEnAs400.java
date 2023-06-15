package cl.laaraucana.satelites.webservices.client.ConsultaCreditoPorFolioEnAs400;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.integracion.ConsultaCreditosPorFolioEnAs400.ConsultaCreditoPorFolioEnAs400PortProxy;
import cl.laaraucana.satelites.integracion.ConsultaCreditosPorFolioEnAs400.ConsultaCreditoPorFolioEntradaVO;
import cl.laaraucana.satelites.integracion.ConsultaCreditosPorFolioEnAs400.ConsultaCreditoPorFolioSalidaVO;
import cl.laaraucana.satelites.integracion.ConsultaCreditosPorFolioEnAs400.DetalleConsultaCreditoPorFolioSalidaVO;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditoPorFolioEnAs400.VO.EntradaConsultaCreditoPorFolioEnAs400VO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditoPorFolioEnAs400.VO.SalidaConsultaCreditoPorFolioEnAs400VO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditoPorFolioEnAs400.VO.SalidaListaConsultaCreditoPorFolioEnAs400VO;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;

public class ClienteConsultaCreditosPorFolioEnAs400 implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {

		EntradaConsultaCreditoPorFolioEnAs400VO entradaVO = (EntradaConsultaCreditoPorFolioEnAs400VO) entrada;
		SalidaListaConsultaCreditoPorFolioEnAs400VO salidaVO=new SalidaListaConsultaCreditoPorFolioEnAs400VO();
//		try {
			logger.debug("<< Ingreso al ClienteConsultaCreditosPorFolioEnAs400 con el folio: "+entradaVO.getFolioCredito());
			
			ConsultaCreditoPorFolioEntradaVO entradaWs = new ConsultaCreditoPorFolioEntradaVO();
			entradaWs.setFolioCredito(entradaVO.getFolioCredito());
			entradaWs.setTipoCredito(entradaVO.getTipoCredito());
			
			ConsultaCreditoPorFolioEnAs400PortProxy ws = new ConsultaCreditoPorFolioEnAs400PortProxy();
			ws._getDescriptor().setEndpoint(Configuraciones.getConfig("ep.CreditoDetalleAs400"));
			logger.debug("LLamada a ws ClienteConsultaCreditosPorFolioEnAs400 con entrada :" + entradaWs.getFolioCredito());
			
//<=====	Codigo nuevo, con validacion
			ConsultaCreditoPorFolioSalidaVO respuesta = new ConsultaCreditoPorFolioSalidaVO();
			
			try {
				respuesta = ws.consultaCreditoPorFolioEnAs400(entradaWs);				
			} catch (Exception e) {
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje("Error en servicio ClienteConsultaCreditosPorFolioEnAs400: compruebe el servicio");
				return salidaVO;
			}
			
			if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
				salidaVO = mapear(respuesta);
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
				salidaVO.setMensaje("Carga de creditos ClienteConsultaCreditosPorFolioEnAs400 OK");
				logger.debug("Carga de creditos ClienteConsultaCreditosPorFolioEnAs400 OK, codigo error= "+0);
			}else{
				if(respuesta.getResultCode().equals("1")){
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
					salidaVO.setMensaje("ClienteConsultaCreditosPorFolioEnAs400 OK. El folio no contiene cuotas");
					logger.debug("Código error= "+0+". "+salidaVO.getMensaje());
				}else{
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
					salidaVO.setMensaje("Error en servicio ClienteConsultaCreditosPorFolioEnAs400: "+ respuesta.getLog().getId());
					logger.debug("Código error= "+1+". "+salidaVO.getMensaje());
				}
			}
//<=====	Codigo nuevo, con validacion



//<=====	Codigo antiguo, sin validacion
//		logger.debug("<< LLamamos al ws ClienteConsultaCreditosPorFolioEnAs400");
//		salidaVO = mapear(ws.consultaCreditoPorFolioEnAs400(entradaWs), entradaWs);
//		logger.debug(">> Salida llamada del ws ClienteConsultaCreditosPorFolioEnAs400");
//		
//		salidaVO.setCodigoError("0");
//		salidaVO.setMensaje("Carga ClienteConsultaCreditosPorFolioEnAs400 OK");
//
//	} catch (Exception e) {
//		salidaVO = new SalidaListaConsultaCreditoPorFolioEnAs400VO();
//		salidaVO.setCodigoError("1");
//		salidaVO.setMensaje("Excepcion en ClienteConsultaCreditosPorFolioEnAs400: " + e.getMessage());
//	}
//<=====	Codigo antiguo, sin validacion
		
		logger.debug(">> Salida ClienteConsultaCreditosPorFolioEnAs400");
		return salidaVO;
	}
	
    private SalidaListaConsultaCreditoPorFolioEnAs400VO mapear(ConsultaCreditoPorFolioSalidaVO entrada){
    	SalidaListaConsultaCreditoPorFolioEnAs400VO salidaVO = new SalidaListaConsultaCreditoPorFolioEnAs400VO();
    	SalidaConsultaCreditoPorFolioEnAs400VO cuotas=null;
    	List<SalidaConsultaCreditoPorFolioEnAs400VO> listaCuotas = new ArrayList<SalidaConsultaCreditoPorFolioEnAs400VO>();
    	
    	for (DetalleConsultaCreditoPorFolioSalidaVO detalle : entrada.getDetalle()) {
			cuotas = new SalidaConsultaCreditoPorFolioEnAs400VO();
			
			cuotas.setEstadoCuota(detalle.getEstadoCuota());
			cuotas.setFechaVencimiento(detalle.getFechaVencimiento());
			cuotas.setMontoAbonado(detalle.getMontoAbonado());
			cuotas.setMontoCapitalAmortizado(detalle.getMontoCapitalAmortizado());
			cuotas.setMontoGravamen(detalle.getMontoGravamen());
			cuotas.setMontoInteres(detalle.getMontoInteres());
			cuotas.setMontoSeguros(detalle.getMontoSeguros());
			cuotas.setNumeroCuota(detalle.getNumeroCuota());
			cuotas.setSaldoCapital(detalle.getSaldoCapital());
			listaCuotas.add(cuotas);
		}
    	
    	salidaVO.setListaCuotas(listaCuotas);
    	return salidaVO;
    }

}

