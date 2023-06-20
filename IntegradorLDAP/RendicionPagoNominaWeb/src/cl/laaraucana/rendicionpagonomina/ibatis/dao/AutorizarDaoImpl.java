/**
 * 
 */
package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import cl.laaraucana.rendicionpagonomina.ibatis.config.MyClassSqlConfig;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @author IBM Software Factory
 *
 */
public class AutorizarDaoImpl implements AutorizarDao {
	
	private static final Logger logger = Logger.getLogger(AutorizarDaoImpl.class);
	
	/* (non-Javadoc)
	 * @see cl.laaraucana.rendicionpagonomina.ibatis.dao.AutorizarDao#getUsuariosConvenio(int)
	 */
	@Override
	public List<String> getUsuariosConvenio(int convenio) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<String> queryForList = (List<String>)sqlMap.queryForList("transferencia.usuariosxConvenio", convenio);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public void deleteUsuario(HashMap<String, String> params) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		sqlMap.delete("transferencia.deleteUsuarioConvenio", params);
	
	}
	
	@Override
	public void insertUsuario(HashMap<String, String> params) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		sqlMap.insert("transferencia.insertUsuarioConvenio", params);
		
	}
	
}
