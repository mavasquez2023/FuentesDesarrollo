package cl.laaraucana.capaservicios.webservices.client.QuerySimulationWeb;

import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import cl.laaraucana.capaservicios.util.Configuraciones;
import cl.laaraucana.capaservicios.util.Constantes;
import cl.laaraucana.capaservicios.util.Utils;
import cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.MessageHeader;
import cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.PAYMENT_OPTIONS_type0;
import cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.PAYMENT_OPTIONS_type1;
import cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.QuerySimWebIn;
import cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.QuerySimWebOut;
import cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.QuerySimWebRequest;
import cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.QuerySimWebRequestOut;
import cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.QuerySimWebResponseOut;
import cl.laaraucana.capaservicios.webservices.autogenerado.QuerySimulationWeb.QuerySimulationWebOUTServiceStub;
import cl.laaraucana.capaservicios.webservices.client.QuerySimulationWeb.VO.PaymentOptionsEntradaVO;
import cl.laaraucana.capaservicios.webservices.client.QuerySimulationWeb.VO.PaymentOptionsSalidaVO;
import cl.laaraucana.capaservicios.webservices.client.QuerySimulationWeb.VO.QuerySimulationEntradaVO;
import cl.laaraucana.capaservicios.webservices.client.QuerySimulationWeb.VO.QuerySimulationSalidaVO;



public class ClienteQuerySimulationWeb {

	protected Logger logger = Logger.getLogger(this.getClass());

	public QuerySimulationSalidaVO call(QuerySimulationEntradaVO entrada) throws Exception {
		logger.debug("Inicio Web Service: "+this.getClass().getSimpleName());
		//obtener endpoint SAP
		String ep = Configuraciones.getConfig("ep.QuerySimulationWeb");
		
		QuerySimulationSalidaVO salidaVO = new QuerySimulationSalidaVO();
		QuerySimulationEntradaVO entradaVO = entrada;

		QuerySimulationWebOUTServiceStub stub = new QuerySimulationWebOUTServiceStub();
		//setear datos conexion con servicio SAP
		stub._getServiceClient().setTargetEPR(new EndpointReference(ep));
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername(Configuraciones.getConfig("sap.user"));
		auth.setPassword(Configuraciones.getConfig("sap.pass"));
		auth.setPreemptiveAuthentication(true);
		stub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);

		QuerySimWebRequest query = new QuerySimWebRequest();
		QuerySimWebIn querySim = new QuerySimWebIn();
		List<PAYMENT_OPTIONS_type0> paymentOptionsList = new ArrayList<PAYMENT_OPTIONS_type0>();
		
		PAYMENT_OPTIONS_type0 payment;
		for (PaymentOptionsEntradaVO paymentEntrada : entradaVO.getPaymentOptionsEntradaList()) {
			payment = new PAYMENT_OPTIONS_type0();
			payment.setAG_TERM(paymentEntrada.getAgTerm());
			payment.setINTEREST_RATE(paymentEntrada.getInterestRate());
			paymentOptionsList.add(payment);
		}
		
		PAYMENT_OPTIONS_type0[] paymentArray = paymentOptionsList.toArray(new PAYMENT_OPTIONS_type0[paymentOptionsList.size()]);
		
		querySim.setORG_ID(entradaVO.getOrgId());
		querySim.setSTART_DATE(entradaVO.getStartDate());
		querySim.setEND_DATE(entradaVO.getEndDate());
		querySim.setPRODUCT_ID(entradaVO.getProductId());
		querySim.setCREDIT_AMOUNT(entradaVO.getCreditAmount());
		querySim.setINTEREST_RATE(entradaVO.getInterestRate());
		querySim.setRATE_AMOUNT_ZNOT(entradaVO.getAmountZnot());
		querySim.setRATE_AMOUNT_ZLTE(entradaVO.getAmountZlte());
		querySim.setRATE_AMOUNT_ZSDE(entradaVO.getAmountZsde());
		querySim.setRATE_AMOUNT_ZSCE(entradaVO.getAmountZsce());
		querySim.setPENSIONADO(entradaVO.getPensionado());
		querySim.setPAYMENT_OPTIONS(paymentArray);

		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(Utils.fechaSAP());
		messageHeader.setHOST(Configuraciones.getConfig("sap.host"));
		messageHeader.setINTERNALORGANIZATION(Configuraciones.getConfig("sap.org"));
		messageHeader.setUSER(Configuraciones.getConfig("sap.sysuser"));

		query.setQuerySimWeb(querySim);
		query.setMessageHeader(messageHeader);

		QuerySimWebRequestOut requestOUT = new QuerySimWebRequestOut();
		requestOUT.setQuerySimWebRequestOut(query);

		QuerySimWebResponseOut respuesta = new QuerySimWebResponseOut();
		
		try {
			respuesta = stub.querySimulationWeb(requestOUT);
		} catch (Exception e) {
			logger.error(e);
			salidaVO.setCodigoError(Constantes.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en servicio "+this.getClass().getSimpleName()+": compruebe el servicio");
			return salidaVO;
		}
		
		if(respuesta.getQuerySimWebResponseOut().getResultCode().equals(Constantes.COD_RESPUESTA_SUCCESS)){
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(Constantes.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("Carga de datos "+this.getClass().getSimpleName()+" OK. Código error: 0");
			logger.debug(salidaVO.getMensaje());
		}else{
			if(respuesta.getQuerySimWebResponseOut().getResultCode().equals(Constantes.COD_RESPUESTA_VACIO)){
				salidaVO.setCodigoError(Constantes.COD_RESPUESTA_VACIO);
				salidaVO.setMensaje(this.getClass().getSimpleName()+". El rut no se encuentra como afiliado. 2");
				logger.debug(salidaVO.getMensaje());
			}else{
				salidaVO.setCodigoError(Constantes.COD_RESPUESTA_ERROR);				
				String msg = respuesta.getQuerySimWebResponseOut().getLog()[0].getMESSAGE() + " (" + respuesta.getQuerySimWebResponseOut().getLog()[0].getSYSTEM() + ")";				
				salidaVO.setMensaje("Error en servicio "+this.getClass().getSimpleName()+": " + msg);
				logger.debug(salidaVO.getMensaje());
			}
		}
		logger.debug(">> Salida Web Service: "+this.getClass().getSimpleName());
		return salidaVO;

	}

	private QuerySimulationSalidaVO mapear(QuerySimWebResponseOut response) {
		QuerySimulationSalidaVO salidaVO = new QuerySimulationSalidaVO();
		List<PaymentOptionsSalidaVO> paymentOptionsSalidaList = new ArrayList<PaymentOptionsSalidaVO>();
		
		QuerySimWebOut querySim = response.getQuerySimWebResponseOut().getQuerySimWeb();
		
		//Se agrega reemplazo de nulos por 0
		salidaVO.setMontoCuota(Utils.quitarDecimalesString(querySim.getMONTO_CUOTA()));
		salidaVO.setCae(Utils.reemplazaNuloPorCero(querySim.getCAE()).replace(".", ","));
		salidaVO.setCostoTotal(Utils.quitarDecimalesString(querySim.getCOSTO_TOTAL()));
		
		PAYMENT_OPTIONS_type1[] paymentSalidaArray = querySim.getPAYMENT_OPTIONS();
		
		PaymentOptionsSalidaVO paymentSalida;
		for (PAYMENT_OPTIONS_type1 paymentOption : paymentSalidaArray) {
			paymentSalida = new PaymentOptionsSalidaVO();
			paymentSalida.setAgTerm(paymentOption.getAG_TERM());
			paymentSalida.setInstallentAmount(paymentOption.getINSTALLMENT_AMOUNT());
			paymentSalida.setInterestRate(paymentOption.getINTEREST_RATE());
			paymentSalida.setStartDate(paymentOption.getSTART_DATE());
			paymentSalida.setEndDate(paymentOption.getEND_DATE());
			paymentOptionsSalidaList.add(paymentSalida);
		}
		
		salidaVO.setPaymentOptionsSalidaList(paymentOptionsSalidaList);		
		

		return salidaVO;
	}
}