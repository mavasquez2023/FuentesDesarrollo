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
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.ClienteQueryCompContHeaderIn;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.EntradaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.SalidaListaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.SalidaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.ClienteQueryContractCashflowIn;
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
			//credito.setFechaCancelacion(Utils.pasaFechaSAPaWEB(creditows.getContractEnd()));
			credito.setFechaCancelacion(Utils.pasaFechaSAPaWEB(creditows.getFecha_ult_pago()));		
			credito.setFechaOtorgamiento(Utils.pasaFechaSAPaWEB(creditows.getContractBegin()));
			//Se agregó tipo de moneda
			credito.setTipoMoneda(creditows.getContractCurrency());
			credito.setFolio(creditows.getContractNumber());
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
		entradaVO.setINCLUYE_EPO("X");
		
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

}
