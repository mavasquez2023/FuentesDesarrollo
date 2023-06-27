package cl.araucana.wslme.business.service.impl;

import org.apache.log4j.Logger;

import cl.araucana.wslme.business.service.ValidacionService;
import cl.araucana.wslme.common.exception.WSLMEException;
import cl.araucana.wslme.common.util.ConfigUtil;
import cl.araucana.wslme.common.util.RutUtil;

public class ValidacionServiceImpl implements ValidacionService{
	private Logger log = Logger.getLogger(ValidacionServiceImpl.class);
	
	public Boolean validacionGeneral(String codigoOperador, String claveOperador,
			String codigoCCAF, Integer rutTrabajadorNum, String rutTrabajadorDig) throws WSLMEException{
		
		log.debug("Validando el Codigo del Operador");
		if(codigoOperador == null || codigoOperador.trim().equals("")){
			log.error("Codigo 0101: Codigo operador no definido");
			throw new WSLMEException("0101","Error, Codigo de Operador no definido.");
		}
		
		log.debug("Validando la Clave del Operador");
		if(claveOperador == null || claveOperador.trim().equals("")){
			log.error("Codigo 0102: Clave Operador no definida");
			throw new WSLMEException("0102","Error, Clave de Operador no definida.");
		}
		
		log.debug("Validando el Codigo CCAF");
		if(codigoCCAF == null || codigoCCAF.trim().equals("")){
			log.error("Codigo 0103: Codigo CCAF no definido");
			throw new WSLMEException("0103","Error, Codigo de CCAF no definido.");
		}
		String codCCAF = ConfigUtil.getValorConfiguracionesDeNegocio("araucana.wslme.business.codigoccaf");
		if(!codigoCCAF.trim().equals(codCCAF)){
			log.error("Codigo 0104: Codigo CCAF no corresponde a esta caja");
			throw new WSLMEException("0104","Error, Codigo CCAF no corresponde a esta caja.");
		}
		
		log.debug("Validando el Rut del Trabajador");
		if(rutTrabajadorNum == null || rutTrabajadorDig == null){
			log.error("Codigo 0105: Rut del trabajador o digito verificador no definido");
			throw new WSLMEException("0105","Error, Rut del trabajador o digito verificador no definido.");
		}
		if(!RutUtil.validaRut(rutTrabajadorNum, rutTrabajadorDig).booleanValue()){
			log.error("Codigo 0106: Rut del Trabajador no valido");
			throw new WSLMEException("0106","Error, Rut del Trabajador no valido.");
		}
		
		log.info("Los parametros de entrada se validaron correctamente");
		return new Boolean(true);
	}
	
}