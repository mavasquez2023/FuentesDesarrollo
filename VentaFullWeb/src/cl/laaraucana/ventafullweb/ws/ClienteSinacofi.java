package cl.laaraucana.ventafullweb.ws;

import org.apache.log4j.Logger;
import cl.laaraucana.servicios.validaNumeroSerie.CredencialesWS;
import cl.laaraucana.servicios.validaNumeroSerie.DatosCedula;
import cl.laaraucana.servicios.validaNumeroSerie.ResponseWS;
import cl.laaraucana.servicios.validaNumeroSerie.ValidaNumeroSeriePortBindingStub;
import cl.laaraucana.ventafullweb.util.Configuraciones;
import cl.laaraucana.ventafullweb.vo.AbstractSalidaVO;
import cl.laaraucana.ventafullweb.vo.SalidaSinacofiVO;

public class ClienteSinacofi {

	protected Logger logger = Logger.getLogger(this.getClass());
	
	public AbstractSalidaVO call(String rut, String serie) throws Exception {
		SalidaSinacofiVO salidaListaVO = new SalidaSinacofiVO();
		String ep = Configuraciones.getConfig("ep.sinacofi");
		String usuario = Configuraciones.getConfig("servicios.sinacofi.username");
		String clave= Configuraciones.getConfig("servicios.sinacofi.pass");
		logger.info("Consultando Número de Serie, url:" + ep);
		CredencialesWS credenciales= new CredencialesWS();
		credenciales.setUSUARIO(usuario);
		credenciales.setPASSWORD(clave);
		DatosCedula cedula= new DatosCedula();
		cedula.setRUT(rut);
		cedula.setNUMERO_SERIE(serie);
		ValidaNumeroSeriePortBindingStub stub= new ValidaNumeroSeriePortBindingStub();
		stub._setProperty(ValidaNumeroSeriePortBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		ResponseWS respuesta = new ResponseWS();
		try {
			respuesta = stub.validaNumeroSerie(credenciales, cedula);
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

	
	public AbstractSalidaVO callPojo(String rut, String serie) throws Exception {
		SalidaSinacofiVO salidaListaVO = new SalidaSinacofiVO();
		salidaListaVO.setCedulaVigente("SI");
		salidaListaVO.setCodigoRetorno("10000");
		salidaListaVO.setCodigoError("1");
		salidaListaVO.setMensaje("Datos generados automaticamente");
		return salidaListaVO;
	}
	
	
	private SalidaSinacofiVO mapear(ResponseWS response) {
		SalidaSinacofiVO info_sinacofi = new SalidaSinacofiVO();
		info_sinacofi.setCodigoRetorno(response.getCodigoRetorno());
		info_sinacofi.setExisteDetalle(response.getExisteDetalle());
		info_sinacofi.setCedulaVigente(response.getCedulaVigente());
		info_sinacofi.setNumeroRegistros(response.getNumeroRegistros());
		info_sinacofi.setMensaje(response.getMensaje());
		return info_sinacofi;
	}
	
}