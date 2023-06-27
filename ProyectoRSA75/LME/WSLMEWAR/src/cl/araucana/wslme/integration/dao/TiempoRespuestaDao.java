package cl.araucana.wslme.integration.dao;

import java.util.List;

import cl.araucana.wslme.common.exception.WSLMEException;

public interface TiempoRespuestaDao {
	public List getTiemposRespuesta(Integer anoMes) throws WSLMEException;
}
