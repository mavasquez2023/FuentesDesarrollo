/*
 * Creado el 15-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.araucana.cierrecpe.dao.DAO_Interface;
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
public class TipoDetalleCPDAO implements DAO_Interface {
	private ConectaDB2 db2;
	private static Logger logger = LogManager.getLogger();
	
	public TipoDetalleCPDAO(ConectaDB2 db2){
		this.db2= db2;
	}
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		TipoDetalleTO detalleTO=null;
		StringBuffer query= new StringBuffer();
		List montos=null;
		query.append("SELECT t1.ID_TIPO_SECCION, t1.ID_TIPO_DETALLE, t1.ID_TIPO_NOMINA, t1.ID_ENT_PAGADORA, t2.ID_CONCEPTO, t2.ID_MONTO  ");
		query.append("FROM TIPO_DETALLE t1, MAPEO_CHEQUE_TESO t2 ");
		query.append("WHERE t1.ID_TIPO_SECCION= t2.ID_TIPO_SECCION ");
		query.append("AND t1.ID_TIPO_DETALLE=t2.ID_TIPO_DETALLE ");
		query.append("AND t1.ID_TIPO_NOMINA= t2.ID_TIPO_NOMINA ");
		query.append("ORDER BY t1.ID_TIPO_SECCION, t1.ID_TIPO_DETALLE, t2.ID_CONCEPTO");
		logger.finest("Query=" + query.toString());
		db2.executeQuery(query.toString());
		List detalles= new ArrayList();
		String llaveold="";
		while (db2.next()) {
			int tiposeccion= db2.getInt(1);
			int tipodetalle= db2.getInt(2);
			String tiponomina= db2.getString(3);
			int identidad= db2.getInt(4);
			int concepto= db2.getInt(5);
			String monto= db2.getString(6);
			String llave= tiponomina + tiposeccion + tipodetalle + concepto;
			if(!llave.equals(llaveold)){
				if (detalleTO != null){
					//agregando detalle anterior
					detalleTO.setMontos(montos);
					detalles.add(detalleTO);
				}
				detalleTO= new TipoDetalleTO();
				detalleTO.setTipoSeccion(tiposeccion);
				detalleTO.setTipoDetalle(tipodetalle);
				detalleTO.setTipoNomina(tiponomina.charAt(0));
				detalleTO.setRutEntidad(new Rut(identidad));
				detalleTO.setConceptoEgreso(concepto);
				montos= new ArrayList();
				montos.add(monto);
				
			}else{
				montos.add(monto);
			}
			llaveold= llave;
		}
		//Se agrega último detalleTO
		detalleTO.setMontos(montos);
		detalles.add(detalleTO);
		
		return detalles;
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
