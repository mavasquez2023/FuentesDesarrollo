package cl.laaraucana.satelites.webservices.client.EarlyPayoffSimulationOUT;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.integracion.EarlyPayoffSimulationOUT.treasury.MessageHeader;
import cl.laaraucana.satelites.integracion.EarlyPayoffSimulationOUT.web_mobile.EarlyPayoffContract;
import cl.laaraucana.satelites.integracion.EarlyPayoffSimulationOUT.web_mobile.EarlyPayoffContractRequest;
import cl.laaraucana.satelites.integracion.EarlyPayoffSimulationOUT.web_mobile.EarlyPayoffSimulationOUTServiceStub;
import cl.laaraucana.satelites.integracion.EarlyPayoffSimulationOUT.web_mobile.EarlyPayoffSimulationRequestOut;
import cl.laaraucana.satelites.integracion.EarlyPayoffSimulationOUT.web_mobile.EarlyPayoffSimulationResponseOut;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.EarlyPayoffSimulationOUT.VO.EntradaEarlyPayoffSimulationOUT;
import cl.laaraucana.satelites.webservices.client.EarlyPayoffSimulationOUT.VO.SalidaEarlyPayoffSimulationOUT;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;

public class ClienteEarlyPayoffSimulationOUT implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		
		logger.debug("Inicio Web Service: EarlyPayoffSimulationOUT");
		String ep = Configuraciones.getConfig("ep.EarlyPayOffSimulation");
		
		SalidaEarlyPayoffSimulationOUT salidaVO = new SalidaEarlyPayoffSimulationOUT();

//		try {
		EntradaEarlyPayoffSimulationOUT entradaVO = (EntradaEarlyPayoffSimulationOUT) entrada;
		logger.debug("Cliente instanciado");
		
		EarlyPayoffSimulationOUTServiceStub stub = new EarlyPayoffSimulationOUTServiceStub();
		stub._getServiceClient().setTargetEPR(new EndpointReference(ep));
		
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername(ServiciosConst.SAP_USERNAME);
		auth.setPassword(ServiciosConst.SAP_PASSWORD);
		auth.setPreemptiveAuthentication(true);
		stub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);
		logger.debug("Seguridad seteada OK");
		
		//Seteo los datos de ingreso Id Contrato y FechaFullEpo en la clase de EarlyPayoffContract
		EarlyPayoffContract ingresoDatos = new EarlyPayoffContract();
		ingresoDatos.setCONTRACT_ID_I(entradaVO.getIdContrato());
		ingresoDatos.setFULL_EPO_DATE(entradaVO.getFechaFullEpo());
		ingresoDatos.setTIPO_PREPAGO(entradaVO.getParametro());
		logger.debug("Campos seteados en EarlyPayoffContract");
		

		EarlyPayoffContractRequest query = new EarlyPayoffContractRequest();
//			EarlyPayoffSimulationRequestOut query = new EarlyPayoffSimulationRequestOut();

		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(Utils.fechaSAP());
		messageHeader.setHOST(ServiciosConst.SAP_HOST);
		messageHeader.setINTERNALORGANIZATION(ServiciosConst.SAP_INTERNALORG);
		messageHeader.setUSER(ServiciosConst.SAP_USER);
		
		query.setEarlyPayoffContract(ingresoDatos);
		query.setMessageHeader(messageHeader);
		logger.debug("Datos seteados al VO");
	
		EarlyPayoffSimulationRequestOut requestOUT = new EarlyPayoffSimulationRequestOut();
		requestOUT.setEarlyPayoffSimulationRequestOut(query);
		logger.debug("RequestOUT seteado OK");

//<==== Codigo nuevo, con validacion	
		EarlyPayoffSimulationResponseOut respuesta = new EarlyPayoffSimulationResponseOut();
		
		try {
			respuesta = stub.earlyPayoffSimulation(requestOUT);
		} catch (Exception e) {
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en servicio EarlyPayoffSimulationOUT: compruebe el servicio "+e.getMessage() );
			return salidaVO;
		}		
		
		if(respuesta.getEarlyPayoffSimulationResponseOut().getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("Carga de EarlyPayoffSimulationOUT Banking OK. Código error: 0.");
			logger.debug(salidaVO.getMensaje());
		}else{
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			String msg = respuesta.getEarlyPayoffSimulationResponseOut().getLog().getMESSAGE() + " (" + respuesta.getEarlyPayoffSimulationResponseOut().getLog().getSYSTEM() + ")";			
			salidaVO.setMensaje("Error en servicio EarlyPayoffSimulationOUT: " + msg);
			logger.debug(salidaVO.getMensaje());	
		}
//<==== Codigo nuevo, con validacion



//<=====	Codigo antiguo, sin validacion			
//			EarlyPayoffSimulationResponseOut respuesta = stub.earlyPayoffSimulation(requestOUT);
//			System.out.println(respuesta.getEarlyPayoffSimulationResponseOut().getPayoffContract().getCONTRACT_ID());
//			System.out.println(respuesta.getEarlyPayoffSimulationResponseOut().getPayoffContract().getARREARS_AMOUNT());
//			System.out.println(respuesta.getEarlyPayoffSimulationResponseOut().getPayoffContract().getMONTO_EPO());
//			System.out.println(respuesta.getEarlyPayoffSimulationResponseOut().getPayoffContract().getPAYMENT_DATE());
//			System.out.println(respuesta.getEarlyPayoffSimulationResponseOut().getPayoffContract().getREMAINING_BALANCE());
//			System.out.println(respuesta.getEarlyPayoffSimulationResponseOut().getPayoffContract().getUNPAID_CHARGE());
//			System.out.println(respuesta.getEarlyPayoffSimulationResponseOut().getPayoffContract().getWAIVER_RATE());
		
//		} catch (Exception e) {
//			logger.debug("error");
//			salidaVO = new SalidaEarlyPayoffSimulationOUT();
//			salidaVO.setCodigoError("1");
//			salidaVO.setMensaje("Excepcion en ClienteEarlyPayoffSimulationOUT: " + e.getMessage());
//	}
//<=====	Codigo antiguo, sin validacion
		logger.debug(">> Salida Web Service: EarlyPayoffSimulationOUT");	
		return salidaVO;
	}
	
	
	private SalidaEarlyPayoffSimulationOUT mapear(EarlyPayoffSimulationResponseOut entrada){
		SalidaEarlyPayoffSimulationOUT salidaVO = new SalidaEarlyPayoffSimulationOUT();
		salidaVO.setContractId(entrada.getEarlyPayoffSimulationResponseOut().getPayoffContract().getCONTRACT_ID());
		salidaVO.setRemainingBalance(entrada.getEarlyPayoffSimulationResponseOut().getPayoffContract().getREMAINING_BALANCE());
		salidaVO.setPaymentDate(entrada.getEarlyPayoffSimulationResponseOut().getPayoffContract().getPAYMENT_DATE());
		salidaVO.setMontoEpo(entrada.getEarlyPayoffSimulationResponseOut().getPayoffContract().getMONTO_EPO());
		salidaVO.setArrearsAmount(entrada.getEarlyPayoffSimulationResponseOut().getPayoffContract().getARREARS_AMOUNT());
		salidaVO.setWaiverRate(entrada.getEarlyPayoffSimulationResponseOut().getPayoffContract().getWAIVER_RATE());
		salidaVO.setUnpaidCharge(entrada.getEarlyPayoffSimulationResponseOut().getPayoffContract().getUNPAID_CHARGE());
		return salidaVO;
	}
		

}
