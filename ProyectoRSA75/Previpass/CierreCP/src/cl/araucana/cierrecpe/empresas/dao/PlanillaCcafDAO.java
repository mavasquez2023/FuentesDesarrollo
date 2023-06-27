

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

import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.empresas.planillas.afp.PlanillaAfpCotizante;
import cl.araucana.cierrecpe.empresas.planillas.afp.PlanillaAfpPaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.ccaf.PlanillaCcafCotizante;
import cl.araucana.cierrecpe.empresas.planillas.ccaf.PlanillaCcafDocumentModel;
import cl.araucana.cierrecpe.empresas.planillas.ccaf.PlanillaCcafPaginaDetalle;
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
public class PlanillaCcafDAO implements DAO_Interface, Constants{
	private ConectaDB2 db2;
	private String esquema;
	private static Logger logger = LogManager.getLogger();
	/**
	 * 
	 */
	public PlanillaCcafDAO(ConectaDB2 db2, String esquema) {
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
		PlanillaCcafDocumentModel planilla= (PlanillaCcafDocumentModel) obj;
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO " + getEsquema() + ".pwf7100 values( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, planilla.getNombreEntidad());
		db2.setStatement(2, String.valueOf(planilla.getPeriodo()).substring(4, 6));
		db2.setStatement(3, String.valueOf(planilla.getPeriodo()).substring(0, 4));
		db2.setStatement(4, planilla.getDatosEmpleador().getRutEmpresa(9));
		db2.setStatement(5, planilla.getFolio());
		db2.setStatement(6, planilla.getSecuenciaFolio());
		db2.setStatement(7, planilla.getTipoProceso());
		db2.setStatement(8, planilla.getPeriodo());
		db2.setStatement(9, planilla.getDatosEmpleador().getRutEmpresa().getDV());
		String periodoGratiDesde= String.valueOf(planilla.getFechaInicioGrati());
		String periodoGratiHasta= String.valueOf(planilla.getFechaTerminoGrati());
		if(periodoGratiDesde.length()>=6 && periodoGratiHasta.length()>=6){
			db2.setStatement(10, periodoGratiDesde.substring(4, 6));
			db2.setStatement(11, periodoGratiDesde.substring(0, 4));
			db2.setStatement(12, periodoGratiHasta.substring(4, 6));
			db2.setStatement(13, periodoGratiHasta.substring(0, 4));
		}else{
			db2.setStatement(10, 0);
			db2.setStatement(11, 0);
			db2.setStatement(12, 0);
			db2.setStatement(13, 0);
		}
		db2.setStatement(14, planilla.getDatosEmpleador().getCodActEconomica());
		db2.setStatement(15, planilla.getDatosEmpleador().getRazonSocial());
		db2.setStatement(16, planilla.getDatosSucursal().getDireccion(75)); //incluye número.
		db2.setStatement(17, 0); //Número
		db2.setStatement(18, ""); //Oficina/Local
		db2.setStatement(19, planilla.getDatosSucursal().getTelefono(12));
		db2.setStatement(20, planilla.getDatosSucursal().getComuna());
		db2.setStatement(21, planilla.getDatosSucursal().getCiudad());
		db2.setStatement(22, 0); //número casilla correo
		db2.setStatement(23, planilla.getDatosSucursal().getEmail(15));
		db2.setStatement(24, planilla.getDatosEmpleador().getRutRepLegal(9));
		db2.setStatement(25, planilla.getDatosEmpleador().getRutRepLegal().getDV());
		db2.setStatement(26, planilla.getDatosEmpleador().getApellidosRepLegal());
		db2.setStatement(27, planilla.getDatosEmpleador().getNombresRepLegal());
		db2.setStatement(28, planilla.getAdheridoMutual());
		db2.setStatement(29, ""); //cambio en la sección
		db2.setStatement(30, planilla.getTotalCargasSimp());
		db2.setStatement(31, planilla.getTotalCargasInvl());
		db2.setStatement(32, planilla.getTotalCargasMat());
		db2.setStatement(33, planilla.getTotalMontoAsigFamiliar());
		db2.setStatement(34, planilla.getTotalHombresNoAfiliadosIsapre());
		db2.setStatement(35, planilla.getTotalMujeresNoAfiliadosIsapre());
		db2.setStatement(36, planilla.getMontoRemuneracionNoAfiliadosIsapre());
		db2.setStatement(37, TASA_NO_AFILIADO_ISAPRE);
		db2.setStatement(38, planilla.getSubtotalNoAfiliadoIsapre());
		
		db2.setStatement(39, planilla.getTotalHombresAfiliadosIsapre());
		db2.setStatement(40, planilla.getTotalMujeresAfiliadosIsapre());
		db2.setStatement(41, planilla.getMontoRemuneracionAfiliadosIsapre());
		db2.setStatement(42, TASA_AFILIADO_ISAPRE);
		db2.setStatement(43, planilla.getSubtotalAfiliadoIsapre());
		
		db2.setStatement(44, planilla.getMontoCreditos());
		db2.setStatement(45, planilla.getMontoConvenioDental());
		db2.setStatement(46, planilla.getMontoLeasing());
		db2.setStatement(47, planilla.getMontoSeguros());
		db2.setStatement(48, planilla.getMontoOtros());
		db2.setStatement(49, planilla.getTotalRemuneraciones());
		db2.setStatement(50, planilla.getTotalSubTotal());
		//REBAJAS
		//Tramo A
		db2.setStatement(51, planilla.getTotalCargasAsigFamiliarSimpTramoA());
		db2.setStatement(52, planilla.getTotalCargasAsigFamiliarInvlTramoA());
		db2.setStatement(53, planilla.getTotalCargasAsigFamiliarMatTramoA());
		db2.setStatement(54, planilla.getTotalTrabajadoresConCargaTramoA());
		db2.setStatement(55, planilla.getTotalMontoAsigFamiliarTramoA());
		//Tramo B
		db2.setStatement(56, planilla.getTotalCargasAsigFamiliarSimpTramoB());
		db2.setStatement(57, planilla.getTotalCargasAsigFamiliarInvlTramoB());
		db2.setStatement(58, planilla.getTotalCargasAsigFamiliarMatTramoB());
		db2.setStatement(59, planilla.getTotalTrabajadoresConCargaTramoB());
		db2.setStatement(60, planilla.getTotalMontoAsigFamiliarTramoB());
		//Tramo C
		db2.setStatement(61, planilla.getTotalCargasAsigFamiliarSimpTramoC());
		db2.setStatement(62, planilla.getTotalCargasAsigFamiliarInvlTramoC());
		db2.setStatement(63, planilla.getTotalCargasAsigFamiliarMatTramoC());
		db2.setStatement(64, planilla.getTotalTrabajadoresConCargaTramoC());
		db2.setStatement(65, planilla.getTotalMontoAsigFamiliarTramoC());
		//Tramo D
		db2.setStatement(66, planilla.getTotalCargasAsigFamiliarSimpTramoD());
		db2.setStatement(67, planilla.getTotalCargasAsigFamiliarInvlTramoD());
		db2.setStatement(68, planilla.getTotalCargasAsigFamiliarMatTramoD());
		db2.setStatement(69, planilla.getTotalTrabajadoresConCargaTramoD());
		//Retroactiva
		db2.setStatement(70, planilla.getTotalCargasAsigFamiliarSimpRetroactiva());
		db2.setStatement(71, planilla.getTotalCargasAsigFamiliarInvlRetroactiva());
		db2.setStatement(72, planilla.getTotalCargasAsigFamiliarMatRetroactiva());
		db2.setStatement(73, planilla.getTotalMontoAsigFamiliarRetroactiva());
		//Reintegro
		db2.setStatement(74, planilla.getTotalCargasAsigFamiliarSimpReintegros());
		db2.setStatement(75, planilla.getTotalCargasAsigFamiliarInvlReintegros());
		db2.setStatement(76, planilla.getTotalCargasAsigFamiliarMatReintegros());
		db2.setStatement(77, planilla.getTotalMontoAsigFamiliarReintegros());
		//Totales
		db2.setStatement(78, planilla.getTotalRebajas());
		db2.setStatement(79, planilla.getSaldoAFavorInstitucion());
		db2.setStatement(80, planilla.getSaldoAFavorEmpleador());
		db2.setStatement(81, planilla.getFechaPago());
		
		//Autorizadas
		db2.setStatement(82, 0);
		db2.setStatement(83, 0);
		db2.setStatement(84, 0);
		db2.setStatement(85, 0);
		db2.setStatement(86, 0);
		db2.setStatement(87, 0);
		db2.setStatement(88, 0);
		db2.setStatement(89, 0);
		db2.setStatement(90, 0);
		db2.setStatement(91, 0);
		db2.setStatement(92, 0);
		db2.setStatement(93, 0);
		db2.setStatement(94, 0);
		db2.setStatement(95, 0);
		db2.setStatement(96, 0);
		//db2.setStatement(96, planilla.getTotalCargasSimp() + planilla.getTotalCargasInvl() + planilla.getTotalCargasMat());
		db2.setStatement(97, planilla.getTotalMontoAsigFamiliar());
		
		db2.setStatement(98, planilla.getConvenio());
		db2.setStatement(99, planilla.getDatosSucursal().getCodigo());
		db2.setStatement(100, planilla.getGrupoConvenio());
		int result= db2.executeUpdate();
		for (Iterator iter = planilla.getPaginasDetalle().iterator(); iter.hasNext();) {
			PlanillaCcafPaginaDetalle pagina = (PlanillaCcafPaginaDetalle) iter.next();
			insertDetalleAnexos(pagina);
		}
		
		for (Iterator iter = planilla.getPaginasDetalleCredito().iterator(); iter.hasNext();) {
			PlanillaCcafPaginaDetalle pagina = (PlanillaCcafPaginaDetalle) iter.next();
			insertDetalleCredito(pagina);
		}
		for (Iterator iter = planilla.getPaginasDetalleLeasing().iterator(); iter.hasNext();) {
			PlanillaCcafPaginaDetalle pagina = (PlanillaCcafPaginaDetalle) iter.next();
			insertDetalleLeasing(pagina);
		}
		for (Iterator iter = planilla.getPaginasDetalleSeguroVida().iterator(); iter.hasNext();) {
			PlanillaCcafPaginaDetalle pagina = (PlanillaCcafPaginaDetalle) iter.next();
			insertDetalleSeguroVida(pagina);
		}
		for (Iterator iter = planilla.getPaginasDetalleConvenioDental().iterator(); iter.hasNext();) {
			PlanillaCcafPaginaDetalle pagina = (PlanillaCcafPaginaDetalle) iter.next();
			insertDetalleConvenioDental(pagina);
		}
		
		return result;
	}
	private int insertDetalleAnexos(Object obj) throws SQLException {
		PlanillaCcafPaginaDetalle pagina= (PlanillaCcafPaginaDetalle) obj;
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO " + getEsquema() + ".pwf7200 values( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		for (Iterator iterator = pagina.getCotizantes().iterator(); iterator.hasNext();) {
			PlanillaCcafCotizante cotizante = (PlanillaCcafCotizante) iterator.next();
			db2.setStatement(1, pagina.getCabeceraPlanilla().getNombreEntidad());
			db2.setStatement(2, pagina.getCabeceraPlanilla().getFolio());
			db2.setStatement(3, pagina.getSecuenciaFolio());
			db2.setStatement(4, String.valueOf(pagina.getCabeceraPlanilla().getPeriodo()).substring(4, 6));
			db2.setStatement(5, String.valueOf(pagina.getCabeceraPlanilla().getPeriodo()).substring(0, 4));
			db2.setStatement(6, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa(9));
			db2.setStatement(7, cotizante.getNumeroLineaDetalle());
			db2.setStatement(8, cotizante.getRutCotizante(9));
			db2.setStatement(9, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getDV());
			db2.setStatement(10, cotizante.getRutCotizante().getDV());
			db2.setStatement(11, cotizante.getApellidosCotizante());
			db2.setStatement(12, cotizante.getNombresCotizante());
			db2.setStatement(13, cotizante.getRemImponibleNoAfiliadosIsapreCotizante());
			db2.setStatement(14, cotizante.getRemImponibleAfiliadosIsapreCotizante());
			db2.setStatement(15, cotizante.getDiasTrabajadosCotizante());
			db2.setStatement(16, cotizante.getCantidadCargasSimCotizante());
			db2.setStatement(17, cotizante.getCantidadCargasInvCotizante());
			db2.setStatement(18, cotizante.getCantidadCargasMatCotizante());
			db2.setStatement(19, cotizante.getMontoAsigFamiliarCotizante());
			db2.setStatement(20, cotizante.getMontoAsigFamiliarRetroactivoCotizante());
			db2.setStatement(21, cotizante.getMontoAsigFamiliarReintegroCotizante());
			db2.setStatement(22, cotizante.getCodigoTramo());
			db2.setStatement(23, cotizante.getCodigoMovimiento());
			try{
				db2.setStatement(24, cotizante.getFechaDesdeAsigFamiliarCotizante().getPeriod() + "" + Formato.padding(cotizante.getFechaDesdeAsigFamiliarCotizante().getDay(), 2));
			}catch(NullPointerException ne){
				db2.setStatement(24, 0);
			}
			try{
				db2.setStatement(25, cotizante.getFechaHastaAsigFamiliarCotizante().getPeriod() + "" + Formato.padding(cotizante.getFechaHastaAsigFamiliarCotizante().getDay(), 2));
			}catch(NullPointerException ne){
					db2.setStatement(25, 0);
			}
			db2.setStatement(26, cotizante.getCodigoSexo());
			db2.setStatement(27, cotizante.getMonto06Cotizante());
			db2.setStatement(28, cotizante.getNacionalidad());
			db2.executeUpdate();
		}
		
		return 0;
	}
	
	private int insertDetalleCredito(Object obj) throws SQLException {
		PlanillaCcafPaginaDetalle pagina= (PlanillaCcafPaginaDetalle) obj;
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO " + getEsquema() + ".pwf7400 values( ?,?,?,?,?,?,?,?,?,?,?,? ) ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		for (Iterator iterator = pagina.getCotizantes().iterator(); iterator.hasNext();) {
			PlanillaCcafCotizante cotizante = (PlanillaCcafCotizante) iterator.next();
			db2.setStatement(1, pagina.getCabeceraPlanilla().getNombreEntidad());
			db2.setStatement(2, pagina.getFolioDetalle());
			db2.setStatement(3, pagina.getSecuenciaFolio());
			String periodo= String.valueOf(pagina.getCabeceraPlanilla().getPeriodo());
			db2.setStatement(4, periodo.substring(4, 6));
			db2.setStatement(5, periodo.substring(0, 4));
			db2.setStatement(6, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa(9));
			db2.setStatement(7, cotizante.getRutCotizante(9));
			db2.setStatement(8, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getDV());
			db2.setStatement(9, cotizante.getRutCotizante().getDV());
			db2.setStatement(10, cotizante.getApellidosCotizante());
			db2.setStatement(11, cotizante.getNombresCotizante());
			db2.setStatement(12, cotizante.getMontoCuotaCredito());
			db2.executeUpdate();
		}
		
		return 0;
	}
	private int insertDetalleLeasing(Object obj) throws SQLException {
		PlanillaCcafPaginaDetalle pagina= (PlanillaCcafPaginaDetalle) obj;
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO " + getEsquema() + ".pwf7600 values( ?,?,?,?,?,?,?,?,?,?,?,? ) ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		for (Iterator iterator = pagina.getCotizantes().iterator(); iterator.hasNext();) {
			PlanillaCcafCotizante cotizante = (PlanillaCcafCotizante) iterator.next();
			db2.setStatement(1, pagina.getCabeceraPlanilla().getNombreEntidad());
			db2.setStatement(2, pagina.getFolioDetalle());
			db2.setStatement(3, pagina.getSecuenciaFolio());
			String periodo= String.valueOf(pagina.getCabeceraPlanilla().getPeriodo());
			db2.setStatement(4, periodo.substring(4, 6));
			db2.setStatement(5, periodo.substring(0, 4));
			db2.setStatement(6, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa(9));
			db2.setStatement(7, cotizante.getRutCotizante(9));
			db2.setStatement(8, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getDV());
			db2.setStatement(9, cotizante.getRutCotizante().getDV());
			db2.setStatement(10, cotizante.getApellidosCotizante());
			db2.setStatement(11, cotizante.getNombresCotizante());
			db2.setStatement(12, cotizante.getMontoCuotaLeasing());
			db2.executeUpdate();
		}
		
		return 0;
	}
	private int insertDetalleConvenioDental(Object obj) throws SQLException {
		PlanillaCcafPaginaDetalle pagina= (PlanillaCcafPaginaDetalle) obj;
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO " + getEsquema() + ".pwf7800 values( ?,?,?,?,?,?,?,?,?,?,?,? ) ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		for (Iterator iterator = pagina.getCotizantes().iterator(); iterator.hasNext();) {
			PlanillaCcafCotizante cotizante = (PlanillaCcafCotizante) iterator.next();
			db2.setStatement(1, pagina.getCabeceraPlanilla().getNombreEntidad());
			db2.setStatement(2, pagina.getFolioDetalle());
			db2.setStatement(3, pagina.getSecuenciaFolio());
			String periodo= String.valueOf(pagina.getCabeceraPlanilla().getPeriodo());
			db2.setStatement(4, periodo.substring(4, 6));
			db2.setStatement(5, periodo.substring(0, 4));
			db2.setStatement(6, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa(9));
			db2.setStatement(7, cotizante.getRutCotizante(9));
			db2.setStatement(8, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getDV());
			db2.setStatement(9, cotizante.getRutCotizante().getDV());
			db2.setStatement(10, cotizante.getApellidosCotizante());
			db2.setStatement(11, cotizante.getNombresCotizante());
			db2.setStatement(12, cotizante.getMontoCuotaConvenioDental());
			db2.executeUpdate();
		}
		
		return 0;
	}
	private int insertDetalleSeguroVida(Object obj) throws SQLException {
		PlanillaCcafPaginaDetalle pagina= (PlanillaCcafPaginaDetalle) obj;
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO " + getEsquema() + ".pwf7900 values( ?,?,?,?,?,?,?,?,?,?,?,? ) ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		for (Iterator iterator = pagina.getCotizantes().iterator(); iterator.hasNext();) {
			PlanillaCcafCotizante cotizante = (PlanillaCcafCotizante) iterator.next();
			db2.setStatement(1, pagina.getCabeceraPlanilla().getNombreEntidad());
			db2.setStatement(2, pagina.getFolioDetalle());
			db2.setStatement(3, pagina.getSecuenciaFolio());
			String periodo= String.valueOf(pagina.getCabeceraPlanilla().getPeriodo());
			db2.setStatement(4, periodo.substring(4, 6));
			db2.setStatement(5, periodo.substring(0, 4));
			db2.setStatement(6, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa(9));
			db2.setStatement(7, cotizante.getRutCotizante(9));
			db2.setStatement(8, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getDV());
			db2.setStatement(9, cotizante.getRutCotizante().getDV());
			db2.setStatement(10, cotizante.getApellidosCotizante());
			db2.setStatement(11, cotizante.getNombresCotizante());
			db2.setStatement(12, cotizante.getMontoCuotaSeguroVida());
			//System.out.println(">>PlanillaCcafDAO.insert detalle, query:" + query.toString());
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

