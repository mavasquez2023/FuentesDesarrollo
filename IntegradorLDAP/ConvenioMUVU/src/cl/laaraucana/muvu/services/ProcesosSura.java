package cl.laaraucana.muvu.services;

import java.util.Date;


public interface ProcesosSura {

	public boolean procesarAltas(String periodoProceso) throws Exception;
	public boolean procesarBajas(String periodoProceso) throws Exception;
}
