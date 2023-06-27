package cl.araucana.wslme.business.service;

import java.io.File;

import cl.araucana.wslme.common.exception.WSLMEException;

public interface EstadisticaService {
	
	public File generaReporteEstadisticas(Integer anoMes) throws WSLMEException;
}
