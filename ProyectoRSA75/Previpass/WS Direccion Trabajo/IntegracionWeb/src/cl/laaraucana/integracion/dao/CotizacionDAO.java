package cl.laaraucana.integracion.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import cl.laaraucana.integracion.vo.CotizacionVO;
import cl.laaraucana.integracion.vo.DatoEntradaVO;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CotizacionDAO {

	private static String mensajeError=null;
	private static Log log = LogFactory.getLog(CotizacionDAO.class);
	
	public static List obtenerCotizaciones(DatoEntradaVO data){
		
		setMensajeError(null);
		log.info("Parámetros consulta");
		log.info("Rut Empleador: "+data.getRutEmpleador());
		log.info("Rut trabajador: "+data.getRutTrabajador());
		log.info("Periodo: "+data.getPeriodo());
		log.info("Bitacora: "+data.getConBitacora());
		
		SqlMapClient sqlMap = SqlMapInstance.getInstance().getSqlMap();
		List cotizaciones = new ArrayList();
		try{
			cotizaciones = sqlMap.queryForList("integracionNS.obtenerCotizaciones2", data );
		}
		catch(Exception e){
			log.error("Error Conectando a la Base de Datos con el jndi jdbc/integracion, mensaje:" + e.getMessage());
			setMensajeError("Error Conectando a la Base de Datos con el jndi jdbc/integracion, mensaje:" + e.getMessage());
		}
		
		return cotizaciones;
	}

	/**
	 * @return el mensajeError
	 */
	public static String getMensajeError() {
		return mensajeError;
	}

	/**
	 * @param mensajeError el mensajeError a establecer
	 */
	public static void setMensajeError(String mensajeError) {
		CotizacionDAO.mensajeError = mensajeError;
	}

	
	
	
	
	
	
}
