package cl.araucana.wslme.business.service;

import java.io.File;

import cl.araucana.wslme.common.exception.WSLMEException;

public interface TiempoRespuestaService {
	
	public File generaReporteTiemposRespuesta(Integer anoMes) throws WSLMEException;
}
