/*
* @(#) MyClassSqlConfig.java 1.0 04/06/2013
*
* Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
* La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
* restringido a los sistemas de información propios de la institución.
* 
* 
*/
package cl.laaraucana.capaservicios.database.ibatis.config;

import java.io.Reader;
import java.sql.SQLException;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class MyClassSqlConfig {
	private static SqlMapClient sqlMap = null;
	
	private static void getConn() {
		try {
			String resource = "cl/laaraucana/capaservicios/database/ibatis/config/IbatisConfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (Exception e) {
			System.out.println("Error en myclasssqlconfig :"+e.getMessage());
			throw new RuntimeException(
					"Error initializing MyAppSqlConfig class. Cause: " + e);
		}
	}
	
	/**
	 * Singleton
	 * @return
	 * @throws SQLException 
	 */
	public static SqlMapClient getSqlMapInstance() throws SQLException {
		if(sqlMap == null){
			getConn();
		}
		return sqlMap;
	}
}