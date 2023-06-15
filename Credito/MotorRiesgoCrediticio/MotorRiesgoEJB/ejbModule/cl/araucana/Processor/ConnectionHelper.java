package cl.araucana.Processor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cse.database.dao.exception.DAOException;
import cse.database.dao.jdbc.JdbcUtil;

public class ConnectionHelper {

	private static Logger logger = Logger.getLogger(ConnectionHelper.class.getName());

	private Connection conn;

	public Connection getConnection() throws DAOException {

		logger.entering(ConnectionHelper.class.getName(), "getConnection");
		try {
			if (conn == null || conn.isClosed()) {
				initConnection();
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "No se pudo comprobar si la conexion a AS400 esta cerrada", e);
		}
		logger.exiting(JdbcUtil.class.getName(), "getConnection", conn);
		return conn;
	}

	public Connection getConnection2() throws DAOException {
		return getJndiDBConnection();
	}

	private void initConnection() {
		String ip = System.getProperty("cl.araucana.as400.server");
		String biblioteca = System.getProperty("cl.araucana.as400.biblioteca2");

		Properties p;
		p = new Properties();
		p.put("user", System.getProperty("cl.araucana.as400.user"));
		p.put("password", System.getProperty("cl.araucana.as400.password"));
		p.put("naming", "sql");

		try {
			DriverManager.registerDriver(new com.ibm.as400.access.AS400JDBCDriver());
			this.conn = DriverManager.getConnection("jdbc:as400://" + ip + "/" + biblioteca, p);
		} catch (SQLException e) {
			logger.log(Level.SEVERE,
					"Conexion a AS400 no pudo ser creada para entrgar respuesta a AS/400", e);
		}
	}

	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Conexion a AS400 no pudo ser cerrada", e);
		}
	}

	private static Connection getJndiDBConnection() throws DAOException {
		logger.entering(JdbcUtil.class.getName(), "getJndiDBConnection");
		Connection conn = null;
		DataSource ds = null;
		try {
			Object obj = lookupJavaContext("AS400Datasource");
			ds = (DataSource) narrow(obj, DataSource.class);
			conn = ds.getConnection();
		} catch (SQLException ex) {
			logger.log(Level.SEVERE,
					"Problemas para conseguir la conexion al recurso JNDI " + ex.getMessage(),
					ex.getCause());
			throw new DAOException(ex);
		}
		logger.exiting(JdbcUtil.class.getName(), "getNonXAJndiDBConnection", conn);
		return conn;
	}

	private static Object lookupJavaContext(final String name) throws DAOException {
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

	private static Context getInitialContext() throws DAOException {
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
