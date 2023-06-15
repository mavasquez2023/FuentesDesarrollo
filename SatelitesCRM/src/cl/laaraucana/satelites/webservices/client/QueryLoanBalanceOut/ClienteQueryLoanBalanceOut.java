package cl.laaraucana.satelites.webservices.client.QueryLoanBalanceOut;

import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.DetalleMonto_type0;
import cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.MessageHeader;
import cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.QueryLoanAcctBalance;
import cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.QueryLoanBalance;
import cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.QueryLoanBalanceOUTServiceStub;
import cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.QueryLoanBalanceRequest;
import cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.QueryLoanBalanceRequestOut;
import cl.laaraucana.satelites.integracion.QueryLoanBalanceOut.web_mobile.QueryLoanBalanceResponseOut;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.QueryLoanBalanceOut.VO.DetalleMontoQueryLoanBalanceOut;
import cl.laaraucana.satelites.webservices.client.QueryLoanBalanceOut.VO.EntradaQueryLoanBalanceOut;
import cl.laaraucana.satelites.webservices.client.QueryLoanBalanceOut.VO.SalidaListaQueryLoanBalanceOut;
import cl.laaraucana.satelites.webservices.client.QueryLoanBalanceOut.VO.SalidaQueryLoanBalanceOut;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;

public class ClienteQueryLoanBalanceOut implements WSInterface{

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		
		EntradaQueryLoanBalanceOut entradaVO = (EntradaQueryLoanBalanceOut) entrada;
		logger.debug("<< Ingreso a ClienteQueryL oanBalanceOut con folio: "+entradaVO.getNroContrato());
		logger.debug("<< Ingreso a ClienteQueryLoanBalanceOut con rut: "+entradaVO.getRut());
		
		String ep = Configuraciones.getConfig("ep.QueryLoanBalanceOut");
		
		SalidaListaQueryLoanBalanceOut salidaVO = new SalidaListaQueryLoanBalanceOut();
		
		QueryLoanBalanceOUTServiceStub stub = new QueryLoanBalanceOUTServiceStub();
		stub._getServiceClient().setTargetEPR(new EndpointReference(ep));
		
		logger.debug("Cliente instanciado");
		
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername(ServiciosConst.SAP_USERNAME);
		auth.setPassword(ServiciosConst.SAP_PASSWORD);
		auth.setPreemptiveAuthentication(true);
		stub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);
		logger.debug("Seguridad seteada OK");
		
		QueryLoanBalanceRequest query = new QueryLoanBalanceRequest();
		
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(Utils.fechaSAP());
		messageHeader.setHOST(ServiciosConst.SAP_HOST);
		messageHeader.setINTERNALORGANIZATION(ServiciosConst.SAP_INTERNALORG);
		messageHeader.setUSER(ServiciosConst.SAP_USER);
		
		QueryLoanBalance balance = new QueryLoanBalance();
		balance.setFlgMoneda(entradaVO.getFlgMoneda());
		balance.setNroContrato(entradaVO.getNroContrato());
		balance.setNroCuota(entradaVO.getNroCuota());
		balance.setRut(entradaVO.getRut().toUpperCase());
		query.setQueryLoanBalance(balance);
		query.setMessageHeader(messageHeader);
		logger.debug("Datos seteados al VO, con folio: "+balance.getNroContrato());
		
		QueryLoanBalanceRequestOut requestOut = new QueryLoanBalanceRequestOut();
		
		requestOut.setQueryLoanBalanceRequestOut(query);
		logger.debug("RequestOUT seteado OK");
//<==== Codigo nuevo, con validacion
		QueryLoanBalanceResponseOut respuesta = new QueryLoanBalanceResponseOut();
		
		//Validar caida de la conexión
		try {
			respuesta = stub.queryLoanBalance(requestOut);			
		} catch (Exception e) {
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en servicio QueryLoanBalanceOut: compruebe el servicio");
			return salidaVO;
		}		
		
		if(respuesta.getQueryLoanBalanceResponseOut().getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("Carga de datos QueryLoanBalanceOut OK. Código error: 0");
			logger.debug(salidaVO.getMensaje());
		}else{
			if(respuesta.getQueryLoanBalanceResponseOut().getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)){
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO);
				salidaVO.setMensaje("QueryLoanBalanceOut cod error: 2");
				logger.debug(salidaVO.getMensaje());
			}else{
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);				
				String msg = respuesta.getQueryLoanBalanceResponseOut().getLog()[0].getMESSAGE() + " (" + respuesta.getQueryLoanBalanceResponseOut().getLog()[0].getMESSAGE() + ")";				
				salidaVO.setMensaje("Error en servicio QueryLoanBalanceOut: " + msg);
				logger.debug(salidaVO.getMensaje());
			}
		}
		
		
//<==== Validacion Nueva	
		logger.debug(">> Salida Web Service: QueryBPStatusOUT");
		return salidaVO;
				
		
	}
	
	private SalidaListaQueryLoanBalanceOut mapear(QueryLoanBalanceResponseOut response) {


		SalidaListaQueryLoanBalanceOut salidaListaVO = new SalidaListaQueryLoanBalanceOut ();
		ArrayList<SalidaQueryLoanBalanceOut> salidaList = new ArrayList<SalidaQueryLoanBalanceOut>();
		List<DetalleMontoQueryLoanBalanceOut> detalleMontoList = null;
		
		SalidaQueryLoanBalanceOut salida = null;
		DetalleMontoQueryLoanBalanceOut detMonto = null;
		for (QueryLoanAcctBalance balance : response.getQueryLoanBalanceResponseOut().getQueryLoanAcctBalance()) {
			
			salida = new SalidaQueryLoanBalanceOut();
			salida.setNroContrato(balance.getNroContrato());
			salida.setNroCuota(balance.getNroCuota());
			salida.setFechaVto(balance.getFechaVto());
			salida.setMontoCuota(balance.getMontoCuota());
			salida.setMontoMora(balance.getMontoMora());
			salida.setMoneda(balance.getMoneda());
			detalleMontoList = new ArrayList<DetalleMontoQueryLoanBalanceOut>();
			for (DetalleMonto_type0 detalle : balance.getDetalleMonto()){
				detMonto = new DetalleMontoQueryLoanBalanceOut();
				detMonto.setConcepto(detalle.getConcepto());
				detMonto.setMonto(detalle.getMonto());
				detMonto.setGravamen(detalle.getGravamen());
				detalleMontoList.add(detMonto);
			}
			salida.setDetalleMontoList(detalleMontoList);
			salidaList.add(salida);
		}
		salidaListaVO.setSalidaList(salidaList);

		return salidaListaVO;
	}

}
