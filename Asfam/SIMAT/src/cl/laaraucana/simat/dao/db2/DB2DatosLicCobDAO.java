package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;

import cl.laaraucana.simat.dao.DatosLicCobDAO;
import cl.laaraucana.simat.entidades.DatosLicCobVO;
import cl.laaraucana.simat.utiles.LectorPropiedades;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2DatosLicCobDAO implements DatosLicCobDAO {

	public void Actualizar(DatosLicCobVO datosLicCob) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		System.out.println("act mysql");
		sqlMap.update("actualizarDatosLicCob", datosLicCob);
	}

	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			ArrayList listaDatosLicCob = new ArrayList();
			listaDatosLicCob = (ArrayList) sqlMap.queryForList("getDatosLicCobIbatisByMes", mes_informacion);

			return listaDatosLicCob;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void Eliminar(DatosLicCobVO datosLicCob) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		sqlMap.delete("eliminarDatosLicCob", datosLicCob);
	}

	public void Insertar(DatosLicCobVO datosLicCob) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		System.out.println("Apunto de insertarX");
		System.out.println(datosLicCob.getCod_comuna_beneficiario());

		sqlMap.insert("insertarDatosLicCob", datosLicCob);
	}

	public DatosLicCobVO BuscarById(DatosLicCobVO datosLicCob) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			datosLicCob = (DatosLicCobVO) sqlMap.queryForObject("getUnoDatosLicCobIbatis", datosLicCob);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datosLicCob;
	}

	public ArrayList BuscarTodo() throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			DatosLicCobVO datosLicCob = new DatosLicCobVO();
			ArrayList listaDatosLicCob = new ArrayList();
			listaDatosLicCob = (ArrayList) sqlMap.queryForList("getTodoDatosLicCob", datosLicCob);

			return listaDatosLicCob;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception {

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		DatosLicCobVO datosLicCob = new DatosLicCobVO();
		ArrayList listaDatosLicCob = new ArrayList();
		try {

			datosLicCob.setKeyFin(keyFin);
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			datosLicCob.setPaginacion(cantidad);
			listaDatosLicCob = (ArrayList) sqlMap.queryForList("getList_avanzar_SMF07", datosLicCob);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDatosLicCob;

	}

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception {

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		DatosLicCobVO datosLicCob = new DatosLicCobVO();
		ArrayList listaDatosLicCob = new ArrayList();
		try {

			datosLicCob.setKeyInicio(keyInicio);
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			datosLicCob.setPaginacion(cantidad);
			listaDatosLicCob = (ArrayList) sqlMap.queryForList("getList_retroceder_SMF07", datosLicCob);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDatosLicCob;
	}

	public ArrayList getDatosLicCobByRutBenef(DatosLicCobVO datosLicCob) throws Exception {
		//		 TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		return (ArrayList) sqlMap.queryForList("getDatosLicCobByRutBenef", datosLicCob);
	}

}
