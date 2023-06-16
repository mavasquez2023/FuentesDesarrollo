package cl.araucana.ctasfam.batch.dao;

import org.apache.log4j.Logger;

import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;

import com.ibm.as400.access.AS400;

public abstract class AbstractAs400Dao {
	final static Logger logger = Logger.getLogger(AbstractDb2Dao.class);
	
	private AS400 connection;
	
	public AS400 getConnection() throws TechnicalException{
		if(connection == null || !connection.isConnectionAlive()){
			BasicDataSource dataSource = new BasicDataSource();
			connection = dataSource.getAs400Connection();
		}

		return connection;
	}
	
	public void closeConnection() {
        try {
        	if(connection != null && connection.isConnected())
        		connection.disconnectAllServices();
        } catch (Exception ex) {
        	logger.error("Ocurrio un error al cerrar la conexion con el sistema de archivo");
        }
    }
}
