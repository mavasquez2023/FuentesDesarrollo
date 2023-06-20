package cl.araucana.ldap.dao;

import org.apache.log4j.Logger;

import cl.araucana.ldap.ibatis.config.MyClassSqlConfig;
import cl.araucana.ldap.ibatis.vo.RegistroVO;

import com.ibatis.sqlmap.client.SqlMapClient;


public class GestorClavesDAO {
	protected static Logger logger = Logger.getLogger("ConsultaCargasDAO");
	
	public RegistroVO consultaRegistro(int rut) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getInstance2();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {			
			RegistroVO queryForObject = (RegistroVO)sqlMap.queryForObject("gestorclaves.consultaRegistro", rut);
			return queryForObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new Exception();
		}
	}
	
	public Integer insertBitacora(RegistroVO registro) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getInstance2();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			Object resultado = sqlMap.insert("gestorclaves.insertBitacora", registro);
			if(resultado!= null){
				return (Integer)resultado;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	
	public Integer updateBitacora(RegistroVO registro) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getInstance2();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			Object resultado = sqlMap.update("gestorclaves.updateBitacora", registro);
			if(resultado!= null){
				return (Integer)resultado;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}
	
	public Integer updateBitacoraClave(int rut) throws Exception {
		SqlMapClient sqlMap = null;
		
		try {
			sqlMap = MyClassSqlConfig.getInstance2();
		} catch (Exception e) {			
			throw new Exception("Error al conectar al Datasource");
		}
		
		try {
			Object resultado = sqlMap.update("gestorclaves.updateBitacoraClave", rut);
			if(resultado!= null){
				return (Integer)resultado;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return 0;
	}

}
