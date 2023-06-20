package cl.laaraucana.mandatocesantia.ws;


import java.util.Date;

import org.apache.log4j.Logger;

import cl.laaraucana.mandatocesantia.util.Configuraciones;
import cl.laaraucana.mandatocesantia.util.ConstantesRespuestasAPP;
import cl.laaraucana.mandatocesantia.util.ConstantesRespuestasWS;
import cl.laaraucana.mandatocesantia.vo.AbstractEntradaVO;
import cl.laaraucana.mandatocesantia.vo.AbstractSalidaVO;
import cl.laaraucana.mandatocesantia.ws.VO.EntradaQueryContractHeaderInVO;
import cl.laaraucana.mandatocesantia.ws.VO.SalidaQueryContractHeaderInVO;

import com.lautaro.xi.BS.Treasury.MessageHeader;
import com.lautaro.xi.BS.WEB_Mobile.ContractHeader;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractHeader;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractHeaderOUTBindingStub;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractHeaderRequest;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractHeaderResponse;


public class ClienteQueryContractHeaderIn implements WSInterfaceSAP{

	protected Logger logger = Logger.getLogger(this.getClass());
	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		logger.debug("Inicio Web Service: QueryContractHeaderIn");		
		SalidaQueryContractHeaderInVO salidaVO= new SalidaQueryContractHeaderInVO();
//		try {
			
		EntradaQueryContractHeaderInVO entradaVO = (EntradaQueryContractHeaderInVO) entrada;
		String ep = Configuraciones.getConfig("ep.QueryContractHeaderIn");
		String username = Configuraciones.getConfig("servicios.sap.username");
		String password= Configuraciones.getConfig("servicios.sap.pass");
		
		QueryContractHeaderOUTBindingStub stub = new QueryContractHeaderOUTBindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(QueryContractHeaderOUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		logger.debug("Datos autenticacion QueryCompContHeader seteados");
		
		
		QueryContractHeaderRequest query = new QueryContractHeaderRequest();
		QueryContractHeader entradaWs = new QueryContractHeader();
		entradaWs.setRUT(entradaVO.getRut().toUpperCase());
		entradaWs.setACNUM_EXT(entradaVO.getAcnum_ext()); //modificar
		
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(new Date());
		messageHeader.setHOST(Configuraciones.getConfig("servicios.sap.host"));
		messageHeader.setINTERNALORGANIZATION(Configuraciones.getConfig("servicios.sap.internalOrg"));
		messageHeader.setUSER(Configuraciones.getConfig("servicios.sap.user"));
		
		query.setMessageHeader(messageHeader);
		query.setQueryContractHeader(entradaWs);
		logger.debug("Datos seteados al VO");
		
//<==== Codigo nuevo, con validacion
		QueryContractHeaderResponse respuesta = new QueryContractHeaderResponse();
		
		try {
			respuesta = stub.queryContractHeader(query);
		} catch (Exception e) {
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en servicio QueryContractHeaderIn: compruebe el servicio" );
			return salidaVO;
		}
		
		if (respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)) {
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("Carga de creditos QueryContractHeaderIn OK. Código error: 0.");
			logger.debug(salidaVO.getMensaje());
		}else{
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);			
			String msg = respuesta.getLog().getMESSAGE() + " (" +respuesta.getLog().getSYSTEM() +")";
			salidaVO.setMensaje("Error en servicio QueryContractHeaderIn: " + msg);
			logger.debug(salidaVO.getMensaje());
		}

		logger.debug("Salida WebService: QueryContractHeaderIn");
		return salidaVO;
	}
	
	
	private SalidaQueryContractHeaderInVO mapear(QueryContractHeaderResponse entrada) {

		SalidaQueryContractHeaderInVO credito = new SalidaQueryContractHeaderInVO();
		if(entrada != null){
			ContractHeader header = entrada.getQueryContractHeader();
			credito.setUnemploymentinsur(header.getUnemploymentinsur());
		}

		return credito;

	}
	
}
