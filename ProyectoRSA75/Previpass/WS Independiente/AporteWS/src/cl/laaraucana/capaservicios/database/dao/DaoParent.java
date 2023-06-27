package cl.laaraucana.capaservicios.database.dao;

import cl.laaraucana.capaservicios.database.ibatis.config.MyClassSqlConfig;
import com.ibatis.sqlmap.client.SqlMapClient;

public class DaoParent {
	
	/**
	 * Obtiene la conexi�n al Datasource, lanza excepci�n si la conexi�n no est� activa
	 * @return
	 * @throws Exception
	 */
	public SqlMapClient getConn() throws Exception{ 
		SqlMapClient sqlMap = null;
	
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Fall� la conexi�n al Datasource: " + e.getMessage());
		}
		return sqlMap;
	}
}
