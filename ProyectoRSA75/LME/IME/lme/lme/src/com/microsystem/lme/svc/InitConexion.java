package com.microsystem.lme.svc;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.microsystem.lme.util.Constants;

public class InitConexion {

	private static InitConexion conn;
	private SqlMapClient sqlMapLocal = null;
	private Reader reader = null;

	//	private Logger log = Logger.getLogger(this.getClass());

	private InitConexion() throws IOException {
		reader = Resources.getResourceAsReader(Constants.RESOURCE_MAP_CONFIG);
		sqlMapLocal = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			sqlMapLocal.getDataSource().getConnection().setTransactionIsolation(Connection.TRANSACTION_NONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlMapClient getConexion() throws IOException {
		if (conn == null) {
			conn = new InitConexion();
		}
		return conn.sqlMapLocal;
	}
	
	public static void closeConexion()  {
		if(conn != null){
			try {
				conn.sqlMapLocal.getDataSource().getConnection().close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			conn.sqlMapLocal = null;
			
			try {
				if(conn.reader != null){
					conn.reader.close();
					conn.reader = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			conn = null;
		}
		SvcFactory.setSvcFactory(null);
	}

}
