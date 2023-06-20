package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.List;


import org.apache.log4j.Logger;

import cl.laaraucana.rendicionpagonomina.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BancoEntity;
import com.ibatis.sqlmap.client.SqlMapClient;



public class BancoDaoImpl implements BancoDao {

	private static final Logger logger = Logger.getLogger(BancoDaoImpl.class);


	@Override
	public List<String> consultaBancosConvenio() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<String> queryForList = (List<String>)sqlMap.queryForList("transferencia.consultaBancosConvenio", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<BancoEntity> getBancos() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<BancoEntity> queryForList = (List<BancoEntity>)sqlMap.queryForList("transferencia.getBancos", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
}
