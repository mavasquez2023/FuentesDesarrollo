package cse.database.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import cse.database.dao.SolicitudDAO;
import cse.database.dao.exception.DAOException;
import cse.database.dao.helper.SolicitudDAOHelper;
import cse.database.dao.jdbc.JdbcConstants;
import cse.database.dao.jdbc.JdbcUtil;
import cse.database.objects.Solicitud;

public class SolicitudDAOImpl_Jdbc implements SolicitudDAO {

	private static Logger logger = Logger.getLogger(SolicitudDAOImpl_Jdbc.class.getName());

	public int deleteByPrimaryKey(String idsolicitud) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public String createNewSolicitud() throws SQLException, DAOException {

		PreparedStatement stmtInsert = null;
		Connection conn = JdbcUtil.getNonXADBConnection();		
		try {

			String idSolicitud = JdbcUtil.getUniqueSolicitudId(conn);

			StringBuffer sbInsert = new StringBuffer();
			sbInsert.append("INSERT INTO ");
			sbInsert.append(JdbcConstants.SOLICITUD_TABLE);
			sbInsert.append("(IdSolicitud, FechaCreacion)");
			sbInsert.append(" VALUES ('");
			sbInsert.append(idSolicitud);
			sbInsert.append("', getdate()");
			sbInsert.append(")");

			stmtInsert = conn.prepareStatement(sbInsert.toString());

			logger.log(Level.INFO, "About to execute INSERT into Solicitud : " + idSolicitud);

			int rows = stmtInsert.executeUpdate();

			if (rows != 1) {
				throw new SQLException("executeUpdate return value: " + rows);
			}
//			conn.commit();
			return idSolicitud;
		} catch (SQLException sqlex) {
			logger.log(Level.SEVERE, "Problems on createNewSolicitud", sqlex);
			throw new DAOException(sqlex);
		} finally {
			JdbcUtil.closeStatement(stmtInsert);
			JdbcUtil.closeJDBCConnection(conn);
		}
	}

	public void insertSelective(Solicitud record) throws SQLException {
		// TODO Auto-generated method stub

	}

	public Solicitud selectByPrimaryKey(String idSolicitud) throws DAOException {
		
		Connection conn = JdbcUtil.getNonXADBConnection();
		
		ResultSet resultSet = null;
		PreparedStatement stmtSelect = null;		
		Solicitud solicitud = null;
		try {
			StringBuffer sb = new StringBuffer("SELECT RUT, DV, TipoAfiliado, IdGenero,IdEstadoCivil,");
			sb.append("FechaNacimiento,Remuneracion, FechaMorosidad, DiasMorosidad,"); 
			sb.append("CreditosAnteriores,LicenciaMedica,MontoNominalSolicitado,");
			sb.append("RUTEmpresa , DVEmpresa, IdClasificacionEmpresa,FechaAntiguedadLaboral,AntiguedadLaboral,");
			sb.append("RiesgoExterno, NroSueldos, MaximoMontoNominalOtorgable, ScoreModeloPersonas, PerfilRiesgo, FechaCreacion, RiesgoExterno ");
			sb.append("FROM Solicitud ");
			sb.append("WHERE IdSolicitud = ? " );
			
			System.out.println(sb);
			stmtSelect = conn.prepareStatement(sb.toString());
			stmtSelect.setString(1, idSolicitud);
			resultSet = stmtSelect.executeQuery();			 
			while(resultSet.next()){
				solicitud = new Solicitud();
				solicitud.setRut(new Integer(resultSet.getInt(1)));
				solicitud.setDv(resultSet.getString(2));
				solicitud.setTipoAfiliado(resultSet.getInt(3));
				solicitud.setIdgenero(resultSet.getString(4));
				solicitud.setIdestadocivil(new Integer(resultSet.getInt(5)));
				solicitud.setFechanacimiento(resultSet.getString(6));
				solicitud.setRemuneracion(new Integer(resultSet.getInt(7)));
				solicitud.setFechamorosidad(resultSet.getString(8));
				solicitud.setDiasmorosidad(new Integer(resultSet.getInt(9)));
				solicitud.setCreditosanteriores(new Integer(resultSet.getInt(10)));
				solicitud.setLicenciamedica(new Integer(resultSet.getInt(11)));
				solicitud.setMontonominalsolicitado(new Integer(resultSet.getInt(12)));
				solicitud.setRutempresa(new Integer(resultSet.getInt(13)));
				solicitud.setDvempresa(new String(resultSet.getString(14)));
				solicitud.setIdclasificacionempresa(new Integer(resultSet.getInt(15)));
				solicitud.setFechaantiguedadlaboral(resultSet.getString(16));
				solicitud.setAntiguedadlaboral(new Integer(resultSet.getInt(17)));
				solicitud.setRiesgoexterno(resultSet.getBigDecimal(18));
				solicitud.setNrosueldos(resultSet.getString(19));
				solicitud.setMaximomontonominalotorgable(new Integer(resultSet.getInt(20)));
				solicitud.setScoremodelopersonas(resultSet.getBigDecimal(21));
				solicitud.setPerfilriesgo(resultSet.getString(22));
				solicitud.setFechacreacion(resultSet.getDate(23));
				solicitud.setRiesgoexterno(resultSet.getBigDecimal(24));
			}
//			conn.commit();
			return solicitud;
		} catch (SQLException sqlex) {
			logger.log(Level.SEVERE, "Problems on selectByPrimaryKey", sqlex);
			throw new DAOException(sqlex);
		} finally {			
			JdbcUtil.closeStatement(stmtSelect);
			JdbcUtil.closeResultSet(resultSet);
			JdbcUtil.closeJDBCConnection(conn);
		}		
	}

	public int updateByPrimaryKey(Solicitud record) throws SQLException, DAOException {

		Connection conn = JdbcUtil.getNonXADBConnection();
		PreparedStatement stmtUpdate = null;

		try {
			StringBuffer sb = new StringBuffer(
					"UPDATE dbo.Solicitud SET RUT = ? , DV = ?, TipoAfiliado = ?, IdGenero = ?, IdEstadoCivil = ?, ");
			sb.append("FechaNacimiento = ?,  Remuneracion = ? , FechaMorosidad = ?, CreditosAnteriores = ? , ");
			sb.append("LicenciaMedica = ? ,MontoNominalSolicitado = ?, RUTEmpresa = ?, DVEmpresa = ?, IdClasificacionEmpresa = ?, ");
			sb.append("FechaAntiguedadLaboral = ? , RiesgoExterno = ? ");
			sb.append("WHERE IdSolicitud = ?");
			//		sb.append("WHERE IdSolicitud = " + record.getIdsolicitud());

			stmtUpdate = conn.prepareStatement(sb.toString());
			stmtUpdate.setObject(1, record.getRut());
			stmtUpdate.setString(2, record.getDv());
			stmtUpdate.setObject(3, record.getTipoAfiliado());		
			stmtUpdate.setObject(4, SolicitudDAOHelper.translateGenero(record));
			stmtUpdate.setObject(5, record.getIdestadocivil());
			stmtUpdate.setString(6, record.getFechanacimiento());
			stmtUpdate.setObject(7, record.getRemuneracion());
			stmtUpdate.setString(8, record.getFechamorosidad());
			stmtUpdate.setObject(9, record.getCreditosanteriores());
			stmtUpdate.setObject(10, record.getLicenciamedica());
			stmtUpdate.setObject(11, record.getMontonominalsolicitado());
			stmtUpdate.setObject(12, record.getRutempresa());
			stmtUpdate.setObject(13, record.getDvempresa());
			stmtUpdate.setObject(14, record.getIdclasificacionempresa());
			stmtUpdate.setString(15, record.getFechaantiguedadlaboral());
			stmtUpdate.setObject(16, record.getRiesgoexterno());
			stmtUpdate.setObject(17, record.getIdsolicitud());
			int resultExecution = stmtUpdate.executeUpdate();
//			conn.commit();
			return resultExecution; 
		} catch (SQLException sqlex) {
			logger.log(Level.SEVERE, "Problems on updateByPrimaryKey", sqlex);
			throw new DAOException(sqlex);
		} finally {		
			JdbcUtil.closeStatement(stmtUpdate);
			JdbcUtil.closeJDBCConnection(conn);
		}
	}

	public void insert(Solicitud record) throws SQLException, DAOException {
		/*
		 * INSERT INTO [MotorCreditScoring].[dbo].[Solicitud] ([IdSolicitud]
		 * ,[RUT] ,[IdGenero] ,[IdEstadoCivil] ,[FechaNacimiento]
		 * ,[Remuneracion] ,[FechaMorosidad] ,[DiasMorosidad]
		 * ,[CreditosAnteriores] ,[LicenciaMedica] ,[MontoNominalSolicitado]
		 * ,[RUTEmpresa] ,[IdClasificacionEmpresa] ,[FechaAntiguedadLaboral]
		 * ,[AntiguedadLaboral] ,[RiesgoExterno] ,[NroSueldos]
		 * ,[ScoreModeloPersonas] ,[PerfilRiesgo] ,[FechaCreacion])
		 */
	}

}
