package cl.laaraucana.licenciascompin.ibatis.dao;


import org.apache.log4j.Logger;

import cl.laaraucana.licenciascompin.ibatis.config.MyClassSqlConfig;

import com.ibatis.sqlmap.client.SqlMapClient;


public class ConsultaMandatoDAO {
	protected static Logger logger = Logger.getLogger("ConsultaMandatoDAO");
	
	public boolean existMandato(int rut) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			@SuppressWarnings("unchecked")
			Integer count = (Integer)sqlMap.queryForObject("mandato.countMandato", rut);
			if( count>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return false;
	}
	

}
