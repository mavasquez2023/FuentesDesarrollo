/**
 * 
 */
package cl.laaraucana.ventaremota.ibatis.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import cl.laaraucana.ventaremota.ibatis.config.MyClassConfig;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @author IBM Software Factory
 *
 */
public class CodigosSinacofiDaoImpl implements CodigosSinacofiDao {
	
	private static final Logger logger = Logger.getLogger(CodigosSinacofiDaoImpl.class);
	/* (non-Javadoc)
	 * @see cl.laaraucana.ventaremota.ibatis.dao.CodigosSinacofiDao#getCodigos()
	 */
	@Override
	public List<HashMap<String, String>> getCodigos() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			List<HashMap<String, String>> query = (List<HashMap<String, String>>) sqlMap.queryForList("credito.codigosSinacofi", null);
			return query;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

}
