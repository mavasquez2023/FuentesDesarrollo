package cl.laaraucana.integracion.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapInstance {
	private static SqlMapInstance instancia = null;
	private static SqlMapClient sqlMap = null;
	private Log log = LogFactory.getLog(SqlMapInstance.class);
	public SqlMapInstance(){
		String resource = "cl/laaraucana/integracion/config/sqlMap-config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n"+e1);
		}
	}
	
	public static SqlMapInstance getInstance(){
		if(instancia == null)
			instancia = new SqlMapInstance();
		return instancia;
		
	}

	public static SqlMapClient getSqlMap() {
		return sqlMap;
	}
	
}
