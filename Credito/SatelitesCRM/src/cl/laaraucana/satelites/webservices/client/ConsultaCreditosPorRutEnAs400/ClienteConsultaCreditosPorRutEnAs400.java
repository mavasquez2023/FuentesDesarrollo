package cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.integracion.ConsultaCreditosPorRutEnAs400.ConsultaCreditoPorRutEnAs400PortProxy;
import cl.laaraucana.satelites.integracion.ConsultaCreditosPorRutEnAs400.ConsultaCreditosPorRutEntradaVO;
import cl.laaraucana.satelites.integracion.ConsultaCreditosPorRutEnAs400.ConsultaCreditosPorRutSalidaVO;
import cl.laaraucana.satelites.integracion.ConsultaCreditosPorRutEnAs400.DetalleConsultaCreditosPorRutSalidaVO;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.VO.EntradaConsultaCreditosPorRutEnAs400VO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.VO.SalidaConsultaCreditosPorRutEnAs400VO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosPorRutEnAs400.VO.SalidaListaConsultaCreditosPorRutEnAs400VO;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;

public class ClienteConsultaCreditosPorRutEnAs400 implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * Llamada a Consulta créditos por rut
	 * 
	 * Lanza una excepcion cuando no se puede conectar al servicio
	 * 
	 * Cógigos de respuesta de servicio
	 * 1: la lista de retorno esta vacia
	 * 3: retorna lista con elementos 
	 * 5: el servicio retorna una excepcion
	 */
	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		
		SalidaListaConsultaCreditosPorRutEnAs400VO salidaVO=new SalidaListaConsultaCreditosPorRutEnAs400VO();
		
//		try {
			
			EntradaConsultaCreditosPorRutEnAs400VO entradaVO = (EntradaConsultaCreditosPorRutEnAs400VO) entrada;
			logger.debug("<< Ingreso a ClienteConsultaCreditosPorRutEnAs400 con rut: "+entradaVO.getRut());

			ConsultaCreditosPorRutEntradaVO entradaWs = new ConsultaCreditosPorRutEntradaVO();
			entradaWs.setRut(entradaVO.getRut().toUpperCase());
			
			ConsultaCreditoPorRutEnAs400PortProxy ws = new ConsultaCreditoPorRutEnAs400PortProxy();
			ws._getDescriptor().setEndpoint(Configuraciones.getConfig("ep.CreditoVigenteAs400"));
			
			//<=====	Codigo nuevo, con validacion
			ConsultaCreditosPorRutSalidaVO respuesta = new ConsultaCreditosPorRutSalidaVO();
			
			try {
				respuesta = ws.consultaCreditosPorRutEnAs400(entradaWs);
			} catch (Exception e) {
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje("Error en servicio ConsultaCreditosPorRutEnAs400: compruebe el servicio");
				return salidaVO;
			}
			
			if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
				salidaVO = mapear(respuesta);
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
				salidaVO.setMensaje("Carga de creditos ConsultaCreditosPorRutEnAs400 OK. Código error: 0.");
				logger.debug(salidaVO.getMensaje());
			}else{
				if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)){
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
					salidaVO.setMensaje("Carga de creditos ConsultaCreditosPorRutEnAs400 OK. El rut no contiene créditos. 0");
					logger.debug(salidaVO.getMensaje());
				}else{
					salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
					salidaVO.setMensaje("Error en servicio ConsultaCreditosPorRutEnAs400: "+respuesta.getLog().getId());
					logger.debug(salidaVO.getMensaje());
				}
			}
			//<=====	Codigo nuevo, con validacion



			//<=====	Codigo antiguo, sin validacion
//			logger.debug("<< LLamamos al ws ClienteConsultaCreditosPorRutEnAs400");
//			salidaVO = mapear(ws.consultaCreditosPorRutEnAs400(entradaWs));
//			logger.debug(">> Salida al ws ClienteConsultaCreditosPorRutEnAs400");
//			
//			
//			salidaVO.setCodigoError("0");
//			salidaVO.setMensaje("Carga ConsultaCreditosPorRutEnAs400 OK");
//			
//		} catch (Exception e) {
//			salidaVO = new SalidaListaConsultaCreditosPorRutEnAs400VO();
//			salidaVO.setCodigoError("1");
//			salidaVO.setMensaje("Excepcion en ClienteConsultaCreditosPorRutEnAs400: " + e.getMessage());
//			e.printStackTrace();
//		}
		//<=====	Codigo antiguo, sin validacion
		
		logger.debug(">> Salida a ClienteConsultaCreditosPorRutEnAs400");
		return salidaVO;
	}
	
	private SalidaListaConsultaCreditosPorRutEnAs400VO mapear(ConsultaCreditosPorRutSalidaVO entrada){
		SalidaListaConsultaCreditosPorRutEnAs400VO salidaVO = new SalidaListaConsultaCreditosPorRutEnAs400VO();
		List<SalidaConsultaCreditosPorRutEnAs400VO> listaCreditos = new ArrayList<SalidaConsultaCreditosPorRutEnAs400VO>();
		SalidaConsultaCreditosPorRutEnAs400VO credito = null;
		
		for (DetalleConsultaCreditosPorRutSalidaVO detalle : entrada.getDetalle()) {

			if((!detalle.getTipoCredito().trim().toUpperCase().equals("ACUERDO") 
					&& !detalle.getTipoCredito().trim().toUpperCase().equals("PROMESA"))){
					
				credito = new SalidaConsultaCreditosPorRutEnAs400VO();
				
				credito.setCantidadCuotas(detalle.getCantidadCuotas());
				credito.setEstadoCredito(detalle.getEstadoCredito());
				credito.setFechaOtorgamiento(detalle.getFechaOtorgamiento());
				credito.setFolioCredito(detalle.getFolioCredito());
				credito.setIndicadorRepactacion(detalle.getIndicadorRepactacion());
				credito.setIndicadorReprogramacion(detalle.getIndicadorReprogramacion());
				credito.setMontoAdaudado(detalle.getMontoAdaudado());
				credito.setMontoCuota(detalle.getMontoCuota());
				credito.setMontoImpuestos(detalle.getMontoImpuestos());
				credito.setMontoSolicitado(detalle.getMontoSolicitado());
				credito.setOficinaOriginadora(detalle.getOficinaOriginadora());
				credito.setRolAsociado(detalle.getRolAsociado());
				credito.setRolPagador(detalle.getRolPagador());
				credito.setTasaInteres(detalle.getTasaInteres());
				credito.setTipoAfiliado(detalle.getTipoAfiliado());
				credito.setTipoCredito(detalle.getTipoCredito());
				credito.setTipoProducto(detalle.getTipoProducto());
				
				credito.setSumaGCob(detalle.getSumaGCob());
				credito.setSumaCuotas(detalle.getSumaCuotas());

				listaCreditos.add(credito);
			}
		}
		salidaVO.setListaCreditos(listaCreditos);
		
		return salidaVO;
	}

}

