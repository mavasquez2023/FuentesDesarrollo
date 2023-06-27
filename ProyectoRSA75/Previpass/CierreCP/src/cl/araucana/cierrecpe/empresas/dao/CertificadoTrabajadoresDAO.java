

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
import cl.araucana.cierrecp.empresas.comprobantes.Comprobante_Encabezado;
import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.dao.DAO_Interface;
import cl.araucana.cierrecpe.dao.PubDAO;
import cl.araucana.cierrecpe.empresas.planillas.PlanillaBaseCotizante;
import cl.araucana.cierrecpe.empresas.planillas.PlanillaBasePaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.afp.PlanillaAfpCotizante;
import cl.araucana.cierrecpe.empresas.planillas.afp.PlanillaAfpPaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.apv.PlanillaApvCotizante;
import cl.araucana.cierrecpe.empresas.planillas.apv.PlanillaApvPaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.ccaf.PlanillaCcafCotizante;
import cl.araucana.cierrecpe.empresas.planillas.ccaf.PlanillaCcafPaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.inp.PlanillaInpCotizante;
import cl.araucana.cierrecpe.empresas.planillas.inp.PlanillaInpPaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.isapre.PlanillaIsapreCotizante;
import cl.araucana.cierrecpe.empresas.planillas.isapre.PlanillaIsaprePaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.mutual.PlanillaMutualCotizante;
import cl.araucana.cierrecpe.empresas.planillas.mutual.PlanillaMutualPaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.otros.PlanillaAfbrCotizante;
import cl.araucana.cierrecpe.empresas.planillas.otros.PlanillaAfbrPaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.tp.PlanillaTpCotizante;
import cl.araucana.cierrecpe.empresas.planillas.tp.PlanillaTpPaginaDetalle;
import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.ConectaDB2;
import cl.recursos.Formato;
import cl.recursos.Today;

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
public class CertificadoTrabajadoresDAO implements DAO_Interface {
	private ConectaDB2 db2, db2det;
	private String tipoNomina;
	private static Logger logger = LogManager.getLogger();
	private PubDAO pubDAO;
	/**
	 * @throws SQLException 
	 * 
	 */
	public CertificadoTrabajadoresDAO(ConectaDB2 db2) throws SQLException {
		this.db2= db2;
		pubDAO= new PubDAO();
		this.db2det= pubDAO.getConnection();
		pubDAO.setAutoCommit(false);
	}

	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void delete(Object pk) throws SQLException {
		Comprobante_Encabezado comprobante= (Comprobante_Encabezado) pk;
		String cabecera="";
		String detalle="";
		String tipoNomina= comprobante.getTipoProceso();
		if(tipoNomina.equals("R")){
			
			cabecera= "PWF6000";
			
			//Borrado Detalle de Remu
			detalle= "PWF6100";
			deleteDetalle(comprobante, detalle);
			
			//Borrado Detalle de APV
			detalle= "PWF6103";
			deleteDetalle(comprobante, detalle);
			
			//Borrado de Mov. Personal
			detalle= "PWF6104";
			deleteDetalle(comprobante, detalle);
			
			//Borrado Cabecera
			deleteCabecera(comprobante, cabecera);
			
		}else if(tipoNomina.equals("G")){
			cabecera= "PWF6001";
			detalle= "PWF6101";
			deleteDetalle(comprobante, detalle);
			deleteCabecera(comprobante, cabecera);
		}else if(tipoNomina.equals("D")){
			cabecera= "PWF6002";
			detalle= "PWF6102";
			deleteDetalle(comprobante, detalle);
			deleteCabecera(comprobante, cabecera);
		}else if(tipoNomina.equals("A")){
			cabecera= "PWF6005";
			detalle= "PWF6105";
			deleteDetalle(comprobante, detalle);
			deleteCabecera(comprobante, cabecera);
		}
		

	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void deleteCabecera(Object pk, String cabecera) throws SQLException {
		Comprobante_Encabezado comprobante= (Comprobante_Encabezado) pk;

		StringBuffer query= new StringBuffer();
		query.append("delete from " + cabecera);
		query.append(" where pwccrutem= ? ");
		query.append(" and pwcccopro= ? ");
		query.append(" and pwcccdhol= ? ");
		query.append(" and pwccconve= ? ");
		
		
		//logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, comprobante.getDatosEmpleador().getRutEmpresa().getNumber());
		db2.setStatement(2, comprobante.getPeriodo());
		db2.setStatement(3, comprobante.getGrupoConvenio());
		db2.setStatement(4, comprobante.getConvenio());

		db2.executeUpdate();

	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#delete(java.lang.Object)
	 */
	public void deleteDetalle(Object pk, String detalle) throws SQLException {
		Comprobante_Encabezado comprobante= (Comprobante_Encabezado) pk;
		String perstr=String.valueOf(comprobante.getPeriodo());
		StringBuffer query= new StringBuffer();
		query.append("delete from " + detalle);
		query.append(" where pwdcrutem= ? ");
		query.append(" and pwdcanore= ? " );
		query.append(" and pwdcmesre= ? ");
		query.append(" and pwdccdhol= ? ");
		query.append(" and pwdcconve= ? ");
		
		
		//logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, comprobante.getDatosEmpleador().getRutEmpresa().getNumber());
		db2.setStatement(2, perstr.substring(0, 4));
		db2.setStatement(3, perstr.substring(4));
		db2.setStatement(4, comprobante.getGrupoConvenio());
		db2.setStatement(5, comprobante.getConvenio());

		db2.executeUpdate();

	}
	
	public int insert(Object obj) throws SQLException {
		return insert(obj, 0);
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#insert(java.lang.Object)
	 */
	public int insert(Object obj, int id_tipo_seccion) throws SQLException {
		int result=0;
		String tabla="";
		String tabladet="";
		String tablaMP="pwf6104";
		String tablaAPV="pwf6103";
		/********** SE INSERTA LA CABECERA************/
		PlanillaBasePaginaDetalle pagina= (PlanillaBasePaginaDetalle) obj;
		String tipoNomina= pagina.getCabeceraPlanilla().getTipoProceso();
		setTipoNomina(tipoNomina);
			
		if(getTipoNomina().equals("R")){
			tabla= "PWF6000";
			tabladet= "PWF6100";
		}else if(getTipoNomina().equals("G")){
			tabla= "PWF6001";
			tabladet= "PWF6101";
		}else if(getTipoNomina().equals("D") || (getTipoNomina().equals("R") &&  id_tipo_seccion==Constants.DEP_CONV_AFP)){
			tabla= "PWF6002";
			tabladet= "PWF6102";
		}else if(getTipoNomina().equals("A")){
			tabla= "PWF6005";
			tabladet= "PWF6105";
		}
		boolean setQuery=false;
		boolean setQueryDet=false;
		for (Iterator iterator = pagina.getCotizantes().iterator(); iterator.hasNext();) {
			PlanillaBaseCotizante cotizante = (PlanillaBaseCotizante) iterator.next();
			if((pagina instanceof PlanillaApvPaginaDetalle)) {
				PlanillaApvCotizante pagosCotizante= (PlanillaApvCotizante) cotizante;
				if (pagosCotizante.getDepositoConvenidoCotizante()>0 || pagosCotizante.getAporteCtaIndemCotizante()>0){
					tabla= "PWF6002";
					tabladet= "PWF6102";
				}else{
					tabladet= "PWF6103";
				}
			}
			StringBuffer query= new StringBuffer();
			query.append("UPDATE ");
			query.append( tabla );
			query.append(" SET PWCCFECEM=?, PWCCCOPRO=?, PWCCSUCUR=?, PWCCCDHOL=?, PWCCADIC1=?, PWCCADIC2=?, PWCCADIC3=? ");
			query.append(" WHERE  PWCCRUTEM=? AND PWCCRUTAF=? AND PWCCCONVE=? ");

			logger.finest("Query=" + query.toString());
			if(!setQuery){
				db2.prepareQuery(query.toString());
				setQuery=true;
			}
			
			//Se verifica si registro cabecera del cotizante ya existe
			int periodo= pagina.getCabeceraPlanilla().getPeriodo();
			int rutempresa= pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getNumber();
			int rutcotizante= cotizante.getRutCotizante().getNumber();
			int convenio= pagina.getCabeceraPlanilla().getConvenio();
			int grupoconvenio= pagina.getCabeceraPlanilla().getGrupoConvenio();
			
			db2.setStatement(1, Today.getAAAAMMDD());
			db2.setStatement(2, pagina.getCabeceraPlanilla().getPeriodo());
			db2.setStatement(3, pagina.getCabeceraPlanilla().getDatosSucursal().getCodigo());
			db2.setStatement(4, grupoconvenio);
			db2.setStatement(5, cotizante.getGeneroCotizante());
			db2.setStatement(6, "0");
			db2.setStatement(7, pagina.getCabeceraPlanilla().getDatosSucursal().getRegion());
			db2.setStatement(8, rutempresa);
			db2.setStatement(9, rutcotizante);
			db2.setStatement(10, convenio);
			result= db2.executeUpdate();
			
			//Si no se actualizó registro entonces no existía y se Inserta nuevo registro
			if(result==0){
				query= new StringBuffer();
				query.append("INSERT INTO ");
				query.append( tabla );
				query.append(" (PWCCRUTEM, PWCCRUTAF, PWCCFECEM, PWCCDIGEM, PWCCDIGAF, PWCCAPEAF, PWCCNOMAF, PWCCRAZSO, PWCCDIREC, PWCCURLPE, PWCCFIRMA, PWCCLINAD, PWCCADIC1, PWCCADIC2, PWCCADIC3, PWCCADIC4, PWCCADIC5, PWCCCOPRO, PWCCCONVE, PWCCSUCUR, PWCCCDHOL)");
				query.append(" values( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ");

				logger.finest("Query=" + query.toString());
				db2.prepareQuery(query.toString());
				db2.setStatement(1, rutempresa);
				db2.setStatement(2, rutcotizante);
				db2.setStatement(3, Today.getAAAAMMDD());
				db2.setStatement(4, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getDV());
				db2.setStatement(5, cotizante.getRutCotizante().getDV());
				db2.setStatement(6, cotizante.getApellidosCotizante());
				db2.setStatement(7, cotizante.getNombresCotizante());
				db2.setStatement(8, pagina.getCabeceraPlanilla().getDatosEmpleador().getRazonSocial());
				db2.setStatement(9, pagina.getCabeceraPlanilla().getDatosSucursal().getDireccion());
				db2.setStatement(10, "");
				db2.setStatement(11, "");
				db2.setStatement(12, 0);
				db2.setStatement(13, cotizante.getGeneroCotizante());
//				Nacionalidad {0:Chileno, 1:Extranjero}
				db2.setStatement(14, "0");
				db2.setStatement(15, pagina.getCabeceraPlanilla().getDatosSucursal().getRegion());
				db2.setStatement(16, "");
				db2.setStatement(17, "");
				db2.setStatement(18, pagina.getCabeceraPlanilla().getPeriodo());
				db2.setStatement(19, convenio);
				db2.setStatement(20, pagina.getCabeceraPlanilla().getDatosSucursal().getCodigo());
				db2.setStatement(21, grupoconvenio);
				db2.executeUpdate();
				setQuery=false;
			}
			
			/********** SE INSERTAN LOS DETALLES DE UNA SECCION************/
			if(!setQueryDet){
				StringBuffer querydet= new StringBuffer();
				querydet.append("INSERT INTO ");
				querydet.append( tabladet);
				querydet.append(" (PWDCRUTEM, PWDCRUTAF, PWDCTIPEN, PWDCENTID, PWDCMESRE, PWDCANORE, PWDCDIGEM, PWDCDIGAF, PWDCREMIM, PWDCMONCO, PWDCFECPA, PWDCFOLIO, PWDCSECFO, PWDCCONVE, PWDCCDHOL)");
				querydet.append(" values( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ");

				logger.finest("Query detalle=" + querydet.toString());
				db2det.prepareQuery(querydet.toString());
				setQueryDet=true;
			}
			/**************************Sección AFP*************************/
			if((pagina instanceof PlanillaAfpPaginaDetalle)) {
				PlanillaAfpPaginaDetalle paginadetalle = (PlanillaAfpPaginaDetalle) pagina;
				PlanillaAfpCotizante pagosCotizante= (PlanillaAfpCotizante) cotizante;
				if(pagosCotizante.getCotizacionObligatoriaFdoPensionCotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_AFP_OBL, "", pagosCotizante.getRemuneracionImponibleFdoPensionCotizante(), pagosCotizante.getCotizacionObligatoriaFdoPensionCotizante() );
				}
				if(pagosCotizante.getDepositoCtaAhorroFdoPensionCotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_AFP_CTA_AHO, "", pagosCotizante.getRemuneracionImponibleFdoPensionCotizante(), pagosCotizante.getDepositoCtaAhorroFdoPensionCotizante() );
				}
				if(pagosCotizante.getSeguroInvalidezFdoPensionCotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_AFP_SIS, "", pagosCotizante.getRemuneracionImponibleFdoPensionCotizante(), pagosCotizante.getSeguroInvalidezFdoPensionCotizante() );
				}
				if(pagosCotizante.getCotizacionAfiliadoCesantiaCotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_AFC_TRA, "", pagosCotizante.getRemuneracionImponibleFdoPensionCotizante(), pagosCotizante.getCotizacionAfiliadoCesantiaCotizante() );
				}
				if(pagosCotizante.getCotizacionEmpleadorCesantiaCotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_AFC_EMP, "", pagosCotizante.getRemuneracionImponibleFdoPensionCotizante(), pagosCotizante.getCotizacionEmpleadorCesantiaCotizante() );
				}
				if(getTipoNomina().equals("R")){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_DIAS, "", pagosCotizante.getRemuneracionImponibleFdoPensionCotizante(), pagosCotizante.getDiasTrabajados() );
				}
				//Movimientos de Personal
				int codigomovper= pagosCotizante.getCodigoMovimientoPersonalCotizante();
				if(codigomovper>0){
					AbsoluteDate fechaInicio= pagosCotizante.getFechaInicioMovimientoPersonalCotizante();
					AbsoluteDate fechaTermino= pagosCotizante.getFechaTerminoMovimientoPersonalCotizante();
					String fecini="0";
					String fecter="0";
					if(fechaInicio !=null){
						fecini= fechaInicio.getPeriod() + Formato.padding(fechaInicio.getDay(), 2);
					}
					if(fechaTermino !=null){
						fecter= fechaTermino.getPeriod() + Formato.padding(fechaTermino.getDay(), 2);
					}
					
					if(!isExistMP(tablaMP, rutempresa, rutcotizante, convenio, grupoconvenio, periodo, codigomovper, Integer.parseInt(fecini), Integer.parseInt(fecter))){
						int nummp= selectMax(tablaMP, rutempresa, rutcotizante, periodo, convenio, codigomovper ) + 1;
						insertDetalleMovPer(paginadetalle, pagosCotizante, pagosCotizante.getCodigoMovimientoPersonalCotizante(), Integer.parseInt(fecini) , Integer.parseInt(fecter), "", nummp );
					}
				}
				/**************************Sección ISAPRE*************************/
			}else if((pagina instanceof PlanillaIsaprePaginaDetalle)) {
				PlanillaIsaprePaginaDetalle paginadetalle = (PlanillaIsaprePaginaDetalle) pagina;
				PlanillaIsapreCotizante pagosCotizante= (PlanillaIsapreCotizante) cotizante;
				if(pagosCotizante.getCotizacion7Porciento()+ pagosCotizante.getCotizacionAdicionalVoluntaria()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_ISA_OBL, "", pagosCotizante.getRemuneracionImponible(), pagosCotizante.getCotizacion7Porciento()+ pagosCotizante.getCotizacionAdicionalVoluntaria() );
				}
				if(pagosCotizante.getCotizacionAdicionalVoluntaria()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_ISA_ADI, "", pagosCotizante.getRemuneracionImponible(), pagosCotizante.getCotizacionAdicionalVoluntaria() );
				}
				if(getTipoNomina().equals("R")){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_DIAS, "", pagosCotizante.getRemuneracionImponible(), pagosCotizante.getDiasTrabajados() );
				}
				//Movimientos de Personal
				int codigomovper= pagosCotizante.getCodigoMovimientoPersonal();
				if(codigomovper>0){
					AbsoluteDate fechaInicio= pagosCotizante.getFechaInicioMovimientoPersonal();
					AbsoluteDate fechaTermino= pagosCotizante.getFechaTerminoMovimientoPersonal();
					String fecini="0";
					String fecter="0";
					if(fechaInicio !=null){
						fecini= fechaInicio.getPeriod() + Formato.padding(fechaInicio.getDay(), 2);
					}
					if(fechaTermino !=null){
						fecter= fechaTermino.getPeriod() + Formato.padding(fechaTermino.getDay(), 2);
					}
					if(!isExistMP(tablaMP, rutempresa, rutcotizante, convenio, grupoconvenio, periodo, codigomovper, Integer.parseInt(fecini), Integer.parseInt(fecter))){
						int nummp= selectMax(tablaMP, rutempresa, rutcotizante, periodo, convenio, codigomovper ) + 1;
						insertDetalleMovPer(paginadetalle, pagosCotizante, pagosCotizante.getCodigoMovimientoPersonal(), Integer.parseInt(fecini) , Integer.parseInt(fecter), "", nummp );
					}
				}
			/**************************Sección MUTUAL*************************/
			}else if((pagina instanceof PlanillaMutualPaginaDetalle)) {
				PlanillaMutualPaginaDetalle paginadetalle = (PlanillaMutualPaginaDetalle) pagina;
				PlanillaMutualCotizante pagosCotizante= (PlanillaMutualCotizante) cotizante;
				if(pagosCotizante.getMontoCotizacion()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_MUT, "", pagosCotizante.getRemuneracionImponible(), pagosCotizante.getMontoCotizacion() );
				}
				if(getTipoNomina().equals("R")){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_DIAS, "", pagosCotizante.getRemuneracionImponible(), pagosCotizante.getDiasTrabajados() );
				}
			/**************************Sección APV y DC***********************/
			}else if((pagina instanceof PlanillaApvPaginaDetalle)) {
				PlanillaApvPaginaDetalle paginadetalle = (PlanillaApvPaginaDetalle) pagina;
				PlanillaApvCotizante pagosCotizante= (PlanillaApvCotizante) cotizante;
				if(pagosCotizante.getCotizacionVoluntariaFdoPensionCotizante()>0){
					int numapv= selectMax(tablaAPV, rutempresa, rutcotizante, periodo, convenio, 0 ) + 1;
					insertDetalle(paginadetalle, pagosCotizante, String.valueOf(numapv), "", pagosCotizante.getRemuneracionImponibleFdoPensionCotizante(), pagosCotizante.getCotizacionVoluntariaFdoPensionCotizante() );
				}
				if(pagosCotizante.getAporteTrabajadorFdoPensionColectivoCotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_APVC_TRA, "", pagosCotizante.getRemuneracionImponibleFdoPensionCotizante(), pagosCotizante.getAporteTrabajadorFdoPensionColectivoCotizante() );
				}
				if(pagosCotizante.getAporteEmpleadorFdoPensionColectivoCotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_APVC_EMP, "", pagosCotizante.getRemuneracionImponibleFdoPensionCotizante(), pagosCotizante.getAporteEmpleadorFdoPensionColectivoCotizante() );
				}
				if(pagosCotizante.getDepositoConvenidoCotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_DEP_CONV, "", pagosCotizante.getRemuneracionImponibleFdoPensionCotizante(), pagosCotizante.getDepositoConvenidoCotizante() );
				}
				if(pagosCotizante.getAporteCtaIndemCotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_DEP_CONV_APO_IND, "", pagosCotizante.getRemuneracionImponibleFdoPensionCotizante(), pagosCotizante.getAporteCtaIndemCotizante() );
				}
			/**************************Sección CCAF*************************/
			}else if((pagina instanceof PlanillaCcafPaginaDetalle)) {
				PlanillaCcafPaginaDetalle paginadetalle = (PlanillaCcafPaginaDetalle) pagina;
				PlanillaCcafCotizante pagosCotizante= (PlanillaCcafCotizante) cotizante;
				if(pagosCotizante.getMontoAsigFamiliarCotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_CAJA_AFA, "", pagosCotizante.getRemuneracionImponibleCotizante(), pagosCotizante.getMontoAsigFamiliarCotizante() );
				}
				if(pagosCotizante.getMonto06Cotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_CAJA_06, "", pagosCotizante.getRemuneracionImponibleCotizante(), pagosCotizante.getMonto06Cotizante() );
				}
				if(pagosCotizante.getMontoCuotaCredito()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_CAJA_CRE, "", pagosCotizante.getRemuneracionImponibleCotizante(), pagosCotizante.getMontoCuotaCredito() );
				}
				if(pagosCotizante.getMontoCuotaLeasing()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_CAJA_LEA, "", pagosCotizante.getRemuneracionImponibleCotizante(), pagosCotizante.getMontoCuotaLeasing() );
				}
				if(pagosCotizante.getMontoCuotaSeguroVida()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_CAJA_VID, "", pagosCotizante.getRemuneracionImponibleCotizante(), pagosCotizante.getMontoCuotaSeguroVida() );
				}
				if(pagosCotizante.getMontoCuotaConvenioDental()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_CAJA_DEN, "", pagosCotizante.getRemuneracionImponibleCotizante(), pagosCotizante.getMontoCuotaConvenioDental() );
				}
				if(pagosCotizante.getMontoAsigFamiliarRetroactivoCotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_CAJA_RET, "", pagosCotizante.getRemuneracionImponibleCotizante(), pagosCotizante.getMontoAsigFamiliarRetroactivoCotizante() );
				}
				if(pagosCotizante.getMontoAsigFamiliarReintegroCotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_CAJA_REI, "", pagosCotizante.getRemuneracionImponibleCotizante(), pagosCotizante.getMontoAsigFamiliarReintegroCotizante() );
				}
				if(getTipoNomina().equals("R")){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_DIAS, "", pagosCotizante.getRemuneracionImponibleCotizante(), pagosCotizante.getDiasTrabajados() );
				}
				//Movimientos de Personal
				int codigomovper= pagosCotizante.getCodigoMovimiento();
				if(codigomovper>0){
					AbsoluteDate fechaInicio= pagosCotizante.getFechaDesdeAsigFamiliarCotizante();
					AbsoluteDate fechaTermino= pagosCotizante.getFechaHastaAsigFamiliarCotizante();
					String fecini="0";
					String fecter="0";
					if(fechaInicio !=null){
						fecini= fechaInicio.getPeriod() + Formato.padding(fechaInicio.getDay(), 2);
					}
					if(fechaTermino !=null){
						fecter= fechaTermino.getPeriod() + Formato.padding(fechaTermino.getDay(), 2);
					}
					if(!isExistMP(tablaMP, rutempresa, rutcotizante, convenio, grupoconvenio, periodo, codigomovper, Integer.parseInt(fecini), Integer.parseInt(fecter))){
						int nummp= selectMax(tablaMP, rutempresa, rutcotizante, periodo, convenio, codigomovper ) + 1;
						insertDetalleMovPer(paginadetalle, pagosCotizante, pagosCotizante.getCodigoMovimiento(), Integer.parseInt(fecini) , Integer.parseInt(fecter), "", nummp );
					}
				}
			/**************************Sección INP*************************/
			}else if((pagina instanceof PlanillaInpPaginaDetalle)) {
				PlanillaInpPaginaDetalle paginadetalle = (PlanillaInpPaginaDetalle) pagina;
				PlanillaInpCotizante pagosCotizante= (PlanillaInpCotizante) cotizante;
				if(pagosCotizante.getPensionInpCotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_IPS, Constants.TIPO_ENTIDAD_DESC_IPSPEN, pagosCotizante.getRemImponibleCotizante(), pagosCotizante.getPensionInpCotizante() );
				}
				if(pagosCotizante.getFonasaCotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_FON, Constants.TIPO_ENTIDAD_DESC_IPSFON, pagosCotizante.getRemImponibleCotizante(), pagosCotizante.getFonasaCotizante() );
				}
				int asfam= pagosCotizante.getMontoAsigFamiliarCotizante() + pagosCotizante.getMontoAsigFamiliarRetroactivoCotizante() - pagosCotizante.getMontoAsigFamiliarReintegrosCotizante();
				if(asfam>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_CAJA_AFA, Constants.TIPO_ENTIDAD_DESC_IPSCAJA, pagosCotizante.getRemImponibleCotizante(), asfam);
				}
				if(pagosCotizante.getAccDelTrabajoCotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_MUT, Constants.TIPO_ENTIDAD_DESC_IPSMUT, pagosCotizante.getRemImponibleCotizante(), pagosCotizante.getAccDelTrabajoCotizante() );
				}
				if(pagosCotizante.getCotizacionDesahucioCotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_IPS_DES, Constants.TIPO_ENTIDAD_DESC_IPSDES, pagosCotizante.getRemImponibleCotizante(), pagosCotizante.getCotizacionDesahucioCotizante() );
				}
				if(pagosCotizante.getMontoBonificacionLey15385Cotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_IPS_REB, Constants.TIPO_ENTIDAD_DESC_IPSREB, pagosCotizante.getRemImponibleCotizante(), pagosCotizante.getMontoBonificacionLey15385Cotizante() );
				}
				if(getTipoNomina().equals("R")){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_DIAS, "", pagosCotizante.getRemImponibleCotizante(), pagosCotizante.getDiasTrabajados() );
				}
				/**************************Sección AFBR*************************/
			}else if((pagina instanceof PlanillaAfbrPaginaDetalle)) {
				PlanillaAfbrPaginaDetalle paginadetalle = (PlanillaAfbrPaginaDetalle) pagina;
				PlanillaAfbrCotizante pagosCotizante= (PlanillaAfbrCotizante) cotizante;
				if(pagosCotizante.getAporteAFBR()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_AFBR, "", pagosCotizante.getRemuneracionImponible(), pagosCotizante.getAporteAFBR());
				}
				
			}
			else if((pagina instanceof PlanillaTpPaginaDetalle)) {
				PlanillaTpPaginaDetalle paginadetalle = (PlanillaTpPaginaDetalle) pagina;
				PlanillaTpCotizante pagosCotizante= (PlanillaTpCotizante) cotizante;
				if(pagosCotizante.getCotizacionTrabajoPesadoCotizante()>0){
					insertDetalle(paginadetalle, pagosCotizante, Constants.TIPO_ENTIDAD_AFP_TP, "", pagosCotizante.getRemuneracionImponibleFdoPensionCotizante(), pagosCotizante.getCotizacionTrabajoPesadoCotizante() );
				}
			}
		}
		
		
		return result;
	}
	
	private int insertDetalle(PlanillaBasePaginaDetalle pagina, PlanillaBaseCotizante cotizante, String tipoEntidad, String descConcepto, int imponible, int monto) throws SQLException {
		String periodo= String.valueOf(pagina.getCabeceraPlanilla().getPeriodo());
		//for (Iterator iterator = pagina.getCotizantes().iterator(); iterator.hasNext();) {
			//PlanillaAfpCotizante cotizante = (PlanillaAfpCotizante) iterator.next();
			
			db2det.setStatement(1, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getNumber());
			db2det.setStatement(2, cotizante.getRutCotizante().getNumber());
			db2det.setStatement(3, tipoEntidad);
			if(tipoEntidad.equals(Constants.TIPO_ENTIDAD_DIAS)){
				db2det.setStatement(4, "");
				db2det.setStatement(12, "");
				db2det.setStatement(13, "");
			}else{
				String nombreINP= pagina.getCabeceraPlanilla().getNombreEntidad();
				if(nombreINP.trim().equals("IPS")){
					nombreINP="INP";
				}
				db2det.setStatement(4, nombreINP + descConcepto);
				db2det.setStatement(12, pagina.getCabeceraPlanilla().getFolio());
				db2det.setStatement(13, pagina.getCabeceraPlanilla().getSecuenciaFolio());
			}
			db2det.setStatement(5, periodo.substring(4));
			db2det.setStatement(6, Integer.parseInt(periodo.substring(0, 4)));
			db2det.setStatement(7, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getDV());
			db2det.setStatement(8, cotizante.getRutCotizante().getDV());
			db2det.setStatement(9, imponible);
			db2det.setStatement(10, monto);
			db2det.setStatement(11, pagina.getCabeceraPlanilla().getFechaPago());
			db2det.setStatement(14, pagina.getCabeceraPlanilla().getConvenio());
			db2det.setStatement(15, pagina.getCabeceraPlanilla().getGrupoConvenio());
			try {
				db2det.executeUpdate();
			} catch (SQLException e) {
				if(!tipoEntidad.equals(Constants.TIPO_ENTIDAD_DIAS)){
					e.printStackTrace();
				}
			}
		//}
		
		return 0;
	}
	
	public int insertDetalleMovPer(PlanillaBasePaginaDetalle pagina, PlanillaBaseCotizante cotizante, int codMovimiento, int fechaInicio, int fechaTermino, String entidadRecaudadora, int correlativo) throws SQLException {
		String tabla="";
		String periodo= String.valueOf(pagina.getCabeceraPlanilla().getPeriodo());
		//for (Iterator iterator = pagina.getCotizantes().iterator(); iterator.hasNext();) {
		//PlanillaAfpCotizante cotizante = (PlanillaAfpCotizante) iterator.next();
		StringBuffer query= new StringBuffer();
		tabla= "PWF6104";
		int rutempresa= pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getNumber();
		int rutcotizante= cotizante.getRutCotizante().getNumber();
		int convenio= pagina.getCabeceraPlanilla().getConvenio();
		int grupoconvenio= pagina.getCabeceraPlanilla().getGrupoConvenio();
		query.append("INSERT INTO ");
		query.append(tabla);
		query.append(" (PWDCRUTEM, PWDCRUTAF, PWDCMESRE, PWDCANORE, PWDCCORR, PWDCMOVPE, PWDCFECINI, PWDCFECTER, PWDCDIGEM, PWDCDIGAF, PWDCFOLIO, PWDCSECFO, PWDCENTID, PWDCCONVE, PWDCCDHOL)");
		query.append(" values( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,? ) ");
		//logger.finest("Query=" + query.toString());
		db2det.prepareQuery(query.toString());
		db2det.setStatement(1, rutempresa);
		db2det.setStatement(2, rutcotizante);
		db2det.setStatement(3, periodo.substring(4));
		db2det.setStatement(4, Integer.parseInt(periodo.substring(0, 4)));
		db2det.setStatement(5, correlativo); //Correlativo
		db2det.setStatement(6, codMovimiento);
		db2det.setStatement(7, fechaInicio);
		db2det.setStatement(8, fechaTermino);
		db2det.setStatement(9, pagina.getCabeceraPlanilla().getDatosEmpleador().getRutEmpresa().getDV());
		db2det.setStatement(10, cotizante.getRutCotizante().getDV());
		db2det.setStatement(11, pagina.getCabeceraPlanilla().getFolio());
		db2det.setStatement(12, pagina.getCabeceraPlanilla().getSecuenciaFolio());
		db2det.setStatement(13, entidadRecaudadora);
		//db2.setStatement(14, ""); //AFP // se comentó campo ya que por omisión queda null (como debe ser).
		db2det.setStatement(14, convenio);
		db2det.setStatement(15, grupoconvenio);
		
		try {
			db2det.executeUpdate();
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		//}

		return 0;
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#select(java.lang.Object)
	 */
	public int selectMax(String tabla, int rutEmpresa, int rutCotizante, int periodo, int convenio, int codigomovper ) throws SQLException {
		int maxapv=0;
		String perstr= String.valueOf(periodo);
		
		StringBuffer query= new StringBuffer();
		if(tabla.equalsIgnoreCase("pwf6104")){
			query.append("select max(pwdccorr) ");
		}else{
			query.append("select max(pwdctipen) ");
		}
		query.append("from  " + tabla);
		query.append(" where pwdcrutem= ? ");
		query.append(" and pwdcrutaf= ? ");
		query.append(" and pwdcconve= ? ");
		query.append(" and pwdcanore= ? ");
		query.append(" and pwdcmesre= ? ");
		if(tabla.equalsIgnoreCase("pwf6104")){
			query.append(" and pwdcmovpe= ? ");
		}
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, rutEmpresa);
		db2.setStatement(2, rutCotizante);
		db2.setStatement(3, convenio);
		db2.setStatement(4, perstr.substring(0, 4));
		db2.setStatement(5, perstr.substring(4));
		
		if(tabla.equalsIgnoreCase("pwf6104")){
			db2.setStatement(6, codigomovper);
		}
		//Se ejecuta la query
		db2.executeQuery();
		if (db2.next()) {
			//Se rescatan los datos de los cotizantes
			maxapv= db2.getInt(1);
		}
		db2.closeStatement();
		return maxapv;
		 
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#isExist(java.lang.Object)
	 */
	/*	public boolean isExist(String tabla, int rutEmpresa, int rutCotizante, int convenio, int codholding ) throws SQLException {
		int count=0;
		StringBuffer query= new StringBuffer();
		query.append("select count(*) ");
		query.append("from  " + getEsquema() + "." + tabla);
		query.append(" where pwccrutem= ? ");
		query.append(" and pwccrutaf= ? ");
		query.append(" and pwccconve= ? ");
		query.append(" and pwcccdhol= ? ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, rutEmpresa);
		db2.setStatement(2, rutCotizante);
		db2.setStatement(3, convenio);
		db2.setStatement(4, codholding);
		
		//Se ejecuta la query
		db2.executeQuery();
		if (db2.next()) {
			//Se rescatan los datos de los cotizantes
			count= db2.getInt(1);
		}
		if(count== 0){
			return false;
		}
		return true;
		 
	}
	*/
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#isExist(java.lang.Object)
	 */
	public boolean isExistMP(String tabla, int rutEmpresa, int rutCotizante, int convenio, int codholding, int periodo, int codmov, int fechaini, int fechafin ) throws SQLException {
		int count=0;
		String perstr= String.valueOf(periodo);
		StringBuffer query= new StringBuffer();
		query.append("select count(*) ");
		query.append("from  " + tabla);
		query.append(" where pwdcrutem= ? ");
		query.append(" and pwdcrutaf= ? ");
		query.append(" and pwdcconve= ? ");
		query.append(" and pwdccdhol= ? ");
		query.append(" and pwdcanore = ? ");
		query.append(" and pwdcmesre= ? ");
		query.append(" and pwdcmovpe= ? ");
		query.append(" and pwdcfecini= ? ");
		query.append(" and pwdcfecter= ? ");
		
		logger.finest("Query=" + query.toString());
		db2.prepareQuery(query.toString());
		db2.setStatement(1, rutEmpresa);
		db2.setStatement(2, rutCotizante);
		db2.setStatement(3, convenio);
		db2.setStatement(4, codholding);
		db2.setStatement(5, perstr.substring(0, 4));
		db2.setStatement(6, perstr.substring(4));
		db2.setStatement(7, codmov);
		db2.setStatement(8, fechaini);
		db2.setStatement(9, fechafin);
		
		//Se ejecuta la query
		db2.executeQuery();
		if (db2.next()) {
			//Se rescatan los datos de los cotizantes
			count= db2.getInt(1);
		}
		db2.closeStatement();
		if(count== 0){
			return false;
		}
		return true;
		 
	}
	
	/* (sin Javadoc)
	 * @see cl.araucana.planillascp.dao.DAO_Interface#update(java.lang.Object)
	 */
	public int update(Object obj) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	/**
	 * @return el tipoNomina
	 */
	public String getTipoNomina() {
		return tipoNomina;
	}

	/**
	 * @param tipoNomina el tipoNomina a establecer
	 */
	public void setTipoNomina(String tipoNomina) {
		this.tipoNomina = tipoNomina;
	}

	public Object select(Object pk) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	public void close(){
		pubDAO.closeConnectionDAO();
	 }
	public void commit() throws SQLException{
		pubDAO.commit();
	 }
	public void rollback() throws SQLException{
		pubDAO.rollback();
	 }
}


