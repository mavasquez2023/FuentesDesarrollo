package cl.laaraucana.simulacion.ibatis.dao;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.simulacion.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.simulacion.ibatis.vo.CreditoSolicitudVO;


public class CreditoSolicitudDAO {
	
	public void saveCreditosSolicitados(CreditoSolicitudVO credito) throws Exception {
		
		
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		}catch(Exception e){
			throw new Exception("Error al conectarse al datasource");
		}
		try{
		     sqlMap.insert("creditosolicitudQuery", credito);
			 
		} catch (Exception e) {
			throw new Exception("Error al insertar el crédito" + e.getMessage());
		}
		
	 
	}

}
