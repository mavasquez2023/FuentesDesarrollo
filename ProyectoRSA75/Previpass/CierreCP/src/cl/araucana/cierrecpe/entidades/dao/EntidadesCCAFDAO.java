/*
 * Creado el 15-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.entidades.to.EntidadCCAFTO;
import cl.araucana.cierrecpe.entidades.to.TipoDetalleTO;
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
public class EntidadesCCAFDAO implements DAO_Interface {
	private ConectaDB2 db2;
	private static Logger logger = LogManager.getLogger();
	
	public EntidadesCCAFDAO(ConectaDB2 db2){
		this.db2= db2;
	}
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		StringBuffer query= new StringBuffer();
		query.append("SELECT ID_CCAF, PORCENTAJE_FONASA, ASFAM, CREDITOS, LEASING, DENTAL, SEGURO  ");
		query.append("FROM ENTIDAD_CCAF ");
		query.append("ORDER BY 1");
		logger.finest("Query=" + query.toString());
		db2.executeQuery(query.toString());
		Map ccafs= new HashMap();
		while (db2.next()) {
			int idCCAF= db2.getInt(1);
			int compensaAporte= db2.getInt(2);
			int compensaAsfam= db2.getInt(3);
			int compensaCredito= db2.getInt(4);
			int compensaLeasing= db2.getInt(5);
			int compensaDental= db2.getInt(6);
			int compensaSeguro= db2.getInt(7);
			EntidadCCAFTO entTO= new EntidadCCAFTO();
			entTO.setIdCCAF(idCCAF);
			if(compensaAporte==1){
				entTO.setCompensaAporte(true);
			}
			if(compensaAsfam==1){
				entTO.setCompensaAsfam(true);
			}
			if(compensaCredito==1){
				entTO.setCompensaCredito(true);
			}
			if(compensaLeasing==1){
				entTO.setCompensaLeasing(true);
			}
			if(compensaDental==1){
				entTO.setCompensaDental(true);
			}
			if(compensaSeguro==1){
				entTO.setCompensaSeguro(true);
			}
			ccafs.put(new Integer(idCCAF), entTO);
		}
		
		return ccafs;
	}

	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
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

}
