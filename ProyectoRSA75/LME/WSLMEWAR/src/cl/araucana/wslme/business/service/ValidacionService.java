package cl.araucana.wslme.business.service;

import cl.araucana.wslme.common.exception.WSLMEException;

public interface ValidacionService {
	
	public Boolean validacionGeneral(String codigoOperador, String claveOperador,
			String codigoCCAF, Integer rutTrabajadorNum, String rutTrabajadorDig) throws WSLMEException;
}
