package com.microsystem.lme.svc;

import java.io.IOException;
import java.io.Reader;
//import java.sql.ResultSet;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.microsystem.lme.util.Constants;

public class InitConexion_SIL {

	private static InitConexion_SIL conn;
	private SqlMapClient sqlMapLocal = null;
	private Reader reader = null;

	//	private Logger log = Logger.getLogger(this.getClass());

	private InitConexion_SIL() throws IOException {
		reader = Resources.getResourceAsReader(Constants.RESOURCE_MAP_CONFIG_SIL);
		sqlMapLocal = SqlMapClientBuilder.buildSqlMapClient(reader);
/*		try {
			//sqlMapLocal.getDataSource().getConnection().setTransactionIsolation(Connection.TRANSACTION_NONE);
			conn.sqlMapLocal.getDataSource().getConnection().setHoldability(ResultSet.CLOSE_CURSORS_AT_COMMIT);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	public static SqlMapClient getConexion() throws IOException {
		if (conn == null) {
			conn = new InitConexion_SIL();
		}
		return conn.sqlMapLocal;
	}
	
	public static void closeConexion_SIL()  {
		if(conn != null){
			try {
				conn.sqlMapLocal.getDataSource().getConnection().close();
				conn.sqlMapLocal = null;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		SvcFactory_SIL.setSvcFactory_SIL(null);//Se debería eliminar si fuera no estatico
		conn = null;
	/*if(conn != null){
	
			
			conn.sqlMapLocal = null;
			
			try {
				if(conn.reader != null){
					conn.reader.close();
					conn.reader = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			conn = null;
		}*/
		//System.gc();
	}

}
