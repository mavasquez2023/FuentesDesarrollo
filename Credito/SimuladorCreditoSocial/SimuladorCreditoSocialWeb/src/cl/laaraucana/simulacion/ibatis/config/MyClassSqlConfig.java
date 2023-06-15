package cl.laaraucana.simulacion.ibatis.config;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class MyClassSqlConfig {
	private static final SqlMapClient sqlMap;
	static {
		try {
			String resource = "cl/laaraucana/simulacion/ibatis/config/IbatisConfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing MyAppSqlConfig class. Cause: " + e);
		}
	}

	public static SqlMapClient getSqlMapInstance() {
		return sqlMap;
	}
	
	
	private static final SqlMapClient sqlMapSql;
	static {
		try {
			String resource = "cl/laaraucana/simulacion/ibatis/config/IbatisConfigSQL.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapSql = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error initializing MyAppSqlConfig class. Cause: " + e);
		}
	}

	public static SqlMapClient getSqlMapInstanceSql() {
		return sqlMapSql;
	}
}