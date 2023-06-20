package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.HashMap;

public interface RechazoDao {

	public HashMap<String, String> consultaRechazoBCI_PNOL(String codigoRechazo) throws Exception;
	
	public HashMap<String, String> consultaRechazoBCI_24HRS(String codigoRechazo) throws Exception;
	
	public HashMap<String, String> consultaRechazoBES(String codigoRechazo) throws Exception;
	
	
}
