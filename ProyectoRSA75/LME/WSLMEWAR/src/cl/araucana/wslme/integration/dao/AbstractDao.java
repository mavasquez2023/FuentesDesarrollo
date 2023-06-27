package cl.araucana.wslme.integration.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import cl.araucana.wslme.common.exception.WSLMEException;
import cl.araucana.wslme.common.util.ConfigUtil;

public class AbstractDao {

	private static final String KEY_DATA_SOURCE_NAME = "araucana.wslme.conexion.datasourcename";
	private static final String KEY_DATA_SOURCE_SQLSERVER_NAME = "sqlserver.wslme.conexion.datasourcename";
	private Connection connection=null;
	private Connection connection2=null;
	private Logger log = Logger.getLogger(AbstractDao.class);
	
	public void openConnection() throws WSLMEException{
    	if(connection != null){
    		connection = null;
    	}
		try {
        	String dataSourceName = ConfigUtil.getValorRecursosDeAplicacion(KEY_DATA_SOURCE_NAME);
        	log.info("Datasource AS400:" + dataSourceName);
        	if(dataSourceName == null){
        		log.error("Codigo 0016: No fue posible cargar obtener el nombre del JNDI");
        		throw new WSLMEException("0016","Error, No fue posible cargar obtener el nombre del JNDI.");
        	}
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource)ic.lookup(dataSourceName);
            connection = ds.getConnection();
        }catch(SQLException ex){
        	log.error("Codigo 0017: No fue posible abrir la conexion a la base de datos");
    		throw new WSLMEException("0017","Error, No fue posible abrir la conexion a la base de datos.");
        }catch(NamingException ex){
        	log.error("Codigo 0018: No se encontro el JNDI especificado");
    		throw new WSLMEException("0018","Error, No se encontro el JNDI especificado.");
        }catch(Exception ex){
        	log.error("Codigo 0019: No fue posible abrir la conexion a la base de datos");
    		throw new WSLMEException("0019","Error, No fue posible abrir la conexion a la base de datos.");
        }finally{}
    	
    }
	
	public void openConnectionSQLServer() throws WSLMEException{
    	if(connection2 != null){
    		connection2 = null;
    	}
		try {
        	String dataSourceName = ConfigUtil.getValorRecursosDeAplicacion(KEY_DATA_SOURCE_SQLSERVER_NAME);
        	if(dataSourceName == null){
        		log.error("Codigo 0016: No fue posible cargar obtener el nombre del JNDI:" + KEY_DATA_SOURCE_SQLSERVER_NAME);
        		throw new WSLMEException("0016","Error, No fue posible cargar obtener el nombre del JNDI:" + KEY_DATA_SOURCE_SQLSERVER_NAME);
        	}
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource)ic.lookup(dataSourceName);
            connection2 = ds.getConnection();
        }catch(SQLException ex){
        	log.error("Codigo 0017: No fue posible abrir la conexion a la base de datos");
    		throw new WSLMEException("0017","Error, No fue posible abrir la conexion a la base de datos.");
        }catch(NamingException ex){
        	log.error("Codigo 0018: No se encontro el JNDI especificado");
    		throw new WSLMEException("0018","Error, No se encontro el JNDI especificado.");
        }catch(Exception ex){
        	log.error("Codigo 0019: No fue posible abrir la conexion a la base de datos");
    		throw new WSLMEException("0019","Error, No fue posible abrir la conexion a la base de datos.");
        }finally{}
    	
    }
	
	public void closeConnection() {
        try {
        	if(connection!=null){
        		connection.close();
        	}
        	if(connection2!=null){
        		connection2.close();
        	}
        } catch (Exception ex) {
        	log.error("Codigo 0020: No fue posible cerrar la conexion a la base de datos");
        }
    }
	
	public Connection getConnection() {
		return connection;
	}
	
	public Connection getConnection2() {
		return connection2;
	}
	
//	private Properties getProperties() {
//		Properties cnf = new Properties();
//		try {
//			cnf.load(new FileInputStream(new File(PATH_FILE + FILE_NAME)));
//		} catch (FileNotFoundException e) {
//			System.out.println("Error: Al cargar el archivo de propiedades... " + e.getLocalizedMessage());
//			e.printStackTrace(System.out);
//		} catch (IOException e) {
//			System.out.println("Error: Al cargar el archivo de propiedades... " + e.getLocalizedMessage());
//			e.printStackTrace(System.out);
//		}
//		return cnf;
//	}
}
