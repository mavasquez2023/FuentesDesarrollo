/**
 * 
 */
package cl.laaraucana.mandato.ibatis.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.mandato.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.mandato.ibatis.vo.RechazoVo;

import com.ibatis.sqlmap.client.SqlMapClient;


/**
 * @author IBM Software Factory
 *
 */
public class RechazoDaoImpl implements RechazoDao {
		private static final Logger logger = Logger.getLogger(RechazoDaoImpl.class);
	
	/* (non-Javadoc)
	 * @see cl.laaraucana.transferencias.ibatis.dao.RechazoDao#consultaRechazos()
	 */
	@Override
	public List<RechazoVo> consultaRechazos() throws Exception {
		
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<RechazoVo> queryForList = sqlMap.queryForList("mandatos.consultaRechazos", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see cl.laaraucana.transferencias.ibatis.dao.RechazoDao#findRechazoByRut(int)
	 */
	@Override
	public void updateRechazoByRut(HashMap<String, Integer> sets) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			sqlMap.update("mandatos.updateRechazoByRut", sets);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
	@Override
	public void insertRechazo(RechazoVo rechazo) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			sqlMap.insert("mandatos.insertRechazo", rechazo);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
}
