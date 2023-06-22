package cse.database.dao.jdbc.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import cse.database.dao.EvaluaCreditScoringPersonasDAO;
import cse.database.dao.exception.DAOException;
import cse.database.dao.jdbc.JdbcUtil;

public class EvaluaCreditScoringPersonasDAOImpl_Jdbc implements EvaluaCreditScoringPersonasDAO {

	private static Logger logger = Logger.getLogger(EvaluaCreditScoringPersonasDAOImpl_Jdbc.class.getName());
	
	public int execute(String idSolicitud) throws SQLException, DAOException {
		logger.entering(this.getClass().getName(), "execute", idSolicitud);
		CallableStatement cstmt = null;
		Connection conn = JdbcUtil.getNonXADBConnection();
		int returnStatus;
		try {
			cstmt = conn.prepareCall("{ ? = call dbo.sproc_EvaluaCreditScoringPersonas(?)}");
			logger.log(Level.INFO, "About to invoke the execution of StoredProcedure for idSolicitud: " + idSolicitud);
			cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
			cstmt.setString(2, idSolicitud);			
			cstmt.execute();
			returnStatus = cstmt.getInt(1);
//			conn.commit();
			logger.log(Level.INFO, "Succesful execution for idSolicitud: " + idSolicitud);
		} catch (SQLException sqlex) {
			logger.log(Level.SEVERE, "Problemas con la invocacion del StoredProcedure - " + sqlex.getMessage(), sqlex);
			throw new DAOException(sqlex);
		} finally {
			JdbcUtil.closeStatement(cstmt);
			JdbcUtil.closeJDBCConnection(conn);
		}
		logger.exiting(this.getClass().getName(), "execute", new Integer(returnStatus));
		return returnStatus;
	}

}
