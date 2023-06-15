package cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn;

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

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.Utils;
/*import cl.laaraucana.satelites.integracion.QueryContractCashflowIn.treasury.MessageHeader;
import cl.laaraucana.satelites.integracion.QueryContractCashflowIn.web_mobile.DetalleCuotasCF;
import cl.laaraucana.satelites.integracion.QueryContractCashflowIn.web_mobile.QueryContractCashFlowOUTServiceStub;
import cl.laaraucana.satelites.integracion.QueryContractCashflowIn.web_mobile.QueryContractCashFlowRequest;
import cl.laaraucana.satelites.integracion.QueryContractCashflowIn.web_mobile.QueryContractCashFlowRequestOut;
import cl.laaraucana.satelites.integracion.QueryContractCashflowIn.web_mobile.QueryContractCashFlowResponseOut;*/
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.VO.EntradaQueryContractCashflowInVO;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.VO.SalidaListaQueryContractCashflowInVO;
import cl.laaraucana.satelites.webservices.client.QueryContractCashflowIn.VO.SalidaQueryContractCashflowInVO;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;

public class ClienteQueryContractCashflowIn implements WSInterface {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	

	/*public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		
		
		EntradaQueryContractCashflowInVO entradaVO = (EntradaQueryContractCashflowInVO) entrada;
		logger.debug("<< Ingreso a ClienteQueryContractCashflowIn con folio: "+entradaVO.getFolioCredito());
		String ep = Configuraciones.getConfig("ep.QueryContractCashFlowOUT");
		
		SalidaListaQueryContractCashflowInVO salidaVO = new SalidaListaQueryContractCashflowInVO();
		
//		try {
		QueryContractCashFlowOUTServiceStub stub = new QueryContractCashFlowOUTServiceStub();
		stub._getServiceClient().setTargetEPR(new EndpointReference(ep));
		
		logger.debug("Cliente instanciado");
		
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername(ServiciosConst.SAP_USERNAME);
		auth.setPassword(ServiciosConst.SAP_PASSWORD);
		auth.setPreemptiveAuthentication(true);
		stub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);
		logger.debug("Seguridad seteada OK");
		
		QueryContractCashFlowRequest query = new QueryContractCashFlowRequest();
		
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(Utils.fechaSAP());
		messageHeader.setHOST(ServiciosConst.SAP_HOST);
		messageHeader.setINTERNALORGANIZATION(ServiciosConst.SAP_INTERNALORG);
		messageHeader.setUSER(ServiciosConst.SAP_USER);
		
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
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en servicio QueryContractCashflowIn: compruebe el servicio");
			return salidaVO;
		}
		
		if(respuesta.getQueryContractCashFlowResponseOut().getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("Carga de creditos QueryContractCashflowIn OK. Código error: 0.");
			logger.debug(salidaVO.getMensaje());
		}else{
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);			
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
	
	public AbstractSalidaVO call(QueryContractCashFlowRequest entrada) throws Exception {
		logger.debug("Inicio Web Service: Banking");
		QueryContractCashFlowResponse respuesta=null;
		SalidaListaQueryContractCashflowInVO salidaVO = new SalidaListaQueryContractCashflowInVO();
		
		String ep = Configuraciones.getConfig("ep.QueryContractCashFlowOUT");
		String username = ServiciosConst.SAP_USERNAME;
		String password= ServiciosConst.SAP_PASSWORD;
				
		QueryContractCashFlowOUTBindingStub stub= new 	QueryContractCashFlowOUTBindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		
		stub._setProperty(QueryContractCashFlowOUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(new Date());
		messageHeader.setHOST(ServiciosConst.SAP_HOST);
		messageHeader.setINTERNALORGANIZATION(ServiciosConst.SAP_INTERNALORG);
		messageHeader.setUSER(username);
		
		entrada.setMessageHeader(messageHeader);
		try {
		respuesta= stub.queryContractCashFlow(entrada);

		} catch (Exception e) {
			logger.error("QueryContactCashFlow error = " + e.getMessage());
			e.printStackTrace();
		}

		if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("Carga de creditos QueryContractCashflowIn OK. Código error: 0.");
			logger.debug(salidaVO.getMensaje());
		}else{
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);			
			String msg = respuesta.getLog().getMESSAGE() + " (" + respuesta.getLog().getSYSTEM() + ")";			
			salidaVO.setMensaje("Error en servicio QueryContractCashflowIn: " + msg);
			logger.debug(salidaVO.getMensaje());
		}
		logger.debug(">> Salida a ClienteQueryContractCashflowIn");
		return salidaVO;
	}
	
	
	private SalidaListaQueryContractCashflowInVO mapear(QueryContractCashFlowResponse entrada){
		SalidaListaQueryContractCashflowInVO salidaVO = new SalidaListaQueryContractCashflowInVO();
		SalidaQueryContractCashflowInVO cuotas =null;
		List<SalidaQueryContractCashflowInVO> listaCuotas = new ArrayList<SalidaQueryContractCashflowInVO>();

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
			cuotas = new SalidaQueryContractCashflowInVO();

			cuotas.setEstadoCuota(detalle.getEstadoCuota());
			cuotas.setEstadoPago(detalle.getEstadoPago());
			cuotas.setFechaVencCuota(Utils.dateToStringSAP(detalle.getFechaVencCuota()));
			cuotas.setFolioPago(detalle.getFolioPago());
			cuotas.setMoneda(detalle.getMoneda());
			cuotas.setMontoAbono(detalle.getMontoAbono());
			cuotas.setMontoCapital(detalle.getMontoCapital());
			cuotas.setMontoGravamenes(detalle.getMontoGravamenes());
			cuotas.setMontoInteres(detalle.getMontoInteres());
			cuotas.setMontoServAdic(detalle.getMontoServAdic());
			cuotas.setNroCuota(String.valueOf(detalle.getNroCuota()));
			cuotas.setOficinaPago(detalle.getOficinaPago());
			cuotas.setTotalCuota(detalle.getTotalCuota());
			cuotas.setTransactionType(detalle.getTransactionType());
			cuotas.setUltFechaPago("");
			if(detalle.getUltFechaPago()!=null){
				cuotas.setUltFechaPago(Utils.dateToStringSAP(detalle.getUltFechaPago()));
			}
			cuotas.setCapitalRestante(detalle.getCapitalRestante());
			cuotas.setMonto_pagado(detalle.getMonto_pagado());
			
			listaCuotas.add(cuotas);
		}
		salidaVO.setListaCuotas(listaCuotas);
		
		return salidaVO;
	}


	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
