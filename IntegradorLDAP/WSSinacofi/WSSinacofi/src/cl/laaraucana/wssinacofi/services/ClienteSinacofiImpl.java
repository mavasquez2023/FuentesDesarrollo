package cl.laaraucana.wssinacofi.services;

import org.apache.log4j.Logger;

import cl.laaraucana.wssinacofi.utils.Configuraciones;
import cl.laaraucana.wssinacofi.vo.AbstractEntradaVO;
import cl.laaraucana.wssinacofi.vo.AbstractSalidaVO;
import cl.laaraucana.wssinacofi.vo.ConstantesRespuestasWS;
import cl.laaraucana.wssinacofi.vo.EntradaSinacofiVO;
import cl.laaraucana.wssinacofi.vo.SalidaSinacofiVO;
import cl.laaraucana.wssinacofi.vo.WSInterface;
import cl.sinacofi.WebServices.CEDU0102SoapStub;
import cl.sinacofi.WebServices.RespuestaCEDU0102;


public class ClienteSinacofiImpl implements ClienteSinacofi, WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		logger.info("Inicio Web Service: Sinacofi");

		SalidaSinacofiVO salidaListaVO = new SalidaSinacofiVO();
		EntradaSinacofiVO entradaVO = (EntradaSinacofiVO) entrada;
		String url= Configuraciones.getConfig("ep.sinacofi");
		String usuario= Configuraciones.getConfig("servicios.sinacofi.username");
		String clave= Configuraciones.getConfig("servicios.sinacofi.pass");
		
		CEDU0102SoapStub stub = new CEDU0102SoapStub();
		stub._setProperty(CEDU0102SoapStub.ENDPOINT_ADDRESS_PROPERTY, url);
		logger.info("Endpoint Sinacofi: " + url);

		RespuestaCEDU0102 respuesta = null;
		
		try {
			logger.info("Invocando consulta, usuario:" + usuario + ", rut:" + entradaVO.getRut() + ", serie:" + entradaVO.getSerie());
			respuesta = stub.consulta(usuario, clave, entradaVO.getRut().replace("-", ""), entradaVO.getSerie());
		} catch (Exception e) {
			logger.error("Error al consultar Sinacofi, mensaje= " + e.getMessage());
			e.printStackTrace();
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			salidaListaVO.setMensaje("Error en servicio Sinacofi: compruebe el servicio");
			return salidaListaVO;
		}
		
		
//<==== Validacion Nueva	
		if(respuesta!=null){
			logger.info("Respuesta exitosa ClienteSinacofi, se mapea resultado");
			salidaListaVO = mapear(respuesta);
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_SUCCESS);
			salidaListaVO.setMensaje("Carga de datos ClienteSinacofi OK.");
		}else{
			String msg = "Error en servicio Sinacofi, respuesta null";				
			salidaListaVO.setMensaje(msg);
			salidaListaVO.setCodigoError(ConstantesRespuestasWS.COD_RESPUESTA_ERROR);
			logger.warn(salidaListaVO.getMensaje());
		}
//<==== Validacion Nueva	
		logger.info(">> Salida Web Service: Sinacofi");
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