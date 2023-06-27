package cl.araucana.wsatento.business.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cl.araucana.wsatento.common.util.ConfigUtil;
import cl.araucana.wsatento.integration.exception.WSAtentoException;

public class AbstractDao {

	private static final String KEY_DATA_SOURCE_NAME = "araucana.wsatento.conexion.datasourcename";
	private Connection connection;
	
	public void openConnection() throws WSAtentoException{
    	if(connection == null){
    		try {
	        	String dataSourceName = ConfigUtil.getValor(KEY_DATA_SOURCE_NAME);
	        	if(dataSourceName == null)
	        		throw new WSAtentoException("0002","Error interno, comuniquese con el administrador.");
	        	
	            InitialContext ic = new InitialContext();
	            DataSource ds = (DataSource)ic.lookup(dataSourceName);
	            connection = ds.getConnection();
	        }catch(SQLException ex){
	        	ex.printStackTrace();
				throw new WSAtentoException("0004","Error interno, comuniquese con el administrador.");
	        }catch(NamingException ex){
	        	ex.printStackTrace();
				throw new WSAtentoException("0003","Error interno, comuniquese con el administrador.");
	        }catch(Exception ex){
	        	ex.printStackTrace();
				throw new WSAtentoException("0001","Error interno, comuniquese con el administrador.");
	        }finally{}
    	}
    }
	
	public void closeConnection() {
        try {
           connection.close();
        } catch (Exception ex) {
        	System.out.println("Error al cerrar la conexion");
        }
    }
	
	public Connection getConnection() {
		return connection;
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
