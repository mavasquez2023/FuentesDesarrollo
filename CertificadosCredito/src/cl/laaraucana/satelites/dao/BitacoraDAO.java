package cl.laaraucana.satelites.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.satelites.dao.VO.BitacoraVO;
import cl.laaraucana.satelites.ibatis.MyClassSqlConfig;

public class BitacoraDAO {


	public static List<BitacoraVO> consultaBitacora(String rut) throws Exception {
		SqlMapClient sqlMap = null;
		List<BitacoraVO> bitacora = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			bitacora =  (List<BitacoraVO>) sqlMap.queryForList("consultaBitacora", rut);		 

		} catch (Exception e) {
			throw new Exception("Error al realizar la consulta bitacora");
		}
		
		return bitacora;
	}
	
	public static boolean insertaBitacora(BitacoraVO data) throws Exception {
		SqlMapClient sqlMap = null;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			sqlMap.insert("insertBitacora", data);		 

		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
}
