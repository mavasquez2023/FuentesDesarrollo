package cl.laaraucana.satelites.webservices.client.QueryContractHeaderIn;


import java.util.Date;

import org.apache.log4j.Logger;
import com.lautaro.xi.BS.Treasury.MessageHeader;
import com.lautaro.xi.BS.WEB_Mobile.ContractHeader;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractHeader;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractHeaderOUTBindingStub;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractHeaderRequest;
import com.lautaro.xi.BS.WEB_Mobile.QueryContractHeaderResponse;
import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.QueryContractHeaderIn.VO.EntradaQueryContractHeaderInVO;
import cl.laaraucana.satelites.webservices.client.QueryContractHeaderIn.VO.SalidaQueryContractHeaderInVO;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;

public class ClienteQueryContractHeaderIn implements WSInterface{

	protected Logger logger = Logger.getLogger(this.getClass());
	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		logger.debug("Inicio Web Service: QueryContractHeaderIn");		
		SalidaQueryContractHeaderInVO salidaVO= new SalidaQueryContractHeaderInVO();
//		try {
			
		EntradaQueryContractHeaderInVO entradaVO = (EntradaQueryContractHeaderInVO) entrada;
		String ep = Configuraciones.getConfig("ep.QueryContractHeaderIn");
		String username = ServiciosConst.SAP_USERNAME;
		String password= ServiciosConst.SAP_PASSWORD;
		
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
		messageHeader.setHOST(ServiciosConst.SAP_HOST);
		messageHeader.setINTERNALORGANIZATION(ServiciosConst.SAP_INTERNALORG);
		messageHeader.setUSER(username);
		
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
//<==== Codigo nuevo, con validacion
			
			
			
//<=====	Codigo antiguo, sin validacion
//			salidaVO = mapear(stub.queryContractHeader(requestOUT));
//			
//			salidaVO.setCodigoError("0");
//			salidaVO.setMensaje("Carga de creditos QueryCompContHeaderIn OK");
//			
//			
//		} catch (Exception e) {
//			salidaVO = new SalidaQueryContractHeaderInVO();
//			salidaVO.setCodigoError("1");
//			salidaVO.setMensaje("Excepcion en ClienteQueryCompContHeaderIn: " + e.getMessage());
//		}
//<=====	Codigo antiguo, sin validacion
		logger.debug("Salida WebService: QueryContractHeaderIn");
		return salidaVO;
	}
	
	
	private SalidaQueryContractHeaderInVO mapear(QueryContractHeaderResponse entrada) {

		SalidaQueryContractHeaderInVO credito = new SalidaQueryContractHeaderInVO();
		if(entrada != null){
			ContractHeader header = entrada.getQueryContractHeader();
			credito.setContractNumber(header.getContractNumber());
			credito.setComercialLine(header.getComercialLine());
			credito.setContractEnd(Utils.dateToStringSAP(header.getContractEnd()));
			credito.setContractCurrency(header.getContractCurrency());
			credito.setStatus(header.getStatus());
			credito.setMonthlyInterestrate(header.getMonthlyInterestrate());
			credito.setLteAmount(header.getLteAmount());
			credito.setNotarialCharge(header.getNotarialCharge());
			credito.setInterestAmount(header.getInterestAmount());
			credito.setLifeInsurance(header.getLifeInsurance());
			credito.setUnemploymentinsur(header.getUnemploymentinsur());
			credito.setPhonoAsistance(header.getPhonoAsistance());
			credito.setUnpaidinstAmount(header.getUnpaidinstAmount());
			credito.setArrearsAmount(header.getArrearsAmount());
			credito.setWaiverrate(header.getWaiverrate());
			credito.setUnpaidcharge(header.getUnpaidcharge());
			credito.setQuantityActiveinst(header.getQuantityActiveinst());
			credito.setQuantityUnpaidinst(header.getQuantityUnpaidinst());
			credito.setRemainingBalance(header.getRemainingBalance());
			credito.setCompanyRut(header.getCompanyRut());
		}

		return credito;

	}
	
}
