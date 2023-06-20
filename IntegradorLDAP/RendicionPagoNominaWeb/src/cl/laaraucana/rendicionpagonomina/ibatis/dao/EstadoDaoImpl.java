package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.List;


import org.apache.log4j.Logger;

import cl.laaraucana.rendicionpagonomina.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ConvenioEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.EstadoEntity;

import com.ibatis.sqlmap.client.SqlMapClient;



public class EstadoDaoImpl implements EstadoDao {

	private static final Logger logger = Logger.getLogger(EstadoDaoImpl.class);


	@Override
	public List<EstadoEntity> consultaEstadosNomina() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<EstadoEntity> queryForList = (List<EstadoEntity>)sqlMap.queryForList("transferencia.consultaEstadosNomina", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}


	@Override
	public List<EstadoEntity> consultaEstadosActivos() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<EstadoEntity> queryForList = (List<EstadoEntity>)sqlMap.queryForList("transferencia.consultaEstadosActivos", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<EstadoEntity> consultaEstadosPago() throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<EstadoEntity> queryForList = (List<EstadoEntity>)sqlMap.queryForList("transferencia.consultaEstadosPago", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

}
