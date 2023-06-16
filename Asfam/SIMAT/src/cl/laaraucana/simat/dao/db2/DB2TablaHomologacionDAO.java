package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;

import cl.laaraucana.simat.dao.TablaHomologacionDAO;
import cl.laaraucana.simat.entidades.TablaHomologacionVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2TablaHomologacionDAO implements TablaHomologacionDAO {

	public void Actualizar(TablaHomologacionVO homologacion) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		System.out.println("Llego al db2 Actualizar " + homologacion.getId_registro());

		sqlMap.update("actualizarTablaHomologacion", homologacion);

	}

	public TablaHomologacionVO BuscarByIdRegistro(TablaHomologacionVO homologacion) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		TablaHomologacionVO homologacionVO = new TablaHomologacionVO();
		try {
			System.out.println("db2 HOMOLOGACIÓN" + homologacion.getId_registro());

			homologacionVO = (TablaHomologacionVO) sqlMap.queryForObject("getTablaHomologacionByIdRegistro", homologacion);

			System.out.println("devueltos lista db2" + homologacion.getId_registro());
			return homologacionVO;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList buscarByClasificacion(TablaHomologacionVO homologacion) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		return (ArrayList) sqlMap.queryForList("getTablaHomologacionByClasificacion", homologacion);
	}//end buscarByClasificacion

	public void Eliminar(TablaHomologacionVO homologacion) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		System.out.println("Apunto de eliminar " + homologacion.getId_registro());

		sqlMap.delete("eliminarTablaHomologacion", homologacion);
	}

	public void Insertar(TablaHomologacionVO homologacion) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		System.out.println("db2 HOMOLOGACIÓN " + homologacion.getClasificacion());

		sqlMap.insert("insertarTablaHomologacion", homologacion);
	}

	public ArrayList BuscarTodo() throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		ArrayList listaHomologacion = new ArrayList();
		try {
			TablaHomologacionVO homologacion = new TablaHomologacionVO();
			listaHomologacion = (ArrayList) sqlMap.queryForList("getTodoTablaHomologacion", homologacion);
		} catch (SQLException e) {
			listaHomologacion = null;
			e.printStackTrace();
		}
		return listaHomologacion;
	}

}//end class
