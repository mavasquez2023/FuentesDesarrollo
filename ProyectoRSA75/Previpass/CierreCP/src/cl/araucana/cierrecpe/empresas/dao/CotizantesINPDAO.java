

/*
 * @(#) EmpresaCPDAO.java    1.0 10-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.planillas.inp.PlanillaInpCotizante;
import cl.araucana.cierrecpe.empresas.to.DetalleSeccionxSucursalTO;
import cl.araucana.cierrecpe.empresas.to.FiltroCotizantesTO;
import cl.araucana.core.util.AbsoluteDate;
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
public class CotizantesINPDAO implements DAO_Interface {
private ConectaDB2 db2;
private static Logger logger = LogManager.getLogger();	
	
	public CotizantesINPDAO(ConectaDB2 db2){
		this.db2= db2;
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
	
	public Object select(Object pk) throws SQLException {
		Object cotizantes=null;
		FiltroCotizantesTO filtroTO= (FiltroCotizantesTO) pk;
		String tipoProceso= filtroTO.getTipoProceso();
		if(tipoProceso.equals("R")){
			cotizantes= selectRemu(filtroTO);
		}else if(tipoProceso.equals("G")){
			cotizantes= selectGrati(filtroTO);
		}else if(tipoProceso.equals("A")){
			cotizantes= selectReliq(filtroTO);
		}
		return cotizantes;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object selectRemu(Object pk) throws SQLException {
		FiltroCotizantesTO filtroTO= (FiltroCotizantesTO) pk;
		StringBuffer query= new StringBuffer();
		
		//Antecedentes Personales
		query.append("SELECT  t1.id_sucursal, t1.id_cotizante, t1.nombres, t1.apellido_paterno, t1.apellido_materno, t2.renta_imponible,  ");
		//INP Pensión
		query.append("case t1.id_ent_fondo_pension when 0 then t2.prevision_obligatorio else 0 end as INP_Pension, ");
		//Fonasa
		query.append("case t1.id_ent_salud when 0 then t2.salud_obligatorio else 0 end as INP_Salud, ");
		//Accidente del Trabajo
		query.append("case t3.id_mutual when 0 then t2.inp_mutual else 0 end as INP_Mutual, ");
		//Desahucio
		query.append("t2.inp_desahucio, ");
		//Rebajas
		query.append("case t3.id_ccaf when 0 then t2.asig_familiar else 0 end as Asignacion_Familiar, ");
		query.append("case t3.id_ccaf when 0 then t2.asig_fam_retroactivo else 0 end as Retroactivo, ");
		query.append("case t3.id_ccaf when 0 then t2.asig_fam_reintegros else 0 end as Reintegro,  ");
		query.append("t2.inp_bonificacion, ");
		//Tramo y N° Cargas
		query.append("case t3.id_ccaf when 0 then t1.id_tramo else 0 end as Tramo,  ");
		query.append("case t3.id_ccaf when 0 then t1.n_cargas_simples else 0 end as Cargas_Simples, ");
		query.append("case t3.id_ccaf when 0 then t1.n_cargas_maternales else 0 end as Cargas_Maternales, ");
		query.append("case t3.id_ccaf when 0 then t1.n_cargas_invalidez else 0 end as Cargas_Invalidez, ");
		query.append("case t3.id_ccaf when 0 then t5.valor_carga_familiar else 0 end as Valor_Carga, ");
		//Dias Trabajados y Genero
		query.append("t1.n_dias_trabajados, left(t6.nombre, 1)  ");
		query.append("FROM cotizante t1, remunerac t2, convenio t3, empresa t4, tramoasfam t5, genero t6 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= t3.id_empresa ");
		query.append("AND t1.id_convenio= t3.id_convenio ");
		query.append("AND t3.id_empresa= t4.id_empresa ");
		query.append("AND t1.id_tramo= t5.id_tramo ");
		query.append("AND t1.id_genero= t6.id_genero ");
		query.append("AND (t1.id_ent_fondo_pension= ? OR t1.id_ent_salud= ? OR (t3.id_ccaf=0 AND t4.privada=0)  OR t3.id_mutual=0) ");
		query.append("AND t1.id_entidad_afpv<=0 ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		if(filtroTO.isPlanillaxSucursal()){
			query.append("AND t1.id_sucursal = ? ");
		}
		query.append("ORDER BY t1.id_cotizante ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getTipo_detalle());
		db2.setStatement(2, filtroTO.getTipo_detalle());
		db2.setStatement(3, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(4, filtroTO.getConvenio());
		if(filtroTO.isPlanillaxSucursal()){
			db2.setStatement(5, filtroTO.getId_sucursal());
		}
		
		//Se ejecuta la query
		db2.executeQuery();
		List cotizantes= new ArrayList();
		while (db2.next()) {
			//Se rescatan los datos de los cotizantes
			String id_sucursal= db2.getString(1);
			int id_cotizante= db2.getInt(2);
			String nombres=db2.getString(3);
			String apellidos= db2.getString(4) + " " + db2.getString(5);
			int renta_imponible= db2.getInt(6);
			int pension= db2.getInt(7);
			int fonasa= db2.getInt(8);
			int accidente= db2.getInt(9);
			int desahucio= db2.getInt(10);
			int asfam= db2.getInt(11);
			int retroactivo= db2.getInt(12);
			int reintegro= db2.getInt(13);
			int bonificacion= db2.getInt(14);
			int codigoTramo= db2.getInt(15);
			int nroCargasSimple= db2.getInt(16);
			int nroCargasMaternal= db2.getInt(17);
			int nroCargasInvalidez= db2.getInt(18);
			int valorTramo= db2.getInt(19);
			int diasTrabajados= db2.getInt(20);
			String generoCotizante=db2.getString(21);
			
			//Se genera nueva instancia de cotizante
			PlanillaInpCotizante cotizante= new PlanillaInpCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			cotizante.setRemImponibleCotizante(renta_imponible);
			cotizante.setPensionInpCotizante(pension);
			cotizante.setFonasaCotizante(fonasa);
			cotizante.setAccDelTrabajoCotizante(accidente);
			cotizante.setCotizacionDesahucioCotizante(desahucio);
			cotizante.setMontoBonificacionLey15385Cotizante(bonificacion);
			if(filtroTO.getIdCcaf()== 0){
				cotizante.setMontoAsigFamiliarCotizante(asfam);
				cotizante.setMontoAsigFamiliarRetroactivoCotizante(retroactivo);
				cotizante.setMontoAsigFamiliarReintegrosCotizante(reintegro);
				cotizante.setCodigoTramoCotizante(codigoTramo);
				cotizante.setNroCargasSimpleCotizante(nroCargasSimple);
				cotizante.setNroCargasMaternalCotizante(nroCargasMaternal);
				cotizante.setNroCargasInvalidaCotizante(nroCargasInvalidez);
				cotizante.setValorTramoCotizante(valorTramo);
				//Se calcula la diferencia entre lo informado y lo calculado para distruir Retroactivo - Reintegros en caso de diferencia
				cotizante.setDiferenciaAsfam();
			}
			//se guarda en Planilla Inp
			cotizante.setDiasTrabajadosCotizante(diasTrabajados);
			//se guarda en Planilla Base 
			cotizante.setDiasTrabajados(diasTrabajados);
			cotizante.setGeneroCotizante(generoCotizante);
			//se agrega registro de cotizante a la lista
			cotizantes.add(cotizante);
		}
		return cotizantes;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object selectGrati(Object pk) throws SQLException {
		FiltroCotizantesTO filtroTO= (FiltroCotizantesTO) pk;
		StringBuffer query= new StringBuffer();
		//Antecedentes Personales
		query.append("SELECT  t1.id_sucursal, t1.id_cotizante, t1.nombres, t1.apellido_paterno, t1.apellido_materno, t2.gratificacion,  ");
		//Pensión
		query.append("case t1.id_ent_fondo_pension when 0 then t2.prevision_obligatorio else 0 end as INP_Pension, ");
		//Fonasa
		query.append("case t1.id_ent_salud when 0 then t2.salud_obligatorio else 0 end as Fonasa, ");
		//Accidente del Trabajo
		query.append("case t3.id_mutual when 0 then t2.inp_pension else 0 end as Accidente, ");
		//Genero
		query.append("left(t4.nombre, 1),  ");
//		fechas grati
		query.append("t2.inicio, t2.termino  ");
		
		query.append("FROM cotizante t1, gratificac t2, convenio t3, genero t4 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= t3.id_empresa ");
		query.append("AND t1.id_convenio= t3.id_convenio ");
		query.append("AND t1.id_genero= t4.id_genero ");
		query.append("AND (id_ent_fondo_pension= ? OR id_ent_salud= ? OR t3.id_mutual=0) ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		if(filtroTO.isPlanillaxSucursal()){
			query.append("AND t1.id_sucursal = ? ");
		}
		query.append("ORDER BY t1.id_cotizante ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getTipo_detalle());
		db2.setStatement(2, filtroTO.getTipo_detalle());
		db2.setStatement(3, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(4, filtroTO.getConvenio());
		if(filtroTO.isPlanillaxSucursal()){
			db2.setStatement(5, filtroTO.getId_sucursal());
		}
		
		//Se ejecuta la query
		db2.executeQuery();
		List cotizantes= new ArrayList();
		while (db2.next()) {
			//Se rescatan los datos de los cotizantes
			String id_sucursal= db2.getString(1);
			int id_cotizante= db2.getInt(2);
			String nombres=db2.getString(3);
			String apellidos= db2.getString(4) + " " + db2.getString(5);
			int gratificacion= db2.getInt(6);
			int pensionInpCotizante= db2.getInt(7);
			int fonasaCotizante= db2.getInt(8);
			int accDelTrabajoCotizante= db2.getInt(9);
			String generoCotizante=db2.getString(10);
			Date fechaini= db2.getDate(11);
			Date fechater= db2.getDate(12);
			
			//Se genera nueva instancia de cotizante
			PlanillaInpCotizante cotizante= new PlanillaInpCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			cotizante.setRemImponibleCotizante(gratificacion);
			cotizante.setPensionInpCotizante(pensionInpCotizante);
			cotizante.setFonasaCotizante(fonasaCotizante);
			cotizante.setAccDelTrabajoCotizante(accDelTrabajoCotizante);
			cotizante.setGeneroCotizante(generoCotizante);
			cotizante.setFechaInicioGratificacionesCotizante(new AbsoluteDate(fechaini));
			cotizante.setFechaTerminoGratificacionesCotizante(new AbsoluteDate(fechater));
			//se agrega registro de cotizante a la lista
			cotizantes.add(cotizante);
		}
		return cotizantes;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object selectReliq(Object pk) throws SQLException {
		FiltroCotizantesTO filtroTO= (FiltroCotizantesTO) pk;
		StringBuffer query= new StringBuffer();
		//Antecedentes Personales
		query.append("SELECT  t1.id_sucursal, t1.id_cotizante, t1.nombres, t1.apellido_paterno, t1.apellido_materno, t2.reliquidacion,  ");
		//Pensión
		query.append("case t1.id_ent_fondo_pension when 0 then t2.prevision_obligatorio else 0 end as INP_Pension, ");
		//Fonasa
		query.append("case t1.id_ent_salud when 0 then t2.salud_obligatorio else 0 end as Fonasa, ");
		//Accidente del Trabajo
		query.append("case t3.id_mutual when 0 then t2.inp_pension else 0 end as Accidente, ");
//		Genero
		query.append("left(t4.nombre, 1)  ");
		
		query.append("FROM cotizante t1, reliquidac t2, convenio t3, genero t4  ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= t3.id_empresa ");
		query.append("AND t1.id_convenio= t3.id_convenio ");
		query.append("AND t1.id_genero= t4.id_genero ");
		query.append("AND (id_ent_fondo_pension= ? OR id_ent_salud= ? OR t3.id_mutual=0) ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		if(filtroTO.isPlanillaxSucursal()){
			query.append("AND t1.id_sucursal = ? ");
		}
		query.append("ORDER BY t1.id_cotizante ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getTipo_detalle());
		db2.setStatement(2, filtroTO.getTipo_detalle());
		db2.setStatement(3, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(4, filtroTO.getConvenio());
		if(filtroTO.isPlanillaxSucursal()){
			db2.setStatement(5, filtroTO.getId_sucursal());
		}
		
		//Se ejecuta la query
		db2.executeQuery();
		List cotizantes= new ArrayList();
		while (db2.next()) {
			//Se rescatan los datos de los cotizantes
			String id_sucursal= db2.getString(1);
			int id_cotizante= db2.getInt(2);
			String nombres=db2.getString(3);
			String apellidos= db2.getString(4) + " " + db2.getString(5);
			int reliquidacion= db2.getInt(6);
			int pensionInpCotizante= db2.getInt(7);
			int fonasaCotizante= db2.getInt(8);
			int accDelTrabajoCotizante= db2.getInt(9);
			String generoCotizante=db2.getString(10);
			
			//Se genera nueva instancia de cotizante
			PlanillaInpCotizante cotizante= new PlanillaInpCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			cotizante.setRemImponibleCotizante(reliquidacion);
			cotizante.setPensionInpCotizante(pensionInpCotizante);
			cotizante.setFonasaCotizante(fonasaCotizante);
			cotizante.setAccDelTrabajoCotizante(accDelTrabajoCotizante);
			cotizante.setGeneroCotizante(generoCotizante);
			//se agrega registro de cotizante a la lista
			cotizantes.add(cotizante);
		}
		return cotizantes;
	}
	
	public Collection selectTotalxSucursal(Object pk) throws SQLException {
		Collection sucursales=null;
		FiltroCotizantesTO filtroTO= (FiltroCotizantesTO) pk;
		String tipoProceso= filtroTO.getTipoProceso();
		if(tipoProceso.equals("R")){
			sucursales= selectRemuTotalxSucursal(filtroTO);
		}else if(tipoProceso.equals("G")){
			sucursales= selectGratiTotalxSucursal(filtroTO);
		}else if(tipoProceso.equals("A")){
			sucursales= selectReliqTotalxSucursal(filtroTO);
		}
		return sucursales;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Collection selectRemuTotalxSucursal(Object pk) throws SQLException {
		FiltroCotizantesTO filtroTO= (FiltroCotizantesTO) pk;
		StringBuffer query= new StringBuffer();
		//Query
		if(filtroTO.isPlanillaxSucursal()){
			query.append("SELECT t1.id_sucursal, ");
		}else{
			query.append("SELECT '" + filtroTO.getId_sucursal() + "', ");
		}
		query.append("sum(t2.renta_imponible_inp) as Imponible_INP, ");
		query.append("sum(case t1.id_ent_fondo_pension when 0 then t2.prevision_obligatorio else 0 end) as Prevision, ");
		query.append("sum(case t1.id_ent_salud when 0 then t2.salud_obligatorio else 0 end) as Salud, ");
		query.append("sum(case t3.id_mutual when 0 then t2.inp_mutual else 0 end) as Accidente, ");
		query.append("sum(t2.inp_desahucio) as Desahucio, ");
		query.append("sum(case t3.id_ccaf when 0 then (asig_familiar+asig_fam_retroactivo-asig_fam_reintegros) else 0 end) as Asfam, ");
		query.append("sum(t2.inp_bonificacion) as Bonificacion, ");
		query.append("count(*) as N_Trabajadores ");

		query.append("FROM cotizante t1, remunerac t2, convenio t3, empresa t4 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= t3.id_empresa ");
		query.append("AND t1.id_convenio= t3.id_convenio ");
		query.append("AND t3.id_empresa= t4.id_empresa ");
		query.append("AND (t1.id_ent_fondo_pension= ? OR t1.id_ent_salud= ? OR (t3.id_ccaf=0 AND t4.privada=0) OR t3.id_mutual=0) ");
		query.append("AND t1.id_entidad_afpv<=0 ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		if(filtroTO.isPlanillaxSucursal()){
			query.append("GROUP BY t1.id_sucursal ");
			query.append("ORDER BY t1.id_sucursal ");
		}
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getTipo_detalle());
		db2.setStatement(2, filtroTO.getTipo_detalle());
		db2.setStatement(3, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(4, filtroTO.getConvenio());
		
		//Se ejecuta la query
		db2.executeQuery();
		List sucursales= new ArrayList();
		while (db2.next()) {
			//Se rescatan los datos de los cotizantes
			String id_sucursal= db2.getString(1);
			long suma_renta_imponible= db2.getLong(2);
			int suma_pension= db2.getInt(3);
			int suma_salud= db2.getInt(4);
			int suma_accidente= db2.getInt(5);
			int suma_desahucio= db2.getInt(6);
			int suma_asfam=db2.getInt(7);
			int suma_bonificacion= db2.getInt(8);
			int n_trabajadores= db2.getInt(9);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM1(suma_renta_imponible);
			totalesSucursal.setM2(suma_pension);
			totalesSucursal.setM3(suma_salud);
			totalesSucursal.setM4(suma_accidente);
			totalesSucursal.setM5(suma_desahucio);
			long pagos=suma_pension+suma_salud+suma_accidente+suma_desahucio;
			totalesSucursal.setM6(pagos);
			totalesSucursal.setM7(suma_asfam);
			totalesSucursal.setM8(suma_bonificacion);
			int rebajas= suma_bonificacion+suma_asfam;
			totalesSucursal.setM9(rebajas);
			totalesSucursal.setM10(pagos-rebajas);
			totalesSucursal.setN_trabajadores(n_trabajadores);
			sucursales.add(totalesSucursal);
		}
		return sucursales;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Collection selectGratiTotalxSucursal(Object pk) throws SQLException {
		FiltroCotizantesTO filtroTO= (FiltroCotizantesTO) pk;
		StringBuffer query= new StringBuffer();
		//Query
		if(filtroTO.isPlanillaxSucursal()){
			query.append("SELECT t1.id_sucursal, ");
		}else{
			query.append("SELECT '" + filtroTO.getId_sucursal() + "', ");
		}
		query.append("sum(t2.gratificacion) as Gratificacion, ");
		query.append("sum(case t1.id_ent_fondo_pension when 0 then t2.prevision_obligatorio else 0 end) as Prevision, ");
		query.append("sum(case t1.id_ent_salud when 0 then t2.salud_obligatorio else 0 end) as Salud, ");
		query.append("sum(case t3.id_mutual when 0 then t2.inp_pension else 0 end) as Accidente, ");
		query.append("count(*) as N_Trabajadores ");

		query.append("FROM cotizante t1, gratificac t2, convenio t3 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= t3.id_empresa ");
		query.append("AND t1.id_convenio= t3.id_convenio ");
		query.append("AND (id_ent_fondo_pension= ? OR id_ent_salud= ? OR t3.id_mutual=0) ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		if(filtroTO.isPlanillaxSucursal()){
			query.append("GROUP BY t1.id_sucursal ");
			query.append("ORDER BY t1.id_sucursal ");
		}
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getTipo_detalle());
		db2.setStatement(2, filtroTO.getTipo_detalle());
		db2.setStatement(3, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(4, filtroTO.getConvenio());
		//Se ejecuta la query
		db2.executeQuery();
		List sucursales= new ArrayList();
		while (db2.next()) {
			//Se rescatan los datos de los cotizantes
			String id_sucursal= db2.getString(1);
			long suma_gratificacion= db2.getLong(2);
			int suma_pension= db2.getInt(3);
			int suma_salud= db2.getInt(4);
			int suma_accidente= db2.getInt(5);
			int n_trabajadores= db2.getInt(6);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM1(suma_gratificacion);
			totalesSucursal.setM2(suma_pension);
			totalesSucursal.setM3(suma_salud);
			totalesSucursal.setM4(suma_accidente);
			long pagos=suma_pension+suma_salud+suma_accidente;
			totalesSucursal.setM6(pagos);
			totalesSucursal.setM10(pagos);
			totalesSucursal.setN_trabajadores(n_trabajadores);
			sucursales.add(totalesSucursal);
		}
		return sucursales;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Collection selectReliqTotalxSucursal(Object pk) throws SQLException {
		FiltroCotizantesTO filtroTO= (FiltroCotizantesTO) pk;
		StringBuffer query= new StringBuffer();
		//Query
		if(filtroTO.isPlanillaxSucursal()){
			query.append("SELECT t1.id_sucursal, ");
		}else{
			query.append("SELECT '" + filtroTO.getId_sucursal() + "', ");
		}
		query.append("sum(t2.reliquidacion) as Reliquidacion, ");
		query.append("sum(case t1.id_ent_fondo_pension when 0 then t2.prevision_obligatorio else 0 end) as Prevision, ");
		query.append("sum(case t1.id_ent_salud when 0 then t2.salud_obligatorio else 0 end) as Salud, ");
		query.append("sum(case t3.id_mutual when 0 then t2.inp_pension else 0 end) as Accidente, ");
		query.append("count(*) as N_Trabajadores ");

		query.append("FROM cotizante t1, reliquidac t2, convenio t3 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= t3.id_empresa ");
		query.append("AND t1.id_convenio= t3.id_convenio ");
		query.append("AND (id_ent_fondo_pension= ? OR id_ent_salud= ? OR t3.id_mutual=0) ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		if(filtroTO.isPlanillaxSucursal()){
			query.append("GROUP BY t1.id_sucursal ");
			query.append("ORDER BY t1.id_sucursal ");
		}
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getTipo_detalle());
		db2.setStatement(2, filtroTO.getTipo_detalle());
		db2.setStatement(3, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(4, filtroTO.getConvenio());
		
		//Se ejecuta la query
		db2.executeQuery();
		List sucursales= new ArrayList();
		while (db2.next()) {
			//Se rescatan los datos de los cotizantes
			String id_sucursal= db2.getString(1);
			long suma_reliquidacion= db2.getLong(2);
			int suma_pension= db2.getInt(3);
			int suma_salud= db2.getInt(4);
			int suma_accidente= db2.getInt(5);
			int n_trabajadores= db2.getInt(6);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM1(suma_reliquidacion);
			totalesSucursal.setM2(suma_pension);
			totalesSucursal.setM3(suma_salud);
			totalesSucursal.setM4(suma_accidente);
			long pagos=suma_pension+suma_salud+suma_accidente;
			totalesSucursal.setM6(pagos);
			totalesSucursal.setM10(pagos);
			totalesSucursal.setN_trabajadores(n_trabajadores);
			sucursales.add(totalesSucursal);
		}
		return sucursales;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

}

