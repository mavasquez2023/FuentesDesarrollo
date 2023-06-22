package cse.database.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cse.database.dao.ClasificacionempresaDAO;
import cse.database.dao.exception.DAOException;
import cse.database.dao.jdbc.JdbcConstants;
import cse.database.dao.jdbc.JdbcUtil;
import cse.database.objects.Clasificacionempresa;

public class ClasificacionempresaDAOImpl_Jdbc implements ClasificacionempresaDAO {

	Logger logger = Logger.getLogger(ClasificacionempresaDAOImpl_Jdbc.class.getName());

	public int deleteByPrimaryKey(Integer idclasificacionempresa) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void insert(Clasificacionempresa record) throws SQLException {
		// TODO Auto-generated method stub

	}

	public void insertSelective(Clasificacionempresa record) throws SQLException {
		// TODO Auto-generated method stub

	}

	public Clasificacionempresa selectByPrimaryKey(Integer idclasificacionempresa)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKey(Clasificacionempresa record) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public List selectByNombre(String nombreClasificacion) throws SQLException, DAOException {
		PreparedStatement stmtSelect = null;
		ResultSet resultSet = null;
		List list = new ArrayList();
		Connection conn = JdbcUtil.getNonXADBConnection();
		try {
			String sql = "SELECT IdClasificacionEmpresa, Nombre, Descripcion FROM "
					+ JdbcConstants.CLASIFICACION_EMPRESA_TABLE + " WHERE Nombre = ? ";
			stmtSelect = conn.prepareStatement(sql);
			stmtSelect.setString(1, nombreClasificacion);
			resultSet = stmtSelect.executeQuery();
			while (resultSet.next()) {
				Clasificacionempresa clasificacionempresa = new Clasificacionempresa();
				clasificacionempresa.setIdclasificacionempresa(new Integer(resultSet.getInt(1)));
				clasificacionempresa.setNombre(resultSet.getString(2));
				clasificacionempresa.setDescripcion(resultSet.getString(3));
				list.add(clasificacionempresa);
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
