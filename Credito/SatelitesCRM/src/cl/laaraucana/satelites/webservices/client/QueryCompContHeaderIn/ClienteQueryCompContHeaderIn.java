package cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.integracion.QueryCompContHeaderIn.treasury.MessageHeader;
import cl.laaraucana.satelites.integracion.QueryCompContHeaderIn.web_mobile.QueryCompContHeaderOUTServiceStub;
import cl.laaraucana.satelites.integracion.QueryCompContHeaderIn.web_mobile.QueryCompactContract;
import cl.laaraucana.satelites.integracion.QueryCompContHeaderIn.web_mobile.QueryCompactContractHeader;
import cl.laaraucana.satelites.integracion.QueryCompContHeaderIn.web_mobile.QueryCompactContractHeaderRequest;
import cl.laaraucana.satelites.integracion.QueryCompContHeaderIn.web_mobile.QueryCompactContractHeaderRequestOut;
import cl.laaraucana.satelites.integracion.QueryCompContHeaderIn.web_mobile.QueryCompactContractHeaderResponseOut;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.EntradaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.SalidaListaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.SalidaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;

public class ClienteQueryCompContHeaderIn implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		String ep = Configuraciones.getConfig("ep.QueryComCompContHeaderIn");
		
		SalidaListaQueryCompContHeaderInVO salidaVO = new SalidaListaQueryCompContHeaderInVO();
		
//		try {
		EntradaQueryCompContHeaderInVO entradaVO = (EntradaQueryCompContHeaderInVO) entrada;
		logger.debug("<< Inicio Web Service: QueryCompContHeaderIn: "+entradaVO.getRut());
		
		QueryCompContHeaderOUTServiceStub stub = new QueryCompContHeaderOUTServiceStub();
		stub._getServiceClient().setTargetEPR(new EndpointReference(ep));
		
		logger.debug("Cliente instanciado");
		
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername(ServiciosConst.SAP_USERNAME);
		auth.setPassword(ServiciosConst.SAP_PASSWORD);
		auth.setPreemptiveAuthentication(true);
		stub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);
		logger.debug("Seguridad seteada OK");
		
		QueryCompactContractHeaderRequest query = new QueryCompactContractHeaderRequest();
		QueryCompactContract entradaWs = new QueryCompactContract();
		
		entradaWs.setRut(entradaVO.getRut().toUpperCase());
		entradaWs.setCreditStatus(entradaVO.getFlagTipoCredito());
		
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(Utils.fechaSAP());
		messageHeader.setHOST(ServiciosConst.SAP_HOST);
		messageHeader.setINTERNALORGANIZATION(ServiciosConst.SAP_INTERNALORG);
		messageHeader.setUSER(ServiciosConst.SAP_USER);
		
		query.setMessageHeader(messageHeader);
		query.setQueryCompactContractHeader(entradaWs);
		logger.debug("Datos seteados al VO");
		
		QueryCompactContractHeaderRequestOut requestOUT = new QueryCompactContractHeaderRequestOut();
		requestOUT.setQueryCompactContractHeaderRequestOut(query);
		logger.debug("RequestOUT seteado OK");
		
//<=====	Codigo nuevo, con validacion
		QueryCompactContractHeaderResponseOut respuesta = new QueryCompactContractHeaderResponseOut();
		try {
			respuesta = stub.queryCompContrHeader(requestOUT);
		} catch (RemoteException e) {
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en servicio QueryCompContHeaderIn: compruebe el servicio");
			return salidaVO;
		} 
		
		if (respuesta.getQueryCompactContractHeaderResponseOut().getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)) {
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("Carga de creditos QueryCompContHeaderIn OK. Código error: 0.");
			logger.debug(salidaVO.getMensaje());	
		}else {
			// codigo error 1 por lista vacia.
			if(respuesta.getQueryCompactContractHeaderResponseOut().getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)){
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
				salidaVO.setMensaje("QueryCompContHeaderIn OK. El rut no contiene créditos. 0");
				logger.debug(salidaVO.getMensaje());	
			}else{
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);				
				String msg = respuesta.getQueryCompactContractHeaderResponseOut().getLog()[0].getMESSAGE() + " (" + respuesta.getQueryCompactContractHeaderResponseOut().getLog()[0].getSYSTEM() + ")";
				salidaVO.setMensaje("Error en servicio QueryCompContHeaderIn: " + msg);
				logger.debug(salidaVO.getMensaje());
			}
		}
//<=====	Codigo nuevo, con validacion
		
		
		
//<=====	Codigo antiguo, sin validacion
//			salidaVO = mapear(stub.queryCompContrHeader(requestOUT));
//			logger.debug("Llamada al ws ok");
//			
//			salidaVO.setCodigoError("0");
//			salidaVO.setMensaje("Carga de creditos QueryCompContHeaderIn OK");
//			
//		} catch (Exception e) {
//			salidaVO = new SalidaListaQueryCompContHeaderInVO();
//			salidaVO.setCodigoError("1");
//			salidaVO.setMensaje("Excepcion en ClienteQueryCompContHeaderIn: " + e.getMessage());
//			e.printStackTrace();
//		}
//<=====	Codigo antiguo, sin validacion
		
		logger.debug(">> Salida Web Service: QueryCompContHeaderIn");
		return salidaVO;
	}

	private SalidaListaQueryCompContHeaderInVO mapear(
			QueryCompactContractHeaderResponseOut entrada) {

		SalidaListaQueryCompContHeaderInVO salidaVO = new SalidaListaQueryCompContHeaderInVO();
		List<SalidaQueryCompContHeaderInVO> listaCreditos = new ArrayList<SalidaQueryCompContHeaderInVO>();
		SalidaQueryCompContHeaderInVO credito = null;
		
		for (QueryCompactContractHeader creditoWs : entrada
				.getQueryCompactContractHeaderResponseOut()
				.getQueryCompactContractHeader()) {
			credito = new SalidaQueryCompContHeaderInVO();
		
			credito.setContractAmount(creditoWs.getContractAmount());
			credito.setContractBegin(creditoWs.getContractBegin());
			credito.setContractCurrency(creditoWs.getContractCurrency());
			credito.setContractEnd(creditoWs.getContractEnd());
			credito.setFecha_ult_pago(creditoWs.getFecha_ult_pago());
			credito.setContractNumber(creditoWs.getContractNumber());
			credito.setInstallmentAmount(creditoWs.getInstallmentAmount());
			credito.setInstallmentQuantity(creditoWs.getInstallmentQuantity());
			credito.setPayer(creditoWs.getPayer());
			credito.setRepacta(creditoWs.getRepacta());
			credito.setReprogramac(creditoWs.getReprogramac());
			credito.setRole(creditoWs.getRole());
			credito.setTerminated(creditoWs.getTerminated());
			credito.setWithExtintion(creditoWs.getWithExtintion());

			listaCreditos.add(credito);
		}
		salidaVO.setListaCreditos(listaCreditos);

		return salidaVO;
	}
	
}
