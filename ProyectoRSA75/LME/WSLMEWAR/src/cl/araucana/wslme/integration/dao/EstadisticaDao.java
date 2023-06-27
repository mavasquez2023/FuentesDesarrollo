package cl.araucana.wslme.integration.dao;

import java.util.List;

import cl.araucana.wslme.common.exception.WSLMEException;

public interface EstadisticaDao {
	public List getEstadisticas(Integer mes) throws WSLMEException;
}
