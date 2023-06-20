package cl.laaraucana.imed.dao;

import java.util.List;

import org.apache.log4j.Logger;

import cl.laaraucana.imed.dao.vo.BitacoraVO;
import cl.laaraucana.imed.dao.vo.RegistroAltaBajaVO;
import cl.laaraucana.imed.ibatis.MyClassSqlConfig;


import com.ibatis.sqlmap.client.SqlMapClient;


public class ConsultaServicesDAO {
	protected static Logger logger = Logger.getLogger("ConsultaCargasDAO");
	
	public List<RegistroAltaBajaVO> consultaAltas() throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			@SuppressWarnings("unchecked")
			List<RegistroAltaBajaVO> queryForList = (List<RegistroAltaBajaVO>)sqlMap.queryForList("imed.consultaAltas", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al consultar las altas, mensaje: " + e.getMessage());
			throw new Exception();
		}
	}
	
	public List<RegistroAltaBajaVO> consultaBajas() throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			@SuppressWarnings("unchecked")
			List<RegistroAltaBajaVO> queryForList = (List<RegistroAltaBajaVO>)sqlMap.queryForList("imed.consultaBajas", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al consultar las bajas, mensaje:" + e.getMessage());
			throw new Exception();
		}
	}
	
	public int insertBitacora(BitacoraVO registro) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			Integer resultado = (Integer)sqlMap.insert("imed.insertBitacora", registro);
			if(resultado!= null){
				return resultado.intValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al insertar bitácora, mensaje:" + e.getMessage());
		}
		return 0;
	}
	
	public int insertErroneo(RegistroAltaBajaVO registro) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			Integer resultado = (Integer)sqlMap.insert("imed.insertErroneo", registro);
			if(resultado!= null){
				return resultado.intValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error al insertar registro Erroneo, mensaje: " + e.getMessage());
		}
		return 0;
	}	

}
