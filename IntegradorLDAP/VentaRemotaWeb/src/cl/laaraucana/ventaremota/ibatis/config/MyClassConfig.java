package cl.laaraucana.ventaremota.ibatis.config;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class MyClassConfig {

	private static MyClassConfig instance = null;
	private static final SqlMapClient sqlMap;
	static {
		try {
			String resource = "cl/laaraucana/ventaremota/ibatis/config/IbatisConfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (Exception e) {
			System.out.println("Error en myclasssqlconfig :"+e.getMessage());
			throw new RuntimeException(
					"Error initializing MyAppSqlConfig class. Cause: " + e);
		}
	}

	public static SqlMapClient getSqlMapInstanceSql() {
		if(instance == null) 
 			instance = new MyClassConfig(); 
		return sqlMap;
	}
}