package cl.laaraucana.contratocr.ws;

import org.apache.log4j.Logger;

import cl.laaraucana.contratocr.util.Configuraciones;
import cl.laaraucana.contratocr.vo.AbstractSalidaVO;
import cl.laaraucana.contratocr.vo.SalidaSinacofiVO;
import cl.sinacofi.WebServices.CEDU0102SoapStub;
import cl.sinacofi.WebServices.RespuestaCEDU0102;



public class ClienteSinacofi {

	protected Logger logger = Logger.getLogger(this.getClass());

	public AbstractSalidaVO call(String rut, String serie) throws Exception {
		String ep = Configuraciones.getConfig("ep.sinacofi");
		String usuario = Configuraciones.getConfig("servicios.sinacofi.username");
		String clave= Configuraciones.getConfig("servicios.sinacofi.pass");
		
		SalidaSinacofiVO salidaListaVO = new SalidaSinacofiVO();
		//EntradaSinacofiVO entradaVO = (EntradaSinacofiVO) entrada;

		CEDU0102SoapStub stub = new CEDU0102SoapStub();
		//stub.setUsername(username);
		//stub.setPassword(password);
		stub._setProperty(CEDU0102SoapStub.ENDPOINT_ADDRESS_PROPERTY, ep);

		RespuestaCEDU0102 respuesta = new RespuestaCEDU0102();
		
		try {
			respuesta = stub.consulta(usuario, clave, rut, serie);
		} catch (Exception e) {
			logger.error("Error al consultar Sinacofi, mensaje= " + e.getMessage());
			e.printStackTrace();
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			salidaListaVO.setMensaje("Error en servicio Sinacofi: compruebe el servicio");
			return salidaListaVO;
		}
		

		if(respuesta!=null){
			salidaListaVO = mapear(respuesta);
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
			salidaListaVO.setMensaje("Carga de datos ClienteSinacofi OK.");
		}else{
			String msg = "Error en servicio Sinacofi, respuesta null";				
			salidaListaVO.setMensaje(msg);
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			logger.warn("Error en servicio Sinacofi, mensaje: " + salidaListaVO.getMensaje());
		}

		return salidaListaVO;

	}

	private SalidaSinacofiVO mapear(RespuestaCEDU0102 response) {
		
		SalidaSinacofiVO info_sinacofi = new SalidaSinacofiVO();
		info_sinacofi.setCodigoRetorno(response.getCodigoRetorno());
		info_sinacofi.setExisteDetalle(response.getExisteDetalle());
		info_sinacofi.setCedulaVigente(response.getCedulaVigente());
		info_sinacofi.setNumeroRegistros(response.getNumeroRegistros());
		info_sinacofi.setDetalle(response.getDetalles());
		
		return info_sinacofi;
	}
}