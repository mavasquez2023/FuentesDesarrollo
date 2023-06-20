package cl.laaraucana.mandatocesantia.ws;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import cl.laaraucana.mandatocesantia.util.Configuraciones;
import cl.laaraucana.mandatocesantia.util.ConstantesRespuestasAPP;
import cl.laaraucana.mandatocesantia.util.ConstantesRespuestasWS;
import cl.laaraucana.mandatocesantia.vo.AbstractEntradaVO;
import cl.laaraucana.mandatocesantia.vo.AbstractSalidaVO;
import cl.laaraucana.mandatocesantia.ws.VO.EntradaQueryCompContHeaderInVO;
import cl.laaraucana.mandatocesantia.ws.VO.SalidaListaQueryCompContHeaderInVO;
import cl.laaraucana.mandatocesantia.ws.VO.SalidaQueryCompContHeaderInVO;

import com.lautaro.xi.BS.Treasury.MessageHeader;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompContHeaderOUTBindingStub;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContract;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeader;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeaderRequest;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeaderResponse;



public class ClienteQueryCompContHeaderIn implements WSInterfaceSAP {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
				
		SalidaListaQueryCompContHeaderInVO salidaVO = new SalidaListaQueryCompContHeaderInVO();
		
//		try {
		EntradaQueryCompContHeaderInVO entradaVO = (EntradaQueryCompContHeaderInVO) entrada;
		logger.debug("<< Inicio Web Service: QueryCompContHeaderIn: "+entradaVO.getRut());
		
		String ep = Configuraciones.getConfig("ep.QueryComCompContHeaderIn");
		String username = Configuraciones.getConfig("servicios.sap.username");
		String password= Configuraciones.getConfig("servicios.sap.pass");
		
		QueryCompContHeaderOUTBindingStub stub = new QueryCompContHeaderOUTBindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(QueryCompContHeaderOUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		logger.debug("Datos autenticacion QueryCompContHeader seteados");
				
		QueryCompactContractHeaderRequest query = new QueryCompactContractHeaderRequest();
		QueryCompactContract entradaWs = new QueryCompactContract();
		
		entradaWs.setRut(entradaVO.getRut().toUpperCase());
		entradaWs.setCreditStatus(entradaVO.getFlagTipoCredito());
		
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(new Date());
		messageHeader.setHOST(Configuraciones.getConfig("servicios.sap.host"));
		messageHeader.setINTERNALORGANIZATION(Configuraciones.getConfig("servicios.sap.internalOrg"));
		messageHeader.setUSER(Configuraciones.getConfig("servicios.sap.user"));
		
		query.setMessageHeader(messageHeader);
		query.setQueryCompactContractHeader(entradaWs);
		logger.debug("Datos seteados al VO");
		
		QueryCompactContractHeaderResponse respuesta = new QueryCompactContractHeaderResponse();
		
		try {
			respuesta = stub.queryCompContrHeader(query);
		} catch (RemoteException e) {
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en servicio QueryCompContHeaderIn: compruebe el servicio");
			return salidaVO;
		} 
		
		if (respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)) {
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("Carga de creditos QueryCompContHeaderIn OK. Código error: 0.");
			logger.debug(salidaVO.getMensaje());	
		}else {
			// codigo error 1 por lista vacia.
			if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)){
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO);
				salidaVO.setMensaje("QueryCompContHeaderIn OK. El rut no contiene créditos. 0");
				logger.debug(salidaVO.getMensaje());	
			}else{
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);				
				String msg = respuesta.getLog()[0].getMESSAGE() + " (" + respuesta.getLog()[0].getSYSTEM() + ")";
				salidaVO.setMensaje("Error en servicio QueryCompContHeaderIn: " + msg);
				logger.debug(salidaVO.getMensaje());
			}
		}
		
		logger.debug(">> Salida Web Service: QueryCompContHeaderIn");
		return salidaVO;
	}

	private SalidaListaQueryCompContHeaderInVO mapear(
			QueryCompactContractHeaderResponse entrada) {

		SalidaListaQueryCompContHeaderInVO salidaVO = new SalidaListaQueryCompContHeaderInVO();
		List<SalidaQueryCompContHeaderInVO> listaCreditos = new ArrayList<SalidaQueryCompContHeaderInVO>();
		SalidaQueryCompContHeaderInVO credito = null;
		
		for (QueryCompactContractHeader creditoWs : entrada.getQueryCompactContractHeader()) {
			credito = new SalidaQueryCompContHeaderInVO();
		
			credito.setContractNumber(creditoWs.getContractNumber());
			credito.setPayer(creditoWs.getPayer());
			credito.setRole(creditoWs.getRole());
			credito.setTerminated(creditoWs.getTerminated());

			
			listaCreditos.add(credito);
		}
		salidaVO.setListaCreditos(listaCreditos);

		return salidaVO;
	}
	
}
