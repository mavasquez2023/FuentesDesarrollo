package cl.laaraucana.simulacion.webservices.client.oficinaGastosNotarial;

import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;

import cl.laaraucana.simulacion.utils.Configuraciones;
import cl.laaraucana.simulacion.utils.ConstantesRespuestasWS;
import cl.laaraucana.simulacion.webservices.autogenerado.oficinaGastosNotarial.web_mobile.Item_type0;
import cl.laaraucana.simulacion.webservices.autogenerado.oficinaGastosNotarial.web_mobile.OficinaGastoNotarialRequest2;
import cl.laaraucana.simulacion.webservices.autogenerado.oficinaGastosNotarial.web_mobile.OficinaGastoNotarialRequest2OUT;
import cl.laaraucana.simulacion.webservices.autogenerado.oficinaGastosNotarial.web_mobile.OficinaGastoNotarialResponse2E;
import cl.laaraucana.simulacion.webservices.autogenerado.oficinaGastosNotarial.web_mobile.OficinaGastosNotarialOUT2ServiceStub;
import cl.laaraucana.simulacion.webservices.client.oficinaGastosNotarial.VO.OficinaGastosNotarialSalidaLista;
import cl.laaraucana.simulacion.webservices.client.oficinaGastosNotarial.VO.OficinaGastosNotarialSalidaVO;


public class OficinaGastosNotarialClient {

	protected Logger logger = Logger.getLogger(this.getClass());

	public OficinaGastosNotarialSalidaLista call() throws Exception {
		logger.debug("Inicio Web Service: CreaCotizacion");
		String ep = Configuraciones.getConfig("ep.oficinaGastos");

		OficinaGastosNotarialSalidaLista salida = new OficinaGastosNotarialSalidaLista();

		OficinaGastosNotarialOUT2ServiceStub stub = new OficinaGastosNotarialOUT2ServiceStub();
		stub._getServiceClient().setTargetEPR(new EndpointReference(ep));

		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setUsername(Configuraciones.getConfig("SAP.user"));
		auth.setPassword(Configuraciones.getConfig("SAP.password"));
		auth.setPreemptiveAuthentication(true);
		stub._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);

		OficinaGastoNotarialRequest2OUT requestOut = new OficinaGastoNotarialRequest2OUT();
		OficinaGastoNotarialRequest2 request = new OficinaGastoNotarialRequest2();
		request.setOFICINAOUTPUT("");
		requestOut.setOficinaGastoNotarialRequest2OUT(request);
		try {
			OficinaGastoNotarialResponse2E resp = stub.oficinaGastosNotarialOUT2(requestOut);
			salida = mapear(resp);
			salida.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			salida.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			salida.setMensaje("Error en servicio CreaCotizacion " + e.getMessage());
		}

		return salida;
	}

	private OficinaGastosNotarialSalidaLista mapear(OficinaGastoNotarialResponse2E servOut) {
		
		List<OficinaGastosNotarialSalidaVO> oficinasGastosList = new ArrayList<OficinaGastosNotarialSalidaVO>();
		OficinaGastosNotarialSalidaVO oficinaGastosVO;
		OficinaGastosNotarialSalidaLista salidaLista = new OficinaGastosNotarialSalidaLista();
		
		Item_type0[] oficinaGastoOutPut =  servOut.getOficinaGastoNotarialResponse2().getOFICINAOUTPUT().getItem();
		
		//for (int i = 0; i < oficinaGastoOutPut.length; i++) {
		for (Item_type0 ofi : oficinaGastoOutPut) {		
			oficinaGastosVO = new OficinaGastosNotarialSalidaVO();
			oficinaGastosVO.setCodigo(ofi.getCODIGO());
			oficinaGastosVO.setDescripcion(ofi.getDESCRIPCION());
			oficinaGastosVO.setGastoNotarial(ofi.getGASTO_NOTARIAL());
			oficinasGastosList.add(oficinaGastosVO);
		}
		
		System.out.println("se imprime la lista "+oficinasGastosList.size());
		
		salidaLista.setOficinasGastosList(oficinasGastosList);
		if(oficinasGastosList.size() == 0){
			salidaLista.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			salidaLista.setMensaje("No obtuvo las oficinas");
		}

		return salidaLista;
	}
}
