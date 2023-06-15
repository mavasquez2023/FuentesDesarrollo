package cl.laaraucana.satelites.certificados.creditocancelado;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowRequest;

import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.certificados.creditocancelado.VO.SalidaCreditoCanceladoVO;
import cl.laaraucana.satelites.certificados.creditocancelado.VO.SalidaDetalleCreditoCanceladoVO;
import cl.laaraucana.satelites.certificados.creditocancelado.VO.SalidaListaCreditosCanceladosVO;
import cl.laaraucana.satelites.certificados.creditocancelado.VO.SalidaListaDetalleCreditoCanceladoVO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosCanceladosPorRutCRC438.ClienteCreditosCanceladosPorRutCRC438;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosCanceladosPorRutCRC438.VO.EntradaCreditosCanceladosCRC438VO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosCanceladosPorRutCRC438.VO.SalidaCreditosCanceladosCRC438VO;
import cl.laaraucana.satelites.webservices.client.ConsultaCreditosCanceladosPorRutCRC438.VO.SalidaListaCreditosCanceladosCRC438VO;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosPagosCreditosPorFolioAs400.ClienteConsultaDatosPagosCreditosPorFolioAs400;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosPagosCreditosPorFolioAs400.VO.EntradaConsultaDatosPagosCreditosVO;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosPagosCreditosPorFolioAs400.VO.SalidaConsultaDatosPagosCreditosPorFolioVO;
import cl.laaraucana.satelites.webservices.client.ConsultaDatosPagosCreditosPorFolioAs400.VO.SalidaListaConsultaDatosPagosCreditosPorFolioVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.ClienteQueryCompContHeaderIn;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.EntradaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.SalidaListaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.SalidaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.ClienteQueryContractCashflowIn;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.VO.EntradaQueryContractCashflowInVO;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.VO.SalidaListaQueryContractCashflowInVO;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.VO.SalidaQueryContractCashflowInVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;

public class ServicioCreditosCancelados {
	protected static Logger logger = Logger.getLogger(ServicioCreditosCancelados.class);

	public static SalidaListaCreditosCanceladosVO obtenerCreditosCanceladosSAP(String rut, String flagTipoCredito){
		
		EntradaQueryCompContHeaderInVO entradaVO = new EntradaQueryCompContHeaderInVO();
		entradaVO.setRut(rut);
		entradaVO.setFlagTipoCredito(flagTipoCredito);//2: obtiene creditos cancelados.
		
		ClienteQueryCompContHeaderIn clienteBanking = new ClienteQueryCompContHeaderIn();
		SalidaListaCreditosCanceladosVO salidaVO = new SalidaListaCreditosCanceladosVO();
		
		try {
			SalidaListaQueryCompContHeaderInVO respuesta = (SalidaListaQueryCompContHeaderInVO) clienteBanking.call(entradaVO);
			logger.debug("Llamada al servicio SAP correcta");
			salidaVO = mapearQueryCompContHeaderIn(respuesta);
			logger.debug("Mapeo de datos SAP correcto.");

		} catch (Exception e) {
			logger.debug("Error al obtener los datos de SAP: " + e.getMessage());
			e.printStackTrace();
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje(e.getMessage());
		}
		
		return salidaVO;
	}
	
	private static SalidaListaCreditosCanceladosVO mapearQueryCompContHeaderIn(SalidaListaQueryCompContHeaderInVO entrada) throws ParseException{
		SalidaListaCreditosCanceladosVO salidaVO = new SalidaListaCreditosCanceladosVO();
		List<SalidaCreditoCanceladoVO> listaCreditos = new ArrayList<SalidaCreditoCanceladoVO>();
		SalidaCreditoCanceladoVO credito =null;
		
		//Cualquier error de mapeo será capturado en obtenerCreditosCanceladosSAP 
		
		for (SalidaQueryCompContHeaderInVO creditows : entrada.getListaCreditos()) {
			credito = new SalidaCreditoCanceladoVO();			
			credito.setFechaCancelacion(Utils.pasaFechaSAPaWEB(creditows.getFecha_ult_pago()));				
			credito.setFechaOtorgamiento(Utils.pasaFechaSAPaWEB(creditows.getContractBegin()));
			//Se agregó tipo de moneda
			credito.setTipoMoneda(creditows.getContractCurrency());
			credito.setFolio(Utils.formatearFolio(creditows.getContractNumber()));
			credito.setMontoCuota(Double.valueOf(creditows.getInstallmentAmount().replace(".", "").replace(",", ".")));
			credito.setMontoSolicitado(Double.valueOf(creditows.getContractAmount().replace(".", "").replace(",", ".")));
			credito.setPlazo(creditows.getInstallmentQuantity());
			credito.setFlagTipoCredito("1");//1 indica que el credito viene desde SAP
			
			listaCreditos.add(credito);
		}
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());		
		salidaVO.setListaCreditos(listaCreditos);
		
		return salidaVO;
	}
	
	public static SalidaListaDetalleCreditoCanceladoVO obtenerDetalleCreditosCanceladosBanking(String folio){
		//EntradaQueryContractCashflowInVO entradaVO = new EntradaQueryContractCashflowInVO();
		QueryContractCashFlowRequest entradaVO = new QueryContractCashFlowRequest();
		SalidaListaDetalleCreditoCanceladoVO salidaVO=new SalidaListaDetalleCreditoCanceladoVO();
		entradaVO.setNroCuenta(folio);
		
		ClienteQueryContractCashflowIn clienteWs = new ClienteQueryContractCashflowIn();
		try {
			salidaVO = mapearQueryContractCashflowIn((SalidaListaQueryContractCashflowInVO) clienteWs.call(entradaVO));
			logger.debug("Retorno WebService Codigo:"+salidaVO.getCodigoError()+", Mensaje:"+salidaVO.getMensaje());

			if(salidaVO.getListaCuotas()==null || salidaVO.getListaCuotas().size()==0 ){
				salidaVO.setCodigoError("1");
				salidaVO.setMensaje("El crédito seleccionado no tiene cuotas asociadas en SAP.");
			}else{
				salidaVO.setCodigoError("0");
				salidaVO.setMensaje("Obtener Detalle de credito cancelado Banking OK");
			}
			
		} catch (Exception e) {
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje(e.getMessage());
		}
		
		
		return salidaVO;

	}
	
	private static SalidaListaDetalleCreditoCanceladoVO mapearQueryContractCashflowIn(SalidaListaQueryContractCashflowInVO entrada){
		SalidaListaDetalleCreditoCanceladoVO salidaVO = new SalidaListaDetalleCreditoCanceladoVO();
		List<SalidaDetalleCreditoCanceladoVO> listaCuotas = new ArrayList<SalidaDetalleCreditoCanceladoVO>();
		SalidaDetalleCreditoCanceladoVO cuotas = null;
		
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());
		
		if(entrada.getListaCuotas() == null){
			return salidaVO;
		}
		
		for (SalidaQueryContractCashflowInVO cuotasws : entrada.getListaCuotas()) {
			cuotas = new SalidaDetalleCreditoCanceladoVO();
			
			cuotas.setDocPago(cuotasws.getFolioPago());
			cuotas.setEstPago(cuotasws.getEstadoPago());
			cuotas.setFecPago(cuotasws.getUltFechaPago());
			cuotas.setFecVencimiento(cuotasws.getFechaVencCuota());
			cuotas.setMonto(Double.valueOf(cuotasws.getMonto_pagado().replace(".", "").replace(",", ".")));
			cuotas.setnCuota(cuotasws.getNroCuota());
			cuotas.setOficina(cuotasws.getOficinaPago());
			//Se agregó tipo de moneda
			cuotas.setTipoMoneda(cuotasws.getMoneda());
			

			listaCuotas.add(cuotas);
		}

		salidaVO.setListaCuotas(listaCuotas);
		
		return salidaVO;
	}
	
	public static SalidaListaCreditosCanceladosVO obtenerCreditosCanceladosAs400(String rut, String flag1, String flag2, String flag3){
		logger.debug("Ingreso a ServicioCreditosCancelados");
		SalidaListaCreditosCanceladosVO salidaVO= new SalidaListaCreditosCanceladosVO();
		ClienteCreditosCanceladosPorRutCRC438 clienteWs = new ClienteCreditosCanceladosPorRutCRC438(); 
		EntradaCreditosCanceladosCRC438VO entradaVO = new EntradaCreditosCanceladosCRC438VO();
		entradaVO.setRut(Utils.obtenerValorAnteriorA(rut,"-"));
		entradaVO.setFlag1(flag1);
		entradaVO.setFlag2(flag2);
		entradaVO.setFlag3(flag3);
		
		try {
			SalidaListaCreditosCanceladosCRC438VO respuesta = (SalidaListaCreditosCanceladosCRC438VO) clienteWs.call(entradaVO);
			logger.debug("Respuesta de AS400 correcta");
				salidaVO = mapearConsultaCreditosCanceladosCRC438(respuesta);
			logger.debug("Mapeo de datos correcto");

		} catch (Exception e) {
			logger.debug("Error al obtener los datos de AS400: " + e.getMessage());
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);		
			salidaVO.setMensaje(e.getMessage());
		}
		
		logger.debug("Salida ServicioCreditosCancelados");
		return salidaVO;
	}
	
	private static SalidaListaCreditosCanceladosVO mapearConsultaCreditosCanceladosCRC438(SalidaListaCreditosCanceladosCRC438VO entrada) throws Exception{
		SalidaListaCreditosCanceladosVO salidaVO= new SalidaListaCreditosCanceladosVO();
		List<SalidaCreditoCanceladoVO> listaCreditos = new ArrayList<SalidaCreditoCanceladoVO>();
		SalidaCreditoCanceladoVO credito = null;	
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());

		if(entrada.getListaCreditos() == null){
			return salidaVO;			
		}
		logger.debug("CANTIDAD DE CREDITOS AS400: " + entrada.getListaCreditos().size());
		for (SalidaCreditosCanceladosCRC438VO detalle : entrada.getListaCreditos()) {
			credito = new SalidaCreditoCanceladoVO();
			credito.setFechaCancelacion(detalle.getFechaCancelacion());
			credito.setFechaOtorgamiento(detalle.getFechaOtorgamiento());
			credito.setFlagTipoCredito("0");
			credito.setFolio(detalle.getOficinaProceso()+"-"+detalle.getFolioCredito());
			credito.setMontoCuota(detalle.getMontoCuota());
			credito.setMontoSolicitado(detalle.getMontoSolicitado());
			credito.setPlazo(detalle.getTotalCuotas());
			
			credito.setTipoMoneda("CLP");
			
			listaCreditos.add(credito);
		}
		salidaVO.setListaCreditos(listaCreditos);
		
		return salidaVO;
	}
	
	public static SalidaListaDetalleCreditoCanceladoVO obtenerDetalleConsultaCreditosPorRutEnAs400(String folio){
		SalidaListaDetalleCreditoCanceladoVO salidaVO = new SalidaListaDetalleCreditoCanceladoVO();
		
		ClienteConsultaDatosPagosCreditosPorFolioAs400 cliente = new ClienteConsultaDatosPagosCreditosPorFolioAs400();
		EntradaConsultaDatosPagosCreditosVO entradaVO = new EntradaConsultaDatosPagosCreditosVO();
		if(folio.length()== 13){
			String split[] = folio.split("-");
			
			entradaVO.setOficinaProceso(split[0]);
			entradaVO.setFolio(split[1]);

			try {
				logger.debug("Llamada al clienteCreditosVigentesAs400 con oficina: "+entradaVO.getOficinaProceso()+" y folio: "+entradaVO.getFolio());
				
				SalidaListaConsultaDatosPagosCreditosPorFolioVO respuesta = (SalidaListaConsultaDatosPagosCreditosPorFolioVO) cliente.call(entradaVO);
				logger.debug("Llamada al servicio de consulta de créditos vigentes");
				
				salidaVO = mapearDetalleConsultaCreditosPorRutEnAs400(respuesta);
				logger.debug("Fin Llamada al clienteCreditosVigentesAs400");

			} catch (Exception e) {
				salidaVO.setCodigoError("2");
				salidaVO.setMensaje(e.getMessage());
			}
			
		}else{
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en largo de folio");
		}
		
		return salidaVO;
	}
	
	private static SalidaListaDetalleCreditoCanceladoVO mapearDetalleConsultaCreditosPorRutEnAs400(SalidaListaConsultaDatosPagosCreditosPorFolioVO entrada) throws Exception{
		SalidaListaDetalleCreditoCanceladoVO salidaVO = new SalidaListaDetalleCreditoCanceladoVO();
		List<SalidaDetalleCreditoCanceladoVO> listaCuotas = new ArrayList<SalidaDetalleCreditoCanceladoVO>();
		SalidaDetalleCreditoCanceladoVO cuotas = null;
		
		salidaVO.setCodigoError(entrada.getCodigoError());
		salidaVO.setMensaje(entrada.getMensaje());
		
		if(entrada.getListaCuotas() == null){
			return salidaVO;
		}
		
		for (SalidaConsultaDatosPagosCreditosPorFolioVO detalle : entrada.getListaCuotas()) {
			cuotas = new SalidaDetalleCreditoCanceladoVO();
			
			cuotas.setDocPago(detalle.getNumeroDocUltimoPago());
			
			//Se quito el try por un throws
			cuotas.setFecPago(Utils.pasaFechaASaWEB(detalle.getFechaUltimoPago()));
			cuotas.setFecVencimiento(Utils.pasaFechaASaWEB(detalle.getFechaVencimiento()));
			//Se quito el try por un throws
			
			/* Cofigo antiguo
/*			try {
				cuotas.setFecPago(Utils.pasaFechaASaWEB(detalle.getFechaUltimoPago()));
			} catch (Exception e) {
				cuotas.setFecPago(detalle.getFechaUltimoPago());
			}
			try {
				cuotas.setFecVencimiento(Utils.pasaFechaASaWEB(detalle.getFechaVencimiento()));
			} catch (Exception e) {
				cuotas.setFecVencimiento(detalle.getFechaVencimiento());
			}*/
			cuotas.setEstPago(detalle.getGlosaEstadoPago());			
			cuotas.setnCuota(detalle.getNumeroCuota());
			cuotas.setOficina(detalle.getOficinaUltimoPago());
			cuotas.setMonto(Double.valueOf(detalle.getMontoTotalAbonadoACuota()));
			
			cuotas.setTipoMoneda("CLP");
			
			listaCuotas.add(cuotas);
		}
		
		salidaVO.setListaCuotas(listaCuotas);
		
		return salidaVO;
	}
}
