package cl.laaraucana.simat.dao.db2;

import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;

import cl.laaraucana.simat.dao.DatosLicResolDAO;
import cl.laaraucana.simat.entidades.DatosLicResolVO;
import cl.laaraucana.simat.utiles.LectorPropiedades;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class DB2DatosLicResolDAO implements DatosLicResolDAO {

	public void Actualizar(DatosLicResolVO datosLicResol) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		System.out.println("act mysql");

		sqlMap.update("actualizarDatosLicResolb", datosLicResol);
	}

	public ArrayList BuscarByMesInf(String mes_informacion) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			ArrayList listaDatosResolCob = new ArrayList();
			listaDatosResolCob = (ArrayList) sqlMap.queryForList("getDatosLicResolIbatisByMes", mes_informacion);

			return listaDatosResolCob;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void Eliminar(DatosLicResolVO datosLicResol) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		sqlMap.delete("eliminarDatosLicResol", datosLicResol);
	}

	public void Insertar(DatosLicResolVO datosLicResol) throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		System.out.println("Llefo al Mysql para insertar");
		System.out.println("Mes capturado: " + datosLicResol.getMes_informacion());

		sqlMap.insert("insertarDatosLicResol", datosLicResol);
	}

	public DatosLicResolVO BuscarById(DatosLicResolVO datosLicResol) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);

		try {
			datosLicResol = (DatosLicResolVO) sqlMap.queryForObject("getUnoDatosLicResolIbatis", datosLicResol);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datosLicResol;
	}

	public ArrayList BuscarTodo() throws Exception {
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		try {
			DatosLicResolVO datosLicResol = new DatosLicResolVO();
			ArrayList listaDatosResolCob = new ArrayList();
			listaDatosResolCob = (ArrayList) sqlMap.queryForList("getTodoDatosLicResol", datosLicResol);

			return listaDatosResolCob;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList BuscarListaAvanzar(int keyFin) throws Exception {

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		ArrayList listaDatosLicResol = new ArrayList();
		DatosLicResolVO datosLicResol = new DatosLicResolVO();
		try {

			datosLicResol.setKeyFin(keyFin);
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			datosLicResol.setPaginacion(cantidad);
			listaDatosLicResol = (ArrayList) sqlMap.queryForList("getList_avanzar_SMF08", datosLicResol);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDatosLicResol;

	}

	public ArrayList BuscarListaRetroceder(int keyInicio) throws Exception {

		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		ArrayList listaDatosLicResol = new ArrayList();
		DatosLicResolVO datosLicResol = new DatosLicResolVO();
		try {

			datosLicResol.setKeyInicio(keyInicio);
			LectorPropiedades lp = new LectorPropiedades();
			int cantidad = Integer.parseInt(lp.getAtributoRepositorio("cantidadPaginacion"));
			datosLicResol.setPaginacion(cantidad);
			listaDatosLicResol = (ArrayList) sqlMap.queryForList("getList_retroceder_SMF08", datosLicResol);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDatosLicResol;

	}

	public ArrayList getDatosLicResolByRutBenef(DatosLicResolVO datosLicResol) throws Exception {
		// TODO Auto-generated method stub
		String resource = "cl/laaraucana/simat/ibatis/sql-map-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlMapClient sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
		return (ArrayList) sqlMap.queryForList("getDatosLicResolByRutBenef", datosLicResol);
	}

}
