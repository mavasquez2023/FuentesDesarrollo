package cl.laaraucana.licenciagestion.ibatis.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.laaraucana.licenciagestion.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.licenciagestion.vo.OficinasLicenciasVO;
import cl.laaraucana.licenciagestion.vo.LicenciasPeriodoVO;
import cl.laaraucana.licenciagestion.vo.LicenciasViaIngresoVO;

import com.ibatis.sqlmap.client.SqlMapClient;


public class ConsultaLicenciasDAO {
	protected static Logger logger = Logger.getLogger("ConsultaLicenciasDAO");
	
	public int maxRangoLicencias(HashMap<String, Integer> periodo) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			@SuppressWarnings("unchecked")
			Integer count = (Integer)sqlMap.queryForObject("licencia.maxRangoLicencias", periodo);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	public List<LicenciasPeriodoVO> licenciasxMes() throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			@SuppressWarnings("unchecked")
			List<LicenciasPeriodoVO> listaxmes = (List<LicenciasPeriodoVO>)sqlMap.queryForList("licencia.licenciasxMes", null);
			return listaxmes;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<OficinasLicenciasVO> oficinasxRango(Map<String, Integer> rangos) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			@SuppressWarnings("unchecked")
			List<OficinasLicenciasVO> listaxoficina = (List<OficinasLicenciasVO>)sqlMap.queryForList("licencia.oficinasxRango", rangos);
			return listaxoficina;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public List<LicenciasViaIngresoVO> licenciasxViaIngeso(HashMap<String, Integer> periodo) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			@SuppressWarnings("unchecked")
			List<LicenciasViaIngresoVO> listaxoficina = (List<LicenciasViaIngresoVO>)sqlMap.queryForList("licencia.licenciasxViaIngreso", periodo);
			return listaxoficina;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
}
