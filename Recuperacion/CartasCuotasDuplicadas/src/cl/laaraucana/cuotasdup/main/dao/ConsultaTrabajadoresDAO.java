package cl.laaraucana.cuotasdup.main.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.laaraucana.cuotasdup.dao.VO.CuotaVO;
import cl.laaraucana.cuotasdup.dao.VO.MapeoArchivoVO;
import cl.laaraucana.cuotasdup.dao.VO.ParamVO;
import cl.laaraucana.cuotasdup.ibatis.MyClassSqlConfig;

public class ConsultaTrabajadoresDAO {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	public List consultaEmpresas(ParamVO param) throws Exception {
		double prima = 0;
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<CuotaVO> queryForList = sqlMap.queryForList("cuotasduplicadas.consultaEmpresas", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List consultaTrabajadores(ParamVO param) throws Exception {
		double prima = 0;
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<CuotaVO> queryForList = sqlMap.queryForList("cuotasduplicadas.consultaTrabajadores", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<MapeoArchivoVO> consultaMapeo() throws Exception {
		double prima = 0;
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<MapeoArchivoVO> queryForList = sqlMap.queryForList("cuotasduplicadas.consultaMapeo", null);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public int insertCuotas(List<CuotaVO> cuotas) throws Exception {
		SqlMapClient sqlMap = null;
		Integer resultado=0;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			sqlMap.delete("cuotasduplicadas.deleteCuotas", null);
			for (Iterator iterator = cuotas.iterator(); iterator.hasNext();) {
				CuotaVO cuotaVO = (CuotaVO) iterator.next();
				sqlMap.insert("cuotasduplicadas.insertCuotas", cuotaVO);
				resultado++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return resultado;
	}
	
	public void insertBitacora(Map<String, String> param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			Object resultado = sqlMap.insert("cuotasduplicadas.insertBitacora", param);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
}
