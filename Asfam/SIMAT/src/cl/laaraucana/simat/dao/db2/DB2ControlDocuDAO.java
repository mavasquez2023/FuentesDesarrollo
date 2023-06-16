package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;

import cl.laaraucana.simat.dao.ControlDocuDAO;
import cl.laaraucana.simat.entidades.ControlDocuVO;
import cl.laaraucana.simat.utiles.LectorPropiedades;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2ControlDocuDAO implements ControlDocuDAO {

	public void Actualizar(ControlDocuVO controlDocu) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		System.out.println("act mysql");

		sqlMap.update("actualizarControlDocu", controlDocu);
	}

	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		ArrayList listaControlDocu = new ArrayList();
		listaControlDocu = (ArrayList) sqlMap.queryForList("getControlDocuByMesInformacion", mes_informacion);
		return listaControlDocu;
	}

	public void Eliminar(ControlDocuVO controlDocu) throws Exception {
		// TODO Auto-generated method stub´
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		sqlMap.delete("delControlDocuIbatis", controlDocu);
	}

	public void Insertar(ControlDocuVO controlDocu) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		System.out.println("Llego a la parte del sqlMap en MYSQLControlDocuDAO");

		sqlMap.insert("insertarControlDocu", controlDocu);
	}

	public ControlDocuVO BuscarById(ControlDocuVO idControlDocu) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			idControlDocu = (ControlDocuVO) sqlMap.queryForObject("getUnoControlDocuIbatis", idControlDocu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idControlDocu;
	}

	public ArrayList BuscarTodo() throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			ControlDocuVO control = new ControlDocuVO();
			ArrayList listaControlDocu = new ArrayList();
			listaControlDocu = (ArrayList) sqlMap.queryForList("getTodoControlDocu", control);

			return listaControlDocu;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception {

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		ControlDocuVO control = new ControlDocuVO();
		ArrayList listaControlDocu = new ArrayList();
		try {
			control.setKeyFin(keyFin);
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			control.setPaginacion(cantidad);
			listaControlDocu = (ArrayList) sqlMap.queryForList("getList_avanzar_SMF05", control);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaControlDocu;

	}

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception {

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		ControlDocuVO control = new ControlDocuVO();
		ArrayList listaControlDocu = new ArrayList();
		try {

			control.setKeyInicio(keyInicio);
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			control.setPaginacion(cantidad);
			listaControlDocu = (ArrayList) sqlMap.queryForList("getList_retroceder_SMF05", control);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaControlDocu;

	}

	public ArrayList getControlDocuByEstadoDoc(ControlDocuVO controlDocu) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		LectorPropiedades lp = new LectorPropiedades();
		int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
		controlDocu.setPaginacion(cantidad);
		return (ArrayList) sqlMap.queryForList("getControlDocuByEstadoDoc", controlDocu);
	}

	public ArrayList getControlDocuByNumDoc(ControlDocuVO controlDocu) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		return (ArrayList) sqlMap.queryForList("getControlDocuByNumDoc", controlDocu);
	}

	public ArrayList BuscarListaAvanzarEstadoDoc_SMF05(ControlDocuVO controlDocu) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		//ArrayList listaControlDocu = new ArrayList();

		LectorPropiedades lp = new LectorPropiedades();
		int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
		controlDocu.setPaginacion(cantidad);
		return (ArrayList) sqlMap.queryForList("BuscarListaAvanzarEstadoDoc_SMF05", controlDocu);

	}

	public ArrayList BuscarListaRetrocederEstadoDoc_SMF05(ControlDocuVO controlDocu) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		//ArrayList listaControlDocu = new ArrayList();

		LectorPropiedades lp = new LectorPropiedades();
		int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
		controlDocu.setPaginacion(cantidad);
		return (ArrayList) sqlMap.queryForList("BuscarListaRetrocederEstadoDoc_SMF05", controlDocu);

	}

}
