package cl.araucana.fonasa.main.dao;


import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import cl.araucana.fonasa.dao.VO.BitacoraVO;
import cl.araucana.fonasa.dao.VO.FormularioVO;
import cl.araucana.fonasa.ibatis.MyClassSqlConfig;
import cl.araucana.fonasa.utils.Utils;


import com.ibatis.sqlmap.client.SqlMapClient;


public class ConsultaServicesDAO {
	protected static Logger logger = Logger.getLogger("ConsultaCargasDAO");
	

	@SuppressWarnings("unchecked")
	public List<BitacoraVO> consultaBitacora(Date fecha) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			logger.info("Consultando bitacora, desde fecha:" + Utils.dateToString(fecha));
			List<BitacoraVO> queryForList = sqlMap.queryForList("fonasaservices.consultaBitacora", fecha);
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
			logger.info("Grabando bitacora");
			Object resultado = sqlMap.insert("fonasaservices.insertBitacora", param);
			if(resultado!= null){
				return (Integer)resultado;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public List<FormularioVO> consultaFormularios(int periodo) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			logger.info("Consultando formularios, periodo:" + periodo);
			List<FormularioVO> queryForList = sqlMap.queryForList("fonasaservices.consultaFormularios", periodo);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
}
