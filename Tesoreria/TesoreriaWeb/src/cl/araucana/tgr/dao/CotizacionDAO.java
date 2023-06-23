package cl.araucana.tgr.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import cl.araucana.tgr.sqlmap.SqlMapLocator;
import cl.araucana.tgr.vo.DatoEntradaVO;
import cl.araucana.tgr.vo.DatosEntradaVO;
import cl.araucana.tgr.vo.TrabajadorVO;
import cl.araucana.tgr.vo.RequestWSTGR;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CotizacionDAO {

	private static Log log = LogFactory.getLog(CotizacionDAO.class);
	
	public static List obtenerCotizaciones(DatoEntradaVO data) throws SQLException{
		
		log.info("Rut Empleador: "+data.getRutemp());
		log.info("Periodo: " +data.getAnio() + data.getMes());
		SqlMapClient sqlMap;
		sqlMap = SqlMapLocator.getInstance();
	
		List cotizaciones = sqlMap.queryForList("publicacion.obtenerRemuneracionesPlus", data );
		
		return cotizaciones;
	}
	
public static List obtenerListaCotizaciones(DatosEntradaVO data) throws SQLException{
		
		log.info("Rut Empleador: "+data.getRutemp());
		log.info("Periodo: " +data.getAnio() + data.getMes());
		
		SqlMapClient sqlMap = SqlMapLocator.getInstance();
	
		List cotizaciones = sqlMap.queryForList("publicacion.obtenerListaRemuneracionesPlus", data );
		
		return cotizaciones;
	}

public static int obtenerStatus(){
	
	Integer status;
	try {
		SqlMapClient sqlMap = SqlMapLocator.getInstance();

		status = (Integer)sqlMap.queryForObject("publicacion.obtenerStatus");
	} catch (SQLException e) {
		e.printStackTrace();
		return 0;
	}
	
	return status.intValue();
}
	
}