/*
 * Creado el 13-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cl.recursos.ConectaDB2;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.entidades.to.ComprobanteCPTO;
import cl.araucana.cierrecpe.entidades.to.InformeContableTO;
import cl.araucana.cierrecpe.entidades.to.PropuestaPagoTO;
import cl.araucana.core.util.Rut;
import cl.araucana.core.util.logging.LogManager;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class InformeContableDAO implements DAO_Interface {
	private ConectaDB2 db2;
	private static Logger logger = LogManager.getLogger();
	
	public InformeContableDAO(ConectaDB2 db2){
		try {
			logger.fine("Verificando conexión en InformeContableDAO:" + !db2.isClosed());
			this.db2= db2;
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public int select() throws SQLException {
		int cantidad=0;
		StringBuffer sqlstmt = new StringBuffer();
        sqlstmt.append("SELECT COUNT(*) FROM BALANCE.RECDOMINO ");
        logger.finest("Query=" + sqlstmt.toString());
        db2.executeQuery(sqlstmt.toString());
        if (db2.next()) {
        	cantidad= db2.getInt(1);
        }
        db2.closeStatement();
		return cantidad;
	}

	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj) throws SQLException {
		PropuestaPagoTO chequeTO= (PropuestaPagoTO)obj;
		
		String periodo= String.valueOf(chequeTO.getPeriodo());
		int año= Integer.parseInt(periodo.substring(0, 4));
		int mes= Integer.parseInt(periodo.substring(4)) + 1;
		if(mes==13){
			mes= 1;
			año++; 
		}
		int rutent= chequeTO.getRut().getNumber();
		char dvrutent=  chequeTO.getRut().getDV();
		int folioe= chequeTO.getFolioEgreso();
		long montotot= chequeTO.getMontoTotal();
		StringBuffer sqlstmt = new StringBuffer();
        sqlstmt.append("INSERT INTO BALANCE.RECDOMINO ");
        sqlstmt.append("(RDANO,RDMES,RDRUTPAGO,RDDVRUTP,RDFOLTESP,RDTOTENT,RDRUTEMP,RDDVRUTE,RDTOTEMP,RDFOLTESE,RDESTCOM,RDINDPRO,RDUSUARIO) ");
        sqlstmt.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        int ret=0;
        logger.finest("Query=" + sqlstmt.toString());
        logger.finest("Param folio egreso=" + folioe);
		for (Iterator iter = chequeTO.getDetalle().iterator(); iter.hasNext();) {
			ComprobanteCPTO detalle = (ComprobanteCPTO) iter.next();
			
	        db2.prepareQuery(sqlstmt.toString());
	        db2.setStatement(1, año);
	        db2.setStatement(2, mes);
	        db2.setStatement(3, rutent);
	        db2.setStatement(4, dvrutent);
	        db2.setStatement(5, folioe);
	        db2.setStatement(6, montotot);
	        db2.setStatement(7, detalle.getRutEmpresa().getNumber());
	        db2.setStatement(8, detalle.getRutEmpresa().getDV());
	        db2.setStatement(9, detalle.getMonto());
	        db2.setStatement(10, detalle.getFolioIngreso());
	        db2.setStatement(11, 'C');
	        db2.setStatement(12, '0');
	        db2.setStatement(13, "domserv");
	        
	        ret+= db2.executeUpdate();
			
		}
		//System.out.println("InformeContableDAO.insert, num registros insertados=" + ret);
        return ret;

	}

	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int update(Object obj) throws SQLException {
		return 0;
	}

	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object pk) throws SQLException {
		String query= "delete from BALANCE.RECDOMINO";
		db2.executeUpdate(query);
		logger.finest("Query=" + query);
	}

	
	public Object select(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

}
