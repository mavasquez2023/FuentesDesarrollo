package cl.laaraucana.satelites.dao;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.satelites.dao.VO.OficMigradVO;
import cl.laaraucana.satelites.dao.VO.RutMigradosVO;
import cl.laaraucana.satelites.ibatis.MyClassSqlConfig;

public class ConsultaMigracionDAO {

	public static boolean consultaOficinaMigrada(long codigo) {
		boolean migrada = false;
		OficMigradVO oficina = null;

		try {
			SqlMapClient sqlMap = MyClassSqlConfig.getSqlMapInstance();
			oficina = (OficMigradVO) sqlMap.queryForObject(
					"consultaOficinaMigrada", codigo);
			if (oficina != null) {
				migrada = true;
			}
		} catch (Exception e) {
		}

		return migrada;
	}

	/*public static boolean consultaRutMigrado(long rut) {
		boolean migrado = false;
		RutMigradosVO rutMigrado = null;

		try {
			SqlMapClient sqlMap = MyClassSqlConfig.getSqlMapInstance();
			rutMigrado = (RutMigradosVO) sqlMap.queryForObject(
					"consultaRutMigrado", rut);
			if (rutMigrado != null) {
				migrado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return migrado;
	}*/

}
