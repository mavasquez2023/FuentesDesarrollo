package cse.database.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import cse.database.dao.EquifaxCacheDAO;
import cse.database.dao.exception.DAOException;
import cse.database.dao.jdbc.JdbcConstants;
import cse.database.dao.jdbc.JdbcUtil;
import cse.model.businessobject.CalificacionRiesgoExterna;
import cse.model.businessobject.Rut;

public class EquifaxCacheDAOImpl_Jdbc implements EquifaxCacheDAO {

	private static Logger logger = Logger.getLogger(EquifaxCacheDAOImpl_Jdbc.class.getName());

	
	public CalificacionRiesgoExterna findByRut(String numero, String digitoChequeo)
			throws DAOException {

		Connection conn = JdbcUtil.getNonXADBConnection();

		ResultSet resultSet = null;
		PreparedStatement stmtSelect = null;
		CalificacionRiesgoExterna cre = null;

		StringBuffer sb = new StringBuffer(
				"SELECT RUT, DV, ScoreExterno ");
		sb.append("FROM " + JdbcConstants.RIESGO_EXTERNO_TABLE + " ");
		sb.append("WHERE rut = ? and dv = ? ");
		sb.append("AND convert(date ,fecha) >= convert(date ,DATEADD(day, -7, GETDATE()))");

		try {
			stmtSelect = conn.prepareStatement(sb.toString());
			stmtSelect.setString(1, numero);
			stmtSelect.setString(2, digitoChequeo);
			resultSet = stmtSelect.executeQuery();

			
			// Esto se debería ejecutar una sóla vez siempre
			if (resultSet.next()) {
				cre = new CalificacionRiesgoExterna();
				cre.setValor(new Integer(resultSet.getInt("ScoreExterno")));
			}
//			conn.commit();
			return cre;
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Problemas al acceder al cache de score Equifax en Motor", e);
			throw new DAOException("Problemas al acceder al cache de score Equifax en Motor", e);

		} finally {
			JdbcUtil.closeStatement(stmtSelect);
			JdbcUtil.closeResultSet(resultSet);
			JdbcUtil.closeJDBCConnection(conn);
		}
	}

	public int insert(Rut rut, CalificacionRiesgoExterna datos) throws DAOException{
		//TODO: Terminar
		Connection conn = JdbcUtil.getNonXADBConnection();

		PreparedStatement stmtInsert = null;
		StringBuffer sb = new StringBuffer("DELETE FROM dbo.RiesgoExterno WHERE RUT = ? AND DV = ?");
		try {
			stmtInsert = conn.prepareStatement(sb.toString());
			stmtInsert.setInt(1, Integer.parseInt(rut.getNumero()));
			stmtInsert.setString(2, rut.getDigitoChequeo());
			stmtInsert.executeUpdate();
		} catch (Exception e) {
			throw new DAOException("Problemas al grabar al cache de score Equifax en Motor", e);
		}
		
		sb = new StringBuffer(
				  "INSERT INTO dbo.RiesgoExterno (Fecha, RUT, DV, ScoreExterno, FechaNac, Genero, EstCivil)");
		sb.append("VALUES (GETDATE(), ?, ?, ?, ?, ?, ? )");
		
		try {
			stmtInsert = conn.prepareStatement(sb.toString());
			stmtInsert.setObject(1, Integer.valueOf(rut.getNumero()));
			stmtInsert.setString(2, rut.getDigitoChequeo());
			stmtInsert.setObject(3, datos.getValor());
			stmtInsert.setString(4, datos.getFecNac());
			stmtInsert.setString(5, datos.getGenero());
			stmtInsert.setString(6, datos.getEstCivil());
			
			int resultExecution = stmtInsert.executeUpdate();
//			conn.commit();
			return resultExecution; 
			
		} catch (NumberFormatException e) {
			logger.log(Level.SEVERE, "Problemas de formato de numero al grabar al cache de score Equifax en Motor", e);
			throw new DAOException("Problemas al grabar al cache de score Equifax en Motor", e);
			
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Problemas al grabar al cache de score Equifax en Motor", e);
			throw new DAOException("Problemas al grabar al cache de score Equifax en Motor", e);
		}
	}
}
