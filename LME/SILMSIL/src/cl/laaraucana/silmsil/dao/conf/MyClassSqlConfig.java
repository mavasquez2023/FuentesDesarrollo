package cl.laaraucana.silmsil.dao.conf;

import org.apache.log4j.Logger;
import cl.laaraucana.silmsil.util.Constantes;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class MyClassSqlConfig {
	private Logger logger = Logger.getLogger(this.getClass());
	private static MyClassSqlConfig myClassSqlConfig;
	private SqlMapClient sqlMap = null;

	private MyClassSqlConfig() {
		try {
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader(Constantes.getInstancia().RESOURCE_MAP_CONFIG));
		} catch (Exception e) {
			logger.error("Error en " + this.getClass().getSimpleName() + ": " + e.getMessage());
			throw new RuntimeException("Error initializing " + this.getClass().getSimpleName() + " class. Cause: " + e);
		}
	}

	public static SqlMapClient getSqlMapInstance() {
		if (myClassSqlConfig == null) {
			myClassSqlConfig = new MyClassSqlConfig();
		}
		return myClassSqlConfig.sqlMap;
	}

}