package cl.laaraucana.pagoenexceso.ws;

import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;
import cl.laaraucana.config.Config;
import com.lautaro.xi.BS.WEB_Mobile.DT_PEXDisp_REQ;
import com.lautaro.xi.BS.WEB_Mobile.DT_PEXDisp_RES;
import com.lautaro.xi.BS.WEB_Mobile.SI_PEXDisp_OUTBindingStub;
 

public class ClientePexSap  {

	protected Logger logger = Logger.getLogger(this.getClass());

	 
	public Integer getDataPex(String rut) throws AxisFault { 
	 
		String ep = Config.getConfig("ep.Pex");
		String username = Config.getConfig("servicios.sap.username");
		String password= Config.getConfig("servicios.sap.pass");
		
		SI_PEXDisp_OUTBindingStub stub = new SI_PEXDisp_OUTBindingStub();
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(SI_PEXDisp_OUTBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		logger.info("Consultando SI_PEXDisp Rut: " + rut);

		DT_PEXDisp_REQ query = new DT_PEXDisp_REQ();
		query.setRUT(rut);

		DT_PEXDisp_RES respuesta = new DT_PEXDisp_RES();
		
		try {
			respuesta = stub.SI_PEXDisp_OUT(query);
		} catch (Exception e) {
			logger.error("Error consultando Pex en Sap, mensaje: " + e.getMessage());
			return null;
		}
		logger.info("monto:" + respuesta.getMONTO() );
		return  respuesta.getMONTO();

	}

}