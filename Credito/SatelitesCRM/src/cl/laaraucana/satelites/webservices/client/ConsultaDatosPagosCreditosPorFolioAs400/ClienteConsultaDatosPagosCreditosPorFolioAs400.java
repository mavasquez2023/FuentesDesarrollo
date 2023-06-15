package cl.laaraucana.satelites.webservices.client.ConsultaDatosPagosCreditosPorFolioAs400;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.integracion.ConsultaDatosPagosCreditosPorFolioAs400.ConsultaDatosPagosCreditosPorFolioAs400EntradaVO;
import cl.laaraucana.satelites.integracion.ConsultaDatosPagosCreditosPorFolioAs400.ConsultaDatosPagosCreditosPorFolioAs400ListaSalidaVO;
import cl.laaraucana.satelites.integracion.ConsultaDatosPagosCreditosPorFolioAs400.ConsultaDatosPagosCreditosPorFolioAs400PortProxy;
import cl.laaraucana.satelites.integracion.ConsultaDatosPagosCreditosPorFolioAs400.DetalleConsultaDatosPagosCreditosPorFolioAs400SalidaVO;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosPagosCreditosPorFolioAs400.VO.EntradaConsultaDatosPagosCreditosVO;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosPagosCreditosPorFolioAs400.VO.SalidaConsultaDatosPagosCreditosPorFolioVO;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosPagosCreditosPorFolioAs400.VO.SalidaListaConsultaDatosPagosCreditosPorFolioVO;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;


public class ClienteConsultaDatosPagosCreditosPorFolioAs400 implements WSInterface {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		
		logger.debug("<< Ingreso a ClienteConsultaDatosPagosCreditosPorFolioAs400");
		EntradaConsultaDatosPagosCreditosVO entradaVO = (EntradaConsultaDatosPagosCreditosVO) entrada;
		SalidaListaConsultaDatosPagosCreditosPorFolioVO salidaVO = new SalidaListaConsultaDatosPagosCreditosPorFolioVO();
		
//		try {
			ConsultaDatosPagosCreditosPorFolioAs400PortProxy ws = new ConsultaDatosPagosCreditosPorFolioAs400PortProxy();
			ws._getDescriptor().setEndpoint(Configuraciones.getConfig("ep.CreditoDatosPagosAs400"));
			
			ConsultaDatosPagosCreditosPorFolioAs400EntradaVO entradaWS = new ConsultaDatosPagosCreditosPorFolioAs400EntradaVO();
			entradaWS.setFolio(entradaVO.getFolio());
			entradaWS.setOficinaProceso(entradaVO.getOficinaProceso());
			logger.debug("LLamada a ws ClienteConsultaDatosPagosCreditosPorFolioAs400 con rut de entrada :" + entradaWS.getFolio());
			
//<=====	Codigo nuevo, con validacion
			ConsultaDatosPagosCreditosPorFolioAs400ListaSalidaVO respuesta = new ConsultaDatosPagosCreditosPorFolioAs400ListaSalidaVO();
			try {
				respuesta=ws.consultaDatosPagosCreditosPorFolioAs400(entradaWS);
			} catch (Exception e) {
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje("Error en servicio ClienteConsultaDatosPagosCreditosPorFolioAs400: compruebe el servicio");
				return salidaVO;
			}
			
			if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
				salidaVO = mapear(respuesta);
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
				salidaVO.setMensaje("Carga de creditos ClienteConsultaDatosPagosCreditosPorFolioAs400 OK");
				logger.debug("Carga de creditos ClienteConsultaDatosPagosCreditosPorFolioAs400 OK, codigo error= "+0);
			}else{
				if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)){
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
					salidaVO.setMensaje("Carga de creditos ClienteConsultaDatosPagosCreditosPorFolioAs400 OK. "+respuesta.getLog().getId());
					logger.debug("Código error= "+0+". "+salidaVO.getMensaje());
				}else{
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
					salidaVO.setMensaje("Error en servicio ClienteConsultaDatosPagosCreditosPorFolioAs400: "+respuesta.getLog().getId());
					logger.debug("ClienteConsultaDatosPagosCreditosPorFolioAs400 Código error= "+1+". "+salidaVO.getMensaje());
				}
			}
//<=====	Codigo nuevo, con validacion



//<=====	Codigo antiguo, sin validacion
//			salidaVO = mapear(ws.consultaDatosPagosCreditosPorFolioAs400(entradaWS));
//			
//			salidaVO.setCodigoError("0");
//			salidaVO.setMensaje("Carga de cuotas As400 OK");
//			
//		} catch (Exception e) {
//			salidaVO.setCodigoError("1");
//			salidaVO.setMensaje(e.getMessage());
//		}
//<=====	Codigo antiguo, sin validacion
		logger.debug(">> Salida ClienteConsultaDatosPagosCreditosPorFolioAs400");
		return salidaVO;
	}
	
	private SalidaListaConsultaDatosPagosCreditosPorFolioVO mapear(ConsultaDatosPagosCreditosPorFolioAs400ListaSalidaVO entrada){
		SalidaListaConsultaDatosPagosCreditosPorFolioVO salidaVO = new SalidaListaConsultaDatosPagosCreditosPorFolioVO();
		List<SalidaConsultaDatosPagosCreditosPorFolioVO> listaCuotas = new ArrayList<SalidaConsultaDatosPagosCreditosPorFolioVO>();
		SalidaConsultaDatosPagosCreditosPorFolioVO cuotas = null; 
		
		
		for (DetalleConsultaDatosPagosCreditosPorFolioAs400SalidaVO detalle : entrada.getListaCuotas()) {
			cuotas = new SalidaConsultaDatosPagosCreditosPorFolioVO();
			
			cuotas.setEstadoAlPago(detalle.getEstadoAlPago());
			cuotas.setFechaUltimoPago(detalle.getFechaUltimoPago());
			cuotas.setFechaVencimiento(detalle.getFechaVencimiento());
			cuotas.setGlosaEstadoPago(detalle.getGlosaEstadoPago());
			cuotas.setMontoTotalAbonadoACuota(detalle.getMontoTotalAbonadoACuota());
			cuotas.setNumeroCuota(detalle.getNumeroCuota());
			cuotas.setOficinaUltimoPago(detalle.getOficinaUltimoPago());
			cuotas.setTipoDocUltimoPago(detalle.getTipoDocUltimoPago());
			cuotas.setNumeroDocUltimoPago(detalle.getNumeroDocUltimoPago());
			
			listaCuotas.add(cuotas);
		}
		salidaVO.setnTotalCuotas(entrada.getNTotalCuotas());
		salidaVO.setListaCuotas(listaCuotas);
		
		return salidaVO;
	}

}
