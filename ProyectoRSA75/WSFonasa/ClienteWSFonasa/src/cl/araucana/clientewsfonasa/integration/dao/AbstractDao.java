package cl.araucana.clientewsfonasa.integration.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cl.araucana.clientewsfonasa.common.exception.DaoException;
import cl.araucana.clientewsfonasa.common.util.ConfigUtil;

public abstract class AbstractDao {

	private static final String KEY_DATA_SOURCE_NAME = "araucana.clientewsfonasa.conexion.datasourcename";
	private Connection connection;
	
//	private Logger log = Logger.getLogger(AbstractDao.class);
	
	public void openConnection() throws DaoException{
    	if(connection == null){
    		try {
	        	String dataSourceName = ConfigUtil.getValor(KEY_DATA_SOURCE_NAME);
	        	if(dataSourceName == null){
//	        		log.error("Codigo 0002: Error interno, no fue posible obtener el nombre del jndi.");
	        		throw new DaoException("0002","Error interno, no fue posible obtener el nombre del jndi.");
	        	}
	            InitialContext ic = new InitialContext();
	            DataSource ds = (DataSource)ic.lookup(dataSourceName);
	            connection = ds.getConnection();
	        }catch(SQLException ex){
//	        	log.error("Codigo 0004: Error interno, no fue posible establecer conexion con la base de datos.");
//	        	log.error(ex.getStackTrace());
	        	ex.printStackTrace();
				throw new DaoException("0004","Error interno, no fue posible establecer conexion con la base de datos.");
				
	        }catch(NamingException ex){
//	        	log.error("Codigo 0003: Error interno, no fue posible encontrar el jndi.");
//	        	log.error(ex.getStackTrace());
				throw new DaoException("0003","Error interno, no fue posible encontrar el jndi.");
	        }catch(Exception ex){
//	        	log.error("Codigo 0001: Error interno, se genero error desconocido.");
//	        	log.error(ex.getStackTrace());
				throw new DaoException("0001","Error interno, se genero error desconocido.");
	        }finally{}
    	}
    }
	
	public void closeConnection() {
        try {
           connection.close();
        } catch (Exception ex) {
//        	log.error("Codigo 0005: Error interno, no fue posible cerrar la conexion.");
//        	log.error(ex.getStackTrace());
        }
    }
	
	public Connection getConnection() throws DaoException{
		try {
			if(connection == null || connection.isClosed())
				connection = null;
				openConnection();
		} catch (SQLException e) {
			throw new DaoException("0006","Error interno, se genero error al validar la conexion.");
		} 
		return connection;
	}
}
