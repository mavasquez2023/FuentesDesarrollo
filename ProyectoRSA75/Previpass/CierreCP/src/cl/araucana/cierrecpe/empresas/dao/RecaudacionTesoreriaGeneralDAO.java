/*
 * Creado el 13-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.empresas.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.business.NuevoConvenio;
import cl.araucana.cierrecpe.empresas.business.TrabajadorTGR;
import cl.araucana.cierrecpe.empresas.to.tgr.ComprobantesTO;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.ConectaDB2;

/**
 * @author Usist24
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class RecaudacionTesoreriaGeneralDAO implements DAO_Interface {
	private ConectaDB2 db2;
	private Map codigosTGR;
	private static Logger logger = LogManager.getLogger();
	
	public RecaudacionTesoreriaGeneralDAO(ConectaDB2 db2, Map codigosTGR){
		try {
			logger.finest("Verificando conexión DataSource:" + !db2.isClosed());
			this.db2= db2;
			this.codigosTGR= codigosTGR;
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj) throws SQLException {
		TrabajadorTGR trabajadorTO= (TrabajadorTGR)obj;
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO REDTA.RE72F1 ");
        query.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        logger.finest("Query= " + query.toString());
        db2.prepareQuery(query.toString());
        db2.setStatement(1, trabajadorTO.getPeriodo());
        db2.setStatement(2, trabajadorTO.getRutEmpresa().getNumber());
        db2.setStatement(3, getCodigoTGR(trabajadorTO.getCodigoAFP()));
        db2.setStatement(4, trabajadorTO.getRutTrabajador().getNumber());
        db2.setStatement(5, trabajadorTO.getRutTrabajador().getDV());
        db2.setStatement(6, trabajadorTO.getApellidoPaterno());
        db2.setStatement(7, trabajadorTO.getApellidoMaterno());
        db2.setStatement(8, trabajadorTO.getNombres());
        db2.setStatement(9, trabajadorTO.getRentaImponible());
        db2.setStatement(10, trabajadorTO.getMontoCotizacion());
        db2.setStatement(11, trabajadorTO.getTipoNomina());
        int ret= db2.executeUpdate();
        return ret;
	}
	
	public boolean existeConvenio(Object pk) throws SQLException {
		ComprobantesTO comprobante= (ComprobantesTO)pk;
		StringBuffer query= new StringBuffer();
		query.append("SELECT * ");
		query.append("FROM REDTA.RE02F1 ");
		query.append("WHERE recorut= ? ");
		query.append("AND recocor= ? ");
		
		logger.finest("Query= " + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, comprobante.getRutEmpresa().getNumber());
		db2.setStatement(2, comprobante.getConvenio());
		//Se ejecuta la query
		db2.executeQuery();
		if (db2.next()) {
			return true;
		}
		db2.closeStatement();
		return false;
	}
	
	public int insertConvenio(Object obj) throws SQLException {
		NuevoConvenio datos= (NuevoConvenio) obj;
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO REDTA.RE02F1 ");
		query.append("(RECORUT, RECOCOR, RECORAZSOC, RECODIG, RECOACTECO, RECODIR, RECONUM, RECOLOC, RECOCOM, RECOCUI, RECOREG, RECOTEL, RECOEMA) ");
		query.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        logger.finest("Query= " + query.toString());
        db2.prepareQuery(query.toString());
        db2.setStatement(1, datos.getRutEmpresa().getNumber());
        db2.setStatement(2, datos.getConvenio());
        db2.setStatement(3, datos.getRazonSocial());
        db2.setStatement(4, datos.getRutEmpresa().getDV());
        db2.setStatement(5, datos.getCodActEconomica());
        db2.setStatement(6, datos.getDireccion());
        db2.setStatement(7, datos.getNumero());
        db2.setStatement(8, datos.getLocal());
        db2.setStatement(9, datos.getComuna());
        db2.setStatement(10, datos.getCiudad());
        db2.setStatement(11, datos.getRegion());
        db2.setStatement(12, datos.getTelefono());
        db2.setStatement(13, datos.getEmail());
        int ret= db2.executeUpdate();
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
	}

	public Object select(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	/**
	 * @return el codigoTGR
	 */
	public String getCodigoTGR(int id_ent_fondo_pension) {
		return (String)codigosTGR.get(new Integer(id_ent_fondo_pension));
	}


}
