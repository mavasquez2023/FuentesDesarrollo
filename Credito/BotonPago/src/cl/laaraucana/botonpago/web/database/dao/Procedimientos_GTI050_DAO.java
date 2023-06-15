package cl.laaraucana.botonpago.web.database.dao;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

public class Procedimientos_GTI050_DAO extends DaoParent {
	private static Logger logger = Logger.getLogger(Procedimientos_GTI050_DAO.class);

	/**
	 * ejecuta el procedimiento almacenado llamado PROLIBROBANCO
	 * @param params
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static String callProLibroBanco(HashMap params) throws Exception {

		String confirmacion = "";
		SqlMapClient sqlMap = getConn();
		try {
			sqlMap.queryForObject("ProLibroBanco", params);
			confirmacion = (String) params.get("DATO9");
			//			sqlMap.openSession().close();

		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("" + e.getMessage());
			throw new Exception("Error al obtener el numero correlativo");
		}
		return confirmacion;
	}//end CallproLibroBanco

}//end class
