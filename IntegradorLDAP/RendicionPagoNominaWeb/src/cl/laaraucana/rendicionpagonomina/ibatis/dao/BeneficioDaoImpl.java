package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.HashMap;
import java.util.List;


import org.apache.log4j.Logger;

import cl.laaraucana.rendicionpagonomina.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ArchivoManualVO;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BeneficiarioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.BeneficioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ConvenioEntity;
import com.ibatis.sqlmap.client.SqlMapClient;



public class BeneficioDaoImpl implements BeneficioDao {

	private static final Logger logger = Logger.getLogger(BeneficioDaoImpl.class);


	@Override
	public List<BeneficioEntity> consultaBeneficios() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<BeneficioEntity> queryForList = (List<BeneficioEntity>)sqlMap.queryForList("transferencia.consultaBeneficios", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}


	@Override
	public List<BeneficioEntity> consultaBeneficiosByParams(
			HashMap<String, String> params) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<BeneficioEntity> queryForList = (List<BeneficioEntity>)sqlMap.queryForList("transferencia.consultaBeneficiosByParams", params);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<BeneficiarioEntity> consultaBeneficiariosBES() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<BeneficiarioEntity> queryForList = (List<BeneficiarioEntity>)sqlMap.queryForList("mandatos.consultaBeneficiariosBES", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<BeneficiarioEntity> consultaBeneficiariosBCI() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<BeneficiarioEntity> queryForList = (List<BeneficiarioEntity>)sqlMap.queryForList("mandatos.consultaBeneficiariosBCI", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}


	@Override
	public int updateBeneficiario(HashMap<String, String> params)
			throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		return sqlMap.update("mandatos.updateBeneficiario", params);
	}


	@Override
	public int rollbackBeneficiarios(Integer idCabecera)
			throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		return sqlMap.update("mandatos.rollbackBeneficiarios", idCabecera);
	}


	@Override
	public int updateBeneficiarioById(HashMap<String, String> params)
			throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		return sqlMap.update("mandatos.updateBeneficiarioById", params);
	}
	
	@Override
	public int updateBeneficiarioRendicion(HashMap<String, String> params)
			throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		return sqlMap.update("mandatos.updateBeneficiarioRendicion", params);
	}

}
