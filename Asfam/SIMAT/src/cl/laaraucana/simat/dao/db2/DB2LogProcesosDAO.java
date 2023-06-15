package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;

import cl.laaraucana.simat.dao.LogProcesosDAO;
import cl.laaraucana.simat.entidades.LogProcesosVO;
import cl.laaraucana.simat.utiles.LectorPropiedades;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2LogProcesosDAO implements LogProcesosDAO {

	public void Actualizar(LogProcesosVO log) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		System.out.println("act mysql");
		sqlMap.update("actualizarLogProcesos", log);
	}

	public ArrayList BuscarByIdRegistro(String id_registro) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		ArrayList listaProcesos = new ArrayList();
		try {
			listaProcesos = (ArrayList) sqlMap.queryForList("getLogProcesosByIdRegistro", id_registro);
			return listaProcesos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaProcesos;
	}

	public void Eliminar(LogProcesosVO log) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		sqlMap.delete("eliminarLogProcesos", log);
	}

	public void Insertar(LogProcesosVO log) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		sqlMap.insert("insertarLogProcesos", log);
	}

	public LogProcesosVO BuscarById(LogProcesosVO log) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			log = (LogProcesosVO) sqlMap.queryForObject("getLogProcesosByIdRegistro", log);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return log;
	}

	public ArrayList BuscarByTable(String tabla) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		ArrayList listaProcesos = new ArrayList();
		try {
			listaProcesos = (ArrayList) sqlMap.queryForList("getLogProcesosByTabla", tabla);
			return listaProcesos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaProcesos;
	}

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception {

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		ArrayList listaProcesos = new ArrayList();
		LogProcesosVO log = new LogProcesosVO();
		try {

			log.setKeyFin(keyFin);
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			log.setPaginacion(cantidad);
			listaProcesos = (ArrayList) sqlMap.queryForList("getList_avanzar_SMLPR", log);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaProcesos;

	}

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception {

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		ArrayList listaProcesos = new ArrayList();
		LogProcesosVO log = new LogProcesosVO();
		try {

			log.setKeyInicio(keyInicio);
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			log.setPaginacion(cantidad);
			listaProcesos = (ArrayList) sqlMap.queryForList("getList_retroceder_SMLPR", log);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaProcesos;

	}

}
