package cl.araucana.infcottra.main.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cl.araucana.infcottra.dao.VO.CotizacionVO;
import cl.araucana.infcottra.dao.VO.ParamVO;
import cl.araucana.infcottra.ibatis.MyClassSqlConfig;

import com.ibatis.sqlmap.client.SqlMapClient;


public class ConsultaCotizacionDAO {
	protected static Logger logger = Logger.getLogger("ConsultaDeudaDAO");
	
	
	@SuppressWarnings("unchecked")
	public CotizacionVO consultaCotizacionesTrabajador(ParamVO param) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			CotizacionVO queryForObject = (CotizacionVO)sqlMap.queryForObject("recaudacion.consultaCotizacionesTrabajador", param);
			return queryForObject;
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
