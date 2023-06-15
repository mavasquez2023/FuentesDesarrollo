package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;

import cl.laaraucana.simat.dao.DocsRevalReemDAO;
import cl.laaraucana.simat.entidades.DocsRevalReemVO;
import cl.laaraucana.simat.utiles.LectorPropiedades;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2DocsRevalReemDAO implements DocsRevalReemDAO {

	public void Actualizar(DocsRevalReemVO docsRevalReem) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		System.out.println("OBS:DAO UP, " + docsRevalReem.getMonto_documento_original());
		sqlMap.update("updateDocsRevalReemIbatis", docsRevalReem);
	}

	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		return (ArrayList) sqlMap.queryForList("getDocsRevalReemIbatisByMes", mes_informacion);

	}

	public void Eliminar(DocsRevalReemVO docsRevalReem) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		sqlMap.delete("delDocsRevalReemIbatis", docsRevalReem);
	}

	public void Insertar(DocsRevalReemVO docsRevalReem) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		sqlMap.insert("insertarDocsRevalReem", docsRevalReem);
	}

	public DocsRevalReemVO BuscarById(DocsRevalReemVO docsRevalReem) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		docsRevalReem = (DocsRevalReemVO) sqlMap.queryForObject("getUnoDocsRevalReemIbatis", docsRevalReem);

		return docsRevalReem;
	}

	public ArrayList BuscarTodo() throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			DocsRevalReemVO docsRevalReem = new DocsRevalReemVO();
			ArrayList listaDocsRevalReem = new ArrayList();
			listaDocsRevalReem = (ArrayList) sqlMap.queryForList("getTodoDocsRevalReem", docsRevalReem);

			return listaDocsRevalReem;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception {

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		DocsRevalReemVO docsRevalReem = new DocsRevalReemVO();
		ArrayList listaDocsRevalReem = new ArrayList();
		try {

			docsRevalReem.setKeyFin(keyFin);
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			docsRevalReem.setPaginacion(cantidad);
			listaDocsRevalReem = (ArrayList) sqlMap.queryForList("getList_avanzar_SMF06", docsRevalReem);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDocsRevalReem;

	}

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception {

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		DocsRevalReemVO docsRevalReem = new DocsRevalReemVO();
		ArrayList listaDocsRevalReem = new ArrayList();
		try {

			docsRevalReem.setKeyInicio(keyInicio);
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			docsRevalReem.setPaginacion(cantidad);
			listaDocsRevalReem = (ArrayList) sqlMap.queryForList("getList_retroceder_SMF06", docsRevalReem);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDocsRevalReem;

	}

	public ArrayList getDocsRevalReemByEstadoDoc(DocsRevalReemVO docsRevalReem) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		LectorPropiedades lp = new LectorPropiedades();
		int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
		docsRevalReem.setPaginacion(cantidad);
		return (ArrayList) sqlMap.queryForList("getDocsRevalReemByEstadoDoc", docsRevalReem);
	}

	public ArrayList getDocsRevalReemByNumDoc(DocsRevalReemVO docsRevalReem) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		return (ArrayList) sqlMap.queryForList("getDocsRevalReemByNumDoc", docsRevalReem);
	}

	public ArrayList BuscarListaAvanzarEstadoDoc(DocsRevalReemVO docsRevalReem) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		ArrayList listaDocsRevalReem = new ArrayList();
		try {
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			docsRevalReem.setPaginacion(cantidad);
			listaDocsRevalReem = (ArrayList) sqlMap.queryForList("BuscarListaAvanzarEstadoDoc", docsRevalReem);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDocsRevalReem;
	}

	public ArrayList BuscarListaRetrocederEstadoDoc(DocsRevalReemVO docsRevalReem) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		ArrayList listaDocsRevalReem = new ArrayList();
		try {
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			docsRevalReem.setPaginacion(cantidad);
			listaDocsRevalReem = (ArrayList) sqlMap.queryForList("BuscarListaRetrocederEstadoDoc", docsRevalReem);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDocsRevalReem;
	}

}
