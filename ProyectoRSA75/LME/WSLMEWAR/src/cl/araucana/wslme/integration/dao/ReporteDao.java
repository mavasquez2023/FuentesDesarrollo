package cl.araucana.wslme.integration.dao;

import java.io.File;
import java.util.List;

import cl.araucana.wslme.common.exception.WSLMEException;

public interface ReporteDao {
	public File guardarReporte(List contenido) throws WSLMEException;
}
