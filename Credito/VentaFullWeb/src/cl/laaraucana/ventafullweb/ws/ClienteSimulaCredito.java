package cl.laaraucana.ventafullweb.ws;

import org.apache.log4j.Logger;
import cl.laaraucana.servicios.simulaCredito.Request;
import cl.laaraucana.servicios.simulaCredito.Response;
import cl.laaraucana.servicios.simulaCredito.SimulaCreditoPortBindingStub;
import cl.laaraucana.ventafullweb.util.Configuraciones;
import cl.laaraucana.ventafullweb.vo.AfiliadoVo;

public class ClienteSimulaCredito {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public Response getValidaCredito(AfiliadoVo afiliado) {
		String ep = Configuraciones.getConfig("ep.WSSimulaCredito");
		String user = Configuraciones.getConfig("user.WSValidaCliente");
		String pass = Configuraciones.getConfig("pass.WSValidaCliente");
		String tipoSeguro = Configuraciones.getConfig("tipo.seguro");
		final String TIPO_TRABAJADOR = "1";
		final String TIPO_PENSIONADO = "2";
		try {
			SimulaCreditoPortBindingStub stub = new SimulaCreditoPortBindingStub();
			stub._setProperty(SimulaCreditoPortBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
			Request req = new Request();
			req.setUSUARIO(user);
			req.setCLAVE(pass);
			req.setMONTO(String.valueOf(afiliado.getMontoSimulacion()));
			req.setCUOTAS(String.valueOf(afiliado.getCuotas()));
			req.setSUCURSAL("001"); //por Defecto se envía siempre la misma
			if(afiliado.getSegmento().equals("PENSIONADOS")) {
				req.setTIPO_AFILIADO(TIPO_PENSIONADO);
			} else {
				req.setTIPO_AFILIADO(TIPO_TRABAJADOR);
			}
			req.setSEGURO_CESANTIA(afiliado.getSeguroCesantia());
			req.setSEGURO_DESGRAVAMEN(afiliado.getSeguroDesgravamen());
			req.setTIPO_SEGURO(tipoSeguro);
			Response salida = stub.simulaCredito(req);
			if(salida == null ) {
				logger.error("Error al ejecutar WS SimulaCredito. Respuesta null");
			} 
			String codRespuesta = salida.getRESULT_CODE();
			logger.info("Codigo de respuesta: " + codRespuesta);
			if(codRespuesta.equals("1")) {
				return salida;
			} else {
				logger.error("Error al ejecutar WSSimulaCredito. cod resp: " + codRespuesta);
				return null;
			}
		} catch(Exception e) {
			logger.error("Error al ejecutar SimulaCredito. " + e);
			return null;
		}
	}	
}
