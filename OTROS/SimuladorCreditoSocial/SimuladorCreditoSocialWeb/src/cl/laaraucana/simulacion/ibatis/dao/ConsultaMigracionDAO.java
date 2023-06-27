package cl.laaraucana.simulacion.ibatis.dao;

import cl.laaraucana.simulacion.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.simulacion.ibatis.vo.OficMigradVO;
import cl.laaraucana.simulacion.ibatis.vo.RutMigradosVO;

import com.ibatis.sqlmap.client.SqlMapClient;


public class ConsultaMigracionDAO {

	public static boolean consultaOficinaMigrada(String codigo) throws Exception {
		boolean migrada = false;
		OficMigradVO oficina = null;
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		}catch(Exception e){
			throw new Exception("Error al conectarse al datasource");
		}
		try{
			oficina = (OficMigradVO) sqlMap.queryForObject("consultaOficinaMigrada", codigo);
			if (oficina != null) {
				migrada = true;
			}
		} catch (Exception e) {
			throw new Exception("Error al consultar el estado de migración de oficina");
		}
		
		return migrada;
	}

	public static boolean consultaRutMigrado(String rut) throws Exception {
		boolean migrado = false;
		Integer rutMigrado;
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error al conectarse al datasource");
		}
		try{
			rutMigrado = (Integer) sqlMap.queryForObject("consultaRutMigrado", rut);
			if (rutMigrado != 0) {
				migrado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al consultar el estado de migración de BP");
		}

		return migrado;
	}

}
