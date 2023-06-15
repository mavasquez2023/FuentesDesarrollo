package cl.laaraucana.satelites.webservices.client.infoProtestosOUT;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import cl.laaraucana.satelites.Utils.Configuraciones;
import cl.laaraucana.satelites.Utils.Utils;
import cl.laaraucana.satelites.integracion.infoProtestos.treasury.MessageHeader;
import cl.laaraucana.satelites.integracion.infoProtestos.web_mobile.InfoProtesto;
import cl.laaraucana.satelites.integracion.infoProtestos.web_mobile.InfoProtestoRequest;
import cl.laaraucana.satelites.integracion.infoProtestos.web_mobile.InfoProtestoRequestOut;
import cl.laaraucana.satelites.integracion.infoProtestos.web_mobile.InfoProtestoResponse;
import cl.laaraucana.satelites.integracion.infoProtestos.web_mobile.InfoProtestoResponseOut;
import cl.laaraucana.satelites.integracion.infoProtestos.web_mobile.InfoProtestosOUTServiceStub;
import cl.laaraucana.satelites.webservices.WSInterface;
import cl.laaraucana.satelites.webservices.client.infoProtestosOUT.VO.EntradaInfoProtestVO;
import cl.laaraucana.satelites.webservices.client.infoProtestosOUT.VO.SalidaInfoProtestVO;
import cl.laaraucana.satelites.webservices.model.AbstractEntradaVO;
import cl.laaraucana.satelites.webservices.model.AbstractSalidaVO;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasAPP;
import cl.laaraucana.satelites.webservices.utils.ConstantesRespuestasWS;
import cl.laaraucana.satelites.webservices.utils.ServiciosConst;

public class ClienteInfoProtesto implements WSInterface {
	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {

		logger.debug("Entra a InfoProtestos");

		EntradaInfoProtestVO entradaVO = (EntradaInfoProtestVO) entrada;

		InfoProtesto infoIN = new InfoProtesto();
		infoIN.setIM_CONTRACTNUMBER(entradaVO.getContractNumber());

		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername(ServiciosConst.SAP_USERNAME);
		auth.setPassword(ServiciosConst.SAP_PASSWORD);
		auth.setPreemptiveAuthentication(true);

		MessageHeader messageHeadercontract = new MessageHeader();
		messageHeadercontract.setDATECREATION(Utils.fechaSAP());
		messageHeadercontract.setHOST(ServiciosConst.SAP_HOST);
		messageHeadercontract.setINTERNALORGANIZATION(ServiciosConst.SAP_INTERNALORG);
		messageHeadercontract.setUSER(ServiciosConst.SAP_USER);

		InfoProtestoRequest infoRequest = new InfoProtestoRequest();
		infoRequest.setMessageHeader(messageHeadercontract);
		infoRequest.setInfoProtesto(infoIN);

		InfoProtestoRequestOut infoRequestOUT = new InfoProtestoRequestOut();
		infoRequestOUT.setInfoProtestoRequestOut(infoRequest);
		
		
	
		InfoProtestosOUTServiceStub  infoStrub = new InfoProtestosOUTServiceStub();
		infoStrub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);

		String ep = Configuraciones.getConfig("ep.InfoProtestos");
		infoStrub._getServiceClient().setTargetEPR(new EndpointReference(ep));

		SalidaInfoProtestVO salida = new SalidaInfoProtestVO();
		InfoProtestoResponseOut infoResponseOut = new InfoProtestoResponseOut();

		try {
			infoResponseOut = infoStrub.infoProtestosIN(infoRequestOUT);
		} catch (Exception e) {
			salida.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
			salida.setMensaje("Error en Servicio InfoProtestos: compruebe el servicio");
			logger.debug("QueryContactHeader error = " + e.getMessage());
			return salida;
		}


		if (infoResponseOut.getInfoProtestoResponseOut().getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS)) {
			logger.debug("contract codigo error=" + 0);
			salida = mapeo(infoResponseOut.getInfoProtestoResponseOut());
			salida.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
			salida.setMensaje("Retorna datos OK");

		} else {
			if (infoResponseOut.getInfoProtestoResponseOut().getResultCode().equals(ConstantesRespuestasWS.COD_RESPUESTA_VACIO)) {
				salida.setAmount("0");
				salida.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_SUCCESS);
				salida.setMensaje("El Servicio InfoProtestos retorna datos vacio");

			} else {
				salida.setCodigoError(ConstantesRespuestasAPP.COD_RESPUESTA_ERROR);
				salida.setMensaje("Error en Servicio InfoProtestos: " + infoResponseOut.getInfoProtestoResponseOut().getLog()[0].getMESSAGE() + "("
						+ infoResponseOut.getInfoProtestoResponseOut().getLog()[0].getSYSTEM() + ")");
				logger.debug("error en la llamada");

			}

		}

		logger.debug("Salida WebService: InfoProtestos");
		return salida;
	}

	public SalidaInfoProtestVO mapeo(InfoProtestoResponse infoProtestoResponse) {

		SalidaInfoProtestVO salidaInfo = new SalidaInfoProtestVO(

				infoProtestoResponse.getInfoProtesto().getEX_CONTRACTNUMBER().trim(), 
				infoProtestoResponse.getInfoProtesto().getEX_AMOUNT().trim(), 
				infoProtestoResponse.getInfoProtesto().getEX_FLAG_PROTESTO().trim());
		return salidaInfo;
	}

}