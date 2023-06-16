

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
import cl.araucana.cierrecpe.empresas.planillas.isapre.PlanillaIsapreCotizante;
import cl.araucana.cierrecpe.empresas.planillas.isapre.PlanillaIsapreDocumentModel;
import cl.araucana.cierrecpe.empresas.planillas.isapre.PlanillaIsaprePaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.otros.PlanillaAfbrCotizante;
import cl.araucana.cierrecpe.empresas.planillas.otros.PlanillaAfbrDocumentModel;
import cl.araucana.cierrecpe.empresas.planillas.otros.PlanillaAfbrPaginaDetalle;
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
public class PlanillaAFBRDAO implements DAO_Interface {
	private ConectaDB2 db2;
	private String esquema;
	private static Logger logger = LogManager.getLogger();
	/**
	 * 
	 */
	public PlanillaAFBRDAO(ConectaDB2 db2, String esquema) {
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
		PlanillaAfbrDocumentModel planilla= (PlanillaAfbrDocumentModel) obj;
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO pwdtad.pwf8300 values( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, planilla.getPeriodo());
		db2.setStatement(2, planilla.getNombreEntidad());
		db2.setStatement(3, planilla.getFolio());
		db2.setStatement(4, planilla.getDatosEmpleador().getRutEmpresa().getNumber());
		db2.setStatement(5, planilla.getDatosEmpleador().getRutEmpresa().getDV());
		db2.setStatement(6, planilla.getSecuenciaFolio());
		db2.setStatement(7, planilla.getTipoProceso());
		db2.setStatement(8, planilla.getDatosEmpleador().getRazonSocial());
		db2.setStatement(9, planilla.getDatosEmpleador().getCodActEconomica());
		db2.setStatement(10, planilla.getDatosSucursal().getDireccion());
		db2.setStatement(11, planilla.getDatosSucursal().getComuna());
		db2.setStatement(12, planilla.getDatosSucursal().getCiudad());
		db2.setStatement(13, planilla.getDatosSucursal().getRegion());
		db2.setStatement(14, planilla.getDatosSucursal().getEmail(50));
		db2.setStatement(15, planilla.getDatosSucursal().getTelefono(12));
		String nomreplegal= planilla.getDatosEmpleador().getNombresRepLegal() + " " + planilla.getDatosEmpleador().getApellidosRepLegal();
		if(nomreplegal.length()>40){
			nomreplegal= nomreplegal.substring(0, 40);
		}
		db2.setStatement(16, nomreplegal);
		db2.setStatement(17, planilla.getDatosEmpleador().getRutRepLegal().getNumber());
		db2.setStatement(18, planilla.getDatosEmpleador().getRutRepLegal().getDV());
		db2.setStatement(19, ""); //Cambio en el representante
		db2.setStatement(20, planilla.getNumeroAfiliadosInformados());
		db2.setStatement(21, planilla.getCotizacionTotalAporte());
		db2.setStatement(22, planilla.getTotalAPagar());
		db2.setStatement(23, planilla.getTipoDeclaracion());
		db2.setStatement(24, planilla.getTipoPago());
		String periodoGratiDesde= String.valueOf(planilla.getFechaInicioGrati());
		String periodoGratiHasta= String.valueOf(planilla.getFechaTerminoGrati());
		if(periodoGratiDesde.length()>=8 && periodoGratiHasta.length()>=8){
		db2.setStatement(25, periodoGratiDesde.substring(0, 8));
		db2.setStatement(26, periodoGratiHasta.substring(0, 8));
		}else{
			db2.setStatement(25, 0);
			db2.setStatement(26, 0);
		}
		db2.setStatement(27, planilla.getFechaPago());
		db2.setStatement(28, planilla.getTotalRemuneraciones());
		db2.setStatement(29, planilla.getNumeroHojasAnexas());
		db2.setStatement(30, planilla.getConvenio());
		db2.setStatement(31, planilla.getDatosSucursal().getCodigo());
		db2.setStatement(32, planilla.getGrupoConvenio());
		//DYNP
		db2.setStatement(33, 0);
		
		int result= db2.executeUpdate();
		for (Iterator iter = planilla.getPaginasDetalle().iterator(); iter.hasNext();) {
			PlanillaAfbrPaginaDetalle pagina = (PlanillaAfbrPaginaDetalle) iter.next();
			insertDetalle(pagina);
		}
		return result;
	}
	
	private int insertDetalle(Object obj) throws SQLException {
		PlanillaAfbrPaginaDetalle pagina= (PlanillaAfbrPaginaDetalle) obj;
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO pwdtad.pwf8400 values( ?,?,?,?,?,?,?,?,?,?,?,?,? ) ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		for (Iterator iterator = pagina.getCotizantes().iterator(); iterator.hasNext();) {
			PlanillaAfbrCotizante cotizante = (PlanillaAfbrCotizante) iterator.next();
			db2.setStatement(1, pagina.getCabeceraPlanilla().getPeriodo());
			db2.setStatement(2, pagina.getCabeceraPlanilla().getNombreEntidad());
			db2.setStatement(3, pagina.getCabeceraPlanilla().getFolio());
			db2.setStatement(4, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getNumber());
			db2.setStatement(5, pagina.getSecuenciaFolio());
			db2.setStatement(6, cotizante.getNumeroLineaDetalle());
			db2.setStatement(7, cotizante.getRutCotizante().getNumber());
			db2.setStatement(8, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getDV());
			db2.setStatement(9, cotizante.getRutCotizante().getDV());
			db2.setStatement(10, cotizante.getApellidosCotizante());
			db2.setStatement(11, cotizante.getNombresCotizante());
			db2.setStatement(12, cotizante.getRemuneracionImponible());
			db2.setStatement(13, cotizante.getAporteAFBR());
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

