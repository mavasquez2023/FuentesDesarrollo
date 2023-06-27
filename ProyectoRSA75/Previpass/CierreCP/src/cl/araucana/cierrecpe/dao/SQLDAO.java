/*
 * Creado el 06-04-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.dao;

import java.sql.SQLException;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.to.ParametrosCPTO;
import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.ConectaDB2;

/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class SQLDAO implements DAO_Interface{
	private ConectaDB2 db2;
	private static Logger logger = LogManager.getLogger();
	
	public SQLDAO(ConectaDB2 db2){
		this.db2= db2;
	}
	
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		
		String query= (String) pk;
		logger.fine("Query:" + query);
		db2.executeQuery(query);
		while (db2.next()){
			String periodo= db2.getString(1);
		}
		
		return null;
	}
	
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj) throws SQLException {
		String query= (String) obj;
		logger.fine("Query:" + query);
		return db2.executeUpdate(query);
	}

	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int update(Object obj) throws SQLException {
		String query= (String) obj;
		logger.fine("Query:" + query);
		return db2.executeUpdate(query);
	}

	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		
	}
}
