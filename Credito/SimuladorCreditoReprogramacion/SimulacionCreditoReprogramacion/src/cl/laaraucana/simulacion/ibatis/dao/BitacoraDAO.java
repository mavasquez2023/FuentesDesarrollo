package cl.laaraucana.simulacion.ibatis.dao;


import cl.laaraucana.simulacion.VO.BitacoraVo;
import cl.laaraucana.simulacion.ibatis.config.MyClassSqlConfig;

import com.ibatis.sqlmap.client.SqlMapClient;


public class BitacoraDAO {


	
	
	public static boolean insertaBitacoraReprogramacion(BitacoraVo data) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			sqlMap.insert("insertBitacoraReprogramacion", data);		 

		} catch (Exception e) {
			throw new Exception("Error al realizar insert bitacora, mensaje:" + e.getMessage());
		}
		
		return true;
	}
	
	public static boolean insertaBitacoraAcuerdo(BitacoraVo data) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			sqlMap.insert("insertBitacoraAcuerdo", data);		 

		} catch (Exception e) {
			throw new Exception("Error al realizar insert bitacora");
		}
		
		return true;
	}
	
}
