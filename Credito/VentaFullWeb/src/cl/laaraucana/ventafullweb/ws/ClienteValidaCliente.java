package cl.laaraucana.ventafullweb.ws;

import org.apache.log4j.Logger;
import cl.laaraucana.servicios.validaCliente.Request;
import cl.laaraucana.servicios.validaCliente.Response;
import cl.laaraucana.servicios.validaCliente.ValidaClientePortBindingStub;
import cl.laaraucana.ventafullweb.util.Configuraciones;
import cl.laaraucana.ventafullweb.vo.SolicitanteVo;


public class ClienteValidaCliente {
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	public Response getValidaCliente(SolicitanteVo solicitante) {
		String ep = Configuraciones.getConfig("ep.WSValidaCliente");
		String user = Configuraciones.getConfig("user.WSValidaCliente");
		String pass = Configuraciones.getConfig("pass.WSValidaCliente");
		try {
			ValidaClientePortBindingStub stub = new ValidaClientePortBindingStub();
			stub._setProperty(ValidaClientePortBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
			Request req = new Request();
			req.setUSUARIO(user);
			req.setCLAVE(pass);
			req.setRUTAFILIADO(solicitante.getRut().replaceAll("\\.", ""));
			req.setRUTEMPRESA(solicitante.getRutEmpresa().replaceAll("\\.", ""));
			Response salida = stub.validaCliente(req);
			if(salida == null ) {
				logger.error("Error al ejecutar WSValidaCliente. Respuesta null");
			} 
			int codRespuesta = salida.getCodigoRespuesta();
			logger.info("Codigo de respuesta: " + codRespuesta);
			if(codRespuesta == 1) {
				return salida;
			} else {
				logger.error("Error al ejecutar WSValidaCliente. cod resp: " + codRespuesta);
				return null;
			}
		} catch(Exception e) {
			logger.error("Error al ejecutar WSValidaCliente. " + e);
			return null;
		}
	}

}
