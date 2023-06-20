package cl.laaraucana.surakm.ibatis.dao;


import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import cl.laaraucana.surakm.ibatis.config.MyClassSqlConfig;
import cl.laaraucana.surakm.ibatis.vo.ParamContacto;

import com.ibatis.sqlmap.client.SqlMapClient;


public class ContactoDWHDaoImpl implements ContactoDWHDao{
	protected static Logger logger = Logger.getLogger("ContactoDWHDaoImpl");

	@Override
	public List<String> getMail(ParamContacto param) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<String> queryForList = (List<String>)sqlMap.queryForList("contacto.consultaMail", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<String> getCelular(ParamContacto param) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<String> queryForList = (List<String>)sqlMap.queryForList("contacto.consultaCelular", param);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Date getFechaCorte(int rut) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			Date queryObject = (Date)sqlMap.queryForObject("contacto.consultaFechaCorte", rut);
			return queryObject;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	

}
