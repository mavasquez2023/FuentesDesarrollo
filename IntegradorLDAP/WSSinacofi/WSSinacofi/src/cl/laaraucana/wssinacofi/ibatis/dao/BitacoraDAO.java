package cl.laaraucana.wssinacofi.ibatis.dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import cl.laaraucana.wssinacofi.ibatis.config.SqlMapLocator;
import com.ibatis.sqlmap.client.SqlMapClient;

public class BitacoraDAO {

	private static Log log = LogFactory.getLog(BitacoraDAO.class);


	public static void insertBitacora(Map<String, String> param){

		try {
			SqlMapClient sqlMap = SqlMapLocator.getInstance();

			sqlMap.insert("bitacora.insertConsulta", param);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}