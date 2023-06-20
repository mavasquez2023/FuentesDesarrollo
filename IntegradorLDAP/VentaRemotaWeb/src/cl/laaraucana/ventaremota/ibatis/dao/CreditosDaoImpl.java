/**
 * 
 */
package cl.laaraucana.ventaremota.ibatis.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.ventaremota.entities.CreditoEntiti;
import cl.laaraucana.ventaremota.ibatis.config.MyClassConfig;
import cl.laaraucana.ventaremota.ibatis.vo.AutenticacionVO;
import cl.laaraucana.ventaremota.ibatis.vo.PreguntaVO;

/**
 * @author IBM Software Factory
 *
 */
public class CreditosDaoImpl implements CreditosDao {
	
	private static final Logger logger = Logger.getLogger(CreditosDaoImpl.class);
	
	/* (non-Javadoc)
	 * @see cl.laaraucana.ventaremota.ibatis.dao.CreditosDao#insertCredito(cl.laaraucana.ventaremota.entities.CreditoEntiti)
	 */
	@Override
	public void insertCredito(CreditoEntiti credito) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource", e);
		}

		try {
			sqlMap.delete("credito.deleteByOferta", credito.getNumeroOferta());
			sqlMap.insert("credito.insertCreditos", credito);

		} catch (Exception e) {

			logger.error("Error ws ", e);

			throw new Exception("Error credito ", e);
		}

	}

	@Override
	public List<AutenticacionVO> getAutenticacionHabilitada() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			@SuppressWarnings("unchecked")
			List<AutenticacionVO> queryForList = (List<AutenticacionVO>)sqlMap.queryForList("credito.getAutenticaciones", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

}
