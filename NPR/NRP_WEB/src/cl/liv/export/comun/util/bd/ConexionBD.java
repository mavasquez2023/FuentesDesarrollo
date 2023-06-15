package cl.liv.export.comun.util.bd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cl.liv.export.comun.util.Mensajes;
import cl.liv.export.comun.util.PropertiesComunUtil;
import cl.liv.export.comun.util.SessionUtil;
import cl.liv.export.comun.util.file.PropertiesFile;

public class ConexionBD {
	
	static Connection conn = null;
	static Date ultimoAcceso = new Date();
	static int tiempoInactividad = 10000;
	static boolean esperandoPorCerrar = false;
	public static Connection getConexion(String datasource){
		try {
			   validarLimpiezaConexion(false);
			   //conn = null;
			   if(conn == null){
				   Mensajes.info("[getConexion] creando conexion para "+ datasource);
				   conn = getConexionFromProperties(datasource);
				   Mensajes.info("[getConexion] conexion creada para "+ datasource);
				   ultimoAcceso.setTime( new Date().getTime() );
			   }
			   else{
				   
			   }
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
	
	public static synchronized void validarLimpiezaConexion(boolean cerrando){
		if(conn != null){
			if(cerrando ){
				if(  new Date().getTime() - ultimoAcceso.getTime() > tiempoInactividad ){
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					conn = null;
				}
			}
			else{
				ultimoAcceso = new Date();
			}
		}
	}

	
	public static void cerrar(){
		if(esperandoPorCerrar) return ;
		
		new Thread(new Runnable() {
			
			public void run() {
				try {
					esperandoPorCerrar = true;
					Thread.sleep(tiempoInactividad);
					validarLimpiezaConexion(true);
					esperandoPorCerrar = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	

	
}
