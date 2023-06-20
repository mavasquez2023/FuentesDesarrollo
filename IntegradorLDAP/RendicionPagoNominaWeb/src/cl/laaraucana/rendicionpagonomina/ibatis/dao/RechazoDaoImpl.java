package cl.laaraucana.rendicionpagonomina.ibatis.dao;


import java.util.HashMap;

import org.apache.log4j.Logger;
import cl.laaraucana.rendicionpagonomina.ibatis.config.MyClassSqlConfig;

import com.ibatis.sqlmap.client.SqlMapClient;



public class RechazoDaoImpl implements RechazoDao {

	private static final Logger logger = Logger.getLogger(RechazoDaoImpl.class);


	@Override
	public HashMap<String, String> consultaRechazoBCI_PNOL(String codigoRechazo) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			HashMap<String, String> queryForObject = (HashMap<String, String>)sqlMap.queryForObject("transferencia.consultaRechazoBCI_PNOL", codigoRechazo);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public HashMap<String, String> consultaRechazoBCI_24HRS(String codigoRechazo) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			logger.info("consultando por códigoRechazo: "+ codigoRechazo +".");
			HashMap<String, String> queryForObject = (HashMap<String, String>)sqlMap.queryForObject("transferencia.consultaRechazoBCI_24HRS", codigoRechazo);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public HashMap<String, String> consultaRechazoBES(String codigoRechazo) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			HashMap<String, String> queryForObject = (HashMap<String, String>)sqlMap.queryForObject("transferencia.consultaRechazoBES", codigoRechazo);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

}
