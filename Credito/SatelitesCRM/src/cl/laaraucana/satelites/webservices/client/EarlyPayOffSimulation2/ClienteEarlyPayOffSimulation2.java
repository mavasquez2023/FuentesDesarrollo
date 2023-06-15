package cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2;

import java.util.ArrayList;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.treasury.MessageHeader;
import cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile.DetalleCuotasSIMULATION_IN_2;
import cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile.EarlyPayoffContract2;
import cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile.EarlyPayoffContractRequest2;
import cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile.EarlyPayoffSimulationOUT2ServiceStub;
import cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile.EarlyPayoffSimulationRequestOut2;
import cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile.EarlyPayoffSimulationResponseOut2;
import cl.laaraucana.satelites.integracion.EarlyPayOffSimulacion2.web_mobile.PayoffContract2;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.VO.EntradaEarlyPayOffSimulation2;
import cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.VO.SalidaDetalleCuotasEarlyPayOff2;
import cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2.VO.SalidaEarlyPayOffSimulation2;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;

public class ClienteEarlyPayOffSimulation2 implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		logger.debug("Inicio Web Service: EarlyPayOffSimulation2");
		String ep = Configuraciones.getConfig("ep.EarlyPayOffSimulation2");
		
		SalidaEarlyPayOffSimulation2 salidaVO = new SalidaEarlyPayOffSimulation2();

		EntradaEarlyPayOffSimulation2 entradaVO = (EntradaEarlyPayOffSimulation2) entrada;

		EarlyPayoffSimulationOUT2ServiceStub stub = new EarlyPayoffSimulationOUT2ServiceStub();
		stub._getServiceClient().setTargetEPR(new EndpointReference(ep));

		
		logger.debug("Cliente instanciado");

		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername(ServiciosConst.SAP_USERNAME);
		auth.setPassword(ServiciosConst.SAP_PASSWORD);
		auth.setPreemptiveAuthentication(true);
		stub._getServiceClient().getOptions()
				.setProperty(HTTPConstants.AUTHENTICATE, auth);
		logger.debug("Seguridad seteada OK");

		EarlyPayoffContract2 query1 = new EarlyPayoffContract2();
		EarlyPayoffContractRequest2 query = new EarlyPayoffContractRequest2();
		

		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(Utils.fechaSAP());
		messageHeader.setHOST(ServiciosConst.SAP_HOST);
		messageHeader.setINTERNALORGANIZATION(ServiciosConst.SAP_INTERNALORG);
		messageHeader.setUSER(ServiciosConst.SAP_USER);

		query1.setCONTRACT_ID_I(entradaVO.getContrato());
		query1.setTIPO_PREPAGO(entradaVO.getTipoPrepago());
		query1.setFECHA(entradaVO.getFechaFullEpo());
		
		query.setMessageHeader(messageHeader);
		query.setEarlyPayoffContract(query1);
		
		logger.debug("Datos seteados al VO");

		EarlyPayoffSimulationRequestOut2 requestOUT = new EarlyPayoffSimulationRequestOut2();
		requestOUT.setEarlyPayoffSimulationRequestOut2(query);
		logger.debug("RequestOUT seteado OK");

		EarlyPayoffSimulationResponseOut2 respuesta = new EarlyPayoffSimulationResponseOut2();
		
		try {
			respuesta = stub.earlyPayoffSimulation(requestOUT);
		} catch (Exception e) {
			e.printStackTrace();
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en servicio EarlyPayOffSimulation2, Folio:" + entradaVO.getContrato() + ". Compruebe el servicio.");
			return salidaVO;
		}
		
		if(respuesta.getEarlyPayoffSimulationResponseOut2().getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("Carga de datos EarlyPayOffSimulation2 OK. Código error: 0");
			logger.debug(salidaVO.getMensaje());
		}else{
			if(respuesta.getEarlyPayoffSimulationResponseOut2().getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)){
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO);
				salidaVO.setMensaje("EarlyPayOffSimulation2. código de error: 2 vacio");
				logger.debug(salidaVO.getMensaje());
			}else{
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);				
				String msg = respuesta.getEarlyPayoffSimulationResponseOut2().getLog().getMESSAGE() + " (" + respuesta.getEarlyPayoffSimulationResponseOut2().getLog().getSYSTEM() + ")";				
				salidaVO.setMensaje("Error en servicio EarlyPayOffSimulation2: " + msg);
				logger.debug(salidaVO.getMensaje());
			}
		}
//<==== Validacion Nueva	
		logger.debug(">> Salida Web Service: QueryBPStatusOUT");
		return salidaVO;

	}

	private SalidaEarlyPayOffSimulation2 mapear(EarlyPayoffSimulationResponseOut2 response) {
		PayoffContract2 pay = response.getEarlyPayoffSimulationResponseOut2().getPayoffContract();

		SalidaEarlyPayOffSimulation2 salidaVO = new SalidaEarlyPayOffSimulation2();
		
		ArrayList<SalidaDetalleCuotasEarlyPayOff2> lista = new ArrayList<SalidaDetalleCuotasEarlyPayOff2>();

		//salidaVO.setMontoSeguros(pay.getMONTO_SEGUROS());
		salidaVO.setMontoComPrep(pay.getMONTO_COM_PREP());
		salidaVO.setMontoIntMoroso(pay.getMONTO_INT_MOROSO());
		salidaVO.setMontoIntDVG(pay.getMONTO_INT_DVG());
		salidaVO.setCuotasMorosas(pay.getCUOTAS_MOROSAS());
		
		//nuevo
		salidaVO.setSegDesgravamen(pay.getSEGURO_DESGRAV());
		salidaVO.setSegCesantia(pay.getSEGURO_CESANT());
		
		salidaVO.setTipoBp(pay.getTIPO_BP());
		salidaVO.setLineaCredito(pay.getLINEA_CREDITO());
		salidaVO.setMoroso(pay.getMOROSO());
		
		salidaVO.setCuotaDesde(pay.getCUOTA_DESDE());
		salidaVO.setCuotasEnTransito(pay.getCUOT_TRANSITO());
		salidaVO.setNumCuotasEnTransito(pay.getNUM_CUOT_TRANSITO());
		salidaVO.setCuotasInformadas(pay.getCUOTA_INFORM());
		salidaVO.setMontoCuota(pay.getMONTOCUOTA());
		
		//agregado clillo 26-09-2017
		 salidaVO.setTotalDiferido(pay.getMONTO_DIFERIDO());
	     salidaVO.setCuotaHasta(pay.getCUOTA_HASTA());
	     salidaVO.setMontoEPO(pay.getMONTO_EPO());
	     salidaVO.setTotalCuotasInformadas(pay.getMONTO_CUOTA_INF());
	     salidaVO.setGravamenes(pay.getARREARS_AMOUNT());
	     salidaVO.setGastoCobranza(pay.getGASTO_COBRANZA());
	     salidaVO.setSaldoCapital(pay.getREMAINING_BALANCE());
		
	   //agregado clillo 18-07-2018
	     salidaVO.setHonorarios(pay.getHONORARIOS());
	     salidaVO.setMontoSeguroDegravamen(pay.getMONTO_SEG_DVG());
	     salidaVO.setMontoSeguroMoroso(pay.getMONTO_SEG_MOROSO());
	     salidaVO.setMontoSeguros(String.valueOf(Double.parseDouble(pay.getMONTO_SEG_DVG())+ Double.parseDouble(pay.getMONTO_SEG_MOROSO())));
	     
		if(pay.getDetalleCuotas() != null){
			
			for (DetalleCuotasSIMULATION_IN_2 cuota : pay.getDetalleCuotas()) {
	
				SalidaDetalleCuotasEarlyPayOff2 detCuota = new SalidaDetalleCuotasEarlyPayOff2();
				
				if(cuota.getFECHA_PAGO() != null && cuota.getMONTO_EPO() != null){
					
					detCuota.setFechaDePago(cuota.getFECHA_PAGO());
					detCuota.setTotalAPagar(Utils.stringToDouble(cuota.getMONTO_EPO()));
					lista.add(detCuota);
				}
				
			}
		}
		salidaVO.setDetalleCuotas(lista);

		return salidaVO;
	}
}