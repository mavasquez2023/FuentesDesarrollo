package com.microsystem.lme.svc;

import java.io.IOException;
import java.io.Reader;
//import java.sql.ResultSet;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.microsystem.lme.util.Constants;

public class InitConexion_LME {

	private static InitConexion_LME conn;
	private SqlMapClient sqlMapLocal = null;
	private Reader reader = null;

	//	private Logger log = Logger.getLogger(this.getClass());

	private InitConexion_LME() throws IOException {
		reader = Resources.getResourceAsReader(Constants.RESOURCE_MAP_CONFIG_LME);
		sqlMapLocal = SqlMapClientBuilder.buildSqlMapClient(reader);
/*		try {
			//sqlMapLocal.getDataSource().getConnection().setTransactionIsolation(Connection.TRANSACTION_NONE);
			conn.sqlMapLocal.getDataSource().getConnection().setHoldability(ResultSet.CLOSE_CURSORS_LMET_COMMIT);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	public static SqlMapClient getConexion() throws IOException {
		if (conn == null) {
			conn = new InitConexion_LME();
		}
		return conn.sqlMapLocal;
	}
	
	public static void closeConexion_LME()  {
		if(conn != null){
			
			try {
				conn.sqlMapLocal.getDataSource().getConnection().close();
				conn.sqlMapLocal = null;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		SvcFactory_LME.setSvcFactory_LME(null);//Se debería eliminar si fuera no estatico
		conn = null;
/*		if(conn != null){
			
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
		}*/
		//System.gc();
		//EndPointUtil.setEndPointsDbMap(null);
		//EndPointUtil.setEndPointsCompletoMap(null);
		//EndPointUtil.setInstancia(null);
	}

}
