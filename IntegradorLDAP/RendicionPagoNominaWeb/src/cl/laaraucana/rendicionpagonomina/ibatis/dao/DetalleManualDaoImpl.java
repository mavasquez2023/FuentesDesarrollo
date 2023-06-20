package cl.laaraucana.rendicionpagonomina.ibatis.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.laaraucana.rendicionpagonomina.entities.DetalleManualEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.rendicionpagonomina.ibatis.vo.ArchivoManualVO;

import com.ibatis.sqlmap.client.SqlMapClient;



public class DetalleManualDaoImpl implements DetalleManualDao {

	private static final Logger logger = Logger.getLogger(DetalleManualDaoImpl.class);


	@Override
	public int updatePendientes(ArchivoManualVO data) throws Exception {
		SqlMapClient sqlMap = null;
		int resultado=0;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			resultado= sqlMap.update("transferencia.updatePendientes", data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return resultado;
	}
	
	@Override
	public int updateMandatoDetalle(DetalleManualEntity detalle) throws Exception {
		SqlMapClient sqlMap = null;
		int resultado=0;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			resultado= sqlMap.update("transferencia.updateDetalleManual", detalle);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return resultado;
	}

	@Override
	public List<DetalleManualEntity> getDetallesxConvenioProducto(
			String convenio, String producto) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			Map<String, String> params= new HashMap<String, String>();
			params.put("convenio", convenio);
			params.put("producto", producto);
			List<DetalleManualEntity> queryForList = (List<DetalleManualEntity>)sqlMap.queryForList("transferencia.detallesxconvenio_producto", params);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<DetalleManualEntity> findByIdCabecera(long idCabecera) throws Exception{
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			List<DetalleManualEntity> queryForList = (List<DetalleManualEntity>)sqlMap.queryForList("transferencia.detallesManualByIdCabecera", idCabecera);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public DetalleManualEntity findByIdDetalle(long idDetalle) throws Exception{
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			DetalleManualEntity queryForObject = (DetalleManualEntity)sqlMap.queryForObject("transferencia.detalleManualByIdDetalle", idDetalle);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public DetalleManualEntity findByRut(HashMap<String, Long> params) throws Exception {
		// TODO Auto-generated method stub
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			DetalleManualEntity queryForObject = (DetalleManualEntity)sqlMap.queryForObject("transferencia.detalleManualByRut", params);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int rollbackTransferencia(ArchivoManualVO data) throws Exception {
		SqlMapClient sqlMap = null;
		int resultado=0;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			resultado= sqlMap.update("transferencia.rollbackTransferencia", data);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return resultado;
	}

	@Override
	public int updateEstadoDetalleManual(HashMap params)
			throws Exception {
		SqlMapClient sqlMap = null;
		int resultado=0;
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			resultado= sqlMap.update("transferencia.updateEstadoDetalleManual", params);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return resultado;
	}

	@Override
	public Long getIdCabeceraByIdDetalle(long idDetalle) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			Long queryForObject = (Long)sqlMap.queryForObject("transferencia.idCabeceraManualByIdDetalle", idDetalle);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public boolean existenRegistrosPendientes(long idCabecera) throws Exception {
		
		
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			Integer cantidadRegistros = ( Integer )sqlMap.queryForObject("transferencia.getCantidadRegistroManualEnEstadoPago3", idCabecera);
			if(cantidadRegistros != null && cantidadRegistros.intValue()>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return false;
	}
}
