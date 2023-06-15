package cl.laaraucana.botonpago.web.database.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

public class DeudorNoAfiliadoDAO extends DaoParent {
	protected static Logger logger = Logger.getLogger(DeudorNoAfiliadoDAO.class);

	@SuppressWarnings("unchecked")
	public static String ejecutarProced(String entrada) throws Exception {

		String datos = "";

		//		SqlMapClient sqlMap = null;
		//		try {
		//			sqlMap = MyClassSqlConfig.getSqlMapInstance();
		//			sqlMap.openSession();
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//			throw new Exception("Falló la conexión al Datasource");
		//		}
		SqlMapClient sqlMap = getConn();
		try {
			@SuppressWarnings("rawtypes")
			Map map = new LinkedHashMap();
			map.put("PARAM", entrada);

			sqlMap.queryForObject("callProcedDeudor", map);
			datos = (String) map.get("PARAM");
			//			sqlMap.openSession().close();

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("" + e.getMessage());
			throw new Exception("Falló al realizar la invoación al procedimiento");
		}
		//		finally{
		//			sqlMap.openSession().close();
		//			
		//		}
		logger.debug("Salida: " + datos);

		return datos;
	}

}
