

/*
 * @(#) planillaAFPDAO.java    1.0 10-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Logger;

import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.planillas.afp.PlanillaAfpCotizante;
import cl.araucana.cierrecpe.empresas.planillas.afp.PlanillaAfpPaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.isapre.PlanillaIsapreCotizante;
import cl.araucana.cierrecpe.empresas.planillas.isapre.PlanillaIsapreDocumentModel;
import cl.araucana.cierrecpe.empresas.planillas.isapre.PlanillaIsaprePaginaDetalle;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.ConectaDB2;
import cl.recursos.Formato;

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
public class PlanillaIsapreDAO implements DAO_Interface {
	private ConectaDB2 db2;
	private String esquema;
	private static Logger logger = LogManager.getLogger();
	/**
	 * 
	 */
	public PlanillaIsapreDAO(ConectaDB2 db2, String esquema) {
		this.db2= db2;
		this.esquema= esquema;
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
		PlanillaIsapreDocumentModel planilla= (PlanillaIsapreDocumentModel) obj;
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO " + getEsquema() + ".pwf1000 values( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, planilla.getNombreEntidad());
		db2.setStatement(2, planilla.getFolio());
		db2.setStatement(3, planilla.getDatosEmpleador().getRutEmpresa(9));
		db2.setStatement(4, planilla.getDatosEmpleador().getRutEmpresa().getDV());
		db2.setStatement(5, planilla.getSecuenciaFolio());
		db2.setStatement(6, planilla.getTipoProceso());
		db2.setStatement(7, planilla.getPeriodo());
		db2.setStatement(8, planilla.getTipoDeclaracion());
		db2.setStatement(9, planilla.getTipoEntePagador());
		db2.setStatement(10, planilla.getDatosEmpleador().getRazonSocial());
		db2.setStatement(11, planilla.getDatosSucursal().getDireccion());
		db2.setStatement(12, planilla.getDatosSucursal().getComuna());
		db2.setStatement(13, planilla.getDatosSucursal().getCiudad());
		db2.setStatement(14, planilla.getDatosSucursal().getRegion());
		db2.setStatement(15, planilla.getDatosSucursal().getTelefono(12));
		String nomreplegal= planilla.getDatosEmpleador().getNombresRepLegal() + " " + planilla.getDatosEmpleador().getApellidosRepLegal();
		if(nomreplegal.length()>40){
			nomreplegal= nomreplegal.substring(0, 40);
		}
		db2.setStatement(16, nomreplegal);
		db2.setStatement(17, planilla.getDatosEmpleador().getRutRepLegal(9));
		db2.setStatement(18, planilla.getDatosEmpleador().getRutRepLegal().getDV());
		db2.setStatement(19, ""); //Cambio en el representante
		db2.setStatement(20, "0"); //Cambio de dirección del Empleador
		db2.setStatement(21, "0"); //Camio de Rut del Empleador
		db2.setStatement(22, planilla.getCotizacionLegal());
		db2.setStatement(23, planilla.getCotizacionLey18566());
		db2.setStatement(24, planilla.getCotizacionAdicionalVoluntaria());
		db2.setStatement(25, planilla.getCotizacionTotalAPagar());
		db2.setStatement(26, planilla.getSubtotal());
		db2.setStatement(27, planilla.getTotalAPagar());
		db2.setStatement(28, planilla.getTipoPago());
		db2.setStatement(29, planilla.getFechaPago());
		db2.setStatement(30, planilla.getNumeroAfiliadosInformados());
		db2.setStatement(31, String.valueOf(planilla.getPeriodo()).substring(4, 6));
		db2.setStatement(32, String.valueOf(planilla.getPeriodo()).substring(0, 4));
		String periodoGratiDesde= String.valueOf(planilla.getFechaInicioGrati());
		String periodoGratiHasta= String.valueOf(planilla.getFechaTerminoGrati());
		if(periodoGratiDesde.length()>=6 && periodoGratiHasta.length()>=6){
		db2.setStatement(33, periodoGratiDesde.substring(4, 6));
		db2.setStatement(34, periodoGratiDesde.substring(0, 4));
		db2.setStatement(35, periodoGratiHasta.substring(4, 6));
		db2.setStatement(36, periodoGratiHasta.substring(0, 4));
		}else{
			db2.setStatement(33, 0);
			db2.setStatement(34, 0);
			db2.setStatement(35, 0);
			db2.setStatement(36, 0);
		}
		db2.setStatement(37, planilla.getNumeroHojasAnexas());
		db2.setStatement(38, planilla.getConvenio());
		db2.setStatement(39, planilla.getDatosSucursal().getCodigo());
		db2.setStatement(40, planilla.getGrupoConvenio());
		db2.setStatement(41, planilla.getTipoPago());
		db2.setStatement(42, planilla.getDatosEmpleador().getCodActEconomica());
		int result= db2.executeUpdate();
		for (Iterator iter = planilla.getPaginasDetalle().iterator(); iter.hasNext();) {
			PlanillaIsaprePaginaDetalle pagina = (PlanillaIsaprePaginaDetalle) iter.next();
			insertDetalle(pagina);
		}
		return result;
	}
	
	private int insertDetalle(Object obj) throws SQLException {
		PlanillaIsaprePaginaDetalle pagina= (PlanillaIsaprePaginaDetalle) obj;
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO " + getEsquema() + ".pwf1100 values( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		for (Iterator iterator = pagina.getCotizantes().iterator(); iterator.hasNext();) {
			PlanillaIsapreCotizante cotizante = (PlanillaIsapreCotizante) iterator.next();
			db2.setStatement(1, pagina.getCabeceraPlanilla().getNombreEntidad());
			db2.setStatement(2, pagina.getCabeceraPlanilla().getFolio());
			db2.setStatement(3, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa(9));
			db2.setStatement(4, pagina.getSecuenciaFolio());
			db2.setStatement(5, cotizante.getNumeroLineaDetalle());
			db2.setStatement(6, cotizante.getRutCotizante(9));
			db2.setStatement(7, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getDV());
			db2.setStatement(8, cotizante.getRutCotizante().getDV());
			db2.setStatement(9, cotizante.getApellidosCotizante());
			db2.setStatement(10, cotizante.getNombresCotizante());
			db2.setStatement(11, cotizante.getRemuneracionImponible());
			db2.setStatement(12, cotizante.getCotizacion7Porciento());
			db2.setStatement(13, cotizante.getPorcentajeLey18566());
			db2.setStatement(14, cotizante.getCotizacionLey18566());
			db2.setStatement(15, cotizante.getCotizacionAdicionalVoluntaria());
			db2.setStatement(16, cotizante.getCotizacionTotalAPagar());
			db2.setStatement(17, cotizante.getCotizacionPactada());
			db2.setStatement(18, cotizante.getCodigoMovimientoPersonal());
			try{
			db2.setStatement(19, cotizante.getFechaInicioMovimientoPersonal().getPeriod() + "" + Formato.padding(cotizante.getFechaInicioMovimientoPersonal().getDay(), 2)); 
			}catch(NullPointerException e){
				db2.setStatement(19, 0);
			}
			try{ 
				db2.setStatement(20, cotizante.getFechaTerminoMovimientoPersonal().getPeriod() + "" + Formato.padding(cotizante.getFechaTerminoMovimientoPersonal().getDay(), 2));
			}catch(NullPointerException e){
				db2.setStatement(20, 0);
			}
			db2.executeUpdate();
		}
		
		return 0;
	}
	
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public Object select(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	/**
	 * @return el esquema
	 */
	public String getEsquema() {
		return esquema;
	}

}

