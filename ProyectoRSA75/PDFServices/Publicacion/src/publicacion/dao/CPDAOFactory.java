/*
 * Creado el 19-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
package publicacion.dao;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import cl.recursos.ConectaDB2;

import java.util.logging.Logger;
import cl.araucana.core.util.logging.LogManager;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
public class CPDAOFactory {
	private Context ctx;
	private DataSource ds;
	private ConectaDB2 db2;
	private static Logger logger = LogManager.getLogger();
	
	public CPDAOFactory(){
		
		try {
			//Se carga web.xml para cargar parametros de conexi�n.
			ctx = new InitialContext();			
			//Se rescata DataSource de conexi�n a BD
			
			logger.finer("Conectando a Datasource--> java:comp/env/jdbc/cpemp");
			//cambiar AQui PRD
			//this.ds = (DataSource)ctx.lookup("jdbc/cp");
			//cambiar AQui
			this.ds = (DataSource)ctx.lookup("java:comp/env/jdbc/cp"); //Cambiar aqu�
			this.db2= new ConectaDB2(ds);
			ctx.close();
			
		} catch (NamingException e) {
			logger.severe("NamingException, mensaje:" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			logger.severe("SQLException, mensaje:" + e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void setAutoCommit(boolean flag) throws SQLException{
		this.db2.getConnection().setAutoCommit(flag);
	}
	
	public void commit() throws SQLException{
		this.db2.getConnection().commit();
	}
	
	public void rollback() throws SQLException{
		this.db2.getConnection().rollback();
	}
	
	public ConectaDB2 getConnection(){
		return this.db2;
	}
	
	public void closeConnectionDAO(){
		try {
			logger.finer("Cerrando Conexi�n DB2 CPE");
			this.db2.desconectaDB2();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
