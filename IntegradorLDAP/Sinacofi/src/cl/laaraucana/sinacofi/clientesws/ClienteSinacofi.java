package cl.laaraucana.sinacofi.clientesws;

import java.util.ArrayList;
import org.apache.log4j.Logger;

import cl.laaraucana.sinacofi.clientesws.model.AbstractEntradaVO;
import cl.laaraucana.sinacofi.clientesws.model.AbstractSalidaVO;
import cl.laaraucana.sinacofi.clientesws.model.ConstantesRespuestasWS;
import cl.laaraucana.sinacofi.clientesws.model.WSInterface;
import cl.laaraucana.sinacofi.clientesws.vo.EntradaSinacofiVO;
import cl.laaraucana.sinacofi.clientesws.vo.SalidaSinacofiVO;
import cl.laaraucana.sinacofi.utils.Configuraciones;
import cl.sinacofi.WebServices.CEDU0102SoapStub;
import cl.sinacofi.WebServices.RespuestaCEDU0102;


public class ClienteSinacofi implements WSInterface {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception {
		logger.info("Inicio Web Service: Sinacofi");

		SalidaSinacofiVO salidaListaVO = new SalidaSinacofiVO();
		EntradaSinacofiVO entradaVO = (EntradaSinacofiVO) entrada;

		CEDU0102SoapStub stub = new CEDU0102SoapStub();
		//stub.setUsername(username);
		//stub.setPassword(password);
		stub._setProperty(CEDU0102SoapStub.ENDPOINT_ADDRESS_PROPERTY, entradaVO.getUrl());
		logger.info("Endpoint:" + entradaVO.getUrl());

		RespuestaCEDU0102 respuesta = new RespuestaCEDU0102();
		
		try {
			logger.info("Invocando consulta, usuario:" + entradaVO.getUsuario() + ", rut:" + entradaVO.getRut() + ", serie:" + entradaVO.getSerie());
			respuesta = stub.consulta(entradaVO.getUsuario(), entradaVO.getClave(), entradaVO.getRut(), entradaVO.getSerie());
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