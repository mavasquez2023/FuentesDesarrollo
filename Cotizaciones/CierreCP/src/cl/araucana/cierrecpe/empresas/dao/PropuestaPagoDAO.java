/*
 * Creado el 13-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.empresas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.ConectaDB2;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PropuestaPagoDAO implements DAO_Interface{
	private ConectaDB2 db2;
	private static Logger logger = LogManager.getLogger();
	
	public PropuestaPagoDAO(ConectaDB2 db2){
		try {
			System.out.println("Propuesta Pago, Verificando conexión db2:" + !db2.isClosed());
			this.db2= db2;
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
//	rescatando lista cierres disponibles para generar Planillas
	public Collection selectListCierres(int periodo) throws SQLException{

		List cierres=null;
		StringBuffer query= new StringBuffer();
		query.append("select CIERRE ");
		query.append("from PROPUESTA_PAGO ");
		query.append("WHERE PERIODO= ? ");
		query.append("GROUP BY CIERRE ORDER BY CIERRE DESC");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, periodo);
		db2.executeQuery();
		cierres = new ArrayList();
		while (db2.next()){
			int cierre= db2.getInt(1);
			cierres.add(new Integer(cierre));
		}
		return cierres;
	}
	
//	rescatando lista cierres disponibles para generar Planillas
	public Collection selectListCierresResumen(int periodo) throws SQLException{

		List cierres=null;
		StringBuffer query= new StringBuffer();
		query.append("select CIERRE ");
		query.append("from RESPROCIE ");
		query.append("WHERE PERIODO= ? ");
		query.append("GROUP BY CIERRE ORDER BY CIERRE DESC");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, periodo);
		db2.executeQuery();
		cierres = new ArrayList();
		while (db2.next()){
			int cierre= db2.getInt(1);
			cierres.add(new Integer(cierre));
		}
		return cierres;
	}
	
	public Collection selectListPeriodos() throws SQLException{
//		rescatando lista periodos disponibles
		String query= "select PERIODO from PROPUESTA_PAGO GROUP BY PERIODO ORDER BY PERIODO DESC";
		logger.finest("Query=" + query.toString());
		db2.executeQuery(query);
		List periodos= new ArrayList();
		while (db2.next()){
			String periodo= db2.getString(1);
			periodos.add(new Integer(Integer.parseInt(periodo)));
		}
		return periodos;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	public void delete(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		
	}

	public int insert(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

}
