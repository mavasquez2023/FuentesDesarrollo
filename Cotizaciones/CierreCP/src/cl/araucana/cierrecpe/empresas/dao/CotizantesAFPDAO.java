

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

import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.planillas.afp.PlanillaAfpCotizante;
import cl.araucana.cierrecpe.empresas.to.DetalleSeccionxSucursalTO;
import cl.araucana.cierrecpe.empresas.to.FiltroCotizantesTO;
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
public class CotizantesAFPDAO implements DAO_Interface {
private ConectaDB2 db2;
private static Logger logger = LogManager.getLogger();	
	
	public CotizantesAFPDAO(ConectaDB2 db2){
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
		query.append("SELECT  t1.id_sucursal, t1.id_cotizante, t1.nombres, t1.apellido_paterno, t1.apellido_materno, t2.renta_imponible, ");
		//Pensión
		query.append("case when id_ent_fondo_pension=0 then 0 else t2.prevision_obligatorio end, t2.prevision_cta_ahorro, " );
		//Cesantía
		query.append("t2.seguro_cesantia, t2.seg_ces_aporte_trabajador, t2.seg_ces_aporte_empleador, ");
		//SIS
		query.append("t2.prevision_sis, ");
		//Fonasa Independiente
		query.append("case when id_ent_salud=0 then t2.salud_total else 0 end, ");
		//Movimiento Personal
		query.append("t3.id_mvto, t3.id_tipo_mvto, t3.inicio, t3.termino, ");
		//Dias Trabajados y Genero
		query.append("t1.n_dias_trabajados, left(t4.nombre, 1)  ");
		query.append("FROM cotizante t1, remunerac t2  left join mvtoperso t3 ");
		query.append("ON t2.id_empresa= t3.id_empresa  ");
		query.append("AND t2.id_convenio= t3.id_convenio ");
		query.append("AND t2.id_cotizante= t3.id_cotizante, ");
		query.append("genero t4 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_genero= t4.id_genero  ");
		query.append("AND (id_ent_fondo_pension= ? OR ent_id_ent_afc= ?)  ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		query.append("AND t1.id_sucursal = ? ");
		query.append("AND t1.id_entidad_afpv<=0 ");
		query.append("ORDER BY t1.id_sucursal, t1.id_cotizante, t3.id_mvto ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getTipo_detalle());
		db2.setStatement(2, filtroTO.getTipo_detalle());
		db2.setStatement(3, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(4, filtroTO.getConvenio());
		db2.setStatement(5, filtroTO.getId_sucursal());
		
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
			int obligatorio= db2.getInt(7);
			int ahorro= db2.getInt(8);
			int renta_cesantia= db2.getInt(9);
			int cesantia_tra= db2.getInt(10);
			int cesantia_emp= db2.getInt(11);
			int seguroInvalidez= db2.getInt(12);
			int fonasaIndependiente= db2.getInt(13);
			if(!filtroTO.getTipo_cliente().equals(Constants.TIPO_CLIENTE_INDEPENDIENTE)){
				fonasaIndependiente=0;
			}
			String id_mvto= db2.getString(14);
			//Se genera nueva instancia de cotizante
			PlanillaAfpCotizante cotizante= new PlanillaAfpCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			if (id_mvto==null || id_mvto.equals("1")){
				cotizante.setRemuneracionImponibleFdoPensionCotizante(renta_imponible);
				cotizante.setCotizacionObligatoriaFdoPensionCotizante(obligatorio);
				cotizante.setDepositoCtaAhorroFdoPensionCotizante(ahorro);
				cotizante.setRemuneracionImponibleCesantiaCotizante(renta_cesantia);
				cotizante.setCotizacionAfiliadoCesantiaCotizante(cesantia_tra);
				cotizante.setCotizacionEmpleadorCesantiaCotizante(cesantia_emp);
				cotizante.setSeguroInvalidezFdoPensionCotizante(seguroInvalidez);
				cotizante.setCotizacionFonasaIndependiente(fonasaIndependiente);
			}
			if (id_mvto != null){
				int codmov= db2.getInt(15);
				cotizante.setCodigoMovimientoPersonalCotizante(codmov);
				Date fecha_inicio=db2.getDate(16);
				if(fecha_inicio!=null){
					cotizante.setFechaInicioMovimientoPersonalCotizante(new AbsoluteDate(fecha_inicio));
				}
				Date fecha_termino=db2.getDate(17);
				if(fecha_termino!=null){
					cotizante.setFechaTerminoMovimientoPersonalCotizante(new AbsoluteDate(fecha_termino));
				}
			}
			int diasTrabajados= db2.getInt(18);
			cotizante.setDiasTrabajados(diasTrabajados);
			String generoCotizante=db2.getString(19);
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
		query.append("SELECT  t1.id_sucursal, t1.id_cotizante, nombres, apellido_paterno, apellido_materno, gratificacion, ");
		//Pensión
		query.append("case when id_ent_fondo_pension=0 then 0 else t2.prevision_obligatorio end, ");
		//Cesantía
		query.append("seg_ces_rem_imp, seg_ces_aporte_trabajador, seg_ces_aporte_empleador, ");
		//SIS
		query.append("t2.prevision_sis, ");
		//Genero
		query.append("left(t3.nombre, 1),  ");
		//fechas grati
		query.append("t2.inicio, t2.termino  ");
		query.append("FROM cotizante t1, gratificac t2, genero t3 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_genero= t3.id_genero ");
		query.append("AND (id_ent_fondo_pension= ? OR ent_id_ent_afc= ?)  ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		query.append("AND t1.id_sucursal = ? ");
		query.append("ORDER BY t1.id_sucursal, t1.id_cotizante ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getTipo_detalle());
		db2.setStatement(2, filtroTO.getTipo_detalle());
		db2.setStatement(3, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(4, filtroTO.getConvenio());
		db2.setStatement(5, filtroTO.getId_sucursal());
		
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
			int obligatorio= db2.getInt(7);
			int renta_cesantia= db2.getInt(8);
			int cesantia_tra= db2.getInt(9);
			int cesantia_emp= db2.getInt(10);
			int seguroInvalidez= db2.getInt(11);
			String generoCotizante=db2.getString(12);
			Date fechaini= db2.getDate(13);
			Date fechater= db2.getDate(14);
			
			//Se genera nueva instancia de cotizante
			PlanillaAfpCotizante cotizante= new PlanillaAfpCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			cotizante.setRemuneracionImponibleFdoPensionCotizante(gratificacion);
			cotizante.setCotizacionObligatoriaFdoPensionCotizante(obligatorio);
			cotizante.setRemuneracionImponibleCesantiaCotizante(renta_cesantia);
			cotizante.setCotizacionAfiliadoCesantiaCotizante(cesantia_tra);
			cotizante.setCotizacionEmpleadorCesantiaCotizante(cesantia_emp);
			cotizante.setSeguroInvalidezFdoPensionCotizante(seguroInvalidez);
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
		query.append("SELECT  t1.id_sucursal, t1.id_cotizante, nombres, apellido_paterno, apellido_materno, reliquidacion, ");
		//Pensión
		query.append("case when id_ent_fondo_pension=0 then 0 else t2.prevision_obligatorio end, ");
		//Cesantía
		query.append("seguro_cesantia, seg_ces_aporte_trabajador, seg_ces_aporte_empleador, ");
		//SIS
		query.append("t2.prevision_sis, ");
		//Genero
		query.append("left(t3.nombre, 1)  ");
		
		query.append("FROM cotizante t1, reliquidac t2, genero t3 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_genero= t3.id_genero ");
		query.append("AND (id_ent_fondo_pension= ? OR ent_id_ent_afc= ?)  ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		query.append("AND t1.id_sucursal = ? ");
		query.append("ORDER BY t1.id_sucursal, t1.id_cotizante ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getTipo_detalle());
		db2.setStatement(2, filtroTO.getTipo_detalle());
		db2.setStatement(3, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(4, filtroTO.getConvenio());
		db2.setStatement(5, filtroTO.getId_sucursal());
		
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
			int obligatorio= db2.getInt(7);
			int renta_cesantia= db2.getInt(8);
			int cesantia_tra= db2.getInt(9);
			int cesantia_emp= db2.getInt(10);
			int seguroInvalidez= db2.getInt(11);
			String generoCotizante=db2.getString(12);
			
			//Se genera nueva instancia de cotizante
			PlanillaAfpCotizante cotizante= new PlanillaAfpCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			cotizante.setRemuneracionImponibleFdoPensionCotizante(reliquidacion);
			cotizante.setCotizacionObligatoriaFdoPensionCotizante(obligatorio);
			cotizante.setRemuneracionImponibleCesantiaCotizante(renta_cesantia);
			cotizante.setCotizacionAfiliadoCesantiaCotizante(cesantia_tra);
			cotizante.setCotizacionEmpleadorCesantiaCotizante(cesantia_emp);
			cotizante.setSeguroInvalidezFdoPensionCotizante(seguroInvalidez);
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
		//Pensión
		query.append("SELECT t1.id_sucursal, ");
		query.append("sum(t2.renta_imponible) as RemuImponible, sum(case when id_ent_fondo_pension=0 then 0 else t2.prevision_obligatorio end) as Obligatorio, sum(t2.prevision_cta_ahorro) as Ahorro, " );
		//SIS
		query.append("sum(t2.prevision_sis) as SIS, ");
		//Fonasa Independiente
		query.append("sum(case when id_ent_salud=0 then t2.salud_total else 0 end) as FONASA, ");
		//Cesantía
		query.append("sum(t2.seguro_cesantia) as RemuCesantia , sum(t2.seg_ces_aporte_trabajador) as AporteCesantiaTra , sum(t2.seg_ces_aporte_empleador) as AporteCesantiaEmp, ");
		query.append("count(*) ");

		query.append("FROM cotizante t1, remunerac t2 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND (id_ent_fondo_pension= ? OR ent_id_ent_afc= ?) ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		query.append("AND t1.id_entidad_afpv<=0 ");
		query.append("GROUP BY t1.id_sucursal ");
		query.append("ORDER BY t1.id_sucursal ");
		
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
			int suma_obligatorio= db2.getInt(3);
			int suma_ahorro= db2.getInt(4);
			int suma_sis= db2.getInt(5);
			int suma_fonasa= db2.getInt(6);
			if(!filtroTO.getTipo_cliente().equals(Constants.TIPO_CLIENTE_INDEPENDIENTE)){
				suma_fonasa=0;
			}
			long suma_renta_cesantia= db2.getLong(7);
			int suma_cesantia_tra= db2.getInt(8);
			int suma_cesantia_emp= db2.getInt(9);
			int n_trabajadores= db2.getInt(10);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM1(suma_renta_imponible);
			totalesSucursal.setM2(suma_obligatorio);
			totalesSucursal.setM3(suma_ahorro);
			totalesSucursal.setM4(suma_obligatorio+suma_ahorro);
			totalesSucursal.setM5(suma_renta_cesantia);
			totalesSucursal.setM6(suma_cesantia_tra);
			totalesSucursal.setM7(suma_cesantia_emp);
			totalesSucursal.setM8(suma_cesantia_tra+suma_cesantia_emp);
			totalesSucursal.setM12(suma_sis);
			totalesSucursal.setM13(suma_fonasa);
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
		//Pensión
		query.append("SELECT t1.id_sucursal, ");
		query.append("sum(t2.gratificacion) as Gratificacion, sum(case when id_ent_fondo_pension=0 then 0 else t2.prevision_obligatorio end) as Obligatorio, " );
		//SIS
		query.append("sum(t2.prevision_sis) as SIS, "); 
		//Cesantía
		query.append("sum(t2.seg_ces_rem_imp) as RemuCesantia , sum(t2.seg_ces_aporte_trabajador) as AporteCesantiaTra , sum(t2.seg_ces_aporte_empleador) as AporteCesantiaEmp, ");
		query.append("count(*) ");

		query.append("FROM cotizante t1, gratificac t2 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND (id_ent_fondo_pension= ? OR ent_id_ent_afc= ?) ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		query.append("GROUP BY t1.id_sucursal ");
		query.append("ORDER BY t1.id_sucursal ");
		
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
			int suma_obligatorio= db2.getInt(3);
			int suma_sis= db2.getInt(4);
			long suma_renta_cesantia= db2.getLong(5);
			int suma_cesantia_tra= db2.getInt(6);
			int suma_cesantia_emp= db2.getInt(7);
			int n_trabajadores= db2.getInt(8);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM1(suma_gratificacion);
			totalesSucursal.setM2(suma_obligatorio);
			totalesSucursal.setM4(suma_obligatorio);
			totalesSucursal.setM5(suma_renta_cesantia);
			totalesSucursal.setM6(suma_cesantia_tra);
			totalesSucursal.setM7(suma_cesantia_emp);
			totalesSucursal.setM8(suma_cesantia_tra+suma_cesantia_emp);
			totalesSucursal.setM12(suma_sis);
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
		//Pensión
		query.append("SELECT t1.id_sucursal, ");
		query.append("sum(t2.reliquidacion) as Reliquidacion, sum(case when id_ent_fondo_pension=0 then 0 else t2.prevision_obligatorio end) as Obligatorio, " );
		//SIS
		query.append("sum(t2.prevision_sis) as SIS, ");
		//Cesantía
		query.append("sum(t2.seguro_cesantia) as RemuCesantia , sum(t2.seg_ces_aporte_trabajador) as AporteCesantiaTra , sum(t2.seg_ces_aporte_empleador) as AporteCesantiaEmp, ");
		query.append("count(*) ");

		query.append("FROM cotizante t1, reliquidac t2 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND (id_ent_fondo_pension= ? OR ent_id_ent_afc= ?) ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		query.append("GROUP BY t1.id_sucursal ");
		query.append("ORDER BY t1.id_sucursal ");
		
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
			int suma_obligatorio= db2.getInt(3);
			int suma_sis= db2.getInt(4);
			long suma_renta_cesantia= db2.getLong(5);
			int suma_cesantia_tra= db2.getInt(6);
			int suma_cesantia_emp= db2.getInt(7);
			int n_trabajadores= db2.getInt(8);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM1(suma_reliquidacion);
			totalesSucursal.setM2(suma_obligatorio);
			totalesSucursal.setM4(suma_obligatorio);
			totalesSucursal.setM5(suma_renta_cesantia);
			totalesSucursal.setM6(suma_cesantia_tra);
			totalesSucursal.setM7(suma_cesantia_emp);
			totalesSucursal.setM8(suma_cesantia_tra+suma_cesantia_emp);
			totalesSucursal.setM12(suma_sis);
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

