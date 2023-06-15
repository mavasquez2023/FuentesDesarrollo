package cl.laaraucana.simat.dao.db2;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;

import cl.laaraucana.simat.dao.SubsPrePostNmDAO;
import cl.laaraucana.simat.entidades.SubsPrePostNMVO;
import cl.laaraucana.simat.utiles.LectorPropiedades;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2SubsPrePostNmDAO implements SubsPrePostNmDAO {

	public void Actualizar(SubsPrePostNMVO subs) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		int res = sqlMap.update("updateUnoSubsPrePostNMIbatis", subs);

	}

	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			System.out.println("llega al db2" + mes_informacion);
			ArrayList listaSubs = new ArrayList();
			listaSubs = (ArrayList) sqlMap.queryForList("getSubsPrePostNMByMesInf", mes_informacion);
			System.out.println("lsita" + listaSubs.size());
			return listaSubs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public SubsPrePostNMVO BuscarById(SubsPrePostNMVO subsPrePostNM) throws IOException {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		System.out.println("Llego al db2 buscarid");
		try {
			subsPrePostNM = (SubsPrePostNMVO) sqlMap.queryForObject("getUnoSubsPrePostNMIbatis", subsPrePostNM);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subsPrePostNM;
	}

	public void Eliminar(SubsPrePostNMVO subs) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		sqlMap.delete("delSubsPrePostNMIbatis", subs);

	}

	public void Insertar(SubsPrePostNMVO subs) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		sqlMap.insert("setSubsPrePostNMIbatis", subs);
	}

	public ArrayList BuscarTodo() throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			SubsPrePostNMVO subs = new SubsPrePostNMVO();
			ArrayList listaSubs = new ArrayList();
			listaSubs = (ArrayList) sqlMap.queryForList("getTodoSubsPrePostNMIbatis", subs);
			System.out.println("lsita" + listaSubs.size());
			return listaSubs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception {

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		SubsPrePostNMVO subs = new SubsPrePostNMVO();
		ArrayList listaSubs = new ArrayList();
		try {

			subs.setKeyFin(keyFin);
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			subs.setPaginacion(cantidad);
			listaSubs = (ArrayList) sqlMap.queryForList("getList_avanzar_SMF02", subs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaSubs;

	}

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception {

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		SubsPrePostNMVO subs = new SubsPrePostNMVO();
		ArrayList listaSubs = new ArrayList();
		try {

			subs.setKeyInicio(keyInicio);
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			subs.setPaginacion(cantidad);
			listaSubs = (ArrayList) sqlMap.queryForList("getList_retroceder_SMF02", subs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaSubs;

	}

	public ArrayList getSubsPrePostNMByNumDoc(SubsPrePostNMVO subsPrePostNM) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		return ((ArrayList) sqlMap.queryForList("getSubsPrePostNMByNumDoc", subsPrePostNM.getNum_documento()));
	}

	public ArrayList getSubsPrePostNMByRutBenef(SubsPrePostNMVO subsPrePostNM) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		return ((ArrayList) sqlMap.queryForList("getSubsPrePostNMByRutBenef", subsPrePostNM.getRun_beneficiario()));
	}

}//end class
