package cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import com.lautaro.xi.BS.Treasury.MessageHeader;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompContHeaderOUTBindingStub;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContract;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeader;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeaderRequest;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompactContractHeaderResponse;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.EntradaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.SalidaListaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryCompContHeaderIn.VO.SalidaQueryCompContHeaderInVO;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;


public class ClienteQueryCompContHeaderIn implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada, boolean castigado) throws Exception {
				
		SalidaListaQueryCompContHeaderInVO salidaVO = new SalidaListaQueryCompContHeaderInVO();
		
//		try {
		EntradaQueryCompContHeaderInVO entradaVO = (EntradaQueryCompContHeaderInVO) entrada;
		logger.debug("<< Inicio Web Service: QueryCompContHeaderIn: "+entradaVO.getRut());
		
		String ep = Configuraciones.getConfig("ep.QueryComCompContHeaderIn");
		String username = Configuraciones.getConfig("SAP.user");
		String password= Configuraciones.getConfig("SAP.password");
		
		QueryCompContHeaderOUTBindingStub stub = new QueryCompContHeaderOUTBindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(QueryCompContHeaderOUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		logger.debug("Datos autenticacion QueryCompContHeader seteados");
		
		QueryCompactContract entradaWs = new QueryCompactContract();
		entradaWs.setRut(entradaVO.getRut().toUpperCase());
		entradaWs.setCreditStatus(entradaVO.getFlagTipoCredito());
		
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(new Date());
		messageHeader.setHOST(Configuraciones.getConfig("servicios.sap.host"));
		messageHeader.setINTERNALORGANIZATION(Configuraciones.getConfig("servicios.sap.internalOrg"));
		messageHeader.setUSER(Configuraciones.getConfig("servicios.sap.user"));
		
		QueryCompactContractHeaderRequest query = new QueryCompactContractHeaderRequest();
		query.setMessageHeader(messageHeader);
		query.setQueryCompactContractHeader(entradaWs);
		logger.debug("Datos seteados al QueryCompactContractHeaderRequest");
		
		
		QueryCompactContractHeaderResponse respuesta = new QueryCompactContractHeaderResponse();
		try {
			respuesta = stub.queryCompContrHeader(query);
			logger.debug("Respuesta Obtenida");
		} catch (RemoteException e) {
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en servicio QueryCompContHeaderIn: compruebe el servicio");
			return salidaVO;
		} 
		
		if (respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)) {
			salidaVO = mapear(respuesta, castigado);
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("Carga de creditos QueryCompContHeaderIn OK. Código error: 0.");
			logger.debug(salidaVO.getMensaje());	
		}else {
			// codigo error 1 por lista vacia.
			if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)){
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
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
			QueryCompactContractHeaderResponse entrada, boolean castigado) {

		SalidaListaQueryCompContHeaderInVO salidaVO = new SalidaListaQueryCompContHeaderInVO();
		List<SalidaQueryCompContHeaderInVO> listaCreditos = new ArrayList<SalidaQueryCompContHeaderInVO>();
		SalidaQueryCompContHeaderInVO credito = null;
		
		for (QueryCompactContractHeader creditoWs : entrada
				.getQueryCompactContractHeader()) {
			
			credito = new SalidaQueryCompContHeaderInVO();
			credito.setContractAmount(creditoWs.getContractAmount());
			credito.setContractBegin(Utils.dateToStringSAP(creditoWs.getContractBegin()));
			credito.setContractCurrency(creditoWs.getContractCurrency());
			credito.setContractEnd(Utils.dateToStringSAP(creditoWs.getContractEnd()));
			credito.setContractNumber(creditoWs.getContractNumber());
			credito.setInstallmentAmount(creditoWs.getInstallmentAmount());
			credito.setInstallmentQuantity(creditoWs.getInstallmentQuantity());
			credito.setPayer(creditoWs.getPayer());
			credito.setRepacta(creditoWs.getRepacta());
			credito.setReprogramac(creditoWs.getReprogramac());
			credito.setRole(creditoWs.getRole());
			credito.setTerminated(creditoWs.getTerminated());
			credito.setWithExtintion(creditoWs.getWithExtintion());
			credito.setInsolvencia(creditoWs.getInsolvencia());
			credito.setSeg_Cesantia(creditoWs.getSeg_Cesantia());
			credito.setSeg_Desgravamen(creditoWs.getSeg_Desgravamen());
			credito.setCastigo(creditoWs.getCastigo());
			
			//Se guarda crédito castigado
			if(castigado && creditoWs.getCastigo()!=null && creditoWs.getCastigo().equals("X")){
				listaCreditos.add(credito);
			}
			//Se guarda crédito no castigado
			if(!castigado && creditoWs.getCastigo()==null){
				listaCreditos.add(credito);
			}
			
		}
		salidaVO.setListaCreditos(listaCreditos);

		return salidaVO;
	}
	
}
