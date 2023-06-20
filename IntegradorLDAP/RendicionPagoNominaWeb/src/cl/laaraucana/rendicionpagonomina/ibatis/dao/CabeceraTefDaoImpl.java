package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


import org.apache.log4j.Logger;

import cl.laaraucana.rendicionpagonomina.entities.CabeceraEntity;
import cl.laaraucana.rendicionpagonomina.entities.DetalleEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.config.MyClassSqlConfig;

import com.ibatis.sqlmap.client.SqlMapClient;



public class CabeceraTefDaoImpl implements CabeceraTefDao {

	private static final Logger logger = Logger.getLogger(CabeceraTefDaoImpl.class);


	@Override
	public List<CabeceraEntity> findNominasSeguimiento(HashMap<String, String> params) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			@SuppressWarnings("unchecked")
			List<CabeceraEntity> queryForList = (List<CabeceraEntity>)sqlMap.queryForList("transferencia.seguimientoNominas", params);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public CabeceraEntity findById(long idCabecera) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			HashMap<String, Long> param= new HashMap<String, Long>();
			param.put("idCabecera", idCabecera);
			CabeceraEntity queryForObject = (CabeceraEntity)sqlMap.queryForObject("transferencia.findNominaByIdCodigo", param);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public CabeceraEntity findByCodigoNomina(long codigoNomina) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			HashMap<String, Long> param= new HashMap<String, Long>();
			param.put("codigoNomina", codigoNomina);
			CabeceraEntity queryForObject = (CabeceraEntity)sqlMap.queryForObject("transferencia.findNominaByIdCodigo", param);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<CabeceraEntity> findNominasRendicion(
			HashMap<String, String> params) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			@SuppressWarnings("unchecked")
			List<CabeceraEntity> queryForList = (List<CabeceraEntity>)sqlMap.queryForList("transferencia.findNominasRendicion", params);
			return queryForList;
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

		sqlMap.delete("transferencia.deleteCabeceraByCodigoNomina", codigoNomina);
	
	}

	@Override
	public int updateNominaTEF(CabeceraEntity cabeceraTEF) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		return sqlMap.update("transferencia.updateCabeceraTEF", cabeceraTEF);
		
	}

	@Override
	public int validaCRC(HashMap<String, String> params) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			Integer queryForObject = (Integer)sqlMap.queryForObject("transferencia.validaCRC", params);
			if(queryForObject!=null && queryForObject>0 ){
				return queryForObject;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public CabeceraEntity insert(CabeceraEntity cabecera) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		sqlMap.insert("transferencia.insertLicenciaFile", cabecera);
		
		return cabecera;
		
	}
	
	@Override
	public HashMap<String, Object> getIdCabeceraPorNombreArchivo(String nombreArchivo ) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			@SuppressWarnings("unchecked")
			HashMap<String, Object> cabecera = (HashMap<String, Object>)sqlMap.queryForObject("transferencia.getIdCabeceraPorNombreArchivo", nombreArchivo);
			return cabecera;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public HashMap<String, Object> getSumTotalesPorEstadoPago(Long idCabecera, Integer estadoPago) throws Exception {
		
		
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("ID_CABECERA", idCabecera);
			params.put("ESTADO_PAGO", estadoPago);
			HashMap<String, Object> cabecera = (HashMap<String, Object>)sqlMap.queryForObject("transferencia.getSumTotalesDetallePorEstado", params);
			return cabecera;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public boolean existenRegistrosEnEstado3(Long idCabecera) throws Exception {
		
		
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			Integer cantidadRegistros = ( Integer )sqlMap.queryForObject("transferencia.getCantidadRegistroEnEstadoPago3", idCabecera);
			if(cantidadRegistros != null && cantidadRegistros.intValue()>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return false;
	}
	

	@Override
	public boolean actualizarTotalesRendicion(CabeceraEntity cabecera) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		int result = sqlMap.update("transferencia.updateCabeceraTEFRendicionTotales", cabecera);
		if(result > 0)
			return true;
		
		return false;
	}
	
	@Override
	public int rollbackNominaTEF(long condigoNomina) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		return sqlMap.update("transferencia.rollbackCabeceraTEF", condigoNomina);
		
	}

	
}