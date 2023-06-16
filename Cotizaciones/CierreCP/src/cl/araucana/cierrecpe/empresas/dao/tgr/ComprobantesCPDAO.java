

/*
 * @(#) EmpresaCPDAO.java    1.0 10-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.dao.tgr;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.business.NuevoConvenio;
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
public class ComprobantesCPDAO implements DAO_Interface {
private ConectaDB2 db2;
private static Logger logger = LogManager.getLogger();	
	
	public ComprobantesCPDAO(ConectaDB2 db2){
		this.db2= db2;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.subsistemas.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente

	}

	/* (sin Javadoc)
	 * @see cl.araucana.subsistemas.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}
	
	
	/* (sin Javadoc)
	 * @see cl.araucana.subsistemas.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.subsistemas.dao.DAO_Interface#select(java.lang.Object, java.lang.Object)
	 */
	public Object select(int periodo, int cierre) throws SQLException {
		StringBuffer query= new StringBuffer();
		
		//Seleccionando comprobantes de Remuneraciones
		query.append("SELECT t1.id_codigo_barra, t2.id_empresa, t2.id_convenio, 'R' as TipoNomina, t1.folio_tesoreria ");
		query.append("from COMPROBANTE_PAGO t1, NOMINARE t2, SECCION t3, RESPROCIE t4 ");
		query.append("WHERE t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("AND t1.id_codigo_barra= t3.id_codigo_barra ");
		//query.append("AND t1.id_estado IN ( 4, 5 ) ");
		query.append("AND t3.tipo_pago in (0, 1) ");
		query.append("AND t3.id_tipo_seccion in(1, 3) ");
		query.append("AND t1.cierre = ? ");
		query.append("AND t3.m2>0 ");
		query.append("AND t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("AND t4.tgr=0 ");
		//Seleccionando comprobantes de Gratificaciones
		query.append("UNION ");
		query.append("SELECT t1.id_codigo_barra, t2.id_empresa, t2.id_convenio, 'G' as TipoNomina, t1.folio_tesoreria ");
		query.append("from COMPROBANTE_PAGO t1, NOMINAGR t2, SECCION t3, RESPROCIE t4 ");
		query.append("WHERE t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("AND t1.id_codigo_barra= t3.id_codigo_barra ");
		//query.append("AND t1.id_estado IN ( 4, 5 ) ");
		query.append("AND t3.tipo_pago in (0, 1) ");
		query.append("AND t3.id_tipo_seccion in(40, 42) ");
		query.append("AND t1.cierre = ? ");
		query.append("AND t3.m1>0 ");
		query.append("AND t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("AND t4.tgr=0 ");
		//Seleccionando comprobantes de Reliquidación
		query.append("UNION ");
		query.append("SELECT t1.id_codigo_barra, t2.id_empresa, t2.id_convenio, 'A' as TipoNomina, t1.folio_tesoreria ");
		query.append("from COMPROBANTE_PAGO t1, NOMINARA t2, SECCION t3, RESPROCIE t4 ");
		query.append("WHERE t1.id_codigo_barra= t2.id_codigo_barra ");
		query.append("AND t1.id_codigo_barra= t3.id_codigo_barra ");
		//query.append("AND t1.id_estado IN ( 4, 5 ) ");
		query.append("AND t3.tipo_pago in (0, 1) ");
		query.append("AND t3.id_tipo_seccion in(20, 22) ");
		query.append("AND t1.cierre = ? ");
		query.append("AND t3.m1>0 ");
		query.append("AND t1.id_codigo_barra= t4.id_codigo_barra ");
		query.append("AND t4.tgr=0 ");
		query.append("GROUP BY t1.id_codigo_barra, t2.id_empresa, t2.id_convenio, t1.folio_tesoreria ");
		query.append("ORDER BY 1");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, cierre);
		db2.setStatement(2, cierre);
		db2.setStatement(3, cierre);
		//Se ejecuta la query
		db2.executeQuery();
		List comprobantes= new ArrayList();
		while (db2.next()) {
			ComprobantesTO comprobanteTO= new ComprobantesTO();
			long idCodigoBarra= db2.getLong(1);
			Rut rutEmpresa= new Rut(db2.getInt(2));
			int convenio= db2.getInt(3);
			String tipoNomina= db2.getString(4);
			int folioTesoreria= db2.getInt(5);
			System.out.println("ComprobantesCPDAO.select, codigo barra:" + idCodigoBarra);
			comprobanteTO.setPeriodo(periodo);
			comprobanteTO.setIdCodigoBarra(idCodigoBarra);
			comprobanteTO.setRutEmpresa(rutEmpresa);
			comprobanteTO.setConvenio(convenio);
			comprobanteTO.settipoNomina(tipoNomina);
			comprobanteTO.setFolioTesoreria(folioTesoreria);
			comprobantes.add(comprobanteTO);
		}
		return comprobantes;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.subsistemas.dao.DAO_Interface#selectDetalleSeccionRemu(java.lang.Object)
	 */
	public Object selectDetalleSeccion(Object pk) throws SQLException {
		Object entidades= null;
		ComprobantesTO comprobante= (ComprobantesTO)pk;
		String tipoNomina= comprobante.gettipoNomina();
		if(tipoNomina.equals("R")){
			entidades= selectDetalleSeccionRemu(comprobante.getIdCodigoBarra());
		}else if(tipoNomina.equals("G")){
			entidades= selectDetalleSeccionGrati(comprobante.getIdCodigoBarra());
		}else if(tipoNomina.equals("A")){
			entidades= selectDetalleSeccionRemu(comprobante.getIdCodigoBarra());
		}
		return entidades;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.subsistemas.dao.DAO_Interface#selectDetalleSeccionRemu(java.lang.Object)
	 */
	public Object selectDetalleSeccionRemu(long idCodigobarra) throws SQLException {
		StringBuffer query= new StringBuffer();
		//Seleccionando comprobantes de Remuneraciones
		query.append("SELECT id_detalle_seccion ");
		query.append("from DETALLE_SECCION ");
		query.append("WHERE id_codigo_barra= ? ");
		query.append("AND id_tipo_seccion in(1, 3) ");
		query.append("AND tipo_pago=1 ");
		query.append("AND m2>0 ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, idCodigobarra);
		//Se ejecuta la query
		db2.executeQuery();
		List entidades= new ArrayList();
		while (db2.next()) {
			int idDetalleSeccion= db2.getInt(1);
			entidades.add(new Integer(idDetalleSeccion));
		}
		return entidades;
	}
	/* (sin Javadoc)
	 * @see cl.araucana.subsistemas.dao.DAO_Interface#selectDetalleSeccionGrati(java.lang.Object)
	 */
	public Object selectDetalleSeccionGrati(long idCodigobarra) throws SQLException {
		StringBuffer query= new StringBuffer();
		//Seleccionando comprobantes de Remuneraciones
		query.append("SELECT id_detalle_seccion ");
		query.append("from DETALLE_SECCION ");
		query.append("WHERE id_codigo_barra= ? ");
		query.append("AND id_tipo_seccion in(40, 42) ");
		query.append("AND tipo_pago=1 ");
		query.append("AND m1>0 ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, idCodigobarra);
		//Se ejecuta la query
		db2.executeQuery();
		List entidades= new ArrayList();
		while (db2.next()) {
			int idDetalleSeccion= db2.getInt(1);
			entidades.add(new Integer(idDetalleSeccion));
		}
		return entidades;
	}
	/* (sin Javadoc)
	 * @see cl.araucana.subsistemas.dao.DAO_Interface#selectDetalleSeccionReli(java.lang.Object)
	 */
	public Object selectDetalleSeccionReli(long idCodigobarra) throws SQLException {
		StringBuffer query= new StringBuffer();
		//Seleccionando comprobantes de Remuneraciones
		query.append("SELECT id_detalle_seccion ");
		query.append("from DETALLE_SECCION ");
		query.append("WHERE id_codigo_barra= ? ");
		query.append("AND id_tipo_seccion in(20, 22) ");
		query.append("AND tipo_pago=1 ");
		query.append("AND m1>0 ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, idCodigobarra);
		//Se ejecuta la query
		db2.executeQuery();
		List entidades= new ArrayList();
		while (db2.next()) {
			int idDetalleSeccion= db2.getInt(1);
			entidades.add(new Integer(idDetalleSeccion));
		}
		return entidades;
	}
	
	
	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}
	
	public Object selectDatosEmpresa(Object pk) throws SQLException {
		NuevoConvenio datos= null;
		ComprobantesTO filtroTO= (ComprobantesTO) pk;
		StringBuffer query= new StringBuffer();
		query.append("SELECT  t0.razon_social, t1.direccion, t1.numero, t1.departamento, t1.telefono, t1.email, t1.id_comuna, t2.nombre as comuna, t3.nombre as ciudad , t3.id_region ");
		query.append("FROM empresa t0, sucursal t1, comuna t2, ciudad t3 ");
		query.append("WHERE t1.id_empresa= ? ");
		query.append("AND t0.id_casa_matriz= t1.id_sucursal ");
		query.append("AND t1.id_comuna= t2.id_comuna ");
		query.append("AND t2.id_ciudad= t3.id_ciudad ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, filtroTO.getRutEmpresa().getNumber());
		//Se ejecuta la query
		db2.executeQuery();
		
		if (db2.next()) {
			//Se rescatan los datos de los cotizantes
			String razonSocial= db2.getString(1);
			String direccion= db2.getString(2);
			String numero= db2.getString(3);
			String departamento= db2.getString(4);
			String telefono= db2.getString(5);
			String email= db2.getString(6);
			int id_comuna= db2.getInt(7);
			String comuna= db2.getString(8);
			String ciudad= db2.getString(9);
			String region= db2.getString(10);
			
			//Se genera nueva instancia de sucursal
			datos= new NuevoConvenio();
			datos.setRutEmpresa(filtroTO.getRutEmpresa());
			datos.setConvenio(filtroTO.getConvenio());
			datos.setRazonSocial(razonSocial);
			datos.setDireccion(direccion);
			datos.setNumero(numero);
			datos.setLocal(departamento);
			datos.setTelefono(telefono);
			datos.setEmail(email);
			datos.setComuna(comuna);
			datos.setCiudad(ciudad);
			datos.setRegion(region);
		}
		return datos;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.cierrecp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Map selectMapeoTGR() throws SQLException {
		StringBuffer query= new StringBuffer();
		query.append("SELECT id_ent_fondo_pension, id_ent_tesgenrep ");
		query.append("FROM mapeotgr ");
		
		logger.finest("Query= " + query.toString());
		db2.prepareQuery(query.toString());
		//Se ejecuta la query
		db2.executeQuery();
		Map codigosTGR= new HashMap();
		while (db2.next()) {
			int id_ent_fondo_pension= db2.getInt(1);
			String id_ent_tesgenrep= db2.getString(2);
			logger.config("id_ent_fondo_pension: " + id_ent_fondo_pension + "cod. TGR ==>"  + id_ent_tesgenrep);
			codigosTGR.put(new Integer(id_ent_fondo_pension), id_ent_tesgenrep);
		}
		return codigosTGR;
	}
}

