package cl.araucana.wslme.integration.dao;

import java.util.Date;
import java.util.List;

import cl.araucana.wslme.common.exception.WSLMEException;

public interface EmpleadorDao {
	public List getEmpleadoresAS400(Integer rut, Date fecIni, Date fecFin) throws WSLMEException, Exception;
	
	public List getEmpleadoresSQLServer(Integer rut, Date fecIni, Date fecFin) throws WSLMEException, Exception;
	
	public List getEmpleadoresCRM(Integer rut, String endpoint, String username, String password) throws WSLMEException, Exception;
}
