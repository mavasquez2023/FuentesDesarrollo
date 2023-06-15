package cl.laaraucana.satelites.webservices.client.EarlyPayOffSimulation2;

import java.util.ArrayList;
import java.util.Date;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import com.lautaro.xi.BS.Treasury.MessageHeader;
import com.lautaro.xi.BS.WEB_Mobile.DetalleCuotasSIMULATION_IN_2;
import com.lautaro.xi.BS.WEB_Mobile.EarlyPayoffContract2;
import com.lautaro.xi.BS.WEB_Mobile.EarlyPayoffContractRequest2;
import com.lautaro.xi.BS.WEB_Mobile.EarlyPayoffContractResponse2;
import com.lautaro.xi.BS.WEB_Mobile.EarlyPayoffSimulationOUT2BindingStub;
import com.lautaro.xi.BS.WEB_Mobile.PayoffContract2;
import com.lautaro.xi.BS.WEB_Mobile.QueryCompContHeaderOUTBindingStub;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.Utils;
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
		
		SalidaEarlyPayOffSimulation2 salidaVO = new SalidaEarlyPayOffSimulation2();

		EntradaEarlyPayOffSimulation2 entradaVO = (EntradaEarlyPayOffSimulation2) entrada;
		
		String ep = Configuraciones.getConfig("ep.EarlyPayOffSimulation2");
		String username = ServiciosConst.SAP_USERNAME;
		String password= ServiciosConst.SAP_PASSWORD;
		
		EarlyPayoffSimulationOUT2BindingStub stub= new EarlyPayoffSimulationOUT2BindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(EarlyPayoffSimulationOUT2BindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		logger.debug("Datos autenticacion EarlyPayOffSimulation2 seteados");


		EarlyPayoffContract2 query1 = new EarlyPayoffContract2();
		EarlyPayoffContractRequest2 query = new EarlyPayoffContractRequest2();
		

		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION(new Date());
		messageHeader.setHOST(ServiciosConst.SAP_HOST);
		messageHeader.setINTERNALORGANIZATION(ServiciosConst.SAP_INTERNALORG);
		messageHeader.setUSER(ServiciosConst.SAP_USER);

		query1.setCONTRACT_ID_I(entradaVO.getContrato());
		query1.setTIPO_PREPAGO(entradaVO.getTipoPrepago());
		query1.setFECHA(Utils.stringToDateSAP(entradaVO.getFechaFullEpo()));
		
		query.setMessageHeader(messageHeader);
		query.setEarlyPayoffContract(query1);
		logger.debug("EarlyPayoffContractRequest2, Datos seteados al VO");

		EarlyPayoffContractResponse2 respuesta = new EarlyPayoffContractResponse2();
		
		try {
			respuesta = stub.earlyPayoffSimulation(query);
		} catch (Exception e) {
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salidaVO.setMensaje("Error en servicio EarlyPayOffSimulation2, Folio:" + entradaVO.getContrato() + ". Compruebe el servicio.");
			return salidaVO;
		}
		
		if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
			salidaVO = mapear(respuesta);
			salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			salidaVO.setMensaje("Carga de datos EarlyPayOffSimulation2 OK. Código error: 0");
			logger.debug(salidaVO.getMensaje());
		}else{
			if(respuesta.getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)){
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_VACIO);
				salidaVO.setMensaje("EarlyPayOffSimulation2. código de error: 2 vacio");
				logger.debug(salidaVO.getMensaje());
			}else{
				salidaVO.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);				
				String msg = respuesta.getLog().getMESSAGE() + " (" + respuesta.getLog().getSYSTEM() + ")";				
				salidaVO.setMensaje("Error en servicio EarlyPayOffSimulation2: " + msg);
				logger.debug(salidaVO.getMensaje());
			}
		}
//<==== Validacion Nueva	
		logger.debug(">> Salida Web Service: EarlyPayoffSimulationOUT");
		return salidaVO;

	}

	private SalidaEarlyPayOffSimulation2 mapear(EarlyPayoffContractResponse2 response) {
		PayoffContract2 pay = response.getPayoffContract();

		SalidaEarlyPayOffSimulation2 salidaVO = new SalidaEarlyPayOffSimulation2();
		
		ArrayList<SalidaDetalleCuotasEarlyPayOff2> lista = new ArrayList<SalidaDetalleCuotasEarlyPayOff2>();
		String monto_seg_dvg="0";
		String monto_seg_moroso="0";
		//clillo 10-06-2019
		if(pay.getMONTO_SEG_DVG()!=null && !pay.getMONTO_SEG_DVG().equals("")){
			monto_seg_dvg= pay.getMONTO_SEG_DVG().replaceAll("\\.", "").trim();
		}
		if(pay.getMONTO_SEG_MOROSO()!=null && !pay.getMONTO_SEG_MOROSO().equals("")){
			monto_seg_moroso= pay.getMONTO_SEG_MOROSO().replaceAll("\\.", "").trim();
		}
		salidaVO.setMontoSeguros(String.valueOf(Integer.parseInt(monto_seg_dvg) + Integer.parseInt(monto_seg_moroso)));
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
		
		salidaVO.setCuotaDesde(Utils.dateToStringSAP(pay.getCUOTA_DESDE()));
		salidaVO.setMontoCuotasEnTransito(pay.getMONTO_CUOTA_INF());
		salidaVO.setNumCuotasEnTransito(pay.getNUM_CUOT_TRANSITO());
		salidaVO.setCuotasInformadas(pay.getCUOTA_INFORM());
		salidaVO.setMontoCuota(pay.getMONTOCUOTA());
		
		//agregado clillo 26-09-2017
		 salidaVO.setTotalDiferido(pay.getMONTO_DIFERIDO());
	     salidaVO.setCuotaHasta(Utils.dateToStringSAP(pay.getCUOTA_HASTA()));
	     salidaVO.setMontoEPO(pay.getMONTO_EPO());
	     salidaVO.setTotalCuotasInformadas(pay.getMONTO_CUOTA_INF());
	     salidaVO.setGravamenes(pay.getARREARS_AMOUNT());
	     salidaVO.setGastoCobranza(pay.getGASTO_COBRANZA());
	     salidaVO.setSaldoCapital(pay.getREMAINING_BALANCE());
	     
	   //agregado clillo 20-07-2018
	     salidaVO.setHonorarios(pay.getHONORARIOS());
	     
	   //agregado clillo 04-12-2019
	     salidaVO.setEstadoCredito(pay.getESTADO());
	     salidaVO.setSaldoCapitalCondonado(pay.getMONTO_CAP_COND());
	     
	   //agregado J-Factory 30-06-2020
	     salidaVO.setSaldoAdeudado(pay.getSALDO_ADEUDADO());
	     salidaVO.setSaldoCapitalCuotasFuturas(pay.getSALDO_K_CUOTAS_FUTURAS());
	     salidaVO.setMontoFinalAdeudado(pay.getMONTO_FINAL_ADEUDADO());
	     salidaVO.setTotalAPagar(pay.getTOTAL_A_PAGAR());
	     salidaVO.setTasaInteres(pay.getTASA_INTERES());
		
		if(pay.getDetalleCuotas() != null){
			
			for (DetalleCuotasSIMULATION_IN_2 cuota : pay.getDetalleCuotas()) {
	
				SalidaDetalleCuotasEarlyPayOff2 detCuota = new SalidaDetalleCuotasEarlyPayOff2();
				
				if(cuota.getFECHA_PAGO() != null && cuota.getMONTO_EPO() != null){
					
					detCuota.setFechaDePago(Utils.dateToString(cuota.getFECHA_PAGO()));
					detCuota.setTotalAPagar(Utils.stringToDouble(cuota.getMONTO_EPO()));
					String incluyeCET= cuota.getINCLUYE_CTA_TRNST();
					incluyeCET= incluyeCET!=null && incluyeCET.equals("X")?"Incluye Cuotas en Tránsito":"";
					detCuota.setIncluyeCuotasTransito(incluyeCET);
					lista.add(detCuota);
				}
				
			}
		}
		salidaVO.setDetalleCuotas(lista);

		return salidaVO;
	}
}