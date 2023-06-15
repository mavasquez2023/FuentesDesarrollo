package cl.araucana.ctasfam.batch.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;
import cl.araucana.ctasfam.batch.thread.AdministradorHebrasThread;

public abstract class AbstractDb2Dao {
	final static Logger logger = Logger.getLogger(AbstractDb2Dao.class);
	
	private Connection connection;
	private Integer indexConnection;
	
	public AbstractDb2Dao(){}
	
	public AbstractDb2Dao(Integer indexConnection){
		this.indexConnection = indexConnection;
	}
	
	public Connection getSinglesConnection() throws TechnicalException{
		try {
			if(connection == null || connection.isClosed()){
				BasicDataSource dataSource = new BasicDataSource();
				connection = dataSource.getDb2Connection();
			}
		} catch (SQLException e) {
			throw new TechnicalException("0701","Ocurrio un error al verificar la conexion con la base de datos", e);
		}
		return connection;
	}
	
	public void closeSinglesConnection() {
        try {
        	if(connection != null && !connection.isClosed())
        		connection.close();
        } catch (SQLException ex) {
        	logger.error("Ocurrio un error al cerrar la conexion con la base de datos");
        }
    }
	
	public CallableStatement getCallableStatement(String sqlCallSP) throws TechnicalException{
		if(indexConnection != null){
			BasicDb2Connection [] poolConn = AdministradorHebrasThread.getPoolConexiones();
			if(indexConnection != null && indexConnection < poolConn.length 
					&& poolConn[indexConnection] != null){
				Connection conn = poolConn[indexConnection].getConnection();
				try {
					return conn.prepareCall(sqlCallSP);
				} catch (SQLException ex) {
					throw new TechnicalException("0702","Ocurrio un error al preparar la llamada a SP con conexion index " +  indexConnection, ex);
		        }
			}else{
				throw new TechnicalException("0703","Ocurrio un error al utilizar la conexion index " + indexConnection);
			}
		}else{
			CallableStatement callSP = null;
			try {
				if(connection != null && !connection.isClosed()){
					callSP = connection.prepareCall(sqlCallSP);
				}else{
					getSinglesConnection(); 
					callSP = connection.prepareCall(sqlCallSP);
				}
			} catch (SQLException ex) {
				throw new TechnicalException("0704","Ocurrio un error al preparar la llamada a SP ", ex);
	        }
			return callSP;
		}
	}
	
	public PreparedStatement getPreparedStatement(String querySql) throws TechnicalException{
		if(indexConnection != null){
			BasicDb2Connection [] poolConn = AdministradorHebrasThread.getPoolConexiones();
			if(indexConnection != null && indexConnection < poolConn.length){
				Connection conn = poolConn[indexConnection].getConnection();
				try {
					return conn.prepareStatement(querySql);
				} catch (SQLException ex) {
					throw new TechnicalException("0705","Ocurrio un error al preparar la ejecucion de la query con conexion index " +  indexConnection, ex);
		        }
			}else{
				throw new TechnicalException("0706","Ocurrio un error al utilizar la conexion index " + indexConnection);
			}
		}else{
			PreparedStatement callSql = null;
			try {
				if(connection != null && !connection.isClosed()){
					callSql = connection.prepareStatement(querySql);
				}else{
					getSinglesConnection(); 
					callSql = connection.prepareStatement(querySql);
				}
			} catch (SQLException ex) {
				throw new TechnicalException("0707","Ocurrio un error al preparar la ejecucion de query", ex);
	        }
			return callSql;
		}
	}
	
//	public PreparedStatement getPreparedStatementAutoGeneratedKey(String querySql) throws TechnicalException{
//		if(indexConnection != null){
//			BasicDb2Connection [] poolConn = AdministradorHebrasThread.getPoolConexiones();
//			if(indexConnection != null && indexConnection < poolConn.length){
//				Connection conn = poolConn[indexConnection].getConnection();
//				try {
//					int [] a = {1};
//					return conn.prepareStatement(querySql, a);
//				} catch (SQLException ex) {
//					throw new TechnicalException("", "");
//		        }
//			}else{
//				throw new TechnicalException("", "");
//			}
//		}else{
//			PreparedStatement callSql = null;
//			try {
//				if(connection != null && !connection.isClosed()){
//					callSql = connection.prepareStatement(querySql, PreparedStatement.RETURN_GENERATED_KEYS);
//				}else{
//					getSinglesConnection(); 
//					callSql = connection.prepareStatement(querySql, PreparedStatement.RETURN_GENERATED_KEYS);
//				}
//			} catch (SQLException ex) {
//				ex.printStackTrace();
//				throw new TechnicalException("", "No fue posible preparar la llamada al Stored Procedure...");
//	        }
//			return callSql;
//		}
//	}
}
