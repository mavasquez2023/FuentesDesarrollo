

/*
 * @(#) EntidadesCPDAO.java    1.0 29-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.planillas.EntidadFolio;
import cl.araucana.core.util.Rut;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.ConectaDB2;

/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 29-07-2009 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> CLAUDIO LILLO AZORÍN <BR> clillo@laaraucana.cl </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD>   </TD>
 *            <TD align="center">  </TD>
 *            <TD>   </TD>
 *            <TD>  </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author CLAUDIO LILLO AZORÍN (clillo@laaraucana.cl)
 *
 * @version 1.0
 */
public class FolioEntidadesCPDAO implements DAO_Interface, Constants {
	private ConectaDB2 db2;
	private static Logger logger = LogManager.getLogger();
	
	public FolioEntidadesCPDAO(ConectaDB2 db2){
		this.db2= db2;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillas.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente

	}

	/* (sin Javadoc)
	 * @see cl.araucana.planillas.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	/* (sin Javadoc)
	 * @see cl.araucana.planillas.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		String cierre = (String)pk;
		StringBuffer query= new StringBuffer();
		query.append("select t4.id_ent_pagadora, nombre, folio_actual, folio_final, count(*) as NUM_PAGOS, t2.id_tipo_seccion ");
		query.append("from comprobante_pago t1, seccion t2, detalle_seccion t3, tipo_detalle t4, foliacion t5, entpagad t6, resprocie t7  ");
		query.append("WHERE id_estado  in ('4', '5') ");
		query.append("AND t1.cierre= ? ");
		//query.append("AND (t2.tipo_pago=1 OR (t2.tipo_pago=0 AND t3.tipo_pago=1))  ");
		query.append("AND t2.tipo_pago<>3 ");
		query.append("AND t3.tipo_pago in (1, 2) ");
		query.append("AND t1.id_codigo_barra =  t2.id_codigo_barra ");
		query.append("AND t2.id_codigo_barra =  t3.id_codigo_barra ");
		query.append("AND t2.id_tipo_seccion = t3.id_tipo_seccion ");
		query.append("AND t3.id_tipo_seccion = t4.id_tipo_seccion ");
		query.append("AND t3.id_detalle_seccion = t4.id_tipo_detalle ");
		query.append("AND t4.id_ent_pagadora = t5.id_entidad_pagadora ");
		query.append("AND t4.id_ent_pagadora = t6.id_ent_pagadora ");
		query.append("AND t1.id_codigo_barra =t7.id_codigo_barra ");
		query.append("AND t7.PWF=0 ");
		query.append("GROUP BY t4.id_ent_pagadora, nombre, folio_actual, folio_final, t2.id_tipo_seccion " ); 
		query.append("ORDER BY id_ent_pagadora");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, cierre);
		
		//Se ejecuta la query
		db2.executeQuery();
		int rutent_old=0;
		Map entidades= new HashMap();
		EntidadFolio entidad=null;
		while (db2.next()) {
			int rutent=db2.getInt(1);
			Rut rutEntidad= new Rut(rutent);
			String nombreEntidad=db2.getString(2);
			int folioActual= db2.getInt(3);
			int folioFinal= db2.getInt(4);
			int numPagos= db2.getInt(5);
			short tipoSeccion= db2.getShort(6);
			int deltaRango= numPagos* FACTOR_RANGO_FOLIO;
			if(rutent!= rutent_old){
				entidad= new EntidadFolio();
				entidad.setFolioActual(folioActual);
				//Se verifica si tipo sección en CCAF para aumentar rango de folios
				//ya que por cada planilla se reservan 5 folios consecutivos(para Credito, Leasing, Seguro y Dental).
				if(tipoSeccion==REMU_CCAF || tipoSeccion==GRATI_CCAF || tipoSeccion==RELIQ_CCAF){
					deltaRango=deltaRango*5;
				}
				entidad.setRangoFinal(folioActual + deltaRango);
				entidad.setFolioFinal(folioFinal);
				entidad.setDeltaRango(deltaRango);
				entidad.setRutEntidad(rutEntidad);
				entidad.setNombreEntidad(nombreEntidad);
				entidades.put(rutEntidad, entidad);
			}else{
				entidad.setRangoFinal(entidad.getRangoFinal() + deltaRango);
				entidad.setDeltaRango(entidad.getDeltaRango() + deltaRango);
			}
			rutent_old= rutent;
		}
		return entidades;
	}
	
	public int select(Rut rutEntidad) throws SQLException {
		int folioActual=0;
		StringBuffer query= new StringBuffer();
		query.append("select folio_actual ");
		query.append("from foliacion ");
		query.append("WHERE id_entidad_pagadora= ? ");
		query.append("AND folios_en_uso= 1 ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, rutEntidad.getNumber());
		
		//Se ejecuta la query
		db2.executeQuery();
		if (db2.next()) {
			folioActual= db2.getInt(1);
		}
		db2.closeStatement();
		return folioActual;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillas.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int update(Object obj) throws SQLException {
		EntidadFolio entidad = (EntidadFolio)obj;
		StringBuffer query= new StringBuffer();
		query.append("UPDATE foliacion ");
		query.append("SET folio_actual= ? ");
		query.append("WHERE id_entidad_pagadora= ? ");
		query.append("AND folios_en_uso= 1 ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, entidad.getRangoFinal());
		db2.setStatement(2, entidad.getRutEntidad().getNumber());
		logger.config("Entidad " + entidad.getNombreEntidad() + ", nuevo folio actual=" + entidad.getRangoFinal());
		//Se ejecuta la query
		return db2.executeUpdate();
	}

}

