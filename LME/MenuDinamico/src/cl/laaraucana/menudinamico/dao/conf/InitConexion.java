package cl.laaraucana.menudinamico.dao.conf;

import java.io.IOException;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class InitConexion {
	
	private static InitConexion conn;
	private SqlMapClient sqlMapLocal = null;

	//	private Logger log = Logger.getLogger(this.getClass());

	private InitConexion() throws IOException {
		String resource = "cl/laaraucana/menudinamico/dao/conf/IbatisConfig.xml";
		sqlMapLocal = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader(resource));
	}

	public static SqlMapClient getConexion() throws IOException {
		if (conn == null) {
			conn = new InitConexion();
		}
		return conn.sqlMapLocal;
	}
}