package cl.laaraucana.capaservicios.database.dao;

import java.util.HashMap;
import com.ibatis.sqlmap.client.SqlMapClient;

public class ParametrosDAO extends DaoParent
{	
	/**
	 * Obtiene lista de parametros de la tabla T_PARAMETRO para inicializar las constantes de la aplicacion
	 * @return HashMap
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getParametros() throws Exception {
		SqlMapClient sqlMap = getConn();
		HashMap<String, String> map=new HashMap<String, String>();
		
		try{
			sqlMap.startTransaction();
			map = (HashMap<String, String>) sqlMap.queryForMap("getParametros", null, "key", "value");
			
			//Obtener monto mínimo giro
			String cant = (String) sqlMap.queryForObject("getMontoMinGiro", null);
			map.put("montoMinGiro", cant.trim());
			
			//Nro máximo de cuotas simulación
			String nroMaxCuotas = (String) sqlMap.queryForObject("getNroMaxCuotas", null);
			map.put("nroMaxCuotas", nroMaxCuotas.trim());
		}catch (Exception e){
			e.printStackTrace();
			throw new Exception("Error al obtener lista de parámetros");
		}finally{
			sqlMap.commitTransaction();
		}
		return (map);
	}//end getParametros
	
	
	
}//end class ParametrosDAO
