package cl.araucana.bonomarzo.dao;

import java.sql.SQLException;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import cl.araucana.bonomarzo.sqlmap.SqlMapLocator;
import cl.araucana.bonomarzo.vo.DatoEntradaVO;
import cl.araucana.bonomarzo.vo.RequestWS;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BonoMarzoDAO {

	private static Log log = LogFactory.getLog(BonoMarzoDAO.class);
	
	public static Object obtenerBonoTrabajador(DatoEntradaVO data) throws SQLException{
		
		log.info("ID Consulta: " +data.getId() + ", RUT: " + data.getRutcliente());
		
		SqlMapClient sqlMap = SqlMapLocator.getInstance();
	
		Object resultado = sqlMap.queryForObject("bonomarzo.consultaBonoMarzo", data );
		
		return resultado;
	}
	
public static Object validaCredenciales(String user) throws SQLException{
		
		log.info("User: " +user );
		
		SqlMapClient sqlMap = SqlMapLocator.getInstance();
	
		Object resultado = sqlMap.queryForObject("bonomarzo.validaUsuario", user );
		
		return resultado;
	}

public static Object insertBitacora(HashMap data) throws SQLException{
	
	
	SqlMapClient sqlMap = SqlMapLocator.getInstance();

	Object resultado = sqlMap.insert("bonomarzo.insertBitacora", data );
	
	return resultado;
}
}