

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

import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.planillas.mutual.PlanillaMutualCotizante;
import cl.araucana.cierrecpe.empresas.to.DetalleSeccionxSucursalTO;
import cl.araucana.cierrecpe.empresas.to.FiltroCotizantesTO;
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
public class CotizantesMutualDAO implements DAO_Interface {
private ConectaDB2 db2;
private static Logger logger = LogManager.getLogger();	
	
	public CotizantesMutualDAO(ConectaDB2 db2){
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
		}
		else if(tipoProceso.equals("A")){
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
		query.append("SELECT  t1.id_sucursal, t1.id_cotizante, t1.nombres, t1.apellido_paterno, t1.apellido_materno, t2.mutual_imponible,  ");
		query.append("t2.inp_mutual, t1.id_genero, t1.n_dias_trabajados, left(t4.nombre, 1)");
		query.append("FROM cotizante t1, remunerac t2, convenio t3, genero t4 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= t3.id_empresa ");
		query.append("AND t1.id_convenio= t3.id_convenio ");
		query.append("AND t1.id_genero= t4.id_genero ");
		query.append("AND t3.id_mutual= ? ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		if(filtroTO.isPlanillaxSucursal()){
			query.append("AND t1.id_sucursal = ? ");
		}
		query.append("AND t1.id_entidad_afpv<=0 ");
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
			int mutual_imponible= db2.getInt(6);
			int monto_mutual= db2.getInt(7);
			char genero= db2.getString(8).charAt(0);
			int n_dias_trab= db2.getInt(9);
			String generoCotizante=db2.getString(10);
			
			//Se genera nueva instancia de cotizante
			PlanillaMutualCotizante cotizante= new PlanillaMutualCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			cotizante.setRemuneracionImponible(mutual_imponible);
			cotizante.setMontoCotizacion(monto_mutual);
			cotizante.setCodigoSexo(genero);
			cotizante.setNumeroDiasTrabajados(n_dias_trab);
			cotizante.setDiasTrabajados(n_dias_trab);
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
		query.append("SELECT  t1.id_sucursal, t1.id_cotizante, t1.nombres, t1.apellido_paterno, t1.apellido_materno, t2.mutual_imponible,  ");
		query.append("t2.inp_pension, t1.id_genero, t1.n_dias_trabajados, left(t4.nombre, 1) ");
		query.append("FROM cotizante t1, gratificac t2, convenio t3, genero t4 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= t3.id_empresa ");
		query.append("AND t1.id_convenio= t3.id_convenio ");
		query.append("AND t1.id_genero= t4.id_genero ");
		query.append("AND t3.id_mutual= ? ");
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
			int mutual_imponible= db2.getInt(6);
			int monto_mutual= db2.getInt(7);
			char genero= db2.getString(8).charAt(0);
			int n_dias_trab= db2.getInt(9);
			String generoCotizante=db2.getString(10);
			
			//Se genera nueva instancia de cotizante
//			Se genera nueva instancia de cotizante
			PlanillaMutualCotizante cotizante= new PlanillaMutualCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			cotizante.setRemuneracionImponible(mutual_imponible);
			cotizante.setMontoCotizacion(monto_mutual);
			cotizante.setCodigoSexo(genero);
			cotizante.setNumeroDiasTrabajados(n_dias_trab);
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
		query.append("SELECT  t1.id_sucursal, t1.id_cotizante, t1.nombres, t1.apellido_paterno, t1.apellido_materno, t2.mutual_imponible,  ");
		query.append("t2.inp_pension, t1.id_genero, t1.n_dias_trabajados, left(t4.nombre, 1) ");
		query.append("FROM cotizante t1, reliquidac t2, convenio t3, genero t4 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= t3.id_empresa ");
		query.append("AND t1.id_convenio= t3.id_convenio ");
		query.append("AND t1.id_genero= t4.id_genero ");
		query.append("AND t3.id_mutual= ? ");
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
			int mutual_imponible= db2.getInt(6);
			int monto_mutual= db2.getInt(7);
			char genero= db2.getString(8).charAt(0);
			int n_dias_trab= db2.getInt(9);
			String generoCotizante=db2.getString(10);
			
			//Se genera nueva instancia de cotizante
			PlanillaMutualCotizante cotizante= new PlanillaMutualCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			cotizante.setRemuneracionImponible(mutual_imponible);
			cotizante.setMontoCotizacion(monto_mutual);
			cotizante.setCodigoSexo(genero);
			cotizante.setNumeroDiasTrabajados(n_dias_trab);
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
		//Mutual Imponible
		query.append("sum(t2.mutual_imponible) as Imponible_Mutual, " );
		//Suma Mutual Individual
		query.append("sum(t2.inp_mutual) as Monto_Mutual, " );
		//N° Cotizantes
		query.append("count(*) ");

		query.append("FROM cotizante t1, remunerac t2 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		if(filtroTO.isPlanillaxSucursal()){
			query.append("GROUP BY t1.id_sucursal ");
			query.append("ORDER BY t1.id_sucursal ");
		}
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(2, filtroTO.getConvenio());
				
		//Se ejecuta la query
		db2.executeQuery();
		List sucursales= new ArrayList();
		while (db2.next()) {
			//Se rescatan los datos de los cotizantes
			String id_sucursal= db2.getString(1);
			long suma_renta_imponible= db2.getLong(2);
			long suma_monto_mutual= db2.getLong(3);
			int n_trabajadores= db2.getInt(4);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM2(suma_renta_imponible);
			totalesSucursal.setM3(suma_monto_mutual);
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
		//Suma Mutual Individual
		query.append("sum(t2.inp_pension) as Monto_Mutual, " );
		//N° Cotizantes
		query.append("count(*) ");

		query.append("FROM cotizante t1, gratificac t2 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		if(filtroTO.isPlanillaxSucursal()){
			query.append("GROUP BY t1.id_sucursal ");
			query.append("ORDER BY t1.id_sucursal ");
		}
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(2, filtroTO.getConvenio());
				
		//Se ejecuta la query
		db2.executeQuery();
		List sucursales= new ArrayList();
		while (db2.next()) {
			//Se rescatan los datos de los cotizantes
			String id_sucursal= db2.getString(1);
			long suma_gratificacion= db2.getLong(2);
			long suma_monto_mutual= db2.getLong(3);
			int n_trabajadores= db2.getInt(4);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM2(suma_gratificacion);
			totalesSucursal.setM3(suma_monto_mutual);
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
		//Suma Mutual Individual
		query.append("sum(t2.inp_pension) as Monto_Mutual, " );
		//N° Cotizantes
		query.append("count(*) ");

		query.append("FROM cotizante t1, reliquidac t2 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		if(filtroTO.isPlanillaxSucursal()){
			query.append("GROUP BY t1.id_sucursal ");
			query.append("ORDER BY t1.id_sucursal ");
		}
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(2, filtroTO.getConvenio());
				
		//Se ejecuta la query
		db2.executeQuery();
		List sucursales= new ArrayList();
		while (db2.next()) {
			//Se rescatan los datos de los cotizantes
			String id_sucursal= db2.getString(1);
			long suma_reliquidacion= db2.getLong(2);
			long suma_monto_mutual= db2.getLong(3);
			int n_trabajadores= db2.getInt(4);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM2(suma_reliquidacion);
			totalesSucursal.setM3(suma_monto_mutual);
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

