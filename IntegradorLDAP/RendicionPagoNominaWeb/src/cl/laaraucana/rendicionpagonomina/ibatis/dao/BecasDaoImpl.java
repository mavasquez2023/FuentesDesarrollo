package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.HashMap;
import java.util.List;


import org.apache.log4j.Logger;

import cl.laaraucana.rendicionpagonomina.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BecasEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BeneficiarioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BeneficioEntity;
import com.ibatis.sqlmap.client.SqlMapClient;



public class BecasDaoImpl implements BecasDao {

	private static final Logger logger = Logger.getLogger(BecasDaoImpl.class);
	
	@Override
	public List<BecasEntity> consultaBecadosBES() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			@SuppressWarnings("unchecked")
			List<BecasEntity> queryForList = (List<BecasEntity>)sqlMap.queryForList("transferencia.consultaBecadosBES", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	
	@Override
	public int updateBecado(HashMap<String, String> params)
			throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		return sqlMap.update("transferencia.updateBecadoById", params);
	}
	
	@Override
	public int updateNominaBecados(HashMap<String, String> params)
			throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		return sqlMap.update("transferencia.updateBecadosByIdCab", params);
	}

	@Override
	public int rollbackBecados(Integer idCabecera)
			throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		return sqlMap.update("transferencia.rollbackBecados", idCabecera);
	}

	
	@Override
	public int updateBecadoRendicion(HashMap<String, String> params)
			throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		return sqlMap.update("transferencia.updateBecadoRendicion", params);
	}

}
