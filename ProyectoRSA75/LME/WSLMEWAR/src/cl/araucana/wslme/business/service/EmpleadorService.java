package cl.araucana.wslme.business.service;

import java.util.List;

import cl.araucana.wslme.common.exception.WSLMEException;

public interface EmpleadorService {
	
	public List getEmpleadoresByRutAfiliado(Integer rut) throws WSLMEException;
}
