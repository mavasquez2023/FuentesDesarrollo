package cl.laaraucana.transferencias.ibatis.dao;

import java.util.List;


import org.apache.log4j.Logger;
import com.ibatis.sqlmap.client.SqlMapClient;
import cl.laaraucana.transferencias.ibatis.config.MyClassSqlConfig;


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

			List<String> queryForList = sqlMap.queryForList("cuentas.PrefijoTelefono", tipo);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}


}
