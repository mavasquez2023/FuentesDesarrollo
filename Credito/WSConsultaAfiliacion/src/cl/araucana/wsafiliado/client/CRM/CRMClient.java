package cl.araucana.wsafiliado.client.CRM;


import org.apache.log4j.Logger;
import java.util.Properties;

import cl.araucana.wsafiliado.util.Configuraciones;
import cl.araucana.wsafiliado.util.LoadPropertiesFile;

import com.lautaro.xi.CRM.WEB_Mobile.DT_INFO_AFILIADO_REQ;
import com.lautaro.xi.CRM.WEB_Mobile.DT_INFO_AFILIADO_RES;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusOUTBindingStub;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusResponse;
import com.lautaro.xi.CRM.WEB_Mobile.QueryBPStatusRut;
import com.lautaro.xi.CRM.WEB_Mobile.SI_INFO_AFILIADO_OUTBindingStub;


public class CRMClient {

	protected Logger logger = Logger.getLogger(this.getClass());

	public QueryBPStatusResponse call(String rut) {
		QueryBPStatusResponse respuesta=null;
		try {
			QueryBPStatusRut entrada= new QueryBPStatusRut();
			entrada.setRut(rut);
			logger.debug("Inicio Web Service: QueryBPStatus");
			String ep = Configuraciones.getConfig("ep.QueryBPStatus");
			String user =  Configuraciones.getConfig("SAP.user");
			String clave =  Configuraciones.getConfig("SAP.password");
			
			QueryBPStatusOUTBindingStub stub = new QueryBPStatusOUTBindingStub();
			stub.setUsername(user);
			stub.setPassword(clave);
			stub._setProperty(QueryBPStatusOUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
			respuesta = stub.queryBPStatus(entrada);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Problemas al invocar ws QueryBPStatus, mensaje= " + e.getMessage());
		} 
	
		return respuesta;
	}
	
	public DT_INFO_AFILIADO_RES callInfo(String rut) {
		DT_INFO_AFILIADO_RES respuesta=null;
		try {

			logger.debug("Inicio Web Service: DT_INFO_AFILIADO_RES");
			String ep = Configuraciones.getConfig("ep.InfoAfiliado");
			String user =  Configuraciones.getConfig("SAP.user");
			String clave =  Configuraciones.getConfig("SAP.password");
			
			SI_INFO_AFILIADO_OUTBindingStub stub = new SI_INFO_AFILIADO_OUTBindingStub();
			stub.setUsername(user);
			stub.setPassword(clave);
			stub._setProperty(SI_INFO_AFILIADO_OUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
			
			DT_INFO_AFILIADO_REQ query = new DT_INFO_AFILIADO_REQ();
			query.setRUT_BP(rut);
			
			respuesta = stub.SI_INFO_AFILIADO_OUT(query);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Problemas al invocar ws DT_INFO_AFILIADO_RES, mensaje= " + e.getMessage());
		} 
	
		return respuesta;
	}
	
	public static void main(String[] args) throws Exception {
		
	}
	
}
