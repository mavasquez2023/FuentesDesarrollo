package cl.laaraucana.satelites.webservices.client.QueryContractHeaderIn;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.integracion.QueryContractHeaderIn.treasury.MessageHeader;
import cl.laaraucana.satelites.integracion.QueryContractHeaderIn.web_mobile.ContractHeader;
import cl.laaraucana.satelites.integracion.QueryContractHeaderIn.web_mobile.QueryContractHeader;
import cl.laaraucana.satelites.integracion.QueryContractHeaderIn.web_mobile.QueryContractHeaderOUTServiceStub;
import cl.laaraucana.satelites.integracion.QueryContractHeaderIn.web_mobile.QueryContractHeaderRequest;
import cl.laaraucana.satelites.integracion.QueryContractHeaderIn.web_mobile.QueryContractHeaderRequestOut;
import cl.laaraucana.satelites.integracion.QueryContractHeaderIn.web_mobile.QueryContractHeaderResponseOut;
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
		String ep = Configuraciones.getConfig("ep.QueryContractHeaderIn");
		
		SalidaQueryContractHeaderInVO salidaVO= new SalidaQueryContractHeaderInVO();
//		try {
			
		EntradaQueryContractHeaderInVO entradaVO = (EntradaQueryContractHeaderInVO) entrada;
		
		QueryContractHeaderOUTServiceStub stub = new QueryContractHeaderOUTServiceStub();
		stub._getServiceClient().setTargetEPR(new EndpointReference(ep));
		
		logger.debug("Cliente instanciado");
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername(ServiciosConst.SAP_USERNAME);
		auth.setPassword(ServiciosConst.SAP_PASSWORD);
		auth.setPreemptiveAuthentication(true);
		stub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);
		logger.debug("Seguridad seteada OK");
		
		QueryContractHeaderRequest query = new QueryContractHeaderRequest();
		QueryContractHeader entradaWs = new QueryContractHeader();
		entradaWs.setRUT(entradaVO.getRut().toUpperCase());
		entradaWs.setACNUM_EXT(entradaVO.getAcnum_ext()); //modificar
		
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(Utils.fechaSAP());
		messageHeader.setHOST(ServiciosConst.SAP_HOST);
		messageHeader.setINTERNALORGANIZATION(ServiciosConst.SAP_INTERNALORG);
		messageHeader.setUSER(ServiciosConst.SAP_USER);
		
		query.setMessageHeader(messageHeader);
		query.setQueryContractHeader(entradaWs);
		logger.debug("Datos seteados al VO");
		
		QueryContractHeaderRequestOut requestOUT = new QueryContractHeaderRequestOut();
		requestOUT.setQueryContractHeaderRequestOut(query);
		logger.debug("RequestOUT seteado OK");
		
//<==== Codigo nuevo, con validacion
		QueryContractHeaderResponseOut respuesta = new QueryContractHeaderResponseOut();
		
		try {
			respuesta = stub.queryContractHeader(requestOUT);
		} catch (Exception e) {
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en servicio QueryContractHeaderIn: compruebe el servicio" );
			return salidaVO;
		}
		
		if (respuesta.getQueryContractHeaderResponseOut().getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)) {
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("Carga de creditos QueryContractHeaderIn OK. Código error: 0.");
			logger.debug(salidaVO.getMensaje());
		}else{
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);			
			String msg = respuesta.getQueryContractHeaderResponseOut().getLog().getMESSAGE() + " (" +respuesta.getQueryContractHeaderResponseOut().getLog().getSYSTEM() +")";
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
	
	
	private SalidaQueryContractHeaderInVO mapear(QueryContractHeaderResponseOut entrada) {

		SalidaQueryContractHeaderInVO credito = new SalidaQueryContractHeaderInVO();
		if(entrada != null){
			ContractHeader header = entrada.getQueryContractHeaderResponseOut().getQueryContractHeader();
			credito.setContractNumber(header.getContractNumber());
			credito.setComercialLine(header.getComercialLine());
			credito.setContractEnd(header.getContractEnd());
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
