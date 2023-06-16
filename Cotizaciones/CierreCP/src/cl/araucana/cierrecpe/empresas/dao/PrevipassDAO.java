/*
 * Creado el 24-02-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.empresas.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.sql.Date;

import javax.sql.DataSource;

import com.ibm.as400.access.*;

import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.to.BoletaTO;
import cl.recursos.*;


/**
 * @author usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PrevipassDAO implements DAO_Interface{
	private ConectaDB2 db2cp;
	private final String  PAGADO="5";
	private final String  SPL_EMPRESA="1";
	private final String  SPL_INDEPENDIENTE="4";


	public PrevipassDAO(DataSource ds) throws SQLException{
		this.db2cp= new ConectaDB2(ds);
	}

	/* (sin Javadoc)
	 * @see cl.scx.dao.DAO_Interface#select(java.lang.Object)
	 */
	public long select(int folio) throws SQLException {
		String query="";
		try {
			query= "select id_codigo_barra from comprobante_pago where folio_tesoreria= ?";
			String date= Today.getAAAAMMDD('-');
			db2cp.prepareQuery(query);
			db2cp.setStatement(1, folio);
			db2cp.executeQuery();
			if(db2cp.next()){
				long codigo= db2cp.getLong(1);
				return codigo;
			}
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return 0;
	}

	/* (sin Javadoc)
	 * @see cl.scx.dao.DAO_Interface#select(java.lang.Object)
	 */
	public long selectBancos(int folio) throws SQLException {
		String query="";
		try {
			query= "select id_codigo_barra from comprobante_pago where folio_tesoreria= ?";
			String date= Today.getAAAAMMDD('-');
			db2cp.prepareQuery(query);
			db2cp.setStatement(1, folio);
			db2cp.executeQuery();
			if(db2cp.next()){
				long codigo= db2cp.getLong(1);
				return codigo;
			}
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return 0;
	}

	/* (sin Javadoc)
	 * @see cl.scx.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	/* (sin Javadoc)
	 * @see cl.scx.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int update(Object obj){
		String queryUpdateComprobante="", queryUpdateNominaRE="", queryUpdateNominaGR="", queryUpdateNominaDC="", queryUpdateNominaRA="";
		int count=0;
		BoletaTO detalleTO= (BoletaTO) obj;
		String[] foliosBoleta= detalleTO.getFolios();
		List no_cursados= new ArrayList();

		queryUpdateComprobante= "update comprobante_pago  set  id_estado= ?, forma_pago= ?, pagado= ?  where  folio_tesoreria = ?  ";
		queryUpdateNominaRE= "update nominare  set  id_estado= ?  where  id_codigo_barra = ?  ";
		queryUpdateNominaGR= "update nominagr  set  id_estado= ?  where  id_codigo_barra = ?  ";
		queryUpdateNominaDC= "update nominadc  set  id_estado= ?  where  id_codigo_barra = ?  ";
		queryUpdateNominaRA= "update nominara  set  id_estado= ?  where  id_codigo_barra = ?  ";
		String date= Today.getAAAAMMDD('-');
		for (int i = 0; i < foliosBoleta.length; i++) {
			int folio = Integer.parseInt(foliosBoleta[i]);
			int resultado;
			try {
				db2cp.prepareQuery(queryUpdateComprobante);
				db2cp.setStatement(1, PAGADO);
				db2cp.setStatement(2, SPL_EMPRESA);
				db2cp.setStatement(3, Date.valueOf(date));
				db2cp.setStatement(4, folio);
				resultado = db2cp.executeUpdate();
				if (resultado>0){
					long id_codigo_barra= select(folio);
					db2cp.prepareQuery(queryUpdateNominaRE);
					db2cp.setStatement(1, PAGADO);
					db2cp.setStatement(2, id_codigo_barra);
					resultado = db2cp.executeUpdate();
					if (resultado==0){
						db2cp.prepareQuery(queryUpdateNominaGR);
						db2cp.setStatement(1, PAGADO);
						db2cp.setStatement(2, id_codigo_barra);
						resultado = db2cp.executeUpdate();
						if (resultado==0){
							db2cp.prepareQuery(queryUpdateNominaDC);
							db2cp.setStatement(1, PAGADO);
							db2cp.setStatement(2, id_codigo_barra);
							resultado = db2cp.executeUpdate();
							if (resultado==0){
								db2cp.prepareQuery(queryUpdateNominaRA);
								db2cp.setStatement(1, PAGADO);
								db2cp.setStatement(2, id_codigo_barra);
								resultado = db2cp.executeUpdate();
							}
						}
					}
					count+= resultado;
				}
			} catch (SQLException e) {
				resultado=0;
				detalleTO.setEstado("E");
				System.out.println("PrevipassDAO, Error en la ejecución SQL en Previpass para ID_PAGO= " + detalleTO.getIdBoleta() + ", folio=" + folio + ", mensaje:" + e.getMessage());
				detalleTO.setDescripcionEstado("Error en la ejecución SQL en Previpass para ID_PAGO= " + detalleTO.getIdBoleta() + ", mensaje:" + e.getMessage());
				e.printStackTrace();
			}
			if (resultado== 0){
				System.out.println("Query NO Cursada= " + queryUpdateComprobante + ", folio=" + folio);
				no_cursados.add(new Integer(folio));
			}
		}
		//detalleTO.setFoliosImpresos(no_cursados);
		detalleTO.setEstado("S");
		return count;
	}

	/* (sin Javadoc)
	 * @see cl.scx.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente

	}


	public void closeConnections() throws SQLException{
		if(db2cp!=null){
			db2cp.desconectaDB2();
		}
	}

	public Object select(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}
}
