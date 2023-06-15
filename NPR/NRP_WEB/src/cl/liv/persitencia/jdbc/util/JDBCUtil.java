/**
 * 
 */
package cl.liv.persitencia.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.liv.comun.utiles.MiHashMap;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.logs.UtilLogErrores;

/**
 * @author desarrollo
 *
 */
public class JDBCUtil {

	

    static Connection connection = null;
	
	public static String getQueryCompleta(String key, MiHashMap parametros){
		
		String query = PropertiesUtil.querysJDBC.getString(key);
		
		
		Iterator it = parametros.entrySet().iterator();
		while(it.hasNext()){
			String keyHash = ((Map.Entry) it.next()).getKey().toString();
			String valueHash = "";
			if(parametros.get( keyHash ) != null ){
				valueHash = parametros.get( keyHash ).toString().replaceAll("'", " ");
			}
			else{
			}
			query = query.replaceAll("#"+keyHash+"+#", "'"+valueHash+"'");
		}
		
		return query;
	}
	
	public static Connection getConnection(){
		
		if(connection == null  ){
			initializaConexion();
		}
		
		return connection;
	}
	
	private static void initializaConexion(){
		
		
		boolean usaJNDI = Boolean.parseBoolean( PropertiesUtil.connectorJDBC.getString("config.jdbc.use.jndi") );
		
		if(usaJNDI){
		
			String JNDI = PropertiesUtil.connectorJDBC.getString("config.jdbc.jndi");

			InitialContext ic;
			try {
				ic = new InitialContext();
			
				DataSource ds = (DataSource)ic.lookup(JNDI);
				connection = ds.getConnection();

			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else{
			 try {
	
		            Class.forName(PropertiesUtil.connectorJDBC.getString("config.jdbc.conn.driver"));
	
		        } catch (ClassNotFoundException e) {
		        	UtilLogErrores.error(e);
		            e.printStackTrace();
		            return;
	
		        }
	
	
	
		        try {
	
		            connection = DriverManager.getConnection(PropertiesUtil.connectorJDBC.getString("config.jdbc.conn.url")
		                    , PropertiesUtil.connectorJDBC.getString("config.jdbc.conn.user"), PropertiesUtil.connectorJDBC.getString("config.jdbc.conn.password"));
	
		        } catch (SQLException e) {
		        	UtilLogErrores.error(e);
		        	UtilLogWorkflow.debug("Connection Failed! Check output console");
		            e.printStackTrace();
		            return;
	
		        }
	
		        if (connection != null) {
		        	UtilLogWorkflow.debug("You made it, take control your database now!");
		        } else {
		        	UtilLogWorkflow.debug("Failed to make connection!");
		        }
		}
		
	}

}
