package cl.araucana.infcotrec.main.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.araucana.infcotrec.dao.VO.PagosVO;
import cl.araucana.infcotrec.dao.VO.ParamVO;
import cl.araucana.infcotrec.ibatis.MyClassSqlConfig;

import com.ibatis.sqlmap.client.SqlMapClient;


public class ConsultaPagosDAO {
	protected static Logger logger = Logger.getLogger("ConsultaDeudaDAO");
	
	@SuppressWarnings("unchecked")
	public Integer consultaMaxPeriodoCotizacion(int rutEmpresa) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			Integer maximo = (Integer)sqlMap.queryForObject("recaudacion.maxPeriodoCotizacion", rutEmpresa);
			return maximo;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public List<ParamVO> consultaOficinasPagosEmpresa(ParamVO param) throws Exception {
		double prima = 0;
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<ParamVO> queryForList = sqlMap.queryForList("recaudacion.consultaOficinasEmpresa", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ParamVO consultaSucursalPagosEmpresa(ParamVO param) throws Exception {
		double prima = 0;
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			ParamVO queryForObject = (ParamVO)sqlMap.queryForObject("recaudacion.consultaSucursalEmpresa", param);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<PagosVO> consultaPagosEmpresa(ParamVO param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<PagosVO> queryForList = sqlMap.queryForList("recaudacion.consultaPagosEmpresa", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Integer insertBitacora(Map<String, String> param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			Object resultado = sqlMap.insert("recaudacion.insertBitacora", param);
			if(resultado!= null){
				return (Integer)resultado;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	public synchronized static int getCorrelativo() throws Exception {
		int correlativo = 0;
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			correlativo = (Integer)sqlMap.queryForObject("recaudacion.getCorrelativo", null);
			sqlMap.insert("recaudacion.setCorrelativo", null);
			return correlativo+1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}

}
