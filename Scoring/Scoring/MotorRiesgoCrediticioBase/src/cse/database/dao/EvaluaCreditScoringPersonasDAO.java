package cse.database.dao;

import java.sql.SQLException;

import cse.database.dao.exception.DAOException;

public interface EvaluaCreditScoringPersonasDAO {

	public int execute(String idSolicitud) throws SQLException, DAOException;
	
}
