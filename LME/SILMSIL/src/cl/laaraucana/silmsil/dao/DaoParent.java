package cl.laaraucana.silmsil.dao;

import org.apache.log4j.Logger;

import cl.laaraucana.silmsil.dao.conf.MyClassSqlConfig;

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
				throw new Exception("Falló la conexión al Datasource: " + e.getMessage());
			}
		}
		return sqlMap;
	}
}
