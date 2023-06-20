/**
 * 
 */
package cl.laaraucana.ventaremota.ibatis.dao;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.ventaremota.entities.UsuarioEntiti;
import cl.laaraucana.ventaremota.ibatis.config.MyClassConfig;
import cl.laaraucana.ventaremota.ws.vo.CredencialesVO;

/**
 * @author IBM Software Factory
 *
 */
public class CredencialesDaoImpl implements CredencialesDao {
	
	private static final Logger logger = Logger.getLogger(CredencialesDaoImpl.class);
	
	/* (non-Javadoc)
	 * @see cl.laaraucana.ventaremota.ibatis.dao.CredencialesDao#consultaCredenciales(cl.laaraucana.ventaremota.ws.vo.CredencialesVO)
	 */
	@Override
	public UsuarioEntiti consultaCredenciales(CredencialesVO user)
			throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			UsuarioEntiti query = (UsuarioEntiti) sqlMap.queryForObject("cuentas.consultaCredenciales", user);
			return query;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

}
