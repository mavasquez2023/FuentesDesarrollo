package cse.database.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import cse.database.dao.exception.DAOException;
import cse.model.util.UID;
import cse.system.helper.PropertyLoader;

public class JdbcUtil {
	
	private static Logger logger = Logger.getLogger(JdbcUtil.class.getName());

	public static final String CONNECTION_MODE_DIRECT = "DIRECT";
	public static final String CONNECTION_MODE_JNDI = "JNDI";
	
	private static String DB_USER;
	private static String DB_PASSWORD;
	private static String DB_NAME;
	private static int DB_PORT;
	private static String DB_SERVER;
	private static String CONNECTION_MODE;
	private static String JNDI_RESOURCE_NAME;
	
	static {
		loadProperties();
	}
	
	protected static void loadProperties() {
		Properties prop = new Properties();
		prop = PropertyLoader.loadProperties("datasource.properties");
		CONNECTION_MODE = prop.getProperty("CONNECTION_MODE");
		JNDI_RESOURCE_NAME =  prop.getProperty("JNDI_RESOURCE_NAME");			
		// get the property value and print it out	
		DB_USER = prop.getProperty("DB_USER");
		DB_PASSWORD = prop.getProperty("DB_PASSWORD");
		DB_SERVER = prop.getProperty("DB_SERVER");
		DB_PORT = Integer.parseInt(prop.getProperty("DB_PORT"));
		DB_NAME = prop.getProperty("DB_NAME");			
	}
	
	public static Connection getNonXADBConnection() throws DAOException {
		if(CONNECTION_MODE.equalsIgnoreCase(CONNECTION_MODE_DIRECT)){
			return getNonXADBDirectConnection();
		} else if (CONNECTION_MODE.equalsIgnoreCase(CONNECTION_MODE_JNDI)){
			return getNonXAJndiDBConnection();
		} else {
			logger.warning("Problema de configuracion en datasource.properties : Asigne un valor valido {DIRECT | JNDI } para CONNECTION_MODE");
			throw new DAOException("Problema de configuracion en datasource.properties : Asigne un valor valido {DIRECT | JNDI } para CONNECTION_MODE");
		}		
	}

	private static Connection getNonXADBDirectConnection() throws DAOException {
		logger.entering(JdbcUtil.class.getName(), "getNonXADBConnection");		
		Connection conn = null;
		SQLServerDataSource ds = null;
		try {
			// Establish the connection.
			ds = new SQLServerDataSource();
			ds.setUser(DB_USER);
			ds.setPassword(DB_PASSWORD);
			ds.setServerName(DB_SERVER);
			ds.setPortNumber(DB_PORT);
			ds.setDatabaseName(DB_NAME);
			conn = ds.getConnection();
		} catch (SQLException ex) {
			logger.log(Level.SEVERE, "Problemas para conseguir la conexion a la base de datos " + ex.getMessage(), ex.getCause());
			throw new DAOException(ex);
		}
		logger.exiting(JdbcUtil.class.getName(), "getNonXADBConnection", conn);
		return conn;
	}

	private static Connection getNonXAJndiDBConnection() throws DAOException {
		logger.entering(JdbcUtil.class.getName(), "getNonXAJndiDBConnection");
		Connection conn = null;
		DataSource ds = null;
		try {
			Object obj = lookupJavaContext(JNDI_RESOURCE_NAME);
			ds = (DataSource) narrow(obj, DataSource.class);
			conn = ds.getConnection();
		} catch (SQLException ex) {
			logger.log(Level.SEVERE, "Problemas para conseguir la conexion al recurso JNDI " + ex.getMessage(), ex.getCause());
			throw new DAOException(ex);
		}
		logger.exiting(JdbcUtil.class.getName(), "getNonXAJndiDBConnection", conn);
		return conn;
	}

	public static String getUniqueSolicitudId(Connection conn) { 
		logger.entering(JdbcUtil.class.getName(), "getUniqueSolicitudId", conn);
		String uid = UID.getUID();
		StringBuffer sb = new StringBuffer();
		sb.append(uid.substring(0, 8));
		sb.append("-");
		sb.append(uid.substring(8, 12));
		sb.append("-");
		sb.append(uid.substring(12, 16));
		sb.append("-");
		sb.append(uid.substring(16, 20));
		sb.append("-");
		sb.append(uid.substring(20));
		String result = sb.toString(); 
		logger.exiting(JdbcUtil.class.getName(), "getUniqueSolicitudId", result);
		return result ;

	}

	public static void closeJDBCConnection(final Connection conn) {
		logger.entering(JdbcUtil.class.getName(), "closeJDBCConnection", conn);
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
				logger.log(Level.SEVERE, "Problemas closing JDBCConnection" + ex.getMessage(), ex);
			}
		}
		logger.exiting(JdbcUtil.class.getName(), "closeJDBCConnection");
	}

	public static void closeStatement(final Statement stmt) {
		logger.entering(JdbcUtil.class.getName(), "closeStatement", stmt);
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				logger.log(Level.SEVERE, "Problemas closing Statement + " + ex.getMessage(), ex);
			}
		}
		logger.exiting(JdbcUtil.class.getName(), "closeStatement");
	}

	public static void closeResultSet(final ResultSet rs) {
		logger.entering(JdbcUtil.class.getName(), "closeResultSet", rs);
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				logger.log(Level.SEVERE, "Problemas closing ResultSet " + ex.getMessage(), ex);
			}
		}
		logger.exiting(JdbcUtil.class.getName(), "closeResultSet");
	}

	static private Object lookupJavaContext(final String name) throws DAOException {
		logger.entering(JdbcUtil.class.getName(), "lookupJavaContext", name);
		Context ctx = getInitialContext();
		Object result = null;
		try {
			Context myJavaContext = (Context) ctx.lookup("java:comp/env");
			result = myJavaContext.lookup(name);
		} catch (NamingException ex) {
			logger.log(Level.SEVERE, "Problemas lookup java context " + ex.getMessage(), ex);
			throw new DAOException(ex);
		}
		logger.exiting(JdbcUtil.class.getName(), "lookupJavaContext", result);
		return result;
	}

	static private Context getInitialContext() throws DAOException {
		Context ctx = null;

		try {
			ctx = new InitialContext();
		} catch (NamingException ex) {
			throw new DAOException(ex);
		}
		return ctx;
	}

	static private Object narrow(final Object obj, final Class clazz) {
		return javax.rmi.PortableRemoteObject.narrow(obj, clazz);
	}

}
