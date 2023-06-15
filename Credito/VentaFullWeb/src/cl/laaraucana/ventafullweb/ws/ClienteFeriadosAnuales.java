package cl.laaraucana.ventafullweb.ws;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import com.lautaro.xi.BS.WEB_Mobile.DT_FeriadosAnuales_REQ;
import com.lautaro.xi.BS.WEB_Mobile.DT_FeriadosAnuales_RESFERIADOS;
import com.lautaro.xi.BS.WEB_Mobile.SI_FeriadosAnuales_OUTBindingStub;
import com.lautaro.xi.CRM.Legacy.Os_CampWebFD_SIBindingStub;
import cl.laaraucana.ventafullweb.util.Configuraciones;


public class ClienteFeriadosAnuales {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public boolean getValidaFeriados(String agno, String fecha) {
		String ep = Configuraciones.getConfig("ep.WSFeriados");
		String user = Configuraciones.getConfig("servicios.sap.username");
		String pass = Configuraciones.getConfig("servicios.sap.pass");
		try {
			SI_FeriadosAnuales_OUTBindingStub stub = new SI_FeriadosAnuales_OUTBindingStub();
			stub._setProperty(Os_CampWebFD_SIBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
			stub._setProperty(Os_CampWebFD_SIBindingStub.USERNAME_PROPERTY, user);
			stub._setProperty(Os_CampWebFD_SIBindingStub.PASSWORD_PROPERTY, pass);
			DT_FeriadosAnuales_REQ req = new DT_FeriadosAnuales_REQ();
			BigInteger anio = BigInteger.valueOf(Long.parseLong(agno));
			req.setANIO(anio);
			DT_FeriadosAnuales_RESFERIADOS salida[] = stub.SI_FeriadosAnuales_OUT(req);
			if(salida == null ) {
				logger.error("Error al ejecutar WS WSFeriadosAnuales. Respuesta null");
			} 
			for(DT_FeriadosAnuales_RESFERIADOS fechas: salida) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String fec = sdf.format(fechas.getFECHA());
				if(fec.equals(fecha)) {
					logger.info(fec + " es feriado");
					return true;
				}
			}
			return false;
			
		} catch(Exception e) {
			logger.error("Error al ejecutar WSCampWeb. " + e);
			return false;
		}
	}
}
