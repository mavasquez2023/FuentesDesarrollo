package cl.laaraucana.claves.clientesws;

import java.util.ArrayList;
import org.apache.log4j.Logger;

import cl.laaraucana.claves.clientesws.model.AbstractSalidaVO;
import cl.laaraucana.claves.clientesws.model.ConstantesRespuestasWS;
import cl.laaraucana.claves.clientesws.vo.SalidaSinacofiVO;
import cl.laaraucana.claves.utils.Configuraciones;
import cl.laaraucana.servicios.validaNumeroSerie.CredencialesWS;
import cl.laaraucana.servicios.validaNumeroSerie.DatosCedula;
import cl.laaraucana.servicios.validaNumeroSerie.ResponseWS;
import cl.laaraucana.servicios.validaNumeroSerie.ValidaNumeroSeriePortBindingStub;
import cl.sinacofi.WebServices.CEDU0102SoapStub;
import cl.sinacofi.WebServices.RespuestaCEDU0102;


public class ClienteSinacofi  {

	protected Logger logger = Logger.getLogger(this.getClass());

public AbstractSalidaVO call(String rut, String serie) throws Exception {
		
		SalidaSinacofiVO salidaListaVO = new SalidaSinacofiVO();
		
		String ep = Configuraciones.getConfig("ep.sinacofi");
		String usuario = Configuraciones.getConfig("servicios.sinacofi.username");
		String clave= Configuraciones.getConfig("servicios.sinacofi.pass");
		int timeout= Integer.parseInt(Configuraciones.getConfig("sinacofi.timeout"));
		
		//CredencialesWS credenciales= new CredencialesWS();
		//credenciales.setUSUARIO(usuario);
		//credenciales.setPASSWORD(clave);
		
		//DatosCedula cedula= new DatosCedula();
		//cedula.setRUT(rut);
		//cedula.setNUMERO_SERIE(serie);

		CEDU0102SoapStub stub = new CEDU0102SoapStub();
		//ValidaNumeroSeriePortBindingStub stub= new ValidaNumeroSeriePortBindingStub();
		
		stub._setProperty(CEDU0102SoapStub.ENDPOINT_ADDRESS_PROPERTY, ep);
		//stub._setProperty(ValidaNumeroSeriePortBindingStub.ENDPOINT_ADDRESS_PROPERTY, ep);

		RespuestaCEDU0102 respuesta = new RespuestaCEDU0102();
		//ResponseWS respuesta = new ResponseWS();
		
		try {
			stub.setTimeout(timeout);
			//respuesta = stub.validaNumeroSerie(credenciales, cedula);
			respuesta = stub.consulta(usuario, clave, rut.replace("-", ""), serie);
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
		info_sinacofi.setMensaje("");
		return info_sinacofi;
	}
}