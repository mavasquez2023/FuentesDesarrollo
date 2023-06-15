package cl.araucana.wsempresa.ibatis.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.araucana.wsempresa.ibatis.config.SqlMapLocator;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BitacoraDAO {

	private static Log log = LogFactory.getLog(BitacoraDAO.class);


	public static void insertConsultaBitacora(Map<String, String> param){

		try {
			SqlMapClient sqlMap = SqlMapLocator.getInstance();

			sqlMap.insert("bitacora.insertConsulta", param);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}