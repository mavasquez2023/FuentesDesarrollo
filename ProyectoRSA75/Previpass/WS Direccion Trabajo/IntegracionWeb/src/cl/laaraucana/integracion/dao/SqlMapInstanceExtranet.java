package cl.laaraucana.integracion.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapInstanceExtranet {
	private static SqlMapInstanceExtranet instancia = null;
	private static SqlMapClient sqlMap = null;
	private Log log = LogFactory.getLog(SqlMapInstanceExtranet.class);
	public SqlMapInstanceExtranet(){
		String resource = "cl/laaraucana/integracion/config/sqlMap-config-previpass.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (IOException e1) {
			log.fatal("Error al cargar los archivos de configuracion de Ibatis... \n"+e1);
		}
	}
	
	public static SqlMapInstanceExtranet getInstance(){
		if(instancia == null)
			instancia = new SqlMapInstanceExtranet();
		return instancia;
		
	}

	public static SqlMapClient getSqlMap() {
		return sqlMap;
	}
	
}
