package cl.araucana.infdeupre.main.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

import cl.araucana.infdeupre.dao.VO.DeudaVO;
import cl.araucana.infdeupre.dao.VO.ParamVO;
import cl.araucana.infdeupre.ibatis.MyClassSqlConfig;

public class ConsultaDeudaDAO {
	protected static Logger logger = Logger.getLogger("ConsultaDeudaDAO");
	
	@SuppressWarnings("unchecked")
	public List<ParamVO> consultaOficinasDeudaEmpresa(int rutEmpresa) throws Exception {
		double prima = 0;
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<ParamVO> queryForList = sqlMap.queryForList("recaudacion.consultaOficinasEmpresa", rutEmpresa);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public ParamVO consultaSucursalDeudaEmpresa(ParamVO param) throws Exception {
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
	
	public List<DeudaVO> consultaDeudaEmpresa(ParamVO param) throws Exception {
		double prima = 0;
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			List<DeudaVO> queryForList = sqlMap.queryForList("recaudacion.consultaDeudaEmpresa", param);
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
