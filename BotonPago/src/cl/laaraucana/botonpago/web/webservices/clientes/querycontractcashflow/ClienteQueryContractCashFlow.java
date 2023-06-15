package cl.laaraucana.botonpago.web.webservices.clientes.querycontractcashflow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import com.lautaro.xi.BS.Treasury.MessageHeader;
import com.lautaro.xi.BS.WEB_Mobile.DetalleCuotasCF;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowOUTBindingStub;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowRequest;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractCashFlowResponse;

import cl.laaraucana.botonpago.web.utils.Configuraciones;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.utils.Util;
import cl.laaraucana.botonpago.web.webservices.WSInterface;
import cl.laaraucana.botonpago.web.webservices.clientes.querycontractcashflow.vo.EntradaQueryContractCashFlowVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querycontractcashflow.vo.SalidaListaQueryContractCashFlowVO;
import cl.laaraucana.botonpago.web.webservices.clientes.querycontractcashflow.vo.SalidaQueryContractCashFlowVO;

import cl.laaraucana.botonpago.web.webservices.model.AbstractEntradaVO;
import cl.laaraucana.botonpago.web.webservices.model.AbstractSalidaVO;



public class ClienteQueryContractCashFlow implements WSInterface {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	/*@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		
		
		EntradaQueryContractCashFlowVO entradaVO = (EntradaQueryContractCashFlowVO) entrada;
		logger.debug("<< Ingreso a ClienteQueryContractCashflowIn con folio: "+entradaVO.getFolioCredito());
		String ep = Configuraciones.getConfig("ep.QueryContractCashFlow");
		
		SalidaListaQueryContractCashFlowVO salidaVO = new SalidaListaQueryContractCashFlowVO();
		
//		try {
		QueryContractCashFlowOUTServiceStub stub = new QueryContractCashFlowOUTServiceStub();
		stub._getServiceClient().setTargetEPR(new EndpointReference(ep));
		
		logger.debug("Cliente instanciado");
		
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername(Constantes.getInstancia().SAP_USERNAME);
		auth.setPassword(Constantes.getInstancia().SAP_PASSWORD);
		auth.setPreemptiveAuthentication(true);
		stub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);
		logger.debug("Seguridad seteada OK");
		
		QueryContractCashFlowRequest query = new QueryContractCashFlowRequest();
		
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(Util.getFechaSAP());
		messageHeader.setHOST(Constantes.getInstancia().SAP_HOST);
		messageHeader.setINTERNALORGANIZATION(Constantes.getInstancia().SAP_INTERNALORG);
		messageHeader.setUSER(Constantes.getInstancia().SAP_USER);
		
		query.setNroCuenta(entradaVO.getFolioCredito());
		query.setINCLUYE_EPO("X");
		query.setMessageHeader(messageHeader);
		logger.debug("Datos seteados al VO, con folio: "+query.getNroCuenta());
		
		QueryContractCashFlowRequestOut requestOut = new QueryContractCashFlowRequestOut();
		requestOut.setQueryContractCashFlowRequestOut(query);
		logger.debug("RequestOUT seteado OK");
//<==== Codigo nuevo, con validacion
		QueryContractCashFlowResponseOut respuesta = new QueryContractCashFlowResponseOut();
		
		try {
			respuesta = stub.queryContractCashFlow(requestOut);
		} catch (Exception e) {
			salidaVO.setCodigoError(Constantes.getInstancia().APP_COD_ERROR);
			salidaVO.setMensaje("Error en servicio QueryContractCashflowIn: compruebe el servicio");
			return salidaVO;
		}
		
		if(respuesta.getQueryContractCashFlowResponseOut().getResultCode().equals(Constantes.getInstancia().WS_COD_SUCCESS)){
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(Constantes.getInstancia().WS_COD_SUCCESS);
			salidaVO.setMensaje("Carga de creditos QueryContractCashflowIn OK. Código error: 0.");
			logger.debug(salidaVO.getMensaje());
		}else{
			salidaVO.setCodigoError(Constantes.getInstancia().APP_COD_ERROR);			
			String msg = respuesta.getQueryContractCashFlowResponseOut().getLog().getMESSAGE() + " (" + respuesta.getQueryContractCashFlowResponseOut().getLog().getSYSTEM() + ")";			
			salidaVO.setMensaje("Error en servicio QueryContractCashflowIn: " + msg);
			logger.debug(salidaVO.getMensaje());
		}

//<==== Codigo nuevo, con validacion
			
			
			
//<=====	Codigo antiguo, sin validacion
//			salidaVO = mapear(stub.queryContractCashFlow(requestOut));
//			
//			salidaVO.setCodigoError("0");
//			salidaVO.setMensaje("Carga QueryContractCashflowIn OK");
//
//		} catch (Exception e) {
//			salidaVO = new SalidaListaQueryContractCashflowInVO();
//			salidaVO.setCodigoError("1");
//			salidaVO.setMensaje("Excepcion en ClienteQueryContractCashflowIn: " + e.getMessage());
//		}
//<=====	Codigo antiguo, sin validacion
		logger.debug(">> Salida a ClienteQueryContractCashflowIn");
		return salidaVO;
	}
	*/
	
	public AbstractSalidaVO call(AbstractEntradaVO params) throws Exception {
		logger.debug("Inicio Web Service: Banking");
		QueryContractCashFlowResponse respuesta=null;
		EntradaQueryContractCashFlowVO entradaVO = (EntradaQueryContractCashFlowVO) params;
		
		QueryContractCashFlowRequest entrada= new QueryContractCashFlowRequest();
		entrada.setNroCuenta(entradaVO.getFolioCredito());
		entrada.setINCLUYE_EPO(entradaVO.getIncluyeEPO());
		
		SalidaListaQueryContractCashFlowVO salidaVO = new SalidaListaQueryContractCashFlowVO();
		
		String ep = Configuraciones.getConfig("ep.QueryContractCashFlow");
		String username = Constantes.getInstancia().SAP_USERNAME;
		String password= Constantes.getInstancia().SAP_PASSWORD;
				
		QueryContractCashFlowOUTBindingStub stub= new 	QueryContractCashFlowOUTBindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		
		stub._setProperty(QueryContractCashFlowOUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(new Date());
		messageHeader.setHOST(Constantes.getInstancia().SAP_HOST);
		messageHeader.setINTERNALORGANIZATION(Constantes.getInstancia().SAP_INTERNALORG);
		messageHeader.setUSER(username);
		
		entrada.setMessageHeader(messageHeader);
		try {
		respuesta= stub.queryContractCashFlow(entrada);

		} catch (Exception e) {
			logger.error("QueryContactCashFlow error = " + e.getMessage());
			e.printStackTrace();
		}

		if(respuesta.getResultCode().equals(Constantes.getInstancia().WS_COD_SUCCESS)){
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(Constantes.getInstancia().WS_COD_SUCCESS);
			salidaVO.setMensaje("Carga de creditos QueryContractCashflowIn OK. Código error: 0.");
			logger.debug(salidaVO.getMensaje());
		}else{
			salidaVO.setCodigoError(Constantes.getInstancia().APP_COD_ERROR);			
			String msg = respuesta.getLog().getMESSAGE() + " (" + respuesta.getLog().getSYSTEM() + ")";			
			salidaVO.setMensaje("Error en servicio QueryContractCashflowIn: " + msg);
			logger.debug(salidaVO.getMensaje());
		}
		logger.debug(">> Salida a ClienteQueryContractCashflowIn");
		return salidaVO;
	}
	
	private SalidaListaQueryContractCashFlowVO mapear(QueryContractCashFlowResponse entrada){
		SalidaListaQueryContractCashFlowVO salidaVO = new SalidaListaQueryContractCashFlowVO();
		SalidaQueryContractCashFlowVO cuotas =null;
		List<SalidaQueryContractCashFlowVO> listaCuotas = new ArrayList<SalidaQueryContractCashFlowVO>();

		if(entrada.getLineaComercial()!=null){
			salidaVO.setLineaComercial(entrada.getLineaComercial());
		}else{
			salidaVO.setLineaComercial("");
		}
		
		if(entrada.getNroCuenta()!=null){
			salidaVO.setNroCuenta(entrada.getNroCuenta());
		}else{
			salidaVO.setNroCuenta("");
		}
		
		if(entrada.getE_TOTAL_CUOTAS()!=null){
			salidaVO.setCantidadTotalCuotas(String.valueOf(entrada.getE_TOTAL_CUOTAS()));
		}else{
			salidaVO.setCantidadTotalCuotas("");
		}
		
		if(entrada.getDetalleCuotas() == null){
			return salidaVO;
		}
		
		for (DetalleCuotasCF detalle : entrada.getDetalleCuotas()) {
			cuotas = new SalidaQueryContractCashFlowVO();

			cuotas.setEstadoCuota(detalle.getEstadoCuota());
			cuotas.setEstadoPago(detalle.getEstadoPago());
			cuotas.setFechaVencCuota(Util.dateToStringSAP(detalle.getFechaVencCuota()));
			cuotas.setFolioPago(detalle.getFolioPago());
			cuotas.setMoneda(detalle.getMoneda());
			cuotas.setMontoAbono(Util.formatNumeroStringSap(detalle.getMontoAbono()));
			cuotas.setMontoCapital(Util.formatNumeroStringSap(detalle.getMontoCapital()));
			//clillo 25-07-2017 se deja en cero gravamenes
			//cuotas.setMontoGravamenes(detalle.getMontoGravamenes());
			cuotas.setMontoGravamenes(Util.formatNumeroStringSap("0"));
			cuotas.setMontoInteres(Util.formatNumeroStringSap(detalle.getMontoInteres()));
			cuotas.setMontoServAdic(Util.formatNumeroStringSap(detalle.getMontoServAdic()));
			cuotas.setNroCuota(String.valueOf(detalle.getNroCuota()));
			cuotas.setOficinaPago(detalle.getOficinaPago());
			cuotas.setTotalCuota(Util.formatNumeroStringSap(detalle.getTotalCuota()));
			cuotas.setTransactionType(detalle.getTransactionType());
			cuotas.setUltFechaPago("");
			if(detalle.getUltFechaPago()!=null){
				cuotas.setUltFechaPago(Util.dateToStringSAP(detalle.getUltFechaPago()));
			}
			cuotas.setCapitalRestante(Util.formatNumeroStringSap(detalle.getCapitalRestante()));
			cuotas.setMonto_pagado(Util.formatNumeroStringSap(detalle.getMonto_pagado()));
			
			listaCuotas.add(cuotas);
		}
		salidaVO.setListaCuotas(listaCuotas);
		
		return salidaVO;
	}
	
	/*private SalidaListaQueryContractCashFlowVO mapear(QueryContractCashFlowResponseOut entrada){
		SalidaListaQueryContractCashFlowVO salidaVO = new SalidaListaQueryContractCashFlowVO();
		SalidaQueryContractCashFlowVO cuotas =null;
		List<SalidaQueryContractCashFlowVO> listaCuotas = new ArrayList<SalidaQueryContractCashFlowVO>();

		if(entrada.getQueryContractCashFlowResponseOut().getLineaComercial()!=null){
			salidaVO.setLineaComercial(entrada.getQueryContractCashFlowResponseOut().getLineaComercial());
		}else{
			salidaVO.setLineaComercial("");
		}
		
		if(entrada.getQueryContractCashFlowResponseOut().getNroCuenta()!=null){
			salidaVO.setNroCuenta(entrada.getQueryContractCashFlowResponseOut().getNroCuenta());
		}else{
			salidaVO.setNroCuenta("");
		}
		
		if(entrada.getQueryContractCashFlowResponseOut().getE_TOTAL_CUOTAS()!=null){
			salidaVO.setCantidadTotalCuotas(entrada.getQueryContractCashFlowResponseOut().getE_TOTAL_CUOTAS());
		}else{
			salidaVO.setCantidadTotalCuotas("");
		}
		
		if(entrada.getQueryContractCashFlowResponseOut().getDetalleCuotas() == null){
			return salidaVO;
		}
		
		for (DetalleCuotasCF detalle : entrada.getQueryContractCashFlowResponseOut().getDetalleCuotas()) {
			cuotas = new SalidaQueryContractCashFlowVO();

			cuotas.setEstadoCuota(detalle.getEstadoCuota());
			cuotas.setEstadoPago(detalle.getEstadoPago());
			cuotas.setFechaVencCuota(detalle.getFechaVencCuota());
			cuotas.setFolioPago(detalle.getFolioPago());
			cuotas.setMoneda(detalle.getMoneda());
			cuotas.setMontoAbono(Util.formatNumeroStringSap(detalle.getMontoAbono()));
			cuotas.setMontoCapital(Util.formatNumeroStringSap(detalle.getMontoCapital()));
			//clillo 25-07-2017 se deja en cero gravamenes
			//cuotas.setMontoGravamenes(Util.formatNumeroStringSap(detalle.getMontoGravamenes()));
			cuotas.setMontoGravamenes(Util.formatNumeroStringSap("0"));
			cuotas.setMontoInteres(Util.formatNumeroStringSap(detalle.getMontoInteres()));
			cuotas.setMontoServAdic(Util.formatNumeroStringSap(detalle.getMontoServAdic()));
			cuotas.setNroCuota(Util.formatNumeroStringSap(detalle.getNroCuota()));
			cuotas.setOficinaPago(detalle.getOficinaPago());
			cuotas.setTotalCuota(Util.formatNumeroStringSap(detalle.getTotalCuota()));
			cuotas.setTransactionType(detalle.getTransactionType());
			cuotas.setUltFechaPago(detalle.getUltFechaPago());
			cuotas.setCapitalRestante(detalle.getCapitalRestante());
			cuotas.setMonto_pagado(Util.formatNumeroStringSap(detalle.getMonto_pagado()));
			listaCuotas.add(cuotas);
		}
		salidaVO.setListaCuotas(listaCuotas);
		
		return salidaVO;
	}*/
	
}
