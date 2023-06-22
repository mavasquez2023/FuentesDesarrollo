package cse.database.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import cse.database.dao.AlertaSistemaDAO;
import cse.database.dao.exception.DAOException;
import cse.database.dao.jdbc.JdbcUtil;

public class AlertaSistemaDAOImpl_Jdbc implements AlertaSistemaDAO {

	private static Logger logger = Logger.getLogger(AlertaSistemaDAOImpl_Jdbc.class.getName());
	
	public int insert(String idSolicitud,String component, String bussMessageHeader, String bussMessageDetail, String techMessage)
			throws DAOException {
		
		Connection conn = JdbcUtil.getNonXADBConnection();

		PreparedStatement stmtInsert = null;
		
		StringBuffer sb = new StringBuffer(
				  "INSERT INTO dbo.AlertasSistema (IdSolicitud, Fecha, Componente, Mensaje, DetalleMensaje, DetalleError)");
		sb.append("VALUES (?, GETDATE(), ?, ?, ?, ? )");
		
		try {
			stmtInsert = conn.prepareStatement(sb.toString());
			
			idSolicitud=idSolicitud==null?"Sin info":idSolicitud;
			stmtInsert.setObject(1, idSolicitud);
			
			component=component==null?"Sin info":component;
			stmtInsert.setObject(2, component);
			
			bussMessageHeader=bussMessageHeader==null?"Sin info":bussMessageHeader;
			stmtInsert.setString(3, bussMessageHeader.length()>250?bussMessageHeader.substring(0, 250):bussMessageHeader);
			
			bussMessageDetail=bussMessageDetail==null?"Sin info":bussMessageDetail;
			stmtInsert.setString(4, bussMessageDetail.length()>250?bussMessageDetail.substring(0, 250):bussMessageDetail);
			
			techMessage=techMessage==null?"Sin info":techMessage;
			stmtInsert.setString(5, techMessage.length()>250?techMessage.substring(0, 250):techMessage);
			
			int resultExecution = stmtInsert.executeUpdate();
			return resultExecution; 

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Problemas al grabar un registro de alerta de sistema", e);
			throw new DAOException("Problemas al grabar un registro de alerta de sistema", e);
		}
	}
}
