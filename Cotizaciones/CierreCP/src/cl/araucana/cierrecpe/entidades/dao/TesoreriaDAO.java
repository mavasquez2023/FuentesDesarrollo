/*
 * Creado el 13-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.entidades.to.ComprobanteCPTO;
import cl.araucana.cierrecpe.entidades.to.FormasPagoTO;
import cl.araucana.cierrecpe.entidades.to.TipoDetalleTO;
import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.Rut;
import cl.recursos.ConectaDB2;
import java.util.logging.Logger;
import cl.araucana.core.util.logging.LogManager;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TesoreriaDAO implements DAO_Interface {
	private ConectaDB2 db2;
	private static Logger logger = LogManager.getLogger();
	
	public TesoreriaDAO(ConectaDB2 db2){
		this.db2= db2;
	}
	
	public long selectTotalTesoreria(String codbar, Date fechamin, Date fechamax) throws SQLException{
		long totalTesoreria=0;
		//rescatando numero de comprobantes pagados
		StringBuffer query= new StringBuffer();
		query.append("SELECT  sum(TE7MA) ");
		query.append("FROM TEDTA.TE07F1 A ");
		query.append("WHERE A.TE3XA = 'I' ");
		query.append("AND A.TE3YA = 'C' ");
		query.append("AND A.TEQA= 23 ");
		query.append("AND subSTR(TEA7A, 1, 2)= ? ");
		query.append("AND A.TE40A BETWEEN (?) AND (?) ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, fechamin);
		db2.setStatement(2, fechamax);

		db2.executeQuery();
		
		if (db2.next()){
			long total= db2.getLong(1);
			totalTesoreria=total;
		}
		db2.closeStatement();
		return totalTesoreria;
	}
	
	public Collection selectComprobantesCuadraturaTesoreria(String codbar, Date fechamin, Date fechamax) throws SQLException{
				
		//Se guardan en única lista los descuadres en CP y Tesorería
		List comprobantes= new ArrayList();
		//rescatando comprobantes que están en Tesorería
		StringBuffer query= new StringBuffer();
		query.append("SELECT A.TEA7A, A.TE3WA, A.TE42A, A.TE7NA, A.TE40A, A.TE1SA ");
		query.append("FROM TEDTA.TE07F1 A ");
		query.append("WHERE A.TE3XA = 'I' ");
		query.append("AND A.TE3YA = 'C' ");
		query.append("AND A.TEQA= 23 ");
		query.append("AND subSTR(TEA7A, 1, 2)= ? ");
		//query.append("AND A.TE41A= ? ");
		query.append("AND A.TE40A BETWEEN (?) AND (?) ");
		System.out.println("query:" + query.toString());
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, codbar);
		db2.setStatement(2, fechamin);
		db2.setStatement(3, fechamax);
		db2.executeQuery();
		while (db2.next()){
			long codigoBarra= db2.getLong(1);
			int folioIngreso= db2.getInt(2);
			System.out.println("folio: " + folioIngreso);
			int rutEmpresa= db2.getInt(3);
			long monto= db2.getLong(4);
			AbsoluteDate pagado= new AbsoluteDate(db2.getDate(5));
			Time time= db2.getTime(6);
			ComprobanteCPTO comprobante= new ComprobanteCPTO();
			comprobante.setProceso("TE");
			comprobante.setCodigoBarra(codigoBarra);
			comprobante.setFolioIngreso(folioIngreso);
			comprobante.setRutEmpresa(new Rut(rutEmpresa));
			comprobante.setMonto(monto);
			comprobante.setFecha(pagado);
			comprobante.setTime(time.toString());
			comprobantes.add(comprobante);
		}
		return comprobantes;
	}
	
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente

	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	
}
