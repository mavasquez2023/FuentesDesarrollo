package cse.database.dao.traza.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import cse.database.dao.exception.DAOException;
import cse.database.dao.jdbc.JdbcUtil;
import cse.database.dao.traza.TrazaCalificacionRiesgoExternaDAO;
import cse.model.businessobject.CalificacionRiesgoExterna;
import cse.model.businessobject.Rut;

public class TrazaCalificacionRiesgoExternaDAOImpl_Jdbc implements TrazaCalificacionRiesgoExternaDAO {

	private static Logger logger = Logger.getLogger(TrazaCalificacionRiesgoExternaDAOImpl_Jdbc.class.getName());
	
	public int insert(String newSolicitudID, Rut rutSolicitante,
			String origenDatos, CalificacionRiesgoExterna datos) throws DAOException {

		Connection conn = JdbcUtil.getNonXADBConnection();

		PreparedStatement stmtInsert = null;
		
		StringBuffer sb = new StringBuffer(
				  "INSERT INTO dbo.TrazaRiesgoExterno (idSolicitud, rutSolicitante, origen, scoreRiesgo)");
		sb.append("VALUES (?, ?, ?, ?)");
		
		try {
			stmtInsert = conn.prepareStatement(sb.toString());
			stmtInsert.setObject(1, newSolicitudID);
			stmtInsert.setObject(2, rutSolicitante.getNumero().concat("-").concat(rutSolicitante.getDigitoChequeo()) );
			stmtInsert.setString(3, origenDatos);
			stmtInsert.setInt(4, datos.getValor().intValue());
			int resultExecution = stmtInsert.executeUpdate();
			return resultExecution; 

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Problemas al grabar un registro de traza de riesgo externo", e);
			throw new DAOException("Problemas al grabar un registro de traza de riesgo externo", e);
		}
	}
}
