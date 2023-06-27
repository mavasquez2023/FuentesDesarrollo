package cl.araucana.wslme.integration.dao;

import cl.araucana.wslme.business.to.Operador;
import cl.araucana.wslme.common.exception.WSLMEException;

public interface OperadorDao {
	public Operador getOperador(String codOperador) throws WSLMEException;
}
