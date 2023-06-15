package cl.laaraucana.simulacion.ibatis.dao;

import cl.laaraucana.simulacion.ibatis.config.MyClassSqlConfig;
import com.ibatis.sqlmap.client.SqlMapClient;

public class ConsultaPreAprobadoDAO {
	
	public static long consultaPreAprobado(String rut) throws Exception {
		long monto = 0;		
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		}catch(Exception e){
			throw new Exception("Error al conectarse al datasource");
		}
		try{
			Object m = sqlMap.queryForObject("consultaMontoPreAprobado", rut);
			if(m!=null){
				monto = (Long) m;
			}
		} catch (Exception e) {
			throw new Exception("Error al consultar el monto pre aprobado");
		}
		
		return monto;
	}
	

}
