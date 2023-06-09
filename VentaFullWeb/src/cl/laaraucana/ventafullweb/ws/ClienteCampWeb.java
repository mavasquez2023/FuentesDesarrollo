package cl.laaraucana.ventafullweb.ws;

import org.apache.log4j.Logger;
import com.lautaro.xi.CRM.Legacy.CampWebFDReq_DT;
import com.lautaro.xi.CRM.Legacy.CampWebFDRes_DT;
import com.lautaro.xi.CRM.Legacy.Os_CampWebFD_SIBindingStub;
import cl.laaraucana.ventafullweb.util.Configuraciones;

public class ClienteCampWeb {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public CampWebFDRes_DT getValidaRut(String afiliado) {
		String ep = Configuraciones.getConfig("ep.WSCampWeb");
		String user = Configuraciones.getConfig("servicios.sap.username");
		String pass = Configuraciones.getConfig("servicios.sap.pass");
		try {
			Os_CampWebFD_SIBindingStub stub = new Os_CampWebFD_SIBindingStub();
			stub._setProperty(Os_CampWebFD_SIBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
			stub._setProperty(Os_CampWebFD_SIBindingStub.USERNAME_PROPERTY, user);
			stub._setProperty(Os_CampWebFD_SIBindingStub.PASSWORD_PROPERTY, pass);
			CampWebFDReq_DT req = new CampWebFDReq_DT();
			req.setIV_RUT_AFILIADO(afiliado.replace(".", ""));
			CampWebFDRes_DT salida = stub.os_CampWebFD_SI(req);
			if(salida == null ) {
				logger.error("Error al ejecutar WS WSCampWeb. Respuesta null");
			} 
			String rutEmpresa = salida.getEV_RUT_EMPRESA();
			logger.info("Rut Empresa: " + rutEmpresa);
			if(rutEmpresa != null) {
				return salida;
			} else {
				logger.error("Error al ejecutar WSCampWeb");
				return null;
			}
		} catch(Exception e) {
			logger.error("Error al ejecutar WSCampWeb. " + e);
			return null;
		}
	}
}
