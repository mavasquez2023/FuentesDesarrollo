package cl.laaraucana.simulacion.webservices.client.ConsultaCreditoPreAprob;

import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import cl.laaraucana.simulacion.utils.CompletaUtil;
import cl.laaraucana.simulacion.utils.Configuraciones;
import cl.laaraucana.simulacion.utils.ConstantesRespuestasWS;
import cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.legacy.MessageHeader;
import cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.ConsultaCreditoPreAprob;
import cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.ConsultaCreditoPreAprobOUTServiceStub;
import cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.ConsultaCreditoPreAprobRequest;
import cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.ConsultaCreditoPreAprobRequestOut;
import cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.ConsultaCreditoPreAprobResponseOut;
import cl.laaraucana.simulacion.webservices.autogenerado.ConsultaCreditoPreAprob.web_mobile.CreditoEspecial;
import cl.laaraucana.simulacion.webservices.client.ConsultaCreditoPreAprob.VO.ConsultaCreditoPreAprobEntradaVO;
import cl.laaraucana.simulacion.webservices.client.ConsultaCreditoPreAprob.VO.ConsultaCreditoPreAprobSalidaVO;
import cl.laaraucana.simulacion.webservices.client.ConsultaCreditoPreAprob.VO.SalidaListaConsultaCreditoPreAprob;
import cl.laaraucana.simulacion.webservices.model.AbstractEntradaVO;

public class ConsultaCreditoPreAprobClient {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	private String simpleName = this.getClass().getSimpleName();
	
	public SalidaListaConsultaCreditoPreAprob call(AbstractEntradaVO entrada) throws Exception{
		logger.debug("Inicio Web Service: ConsultaCreditoPreAprob");
		String ep = Configuraciones.getConfig("ep.ConsultaCreditoPreAprob");
		
		SalidaListaConsultaCreditoPreAprob salidaListaVO = new SalidaListaConsultaCreditoPreAprob();
		ConsultaCreditoPreAprobEntradaVO entradaVO = (ConsultaCreditoPreAprobEntradaVO) entrada;
		
		ConsultaCreditoPreAprobOUTServiceStub stub = new ConsultaCreditoPreAprobOUTServiceStub();
		stub._getServiceClient().setTargetEPR(new EndpointReference(ep));
		
		//logger.debug("Cliente instanciado");
		
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername(Configuraciones.getConfig("SAP.user"));
		auth.setPassword(Configuraciones.getConfig("SAP.password"));
		auth.setPreemptiveAuthentication(true);
		stub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);
		
		
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setDATECREATION("");
		messageHeader.setHOST("");
		messageHeader.setINTERNALORGANIZATION("");
		messageHeader.setUSER("");
		
		ConsultaCreditoPreAprob consultaPre = new ConsultaCreditoPreAprob();
		
		consultaPre.setPVI_BP_PERSONA(entradaVO.getRut());
		consultaPre.setPVI_LINEA_PROD(entradaVO.getLinea());
		consultaPre.setPVI_FECHA_PROC(entradaVO.getFecha());
		
		ConsultaCreditoPreAprobRequest request = new ConsultaCreditoPreAprobRequest();
		request.setConsultaCreditoPreAprob(consultaPre);
		request.setMessageHeader(messageHeader);
		
		ConsultaCreditoPreAprobRequestOut requestOut = new ConsultaCreditoPreAprobRequestOut();
		requestOut.setConsultaCreditoPreAprobRequestOut(request);
		//logger.debug("RequestOUT seteado OK");
		
		try {
			ConsultaCreditoPreAprobResponseOut respuesta = stub.consultaCreditoPreAprobIN(requestOut);
			if(respuesta.getConsultaCreditoPreAprobResponseOut().getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)){
				salidaListaVO = mapear(respuesta);
				salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
				salidaListaVO.setMensaje("Carga de datos " + simpleName + " OK. Código error: 0");
				logger.debug(salidaListaVO.getMensaje());
			}else if(respuesta.getConsultaCreditoPreAprobResponseOut().getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)){
				salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_VACIO);				
				String msg = respuesta.getConsultaCreditoPreAprobResponseOut().getLog()[0].getMESSAGE() + " (" + respuesta.getConsultaCreditoPreAprobResponseOut().getLog()[0].getSYSTEM() + ")";				
				salidaListaVO.setMensaje("Vacio en servicio " + simpleName + ": " + msg);
				logger.debug(salidaListaVO.getMensaje());
			}else{
				salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);				
				String msg = respuesta.getConsultaCreditoPreAprobResponseOut().getLog()[0].getMESSAGE() + " (" + respuesta.getConsultaCreditoPreAprobResponseOut().getLog()[0].getSYSTEM() + ")";				
				salidaListaVO.setMensaje("Error en servicio " + simpleName + ": " + msg);
				logger.debug(salidaListaVO.getMensaje());
			}
		} catch (Exception e) {
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);				
			salidaListaVO.setMensaje("Error en servicio " + simpleName + ": " + e.getMessage());
			logger.debug(salidaListaVO.getMensaje());
		}
		
		
		
		
		return salidaListaVO;
	}
	
	
	
	
	private SalidaListaConsultaCreditoPreAprob mapear(ConsultaCreditoPreAprobResponseOut response) {
		//CreditoEspecial[] creditoEspecial = response.getConsultaCreditoPreAprobResponseOut().getCreditoEsp();
		CreditoEspecial[] creditosEspeciales = response.getConsultaCreditoPreAprobResponseOut().getCreditoEsp();
		SalidaListaConsultaCreditoPreAprob salidaListaVO = new SalidaListaConsultaCreditoPreAprob();

		List<ConsultaCreditoPreAprobSalidaVO> detallePreAprobado = new ArrayList<ConsultaCreditoPreAprobSalidaVO>();	
		for (CreditoEspecial especialWeb : creditosEspeciales) {
			ConsultaCreditoPreAprobSalidaVO salidaVO = new ConsultaCreditoPreAprobSalidaVO();
			salidaVO.setBpPersona(CompletaUtil.quitaCerosIzqString(especialWeb.getBP_PERSONA()));
			salidaVO.setRutPersona(especialWeb.getRUT_PERSONA());
			salidaVO.setBpEmpleador(CompletaUtil.quitaCerosIzqString(especialWeb.getBP_EMPLEADOR()));
			salidaVO.setRutEmpleador(especialWeb.getRUT_EMPLEADOR());
			salidaVO.setTipoCredito(especialWeb.getTIPO_CREDITO());
			salidaVO.setNoMinCuotas(CompletaUtil.quitaCerosIzqString(especialWeb.getNO_MIN_CUOTAS()));
			salidaVO.setNoMaxCuotas(CompletaUtil.quitaCerosIzqString(especialWeb.getNO_MAX_CUOTAS()));
			salidaVO.setMontoMax(CompletaUtil.quitaCerosIzqString(especialWeb.getMONTO_MAX()));
			salidaVO.setFechaIniOferta(especialWeb.getFECHA_INI_OFERTA()); 
			salidaVO.setFechaExpOferta(especialWeb.getFECHA_EXP_OFERTA());
			salidaVO.setCapitalAdeudado(especialWeb.getCAPITAL_ADEUDADO());
			salidaVO.setSumCuotasMens(especialWeb.getSUM_CUOTAS_MENS());
			salidaVO.setFechaHoraAct(especialWeb.getFECHA_HORA_ACT());
			salidaVO.setRentaInformada(especialWeb.getRENTA_INFORMADA());
			//salidaVO.setNoInscripcion(especialWeb.getNO_INSCRIPCION());
			salidaVO.setOficinaFormaliza(especialWeb.getOFICFORMALIZA());
			salidaVO.setNombreEmpleador(especialWeb.getNOMBRE_EMPLEADOR());
			detallePreAprobado.add(salidaVO);
			
		}
		salidaListaVO.setDetallePreAprobado(detallePreAprobado);
			
		return salidaListaVO;
	}
	
	
	
	
}
