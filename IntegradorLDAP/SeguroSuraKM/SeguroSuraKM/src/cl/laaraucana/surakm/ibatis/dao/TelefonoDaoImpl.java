package cl.laaraucana.surakm.ibatis.dao;

import java.util.List;


import org.apache.log4j.Logger;

import cl.laaraucana.surakm.ibatis.config.MyClassSqlConfig;

import com.ibatis.sqlmap.client.SqlMapClient;


public class TelefonoDaoImpl implements TelefonoDao {

	private static final Logger logger = Logger.getLogger(TelefonoDaoImpl.class);


	@SuppressWarnings("unchecked")
	@Override
	public List<String> getPrefijoTelefono(int tipo) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<String> queryForList = sqlMap.queryForList("formweb.PrefijoTelefono", tipo);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}


}
