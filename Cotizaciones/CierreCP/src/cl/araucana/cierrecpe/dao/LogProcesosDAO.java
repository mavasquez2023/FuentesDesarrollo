/*
 * Creado el 06-04-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.to.ProcesosEjecutadosTO;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.ConectaDB2;

/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class LogProcesosDAO implements DAO_Interface{
	private ConectaDB2 db2;
	private static Logger logger = LogManager.getLogger();
	
	public LogProcesosDAO(ConectaDB2 db2){
		this.db2= db2;
	}
	
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		StringBuffer query= new StringBuffer();
		query.append("select periodo, cierre, proceso, inicio, termino, round((termino-inicio)/60, 1) ");
		query.append("from LOG_CIERRE ");
		query.append("order by id desc ");
		query.append("FETCH FIRST 100 ROWS ONLY ");
		logger.finest("Query:" + query.toString());
		db2.executeQuery(query.toString());
		List log= new ArrayList();
		while (db2.next()){
			int periodo= db2.getInt(1);
			int cierre= db2.getInt(2);
			String proceso= db2.getString(3);
			String inicio= db2.getString(4);
			String termino= db2.getString(5);
			double duracion= db2.getDouble(6);
			ProcesosEjecutadosTO logTO = new ProcesosEjecutadosTO();
			logTO.setPeriodo(periodo);
			logTO.setCierre(cierre);
			logTO.setProceso(proceso);
			logTO.setInicio(inicio.substring(0, inicio.lastIndexOf('.')));
			logTO.setTermino(termino.substring(0, termino.lastIndexOf('.')));
			logTO.setDuracion(duracion);
			log.add(logTO);
		}
		return log;
	}
	
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(int periodo, int cierre, String proceso, String inicio, String termino) throws SQLException {
		String query= "insert into LOG_CIERRE (PERIODO, CIERRE, PROCESO, INICIO, TERMINO) values(?, ?, ?, ?, ?) ";
		logger.finest("Query:" + query);
		db2.prepareQuery(query);
		db2.setStatement(1, periodo);
		db2.setStatement(2, cierre);
		db2.setStatement(3, proceso);
		db2.setStatement(4, inicio);
		db2.setStatement(5, termino);
		return db2.executeUpdate();
	}

	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		
	}


	public int insert(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}
}
