package cl.laaraucana.rendicionpagonomina.ibatis.dao;

import java.util.HashMap;
import java.util.List;


import org.apache.log4j.Logger;

import cl.laaraucana.rendicionpagonomina.entities.CabeceraManualEntity;
import cl.laaraucana.rendicionpagonomina.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.rendicionpagonomina.vo.NominaManualVo;

import com.ibatis.sqlmap.client.SqlMapClient;



public class CabeceraManualDaoImpl implements CabeceraManualDao {

	private static final Logger logger = Logger.getLogger(CabeceraManualDaoImpl.class);


	@Override
	public List<CabeceraManualEntity> findManualByParams(NominaManualVo params) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<CabeceraManualEntity> queryForList = (List<CabeceraManualEntity>)sqlMap.queryForList("transferencia.findManualByParams", params);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public CabeceraManualEntity findById(long idCabecera) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			CabeceraManualEntity queryForObject = (CabeceraManualEntity)sqlMap.queryForObject("transferencia.findManualByIdCabecera", idCabecera);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public int updateMontoPendiente(long idCabecera) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		return sqlMap.update("transferencia.updateMontoPendiente", idCabecera);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getSumTotalesPorEstadoPago(Integer idCabecera, Integer estadoPago) throws Exception {
		
		
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {
			HashMap<String, Object> cabecera = null;
			if(estadoPago==1){
				cabecera = (HashMap<String, Object>)sqlMap.queryForObject("transferencia.getSumTotalesPagadosDetalleManual", idCabecera);
			}else if(estadoPago==3){
				cabecera = (HashMap<String, Object>)sqlMap.queryForObject("transferencia.getSumTotalesPendientesDetalleManual", idCabecera);
			}
			return cabecera;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public boolean actualizarTotalesRendicion(CabeceraManualEntity cabecera)
			throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstanceSql();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		int result = sqlMap.update("transferencia.updateCabeceraManualTEFRendicionTotales", cabecera);
		if(result > 0)
			return true;
		
		return false;
	}
}
