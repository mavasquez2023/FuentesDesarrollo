package cl.laaraucana.rendicionpagonomina.ibatis.dao;


import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.rendicionpagonomina.entities.DetalleEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.config.MyClassSqlConfig;

import com.ibatis.sqlmap.client.SqlMapClient;



public class DetalleTefDaoImpl implements DetalleTefDao {

	private static final Logger logger = Logger.getLogger(DetalleTefDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<DetalleEntity> findByIdCabecera(long idCabecera) throws Exception{
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			List<DetalleEntity> queryForList = (List<DetalleEntity>)sqlMap.queryForList("transferencia.detallesByIdCabecera", idCabecera);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public DetalleEntity findByIdDetalle(long idDetalle) throws Exception{
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			DetalleEntity queryForObject = (DetalleEntity)sqlMap.queryForObject("transferencia.detalleByIdDetalle", idDetalle);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public DetalleEntity findByRut(HashMap<String, Long> params) throws Exception {
		// TODO Auto-generated method stub
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			DetalleEntity queryForObject = (DetalleEntity)sqlMap.queryForObject("transferencia.detalleByRut", params);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public void deleteByCodigoNomina(long codigoNomina) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		sqlMap.delete("transferencia.deleteDetalleByCodigoNomina", codigoNomina);
	
	}

	@Override
	public List<DetalleEntity> findByEstadoPago(HashMap<String, Long> params)
			throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			List<DetalleEntity> queryForList = (List<DetalleEntity>)sqlMap.queryForList("transferencia.detallesByEstado", params);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<DetalleEntity> seguimientoAfiliado(
			HashMap<String, String> params) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			List<DetalleEntity> queryForList = (List<DetalleEntity>)sqlMap.queryForList("transferencia.seguimientoAfiliado", params);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}


	@Override
	public Long getIdCabeceraPorDetalle(HashMap<String, Long> params) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			Long idCabecera = (Long)sqlMap.queryForObject("transferencia.getIdCabeceraPorDetalle", params);
			return idCabecera;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public void updateDetallePendientedePago(HashMap<String, Long> params) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		sqlMap.update("transferencia.updateDetallePendientedePago", params);
		
	}

}
