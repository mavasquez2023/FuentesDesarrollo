/*
 * @(#) MyClassSqlConfig.java 1.0 04/06/2013
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 * 
 * 
 */
package cl.laaraucana.botonpago.web.database.ibatis.config;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class MyClassSqlConfig {

	private static SqlMapClient sqlMap;
	private static MyClassSqlConfig myClassSqlConfig;

	public MyClassSqlConfig() {
		try {
			String resource = "cl/laaraucana/botonpago/web/database/ibatis/config/IbatisConfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error initializing MyAppSqlConfig class. Cause: " + e);
		}
	}

	public static SqlMapClient getSqlMapInstance() {
		if (myClassSqlConfig == null) {
			myClassSqlConfig = new MyClassSqlConfig();
		}
		return sqlMap;
	}

}