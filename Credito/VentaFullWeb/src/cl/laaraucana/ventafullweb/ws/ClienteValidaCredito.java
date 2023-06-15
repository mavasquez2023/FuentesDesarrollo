package cl.laaraucana.ventafullweb.ws;

import org.apache.log4j.Logger;

import cl.laaraucana.servicios.validaCredito.Request;
import cl.laaraucana.servicios.validaCredito.Response;
import cl.laaraucana.servicios.validaCredito.ValidaCreditoPortBindingStub;
import cl.laaraucana.ventafullweb.util.AfiliadoUtils;
import cl.laaraucana.ventafullweb.util.Configuraciones;
import cl.laaraucana.ventafullweb.vo.AfiliadoVo;

public class ClienteValidaCredito {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public Response getValidaCredito(AfiliadoVo afiliado) {
		String ep = Configuraciones.getConfig("ep.WSValidaCredito");
		String user = Configuraciones.getConfig("user.WSValidaCliente");
		String pass = Configuraciones.getConfig("pass.WSValidaCliente");
		try {
			ValidaCreditoPortBindingStub stub = new ValidaCreditoPortBindingStub();
			stub._setProperty(ValidaCreditoPortBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
			Request req = new Request();
			req.setUSUARIO(user);
			req.setCLAVE(pass);
			req.setRUTAFILIADO(afiliado.getRutAfiliado());
			req.setRUTEMPRESA(afiliado.getRutEmpresa());
			req.setSERIECEDULA(afiliado.getSerieCedula());
			/*
			req.setMONTOSIMULADO(AfiliadoUtils.formateaVal(String.valueOf(afiliado.getMontoSimulacion())));
			req.setPLAZOSIMULADO(String.valueOf(afiliado.getCuotas()));
			req.setMONTOTOTAL(AfiliadoUtils.formateaVal(String.valueOf(afiliado.getCostoTotalCredito())));
			req.setMONTOCUOTA(AfiliadoUtils.formateaVal(String.valueOf(afiliado.getMontoCuota())));
			*/
			req.setMONTOSIMULADO(String.valueOf(afiliado.getMontoSimulacion()));
			req.setPLAZOSIMULADO(String.valueOf(afiliado.getCuotas()));
			req.setMONTOTOTAL(String.valueOf(afiliado.getCostoTotalCredito()));
			req.setMONTOCUOTA(String.valueOf(afiliado.getMontoCuota()));
			
			req.setSEGMENTO((afiliado.getSegmento().equals("PENSIONADOS")?"ZAFPEN":"ZAFTRA"));
			req.setANEXOCAMPAGNA(String.valueOf(afiliado.getIdCampagna()));
			
			String rentaPromedio = afiliado.getRentaPromedio();
			if(rentaPromedio == null || rentaPromedio.equals("0")) {
				rentaPromedio = "1";
			} else {
				rentaPromedio = afiliado.getRentaPromedio();
			}
			
			req.setRENTACOTIZADA(rentaPromedio);
			req.setPENSIONCAMPAGNA(afiliado.getInscripcionPension()); //Claudio Cubillos 15 NOV 2022 13:40
			Response salida = stub.validaCredito(req);
			if(salida == null ) {
				logger.error("Error al ejecutar WS ValidaCredito. Respuesta null");
			} 
			int codRespuesta = salida.getCodigoRespuesta();
			logger.info("Codigo de respuesta: " + codRespuesta);
			if(codRespuesta == 1) {
				return salida;
			} else {
				logger.error("Error al ejecutar WSValidaCredito. cod resp: " + codRespuesta);
				return null;
			}
		} catch(Exception e) {
			logger.error("Error al ejecutar ValidaCredito. " + e);
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
