/*
 * Creado el 19-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.migra.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.Properties;
import java.util.logging.Logger;
import cl.araucana.core.util.logging.LogManager;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class SQLServerDAO {
	private Context ctx;
	private DataSource ds;
	private Connection connection;
	private static Logger logger = LogManager.getLogger();
	protected Properties properties;
	//private Connection connection;
	public SQLServerDAO(){

		try {

			//Se carga web.xml para cargar parametros de conexión.
			ctx = new InitialContext();
			//Se rescata DataSource de conexión a BD
			logger.finer("Conectando a Datasource--> java:comp/env/jdbc/lmeaux");
			this.ds = (DataSource)ctx.lookup("jdbc/lmeaux");
			//this.ds = (DataSource)ctx.lookup("jdbc/axis");
			this.connection= ConectaDB(ds);

		} catch (NamingException e) {
			logger.severe("NamingException, mensaje:" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.severe("SQLException, mensaje:" + e.getMessage());
			e.printStackTrace();
		}

	}

	
	private Connection ConectaDB(DataSource ds) throws SQLException{
		//Se establece la conexión
		connection= ds.getConnection();
		return connection;
}


	public Connection getConnection() {
		return connection;
	}


	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void setAutoCommit(boolean flag) throws SQLException{
		this.connection.setAutoCommit(flag);
	}

	public void commit() throws SQLException{
		this.connection.commit();
	}

	public void rollback() throws SQLException{
		this.connection.rollback();
	}

	public void closeConnectionDAO(){
		try {
			logger.finer("Cerrando Conexión SQLServer");
			if(connection != null){
				this.connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
