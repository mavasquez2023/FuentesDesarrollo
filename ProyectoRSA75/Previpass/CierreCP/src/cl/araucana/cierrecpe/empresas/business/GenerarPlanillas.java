

/*
 * @(#) GenerarPlanillas.java    1.0 21-07-2009
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.cierrecpe.empresas.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Logger;

import lotus.domino.*;

import cl.araucana.cierrecp.empresas.comprobantes.Comprobante_Detalle;
import cl.araucana.cierrecp.empresas.comprobantes.Comprobante_Encabezado;
import cl.araucana.cierrecp.empresas.comprobantes.Comprobante_Totales;
import cl.araucana.cierrecpe.business.Constants;
import cl.araucana.cierrecpe.dao.CPDAO;
import cl.araucana.cierrecpe.dao.PubDAO;
import cl.araucana.cierrecpe.dao.TesoDAO;
import cl.araucana.cierrecpe.empresas.dao.CertificadoTrabajadoresDAO;
import cl.araucana.cierrecpe.empresas.dao.ComprobantesCPDAO;
import cl.araucana.cierrecpe.empresas.dao.ComprobantesPlanillasDAO;
import cl.araucana.cierrecpe.empresas.dao.CotizantesAFBRDAO;
import cl.araucana.cierrecpe.empresas.dao.CotizantesAFPDAO;
import cl.araucana.cierrecpe.empresas.dao.CotizantesAFPTPDAO;
import cl.araucana.cierrecpe.empresas.dao.CotizantesAFPVDAO;
import cl.araucana.cierrecpe.empresas.dao.CotizantesAPVCDAO;
import cl.araucana.cierrecpe.empresas.dao.CotizantesAPVDAO;
import cl.araucana.cierrecpe.empresas.dao.CotizantesCcafDAO;
import cl.araucana.cierrecpe.empresas.dao.CotizantesINPDAO;
import cl.araucana.cierrecpe.empresas.dao.CotizantesIsapreDAO;
import cl.araucana.cierrecpe.empresas.dao.CotizantesMutualDAO;
import cl.araucana.cierrecpe.empresas.dao.PlanillaAFBRDAO;
import cl.araucana.cierrecpe.empresas.dao.PlanillaAFPDAO;
import cl.araucana.cierrecpe.empresas.dao.PlanillaAPVCDAO;
import cl.araucana.cierrecpe.empresas.dao.PlanillaAPVDAO;
import cl.araucana.cierrecpe.empresas.dao.PlanillaCcafDAO;
import cl.araucana.cierrecpe.empresas.dao.PlanillaDNPAFPDAO;
import cl.araucana.cierrecpe.empresas.dao.PlanillaDNPINPDAO;
import cl.araucana.cierrecpe.empresas.dao.PlanillaINPDAO;
import cl.araucana.cierrecpe.empresas.dao.PlanillaINPDYNPDAO;
import cl.araucana.cierrecpe.empresas.dao.PlanillaIsapreDAO;
import cl.araucana.cierrecpe.empresas.dao.PlanillaMutualDAO;
import cl.araucana.cierrecpe.empresas.dao.PlanillaTPDAO;
import cl.araucana.cierrecpe.empresas.dao.ResumenCierreCPDAO;
import cl.araucana.cierrecpe.empresas.planillas.IdentificacionSucursal;
import cl.araucana.cierrecpe.empresas.planillas.afp.PlanillaAfpCotizante;
import cl.araucana.cierrecpe.empresas.planillas.afp.PlanillaAfpDocumentModel;
import cl.araucana.cierrecpe.empresas.planillas.afp.PlanillaAfpPaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.apv.PlanillaApvCotizante;
import cl.araucana.cierrecpe.empresas.planillas.apv.PlanillaApvDocumentModel;
import cl.araucana.cierrecpe.empresas.planillas.apv.PlanillaApvPaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.ccaf.PlanillaCcafCotizante;
import cl.araucana.cierrecpe.empresas.planillas.ccaf.PlanillaCcafDocumentModel;
import cl.araucana.cierrecpe.empresas.planillas.ccaf.PlanillaCcafPaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.inp.PlanillaInpCotizante;
import cl.araucana.cierrecpe.empresas.planillas.inp.PlanillaInpDocumentModel;
import cl.araucana.cierrecpe.empresas.planillas.inp.PlanillaInpPaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.isapre.PlanillaIsapreCotizante;
import cl.araucana.cierrecpe.empresas.planillas.isapre.PlanillaIsapreDocumentModel;
import cl.araucana.cierrecpe.empresas.planillas.isapre.PlanillaIsaprePaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.mutual.PlanillaMutualCotizante;
import cl.araucana.cierrecpe.empresas.planillas.mutual.PlanillaMutualDocumentModel;
import cl.araucana.cierrecpe.empresas.planillas.mutual.PlanillaMutualPaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.otros.PlanillaAfbrCotizante;
import cl.araucana.cierrecpe.empresas.planillas.otros.PlanillaAfbrDocumentModel;
import cl.araucana.cierrecpe.empresas.planillas.otros.PlanillaAfbrPaginaDetalle;
import cl.araucana.cierrecpe.empresas.planillas.tp.PlanillaTpCotizante;
import cl.araucana.cierrecpe.empresas.planillas.tp.PlanillaTpDocumentModel;
import cl.araucana.cierrecpe.empresas.planillas.tp.PlanillaTpPaginaDetalle;
import cl.araucana.cierrecpe.empresas.to.DetalleSeccionxSucursalTO;
import cl.araucana.cierrecpe.empresas.to.FiltroCotizantesTO;
import cl.araucana.cierrecpe.entidades.dao.EntidadesCCAFDAO;
import cl.araucana.cierrecpe.entidades.dao.FoliacionCPDAO;
import cl.araucana.cierrecpe.entidades.to.EntidadCCAFTO;
import cl.araucana.cierrecpe.entidades.to.FoliosEntidadTO;
import cl.araucana.core.util.AbsoluteDate;
import cl.araucana.core.util.PropertiesLoader;
import cl.araucana.core.util.logging.LogManager;
import cl.recursos.EnviarMail;
import cl.recursos.Formato;

/*
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
 *            <TD> 21-07-2009 </TD>
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
public class GenerarPlanillas implements Constants{
	static ResourceBundle mailProperties = ResourceBundle.getBundle("etc/mail");
	private CPDAO cpDAO=null;
	private PubDAO pubDAO=null;
	private TesoDAO tesoDAO=null;
	private static Logger logger = LogManager.getLogger();
	private String mensajeError="";
	private String ipAS400, usuarioAS400, passwordAS400, esquemaOrigen, esquemaFormato, esquemaDNPINP, esquemaFormatoPeriodo, esquemaFormatoDNP;
	//private String esquemaCentralizacion;
	private String ipDomino, usuarioDomino, passwordDomino, vistaDomino;
	protected Properties properties;
	private int numeroTotalComprobantes=0, numeroComprobantesConPlanillas=0;
	private String primerCierre; 
	//private boolean existeTP=false, existeDNP=false;
	
	public GenerarPlanillas() throws SQLException{
		cpDAO= new CPDAO();
		//cpDAO.setAutoCommit(false);
		pubDAO= new PubDAO();
		pubDAO.setAutoCommit(false);
		tesoDAO= new TesoDAO();
		tesoDAO.setAutoCommit(false);
		
		loadProperties("/etc/pwf.properties");
		this.ipAS400= properties.getProperty("IPPublicador");
		this.usuarioAS400= properties.getProperty("UsuarioAS400");
		this.passwordAS400= properties.getProperty("PasswordAS400");
		this.esquemaOrigen= properties.getProperty("EsquemaACopiar");
		this.esquemaFormato= properties.getProperty("BibliotecaCierre");
		this.esquemaFormatoPeriodo= properties.getProperty("BibliotecaPeriodo");
		this.esquemaDNPINP= properties.getProperty("BibliotecaDNPINP");
		this.esquemaFormatoDNP= properties.getProperty("BibliotecaDNPAFP");
		//this.esquemaCentralizacion= properties.getProperty("BibliotecaCentralizacion");
		this.primerCierre= properties.getProperty("PrimerCierre");
		
		loadProperties("/etc/domino.properties");
		this.ipDomino= properties.getProperty("IPDomino");
		this.usuarioDomino= properties.getProperty("UsuarioDomino");
		this.passwordDomino= properties.getProperty("PasswordDomino");
		this.vistaDomino= properties.getProperty("VistaEntidadesDomino");
		
/*		
		Context ctx = new InitialContext();
		//rescatando parámetros conexión AS400
		this.ipAS400= (String) ctx.lookup("java:comp/env/IPPublicador");
		this.usuarioAS400= (String) ctx.lookup("java:comp/env/UsuarioAS400");
		this.passwordAS400= (String) ctx.lookup("java:comp/env/PasswordAS400");
		this.esquemaOrigen= (String) ctx.lookup("java:comp/env/EsquemaACopiar");
		this.esquemaFormato= (String) ctx.lookup("java:comp/env/BibliotecaCierre");
*/
	}
	
	public boolean sincronizaFoliosDomino(String estado){
		try {
			logger.fine("Abriendo sesión Notes ip:" + ipDomino + ", usuario: " + usuarioDomino);
			Session s = NotesFactory.createSession(ipDomino, usuarioDomino, passwordDomino);
			logger.fine("Abriendo DB Cotiza/Cotiza.nsf:");
			Database db	= 	s.getDatabase(s.getServerName(), "Cotiza/Cotiza.nsf");
			logger.fine("Abriendo Conexión a DB2");
			FoliacionCPDAO foliacion= new FoliacionCPDAO(cpDAO.getConnection(), db);
			//se sincroniza folios desde Domino hacia CP
			if(estado.equals("inicio")){
				logger.fine("Sincronizando folios desde Domino a DB2, vista Domino:" + vistaDomino);
				List listFoliacion= (List)foliacion.selectDomino(vistaDomino);
				for (Iterator entidad = listFoliacion.iterator(); entidad.hasNext();) {
					FoliosEntidadTO foliosTO = (FoliosEntidadTO) entidad.next();
					foliacion.update(foliosTO);
				}
				cpDAO.commit();
			//Se sincroniza folios desde CP hacia Domino una vez terminado generación de planillas.
			}else{
				logger.fine("Sincronizando folios desde DB2 a Domino");
				List listFoliacion= (List)foliacion.select(null);
				for (Iterator entidad = listFoliacion.iterator(); entidad.hasNext();) {
					FoliosEntidadTO foliosTO = (FoliosEntidadTO) entidad.next();
					foliacion.updateDomino(foliosTO, vistaDomino);
				}
			}
			db.recycle();
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			return false;
		}
		finally{
			
		}
		return true;
	}
	
	public boolean principal(int periodo, int cierre, String fechaPago, String centralizacion, String emails){
		DuplicarEsquemaPW esquemaPW=null;
		boolean estado= false;
		try{

			//Si se generaron planillas se respaldan en base de acuerdo al formato definido
			String esquemaDestino= nombreEsquema(esquemaFormato, periodo, cierre);
			//String esquemaDestinoTP= nombreEsquemaComplementario(esquemaFormatoTP, periodo, cierre);
			String esquemaDestinoPeriodo= nombreEsquemaComplementario(esquemaFormatoPeriodo, periodo, cierre);
			String esquemaDestinoDNP= nombreEsquemaComplementario(esquemaFormatoDNP, periodo, cierre);
			//se instancia clase que gestiona biblioteca de publicación
			esquemaPW= new DuplicarEsquemaPW(ipAS400, usuarioAS400, passwordAS400);
			//se limpia el esquema origen para almacenar las planillas
			if(esquemaPW.clearLib(esquemaOrigen)){
				//Se copia estructura vacía del esquema origen hacia esquema destino
				if(esquemaPW.copyLib(esquemaOrigen, esquemaDestino)){
					//esquemaPW.copyLib(esquemaOrigen, esquemaDestinoTP);
					esquemaPW.copyLib(esquemaOrigen, esquemaDestinoDNP);
					//Se elimina generar biblioteca de Periodo cierre999, se hará como una funcionalida aparte que incluya Domino
					/*if (cierre==Integer.parseInt(primerCierre)){
						esquemaPW.copyLib(esquemaOrigen, esquemaDestinoPeriodo);
					}*/
					//Se generan y almacenan las planillas en esquema origen.
					if (generarPlanillas(periodo, cierre, fechaPago, centralizacion, esquemaOrigen, esquemaDNPINP)>0){
						//Se copian los archivos poblados en esquema origen hacia destino
						esquemaPW.copyFiles(esquemaOrigen, esquemaDestino, "ADD", "CP");
						//Se copian además a cierre999
						//esquemaPW.copyFiles(esquemaOrigen, esquemaDestinoPeriodo, "ADD", "CP");
						
						//si existen datos generados en DYNP(PWF2001) se copian  sino se boora esquema
						if(esquemaPW.existsData(esquemaOrigen, "PWF2001") || esquemaPW.existsData(esquemaOrigen, "PWF4701")){
							esquemaPW.copyFiles(esquemaOrigen, esquemaDestinoDNP, "ADD", "DNP");
							enviarMail(Formato.split(emails, ";"), "Generar Planillas (PWF), periodo:" + periodo + ", cierre:" + cierre);
						}else{
							esquemaPW.dltLib(esquemaDestinoDNP);
						}
						estado= true;
					}
				}
			}
		}catch (Exception e) {
			logger.severe("Error, mensaje= " + e.getMessage());
			e.printStackTrace();
			estado= false;
		}
		finally{
			if (esquemaPW!= null){
				esquemaPW.close();
			}
		}
		return estado;
	}
	
	public String nombreEsquema(String esquemaFormato, int periodo, int cierre){
		String esquemaDestino= esquemaFormato.replaceAll("AAMM", String.valueOf(periodo).substring(2, 6));
		esquemaDestino= esquemaDestino.replaceAll("XXX", Formato.padding(cierre, 3));
		return esquemaDestino;
	}
	public String nombreEsquemaComplementario(String esquemaFormato, int periodo, int cierre){
		String esquemaDestino= esquemaFormato.replaceAll("AAMM", String.valueOf(periodo).substring(2, 6));
		esquemaDestino= esquemaDestino.replaceAll("XX", Formato.padding(cierre, 2));
		return esquemaDestino;
	}
	
	public int generarPlanillas(int periodo, int cierre, String fechaPago, String centralizacion, String esquemaDestino, String esquemaDNPINP){
		boolean termino=true;
		Collection comprobantes= null;
		FolioEntidades folios=null;
		SucursalesEmpresa sucursales=null;
		ComprobantesCPDAO comprobantesDAO=null;
		ComprobantesPlanillasDAO comprobantesPlanillasDAO=null;
		ResumenCierreCPDAO resumenDAO=null;
		CertificadoTrabajadoresDAO certificadoDAO=null;
		try {
			//se abre una conexión a la BD 
			logger.info("Iniciando Generación Planillas cierre:" + cierre);
			comprobantesDAO= new ComprobantesCPDAO(cpDAO.getConnection());
			resumenDAO= new ResumenCierreCPDAO(cpDAO.getConnection());
			EntidadesCCAFDAO ccafDAO= new EntidadesCCAFDAO(cpDAO.getConnection());
			comprobantesPlanillasDAO= new ComprobantesPlanillasDAO(pubDAO.getConnection(), esquemaDestino);
			//se buscan los comprobantes pagados asociados al cierre
			comprobantes= (List) comprobantesDAO.select(String.valueOf(cierre));
			logger.info("Nro comprobantes cursados asociados al cierre= " + comprobantes.size());
			
			//Se setea la cantidad de comprobantes a procesar
			this.numeroTotalComprobantes= comprobantes.size();
			
			//se reservan folios asociado a cada entidad involucrada en el cierre
			if (this.numeroTotalComprobantes>0){
				folios= new FolioEntidades(cierre);
			}
			//Se carga la información de Compensación para Cajas
			Map listaCCAF= (Map)ccafDAO.select(null);
			
			//Se itera sobre los comprobantes para generar las planillas por c/u
			//Sino se puede generar planillas para un comprobante se continúa con siguiente comprobante.
			int numeroComprobantesErroneos=0;
			for (Iterator listacomp = comprobantes.iterator(); listacomp.hasNext();) {
				int estadoproc=0;
				//Cada comprobante solo contiene la información general o de cabecera
				Comprobante_Encabezado comprobante = (Comprobante_Encabezado) listacomp.next();
				
				//Comienza la transacción en tabla Publicación de Planillas
				try{
					comprobante.setPeriodo(periodo);
					comprobante.setFechaPublicacion(new AbsoluteDate(fechaPago));
					logger.fine("Generando planillas comprobante: " + comprobante.getId_codigo_barra());
					
					//Se genera objeto para almcenar las Sucursales de la empresa
					sucursales= new SucursalesEmpresa(comprobante.getDatosEmpleador().getRutEmpresa());
					
					//Se inserta la cabecera del Comprobante y los totales x sección en Tablas de Publicación
					IdentificacionSucursal sucursalmatriz= sucursales.getSucursal(comprobante.getIdCasaMatriz());
					if(centralizacion.equalsIgnoreCase("del")){
						comprobantesPlanillasDAO.delete(comprobante);
					}
					comprobantesPlanillasDAO.insert(comprobante, sucursalmatriz);
					for (Iterator totales = comprobante.getTotales().iterator(); totales.hasNext();) {
						Comprobante_Totales tot_comprobante = (Comprobante_Totales) totales.next();
						comprobantesPlanillasDAO.insertTotalesxSeccion(tot_comprobante);
					}
					
					//Se inicializa DAO de CertificadoTrabajadores
					if(!centralizacion.equalsIgnoreCase("none")){
						certificadoDAO= new CertificadoTrabajadoresDAO(pubDAO.getConnection());
						if(centralizacion.equalsIgnoreCase("del")){
							certificadoDAO.delete(comprobante);
						}
					}
					//Se rescata del comprobante las entidades involucradas a generar planillas
					boolean isGeneradaPlanillaAFP_Ind= false;
					for (Iterator entidades = comprobante.getEntidades().iterator(); entidades.hasNext();) {
						//Se genera lista para acumular las planillas por Sucursal asociadas a la entidad
						List planillasSucursales= new ArrayList();
						Comprobante_Detalle det_comprobante = (Comprobante_Detalle) entidades.next();
						
						//Se inserta el detalle del Comprobante en Tablas de Publicación
						if(det_comprobante.getTipoDeclaracionPago()== SECCION_PAGADA){
							comprobantesPlanillasDAO.insertDetalle(det_comprobante);
						}
						
						//Se genera filtro para buscar los cotizantes
						FiltroCotizantesTO filtroTO= new FiltroCotizantesTO();
						filtroTO.setTipo_detalle(det_comprobante.getTipo_detalle());
						filtroTO.setRutEmpresa(comprobante.getDatosEmpleador().getRutEmpresa());
						filtroTO.setConvenio(comprobante.getConvenio());
						filtroTO.setTipoProceso(comprobante.getTipoProceso());
						filtroTO.setTipo_cliente(comprobante.getTipoCliente());
						
						Collection cotizantes=null;
						Collection totalesxSucursal=null;
						//se busca el tipo de sección a la cual pertenece el pago  para generar la planilla respectiva a la entidad
						int tipo_seccion= det_comprobante.getTipo_seccion();
						filtroTO.setTipo_seccion(tipo_seccion);
						logger.fine("Procesando SECCIÓN= " + tipo_seccion + ", ENTIDAD= " + det_comprobante.getTipo_detalle());
						switch (tipo_seccion) {
						case REMU_AFP:
						case GRATI_AFP:
						case RELIQ_AFP:{
							logger.finer("Generando planilla AFP");
							//se abre conexióna BD para rescatar los datos del detalle (cotizantes) de la planilla
							CotizantesAFPDAO cotizantesDAO= new CotizantesAFPDAO(cpDAO.getConnection());
							//Se busca las Sucursales a generar Planillas y los subtotales x Sucursal
							totalesxSucursal= cotizantesDAO.selectTotalxSucursal(filtroTO);
							logger.finer("Número de sucursales asociada:"  + totalesxSucursal.size());
							for (Iterator iter = totalesxSucursal.iterator(); iter.hasNext();) {
								DetalleSeccionxSucursalTO detallesucTO = (DetalleSeccionxSucursalTO) iter.next();
								filtroTO.setId_sucursal(detallesucTO.getId_sucursal());
								//Se rescatan los datos de la sucursal
								IdentificacionSucursal sucursal= sucursales.getSucursal(detallesucTO.getId_sucursal());
								if(sucursal==null){
									logger.severe("No existe sucursal con código:" + detallesucTO.getId_sucursal() + " definida en Cotizantes.");
									throw new Exception();
								}
								logger.finer("Generando planilla sucursal:" + sucursal.getCodigo());
								PlanillaAfpDocumentModel planillaCabecera=cabeceraPlanillaAFP(comprobante, det_comprobante, detallesucTO, sucursal, folios);
								cotizantes= (List) cotizantesDAO.select(filtroTO);
								if(filtroTO.getTipoProceso().equals("G")){
									PlanillaAfpCotizante unacotizacion= (PlanillaAfpCotizante)cotizantes.iterator().next();
									if(!unacotizacion.getFechaInicioGratificacionesCotizante().toString().equals("")){
										planillaCabecera.setFechaInicioGrati(unacotizacion.getFechaInicioGratificacionesCotizante().getPeriod()*100 + unacotizacion.getFechaInicioGratificacionesCotizante().getDay());
									}
									if(!unacotizacion.getFechaTerminoGratificacionesCotizante().toString().equals("")){
										planillaCabecera.setFechaTerminoGrati(unacotizacion.getFechaTerminoGratificacionesCotizante().getPeriod()*100 + unacotizacion.getFechaTerminoGratificacionesCotizante().getDay());
									}
								}
								logger.finer("Número cotizantes segun filtro=" + cotizantes.size());
								//Se dividen los cotizantes por sucursal definidos por constante NUMLINEPAGE
								Collection paginasDetalle= splitDetallePlanillaAFP( cotizantes, planillaCabecera);
								logger.finer("Número de Páginas de Detalle generadas:" + paginasDetalle.size());
								planillaCabecera.setPaginasDetalle(paginasDetalle);
								planillasSucursales.add(planillaCabecera);
							}
							
							if(planillasSucursales.size()>0){
								//Se invoca DAO para insertar planillas AFP
								PlanillaAFPDAO planillaDAO=null;
								PlanillaDNPAFPDAO planillaDNPDAO=null;
								if(det_comprobante.getTipoDeclaracionPago()== SECCION_PAGADA){
									planillaDAO= new PlanillaAFPDAO(pubDAO.getConnection(), esquemaDestino);
								}else{
									planillaDNPDAO= new PlanillaDNPAFPDAO(pubDAO.getConnection(), esquemaDestino);
								}
								//Se itera sobre las planillas x Sucursal generadas para insertar en DB2
								logger.finer("Total planillas AFP generadas:" + planillasSucursales.size());
								for (Iterator iter = planillasSucursales.iterator(); iter.hasNext();) {
									PlanillaAfpDocumentModel planillaSucursal = (PlanillaAfpDocumentModel) iter.next();
									logger.finer("Se inserta planilla asociada a sucursal:" + planillaSucursal.getDatosSucursal().getCodigo() + ", folio=" + planillaSucursal.getFolio());
									if(planillaSucursal.getFilter()!=FILTER_AFP_DNP){
										planillaDAO.insert(planillaSucursal);
										//Si es independiente y lee AFP significa que cargará monto de Fonasa si corresponde
										isGeneradaPlanillaAFP_Ind= true;
										for (Iterator itercert = planillaSucursal.getPaginasDetalle().iterator(); itercert.hasNext();) {
											PlanillaAfpPaginaDetalle paginadet = (PlanillaAfpPaginaDetalle) itercert.next();
											if(centralizacion.equalsIgnoreCase("add") || centralizacion.equalsIgnoreCase("del")){
												certificadoDAO.insert(paginadet);
											}
											
										}
									}else{
										planillaDNPDAO.insert(planillaSucursal);
									}
								}
							}else{
								logger.warning("No se encontraron cotizantes para entidad:" + filtroTO.getTipo_detalle() + ", empresa:" + filtroTO.getRutEmpresa().getNumber() + ", convenio:" + filtroTO.getConvenio());
								throw new Exception();
							}
						}
						//Verificando si declata Trabajos Pesados
						if ((tipo_seccion == REMU_AFP && det_comprobante.getM9()>0) || (tipo_seccion != REMU_AFP && det_comprobante.getM5()>0)){
							logger.finer("Generando planillas AFP TP");
							//Se limpita lista para acumular las planillas por Sucursal
							planillasSucursales= new ArrayList();
							//se abre conexióna BD para rescatar los datos del detalle (cotizantes) de la planilla
							CotizantesAFPTPDAO cotizantesDAO= new CotizantesAFPTPDAO(cpDAO.getConnection());
							//Se busca las Sucursales a generar Planillas y los subtotales x Sucursal
							totalesxSucursal= cotizantesDAO.selectTotalxSucursal(filtroTO);
							logger.finer("Número de sucursales asociada:"  + totalesxSucursal.size());
							for (Iterator iter = totalesxSucursal.iterator(); iter.hasNext();) {
								DetalleSeccionxSucursalTO detallesucTO = (DetalleSeccionxSucursalTO) iter.next();
								filtroTO.setId_sucursal(detallesucTO.getId_sucursal());
								//Se rescatan los datos de la sucursal
								IdentificacionSucursal sucursal= sucursales.getSucursal(detallesucTO.getId_sucursal());
								if(sucursal==null){
									logger.severe("No existe sucursal con código:" + detallesucTO.getId_sucursal() + " definida en Cotizantes.");
									throw new Exception();
								}
								logger.finer("Generando planilla sucursal:" + sucursal.getCodigo());
								PlanillaTpDocumentModel planillaCabecera=cabeceraPlanillaTP(comprobante, det_comprobante, detallesucTO, sucursal, folios);
								cotizantes= (List) cotizantesDAO.select(filtroTO);
								logger.finer("Nro cotizantes TP segun filtro=" + cotizantes.size());
								if(filtroTO.getTipoProceso().equals("G")){
									PlanillaTpCotizante unacotizacion= (PlanillaTpCotizante)cotizantes.iterator().next();
									if(!unacotizacion.getFechaInicioGratificacionesCotizante().toString().equals("")){
										planillaCabecera.setFechaInicioGrati(unacotizacion.getFechaInicioGratificacionesCotizante().getPeriod()*100 + unacotizacion.getFechaInicioGratificacionesCotizante().getDay());
									}
									if(!unacotizacion.getFechaTerminoGratificacionesCotizante().toString().equals("")){
										planillaCabecera.setFechaTerminoGrati(unacotizacion.getFechaTerminoGratificacionesCotizante().getPeriod()*100 + unacotizacion.getFechaTerminoGratificacionesCotizante().getDay());
									}
								}
								//Se dividen los cotizantes por sucursal definidos por constante NUMLINEPAGE
								Collection paginasDetalle= splitDetallePlanillaTP( cotizantes, planillaCabecera);
								logger.finer("Número de Páginas de Detalle generadas:" + paginasDetalle.size());
								planillaCabecera.setPaginasDetalle(paginasDetalle);
								planillasSucursales.add(planillaCabecera);
							}
							if(planillasSucursales.size()>0){
								//Se invoca DAO para insertar planillas AFP
								PlanillaTPDAO planillaDAO= new PlanillaTPDAO(pubDAO.getConnection(), esquemaDestino);
								//Se itera sobre las planillas x Sucursal generadas para insertar en DB2
								logger.finer("Total planillas TP generadas:" + planillasSucursales.size());
								for (Iterator iter = planillasSucursales.iterator(); iter.hasNext();) {
									PlanillaTpDocumentModel planillaSucursal = (PlanillaTpDocumentModel) iter.next();
									logger.finer("Se inserta planilla asociada a sucursal:" + planillaSucursal.getDatosSucursal().getCodigo() + ", folio=" + planillaSucursal.getFolio());
									planillaDAO.insert(planillaSucursal);
									for (Iterator itercert = planillaSucursal.getPaginasDetalle().iterator(); itercert.hasNext();) {
										PlanillaTpPaginaDetalle paginadet = (PlanillaTpPaginaDetalle) itercert.next();
										if(centralizacion.equalsIgnoreCase("add") || centralizacion.equalsIgnoreCase("del")){
											certificadoDAO.insert(paginadet);
										}
									}
								}
							}else{
								logger.warning("No se encontraron cotizantes para entidad:" + filtroTO.getTipo_detalle() + ", empresa:" + filtroTO.getRutEmpresa().getNumber() + ", convenio:" + filtroTO.getConvenio());
								throw new Exception();
							}
						}
						//Verificando si declara Trabador Voluntario
						if (tipo_seccion == REMU_AFP && det_comprobante.getM10()>0){
							logger.finer("Generando planillas AFPV");
							//Se limpita lista para acumular las planillas por Sucursal
							planillasSucursales= new ArrayList();
							//se abre conexióna BD para rescatar los datos del detalle (cotizantes) de la planilla
							CotizantesAFPVDAO cotizantesDAO= new CotizantesAFPVDAO(cpDAO.getConnection());
							//Se busca las Sucursales a generar Planillas y los subtotales x Sucursal
							totalesxSucursal= cotizantesDAO.selectTotalxSucursal(filtroTO);
							logger.finer("Número de sucursales asociada:"  + totalesxSucursal.size());
							for (Iterator iter = totalesxSucursal.iterator(); iter.hasNext();) {
								DetalleSeccionxSucursalTO detallesucTO = (DetalleSeccionxSucursalTO) iter.next();
								filtroTO.setId_sucursal(detallesucTO.getId_sucursal());
								//Se rescatan los datos de la sucursal
								IdentificacionSucursal sucursal= sucursales.getSucursal(detallesucTO.getId_sucursal());
								if(sucursal==null){
									logger.severe("No existe sucursal con código:" + detallesucTO.getId_sucursal() + " definida en Cotizantes.");
									throw new Exception();
								}
								logger.finer("Generando planilla sucursal:" + sucursal.getCodigo());
								PlanillaAfpDocumentModel planillaCabecera=cabeceraPlanillaAFPV(comprobante, det_comprobante, detallesucTO, sucursal, folios);
								cotizantes= (List) cotizantesDAO.select(filtroTO);
								logger.finer("Nro cotizantes TP segun filtro=" + cotizantes.size());
								//Se dividen los cotizantes por sucursal definidos por constante NUMLINEPAGE
								Collection paginasDetalle= splitDetallePlanillaAFPV( cotizantes, planillaCabecera);
								logger.finer("Número de Páginas de Detalle generadas:" + paginasDetalle.size());
								planillaCabecera.setPaginasDetalle(paginasDetalle);
								planillasSucursales.add(planillaCabecera);
							}
							if(planillasSucursales.size()>0){
								//Se invoca DAO para insertar planillas AFPV
								PlanillaAFPDAO planillaDAO= new PlanillaAFPDAO(pubDAO.getConnection(), esquemaDestino);
								//Se itera sobre las planillas x Sucursal generadas para insertar en DB2
								logger.finer("Total planillas APFV generadas:" + planillasSucursales.size());
								for (Iterator iter = planillasSucursales.iterator(); iter.hasNext();) {
									PlanillaAfpDocumentModel planillaSucursal = (PlanillaAfpDocumentModel) iter.next();
									logger.finer("Se inserta planilla AFPV asociada a sucursal:" + planillaSucursal.getDatosSucursal().getCodigo() + ", folio=" + planillaSucursal.getFolio());
									planillaDAO.insert(planillaSucursal);
									for (Iterator itercert = planillaSucursal.getPaginasDetalle().iterator(); itercert.hasNext();) {
										PlanillaAfpPaginaDetalle paginadet = (PlanillaAfpPaginaDetalle) itercert.next();
										if(centralizacion.equalsIgnoreCase("add") || centralizacion.equalsIgnoreCase("del")){
											certificadoDAO.insert(paginadet);
										}
									}
								}
							}else{
								logger.warning("No se encontraron cotizantes para entidad:" + filtroTO.getTipo_detalle() + ", empresa:" + filtroTO.getRutEmpresa().getNumber() + ", convenio:" + filtroTO.getConvenio());
								throw new Exception();
							}
						}
						break;
						case REMU_ISAPRE:
						case GRATI_ISAPRE:
						case RELIQ_ISAPRE:{
							logger.finer("Generando planilla ISAPRE");
							//se abre conexióna BD para rescatar los datos del detalle (cotizantes) de la planilla
							CotizantesIsapreDAO cotizantesDAO= new CotizantesIsapreDAO(cpDAO.getConnection());
							//Se busca las Sucursales a generar Planillas y los subtotales x Sucursal
							totalesxSucursal= cotizantesDAO.selectTotalxSucursal(filtroTO);
							logger.finer("Número de sucursales asociada:"  + totalesxSucursal.size());
							
							for (Iterator iter = totalesxSucursal.iterator(); iter.hasNext();) {
								DetalleSeccionxSucursalTO detallesucTO = (DetalleSeccionxSucursalTO) iter.next();
								filtroTO.setId_sucursal(detallesucTO.getId_sucursal());
								//Se rescatan los datos de la sucursal
								IdentificacionSucursal sucursal= sucursales.getSucursal(detallesucTO.getId_sucursal());
								if(sucursal==null){
									logger.severe("No existe sucursal con código:" + detallesucTO.getId_sucursal() + " definida en Cotizantes.");
									throw new Exception();
								}
								logger.finer("Generando planilla sucursal:" + sucursal.getCodigo());
								PlanillaIsapreDocumentModel planillaCabecera=cabeceraPlanillaIsapre(comprobante, det_comprobante, detallesucTO, sucursal, folios);
								cotizantes= (List) cotizantesDAO.select(filtroTO);
								logger.finer("Número cotizantes segun filtro=" + cotizantes.size());
								//Se dividen los cotizantes por sucursal definidos por constante NUMLINEPAGE
								Collection paginasDetalle= splitDetallePlanillaIsapre( cotizantes, planillaCabecera);
								logger.finer("Número de Páginas de Detalle generadas:" + paginasDetalle.size());
								planillaCabecera.setPaginasDetalle(paginasDetalle);
								planillasSucursales.add(planillaCabecera);
							}
							if(planillasSucursales.size()>0){
								//Se invoca DAO para insertar planillas Isapre
								PlanillaIsapreDAO planillaDAO= new PlanillaIsapreDAO(pubDAO.getConnection(), esquemaDestino);
								//Se itera sobre las planillas x Sucursal generadas para insertar en DB2
								logger.finer("Total planillas ISAPRE generadas:" + planillasSucursales.size());
								for (Iterator iter = planillasSucursales.iterator(); iter.hasNext();) {
									PlanillaIsapreDocumentModel planillaSucursal = (PlanillaIsapreDocumentModel) iter.next();
									logger.finer("Se inserta planilla asociada a sucursal:" + planillaSucursal.getDatosSucursal().getCodigo() + ", folio=" + planillaSucursal.getFolio());
									planillaDAO.insert(planillaSucursal);
									for (Iterator itercert = planillaSucursal.getPaginasDetalle().iterator(); itercert.hasNext();) {
										PlanillaIsaprePaginaDetalle paginadet = (PlanillaIsaprePaginaDetalle) itercert.next();
										if(centralizacion.equalsIgnoreCase("add") || centralizacion.equalsIgnoreCase("del")){
											certificadoDAO.insert(paginadet);
										}
									}
								}
							}else{
								logger.warning("No se encontraron cotizantes para entidad:" + filtroTO.getTipo_detalle() + ", empresa:" + filtroTO.getRutEmpresa().getNumber() + ", convenio:" + filtroTO.getConvenio());
								throw new Exception();
							}
						}
						break;
						case REMU_IPS:
						case GRATI_IPS:
						case RELIQ_IPS:{
							logger.finer("Generando planilla INP");
							filtroTO.setIdCcaf(comprobante.getIdCcaf());
							filtroTO.setIdMutual(comprobante.getIdMutual());
							//se abre conexióna BD para rescatar los datos del detalle (cotizantes) de la planilla
							CotizantesINPDAO cotizantesDAO= new CotizantesINPDAO(cpDAO.getConnection());
							//Se verifica si se debe generar planillas por sucursal
							if(!comprobante.isGenerarPlanillaxSucursalINP()){
								logger.finer("Generando planilla por Empresa, asociada a Sucursal Casa Matriz");
								//Si planilla se genera por empresa se asigna la Sucursal Matriz en filtroTO
								filtroTO.setId_sucursal(comprobante.getIdCasaMatriz());
								filtroTO.setPlanillaxSucursal(false);
							}
							//Se busca las Sucursales a generar Planillas y los subtotales x Sucursal
							totalesxSucursal= cotizantesDAO.selectTotalxSucursal(filtroTO);
							logger.finer("Número de sucursales asociada:"  + totalesxSucursal.size());
							for (Iterator iter = totalesxSucursal.iterator(); iter.hasNext();) {
								DetalleSeccionxSucursalTO detallesucTO = (DetalleSeccionxSucursalTO) iter.next();
								//Si Trabajador es Independiente y tiene pago en Pension, Accidente o Desahcio se genera planilla
								//ya que si solo paga Fonasa esta ya que fue incorporado en planilla AFP
								if(!comprobante.getTipoCliente().equals(Constants.TIPO_CLIENTE_INDEPENDIENTE) || (detallesucTO.getM2() + detallesucTO.getM4() + detallesucTO.getM5())>0 || (!isGeneradaPlanillaAFP_Ind && detallesucTO.getM3()>0)){
									filtroTO.setId_sucursal(detallesucTO.getId_sucursal());
									//Se rescatan los datos de la sucursal
									IdentificacionSucursal sucursal= sucursales.getSucursal(detallesucTO.getId_sucursal());
									if(sucursal==null){
										logger.severe("No existe sucursal con código:" + detallesucTO.getId_sucursal() + " definida en Cotizantes.");
										throw new Exception();
									}
									logger.finer("Generando planilla sucursal:" + sucursal.getCodigo());
									PlanillaInpDocumentModel planillaCabecera=cabeceraPlanillaINP(comprobante, det_comprobante, detallesucTO, sucursal, folios);
									cotizantes= (List) cotizantesDAO.select(filtroTO);
									if(filtroTO.getTipoProceso().equals("G")){
										PlanillaInpCotizante unacotizacion= (PlanillaInpCotizante)cotizantes.iterator().next();
										if(!unacotizacion.getFechaInicioGratificacionesCotizante().toString().equals("")){
											planillaCabecera.setFechaInicioGrati(unacotizacion.getFechaInicioGratificacionesCotizante().getPeriod()*100 + unacotizacion.getFechaInicioGratificacionesCotizante().getDay());
										}
										if(!unacotizacion.getFechaTerminoGratificacionesCotizante().toString().equals("")){
											planillaCabecera.setFechaTerminoGrati(unacotizacion.getFechaTerminoGratificacionesCotizante().getPeriod()*100 + unacotizacion.getFechaTerminoGratificacionesCotizante().getDay());
										}
									}
									logger.finer("Número cotizantes segun filtro=" + cotizantes.size());
									//Se dividen los cotizantes por sucursal definidos por constante NUMLINEPAGE
									Collection paginasDetalle= splitDetallePlanillaINP( cotizantes, planillaCabecera);
									logger.finer("Número de Páginas de Detalle generadas:" + paginasDetalle.size());
									planillaCabecera.setPaginasDetalle(paginasDetalle);
									planillasSucursales.add(planillaCabecera);
								}
							}
							if(planillasSucursales.size()>0){
								//Se invoca DAO para insertar planillas Inp
								PlanillaINPDAO planillaDAO=null;
								PlanillaINPDYNPDAO planillaDYNPDAO=null;
								PlanillaDNPINPDAO planillaDNPINPDAO=null;
								if(det_comprobante.getTipoDeclaracionPago()== SECCION_PAGADA ){
									planillaDAO= new PlanillaINPDAO(pubDAO.getConnection(), esquemaDestino);
								}else{
									planillaDYNPDAO= new PlanillaINPDYNPDAO(pubDAO.getConnection(), esquemaDestino);
									planillaDNPINPDAO= new PlanillaDNPINPDAO(tesoDAO.getConnection(), esquemaDNPINP);
									//setExisteDNP(true);
								}
								//Se itera sobre las planillas x Sucursal generadas para insertar en DB2
								logger.finer("Total planillas INP generadas:" + planillasSucursales.size());
								for (Iterator iter = planillasSucursales.iterator(); iter.hasNext();) {
									PlanillaInpDocumentModel planillaSucursal = (PlanillaInpDocumentModel) iter.next();
									logger.finer("Se inserta planilla asociada a sucursal:" + planillaSucursal.getDatosSucursal().getCodigo() + ", folio=" + planillaSucursal.getFolio());
									if(planillaSucursal.getFilter()==FILTER_INP_DYP){
										planillaDAO.insert(planillaSucursal);
										for (Iterator itercert = planillaSucursal.getPaginasDetalle().iterator(); itercert.hasNext();) {
											PlanillaInpPaginaDetalle paginadet = (PlanillaInpPaginaDetalle) itercert.next();
											if(centralizacion.equalsIgnoreCase("add") || centralizacion.equalsIgnoreCase("del")){
												certificadoDAO.insert(paginadet);
											}
										}
									}else{
										//Se inserta las planillas en tablas normales para generar archivos de DYNP de INP
										planillaDYNPDAO.insert(planillaSucursal);
										//Se inserta en tablas específicas de DYNP de INP (PWDNPDTA)
										planillaDNPINPDAO.insert(planillaSucursal);
									}
								}
							}else{
								logger.warning("No se encontraron cotizantes para entidad:" + filtroTO.getTipo_detalle() + ", empresa:" + filtroTO.getRutEmpresa().getNumber() + ", convenio:" + filtroTO.getConvenio());
								if(!comprobante.getTipoCliente().equals(Constants.TIPO_CLIENTE_INDEPENDIENTE)){
									throw new Exception();
								}
							}
						}
						break;
						case REMU_MUTUAL:
						case GRATI_MUTUAL:
						case RELIQ_MUTUAL:{
							logger.finer("Generando planilla MUTUAL");
							//se abre conexióna BD para rescatar los datos del detalle (cotizantes) de la planilla
							CotizantesMutualDAO cotizantesDAO= new CotizantesMutualDAO(cpDAO.getConnection());
							//Se verifica si se debe generar planillas por sucursal
							boolean plaxsuc= comprobante.isGenerarPlanillaxSucursalMUTUAL();
							if(!plaxsuc){
								logger.finer("Generando planilla por Empresa, asociada a Sucursal Casa Matriz");
								//Si planilla se genera por empresa se asigna la Sucursal Matriz en filtroTO
								filtroTO.setId_sucursal(comprobante.getIdCasaMatriz());
								filtroTO.setPlanillaxSucursal(false);
							}
							//Se busca las Sucursales a generar Planillas y los subtotales x Sucursal
							totalesxSucursal= cotizantesDAO.selectTotalxSucursal(filtroTO);
							logger.finer("Número de sucursales asociada:"  + totalesxSucursal.size());
							for (Iterator iter = totalesxSucursal.iterator(); iter.hasNext();) {
								DetalleSeccionxSucursalTO detallesucTO = (DetalleSeccionxSucursalTO) iter.next();
								filtroTO.setId_sucursal(detallesucTO.getId_sucursal());
								//Se rescatan los datos de la sucursal
								IdentificacionSucursal sucursal= sucursales.getSucursal(detallesucTO.getId_sucursal());
								if(sucursal==null){
									logger.severe("No existe sucursal con código:" + detallesucTO.getId_sucursal() + " definida en Cotizantes.");
									throw new Exception();
								}
								logger.finer("Generando planilla sucursal:" + sucursal.getCodigo());
								PlanillaMutualDocumentModel planillaCabecera=cabeceraPlanillaMutual(comprobante, det_comprobante, detallesucTO, sucursal, folios);
								cotizantes= (List) cotizantesDAO.select(filtroTO);
								logger.finer("Número cotizantes segun filtro=" + cotizantes.size());
								//Se dividen los cotizantes por sucursal definidos por constante NUMLINEPAGE
								Collection paginasDetalle= splitDetallePlanillaMutual( cotizantes, planillaCabecera);
								logger.finer("Número de Páginas de Detalle generadas:" + paginasDetalle.size());
								planillaCabecera.setPaginasDetalle(paginasDetalle);
								planillasSucursales.add(planillaCabecera);
							}
							if(planillasSucursales.size()>0){
								//Se invoca DAO para insertar planillas Mutual
								PlanillaMutualDAO planillaDAO= new PlanillaMutualDAO(pubDAO.getConnection(), esquemaDestino);
								//Se itera sobre las planillas x Sucursal generadas para insertar en DB2
								logger.finer("Total planillas MUTUAL generadas:" + planillasSucursales.size());
								for (Iterator iter = planillasSucursales.iterator(); iter.hasNext();) {
									PlanillaMutualDocumentModel planillaSucursal = (PlanillaMutualDocumentModel) iter.next();
									logger.finer("Se inserta planilla asociada a sucursal:" + planillaSucursal.getDatosSucursal().getCodigo() + ", folio=" + planillaSucursal.getFolio());
									planillaDAO.insert(planillaSucursal);
									for (Iterator itercert = planillaSucursal.getPaginasDetalle().iterator(); itercert.hasNext();) {
										PlanillaMutualPaginaDetalle paginadet = (PlanillaMutualPaginaDetalle) itercert.next();
										if(centralizacion.equalsIgnoreCase("add") || centralizacion.equalsIgnoreCase("del")){
											certificadoDAO.insert(paginadet);
										}
									}
								}
							}else{
								logger.warning("No se encontraron cotizantes para entidad:" + filtroTO.getTipo_detalle() + ", empresa:" + filtroTO.getRutEmpresa().getNumber() + ", convenio:" + filtroTO.getConvenio());
								throw new Exception();
							}
						}
						break;
						case REMU_CCAF:
						case GRATI_CCAF:
						case RELIQ_CCAF:{
							logger.finer("Generando planilla CCAF");
							//Se rescata información de Compensación para la Caja
							EntidadCCAFTO ccafTO= (EntidadCCAFTO)listaCCAF.get(new Integer(det_comprobante.getTipo_detalle()));
					
							//se abre conexióna BD para rescatar los datos del detalle (cotizantes) de la planilla
							CotizantesCcafDAO cotizantesDAO= new CotizantesCcafDAO(cpDAO.getConnection());
							//Se verifica si se debe generar planillas por sucursal 
							//clillo 3-12-15 , o Caja es Los Andes o Los Heroes
							if(!comprobante.isGenerarPlanillaxSucursalCCAF() || comprobante.getIdCcaf()==1 || comprobante.getIdCcaf()==2){
								logger.finer("Generando planilla por Empresa, asociada a Sucursal Casa Matriz");
								//Si planilla se genera por empresa se asigna la Sucursal Matriz en filtroTO
								filtroTO.setId_sucursal(comprobante.getIdCasaMatriz());
								filtroTO.setPlanillaxSucursal(false);
							}
							//Se busca las Sucursales a generar Planillas y los subtotales x Sucursal
							totalesxSucursal= cotizantesDAO.selectTotalxSucursal(filtroTO);
							logger.finer("Número de sucursales asociada:"  + totalesxSucursal.size());
							for (Iterator iter = totalesxSucursal.iterator(); iter.hasNext();) {
								DetalleSeccionxSucursalTO detallesucTO = (DetalleSeccionxSucursalTO) iter.next();
								filtroTO.setId_sucursal(detallesucTO.getId_sucursal());
								//Se rescatan los datos de la sucursal
								IdentificacionSucursal sucursal= sucursales.getSucursal(detallesucTO.getId_sucursal());
								if(sucursal==null){
									logger.severe("No existe sucursal con código:" + detallesucTO.getId_sucursal() + " definida en Cotizantes.");
									throw new Exception();
								}
								logger.finer("Generando planilla sucursal:" + sucursal.getCodigo());
								int i=0;
								PlanillaCcafDocumentModel planillaCabecera=cabeceraPlanillaCcaf(comprobante, det_comprobante, detallesucTO, sucursal, folios, ccafTO);
								cotizantes= (List) cotizantesDAO.select(filtroTO);
								logger.finer("Número cotizantes segun filtro=" + cotizantes.size());
								if(filtroTO.getTipoProceso().equals("G")){
									PlanillaCcafCotizante unacotizacion= (PlanillaCcafCotizante)cotizantes.iterator().next();
									if(!unacotizacion.getFechaInicioGratificacionesCotizante().toString().equals("")){
										planillaCabecera.setFechaInicioGrati(unacotizacion.getFechaInicioGratificacionesCotizante().getPeriod()*100 + unacotizacion.getFechaInicioGratificacionesCotizante().getDay());
									}
									if(!unacotizacion.getFechaTerminoGratificacionesCotizante().toString().equals("")){
										planillaCabecera.setFechaTerminoGrati(unacotizacion.getFechaTerminoGratificacionesCotizante().getPeriod()*100 + unacotizacion.getFechaTerminoGratificacionesCotizante().getDay());
									}
								}
								//Se dividen los cotizantes por sucursal definidos por constante NUMLINEPAGE
								Collection paginasDetalle= splitDetallePlanillaCcaf( cotizantes, planillaCabecera, det_comprobante);
								logger.finer("Número de Páginas de Detalle generadas:" + paginasDetalle.size());
								planillaCabecera.setPaginasDetalle(paginasDetalle);
								planillasSucursales.add(planillaCabecera);
							}
							if(planillasSucursales.size()>0){
								//Se invoca DAO para insertar planillas CCAF
								PlanillaCcafDAO planillaDAO= new PlanillaCcafDAO(pubDAO.getConnection(), esquemaDestino);
								//Se itera sobre las planillas x Sucursal generadas para insertar en DB2
								logger.finer("Total planillas CCAF generadas:" + planillasSucursales.size());
								for (Iterator iter = planillasSucursales.iterator(); iter.hasNext();) {
									PlanillaCcafDocumentModel planillaSucursal = (PlanillaCcafDocumentModel) iter.next();
									int numeroPaginasDetalle= planillaSucursal.getPaginasDetalle().size();
									logger.finer("Se inserta planilla asociada a sucursal:" + planillaSucursal.getDatosSucursal().getCodigo() + ", folio=" + planillaSucursal.getFolio());
//									Verificando si declata Credito
									if (planillaSucursal.getPaginasDetalleCredito().size()>0){
										logger.fine(">>Generando Detalle Crédito, número páginas:" + planillaSucursal.getPaginasDetalleCredito().size());
										numeroPaginasDetalle+= splitComplementosPlanillaCcaf(planillaSucursal, planillaSucursal.getPaginasDetalleCredito(), CCAF_ATTACHMENT_CREDITO);
									}
									//Verificando si declata Leasing
									if (planillaSucursal.getPaginasDetalleLeasing().size()>0){
										logger.info(">>Generando Detalle Leasing, número páginas:" + planillaSucursal.getPaginasDetalleLeasing().size());
										numeroPaginasDetalle+= splitComplementosPlanillaCcaf(planillaSucursal, planillaSucursal.getPaginasDetalleLeasing(), CCAF_ATTACHMENT_LEASING);
									}
									//Verificando si declata Seguro Vida
									if (planillaSucursal.getPaginasDetalleSeguroVida().size()>0){
										logger.info(">>Generando Seguro Vida, número páginas:" + planillaSucursal.getPaginasDetalleSeguroVida().size());
										numeroPaginasDetalle+= splitComplementosPlanillaCcaf(planillaSucursal, planillaSucursal.getPaginasDetalleSeguroVida(), CCAF_ATTACHMENT_SEGURO_VIDA);
									}
									//Verificando si declata Convenio Dental
									if (planillaSucursal.getPaginasDetalleConvenioDental().size()>0){
										logger.info(">>Generando Convenio Dental, número páginas:" + planillaSucursal.getPaginasDetalleConvenioDental().size());
										numeroPaginasDetalle+= splitComplementosPlanillaCcaf(planillaSucursal, planillaSucursal.getPaginasDetalleConvenioDental(), CCAF_ATTACHMENT_CONVENIO_DENTAL);
									}
									planillaDAO.insert(planillaSucursal);
									for (Iterator itercert = planillaSucursal.getPaginasDetalle().iterator(); itercert.hasNext();) {
										PlanillaCcafPaginaDetalle paginadet = (PlanillaCcafPaginaDetalle) itercert.next();
										if(centralizacion.equalsIgnoreCase("add") || centralizacion.equalsIgnoreCase("del")){
											certificadoDAO.insert(paginadet);
										}
									}
								}
							}else{
								logger.warning("No se encontraron cotizantes para entidad:" + filtroTO.getTipo_detalle() + ", empresa:" + filtroTO.getRutEmpresa().getNumber() + ", convenio:" + filtroTO.getConvenio());
								throw new Exception();
							}
						}
						break;
						case REMU_APV:
						case DEP_CONV_AFP:{
							logger.finer("Generando planilla APV / Dep. Convenidos");
							//se abre conexióna BD para rescatar los datos del detalle (cotizantes) de la planilla
							CotizantesAPVDAO cotizantesDAO= new CotizantesAPVDAO(cpDAO.getConnection());
							//Se busca las Sucursales a generar Planillas y los subtotales x Sucursal
							totalesxSucursal= cotizantesDAO.selectTotalxSucursal(filtroTO);
							logger.finer("Número de sucursales asociada:"  + totalesxSucursal.size());
							for (Iterator iter = totalesxSucursal.iterator(); iter.hasNext();) {
								DetalleSeccionxSucursalTO detallesucTO = (DetalleSeccionxSucursalTO) iter.next();
								filtroTO.setId_sucursal(detallesucTO.getId_sucursal());
								//Se rescatan los datos de la sucursal
								IdentificacionSucursal sucursal= sucursales.getSucursal(detallesucTO.getId_sucursal());
								if(sucursal==null){
									logger.severe("No existe sucursal con código:" + detallesucTO.getId_sucursal() + " definida en Cotizantes.");
									throw new Exception();
								}
								logger.finer("Generando planilla sucursal:" + sucursal.getCodigo());
								PlanillaApvDocumentModel planillaCabecera=cabeceraPlanillaAPV(comprobante, det_comprobante, detallesucTO, sucursal, folios);
								cotizantes= (List) cotizantesDAO.select(filtroTO);
								logger.finer("Número cotizantes segun filtro=" + cotizantes.size());
								//Se dividen los cotizantes por sucursal definidos por constante NUMLINEPAGE
								Collection paginasDetalle= splitDetallePlanillaAPV( cotizantes, planillaCabecera);
								logger.finer("Número de Páginas de Detalle generadas:" + paginasDetalle.size());
								planillaCabecera.setPaginasDetalle(paginasDetalle);
								planillasSucursales.add(planillaCabecera);
							}
							if(planillasSucursales.size()>0){
								//Se invoca DAO para insertar planillas Apv
								PlanillaAPVDAO planillaDAO= new PlanillaAPVDAO(pubDAO.getConnection(), esquemaDestino);
								//Se itera sobre las planillas x Sucursal generadas para insertar en DB2
								logger.finer("Total planillas APV generadas:" + planillasSucursales.size());
								for (Iterator iter = planillasSucursales.iterator(); iter.hasNext();) {
									PlanillaApvDocumentModel planillaSucursal = (PlanillaApvDocumentModel) iter.next();
									logger.finer("Se inserta planilla asociada a sucursal:" + planillaSucursal.getDatosSucursal().getCodigo() + ", folio=" + planillaSucursal.getFolio());
									planillaDAO.insert(planillaSucursal, filtroTO.getTipo_seccion());
									for (Iterator itercert = planillaSucursal.getPaginasDetalle().iterator(); itercert.hasNext();) {
										PlanillaApvPaginaDetalle paginadet = (PlanillaApvPaginaDetalle) itercert.next();
										if(centralizacion.equalsIgnoreCase("add") || centralizacion.equalsIgnoreCase("del")){
											certificadoDAO.insert(paginadet, filtroTO.getTipo_seccion());
										}
									}
								}
							}else{
								logger.warning("No se encontraron cotizantes para entidad:" + filtroTO.getTipo_detalle() + ", empresa:" + filtroTO.getRutEmpresa().getNumber() + ", convenio:" + filtroTO.getConvenio());
								throw new Exception();
							}
						}
						break;
						case REMU_APVC:{
							logger.finer("Generando planilla APVC");
							//se abre conexióna BD para rescatar los datos del detalle (cotizantes) de la planilla
							CotizantesAPVCDAO cotizantesDAO= new CotizantesAPVCDAO(cpDAO.getConnection());
							//Se busca las Sucursales a generar Planillas y los subtotales x Sucursal
							totalesxSucursal= cotizantesDAO.selectTotalxSucursal(filtroTO);
							logger.finer("Número de sucursales asociada:"  + totalesxSucursal.size());
							for (Iterator iter = totalesxSucursal.iterator(); iter.hasNext();) {
								DetalleSeccionxSucursalTO detallesucTO = (DetalleSeccionxSucursalTO) iter.next();
								filtroTO.setId_sucursal(detallesucTO.getId_sucursal());
								//Se rescatan los datos de la sucursal
								IdentificacionSucursal sucursal= sucursales.getSucursal(detallesucTO.getId_sucursal());
								if(sucursal==null){
									logger.severe("No existe sucursal con código:" + detallesucTO.getId_sucursal() + " definida en Cotizantes.");
									throw new Exception();
								}
								logger.finer("Generando planilla sucursal:" + sucursal.getCodigo());
								PlanillaApvDocumentModel planillaCabecera=cabeceraPlanillaAPV(comprobante, det_comprobante, detallesucTO, sucursal, folios);
								cotizantes= (List) cotizantesDAO.select(filtroTO);
								logger.finer("Número cotizantes segun filtro=" + cotizantes.size());
								//Se dividen los cotizantes por sucursal definidos por constante NUMLINEPAGE
								Collection paginasDetalle= splitDetallePlanillaAPV(cotizantes, planillaCabecera);
								logger.finer("Número de Páginas de Detalle generadas:" + paginasDetalle.size());
								planillaCabecera.setPaginasDetalle(paginasDetalle);
								planillasSucursales.add(planillaCabecera);
							}
							
							if(planillasSucursales.size()>0){
								//Se invoca DAO para insertar planillas APVC
								PlanillaAPVCDAO planillaDAO= new PlanillaAPVCDAO(pubDAO.getConnection(), esquemaDestino);
								//Se itera sobre las planillas x Sucursal generadas para insertar en DB2
								logger.finer("Total planillas APVC generadas:" + planillasSucursales.size());
								for (Iterator iter = planillasSucursales.iterator(); iter.hasNext();) {
									PlanillaApvDocumentModel planillaSucursal = (PlanillaApvDocumentModel) iter.next();
									logger.finer("Se inserta planilla asociada a sucursal:" + planillaSucursal.getDatosSucursal().getCodigo() + ", folio=" + planillaSucursal.getFolio());
									planillaDAO.insert(planillaSucursal);
									for (Iterator itercert = planillaSucursal.getPaginasDetalle().iterator(); itercert.hasNext();) {
										PlanillaApvPaginaDetalle paginadet = (PlanillaApvPaginaDetalle) itercert.next();
										if(centralizacion.equalsIgnoreCase("add") || centralizacion.equalsIgnoreCase("del")){
											certificadoDAO.insert(paginadet);
										}
									}
								}
							}else{
								logger.warning("No se encontraron cotizantes para entidad:" + filtroTO.getTipo_detalle() + ", empresa:" + filtroTO.getRutEmpresa().getNumber() + ", convenio:" + filtroTO.getConvenio());
								throw new Exception();
							}
						}
						break;
						case REMU_AFBR:
						case RELIQ_AFBR:{
							logger.finer("Generando planilla AFBR");
							//se abre conexióna BD para rescatar los datos del detalle (cotizantes) de la planilla
							CotizantesAFBRDAO cotizantesDAO= new CotizantesAFBRDAO(cpDAO.getConnection());
							//Se busca las Sucursales a generar Planillas y los subtotales x Sucursal
							totalesxSucursal= cotizantesDAO.selectTotalxSucursal(filtroTO);
							logger.finer("Número de sucursales asociada:"  + totalesxSucursal.size());
							
							for (Iterator iter = totalesxSucursal.iterator(); iter.hasNext();) {
								DetalleSeccionxSucursalTO detallesucTO = (DetalleSeccionxSucursalTO) iter.next();
								filtroTO.setId_sucursal(detallesucTO.getId_sucursal());
								//Se rescatan los datos de la sucursal
								IdentificacionSucursal sucursal= sucursales.getSucursal(detallesucTO.getId_sucursal());
								if(sucursal==null){
									logger.severe("No existe sucursal con código:" + detallesucTO.getId_sucursal() + " definida en Cotizantes.");
									throw new Exception();
								}
								logger.finer("Generando planilla sucursal:" + sucursal.getCodigo());
								PlanillaAfbrDocumentModel planillaCabecera=cabeceraPlanillaAFBR(comprobante, det_comprobante, detallesucTO, sucursal, folios);
								cotizantes= (List) cotizantesDAO.select(filtroTO);
								logger.finer("Número cotizantes segun filtro=" + cotizantes.size());
								//Se dividen los cotizantes por sucursal definidos por constante NUMLINEPAGE
								Collection paginasDetalle= splitDetallePlanillaAFBR( cotizantes, planillaCabecera);
								logger.finer("Número de Páginas de Detalle generadas:" + paginasDetalle.size());
								planillaCabecera.setPaginasDetalle(paginasDetalle);
								planillasSucursales.add(planillaCabecera);
							}
							if(planillasSucursales.size()>0){
								//Se invoca DAO para insertar planillas AFBR
								PlanillaAFBRDAO planillaDAO= new PlanillaAFBRDAO(pubDAO.getConnection(), esquemaDestino);
								//Se itera sobre las planillas x Sucursal generadas para insertar en DB2
								logger.finer("Total planillas AFBR generadas:" + planillasSucursales.size());
								for (Iterator iter = planillasSucursales.iterator(); iter.hasNext();) {
									PlanillaAfbrDocumentModel planillaSucursal = (PlanillaAfbrDocumentModel) iter.next();
									logger.finer("Se inserta planilla asociada a sucursal:" + planillaSucursal.getDatosSucursal().getCodigo() + ", folio=" + planillaSucursal.getFolio());
									planillaDAO.insert(planillaSucursal);
									for (Iterator itercert = planillaSucursal.getPaginasDetalle().iterator(); itercert.hasNext();) {
										PlanillaAfbrPaginaDetalle paginadet = (PlanillaAfbrPaginaDetalle) itercert.next();
										if(centralizacion.equalsIgnoreCase("add") || centralizacion.equalsIgnoreCase("del")){
											certificadoDAO.insert(paginadet);
										}
									}
								}
							}else{
								logger.warning("No se encontraron cotizantes para entidad:" + filtroTO.getTipo_detalle() + ", empresa:" + filtroTO.getRutEmpresa().getNumber() + ", convenio:" + filtroTO.getConvenio());
								throw new Exception();
							}
						}
						break;
						}//fin switch
						
					}
					isGeneradaPlanillaAFP_Ind= false;
					//se cierra conexión al DAO para las Sucursales.
					sucursales.close();
					//se concreta planillas de comprobante
					logger.info("Commit planillas comprobante:" + comprobante.getId_codigo_barra());
					pubDAO.commit();
					if(certificadoDAO!=null){
						certificadoDAO.commit();
					}
					tesoDAO.commit();
					estadoproc=1;
					this.numeroComprobantesConPlanillas++;
				} catch (Exception e) {
					logger.warning("GenerarPlanillas.generarPlanillas, ERROR al generar Planillas comprobante Codigo Barra:" + comprobante.getId_codigo_barra() + ", mensaje: " + e.getMessage());
					numeroComprobantesErroneos++;
					e.printStackTrace();
					//se cierra conexión al DAO para las Sucursales.
					//int i=0;
					sucursales.close();
					pubDAO.rollback();
					if(certificadoDAO!=null){
						certificadoDAO.rollback();
					}
					tesoDAO.rollback();
					e.printStackTrace();
				}
				//Actualizanzo campo cierre de comprobante finalizado.
				logger.finer("Se actualiza campo resumen cierre de comprobante");
				if(centralizacion.equalsIgnoreCase("add") || centralizacion.equalsIgnoreCase("del")){
					resumenDAO.updateCentralizacion(comprobante.getFolioTesoreria(), estadoproc);
					logger.fine("Actualizanzo campo 'tgr' de resumen cierre, campo Nuevo Convenio Caducado, por defecto va -1");
					resumenDAO.updateTGR(comprobante.getFolioTesoreria(), estadoproc, -1);
				}
				resumenDAO.updatePlanilla(comprobante.getFolioTesoreria(), estadoproc);
				//Si el X% está con problemas se aborta proceso
				if((numeroComprobantesErroneos/comprobantes.size())*100 > PORCENTAJE_MAX_COMPROBANTES_SIN_PLANILLAS){
					logger.warning("GenerarPlanillas.generarPlanillas, PORCENTAJE_MAX_COMPROBANTES_SIN_PLANILLAS, se aborta proceso!!");
					termino= false;
					break;
				}
			} //next comprobante
			//se eliminan las Propuestas de Caja con pago 0
			List cajasActivas= comprobantesDAO.buscarCajasCierre(cierre);
			comprobantesDAO.deleteCajasPagoCero(cajasActivas, periodo, cierre);
			
		} catch (Exception e) {
			e.printStackTrace();
			termino= false;
			this.mensajeError= e.getMessage();
		}
		finally{
			
			//Se devuelven folios reservados no utilizados
			if (comprobantes!=null && comprobantes.size()>0){
				logger.info("Devolviendo folios no utilizados");
				folios.reciclar();
				//Se cierra conexión a DS. 
				folios.close();
			}
			if(certificadoDAO!=null){
				certificadoDAO.close();
			}
			//cerrando conexión de comprobantes detalle y sucursales Empresa;
			logger.info("Fin Generación planillas, cerrando conexiones.");
			comprobantesDAO.close();
		}
		return numeroComprobantesConPlanillas;
	}
	
	 public void close(){
		cpDAO.closeConnectionDAO();
		pubDAO.closeConnectionDAO();
		tesoDAO.closeConnectionDAO();
	 }
	
	/*
	 * AFP
	 */
	public PlanillaAfpDocumentModel cabeceraPlanillaAFP(Comprobante_Encabezado comprobante, Comprobante_Detalle det_comprobante, DetalleSeccionxSucursalTO det_comp_sucursal, IdentificacionSucursal sucursal, FolioEntidades folios){
		//Se genera una nueva instancia de planilla de AFP
		PlanillaAfpDocumentModel planillaAFP=null;
		try {
			planillaAFP = new PlanillaAfpDocumentModel(comprobante);
			if(comprobante.getTipoCliente().equals(TIPO_CLIENTE_INDEPENDIENTE)){
				planillaAFP.setFilter(FILTER_INDEPENDIENTE_OBL);
			}else{
				if (det_comprobante.getTipoDeclaracionPago()== SECCION_PAGADA){
					planillaAFP.setFilter(FILTER_AFP_DYP);
				}else{
					planillaAFP.setFilter(FILTER_AFP_DNP);
				}
			}
			//logger.info("GenerarPlanillas.cabeceraPlanillaAFP, filter= " + planillaAFP.getFilter());
			planillaAFP.setTipoPago(PAGO_NORMAL);
	
			//Se solicita folio para la entidad asociada
			int folio= folios.getFolio(det_comprobante.getRutEntidad());
			//logger.finer("GenerarPlanillas.cabeceraPlanillaAFP folio a asignar planilla AFP:" + folio);
			planillaAFP.setFolio(Formato.padding(folio, 7));
			planillaAFP.setNombreEntidad(folios.getNombreEntidad(det_comprobante.getRutEntidad()));
			
			//Se almacenan los datos de la cabecera de la planilla
			planillaAFP.setDatosSucursal(sucursal);
			planillaAFP.setSecuenciaFolio("000");
			int numeroHojasAnexas= (int)(Math.ceil((double)det_comp_sucursal.getN_trabajadores()/NUMLINEPAGE));
			//logger.finer("GenerarPlanillas.cabeceraPlanillaAFP, numeroHojasAnexas=" + numeroHojasAnexas);
			planillaAFP.setNumeroHojasAnexas(numeroHojasAnexas);
			
			//Se registran montos totales asociados a la sucursal 
			planillaAFP.setNumeroAfiliadosInformados(det_comp_sucursal.getN_trabajadores());
			planillaAFP.setTotalRemuneracionesOGratifFdoPensiones(det_comp_sucursal.getM1());
			planillaAFP.setCotizacionObligFdoPensiones(det_comp_sucursal.getM2());
			planillaAFP.setDepositosCtaAhorroFdoPensiones(det_comp_sucursal.getM3());
			planillaAFP.setSeguroInvalidezFdoPensiones(det_comp_sucursal.getM12());
			planillaAFP.setSubtotalAPagarFdoPensiones(det_comp_sucursal.getM4()+ det_comp_sucursal.getM12());
			planillaAFP.setTotalAPagarFdoPensiones(det_comp_sucursal.getM4() + det_comp_sucursal.getM12());
			planillaAFP.setTotalRemuneracionesOGratifFdoCesantia(det_comp_sucursal.getM5());
			planillaAFP.setCotizacionAfiliadoFdoCesantia(det_comp_sucursal.getM6());
			planillaAFP.setCotizacionEmpleadorFdoCesantia(det_comp_sucursal.getM7());
			planillaAFP.setSubtotalAPagarFdoCesantia(det_comp_sucursal.getM8());
			planillaAFP.setTotalAPagarFdoCesantia(det_comp_sucursal.getM8());
			//Para el caso de Independientes
			planillaAFP.setCotizacionSalud(det_comp_sucursal.getM13());
			planillaAFP.setTotalAPagarAFPSalud(det_comp_sucursal.getM13());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return planillaAFP;
	}
	
	public Collection splitDetallePlanillaAFP(Collection cotizantes, PlanillaAfpDocumentModel cabecera){
		PlanillaAfpPaginaDetalle pagina_detalle=null;
		List detalles= new ArrayList();
		
		//Se inicializa la secuencia del folio y número de línea de detalle
		int secuenciaFolio=1;
		int numeroLineaDetalle=0;
		int numeroAfiliadosFdoPensiones=0;
		int numeroAfiliadosFdoCesantia=0;
		
		//Se itera sobre la lista de cotizantes
		for (Iterator lista_cotiz = cotizantes.iterator(); lista_cotiz.hasNext();) {
			PlanillaAfpCotizante cotizante = (PlanillaAfpCotizante) lista_cotiz.next();
			numeroLineaDetalle++;

			//Si se completa una página se resetea numero linea detalle para gatillar nueva página
			if (numeroLineaDetalle>NUMLINEPAGE){
				secuenciaFolio++;
				numeroLineaDetalle=1;
			}
			if(numeroLineaDetalle==1){
				//Se genera y almacena nueva página detalle que acumulará los cotizantes asociados
				pagina_detalle= new PlanillaAfpPaginaDetalle();
				detalles.add(pagina_detalle);
				//se asocia a la página detalle la cabecera de la planilla
				pagina_detalle.setCabeceraPlanilla(cabecera);
				//Se asigna a la nueva página la secuencia del folio y el número de página
				pagina_detalle.setSecuenciaFolio(Formato.padding(secuenciaFolio, 3));
				pagina_detalle.setPaginaActual(secuenciaFolio);
				pagina_detalle.setPaginaFinal(cabecera.getNumeroHojasAnexas());
			}
			cotizante.setNumeroLineaDetalle(numeroLineaDetalle);
			//Se acumula Número de Afiliados Fondo de Pensiones si tiene Cotización Obligatoria
			if (cotizante.getCotizacionObligatoriaFdoPensionCotizante()>0){
				numeroAfiliadosFdoPensiones++;
			}
			//Se acumula Número de Afiliados del Fondo de Cesantía si tiene cesantía en alguno de los conceptos.
			if(cotizante.getCotizacionAfiliadoCesantiaCotizante() + cotizante.getCotizacionEmpleadorCesantiaCotizante()>0){
				numeroAfiliadosFdoCesantia++;
			}
			//Se agrega el cotizante a la página de detalle
			pagina_detalle.addCotizante(cotizante);
		}
		//Se guarda en cabecera información de numero de trabajadores con pensión y con cesantía
		cabecera.setNumeroAfiliadosFdoPensiones(numeroAfiliadosFdoPensiones);
		cabecera.setNumeroAfiliadosFdoCesantia(numeroAfiliadosFdoCesantia);
		
		//Se retorna lista de páginas de detalle
		return detalles;
	}
	
	/*
	 * AFPV
	 */
	public PlanillaAfpDocumentModel cabeceraPlanillaAFPV(Comprobante_Encabezado comprobante, Comprobante_Detalle det_comprobante, DetalleSeccionxSucursalTO det_comp_sucursal, IdentificacionSucursal sucursal, FolioEntidades folios){
		//Se genera una nueva instancia de planilla de AFPV
		PlanillaAfpDocumentModel planillaAFP=null;
		try {
			planillaAFP = new PlanillaAfpDocumentModel(comprobante);
			//logger.finer("GenerarPlanillas.cabeceraPlanillaAFPV, getTipoDeclaracionPago= " + det_comprobante.getTipoDeclaracionPago());
			planillaAFP.setFilter(FILTER_AFP_AFV);
			//logger.finer("GenerarPlanillas.cabeceraPlanillaAFPV, filter= " + planillaAFP.getFilter());
			planillaAFP.setTipoPago(PAGO_NORMAL);
	
			//Se solicita folio para la entidad asociada
			int folio= folios.getFolio(det_comprobante.getRutEntidad());
			//logger.finer("GenerarPlanillas.cabeceraPlanillaAFPv folio a asignar planilla AFPV:" + folio);
			planillaAFP.setFolio(Formato.padding(folio, 7));
			planillaAFP.setNombreEntidad(folios.getNombreEntidad(det_comprobante.getRutEntidad()));
			
			//Se almacenan los datos de la cabecera de la planilla
			planillaAFP.setDatosSucursal(sucursal);
			planillaAFP.setSecuenciaFolio("000");
			int numeroHojasAnexas= (int)(Math.ceil((double)det_comp_sucursal.getN_trabajadores()/NUMLINEPAGE));
			//logger.finer("GenerarPlanillas.cabeceraPlanillaAFP, numeroHojasAnexas=" + numeroHojasAnexas);
			planillaAFP.setNumeroHojasAnexas(numeroHojasAnexas);
			
			//Se registran montos totales asociados a la sucursal 
			planillaAFP.setNumeroAfiliadosInformados(det_comp_sucursal.getN_trabajadores());
			planillaAFP.setCotizacionObligFdoPensiones(det_comp_sucursal.getM2());
			planillaAFP.setDepositosCtaAhorroFdoPensiones(det_comp_sucursal.getM3());
			planillaAFP.setSubtotalAPagarFdoPensiones(det_comp_sucursal.getM10());
			planillaAFP.setTotalAPagarFdoPensiones(det_comp_sucursal.getM10());
			planillaAFP.setTotalAPagar(det_comp_sucursal.getM10());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return planillaAFP;
	}
	
	public Collection splitDetallePlanillaAFPV(Collection cotizantes, PlanillaAfpDocumentModel cabecera){
		PlanillaAfpPaginaDetalle pagina_detalle=null;
		List detalles= new ArrayList();
		
		//Se inicializa la secuencia del folio y número de línea de detalle
		int secuenciaFolio=1;
		int numeroLineaDetalle=0;
		
		//Se itera sobre la lista de cotizantes
		for (Iterator lista_cotiz = cotizantes.iterator(); lista_cotiz.hasNext();) {
			PlanillaAfpCotizante cotizante = (PlanillaAfpCotizante) lista_cotiz.next();
			numeroLineaDetalle++;

			//Si se completa una página se resetea numero linea detalle para gatillar nueva página
			if (numeroLineaDetalle>NUMLINEPAGE){
				secuenciaFolio++;
				numeroLineaDetalle=1;
			}
			if(numeroLineaDetalle==1){
				//Se genera y almacena nueva página detalle que acumulará los cotizantes asociados
				pagina_detalle= new PlanillaAfpPaginaDetalle();
				detalles.add(pagina_detalle);
				//se asocia a la página detalle la cabecera de la planilla
				pagina_detalle.setCabeceraPlanilla(cabecera);
				//Se asigna a la nueva página la secuencia del folio y el número de página
				pagina_detalle.setSecuenciaFolio(Formato.padding(secuenciaFolio, 3));
				pagina_detalle.setPaginaActual(secuenciaFolio);
				pagina_detalle.setPaginaFinal(cabecera.getNumeroHojasAnexas());
			}
			cotizante.setNumeroLineaDetalle(numeroLineaDetalle);
			//Se agrega el cotizante a la página de detalle
			pagina_detalle.addCotizante(cotizante);
		}
		
		//Se retorna lista de páginas de detalle
		return detalles;
	}
	
	/*
	 * TRABAJOS PESADOS
	 */
	public PlanillaTpDocumentModel cabeceraPlanillaTP(Comprobante_Encabezado comprobante, Comprobante_Detalle det_comprobante, DetalleSeccionxSucursalTO det_comp_sucursal, IdentificacionSucursal sucursal, FolioEntidades folios){
		//Se genera una nueva instancia de planilla de TP
		PlanillaTpDocumentModel planilla=null;
		try {
			planilla = new PlanillaTpDocumentModel(comprobante);
			planilla.setTipoPago(PAGO_NORMAL);
	
			//Se solicita folio para la entidad asociada
			int folio= folios.getFolio(det_comprobante.getRutEntidad());
			//logger.finer("GenerarPlanillas.cabeceraPlanillaTP folio a asignar planilla AFP-TP:" + folio);
			planilla.setFolio(Formato.padding(folio, 7));
			planilla.setNombreEntidad(folios.getNombreEntidad(det_comprobante.getRutEntidad()));

			//Se almacenan los datos de la cabecera de la planilla
			planilla.setDatosSucursal(sucursal);
			planilla.setSecuenciaFolio("000");
			int numeroHojasAnexas= (int)(Math.ceil((double)det_comp_sucursal.getN_trabajadores()/NUMLINEPAGE));
			//logger.finer("GenerarPlanillas.cabeceraPlanillaAFP, numeroHojasAnexas=" + numeroHojasAnexas);
			planilla.setNumeroHojasAnexas(numeroHojasAnexas);
			
			//Se registran montos totales asociados a la sucursal 
			planilla.setTotalRemuneraciones(det_comp_sucursal.getM1());
			planilla.setCotizacionTrabajosPesadosFdoPensiones(det_comp_sucursal.getM9());
			planilla.setTotalAPagarFdoPensiones(det_comp_sucursal.getM9());
			planilla.setTotalAPagar(det_comp_sucursal.getM9());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return planilla;
	}
	
	public Collection splitDetallePlanillaTP(Collection cotizantes, PlanillaTpDocumentModel cabecera){
		PlanillaTpPaginaDetalle pagina_detalle=null;
		List detalles= new ArrayList();
		
		//Se inicializa la secuencia del folio y número de línea de detalle
		int secuenciaFolio=1;
		int numeroLineaDetalle=0;
		
		//Se itera sobre la lista de cotizantes
		for (Iterator lista_cotiz = cotizantes.iterator(); lista_cotiz.hasNext();) {
			PlanillaTpCotizante cotizante = (PlanillaTpCotizante) lista_cotiz.next();
			numeroLineaDetalle++;

			//Si se completa una página se resetea numero linea detalle para gatillar nueva página
			if (numeroLineaDetalle>NUMLINEPAGE){
				secuenciaFolio++;
				numeroLineaDetalle=1;
			}
			if(numeroLineaDetalle==1){
				//Se genera y almacena nueva página detalle que acumulará los cotizantes asociados
				pagina_detalle= new PlanillaTpPaginaDetalle();
				detalles.add(pagina_detalle);
				//se asocia a la página detalle la cabecera de la planilla
				pagina_detalle.setCabeceraPlanilla(cabecera);
				//Se asigna a la nueva página la secuencia del folio y el número de página
				pagina_detalle.setSecuenciaFolio(Formato.padding(secuenciaFolio, 3));
				pagina_detalle.setPaginaActual(secuenciaFolio);
				pagina_detalle.setPaginaFinal(cabecera.getNumeroHojasAnexas());
			}
			cotizante.setNumeroLineaDetalle(numeroLineaDetalle);
			//Se agrega el cotizante a la página de detalle
			pagina_detalle.addCotizante(cotizante);
		}
		
		//Se retorna lista de páginas de detalle
		return detalles;
	}
	
	/*
	 * APV
	 */
	public PlanillaApvDocumentModel cabeceraPlanillaAPV(Comprobante_Encabezado comprobante, Comprobante_Detalle det_comprobante, DetalleSeccionxSucursalTO det_comp_sucursal, IdentificacionSucursal sucursal, FolioEntidades folios){
		//Se genera una nueva instancia de planilla de AFP
		PlanillaApvDocumentModel planillaAPV=null;
		try {
			planillaAPV = new PlanillaApvDocumentModel(comprobante);
			//planillaAPV.setFilter(FILTER_APV_IND);
			planillaAPV.setTipoPago(PAGO_NORMAL);
	
			//Se solicita folio para la entidad asociada
			int folio= folios.getFolio(det_comprobante.getRutEntidad());
			//logger.finer("GenerarPlanillas.cabeceraPlanillaAPV folio a asignar planilla APV:" + folio);
			planillaAPV.setFolio(Formato.padding(folio, 7));
			planillaAPV.setNombreEntidad(folios.getNombreEntidad(det_comprobante.getRutEntidad()));
			
			//Se almacenan los datos de la cabecera de la planilla
			planillaAPV.setDatosSucursal(sucursal);
			planillaAPV.setSecuenciaFolio("000");
			int numeroHojasAnexas= (int)(Math.ceil((double)det_comp_sucursal.getN_trabajadores()/NUMLINEPAGE));
			//logger.finer("GenerarPlanillas.cabeceraPlanillaAPV, numeroHojasAnexas=" + numeroHojasAnexas);
			planillaAPV.setNumeroHojasAnexas(numeroHojasAnexas);
			planillaAPV.setNumeroAfiliadosInformados(det_comp_sucursal.getN_trabajadores());
			if(det_comprobante.getTipo_seccion()== REMU_APV){
				planillaAPV.setCotizacionVoluntariaFdoPensiones(det_comp_sucursal.getM3());
				planillaAPV.setSubtotalAPagarFdoPensiones(det_comp_sucursal.getM3());
				planillaAPV.setTotalAPagarFdoPensiones(det_comp_sucursal.getM3());
				planillaAPV.setTotalAPagar(0);
			}else if(det_comprobante.getTipo_seccion()== REMU_APVC){
				planillaAPV.setAporteEmpleadorFdoPensionesColectivo(det_comp_sucursal.getM1());
				planillaAPV.setAporteTrabajadorFdoPensionesColectivo(det_comp_sucursal.getM2());
				planillaAPV.setTotalAPagarFdoPensiones(det_comp_sucursal.getM3());
				planillaAPV.setTotalAPagar(0);
			}else if(det_comprobante.getTipo_seccion()== DEP_CONV_AFP){
				//Depósitos Convenidos
				planillaAPV.setDepositoConvenidoFdoPensiones(det_comp_sucursal.getM1());
				planillaAPV.setAporteIndemnizacionFdoPensiones(det_comp_sucursal.getM2());
				planillaAPV.setTotalAPagarFdoPensiones(det_comp_sucursal.getM3());
				planillaAPV.setTotalAPagar(0);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return planillaAPV;
	}
	
	public Collection splitDetallePlanillaAPV(Collection cotizantes, PlanillaApvDocumentModel cabecera){
		PlanillaApvPaginaDetalle pagina_detalle=null;
		List detalles= new ArrayList();
		
		//Se inicializa la secuencia del folio y número de línea de detalle
		int secuenciaFolio=1;
		int numeroLineaDetalle=0;
		
		//Se itera sobre la lista de cotizantes
		for (Iterator lista_cotiz = cotizantes.iterator(); lista_cotiz.hasNext();) {
			PlanillaApvCotizante cotizante = (PlanillaApvCotizante) lista_cotiz.next();
			numeroLineaDetalle++;

			//Si se completa una página se resetea numero linea detalle para gatillar nueva página
			if (numeroLineaDetalle>NUMLINEPAGE){
				secuenciaFolio++;
				numeroLineaDetalle=1;
			}
			if(numeroLineaDetalle==1){
				//Se genera y almacena nueva página detalle que acumulará los cotizantes asociados
				pagina_detalle= new PlanillaApvPaginaDetalle();
				detalles.add(pagina_detalle);
				//se asocia a la página detalle la cabecera de la planilla
				pagina_detalle.setCabeceraPlanilla(cabecera);
				//Se asigna a la nueva página la secuencia del folio y el número de página
				pagina_detalle.setSecuenciaFolio(Formato.padding(secuenciaFolio, 3));
				pagina_detalle.setPaginaActual(secuenciaFolio);
				pagina_detalle.setPaginaFinal(cabecera.getNumeroHojasAnexas());
			}
			cotizante.setNumeroLineaDetalle(numeroLineaDetalle);
			//Se agrega el cotizante a la página de detalle
			pagina_detalle.addCotizante(cotizante);
		}
		
		//Se retorna lista de páginas de detalle
		return detalles;
	}
	
	/*
	 * ISAPRE
	 */
	public PlanillaIsapreDocumentModel cabeceraPlanillaIsapre(Comprobante_Encabezado comprobante, Comprobante_Detalle det_comprobante, DetalleSeccionxSucursalTO det_comp_sucursal, IdentificacionSucursal sucursal, FolioEntidades folios){
		//Se genera una nueva instancia de planilla de AFP
		PlanillaIsapreDocumentModel planillaIsapre=null;
		try {
			planillaIsapre = new PlanillaIsapreDocumentModel(comprobante);
			planillaIsapre.setTipoPago(0);
			if(!comprobante.getTipoProceso().equals("G")){
				planillaIsapre.setTipoDeclaracion("10000");
			}else{
				planillaIsapre.setTipoDeclaracion("10010");
			}
			planillaIsapre.setTipoEntePagador("1000");
	
			//Se solicita folio para la entidad asociada
			int folio= folios.getFolio(det_comprobante.getRutEntidad());
			//logger.finer("GenerarPlanillas.cabeceraPlanillaIsapre folio a asignar planilla Isapre:" + folio);
			planillaIsapre.setFolio(Formato.padding(folio, 7));
			planillaIsapre.setNombreEntidad(folios.getNombreEntidad(det_comprobante.getRutEntidad()));
			
			//Se almacenan los datos de la cabecera de la planilla
			planillaIsapre.setDatosSucursal(sucursal);
			planillaIsapre.setSecuenciaFolio("000");
			int numeroHojasAnexas= (int)(Math.ceil((double)det_comp_sucursal.getN_trabajadores()/NUMLINEPAGE));
			//logger.finer("GenerarPlanillas.cabeceraPlanillaAFP, numeroHojasAnexas=" + numeroHojasAnexas);
			planillaIsapre.setNumeroHojasAnexas(numeroHojasAnexas);
			
			//Se registran montos totales asociados a la sucursal 
			planillaIsapre.setNumeroAfiliadosInformados(det_comp_sucursal.getN_trabajadores());
			planillaIsapre.setCotizacionLegal(det_comp_sucursal.getM2());
			planillaIsapre.setCotizacionAdicionalVoluntaria(det_comp_sucursal.getM3());
			planillaIsapre.setCotizacionTotalAPagar(det_comp_sucursal.getM4());
			planillaIsapre.setSubtotal(det_comp_sucursal.getM4());
			planillaIsapre.setTotalAPagar(det_comp_sucursal.getM4());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return planillaIsapre;
	}
	
	public Collection splitDetallePlanillaIsapre(Collection cotizantes, PlanillaIsapreDocumentModel cabecera){
		PlanillaIsaprePaginaDetalle pagina_detalle=null;
		List detalles= new ArrayList();
		
		//Se inicializa la secuencia del folio y número de línea de detalle
		int secuenciaFolio=1;
		int numeroLineaDetalle=0;
		
		//Se itera sobre la lista de cotizantes
		for (Iterator lista_cotiz = cotizantes.iterator(); lista_cotiz.hasNext();) {
			PlanillaIsapreCotizante cotizante = (PlanillaIsapreCotizante) lista_cotiz.next();
			numeroLineaDetalle++;

			//Si se completa una página se resetea numero linea detalle para gatillar nueva página
			if (numeroLineaDetalle>NUMLINEPAGE){
				secuenciaFolio++;
				numeroLineaDetalle=1;
			}
			if(numeroLineaDetalle==1){
				//Se genera y almacena nueva página detalle que acumulará los cotizantes asociados
				pagina_detalle= new PlanillaIsaprePaginaDetalle();
				detalles.add(pagina_detalle);
				//se asocia a la página detalle la cabecera de la planilla
				pagina_detalle.setCabeceraPlanilla(cabecera);
				//Se asigna a la nueva página la secuencia del folio y el número de página
				pagina_detalle.setSecuenciaFolio(Formato.padding(secuenciaFolio, 3));
				pagina_detalle.setPaginaActual(secuenciaFolio);
				pagina_detalle.setPaginaFinal(cabecera.getNumeroHojasAnexas());
			}
			cotizante.setNumeroLineaDetalle(numeroLineaDetalle);
			//Se agrega el cotizante a la página de detalle
			pagina_detalle.addCotizante(cotizante);
		}
		
		//Se retorna lista de páginas de detalle
		return detalles;
	}
	
	/*
	 * MUTUAL
	 */
	public PlanillaMutualDocumentModel cabeceraPlanillaMutual(Comprobante_Encabezado comprobante, Comprobante_Detalle det_comprobante, DetalleSeccionxSucursalTO det_comp_sucursal, IdentificacionSucursal sucursal, FolioEntidades folios){
		//Se genera una nueva instancia de planilla de AFP
		PlanillaMutualDocumentModel planillaMutual=null;
		try {
			planillaMutual = new PlanillaMutualDocumentModel(comprobante);
			//logger.finer("GenerarPlanillas.cabeceraPlanillaMutual, planillaMutual periodo" + planillaMutual.getPeriodo());
			if (det_comprobante.getTipoDeclaracionPago()== SECCION_PAGADA){
				if(comprobante.getTipoCliente().equals(TIPO_CLIENTE_INDEPENDIENTE)){
					planillaMutual.setFilter(FILTER_INDEPENDIENTE_OBL);
				}else{
					planillaMutual.setFilter(FILTER_MUTUAL_DYP);
				}
			}else{
				planillaMutual.setFilter(FILTER_MUTUAL_DNP);
			}
			//Se solicita folio para la entidad asociada
			int folio= folios.getFolio(det_comprobante.getRutEntidad());
			//logger.finer("GenerarPlanillas.cabeceraPlanillaMutual folio a asignar planilla Mutual:" + folio);
			planillaMutual.setFolio(Formato.padding(folio, 8));
			planillaMutual.setNombreEntidad(folios.getNombreEntidad(det_comprobante.getRutEntidad()));
			
			//Se almacenan los datos de la cabecera de la planilla
			planillaMutual.setDatosSucursal(sucursal);
			planillaMutual.setSecuenciaFolio("00");
			int numeroHojasAnexas= (int)(Math.ceil((double)det_comp_sucursal.getN_trabajadores()/NUMLINEPAGE));
			//logger.finer("GenerarPlanillas.cabeceraPlanillaMutual, numeroHojasAnexas=" + numeroHojasAnexas);
			planillaMutual.setNumeroHojasAnexas(numeroHojasAnexas);
			planillaMutual.setNumeroAfiliadosInformados(det_comp_sucursal.getN_trabajadores());
			planillaMutual.setNumeroAdherente(comprobante.getNumeroAdherenteMutual());
			//Se recupera y adecúa tasa Mutual(adicional + fija)
			//se divide por 100 para obtener porcentaje real de campo tipo long
			double porctasa= (double)det_comprobante.getM1()/100;
			//logger.finer("GenerarPlanillas.cabeceraPlanillaMutual, TASA DETALLE_SECCION=" + tasa);
			
			//Se registran montos totales asociados a la sucursal
			planillaMutual.setPorcTasaCotizacion(porctasa);
			planillaMutual.setTotalRemuneracionImponible(det_comp_sucursal.getM2());
			long totalcot=0;
			if(comprobante.isMutualCalculoIndividual()){
				totalcot= det_comp_sucursal.getM3();
			}else{
				totalcot= Math.round(det_comp_sucursal.getM2()* (porctasa/100.0));
			}
			planillaMutual.setTotalCotizacion(totalcot);
			planillaMutual.setTotalAPagar(totalcot);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return planillaMutual;
	}
	
	public Collection splitDetallePlanillaMutual(Collection cotizantes, PlanillaMutualDocumentModel cabecera){
		PlanillaMutualPaginaDetalle pagina_detalle=null;
		List detalles= new ArrayList();
		
		//Se inicializa la secuencia del folio y número de línea de detalle
		int secuenciaFolio=1;
		int numeroLineaDetalle=0;
		
		//Se itera sobre la lista de cotizantes
		for (Iterator lista_cotiz = cotizantes.iterator(); lista_cotiz.hasNext();) {
			PlanillaMutualCotizante cotizante = (PlanillaMutualCotizante) lista_cotiz.next();
			numeroLineaDetalle++;

			//Si se completa una página se resetea numero linea detalle para gatillar nueva página
			if (numeroLineaDetalle>NUMLINEPAGE){
				secuenciaFolio++;
				numeroLineaDetalle=1;
			}
			if(numeroLineaDetalle==1){
				//Se genera y almacena nueva página detalle que acumulará los cotizantes asociados
				pagina_detalle= new PlanillaMutualPaginaDetalle();
				detalles.add(pagina_detalle);
				//se asocia a la página detalle la cabecera de la planilla
				pagina_detalle.setCabeceraPlanilla(cabecera);
				//Se asigna a la nueva página la secuencia del folio y el número de página
				pagina_detalle.setSecuenciaFolio(Formato.padding(secuenciaFolio, 3));
				pagina_detalle.setPaginaActual(secuenciaFolio);
				pagina_detalle.setPaginaFinal(cabecera.getNumeroHojasAnexas());
			}
			cotizante.setNumeroLineaDetalle(numeroLineaDetalle);
			//Se agrega el cotizante a la página de detalle
			pagina_detalle.addCotizante(cotizante);
		}
		
		//Se retorna lista de páginas de detalle
		return detalles;
	}
	/*
	 * INP
	 */
	public PlanillaInpDocumentModel cabeceraPlanillaINP(Comprobante_Encabezado comprobante, Comprobante_Detalle det_comprobante, DetalleSeccionxSucursalTO det_comp_sucursal, IdentificacionSucursal sucursal, FolioEntidades folios){
		//Se genera una nueva instancia de planilla de INP
		PlanillaInpDocumentModel planillaINP=null;
		try {
			planillaINP = new PlanillaInpDocumentModel(comprobante);
			if (det_comprobante.getTipoDeclaracionPago()== SECCION_PAGADA){
				planillaINP.setFilter(FILTER_INP_DYP);
			}else{
				planillaINP.setFilter(FILTER_INP_DNP);
			}
			
			//Se solicita folio para la entidad asociada
			int folio= folios.getFolio(det_comprobante.getRutEntidad());
			//logger.finer("GenerarPlanillas.cabeceraPlanillaINP folio a asignar planilla INP:" + folio);
			planillaINP.setFolio(Formato.paddingLeft(String.valueOf(folio), 9, ' '));
			planillaINP.setFolio_Recaudador("0000001");
			planillaINP.setNombreEntidad(folios.getNombreEntidad(det_comprobante.getRutEntidad()));
			
			//Se almacenan los datos de la cabecera de la planilla
			planillaINP.setDatosSucursal(sucursal);
			planillaINP.setSecuenciaFolio("");
			int numeroHojasAnexas= (int)(Math.ceil((double)det_comp_sucursal.getN_trabajadores()/NUMLINEPAGE_INP));

			//logger.finer("GenerarPlanillas.cabeceraPlanillaINP, numeroHojasAnexas=" + numeroHojasAnexas);
			planillaINP.setNumeroHojasAnexas(numeroHojasAnexas);
			planillaINP.setNumeroAfiliadosInformados(det_comp_sucursal.getN_trabajadores());
			planillaINP.setCodigoCCAF(comprobante.getIdCcaf());
			planillaINP.setNombreCCAF(comprobante.getNombreCcaf());
			//clillo 20-06-2014
			//Para el código Mutual, si es 0 se verifica si corresponde a INP(Mutual) para asignar valor 1
			int idMutual= comprobante.getIdMutual();
			String nombreMutual= comprobante.getNombreMutual();
			if(idMutual==0 && det_comprobante.getM4()>0){
				idMutual= 1; //INP(Mutual)
				nombreMutual= INP_MUTUAL;
			}
			planillaINP.setCodigoMutual(idMutual);
			planillaINP.setNombreMutual(nombreMutual);
			
			//Se registran montos totales asociados a la sucursal
			//planillaINP.set(det_comp_sucursal.getM1());
			planillaINP.setTotalMontoPensionesInp(det_comp_sucursal.getM2());
			planillaINP.setTotalMontoFonasa(det_comp_sucursal.getM3());
			planillaINP.setTotalMontoAccDelTrabajo(det_comp_sucursal.getM4());
			planillaINP.setTotalMontoDesahucio(det_comp_sucursal.getM5());
			planillaINP.setTotalMontoCotizaciones(det_comp_sucursal.getM6());
			planillaINP.setTotalMontoAsigFamiliar(det_comp_sucursal.getM7());
			planillaINP.setTotalMontoBonificacionLey15386(det_comp_sucursal.getM8());
			planillaINP.setTotalMontoRebajas(det_comp_sucursal.getM9());
			long total= det_comp_sucursal.getM10();
			if (total>0){
				planillaINP.setTotalMontoSaldoFavorInstitucion(total);
				planillaINP.setTotalAPagar(total);
			}else{
				planillaINP.setTotalMontoSaldoFavorEmpleador(total*-1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return planillaINP;
	}
	
	public Collection splitDetallePlanillaINP(Collection cotizantes, PlanillaInpDocumentModel cabecera){
		PlanillaInpPaginaDetalle pagina_detalle=null;
		List detalles= new ArrayList();
		
		//Se inicializa la secuencia del folio y número de línea de detalle
		int secuenciaFolio=1;
		int numeroLineaDetalle=0;
		
		//Se inicializa total general página
		long totalGeneralRemuneraciones=0;
	    long totalGeneralPensiones=0;
	    long totalGeneralFonasa=0;
	    long totalGeneralAccidente=0;
	    long totalGeneralRemuneracionesDesahucio=0;
	    long totalGeneralCotizacionDesahucio=0;
	    long totalGeneralMontoSimple=0;
	    long totalGeneralMontoInvalida=0;
	    long totalGeneralMontoMaternal=0;
	    long totalGeneralBonificacion15366=0;
		
		//Se itera sobre la lista de cotizantes
		for (Iterator lista_cotiz = cotizantes.iterator(); lista_cotiz.hasNext();) {
			PlanillaInpCotizante cotizante = (PlanillaInpCotizante) lista_cotiz.next();
			numeroLineaDetalle++;

			//Si se completa una página se resetea numero linea detalle para gatillar nueva página
			//Se rescata el Total Página para ir acumulando al Total General
			if (numeroLineaDetalle>NUMLINEPAGE_INP){
				secuenciaFolio++;
				numeroLineaDetalle=1;
				//se guarda los totales página para acumular en página siguiente
				totalGeneralRemuneraciones+= pagina_detalle.getTotalRemuneraciones();
			    totalGeneralPensiones+=pagina_detalle.getTotalPensiones();
			    totalGeneralFonasa+=pagina_detalle.getTotalFonasa();
			    totalGeneralAccidente+= pagina_detalle.getTotalAccidente();
			    totalGeneralRemuneracionesDesahucio+= pagina_detalle.getTotalRemuneracionesDesahucio();
			    totalGeneralCotizacionDesahucio+= pagina_detalle.getTotalCotizacionDesahucio();
			    totalGeneralMontoSimple+= pagina_detalle.getTotalMontoSimple();
			    totalGeneralMontoInvalida+= pagina_detalle.getTotalMontoInvalida();
			    totalGeneralMontoMaternal+= pagina_detalle.getTotalMontoMaternal();
			    totalGeneralBonificacion15366+= pagina_detalle.getTotalBonificacion15366();
				
			}
			if(numeroLineaDetalle==1){
				//Se genera y almacena nueva página detalle que acumulará los cotizantes asociados
				pagina_detalle= new PlanillaInpPaginaDetalle();
				//Se setea valor para Total General por página
				pagina_detalle.setTotalGeneralRemuneraciones(totalGeneralRemuneraciones);
				pagina_detalle.setTotalGeneralPensiones(totalGeneralPensiones);
				pagina_detalle.setTotalGeneralFonasa(totalGeneralFonasa);
				pagina_detalle.setTotalGeneralAccidente(totalGeneralAccidente);
				pagina_detalle.setTotalGeneralRemuneracionesDesahucio(totalGeneralRemuneracionesDesahucio);
				pagina_detalle.setTotalGeneralCotizacionDesahucio(totalGeneralCotizacionDesahucio);
				pagina_detalle.setTotalGeneralMontoSimple(totalGeneralMontoSimple);
				pagina_detalle.setTotalGeneralMontoInvalida(totalGeneralMontoInvalida);
				pagina_detalle.setTotalGeneralMontoMaternal(totalGeneralMontoMaternal);
				
				detalles.add(pagina_detalle);
				//se asocia a la página detalle la cabecera de la planilla
				pagina_detalle.setCabeceraPlanilla(cabecera);
				//Se asigna a la nueva página la secuencia del folio y el número de página
				pagina_detalle.setSecuenciaFolio(Formato.padding(secuenciaFolio, 3));
				pagina_detalle.setPaginaActual(secuenciaFolio);
				pagina_detalle.setPaginaFinal(cabecera.getNumeroHojasAnexas());
			}
			cotizante.setNumeroLineaDetalle(numeroLineaDetalle);
			//Se agrega el cotizante a la página de detalle
			pagina_detalle.addCotizante(cotizante);
		}
		
		//Se retorna lista de páginas de detalle
		return detalles;
	}
	
	/*
	 * CCAF
	 */
	public PlanillaCcafDocumentModel cabeceraPlanillaCcaf(Comprobante_Encabezado comprobante, Comprobante_Detalle det_comprobante, DetalleSeccionxSucursalTO det_comp_sucursal, IdentificacionSucursal sucursal, FolioEntidades folios, EntidadCCAFTO ccafTO){
		//Se genera una nueva instancia de planilla de AFP
		PlanillaCcafDocumentModel planillaCcaf=null;
		long compensado=0;
		long no_compensado=0;
		long sfi=0;
		long sfe=0;
		try {
			planillaCcaf = new PlanillaCcafDocumentModel(comprobante);
			
			//Se solicita folio para la entidad asociada
			int folio= folios.getFolio(det_comprobante.getRutEntidad());
			//se reservan 4 folios adicionales para crédito, leasing, convenio dental y seguro vida
			for (int i = 0; i < 4; i++) {
				folios.getFolio(det_comprobante.getRutEntidad());
			}
			//logger.finer("GenerarPlanillas.cabeceraPlanillaCcaf folio a asignar planilla Ccaf:" + folio);
			planillaCcaf.setFolio(Formato.padding(folio, 7));
			planillaCcaf.setNombreEntidad(folios.getNombreEntidad(det_comprobante.getRutEntidad()));
			
			//Se almacenan los datos de la cabecera de la planilla
			planillaCcaf.setDatosSucursal(sucursal);
			planillaCcaf.setSecuenciaFolio("000");
			
			int numeroHojasAnexas= (int)(Math.ceil((double)det_comp_sucursal.getN_trabajadores()/NUMLINEPAGE));
			//logger.finer("GenerarPlanillas.cabeceraPlanillaAFP, numeroHojasAnexas=" + numeroHojasAnexas);
			planillaCcaf.setNumeroHojasAnexas(numeroHojasAnexas);
			planillaCcaf.setNumeroAfiliadosInformados(det_comp_sucursal.getN_trabajadores());
			
			//Se verifica si Convenio está adeherido a Mutual
			if(comprobante.getIdMutual()>0){
				planillaCcaf.setAdheridoMutual(FILTER_CCAF_ADHERIDO_MUTUAL);
			}else{
				planillaCcaf.setAdheridoMutual(FILTER_CCAF_NO_ADHERIDO_MUTUAL);
			}
			
			if(det_comprobante.getTipo_seccion()== REMU_CCAF){
				//Aporte 0,6
				planillaCcaf.setSubtotalNoAfiliadoIsapre((int)det_comp_sucursal.getM1());
				//Asfam
				planillaCcaf.setTotalRebajas((int)det_comp_sucursal.getM2());
				planillaCcaf.setTotalMontoAsigFamiliar((int)det_comp_sucursal.getM2());
				//Creditos
				planillaCcaf.setMontoCreditos(det_comp_sucursal.getM4());
				//Leasing
				planillaCcaf.setMontoLeasing(det_comp_sucursal.getM5());
				//Seguro Vida
				planillaCcaf.setMontoSeguros((int)det_comp_sucursal.getM6());
				//Convenio Dental
				planillaCcaf.setMontoConvenioDental((int)det_comp_sucursal.getM7());
				//Total Pagos
				planillaCcaf.setTotalSubTotal(det_comp_sucursal.getM1() + det_comp_sucursal.getM4() + det_comp_sucursal.getM5() + det_comp_sucursal.getM6() + det_comp_sucursal.getM7());
				//SFI y SFE
				//Asfam siempre se resta a los valores a compensar;
				compensado+= det_comp_sucursal.getM2()*-1;
				//Compesación Aporte
				if(ccafTO.isCompensaAporte()){
					compensado+= det_comp_sucursal.getM1();
				}else{
					no_compensado+= det_comp_sucursal.getM1();
				}
				//Compensación Crédito
				if(ccafTO.isCompensaCredito()){
					compensado+= det_comp_sucursal.getM4();
				}else{
					no_compensado+= det_comp_sucursal.getM4();
				}
				//Compensación Leasing
				if(ccafTO.isCompensaLeasing()){
					compensado+= det_comp_sucursal.getM5();
				}else{
					no_compensado+= det_comp_sucursal.getM5();
				}
				//Compensación Seguro de Vida
				if(ccafTO.isCompensaSeguro()){
					compensado+= det_comp_sucursal.getM6();
				}else{
					no_compensado+= det_comp_sucursal.getM6();
				}
				//Compensación Dental
				if(ccafTO.isCompensaDental()){
					compensado+= det_comp_sucursal.getM7();
				}else{
					no_compensado+= det_comp_sucursal.getM7();
				}
				//Se revisa si valor compensado es + o - para establecer sfi y sfe
				if(compensado>=0){
					sfi= compensado + no_compensado;
					sfe=0;
				}else{
					sfi= no_compensado;
					sfe= Math.abs(compensado);
				}
				planillaCcaf.setSaldoAFavorInstitucion(sfi);
				planillaCcaf.setSaldoAFavorEmpleador((int)sfe);
				
				//Total == SFI
				planillaCcaf.setTotalAPagar(sfi);
			}else{
				//Aporte 0,6
				planillaCcaf.setSubtotalNoAfiliadoIsapre((int)det_comp_sucursal.getM1()); 
				//Total Pagos == 0,6
				planillaCcaf.setTotalSubTotal(det_comp_sucursal.getM1());
				//SFI
				planillaCcaf.setSaldoAFavorInstitucion(det_comp_sucursal.getM1());
				//Total == SFI
				planillaCcaf.setTotalAPagar(det_comp_sucursal.getM1());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return planillaCcaf;
	}
	
	public Collection splitDetallePlanillaCcaf(Collection cotizantes, PlanillaCcafDocumentModel cabecera, Comprobante_Detalle det_comprobante){
		PlanillaCcafPaginaDetalle pagina_detalle=null;
		List detalles= new ArrayList();
		List detalleCredito=null, detalleLeasing=null, detalleSeguroVida=null, detalleConvenioDental=null;
		int secuenciaFolio=1;
		int numeroLineaDetalle=0;
		int numeroAfiliadosInformados=0;
		//Total Remuneraciones
		long totalRemuneracionNOAfiliadoIsapre=0;
		long totalRemuneracionAfiliadoIsapre=0;
		//Total Hombres y Mujeres
		int totalHombresNoAfiliadosIsapre=0;
		int totalMujeresNoAfiliadosIsapre=0;
		int totalHombresAfiliadosIsapre=0;
		int totalMujeresAfiliadosIsapre=0;
		//Cantidad de Cargas
		int totalCargasTramoASimples=0;
		int totalCargasTramoBSimples=0;
		int totalCargasTramoCSimples=0;
		int totalCargasTramoDSimples=0;
		int totalCargasTramoAInvalidez=0;
		int totalCargasTramoBInvalidez=0;
		int totalCargasTramoCInvalidez=0;
		int totalCargasTramoDInvalidez=0;
		int totalCargasTramoAMaternal=0;
		int totalCargasTramoBMaternal=0;
		int totalCargasTramoCMaternal=0;
		int totalCargasTramoDMaternal=0;
		//Total trabajadores con carga
		int totalTrabCargasTramoA=0;
		int totalTrabCargasTramoB=0;
		int totalTrabCargasTramoC=0;
		int totalTrabCargasTramoD=0;
		//Total trabajadores con reintegro o retroactivo
		int totalTrabReintegros=0;
		int totalTrabRetroactivos=0;
		//montoTotales x Tramo
		int montoTotalTramoA=0;
		int montoTotalTramoB=0;
		int montoTotalTramoC=0;
		int montoTotalTramoD=0;
		int montoTotalReintegros=0;
		int montoTotalRetroactivos=0;
		
		//Inicializando detalles en crédito, leasing, seguro vida y dental
			detalleCredito= new ArrayList();
			detalleLeasing= new ArrayList();
			detalleSeguroVida= new ArrayList();
			detalleConvenioDental= new ArrayList();
			
			//Se itera sobre la lista de cotizantes
			for (Iterator lista_cotiz = cotizantes.iterator(); lista_cotiz.hasNext();) {
				PlanillaCcafCotizante cotizante = (PlanillaCcafCotizante) lista_cotiz.next();
				numeroLineaDetalle++;

				//Si se completa una página se resetea numero linea detalle para gatillar nueva página
				if (numeroLineaDetalle>NUMLINEPAGE){
					secuenciaFolio++;
					numeroLineaDetalle=1;
				}
				if(numeroLineaDetalle==1){
					//Se genera y almacena nueva página detalle que acumulará los cotizantes asociados
					pagina_detalle= new PlanillaCcafPaginaDetalle();
					detalles.add(pagina_detalle);
					//se asocia a la página detalle la cabecera de la planilla
					pagina_detalle.setCabeceraPlanilla(cabecera);
					//Se asigna a la nueva página la secuencia del folio y el número de página
					pagina_detalle.setSecuenciaFolio(Formato.padding(secuenciaFolio, 3));
					pagina_detalle.setPaginaActual(secuenciaFolio);
					pagina_detalle.setPaginaFinal(cabecera.getNumeroHojasAnexas());
				}
				cotizante.setNumeroLineaDetalle(numeroLineaDetalle);
				//Clasificando afiliación a Isapre y género del cotizante
				if(cotizante.getEntidadSalud()>0){
					if(cotizante.getCodigoSexo()== MASCULINO){
						totalHombresAfiliadosIsapre++;
					}else{
						totalMujeresAfiliadosIsapre++;
					}
				}else if(cotizante.getEntidadSalud()==0){
					if(cotizante.getCodigoSexo()== MASCULINO){
						totalHombresNoAfiliadosIsapre++;
					}else{
						totalMujeresNoAfiliadosIsapre++;
					}
				}
				
				//Se acumula total Remuneracion Imponible
				if(cotizante.getEntidadSalud()>0){
					totalRemuneracionAfiliadoIsapre+= cotizante.getRemuneracionImponibleCotizante();
				}else{
					totalRemuneracionNOAfiliadoIsapre+= cotizante.getRemuneracionImponibleCotizante();
				}
				
				if(det_comprobante.getTipo_seccion()==REMU_CCAF){
					//Contabilizando Número de Cargas
					switch (cotizante.getCodigoTramo()) {
					case TRAMO_A:
						totalCargasTramoASimples+= cotizante.getCantidadCargasSimCotizante();
						totalCargasTramoAInvalidez+= cotizante.getCantidadCargasInvCotizante();
						totalCargasTramoAMaternal+= cotizante.getCantidadCargasMatCotizante();
						if(cotizante.getCantidadCargasSimCotizante() + cotizante.getCantidadCargasInvCotizante() + cotizante.getCantidadCargasMatCotizante()>0){
							totalTrabCargasTramoA++;
						}
						montoTotalTramoA+= cotizante.getMontoAsigFamiliarCotizante();
						break;
					case TRAMO_B:
						totalCargasTramoBSimples+= cotizante.getCantidadCargasSimCotizante();
						totalCargasTramoBInvalidez+= cotizante.getCantidadCargasInvCotizante();
						totalCargasTramoBMaternal+= cotizante.getCantidadCargasMatCotizante();
						if(cotizante.getCantidadCargasSimCotizante() + cotizante.getCantidadCargasInvCotizante() + cotizante.getCantidadCargasMatCotizante()>0){
							totalTrabCargasTramoB++;
						}
						montoTotalTramoB+= cotizante.getMontoAsigFamiliarCotizante();
						break;
					case TRAMO_C:
						totalCargasTramoCSimples+= cotizante.getCantidadCargasSimCotizante();
						totalCargasTramoCInvalidez+= cotizante.getCantidadCargasInvCotizante();
						totalCargasTramoCMaternal+= cotizante.getCantidadCargasMatCotizante();
						if(cotizante.getCantidadCargasSimCotizante() + cotizante.getCantidadCargasInvCotizante() + cotizante.getCantidadCargasMatCotizante()>0){
							totalTrabCargasTramoC++;
						}
						montoTotalTramoC+= cotizante.getMontoAsigFamiliarCotizante();
						break;
					case TRAMO_D:
						totalCargasTramoDSimples+= cotizante.getCantidadCargasSimCotizante();
						totalCargasTramoDInvalidez+= cotizante.getCantidadCargasInvCotizante();
						totalCargasTramoDMaternal+= cotizante.getCantidadCargasMatCotizante();
						if(cotizante.getCantidadCargasSimCotizante() + cotizante.getCantidadCargasInvCotizante() + cotizante.getCantidadCargasMatCotizante()>0){
							totalTrabCargasTramoD++;
						}
						montoTotalTramoD+= cotizante.getMontoAsigFamiliarCotizante();
						break;
					default:
						break;
					}
					//Contabilizando Reintegros y Retroactivos
					if(cotizante.getMontoAsigFamiliarReintegroCotizante()>0){
						totalTrabReintegros++;
						montoTotalReintegros+= cotizante.getMontoAsigFamiliarReintegroCotizante();
					}
					if(cotizante.getMontoAsigFamiliarRetroactivoCotizante()>0){
						totalTrabRetroactivos++;
						montoTotalRetroactivos+= cotizante.getMontoAsigFamiliarRetroactivoCotizante();
					}
					
					//Se rescata y acumula si tiene Credito, Leasing, Seguro Vida o Convenio Dental
					if(cotizante.getMontoCuotaCredito()>0){
						detalleCredito.add(cotizante);
					}
					if(cotizante.getMontoCuotaLeasing()>0){
						detalleLeasing.add(cotizante);
					}
					if(cotizante.getMontoCuotaSeguroVida()>0){
						detalleSeguroVida.add(cotizante);
					}
					if(cotizante.getMontoCuotaConvenioDental()>0){
						detalleConvenioDental.add(cotizante);
					}
				}
				//Se agrega el cotizante a la página de detalle
				pagina_detalle.addCotizante(cotizante);
			}
			if (cabecera!=null){
				//Se guarda total total Remuneracion NO Afiliado a Isapre
				cabecera.setMontoRemuneracionNoAfiliadosIsapre(totalRemuneracionNOAfiliadoIsapre);
				//Se guarda total total Remuneracion Afiliado a Isapre
				cabecera.setMontoRemuneracionAfiliadosIsapre(totalRemuneracionAfiliadoIsapre);
				//Se guarda total Remuneraciones
				cabecera.setTotalRemuneraciones(totalRemuneracionAfiliadoIsapre + totalRemuneracionNOAfiliadoIsapre);

				//Se guarda total hombres y mujeres
				cabecera.setTotalHombresAfiliadosIsapre(totalHombresAfiliadosIsapre);
				cabecera.setTotalHombresNoAfiliadosIsapre(totalHombresNoAfiliadosIsapre);
				cabecera.setTotalMujeresAfiliadosIsapre(totalMujeresAfiliadosIsapre);
				cabecera.setTotalMujeresNoAfiliadosIsapre(totalMujeresNoAfiliadosIsapre);
				
				if(det_comprobante.getTipo_seccion()==REMU_CCAF){
					//Se guarda total cargas por tramo
					//Simples
					cabecera.setTotalCargasAsigFamiliarSimpTramoA(totalCargasTramoASimples);
					cabecera.setTotalCargasAsigFamiliarSimpTramoB(totalCargasTramoBSimples);
					cabecera.setTotalCargasAsigFamiliarSimpTramoC(totalCargasTramoCSimples);
					cabecera.setTotalCargasAsigFamiliarSimpTramoD(totalCargasTramoDSimples);
					//Invalidez
					cabecera.setTotalCargasAsigFamiliarInvlTramoA(totalCargasTramoAInvalidez);
					cabecera.setTotalCargasAsigFamiliarInvlTramoB(totalCargasTramoBInvalidez);
					cabecera.setTotalCargasAsigFamiliarInvlTramoC(totalCargasTramoCInvalidez);
					cabecera.setTotalCargasAsigFamiliarInvlTramoD(totalCargasTramoDInvalidez);
					//Maternal
					cabecera.setTotalCargasAsigFamiliarMatTramoA(totalCargasTramoAMaternal);
					cabecera.setTotalCargasAsigFamiliarMatTramoB(totalCargasTramoBMaternal);
					cabecera.setTotalCargasAsigFamiliarMatTramoC(totalCargasTramoCMaternal);
					cabecera.setTotalCargasAsigFamiliarMatTramoD(totalCargasTramoDMaternal);
					//Trabaj. con cargas
					cabecera.setTotalTrabajadoresConCargaTramoA(totalTrabCargasTramoA);
					cabecera.setTotalTrabajadoresConCargaTramoB(totalTrabCargasTramoB);
					cabecera.setTotalTrabajadoresConCargaTramoC(totalTrabCargasTramoC);
					cabecera.setTotalTrabajadoresConCargaTramoD(totalTrabCargasTramoD);
					cabecera.setTotalTrabajadoresConCargaRetroactiva(totalTrabRetroactivos);
					cabecera.setTotalTrabajadoresConCargaReintegros(totalTrabReintegros);
					//Montos x Tramo
					cabecera.setTotalMontoAsigFamiliarTramoA(montoTotalTramoA);
					cabecera.setTotalMontoAsigFamiliarTramoB(montoTotalTramoB);
					cabecera.setTotalMontoAsigFamiliarTramoC(montoTotalTramoC);
					cabecera.setTotalMontoAsigFamiliarTramoD(montoTotalTramoD);
					cabecera.setTotalMontoAsigFamiliarReintegros(montoTotalReintegros);
					cabecera.setTotalMontoAsigFamiliarRetroactiva(montoTotalRetroactivos);
					//Total Cargas por tipo carga
					cabecera.setTotalCargasSimp(totalCargasTramoASimples + totalCargasTramoBSimples + totalCargasTramoCSimples + totalCargasTramoDSimples);
					cabecera.setTotalCargasInvl(totalCargasTramoAInvalidez+totalCargasTramoBInvalidez+totalCargasTramoCInvalidez+totalCargasTramoDInvalidez);
					cabecera.setTotalCargasMat(totalCargasTramoAMaternal+totalCargasTramoBMaternal+totalCargasTramoCMaternal+totalCargasTramoDMaternal);
				}
				//Se agrega lista de trabajadores con crédito, leasing, seguro vida y dental
				cabecera.setPaginasDetalleCredito(detalleCredito);
				cabecera.setPaginasDetalleLeasing(detalleLeasing);
				cabecera.setPaginasDetalleSeguroVida(detalleSeguroVida);
				cabecera.setPaginasDetalleConvenioDental(detalleConvenioDental);
			}
			//Se retorna lista de páginas de detalle
			return detalles;
	}
	
	public int splitComplementosPlanillaCcaf(PlanillaCcafDocumentModel planillaCcaf, Collection cotizantes, int tipoDetalle){
		PlanillaCcafPaginaDetalle pagina_detalle=null;
		List detalle_Anexos= new ArrayList();
		int secuenciaFolio=1;
		int numeroLineaDetalle=0;
		//Se itera sobre la lista de cotizantes
		for (Iterator lista_cotiz = cotizantes.iterator(); lista_cotiz.hasNext();) {
			PlanillaCcafCotizante cotizante = (PlanillaCcafCotizante) lista_cotiz.next();
			numeroLineaDetalle++;
			//Si se completa una página de detalle se genera una nueva página
			if (numeroLineaDetalle>NUMLINEPAGE){
				detalle_Anexos.add(pagina_detalle);				
				secuenciaFolio++;
				numeroLineaDetalle=1;
				//logger.finer("GenerarPlanillas.splitDetallePlanillaCcaf, se genera nueva pagina de detalle, secuencia folio:" + secuenciaFolio);
			}
			if(numeroLineaDetalle==1){
				//SE agregan otros datos variables a la página
				pagina_detalle= new PlanillaCcafPaginaDetalle();
				pagina_detalle.setCabeceraPlanilla(planillaCcaf);
				pagina_detalle.setTipoDetalle(tipoDetalle);
				pagina_detalle.setSecuenciaFolio(Formato.padding(secuenciaFolio, 3));
				//logger.finer(">>>>>>GenerarPlanillas.splitDetallePlanillaCcaf, secuenciaFOlio formateado=" + pagina_detalle.getSecuenciaFolio());
				pagina_detalle.setPaginaActual(secuenciaFolio);
			}
			switch (tipoDetalle) {
			case 1:
				cotizante.setNumeroLineaDetalleCredito(numeroLineaDetalle);
				break;
			case 2:
				cotizante.setNumeroLineaDetalleLeasing(numeroLineaDetalle);
				break;
			case 3:
				cotizante.setNumeroLineaDetalleConvenioDental(numeroLineaDetalle);
				break;
			case 4:
				cotizante.setNumeroLineaDetalleSeguroVida(numeroLineaDetalle);
				break;
			}
			
			//Se agrega el cotizante a la página de detalle
			pagina_detalle.addCotizante(cotizante);
		}
		//Se agrega la última página de detalle creada
		detalle_Anexos.add(pagina_detalle);
		//se agregan a la planilla las páginas de detalle según tipo.
		switch (tipoDetalle) {
		case 1:
			planillaCcaf.setPaginasDetalleCredito(detalle_Anexos);
			break;
		case 2:
			planillaCcaf.setPaginasDetalleLeasing(detalle_Anexos);
			break;
		case 3:
			planillaCcaf.setPaginasDetalleConvenioDental(detalle_Anexos);
			break;
		case 4:
			planillaCcaf.setPaginasDetalleSeguroVida(detalle_Anexos);
			break;
		}

		return detalle_Anexos.size();
	}
	
	/*
	 * ISAPRE
	 */
	public PlanillaAfbrDocumentModel cabeceraPlanillaAFBR(Comprobante_Encabezado comprobante, Comprobante_Detalle det_comprobante, DetalleSeccionxSucursalTO det_comp_sucursal, IdentificacionSucursal sucursal, FolioEntidades folios){
		//Se genera una nueva instancia de planilla de AFBR
		PlanillaAfbrDocumentModel planillaAFBR=null;
		try {
			planillaAFBR = new PlanillaAfbrDocumentModel(comprobante);
			if(comprobante.getTipoProceso().equals("R")){
				planillaAFBR.setTipoDeclaracion("10");
			}else{
				planillaAFBR.setTipoDeclaracion("01");
			}
			//Planilla Normal
			planillaAFBR.setTipoPago("100");
	
			//Se solicita folio para la entidad asociada
			int folio= folios.getFolio(det_comprobante.getRutEntidad());
			//logger.finer("GenerarPlanillas.cabeceraPlanillaIsapre folio a asignar planilla Isapre:" + folio);
			planillaAFBR.setFolio(Formato.padding(folio, 7));
			planillaAFBR.setNombreEntidad(folios.getNombreEntidad(det_comprobante.getRutEntidad()));
			
			//Se almacenan los datos de la cabecera de la planilla
			planillaAFBR.setDatosSucursal(sucursal);
			planillaAFBR.setSecuenciaFolio("000");
			int numeroHojasAnexas= (int)(Math.ceil((double)det_comp_sucursal.getN_trabajadores()/NUMLINEPAGE));
			//logger.finer("GenerarPlanillas.cabeceraPlanillaAFP, numeroHojasAnexas=" + numeroHojasAnexas);
			planillaAFBR.setNumeroHojasAnexas(numeroHojasAnexas);
			
			//Se registran montos totales asociados a la sucursal 
			planillaAFBR.setNumeroAfiliadosInformados(det_comp_sucursal.getN_trabajadores());
			planillaAFBR.setCotizacionTotalAporte(det_comp_sucursal.getM1());
			planillaAFBR.setTotalAPagar(det_comp_sucursal.getM1());
			planillaAFBR.setTotalRemuneraciones(det_comp_sucursal.getM2());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return planillaAFBR;
	}
	
	public Collection splitDetallePlanillaAFBR(Collection cotizantes, PlanillaAfbrDocumentModel cabecera){
		PlanillaAfbrPaginaDetalle pagina_detalle=null;
		List detalles= new ArrayList();
		
		//Se inicializa la secuencia del folio y número de línea de detalle
		int secuenciaFolio=1;
		int numeroLineaDetalle=0;
		
		//Se itera sobre la lista de cotizantes
		for (Iterator lista_cotiz = cotizantes.iterator(); lista_cotiz.hasNext();) {
			PlanillaAfbrCotizante cotizante = (PlanillaAfbrCotizante) lista_cotiz.next();
			numeroLineaDetalle++;

			//Si se completa una página se resetea numero linea detalle para gatillar nueva página
			if (numeroLineaDetalle>NUMLINEPAGE_AFBR){
				secuenciaFolio++;
				numeroLineaDetalle=1;
			}
			if(numeroLineaDetalle==1){
				//Se genera y almacena nueva página detalle que acumulará los cotizantes asociados
				pagina_detalle= new PlanillaAfbrPaginaDetalle();
				detalles.add(pagina_detalle);
				//se asocia a la página detalle la cabecera de la planilla
				pagina_detalle.setCabeceraPlanilla(cabecera);
				//Se asigna a la nueva página la secuencia del folio y el número de página
				pagina_detalle.setSecuenciaFolio(Formato.padding(secuenciaFolio, 3));
				pagina_detalle.setPaginaActual(secuenciaFolio);
				pagina_detalle.setPaginaFinal(cabecera.getNumeroHojasAnexas());
			}
			cotizante.setNumeroLineaDetalle(numeroLineaDetalle);
			//Se agrega el cotizante a la página de detalle
			pagina_detalle.addCotizante(cotizante);
		}
		
		//Se retorna lista de páginas de detalle
		return detalles;
	}
	
	/**
	 * @return el mensajeError
	 */
	public String getMensajeError() {
		return mensajeError;
	}
	/**
	 * @param mensajeError el mensajeError a establecer
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	private void loadProperties(String fileproperties){
		PropertiesLoader propertiesloader = new PropertiesLoader();
		try
		{
			properties = propertiesloader.load(fileproperties, cl.araucana.cierrecpe.empresas.business.GenerarPlanillas.class);
		}
		catch(Exception eproperties)
		{
			logger.severe("cannot open " + fileproperties + " properties file." + eproperties.getMessage());
			eproperties.printStackTrace();
		}
	}

	/**
	 * @return el numeroComprobantesConPlanillas
	 */
	public int getNumeroComprobantesConPlanillas() {
		return numeroComprobantesConPlanillas;
	}

	/**
	 * @param numeroComprobantesConPlanillas el numeroComprobantesConPlanillas a establecer
	 */
	public void setNumeroComprobantesConPlanillas(int numeroComprobantesConPlanillas) {
		this.numeroComprobantesConPlanillas = numeroComprobantesConPlanillas;
	}

	/**
	 * @return el numeroTotalComprobantes
	 */
	public int getNumeroTotalComprobantes() {
		return numeroTotalComprobantes;
	}

	/**
	 * @param numeroTotalComprobantes el numeroTotalComprobantes a establecer
	 */
	public void setNumeroTotalComprobantes(int numeroTotalComprobantes) {
		this.numeroTotalComprobantes = numeroTotalComprobantes;
	}

	 //Mail de aviso al cliente  x existencia de Observaciones o éxito proceso
    public void enviarMail(String[] mailEncargados, String proceso) {
    	String subject="";
		try {			

			String host=mailProperties.getString("smtp.host");
			String port=mailProperties.getString("smtp.port");
			String user=mailProperties.getString("smtp.user");
			String pass=mailProperties.getString("smtp.password");
			EnviarMail mail = new EnviarMail(host, port , user, pass);
				
		     StringBuffer body= new  StringBuffer();
		     subject= " DYNP en proceso " + proceso ;
		     body.append("Señor Usuario: su proceso ha generado planillas de DYNP. <BR>");
		     body.append("Tenga en consideración para la Rendición y Publicación <BR>");
			
			body.append("<br><br>");
			body.append("Saluda atte. a Ud. "+"<BR>");
			body.append("La Araucana - Soluciones Sociales.");

		  	mail.mailTo("aplica@laaraucana.cl", mailEncargados, null, null , subject, body.toString());
		  	
		  	System.out.println(".............. EMAIL GENERADO .................... " );
		  	
			}catch(Exception e) {
				System.out.println("CAI EN MAIL  " );
				e.printStackTrace();
			}
 	 }
}

