package cl.laaraucana.wssinacofi.ibatis.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import cl.laaraucana.wssinacofi.ibatis.config.SqlMapLocator;

import com.ibatis.sqlmap.client.SqlMapClient;

public class AdministracionDAO {

	private static Log log = LogFactory.getLog(AdministracionDAO.class);

	public Map<String, String> validaUsuarioWS(String usuario) throws SQLException{

		SqlMapClient sqlMap = SqlMapLocator.getInstance();

		@SuppressWarnings("unchecked")
		Map<String, String> resultado = (HashMap<String, String>)sqlMap.queryForObject("administracion.validaUsuario", usuario );

		return resultado;
	}

	

}