

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

import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.planillas.apv.PlanillaApvCotizante;
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
public class CotizantesAPVDAO implements DAO_Interface {
private ConectaDB2 db2;
private static Logger logger = LogManager.getLogger();	
	
	public CotizantesAPVDAO(ConectaDB2 db2){
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
		if(tipoProceso.equals("R") && filtroTO.getTipo_seccion()!=Constants.DEP_CONV_AFP){
			cotizantes= selectRemu(filtroTO);
		}else if(tipoProceso.equals("R") && filtroTO.getTipo_seccion()==Constants.DEP_CONV_AFP){
			cotizantes= selectDepConvRemu(filtroTO);
		}else if(tipoProceso.equals("D")){
			cotizantes= selectDepConv(filtroTO);
		}
		return cotizantes;
	}
	
	public Collection selectTotalxSucursal(Object pk) throws SQLException {
		Collection sucursales=null;
		FiltroCotizantesTO filtroTO= (FiltroCotizantesTO) pk;
		String tipoProceso= filtroTO.getTipoProceso();
		if(tipoProceso.equals("R") && filtroTO.getTipo_seccion()!=Constants.DEP_CONV_AFP){
			sucursales= selectRemuTotalxSucursal(filtroTO);
		}else if(tipoProceso.equals("R") && filtroTO.getTipo_seccion()==Constants.DEP_CONV_AFP){
			sucursales= selectDepConvRemuTotalxSucursal(filtroTO);
		}else if(tipoProceso.equals("D")){
			sucursales= selectDepConvTotalxSucursal(filtroTO);
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
		query.append("SELECT t1.id_sucursal, ");
		query.append("sum(t2.renta_imponible) as Renta_Imponible, sum(t3.ahorro) as Ahorro, ");
		query.append("count(*) ");

		query.append("FROM cotizante t1, remunerac t2  left join apv t3 ");
		query.append("ON t2.id_empresa= t3.id_empresa  ");
		query.append("AND t2.id_convenio= t3.id_convenio ");
		query.append("AND t2.id_cotizante= t3.id_cotizante ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t3.id_ent_apv= ? ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		query.append("GROUP BY t1.id_sucursal ");
		query.append("ORDER BY t1.id_sucursal ");
		
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
			int suma_ahorro= db2.getInt(3);
			int n_trabajadores= db2.getInt(4);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM4(suma_renta_imponible);
			totalesSucursal.setM3(suma_ahorro);
			totalesSucursal.setN_trabajadores(n_trabajadores);
			sucursales.add(totalesSucursal);
		}
		return sucursales;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object selectRemu(Object pk) throws SQLException {
		FiltroCotizantesTO filtroTO= (FiltroCotizantesTO) pk;
		StringBuffer query= new StringBuffer();
		query.append("SELECT  t1.id_sucursal, t1.id_cotizante, nombres, apellido_paterno, apellido_materno, renta_imponible, ");
		query.append("t3.ahorro ");
		query.append("FROM cotizante t1, remunerac t2  left join apv t3 ");
		query.append("ON t2.id_empresa= t3.id_empresa  ");
		query.append("AND t2.id_convenio= t3.id_convenio ");
		query.append("AND t2.id_cotizante= t3.id_cotizante ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t3.id_ent_apv= ? ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		query.append("AND t1.id_sucursal = ? ");
		query.append("ORDER BY t1.id_sucursal, t1.id_cotizante ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getTipo_detalle());
		db2.setStatement(2, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(3, filtroTO.getConvenio());
		db2.setStatement(4, filtroTO.getId_sucursal());
		
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
			int ahorro= db2.getInt(7);

			//Se genera nueva instancia de cotizante
			PlanillaApvCotizante cotizante= new PlanillaApvCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			cotizante.setRemuneracionImponibleFdoPensionCotizante(renta_imponible);
			cotizante.setCotizacionVoluntariaFdoPensionCotizante(ahorro);
			//se agrega registro de cotizante a la lista
			cotizantes.add(cotizante);
		}
		return cotizantes;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object selectDepConv(Object pk) throws SQLException {
		FiltroCotizantesTO filtroTO= (FiltroCotizantesTO) pk;
		StringBuffer query= new StringBuffer();
		query.append("SELECT  t1.id_sucursal, t1.id_cotizante, t1.nombres, t1.apellido_paterno, t1.apellido_materno, t2.renta_imponible, ");
		query.append("t2.deposito_convenido, t2.tipo_regimen_previsional, t2.tasa_pactada, t2.indemnizacion_aporte, t2.indemnizacion_inicio, t2.indemnizacion_termino, t2.n_periodos ");
		query.append("FROM cotizante t1, depconveni t2 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t2.id_ent_dep= ? ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		query.append("ORDER BY t1.id_sucursal, t1.id_cotizante ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getTipo_detalle());
		db2.setStatement(2, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(3, filtroTO.getConvenio());
		
		//Se ejecuta la query
		db2.executeQuery();
		List cotizantes= new ArrayList();
		while (db2.next()) {
			//Se rescatan los datos de los cotizantes
			String id_sucursal= db2.getString(1);
			int id_cotizante= db2.getInt(2);
			String nombres=db2.getString(3);
			String apellidos= db2.getString(4) + " " + db2.getString(5);
			int remuneracion= db2.getInt(6);
			int deposito_conv= db2.getInt(7);
			int tipo_regimen= db2.getInt(8);
			double tasa_pactada= db2.getDouble(9);
			int aporte_indemnizacion= db2.getInt(10);
			Date inicio_indemnizacion= db2.getDate(11);
			Date termino_indemnizacion= db2.getDate(12);
			int n_periodos= db2.getInt(13);
			
			//Se genera nueva instancia de cotizante
			PlanillaApvCotizante cotizante= new PlanillaApvCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			cotizante.setRemuneracionImponibleFdoPensionCotizante(remuneracion);
			cotizante.setDepositoConvenidoCotizante(deposito_conv);
			cotizante.setRegimenPrevisionalCtaIndemCotizante(tipo_regimen);
			cotizante.setTasaPactadaCtaIdemCotizante(tasa_pactada);
			cotizante.setAporteCtaIndemCotizante(aporte_indemnizacion);
			cotizante.setFechaDesdeCtaIndemCotizante(new AbsoluteDate(inicio_indemnizacion));
			cotizante.setFechaHastaCtaIndemCotizante(new AbsoluteDate(termino_indemnizacion));
			cotizante.setNumeroPeriodosCtaIndemCotizante(n_periodos);
			//se agrega registro de cotizante a la lista
			cotizantes.add(cotizante);
		}
		return cotizantes;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Collection selectDepConvTotalxSucursal(Object pk) throws SQLException {
		FiltroCotizantesTO filtroTO= (FiltroCotizantesTO) pk;
		StringBuffer query= new StringBuffer();
		//Query
		query.append("SELECT t1.id_sucursal, ");
		query.append("sum(t2.renta_imponible) as Renta_Imponible, sum(t2.deposito_convenido) as Deposito_Convenido, sum(t2.indemnizacion_aporte) as Aporte, ");
		query.append("count(*) ");

		query.append("FROM cotizante t1, depconveni t2 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t2.id_ent_dep= ? ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		query.append("GROUP BY t1.id_sucursal ");
		query.append("ORDER BY t1.id_sucursal ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		//db2.setStatement(1, 5); //PAGADO
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
			int suma_deposito= db2.getInt(3);
			int suma_aporte= db2.getInt(4);
			int n_trabajadores= db2.getInt(5);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM1(suma_deposito);
			totalesSucursal.setM2(suma_aporte);
			totalesSucursal.setM3(suma_deposito+suma_aporte);
			totalesSucursal.setM4(suma_renta_imponible);
			totalesSucursal.setN_trabajadores(n_trabajadores);
			sucursales.add(totalesSucursal);
		}
		return sucursales;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Collection selectDepConvRemuTotalxSucursal(Object pk) throws SQLException {
		FiltroCotizantesTO filtroTO= (FiltroCotizantesTO) pk;
		StringBuffer query= new StringBuffer();
		//Query
		query.append("SELECT t1.id_sucursal, ");
		query.append("sum(t2.renta_imponible) as Renta_Imponible, sum(t2.deposito_convenido) as Deposito_Convenido, sum(t2.indemnizacion_aporte) as Aporte, ");
		query.append("count(*) ");

		query.append("FROM cotizante t1, remunerac t2 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t2.id_ent_dep= ? ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		query.append("GROUP BY t1.id_sucursal ");
		query.append("ORDER BY t1.id_sucursal ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		//db2.setStatement(1, 5); //PAGADO
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
			int suma_deposito= db2.getInt(3);
			int suma_aporte= db2.getInt(4);
			int n_trabajadores= db2.getInt(5);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM1(suma_deposito);
			totalesSucursal.setM2(suma_aporte);
			totalesSucursal.setM3(suma_deposito+suma_aporte);
			totalesSucursal.setM4(suma_renta_imponible);
			totalesSucursal.setN_trabajadores(n_trabajadores);
			sucursales.add(totalesSucursal);
		}
		return sucursales;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object selectDepConvRemu(Object pk) throws SQLException {
		FiltroCotizantesTO filtroTO= (FiltroCotizantesTO) pk;
		StringBuffer query= new StringBuffer();
		query.append("SELECT  t1.id_sucursal, t1.id_cotizante, t1.nombres, t1.apellido_paterno, t1.apellido_materno, t2.renta_imponible, ");
		query.append("t2.deposito_convenido, t2.tipo_regimen_previsional, t2.tasa_pactada, t2.indemnizacion_aporte, t2.indemnizacion_inicio, t2.indemnizacion_termino, t2.n_periodos ");
		query.append("FROM cotizante t1, remunerac t2 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t2.id_ent_dep= ? ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		query.append("ORDER BY t1.id_sucursal, t1.id_cotizante ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getTipo_detalle());
		db2.setStatement(2, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(3, filtroTO.getConvenio());
		
		//Se ejecuta la query
		db2.executeQuery();
		List cotizantes= new ArrayList();
		while (db2.next()) {
			//Se rescatan los datos de los cotizantes
			String id_sucursal= db2.getString(1);
			int id_cotizante= db2.getInt(2);
			String nombres=db2.getString(3);
			String apellidos= db2.getString(4) + " " + db2.getString(5);
			int remuneracion= db2.getInt(6);
			int deposito_conv= db2.getInt(7);
			int tipo_regimen= db2.getInt(8);
			double tasa_pactada= db2.getDouble(9);
			int aporte_indemnizacion= db2.getInt(10);
			Date inicio_indemnizacion= db2.getDate(11);
			Date termino_indemnizacion= db2.getDate(12);
			int n_periodos= db2.getInt(13);
			
			//Se genera nueva instancia de cotizante
			PlanillaApvCotizante cotizante= new PlanillaApvCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			cotizante.setRemuneracionImponibleFdoPensionCotizante(remuneracion);
			cotizante.setDepositoConvenidoCotizante(deposito_conv);
			cotizante.setRegimenPrevisionalCtaIndemCotizante(tipo_regimen);
			cotizante.setTasaPactadaCtaIdemCotizante(tasa_pactada);
			cotizante.setAporteCtaIndemCotizante(aporte_indemnizacion);
			cotizante.setFechaDesdeCtaIndemCotizante(new AbsoluteDate(inicio_indemnizacion));
			cotizante.setFechaHastaCtaIndemCotizante(new AbsoluteDate(termino_indemnizacion));
			cotizante.setNumeroPeriodosCtaIndemCotizante(n_periodos);
			//se agrega registro de cotizante a la lista
			cotizantes.add(cotizante);
		}
		return cotizantes;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

}

