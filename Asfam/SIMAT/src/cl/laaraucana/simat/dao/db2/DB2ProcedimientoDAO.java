package cl.laaraucana.simat.dao.db2;

import java.sql.SQLException;
import java.util.HashMap;

import cl.laaraucana.simat.dao.ProcedimientosDAO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.ibatis.sqlmap.client.SqlMapSession;

public class DB2ProcedimientoDAO implements ProcedimientosDAO {

	public void callProcedureValidar(String periodo) throws Exception {
		SqlMapClient sqlMapLocal = null;
		sqlMapLocal = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader("cl/laaraucana/simat/ibatis/sql-map-config.xml"));

		SqlMapSession session = sqlMapLocal.openSession();
		try {
			HashMap params = new HashMap();
			params.put("Fecha", periodo);
			sqlMapLocal.queryForObject("ProcedimientoValidacion", params);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void callProcedureCargaArchivosDB2(String periodo) throws Exception {
		SqlMapClient sqlMapLocal = null;
		sqlMapLocal = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader("cl/laaraucana/simat/ibatis/sql-map-config.xml"));

		SqlMapSession session = sqlMapLocal.openSession();
		try {
			HashMap params = new HashMap();
			params.put("Fecha", periodo);
			sqlMapLocal.queryForObject("ProcedimientoCargaArchivosDB2", params);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void callProcedureRespaldoHistorico(String periodo, String tabla) throws Exception {
		SqlMapClient sqlMapLocal = null;
		sqlMapLocal = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader("cl/laaraucana/simat/ibatis/sql-map-config.xml"));

		SqlMapSession session = sqlMapLocal.openSession();
		try {
			HashMap params = new HashMap();
			params.put("Fecha", periodo);
			params.put("tabla", tabla);
			sqlMapLocal.queryForObject("ProcedimientoRespaldoHistorico", params);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void callProcedureDistribucion(String periodo) throws Exception {
		SqlMapClient sqlMapLocal = null;
		sqlMapLocal = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader("cl/laaraucana/simat/ibatis/sql-map-config.xml"));

		SqlMapSession session = sqlMapLocal.openSession();
		try {
			HashMap params = new HashMap();
			params.put("Fecha", periodo);
			sqlMapLocal.queryForObject("ProcedimientoDistribucion", params);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			session.close();
			try {
				sqlMapLocal.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//	public void callProcedureFechaPorDefecto(String tabla) throws Exception {
	//		SqlMapClient sqlMapLocal = null;
	//		sqlMapLocal = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader("cl/laaraucana/simat/ibatis/sql-map-config.xml"));
	////cambiara fechas "2039-01-01" por 0001-01-01
	//		SqlMapSession session = sqlMapLocal.openSession();
	//		try {
	//			HashMap params = new HashMap();
	//			params.put("tabla", tabla);
	//            sqlMapLocal.queryForObject("ProcedimientoFechaPorDefecto", params);   
	//		}catch(SQLException e) {
	//			e.printStackTrace();
	//		} finally {
	//			session.close();
	//			try {
	//				sqlMapLocal.endTransaction();
	//			} catch(SQLException e) {
	//				e.printStackTrace();
	//			}
	//		}
	//	}//end callProcedureFechaPorDefecto

}//end

