package cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT;

import java.util.ArrayList;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.integracion.QueryBPStatusOUT.legacy.MessageHeader;
import cl.laaraucana.satelites.integracion.QueryBPStatusOUT.web_mobile.QueryBPStatus;
import cl.laaraucana.satelites.integracion.QueryBPStatusOUT.web_mobile.QueryBPStatusOUTServiceStub;
import cl.laaraucana.satelites.integracion.QueryBPStatusOUT.web_mobile.QueryBPStatusRequestOut;
import cl.laaraucana.satelites.integracion.QueryBPStatusOUT.web_mobile.QueryBPStatusResponseOut;
import cl.laaraucana.satelites.integracion.QueryBPStatusOUT.web_mobile.QueryBPStatusRut;

import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO.EntradaAfiliadoVO;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO.SalidaAfiliadoVO;
import cl.laaraucana.satelites.webservices.client.QueryBPStatusOUT.VO.SalidaListaAfiliadoVO;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;

public class ClienteQueryBPStatusOUT implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		logger.debug("Inicio Web Service: QueryBPStatusOUT");
		String ep = Configuraciones.getConfig("ep.QueryBPStatus");
		
		SalidaListaAfiliadoVO salidaListaVO = new SalidaListaAfiliadoVO();

		EntradaAfiliadoVO entradaVO = (EntradaAfiliadoVO) entrada;

		QueryBPStatusOUTServiceStub stub = new QueryBPStatusOUTServiceStub();
		stub._getServiceClient().setTargetEPR(new EndpointReference(ep));

		
		logger.debug("Cliente instanciado");

		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername(ServiciosConst.SAP_USERNAME);
		auth.setPassword(ServiciosConst.SAP_PASSWORD);
		auth.setPreemptiveAuthentication(true);
		stub._getServiceClient().getOptions()
				.setProperty(HTTPConstants.AUTHENTICATE, auth);
		logger.debug("Seguridad seteada OK");

		QueryBPStatusRut query = new QueryBPStatusRut();

		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(Utils.fechaSAP());
		messageHeader.setHOST(ServiciosConst.SAP_HOST);
		messageHeader.setINTERNALORGANIZATION(ServiciosConst.SAP_INTERNALORG);
		messageHeader.setUSER(ServiciosConst.SAP_USER);

		query.setRut(entradaVO.getRut().toUpperCase());
		query.setMessageHeader(messageHeader);
		logger.debug("Datos seteados al VO");

		QueryBPStatusRequestOut requestOUT = new QueryBPStatusRequestOut();
		requestOUT.setQueryBPStatusRequestOut(query);
		logger.debug("RequestOUT seteado OK");

		QueryBPStatusResponseOut respuesta = new QueryBPStatusResponseOut();
		
		try {
			respuesta = stub.queryBPStatus(requestOUT);
		} catch (Exception e) {
			salidaListaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaListaVO.setMensaje("Error en servicio QueryBPStatus: compruebe el servicio");
			return salidaListaVO;
		}
		
		

//<==== Validacion Antigua	
//		if (!respuesta.getQueryBPStatusResponseOut().getResultCode().getResultCode().equals("3")) {
//			salidaListaVO.setCodigoError("1");
//			logger.debug("error respuesta vacia.");
//		} else {
//
//			salidaListaVO = mapear(respuesta);
//			
//			logger.debug("Llamada al ws ok");
//			
//			if(salidaListaVO.getListaAfiliado() == null || salidaListaVO.getListaAfiliado().isEmpty()){
//				salidaListaVO.setCodigoError("1");
//				salidaListaVO.setMensaje("La persona no se encuentra como afiliado en SAP");
//			}else{
//				
//				salidaListaVO.setCodigoError("0");
//				salidaListaVO.setMensaje("Carga de Afiliado Banking OK");
//			}			
//			
//		}
//<==== Validacion Antigua
		
//<==== Validacion Nueva	
		if(respuesta.getQueryBPStatusResponseOut().getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
			salidaListaVO = mapear(respuesta);
			salidaListaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			salidaListaVO.setMensaje("Carga de datos ClienteQueryBPStatusOUT OK. Código error: 0");
			logger.debug(salidaListaVO.getMensaje());
		}else{
			if(respuesta.getQueryBPStatusResponseOut().getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)){
				salidaListaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO);
				salidaListaVO.setMensaje("ClienteQueryBPStatusOUT. El rut no se encuentra como afiliado. 2");
				logger.debug(salidaListaVO.getMensaje());
			}else{
				salidaListaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);				
				String msg = respuesta.getQueryBPStatusResponseOut().getLog()[0].getMESSAGE() + " (" + respuesta.getQueryBPStatusResponseOut().getLog()[0].getSYSTEM() + ")";				
				salidaListaVO.setMensaje("Error en servicio QueryBPStatus: " + msg);
				logger.debug(salidaListaVO.getMensaje());
			}
		}
//<==== Validacion Nueva	
		logger.debug(">> Salida Web Service: QueryBPStatusOUT");
		return salidaListaVO;

	}

	private SalidaListaAfiliadoVO mapear(QueryBPStatusResponseOut response) {
		QueryBPStatus[] bp = response.getQueryBPStatusResponseOut()
				.getQueryBPStatus();

		SalidaListaAfiliadoVO salidaLista = new SalidaListaAfiliadoVO();
		ArrayList<SalidaAfiliadoVO> lista = new ArrayList<SalidaAfiliadoVO>();

		for (QueryBPStatus queryBPStatus : bp) {

			SalidaAfiliadoVO afiliado = new SalidaAfiliadoVO(
					queryBPStatus.getRut().trim(), // Rut
					queryBPStatus.getNombreCompleto().replaceAll(" +", " ").trim(), // NombreCompleto
					queryBPStatus.getFechaAfiliacion().trim(), // FechaAfiliacion
					queryBPStatus.getEstadoAfiliacion(), // EstadoAfiliacion
					queryBPStatus.getRol().trim(), // Rol
					queryBPStatus.getRazonSocial().replaceAll(" +", " ").trim()// RazonSocial

			);
			lista.add(afiliado);
		}

		salidaLista.setListaAfiliado(lista);

		return salidaLista;
	}
}