package cl.laaraucana.satelites.webservices.client.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.integracion.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.ConsultaCuotasCreditoPartidasAbiertaPorFolioSalidaVO;
import cl.laaraucana.satelites.integracion.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400PortProxy;
import cl.laaraucana.satelites.integracion.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.ConsultaCuotasCreditoPartidasAbiertasPorFolioEntradaVO;
import cl.laaraucana.satelites.integracion.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.DetalleConsultaCuotasCreditoPartidasAbiertaPorFolioSalidaVO;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.VO.EntradaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO;
import cl.laaraucana.satelites.webservices.client.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.VO.SalidaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO;
import cl.laaraucana.satelites.webservices.client.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400.VO.SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;

public class ClienteConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400 implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {

		EntradaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO entradaVO = (EntradaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO) entrada;
		SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO salidaVO=new SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO();
//		try {
			logger.debug("<< Ingreso al ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400 con el folio: "+entradaVO.getFolioCredito());
			
			ConsultaCuotasCreditoPartidasAbiertasPorFolioEntradaVO entradaWs = new ConsultaCuotasCreditoPartidasAbiertasPorFolioEntradaVO();
			entradaWs.setFolioCredito(entradaVO.getFolioCredito());
			entradaWs.setTipoCredito(entradaVO.getTipoCredito());
			
			ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400PortProxy ws = new ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400PortProxy();
			ws._getDescriptor().setEndpoint(Configuraciones.getConfig("ep.PartidasAbiertas"));
			logger.debug("la ruta es "+Configuraciones.getConfig("ep.PartidasAbiertas"));
			
//<=====	Codigo nuevo, con validacion
			ConsultaCuotasCreditoPartidasAbiertaPorFolioSalidaVO respuesta = new ConsultaCuotasCreditoPartidasAbiertaPorFolioSalidaVO();
			
			try {
				respuesta = ws.consultaCuotasCreditoPartidasAbiertasPorFolioEnAs400(entradaWs);				
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(e.getMessage());
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje("Error en servicio ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400: compruebe el servicio");
				return salidaVO;
			}
			
			if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
				salidaVO = mapear(respuesta);
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
				salidaVO.setMensaje("Carga de creditos ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400 OK");
				logger.debug("Carga de creditos ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400 OK, codigo error= "+0);
			}else{
				if(respuesta.getResultCode().equals("1")){
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
					salidaVO.setMensaje("ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400 OK. El folio no contiene cuotas");
					logger.debug("Código error= "+0+". "+salidaVO.getMensaje());
				}else{
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
					salidaVO.setMensaje("Error en servicio ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400: "+ respuesta.getLog().getId());
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
		
		logger.debug(">> Salida ConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400");
		return salidaVO;
	}
	
    private SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO mapear(ConsultaCuotasCreditoPartidasAbiertaPorFolioSalidaVO entrada){
    	SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO salidaVO = new SalidaListaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO();
    	SalidaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO cuotas=null;
    	List<SalidaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO> listaCuotas = new ArrayList<SalidaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO>();
    	
    	for (DetalleConsultaCuotasCreditoPartidasAbiertaPorFolioSalidaVO detalle : entrada.getDetalle()) {
			cuotas = new SalidaConsultaCuotasCreditoPartidasAbiertasPorFolioEnAs400VO();
			
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

