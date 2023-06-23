package cl.laaraucana.capaservicios.webservices.client.QueryContractHeaderIn;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import cl.laaraucana.capaservicios.util.Configuraciones;
import cl.laaraucana.capaservicios.util.Constantes;
import cl.laaraucana.capaservicios.util.Utils;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractHeaderIn.treasury.MessageHeader;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractHeaderIn.web_mobile.ContractHeader;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractHeaderIn.web_mobile.QueryContractHeader;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractHeaderIn.web_mobile.QueryContractHeaderOUTServiceStub;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractHeaderIn.web_mobile.QueryContractHeaderRequest;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractHeaderIn.web_mobile.QueryContractHeaderRequestOut;
import cl.laaraucana.capaservicios.webservices.autogenerado.QueryContractHeaderIn.web_mobile.QueryContractHeaderResponseOut;
import cl.laaraucana.capaservicios.webservices.client.WSInterface;
import cl.laaraucana.capaservicios.webservices.client.QueryContractHeaderIn.VO.EntradaQueryContractHeaderInVO;
import cl.laaraucana.capaservicios.webservices.client.QueryContractHeaderIn.VO.SalidaQueryContractHeaderInVO;
import cl.laaraucana.capaservicios.webservices.model.AbstractEntradaVO;
import cl.laaraucana.capaservicios.webservices.model.AbstractSalidaVO;

public class ClienteQueryContractHeaderIn implements WSInterface{

	protected Logger logger = Logger.getLogger(this.getClass());
	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		String ep = Configuraciones.getConfig("ep.QueryContractHeaderIn");
		
		SalidaQueryContractHeaderInVO salidaVO= new SalidaQueryContractHeaderInVO();

		EntradaQueryContractHeaderInVO entradaVO = (EntradaQueryContractHeaderInVO) entrada;
		
		QueryContractHeaderOUTServiceStub stub = new QueryContractHeaderOUTServiceStub();
		stub._getServiceClient().setTargetEPR(new EndpointReference(ep));
		
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername(Configuraciones.getConfig("sap.user"));
		auth.setPassword(Configuraciones.getConfig("sap.pass"));
		auth.setPreemptiveAuthentication(true);
		stub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);
		
		QueryContractHeaderRequest query = new QueryContractHeaderRequest();
		QueryContractHeader entradaWs = new QueryContractHeader();
		entradaWs.setRUT(entradaVO.getRut().toUpperCase());
		entradaWs.setACNUM_EXT(entradaVO.getAcnum_ext()); //modificar
		
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(Utils.fechaSAP());
		messageHeader.setHOST(Configuraciones.getConfig("sap.host"));
		messageHeader.setINTERNALORGANIZATION(Configuraciones.getConfig("sap.org"));
		messageHeader.setUSER(Configuraciones.getConfig("sap.sysuser"));
		
		query.setMessageHeader(messageHeader);
		query.setQueryContractHeader(entradaWs);
		
		QueryContractHeaderRequestOut requestOUT = new QueryContractHeaderRequestOut();
		requestOUT.setQueryContractHeaderRequestOut(query);
		
		QueryContractHeaderResponseOut respuesta = new QueryContractHeaderResponseOut();
		
		try {
			respuesta = stub.queryContractHeader(requestOUT);
		} catch (Exception e) {
			throw new Exception("Error en QueryContractHeaderIn: compruebe el servicio", e);
		}
		
		String msg = respuesta.getQueryContractHeaderResponseOut().getLog().getMESSAGE() + " (" +respuesta.getQueryContractHeaderResponseOut().getLog().getSYSTEM() +")";
		if (respuesta.getQueryContractHeaderResponseOut().getResultCode().equals(Constantes.COD_RESPUESTA_SUCCESS)) {
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(Constantes.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("Carga de creditos QueryContractHeaderIn OK. Código error: 0.");
			logger.debug(salidaVO.getMensaje());
		}else if(respuesta.getQueryContractHeaderResponseOut().getResultCode().equals(Constantes.COD_RESPUESTA_VACIO)){
			salidaVO.setCodigoError(Constantes.COD_RESPUESTA_VACIO);
			salidaVO.setMensaje(msg);
		}else{
			logger.debug("Error en QueryContractHeaderIn: " + msg);
			throw new Exception("Error en QueryContractHeaderIn: " + msg);
		}
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
			credito.setPhonoAsistance(header.getPhonoAsistance());
			credito.setUnpaidinstAmount(header.getUnpaidinstAmount());
			credito.setArrearsAmount(header.getArrearsAmount());
			credito.setWaiverrate(header.getWaiverrate());
			credito.setUnpaidcharge(header.getUnpaidcharge().replaceAll("[\\s|.]", ""));
			credito.setQuantityActiveinst(header.getQuantityActiveinst());
			credito.setQuantityUnpaidinst(header.getQuantityUnpaidinst());
			credito.setRemainingBalance(header.getRemainingBalance());
			credito.setCompanyRut(header.getCompanyRut());
			
			//Valores a rellenar con 0
			credito.setMonthlyInterestrate(Utils.reemplazaNuloPorCero(header.getMonthlyInterestrate()).trim());
			credito.setUnemploymentinsur(Utils.reemplazaNuloPorCero(header.getUnemploymentinsur()).replaceAll("[\\s|.]", ""));
			credito.setLifeInsurance(Utils.reemplazaNuloPorCero(header.getLifeInsurance()).replaceAll("[\\s|.]", ""));
			credito.setLteAmount(Utils.reemplazaNuloPorCero(header.getLteAmount()).replaceAll("[\\s|.]", ""));
			credito.setNotarialCharge(Utils.reemplazaNuloPorCero(header.getNotarialCharge()).replaceAll("[\\s|.]", ""));
			credito.setInterestAmount(Utils.reemplazaNuloPorCero(header.getInterestAmount()).replaceAll("[\\s|.]", ""));
			
		}
		return credito;
	}
}
