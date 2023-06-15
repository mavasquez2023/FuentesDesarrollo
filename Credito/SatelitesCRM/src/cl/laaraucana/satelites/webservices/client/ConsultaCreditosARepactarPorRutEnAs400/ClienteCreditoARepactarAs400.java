package cl.laaraucana.satelites.webservices.client.ConsultaCreditosARepactarPorRutEnAs400;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.integracion.ConsultaCreditosARepactarPorRutEnAs400.ConsultaCreditosARepactarPorRutAs400EntradaVO;
import cl.laaraucana.satelites.integracion.ConsultaCreditosARepactarPorRutEnAs400.ConsultaCreditosARepactarPorRutAs400ListaSalidaVO;
import cl.laaraucana.satelites.integracion.ConsultaCreditosARepactarPorRutEnAs400.ConsultaCreditosARepactarPorRutAs400PortProxy;
import cl.laaraucana.satelites.integracion.ConsultaCreditosARepactarPorRutEnAs400.ConsultaCreditosARepactarPorRutAs400SalidaVO;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosARepactarPorRutEnAs400.VO.EntradaCreditoARepactarVO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosARepactarPorRutEnAs400.VO.SalidaCreditoARepactarVO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosARepactarPorRutEnAs400.VO.SalidaListaCreditoARepactarVO;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;

public class ClienteCreditoARepactarAs400 implements WSInterface {
	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {

		logger.debug("<< Ingreso a ClienteCreditoARepactarVigenteAs400");

		SalidaListaCreditoARepactarVO salidaVO = new SalidaListaCreditoARepactarVO();
//		try {
			
		
		// VO's propios
		EntradaCreditoARepactarVO entradaVO = (EntradaCreditoARepactarVO) entrada;

		// VO's WS

		ConsultaCreditosARepactarPorRutAs400EntradaVO entradaWs = new ConsultaCreditosARepactarPorRutAs400EntradaVO();
		entradaWs.setRut(entradaVO.getRut().toUpperCase());
		entradaWs.setFechaColocacion(entradaVO.getFechaColocacion());
		entradaWs.setPeriodoRepactacion(entradaVO.getPeriodoRepactacion());
		entradaWs.setFlag1("0");
		entradaWs.setFlag2("0");
		entradaWs.setFlag3("0");
		ConsultaCreditosARepactarPorRutAs400PortProxy ws = new ConsultaCreditosARepactarPorRutAs400PortProxy();
		ws._getDescriptor().setEndpoint(Configuraciones.getConfig("ep.CreditoARepactarAs400"));
		logger.debug("LLamada a ws ClienteCreditoARepactarVigenteAs400 con rut de entrada :" + entradaWs.getRut());

		//<=====	Codigo nuevo, con validacion
		ConsultaCreditosARepactarPorRutAs400ListaSalidaVO respuesta = new ConsultaCreditosARepactarPorRutAs400ListaSalidaVO();
		
		try {
			respuesta = ws.consultaCreditosARepactarPorRutAs400(entradaWs);			
		} catch (Exception e) {
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en servicio ClienteCreditoARepactarVigenteAs400: compruebe el servicio");
			return salidaVO;
		}
		
		if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("Carga de creditos ClienteCreditoARepactarVigenteAs400 OK");
			logger.debug("Carga de creditos ClienteCreditoARepactarVigenteAs400 OK, codigo error= "+0);
		}else{
			if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)){
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
				salidaVO.setMensaje("Carga de creditos ClienteCreditoARepactarVigenteAs400 OK. El rut no contiene créditos.");
				logger.debug("Código error= "+0+". "+salidaVO.getMensaje());
			}else{
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salidaVO.setMensaje("Error en servicio ClienteCreditoARepactarVigenteAs400: "+respuesta.getLog().getId());
				logger.debug("Código error= "+1+". "+salidaVO.getMensaje());
			}
		}
		//<=====	Codigo nuevo, con validacion



		//<=====	Codigo antiguo, sin validacion
//		salidaVO = mapear(ws.consultaCreditosARepactarPorRutAs400(entradaWs));
//
//		
//		salidaVO.setCodigoError("0");
//		salidaVO.setMensaje("Carga de creditos a repactar vigentes AS400 OK");
//
//		logger.debug("Retorno llamada a ws ClienteCreditoARepactarVigenteAs400");
//		logger.debug(">> Salida de ClienteCreditoARepactarVigenteAs400");
//		
//		} catch (Exception e) {
//			salidaVO = new SalidaListaCreditoARepactarVO();
//			salidaVO.setCodigoError("1");
//			salidaVO.setMensaje("Excepcion en ClienteConsultaCreditosPorRutEnAs400: " + e.getMessage());
//			e.printStackTrace();
//		}
		//<=====	Codigo antiguo, sin validacion
		logger.debug(">> Salida ClienteCreditoARepactarVigenteAs400");
		return salidaVO;
	}

	/**
	 * @param entrada
	 *            Rebice el VO del web service y lo mapea en VO's propios
	 * @param flagTipoCredito
	 *            recibe el tipo de credito #cancelado #vigente, y almacena los
	 *            creditos que corresponden.
	 * @return Clase que contiene una lista con los creditos.
	 * @throws ParseException 
	 */
	private SalidaListaCreditoARepactarVO mapear(ConsultaCreditosARepactarPorRutAs400ListaSalidaVO entrada) throws ParseException {
		logger.debug("Ingreso a mapeo de datos ClienteCreditoARepactarVigenteAs400");
		logger.debug("imprimiendo la entrada " + entrada);
		logger.debug(entrada.getListaSalida());
		SalidaCreditoARepactarVO credito = null;
//		String estadoCredito = null;
		SalidaListaCreditoARepactarVO salidaVO = new SalidaListaCreditoARepactarVO();
		List<SalidaCreditoARepactarVO> listaCreditos = new ArrayList<SalidaCreditoARepactarVO>();

		for (ConsultaCreditosARepactarPorRutAs400SalidaVO salida : entrada.getListaSalida()) {
//			estadoCredito = salida.getEstadoCredito().trim().toUpperCase();
			// int estadoCreditoInt = Integer.parseInt(estadoCredito);
			// if(estadoCreditoInt<=6){
			credito = new SalidaCreditoARepactarVO();

			credito.setComisionPrepago(salida.getComisionPrepago());
			credito.setCuotasImpagas(salida.getTotalCuotasImpagas());
			credito.setCuotasVigentes(salida.getTotalCuotasVigentes());
			credito.setDescuentoConMora(salida.getDescuentoConMora());
			credito.setDescuentoMora(salida.getDescuentoConMora());
			credito.setDescuentoSinMora(salida.getDescuentoSinMora());
			credito.setDigitoVerificadorRutEmpresa(salida.getDigitoVerificadorRutEmpresa());
			credito.setEstadoCredito(salida.getEstadoCredito());
			credito.setFechaOtorgamiento(Utils.pasaFechaASaWEB(salida.getFechaOtorgamiento()));
			credito.setFechaVencimiento(Utils.pasaFechaASaWEB(salida.getFechaVencimiento()));
			credito.setFolio(salida.getFolio());
			credito.setLinea(salida.getLinea());
			credito.setMontoCuota(salida.getMontoCuota());
			credito.setMontoExentoMoroso(salida.getMontoExentoMoroso());
			credito.setMontoExentoRepactado(salida.getMontoExentoRepactado());
			credito.setMontoGastoCobranza(salida.getMontoGastoCobranza());

			credito.setMontoInteres(salida.getMontoInteres());
			credito.setMontoInteresMora(salida.getMontoInteresMora());
			credito.setMontoMora(salida.getMontoMoroso());
			credito.setMontoOtorgado(salida.getMontoOtorgado());
			credito.setMontoReajustado(salida.getMontoReajuste());
			credito.setMontoRepactacion(salida.getMontoARepactar());
			credito.setMontoSeguros(salida.getMontoSeguroVida());
			credito.setOficina(salida.getOficina());
			credito.setPlazo(salida.getPlazo());
			credito.setRutEmpresa(salida.getRutEmpresa());
			credito.setTipo(salida.getTipo());
			credito.setTipoOperacion(salida.getTipoDeOperacion());
			listaCreditos.add(credito);
			// }
		}

		salidaVO.setListaCreditos(listaCreditos);
		logger.debug("Salida de mapeo de datos ClienteCreditoVigenteAs400");
		return salidaVO;
	}

}
