

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
import cl.araucana.cierrecpe.empresas.planillas.inp.PlanillaInpCotizante;
import cl.araucana.cierrecpe.empresas.planillas.inp.PlanillaInpDocumentModel;
import cl.araucana.cierrecpe.empresas.planillas.inp.PlanillaInpPaginaDetalle;
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
public class PlanillaINPDAO implements DAO_Interface {
	private ConectaDB2 db2;
	private String esquema;
	private static Logger logger = LogManager.getLogger();
	/**
	 * 
	 */
	public PlanillaINPDAO(ConectaDB2 db2, String esquema) {
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
		PlanillaInpDocumentModel planilla= (PlanillaInpDocumentModel) obj;
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO " + getEsquema() + ".pwf4700 values( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, planilla.getFolio()); //Nro. Serie
		db2.setStatement(2, planilla.getFolio_Recaudador());
		db2.setStatement(3, planilla.getConvenio());
		db2.setStatement(4, planilla.getDatosSucursal().getCodigo());
		db2.setStatement(5, planilla.getNumeroHojasAnexas());
		db2.setStatement(6, planilla.getDatosEmpleador().getRutEmpresa(9));
		db2.setStatement(7, planilla.getTipoProceso());
		db2.setStatement(8, planilla.getPeriodo());
		db2.setStatement(9, planilla.getDatosEmpleador().getRutEmpresa().getDV());
		db2.setStatement(10, planilla.getDatosEmpleador().getRazonSocial());
		db2.setStatement(11, planilla.getDatosSucursal().getTelefono(12));
		db2.setStatement(12, planilla.getDatosSucursal().getFax(7));
		db2.setStatement(13, planilla.getDatosSucursal().getDireccion());
		db2.setStatement(14, planilla.getDatosSucursal().getEmail(30));
		db2.setStatement(15, planilla.getDatosSucursal().getComuna());
		db2.setStatement(16, planilla.getDatosSucursal().getCiudad());
		db2.setStatement(17, planilla.getDatosSucursal().getRegion());
		db2.setStatement(18, ""); //Cód. postal
		db2.setStatement(19, planilla.getDatosEmpleador().getCodActEconomica());
		db2.setStatement(20, planilla.getNombreCCAF());
		db2.setStatement(21, planilla.getNombreMutual());
		db2.setStatement(22, planilla.getDatosEmpleador().getRutRepLegal(9));
		db2.setStatement(23, planilla.getDatosEmpleador().getRutRepLegal().getDV());
		db2.setStatement(24, planilla.getDatosEmpleador().getApellidosRepLegal());
		db2.setStatement(25, planilla.getDatosEmpleador().getNombresRepLegal());
		db2.setStatement(26, planilla.getDatosSucursal().getTelefono(7));
		db2.setStatement(27, planilla.getDatosSucursal().getFax(7));
		db2.setStatement(28, planilla.getDatosSucursal().getEmail(30));
		db2.setStatement(29, String.valueOf(planilla.getPeriodo()).substring(0, 4));
		db2.setStatement(30, String.valueOf(planilla.getPeriodo()).substring(4, 6));
		String periodoGratiDesde= String.valueOf(planilla.getFechaInicioGrati());
		String periodoGratiHasta= String.valueOf(planilla.getFechaTerminoGrati());
		if(periodoGratiDesde.length()>=6 && periodoGratiHasta.length()>=6){
			db2.setStatement(31, periodoGratiDesde.substring(0, 4));
			db2.setStatement(32, periodoGratiDesde.substring(4, 6));
			db2.setStatement(33, periodoGratiHasta.substring(0, 4));
			db2.setStatement(34, periodoGratiHasta.substring(4, 6));
		}else{
			db2.setStatement(31, 0);
			db2.setStatement(32, 0);
			db2.setStatement(33, 0);
			db2.setStatement(34, 0);
		}
		try{
			periodoGratiDesde= String.valueOf(planilla.getFechaGratificacionVolDesde().getPeriod());
			periodoGratiHasta= String.valueOf(planilla.getFechaGratificacionVolHasta().getPeriod());
			db2.setStatement(35, periodoGratiDesde.substring(0, 4));
			db2.setStatement(36, periodoGratiDesde.substring(4, 6));
			db2.setStatement(37, periodoGratiHasta.substring(0, 4));
			db2.setStatement(38, periodoGratiHasta.substring(4, 6));
			
		}catch(NullPointerException ne){
			db2.setStatement(35, 0);
			db2.setStatement(36, 0);
			db2.setStatement(37, 0);
			db2.setStatement(38, 0);
		}	
			
		db2.setStatement(39, planilla.getTotalMontoPensionesInp());
		db2.setStatement(40, planilla.getTotalMontoFonasa());
		db2.setStatement(41, planilla.getTotalMontoAccDelTrabajo());
		db2.setStatement(42, planilla.getTotalMontoDesahucio());
		db2.setStatement(43, planilla.getTotalMontoCotizaciones());
		db2.setStatement(44, planilla.getTotalMontoReajusteEInteres());
		double tasarei=planilla.getTasaReajusteEInteres();
		if(tasarei>0){
			db2.setStatement(45, planilla.getTasaReajusteEInteres());
		}else{
			db2.setStatement(45, '0');
		}
		db2.setStatement(46, planilla.getTotalMontoMultas());
		db2.setStatement(47, planilla.getTotalMontoGravamenes());
		db2.setStatement(48, planilla.getTotalMontoAsigFamiliar());
		db2.setStatement(49, planilla.getTotalMontoBonificacionLey15386());
		db2.setStatement(50, planilla.getTotalMontoRebajas());
		db2.setStatement(51, planilla.getTotalAPagar());
		db2.setStatement(52, planilla.getTotalMontoSaldoFavorInstitucion());
		db2.setStatement(53, planilla.getTotalMontoSaldoFavorEmpleador());
		db2.setStatement(54, planilla.getFechaPago());
		db2.setStatement(55, planilla.getGrupoConvenio());
		db2.setStatement(56, planilla.getFilter());
		db2.setStatement(57, planilla.getDatosSucursal().getIdComuna());
		db2.setStatement(58, planilla.getCodigoMutual());
		db2.setStatement(59, planilla.getCodigoCCAF());
		
		int result= db2.executeUpdate();
		for (Iterator iter = planilla.getPaginasDetalle().iterator(); iter.hasNext();) {
			PlanillaInpPaginaDetalle pagina = (PlanillaInpPaginaDetalle) iter.next();
			insertDetalle(pagina);
			insertTotales(pagina);
		}
		return result;
	}
	
	private int insertDetalle(Object obj) throws SQLException {
		PlanillaInpPaginaDetalle pagina= (PlanillaInpPaginaDetalle) obj;
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO " + getEsquema() + ".pwf4800 values( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		for (Iterator iterator = pagina.getCotizantes().iterator(); iterator.hasNext();) {
			PlanillaInpCotizante cotizante = (PlanillaInpCotizante) iterator.next();
			db2.setStatement(1, pagina.getCabeceraPlanilla().getFolio());
			db2.setStatement(2, cotizante.getRutCotizante(9));
			db2.setStatement(3, pagina.getPaginaActual());
			db2.setStatement(4, cotizante.getNumeroLineaDetalle());
			db2.setStatement(5, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa(9));
			db2.setStatement(6, cotizante.getRutCotizante().getDV());
			db2.setStatement(7, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getDV());
			db2.setStatement(8, cotizante.getApellidosCotizante());
			db2.setStatement(9, cotizante.getNombresCotizante());
			db2.setStatement(10, cotizante.getDiasTrabajadosCotizante());
			db2.setStatement(11, cotizante.getRemImponibleCotizante());
			db2.setStatement(12, cotizante.getPensionInpCotizante());
			db2.setStatement(13, cotizante.getFonasaCotizante());
			db2.setStatement(14, cotizante.getAccDelTrabajoCotizante());
			db2.setStatement(15, cotizante.getRemImponibleDesahucioCotizante());
			db2.setStatement(16, cotizante.getCotizacionDesahucioCotizante());
			db2.setStatement(17, cotizante.getCodigoTramoCotizante());
			db2.setStatement(18, cotizante.getMontoAsigFamiliarSimpleCotizante());
			db2.setStatement(19, cotizante.getNroCargasSimpleCotizante());
			db2.setStatement(20, cotizante.getMontoAsigFamiliarInvalidaCotizante());
			db2.setStatement(21, cotizante.getNroCargasInvalidaCotizante());
			db2.setStatement(22, cotizante.getMontoAsigFamiliarMaternalCotizante());
			db2.setStatement(23, cotizante.getNroCargasMaternalCotizante());
			db2.setStatement(24, cotizante.getMontoBonificacionLey15385Cotizante());
			db2.executeUpdate();
		}
		
		return 0;
	}
	
	private int insertTotales(Object obj) throws SQLException {
		PlanillaInpPaginaDetalle pagina= (PlanillaInpPaginaDetalle) obj;
			StringBuffer query= new StringBuffer();
			query.append("INSERT INTO " + getEsquema() + ".pwf4900 values( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ");
			logger.finest("Query=" + query.toString());
			db2.prepareQuery(query.toString());
			db2.setStatement(1, pagina.getCabeceraPlanilla().getFolio());
			db2.setStatement(2, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa(9));
			db2.setStatement(3, pagina.getPaginaActual());
			db2.setStatement(4, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getDV());
			db2.setStatement(5, pagina.getTotalRemuneraciones());
			db2.setStatement(6, pagina.getTotalPensiones());
			db2.setStatement(7, pagina.getTotalFonasa());
			db2.setStatement(8, pagina.getTotalAccidente());
			db2.setStatement(9, pagina.getTotalRemuneracionesDesahucio());
			db2.setStatement(10, pagina.getTotalCotizacionDesahucio());
			db2.setStatement(11, pagina.getTotalMontoSimple());
			db2.setStatement(12, pagina.getTotalMontoInvalida());
			db2.setStatement(13, pagina.getTotalMontoMaternal());
			db2.setStatement(14, pagina.getTotalBonificacion15366());
			db2.setStatement(15, pagina.getTotalGeneralRemuneraciones());
			db2.setStatement(16, pagina.getTotalGeneralPensiones());
			db2.setStatement(17, pagina.getTotalGeneralFonasa());
			db2.setStatement(18, pagina.getTotalGeneralAccidente());
			db2.setStatement(19, pagina.getTotalGeneralRemuneracionesDesahucio());
			db2.setStatement(20, pagina.getTotalGeneralCotizacionDesahucio());
			db2.setStatement(21, pagina.getTotalGeneralMontoSimple());
			db2.setStatement(22, pagina.getTotalGeneralMontoInvalida());
			db2.setStatement(23, pagina.getTotalGeneralMontoMaternal());
			db2.setStatement(24, pagina.getTotalGeneralBonificacion15366());
			db2.executeUpdate();
		
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

