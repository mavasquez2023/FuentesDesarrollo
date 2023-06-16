/*
 * Creado el 17-02-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.dao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cl.recursos.ConectaDB2;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.entidades.to.PropuestaPagoTO;
import cl.araucana.core.util.logging.LogManager;

/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ChequeTesoreriaDAO implements DAO_Interface {
	private ConectaDB2 db2;
	private static Logger logger = LogManager.getLogger();
	
	public ChequeTesoreriaDAO(ConectaDB2 db2){
		try {
			System.out.println("Verificando conexión:" + !db2.isClosed());
			logger.info("ChequeTesoreriaDAO, verificando conexión db2:" + !db2.isClosed());
			this.db2= db2;
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj) throws SQLException {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy");
        DateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
        Date today = new Date();
        String dateOut = dateFormatter.format(today);
        String timeOut = timeFormatter.format(today);
        
        PropuestaPagoTO chequeTO= (PropuestaPagoTO)obj;
        
		StringBuffer sqlstmt = new StringBuffer();
        sqlstmt.append("INSERT INTO TEDTA.TE07F1 ");
        sqlstmt.append("(");
        sqlstmt.append("TE3WA,");
        sqlstmt.append("TE3XA,");
        sqlstmt.append("TE3YA,");
        sqlstmt.append("TE3ZA,");
        sqlstmt.append("TE1SA,");
        sqlstmt.append("TE41A,");
        sqlstmt.append("TE40A,");
        sqlstmt.append("TE1TA,");
        sqlstmt.append("TE42A,");
        sqlstmt.append("TE43A,");
        sqlstmt.append("TE44A,");
        sqlstmt.append("TE7MA,");
        sqlstmt.append("TE7NA,");
        sqlstmt.append("TE4BA,");
        sqlstmt.append("TE4CA,");
        sqlstmt.append("TE4DA,");
        sqlstmt.append("TE4EA,");
        sqlstmt.append("CMBA,");
        sqlstmt.append("TEQA,");
        sqlstmt.append("TE1CA,");
        sqlstmt.append("OBF002,");
        sqlstmt.append("OBF003,");
        sqlstmt.append("OBF005,");
        sqlstmt.append("OBF006,");
        sqlstmt.append("SAJKM94,");
        sqlstmt.append("SAJKM92");
        sqlstmt.append(")");
        sqlstmt.append("VALUES ");
        sqlstmt.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        logger.finest("Query=" + sqlstmt.toString());
        db2.prepareQuery(sqlstmt.toString());
        
        db2.setStatement(1, chequeTO.getFolioEgreso());
        db2.setStatement(2, "E");
        db2.setStatement(3, "G");
        db2.setStatement(4, dateOut);
        db2.setStatement(5, timeOut);
        db2.setStatement(6, "C");
        db2.setStatement(7, "01/01/01");
        db2.setStatement(8, "00:00:00");
        db2.setStatement(9, chequeTO.getRut().getNumber());
        db2.setStatement(10, chequeTO.getRut().getDV());
        db2.setStatement(11, chequeTO.getRazonSocial());
        db2.setStatement(12, chequeTO.getMontoTotal());
        db2.setStatement(13, chequeTO.getMontoTotal());
        db2.setStatement(14, "A");
        db2.setStatement(15, "C");
        db2.setStatement(16, dateOut);
        db2.setStatement(17, "N");
        db2.setStatement(18, "1");
        db2.setStatement(19, "23");
        db2.setStatement(20, "01/01/01");
        db2.setStatement(21, dateOut);
        db2.setStatement(22, timeOut);
        db2.setStatement(23, dateOut);
        db2.setStatement(24, timeOut);
        db2.setStatement(25, "domserv");
        db2.setStatement(26, "domserv");
        int ret= db2.executeUpdate();
		return ret;
	}
	
	public int insertDetalle(Object obj, int correlativo) throws SQLException{
		PropuestaPagoTO chequeTO= (PropuestaPagoTO)obj;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy");
        DateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
        Date today = new Date();
        String dateOut = dateFormatter.format(today);
        String timeOut = timeFormatter.format(today);
        
		StringBuffer sqlstmt = new StringBuffer();
        sqlstmt.append("INSERT INTO TEDTA.TE07F2");
        sqlstmt.append("(TE4QA,TE4SA,TE2XA,TE4UA,TE1YA,TE3WA,OBF002,OBF003,OBF005,OBF006,SAJKM94,SAJKM92)");
        sqlstmt.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
        logger.finest("Query=" + sqlstmt.toString());
        db2.prepareQuery(sqlstmt.toString());
        db2.setStatement(1, correlativo);
        db2.setStatement(2, chequeTO.getMontoTotal());
       	db2.setStatement(3, 1);
        db2.setStatement(4, 0);
        db2.setStatement(5, chequeTO.getConceptoTesoreria());
        db2.setStatement(6, chequeTO.getFolioEgreso());
        db2.setStatement(7, dateOut);
        db2.setStatement(8, timeOut);
        db2.setStatement(9, dateOut);
        db2.setStatement(10, timeOut);
        db2.setStatement(11, "domserv");
        db2.setStatement(12, "domserv");
        int ret= db2.executeUpdate();
        return ret;
	}
	
	public int insertRecaudacion(Object obj) throws SQLException{
		PropuestaPagoTO chequeTO= (PropuestaPagoTO)obj;
		StringBuffer sqlstmt = new StringBuffer();
        sqlstmt.append("INSERT INTO REDTA.RE50F1 ");
        sqlstmt.append("(REEPRUT,TE3WA,TE43A,REEMRAZSOC,REPDTOTAPA,REPRPRO) ");
        sqlstmt.append("VALUES (?, ?, ?, ?, ?, ?)");
        logger.finest("Query=" + sqlstmt.toString());
        db2.prepareQuery(sqlstmt.toString());
        db2.setStatement(1, chequeTO.getRut().getNumber());
        db2.setStatement(2, chequeTO.getFolioEgreso());
        db2.setStatement(3, chequeTO.getRut().getDV());
        
        db2.setStatement(4, chequeTO.getRazonSocial(40));
        db2.setStatement(5, chequeTO.getMontoTotal());
        db2.setStatement(6, 1);
        int ret= db2.executeUpdate();
        return ret;
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
	public int deleteRecaudacion() throws SQLException{
		String query= "delete from redta.re50f1";
		logger.finest("Query=" + query);
		int ret= db2.executeUpdate(query);
        return ret;
	}

}
