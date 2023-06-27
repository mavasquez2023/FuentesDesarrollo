

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
import cl.araucana.cierrecpe.empresas.planillas.ccaf.PlanillaCcafCotizante;
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
public class CotizantesCcafDAO implements DAO_Interface {
private ConectaDB2 db2;
private static Logger logger = LogManager.getLogger();	
	
	public CotizantesCcafDAO(ConectaDB2 db2){
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
		query.append("SELECT  t1.id_sucursal, t1.id_cotizante, t1.nombres, t1.apellido_paterno, t1.apellido_materno, t2.renta_imponible,  ");
		query.append("t1.id_ent_salud, case when t4.nombre='Ninguno' then '0' else t4.nombre end as tramo , t1.id_genero, t1.n_cargas_simples, t1.n_cargas_maternales, t1.n_cargas_invalidez, t1.n_dias_trabajados, ");
		query.append("t2.asig_familiar, t2.asig_fam_retroactivo, t2.asig_fam_reintegros, t2.ccaf_creditos, t2.ccaf_dentales, t2.ccaf_leasing, t2.ccaf_seguro_vida, t2.ccaf_otros, t2.ccaf_aporte, ");
		//Mov. de Personal
		query.append("t5.id_mvto, t5.id_tipo_mvto, t5.inicio, t5.termino, ");
		//Genero
		query.append("left(t6.nombre, 1)  ");
		//query.append("t3.id_mvto, t3.id_tipo_mvto, t3.inicio, t3.termino ");
		query.append("FROM cotizante t1, remunerac t2  left join mvtoperso t5 ");
		query.append("ON t2.id_empresa= t5.id_empresa ");
		query.append("AND t2.id_convenio= t5.id_convenio ");
		query.append("AND t2.id_cotizante= t5.id_cotizante, ");	
		query.append("convenio t3, tramoasfam t4, genero t6 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= t3.id_empresa ");
		query.append("AND t1.id_convenio= t3.id_convenio ");
		query.append("AND t1.id_tramo= t4.id_tramo ");
		query.append("AND t1.id_genero= t6.id_genero ");
		query.append("AND t3.id_ccaf= ? ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		if(filtroTO.isPlanillaxSucursal()){
			query.append("AND t1.id_sucursal = ? ");
		}
		query.append("AND t1.id_entidad_afpv<=0 ");
		query.append("ORDER BY t1.id_cotizante, t5.id_mvto ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getTipo_detalle());
		db2.setStatement(2, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(3, filtroTO.getConvenio());
		if(filtroTO.isPlanillaxSucursal()){
			db2.setStatement(4, filtroTO.getId_sucursal());
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
			int id_ent_salud= db2.getInt(7);
			String nombre_tramo= db2.getString(8);
			int genero= db2.getInt(9);
			int n_cargas_simples= db2.getInt(10);
			int n_cargas_maternales= db2.getInt(11);
			int n_cargas_invalidez= db2.getInt(12);
			int n_dias_trabajados= db2.getInt(13);
			int asig_familiar= db2.getInt(14);
			int asig_fam_retroactivo= db2.getInt(15);
			int asig_fam_reintegros= db2.getInt(16);
			int ccaf_creditos= db2.getInt(17);
			int ccaf_dentales= db2.getInt(18);
			int ccaf_leasing= db2.getInt(19);
			int ccaf_seguro_vida= db2.getInt(20);
			int ccaf_otros= db2.getInt(21);
			int ccaf_aporte= db2.getInt(22);
			String id_mvto= db2.getString(23);
			int codmov= db2.getInt(24);
			Date fecha_inicio=db2.getDate(25);
			Date fecha_termino=db2.getDate(26);
			String generoCotizante=db2.getString(27);
			
			//Se genera nueva instancia de cotizante
			PlanillaCcafCotizante cotizante= new PlanillaCcafCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			cotizante.setEntidadSalud(id_ent_salud);
			cotizante.setCodigoSexo(genero);
			cotizante.setGeneroCotizante(generoCotizante);
			if (id_mvto==null || id_mvto.equals("1")){
				cotizante.setRemuneracionImponibleCotizante(renta_imponible);
				cotizante.setCodigoTramo(nombre_tramo.charAt(0));
				cotizante.setCantidadCargasSimCotizante(n_cargas_simples);
				cotizante.setCantidadCargasInvCotizante(n_cargas_invalidez);
				cotizante.setCantidadCargasMatCotizante(n_cargas_maternales);
				cotizante.setDiasTrabajadosCotizante(n_dias_trabajados);
				cotizante.setDiasTrabajados(n_dias_trabajados);
				cotizante.setMontoAsigFamiliarCotizante(asig_familiar);
				cotizante.setMontoAsigFamiliarRetroactivoCotizante(asig_fam_retroactivo);
				cotizante.setMontoAsigFamiliarReintegroCotizante(asig_fam_reintegros);
				cotizante.setMontoCuotaCredito(ccaf_creditos);
				cotizante.setMontoCuotaConvenioDental(ccaf_dentales);
				cotizante.setMontoCuotaLeasing(ccaf_leasing);
				cotizante.setMontoCuotaSeguroVida(ccaf_seguro_vida);
				cotizante.setMonto06Cotizante(ccaf_aporte);
				cotizante.setMontoOtrosCotizante(ccaf_otros);
			}
			if (id_mvto != null){
				cotizante.setCodigoMovimiento(codmov);
				if(fecha_inicio!=null){
					cotizante.setFechaDesdeAsigFamiliarCotizante(new AbsoluteDate(fecha_inicio));
				}
				if(fecha_termino!=null){
					cotizante.setFechaHastaAsigFamiliarCotizante(new AbsoluteDate(fecha_termino));
				}
			}
			cotizante.setNacionalidad('S');
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
		query.append("SELECT  t1.id_sucursal, t1.id_cotizante, t1.nombres, t1.apellido_paterno, t1.apellido_materno, t2.gratificacion,  ");
		query.append("t1.id_ent_salud, t1.id_genero, t2.ccaf_aporte, ");
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
		query.append("AND t3.id_ccaf= ? ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		if(filtroTO.isPlanillaxSucursal()){
			query.append("AND t1.id_sucursal = ? ");
		}
		query.append("ORDER BY t1.id_cotizante ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getTipo_detalle());
		db2.setStatement(2, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(3, filtroTO.getConvenio());
		if(filtroTO.isPlanillaxSucursal()){
			db2.setStatement(4, filtroTO.getId_sucursal());
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
			int id_ent_salud= db2.getInt(7);
			int genero= db2.getInt(8);
			int ccaf_aporte= db2.getInt(9);
			String generoCotizante=db2.getString(10);
			Date fechaini= db2.getDate(11);
			Date fechater= db2.getDate(12);
			
			//Se genera nueva instancia de cotizante
//			Se genera nueva instancia de cotizante
			PlanillaCcafCotizante cotizante= new PlanillaCcafCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			cotizante.setEntidadSalud(id_ent_salud);
			cotizante.setRemuneracionImponibleCotizante(gratificacion);
			cotizante.setCodigoSexo(genero);
			cotizante.setMonto06Cotizante(ccaf_aporte);
			cotizante.setNacionalidad('S');
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
		query.append("SELECT  t1.id_sucursal, t1.id_cotizante, t1.nombres, t1.apellido_paterno, t1.apellido_materno, t2.reliquidacion,  ");
		query.append("t1.id_ent_salud, t1.id_genero, t2.ccaf_aporte, ");
		//Genero
		query.append("left(t4.nombre, 1)  ");
		query.append("FROM cotizante t1, reliquidac t2, convenio t3, t4 genero ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= t3.id_empresa ");
		query.append("AND t1.id_convenio= t3.id_convenio ");
		query.append("AND t1.id_genero= t4.id_genero ");
		query.append("AND t3.id_ccaf= ? ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		if(filtroTO.isPlanillaxSucursal()){
			query.append("AND t1.id_sucursal = ? ");
		}
		query.append("ORDER BY t1.id_cotizante ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getTipo_detalle());
		db2.setStatement(2, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(3, filtroTO.getConvenio());
		if(filtroTO.isPlanillaxSucursal()){
			db2.setStatement(4, filtroTO.getId_sucursal());
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
			int id_ent_salud= db2.getInt(7);
			int genero= db2.getInt(8);
			int ccaf_aporte= db2.getInt(9);
			String generoCotizante=db2.getString(10);
			
			//Se genera nueva instancia de cotizante
//			Se genera nueva instancia de cotizante
			PlanillaCcafCotizante cotizante= new PlanillaCcafCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			cotizante.setEntidadSalud(id_ent_salud);
			cotizante.setRemuneracionImponibleCotizante(reliquidacion);
			cotizante.setCodigoSexo(genero);
			cotizante.setMonto06Cotizante(ccaf_aporte);
			cotizante.setNacionalidad('S');
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
		//Renta Imponible
		query.append("sum(t2.renta_imponible) as Renta_Imponible, " );
		//Montos
		query.append("sum(t2.ccaf_aporte) as Aporte06, ");
		query.append("sum(t2.asig_familiar + t2.asig_fam_retroactivo - t2.asig_fam_reintegros) as Asfam, ");
		query.append("sum(t2.ccaf_creditos) as Creditos, ");
		query.append("sum(t2.ccaf_leasing) as Leasing, ");
		query.append("sum(t2.ccaf_seguro_vida) as Seguro_Vida, ");
		query.append("sum(t2.ccaf_dentales) as Dentales, ");
		query.append("count(*) ");
		
		query.append("FROM cotizante t1, remunerac t2, convenio t3 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= t3.id_empresa ");
		query.append("AND t1.id_convenio= t3.id_convenio ");
		query.append("AND t3.id_ccaf= ? ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		if(filtroTO.isPlanillaxSucursal()){
			query.append("GROUP BY t1.id_sucursal ");
			query.append("ORDER BY t1.id_sucursal ");
		}
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getTipo_detalle());
		db2.setStatement(2, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(3, filtroTO.getConvenio());
				
		//Se ejecuta la query
		db2.executeQuery();
		List sucursales= new ArrayList();
		while (db2.next()) {
			//Se rescatan los datos de los cotizantes
			String id_sucursal= db2.getString(1);
			long suma_renta_imponible= db2.getLong(2);
			int suma_aporte= db2.getInt(3);
			int suma_asfam= db2.getInt(4);
			int suma_creditos= db2.getInt(5);
			int suma_leasing= db2.getInt(6);
			int suma_segurovida= db2.getInt(7);
			int suma_dental= db2.getInt(8);
			int n_trabajadores= db2.getInt(9);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM1(suma_aporte);
			totalesSucursal.setM2(suma_asfam);
			totalesSucursal.setM3(suma_asfam-suma_aporte);
			totalesSucursal.setM4(suma_creditos);
			totalesSucursal.setM5(suma_leasing);
			totalesSucursal.setM6(suma_segurovida);
			totalesSucursal.setM7(suma_dental);
			//totalesSucursal.setM8(suma_aporte-suma_asfam+suma_creditos+suma_leasing+suma_segurovida+suma_dental);//total compensado según Caja.(PENDIENTE)
			totalesSucursal.setM9(suma_renta_imponible);
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
		//Gratificacion
		query.append("sum(t2.gratificacion) as Gratificacion, " );
		//Montos
		query.append("sum(t2.ccaf_aporte) as Aporte06, ");
		query.append("count(*) ");

		query.append("FROM cotizante t1, gratificac t2, convenio t3 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= t3.id_empresa ");
		query.append("AND t1.id_convenio= t3.id_convenio ");
		query.append("AND t3.id_ccaf= ? ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		if(filtroTO.isPlanillaxSucursal()){
			query.append("GROUP BY t1.id_sucursal ");
			query.append("ORDER BY t1.id_sucursal ");
		}
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getTipo_detalle());
		db2.setStatement(2, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(3, filtroTO.getConvenio());
				
		//Se ejecuta la query
		db2.executeQuery();
		List sucursales= new ArrayList();
		while (db2.next()) {
			//Se rescatan los datos de los cotizantes
			String id_sucursal= db2.getString(1);
			long suma_gratificacion= db2.getLong(2);
			int suma_aporte= db2.getInt(3);
			int n_trabajadores= db2.getInt(4);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM1(suma_aporte);
			totalesSucursal.setM9(suma_gratificacion);
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
		//Reliquidacion
		query.append("sum(t2.reliquidacion) as Reliquidacion, " );
		//Montos
		query.append("sum(t2.ccaf_aporte) as Aporte06, ");
		query.append("count(*) ");

		query.append("FROM cotizante t1, reliquidac t2, convenio t3 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= t3.id_empresa ");
		query.append("AND t1.id_convenio= t3.id_convenio ");
		query.append("AND t3.id_ccaf= ? ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		if(filtroTO.isPlanillaxSucursal()){
			query.append("GROUP BY t1.id_sucursal ");
			query.append("ORDER BY t1.id_sucursal ");
		}
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getTipo_detalle());
		db2.setStatement(2, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(3, filtroTO.getConvenio());
				
		//Se ejecuta la query
		db2.executeQuery();
		List sucursales= new ArrayList();
		while (db2.next()) {
			//Se rescatan los datos de los cotizantes
			String id_sucursal= db2.getString(1);
			long suma_reliquidacion= db2.getLong(2);
			int suma_aporte= db2.getInt(3);
			int n_trabajadores= db2.getInt(4);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM1(suma_aporte);
			totalesSucursal.setM9(suma_reliquidacion);
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

