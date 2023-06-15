package cl.araucana.wsafiliado.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import cl.araucana.wsafiliado.sqlmap.SqlMapLocator;
import cl.araucana.wsafiliado.sqlmap.SqlMapLocatorAux;
import cl.araucana.wsafiliado.vo.DatoEntradaVO;

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


	public static void insertConsultaBitacoraAux(Map<String, String> param){

		try {
			SqlMapClient sqlMap = SqlMapLocatorAux.getInstance();

			sqlMap.insert("bitacora.insertConsulta", param);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}