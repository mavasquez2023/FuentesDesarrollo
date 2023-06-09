package cl.laaraucana.ventafullweb.ws;

import java.rmi.RemoteException;

import org.apache.axis.AxisFault;
import org.apache.log4j.Logger;

import com.lautaro.xi.CRM.Legacy.OfertasVigentesFDReq_DT;
import com.lautaro.xi.CRM.Legacy.OfertasVigentes_DT;
import com.lautaro.xi.CRM.Legacy.Os_OfertasVigentesFD_SIBindingStub;

import cl.laaraucana.ventafullweb.util.Configuraciones;

public class ClienteOfertasVigentes {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public OfertasVigentes_DT[] getRespuestaWSOfertasVigentes(String rutAfiliado) throws AxisFault {
		String ep = Configuraciones.getConfig("ep.WSOfertasVigentes");
		String username = Configuraciones.getConfig("servicios.sap.username");
		String password= Configuraciones.getConfig("servicios.sap.pass");
		//SalidaOfertasVigentesVO respuestaServicio = new SalidaOfertasVigentesVO();
		
		Os_OfertasVigentesFD_SIBindingStub stub = new Os_OfertasVigentesFD_SIBindingStub();	
		stub.setUsername(username);
		stub.setPassword(password);
		stub._setProperty(Os_OfertasVigentesFD_SIBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);

		OfertasVigentesFDReq_DT req = new OfertasVigentesFDReq_DT();

		req.setRUT_AFILIADO(rutAfiliado);
					
		OfertasVigentes_DT[] salida = null;
		
		try {
			salida = stub.os_OfertasVigentesFD_SI(req);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(salida == null ) {
			logger.error("Error al ejecutar WSOfertasVigentes. Respuesta null");
		} else {			
			logger.info("Se ha ejecutado correctamente el WSOfertasVigentes.");
		}
		
		return salida;	
		
		
	}
	
}