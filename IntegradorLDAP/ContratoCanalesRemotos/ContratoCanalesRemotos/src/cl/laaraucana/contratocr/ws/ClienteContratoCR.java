/**
 * 
 */
package cl.laaraucana.contratocr.ws;

import java.math.BigInteger;

import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;

import cl.laaraucana.contratocr.controller.InitController;
import cl.laaraucana.contratocr.util.Configuraciones;
import cl.laaraucana.contratocr.vo.SalidainfoAfiliadoVO;

import com.lautaro.xi.CRM.Legacy.Os_GetContrCanalRemoto_SIBindingStub;
import com.lautaro.xi.CRM.WEB_Mobile.DT_INFO_AFILIADO_REQ;
import com.lautaro.xi.CRM.WEB_Mobile.DT_INFO_AFILIADO_RES;
import com.lautaro.xi.CRM.WEB_Mobile.SI_INFO_AFILIADO_OUTBindingStub;

import functions.rfc.sap.document.sap_com.ZRFC_GET_CONTR_CANAL_REMOTO;
import functions.rfc.sap.document.sap_com.ZRFC_GET_CONTR_CANAL_REMOTOResponse;

/**
 * @author IBM Software Factory
 *
 */
public class ClienteContratoCR {
	private static final Logger logger = Logger.getLogger(ClienteContratoCR.class);
	public String declaraContrato(String rut) throws AxisFault { 
	 
		
		String ep = Configuraciones.getConfig("ep.crm");
		String username = Configuraciones.getConfig("servicios.sap.username");
		String password= Configuraciones.getConfig("servicios.sap.pass");
		
	 

		Os_GetContrCanalRemoto_SIBindingStub stub = new Os_GetContrCanalRemoto_SIBindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(Os_GetContrCanalRemoto_SIBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);

		ZRFC_GET_CONTR_CANAL_REMOTO parameters = new ZRFC_GET_CONTR_CANAL_REMOTO();
		parameters.setIV_RUT(rut);
		 
		ZRFC_GET_CONTR_CANAL_REMOTOResponse respuesta = new ZRFC_GET_CONTR_CANAL_REMOTOResponse();
		
		try {
			respuesta = stub.os_GetContrCanalRemoto_SI(parameters);
		} catch (Exception e) {
			logger.warn("Error en servicio Contrato Canales Remotos, mensaje: " + e.getMessage());
		}
		
		if(respuesta!=null){
			return mapear(respuesta);
		}else{
			logger.warn("Error en servicio Contrato Canales Remotos, mensaje: respuesta null" );
		}

		return "Error";

	}
	
	private String mapear(ZRFC_GET_CONTR_CANAL_REMOTOResponse response) {
		BigInteger subrc= response.getEV_SUBRC();
		if(subrc.intValue()== 4){
			return "OK";
		}
		return "Error";
		
	}
}
