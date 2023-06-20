package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.apache.log4j.Logger;

import cl.laaraucana.rendicionpagonomina.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ArchivoManualVO;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ConvenioEntity;
import com.ibatis.sqlmap.client.SqlMapClient;



public class CommonLicenciaDaoImpl implements CommonLicenciaDao {

	private static final Logger logger = Logger.getLogger(CommonLicenciaDaoImpl.class);


	@Override
	public HashMap<String, String> getBancos() throws Exception {
		HashMap<String, String> out = new HashMap<String, String>();
		SqlMapClient sqlMap = null;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}
		try {
			ArrayList<HashMap<String, Object>> data = (ArrayList<HashMap<String, Object>>)sqlMap.queryForList("transferencia.getBancos", null);
			if(data != null && data.size()>0) {				
				for (HashMap<String, Object> item : data) {
					out.put( ((BigDecimal)item.get("id")).intValue()+"", item.get("valor").toString());
				}			
			}	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return out;
	}

	@Override
	public HashMap<String, String> getFormasPago() throws Exception {
		HashMap<String, String> out = new HashMap<String, String>();
		SqlMapClient sqlMap = null;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}
		try {
			ArrayList<HashMap<String, Object>> data = (ArrayList<HashMap<String, Object>>)sqlMap.queryForList("transferencia.getFormasPago", null);
			if(data != null && data.size()>0) {				
				for (HashMap<String, Object> item : data) {
					out.put(item.get("id").toString(), item.get("valor").toString());
				}			
			}	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return out;
	}
	
	@Override
	public HashMap<String, String> getDescripcionRechazo() throws Exception {
		HashMap<String, String> out = new HashMap<String, String>();
		SqlMapClient sqlMap = null;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}
		try {
			ArrayList<HashMap<String, Object>> data = (ArrayList<HashMap<String, Object>>)sqlMap.queryForList("transferencia.getDescripcionRechazo", null);
			if(data != null && data.size()>0) {				
				for (HashMap<String, Object> item : data) {
					out.put(item.get("id").toString(), item.get("valor").toString());
				}			
			}	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return out;
	}
}
