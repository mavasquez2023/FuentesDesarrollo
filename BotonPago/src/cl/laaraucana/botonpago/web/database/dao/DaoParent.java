package cl.laaraucana.botonpago.web.database.dao;

import org.apache.log4j.Logger;

import cl.laaraucana.botonpago.web.database.ibatis.config.MyClassSqlConfig;

import com.ibatis.sqlmap.client.SqlMapClient;

public class DaoParent {

	/**
	 * Obtiene la conexión al Datasource, lanza excepción si la conexión no está
	 * activa
	 * 
	 * @return
	 * @throws Exception
	 */
	private static SqlMapClient sqlMap = null;
	private static Logger logger = Logger.getLogger(DaoParent.class);

	public static SqlMapClient getConn() throws Exception {
		if (sqlMap == null) {
			logger.debug("Se obtiene nueva SqlMapInstance");
			try {
				sqlMap = MyClassSqlConfig.getSqlMapInstance();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Falló la conexión al Datasource: " + e.getMessage());
			}
		}
		return sqlMap;
	}
}
