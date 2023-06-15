package cl.laaraucana.satelites.webservices.client.ConsultaCreditosCanceladosPorRutCRC438;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.integracion.ConsultaCreditosCanceladosPorRutCRC438.ConsultaCreditosCanceladosPorRutCRC438As400EntradaVO;
import cl.laaraucana.satelites.integracion.ConsultaCreditosCanceladosPorRutCRC438.ConsultaCreditosCanceladosPorRutCRC438As400ListaSalidaVO;
import cl.laaraucana.satelites.integracion.ConsultaCreditosCanceladosPorRutCRC438.ConsultaCreditosCanceladosPorRutCRC438As400PortProxy;
import cl.laaraucana.satelites.integracion.ConsultaCreditosCanceladosPorRutCRC438.ConsultaCreditosCanceladosPorRutCRC438As400SalidaVO;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosCanceladosPorRutCRC438.VO.EntradaCreditosCanceladosCRC438VO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosCanceladosPorRutCRC438.VO.SalidaCreditosCanceladosCRC438VO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosCanceladosPorRutCRC438.VO.SalidaListaCreditosCanceladosCRC438VO;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;

public class ClienteCreditosCanceladosPorRutCRC438 implements WSInterface {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		
		logger.debug("<< Ingreso a ClienteCreditosCanceladosPorRutCRC438");
		
		SalidaListaCreditosCanceladosCRC438VO salidaVO=new SalidaListaCreditosCanceladosCRC438VO();
		
//		try {
			
			EntradaCreditosCanceladosCRC438VO entradaVO = (EntradaCreditosCanceladosCRC438VO) entrada;
			ConsultaCreditosCanceladosPorRutCRC438As400EntradaVO entradaWs = new ConsultaCreditosCanceladosPorRutCRC438As400EntradaVO();
			
			entradaWs.setFlag1(entradaVO.getFlag1());
			entradaWs.setFlag2(entradaVO.getFlag2());
			entradaWs.setFlag3(entradaVO.getFlag3());
			entradaWs.setRut(entradaVO.getRut().toUpperCase());
			
			ConsultaCreditosCanceladosPorRutCRC438As400PortProxy ws = new ConsultaCreditosCanceladosPorRutCRC438As400PortProxy();
			ws._getDescriptor().setEndpoint(Configuraciones.getConfig("ep.CreditoCanceladoAs400"));
			logger.debug("LLamada a ws ClienteCreditosCanceladosPorRutCRC438 con rut de entrada :" + entradaWs.getRut());
			
//<=====	Codigo nuevo, con validacion
			ConsultaCreditosCanceladosPorRutCRC438As400ListaSalidaVO respuesta = new ConsultaCreditosCanceladosPorRutCRC438As400ListaSalidaVO();
			try {				
				respuesta = ws.consultaCreditosCanceladosPorRutCRC438As400(entradaWs);
			} catch (Exception e) {
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje("Error en servicio ClienteCreditosCanceladosPorRutCRC438: compruebe el servicio");
				return salidaVO;
			}
			
			if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
				salidaVO = mapear(respuesta);
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
				salidaVO.setMensaje("Carga de creditos ClienteCreditosCanceladosPorRutCRC438 OK");
				logger.debug("Carga de creditos ClienteCreditosCanceladosPorRutCRC438 OK, codigo error= "+0);
			}else{
				if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)){
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
					salidaVO.setMensaje("Carga de creditos ClienteCreditosCanceladosPorRutCRC438 OK. El rut no contiene créditos.");
					logger.debug("Código error= "+0+". "+salidaVO.getMensaje());
				}else{
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
					salidaVO.setMensaje("Error en servicio ClienteCreditosCanceladosPorRutCRC438: "+respuesta.getLog().getId());
					logger.debug("Código error= "+1+ ". " + salidaVO.getMensaje());
				}
			}
//<=====	Codigo nuevo, con validacion



//<=====	Codigo antiguo, sin validacion
//			salidaVO = mapear(ws.consultaCreditosCanceladosPorRutCRC438As400(entradaWs));
//			
//			salidaVO.setCodigoError("0");
//			salidaVO.setMensaje("Carga de creditos cancelados As400 OK");
//
//		
//		} catch (Exception e) {
//			salidaVO = new SalidaListaCreditosCanceladosCRC438VO();
//			salidaVO.setCodigoError("1");
//			salidaVO.setMensaje("Excepcion en ClienteCreditosCanceladosPorRutCrc438: " + e.getMessage());
//			e.printStackTrace();
//		}
//<=====	Codigo antiguo, sin validacion	
		logger.debug(">> Salida ClienteCreditosCanceladosPorRutCRC438");
		return salidaVO;
	}
	
	private SalidaListaCreditosCanceladosCRC438VO mapear(ConsultaCreditosCanceladosPorRutCRC438As400ListaSalidaVO entrada) throws ParseException{
		SalidaListaCreditosCanceladosCRC438VO salidaVO = new SalidaListaCreditosCanceladosCRC438VO();
		SalidaCreditosCanceladosCRC438VO credito=null;
		List<SalidaCreditosCanceladosCRC438VO> listaCreditos = new ArrayList<SalidaCreditosCanceladosCRC438VO>();
		
		for (ConsultaCreditosCanceladosPorRutCRC438As400SalidaVO datos : entrada.getListaSalida()) {
			credito = new SalidaCreditosCanceladosCRC438VO();
			credito.setFolioCredito(datos.getFolioCredito());
			credito.setFechaCancelacion(Utils.pasaFechaASaWEB(datos.getFechaCancelacion()));
			credito.setFechaOtorgamiento(Utils.pasaFechaASaWEB(datos.getFechaOtorgamiento()));
			credito.setFolioCredito(datos.getFolioCredito());
			credito.setMontoCuota((Double.valueOf(datos.getMontoCuota())));//TRASNSFORMAR A DOUBLE
			credito.setMontoSolicitado(Double.valueOf(datos.getMontoSolicitado()));//TRASNSFORMAR A DOUBLE
			credito.setTotalCuotas(datos.getTotalCuotas());
			credito.setOficinaProceso(datos.getOficinaProceso());
			
			listaCreditos.add(credito);
		}
		
		salidaVO.setListaCreditos(listaCreditos);
		
		return salidaVO;
	}

}
