package cl.liv.export.comun.util.bd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.liv.export.comun.util.Mensajes;
import cl.liv.export.comun.util.PropertiesComunUtil;
import cl.liv.export.comun.util.SessionUtil;
import cl.liv.export.comun.util.file.PropertiesFile;

public class ConexionBDAux {
	
	static Connection conn = null;
	public Connection getConexion(String datasource){
		UtilLogWorkflow.debug("obteniendo conexion...");
		try {
			  if(conn == null)
				  conn = getConexionFromProperties(datasource);
				 
			   return conn;
			} catch (Exception e) {
			   e.printStackTrace();
			   Mensajes.debug(e.getMessage());
			}
		return conn;
	}
	
	
	private static Connection getConexionFromProperties(String datasource) throws SQLException, ClassNotFoundException, IOException{
		Properties props = null;
		if(SessionUtil.datasources.get(datasource)!= null){
			Mensajes.info("Properties ya cargadas...");
			props = SessionUtil.datasources.get(datasource);
		}
		else{

			String filePath = PropertiesComunUtil.getProperty("export.path.resources")+datasource+".properties";
			Mensajes.info("Cargando Properties...");
			PropertiesFile.loadPropeties(datasource.substring(datasource.lastIndexOf("/")+1), filePath);
			props = SessionUtil.datasources.get(datasource.substring(datasource.lastIndexOf("/")+1));
		}
		
		
		boolean usaJNDI = Boolean.parseBoolean( props.get("config.jdbc.use.jndi").toString() );
		if(usaJNDI){
		
			String JNDI = props.get("config.jdbc.jndi").toString();
			InitialContext ic;
			try {
				ic = new InitialContext();
			
				DataSource ds = (DataSource)ic.lookup(JNDI);
				return ds.getConnection();

			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else{
			Class.forName(props.get("config.jdbc.conn.driver").toString());
			return DriverManager.getConnection(props.get("config.jdbc.conn.url").toString(),props.get("config.jdbc.conn.user").toString(), props.get("config.jdbc.conn.password").toString());
		}
		return null;
	}


	
}
