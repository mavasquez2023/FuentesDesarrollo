

/*
 * @(#) EmpresaCPDAO.java    1.0 10-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.to.DetalleComprobantesTO;
import cl.araucana.cierrecpe.empresas.to.EstadisticaPagoTO;
import cl.araucana.cierrecpe.empresas.to.ResumenCierreComprobantesTO;
import cl.araucana.core.util.AbsoluteDate;
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
 *            <TD> 10-07-2009 </TD>
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
public class ResumenCierreCPDAO implements DAO_Interface {
private ConectaDB2 db2;
private static Logger logger = LogManager.getLogger();	
	
	public ResumenCierreCPDAO(ConectaDB2 db2){
		this.db2= db2;
		//Se abre segunda conexión para rescatar detalle de cada comprobante sin perder resultado de primera conexion almacenada en var db2
		//CPDAO cpDAO= new CPDAO();
	}
	
	public Collection selectCountResumenHistorico(int periodo) throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("SELECT cierre, id_tipo_nomina, ");
		query.append("sum(1) as comprobantes,  ");
		query.append("sum((case when pwf=0 then 1 else 0 end)) as sinplanillas, ");
		query.append("sum((case when tgr=0 then 1 else 0 end)) as sintgr, ");
		query.append("sum((case when aia=0 then 1 else 0 end)) as sinaia, ");
		query.append("sum((case when cen=0 then 1 else 0 end)) as sincen, ");
		query.append("sum((case when (tgr=0 or tgr=1) then 1 else 0 end)) as n_tgr, ");
		query.append("sum((case when (aia=0 or aia=1) then 1 else 0 end)) as n_aia, ");
		query.append("sum((case when (cen=0 or cen=1) then 1 else 0 end)) as n_cen, ");
		query.append("sum(total), sum(n_trabajadores)  ");
		//query.append("forma_pago  ");
		query.append("FROM resumen_procesos_cierre ");
		query.append("WHERE periodo= ? ");
		query.append("GROUP BY cierre, id_tipo_nomina "); //, forma_pago ");
		query.append("ORDER BY cierre, id_tipo_nomina desc ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		//Se ejecuta la query
		db2.setStatement(1, periodo);
		db2.executeQuery();
		List cierres= new ArrayList();
		while (db2.next()) {
			int cierre= db2.getInt(1);
			String tipoNomina= db2.getString(2);
			int numeroComprobantes= db2.getInt(3);
			int sinPlanillas= db2.getInt(4);
			int sinTGR= db2.getInt(5);
			int sinAIA= db2.getInt(6);
			int sinCEN= db2.getInt(7);
			int numTGR= db2.getInt(8);
			int numAIA= db2.getInt(9);
			int numCEN= db2.getInt(10);
			long total= db2.getLong(11);
			int numTrabajadores= db2.getInt(12);
			//int formaPago= db2.getInt(13);
			ResumenCierreComprobantesTO resumen= new ResumenCierreComprobantesTO();
			resumen.setCierre(cierre);
			resumen.setTipoNomina(tipoNomina);
			resumen.setNumeroComprobantes(numeroComprobantes);
			resumen.setNumeroComprobantesTGR(numTGR);
			resumen.setNumeroComprobantesAIA(numAIA);
			resumen.setNumeroComprobantesCEN(numCEN);
			resumen.setSinPlanillas(sinPlanillas);
			resumen.setSinTGR(sinTGR);
			resumen.setSinAIA(sinAIA);
			resumen.setSinCEN(sinCEN);
			resumen.setTotal(total);
			resumen.setNumTrabajadores(numTrabajadores);
			//resumen.setFormaPago(formaPago);
			cierres.add(resumen);
		}
		return cierres;
	}
	
	public Collection selectComprobantesSinPlanillas(int periodo, int cierre) throws SQLException {
		
		StringBuffer query= new StringBuffer();
		query.append("select id_codigo_barra, total, n_trabajadores, case when forma_pago in (1, 3) then 'SPL' else 'MIXTO' end, pagado, pwf, tgr, aia, cen ");
		query.append("from RESUMEN_PROCESOS_CIERRE ");
		query.append("WHERE periodo =? ");
		query.append("AND cierre =? ");
		query.append("AND pwf = 0 ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, periodo);
		db2.setStatement(2, cierre);
		//Se ejecuta la query
		db2.executeQuery();
		List comprobantes= new ArrayList();
		while(db2.next()){
			DetalleComprobantesTO comprobante= new DetalleComprobantesTO();
			comprobante.setCodigoBarra(db2.getLong(1));
			comprobante.setTotal(db2.getInt(2));
			comprobante.setNumeroTrabajadores(db2.getInt(3));
			comprobante.setFormaPago(db2.getString(4));
			comprobante.setFechaPago(new AbsoluteDate(db2.getDate(5)));
			comprobante.setPwf(db2.getShort(6));
			comprobante.setTgr(db2.getShort(7));
			comprobante.setAia(db2.getShort(8));
			comprobante.setCen(db2.getShort(9));
			comprobantes.add(comprobante);
		}
		return comprobantes;
	}
	
public Collection selectComprobantesSinTGR(int periodo, int cierre) throws SQLException {
		
		StringBuffer query= new StringBuffer();
		query.append("select id_codigo_barra, total, n_trabajadores, case when forma_pago in (1, 3) then 'SPL' else 'MIXTO' end, pagado, pwf, tgr, aia, cen ");
		query.append("from RESUMEN_PROCESOS_CIERRE ");
		query.append("WHERE periodo =? ");
		query.append("AND cierre =? ");
		query.append("AND tgr = 0 ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, periodo);
		db2.setStatement(2, cierre);
		//Se ejecuta la query
		db2.executeQuery();
		List comprobantes= new ArrayList();
		while(db2.next()){
			DetalleComprobantesTO comprobante= new DetalleComprobantesTO();
			comprobante.setCodigoBarra(db2.getLong(1));
			comprobante.setTotal(db2.getInt(2));
			comprobante.setNumeroTrabajadores(db2.getInt(3));
			comprobante.setFormaPago(db2.getString(4));
			comprobante.setFechaPago(new AbsoluteDate(db2.getDate(5)));
			comprobante.setPwf(db2.getShort(6));
			comprobante.setTgr(db2.getShort(7));
			comprobante.setAia(db2.getShort(8));
			comprobante.setCen(db2.getShort(9));
			comprobantes.add(comprobante);
		}
		return comprobantes;
	}

public Collection selectComprobantesResumen(int periodo, int cierre, String tipoNomina) throws SQLException {
	
	StringBuffer query= new StringBuffer();
	query.append("select id_codigo_barra, total, n_trabajadores, case when forma_pago in (1, 3) then 'SPL' else 'MIXTO' end, pagado, pwf, tgr, aia, cen, ncr ");
	query.append("from RESUMEN_PROCESOS_CIERRE ");
	query.append("WHERE periodo =? ");
	query.append("AND cierre =? ");
	query.append("AND id_tipo_nomina =? ");
	logger.finest("Query=" + query.toString());
	db2.prepareQuery(query.toString());
	db2.setStatement(1, periodo);
	db2.setStatement(2, cierre);
	db2.setStatement(3, tipoNomina);
	//Se ejecuta la query
	db2.executeQuery();
	List comprobantes= new ArrayList();
	while(db2.next()){
		DetalleComprobantesTO comprobante= new DetalleComprobantesTO();
		comprobante.setCodigoBarra(db2.getLong(1));
		comprobante.setTotal(db2.getLong(2));
		comprobante.setNumeroTrabajadores(db2.getInt(3));
		comprobante.setFormaPago(db2.getString(4));
		comprobante.setFechaPago(new AbsoluteDate(db2.getDate(5)));
		comprobante.setPwf(db2.getShort(6));
		comprobante.setTgr(db2.getShort(7));
		comprobante.setAia(db2.getShort(8));
		comprobante.setCen(db2.getShort(9));
		comprobante.setNcr(db2.getShort(10));
		comprobantes.add(comprobante);
	}
	return comprobantes;
}

//rescatando estadisticas del periodo
public Collection selectEstadistica() throws SQLException{
	EstadisticaPagoTO estad=null;
	List estadisticas=null;
	StringBuffer query= new StringBuffer();
	query.append("select periodo, forma_pago, sum(total) as TOTAL, sum(n_trabajadores) as N_TRABAJADORES, count(1) as N_COMPROBANTES ");
	query.append("from resumen_procesos_cierre ");
	query.append("group by periodo, forma_pago ");
	query.append("order by 1, 2 " );
	logger.finest("Query=" + query.toString());
	db2.executeQuery(query.toString());
	estadisticas = new ArrayList();
	int periodo_old=0;
	while (db2.next()){
		int periodo= db2.getInt(1);
		int formaPago= db2.getInt(2);
		long monto= db2.getLong(3);
		int numeroTrabajadores= db2.getInt(4);
		int cantidadComprobantes= db2.getInt(5);
		if(periodo!= periodo_old){
			if (periodo_old!=0){
				estadisticas.add(estad);
			}
			estad= new EstadisticaPagoTO();
			estad.setPeriodo(periodo);
		}
		if(formaPago==1){
			estad.setTotalPagoSPL(monto);
			estad.setCantidadComprobantesSPL(cantidadComprobantes);
			estad.setNumeroTrabajadoresSPL(numeroTrabajadores);
		}else{
			estad.setTotalPagoMixto(monto);
			estad.setCantidadComprobantesMixto(cantidadComprobantes);
			estad.setNumeroTrabajadoresMixto(numeroTrabajadores);
		}
		periodo_old= periodo;
	}
	//Agregando ultima estadistica
	estadisticas.add(estad);
	return estadisticas;
}

/* (sin Javadoc)
 * @see cl.araucana.planillascp.dao.DAO_Interface#update(java.lang.Object)
 */
public int updatePlanilla(int folio_tesoreria, int estado) throws SQLException {
	StringBuffer query= new StringBuffer();
	//query.append("UPDATE RESUMEN_PROCESOS_CIERRE set PWF= ?, CEN= ? ");
	query.append("UPDATE RESUMEN_PROCESOS_CIERRE set PWF= ? ");
	query.append("WHERE folio_ingreso= ? ");
	logger.finest("Query=" + query.toString());
	db2.prepareQuery(query.toString());
	/*db2.setStatement(1, estado);
	db2.setStatement(2, estado);
	db2.setStatement(3, folio_tesoreria);*/
	db2.setStatement(1, estado);
	db2.setStatement(2, folio_tesoreria);
	return db2.executeUpdate();
}

/* (sin Javadoc)
 * @see cl.araucana.planillascp.dao.DAO_Interface#update(java.lang.Object)
 */
public int updateTGR(int folio_tesoreria, int estadotgr, int estadoconv) throws SQLException {
	StringBuffer query= new StringBuffer();
	query.append("UPDATE RESUMEN_PROCESOS_CIERRE set TGR= ?, NCR= ? ");
	query.append("WHERE folio_ingreso= ? ");
	logger.finest("Query=" + query.toString());
	db2.prepareQuery(query.toString());
	db2.setStatement(1, estadotgr);
	db2.setStatement(2, estadoconv);
	db2.setStatement(3, folio_tesoreria);
	return db2.executeUpdate();
}

public int updateTGR(int periodo, int cierre, char tipoNomina, int estadoTGR, int estadoNCR) throws SQLException {

	StringBuffer query= new StringBuffer();
	query.append("update RESUMEN_PROCESOS_CIERRE ");
	query.append("set TGR= ? , NCR= ? ");
	query.append("where periodo= ? ");
	query.append("and cierre= ? ");
	query.append("and id_tipo_nomina= ? ");
	db2.prepareQuery(query.toString());
	db2.setStatement(1, estadoTGR);
	db2.setStatement(2, estadoNCR);
	db2.setStatement(3, periodo);
	db2.setStatement(4, cierre);
	db2.setStatement(5, tipoNomina);
	logger.finest("Query=" + query.toString());
	return db2.executeUpdate();
}

/* (sin Javadoc)
 * @see cl.araucana.cierrecp.dao.DAO_Interface#update(java.lang.Object)
 */
public int updateArchivosImpresion(int periodo, int cierre, int estado) throws SQLException {
	int resultado=0;
	StringBuffer query= new StringBuffer();
	query.append("update RESUMEN_PROCESOS_CIERRE ");
	query.append("set AIA= ? ");
	query.append("where periodo= ? ");
	query.append("and cierre= ? ");
	query.append("and aia= 0 ");
	logger.finest("Query=" + query.toString());
	db2.prepareQuery(query.toString());
	db2.setStatement(1, estado);
	db2.setStatement(2, periodo);
	db2.setStatement(3, cierre);
	logger.finest("Query=" + query.toString());
	resultado= db2.executeUpdate();
	/*
	if (estado==1){
		estado++;
		query= new StringBuffer();
		query.append("update RESUMEN_PROCESOS_CIERRE ");
		query.append("set AIA= ? ");
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
		query.append(", CONVENIO t5 ");
		query.append("on (t1.id_empresa= t5.id_empresa and t1.id_convenio= t5.id_convenio) ");
		query.append("or (t2.id_empresa= t5.id_empresa and t2.id_convenio= t5.id_convenio) ");
		query.append("or (t3.id_empresa= t5.id_empresa and t3.id_convenio= t5.id_convenio) ");
		query.append("or (t4.id_empresa= t5.id_empresa and t4.id_convenio= t5.id_convenio) ");
		query.append(", GRUPOCONV t6, OPCIONPROC t7 ");
		query.append("where t5.id_grupo_convenios=t6.id_grupo_convenios ");
		query.append("and t6.id_opcion= t7.id_opcion ");
		query.append("and t7.imprimir_planillas= 1 ");
		query.append("and t0.periodo= ? ");
		query.append("and t0.cierre= ? ");
		query.append("and (t1.id_codigo_barra is not null or t2.id_codigo_barra is not null or t3.id_codigo_barra is not null or t4.id_codigo_barra is not null) ");
		query.append("group by t0.folio_ingreso ");
		query.append(") ");
		db2.prepareQuery(query.toString());
		db2.setStatement(1, estado);
		db2.setStatement(2, periodo);
		db2.setStatement(3, cierre);
		logger.finest("Query=" + query.toString());
		resultado= db2.executeUpdate();
	}
	*/
	return resultado;
}

/* (sin Javadoc) DEPRECATED
 * @see cl.araucana.cierrecp.dao.DAO_Interface#update(java.lang.Object)
 */
public int updateCentralizacion(int periodo, int cierre, char tipoNomina, int estado) throws SQLException {

	StringBuffer query= new StringBuffer();
	query.append("update RESUMEN_PROCESOS_CIERRE ");
	query.append("set CEN= ? ");
	query.append("where CEN= 0 ");
	query.append("and periodo= ? ");
	query.append("and cierre= ? ");
	query.append("and id_tipo_nomina= ? ");
	db2.prepareQuery(query.toString());
	db2.setStatement(1, estado);
	db2.setStatement(2, periodo);
	db2.setStatement(3, cierre);
	db2.setStatement(4, tipoNomina);
	logger.finest("Query=" + query.toString());
	return db2.executeUpdate();
}

/* (sin Javadoc)
 * @see cl.araucana.planillascp.dao.DAO_Interface#update(java.lang.Object)
 */
public int updateCentralizacion(int folio_tesoreria, int estado) throws SQLException {
	StringBuffer query= new StringBuffer();
	query.append("UPDATE RESUMEN_PROCESOS_CIERRE set CEN= ? ");
	query.append("WHERE folio_ingreso= ? ");
	logger.finest("Query=" + query.toString());
	db2.prepareQuery(query.toString());
	db2.setStatement(1, estado);
	db2.setStatement(2, folio_tesoreria);
	return db2.executeUpdate();
}

public Object select(Object pk) throws SQLException {
	// TODO Apéndice de método generado automáticamente
	return null;
}
/* (sin Javadoc)
 * @see cl.araucana.planillascp.dao.DAO_Interface#delete(java.lang.Object)
 */
public void delete(Object pk) throws SQLException {
	// TODO Apéndice de método generado automáticamente

}

/* (sin Javadoc)
 * @see cl.araucana.planillascp.dao.DAO_Interface#insert(java.lang.Object)
 */
public int insert(Object obj) throws SQLException {
	// TODO Apéndice de método generado automáticamente
	return 0;
}

public int update(Object obj) throws SQLException {
	// TODO Apéndice de método generado automáticamente
	return 0;
}

}

