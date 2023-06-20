package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.HashMap;
import java.util.List;


import org.apache.log4j.Logger;

import cl.laaraucana.rendicionpagonomina.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ArchivoManualVO;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ConvenioEntity;
import com.ibatis.sqlmap.client.SqlMapClient;



public class ConvenioDaoImpl implements ConvenioDao {

	private static final Logger logger = Logger.getLogger(ConvenioDaoImpl.class);


	@Override
	public List<ConvenioEntity> consultaConvenios() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<ConvenioEntity> queryForList = (List<ConvenioEntity>)sqlMap.queryForList("transferencia.consultaConvenios", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<ConvenioEntity> consultaConveniosActivosManual(HashMap<String, String> param) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<ConvenioEntity> queryForList = (List<ConvenioEntity>)sqlMap.queryForList("transferencia.consultaConveniosActivosManual", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<ConvenioEntity> consultaConveniosActivos(HashMap<String, String> param) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<ConvenioEntity> queryForList = (List<ConvenioEntity>)sqlMap.queryForList("transferencia.consultaConveniosActivos", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<ConvenioEntity> consultaConveniosconPlantilla(HashMap<String, String> param) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<ConvenioEntity> queryForList = (List<ConvenioEntity>)sqlMap.queryForList("transferencia.consultaConveniosconPlantilla", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public ConvenioEntity getConvenio(int codigo) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			ConvenioEntity queryObject = (ConvenioEntity)sqlMap.queryForObject("transferencia.findConvenio", codigo);
			return queryObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}


	@Override
	public List<ArchivoManualVO> getConvenioTransferencia() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<ArchivoManualVO> queryForList = (List<ArchivoManualVO>)sqlMap.queryForList("transferencia.archivosxconvenio", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	

}
