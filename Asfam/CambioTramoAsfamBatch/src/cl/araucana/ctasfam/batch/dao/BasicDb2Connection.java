package cl.araucana.ctasfam.batch.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import cl.araucana.ctasfam.batch.common.exceptions.TechnicalException;

public class BasicDb2Connection {
	
	final static Logger logger = Logger.getLogger(BasicDb2Connection.class);
	
	private Connection connection;
	private Integer countVecesUsada;
	private BasicDataSource dataSource;

	public BasicDb2Connection() {
		dataSource = new BasicDataSource();
	}

	public Connection getConnection() throws TechnicalException {
		// Si esta conexion esta abierta y se uso al menos 100 veses se cierra y abre nuevamente
		if (connection != null && isOpenConnection()) {
			if (countVecesUsada >= 1000) {
				closeConnection();
				openConnection();
			}
		} else {
			openConnection();
		}
		countVecesUsada++;
		return connection;
	}

	public void openConnection() throws TechnicalException {
		connection = dataSource.getDb2Connection();
		countVecesUsada = 0;
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Boolean isOpenConnection() {
		try {
			return !connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public Integer getCountVecesUsada() {
		return countVecesUsada;
	}
}
