/*
 * @(#) MyClassSqlConfig.java 1.0 04/06/2013
 *
 * Este c�digo fuente pertenece a la Caja de Compensaci�n de Asignaci�n Familiar
 * La Araucana (C.C.A.F.). Su utilizaci�n y reproducci�n es confidencial y est�
 * restringido a los sistemas de informaci�n propios de la instituci�n.
 * 
 * 
 */
package cl.laaraucana.mediopagosilws.core.database.config;

import java.io.IOException;
import java.io.Reader;

import cl.laaraucana.mediopagosilws.core.database.exception.DaoException;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class MyClassSqlConfig {
	private static SqlMapClient sqlMap = null;

	private static void conectar() throws DaoException {
		String resource = "cl/laaraucana/mediopagosilws/core/database/config/IbatisConfig.xml";
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (IOException e) {
			throw new DaoException("Error al leer archivo de configuraci�n de Ibatis: ", e);
		} catch (Exception e) {
			throw new DaoException("Error al conectarse al datasource: ", e);
		}
	}

	public static SqlMapClient getInstance() throws DaoException {
		if (sqlMap == null) {
			conectar();
		}
		return sqlMap;
	}

}