

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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.business.TrabajadorTGR;
import cl.araucana.cierrecpe.empresas.to.tgr.ComprobantesTO;
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
public class CotizantesPensionDAO implements DAO_Interface {
private ConectaDB2 db2;
private static Logger logger = LogManager.getLogger();	
	
	public CotizantesPensionDAO(ConectaDB2 db2){
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
		ComprobantesTO filtroTO= (ComprobantesTO) pk;
		String tipoProceso= filtroTO.gettipoNomina();
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
	public Object selectRemu(ComprobantesTO filtroTO) throws SQLException {
		String listEntidades="";
		for (Iterator entidades = filtroTO.getEntidades().iterator(); entidades.hasNext();) {
			Integer codEntidad = (Integer) entidades.next();
			listEntidades+= codEntidad + ", ";
		}
		listEntidades= listEntidades.substring(0, listEntidades.lastIndexOf(','));
		StringBuffer query= new StringBuffer();
		//Antecedentes
		query.append("SELECT t1.id_ent_fondo_pension, t1.id_cotizante, t1.nombres, t1.apellido_paterno, t1.apellido_materno, ");
		//Renta Imponible
		//query.append("CASE t1.id_ent_fondo_pension WHEN 0 THEN t2.renta_imponible_inp ELSE t2.renta_imponible END as Renta_Imponible, " );
		query.append(" t2.renta_imponible as Renta_Imponible, " );
		//Pensión
		query.append("t2.prevision_obligatorio as Pension ");
		
		query.append("FROM cotizante t1, remunerac t2 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		query.append("AND t1.id_entidad_afpv<=0 ");
		query.append("AND t1.id_ent_fondo_pension in (" + listEntidades + " ) ");
		query.append("ORDER BY t1.id_ent_fondo_pension, t1.id_cotizante ");
		//System.out.println("Query=" + query.toString());
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(2, filtroTO.getConvenio());
		
		//Se ejecuta la query
		db2.executeQuery();
		List cotizantes= new ArrayList();
		while (db2.next()) {
			//Se rescatan los datos de los cotizantes
			int id_entidad= db2.getInt(1);
			int id_cotizante= db2.getInt(2);
			String nombres=db2.getString(3);
			String apellidoPaterno= db2.getString(4);
			String apellidoMaterno= db2.getString(5);
			int rentaImponible= db2.getInt(6);
			int obligatorio= db2.getInt(7);

			//Se genera nueva instancia de cotizante
			TrabajadorTGR cotizante= new TrabajadorTGR();
			cotizante.setPeriodo(filtroTO.getPeriodo());
			cotizante.setTipoNomina(0);
			cotizante.setCodigoAFP(id_entidad);
			cotizante.setRutTrabajador(new Rut(id_cotizante));
			cotizante.setNombres(nombres);
			cotizante.setApellidoPaterno(apellidoPaterno);
			cotizante.setApellidoMaterno(apellidoMaterno);
			cotizante.setRentaImponible(rentaImponible);
			cotizante.setMontoCotizacion(obligatorio);
			cotizante.setRutEmpresa(filtroTO.getRutEmpresa());
			//se agrega registro de cotizante a la lista
			cotizantes.add(cotizante);
		}
		return cotizantes;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object selectGrati(ComprobantesTO filtroTO) throws SQLException {
		String listEntidades="";
		for (Iterator entidades = filtroTO.getEntidades().iterator(); entidades.hasNext();) {
			Integer codEntidad = (Integer) entidades.next();
			listEntidades+= codEntidad + ", ";
		}
		listEntidades= listEntidades.substring(0, listEntidades.lastIndexOf(','));
		StringBuffer query= new StringBuffer();
		///Antecedentes
		query.append("SELECT t1.id_ent_fondo_pension, t1.id_cotizante, t1.nombres, t1.apellido_paterno, t1.apellido_materno, ");
		//Gratificacion
		query.append("gratificacion, " );
		//Pensión
		query.append("t2.prevision_obligatorio as Pension ");
		
		query.append("FROM cotizante t1, gratificac t2 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		query.append("AND t1.id_ent_fondo_pension in (" + listEntidades + " ) ");
		query.append("ORDER BY t1.id_ent_fondo_pension, t1.id_cotizante ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(2, filtroTO.getConvenio());
		
		//Se ejecuta la query
		db2.executeQuery();
		List cotizantes= new ArrayList();
		while (db2.next()) {
			//Se rescatan los datos de los cotizantes
			int id_entidad= db2.getInt(1);
			int id_cotizante= db2.getInt(2);
			String nombres=db2.getString(3);
			String apellidoPaterno= db2.getString(4);
			String apellidoMaterno= db2.getString(5);
			int gratificacion= db2.getInt(6);
			int obligatorio= db2.getInt(7);

			//Se genera nueva instancia de cotizante
			TrabajadorTGR cotizante= new TrabajadorTGR();
			cotizante.setTipoNomina(1);
			cotizante.setCodigoAFP(id_entidad);
			cotizante.setRutTrabajador(new Rut(id_cotizante));
			cotizante.setNombres(nombres);
			cotizante.setApellidoPaterno(apellidoPaterno);
			cotizante.setApellidoMaterno(apellidoMaterno);
			cotizante.setRentaImponible(gratificacion);
			cotizante.setMontoCotizacion(obligatorio);
			cotizante.setRutEmpresa(filtroTO.getRutEmpresa());
			//se agrega registro de cotizante a la lista
			cotizantes.add(cotizante);
		}
		return cotizantes;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object selectReliq(ComprobantesTO filtroTO) throws SQLException {
		String listEntidades="";
		for (Iterator entidades = filtroTO.getEntidades().iterator(); entidades.hasNext();) {
			Integer codEntidad = (Integer) entidades.next();
			listEntidades+= codEntidad + ", ";
		}
		listEntidades= listEntidades.substring(0, listEntidades.lastIndexOf(','));
		StringBuffer query= new StringBuffer();
		//Antecedentes
		query.append("SELECT t1.id_ent_fondo_pension, t1.id_cotizante, t1.nombres, t1.apellido_paterno, t1.apellido_materno, ");
		//Reliquidacion
		query.append("reliquidacion, " );
		//Pensión
		query.append("t2.prevision_obligatorio as Pension ");
		
		query.append("FROM cotizante t1, reliquidac t2 ");
		query.append("WHERE t1.id_empresa= t2.id_empresa ");
		query.append("AND t1.id_convenio= t2.id_convenio ");
		query.append("AND t1.id_cotizante= t2.id_cotizante ");
		query.append("AND t1.id_empresa= ? ");
		query.append("AND t1.id_convenio= ? ");
		query.append("AND t1.id_ent_fondo_pension in (" + listEntidades + " ) ");
		query.append("ORDER BY t1.id_ent_fondo_pension, t1.id_cotizante ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getRutEmpresa().getNumber());
		db2.setStatement(2, filtroTO.getConvenio());
		
		//Se ejecuta la query
		db2.executeQuery();
		List cotizantes= new ArrayList();
		while (db2.next()) {
			//Se rescatan los datos de los cotizantes
			int id_entidad= db2.getInt(1);
			int id_cotizante= db2.getInt(2);
			String nombres=db2.getString(3);
			String apellidoPaterno= db2.getString(4);
			String apellidoMaterno= db2.getString(5);
			int reliquidacion= db2.getInt(6);
			int obligatorio= db2.getInt(7);

			//Se genera nueva instancia de cotizante
			TrabajadorTGR cotizante= new TrabajadorTGR();
			cotizante.setTipoNomina(1);
			cotizante.setCodigoAFP(id_entidad);
			cotizante.setRutTrabajador(new Rut(id_cotizante));
			cotizante.setNombres(nombres);
			cotizante.setApellidoPaterno(apellidoPaterno);
			cotizante.setApellidoMaterno(apellidoMaterno);
			cotizante.setRentaImponible(reliquidacion);
			cotizante.setMontoCotizacion(obligatorio);
			cotizante.setRutEmpresa(filtroTO.getRutEmpresa());
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

