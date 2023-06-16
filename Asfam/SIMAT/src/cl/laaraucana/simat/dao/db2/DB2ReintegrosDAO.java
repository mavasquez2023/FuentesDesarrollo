package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;

import cl.laaraucana.simat.dao.ReintegrosDAO;
import cl.laaraucana.simat.entidades.ReintegrosVO;
import cl.laaraucana.simat.utiles.LectorPropiedades;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2ReintegrosDAO implements ReintegrosDAO {

	public void Actualizar(ReintegrosVO reintegros) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		System.out.println("act mysql");
		sqlMap.update("updateUnoReintegrosIbatis", reintegros);
	}

	public void Eliminar(ReintegrosVO reintegros) throws Exception {
		// TODO Auto-generated method stub		
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		sqlMap.delete("delReintegrosIbatis", reintegros);

	}

	public void Insertar(ReintegrosVO reintegros) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		sqlMap.insert("setReintegrosIbatis", reintegros);
	}

	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			ArrayList listaReintegros = new ArrayList();
			listaReintegros = (ArrayList) sqlMap.queryForList("getReintegrosIbatisByMes", mes_informacion);

			return listaReintegros;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ReintegrosVO BuscarById(ReintegrosVO reintegros) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		reintegros = (ReintegrosVO) sqlMap.queryForObject("getReintegrosIbatisById", reintegros);
		return reintegros;
	}

	public ArrayList buscarTodo() throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			ReintegrosVO raux = new ReintegrosVO();
			ArrayList listaReintegros = new ArrayList();
			listaReintegros = (ArrayList) sqlMap.queryForList("getTodoReintegrosIbatis", raux);

			return listaReintegros;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception {

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		ArrayList listaReintegros = new ArrayList();
		try {
			ReintegrosVO reintegros = new ReintegrosVO();
			reintegros.setKeyFin(keyFin);
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			reintegros.setPaginacion(cantidad);
			listaReintegros = (ArrayList) sqlMap.queryForList("getList_avanzar_SMF01", reintegros);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaReintegros;

	}

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception {

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		ArrayList listaReintegros = new ArrayList();
		try {
			ReintegrosVO reintegros = new ReintegrosVO();
			reintegros.setKeyInicio(keyInicio);
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			reintegros.setPaginacion(cantidad);
			listaReintegros = (ArrayList) sqlMap.queryForList("getList_retroceder_SMF01", reintegros);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaReintegros;
	}

	public ArrayList getReintegrosByRutBenef(ReintegrosVO reintegros) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		ArrayList listaReintegros = new ArrayList();
		listaReintegros = (ArrayList) sqlMap.queryForList("getReintegrosByRutBenef", reintegros);
		return listaReintegros;
	}

}
