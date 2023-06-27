/*
 * Creado el 13-01-2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package cl.araucana.cierrecpe.entidades.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.entidades.to.ArchivoEntidadTO;
import cl.araucana.cierrecpe.entidades.to.ComprobanteCPTO;
import cl.araucana.cierrecpe.entidades.to.EstadisticaPagoTO;
import cl.araucana.cierrecpe.entidades.to.InformeContableTO;
import cl.araucana.cierrecpe.entidades.to.PropuestaPagoTO;
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
public class PropuestaPagoDAO implements DAO_Interface{
	private ConectaDB2 db2;
	private static Logger logger = LogManager.getLogger();
	
	public PropuestaPagoDAO(ConectaDB2 db2){
		try {
			logger.info("PropuestaPagoDAO, verificando conexión:" + !db2.isClosed());
			this.db2= db2;
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(int paramperiodo, int paramcierre) throws SQLException {
		StringBuffer query= new StringBuffer();
		query.append("select T1.FOLIO_EGRESO, T1.PERIODO, T1.CIERRE, T1.ID_TIPO_SECCION, T2.CLAVE, T1.ID_TIPO_DETALLE, T4.NOMBRE, T1.ID_TIPO_NOMINA, T1.MONTO, T3.ID_ENT_PAGADORA, T1.ESTADO, T1.ID_CONCEPTO, ");
		query.append("T1.DEPOSITO, T1.ORIGEN, T4.GENERA_CHEQUE_SPL, T5.NOMBRE, T4.ID_CTA_CTE, T6.NOMBRE, T4.ID_CTA_CTE_SPL, T1.FECHA_CREACION, ");
		query.append("(	select 	SUM(T11.MONTO)  ");
		query.append("from 	PROPUESTA_PAGO T11, TIPO_SECCION T21 ");
		query.append("WHERE T11.PERIODO= T1.PERIODO ");
		query.append("AND T11.CIERRE= T1.CIERRE ");
		query.append("AND T11.ID_TIPO_SECCION= T21.ID_TIPO_SECCION ");
		query.append("AND T21.CLAVE=T2.CLAVE ");
		query.append("GROUP BY T21.CLAVE ");
		query.append(") AS TOTALPORCLAVE, ");
		query.append("(	select 	COUNT(*)  ");
		query.append("from 	PROPUESTA_PAGO T11, TIPO_SECCION T21 ");
		query.append("WHERE T11.PERIODO= T1.PERIODO ");
		query.append("AND T11.CIERRE= T1.CIERRE ");
		query.append("AND T11.ID_TIPO_SECCION= T21.ID_TIPO_SECCION ");
		query.append("AND T21.CLAVE=T2.CLAVE ");
		query.append("GROUP BY T21.CLAVE ");
		query.append(") AS CANTIDADPORCLAVE ");
		query.append("from PROPUESTA_PAGO T1, TIPO_SECCION T2, TIPO_DETALLE T3, ENTPAGAD T4, BANCO T5, BANCO T6 ");
		query.append("WHERE T1.PERIODO= ? ");
		//query.append("AND T1.ESTADO= ? ");
		if (paramcierre>0){
			query.append("AND T1.CIERRE= ? ");
		}
		query.append("AND T1.ID_TIPO_SECCION= T2.ID_TIPO_SECCION ");
		query.append("AND T1.ID_TIPO_SECCION= T3.ID_TIPO_SECCION ");
		query.append("AND T1.ID_TIPO_DETALLE= T3.ID_TIPO_DETALLE ");
		query.append("AND T1.ID_TIPO_NOMINA = T3.ID_TIPO_NOMINA ");
		query.append("AND T3.ID_ENT_PAGADORA= T4.ID_ENT_PAGADORA ");
		query.append("AND T4.ID_BANCO= T5.ID_BANCO ");
		query.append("AND T4.ID_BANCO_SPL= T6.ID_BANCO ");
		query.append("ORDER BY T1.CIERRE, T2.CLAVE, T4.NOMBRE ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, paramperiodo);
		//db2.setStatement(2, "G");
		if (paramcierre>0){
			db2.setStatement(2, paramcierre);
		}
		List cheques= new ArrayList();
		executeQuery(db2, cheques);
		return cheques;
	}
	
	public Object selectbyDeposito(int paramperiodo, int paramcierre) throws SQLException{
		StringBuffer query= new StringBuffer();
			//Se selecciona primero los cheques que sean  tipo de depósito = 'CHEQUE'
			query.append("select T1.FOLIO_EGRESO, T1.PERIODO, T1.CIERRE, T1.ID_TIPO_SECCION, T2.CLAVE, T1.ID_TIPO_DETALLE, T4.NOMBRE, T1.ID_TIPO_NOMINA, T1.MONTO, T3.ID_ENT_PAGADORA, T1.ESTADO, T1.ID_CONCEPTO, ");
			query.append("T1.DEPOSITO, T1.ORIGEN, T4.GENERA_CHEQUE_SPL, T5.NOMBRE, T4.ID_CTA_CTE, T6.NOMBRE, T4.ID_CTA_CTE_SPL, T1.FECHA_CREACION, ");
			query.append("(	select 	SUM(T11.MONTO)  ");
			query.append("from 	PROPUESTA_PAGO T11, TIPO_SECCION T21 ");
			query.append("WHERE T11.PERIODO= T1.PERIODO ");
			query.append("AND T11.CIERRE= T1.CIERRE ");
			query.append("AND T11.ID_TIPO_SECCION= T21.ID_TIPO_SECCION ");
			query.append("AND T21.CLAVE=T2.CLAVE ");
			query.append("AND T11.DEPOSITO= T1.DEPOSITO ");
			query.append("GROUP BY T11.DEPOSITO, T21.CLAVE ");
			query.append(") AS TOTALPORCLAVE, ");
			query.append("(	select 	COUNT(*)  ");
			query.append("from 	PROPUESTA_PAGO T11, TIPO_SECCION T21 ");
			query.append("WHERE T11.PERIODO= T1.PERIODO ");
			query.append("AND T11.CIERRE= T1.CIERRE ");
			query.append("AND T11.ID_TIPO_SECCION= T21.ID_TIPO_SECCION ");
			query.append("AND T21.CLAVE=T2.CLAVE ");
			query.append("AND T11.DEPOSITO= T1.DEPOSITO ");
			query.append("GROUP BY T11.DEPOSITO, T21.CLAVE ");
			query.append(") AS CANTIDADPORCLAVE ");
			query.append("from PROPUESTA_PAGO T1, TIPO_SECCION T2, TIPO_DETALLE T3, ENTPAGAD T4, BANCO T5, BANCO T6  ");
			query.append("WHERE T1.PERIODO= ? ");
			//query.append("AND T1.ESTADO= ? ");
			if (paramcierre>0){
				query.append("AND T1.CIERRE= ? ");
			}
			query.append("AND T1.ID_TIPO_SECCION= T2.ID_TIPO_SECCION ");
			query.append("AND T1.ID_TIPO_SECCION= T3.ID_TIPO_SECCION ");
			query.append("AND T1.ID_TIPO_DETALLE= T3.ID_TIPO_DETALLE ");
			query.append("AND T1.ID_TIPO_NOMINA = T3.ID_TIPO_NOMINA ");
			query.append("AND T3.ID_ENT_PAGADORA= T4.ID_ENT_PAGADORA ");
			query.append("AND T4.ID_BANCO= T5.ID_BANCO ");
			query.append("AND T4.ID_BANCO_SPL= T6.ID_BANCO ");
			query.append("AND T1.DEPOSITO= ?");
			query.append("ORDER BY T1.CIERRE, T1.DEPOSITO, T2.CLAVE, T4.NOMBRE  ");
			logger.finest("Query 1, Ver Propuesta por Depositos:" + query.toString());
			db2.prepareQuery(query.toString());
			db2.setStatement(1, paramperiodo);
			if (paramcierre>0){
				db2.setStatement(2, paramcierre);
				db2.setStatement(3, Constants.DEPOSITO_CHEQUE);
			}else{
				db2.setStatement(2, Constants.DEPOSITO_CHEQUE);
			}
			List cheques= new ArrayList();
			executeQuery(db2, cheques);
			
			
			//Segundo paso es seleccionar los tipo depositos= 'TRANSFERENCIA' pero agrupados por Entidad
			query= new StringBuffer();
			query.append("select 0, PERIODO, CIERRE, 99 AS TIPO_SECCION, 'xENTIDAD' as CLAVE, 0, NOMBRE_ENT, '-', sum(Monto) as TOTAL, RUT_ENT, '-', ");
			query.append("ID_CONCEPTO, DEPOSITO, ORIGEN, GENERA_CHEQUE_SPL, 0, 0, BANCO_SPL, CTA_SPL, FECHA_CREACION, ");
			query.append("(select sum(Monto) from PROPUESTA_PAGO where periodo= ? ");
			if (paramcierre>0){
				query.append("and cierre=? ");
			}
			query.append("and deposito= ?) as TOTAL_TRANF, 0 as CANTIDADPORENT ");
			query.append("from (select T1.PERIODO as PERIODO, T1.CIERRE as CIERRE, ");
			query.append("CASE WHEN T1.ID_CONCEPTO= 23060 THEN (select nombre from ENTPAGAD where id_ent_pagadora=96981130) ELSE T4.NOMBRE END as NOMBRE_ENT, ");
			query.append("sum(T1.MONTO) as Monto, ");
			query.append("CASE WHEN T1.ID_CONCEPTO= 23060 THEN 96981130 ELSE T3.ID_ENT_PAGADORA END as RUT_ENT, ");
			query.append("T1.ID_CONCEPTO as ID_CONCEPTO, T1.DEPOSITO AS DEPOSITO, T1.ORIGEN AS ORIGEN, T4.GENERA_CHEQUE_SPL AS GENERA_CHEQUE_SPL, ");
			query.append("CASE WHEN T1.ID_CONCEPTO= 23060 THEN (select B.NOMBRE from ENTPAGAD A, BANCO B where id_ent_pagadora=96981130 AND A.ID_BANCO_SPL= B.ID_BANCO) ELSE T5.NOMBRE END as BANCO_SPL, ");
			query.append("CASE WHEN T1.ID_CONCEPTO= 23060 THEN (select id_cta_cte_spl from ENTPAGAD where id_ent_pagadora=96981130) ELSE T4.ID_CTA_CTE_SPL END as CTA_SPL, ");
			query.append("T1.FECHA_CREACION as FECHA_CREACION, count(*) as CANTIDAD ");
			query.append("from PROPUESTA_PAGO T1, TIPO_SECCION T2, TIPO_DETALLE T3, ENTPAGAD T4, BANCO T5 ");
			query.append("WHERE T1.PERIODO= ? ");
			if (paramcierre>0){
				query.append("AND T1.CIERRE= ? ");
			}
			query.append("AND T1.ID_TIPO_SECCION= T2.ID_TIPO_SECCION ");
			query.append("AND T1.ID_TIPO_SECCION= T3.ID_TIPO_SECCION ");
			query.append("AND T1.ID_TIPO_DETALLE= T3.ID_TIPO_DETALLE ");
			query.append("AND T1.ID_TIPO_NOMINA = T3.ID_TIPO_NOMINA ");
			query.append("AND T3.ID_ENT_PAGADORA= T4.ID_ENT_PAGADORA ");
			query.append("AND T4.ID_BANCO_SPL= T5.ID_BANCO ");
			query.append("AND T1.DEPOSITO= ? ");
			query.append("GROUP BY T1.PERIODO, T1.CIERRE,  T4.NOMBRE, T3.ID_ENT_PAGADORA, T1.ID_CONCEPTO, ");
			query.append("T1.DEPOSITO, T1.ORIGEN, T4.GENERA_CHEQUE_SPL, T5.NOMBRE, T4.ID_CTA_CTE_SPL, T1.FECHA_CREACION) as Z ");
			query.append("GROUP BY Z.PERIODO, Z.CIERRE, Z.NOMBRE_ENT, Z.RUT_ENT, Z.ID_CONCEPTO, ");
			query.append("Z.DEPOSITO, Z.ORIGEN, Z.GENERA_CHEQUE_SPL, Z.BANCO_SPL, Z.CTA_SPL, Z.FECHA_CREACION ");
			query.append("ORDER BY Z.CIERRE, Z.NOMBRE_ENT  ");
			logger.finest("Query 2, Ver Propuesta por Depositos:" + query.toString());
			db2.prepareQuery(query.toString());
			db2.setStatement(1, paramperiodo);
			if (paramcierre>0){
				db2.setStatement(2, paramcierre);
				db2.setStatement(3, Constants.DEPOSITO_TRANSFERENCIA);
				db2.setStatement(4, paramperiodo);
				db2.setStatement(5, paramcierre);
				db2.setStatement(6, Constants.DEPOSITO_TRANSFERENCIA);
			}else{
				db2.setStatement(2, Constants.DEPOSITO_TRANSFERENCIA);
				db2.setStatement(3, paramperiodo);
				db2.setStatement(4, Constants.DEPOSITO_TRANSFERENCIA);
			}
			//Se ejecuta query pasando el mismo listado de 'cheques' para acumular resultado
			executeQuery(db2, cheques);
			
		return cheques;
	}
	
	private void executeQuery(ConectaDB2 db2, List cheques) throws SQLException{
		//Se ejecuta la query
		db2.executeQuery();
		while (db2.next()) {
			int folio= db2.getInt(1);
			int periodo= db2.getInt(2);
			int numcierre= db2.getInt(3);
			int seccion= db2.getInt(4);
			String seccion_desc= db2.getString(5);
			int detalle= db2.getInt(6);
			String nombre_entidad= db2.getString(7);
			String nomina= db2.getString(8);
			long monto= db2.getLong(9);
			int rutentidad= db2.getInt(10);
			String estado= db2.getString(11);
			int concepto= db2.getInt(12);
			String deposito= db2.getString(13);
			String origen= db2.getString(14);
			int generaChequeSPL= db2.getInt(15);
			String id_banco= db2.getString(16);
			String id_cta_cte= db2.getString(17);
			String id_banco_spl= db2.getString(18);
			String id_cta_cte_spl= db2.getString(19);
			Date fechaCreacion= db2.getDate(20);
			long totalSeccion= db2.getLong(21);
			int cantidadSeccion= db2.getInt(22);
			PropuestaPagoTO chequeTO= new PropuestaPagoTO();
			chequeTO.setFolioEgreso(folio);
			chequeTO.setPeriodo(periodo);
			chequeTO.setCierre(numcierre);
			chequeTO.setTipoSeccion(seccion);
			chequeTO.setDescripcionSeccion(seccion_desc);
			chequeTO.setTipoDetalle(detalle);
			if(concepto==23060){
				chequeTO.setRazonSocial(Constants.FILTER_NOMBRE_AFC);
			}else{
				chequeTO.setRazonSocial(nombre_entidad);
			}
			chequeTO.setTipoNomina(nomina.charAt(0));
			chequeTO.setMontoTotal(monto);
			chequeTO.setRut(new Rut(rutentidad));
			chequeTO.setEstado(estado.charAt(0));
			chequeTO.setConceptoTesoreria(concepto);
			chequeTO.setDeposito(deposito);
			chequeTO.setOrigen(origen.substring(0, 3));
			chequeTO.setFechaCreacion(new AbsoluteDate(fechaCreacion));
			chequeTO.setTotalSeccion(totalSeccion);
			chequeTO.setCantidadSeccion(cantidadSeccion);
			//Si forma de pago es full se verifica si se debe generar egreso en tesorería.
			if (deposito.equals(Constants.DEPOSITO_TRANSFERENCIA)  && generaChequeSPL==0){
				chequeTO.setGeneraEgresoSPL(false);
			}
			if(deposito.equals(Constants.DEPOSITO_TRANSFERENCIA)){
				chequeTO.setCodigoBanco(id_banco_spl);
				chequeTO.setCuentaCorriente(id_cta_cte_spl);
				chequeTO.setDeposito(Constants.DEPOSITO_TRANSFERENCIA_ABREV);
			}else{
				chequeTO.setCodigoBanco(id_banco);
				chequeTO.setCuentaCorriente(id_cta_cte);
			}
			cheques.add(chequeTO);
		}
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object selectNoCargados(int paramperiodo, int paramcierre, String option) throws SQLException {
		StringBuffer query= new StringBuffer();
		query.append("select T1.FOLIO_EGRESO, T1.PERIODO, T1.CIERRE, T1.ID_TIPO_SECCION, T2.CLAVE, T1.ID_TIPO_DETALLE, T4.NOMBRE, T1.ID_TIPO_NOMINA, T1.MONTO, T3.ID_ENT_PAGADORA, T1.ESTADO, T1.ID_CONCEPTO, ");
		query.append("T1.DEPOSITO, T1.ORIGEN,  T4.GENERA_CHEQUE_SPL, T4.ID_BANCO, T4.ID_CTA_CTE, T4.ID_BANCO_SPL, T4.ID_CTA_CTE_SPL, T1.FECHA_CREACION, ");
		query.append("(	select 	SUM(T11.MONTO)  ");
		query.append("from 	PROPUESTA_PAGO T11, TIPO_SECCION T21 ");
		query.append("WHERE T11.PERIODO= T1.PERIODO ");
		query.append("AND T11.CIERRE= T1.CIERRE ");
		query.append("AND T11.ID_TIPO_SECCION= T21.ID_TIPO_SECCION ");
		query.append("AND T21.CLAVE=T2.CLAVE ");
		query.append("GROUP BY T21.CLAVE ");
		query.append(") AS TOTALPORCLAVE, ");
		query.append("(	select 	COUNT(*)  ");
		query.append("from 	PROPUESTA_PAGO T11, TIPO_SECCION T21 ");
		query.append("WHERE T11.PERIODO= T1.PERIODO ");
		query.append("AND T11.CIERRE= T1.CIERRE ");
		query.append("AND T11.ID_TIPO_SECCION= T21.ID_TIPO_SECCION ");
		query.append("AND T21.CLAVE=T2.CLAVE ");
		query.append("GROUP BY T21.CLAVE ");
		query.append(") AS CANTIDADPORCLAVE ");
		query.append("from PROPUESTA_PAGO T1, TIPO_SECCION T2, TIPO_DETALLE T3, ENTPAGAD T4 ");
		query.append("WHERE T1.PERIODO= ? ");
		if(!option.equals("paso")){
			query.append("AND T1.ESTADO= ? ");
		}
		if (paramcierre>0){
			query.append("AND T1.CIERRE= ? ");
		}
		query.append("AND T1.ID_TIPO_SECCION= T2.ID_TIPO_SECCION ");
		query.append("AND T1.ID_TIPO_SECCION= T3.ID_TIPO_SECCION ");
		query.append("AND T1.ID_TIPO_DETALLE= T3.ID_TIPO_DETALLE ");
		query.append("AND T1.ID_TIPO_NOMINA = T3.ID_TIPO_NOMINA ");
		query.append("AND T3.ID_ENT_PAGADORA= T4.ID_ENT_PAGADORA ");
		query.append("ORDER BY T1.CIERRE, T2.CLAVE, T4.NOMBRE ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, paramperiodo);
		if(!option.equals("paso")){
			db2.setStatement(2, "G");
			if (paramcierre>0){
				db2.setStatement(3, paramcierre);
			}
		}else{
			if (paramcierre>0){
				db2.setStatement(2, paramcierre);
			}
		}
		
		//Se ejecuta la query
		db2.executeQuery();
		List cheques= new ArrayList();
		while (db2.next()) {
			int folio= db2.getInt(1);
			int periodo= db2.getInt(2);
			int numcierre= db2.getInt(3);
			int seccion= db2.getInt(4);
			String seccion_desc= db2.getString(5);
			int detalle= db2.getInt(6);
			String nombre_entidad= db2.getString(7);
			String nomina= db2.getString(8);
			long monto= db2.getLong(9);
			int rutentidad= db2.getInt(10);
			String estado= db2.getString(11);
			int concepto= db2.getInt(12);
			String deposito= db2.getString(13);
			String origen= db2.getString(14);
			int generaChequeSPL= db2.getInt(15);
			String id_banco= db2.getString(16);
			String id_cta_cte= db2.getString(17);
			String id_banco_spl= db2.getString(18);
			String id_cta_cte_spl= db2.getString(19);
			Date fechaCreacion= db2.getDate(20);
			long totalSeccion= db2.getLong(21);
			int cantidadSeccion= db2.getInt(22);
			PropuestaPagoTO chequeTO= new PropuestaPagoTO();
			chequeTO.setFolioEgreso(folio);
			chequeTO.setPeriodo(periodo);
			chequeTO.setCierre(numcierre);
			chequeTO.setTipoSeccion(seccion);
			chequeTO.setDescripcionSeccion(seccion_desc);
			chequeTO.setTipoDetalle(detalle);
			if(concepto==23060){
				chequeTO.setRazonSocial(Constants.FILTER_NOMBRE_AFC);
			}else{
				chequeTO.setRazonSocial(nombre_entidad);
			}
			chequeTO.setTipoNomina(nomina.charAt(0));
			chequeTO.setMontoTotal(monto);
			chequeTO.setRut(new Rut(rutentidad));
			chequeTO.setEstado(estado.charAt(0));
			chequeTO.setConceptoTesoreria(concepto);
			chequeTO.setDeposito(deposito);
			chequeTO.setOrigen(origen);
			chequeTO.setFechaCreacion(new AbsoluteDate(fechaCreacion));
			chequeTO.setTotalSeccion(totalSeccion);
			chequeTO.setCantidadSeccion(cantidadSeccion);
			//Si forma de pago es full se verifica si se debe generar egreso en tesorería.
			if (deposito.equals(Constants.DEPOSITO_TRANSFERENCIA) && generaChequeSPL==0){
				chequeTO.setGeneraEgresoSPL(false);
			}
			if(deposito.equals(Constants.DEPOSITO_TRANSFERENCIA)){
				chequeTO.setCodigoBanco(id_banco_spl);
				chequeTO.setCuentaCorriente(id_cta_cte_spl);
			}else{
				chequeTO.setCodigoBanco(id_banco);
				chequeTO.setCuentaCorriente(id_cta_cte);
			}
			cheques.add(chequeTO);
		}
		return cheques;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#selectDetalle(java.lang.Object)
	 */
	public Object selectDetalle(int folioEgreso) throws SQLException {
		StringBuffer query= new StringBuffer();
		query.append("select FOLIO_INGRESO, RUT_EMPRESA, MONTO_DETALLE ");
		query.append("from DETALLE_PROPUESTA_PAGO ");
		query.append("WHERE FOLIO_EGRESO= ? " );
		query.append("ORDER BY 1, 2");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, folioEgreso);
		
		//Se ejecuta la query
		db2.executeQuery();
		List folios= new ArrayList();
		while (db2.next()) {
			int folio= db2.getInt(1);
			int rutempresa= db2.getInt(2);
			long monto= db2.getLong(3);
			ComprobanteCPTO detchequeTO= new ComprobanteCPTO();
			detchequeTO.setFolioIngreso(folio);
			detchequeTO.setMonto(monto);
			detchequeTO.setRutEmpresa(new Rut(rutempresa));
			folios.add(detchequeTO);
		}
		if(folios.size()==0){
			return null;
		}
		return folios;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#selectSecciones(java.lang.Object)
	 */
	public Object selectSecciones(int periodo) throws SQLException {
		StringBuffer query= new StringBuffer();
		query.append("select T2.CLAVE,  (case when T2.CLAVE='CAJA' OR T2.CLAVE='APV' then T4.NOMBRE else '' end) as NOMBRE ");
		query.append("from PROPUESTA_PAGO T1, TIPO_SECCION T2, TIPO_DETALLE T3, ENTPAGAD T4 ");
		query.append("WHERE T1.PERIODO= ? " );
		query.append("AND T1.ID_TIPO_SECCION= T2.ID_TIPO_SECCION ");
		query.append("AND T1.ID_TIPO_SECCION= T3.ID_TIPO_SECCION ");
		query.append("AND T1.ID_TIPO_DETALLE= T3.ID_TIPO_DETALLE ");
		query.append("AND T1.ID_TIPO_NOMINA = T3.ID_TIPO_NOMINA ");
		query.append("AND T3.ID_ENT_PAGADORA= T4.ID_ENT_PAGADORA ");
		query.append("GROUP BY T2.CLAVE,  (case when T2.CLAVE='CAJA' OR T2.CLAVE='APV' then T4.NOMBRE else '' end) ");
		query.append("ORDER BY 1, 2 ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, periodo);
		
		//Se ejecuta la query
		db2.executeQuery();
		List secciones= new ArrayList();
		while (db2.next()) {
			String tipoSeccion= db2.getString(1);
			String nombreEntidad= db2.getString(2);
			ArchivoEntidadTO seccion= new ArchivoEntidadTO();
			seccion.setTipoSeccion(tipoSeccion);
			seccion.setNombreEntidad(nombreEntidad);
			secciones.add(seccion);
		}
		if(secciones.size()==0){
			return null;
		}
		return secciones;
	}
	
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj) {
		int ninsert=0;
		try {
			PropuestaPagoTO chequeTO= (PropuestaPagoTO) obj;
			StringBuffer query= new StringBuffer();
			query.append("insert into PROPUESTA_PAGO ");
			query.append("(FOLIO_EGRESO, PERIODO, CIERRE, ID_TIPO_SECCION, ID_TIPO_DETALLE, ID_TIPO_NOMINA, MONTO, ID_ENT_PAGADORA, ESTADO, ID_CONCEPTO, DEPOSITO, ORIGEN) ");
			query.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			logger.finest("Query=" + query.toString());
			logger.finest("Folio egreso=" + chequeTO.getFolioEgreso());
			db2.prepareQuery(query.toString());
			db2.setStatement(1, chequeTO.getFolioEgreso());
			db2.setStatement(2, chequeTO.getPeriodo());
			db2.setStatement(3, chequeTO.getCierre());
			db2.setStatement(4, chequeTO.getTipoSeccion());
			db2.setStatement(5, chequeTO.getTipoDetalle());
			db2.setStatement(6, chequeTO.getTipoNomina());
			db2.setStatement(7, chequeTO.getMontoTotal());
			db2.setStatement(8, chequeTO.getRut().getNumber());
			db2.setStatement(9, chequeTO.getEstado()); //GENERADO
			db2.setStatement(10, chequeTO.getConceptoTesoreria());
			db2.setStatement(11, chequeTO.getDeposito());
			db2.setStatement(12, chequeTO.getOrigen());
			db2.executeUpdate();
			if (chequeTO.getEstado()=='G' && !chequeTO.getDetalle().isEmpty()){
				ninsert= insertDetalle(chequeTO.getDetalle());
			}
		} catch (SQLException e) {
			logger.severe("Error, mensaje=" + e.getMessage());
			e.printStackTrace();
			ninsert=0;
		}
		return ninsert;
	}

	private int insertDetalle(Object obj){
		int ndetalle=0;
		try {
			Collection listdetalleCheques= (List) obj;
			for (Iterator detalle = listdetalleCheques.iterator(); detalle.hasNext();) {
				ComprobanteCPTO detchequeTO = (ComprobanteCPTO) detalle.next();
				StringBuffer query= new StringBuffer();
				query.append("insert into DETALLE_PROPUESTA_PAGO ");
				query.append("(FOLIO_EGRESO, FOLIO_INGRESO, MONTO_DETALLE, RUT_EMPRESA) ");
				query.append("VALUES(?, ?, ?, ?)");
				logger.finest("Query=" + query.toString());
				logger.finest("Folio egreso=" + detchequeTO.getChequeEntidadTO().getFolioEgreso() + ", Folio ingreso=" + detchequeTO.getFolioIngreso());
				db2.prepareQuery(query.toString());
				db2.setStatement(1, detchequeTO.getChequeEntidadTO().getFolioEgreso());
				db2.setStatement(2, detchequeTO.getFolioIngreso());
				db2.setStatement(3, detchequeTO.getMonto());
				db2.setStatement(4, detchequeTO.getRutEmpresa().getNumber());
				ndetalle+= db2.executeUpdate();
			}
		} catch (SQLException e) {
			logger.severe("Error, mensaje=" + e.getMessage());
			e.printStackTrace();
			return 0;
		}
		return ndetalle;
	}
	
	public int insertResumenCierre(int periodo, int cierre){
		int ndetalle=0;
		try {
			StringBuffer query= new StringBuffer();
			query.append("insert into RESUMEN_PROCESOS_CIERRE ");
			query.append("select t1.periodo, t1.cierre, t1.id_tipo_nomina, t2.folio_ingreso, t3.id_codigo_barra, t3.forma_pago, ");
			//query.append("sum(t2.monto_detalle), t3.n_trabajadores, 0, -1, -1, -1, t3.pagado, -1 ");
			query.append("t3.total, t3.n_trabajadores, 0, -1, -1, -1, t3.pagado, -1 ");
			query.append("from propago t1, detpropago t2, comprobante_pago t3 ");
			query.append("where t1.folio_egreso= t2.folio_egreso ");
			query.append("and t2.folio_ingreso=t3.folio_tesoreria ");
			query.append("and t1.periodo= ? ");
			query.append("and t1.cierre= ? ");
			query.append("group by t1.periodo, t1.cierre, t1.id_tipo_nomina, t2.folio_ingreso, t3.id_codigo_barra, t3.forma_pago, t3.total, t3.n_trabajadores, t3.pagado ");
			query.append("order by t1.id_tipo_nomina desc, t2.folio_ingreso ");
			logger.finest("Query=" + query.toString() + ", periodo=" + periodo + ", cierre=" + cierre);
			System.out.println("query:" + query.toString()+ ", periodo=" + periodo + ", cierre=" + cierre);
			db2.prepareQuery(query.toString());
			db2.setStatement(1, periodo);
			db2.setStatement(2, cierre);
			ndetalle= db2.executeUpdate();
		} catch (SQLException e) {
			logger.severe("Error, mensaje=" + e.getMessage());
			e.printStackTrace();
			return 0;
		}
		return ndetalle;
	}
	
	public void updateComprobantesTGR(int periodo, int cierre) throws SQLException {
		
		StringBuffer query= new StringBuffer();
		query.append("update RESUMEN_PROCESOS_CIERRE set TGR=0, NCR=0 where folio_ingreso in ( ");
		query.append("select t0.folio_ingreso ");
		query.append("from RESUMEN_PROCESOS_CIERRE t0 ");
		query.append("left join NOMINARE t1 ");
		query.append("on t0.id_codigo_barra= t1.id_codigo_barra ");
		query.append("left join NOMINAGR t2 ");
		query.append("on t0.id_codigo_barra= t2.id_codigo_barra ");
		query.append("left join NOMINARE t3 ");
		query.append("on t0.id_codigo_barra= t2.id_codigo_barra ");
		query.append(", DETALLE_SECCION t4  ");
		query.append("WHERE ((t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("AND t4.id_tipo_seccion in(1, 3) ");
		query.append("AND t4.m2>0 ) ");
		query.append("OR  (t2.id_codigo_barra= t4.id_codigo_barra ");
		query.append("AND t4.id_tipo_seccion in(40, 42) ");
		query.append("AND t4.m1>0 ) ");
		query.append("OR (t3.id_codigo_barra= t4.id_codigo_barra ");
		query.append("AND t4.id_tipo_seccion in(20, 22) ");
		query.append("AND t4.m1>0 )) ");
		query.append("AND t4.tipo_pago = 1 ");
		query.append("and t0.periodo= ? ");
		query.append("AND t0.cierre = ? ");
		query.append("group by t0.folio_ingreso ");
		query.append(") ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, periodo);
		db2.setStatement(2, cierre);
		//Se ejecuta la query
		db2.executeUpdate();
		
		/*
		//REMU
		StringBuffer query= new StringBuffer();
		query.append("update RESUMEN_PROCESOS_CIERRE set TGR=0 where folio_ingreso in ( ");
		query.append("select t1.folio_tesoreria ");
		query.append("from RESUMEN_PROCESOS_CIERRE t1, NOMINARE t2, SECCION t3 ");
		query.append("WHERE t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("AND t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.periodo= ? ");
		query.append("AND t1.cierre = ? ");
		query.append("AND t3.tipo_pago in (0, 1) ");
		query.append("AND t3.id_tipo_seccion in(1, 3) ");
		query.append("AND t3.m2>0 ");
		query.append(") ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, periodo);
		db2.setStatement(2, cierre);
		//Se ejecuta la query
		db2.executeQuery();
		
		//GRATI
		query= new StringBuffer();
		query.append("update RESUMEN_PROCESOS_CIERRE set TGR=0 where folio_ingreso in ( ");
		query.append("select t1.folio_tesoreria ");
		query.append("from RESUMEN_PROCESOS_CIERRE t1, NOMINAGR t2, SECCION t3 ");
		query.append("WHERE t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("AND t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.periodo= ? ");
		query.append("AND t1.cierre = ? ");
		query.append("AND t3.tipo_pago in (0, 1) ");
		query.append("AND t3.id_tipo_seccion in(40, 42) ");
		query.append("AND t3.m1>0 ");
		query.append(") ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, periodo);
		db2.setStatement(2, cierre);
		//Se ejecuta la query
		db2.executeQuery();
		
		//RELIQ
		query= new StringBuffer();
		query.append("update RESUMEN_PROCESOS_CIERRE set TGR=0 where folio_ingreso in ( ");
		query.append("select t1.folio_tesoreria ");
		query.append("from RESUMEN_PROCESOS_CIERRE t1, NOMINARA t2, SECCION t3 ");
		query.append("WHERE t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("AND t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.periodo= ? ");
		query.append("AND t1.cierre = ? ");
		query.append("AND t3.tipo_pago in (0, 1) ");
		query.append("AND t3.id_tipo_seccion in(20, 22) ");
		query.append("AND t3.m1>0 ");
		query.append(") ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, periodo);
		db2.setStatement(2, cierre);
		//Se ejecuta la query
		db2.executeQuery();
		*/
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecpent.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int updateArchivosImpresion(int periodo, int cierre) throws SQLException {

		StringBuffer query= new StringBuffer();
		query.append("update RESUMEN_PROCESOS_CIERRE ");
		query.append("set AIA= 0 ");
		query.append("where folio_ingreso in ( ");
		query.append("select t0.folio_ingreso ");
		query.append("from RESUMEN_PROCESOS_CIERRE t0 ");
		query.append("left join NOMINARE t1 ");
		query.append("on t0.id_codigo_barra= t1.id_codigo_barra ");
		query.append("left join NOMINAGR t2 ");
		query.append("on t0.id_codigo_barra= t2.id_codigo_barra ");
		query.append("left join NOMINADC t3 ");
		query.append("on t0.id_codigo_barra= t3.id_codigo_barra ");
		query.append("left join NOMINARA t4 ");
		query.append("on t0.id_codigo_barra= t4.id_codigo_barra ");
		query.append(", CONVENIO t5, GRUPOCONV t6, OPCIONPROC t7 ");
		query.append("where ((t1.id_empresa= t5.id_empresa and t1.id_convenio= t5.id_convenio) ");
		query.append("or (t2.id_empresa= t5.id_empresa and t2.id_convenio= t5.id_convenio) ");
		query.append("or (t3.id_empresa= t5.id_empresa and t3.id_convenio= t5.id_convenio) ");
		query.append("or (t4.id_empresa= t5.id_empresa and t4.id_convenio= t5.id_convenio)) ");
		query.append("and t5.id_grupo_convenios=t6.id_grupo_convenios ");
		query.append("and t6.id_opcion= t7.id_opcion ");
		query.append("and t7.imprimir_planillas= 1 ");
		query.append("and t0.periodo= ? ");
		query.append("and t0.cierre= ? ");
		query.append("group by t0.folio_ingreso ");
		query.append(") ");
		db2.prepareQuery(query.toString());
		db2.setStatement(1, periodo);
		db2.setStatement(2, cierre);
		logger.finest("Query=" + query.toString());
		//return db2.executeUpdate();
		return 1;
	}

	/* (sin Javadoc)
	 * @see cl.araucana.cierrecpent.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int updateCentralizacion(int periodo, int cierre) throws SQLException {

		StringBuffer query= new StringBuffer();
		query.append("update RESUMEN_PROCESOS_CIERRE ");
		query.append("set CEN= 0 ");
		query.append("where folio_ingreso in ( ");
		query.append("select t1.folio_ingreso ");
		query.append("from RESUMEN_PROCESOS_CIERRE t1 left join NOMINARE t2 ");
		query.append("on t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("left join NOMINAGR t3 ");
		query.append("on t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("left join NOMINADC t4 ");
		query.append("on t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("left join NOMINARA t5 ");
		query.append("on t1.id_codigo_barra= t5.id_codigo_barra ");
		query.append("where t1.periodo= ? ");
		query.append("and t1.cierre= ? ");
		query.append(") ");
		db2.prepareQuery(query.toString());
		db2.setStatement(1, periodo);
		db2.setStatement(2, cierre);
		logger.finest("Query=" + query.toString());
		return db2.executeUpdate();
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#update(java.lang.Object)
	 * Debido a la integridad referencial, para actualizar el folio se debe
	 * ingresar nuevo registro asociado al folio egreso real
	 * y luego se elimina registro con folio correlativo.
	 */
	public int update(Object obj) throws SQLException {
		PropuestaPagoTO chequeTO= (PropuestaPagoTO) obj;
		int rtdo=0;
		insert(chequeTO);
		
		String query= "update DETALLE_PROPUESTA_PAGO set FOLIO_EGRESO= ? where FOLIO_EGRESO= ? ";
		db2.prepareQuery(query);
		db2.setStatement(1, chequeTO.getFolioEgreso());
		db2.setStatement(2, chequeTO.getFolioTemporal());
		logger.finest("Query=" + query.toString());
		logger.finest("param 1=" + chequeTO.getFolioEgreso() + ", param2=" + chequeTO.getFolioTemporal());
		db2.executeUpdate();
		
		delete(chequeTO);
		
		return rtdo;
		
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int updateEstado(Object obj, char estado) throws SQLException {
		PropuestaPagoTO chequeTO= (PropuestaPagoTO) obj;
	
		String query= "update PROPUESTA_PAGO set ESTADO= ? where FOLIO_EGRESO= ? ";
		db2.prepareQuery(query);
		db2.setStatement(1, estado);
		db2.setStatement(2, chequeTO.getFolioEgreso());
		logger.finest("Query=" + query.toString());
		logger.finest("param 1=" + estado + ", param2=" + chequeTO.getFolioEgreso());
		return db2.executeUpdate();
	}
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int updateEstado(Object obj, char estado, String deposito) throws SQLException {
		PropuestaPagoTO chequeTO= (PropuestaPagoTO) obj;
	
		String query= "update PROPUESTA_PAGO set ESTADO= ?, TIPO_DEPOSITO= ? where FOLIO_EGRESO= ? ";
		db2.prepareQuery(query);
		db2.setStatement(1, estado);
		db2.setStatement(2, deposito);
		db2.setStatement(3, chequeTO.getFolioEgreso());
		logger.finest("Query=" + query.toString());
		logger.finest("param 1=" + estado + ", param2=" + chequeTO.getFolioEgreso());
		return db2.executeUpdate();
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int updateEstado(int periodo, int cierre, String tipoSeccion, char estado) throws SQLException {
	
		StringBuffer query= new StringBuffer();
		query.append("update PROPUESTA_PAGO ");
		query.append("set estado= ? ");
		query.append("where periodo= ? ");
		query.append("and cierre= ? ");
		query.append("and estado!= 'I' ");
		query.append("and id_tipo_seccion in ( ");
		query.append("select id_tipo_seccion ");
		query.append("from TIPO_SECCION ");
		query.append("where clave= ? ");
		query.append(") ");
		db2.prepareQuery(query.toString());
		db2.setStatement(1, estado);
		db2.setStatement(2, periodo);
		db2.setStatement(3, cierre);
		db2.setStatement(4, tipoSeccion);
		logger.finest("Query=" + query.toString());
		return db2.executeUpdate();
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(int periodo, int cierre) throws SQLException {
		String query= "delete from PROPUESTA_PAGO where periodo= ? and cierre= ? ";
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query);
		db2.setStatement(1, periodo);
		db2.setStatement(2, cierre);
		db2.executeUpdate();
		deleteResumen(periodo, cierre);
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#delete(java.lang.Object)
	 */
	private void deleteResumen(int periodo, int cierre) throws SQLException {
		String query= "delete from RESUMEN_PROCESOS_CIERRE where periodo= ? and cierre= ? ";
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query);
		db2.setStatement(1, periodo);
		db2.setStatement(2, cierre);
		db2.executeUpdate();
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws SQLException {
		PropuestaPagoTO chequeTO= (PropuestaPagoTO) obj;
		String query="";
		int folio= 0;  
		if(chequeTO.getEstado()=='G'){
			folio= chequeTO.getFolioEgreso();
		}else{
			folio= chequeTO.getFolioTemporal();
		}
		query= "delete from PROPUESTA_PAGO where FOLIO_EGRESO= ? ";
		logger.finest("Query=" + query.toString());
		logger.finest("param=" + folio);
		db2.prepareQuery(query);
		db2.setStatement(1, folio);
		db2.executeUpdate();
	}
	
//	rescatando lista cierres disponibles
	public Collection selectListCierres(int periodo) throws SQLException{

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

//	rescatando max cierre disponible
	public int selectMaxCierre(int periodo) throws SQLException{
		int cierre=0;
		StringBuffer query= new StringBuffer();
		query.append("select MAX(CIERRE)+1 ");
		query.append("from RESPROCIE ");
		query.append("WHERE PERIODO= ? ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, periodo);
		db2.executeQuery();
		if (db2.next()){
			cierre= db2.getInt(1);
		}
		db2.closeStatement();
		return cierre;
	}
	
//	rescatando lista cierres disponibles para cargar Subsistemas(con estado Cargado para el periodo actual del sistema)
	public Collection selectListCierresCargados(int periodo) throws SQLException{

		List cierres=null;
		String query= "select CIERRE from PROPUESTA_PAGO WHERE PERIODO= ? AND ESTADO='C' GROUP BY CIERRE ORDER BY CIERRE ";
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query);
		db2.setStatement(1, periodo);
		db2.executeQuery();
		cierres = new ArrayList();
		while (db2.next()){
			int cierre= db2.getInt(1);
			cierres.add(new Integer(cierre));
		}
		return cierres;
	}
	
//	rescatando ultimo cierre por forma de pago disponible
	public int selectCierrePendiente(int periodo) throws SQLException{
		int cierre=0;
		StringBuffer query= new StringBuffer();
		query.append("SELECT max(cierre) ");
		query.append("FROM PROPUESTA_PAGO ");
		query.append("WHERE periodo= ? ");
		query.append("AND estado='G' ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, periodo);
		db2.executeQuery();
		while (db2.next()){
			cierre= db2.getInt(1);
		}
		return cierre;
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
	
	//rescatando estadisticas del periodo
	public Collection selectEstadistica() throws SQLException{
		EstadisticaPagoTO estad=null;
		List estadisticas=null;
		StringBuffer query= new StringBuffer();
		query.append("SELECT A.periodo, ");
		query.append("A.tipo_egreso, ");
		query.append("sum(A.pagos) as CANT_PAGOS, ");
		query.append("sum(A.SUMA) as TOTAL ");
		query.append("FROM( " );
		query.append("select t1.periodo,  ");
		query.append("t1.deposito as tipo_egreso, ");
		query.append("t1.id_ent_pagadora as Entidad, ");
		query.append("(case when t1.deposito='CHEQUE' then count('1') else 1 end) as pagos, ");
		query.append("sum(t1.monto) as suma ");
		query.append("from propago t1, entpagad t2 ");
		query.append("where t1.id_ent_pagadora= t2.id_ent_pagadora ");
		query.append("and t1.id_ent_pagadora != 70016160 ");
		query.append("group by t1.periodo, t1.deposito, t1.id_ent_pagadora ");
		query.append(") as A ");
		query.append("GROUP BY A.periodo, A.tipo_egreso ");
		query.append("ORDER BY A.periodo desc, A.tipo_egreso ");
		logger.finest("Query=" + query.toString());
		db2.executeQuery(query.toString());
		estadisticas = new ArrayList();
		int periodo_old=0;
		while (db2.next()){
			int periodo= db2.getInt(1);
			String tipo_egreso= db2.getString(2);
			int cantidadPagos= db2.getInt(3);
			long total= db2.getLong(4);
			
			if(periodo!= periodo_old){
				if (periodo_old!=0){
					estadisticas.add(estad);
				}
				estad= new EstadisticaPagoTO();
				estad.setPeriodo(periodo);
			}
			if(tipo_egreso.equals(Constants.DEPOSITO_TRANSFERENCIA)){
				estad.setTotalSPL(total);
				estad.setCantidadPagosSPL(cantidadPagos);
			}else{
				estad.setTotalCheque(total);
				estad.setCantidadPagosCheque(cantidadPagos);
			}
			periodo_old= periodo;
		}
		//Agregando ultima estadistica
		estadisticas.add(estad);
		return estadisticas;
	}
	
	public boolean selectIsConPlanillas(int periodo, int cierre) throws SQLException{
		StringBuffer query= new StringBuffer();
		int cantidad=0;
		query.append("SELECT count(*) ");
		query.append("FROM resumen_procesos_cierre ");
		query.append("WHERE periodo= ? ");
		if(cierre>0){
			query.append("AND cierre= ? ");
		}
		query.append("AND pwf=0 ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		//Se ejecuta la query
		db2.setStatement(1, periodo);
		if(cierre>0){
			db2.setStatement(2, cierre);
		}
		System.out.println("Query=" + query.toString() + ", periodo=" + periodo + ", cierre=" + cierre);
		db2.executeQuery();
		if (db2.next()) {
			cantidad= db2.getInt(1);
		}
		db2.closeStatement();
		//System.out.println("cantidad=" + cantidad);
		if (cantidad==0){
			return true;
		}
		return false;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	public synchronized int getNumFolio()throws SQLException{
		int folio=-1;
		String query= "select numfolio from Folio";
		logger.finest("Query=" + query);
		db2.executeQuery(query);
		if (db2.next()){
			folio= Integer.parseInt(db2.getString(1));
			//actualizando el numero de folio en 1
			query= "update Folio set numFolio= ?";
			logger.finest("Query=" + query);
			db2.prepareQuery(query);
			db2.setStatement(1, folio+1);
			db2.executeUpdate();
		}
		return folio+1;
	}
	
	public Object select(int periodo) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("select periodo,  ");
		query.append("case when id_concepto=23060 then 'AFC' else c.clave end as seccion, ");
		query.append("id_concepto, id_ent_pagadora, a.folio_egreso, id_tipo_nomina, monto, rut_empresa, folio_ingreso, monto_detalle ");
		query.append("from propago a, detpropago b,  tipo_seccion c ");
		query.append("where a.folio_egreso= b.folio_egreso ");
		query.append("and a.id_tipo_seccion = c.id_tipo_seccion ");
		query.append("and periodo= ? and id_ent_pagadora!= 70016160 ");
		//A transferencias se asignan folios pequeños
		query.append("and a.folio_egreso <10000000 "); //folio Tesorería actual va en 26MM
		query.append("order by seccion, id_ent_pagadora, a.folio_egreso, folio_ingreso ");
        logger.fine("Query=" + query.toString());
        db2.prepareQuery(query.toString());
        db2.setStatement(1, periodo);
        db2.executeQuery();
        List transferencias= new ArrayList();
        while (db2.next()) {
        	int periodoc= db2.getInt(1);
        	String clave= db2.getString(2);
        	int conceptoTesoreria= db2.getInt(3);
        	int rutEntidad= db2.getInt(4);
        	int folioEgreso= db2.getInt(5);
        	char tipoNomina= db2.getString(6).charAt(0);
        	long montoEgreso= db2.getLong(7);
        	int rutEmpresa= db2.getInt(8);
        	int folioIngreso= db2.getInt(9);
        	long montoIngreso= db2.getLong(10);
        	InformeContableTO informe= new InformeContableTO();
        	informe.setPeriodo(periodo);
        	informe.setClave(clave);
        	informe.setConceptoTesoreria(conceptoTesoreria);
        	informe.setRutEntidad(new Rut(rutEntidad));
        	informe.setFolioEgreso(folioEgreso);
        	informe.setTipoNomina(tipoNomina);
        	informe.setMontoEgreso(montoEgreso);
        	informe.setRutEmpresa(new Rut(rutEmpresa));
        	informe.setFolioIngreso(folioIngreso);
        	informe.setMontoIngreso(montoIngreso);
        	transferencias.add(informe);
        }
        return transferencias;
	}
}
