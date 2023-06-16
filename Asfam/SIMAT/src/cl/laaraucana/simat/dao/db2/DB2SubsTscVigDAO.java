package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;

import cl.laaraucana.simat.dao.SubsTscVigDAO;
import cl.laaraucana.simat.entidades.SubsTscVigVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2SubsTscVigDAO implements SubsTscVigDAO {

	public void Actualizar(SubsTscVigVO subs) throws Exception {
		// TODO Auto-generated method stub

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		sqlMap.update("updateUnoSubsTscVigIbatis", subs);
	}

	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception {
		// TODO Auto-generated method stub

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			ArrayList listaSubsTscVig = new ArrayList();
			System.out.println("Llego al -MYSQL buscar");
			listaSubsTscVig = (ArrayList) sqlMap.queryForList("getSubsTscVigIbatisByMes", mes_informacion);

			return listaSubsTscVig;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void Eliminar(SubsTscVigVO subs) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		sqlMap.delete("delSubsTscVigIbatis", subs);

	}

	public void Insertar(SubsTscVigVO subs) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Llego al db2 insertar");

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		System.out.println("Llego al Mannager eliminar");

		sqlMap.insert("setSubsTscVigIbatis", subs);
	}

	public SubsTscVigVO BuscarById(SubsTscVigVO subsTscVig) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		subsTscVig = (SubsTscVigVO) sqlMap.queryForObject("getUnoSubsTscVigIbatis", subsTscVig);
		return subsTscVig;
	}

	public ArrayList BuscarTodo() throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			SubsTscVigVO subsTscVigres = new SubsTscVigVO();
			ArrayList listaSubsTscVig = new ArrayList();
			System.out.println("Llego al -MYSQL buscar");
			listaSubsTscVig = (ArrayList) sqlMap.queryForList("getTodoSubsTscVigIbatis", subsTscVigres);

			return listaSubsTscVig;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
