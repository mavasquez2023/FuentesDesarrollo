/*
 * Creado el 19-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cheque.dao;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import cl.recursos.ConectaDB2;
import org.apache.log4j.Logger;
import java.util.Properties;



/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TesoDAO {
	private Context ctx;
	private DataSource ds;
	private ConectaDB2 db2;
	private static Logger log = Logger.getLogger(TesoDAO.class);
	protected Properties properties;
	
	public TesoDAO(){
		
		try {
			//loadProperties("/etc/config.properties");
			//String nameJDBC= properties.getProperty("jndiTesoreria");
			//Se carga web.xml para cargar parametros de conexión.
			ctx = new InitialContext();			
			//Se rescata DataSource de conexión a BD
			log.debug("Conectando a Datasource");
			this.ds = (DataSource)ctx.lookup("java:comp/env/jdbc/jnditeso");
			this.db2= new ConectaDB2(ds);
			
		} catch (NamingException e) {
			log.error("NamingException, mensaje:" + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			log.error("SQLException, mensaje:" + e.getMessage());
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
			log.debug("Cerrando Conexión DB2");
			this.db2.desconectaDB2();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
