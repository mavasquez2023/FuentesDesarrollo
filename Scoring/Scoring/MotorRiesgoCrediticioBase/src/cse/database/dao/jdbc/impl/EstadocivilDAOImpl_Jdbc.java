package cse.database.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cse.database.dao.EstadocivilDAO;
import cse.database.dao.exception.DAOException;
import cse.database.dao.jdbc.JdbcConstants;
import cse.database.dao.jdbc.JdbcUtil;
import cse.database.objects.Estadocivil;

public class EstadocivilDAOImpl_Jdbc implements EstadocivilDAO {
	
	Logger logger = Logger.getLogger(EstadocivilDAOImpl_Jdbc.class.getName());

	public int deleteByPrimaryKey(Integer idestadocivil) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void insert(Estadocivil record) throws SQLException {
		// TODO Auto-generated method stub

	}

	public void insertSelective(Estadocivil record) throws SQLException {
		// TODO Auto-generated method stub

	}

	public Estadocivil selectByPrimaryKey(Integer idestadocivil) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKey(Estadocivil record) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public List selectByNombre(String nombreEstadoCivil) throws SQLException, DAOException {
		PreparedStatement stmtSelect = null;
		ResultSet resultSet = null;
		List list = new ArrayList();
		Connection conn = JdbcUtil.getNonXADBConnection();
		try {			
			String sql = "SELECT IdEstadoCivil,Nombre FROM " + JdbcConstants.ESTADO_CIVIL_TABLE + " WHERE Nombre = ?";
			stmtSelect = conn.prepareStatement(sql);
			stmtSelect.setString(1, nombreEstadoCivil);
			resultSet = stmtSelect.executeQuery();
			while(resultSet.next()){
				Estadocivil estadoCivil = new Estadocivil();
				estadoCivil.setIdestadocivil(new Integer(resultSet.getInt(1)));
				estadoCivil.setNombre(resultSet.getString(2));
				list.add(estadoCivil);
			}
//			conn.commit();
			return list;
		} catch (SQLException sqlex) {
			logger.log(Level.SEVERE, "", sqlex);
			throw new DAOException(sqlex);
		} finally {
			JdbcUtil.closeStatement(stmtSelect);
			JdbcUtil.closeResultSet(resultSet);
			JdbcUtil.closeJDBCConnection(conn);
		}		
	}

}
