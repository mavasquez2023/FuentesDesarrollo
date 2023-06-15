package cl.laaraucana.boletaelectronica.services;

import java.util.List;
import cl.laaraucana.boletaelectronica.entities.Parametros;

public interface ParametrosService {
	
	public List<Parametros> getParametros(int estado) throws Exception;
	
	public void deleteParametroById(long id) throws Exception;
	
	public Parametros getParametrosById(long id) throws Exception;
	
	public void updateParametro(Parametros parametro) throws Exception;
	
	public void saveParametro(Parametros parametro) throws Exception;
	
	public Parametros getParamByCode(int code) throws Exception;
	
	public Parametros getLastParam() throws Exception;
	
	public List<Parametros> getAllParam() throws Exception;
	 

}
