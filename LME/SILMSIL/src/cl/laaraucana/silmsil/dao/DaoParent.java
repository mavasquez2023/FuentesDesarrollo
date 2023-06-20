package cl.laaraucana.silmsil.dao;

import org.apache.log4j.Logger;

import cl.laaraucana.silmsil.dao.conf.MyClassSqlConfig;

import com.ibatis.sqlmap.client.SqlMapClient;

public class DaoParent {
	/**
	 * Obtiene la conexi�n al Datasource, lanza excepci�n si la conexi�n no est�
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
				throw new Exception("Fall� la conexi�n al Datasource: " + e.getMessage());
			}
		}
		return sqlMap;
	}
}
