

/*
 * @(#) EmpresaCPDAO.java    1.0 10-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cl.araucana.cierrecp.empresas.comprobantes.Comprobante_Detalle;
import cl.araucana.cierrecp.empresas.comprobantes.Comprobante_Encabezado;
import cl.araucana.cierrecp.empresas.comprobantes.Comprobante_Totales;
import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.planillas.IdentificacionEmpleador;
import cl.araucana.cierrecpe.empresas.to.Cuadratura_Comprobante;
import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.Rut;
import cl.recursos.ConectaDB2;


import java.util.logging.Logger;
import cl.araucana.core.util.logging.LogManager;

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
public class ComprobantesCPDAO implements DAO_Interface {
private ConectaDB2 db2, db2det;
private static Logger logger = LogManager.getLogger();	
	
	public ComprobantesCPDAO(ConectaDB2 db2){
		this.db2= db2;
		//Se abre segunda conexión para rescatar detalle de cada comprobante sin perder resultado de primera conexion almacenada en var db2
		CPDAO cpDAO= new CPDAO();
		db2det= cpDAO.getConnection();
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente

	}

	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		String cierre= (String)pk;
		StringBuffer query= new StringBuffer();
		//Seleccionando comprobantes de Remuneraciones
		query.append("select t1.id_codigo_barra, t1.pagado, t2.id_empresa, t2.id_convenio, t3.id_actividad, t4.razon_social, t4.id_rep_legal, t5.nombres, t5.apellido_paterno, t5.apellido_materno, ");
		query.append("'R' as TipoNomina, t3.id_grupo_convenios, current_date, current_date, t3.id_ccaf, t3.id_mutual, t8.nombre as Caja, t9.nombre as Mutual, t3.mutual_numero_adherente, t4.id_casa_matriz, ");
		query.append("t10.generar_inp_por_sucursal, t10.generar_mutual_por_sucursal, t10.generar_ccaf_por_sucursal, t1.folio_tesoreria, t1.total, t1.n_trabajadores, t1.emitido, t3.mutual_calculo_individual, ");
		query.append("t1.forma_pago, t1.renta_imponible ");
		query.append("from COMPROBANTE_PAGO t1, NOMINARE t2, CONVENIO t3, EMPRESA t4, PERSONA t5, entidad_ccaf t6, entidad_mutual t7, entpagad t8, entpagad t9, opcionproc t10, resprocie t11 ");
		query.append("WHERE t1.id_codigo_barra= t2.id_codigo_barra ");
		//query.append("AND t1.id_estado IN ( 4, 5 ) ");
		query.append("AND t1.cierre= ? ");
		query.append("AND t2.id_convenio= t3.id_convenio ");
		query.append("AND t2.id_empresa= t3.id_empresa ");
		query.append("AND t3.id_empresa= t4.id_empresa ");
		query.append("AND t4.id_rep_legal= t5.id_persona ");
		query.append("AND t3.id_ccaf=t6.id_ccaf ");
		query.append("AND t6.id_ent_pagadora= t8.id_ent_pagadora ");
		query.append("AND t3.id_mutual=t7.id_mutual ");
		query.append("AND t7.id_ent_pagadora= t9.id_ent_pagadora ");
		query.append("AND t3.id_opcion=t10.id_opcion ");
		query.append("AND t1.id_codigo_barra=t11.id_codigo_barra ");
		query.append("AND t11.PWF=0 ");
		query.append("GROUP BY t1.id_codigo_barra, t1.pagado, t2.id_empresa, t2.id_convenio, t3.id_actividad, t4.razon_social, t4.id_rep_legal, t5.nombres, t5.apellido_paterno, t5.apellido_materno, ");
		query.append("t3.id_grupo_convenios, current_date, current_date, t3.id_ccaf, t3.id_mutual, t8.nombre, t9.nombre, t3.mutual_numero_adherente, t4.id_casa_matriz, ");
		query.append("t10.generar_inp_por_sucursal, t10.generar_mutual_por_sucursal, t10.generar_ccaf_por_sucursal, t1.folio_tesoreria, t1.total, t1.n_trabajadores, t1.emitido, t3.mutual_calculo_individual, ");
		query.append("t1.forma_pago, t1.renta_imponible ");
		//Seleccionando comprobantes de Gratificaciones
		query.append("UNION ");
		query.append("select t1.id_codigo_barra, t1.pagado, t2.id_empresa, t2.id_convenio, t3.id_actividad, t4.razon_social, t4.id_rep_legal, t5.nombres, t5.apellido_paterno, t5.apellido_materno, ");
		query.append("'G' as TipoNomina, t3.id_grupo_convenios, t2.inicio, t2.termino, t3.id_ccaf, t3.id_mutual, t8.nombre as Caja, t9.nombre as Mutual, t3.mutual_numero_adherente, t4.id_casa_matriz, ");
		query.append("t10.generar_inp_por_sucursal, t10.generar_mutual_por_sucursal, t10.generar_ccaf_por_sucursal, t1.folio_tesoreria, t1.total, t1.n_trabajadores, t1.emitido, t3.mutual_calculo_individual, ");
		query.append("t1.forma_pago, t1.renta_imponible ");
		query.append("from COMPROBANTE_PAGO t1, NOMINAGR t2, CONVENIO t3, EMPRESA t4, PERSONA t5, entidad_ccaf t6, entidad_mutual t7, entpagad t8, entpagad t9, opcionproc t10, resprocie t11 ");
		query.append("WHERE t1.id_codigo_barra= t2.id_codigo_barra ");
		//query.append("AND t1.id_estado IN ( 4, 5 ) ");
		query.append("AND t1.cierre= ? ");
		query.append("AND t2.id_convenio= t3.id_convenio ");
		query.append("AND t2.id_empresa= t3.id_empresa ");
		query.append("AND t3.id_empresa= t4.id_empresa ");
		query.append("AND t4.id_rep_legal= t5.id_persona ");
		query.append("AND t3.id_ccaf=t6.id_ccaf ");
		query.append("AND t6.id_ent_pagadora= t8.id_ent_pagadora ");
		query.append("AND t3.id_mutual=t7.id_mutual ");
		query.append("AND t7.id_ent_pagadora= t9.id_ent_pagadora ");
		query.append("AND t3.id_opcion=t10.id_opcion ");
		query.append("AND t1.id_codigo_barra=t11.id_codigo_barra ");
		query.append("AND t11.PWF=0 ");
		query.append("GROUP BY t1.id_codigo_barra, t1.pagado, t2.id_empresa, t2.id_convenio, t3.id_actividad, t4.razon_social, t4.id_rep_legal, t5.nombres, t5.apellido_paterno, t5.apellido_materno, ");
		query.append("t3.id_grupo_convenios, t2.inicio, t2.termino, t3.id_ccaf, t3.id_mutual, t8.nombre, t9.nombre, t3.mutual_numero_adherente, t4.id_casa_matriz, ");
		query.append("t10.generar_inp_por_sucursal, t10.generar_mutual_por_sucursal, t10.generar_ccaf_por_sucursal, t1.folio_tesoreria, t1.total, t1.n_trabajadores, t1.emitido, t3.mutual_calculo_individual, ");
		query.append("t1.forma_pago, t1.renta_imponible ");
		//Seleccionando comprobantes de Reliquidación
		query.append("UNION ");
		query.append("select t1.id_codigo_barra, t1.pagado, t2.id_empresa, t2.id_convenio, t3.id_actividad, t4.razon_social, t4.id_rep_legal, t5.nombres, t5.apellido_paterno, t5.apellido_materno, ");
		query.append("'A' as TipoNomina, t3.id_grupo_convenios, current_date, current_date, t3.id_ccaf, t3.id_mutual, t8.nombre as Caja, t9.nombre as Mutual, t3.mutual_numero_adherente, t4.id_casa_matriz, ");
		query.append("t10.generar_inp_por_sucursal, t10.generar_mutual_por_sucursal, t10.generar_ccaf_por_sucursal, t1.folio_tesoreria, t1.total, t1.n_trabajadores, t1.emitido, t3.mutual_calculo_individual, ");
		query.append("t1.forma_pago, t1.renta_imponible ");
		query.append("from COMPROBANTE_PAGO t1, NOMINARA t2, CONVENIO t3, EMPRESA t4, PERSONA t5, entidad_ccaf t6, entidad_mutual t7, entpagad t8, entpagad t9, opcionproc t10, resprocie t11 ");
		query.append("WHERE t1.id_codigo_barra= t2.id_codigo_barra ");
		//query.append("AND t1.id_estado IN ( 4, 5 ) ");
		query.append("AND t1.cierre= ? ");
		query.append("AND t2.id_convenio= t3.id_convenio ");
		query.append("AND t2.id_empresa= t3.id_empresa ");
		query.append("AND t3.id_empresa= t4.id_empresa ");
		query.append("AND t4.id_rep_legal= t5.id_persona ");
		query.append("AND t3.id_ccaf=t6.id_ccaf ");
		query.append("AND t6.id_ent_pagadora= t8.id_ent_pagadora ");
		query.append("AND t3.id_mutual=t7.id_mutual ");
		query.append("AND t7.id_ent_pagadora= t9.id_ent_pagadora ");
		query.append("AND t3.id_opcion=t10.id_opcion ");
		query.append("AND t1.id_codigo_barra=t11.id_codigo_barra ");
		query.append("AND t11.PWF=0 ");
		query.append("GROUP BY t1.id_codigo_barra, t1.pagado, t2.id_empresa, t2.id_convenio, t3.id_actividad, t4.razon_social, t4.id_rep_legal, t5.nombres, t5.apellido_paterno, t5.apellido_materno, ");
		query.append("t3.id_grupo_convenios, current_date, current_date, t3.id_ccaf, t3.id_mutual, t8.nombre, t9.nombre, t3.mutual_numero_adherente, t4.id_casa_matriz, ");
		query.append("t10.generar_inp_por_sucursal, t10.generar_mutual_por_sucursal, t10.generar_ccaf_por_sucursal, t1.folio_tesoreria, t1.total, t1.n_trabajadores, t1.emitido, t3.mutual_calculo_individual, ");
		query.append("t1.forma_pago, t1.renta_imponible ");
		//Seleccionando comprobantes de Depósitos Convenidos
		query.append("UNION ");
		query.append("select t1.id_codigo_barra, t1.pagado, t2.id_empresa, t2.id_convenio, t3.id_actividad, t4.razon_social, t4.id_rep_legal, t5.nombres, t5.apellido_paterno, t5.apellido_materno, ");
		query.append("'D' as TipoNomina, t3.id_grupo_convenios, current_date, current_date, t3.id_ccaf, t3.id_mutual, t8.nombre as Caja, t9.nombre as Mutual, t3.mutual_numero_adherente, t4.id_casa_matriz, ");
		query.append("t10.generar_inp_por_sucursal, t10.generar_mutual_por_sucursal, t10.generar_ccaf_por_sucursal, t1.folio_tesoreria, t1.total, t1.n_trabajadores, t1.emitido, t3.mutual_calculo_individual, ");
		query.append("t1.forma_pago, t1.renta_imponible ");
		query.append("from COMPROBANTE_PAGO t1, NOMINADC t2, CONVENIO t3, EMPRESA t4, PERSONA t5, entidad_ccaf t6, entidad_mutual t7, entpagad t8, entpagad t9, opcionproc t10, resprocie t11 ");
		query.append("WHERE t1.id_codigo_barra= t2.id_codigo_barra ");
		//query.append("AND t1.id_estado IN ( 4, 5 ) ");
		query.append("AND t1.cierre= ? ");
		query.append("AND t2.id_convenio= t3.id_convenio ");
		query.append("AND t2.id_empresa= t3.id_empresa ");
		query.append("AND t3.id_empresa= t4.id_empresa ");
		query.append("AND t4.id_rep_legal= t5.id_persona ");
		query.append("AND t3.id_ccaf=t6.id_ccaf ");
		query.append("AND t6.id_ent_pagadora= t8.id_ent_pagadora ");
		query.append("AND t3.id_mutual=t7.id_mutual ");
		query.append("AND t7.id_ent_pagadora= t9.id_ent_pagadora ");
		query.append("AND t3.id_opcion=t10.id_opcion ");
		query.append("AND t1.id_codigo_barra=t11.id_codigo_barra ");
		query.append("AND t11.PWF=0 ");
		query.append("GROUP BY t1.id_codigo_barra, t1.pagado, t2.id_empresa, t2.id_convenio, t3.id_actividad, t4.razon_social, t4.id_rep_legal, t5.nombres, t5.apellido_paterno, t5.apellido_materno, ");
		query.append("t3.id_grupo_convenios, current_date, current_date, t3.id_ccaf, t3.id_mutual, t8.nombre, t9.nombre, t3.mutual_numero_adherente, t4.id_casa_matriz, ");
		query.append("t10.generar_inp_por_sucursal, t10.generar_mutual_por_sucursal, t10.generar_ccaf_por_sucursal, t1.folio_tesoreria, t1.total, t1.n_trabajadores, t1.emitido, t3.mutual_calculo_individual, ");
		query.append("t1.forma_pago, t1.renta_imponible ");
		query.append("ORDER BY 1");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		//db2.setStatement(1, 5); //PAGADO
		db2.setStatement(1, cierre);
		db2.setStatement(2, cierre);
		db2.setStatement(3, cierre);
		db2.setStatement(4, cierre);
		//Se ejecuta la query
		db2.executeQuery();
		List comprobantes= new ArrayList();
		while (db2.next()) {
			long id_codigo_barra= db2.getLong(1);
			AbsoluteDate fechaPago= new AbsoluteDate(db2.getDate(2));
			Rut rutEmpresa= new Rut(db2.getInt(3));
			int convenio= db2.getInt(4);
			int codActEconomica= db2.getInt(5);
			String razonSocial= db2.getString(6);
			Rut rutRepLegal= new Rut(db2.getInt(7));
			String nombreRepLegal= db2.getString(8);
			String apPatRepLegal=db2.getString(9);
			String apMatRepLegal=db2.getString(10);
			String tipoProceso=db2.getString(11);
			int grupoConvenio= db2.getInt(12);
			int idCcaf= db2.getInt(15);
			int idMutual= db2.getInt(16);
			String nombreCcaf= db2.getString(17);
			String nombreMutual= db2.getString(18);
			int numeroAdherenteMutual= db2.getInt(19);
			String id_casa_matriz= db2.getString(20);
			short gen_plaxinp= db2.getShort(21);
			short gen_plaxmut= db2.getShort(22);
			short gen_plaxccaf= db2.getShort(23);
			int folioTesoreria= db2.getInt(24);
			long montoTotal= db2.getLong(25);
			int numTrabajadores= db2.getInt(26);
			Timestamp fechaEmision= db2.getTimestamp(27);
			short mutual_calculo_ind= db2.getShort(28);
			short formaPago= db2.getShort(29);
			long renta_imponible= db2.getLong(30);
			Comprobante_Encabezado comprobante =new Comprobante_Encabezado();
			comprobante.setCierre(Integer.parseInt(cierre));
			comprobante.setId_codigo_barra(id_codigo_barra);
			comprobante.setFolioTesoreria(folioTesoreria);
			comprobante.setFechaPago(fechaPago);
			comprobante.setTipoProceso(tipoProceso);
			comprobante.setConvenio(convenio);
			comprobante.setMontoTotal(montoTotal);
			comprobante.setTotalRemuneraciones(renta_imponible);
			comprobante.setNumTrabajadores(numTrabajadores);
			comprobante.setGrupoConvenio(grupoConvenio);
			IdentificacionEmpleador datosEmpleador= new IdentificacionEmpleador();
			datosEmpleador.setRutEmpresa(rutEmpresa);
			datosEmpleador.setCodActEconomica(codActEconomica);
			datosEmpleador.setRazonSocial(razonSocial);
			datosEmpleador.setRutRepLegal(rutRepLegal);
			datosEmpleador.setNombresRepLegal(nombreRepLegal);
			datosEmpleador.setApellidosRepLegal(apPatRepLegal + " " + apMatRepLegal);
			comprobante.setDatosEmpleador(datosEmpleador);
			if (tipoProceso.equals("G")){
				AbsoluteDate fechaInicioGrati= new AbsoluteDate(db2.getDate(13));
				AbsoluteDate fechaTerminoGrati= new AbsoluteDate(db2.getDate(14));
				comprobante.setFechaInicioGrati(fechaInicioGrati);
				comprobante.setFechaTerminoGrati(fechaTerminoGrati);
			}
			comprobante.setIdCcaf(idCcaf);
			comprobante.setIdMutual(idMutual);
			comprobante.setNombreCcaf(nombreCcaf);
			comprobante.setNombreMutual(nombreMutual);
			comprobante.setNumeroAdherenteMutual(numeroAdherenteMutual);
			comprobante.setIdCasaMatriz(id_casa_matriz);
			comprobante.setFechaEmision(fechaEmision);
			if(gen_plaxinp==0){
				comprobante.setGenerarPlanillaxSucursalINP(false);
			}
			if(gen_plaxmut==0){
				comprobante.setGenerarPlanillaxSucursalMUTUAL(false);
			}
			if(gen_plaxccaf==0){
				comprobante.setGenerarPlanillaxSucursalCCAF(false);
			}
			if(mutual_calculo_ind==0){
				comprobante.setMutualCalculoIndividual(false);
			}
			if(formaPago== Constants.FORMAPAGO_MIXTA_INDEPENDIENTE|| formaPago== Constants.FORMAPAGO_SPL_INDEPENDIENTE){
				comprobante.setTipoCliente(Constants.TIPO_CLIENTE_INDEPENDIENTE);
			}else{
				comprobante.setTipoCliente(Constants.TIPO_CLIENTE_EMPRESA);
			}
			
			if(formaPago== Constants.FORMAPAGO_MIXTA_INDEPENDIENTE|| formaPago== Constants.FORMAPAGO_MIXTA_EMPRESA){
				//pago por Caja
				comprobante.setFormaPago('C');
			}else{
				//Pago por SPL
				comprobante.setFormaPago('P');
			}
			if(comprobante.getTipoProceso().equals("R")){
				setSeccionesCaja(comprobante);
			}
			
			selectTotalesComprobante(comprobante);
			selectDetalleComprobante(comprobante);
			comprobantes.add(comprobante);
		}
		return comprobantes;
	}
	
	public void setSeccionesCaja(Comprobante_Encabezado comprobante) throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select count(*) ");
		query.append("from seccion ");
		query.append("where id_codigo_barra= ? ");
		query.append("and id_tipo_seccion= ? ");
		logger.finest("Query=" + query.toString());
		db2det.prepareQuery(query.toString());
		db2det.setStatement(1, comprobante.getId_codigo_barra());
		db2det.setStatement(2, 5);
		//Se ejecuta la query
		db2det.executeQuery();
		if (db2det.next()) {
			int cantidad= db2det.getInt(1);
			if (cantidad==0){
				//SE INSERTA SECCION EN CERO 
				query= new StringBuffer();
				query.append("insert into seccion ");
				query.append("values (?, ?, ?, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ) ");
				db2det.prepareQuery(query.toString());
				db2det.setStatement(1, comprobante.getId_codigo_barra());
				db2det.setStatement(2, 5); //id_seccion
				db2det.setStatement(3, comprobante.getNumTrabajadores());
				//Se ejecuta la query
				db2det.executeUpdate();

				//SE INSERTA DETALLE_SECCION EN CERO 
				query= new StringBuffer();
				query.append("insert into detalle_seccion ");
				query.append("values (?, ?, ?, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, ?, 1, 0 ) ");
				db2det.prepareQuery(query.toString());
				db2det.setStatement(1, comprobante.getId_codigo_barra());
				db2det.setStatement(2, 5); //id_seccion
				db2det.setStatement(3, comprobante.getIdCcaf());
				db2det.setStatement(4, comprobante.getNumTrabajadores());
				//Se ejecuta la query
				db2det.executeUpdate();
			}
		}
		db2det.closeStatement();
	}
	
	public List buscarCajasCierre(int cierre) throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("SELECT distinct id_detalle_seccion  ");
		query.append("FROM DETALLE_SECCION a, comprobante_pago b ");
		query.append("where a.id_codigo_barra=b.id_codigo_barra ");
		query.append("and id_tipo_seccion=5 ");
		query.append("and cierre= ? ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, cierre);
		//Se ejecuta la query
		db2.executeQuery();
		List lista= new ArrayList();
		while (db2.next()) {
			int id_ccaf= db2.getInt(1);
			lista.add(new Integer(id_ccaf));
		}
		return lista;
	}
	
	public void deleteCajasPagoCero(List listaCCAF, int periodo, int cierre) throws SQLException{
		String lista="";
		for (Iterator iter = listaCCAF.iterator(); iter.hasNext();) {
			Integer id_ccaf = (Integer) iter.next();
			lista= lista + id_ccaf + ", ";
		}
		lista= lista.substring(0, lista.length()-2);
		StringBuffer query= new StringBuffer();
		query.append("delete from propago ");
		query.append("where periodo= ? ");
		query.append("and cierre=? ");
		query.append("and id_tipo_seccion=? ");
		query.append("and id_tipo_detalle not in (" + lista + ")");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, periodo);
		db2.setStatement(2, cierre);
		db2.setStatement(3, Constants.REMU_CCAF);
		
		//Se ejecuta la query
		db2.executeUpdate();
	}
	
	private void selectDetalleComprobante(Comprobante_Encabezado comprobante)throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select t3.id_tipo_seccion, t3.id_detalle_seccion, t3.n_trabajadores, t3.m1, t3.m2, t3.m3, t3.m4, t3.m5, t3.m6, t3.m7, t3.m8, t3.m9, t3.m10, t3.m11, t3.m12, t4.id_ent_pagadora, case when t2.tipo_pago=0 then t3.tipo_pago else t2.tipo_pago end as tipo_pago, case t3.id_tipo_seccion when 60 then 'DEP.CONV.' else t5.clave end, t6.nombre, t5.m_total ");
		query.append("from comprobante_pago t1, seccion t2, detalle_seccion t3, tipo_detalle t4, tipo_seccion t5, entpagad t6 ");
		query.append("WHERE t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("AND t2.id_codigo_barra= t3.id_codigo_barra ");
		query.append("AND t2.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("AND t2.tipo_pago<>3 "); //Sección distinta de No Pagada
		query.append("AND t3.tipo_pago in (1, 2) "); //Detalle_seccion Pagada o Declarada
		query.append("AND t1.id_codigo_barra= ? ");
		query.append("AND t3.id_tipo_seccion = t4.id_tipo_seccion ");
		query.append("AND t3.id_detalle_seccion = t4.id_tipo_detalle ");
		query.append("AND t2.id_tipo_seccion= t5.id_tipo_seccion ");
		query.append("AND t4.id_ent_pagadora= t6.id_ent_pagadora ");
		query.append("GROUP BY t3.id_tipo_seccion, t3.id_detalle_seccion, t3.n_trabajadores, t3.m1, t3.m2, t3.m3, t3.m4, t3.m5, t3.m6, t3.m7, t3.m8, t3.m9, t3.m10, t3.m11, t3.m12, t4.id_ent_pagadora, t2.tipo_pago, t3.tipo_pago, t5.clave, t6.nombre, t5.m_total "); 
		query.append("ORDER BY t3.id_tipo_seccion, t3.id_detalle_seccion ");
		logger.finest("Query=" + query.toString());
		db2det.prepareQuery(query.toString());
		db2det.setStatement(1, comprobante.getId_codigo_barra());
		
		//Se ejecuta la query
		db2det.executeQuery();
		
		List detalles= new ArrayList();
		while (db2det.next()) {
			Comprobante_Detalle det_comprobante= new Comprobante_Detalle();
			det_comprobante.setTipo_seccion(db2det.getInt(1));
			det_comprobante.setTipo_detalle(db2det.getInt(2));
			det_comprobante.setN_trabajadores(db2det.getInt(3));
			det_comprobante.setM1(db2det.getLong(4));
			det_comprobante.setM2(db2det.getLong(5));
			det_comprobante.setM3(db2det.getLong(6));
			det_comprobante.setM4(db2det.getLong(7));
			det_comprobante.setM5(db2det.getLong(8));
			det_comprobante.setM6(db2det.getLong(9));
			det_comprobante.setM7(db2det.getLong(10));
			det_comprobante.setM8(db2det.getLong(11));
			det_comprobante.setM9(db2det.getLong(12));
			det_comprobante.setM10(db2det.getLong(13));
			det_comprobante.setM11(db2det.getLong(14));
			det_comprobante.setM12(db2det.getLong(15));
			det_comprobante.setRutEntidad(new Rut(db2det.getInt(16)));
			det_comprobante.setTipoDeclaracionPago(db2det.getInt(17));
			det_comprobante.setNombreSeccion(db2det.getString(18));
			det_comprobante.setNombreEntidad(db2det.getString(19));
			String columna_total= db2det.getString(20);
			if(columna_total!=null){
				long total= db2det.getLong(columna_total);
				det_comprobante.setMtotal(total);
			}
			det_comprobante.setEncabezado(comprobante);
			detalles.add(det_comprobante);
		}
		comprobante.setEntidades(detalles);
	}
	
	private void selectTotalesComprobante(Comprobante_Encabezado comprobante)throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select t3.id_tipo_seccion, case t3.id_tipo_seccion when 60 then 'DEP.CONV.' else t5.clave end, sum(t3.n_trabajadores) as n_trabajadores, sum(t3.m1) as m1, sum(t3.m2) as m2, sum(t3.m3) as m3, sum(t3.m4) as m4, sum(t3.m5) as m5, sum(t3.m6) as m6, sum(t3.m7) as m7, sum(t3.m8) as m8, sum(t3.m9) as m9, sum(t3.m10) as m10, sum(t3.m11) as m11, sum(t3.m12) as m12, t5.m_total ");
		query.append("from comprobante_pago t1, seccion t2, detalle_seccion t3, tipo_seccion t5 ");
		query.append("WHERE t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("AND t2.id_codigo_barra= t3.id_codigo_barra ");
		query.append("AND t2.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("AND t2.tipo_pago<>3 "); //Sección distinta de No Pagada
		query.append("AND t3.tipo_pago =1 "); //Detalle_seccion Pagada o Declarada
		query.append("AND t1.id_codigo_barra= ? ");
		query.append("AND t2.id_tipo_seccion= t5.id_tipo_seccion ");
		query.append("GROUP BY t3.id_tipo_seccion, t5.clave, t5.m_total "); 
		query.append("ORDER BY t3.id_tipo_seccion ");
		logger.finest("Query=" + query.toString());
		db2det.prepareQuery(query.toString());
		db2det.setStatement(1, comprobante.getId_codigo_barra());
		
		//Se ejecuta la query
		db2det.executeQuery();
		
		List totales= new ArrayList();
		while (db2det.next()) {
			Comprobante_Totales comp_totales= new Comprobante_Totales();
			comp_totales.setTipo_seccion(db2det.getInt(1));
			comp_totales.setNombreSeccion(db2det.getString(2));
			comp_totales.setN_trabajadores(db2det.getInt(3));
			String columna_total= db2det.getString(16);
			if(columna_total!=null){
				long total= db2det.getLong(columna_total);
				comp_totales.setMtotal(total);
			}	
			comp_totales.setEncabezado(comprobante);
			totales.add(comp_totales);
		}
		comprobante.setTotales(totales);
	}
	public Collection selectCuadraturaxEntidad(String cierre, String tipoSeccion) throws SQLException{
		if(tipoSeccion.equals("AFP")){
			logger.fine("Ejecutando cudratura AFP");
			Map totales= selectTotalesCuadraturaAFP(cierre);
			return selectCuadraturaAFP(cierre, totales);
		}else if(tipoSeccion.equals("ISAPRE")){
			logger.fine("Ejecutando cudratura ISAPRE");
			Map totales= selectTotalesCuadraturaISAPRE(cierre);
			return selectCuadraturaISAPRE(cierre, totales);
		}else if(tipoSeccion.equals("CCAF")){
			logger.fine("Ejecutando cudratura ISAPRE");
			Map totales= selectTotalesCuadraturaCCAF(cierre);
			return selectCuadraturaCCAF(cierre, totales);
		}else if(tipoSeccion.equals("INP")){
			logger.fine("Ejecutando cudratura INP");
			Map totales= selectTotalesCuadraturaINP(cierre);
			return selectCuadraturaINP(cierre, totales);
		}else if(tipoSeccion.equals("MUTUAL")){
			logger.fine("Ejecutando cudratura MUTUAL");
			Map totales= selectTotalesCuadraturaMUTUAL(cierre);
			return selectCuadraturaMUTUAL(cierre, totales);
		}else if(tipoSeccion.equals("APV")){
			logger.fine("Ejecutando cudratura APV");
			Map totales= selectTotalesCuadraturaAPV(cierre);
			return selectCuadraturaAPV(cierre, totales);
		}
		
		return null;
	}
	
	private Map selectTotalesCuadraturaAFP(String cierre)throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select TRIM(X.nombre) , sum(Z.Obligatorio) as Obligatorio, sum(Z.Ahorro) as Ahorro, sum(Z.SIS) as SIS, sum(Z.AFC_Tra) as AFC_Tra, sum(Z.AFC_Emp) as AFC_Emp, sum(Z.TP) as TP, sum(Z.Obligatorio + Z.Ahorro + Z.SIS + Z.AFC_Tra + Z.AFC_Emp + Z.TP) as Total from ");
		query.append("(select t1.id_detalle_seccion as Entidad, t1.m2 as Obligatorio, t1.m3 as Ahorro, t1.m12 as SIS, t1.m6 as AFC_Tra, t1.m7 as AFC_EMP, t1.m9 as TP ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_tipo_seccion=1 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t1.m2, t1.m3, t1.m12, t1.m6, t1.m7, t1.m9 ");
		query.append("UNION ");
		query.append("select t1.id_detalle_seccion as Entidad, t1.m1 as Obligatorio, 0 as Ahorro, t1.m7 as SIS, t1.m2 as AFC_Tra, t1.m3 as AFC_EMP, t1.m5 as TP ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_tipo_seccion in (20, 40) ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t1.m1, t1.m7, t1.m2, t1.m3, t1.m5) as Z, Entidad_Fondo_Pension Y, Entpagad X ");
		query.append("where Z.Entidad= Y.id_ent_fondo_pension ");
		query.append("and Y.id_ent_pagadora= X.id_ent_pagadora ");
		query.append("group by X.nombre ");
		query.append("order by X.nombre ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, cierre);
		db2.setStatement(2, cierre);
		
		//Se ejecuta la query
		db2.executeQuery();
		
		Map totalesEntidad= new HashMap();
		while (db2.next()) {
			Cuadratura_Comprobante cuadratura= new Cuadratura_Comprobante();
			String entidad= db2.getString(1);
			long totalObligatorio= db2.getLong(2);
			long totalAhorro= db2.getLong(3);
			long totalSis= db2.getLong(4);
			long totalAfctra= db2.getLong(5);
			long totalAfcemp= db2.getLong(6);
			long totalTp= db2.getLong(7);
			long totalTotal= db2.getLong(8);
			
			cuadratura.setNombreEntidad(entidad);
			cuadratura.setM2(totalObligatorio);
			cuadratura.setM3(totalAhorro);
			cuadratura.setM12(totalSis);
			cuadratura.setM6(totalAfctra);
			cuadratura.setM7(totalAfcemp);
			cuadratura.setM9(totalTp);
			cuadratura.setM11(totalTotal);
			totalesEntidad.put(entidad, cuadratura);
		}
		return totalesEntidad;
	}
	
	private Collection selectCuadraturaAFP(String cierre, Map totales)throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select TRIM(X.nombre) , Z.RutEmpresa, Z.Convenio, Z.TipoNomina, sum(Z.Obligatorio) as Obligatorio, sum(Z.Ahorro) as Ahorro, sum(Z.SIS) as SIS, sum(Z.AFC_Tra) as AFC_Tra, sum(Z.AFC_Emp) as AFC_Emp, sum(Z.TP) as TP, sum(Z.Obligatorio + Z.Ahorro + Z.SIS + Z.AFC_Tra + Z.AFC_Emp + Z.TP) as Total from ");
		query.append("(select t1.id_detalle_seccion as Entidad, t4.id_empresa as RutEmpresa, t4.id_convenio as Convenio, 'R' as TipoNomina, t1.m2 as Obligatorio, t1.m3 as Ahorro, t1.m12 as SIS, t1.m6 as AFC_Tra, t1.m7 as AFC_EMP, t1.m9 as TP ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3, nominare t4 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion=1 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t4.id_empresa, t4.id_convenio, t1.m2, t1.m3, t1.m12, t1.m6, t1.m7, t1.m9 ");
		query.append("UNION ");
		query.append("select t1.id_detalle_seccion as Entidad, t4.id_empresa as RutEmpresa, t4.id_convenio as Convenio, 'G' as TipoNomina, t1.m1 as Obligatorio, 0 as Ahorro, t1.m7 as SIS, t1.m2 as AFC_Tra, t1.m3 as AFC_EMP, t1.m5 as TP ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3, nominagr t4 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion=40 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t4.id_empresa, t4.id_convenio, t1.m1, t1.m7, t1.m2, t1.m3, t1.m5 ");
		query.append("UNION ");
		query.append("select t1.id_detalle_seccion as Entidad, t4.id_empresa as RutEmpresa, t4.id_convenio as Convenio, 'A' as TipoNomina, t1.m1 as Obligatorio, 0 as Ahorro, t1.m7 as SIS, t1.m2 as AFC_Tra, t1.m3 as AFC_EMP, t1.m5 as TP ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3, nominara t4 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion=20 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t4.id_empresa, t4.id_convenio, t1.m1, t1.m7, t1.m2, t1.m3, t1.m5) as Z, Entidad_Fondo_Pension Y, Entpagad X ");
		query.append("where Z.Entidad= Y.id_ent_fondo_pension ");
		query.append("and Y.id_ent_pagadora= X.id_ent_pagadora ");
		query.append("group by X.nombre, Z.RutEmpresa, Z.Convenio, Z.TipoNomina ");
		query.append("order by X.nombre,  Z.RutEmpresa, Z.Convenio, Z.TipoNomina desc ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, cierre);
		db2.setStatement(2, cierre);
		db2.setStatement(3, cierre);
		
		//Se ejecuta la query
		db2.executeQuery();
		
		List detalles= new ArrayList();
		String entidad_old="";
		Cuadratura_Comprobante totalesEntidad=null;
		while (db2.next()) {
			Cuadratura_Comprobante cuadratura= new Cuadratura_Comprobante();
			String entidad= db2.getString(1);
			Rut rutEmpresa= new Rut(db2.getInt(2));
			int convenio=  db2.getInt(3);
			String tipoNomina= db2.getString(4);
			long obligatorio= db2.getLong(5);
			long ahorro= db2.getLong(6);
			long sis= db2.getLong(7);
			long afctra= db2.getLong(8);
			long afcemp= db2.getLong(9);
			long tp= db2.getLong(10);
			long total= db2.getLong(11);
			
			cuadratura.setNombreEntidad(entidad);
			cuadratura.setRutEmpresa(rutEmpresa);
			cuadratura.setConvenio(convenio);
			cuadratura.setTipoNomina(tipoNomina);
			cuadratura.setM2(obligatorio);
			cuadratura.setM3(ahorro);
			cuadratura.setM12(sis);
			cuadratura.setM6(afctra);
			cuadratura.setM7(afcemp);
			cuadratura.setM9(tp);
			cuadratura.setM11(total);
			if(!entidad.equals(entidad_old)){
				totalesEntidad= (Cuadratura_Comprobante)totales.get(entidad);
			}
			cuadratura.setTotalM2(totalesEntidad.getM2());
			cuadratura.setTotalM3(totalesEntidad.getM3());
			cuadratura.setTotalM12(totalesEntidad.getM12());
			cuadratura.setTotalM6(totalesEntidad.getM6());
			cuadratura.setTotalM7(totalesEntidad.getM7());
			cuadratura.setTotalM9(totalesEntidad.getM9());
			cuadratura.setTotalM11(totalesEntidad.getM11());
			entidad_old= entidad;
			detalles.add(cuadratura);
		}
		return detalles;
	}
	
	private Map selectTotalesCuadraturaISAPRE(String cierre)throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select TRIM(X.nombre) , sum(Z.Obligatorio) as Obligatorio, sum(Z.Adicional) as Adicional, sum(Z.Obligatorio + Z.Adicional ) as Total from ");
		query.append("(select t1.id_detalle_seccion as Entidad, t1.m2 as Obligatorio, t1.m3 as Adicional ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_tipo_seccion=2 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t1.m2, t1.m3 ");
		query.append("UNION ");
		query.append("select t1.id_detalle_seccion as Entidad, t1.m1 as Obligatorio, 0 as Adicional ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_tipo_seccion in (21, 41) ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t1.m1) as Z, Entidad_Salud Y, Entpagad X ");
		query.append("where Z.Entidad= Y.id_ent_salud ");
		query.append("and Y.id_ent_pagadora= X.id_ent_pagadora ");
		query.append("group by X.nombre ");
		query.append("order by X.nombre ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, cierre);
		db2.setStatement(2, cierre);
		
		//Se ejecuta la query
		db2.executeQuery();
		
		Map totalesEntidad= new HashMap();
		while (db2.next()) {
			Cuadratura_Comprobante cuadratura= new Cuadratura_Comprobante();
			String entidad= db2.getString(1);
			long totalObligatorio= db2.getLong(2);
			long totalAdicional= db2.getLong(3);
			long totalTotal= db2.getLong(4);
			
			cuadratura.setNombreEntidad(entidad);
			cuadratura.setM2(totalObligatorio);
			cuadratura.setM3(totalAdicional);
			cuadratura.setM4(totalTotal);
			totalesEntidad.put(entidad, cuadratura);
		}
		return totalesEntidad;
	}
	
	private Collection selectCuadraturaISAPRE(String cierre, Map totales)throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select TRIM(X.nombre) , Z.RutEmpresa, Z.Convenio, Z.TipoNomina, sum(Z.Obligatorio) as Obligatorio, sum(Z.Adicional) as Adicional, sum(Z.Obligatorio + Z.Adicional) as Total from ");
		query.append("(select t1.id_detalle_seccion as Entidad, t4.id_empresa as RutEmpresa, t4.id_convenio as Convenio, 'R' as TipoNomina, t1.m2 as Obligatorio, t1.m3 as Adicional ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3, nominare t4 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion=2 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t4.id_empresa, t4.id_convenio, t1.m2, t1.m3 ");
		query.append("UNION ");
		query.append("select t1.id_detalle_seccion as Entidad, t4.id_empresa as RutEmpresa, t4.id_convenio as Convenio, 'G' as TipoNomina, t1.m1 as Obligatorio, 0 as Adicional ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3, nominagr t4 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion=41 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t4.id_empresa, t4.id_convenio, t1.m1 ");
		query.append("UNION ");
		query.append("select t1.id_detalle_seccion as Entidad, t4.id_empresa as RutEmpresa, t4.id_convenio as Convenio, 'A' as TipoNomina, t1.m1 as Obligatorio, 0 as Adicional ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3, nominara t4 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion=21 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t4.id_empresa, t4.id_convenio, t1.m1) as Z, Entidad_Salud Y, Entpagad X ");
		query.append("where Z.Entidad= Y.id_ent_salud ");
		query.append("and Y.id_ent_pagadora= X.id_ent_pagadora ");
		query.append("group by X.nombre, Z.RutEmpresa, Z.Convenio, Z.TipoNomina ");
		query.append("order by X.nombre,  Z.RutEmpresa, Z.Convenio, Z.TipoNomina desc ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, cierre);
		db2.setStatement(2, cierre);
		db2.setStatement(3, cierre);
		
		//Se ejecuta la query
		db2.executeQuery();
		
		List detalles= new ArrayList();
		String entidad_old="";
		Cuadratura_Comprobante totalesEntidad=null;
		while (db2.next()) {
			Cuadratura_Comprobante cuadratura= new Cuadratura_Comprobante();
			String entidad= db2.getString(1);
			Rut rutEmpresa= new Rut(db2.getInt(2));
			int convenio=  db2.getInt(3);
			String tipoNomina= db2.getString(4);
			long obligatorio= db2.getLong(5);
			long adicional= db2.getLong(6);
			long total= db2.getLong(7);
			
			cuadratura.setNombreEntidad(entidad);
			cuadratura.setRutEmpresa(rutEmpresa);
			cuadratura.setConvenio(convenio);
			cuadratura.setTipoNomina(tipoNomina);
			cuadratura.setM2(obligatorio);
			cuadratura.setM3(adicional);
			cuadratura.setM4(total);
			if(!entidad.equals(entidad_old)){
				totalesEntidad= (Cuadratura_Comprobante)totales.get(entidad);
			}
			cuadratura.setTotalM2(totalesEntidad.getM2());
			cuadratura.setTotalM3(totalesEntidad.getM3());
			cuadratura.setTotalM4(totalesEntidad.getM4());
			entidad_old= entidad;
			detalles.add(cuadratura);
		}
		return detalles;
	}
	
	private Map selectTotalesCuadraturaCCAF(String cierre)throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select TRIM(X.nombre) , sum(Z.Aporte06) as Aporte06, sum(Z.Asfam) as Asfam, sum(Z.Credito) as Credito, sum(Z.Leasing) as Leasing, sum(Z.SeguroVida) as SeguroVida, sum(Z.Dental) as Dental, sum(Z.Compensado) as Compensado, sum(Z.Total) as Total from ");
		query.append("(select t1.id_detalle_seccion as Entidad, t1.m1 as Aporte06, t1.m2 as Asfam, t1.m4 as Credito, t1.m5 as Leasing, t1.m6 as SeguroVida, t1.m7 as Dental, t1.m3 as Compensado, t1.m8 as Total ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_tipo_seccion=5 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t1.m1, t1.m2, t1.m4, t1.m5, t1.m6, t1.m7, t1.m3, t1.m8 ");
		query.append("UNION ");
		query.append("select t1.id_detalle_seccion as Entidad, t1.m1 as Aporte06, 0 as Asfam, 0 as Credito, 0 as Leasing, 0 as SeguroVida, 0 as Dental, t1.m1 as Compensado, t1.m1 as Total ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_tipo_seccion in (24, 44) ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t1.m1) as Z, Entidad_CCAF Y, Entpagad X ");
		query.append("where Z.Entidad= Y.id_ccaf ");
		query.append("and Y.id_ent_pagadora= X.id_ent_pagadora ");
		query.append("group by X.nombre ");
		query.append("order by X.nombre ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, cierre);
		db2.setStatement(2, cierre);
		
		//Se ejecuta la query
		db2.executeQuery();
		
		Map totalesEntidad= new HashMap();
		while (db2.next()) {
			Cuadratura_Comprobante cuadratura= new Cuadratura_Comprobante();
			String entidad= db2.getString(1);
			long totalAporte06= db2.getLong(2);
			long totalAsfam= db2.getLong(3);
			long totalCredito= db2.getLong(4);
			long totalLeasing= db2.getLong(5);
			long totalSeguroVida= db2.getLong(6);
			long totalDental= db2.getLong(7);
			long totalCompensado= db2.getLong(8);
			long totalTotal= db2.getLong(9);
			
			cuadratura.setNombreEntidad(entidad);
			cuadratura.setM1(totalAporte06);
			cuadratura.setM2(totalAsfam);
			cuadratura.setM4(totalCredito);
			cuadratura.setM5(totalLeasing);
			cuadratura.setM6(totalSeguroVida);
			cuadratura.setM7(totalDental);
			cuadratura.setM3(totalCompensado);
			cuadratura.setM8(totalTotal);
			totalesEntidad.put(entidad, cuadratura);
		}
		return totalesEntidad;
	}
	
	private Collection selectCuadraturaCCAF(String cierre, Map totales)throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select TRIM(X.nombre) , Z.RutEmpresa, Z.Convenio, Z.TipoNomina, sum(Z.Aporte06) as Aporte06, sum(Z.Asfam) as Asfam, sum(Z.Credito) as Credito, sum(Z.Leasing) as Leasing, sum(Z.SeguroVida) as SeguroVida, sum(Z.Dental) as Dental, sum(Z.Compensado) as Compensado, sum(Z.Total) as Total from ");
		query.append("(select t1.id_detalle_seccion as Entidad, t4.id_empresa as RutEmpresa, t4.id_convenio as Convenio, 'R' as TipoNomina, t1.m1 as Aporte06, t1.m2 as Asfam, t1.m4 as Credito, t1.m5 as Leasing, t1.m6 as SeguroVida, t1.m7 as Dental, t1.m3 as Compensado, t1.m8 as Total ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3, nominare t4 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion=5 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t4.id_empresa, t4.id_convenio, t1.m1, t1.m2, t1.m4, t1.m5, t1.m6, t1.m7, t1.m3, t1.m8 ");
		query.append("UNION ");
		query.append("select t1.id_detalle_seccion as Entidad, t4.id_empresa as RutEmpresa, t4.id_convenio as Convenio, 'G' as TipoNomina, t1.m1 as Aporte06, 0 as Asfam, 0 as Credito, 0 as Leasing, 0 as SeguroVida, 0 as Dental, t1.m1 as Compensado, t1.m1 as Total ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3, nominagr t4 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion=44 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t4.id_empresa, t4.id_convenio, t1.m1 ");
		query.append("UNION ");
		query.append("select t1.id_detalle_seccion as Entidad, t4.id_empresa as RutEmpresa, t4.id_convenio as Convenio, 'A' as TipoNomina, t1.m1 as Aporte06, 0 as Asfam, 0 as Credito, 0 as Leasing, 0 as SeguroVida, 0 as Dental, t1.m1 as Compensado, t1.m1 as Total ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3, nominara t4 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion=24 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t4.id_empresa, t4.id_convenio, t1.m1 ) as Z, Entidad_CCAF Y, Entpagad X ");
		query.append("where Z.Entidad= Y.id_ccaf ");
		query.append("and Y.id_ent_pagadora= X.id_ent_pagadora ");
		query.append("group by X.nombre, Z.RutEmpresa, Z.Convenio, Z.TipoNomina ");
		query.append("order by X.nombre,  Z.RutEmpresa, Z.Convenio, Z.TipoNomina desc ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, cierre);
		db2.setStatement(2, cierre);
		db2.setStatement(3, cierre);
		
		//Se ejecuta la query
		db2.executeQuery();
		
		List detalles= new ArrayList();
		String entidad_old="";
		Cuadratura_Comprobante totalesEntidad=null;
		while (db2.next()) {
			Cuadratura_Comprobante cuadratura= new Cuadratura_Comprobante();
			String entidad= db2.getString(1);
			Rut rutEmpresa= new Rut(db2.getInt(2));
			int convenio=  db2.getInt(3);
			String tipoNomina= db2.getString(4);
			long aporte06= db2.getLong(5);
			long asfam= db2.getLong(6);
			long credito= db2.getLong(7);
			long leasing= db2.getLong(8);
			long seguroVida= db2.getLong(9);
			long dental= db2.getLong(10);
			long compensado= db2.getLong(11);
			long total= db2.getLong(12);
			
			cuadratura.setNombreEntidad(entidad);
			cuadratura.setRutEmpresa(rutEmpresa);
			cuadratura.setConvenio(convenio);
			cuadratura.setTipoNomina(tipoNomina);
			cuadratura.setM1(aporte06);
			cuadratura.setM2(asfam);
			cuadratura.setM4(credito);
			cuadratura.setM5(leasing);
			cuadratura.setM6(seguroVida);
			cuadratura.setM7(dental);
			cuadratura.setM3(compensado);
			cuadratura.setM8(total);
			if(!entidad.equals(entidad_old)){
				totalesEntidad= (Cuadratura_Comprobante)totales.get(entidad);
			}
			cuadratura.setTotalM1(totalesEntidad.getM1());
			cuadratura.setTotalM2(totalesEntidad.getM2());
			cuadratura.setTotalM4(totalesEntidad.getM4());
			cuadratura.setTotalM5(totalesEntidad.getM5());
			cuadratura.setTotalM6(totalesEntidad.getM6());
			cuadratura.setTotalM7(totalesEntidad.getM7());
			cuadratura.setTotalM3(totalesEntidad.getM3());
			cuadratura.setTotalM8(totalesEntidad.getM8());
			entidad_old= entidad;
			detalles.add(cuadratura);
		}
		return detalles;
	}
	
	private Map selectTotalesCuadraturaMUTUAL(String cierre)throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select TRIM(X.nombre) , sum(Z.Imponible) as Imponible, sum(Z.Total) as Total from ");
		query.append("(select t1.id_detalle_seccion as Entidad, t1.m2 as Imponible, t1.m3 as Total ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_tipo_seccion=4 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t1.m2, t1.m3 ");
		query.append("UNION ");
		query.append("select t1.id_detalle_seccion as Entidad, 0 as Imponible, t1.m2 as Total  ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_tipo_seccion in (23, 43) ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t1.m2) as Z, Entidad_Mutual Y, Entpagad X ");
		query.append("where Z.Entidad= Y.id_mutual ");
		query.append("and Y.id_ent_pagadora= X.id_ent_pagadora ");
		query.append("group by X.nombre ");
		query.append("order by X.nombre ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, cierre);
		db2.setStatement(2, cierre);
		
		//Se ejecuta la query
		db2.executeQuery();
		
		Map totalesEntidad= new HashMap();
		while (db2.next()) {
			Cuadratura_Comprobante cuadratura= new Cuadratura_Comprobante();
			String entidad= db2.getString(1);
			long totalImponible= db2.getLong(2);
			long totalTotal= db2.getLong(3);
			
			cuadratura.setNombreEntidad(entidad);
			cuadratura.setM2(totalImponible);
			cuadratura.setM3(totalTotal);
			totalesEntidad.put(entidad, cuadratura);
		}
		return totalesEntidad;
	}
	
	private Collection selectCuadraturaMUTUAL(String cierre, Map totales)throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select TRIM(X.nombre) , Z.RutEmpresa, Z.Convenio, Z.TipoNomina, Z.Tasa, sum(Z.Imponible) as Imponible, sum(Z.Total) as Total from ");
		query.append("(select t1.id_detalle_seccion as Entidad, t4.id_empresa as RutEmpresa, t4.id_convenio as Convenio, 'R' as TipoNomina, t1.m1 as Tasa, t1.m2 as Imponible, t1.m3 as Total ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3, nominare t4 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion=4 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t4.id_empresa, t4.id_convenio, t1.m1, t1.m2, t1.m3 ");
		query.append("UNION ");
		query.append("select t1.id_detalle_seccion as Entidad, t4.id_empresa as RutEmpresa, t4.id_convenio as Convenio, 'G' as TipoNomina, t1.m1 as Tasa, 0 as Imponible, t1.m2 as Total ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3, nominagr t4 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion=43 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t4.id_empresa, t4.id_convenio, t1.m1, t1.m2 ");
		query.append("UNION ");
		query.append("select t1.id_detalle_seccion as Entidad, t4.id_empresa as RutEmpresa, t4.id_convenio as Convenio, 'A' as TipoNomina, t1.m1 as Tasa, 0 as Imponible, t1.m2 as Total ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3, nominara t4 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion=23 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t4.id_empresa, t4.id_convenio, t1.m1, t1.m2) as Z, Entidad_Mutual Y, Entpagad X ");
		query.append("where Z.Entidad= Y.id_mutual ");
		query.append("and Y.id_ent_pagadora= X.id_ent_pagadora ");
		query.append("group by X.nombre, Z.RutEmpresa, Z.Convenio, Z.TipoNomina, Z.Tasa ");
		query.append("order by X.nombre,  Z.RutEmpresa, Z.Convenio, Z.TipoNomina desc ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, cierre);
		db2.setStatement(2, cierre);
		db2.setStatement(3, cierre);
		
		//Se ejecuta la query
		db2.executeQuery();
		
		List detalles= new ArrayList();
		String entidad_old="";
		Cuadratura_Comprobante totalesEntidad=null;
		while (db2.next()) {
			Cuadratura_Comprobante cuadratura= new Cuadratura_Comprobante();
			String entidad= db2.getString(1);
			Rut rutEmpresa= new Rut(db2.getInt(2));
			int convenio=  db2.getInt(3);
			String tipoNomina= db2.getString(4);
			int tasa= db2.getInt(5);
			long imponible= db2.getLong(6);
			long total= db2.getLong(7);
			
			cuadratura.setNombreEntidad(entidad);
			cuadratura.setRutEmpresa(rutEmpresa);
			cuadratura.setConvenio(convenio);
			cuadratura.setTipoNomina(tipoNomina);
			cuadratura.setM1(tasa);
			cuadratura.setM2(imponible);
			cuadratura.setM3(total);
			if(!entidad.equals(entidad_old)){
				totalesEntidad= (Cuadratura_Comprobante)totales.get(entidad);
			}
			cuadratura.setTotalM2(totalesEntidad.getM2());
			cuadratura.setTotalM3(totalesEntidad.getM3());
			entidad_old= entidad;
			detalles.add(cuadratura);
		}
		return detalles;
	}
	
	private Map selectTotalesCuadraturaINP(String cierre)throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select TRIM(X.nombre) , sum(Z.Pension) as Pension, sum(Z.Fonasa) as Fonasa, sum(Z.Accidente) as Accidente, sum(Z.Desahucio) as Desahucio, sum(Z.TotalPagos) as TotalPagos, sum(Z.Asfam) as Asfam, sum(Z.Ley15386) as Ley15386, sum(Z.TotalRebajas) as TotalRebajas, sum(Z.Total) as Total from ");
		query.append("(select t1.id_detalle_seccion as Entidad, t1.m2 as Pension, t1.m3 as Fonasa, t1.m4 as Accidente, t1.m5 as Desahucio, t1.m6 as TotalPagos, t1.m7 as Asfam, t1.m8 as Ley15386, t1.m9 as TotalRebajas, t1.m10 as Total ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_tipo_seccion=3 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t1.m2, t1.m3, t1.m4, t1.m5, t1.m6, t1.m7, t1.m8, t1.m9, t1.m10 ");
		query.append("UNION ");
		query.append("select t1.id_detalle_seccion as Entidad, t1.m1 as Pension, t1.m2 as Fonasa, t1.m3 as Accidente, 0 as Desahucio, t1.m4 as TotalPagos, 0 as Asfam, 0 as Ley15386, 0 as TotalRebajas, t1.m4 as Total ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_tipo_seccion in (22, 42) ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t1.m1, t1.m2, t1.m3, t1.m4) as Z, Entidad_Fondo_Pension Y, Entpagad X ");
		query.append("where Z.Entidad= Y.id_ent_fondo_pension ");
		query.append("and Y.id_ent_pagadora= X.id_ent_pagadora ");
		query.append("group by X.nombre ");
		query.append("order by X.nombre ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, cierre);
		db2.setStatement(2, cierre);
		
		//Se ejecuta la query
		db2.executeQuery();
		
		Map totalesEntidad= new HashMap();
		while (db2.next()) {
			Cuadratura_Comprobante cuadratura= new Cuadratura_Comprobante();
			String entidad= db2.getString(1);
			long totalPension= db2.getLong(2);
			long totalFonasa= db2.getLong(3);
			long totalAccidente= db2.getLong(4);
			long totalDesahucio= db2.getLong(5);
			long totalPagos= db2.getLong(6);
			long totalAsfam= db2.getLong(7);
			long totalLey15386= db2.getLong(8);
			long totalRebajas= db2.getLong(9);
			long totalTotal= db2.getLong(10);
			cuadratura.setNombreEntidad(entidad);
			cuadratura.setM2(totalPension);
			cuadratura.setM3(totalFonasa);
			cuadratura.setM4(totalAccidente);
			cuadratura.setM5(totalDesahucio);
			cuadratura.setM6(totalPagos);
			cuadratura.setM7(totalAsfam);
			cuadratura.setM8(totalLey15386);
			cuadratura.setM9(totalRebajas);
			cuadratura.setM10(totalTotal);
			totalesEntidad.put(entidad, cuadratura);
		}
		return totalesEntidad;
	}
	
	private Collection selectCuadraturaINP(String cierre, Map totales)throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select TRIM(X.nombre) , Z.RutEmpresa, Z.Convenio, Z.TipoNomina, sum(Z.Pension) as Pension, sum(Z.Fonasa) as Fonasa, sum(Z.Accidente) as Accidente, sum(Z.Desahucio) as Desahucio, sum(Z.TotalPagos) as TotalPagos, sum(Z.Asfam) as Asfam, sum(Z.Ley15386) as Ley15386, sum(Z.TotalRebajas) as TotalRebajas, sum(Z.Total) as Total from ");
		query.append("(select t1.id_detalle_seccion as Entidad, t4.id_empresa as RutEmpresa, t4.id_convenio as Convenio, 'R' as TipoNomina, t1.m2 as Pension, t1.m3 as Fonasa, t1.m4 as Accidente, t1.m5 as Desahucio, t1.m6 as TotalPagos, t1.m7 as Asfam, t1.m8 as Ley15386, t1.m9 as TotalRebajas, t1.m10 as Total ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3, nominare t4 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion=3 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t4.id_empresa, t4.id_convenio, t1.m2, t1.m3, t1.m4, t1.m5, t1.m6, t1.m7, t1.m8, t1.m9, t1.m10 ");
		query.append("UNION ");
		query.append("select t1.id_detalle_seccion as Entidad, t4.id_empresa as RutEmpresa, t4.id_convenio as Convenio, 'G' as TipoNomina, t1.m1 as Pension, t1.m2 as Fonasa, t1.m3 as Accidente, 0 as Desahucio, t1.m4 as TotalPagos, 0 as Asfam, 0 as Ley15386, 0 as TotalRebajas, t1.m4 as Total ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3, nominagr t4 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion=42 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t4.id_empresa, t4.id_convenio, t1.m1, t1.m2, t1.m3, t1.m4 ");
		query.append("UNION ");
		query.append("select t1.id_detalle_seccion as Entidad, t4.id_empresa as RutEmpresa, t4.id_convenio as Convenio, 'A' as TipoNomina, t1.m1 as Pension, t1.m2 as Fonasa, t1.m3 as Accidente, 0 as Desahucio, t1.m4 as TotalPagos, 0 as Asfam, 0 as Ley15386, 0 as TotalRebajas, t1.m4 as Total ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3, nominara t4 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion=22 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t4.id_empresa, t4.id_convenio, t1.m1, t1.m2, t1.m3, t1.m4) as Z, Entidad_Fondo_Pension Y, Entpagad X ");
		query.append("where Z.Entidad= Y.id_ent_fondo_pension ");
		query.append("and Y.id_ent_pagadora= X.id_ent_pagadora ");
		query.append("group by X.nombre, Z.RutEmpresa, Z.Convenio, Z.TipoNomina ");
		query.append("order by X.nombre,  Z.RutEmpresa, Z.Convenio, Z.TipoNomina desc ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, cierre);
		db2.setStatement(2, cierre);
		db2.setStatement(3, cierre);
		
		//Se ejecuta la query
		db2.executeQuery();
		
		List detalles= new ArrayList();
		String entidad_old="";
		Cuadratura_Comprobante totalesEntidad=null;
		while (db2.next()) {
			Cuadratura_Comprobante cuadratura= new Cuadratura_Comprobante();
			String entidad= db2.getString(1);
			Rut rutEmpresa= new Rut(db2.getInt(2));
			int convenio=  db2.getInt(3);
			String tipoNomina= db2.getString(4);
			long pension= db2.getLong(5);
			long fonasa= db2.getLong(6);
			long accidente= db2.getLong(7);
			long desahucio= db2.getLong(8);
			long pagos= db2.getLong(9);
			long asfam= db2.getLong(10);
			long ley15386= db2.getLong(11);
			long rebajas= db2.getLong(12);
			long total= db2.getLong(13);
			
			cuadratura.setNombreEntidad(entidad);
			cuadratura.setRutEmpresa(rutEmpresa);
			cuadratura.setConvenio(convenio);
			cuadratura.setTipoNomina(tipoNomina);
			cuadratura.setM2(pension);
			cuadratura.setM3(fonasa);
			cuadratura.setM4(accidente);
			cuadratura.setM5(desahucio);
			cuadratura.setM6(pagos);
			cuadratura.setM7(asfam);
			cuadratura.setM8(ley15386);
			cuadratura.setM9(rebajas);
			cuadratura.setM10(total);
			if(!entidad.equals(entidad_old)){
				totalesEntidad= (Cuadratura_Comprobante)totales.get(entidad);
			}
			cuadratura.setTotalM2(totalesEntidad.getM2());
			cuadratura.setTotalM3(totalesEntidad.getM3());
			cuadratura.setTotalM4(totalesEntidad.getM4());
			cuadratura.setTotalM5(totalesEntidad.getM5());
			cuadratura.setTotalM6(totalesEntidad.getM6());
			cuadratura.setTotalM7(totalesEntidad.getM7());
			cuadratura.setTotalM8(totalesEntidad.getM8());
			cuadratura.setTotalM9(totalesEntidad.getM9());
			cuadratura.setTotalM10(totalesEntidad.getM10());
			entidad_old= entidad;
			detalles.add(cuadratura);
		}
		return detalles;
	}

	private Map selectTotalesCuadraturaAPV(String cierre)throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select TRIM(X.nombre) , sum(Z.Voluntario) as Voluntario, sum(Z.Deposito) as Deposito, sum(Z.Aporte ) as Aporte from ");
		query.append("(select t1.id_detalle_seccion as Entidad, t1.m3 as Voluntario, 0 as Deposito, 0 as Aporte ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_tipo_seccion in (6, 7) ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t1.m3 ");
		query.append("UNION ");
		query.append("select t1.id_detalle_seccion as Entidad, 0 as Voluntario, t1.m1 as Deposito, t1.m2 as Aporte ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_tipo_seccion = 60 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t1.m1, t1.m2) as Z, ENTAPV Y, Entpagad X ");
		query.append("where Z.Entidad= Y.id_ent_apv ");
		query.append("and Y.id_ent_pagadora= X.id_ent_pagadora ");
		query.append("group by X.nombre ");
		query.append("order by X.nombre ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, cierre);
		db2.setStatement(2, cierre);
		
		//Se ejecuta la query
		db2.executeQuery();
		
		Map totalesEntidad= new HashMap();
		while (db2.next()) {
			Cuadratura_Comprobante cuadratura= new Cuadratura_Comprobante();
			String entidad= db2.getString(1);
			long totalVolunatrio= db2.getLong(2);
			long totalDeposito= db2.getLong(3);
			long totalAporte= db2.getLong(4);
			
			cuadratura.setNombreEntidad(entidad);
			cuadratura.setM3(totalVolunatrio);
			cuadratura.setM1(totalDeposito);
			cuadratura.setM2(totalAporte);
			totalesEntidad.put(entidad, cuadratura);
		}
		return totalesEntidad;
	}
	
	private Collection selectCuadraturaAPV(String cierre, Map totales)throws SQLException{
		StringBuffer query= new StringBuffer();
		query.append("select TRIM(X.nombre) , Z.RutEmpresa, Z.Convenio, Z.TipoNomina, sum(Z.Voluntario) as Voluntario, sum(Z.Deposito) as Deposito, sum(Z.Aporte) as Aporte from ");
		query.append("(select t1.id_detalle_seccion as Entidad, t4.id_empresa as RutEmpresa, t4.id_convenio as Convenio, 'R' as TipoNomina, t1.m3 as Voluntario, 0 as Deposito, 0 as Aporte ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3, nominare t4 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion in (6, 7) ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t4.id_empresa, t4.id_convenio, t1.m3 ");
		query.append("UNION ");
		query.append("select t1.id_detalle_seccion as Entidad, t4.id_empresa as RutEmpresa, t4.id_convenio as Convenio, 'R' as TipoNomina, 0 as Voluntario, t1.m1 as Deposito, t1.m2 as Aporte ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3, nominare t4 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion=60 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t4.id_empresa, t4.id_convenio, t1.m1, t1.m2 ");
		query.append("UNION ");
		query.append("select t1.id_detalle_seccion as Entidad, t4.id_empresa as RutEmpresa, t4.id_convenio as Convenio, 'D' as TipoNomina, 0 as Voluntario, t1.m1 as Deposito, t1.m2 as Aporte ");
		query.append("from detalle_seccion t1, comprobante_pago t2, seccion t3, nominadc t4 ");
		query.append("where t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("and t1.id_codigo_barra= t3.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion= t3.id_tipo_seccion ");
		query.append("and t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("and t1.id_tipo_seccion=60 ");
		query.append("and t3.tipo_pago<>3 ");
		query.append("and t1.tipo_pago in (1, 2) ");
		query.append("and t2.cierre= ? ");
		query.append("group by  t1.id_detalle_seccion, t4.id_empresa, t4.id_convenio, t1.m1, t1.m2) as Z, ENTAPV Y, Entpagad X ");
		query.append("where Z.Entidad= Y.id_ent_apv ");
		query.append("and Y.id_ent_pagadora= X.id_ent_pagadora ");
		query.append("group by X.nombre, Z.RutEmpresa, Z.Convenio, Z.TipoNomina ");
		query.append("order by X.nombre,  Z.RutEmpresa, Z.Convenio, Z.TipoNomina desc ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, cierre);
		db2.setStatement(2, cierre);
		db2.setStatement(3, cierre);
		
		//Se ejecuta la query
		db2.executeQuery();
		
		List detalles= new ArrayList();
		String entidad_old="";
		Cuadratura_Comprobante totalesEntidad=null;
		while (db2.next()) {
			Cuadratura_Comprobante cuadratura= new Cuadratura_Comprobante();
			String entidad= db2.getString(1);
			Rut rutEmpresa= new Rut(db2.getInt(2));
			int convenio=  db2.getInt(3);
			String tipoNomina= db2.getString(4);
			long voluntario= db2.getLong(5);
			long deposito= db2.getLong(6);
			long aporte= db2.getLong(7);
			
			cuadratura.setNombreEntidad(entidad);
			cuadratura.setRutEmpresa(rutEmpresa);
			cuadratura.setConvenio(convenio);
			cuadratura.setTipoNomina(tipoNomina);
			cuadratura.setM3(voluntario);
			cuadratura.setM1(deposito);
			cuadratura.setM2(aporte);
			if(!entidad.equals(entidad_old)){
				totalesEntidad= (Cuadratura_Comprobante)totales.get(entidad);
			}
			cuadratura.setTotalM1(totalesEntidad.getM1());
			cuadratura.setTotalM2(totalesEntidad.getM2());
			cuadratura.setTotalM3(totalesEntidad.getM3());
			entidad_old= entidad;
			detalles.add(cuadratura);
		}
		return detalles;
	}
	
	//Solo para cerrar conexión por detalle comprobantes
	public void close(){
		try {
			db2det.desconectaDB2();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	

	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	public int insert(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}


}

