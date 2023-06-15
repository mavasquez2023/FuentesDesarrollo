package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;

import cl.laaraucana.simat.dao.SubsParentalDAO;
import cl.laaraucana.simat.entidades.SubsParentalVO;
import cl.laaraucana.simat.utiles.LectorPropiedades;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2SubsParentalDAO implements SubsParentalDAO {

	public void Actualizar(SubsParentalVO subs) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		sqlMap.update("updateUnoSubsParentalIbatis", subs);
	}

	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		return ((ArrayList) sqlMap.queryForList("getSubsParentalIbatisByMes", mes_informacion));
	}

	public SubsParentalVO BuscarById(SubsParentalVO subs) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		subs = (SubsParentalVO) sqlMap.queryForObject("getUnoSubsParentalIbatis", subs);
		return subs;
	}

	public void Eliminar(SubsParentalVO subs) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("Llego al db2dao del");

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		sqlMap.delete("delSubsParentalIbatis", subs);
	}

	public void Insertar(SubsParentalVO subs) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Llego al db2dao insertardb2");

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		System.out.println("Llego al db2dao insertardb2");

		System.out.println("d1: " + subs.getMonto_documento());
		sqlMap.insert("setSubsParentalIbatis", subs);
		//sqlMap.insert("setSubsParentalMAP", subs);

	}

	public ArrayList BuscarTodo() throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			SubsParentalVO subsParental = new SubsParentalVO();
			ArrayList listaSubsParental = (ArrayList) sqlMap.queryForList("getTodoSubsParentalIbatis", subsParental);
			return listaSubsParental;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList BuscarLista(int keyInicio, int keyFin) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		ArrayList listaSubsParental = new ArrayList();
		try {
			SubsParentalVO subsParental = new SubsParentalVO();
			subsParental.setKeyFin(keyFin);
			subsParental.setKeyInicio(keyInicio);

			listaSubsParental = (ArrayList) sqlMap.queryForList("getList_SMF03", subsParental);
			return listaSubsParental;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaSubsParental;
	}

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		ArrayList listaSubsParental = new ArrayList();
		try {
			SubsParentalVO subsParental = new SubsParentalVO();
			subsParental.setKeyFin(keyFin);
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			subsParental.setPaginacion(cantidad);
			listaSubsParental = (ArrayList) sqlMap.queryForList("getList_avanzar_SMF03", subsParental);
			return listaSubsParental;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaSubsParental;
	}

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		ArrayList listaSubsParental = new ArrayList();
		try {
			SubsParentalVO subsParental = new SubsParentalVO();
			subsParental.setKeyInicio(keyInicio);
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			subsParental.setPaginacion(cantidad);
			listaSubsParental = (ArrayList) sqlMap.queryForList("getList_retroceder_SMF03", subsParental);
			return listaSubsParental;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaSubsParental;
	}

	public ArrayList getSubsParentalByNumDoc(SubsParentalVO subsParental) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		return ((ArrayList) sqlMap.queryForList("getSubsParentalByNumDoc", subsParental));
	}

	public ArrayList getSubsParentalByRutBenef(SubsParentalVO subsParental) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		return ((ArrayList) sqlMap.queryForList("getSubsParentalByRutBenef", subsParental));
	}

}
