package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;

import cl.laaraucana.simat.dao.PeriodoDAO;
import cl.laaraucana.simat.entidades.PeriodoVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2PeriodoDAO implements PeriodoDAO {

	public void Actualizar(PeriodoVO periodo) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		sqlMap.update("actualizarPeriodo", periodo);

	}

	public PeriodoVO BuscarById(PeriodoVO periodo) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		periodo = (PeriodoVO) sqlMap.queryForObject("getPeriodoById", periodo);
		return periodo;
	}

	public ArrayList BuscarTodo() throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			Integer i = new Integer(1);
			ArrayList listaUsuarios = new ArrayList();
			listaUsuarios = (ArrayList) sqlMap.queryForList("getTodoPeriodo", i);

			return listaUsuarios;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void Eliminar(PeriodoVO periodo) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		sqlMap.delete("eliminarPeriodo", periodo);
	}

	public void Insertar(PeriodoVO periodo) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		sqlMap.insert("insertarPeriodoValidado", periodo);

	}

	public PeriodoVO BuscarByMes(PeriodoVO periodo) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		periodo = (PeriodoVO) sqlMap.queryForObject("getPeriodoByMes", periodo);
		return periodo;
	}

}
