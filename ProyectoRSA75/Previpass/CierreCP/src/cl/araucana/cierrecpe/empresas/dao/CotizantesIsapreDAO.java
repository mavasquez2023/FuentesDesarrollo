

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
import cl.araucana.cierrecpe.empresas.planillas.isapre.PlanillaIsapreCotizante;
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
public class CotizantesIsapreDAO implements DAO_Interface {
private ConectaDB2 db2;
private static Logger logger = LogManager.getLogger();	
	
	public CotizantesIsapreDAO(ConectaDB2 db2){
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
		query.append("t2.salud_obligatorio, t2.porcent_bonific_salud, t2.salud_bonificacion, t2.salud_pactado, t2.salud_adicional_voluntario, t2.salud_total, ");
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
		query.append("AND id_ent_salud= ? ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		query.append("AND t1.id_sucursal = ? ");
		query.append("ORDER BY t1.id_sucursal, t1.id_cotizante, t3.id_mvto ");
		
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
			int obligatorio= db2.getInt(7);
			double porcentajeLey18566= db2.getDouble(8);
			int montoLey18566= db2.getInt(9);
			int pactado= db2.getInt(10);
			int adicional= db2.getInt(11);
			int total= db2.getInt(12);
			String id_mvto= db2.getString(13);
			int id_tipo_mvto= db2.getInt(14);
			Date fecha_inicio= db2.getDate(15);
			Date fecha_termino= db2.getDate(16);
			int diasTrabajados= db2.getInt(17);
			String generoCotizante=db2.getString(18);
			
			//Se genera nueva instancia de cotizante
			PlanillaIsapreCotizante cotizante= new PlanillaIsapreCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			if (id_mvto==null || id_mvto.equals("1")){
				cotizante.setRemuneracionImponible(renta_imponible);
				cotizante.setCotizacion7Porciento(obligatorio);
				cotizante.setPorcentajeLey18566(porcentajeLey18566);
				cotizante.setCotizacionLey18566(montoLey18566);
				cotizante.setCotizacionPactada(pactado);
				cotizante.setCotizacionAdicionalVoluntaria(adicional);
				cotizante.setCotizacionTotalAPagar(total);
				cotizante.setDiasTrabajados(diasTrabajados);
				cotizante.setGeneroCotizante(generoCotizante);
			}
			if (id_mvto != null){
				cotizante.setCodigoMovimientoPersonal(id_tipo_mvto);
				if(fecha_inicio!=null){
					cotizante.setFechaInicioMovimientoPersonal(new AbsoluteDate(fecha_inicio));
				}
				if(fecha_termino!=null){
					cotizante.setFechaTerminoMovimientoPersonal(new AbsoluteDate(fecha_termino));
				}
			}
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
		query.append("SELECT  t1.id_sucursal, t1.id_cotizante, nombres, apellido_paterno, apellido_materno, ");
		//Obligatorio
		query.append("gratificacion, salud_obligatorio, ");
		//Genero
		query.append("left(t3.nombre, 1)  ");
		query.append("FROM cotizante t1, gratificac t2, genero t3 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_genero= t3.id_genero ");
		query.append("AND id_ent_salud= ? ");
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
			int gratificacion= db2.getInt(6);
			int obligatorio= db2.getInt(7);
			String generoCotizante=db2.getString(8);
			
			//Se genera nueva instancia de cotizante
			PlanillaIsapreCotizante cotizante= new PlanillaIsapreCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			cotizante.setRemuneracionImponible(gratificacion);
			cotizante.setCotizacion7Porciento(obligatorio);
			cotizante.setCotizacionTotalAPagar(obligatorio);
			cotizante.setGeneroCotizante(generoCotizante);
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
		query.append("SELECT  t1.id_sucursal, t1.id_cotizante, nombres, apellido_paterno, apellido_materno, ");
		//Obligatorio
		query.append("reliquidacion, salud_obligatorio ");
		//Genero
		query.append("left(t3.nombre, 1)  ");
		query.append("FROM cotizante t1, reliquidac t2, genero t3 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_genero= t3.id_genero ");
		query.append("AND id_ent_salud= ? ");
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
			int reliquidacion= db2.getInt(6);
			int obligatorio= db2.getInt(7);
			String generoCotizante=db2.getString(8);
			
			//Se genera nueva instancia de cotizante
			PlanillaIsapreCotizante cotizante= new PlanillaIsapreCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			cotizante.setRemuneracionImponible(reliquidacion);
			cotizante.setCotizacion7Porciento(obligatorio);
			cotizante.setCotizacionTotalAPagar(obligatorio);
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
		query.append("SELECT t1.id_sucursal, ");
		query.append("sum(t2.renta_imponible) as RentaImponible, sum(t2.salud_obligatorio) as Obligatorio, sum(t2.salud_adicional_voluntario) as Adicional, ");
		query.append("count(*) ");

		query.append("FROM cotizante t1, remunerac t2 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND id_ent_salud= ? ");
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
			int suma_obligatorio= db2.getInt(3);
			int suma_adicional= db2.getInt(4);
			int n_trabajadores= db2.getInt(5);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM1(suma_renta_imponible);
			totalesSucursal.setM2(suma_obligatorio);
			totalesSucursal.setM3(suma_adicional);
			totalesSucursal.setM4(suma_obligatorio+suma_adicional);
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
		query.append("SELECT t1.id_sucursal, ");
		query.append("sum(t2.gratificacion) as Gratificacion, sum(t2.salud_obligatorio) as Obligatorio, ");
		query.append("count(*) ");

		query.append("FROM cotizante t1, gratificac t2 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND id_ent_salud= ? ");
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
			long suma_gratificacion= db2.getLong(2);
			int suma_obligatorio= db2.getInt(3);
			int n_trabajadores= db2.getInt(4);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM1(suma_gratificacion);
			totalesSucursal.setM2(suma_obligatorio);
			totalesSucursal.setM4(suma_obligatorio);
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
		query.append("SELECT t1.id_sucursal, ");
		query.append("sum(t2.reliquidacion) as Gratificacion, sum(t2.salud_obligatorio) as Obligatorio, ");
		query.append("count(*) ");

		query.append("FROM cotizante t1, reliquidac t2 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND id_ent_salud= ? ");
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
			long suma_reliquidacion= db2.getLong(2);
			int suma_obligatorio= db2.getInt(3);
			int n_trabajadores= db2.getInt(4);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM1(suma_reliquidacion);
			totalesSucursal.setM2(suma_obligatorio);
			totalesSucursal.setM4(suma_obligatorio);
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

