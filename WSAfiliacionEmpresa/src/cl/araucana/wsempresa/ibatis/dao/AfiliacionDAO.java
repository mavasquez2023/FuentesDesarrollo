package cl.araucana.wsempresa.ibatis.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;


import cl.araucana.wsempresa.ibatis.config.SqlMapLocator;

import com.ibatis.sqlmap.client.SqlMapClient;


public class AfiliacionDAO {

	private static Logger log = Logger.getLogger(AfiliacionDAO.class);
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> obtenerEstadoAfiliacionDB2(int rutEmpresa) {
		Map<String, String> salida= new HashMap<String, String>();
		try {
			SqlMapClient sqlMap = SqlMapLocator.getInstance();

			Map resultado = (HashMap<String, String>)sqlMap.queryForObject("afiliacion.obtenerEstadoAfiliacion", rutEmpresa);
			if(resultado==null){
				salida.put("RAZON_SOCIAL", "");
				salida.put("ESTADO", "0");
			}else if(resultado.get("ESTADO").toString().equals("E")){
				salida.put("ESTADO", "0");
				salida.put("RAZON_SOCIAL", resultado.get("RAZON_SOCIAL").toString().trim());
			}else{
				salida.put("ESTADO", "1");
				salida.put("RAZON_SOCIAL", resultado.get("RAZON_SOCIAL").toString().trim());
			}
			salida.put("GLOSA_ERROR", "");
				
		} catch (SQLException e) {
			log.warn("Error en conectar al Sistema " + e.getMessage());
			e.printStackTrace();
			salida.put("ESTADO", "-1");
			salida.put("RAZON_SOCIAL", "");
			salida.put("GLOSA_ERROR", e.getMessage());
		}
		
		return salida;
	}
	
}