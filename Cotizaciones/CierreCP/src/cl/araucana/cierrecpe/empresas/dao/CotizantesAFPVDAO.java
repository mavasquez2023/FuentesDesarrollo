

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
public class CotizantesAFPVDAO implements DAO_Interface {
private ConectaDB2 db2;
private static Logger logger = LogManager.getLogger();		
	
	public CotizantesAFPVDAO(ConectaDB2 db2){
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
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Collection selectTotalxSucursal(Object pk) throws SQLException {
		FiltroCotizantesTO filtroTO= (FiltroCotizantesTO) pk;
		StringBuffer query= new StringBuffer();
		query.append("SELECT  t1.id_sucursal, ");
		//Pensión
		query.append("sum(t2.renta_imponible) as RemuImponible, sum(t2.prevision_obligatorio) as Obligatorio, sum(t2.prevision_cta_ahorro) as Ahorro, " );
		query.append("count(*) ");

		query.append("FROM cotizante t1, remunerac t2 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND id_ent_fondo_pension= ? ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		query.append("AND t1.id_entidad_afpv>0 ");
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
			int suma_obligatorio= db2.getInt(3);
			int suma_ahorro= db2.getInt(4);
			int n_trabajadores= db2.getInt(5);
			DetalleSeccionxSucursalTO totalesSucursal= new DetalleSeccionxSucursalTO();
			totalesSucursal.setId_sucursal(id_sucursal);
			totalesSucursal.setM1(suma_renta_imponible);
			totalesSucursal.setM2(suma_obligatorio);
			totalesSucursal.setM3(suma_ahorro);
			totalesSucursal.setM10(suma_obligatorio+suma_ahorro);
			totalesSucursal.setN_trabajadores(n_trabajadores);
			sucursales.add(totalesSucursal);
		}
		return sucursales;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		FiltroCotizantesTO filtroTO= (FiltroCotizantesTO) pk;
		StringBuffer query= new StringBuffer();
		query.append("SELECT  t1.id_sucursal, t1.id_cotizante, t1.nombres, t1.apellido_paterno, t1.apellido_materno,  ");
		//Pensión
		query.append("t2.prevision_obligatorio, t2.prevision_cta_ahorro, " );
		//Dependiente
		query.append("t1.id_entidad_afpv, t2.rut_dependiente, t2.nombre_dependiente, t2.apellido_pat_dep, t2.apellido_mat_dep, ");
		//Movimiento Personal
		query.append("t3.id_mvto, t3.id_tipo_mvto, t3.inicio, t3.termino ");
		
		query.append("FROM cotizante t1, remunerac t2  left join mvtoperso t3 ");
		query.append("ON t2.id_empresa= t3.id_empresa  ");
		query.append("AND t2.id_convenio= t3.id_convenio ");
		query.append("AND t2.id_cotizante= t3.id_cotizante ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND id_ent_fondo_pension= ? ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		query.append("AND t1.id_sucursal = ? ");
		query.append("AND t1.id_entidad_afpv>0 ");
		query.append("ORDER BY t1.id_sucursal, t1.id_cotizante ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		//db2.setStatement(1, 5); //PAGADO
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
			int obligatorio= db2.getInt(6);
			int ahorro= db2.getInt(7);
			int id_entidad_afpv= db2.getInt(8);
			int rut_dependiente= db2.getInt(9);
			String nombres_dependiente=db2.getString(10);
			String apellidos_dependiente= db2.getString(11) + " " + db2.getString(12);
			String id_mvto= db2.getString(13);

			//Se genera nueva instancia de cotizante
			PlanillaAfpCotizante cotizante= new PlanillaAfpCotizante();
			cotizante.setId_sucursal(id_sucursal);
			cotizante.setRutCotizante(new Rut(id_cotizante));
			cotizante.setNombresCotizante(nombres);
			cotizante.setApellidosCotizante(apellidos);
			if (id_mvto==null || id_mvto.equals("1")){
				cotizante.setCotizacionObligatoriaFdoPensionCotizante(obligatorio);
				cotizante.setDepositoCtaAhorroFdoPensionCotizante(ahorro);
				cotizante.setCodigoAfpAfiliadoVoluntarioCotizante(id_entidad_afpv);
				cotizante.setRutDependienteAfiliadoVoluntario(new Rut(rut_dependiente));
				cotizante.setNombresDependienteAfiliadoVoluntario(nombres_dependiente);
				cotizante.setApellidosDependienteAfiliadoVoluntario(apellidos_dependiente);
			}
			if (id_mvto != null){
				int codmov= db2.getInt(14);
				cotizante.setCodigoMovimientoPersonalCotizante(codmov);
				try {
					AbsoluteDate inicio= new AbsoluteDate(db2.getDate(15));
					cotizante.setFechaInicioMovimientoPersonalCotizante(inicio);
				} catch (Exception e) {
				}
				try {
					AbsoluteDate termino= new AbsoluteDate(db2.getDate(16));
					cotizante.setFechaTerminoMovimientoPersonalCotizante(termino);
				} catch (Exception e) {
				}
			}
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

