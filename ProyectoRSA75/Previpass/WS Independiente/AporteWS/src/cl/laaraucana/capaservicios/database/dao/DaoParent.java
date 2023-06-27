package cl.laaraucana.capaservicios.database.dao;

import cl.laaraucana.capaservicios.database.ibatis.config.MyClassSqlConfig;
import com.ibatis.sqlmap.client.SqlMapClient;

public class DaoParent {
	
	/**
	 * Obtiene la conexión al Datasource, lanza excepción si la conexión no está activa
	 * @return
	 * @throws Exception
	 */
	public SqlMapClient getConn() throws Exception{ 
		SqlMapClient sqlMap = null;
	
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Falló la conexión al Datasource: " + e.getMessage());
		}
		return sqlMap;
	}
}
