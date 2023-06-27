package cl.araucana.wslme.business.service;

import cl.araucana.wslme.common.exception.WSLMEException;

public interface OperadorService {
	
	public Boolean validaPermisoOperador(String codOperador, String claveOperador) throws WSLMEException;
}
