package cl.laaraucana.convenfsura.ibatis.dao;


import java.util.List;

import org.apache.log4j.Logger;
import cl.laaraucana.convenfsura.ibatis.config.MyClassSqlConfig;

import com.ibatis.sqlmap.client.SqlMapClient;


public class ContactoDWHDaoImpl implements ContactoDWHDao{
	protected static Logger logger = Logger.getLogger("ContactoDWHDaoImpl");

	@Override
	public List<String> getMail(int rut) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<String> queryForList = (List<String>)sqlMap.queryForList("contacto.consultaMail", rut);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<String> getTelefono(int rut) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<String> queryForList = (List<String>)sqlMap.queryForList("contacto.consultaTelefono", rut);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<String> getCelular(int rut) throws Exception {
		SqlMapClient sqlMap = null;

		try {
			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		} catch (Exception e) {
			throw new Exception("Error al conectar al Datasource");
		}

		try {

			List<String> queryForList = (List<String>)sqlMap.queryForList("contacto.consultaCelular", rut);
			return queryForList;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	

}
