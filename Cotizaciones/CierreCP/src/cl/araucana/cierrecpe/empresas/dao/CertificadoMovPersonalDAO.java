

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
import cl.araucana.cierrecpe.empresas.planillas.afp.PlanillaAfpDocumentModel;
import cl.araucana.cierrecpe.empresas.planillas.afp.PlanillaAfpPaginaDetalle;
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
public class CertificadoMovPersonalDAO implements DAO_Interface {
	private ConectaDB2 db2;
	private String esquema;
	private static Logger logger = LogManager.getLogger();
	/**
	 * 
	 */
	public CertificadoMovPersonalDAO(ConectaDB2 db2, String esquema) {
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
		PlanillaAfpDocumentModel planilla= (PlanillaAfpDocumentModel) obj;
		StringBuffer query= new StringBuffer();
		query.append("INSERT INTO " + getEsquema() + ".pwf2000 values( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ");
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, planilla.getNombreEntidad());
		db2.setStatement(2, planilla.getFolio());
		db2.setStatement(3, planilla.getDatosEmpleador().getRutEmpresa(9));
		db2.setStatement(4, planilla.getDatosEmpleador().getRutEmpresa().getDV());
		db2.setStatement(5, planilla.getSecuenciaFolio());
		db2.setStatement(6, planilla.getTipoProceso());
		db2.setStatement(7, planilla.getPeriodo());
		db2.setStatement(8, 0);
		db2.setStatement(9, planilla.getDatosEmpleador().getRazonSocial());
		db2.setStatement(10, planilla.getDatosEmpleador().getCodActEconomica());
		db2.setStatement(11, planilla.getDatosSucursal().getDireccion());
		db2.setStatement(12, planilla.getDatosSucursal().getComuna());
		db2.setStatement(13, planilla.getDatosSucursal().getCiudad());
		db2.setStatement(14, planilla.getDatosSucursal().getRegion());
		db2.setStatement(15, planilla.getDatosSucursal().getTelefono());
		db2.setStatement(16, planilla.getDatosEmpleador().getNombresRepLegal() + " " + planilla.getDatosEmpleador().getApellidosRepLegal());
		db2.setStatement(17, planilla.getDatosEmpleador().getRutRepLegal(9));
		db2.setStatement(18, planilla.getDatosEmpleador().getRutRepLegal().getDV());
		db2.setStatement(19, '0');
		db2.setStatement(20, planilla.getNumeroAfiliadosInformados());
		db2.setStatement(21, planilla.getCotizacionObligFdoPensiones());
		db2.setStatement(22, planilla.getDepositosCtaAhorroFdoPensiones());
		db2.setStatement(23, planilla.getSubtotalAPagarFdoPensiones());
		db2.setStatement(24, planilla.getTotalAPagarFdoPensiones());
		db2.setStatement(25, planilla.getTotalAPagarAfp());
		db2.setStatement(26, planilla.getCotizacionAfiliadoFdoCesantia());
		db2.setStatement(27, planilla.getCotizacionEmpleadorFdoCesantia());
		db2.setStatement(28, planilla.getSubtotalAPagarFdoCesantia());
		db2.setStatement(29, planilla.getTotalAPagarFdoCesantia());
		db2.setStatement(30, planilla.getTotalAPagarAfc());
		db2.setStatement(31, planilla.getTipoIngresoImponible());
		db2.setStatement(32, String.valueOf(planilla.getPeriodo()).substring(4, 6));
		db2.setStatement(33, String.valueOf(planilla.getPeriodo()).substring(0, 4));
		db2.setStatement(34, planilla.getTipoPago());
		db2.setStatement(35, planilla.getFechaInicioGrati());
		db2.setStatement(36, planilla.getFechaTerminoGrati());
		db2.setStatement(37, planilla.getFechaPago());
		db2.setStatement(38, planilla.getTotalRemuneracionesOGratifFdoPensiones());
		db2.setStatement(39, planilla.getTotalRemuneracionesOGratifFdoCesantia());
		db2.setStatement(40, planilla.getNumeroAfiliadosFdoPensiones());
		db2.setStatement(41, planilla.getNumeroAfiliadosFdoCesantia());
		db2.setStatement(42, planilla.getNumeroHojasAnexas());
		db2.setStatement(43, planilla.getConvenio());
		db2.setStatement(44, planilla.getDatosSucursal().getCodigo());
		db2.setStatement(45, planilla.getGrupoConvenio());
		db2.setStatement(46, planilla.getFilter()); //Declaración y No Pago
		db2.setStatement(47, planilla.getSeguroInvalidezFdoPensiones()); //SIS
		int result= db2.executeUpdate();
		for (Iterator iter = planilla.getPaginasDetalle().iterator(); iter.hasNext();) {
			PlanillaAfpPaginaDetalle pagina = (PlanillaAfpPaginaDetalle) iter.next();
			insertDetalle(pagina);
		}
		return result;
	}
	
	private int insertDetalle(Object obj) throws SQLException {
		PlanillaAfpPaginaDetalle pagina= (PlanillaAfpPaginaDetalle) obj;
		for (Iterator iterator = pagina.getCotizantes().iterator(); iterator.hasNext();) {
			PlanillaAfpCotizante cotizante = (PlanillaAfpCotizante) iterator.next();
			StringBuffer query= new StringBuffer();
			query.append("INSERT INTO " + getEsquema() + ".pwf2100 values( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ");
			//logger.finest("Query=" + query.toString());
			db2.prepareQuery(query.toString());
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
			db2.setStatement(11, cotizante.getRemuneracionImponibleFdoPensionCotizante());
			db2.setStatement(12, cotizante.getCotizacionObligatoriaFdoPensionCotizante());
			db2.setStatement(13, cotizante.getDepositoCtaAhorroFdoPensionCotizante());
			db2.setStatement(14, cotizante.getRemuneracionImponibleCesantiaCotizante());
			db2.setStatement(15, cotizante.getCotizacionAfiliadoCesantiaCotizante());
			db2.setStatement(16, cotizante.getCotizacionEmpleadorCesantiaCotizante());
			db2.setStatement(17, cotizante.getCodigoMovimientoPersonalCotizante());
			
			try {
				db2.setStatement(18, cotizante.getFechaInicioMovimientoPersonalCotizante().getPeriod() + "" + Formato.padding(cotizante.getFechaInicioMovimientoPersonalCotizante().getDay(), 2));
			} catch (Exception e) {
				db2.setStatement(18, 0);
			} 
			try {
				db2.setStatement(19, cotizante.getFechaTerminoMovimientoPersonalCotizante().getPeriod() + "" + Formato.padding(cotizante.getFechaTerminoMovimientoPersonalCotizante().getDay(), 2));
			} catch (Exception e) {
				db2.setStatement(19, 0);
			}
			try{
			db2.setStatement(20, cotizante.getRutEntidadPagadorSubsidioCotizante().getNumber());
			db2.setStatement(21, cotizante.getRutEntidadPagadorSubsidioCotizante().getDV());
			}catch(NullPointerException ne){
				db2.setStatement(20, 0);
				db2.setStatement(21, "");
			}
			db2.setStatement(22, cotizante.getCodigoAfpAfiliadoVoluntarioCotizante());
			if( cotizante.getRutDependienteAfiliadoVoluntario()!= null){
				db2.setStatement(23, cotizante.getRutDependienteAfiliadoVoluntario().getNumber());
				db2.setStatement(24, cotizante.getRutDependienteAfiliadoVoluntario().getDV());
			}else{
				db2.setStatement(23, "");
				db2.setStatement(24, "");
			}
			db2.setStatement(25, cotizante.getApellidosDependienteAfiliadoVoluntario());
			db2.setStatement(26, cotizante.getNombresDependienteAfiliadoVoluntario());
			db2.setStatement(27, cotizante.getSeguroInvalidezFdoPensionCotizante()); //SIS
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

