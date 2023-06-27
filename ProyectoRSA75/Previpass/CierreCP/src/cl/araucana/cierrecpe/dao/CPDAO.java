/*
 * Creado el 19-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.dao;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import cl.recursos.ConectaDB2;

import java.util.Properties;
import java.util.logging.Logger;

import cl.araucana.core.util.PropertiesLoader;
import cl.araucana.core.util.logging.LogManager;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class CPDAO {
	private Context ctx;
	private String biblioteca;
	private DataSource ds;
	private ConectaDB2 db2;
	private static Logger logger = LogManager.getLogger();
	protected Properties properties;
	public CPDAO(){
		
		try {
			loadProperties("/etc/dao.properties");
			String nameJDBC= properties.getProperty("jndiCP");
			//Se carga web.xml para cargar parametros de conexión.
			ctx = new InitialContext();			
			//Se rescata DataSource de conexión a BD
			logger.finer("Conectando a Datasource--> java:comp/env/jdbc/cpemp");
			this.ds = (DataSource)ctx.lookup(nameJDBC);
			this.db2= new ConectaDB2(ds);
			
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
			logger.finer("Cerrando Conexión DB2 CPE");
			this.db2.desconectaDB2();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void loadProperties(String fileproperties){
		PropertiesLoader propertiesloader = new PropertiesLoader();
		try
		{
			properties = propertiesloader.load(fileproperties, cl.araucana.cierrecpe.empresas.business.ArchivosImpresion.class);
		}
		catch(Exception eproperties)
		{
			logger.severe("cannot open " + fileproperties + " properties file." + eproperties.getMessage());
			eproperties.printStackTrace();
		}
	}
}
