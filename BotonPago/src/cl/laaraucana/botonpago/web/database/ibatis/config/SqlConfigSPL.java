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

import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlConfigSPL {
	private Logger logger = Logger.getLogger(this.getClass());
	private static SqlMapClient sqlMap;
	private static SqlConfigSPL myClassSqlConfig;

	public SqlConfigSPL() {
		try {
			String resource = "cl/laaraucana/botonpago/web/database/ibatis/config/IbatisConfigSPL.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error en myclasssqlconfig :" + e.getMessage());
			throw new RuntimeException("Error initializing MyAppSqlConfig class. Cause: " + e);
		}
	}

	public static SqlMapClient getSqlMapInstance() {
		if (myClassSqlConfig == null) {
			myClassSqlConfig = new SqlConfigSPL();
		}
		return sqlMap;
	}
}