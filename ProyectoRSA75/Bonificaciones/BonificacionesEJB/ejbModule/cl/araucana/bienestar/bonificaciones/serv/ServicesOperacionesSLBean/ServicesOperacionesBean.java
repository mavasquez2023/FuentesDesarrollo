package cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesSLBean;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import cl.araucana.bienestar.bonificaciones.common.Constants;
import cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO;
import cl.araucana.bienestar.bonificaciones.dao.DAOFactory;
import cl.araucana.bienestar.bonificaciones.dao.ParametrosDAO;
import cl.araucana.bienestar.bonificaciones.model.Concepto;
import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.DetalleCaso;
import cl.araucana.bienestar.bonificaciones.model.InformacionAsiento;
import cl.araucana.bienestar.bonificaciones.model.Parametro;
import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.AporteBienestarVO;
import cl.araucana.bienestar.bonificaciones.vo.CasosDescontadosSinFormatoVO;
import cl.araucana.bienestar.bonificaciones.vo.CasosDescontadosVO;
import cl.araucana.bienestar.bonificaciones.vo.ContabilidadPendienteVO;
import cl.araucana.bienestar.bonificaciones.vo.ContabilidadVO;
import cl.araucana.bienestar.bonificaciones.vo.CuotaPrestamoVO;
import cl.araucana.bienestar.bonificaciones.vo.CuotasPrestamoDescontadasVO;
import cl.araucana.bienestar.bonificaciones.vo.DeglosePagoConvenioVO;
import cl.araucana.bienestar.bonificaciones.vo.DescuentoTotalSocioVO;
import cl.araucana.bienestar.bonificaciones.vo.DescuentosVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleAporteBienestarVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleBancoVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleCasoDescontadoVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleDescuentosConveniosVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleDescuentosOficinaVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleDescuentosSocioVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleInformeReembolsosVO;
import cl.araucana.bienestar.bonificaciones.vo.InformeDescuentosConveniosVO;
import cl.araucana.bienestar.bonificaciones.vo.InformeDescuentosVO;
import cl.araucana.bienestar.bonificaciones.vo.InformePagoConvenioVO;
import cl.araucana.bienestar.bonificaciones.vo.InformeReemBancoVO;
import cl.araucana.bienestar.bonificaciones.vo.InformeReembolsosVO;
import cl.araucana.bienestar.bonificaciones.vo.PagoConPrestamoVO;
import cl.araucana.bienestar.bonificaciones.vo.PagoConvenioPendienteCuotaVO;
import cl.araucana.bienestar.bonificaciones.vo.PagoConvenioPendienteVO;
import cl.araucana.bienestar.bonificaciones.vo.PagoConvenioVO;
import cl.araucana.bienestar.bonificaciones.vo.ParamMailVO;
import cl.araucana.bienestar.bonificaciones.vo.ParamResumenMovimientosVO;
import cl.araucana.bienestar.bonificaciones.vo.ParametroVO;
import cl.araucana.bienestar.bonificaciones.vo.ReembolsoSocioVO;
import cl.araucana.bienestar.bonificaciones.vo.ReembolsoTotalVO;
import cl.araucana.bienestar.bonificaciones.vo.ReembolsoVO;
import cl.araucana.bienestar.bonificaciones.vo.UsuarioVO;
import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.contabilidad.model.Asiento;
import cl.araucana.contabilidad.model.Linea;
import cl.araucana.contabilidad.serv.ServicesContabilidadDelegate;
import cl.araucana.personal.serv.ServicesEmpleadosDelegate;
import cl.araucana.personal.vo.BancoVO;
import cl.araucana.personal.vo.DescuentoVO;
import cl.araucana.prestamo.serv.ServicesPrestamoDelegate;
import cl.araucana.prestamo.vo.CuotaVO;
import cl.araucana.tesoreria.dao.DB2ComprobanteDAO;
import cl.araucana.tesoreria.model.Comprobante;
import cl.araucana.tesoreria.model.Detalle;
import cl.araucana.tesoreria.serv.ServicesTesoreriaDelegate;

import com.schema.util.FileSettings;


/**
 * @author aituarte
 * Bean implementation class for Enterprise Bean: ServicesOperacion
 * Servicios de Consulta a Información de Socios de Bienestar de La Araucana
 */
public class ServicesOperacionesBean implements javax.ejb.SessionBean {

	/** Serial */
	private static final long serialVersionUID = 1L;

	
	private BonificacionesDAO bonificacionesDao;
	private ParametrosDAO parametrosDao;
	
	//private static Logger logger = LogManager.getLogger();
	Logger logger = Logger.getLogger(ServicesOperacionesBean.class);
	
	private javax.ejb.SessionContext mySessionCtx;
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
		// Recurso DAO de Bonificaciones
		int bonificacionesDaoType = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/bonificaciones/dao-type"));	
		// Recurso DAO de Parametros
		int parametrosDaoType = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/bonificaciones/parametro-dao-type"));
		try {
			//DAO Bonificaciones
			DAOFactory daoFactory = (DAOFactory)DAOFactory.getDAOFactory(bonificacionesDaoType);
			bonificacionesDao = daoFactory.getBonificacionesDAO();
			//DAO Parametros
			daoFactory = (DAOFactory)DAOFactory.getDAOFactory(parametrosDaoType);
			parametrosDao = daoFactory.getParametrosDAO();
		} catch (Exception e) {
			throw new javax.ejb.CreateException(e.getMessage());
		}

	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	/** 
	 * Obtiene la lista de conceptos existentes
	 * @param tipo de concepto
	 * @return ArrayList de Concepto
	 * @throws Exception
	 */
	public ArrayList getConceptos() throws Exception, BusinessException {
		return bonificacionesDao.getConceptos();
	}
	
	/** 
	 * Obtiene un concepto existente
	 * @param codigo y tipo de concepto
	 * @return Concepto
	 * @throws Exception
	 */
	public Concepto getConcepto(long codigo) throws Exception, BusinessException {
		return bonificacionesDao.getConcepto(codigo);
	}
	
	/**
	 * Crea un nuevo concepto en Bienestar
	 * @param concepto: el Objeto Concepto
	 */
	public void creaConcepto(Concepto concepto) throws BusinessException,Exception {
		logger.debug("Concepto Codigo: " + concepto.getCodigo());
		concepto.setFechaCreacion(new Date());
		bonificacionesDao.insertConcepto(concepto);
	}
	
	/**
	 * Actualiza un concepto de Bienestar
	 * @param concepto: el Objeto Concepto
	 */
	public void actualizaConcepto(Concepto concepto) throws Exception, BusinessException{
		bonificacionesDao.updateConcepto(concepto);
	}
	
	/**
	 * Elimina un concepto de Bienestar
	 * @param concepto: el Objeto Concepto
	 */
	public void eliminaConcepto(Concepto concepto) throws Exception,BusinessException{


		Convenio convenio = new Convenio();
		convenio.setCodigoConcepto(concepto.getCodigo());
		convenio.setEstado(Convenio.STD_ACTIVO);
		if (bonificacionesDao.getConvenios(convenio,0).size() > 0)
			throw new BusinessException("CCAF_BONIF_CONCEPTOINVALIDO",
					"No se puede Eliminar el Concepto, ya que, tiene Convenios relacionados");

		bonificacionesDao.deleteConcepto(concepto);
	}

	
	/**
	 * Genera los reembolsos para los casos pasados en el array de casos ID
	 * @param casos
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void generarReembolsos(ArrayList reembolsos,UsuarioVO usuario) throws Exception, BusinessException {
		
		//Código de Oficina para Tesoreria Bienestar
		int oficinaBienestar = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/param/bienestar/oficina"));

		if (oficinaBienestar == 0)
			throw new BusinessException("CCAF_TESO_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: bienestar/oficina en el Sistema");
		
		long codigoReembolso=0;
//		String estadoPrevio = Caso.STD_ACTIVO;
//		String indicadorPrevio = Caso.ESTADOINDICADOR_NO;
//		int filasActualizadas=0;
		String usuarioCreador = usuario.getUsuario();
		int codigoOficinaUsuario = Integer.parseInt(usuario.getCodigoOficina());
		
		if (reembolsos == null)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
				"La información es nula");
				
		//Servicio de TesoreriaEJB
		ServicesTesoreriaDelegate tesoreria = new ServicesTesoreriaDelegate();
		
		//Servicio de CasosEJB
		ServicesCasosDelegate caso = new ServicesCasosDelegate();
				
		//genero un nuevo codigo de Reembolso(En Bienestar). Es el mismo 
		//para todos los movimientos que se están procesando.
		ReembolsoTotalVO reembolsoTotal = new ReembolsoTotalVO();
		reembolsoTotal.setFecha(new Date());
		reembolsoTotal.setUsuario(usuario.getUsuario());
		codigoReembolso=bonificacionesDao.insertReembolsoTotal(reembolsoTotal);
		reembolsoTotal.setCodigo(codigoReembolso);
		bonificacionesDao.insertEmailReembolsoTotal(reembolsoTotal);		
		
		//Actualiza indicadores de los Casos en la tabla casos de Bienestar
		caso.actualizaIndicadorReembolso(reembolsos);
			
		//Prepara Información para Tesorerías, la devuelve en un arreglo
		//que contiene dos arreglos: el primero con la información para tesoreria
		//de Bienestar y el segundo para Tesorería de La Araucana
		ArrayList informacion  = preparaInformacionTesorerias(reembolsos, codigoReembolso,usuarioCreador);
		
		ArrayList listaComprobantesBienestar = (ArrayList)informacion.get(0);
		//Comentado por requerimiento La Araucana (12/04/2013 - Gabriel Criado)
		//ArrayList listaComprobantesAraucana = (ArrayList)informacion.get(1);
		
		//Envía Información a Tesorería Bienestar
		//Actualiza la lista de reembolsos con los folios de tesoreria de Bienestar
		reembolsos = informarReembolsosTesoreria(listaComprobantesBienestar,reembolsos,DB2ComprobanteDAO.TESORERIA_BIENESTAR);
		
		//Envía Información a Tesorería Araucana
		//Actualiza la lista de reembolsos con los folios de tesoreria de la Araucana
		//Comentado por requerimiento La Araucana (12/04/2013 - Gabriel Criado)
		//reembolsos = informarReembolsosTesoreria(listaComprobantesAraucana,reembolsos,DB2ComprobanteDAO.TESORERIA_ARAUCANA);
		
		//Registro los casos con los folios de las Tesorerias en la tabla de
		//reembolsos de Bienestar. Además calculo el monto total reembolsado.
		for (int x=0;x<reembolsos.size();x++) {
			ReembolsoVO casoReembolso = (ReembolsoVO) reembolsos.get(x);
			reembolsoTotal.setTotal(reembolsoTotal.getTotal()+(int)casoReembolso.getMontoReembolso());
			casoReembolso.setCodigoReembolso(codigoReembolso);
			bonificacionesDao.insertReembolso(casoReembolso);
			bonificacionesDao.insertInfoAdiReembolso(casoReembolso);
		}
		
		//Genero Egreso por el Total Reembolsado en Tesoreria Bienestar
		Comprobante comprobanteEgresoBienestar = egresoTotalBienestar(reembolsoTotal.getTotal(),codigoOficinaUsuario,usuarioCreador);
		comprobanteEgresoBienestar.setCodigoOficina(oficinaBienestar);
		reembolsoTotal.setFolioEgresoBienestar(tesoreria.registrarMovimientoTesoreriaBienestar(comprobanteEgresoBienestar));
		
		//Genero Ingreso por el Total Reembolsado en Tesoreria Bienestar
		Comprobante comprobanteIngresoBienestar = ingresoTotalBienestar(reembolsoTotal.getTotal(),codigoOficinaUsuario,usuarioCreador);
		comprobanteIngresoBienestar.setCodigoOficina(oficinaBienestar);
		reembolsoTotal.setFolioIngresoBienestar(tesoreria.registrarMovimientoTesoreriaBienestar(comprobanteIngresoBienestar));
		
		//Genero Ingreso por el Total Reembolsado en Tesoreria Araucana
		//Comentado por requerimiento La Araucana (12/04/2013 - Gabriel Criado)
		//Comprobante comprobanteIngresoAraucana = ingresoTotalAraucana(reembolsoTotal.getTotal(),codigoOficinaUsuario,usuarioCreador);
		//reembolsoTotal.setFolioIngresoAraucana(tesoreria.registrarMovimientoTesoreriaAraucana(comprobanteIngresoAraucana));

		
		
		
		//Actualizo tabla de reembolsos totales en Bienestar con:
		//Monto Total Reembolsado (Proceso)
		//Folios de Egreso e Ingreso de Tesoreria de Bienestar
		//Folio de Ingreso de Tesoreria de la Araucana
		bonificacionesDao.updateReembolsoTotal(reembolsoTotal);
	}
	
	/**
	 * Prepara Información para Tesorerías
	 * @param reembolsos
	 * @param codigoReembolso
	 * @return ArrayList con información de los Reembolsos
	 */
	public ArrayList preparaInformacionTesorerias(ArrayList reembolsos, long codigoReembolso, String usuarioCreador)  throws Exception, BusinessException {
	
		//Glosa para los Reembolsos de Bienestar (en ambas tesorerias)
		String glosaReembolsos = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/bonificaciones/param/glosa-reembolsos");
				 
		//Area de Negocios de Bonificaciones en Tesoreria Bienestar
		int areaNegocioBonificacionesBienestar = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/param/bienestar/area-negocio-bonificaciones"));
				 
		if (areaNegocioBonificacionesBienestar == 0)
			throw new BusinessException("CCAF_TESO_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: bienestar/area-negocio-bonificaciones en el Sistema");
	 
		//Area de Negocios de Bonificaciones en Tesoreria Araucana
		int areaNegocioBonificacionesAraucana = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/param/araucana/area-negocio-bonificaciones"));
				 
		if (areaNegocioBonificacionesAraucana == 0)
			throw new BusinessException("CCAF_TESO_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: araucana/area-negocio-bonificaciones en el Sistema");
				 
		ArrayList informacion = new ArrayList();
		ArrayList listaComprobantesBienestar = new ArrayList();
		ArrayList listaComprobantesAraucana = new ArrayList();
		ArrayList listaRutReembolso = new ArrayList();
		
		for (int x=0; x < reembolsos.size(); x++) {
			ReembolsoVO casoReembolso = (ReembolsoVO)reembolsos.get(x); 
			if (casoReembolso.getCasoId() > 0) {

				//Busco los Detalles del Caso
				ArrayList detallesCaso = bonificacionesDao.getDetallesCaso(casoReembolso.getCasoId());
						 			
				if (!listaRutReembolso.contains(casoReembolso.getRut())) {
					//Si el rut no existe aún en la lista lo agrego
					//y agrego el Comprobante de Reembolso a la lista
					Comprobante comprobanteReembolsoBienestar = new Comprobante();	
					Comprobante comprobanteReembolsoAraucana = new Comprobante();
					listaRutReembolso.add(casoReembolso.getRut());

					//Prepara comprobante para tesoreria de Bienestar
					comprobanteReembolsoBienestar=preparaComprobante(casoReembolso, areaNegocioBonificacionesBienestar,glosaReembolsos,usuarioCreador);
					//Prepara comprobante para tesoreria de La Araucana
					comprobanteReembolsoAraucana=preparaComprobante(casoReembolso, areaNegocioBonificacionesAraucana,glosaReembolsos,usuarioCreador);
					
					//Prepara Detalles (datos comunes) para tesoreria de Bienestar
					comprobanteReembolsoBienestar.setDatosComunes(preparaDetalle(detallesCaso, DB2ComprobanteDAO.TESORERIA_BIENESTAR,usuarioCreador));
					//Prepara comprobante para tesoreria de La Araucana
					comprobanteReembolsoAraucana.setDatosComunes(preparaDetalle(detallesCaso, DB2ComprobanteDAO.TESORERIA_ARAUCANA,usuarioCreador));
					
					//Agrega el nuevo comprobante a la lista de comprobantes de Bienestar
					listaComprobantesBienestar.add(comprobanteReembolsoBienestar);
					//Agrega el nuevo comprobante a la lista de comprobantes de La Araucana
					listaComprobantesAraucana.add(comprobanteReembolsoAraucana);
					
				}else {
					int index = listaRutReembolso.indexOf(casoReembolso.getRut());
					Comprobante comprobanteReembolsoBienestar = (Comprobante) listaComprobantesBienestar.get(index);	
					Comprobante comprobanteReembolsoAraucana = (Comprobante) listaComprobantesAraucana.get(index);

					//Actualiza monto del Comprobante de Bienestar
					comprobanteReembolsoBienestar.setMontoInformado(comprobanteReembolsoBienestar.getMontoInformado() + (int)casoReembolso.getMontoReembolso());
					comprobanteReembolsoBienestar.setMontoEmitido(comprobanteReembolsoBienestar.getMontoInformado());
					//Actualiza monto del Comprobante de La Araucana
					comprobanteReembolsoAraucana.setMontoInformado(comprobanteReembolsoAraucana.getMontoInformado() + (int)casoReembolso.getMontoReembolso());
					comprobanteReembolsoAraucana.setMontoEmitido(comprobanteReembolsoAraucana.getMontoInformado());
					
					//Se agregan detalles al comprobante de Bienestar
					comprobanteReembolsoBienestar.getDatosComunes().addAll(preparaDetalle(detallesCaso, DB2ComprobanteDAO.TESORERIA_BIENESTAR, usuarioCreador));
					//Se agregan detalles al comprobante de La Araucana
					comprobanteReembolsoAraucana.getDatosComunes().addAll(preparaDetalle(detallesCaso, DB2ComprobanteDAO.TESORERIA_ARAUCANA, usuarioCreador));
					
					//Agrega el nuevo comprobante a la lista de comprobantes de Bienestar
					listaComprobantesBienestar.set(index,comprobanteReembolsoBienestar);
					//Agrega el nuevo comprobante a la lista de comprobantes de La Araucana
					listaComprobantesAraucana.set(index,comprobanteReembolsoAraucana);
				}
			}
		}
		//Agrega la información de la lista de comprobantes de Bienestar
		informacion.add(listaComprobantesBienestar);
		//Agrega la información de la lista de comprobantes de La Araucana
		informacion.add(listaComprobantesAraucana);
		return informacion;
	}
	
	/**
	 * 
	 * @param casoReembolso
	 * @param areaNegocio
	 * @param glosaReembolsos
	 * @return
	 */
	public Comprobante preparaComprobante(ReembolsoVO casoReembolso, int areaNegocio, String glosaReembolsos, String usuarioCreador) {
		
		Comprobante comprobanteReembolso = new Comprobante();
				
		//Comprobante
		comprobanteReembolso.setTipoMovimiento(Comprobante.TPO_MOVI_EGRESO);
		comprobanteReembolso.setEstadoMovimientoCaja(Comprobante.STD_MOV_CAJA_GENERADO);
		comprobanteReembolso.setFormaPago(Comprobante.FORMA_PAGO_CAJA);
		comprobanteReembolso.setRut1(Integer.parseInt(casoReembolso.getRut()));
		comprobanteReembolso.setDv1(casoReembolso.getDv());
		comprobanteReembolso.setNombreRut1(casoReembolso.getFullNombre());
		comprobanteReembolso.setMontoInformado((int)casoReembolso.getMontoReembolso());
		comprobanteReembolso.setMontoEmitido((int)casoReembolso.getMontoReembolso());
		comprobanteReembolso.setObservaciónMovimientoCaja(glosaReembolsos);
		comprobanteReembolso.setSucursal(0);
		comprobanteReembolso.setEstadoAutorizacion(Comprobante.STD_AUTORIZACION_AUTORIZADO);
		comprobanteReembolso.setTipoPago(Comprobante.TIPO_PAGO_EFECTIVO);
		comprobanteReembolso.setEmiteFactura(Comprobante.EMITE_FACTURA_NO);
		comprobanteReembolso.setCodigoOficina(Integer.parseInt(casoReembolso.getOficina()));
		comprobanteReembolso.setCorrelativoPago(0);
		comprobanteReembolso.setCodigoAreaNegocio(areaNegocio);
		comprobanteReembolso.setSerPagadoPorCodigoOficina(Integer.parseInt(casoReembolso.getOficina()));
		comprobanteReembolso.setUsuarioCreoRegistro(usuarioCreador);
		
		return comprobanteReembolso;
	}
	
	
	/**
	 * 
	 * @param detallesCaso
	 * @param comprobanteReembolso
	 * @param tesoreria
	 * @return
	 */
	private ArrayList preparaDetalle(ArrayList detallesCaso, int tesoreria, String usuarioCreador) throws Exception, BusinessException {
		
		//Codigo de Concepto de Bonificaciones en Tesoreria Bienestar
		int codigoConceptoBonificacionesBienestar = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/param/bienestar/conceptos-bonificaciones/bonificaciones-por-pagar-socios"));
				 
		if (codigoConceptoBonificacionesBienestar == 0)
			throw new BusinessException("CCAF_TESO_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: bienestar/conceptos-bonificaciones/bonificaciones-por-pagar-socios en el Sistema");
				
		//Codigo de Concepto de Bonificaciones (egreso) en Tesoreria Araucana
		int codigoConceptoBonificacionesAraucanaEgreso = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/param/araucana/conceptos-bonificaciones/egresos"));
				 
		if (codigoConceptoBonificacionesAraucanaEgreso == 0)
			throw new BusinessException("CCAF_TESO_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: araucana/conceptos-bonificaciones/egresos en el Sistema");

		
		ArrayList detalles = new ArrayList();
		
		for (int x=0;x<detallesCaso.size();x++) {
			DetalleCaso detalle = (DetalleCaso)detallesCaso.get(x);
			Detalle datosComunes = new Detalle();
			//Genero los Datos Comunes (Detalles)
			if (detalle.getAporteBienestar() > 0) {
				datosComunes.setMontoDetalle((int)detalle.getAporteBienestar());
				datosComunes.setObservaciónMovimientoDetalle(detalle.getProducto().getCobertura().getDescripcion());
				datosComunes.setDocumentoRespaldo("O");
				datosComunes.setCantidadDocumentos(1);
				datosComunes.setMontoPagoCheque(0);
				datosComunes.setNumeroCaratula(0);
				datosComunes.setCreationUser(usuarioCreador);
				switch (tesoreria) {
					case DB2ComprobanteDAO.TESORERIA_BIENESTAR:
						datosComunes.setMontoPagoEfectivo(datosComunes.getMontoDetalle());
						datosComunes.setCodigoConcepto(codigoConceptoBonificacionesBienestar);
						break;
					case DB2ComprobanteDAO.TESORERIA_ARAUCANA:
						datosComunes.setMontoPagoEfectivo(0);
						datosComunes.setCodigoConcepto(codigoConceptoBonificacionesAraucanaEgreso);
						break;
					default:
						throw new BusinessException("CCAF_BONIF_REEMBOLSOINVALIDO",
							"La información del Sistema de Tesorería seleccionado es incorrecta");
				}
				detalles.add(datosComunes);
			}
		}
		return detalles;
	}
		
	/**
	 * Envía la información de los Reembolsos a Tesorería de Bienestar
	 * @param listaComprobantes
	 * @param reembolsos
	 * @param tesoreria: indica a que tesorería se están enviando los movimientos
	 * @return Lista de reembolsos con los folios de Tesoreria de Bienestar Actualizados
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList informarReembolsosTesoreria(ArrayList listaComprobantes, ArrayList reembolsos, int tesoreria) throws Exception, BusinessException {
		
		//Código de Oficina para Tesoreria Bienestar
		int oficinaBienestar = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/param/bienestar/oficina"));
		
		/* Servicio de TesoreriaEJB */
		ServicesTesoreriaDelegate tesoreriaDelegate = new ServicesTesoreriaDelegate();

		//Envía Información a Tesorería
		for (int x=0; x<listaComprobantes.size();x++) {
			Comprobante comprobanteReembolso = (Comprobante) listaComprobantes.get(x);
			long folio=0;
			//Registra Movimiento de Reembolso en el sistema de Tesoreria indicado
			switch (tesoreria) {
				case DB2ComprobanteDAO.TESORERIA_BIENESTAR:
					comprobanteReembolso.setCodigoOficina(oficinaBienestar);
					folio = tesoreriaDelegate.registrarMovimientoTesoreriaBienestar(comprobanteReembolso);
					break;
				case DB2ComprobanteDAO.TESORERIA_ARAUCANA:
					folio = tesoreriaDelegate.registrarMovimientoTesoreriaAraucana(comprobanteReembolso);
					break;
				default:
					throw new BusinessException("CCAF_BONIF_REEMBOLSOINVALIDO",
						"La información del Sistema de Tesorería seleccionado es incorrecta");
			}
			
			//Actualiza la información de Bienestar con el folio generado
			for (int y=0;y<reembolsos.size();y++) {
				ReembolsoVO casoReembolso = (ReembolsoVO) reembolsos.get(y);
				if (comprobanteReembolso.getRut1() == Integer.parseInt(casoReembolso.getRut())) {
					switch (tesoreria) {
						case DB2ComprobanteDAO.TESORERIA_BIENESTAR:
							casoReembolso.setFolioTesoreriaBienestar(folio);
							break;
						case DB2ComprobanteDAO.TESORERIA_ARAUCANA:
							casoReembolso.setFolioTesoreriaAraucana(folio);
							break;
						default:
							throw new BusinessException("CCAF_BONIF_REEMBOLSOINVALIDO",
								"La información del Sistema de Tesorería seleccionado es incorrecta");
					}
					reembolsos.set(y,casoReembolso);	 
				}
			}
		}
		return reembolsos;
	}
		
	/**
	 * Envía la información del Egreso Total por los Reembolsos realizados
	 * a Tesorería de Bienestar
	 * @param totalReembolsos
	 * @param codigoOficina
	 * @return
	 */	
	public Comprobante egresoTotalBienestar(int totalReembolsos,int codigoOficinaUsuario, String usuarioCreador) throws Exception, BusinessException  {
		
		//Rut de La Araucana
		String  rutAraucana = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/rut-araucana");
					  
		if (rutAraucana == null || rutAraucana.length()== 0)
			throw new BusinessException("CCAF_TESO_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: bonificaciones/param/rut-araucana en el Sistema");
		
		StringTokenizer st = new StringTokenizer(rutAraucana,"-");				
		long rutArau=Long.parseLong(st.nextToken());
		String dvArau = rutAraucana.substring(rutAraucana.length()-1);
		
		//Nombre de La Araucana
		String  nombreAraucana = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/nombre-araucana");
		
		//Area de Negocios de Bonificaciones en Tesoreria Bienestar
		int areaNegocioBonificacionesBienestar = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/param/bienestar/area-negocio-bonificaciones"));
				 
		if (areaNegocioBonificacionesBienestar == 0)
			throw new BusinessException("CCAF_TESO_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: bienestar/area-negocio-bonificaciones en el Sistema");
				 
		//Código de Concepto de Bonificaciones en Tesoreria Bienestar
		int codigoConceptoBonificacionesBienestar = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/param/bienestar/conceptos-bonificaciones/bonificaciones-por-pagar-socios"));
				 
		if (codigoConceptoBonificacionesBienestar == 0)
			throw new BusinessException("CCAF_TESO_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: bienestar/conceptos-bonificaciones/bonificaciones-por-pagar-socios en el Sistema");
				 
		//Glosa para los Reembolsos (en ambas tesorerias)
		String glosaReembolsos = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/bonificaciones/param/glosa-reembolsos");
		
		//Prepara el Comprobante de Egreso por el Total en Tesoreria Bienestar
		Comprobante comprobanteReembolso = new Comprobante();

		//Comprobante
		comprobanteReembolso.setTipoMovimiento(Comprobante.TPO_MOVI_EGRESO);
		comprobanteReembolso.setEstadoMovimientoCaja(Comprobante.STD_MOV_CAJA_GENERADO);
		comprobanteReembolso.setFormaPago(Comprobante.FORMA_PAGO_CAJA);
		comprobanteReembolso.setRut1(rutArau);
		comprobanteReembolso.setDv1(dvArau);
		comprobanteReembolso.setNombreRut1(nombreAraucana);
		comprobanteReembolso.setMontoInformado(totalReembolsos);
		comprobanteReembolso.setMontoEmitido(totalReembolsos);
		comprobanteReembolso.setObservaciónMovimientoCaja(glosaReembolsos);
		comprobanteReembolso.setSucursal(0);
		comprobanteReembolso.setEstadoAutorizacion(Comprobante.STD_AUTORIZACION_AUTORIZADO);
		//comprobanteReembolso.setTipoPago(Comprobante.TIPO_PAGO_CHEQUE); 
		comprobanteReembolso.setTipoPago(Comprobante.TIPO_PAGO_EFECTIVO); //2013-08-05 ahora es efectivo
		comprobanteReembolso.setEmiteFactura(Comprobante.EMITE_FACTURA_NO);
		comprobanteReembolso.setCodigoOficina(codigoOficinaUsuario);
		comprobanteReembolso.setCorrelativoPago(0);
		comprobanteReembolso.setCodigoAreaNegocio(areaNegocioBonificacionesBienestar);
		comprobanteReembolso.setSerPagadoPorCodigoOficina(codigoOficinaUsuario);
		comprobanteReembolso.setUsuarioCreoRegistro(usuarioCreador);
		
		//Genero los Datos Comunes (Detalles)
		Detalle datosComunes = new Detalle();
		datosComunes.setMontoDetalle(totalReembolsos);
		datosComunes.setObservaciónMovimientoDetalle("");
		datosComunes.setMontoPagoEfectivo(datosComunes.getMontoDetalle());
		datosComunes.setDocumentoRespaldo("O");
		datosComunes.setCantidadDocumentos(1);
		datosComunes.setMontoPagoCheque(0);
		datosComunes.setNumeroCaratula(0);
		datosComunes.setCodigoConcepto(codigoConceptoBonificacionesBienestar);
		datosComunes.setCreationUser(usuarioCreador);
		
		comprobanteReembolso.getDatosComunes().add(datosComunes);
		
		return comprobanteReembolso;
	}
	
	
	/**
	 * Envía la información del Ingreso Total por los Reembolsos realizados
	 * a Tesorería de Bienestar 
	 * @param totalReembolsos
	 * @param codigoOficina
	 * @return
	 */
	public Comprobante ingresoTotalBienestar(int totalReembolsos,int codigoOficinaUsuario, String usuarioCreador) throws Exception, BusinessException {
		
		//Rut de Bienestar
		String  rutBienestar = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/rut-bienestar");
					  
		if (rutBienestar == null || rutBienestar.length() == 0)
			throw new BusinessException("CCAF_TESO_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: bonificaciones/param/rut-bienestar en el Sistema");
		
		StringTokenizer st = new StringTokenizer(rutBienestar,"-");				
		long rutBie=Long.parseLong(st.nextToken());
		String dvBie = rutBienestar.substring(rutBienestar.length()-1);
		
		//Nombre de Bienestar
		String  nombreBienestar = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/nombre-bienestar");
		
		//Area de Negocios de Bonificaciones en Tesoreria Bienestar
		int areaNegocioBonificacionesBienestar = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/param/bienestar/area-negocio-bonificaciones"));
				 
		if (areaNegocioBonificacionesBienestar == 0)
			throw new BusinessException("CCAF_TESO_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: bienestar/area-negocio-bonificaciones en el Sistema");
				 
		//Código de Concepto de Bonificaciones en Tesoreria Bienestar
		int codigoConceptoBonificacionesBienestar = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/param/bienestar/conceptos-bonificaciones/bonificaciones-por-pagar-araucana"));
				 
		if (codigoConceptoBonificacionesBienestar == 0)
			throw new BusinessException("CCAF_TESO_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: conceptos-bonificaciones/bonificaciones-por-pagar-araucana en el Sistema");
				 
		//Glosa para los Reembolsos (en ambas tesorerias)
		String glosaReembolsos = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/bonificaciones/param/glosa-reembolsos");
		
		//Prepara el Ingreso por el Total en Tesoreria Bienestar
		Comprobante comprobanteReembolso = new Comprobante();

		//Comprobante
		comprobanteReembolso.setTipoMovimiento(Comprobante.TPO_MOVI_INGRESO);
		comprobanteReembolso.setEstadoMovimientoCaja(Comprobante.STD_MOV_CAJA_GENERADO);
		comprobanteReembolso.setFormaPago(Comprobante.FORMA_PAGO_CAJA);
		comprobanteReembolso.setRut1(rutBie);
		comprobanteReembolso.setDv1(dvBie);
		comprobanteReembolso.setNombreRut1(nombreBienestar);
		comprobanteReembolso.setMontoInformado(totalReembolsos);
		comprobanteReembolso.setMontoEmitido(totalReembolsos);
		comprobanteReembolso.setObservaciónMovimientoCaja(glosaReembolsos);
		comprobanteReembolso.setSucursal(0);
		comprobanteReembolso.setEstadoAutorizacion(Comprobante.STD_AUTORIZACION_AUTORIZADO);
		comprobanteReembolso.setTipoPago(Comprobante.TIPO_PAGO_EFECTIVO);
		comprobanteReembolso.setEmiteFactura(Comprobante.EMITE_FACTURA_NO);
		comprobanteReembolso.setCodigoOficina(codigoOficinaUsuario);
		comprobanteReembolso.setCorrelativoPago(0);
		comprobanteReembolso.setCodigoAreaNegocio(areaNegocioBonificacionesBienestar);
		comprobanteReembolso.setSerPagadoPorCodigoOficina(codigoOficinaUsuario);
		comprobanteReembolso.setUsuarioCreoRegistro(usuarioCreador);
		
		//Genero los Datos Comunes (Detalles)
		Detalle datosComunes = new Detalle();
		datosComunes.setMontoDetalle(totalReembolsos);
		datosComunes.setObservaciónMovimientoDetalle("");
		datosComunes.setMontoPagoEfectivo(datosComunes.getMontoDetalle());
		datosComunes.setDocumentoRespaldo("O");
		datosComunes.setCantidadDocumentos(1);
		datosComunes.setMontoPagoCheque(0);
		datosComunes.setNumeroCaratula(0);
		datosComunes.setCodigoConcepto(codigoConceptoBonificacionesBienestar);
		datosComunes.setCreationUser(usuarioCreador);
		
		comprobanteReembolso.getDatosComunes().add(datosComunes);
		
		return comprobanteReembolso;
	}
	
	
	/**
	 * Envía la información del Ingreso Total por los Reembolsos realizados
	 * a Tesorería de Bienestar
	 * @param totalReembolsos
	 * @param codigoOficina
	 * @return
	 */
	public Comprobante ingresoTotalAraucana(int totalReembolsos,int codigoOficinaUsuario, String usuarioCreador) throws BusinessException {
		
		//Rut de Bienestar
		String  rutBienestar = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/rut-bienestar");
		
		StringTokenizer st = new StringTokenizer(rutBienestar,"-");				
		long rutBie=Long.parseLong(st.nextToken());
		String dvBie = rutBienestar.substring(rutBienestar.length()-1);
		
		//Nombre de Bienestar
		String  nombreBienestar = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/nombre-bienestar");
		
		//Area de Negocios de Bonificaciones en Tesoreria Araucana
		int areaNegocioBonificacionesAraucana = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/param/araucana/area-negocio-bonificaciones"));
	
		//Código de Concepto de Bonificaciones en Tesoreria Araucana
		int codigoConceptoBonificacionesAraucanaIngreso = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/param/araucana/conceptos-bonificaciones/ingresos"));
				 
		if (codigoConceptoBonificacionesAraucanaIngreso == 0)
			throw new BusinessException("CCAF_TESO_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: araucana/conceptos-bonificaciones/ingresos en el Sistema");

				 
		//Glosa para los Reembolsos (en ambas tesorerias)
		String glosaReembolsos = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/bonificaciones/param/glosa-reembolsos");
		
		//Prepara el Ingreso por el Total en Tesoreria Araucana
		Comprobante comprobanteReembolso = new Comprobante();

		//Comprobante
		comprobanteReembolso.setTipoMovimiento(Comprobante.TPO_MOVI_INGRESO);
		comprobanteReembolso.setEstadoMovimientoCaja(Comprobante.STD_MOV_CAJA_GENERADO);
		comprobanteReembolso.setFormaPago(Comprobante.FORMA_PAGO_CAJA);
		comprobanteReembolso.setRut1(rutBie);
		comprobanteReembolso.setDv1(dvBie);
		comprobanteReembolso.setNombreRut1(nombreBienestar);
		comprobanteReembolso.setMontoInformado(totalReembolsos);
		comprobanteReembolso.setMontoEmitido(totalReembolsos);
		comprobanteReembolso.setObservaciónMovimientoCaja(glosaReembolsos);
		comprobanteReembolso.setSucursal(0);
		comprobanteReembolso.setEstadoAutorizacion(Comprobante.STD_AUTORIZACION_AUTORIZADO);
		comprobanteReembolso.setTipoPago(Comprobante.TIPO_PAGO_EFECTIVO);
		comprobanteReembolso.setEmiteFactura(Comprobante.EMITE_FACTURA_NO);
		comprobanteReembolso.setCodigoOficina(codigoOficinaUsuario);
		comprobanteReembolso.setCorrelativoPago(0);
		comprobanteReembolso.setCodigoAreaNegocio(areaNegocioBonificacionesAraucana);
		comprobanteReembolso.setSerPagadoPorCodigoOficina(codigoOficinaUsuario);
		comprobanteReembolso.setUsuarioCreoRegistro(usuarioCreador);
		
		//Genero los Datos Comunes (Detalles)
		Detalle datosComunes = new Detalle();
		datosComunes.setMontoDetalle(totalReembolsos);
		datosComunes.setObservaciónMovimientoDetalle("");
		datosComunes.setMontoPagoEfectivo(0);
		datosComunes.setDocumentoRespaldo("O");
		datosComunes.setCantidadDocumentos(1);
		datosComunes.setMontoPagoCheque(0);
		datosComunes.setNumeroCaratula(0);
		datosComunes.setCodigoConcepto(codigoConceptoBonificacionesAraucanaIngreso);
		datosComunes.setCreationUser(usuarioCreador);
		
		comprobanteReembolso.getDatosComunes().add(datosComunes);				

		return comprobanteReembolso;
	}
	
	/**
	 * Recupera las cuotas por cobrar desde el Sistema de Prestamos
	 * y las carga en la tabla de Bienstar para mantener la información 
	 * de los descuentos
	 */
	public void cargarCuotasPrestamos(Date fecha) throws Exception, BusinessException {
				
		SimpleDateFormat formato =
			new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
			
		SimpleDateFormat formatoPeriodo =
			new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
			
		GregorianCalendar periodo = new GregorianCalendar();
		periodo.setTime(fecha);
		int dia = periodo.get(Calendar.DAY_OF_MONTH);
		int mes = periodo.get(Calendar.MONTH)+1;
		int anio = periodo.get(Calendar.YEAR);

		Date periodoConsultado = formato.parse(dia + "/" + mes + "/" + anio);

		//Servicio de PrestamoEJB
		ServicesPrestamoDelegate prestamo = new ServicesPrestamoDelegate();

		ArrayList cuotas = prestamo.getCuotas();
		
		if( cuotas.size() == 0)
			throw new BusinessException(
					"CCAF_BONIF_CUOTAPRESTAMOINVALIDO",
					"No existe información de las Cuotas Informadas por Préstamos. ");
		
		for (int x = 0; x < cuotas.size(); x++) {
			
			CuotaPrestamoVO cuota =  new CuotaPrestamoVO((CuotaVO)cuotas.get(x));
			GregorianCalendar fechaVencimiento = new GregorianCalendar();
			fechaVencimiento.setTime(cuota.getFecha());
			int diaVencimiento = fechaVencimiento.get(Calendar.DAY_OF_MONTH);
			int mesVencimiento = fechaVencimiento.get(Calendar.MONTH)+1;
			int anioVencimiento = fechaVencimiento.get(Calendar.YEAR);
			
			Date vencimientoCuota =
				formato.parse(
					diaVencimiento
						+ "/"
						+ mesVencimiento
						+ "/"
						+ anioVencimiento);
			//Valido Fechas de Vencimiento
			if (periodoConsultado.equals(vencimientoCuota)) {
			//if(true){//
				//Carga Cuotas en Tablas de Bienestar
				try{
					bonificacionesDao.insertCuotaPrestamo(cuota);
				}catch(Exception sqlEx){
					//duplicatedKeyException
					sqlEx.printStackTrace();
				}
			} else
				throw new BusinessException(
					"CCAF_BONIF_CUOTAPRESTAMOINVALIDO",
					"La Información de las Cuotas Informadas por Préstamos, no corresponden al periodo Consultado.<BR> Periodo Consultado: "
						+ formatoPeriodo.format(periodoConsultado)
						+ "<BR> Cuotas Informadas por Préstamos: "
						+ formatoPeriodo.format(vencimientoCuota));
		}
	}
	
	/**
	 * Recupera las cuotas por cobrar desde el Sistema de Prestamos
	 * y las carga en la tabla de Bienstar para mantener la información 
	 * de los descuentos de un afiliado
	 */
	public void cargarCuotasPrestamo(String rut,Date fecha) throws Exception, BusinessException {
				
		SimpleDateFormat formato =
			new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
			
		SimpleDateFormat formatoPeriodo =
			new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
			
		GregorianCalendar periodo = new GregorianCalendar();
		periodo.setTime(fecha);
		int dia = periodo.get(Calendar.DAY_OF_MONTH);
		int mes = periodo.get(Calendar.MONTH)+1;
		int anio = periodo.get(Calendar.YEAR);

		Date periodoConsultado = formato.parse(dia + "/" + mes + "/" + anio);

		//Servicio de PrestamoEJB
		ServicesPrestamoDelegate prestamo = new ServicesPrestamoDelegate();

		ArrayList cuotas = prestamo.getCuotas(rut);
		
		for (int x = 0; x < cuotas.size(); x++) {
			
			CuotaPrestamoVO cuota =  new CuotaPrestamoVO((CuotaVO)cuotas.get(x));
			GregorianCalendar fechaVencimiento = new GregorianCalendar();
			fechaVencimiento.setTime(cuota.getFecha());
			int diaVencimiento = fechaVencimiento.get(Calendar.DAY_OF_MONTH);
			int mesVencimiento = fechaVencimiento.get(Calendar.MONTH)+1;
			int anioVencimiento = fechaVencimiento.get(Calendar.YEAR);
			
			Date vencimientoCuota =
				formato.parse(
					diaVencimiento
						+ "/"
						+ mesVencimiento
						+ "/"
						+ anioVencimiento);
			//Valido Fechas de Vencimiento
			if (periodoConsultado.equals(vencimientoCuota)) {//if(true){
				//Carga Cuotas en Tablas de Bienestar
				try{
					bonificacionesDao.insertCuotaPrestamo(cuota);
				}catch(Exception sqlEx){
					//duplicatedKeyException
					sqlEx.printStackTrace();
				}
			}
			else
				throw new BusinessException(
					"CCAF_BONIF_CUOTAPRESTAMOINVALIDO",
					"La Información de las Cuotas Informadas por Préstamos, no corresponden al periodo Consultado.<BR> Periodo Consultado: "
						+ formatoPeriodo.format(periodoConsultado)
						+ "<BR> Cuotas Informadas por Préstamos: "
						+ formatoPeriodo.format(vencimientoCuota));
		}
	}	
	
	
	/**
	 * Genera los descuentos para los casos pasados en el array de casos ID
	 * @param casos
	 * @param UsuarioVO usuario
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void generarDescuentos(ArrayList descuentos, UsuarioVO usuario) throws Exception, BusinessException {
		
		int  diaVencimientoCuota = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/dia-vencimiento-Cuota"));
										
		long codigoDescuento=0;
		
		SimpleDateFormat formato =
			new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
			
		SimpleDateFormat formatoPeriodo =
			new SimpleDateFormat("yyyyMM", Locale.getDefault());
				
		//Servicio de PersonalEJB
		ServicesEmpleadosDelegate personal = new ServicesEmpleadosDelegate();
		
		//Servicio de CasosEJB
		ServicesCasosDelegate caso = new ServicesCasosDelegate();
		
		if (descuentos == null || descuentos.size() == 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
				"La información es nula");
				
		//Path del Archivo de Descuentos
		String pathArchivoDescuento = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/bonificaciones/param/path-archivo-descuento");
		
		//Prefijo del Nombre del Archivo de Descuentos
		String prefijoArchivoDescuento = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/bonificaciones/param/prefijo-archivo-descuento");
				 
		//Fecha de Descuento		
		Date fecha = new Date();
		
		GregorianCalendar periodoDescuento = new GregorianCalendar();
		periodoDescuento.setTime(fecha);

		Date fechaDescuento = formato.parse(diaVencimientoCuota +
								"/" +
								(periodoDescuento.get(Calendar.MONTH)+1)+
								"/" +
								periodoDescuento.get(Calendar.YEAR));
		
		GregorianCalendar fec = new GregorianCalendar();
		fec.setGregorianChange(fecha);
				
		//Periodo de Descuento
		String periodo = formatoPeriodo.format(fechaDescuento);
		logger.debug("Periodo: "+ periodo);
	
		
		//Ej: BI200405.xls, BI + periodo (yyyymm)
		String fileName = pathArchivoDescuento + prefijoArchivoDescuento + periodo +".xls";
		
		//Cargar las cuotas de los prestamos
		cargarCuotasPrestamos(fechaDescuento);			
		
		//Actualiza indicador de descuento de los Casos en la tabla de casos
		//de Bienestar y actualiza el estado de la cuota en caso de ser en cuotas
		caso.actualizaIndicadorDescuento(descuentos);
		
		codigoDescuento = bonificacionesDao.generaCodigoDescuento();
		
		//Crea los descuentos en la tabla de descuento de Bienestar
		for (int x=0;x<descuentos.size();x++) {
			DescuentosVO descuento = (DescuentosVO) descuentos.get(x);
			descuento.setFechaDescuento(formato.parse(
									periodoDescuento.get(Calendar.DAY_OF_MONTH)+
									"/" +
									(periodoDescuento.get(Calendar.MONTH)+1)+
									"/" +
									periodoDescuento.get(Calendar.YEAR)));
			descuento.setCodigoDescuento(codigoDescuento); 
			bonificacionesDao.insertDescuento(descuento);
		}
		
		//Obtiene las cuotas de los Prestamos
		CuotaPrestamoVO cuotaFiltro = new CuotaPrestamoVO();
		cuotaFiltro.setFecha(fechaDescuento);
		ArrayList cuotasPrestamos = bonificacionesDao.getCuotasPrestamo(cuotaFiltro);
		
		//Prepara los Descuentos
		ArrayList descuentosPersonal = preparaListaDescuentos(descuentos,cuotasPrestamos);
		
		bonificacionesDao.updateCuotasPrestamos(codigoDescuento,fechaDescuento);
		
		for (int x=0;x<descuentosPersonal.size();x++) {
			DescuentoVO descPers = (DescuentoVO) descuentosPersonal.get(x); 
			DescuentoTotalSocioVO descuentoTotalSocio = new DescuentoTotalSocioVO(descPers);
			descuentoTotalSocio.setCodigoDescuento(codigoDescuento);
			descuentoTotalSocio.setFecha(fecha);
			descuentoTotalSocio.setUsuario(usuario.getUsuario());
			
			//req 4353
			ArrayList listaDescuentoPersonalPeriodoActual = bonificacionesDao.getDescuentosRealizadosBySocioEnPeriodo(descuentoTotalSocio.getRut(),codigoDescuento);
			
			if(listaDescuentoPersonalPeriodoActual.size() == 0)
				//no tiene descuento en bf24f1 (tabla que se envía a descontar por planilla)
				bonificacionesDao.insertDescuentoTotalSocio(descuentoTotalSocio);
			else{
				Iterator i = listaDescuentoPersonalPeriodoActual.iterator();
				int acumMontoTotal = 0;
				
				while(i.hasNext()){
					InformeDescuentosVO infDescuento =  (InformeDescuentosVO) i.next();
					acumMontoTotal += infDescuento.getMontoTotal();
				}
				descuentoTotalSocio.setMontoDescuento(descuentoTotalSocio.getMontoDescuento() + acumMontoTotal);
				
				bonificacionesDao.updateDescuentoTotalSocio(descuentoTotalSocio);
			}
		}
		
		//Informa los Descuentos al Sistema de Personal
		logger.debug("*****Son: "+descuentosPersonal.size() + " Descuentos");
		logger.debug("*****En el archivo: "+fileName);
		personal.generarDescuentos(descuentosPersonal, fileName);
	}

	/**
	 * 4353
	 * genera proceso similar a función generardescuentos para un solo caso que se pasa en el arrayList con un objeto
	 * DescuentoVO. Se replico metodo masivo, evitando que guarde en la tabla de indices uno nuevo para
	 * que cuando se pague el convenio el descuento pagado anticipado forme parte de ese pago.
	 * 
	 * @param casos
	 * @param UsuarioVO usuario
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void generarProcesoCasoIndividual(String rutSocio,ArrayList descuentos, UsuarioVO usuario) throws Exception, BusinessException {
		
		int  diaVencimientoCuota = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/dia-vencimiento-Cuota"));
										
		long codigoDescuento=0;
		
		SimpleDateFormat formato =
			new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
			
		SimpleDateFormat formatoPeriodo =
			new SimpleDateFormat("yyyyMM", Locale.getDefault());
				
//		//Servicio de PersonalEJB
//		ServicesEmpleadosDelegate personal = new ServicesEmpleadosDelegate();
		
		//Servicio de CasosEJB
		ServicesCasosDelegate caso = new ServicesCasosDelegate();
		
		if (descuentos == null || descuentos.size() == 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
				"La información es nula");
				
//		//Path del Archivo de Descuentos
//		String pathArchivoDescuento = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//				 "/application-settings/bonificaciones/param/path-archivo-descuento");
		
//		//Prefijo del Nombre del Archivo de Descuentos
//		String prefijoArchivoDescuento = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//				 "/application-settings/bonificaciones/param/prefijo-archivo-descuento");
				 
		//Fecha de Descuento		
		Date fecha = new Date();
		
		GregorianCalendar periodoDescuento = new GregorianCalendar();
		periodoDescuento.setTime(fecha);

		Date fechaDescuento = formato.parse(diaVencimientoCuota +
								"/" +
								(periodoDescuento.get(Calendar.MONTH)+1)+
								"/" +
								periodoDescuento.get(Calendar.YEAR));
		
		GregorianCalendar fec = new GregorianCalendar();
		fec.setGregorianChange(fecha);
				
		//Periodo de Descuento
		String periodo = formatoPeriodo.format(fechaDescuento);
		logger.debug("Periodo: "+ periodo);
	
//		//Cargar las cuotas de los prestamos asociados al dueño del caso
//		Iterator iterDescuento = descuentos.iterator();

		try{
			//1ero carga las cuotas de prestamos asociados al socio (BIF3015)
			if(rutSocio != null)	cargarCuotasPrestamo(rutSocio,fechaDescuento);			
		}catch(SQLException sqlEx){
			//duplicatedKeyException
			sqlEx.printStackTrace();
		}
		//Actualiza indicador de descuento de los Casos en la tabla de casos
		//de Bienestar y actualiza el estado de la cuota
		caso.actualizaIndicadorDescuentoSaldoTotal(descuentos);
		
		codigoDescuento = bonificacionesDao.getProximoCodigoDescuento();
		
		//inserta registro por pago anticipado (es decir una sola cuota) en tabla que registra esta operación
		for (int x=0;x<descuentos.size();x++) {
			DescuentosVO descuento = (DescuentosVO) descuentos.get(x);
			descuento.setFechaDescuento(formato.parse(
									periodoDescuento.get(Calendar.DAY_OF_MONTH)+
									"/" +
									(periodoDescuento.get(Calendar.MONTH)+1)+
									"/" +
									periodoDescuento.get(Calendar.YEAR)));
			descuento.setCodigoDescuento(codigoDescuento); 
			//descuento.setTipoDescuento(DescuentosVO.TIPO_DESCUENTO_SALDO_ANTICIPADO);
			bonificacionesDao.insertPagoAnticipado(descuento);
		}
		
//		//Obtiene las cuotas de los Prestamos
//		CuotaPrestamoVO cuotaFiltro = new CuotaPrestamoVO();
//		cuotaFiltro.setFecha(fechaDescuento);
//		cuotaFiltro.setRut(rutSocio);

//		ArrayList cuotasPrestamos = bonificacionesDao.getCuotasPrestamo(cuotaFiltro);
		
//		//Prepara los Descuentos
//		ArrayList descuentosPersonal = preparaListaDescuentos(descuentos,cuotasPrestamos);
//		
//		bonificacionesDao.updateCuotasPrestamos(codigoDescuento,fechaDescuento);
//		
//		for (int x=0;x<descuentosPersonal.size();x++) {
//			DescuentoVO descPers = (DescuentoVO) descuentosPersonal.get(x); 
//			DescuentoTotalSocioVO descuentoTotalSocio = new DescuentoTotalSocioVO(descPers);
//			descuentoTotalSocio.setCodigoDescuento(codigoDescuento);
//			descuentoTotalSocio.setFecha(fecha);
//			descuentoTotalSocio.setUsuario(usuario.getUsuario());
////			req 4353
//			  ArrayList listaDescuentoPersonalPeriodoActual = bonificacionesDao.getDescuentosRealizadosBySocioEnPeriodo(descuentoTotalSocio.getRut(),codigoDescuento);
//	
//			  if(listaDescuentoPersonalPeriodoActual.size() == 0)
//				  //no tiene descuento en bf24f1 (tabla que se envía a descontar por planilla)
//				  bonificacionesDao.insertDescuentoTotalSocioSaldoDeuda(descuentoTotalSocio);
//			  else{
//				  Iterator i = listaDescuentoPersonalPeriodoActual.iterator();
//				  int acumMontoTotal = 0;
//		
//				  while(i.hasNext()){
//					  InformeDescuentosVO infDescuento =  (InformeDescuentosVO) i.next();
//					  acumMontoTotal += infDescuento.getMontoTotal();
//				  }
//				  descuentoTotalSocio.setMontoDescuento(descuentoTotalSocio.getMontoDescuento() + acumMontoTotal);
//		
//				  bonificacionesDao.updateDescuentoTotalSocio(descuentoTotalSocio);  
//			  }
//		}
	}
	
	/**
	 * Prepara los descuentos, genera un ArrayList con la información
	 * Lista para enviar a Personal
	 * @param ArrayList de descuentos de uso de convenios
	 * @param ArrayList de cuotasPrestamos
	 * @return Información de descuentos para Personal
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList preparaListaDescuentos(ArrayList descuentos, ArrayList cuotasPrestamos) throws Exception, BusinessException {
	
		Date fecha = ((DescuentosVO)descuentos.get(0)).getFechaDescuento();
			
		ArrayList listaDescuentosPersonal = new ArrayList();
		ArrayList listaRutSocios = new ArrayList();
		
		//Genera Gran Total con los descuentos de uso de convenios
		for (int x = 0; x < descuentos.size(); x++) {
			DescuentosVO descuento = (DescuentosVO) descuentos.get(x);
			if(descuento.getMontoDescuento()>0) {
				if (!listaRutSocios.contains(descuento.getRut())) {
					listaDescuentosPersonal.add(
						preparaDescuento(
							descuento.getFullRut(),
							descuento.getFechaDescuento(),
							(int) descuento.getMontoDescuento()));
					listaRutSocios.add(descuento.getRut());
				} else {
					int index = listaRutSocios.indexOf(descuento.getRut());
					DescuentoVO descuentoPersonal =
						(DescuentoVO) listaDescuentosPersonal.get(index);
					descuentoPersonal.setClValo(String.valueOf(
						Integer.parseInt(descuentoPersonal.getClValo())
							+ (int) descuento.getMontoDescuento()));
					listaDescuentosPersonal.set(index, descuentoPersonal);
				}
			}
		}
		//Suma ó agrega a la lista de descuentos los descuentos 
		//por cuotas de prestamos
		for (int y = 0; y < cuotasPrestamos.size(); y++) {
			CuotaPrestamoVO cuotaPres = (CuotaPrestamoVO) cuotasPrestamos.get(y);
			if (!listaRutSocios.contains(cuotaPres.getRut())) {
				listaDescuentosPersonal.add(
					preparaDescuento(
						cuotaPres.getFullRut(),
						fecha,
						cuotaPres.getMonto()));
				listaRutSocios.add(cuotaPres.getRut());
			} else {
				int index = listaRutSocios.indexOf(cuotaPres.getRut());
				DescuentoVO descuentoPersonal =
					(DescuentoVO) listaDescuentosPersonal.get(index);
				descuentoPersonal.setClValo(String.valueOf(Integer.parseInt(descuentoPersonal.getClValo()) + cuotaPres.getMonto()));
				listaDescuentosPersonal.set(index, descuentoPersonal);
			}
		}
		//Devuelve una lista con el monto (gran total) a descontar a cada socio
		//que presente movimientos de uso de convenios ó préstamos		
		return listaDescuentosPersonal;
	}
	
	/**
	 * Prepara la información de los descuentos que se deben informar a Personal
	 * para que los descuente de la remuneración mensual del Socio
	 * @param rut
	 * @param fecha
	 * @param monto
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public DescuentoVO preparaDescuento(String rut, Date fecha, int monto) throws Exception, BusinessException {
		
		SimpleDateFormat formato =
			new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		SimpleDateFormat formatoPeriodo =
			new SimpleDateFormat("yyyy/MM", Locale.getDefault());
			
		GregorianCalendar fechaDescuento = new GregorianCalendar();
		fechaDescuento.setTime(fecha);
		
		String  empresaDescuento = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/empresa-descuentos");
		String  glosaDescuento = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/glosa-descuentos");
		String  estadoDescuento = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/estado-descuentos");
		
		// Fecha Descuento
		String fechaDescuentoConFormato = formato.format(fechaDescuento.getTime());
		logger.debug("Fecha Descuento Con Formato: "+fechaDescuentoConFormato);
									
		// Periodo de Descuento
		String periodoDescuento = formatoPeriodo.format(fechaDescuento.getTime());
		logger.debug("Periodo Descuento: "+periodoDescuento);

		
		DescuentoVO descuentoPersonal = new DescuentoVO();
		descuentoPersonal.setClEmpr(empresaDescuento);
		descuentoPersonal.setClCoFu(rut);
		descuentoPersonal.setClConc(glosaDescuento);
		descuentoPersonal.setClFeMo(fechaDescuentoConFormato);
		descuentoPersonal.setClValo(String.valueOf(monto));
		descuentoPersonal.setClEsta(estadoDescuento);
		descuentoPersonal.setClPeri(periodoDescuento);
	
		return descuentoPersonal;
	}
	
	/**
	 * Genera los pagos que se deben realizar a los Convenios, para los casos
	 * pasados en el array de casos ID
	 * @param pagos (casos ID)
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void generarPagoConvenios(long codigoDescuento,UsuarioVO usuario) throws Exception, BusinessException {
		
		//Código de Oficina para Tesoreria Bienestar
		int oficinaBienestar = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/param/bienestar/oficina"));
				
		System.out.println("USUARIO : "+usuario.getUsuario()+"nombre: "+usuario.getNombre()+" "+usuario.getApellidoPaterno());
		int oficina =Integer.parseInt(usuario.getCodigoOficina());
		String usuarioCreador = usuario.getUsuario();
		
		SimpleDateFormat formato =
			new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
			
		GregorianCalendar fechaPago = new GregorianCalendar();
		fechaPago.setTime(new Date());
		
		//Servicio de TesoreriaEJB
		ServicesTesoreriaDelegate tesoreria = new ServicesTesoreriaDelegate();
		
		//Servicio de CasosEJB
		ServicesCasosDelegate caso = new ServicesCasosDelegate();
		
		//metodo getcasosPorPagar editado para que incluya casos en tabla BF32F1 (pago anticipado).
		ArrayList casos = caso.getCasosPorPagar(codigoDescuento);
		
		if (casos == null || casos.size() == 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
				"La información es nula");
				
		for (int x=0;x<casos.size();x++) {
			PagoConvenioVO pag = (PagoConvenioVO)casos.get(x);
			if (pag.getConceptoTesoreriaConvenioEgreso() == 0)
				throw new BusinessException("CCAF_BONIF_CONVENIOINVALIDO",
					"Debe definir previamente un concepto de tesorería para el convenio: "+
					pag.getNombreConvenio() + ", cógigo convenio: "+pag.getCodigoConvenio());
		}
		
		// recupero información de pagos
		ArrayList pagos = getPagoConvenioPendientes(codigoDescuento);		
				
		//Actualiza indicador de pago de los Casos en la tabla de casos
		//de Bienestar y actualiza el estado de la cuota en caso de ser en cuotas
		caso.actualizaIndicadorPago(casos);
		
		//Informa los Pagos al Sistema de Tesoreria de Bienestar
		
		for (int x=0;x<pagos.size();x++) {
			PagoConvenioPendienteVO pago = (PagoConvenioPendienteVO)pagos.get(x);
			//Prepara el Pago
			Comprobante comprobante = preparaPago(pago, oficina,usuarioCreador);
			comprobante.setCodigoOficina(oficinaBienestar);
			long folio = tesoreria.registrarMovimientoTesoreriaBienestar(comprobante);
			pago.setFolioTesoreria(folio);
			pago.setCodigoPago(codigoDescuento);
			pago.setFechaPago(formato.parse(fechaPago.get(Calendar.DAY_OF_MONTH)+"/"+(fechaPago.get(Calendar.MONTH)+1)+"/"+fechaPago.get(Calendar.YEAR)));
						
			bonificacionesDao.insertPagoConvenio(pago);
			
			//Crea los pagos en la tabla de Pago Convenios de Bienestar
			//con el número de folio generado en Tesorería de Bienestar
			for (int y=0;y<casos.size();y++) {
				PagoConvenioVO pagoConvenio = (PagoConvenioVO) casos.get(y);
				pagoConvenio.setUsuario(usuario.getUsuario());
				
				if (pago.getCodigoConvenio() == pagoConvenio.getCodigoConvenio()) {
					pagoConvenio.setFolioTesoreriaBienestar(folio);
					pagoConvenio.setCodigoPago(codigoDescuento);
					pagoConvenio.setFechaPago(formato.parse(fechaPago.get(Calendar.DAY_OF_MONTH)+"/"+(fechaPago.get(Calendar.MONTH)+1)+"/"+fechaPago.get(Calendar.YEAR)));
					bonificacionesDao.insertPago(pagoConvenio); 
				}	
			}
		}
	}
	
	
	/**
	 * Prepara Información de Pago Convenios para Tesorería Bienestar
	 * @param PagoConvenioPendienteVO pago
	 * @param int oficina
	 * @param String usuarioCreador
	 * @return Comprobante
	 */
	public Comprobante preparaPago(PagoConvenioPendienteVO casoPago, int oficina,String usuarioCreador) {
	
//		//Area de Negocios de Convenios en Tesoreria Bienestar
//		int areaNegocioConveniosBienestar = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//				 "/application-settings/tesoreria/param/bienestar/area-negocio-convenios"));
	
//		ArrayList listaComprobantes = new ArrayList();
		 
		Comprobante comprobantePago = new Comprobante();				
		//Comprobante
		comprobantePago.setTipoMovimiento(Comprobante.TPO_MOVI_EGRESO);
		comprobantePago.setEstadoMovimientoCaja(Comprobante.STD_MOV_CAJA_GENERADO);
		comprobantePago.setFormaPago(Comprobante.FORMA_PAGO_CAJA);
		comprobantePago.setRut1(Integer.parseInt(casoPago.getRut()));
		comprobantePago.setDv1(casoPago.getDv());
		comprobantePago.setNombreRut1(casoPago.getNombreConvenio());
		comprobantePago.setMontoInformado((int)casoPago.getMonto());
		comprobantePago.setMontoEmitido((int)casoPago.getMonto());
		comprobantePago.setObservaciónMovimientoCaja("");
		comprobantePago.setSucursal(0);
		comprobantePago.setEstadoAutorizacion(Comprobante.STD_AUTORIZACION_AUTORIZADO);
		comprobantePago.setTipoPago(Comprobante.TIPO_PAGO_CHEQUE);
		comprobantePago.setEmiteFactura(Comprobante.EMITE_FACTURA_NO);
		comprobantePago.setCodigoOficina(oficina);
		comprobantePago.setCorrelativoPago(0);
		//comprobantePago.setCodigoAreaNegocio(areaNegocioConveniosBienestar);
		comprobantePago.setCodigoAreaNegocio((int)casoPago.getArea());
		comprobantePago.setSerPagadoPorCodigoOficina(oficina);
		comprobantePago.setUsuarioCreoRegistro(usuarioCreador);
	
		//Genero los Datos Comunes (Detalles)
		Detalle datosComunes = new Detalle();
		datosComunes.setMontoDetalle((int)casoPago.getMonto());
		datosComunes.setObservaciónMovimientoDetalle("");
		datosComunes.setMontoPagoEfectivo(0);
		datosComunes.setDocumentoRespaldo("O");
		datosComunes.setCantidadDocumentos(1);
		datosComunes.setMontoPagoCheque(0);
		datosComunes.setNumeroCaratula(0);
		datosComunes.setCodigoConcepto((int)casoPago.getConceptoEgreso());
		datosComunes.setCreationUser(usuarioCreador);
		
		comprobantePago.getDatosComunes().add(datosComunes);

		return comprobantePago;
	}

	/**
	 * Prepara Información de Pago Convenios para Tesorería Bienestar
	 * @param PagoConvenioPendienteVO pago
	 * @param int oficina
	 * @param String usuarioCreador
	 * @return Comprobante
	 */
	private Comprobante preparaPagoSaldoDeudaTotal(int montoDescuento, int oficina,String usuarioCreador,String rutSocio, int areaNegocio,int codConcepto,long codConvenio) throws RemoteException, BusinessException, Exception {
	
//		//Area de Negocios de Convenios en Tesoreria Bienestar
//		int areaNegocioConveniosBienestar = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//				 "/application-settings/tesoreria/param/bienestar/area-negocio-convenios"));
		ServicesSociosDelegate servicioSocios = new ServicesSociosDelegate();
		ServicesConveniosDelegate delegateConvenio = new ServicesConveniosDelegate();
		
//		ArrayList listaComprobantes = new ArrayList();
		 			
		//Comprobante	
		Comprobante comprobante = new Comprobante();
		comprobante.setFechaEmision(new Date());
		comprobante.setTipoMovimiento(Comprobante.TPO_MOVI_INGRESO);
		comprobante.setEstadoMovimientoCaja(Comprobante.STD_MOV_CAJA_GENERADO);
		comprobante.setFormaPago(Comprobante.FORMA_PAGO_CAJA);
		comprobante.setEstadoAutorizacion(Comprobante.STD_AUTORIZACION_AUTORIZADO);
		comprobante.setTipoPago(Comprobante.TIPO_PAGO_EFECTIVO);
		comprobante.setEmiteFactura(Comprobante.EMITE_FACTURA_NO);
		comprobante.setMontoEmitido((int) montoDescuento);
		comprobante.setMontoInformado((int) montoDescuento);
		comprobante.setMontoInteres(0);
		comprobante.setMontoReajuste(0);
		comprobante.setSucursal(0);
		comprobante.setCorrelativoPago(0);
		comprobante.setCodigoAreaNegocio(areaNegocio);

		Socio socio = servicioSocios.getSocio(rutSocio);
				
		comprobante.setRut1(new Long(socio.getRut()).longValue());
		comprobante.setDv1(new Character(socio.getDigito()).toString());
		comprobante.setUsuarioCreoRegistro(usuarioCreador);
		
		Convenio convenio = delegateConvenio.getConvenio(codConvenio);
		if(convenio != null)
			comprobante.setObservaciónMovimientoCaja(convenio.getNombre());
		else
			comprobante.setObservaciónMovimientoCaja("");
		
		comprobante.setFechaDisponibilidad(new Date());
		comprobante.setCodigoOficina(oficina);
		comprobante.setSerPagadoPorCodigoOficina(oficina);
		comprobante.setNombreRut1(socio.getNombre().toUpperCase()+" "+socio.getApePat().toUpperCase()+" "+socio.getApeMat().toUpperCase());
			
		//Genero los Datos Comunes (Detalles)
		Detalle datosComunes = new Detalle();
		datosComunes.setMontoDetalle((int)montoDescuento);
		datosComunes.setObservaciónMovimientoDetalle("");
		datosComunes.setMontoPagoEfectivo(0);
		datosComunes.setDocumentoRespaldo("O");
		datosComunes.setCantidadDocumentos(1);
		datosComunes.setMontoPagoCheque(0);
		datosComunes.setNumeroCaratula(0);
		datosComunes.setCodigoConcepto((int)codConcepto);
		datosComunes.setCreationUser(usuarioCreador);
		
		comprobante.getDatosComunes().add(datosComunes);

		return comprobante;
	}
	
	/**
	 * Genera una lista con los periodos que aún no se han contabilizado
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPeriodosPorContabilizar() throws Exception, BusinessException {
	
		ArrayList periodos = bonificacionesDao.getPeriodosPorContabilizar(InformacionAsiento.TIPO_DESCUENTO);
		
		for(int x=0; x<periodos.size();x++) {
			ContabilidadPendienteVO pendiente = (ContabilidadPendienteVO)periodos.get(x);
			Calendar calendario = Calendar.getInstance();
			calendario.setTime(pendiente.getFechaDescuento());
			calendario.add(Calendar.MONTH,-1);
			pendiente.setFechaDescuento(calendario.getTime());
		}
		
		return periodos;
	}
	
	
	/**
	 * Genera Los Asientos contables y los envía a contabilidad, los
	 * asientos contables generados son:
	 * Asiento Contable: Bonificaciones Convenios
	 * Asiento Contable: Descuentos del Mes
	 * @param fecha
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void generarAsientosContables(ContabilidadPendienteVO contabilizar) throws Exception, BusinessException {
		
		long secuenciaAsientoBonificacionesConvenios=0;
		long secuenciaAsientoDescuentosMes=0;
		
		// Servicio de ContabilidadEJB
		ServicesContabilidadDelegate contabilidad = new ServicesContabilidadDelegate();
			
		SimpleDateFormat formato =
			new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		SimpleDateFormat formatoFechaPase =
			new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
		SimpleDateFormat formatoMes =
			new SimpleDateFormat("MMMM", Locale.getDefault());
		
		
		GregorianCalendar fec = new GregorianCalendar();
		fec.setTime(contabilizar.getFechaDescuento());
				
		// Fecha Pase
		Date fechaPase = formato.parse(
							fec.get(Calendar.DAY_OF_MONTH)+
							"/"+
							(fec.get(Calendar.MONTH)+1)+
							"/"+
							fec.get(Calendar.YEAR));
		
		// Fecha Pase con Formato
		String fechaPaseFormato = formatoFechaPase.format(fechaPase);
		logger.debug("Fecha Pase: "+fechaPaseFormato);
		
		// Fecha Inicio
		Date fechaInicio = formato.parse(
						"01/"+
						(fec.get(Calendar.MONTH)+1)+
						"/"+
						fec.get(Calendar.YEAR));
		logger.debug("Fecha Inicio: "+fechaInicio);
						
		// Fecha Fin
		Date fechaFin = formato.parse(
						"01/"+
						(fec.get(Calendar.MONTH)+2)+
						"/"+
						fec.get(Calendar.YEAR));
		logger.debug("Fecha Fin"+fechaFin);
		
		// Mes Contabilizacion
		String mesContabilizado = formatoMes.format(fechaPase);
		logger.debug("Mes Contabilizado"+mesContabilizado);
		
		// Preparación de Asientos Contables
		// Asiento Contable: Bonificaciones Convenios
		// BHLDES
		String bhldesBonificacionesConvenio = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/contabilidad/param/bienestar/BHLDES-BON-CONV");
		if (bhldesBonificacionesConvenio == null || bhldesBonificacionesConvenio.length() == 0)
			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: BHLDES-BON-CONV en el Sistema");
				
		//BHPGM Nombre de Actividad Asiento Bonificaciones Convenios
		String bhpgmBonificacionesConvenio = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/contabilidad/param/bienestar/BHPGM-BON-CONV");
		if (bhpgmBonificacionesConvenio == null || bhpgmBonificacionesConvenio.length() == 0)
			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: BHPGM-BON-CONV en el Sistema");			
		
		bhldesBonificacionesConvenio=bhldesBonificacionesConvenio+" "+mesContabilizado;
		
		Asiento asientoBonificacionesConvenios = preparaAsiento(fechaPaseFormato,bhldesBonificacionesConvenio,bhpgmBonificacionesConvenio);
		// Asiento Contable: Descuentos del Mes
		String bhldesDescuentosMes = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/contabilidad/param/bienestar/BHLDES-DES-MES");
				 
		bhldesDescuentosMes=bhldesDescuentosMes+" "+mesContabilizado;
				 
		if (bhldesDescuentosMes == null || bhldesDescuentosMes.length() == 0)
			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: BHLDES-DES-MES en el Sistema");
				
		//BHPGM Nombre de Actividad Asiento Descuentos del Mes
		String bhpgmDescuentosMes = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/contabilidad/param/bienestar/BHPGM-DES-MES");
		if (bhpgmDescuentosMes == null || bhpgmDescuentosMes.length() == 0)
			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: BHPGM-DES-MES en el Sistema");
				
		Asiento asientoDescuentosMes = preparaAsiento(fechaPaseFormato,bhldesDescuentosMes,bhpgmDescuentosMes);
		
		// Codigos de Reembolso a Contabilizar --información de los reembolsos realizados em el periodo dado, obtenido desde BF22F1.
		ArrayList codigosReembolso = bonificacionesDao.getCodigoReembolsosPorContabilizar(fechaInicio,fechaFin);
		
		// Casos cerrados por pago con prestamo
		ArrayList casosPagadosPrestamo = bonificacionesDao.getPagoConPrestamo(fechaInicio,fechaFin);
		 
		// Recuperación de Información
		
		// Descuentos	
		ArrayList descuentos = bonificacionesDao.getDescuentosMes(contabilizar.getCodigoDescuento());
		
		//2006.03.17 asepulveda
		//Se agregan los casos pagados totalmente en forma anticipada con prestamo y/o abono del socio
		ArrayList casosPagadosAnticipadamente = bonificacionesDao.getCasosPagadosAnticipadamente(fechaInicio, fechaFin);
		descuentos.addAll(casosPagadosAnticipadamente);
		
		// Monto Total Descuentos
		//total por uso convenios
		int montoTotalDescuentosUsoConvenios = bonificacionesDao.getMontoTotalDescuento(contabilizar.getCodigoDescuento());
				
		/*
		 * (asepulveda) Septiembre 2005 ahora se debe tener la siguiente consideración
		 * 
		 * La información de los préstamos que se deben enviar a contabilidad
		 * debe ser la que corresponde al mes de la cuota descontada en el mes que se está
		 * contabilizando, por lo tanto, corresponde al descuento que se realizo justo al
		 * mes anterior al codigo de descuento de bonificaciones 
		 */
		//long codigoDescuentoPrestamos=contabilizar.getCodigoDescuento()-1;		 
		 //17.03.2006 esto ya no es asì a partir de ahora por instrucción de Contabilidad (Manuel Zamorano)
		 //debe ser la del mismo mes, tal como se realizaba antes de septiembre del 2005 
		//long codigoDescuentoPrestamos=contabilizar.getCodigoDescuento();/
		
		//19-05-2011 asepulveda-schema
		//Se vuelve a cambiar la forma de contabilizar, esto de acuerdo al REQ5590
		long codigoDescuentoPrestamos=contabilizar.getCodigoDescuento() - 1;
		//long codigoDescuentoPrestamos=contabilizar.getCodigoDescuento();
		
		//total por uso prestamos
		int montoTotalDescuentosPorPrestamos = bonificacionesDao.getMontoTotalDescuentoPrestamos(codigoDescuentoPrestamos);
		
		//Total en suma de uso convenios más cuotas prestamos
		int montoTotalDescuentos = montoTotalDescuentosUsoConvenios + montoTotalDescuentosPorPrestamos;//TODO: Mostrar este valor por pantalla.
		// Total a pagar a acreedores
		ArrayList acreedoresConvenios = bonificacionesDao.getAcreedoresConvenios(contabilizar.getCodigoDescuento());
		// Reembolsos
		ArrayList reembolsos = bonificacionesDao.getReembolsosMes(fechaInicio,fechaFin);	
		
		// Cuotas de Préstamos
		ArrayList cuotasPrestamos = bonificacionesDao.getPrestamoMes(codigoDescuentoPrestamos);
		// Aportes Bienestar 
		ArrayList aportesBienestar = bonificacionesDao.getAportesBienestar(contabilizar.getCodigoDescuento(),codigosReembolso,fechaInicio,fechaFin);
		ArrayList aportesBienestarBonificacionEspecial = bonificacionesDao.getAportesBienestar_BonificacionEspecial(contabilizar.getCodigoDescuento(),codigosReembolso,fechaInicio,fechaFin);
		//Se añade a lista de aportesBienestar todo lo que está en aporteBienestarBonificacionEspecial
		for(Iterator i = aportesBienestarBonificacionEspecial.iterator(); i.hasNext(); ){
			ContabilidadVO aBEsp = (ContabilidadVO) i.next(); 
			
			//Asepulveda 16-09-2010
//			//Indicador utilizado para saber si la cuenta de gasto de la lista "aportesBienestarBonificacionEspecial", se encuentra en la lista "aportesBienestar"
			boolean existeCuentaGastoABEspInAb = false;   
			for(Iterator j = aportesBienestar.iterator(); j.hasNext(); ){
				ContabilidadVO aB = (ContabilidadVO) j.next();
				if(aBEsp.getCuentaGasto().equals(aB.getCuentaGasto())){
					aB.setAporteBienestar(aB.getAporteBienestar() + aBEsp.getAporteBienestar());
					//Asepulveda 16-09-2010
					//La encontró, entonces cambia el flag
					existeCuentaGastoABEspInAb =true;
					break;
				}
			}
			//Asepulveda 16-09-2010
			//Si la cuenta de gasto no existe en la lista de "aportesBienestar", entonces se debe agregar.
			if(!existeCuentaGastoABEspInAb){
				aportesBienestar.add(aBEsp);
			}
			
		}
		// Preparación de la información
		
		// Asiento Contable: Bonificaciones Convenios
		asientoBonificacionesConvenios.getLineas().addAll(preparaLineasAsientoBonificacionesConvenios(descuentos,acreedoresConvenios,aportesBienestar, reembolsos, casosPagadosPrestamo));
		// Asiento Contable: Descuentos del Mes
		asientoDescuentosMes.getLineas().addAll(preparaLineasAsientoDescuentosMes(descuentos,montoTotalDescuentos,cuotasPrestamos));
		
		 // Generación de Asientos Contables
		 
		
		/**
		 * GBH Pase BONIFICACI
		 */
		// Asiento Contable: Bonificaciones Convenios
		secuenciaAsientoBonificacionesConvenios = contabilidad.creaAsientoContableBienestar(asientoBonificacionesConvenios); 
		
		/**
		 * GBH Pase DESCUENTOS
		 */
		// Asiento Contable: Descuentos del Mes
		secuenciaAsientoDescuentosMes = contabilidad.creaAsientoContableBienestar(asientoDescuentosMes);
		
		// Registra Información de las secuencias generadas por contabilidad
		
		// Reembolsos
		for (int x=0;x<codigosReembolso.size();x++) {
			InformacionAsiento informacionAsiento = (InformacionAsiento) codigosReembolso.get(x);
			informacionAsiento.setTipo(InformacionAsiento.TIPO_REEMBOLSO);
			informacionAsiento.setFecha(new Date());
			informacionAsiento.setSecuencia(secuenciaAsientoBonificacionesConvenios);
			informacionAsiento.setUsuario(contabilizar.getUsuario());
			bonificacionesDao.insertInformacionAsientos(informacionAsiento);
		}
		// Descuentos
		InformacionAsiento informacionAsiento = new InformacionAsiento();
		informacionAsiento.setCodigo(contabilizar.getCodigoDescuento()); 
		informacionAsiento.setTipo(InformacionAsiento.TIPO_DESCUENTO);
		informacionAsiento.setFecha(new Date());
		informacionAsiento.setSecuencia(secuenciaAsientoBonificacionesConvenios);
		informacionAsiento.setUsuario(contabilizar.getUsuario());
		bonificacionesDao.insertInformacionAsientos(informacionAsiento);
		informacionAsiento.setSecuencia(secuenciaAsientoDescuentosMes);
		bonificacionesDao.insertInformacionAsientos(informacionAsiento);
	}
	
	/**
	 * Prepara el Asiento Contable
	 * @return Asiento Contable
	 */
	public Asiento preparaAsiento(String fechaPase, String bhldes, String bhpgm)throws Exception, BusinessException {
		
		//BHID
		String bhid = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/contabilidad/param/bienestar/BHID");
		if (bhid == null || bhid.length() == 0)
			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: BHID en el Sistema");
				
		//BHCURR
		String bhcurr = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/contabilidad/param/bienestar/BHCURR");
		if (bhcurr == null || bhcurr.length() == 0)
			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: BHCURR en el Sistema");
				
		//BHUSER
		String bhuser = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/contabilidad/param/bienestar/BHUSER");
		if (bhuser == null || bhuser.length() == 0)
			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: BHUSER en el Sistema");

				 
		Asiento asiento = new Asiento();
		asiento.setBhId(bhid);
		asiento.setBhPgm(bhpgm);
		asiento.setBhLdes(bhldes);
		asiento.setBhCurr(bhcurr);
		asiento.setBhEvnt(bhpgm);
		asiento.setBhJdat(fechaPase);
		asiento.setBhUser(bhuser);
		return asiento;
	}
	
	/**
	 * Prepara las lineas para el Asiento Contable
	 * @param informacion
	 * @return
	 */
	public ArrayList preparaLineasAsientoBonificacionesConvenios(ArrayList descuentos,ArrayList acreedoresConvenios, ArrayList aportesBienestar, ArrayList reembolsos, ArrayList casosPagadosPrestamo) throws Exception, BusinessException{
				
		//BLID
		String blid = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/contabilidad/param/bienestar/BLID");
		if (blid == null || blid.length() == 0)
			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: BLID en el Sistema");

		//BLPGM Nombre de Actividad Asiento Bonificaciones Convenios
		String blpgm = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/contabilidad/param/bienestar/BLPGM-BON-CONV");
		if (blpgm == null || blpgm.length() == 0)
			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: BLPGM-BON-CONV en el Sistema");	
				 
		//BLUS01
		String blus01 = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/contabilidad/param/bienestar/BLUS01");
		if (blus01 == null || blus01.length() == 0)
			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: BLUS01 en el Sistema"); 
				 
		//BLUS02
		String blus02 = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/contabilidad/param/bienestar/BLUS02");
		if (blus02 == null || blus02.length() == 0)
			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: BLUS02 en el Sistema");
				 
		//BLUS04
		String blus04 = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/contabilidad/param/bienestar/BLUS04");		
				
		//cuenta: Bonificaciones por pagar
		String cuentaBonificacionesPorPagar = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
								"/application-settings/contabilidad/provisiones-bonif-por-pagar");
		logger.debug("Cuenta Bonificaciones por Pagar: "+cuentaBonificacionesPorPagar);
		if (cuentaBonificacionesPorPagar == null ||
			cuentaBonificacionesPorPagar.length()==0)
			throw new BusinessException("CCAF_BONIF_NUMEROCUENTAINVALIDO",
				"Debe definir la Cuenta de Bonificaciones Por Pagar en el Sistema");
								
								
		//cuenta: deudores prestamos medicos
		String cuentaDeudoresPrestamosMedicos = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
								"/application-settings/contabilidad/deudores-prestamos-medicos");
		logger.debug("Cuenta Deudores Prestamos Medicos: "+cuentaDeudoresPrestamosMedicos);
		if (cuentaDeudoresPrestamosMedicos == null ||
		cuentaDeudoresPrestamosMedicos.length()==0)
			throw new BusinessException("CCAF_BONIF_NUMEROCUENTAINVALIDO",
				"Debe definir la Cuenta Deudores Préstamos Médicos en el Sistema");
		
		ArrayList lineas = new ArrayList();
		
		if (acreedoresConvenios != null) {
			//Preparar Lineas Acreedores, una por cada convenio
			for (int x=0;x<acreedoresConvenios.size();x++) {
				ContabilidadVO acreedor = (ContabilidadVO) acreedoresConvenios.get(x);
				if(acreedor.getTotal()>0) {
					logger.debug("Cuenta Acreedora: "+acreedor.getCuentaAcreedor());
					if (acreedor.getCuentaAcreedor() == null ||
						acreedor.getCuentaAcreedor().equals("0"))
						throw new BusinessException("CCAF_BONIF_NUMEROCUENTAINVALIDO",
							"Debe definir la Cuenta Acreedora para el convenio: "+acreedor.getNombreConvenio());
							
					logger.debug("Cuenta Deudora: "+acreedor.getCuentaDeudor());
					if (acreedor.getCuentaDeudor() == null ||
						acreedor.getCuentaDeudor().equals("0"))
						throw new BusinessException("CCAF_BONIF_NUMEROCUENTAINVALIDO",
							"Debe definir la Cuenta Deudora para el convenio: "+acreedor.getNombreConvenio());
							
					// Monto Acreedor
					Linea linea = new Linea();
					linea.setBlId(blid);
					linea.setBlPgm(blpgm);
					linea.setBlUs01(blus01);
					linea.setBlUs02(blus02);
					linea.setBlUs03(acreedor.getCuentaAcreedor());
					linea.setBlUs04(blus04);
					if (acreedor.getNombreConvenio().length() > 14)
						linea.setBlUs05(acreedor.getNombreConvenio().substring(0,15));
					else
						linea.setBlUs05(acreedor.getNombreConvenio());
					linea.setBlUs06(acreedor.getFullRutConvenioSinGuion());
					linea.setBlUs08(acreedor.getDocumento());
					linea.setBlUs09(String.valueOf(acreedor.getCodigoConvenio()));
					linea.setBlAf02(acreedor.getTotal()); //montoCredito
					lineas.add(linea);
					
					// Monto Deudor
					linea = new Linea();
					linea.setBlId(blid);
					linea.setBlPgm(blpgm);
					linea.setBlUs01(blus01);
					linea.setBlUs02(blus02);
					linea.setBlUs03(acreedor.getCuentaDeudor());
					linea.setBlUs04(blus04);
					linea.setBlUs05(acreedor.getFullNombreSocio());
					linea.setBlUs06(acreedor.getFullRutSocioSinGuion());
					linea.setBlUs08(acreedor.getDocumento());
					linea.setBlUs09(String.valueOf(acreedor.getCodigoConvenio()));
					linea.setBlAf01(acreedor.getTotal()); //montoCredito
					lineas.add(linea);
				}
			}
		}
		
		if (aportesBienestar != null) {
			//Preparar Lineas Aporte Bienestar (por totales)
			for (int x=0;x<aportesBienestar.size();x++) {
				ContabilidadVO aporteBienestar = (ContabilidadVO) aportesBienestar.get(x);
				if(aporteBienestar.getAporteBienestar()>0) {
					logger.debug("Cuenta Gasto: "+aporteBienestar.getCuentaGasto());
					if (aporteBienestar.getCuentaGasto() == null ||
						aporteBienestar.getCuentaGasto().equals("0"))
						throw new BusinessException("CCAF_BONIF_NUMEROCUENTAINVALIDO",
							"Debe definir la Cuenta de Gasto para las coberturas del Caso: "+aporteBienestar.getCasoId());
					Linea linea = new Linea();
					linea.setBlId(blid);
					linea.setBlPgm(blpgm);
					linea.setBlUs01(blus01);
					linea.setBlUs02(blus02);
					linea.setBlUs03(aporteBienestar.getCuentaGasto());
					linea.setBlUs04(blus04);
					linea.setBlAf01(aporteBienestar.getAporteBienestar()); //montoDebito
					lineas.add(linea);
				}
			}
		}
		
		if (descuentos != null) {
			//Aportes de Bienestar detallados por caso
			for (int x=0;x<descuentos.size();x++) {
				ContabilidadVO descuento = (ContabilidadVO) descuentos.get(x);
				if(descuento.getCuotaActual()<=1 && descuento.getAporteBienestar()>0) {
					logger.debug("Cuenta Deudora: "+descuento.getCuentaDeudor());
					if (descuento.getCuentaDeudor() == null ||
						descuento.getCuentaDeudor().equals("0"))
						throw new BusinessException("CCAF_BONIF_NUMEROCUENTAINVALIDO",
							"Debe definir la Cuenta Deudora para el convenio: "+descuento.getNombreConvenio());
					Linea linea = new Linea();
					linea.setBlId(blid);
					linea.setBlPgm(blpgm);
					linea.setBlUs01(blus01);
					linea.setBlUs02(blus02);
					linea.setBlUs03(descuento.getCuentaDeudor());
					linea.setBlUs04(blus04);
					linea.setBlUs05(descuento.getFullNombreSocio());
					linea.setBlUs06(descuento.getFullRutSocioSinGuion());
					linea.setBlUs07(String.valueOf(descuento.getCasoId()));
					linea.setBlUs08(descuento.getDocumento());
					linea.setBlUs09(String.valueOf(descuento.getCodigoConvenio()));
					linea.setBlAf02(descuento.getAporteBienestar()); //montoCredito
					lineas.add(linea);	
				}
			}
		}		

		if (reembolsos != null) {
			//Reembolsos
			for (int x=0;x<reembolsos.size();x++) {
				ContabilidadVO reembolso = (ContabilidadVO) reembolsos.get(x);
				if(reembolso.getAporteBienestar()>0) {
					Linea linea = new Linea();
					linea.setBlId(blid);
					linea.setBlPgm(blpgm);
					linea.setBlUs01(blus01);
					linea.setBlUs02(blus02);
					linea.setBlUs03(String.valueOf(cuentaBonificacionesPorPagar));
					linea.setBlUs04(blus04);
					linea.setBlUs05(reembolso.getFullNombreSocio());
					linea.setBlUs06(reembolso.getFullRutSocioSinGuion());
					linea.setBlUs07(String.valueOf(reembolso.getCasoId()));
					linea.setBlUs08(reembolso.getDocumento());
					linea.setBlUs09(String.valueOf(reembolso.getCodigoConvenio()));
					linea.setBlAf02(reembolso.getAporteBienestar()); //montoCredito
					lineas.add(linea);
				}
			}
		}
		
		if (casosPagadosPrestamo != null) {
			//Casos pagados con prestamo
			for (int x=0;x<casosPagadosPrestamo.size();x++) {
				PagoConPrestamoVO pagoConPrestamo = (PagoConPrestamoVO) casosPagadosPrestamo.get(x);
				Linea linea = new Linea();				
				
				if(pagoConPrestamo.getMontoPrestamo()>0) {
					logger.debug("Cuenta Deudora: "+pagoConPrestamo.getCuentaDeudor());
					if (pagoConPrestamo.getCuentaDeudor() == null ||
						pagoConPrestamo.getCuentaDeudor().equals("0"))
						throw new BusinessException("CCAF_BONIF_NUMEROCUENTAINVALIDO",
							"Debe definir la Cuenta Deudora para el convenio: "+pagoConPrestamo.getNombreConvenio());
							
					// Deudores socio  (haber)
					linea = new Linea();
					linea.setBlId(blid);
					linea.setBlPgm(blpgm);
					linea.setBlUs01(blus01);
					linea.setBlUs02(blus02);
					linea.setBlUs03(pagoConPrestamo.getCuentaDeudor());
					linea.setBlUs04(blus04);
					linea.setBlUs05(pagoConPrestamo.getFullNombreSocio());
					linea.setBlUs06(pagoConPrestamo.getFullRutSocioSinGuion());
					linea.setBlUs07(String.valueOf(pagoConPrestamo.getCasoId()));
					linea.setBlUs08(pagoConPrestamo.getDocumento());
					linea.setBlUs09(String.valueOf(pagoConPrestamo.getCodigoConvenio()));
					linea.setBlAf02(pagoConPrestamo.getMontoPrestamo()); //montoCredito
					lineas.add(linea);
				
					// Deudores prestamos medicos
					linea = new Linea();
					linea.setBlId(blid);
					linea.setBlPgm(blpgm);
					linea.setBlUs01(blus01);
					linea.setBlUs02(blus02);
					linea.setBlUs03(String.valueOf(cuentaDeudoresPrestamosMedicos));
					linea.setBlUs04(blus04);
					linea.setBlUs05(pagoConPrestamo.getFullNombreSocio());
					linea.setBlUs06(pagoConPrestamo.getFullRutSocioSinGuion());
					linea.setBlUs07(String.valueOf(pagoConPrestamo.getCasoId()));
					linea.setBlUs08(String.valueOf(pagoConPrestamo.getNumeroPrestamo()));
					linea.setBlUs09(String.valueOf(pagoConPrestamo.getCodigoConvenio()));
					linea.setBlAf01(pagoConPrestamo.getMontoPrestamo()); //montoDebito
					lineas.add(linea);
				}
			}
			
		}
		return lineas;
	}
	
	
	/**
	 * Prepara las lineas para el Asiento Contable
	 * @param informacion
	 * @return
	 */
	public ArrayList preparaLineasAsientoDescuentosMes(ArrayList descuentos,int montoTotalDescuentos, ArrayList cuotasPrestamos) throws Exception, BusinessException {
		
		//BLID
		String blid = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/contabilidad/param/bienestar/BLID");
		if (blid == null || blid.length() == 0)
			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: BLID en el Sistema");

		//BLPGM Nombre de Actividad Asiento Descuentos del Mes
		String blpgm = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/contabilidad/param/bienestar/BLPGM-DES-MES");
		if (blpgm == null || blpgm.length() == 0)
			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: BLPGM-DES-MES en el Sistema");

		//BLUS01
		String blus01 = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/contabilidad/param/bienestar/BLUS01");
		if (blus01 == null || blus01.length() == 0)
			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: BLUS01 en el Sistema");
				
		//BLUS02
		String blus02 = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/contabilidad/param/bienestar/BLUS02");
		if (blus02 == null || blus02.length() == 0)
			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: BLUS02 en el Sistema");
				
		//BLUS04
		String blus04 = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/contabilidad/param/bienestar/BLUS04");
		
		//cuenta Deudores Araucana
		String cuentaDeudoresAraucana = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
									"/application-settings/contabilidad/deudores-por-planilla-descuento");
		logger.debug("Cuenta Deudores Araucana: "+cuentaDeudoresAraucana);
		if (cuentaDeudoresAraucana == null ||
			cuentaDeudoresAraucana.length()==0)
			throw new BusinessException("CCAF_BONIF_NUMEROCUENTAINVALIDO",
				"Debe definir la Cuenta Deudores Araucana en el Sistema");
				
//		String cuentaDeudorPrestamo=null;
		
		ArrayList lineas = new ArrayList();
		
		if (montoTotalDescuentos>0) {
			//Preparar Lineas Deudores Araucana
			Linea lin = new Linea();
			lin.setBlId(blid);
			lin.setBlPgm(blpgm);
			lin.setBlUs01(blus01);
			lin.setBlUs02(blus02);
			lin.setBlUs03(String.valueOf(cuentaDeudoresAraucana));
			lin.setBlUs04(blus04);
			lin.setBlAf01(montoTotalDescuentos); //montoDebito	
			lineas.add(lin);
		
		
			if (descuentos != null) {
				//Descuentos
				for (int x=0;x<descuentos.size();x++) {
					ContabilidadVO descuento = (ContabilidadVO) descuentos.get(x);
					if(descuento.getTotal()>0) {
						//Descontado al Socio
						logger.debug("Cuenta Deudor: "+descuento.getCuentaDeudor());
						if (descuento.getCuentaDeudor() == null ||
						descuento.getCuentaDeudor().equals("0"))
							throw new BusinessException("CCAF_BONIF_NUMEROCUENTAINVALIDO",
								"Debe definir la Cuenta Deudores para el convenio: "+descuento.getNombreConvenio());
						Linea linea = new Linea();
						linea.setBlId(blid);
						linea.setBlPgm(blpgm);
						linea.setBlUs01(blus01);
						linea.setBlUs02(blus02);
						linea.setBlUs03(descuento.getCuentaDeudor());
						linea.setBlUs04(blus04);
						linea.setBlUs05(descuento.getFullNombreSocio());
						linea.setBlUs06(descuento.getFullRutSocioSinGuion());
						linea.setBlUs07(String.valueOf(descuento.getCasoId()));
						linea.setBlUs08(descuento.getDocumento());
						linea.setBlUs09(String.valueOf(descuento.getCodigoConvenio()));
						linea.setBlAf02(descuento.getTotal()); //montoCredito, monto descontado
						lineas.add(linea);
					}
					
					// ahora no se van a contabilizar los aoprtes de los convenios
//					if(descuento.getAporteConvenio()>0) {
//						//Descontado al Convenio
//						logger.debug("Cuenta Deudor: "+descuento.getCuentaDeudor());
//						if (descuento.getCuentaDeudor() == null ||
//						descuento.getCuentaDeudor().equals("0"))
//							throw new BusinessException("CCAF_BONIF_NUMEROCUENTAINVALIDO",
//								"Debe definir la Cuenta Deudores para el convenio: "+descuento.getNombreConvenio());
//								
//						Linea linea = new Linea();
//						linea.setBlId(blid);
//						linea.setBlPgm(blpgm);
//						linea.setBlUs01(blus01);
//						linea.setBlUs02(blus02);
//						linea.setBlUs03(descuento.getCuentaDeudor());
//						linea.setBlUs04(blus04);
//						if (descuento.getNombreConvenio().length() > 14)
//							linea.setBlUs05(descuento.getNombreConvenio().substring(0,15));
//						else
//							linea.setBlUs05(descuento.getNombreConvenio());
//						linea.setBlUs06(descuento.getFullRutConvenioSinGuion());
//						linea.setBlUs07(String.valueOf(descuento.getCasoId()));
//						linea.setBlUs08(descuento.getDocumento());
//						linea.setBlUs09(String.valueOf(descuento.getCodigoConvenio()));
//						linea.setBlAf02(descuento.getAporteConvenio()); //montoCredito, monto descontado
//						lineas.add(linea);
//					}
				}
			}
		
			if (cuotasPrestamos != null) {
				//Cuotas Préstamos
				for (int x=0;x<cuotasPrestamos.size();x++) {
					ContabilidadVO cuota = (ContabilidadVO) cuotasPrestamos.get(x);
					if(cuota.getAporteSocio()>0) {
//						cuentaDeudorPrestamo = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//								 "/application-settings/contabilidad/prestamo-tipo-"+ cuota.getTipoPrestamo());		 
//						logger.debug("Cuenta Deudores Préstamo: "+cuentaDeudorPrestamo);
//						if (cuentaDeudorPrestamo == null ||
//							cuentaDeudorPrestamo.length()==0)
//							throw new BusinessException("CCAF_BONIF_NUMEROCUENTAINVALIDO",
//								"Debe definir la Cuenta Deudores Préstamos tipo: "+cuota.getTipoPrestamo()+" en el Sistema");
								
						Linea linea = new Linea();
						linea.setBlId(blid);
						linea.setBlPgm(blpgm);
						linea.setBlUs01(blus01);
						linea.setBlUs02(blus02);
//						linea.setBlUs03(String.valueOf(cuentaDeudorPrestamo));
						linea.setBlUs03(cuota.getCuentaContable());
						linea.setBlUs04(blus04);
						linea.setBlUs05(cuota.getFullNombreSocio());
						linea.setBlUs06(cuota.getFullRutSocioSinGuion());
						linea.setBlUs07(cuota.getDocumento()); //Se escribe codTipoPrestamo:cuotaCobrada/CuotasTotales ejm: 2:4/6
																// ya que, no se cuenta con el número de prestamo
						linea.setBlAf02(cuota.getAporteSocio()); //montoCredito
						lineas.add(linea);
					}
				}
			}
		}
		return lineas;
	}
	
	/**
	 * Obtiene información los reembolsos generados semanalmente
	 * @param reembolsoCodigo, si es 0 (cero) los trae todos
	 * @return 
	 * @throws BusinessException
	 * @throws Exception
	 */
	public ArrayList getReembolsosTotales(long reembolsoCodigo) throws BusinessException,Exception{
		
		return bonificacionesDao.getReembolsoTotal(reembolsoCodigo);
			
	}
	
	/**
	 * Obtiene información los aportes realizados por bienestar en las diferentes coberturas
	 * @param ParamResumenMovimientosVO
	 * @param AporteBienestarVO con filtros
	 * @return AporteBienestarVO
	 * @throws BusinessException
	 * @throws Exception
	 */
	public AporteBienestarVO getResumenAportesBienestar(ParamResumenMovimientosVO parametros,DetalleAporteBienestarVO detalleAporteBienestar) throws BusinessException,Exception{

		int total=0;
		ArrayList listaDetalleAportesBienestar = bonificacionesDao.getResumenAportesBienestar(parametros.getFechaInicio(),parametros.getFechaFin(),detalleAporteBienestar);
		for (int x=0;x<listaDetalleAportesBienestar.size();x++) {
			DetalleAporteBienestarVO detalleAporte = (DetalleAporteBienestarVO)listaDetalleAportesBienestar.get(x);
			total = total + (int)detalleAporte.getMonto();
		}
		AporteBienestarVO aporteBienestar = new AporteBienestarVO();
		aporteBienestar.setDetalleAporteBienestar(listaDetalleAportesBienestar);
		aporteBienestar.setMontoTotal(total);
		 
		return aporteBienestar;
			
	}
	
	/**
	 * Genera el informe de los reembolsos generados semanalmente
	 * @param reembolsoCodigo
	 * @return 
	 * @throws BusinessException
	 * @throws Exception
	 */
	public InformeReembolsosVO getInformeReembolsos(long reembolsoCodigo) throws BusinessException,Exception{
		
		//Obtiene información del reembolso total consultado
		ReembolsoTotalVO reembolsoTotal = (ReembolsoTotalVO) bonificacionesDao.getReembolsoTotal(reembolsoCodigo).get(0);
		
		InformeReembolsosVO informe= new InformeReembolsosVO(reembolsoTotal);
		
		//Obtiene información resumida por oficina de los reembolsos de la semana consultada	
		ArrayList resumen = bonificacionesDao.getResumenReembolso(reembolsoCodigo);
		
		//Obtiene los detalles de los reembolsos de la semana consultada	
		ArrayList listaReembolsos = bonificacionesDao.getDetallesReembolso(reembolsoCodigo);
		
		ServicesEmpleadosDelegate personal = new ServicesEmpleadosDelegate();
		HashMap hashBancos = personal.getListaBancos();
		
		int filasTotales=0;
		
		for (int x=0;x<resumen.size();x++) {
			DetalleInformeReembolsosVO detalle = (DetalleInformeReembolsosVO) resumen.get(x);
			filasTotales = filasTotales + detalle.getCantidadFilas();
			detalle.setOficina(personal.getOficinaEmpleado(detalle.getCodigoOficina()));
			String rutPrevio=null;
			int index=0; 
			for (int y=0;y<listaReembolsos.size();y++) {
				ReembolsoVO ree = (ReembolsoVO) listaReembolsos.get(y);
				if(ree.getBanco() != null && !ree.getBanco().equals("")) {
					BancoVO bancoVo = (BancoVO)hashBancos.get(ree.getBanco());
					ree.setDescripcionBanco( bancoVo.getDescripcionLarca() );					
				}
				if (detalle.getCodigoOficina().equals(ree.getOficina())) {
					if (rutPrevio != null && rutPrevio.equals(ree.getRut())) {
						ReembolsoVO reePrevio = (ReembolsoVO) detalle.getReembolsos().get(index);
						reePrevio.setCasos(reePrevio.getCasos()+", "+ree.getCasoId());
						reePrevio.setMontoReembolso(reePrevio.getMontoReembolso()+ree.getMontoReembolso());
						reePrevio.setDescripcionBanco(ree.getDescripcionBanco());
						detalle.getReembolsos().set(index,reePrevio);
					} else {
						ree.setCasos(String.valueOf(ree.getCasoId()));
						detalle.getReembolsos().add(ree);
						index = detalle.getReembolsos().size()-1;
					}
				}
				rutPrevio=ree.getRut();
			}
			informe.getDetalleInforme().add(detalle);
		}
		informe.setTotalFilas(filasTotales);
		return informe;	
	}
	
	/** 
	 * Obtiene información de los codigos de descuento generados y la fecha de
	 * descuento. (Los cuales se realizan en forma mensual)
	 * @param 
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getDescuentosRealizados() throws Exception, BusinessException {
		return bonificacionesDao.getDescuentosRealizados();
	}
	
	/** 
	 * Obtiene información de los codigos de descuento en los cuales el socio ha enido
	 * descuento. (Los cuales se realizan en forma mensual)
	 * @param String rutSocio
	 * @return ArrayList de InformeDescuentosVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getDescuentosRealizadosBySocio(String rutSocio) throws Exception, BusinessException {
		return bonificacionesDao.getDescuentosRealizadosBySocio(rutSocio);	
	}	
	
	/**
	 * Genera el informe de los descuentos generados mensualmente
	 * @param InformeDescuentosVO
	 * @return InformeDescuentosVO
	 * @throws BusinessException
	 * @throws Exception
	 */
	public InformeDescuentosVO getInformeDescuentos(InformeDescuentosVO informe) throws BusinessException,Exception{

		if (informe == null)
			throw new BusinessException("CCAF_BONIF_DECUENTOINVALIDO",
						   "Se ha solicitado un informe de descuentos Nulo");
		
		//Services de Empleado
		ServicesEmpleadosDelegate personal = new ServicesEmpleadosDelegate();
		
		//Obtengo las oficinas que estan contenidas en el informe solicitado
		ArrayList oficinas = bonificacionesDao.getOficinasInformeDescuentos(informe.getCodigoDescuento(),null);
		
		//Obtengo los totales de los descuentos de los socios que estan contenidas en el informe solicitado
		ArrayList socios = bonificacionesDao.getSociosInformeDescuentos(informe.getCodigoDescuento(),null,null);

		for (int x=0;x<oficinas.size();x++) {
			DetalleDescuentosOficinaVO oficina = (DetalleDescuentosOficinaVO) oficinas.get(x);
			oficina.setOficina(personal.getOficinaEmpleado(oficina.getCodigoOficina()));
			
			for (int y=0;y<socios.size();y++) {
				DetalleDescuentosSocioVO socio = (DetalleDescuentosSocioVO) socios.get(y);
				if (oficina.getCodigoOficina().equals(socio.getCodigoOficina())) {
					oficina.getDetalleSocios().add(socio);
				}
			}
			informe.getDetalleOficinas().add(oficina);
		}
		return informe;	
	}
	
	/**
	 * Genera el detalle del descuento realizado a un socio
	 * @param rut y codigo de Descuento
	 * @return DetalleDescuentosSocioVO
	 * @throws BusinessException
	 * @throws Exception
	 */
	public DetalleDescuentosSocioVO getDetalleDescuentoSocio(String rut, long codigoDescuento) throws BusinessException,Exception{

		if (rut == null)
			throw new BusinessException("CCAF_BONIF_DECUENTOINVALIDO",
						   "Debe especificar un Socio");	
		
		//Services de Empleado
		ServicesEmpleadosDelegate personal = new ServicesEmpleadosDelegate();

		DetalleDescuentosSocioVO informeSocio = new DetalleDescuentosSocioVO();  
		
		//Obtengo los totales de los descuentos de los socios que estan contenidas en el informe solicitado
		ArrayList totales = bonificacionesDao.getSociosInformeDescuentos(codigoDescuento,rut,null);
		
		if (totales != null && totales.size()>0) {
			informeSocio = (DetalleDescuentosSocioVO) totales.get(0);
			informeSocio.setOficina(personal.getOficinaEmpleado(informeSocio.getCodigoOficina()));
			informeSocio.setDepartamento(personal.getDeptoEmpleadoByCodigoDepto(informeSocio.getCodDepartamento()));		
		
			//Obtengo los detalles de los casos del socios que estan
			//contenidas en el informe solicitado y que corresponden a los descuentos del mes
			informeSocio.getCasosDescontados().addAll(getDetalleCasosDescontados(codigoDescuento, rut, null));
		
			//Obtengo los detalles de los descuentos de los socios que estan
			//contenidas en el informe solicitado y que corresponden a las cuotas de los préstamos
			CuotaPrestamoVO cuotaFiltro = new CuotaPrestamoVO();
			cuotaFiltro.setCodigoDescuento(codigoDescuento);
			cuotaFiltro.setRut(rut);
			ArrayList cuotas = bonificacionesDao.getCuotasPrestamo(cuotaFiltro);
			for (int l=0;l<cuotas.size();l++) {
				CuotaPrestamoVO cuotaPrestamo = (CuotaPrestamoVO) cuotas.get(l);
				CuotasPrestamoDescontadasVO cuotaPrestamoDescontada = new CuotasPrestamoDescontadasVO();
				cuotaPrestamoDescontada.setCuotaActual(cuotaPrestamo.getCuotaActual());
				cuotaPrestamoDescontada.setDescripcionPrestamo("Cuota Préstamo tipo: "+cuotaPrestamo.getTipoPrestamo());
				cuotaPrestamoDescontada.setMonto(cuotaPrestamo.getMonto());
				cuotaPrestamoDescontada.setNumeroCuotasTotales(cuotaPrestamo.getNumeroCuotasTotales());
				cuotaPrestamoDescontada.setRut(cuotaPrestamo.getRut());
				cuotaPrestamoDescontada.setTipoPrestamo(cuotaPrestamo.getTipoPrestamo());
				informeSocio.getCuotasPrestamos().add(cuotaPrestamoDescontada);
			}
		}
		return informeSocio;
	}
	
	
	/**
	 * Devuelve los casos descontados con la información del detalle de cada caso
	 * @param long codigoDescuento, String rut, String oficina
	 * @return ArrayList de CasosDescontados
	 */
	private ArrayList getDetalleCasosDescontados(long codigoDescuento, String rut, String oficina) throws BusinessException,Exception {
		
		ArrayList retorno = new ArrayList();
		
		ArrayList listaSinFormato = bonificacionesDao.getCasosInformeDescuentos(codigoDescuento, rut, null);
		
		CasosDescontadosVO caso = null;
		long casoIdAnterior=0;
		for (int x=0;x<listaSinFormato.size();x++) {
			CasosDescontadosSinFormatoVO sinFormato = (CasosDescontadosSinFormatoVO) listaSinFormato.get(x);
			if(casoIdAnterior==sinFormato.getCasoId()) {
				DetalleCasoDescontadoVO detalle = new DetalleCasoDescontadoVO(sinFormato);
				caso.getDetalle().add(detalle);
			}else {
				caso = new CasosDescontadosVO(sinFormato);
				DetalleCasoDescontadoVO detalle = new DetalleCasoDescontadoVO(sinFormato);
				caso.getDetalle().add(detalle);
				retorno.add(caso);
				casoIdAnterior=sinFormato.getCasoId();
			}
		}
		
		return retorno; 
	}

	
	/**
	 * Genera el detalle del descuento realizado a una oficina
	 * @param codigo de Descuento, codigo de oficina
	 * @return DetalleDescuentosOficinaVO
	 * @throws BusinessException
	 * @throws Exception
	 */
	public DetalleDescuentosOficinaVO getDetalleDescuentosOficina(String codigoOficina, long codigoDescuento) throws BusinessException,Exception{

		if (codigoOficina == null)
			throw new BusinessException("CCAF_BONIF_DECUENTOINVALIDO",
						   "Debe especificar una Oficina");
		
		//Services de Empleado
		ServicesEmpleadosDelegate personal = new ServicesEmpleadosDelegate();
			
		DetalleDescuentosOficinaVO oficina = (DetalleDescuentosOficinaVO) bonificacionesDao.getOficinasInformeDescuentos(codigoDescuento,codigoOficina).get(0); 
			
		//Obtengo los totales de los descuentos de los socios que estan contenidas en la oficia solicitada
		ArrayList socios = bonificacionesDao.getSociosInformeDescuentos(codigoDescuento, null,codigoOficina);

		//Obtengo los detalles de los casos de los socios que estan
		//contenidas en el informe solicitado y que corresponden a las cuotas de los préstamos
		ArrayList casos = getDetalleCasosDescontados(codigoDescuento, null, codigoOficina); 
			
		//Obtengo los detalles de los descuentos de los socios que estan
		//contenidas en el informe solicitado y que corresponden a las cuotas de los préstamos
		CuotaPrestamoVO cuotaFiltro = new CuotaPrestamoVO();
		cuotaFiltro.setCodigoDescuento(codigoDescuento);
		cuotaFiltro.setCodigoOficina(codigoOficina);
		ArrayList cuotasPrestamos = bonificacionesDao.getCuotasPrestamo(cuotaFiltro);
		
		oficina.setOficina(personal.getOficinaEmpleado(oficina.getCodigoOficina()));
		
		for (int y=0;y<socios.size();y++) {
			DetalleDescuentosSocioVO socio = (DetalleDescuentosSocioVO) socios.get(y);
			socio.setDepartamento(personal.getDeptoEmpleadoByCodigoDepto(socio.getCodDepartamento()));
			if (oficina.getCodigoOficina().equals(socio.getCodigoOficina())) {
				for (int z=0;z<casos.size();z++) {
					CasosDescontadosVO caso = (CasosDescontadosVO) casos.get(z);
					if (socio.getRut().equals(caso.getRut()))
						socio.getCasosDescontados().add(caso);
				}
				for (int l=0;l<cuotasPrestamos.size();l++) {
					CuotaPrestamoVO cuotaPrestamo = (CuotaPrestamoVO) cuotasPrestamos.get(l);
					CuotasPrestamoDescontadasVO cuotaPrestamoDescontada = new CuotasPrestamoDescontadasVO();
					cuotaPrestamoDescontada.setCuotaActual(cuotaPrestamo.getCuotaActual());
					cuotaPrestamoDescontada.setDescripcionPrestamo("Cuota Préstamo tipo: "+cuotaPrestamo.getDescripcionTipoPrestamo());
					cuotaPrestamoDescontada.setMonto(cuotaPrestamo.getMonto());
					cuotaPrestamoDescontada.setNumeroCuotasTotales(cuotaPrestamo.getNumeroCuotasTotales());
					cuotaPrestamoDescontada.setRut(cuotaPrestamo.getRut());
					cuotaPrestamoDescontada.setTipoPrestamo(cuotaPrestamo.getTipoPrestamo());
					if (socio.getRut().equals(cuotaPrestamoDescontada.getRut()))
						socio.getCuotasPrestamos().add(cuotaPrestamoDescontada);
				}
				oficina.getDetalleSocios().add(socio);
			}
		}
		
		return oficina;	
	}
	
	/** 
	 * Obtiene información de los codigos de descuento generados y la fecha de
	 * descuento. (Los cuales se realizan en forma mensual)
	 * Esta información corresponde a los cobros que se debe realizar a los 
	 * convenios (es para los convenios en los cuales ellos pagan el co-pago del socio 
	 * para ciertas prestaciones.
	 * @param 
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getDescuentosConvenios() throws Exception, BusinessException {
		return bonificacionesDao.getDescuentosConvenios();
	}
	
	/**
	 * Genera el informe de los descuentos que se deben realizar a los convenios mensualmente
	 * @param long codigoConvenio, long codigoDescuento
	 * @return InformeDescuentosConveniosVO
	 * @throws BusinessException
	 * @throws Exception
	 */
	public InformeDescuentosConveniosVO getInformeDescuentosConvenios(long codigoConvenio, long codigoDescuento) throws BusinessException,Exception{

		if (codigoDescuento == 0)
			throw new BusinessException("CCAF_BONIF_DECUENTOINVALIDO",
						   "Se ha solicitado un informe de descuentos Nulo");
		
		InformeDescuentosConveniosVO informe = new InformeDescuentosConveniosVO();
		ArrayList descuentosConvenios = bonificacionesDao.getDescuentosConvenios();
		for (int x=0; x<descuentosConvenios.size();x++) {
			informe = (InformeDescuentosConveniosVO)descuentosConvenios.get(x);
			if(informe.getCodigoDescuento()==codigoDescuento)
				break;	
		}
		
		informe.setDetalleConvenios(bonificacionesDao.getConveniosConDescuentos(codigoConvenio, codigoDescuento));
		
		return informe;	
	}
	
	
	/**
	 * Genera el detalle del descuento que se debe realizar a un convenio
	 * @param DetalleDescuentosConveniosVO, codigo de convenio
	 * @return 
	 * @throws BusinessException
	 * @throws Exception
	 */
	public DetalleDescuentosConveniosVO getDetalleDescuentosConvenio(DetalleDescuentosConveniosVO reporte, long codigoDescuento) throws BusinessException,Exception{

		if (reporte == null)
			throw new BusinessException("CCAF_BONIF_DECUENTOINVALIDO",
						   "Debe especificar un Convenio");
				
		ArrayList detalleCasos = bonificacionesDao.getDetalleDescuentosConvenio(reporte.getCodigoConvenio(),codigoDescuento);
		
		reporte.setDetalleCasos(detalleCasos);
		
		return reporte;	
	}
	
	
	/** 
	 * Obtiene información de los codigos de pagos generados y la fecha de
	 * pago. (Los cuales se realizan en forma mensual)
	 * @param 
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPagosRealizados() throws Exception, BusinessException {
		return bonificacionesDao.getPagosRealizados();
	}
	
	/** 
	 * Obtiene información de los convenios que presentan pago 
	 * para el codigo de pago pasado como parametro
	 * @param 
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public InformePagoConvenioVO getInformePagoConvenio(InformePagoConvenioVO informe) throws Exception, BusinessException {
		ArrayList resultado = bonificacionesDao.getConveniosInformePago(informe.getCodigoPago());
		informe.getDetalleConvenios().addAll(resultado);
		informe.setFilas(resultado.size());
		return informe;
	}
	
	/** 
	 * Obtiene información de los codigos de descuento
	 * que aún no se han pagado
	 * @param 
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPagosPorRealizar() throws Exception, BusinessException {
		return bonificacionesDao.getPagosPorRealizar();
	}

	/** 
	 * Obtiene información de los pagos que se deben realizar a 
	 * los convenios
	 * @param long codigoDescuento
	 * @return ArrayList PagoConvenioPendienteVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPagoConvenioPendientes(long codigoDescuento) throws Exception, BusinessException {

		logger.info(">>getPagoConvenioPendientes");
		ServicesCasosDelegate casosDelegate = new ServicesCasosDelegate();

		List pagoConvList = new ArrayList();
		
		ArrayList listaPagosConCuotas = new ArrayList();
		ArrayList listaSinCuotas = bonificacionesDao.getPagoConvenioPendientesSinCuotas(codigoDescuento);
		ArrayList listaPagoAnticipado = bonificacionesDao.getpagoConvenioPendientesAnticipado(codigoDescuento);
		ArrayList listaConCuotas = bonificacionesDao.getDetalleCasosPagoConvenioPendientesConCuotas(codigoDescuento);
		
		//se añaden todas las cuotas que corresponden a pago anticipado a la lista que contiene los casos sin mas de 1 cuota descontados.
		for(Iterator it = listaPagoAnticipado.iterator() ; it.hasNext() ; ){
			PagoConvenioPendienteCuotaVO p = (PagoConvenioPendienteCuotaVO) it.next();
			listaConCuotas.add(p);
		}
		
		boolean existe=false;
		
		// Genera un gran total por convenio para los casos con cuotas
		// deja el resultado en listaPagosConCuotas
		for(int x=0;x<listaConCuotas.size();x++) {
			existe=false;
			PagoConvenioPendienteCuotaVO pagoConCuota = (PagoConvenioPendienteCuotaVO) listaConCuotas.get(x);
			
			
			if (listaPagosConCuotas.size()==0){
				if(pagoConCuota.getNumeroCuotasBienestar()>=1) {//TODO: evaluar que implicancia tiene dejar >=1 (inicialmente estaba > 1) 
					if(pagoConCuota.getCuotaDescontada()==1){
						logger.debug("\tConvenio: "+pagoConCuota.getCodigoConvenio()+" caso: "+pagoConCuota.getCasid()+" cuotas bienestar");
						pagoConCuota.setMontoDescuento(pagoConCuota.getMontoTotal());
						listaPagosConCuotas.add(pagoConCuota);
						
						DeglosePagoConvenioVO  detPagoConv = new DeglosePagoConvenioVO(pagoConCuota.getCasid(),pagoConCuota.getCodigoConvenio(),pagoConCuota.getMontoDescuento(),codigoDescuento);
						pagoConvList.add(detPagoConv);
						
					}
				}else if (pagoConCuota.getNumeroCuotasConvenio()>=1) {//TODO: evaluar que implicancia tiene dejar >=1 (inicialmente estaba > 1)
					logger.debug("\tConvenio: "+pagoConCuota.getCodigoConvenio()+" caso: "+pagoConCuota.getCasid()+" cuotas convenio");
					
					 //calculamos cuanto es lo que tiene que poner bienestar de acuerdo a lo que bonifica para la cuota en cuestion
					 //y asi pagar correctamente al convenio

					//int cuotaBienestar = casosDelegate.getCuota(pagoConCuota.getAporteBienestar(),pagoConCuota.getNumeroCuotasConvenio(),pagoConCuota.getCuotaDescontada());
					int cuotaBienestar = casosDelegate.getMontoCuotaPagadoPorBienestar(pagoConCuota.getCasid(),pagoConCuota.getCuotaDescontada());
					
					/**
					 * modificación req 4353 para saldar deuda total
					 */
					/*if(pagoConCuota.getAporteBienestar() > cuotaBienestar){
						cuotaBienestar = pagoConCuota.getAporteBienestar();
					}*/
					pagoConCuota.setMontoDescuento(pagoConCuota.getMontoCuota()+cuotaBienestar);
					logger.debug("\tConvenio: "+pagoConCuota.getCodigoConvenio()+" caso : "+pagoConCuota.getCasid()+" parte de la cuota pagada por bienestar: "+cuotaBienestar+ " total pagado (campo montoDescuento): "+new Integer(pagoConCuota.getMontoCuota()+cuotaBienestar).toString());
					listaPagosConCuotas.add(pagoConCuota);
					
					DeglosePagoConvenioVO  detPagoConv = new DeglosePagoConvenioVO(pagoConCuota.getCasid(),pagoConCuota.getCodigoConvenio(),pagoConCuota.getMontoDescuento(),codigoDescuento);
					pagoConvList.add(detPagoConv);
				}
			} else {
				for (int y=0;y<listaPagosConCuotas.size();y++){
					PagoConvenioPendienteCuotaVO pago = (PagoConvenioPendienteCuotaVO) listaPagosConCuotas.get(y);
					if (pago.getCodigoConvenio()==pagoConCuota.getCodigoConvenio()){
						if(pagoConCuota.getNumeroCuotasBienestar()>= 1) { //TODO: evaluar que implicancia tiene dejar >=1 (inicialmente estaba > 1) OJO! cuotas bienestar aquí?? WTF?!
							if(pagoConCuota.getCuotaDescontada()==1){
								logger.debug("\tConvenio: "+pagoConCuota.getCodigoConvenio()+" caso: "+pagoConCuota.getCasid()+" cuotas bienestar");
								pago.setMontoDescuento(pago.getMontoDescuento()+pagoConCuota.getMontoTotal());
								listaPagosConCuotas.set(y,pago);
								
								DeglosePagoConvenioVO  detPagoConv = new DeglosePagoConvenioVO(pagoConCuota.getCasid(),pagoConCuota.getCodigoConvenio(),pagoConCuota.getMontoTotal(),codigoDescuento);
								pagoConvList.add(detPagoConv);
								
								existe=true;
								break;
							}
						}else if (pagoConCuota.getNumeroCuotasConvenio()>= 1) {//TODO: evaluar que implicancia tiene dejar >=1 (inicialmente estaba > 1)
							logger.debug("\tConvenio: "+pagoConCuota.getCodigoConvenio()+" caso: "+pagoConCuota.getCasid()+" cuotas convenio");
							//int cuotaBienestar = casosDelegate.getCuota(pagoConCuota.getAporteBienestar(),pagoConCuota.getNumeroCuotasConvenio(),pagoConCuota.getCuotaDescontada());
							int cuotaBienestar = casosDelegate.getMontoCuotaPagadoPorBienestar(pagoConCuota.getCasid(),pagoConCuota.getCuotaDescontada());
							
							pago.setMontoDescuento(pago.getMontoDescuento()+pagoConCuota.getMontoCuota()+cuotaBienestar);
							
							logger.debug("\tConvenio: "+pagoConCuota.getCodigoConvenio()+" caso : "+pagoConCuota.getCasid()+" parte de la cuota pagada por bienestar: "+cuotaBienestar+" total pagado (campo montoDescuento): "+new Integer(pagoConCuota.getMontoCuota()+cuotaBienestar).toString());
							listaPagosConCuotas.set(y,pago);
							
							DeglosePagoConvenioVO  detPagoConv = new DeglosePagoConvenioVO(pagoConCuota.getCasid(),pagoConCuota.getCodigoConvenio(),pagoConCuota.getMontoCuota()+cuotaBienestar,codigoDescuento);
							pagoConvList.add(detPagoConv);
							
							existe=true;
							break;
						}
					}
				}
				if (!existe) {
					if(pagoConCuota.getNumeroCuotasBienestar()>1) {//TODO: evaluar que implicancia tiene dejar >=1 (inicialmente estaba > 1)
						if(pagoConCuota.getCuotaDescontada()==1){
							pagoConCuota.setMontoDescuento(pagoConCuota.getMontoTotal());
							listaPagosConCuotas.add(pagoConCuota);
							
							//pagoConCuota.setMontoDescuento(pagoConCuota.getMontoTotal());
							//listaPagosConCuotas.add(pagoConCuota);
						}
					}else if (pagoConCuota.getNumeroCuotasConvenio()>=1) {//TODO: evaluar que implicancia tiene dejar >=1 (inicialmente estaba > 1)
						//logger.debug("\tConvenio: "+pagoConCuota.getCodigoConvenio()+" caso: "+pagoConCuota.getCasid()+" cuotas convenio");
						//int cuotaBienestar = casosDelegate.getCuota(pagoConCuota.getAporteBienestar(),pagoConCuota.getNumeroCuotasConvenio(),pagoConCuota.getCuotaDescontada());
						int cuotaBienestar = casosDelegate.getMontoCuotaPagadoPorBienestar(pagoConCuota.getCasid(),pagoConCuota.getCuotaDescontada());
						
						logger.debug("\tConvenio: "+pagoConCuota.getCodigoConvenio()+" caso : "+pagoConCuota.getCasid()+" parte de la cuota pagada por bienestar: "+cuotaBienestar+" total pagado (campo montoDescuento): "+new Integer(pagoConCuota.getMontoCuota()+cuotaBienestar).toString());
						
						pagoConCuota.setMontoDescuento(pagoConCuota.getMontoCuota()+cuotaBienestar);
						listaPagosConCuotas.add(pagoConCuota);
						
						DeglosePagoConvenioVO  detPagoConv = new DeglosePagoConvenioVO(pagoConCuota.getCasid(),pagoConCuota.getCodigoConvenio(),pagoConCuota.getMontoCuota()+cuotaBienestar,codigoDescuento);
						pagoConvList.add(detPagoConv);
					}
				}
			}			
		}
		
		
		/**
		 * Se añade todo lo que está en listaSinCuotas a deglose pago convenios.
		 */
		for(Iterator i = listaSinCuotas.iterator(); i.hasNext() ; ){
			PagoConvenioPendienteVO p = (PagoConvenioPendienteVO) i.next();
			
			DeglosePagoConvenioVO  detPagoConv = new DeglosePagoConvenioVO(0,p.getCodigoConvenio(),p.getMonto(),p.getCodigoConvenio());
			pagoConvList.add(detPagoConv);
		}
			
		
		/**
		 * Se añade a listaSinCuotas todos los casos con cuotas, agrupados por CONVENIO.
		 */
		if (listaPagosConCuotas.size()>0) {
			// Genera un gran total por convenio con los valores en cuotas y sin cuotas
			for(int x=0;x<listaPagosConCuotas.size();x++) {
				existe=false;
				PagoConvenioPendienteCuotaVO pago = (PagoConvenioPendienteCuotaVO)listaPagosConCuotas.get(x);
				for(int y=0;y<listaSinCuotas.size();y++){
					PagoConvenioPendienteVO pagoSinCuota = (PagoConvenioPendienteVO) listaSinCuotas.get(y);
					if (pago.getCodigoConvenio()==pagoSinCuota.getCodigoConvenio()) {
						logger.debug("\tconvenio: "+pagoSinCuota.getCodigoConvenio()+ " caso: "+ pago.getCasid() + " monto acum:"+new Integer(pagoSinCuota.getMonto()+pago.getMontoDescuento()).toString()+" monto: "+pagoSinCuota.getMonto());
						pagoSinCuota.setMonto(pagoSinCuota.getMonto()+pago.getMontoDescuento());
						listaSinCuotas.set(y,pagoSinCuota);
						
						//TODO: no tengo de donde sacar el CASID
						//DeglosePagoConvenioVO  detPagoConv = new DeglosePagoConvenioVO(1,pagoSinCuota.getCodigoConvenio(),pagoSinCuota.getMonto(),codigoDescuento);
						//pagoConvList.add(detPagoConv);
						
						existe=true;
						break;
					}
				}
				if (!existe) {
					
					//logger.debug("\tconvenio: "+pago.getCodigoConvenio()+ " caso: "+ pago.getCasid() + " monto acum:" + pago.getMontoDescuento());
					
					PagoConvenioPendienteVO pagoNuevo = new PagoConvenioPendienteVO();
					pagoNuevo.setCodigoConvenio(pago.getCodigoConvenio());
					pagoNuevo.setConceptoEgreso(pago.getConceptoEgreso());
					pagoNuevo.setArea(pago.getArea());
					pagoNuevo.setDv(pago.getDv());
					pagoNuevo.setMonto(pago.getMontoDescuento());
					pagoNuevo.setNombreConvenio(pago.getNombreConvenio());
					pagoNuevo.setRut(pago.getRut());
					listaSinCuotas.add(pagoNuevo);
					
					//DeglosePagoConvenioVO  detPagoConv = new DeglosePagoConvenioVO(pago.getCasid(),pagoNuevo.getCodigoConvenio(),pagoNuevo.getMonto(),codigoDescuento);
					//pagoConvList.add(detPagoConv);
				}
			}	
		}
		
		
		_debugSumList(pagoConvList);
		logger.info("<<getPagoConvenioPendientes");
		return listaSinCuotas;
	}
	
	
	public void _debugSumList(List pagoConvList){
		for(Iterator i = pagoConvList.iterator(); i.hasNext(); ){
			DeglosePagoConvenioVO d = (DeglosePagoConvenioVO) i.next();
			System.out.println("Caso: " + d.getCasoId() + " codConv: " + d.getCodConvenio() + " monto: " +d.getMonto());
		}
	}
	/** 
	 * Obtiene información de las cuotas de los prestamos que
	 * se encuentran vigentes
	 * 
	 * @return ArrayList de CuotaPrestamo
	 * @throws Exception
	 */
	public ArrayList getCuotasPrestamoVigenteSocio(String rut) throws Exception, BusinessException {
		return bonificacionesDao.getCuotasPrestamoVigenteSocio(rut);	
	}
	
	/** 
	 * Obtiene información de los descuentos aplicados a un Socio
	 * 
	 * @return ArrayList de DescuentoAplicadosSocioVO 
	 * @throws Exception
	 */
	public ArrayList getDescuentosAplicadosSocio(String rut) throws Exception, BusinessException {
		return bonificacionesDao.getDescuentosAplicadosSocio(rut);	
	}
	
	/**
	 * Retorna una lista que contiene:
	 * Tipo, codigo y descripción de un parametro utilizado en la aplicación
	 * @param tipo: india si es de tipo cuenta contable o concepto de tesoreria
	 * @param subTipo: se utliza en caso de cuenta contable para solicitar un tipo de cuenta especial
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getParametros(int tipo,String subTipo) throws Exception,BusinessException {
		
		Parametro parametroNinguna = new Parametro();
		Parametro parametroNingunaAdicional = new Parametro();
		ArrayList filtrados = new ArrayList();
		ArrayList lista = new ArrayList();
		String codigoPadre=null;
		
		parametroNinguna.setCodigo("0");
		parametroNingunaAdicional.setCodigo("0");
		switch (tipo) {
			case Parametro.TIPO_CONCEPTO_TESORERIA_BIENESTAR_CONVENIOS_INGRESOS:
					codigoPadre = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
											 "/application-settings/tesoreria/param/bienestar/conceptos-convenios/area");
					parametroNinguna.setDescripcion("NINGUNO");
					parametroNinguna.setCodigoPadre(codigoPadre);
					parametroNingunaAdicional.setDescripcion("NINGUNO");
					parametroNingunaAdicional.setCodigoPadre("0");					
					break;
			case Parametro.TIPO_CONCEPTO_TESORERIA_BIENESTAR_CONVENIOS_EGRESOS:
					codigoPadre = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
									 "/application-settings/tesoreria/param/bienestar/conceptos-convenios/area");			
					parametroNinguna.setDescripcion("NINGUNO");
					parametroNinguna.setCodigoPadre(codigoPadre);
					parametroNingunaAdicional.setDescripcion("NINGUNO");
					parametroNingunaAdicional.setCodigoPadre("0");					
					break;					
			case Parametro.TIPO_CONCEPTO_TESORERIA_BIENESTAR_SALUD_INGRESOS:
					codigoPadre = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
									 "/application-settings/tesoreria/param/bienestar/conceptos-salud/area");
					parametroNinguna.setDescripcion("NINGUNO");
					parametroNinguna.setCodigoPadre(codigoPadre);					
					break;					
			case Parametro.TIPO_CONCEPTO_TESORERIA_BIENESTAR_SALUD_EGRESOS:
					codigoPadre = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
							 "/application-settings/tesoreria/param/bienestar/conceptos-salud/area");			
					parametroNinguna.setDescripcion("NINGUNO");
					parametroNinguna.setCodigoPadre(codigoPadre);					
					break;					
			case Parametro.TIPO_CUENTA_CONTABLE:
					parametroNinguna.setDescripcion("NINGUNA");
					break;
			case Parametro.TIPO_AREA_TESORERIA:
					parametroNinguna.setDescripcion("NINGUNA");
					break;
		}
		
		lista = parametrosDao.getParametros(tipo, codigoPadre);
		
		if (subTipo.equals(Parametro.SUB_TIPO_TODOS)){
			lista.add(0,parametroNinguna);
			lista.add(0,parametroNingunaAdicional);
			return lista;
		}
		else {
			filtrados.add(0,parametroNinguna);
			for (int x=0;x<lista.size();x++) {
				Parametro param = (Parametro) lista.get(x);
				param.setCodigoPadre(codigoPadre); 
				if (param.getTipo().equals(subTipo))
					filtrados.add(param);
			}
			return filtrados;
		}	
	}
	
	/**
	 * req.   encargado de generar el detalle del comprobante e invocar al metodo registrarMovimientoTesoreriaBienestar de tesoreriaEJB
	 */
	public long generarComprobanteIngresoBienestar(long idCaso,int montoDescuento,String usuarioCreador,String rutSocio,long codConvenio) throws Exception,BusinessException{

		ArrayList parametrosFolio = parametrosDao.getParametrosSaldoDeudaTotal();
	
//		//Nombre de La Araucana
//		String  nombreAraucana = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//					  "/application-settings/bonificaciones/param/nombre-araucana");
		
		
		int areaNegocioBonificacionesBienestar = 0;
	    int codigoConceptoBonificacionesBienestar = 0;
		
		for(Iterator i = parametrosFolio.iterator() ; i.hasNext() ; ){
			Parametro p = (Parametro) i.next();
			if(new Integer(p.getTipo()).intValue() == codConvenio){
				areaNegocioBonificacionesBienestar = new Integer(p.getDescripcion()).intValue();
				codigoConceptoBonificacionesBienestar = new Integer(p.getCodigo()).intValue();
			}
			
		}
			
		
		if (areaNegocioBonificacionesBienestar == 0)
			throw new BusinessException("CCAF_TESO_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: bienestar/area-negocio-bonificaciones en el Sistema");
				 
		
//			Código de Concepto de Bonificaciones en Tesoreria Bienestar
		int codigoOficina = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
			   "/application-settings/tesoreria/param/bienestar/oficina"));
				
				 
		if (codigoConceptoBonificacionesBienestar == 0)
			throw new BusinessException("CCAF_TESO_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: bienestar/conceptos-bonificaciones/bonificaciones-por-pagar-socios en el Sistema");
				 

		ServicesTesoreriaDelegate tesoreria = new ServicesTesoreriaDelegate();

		//ArrayList detallesCaso = bonificacionesDao.getDetallesCaso(idCaso);
		
		//usado en registrarMovimientoTesoreriaBienestar para llenar tabla bf09f1
		//usado en registrarMovimientoTesoreriaBienestar para llenar el detalle del folio en tabla te07f2 

		Comprobante comprobante = preparaPagoSaldoDeudaTotal(montoDescuento,codigoOficina,usuarioCreador,rutSocio,areaNegocioBonificacionesBienestar,codigoConceptoBonificacionesBienestar,codConvenio);
		
		long folio = tesoreria.registrarMovimientoTesoreriaBienestar(comprobante);
		
		return folio;
				
	}
	
	/**
	 * Genera el informe de los reembolsos con detale por banco  (asepulveda 2013-04-22)
	 * @param reembolsoCodigo
	 * @return InformeReemBancoVO
	 * @throws BusinessException
	 * @throws Exception
	 */
	public InformeReemBancoVO getInformeReemBanco(long reembolsoCodigo) throws BusinessException,Exception{
		
		InformeReemBancoVO informeReemBancoVO = new InformeReemBancoVO();
		
		//Obtiene información del reembolso total consultado
		ReembolsoTotalVO reembolsoTotal = (ReembolsoTotalVO) bonificacionesDao.getReembolsoTotal(reembolsoCodigo).get(0);		
		informeReemBancoVO.setResumenReembolsoTotal(reembolsoTotal);
		
		//Obtengo un resumen por banco para el código de reembolso consultado
		DetalleBancoVO[] detalleBancos = bonificacionesDao.getDetalleBancos(reembolsoCodigo);
		
		if(detalleBancos != null && detalleBancos.length > 0){
			HashMap hashBancos = this.getListaBancos();
			for(int x=0; x<detalleBancos.length; x++) {
				DetalleBancoVO detalleBanco = detalleBancos[x];
				BancoVO banco = (BancoVO)hashBancos.get(detalleBanco.getCodigoBanco());
				if(banco != null)
					detalleBanco.setBanco(banco.getDescripcionLarca());
				else
					detalleBanco.setBanco("Sin Información");
			}
		}
		
		informeReemBancoVO.setDetalleBanco(detalleBancos);
		
		return informeReemBancoVO;
		
	}
	
	/**
	 * Devueve un HashMap con la lista de bancos (asepulveda 2013-04-22)
	 * @return HashMap con lista de bancos
	 * @throws Exception
	 */
	private HashMap getListaBancos() throws Exception {
		ServicesEmpleadosDelegate delegate = new ServicesEmpleadosDelegate();
		 return delegate.getListaBancos();
	}
	
	/**
	 * Obtiene la información del ultimo reembolso
	 * 
	 * @return ReembolsoTotalVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ReembolsoTotalVO getUltimoReembolsoTotal() throws Exception, BusinessException {
		return bonificacionesDao.getUltimoReembolsoTotal();
	}
	
	
	/*
	 * Se encarga de enviar un e-mail de aviso de los reembolsos a cada uno de los socios que tengan un reembolso en el proceso de reembolso indicado y que tengan registrado un e-mail 
	 */
	public void informarReembolsosViaEMail(long codigoReembolso) throws Exception {
		
		HashMap hashParam = bonificacionesDao.getParametros();
		String eMailModoTest = ((ParametroVO)hashParam.get(Constants.TRANS_REE_MAIL_MODO_TEST)).getValor();
		String eMailTest = ((ParametroVO)hashParam.get(Constants.TRANS_REE_MAIL_TEST)).getValor();
		int cantidadDeDias = Integer.parseInt(((ParametroVO)hashParam.get(Constants.TRANS_REE_SUMA_DIAS)).getValor());
		String hostLocal = ((ParametroVO)hashParam.get(Constants.TRANS_REE_MAILHOSTLOCAL)).getValor();
		String mailFrom = ((ParametroVO)hashParam.get(Constants.TRANS_REE_MAILFROM)).getValor();
		String userMail = ((ParametroVO)hashParam.get(Constants.TRANS_REE_USERMAIL)).getValor();
		String passMail = ((ParametroVO)hashParam.get(Constants.TRANS_REE_PASSMAIL)).getValor();
		String mailHostTo = ((ParametroVO)hashParam.get(Constants.TRANS_REE_MAILHOSTTO)).getValor();
		String mailPort = ((ParametroVO)hashParam.get(Constants.TRANS_REE_MAILPORT)).getValor();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		Calendar fechaFondosDisponibles = Calendar.getInstance();
		fechaFondosDisponibles.add(Calendar.DAY_OF_MONTH, cantidadDeDias);
		String fechaReembolso = formato.format(fechaFondosDisponibles.getTime());

		ParamMailVO paramMailVO = new ParamMailVO();
		paramMailVO.setFecha(fechaReembolso);
		paramMailVO.setHostLocal(hostLocal);
		paramMailVO.setMailFrom(mailFrom);
		paramMailVO.setMailHostTo(mailHostTo);
		paramMailVO.setMailPort(mailPort);
		paramMailVO.setPassMail(passMail);
		paramMailVO.setUserMail(userMail);

		ReembolsoSocioVO[] reembolsosPorSocio = bonificacionesDao.getDetallesReembolsoPorSocio(codigoReembolso);
		HashMap hashBancos = this.getListaBancos();
		if(reembolsosPorSocio != null && reembolsosPorSocio.length > 0) {
			for(int x=0; x<reembolsosPorSocio.length; x++){
				ReembolsoSocioVO reembolsoSocioVO = reembolsosPorSocio[x];
				BancoVO banco = (BancoVO)hashBancos.get(reembolsoSocioVO.getBanco());
				reembolsoSocioVO.setDescripcionBanco(banco != null ? banco.getDescripcionLarca() : "");
				if(eMailModoTest.equals(Constants.MAIL_MODO_TEST_NO)){ //Envía un e-mail a cada Socio
					paramMailVO.setMailTo(reembolsoSocioVO.getCorreo());
					this.enviarMailAvisoReembolso(reembolsoSocioVO, paramMailVO);
				} else {
					paramMailVO.setMailTo(eMailTest);
					if(eMailModoTest.equals(Constants.MAIL_MODO_TEST_1_MAIL)) { //Envía sólo 1 e-mail a la cuenta de test
						this.enviarMailAvisoReembolso(reembolsoSocioVO, paramMailVO);
						break;
					} else //Envía todos los e-mail que corresponden a la cuenta test
						this.enviarMailAvisoReembolso(reembolsoSocioVO, paramMailVO);					
				}
			}
		}
		
		bonificacionesDao.updateIndicadorEnvioEMailReembolso(codigoReembolso);
	}
	
	/*
	 * Envía un e-mail informando al Socio del reembolso
	 */
	private void enviarMailAvisoReembolso(ReembolsoSocioVO reembolsoSocioVO, ParamMailVO paramMailVO) throws Exception {
		
		try
		{
			Properties props = new Properties();
			logger.debug("enviando email con Clave");
			props.setProperty("mail.smtp.host", paramMailVO.getHostLocal());
			props.put("mail.smtp.port", String.valueOf(paramMailVO.getMailPort()));
			props.put("mail.smtp.auth", "true" );
			props.put("mail.smtp.localhost", paramMailVO.getMailHostTo());
			
			Session session = Session.getInstance(props);
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(paramMailVO.getMailFrom()));

			String[] asCorreoDestino = new String[1];
			asCorreoDestino[0] = paramMailVO.getMailTo();

			InternetAddress address[] = new InternetAddress[asCorreoDestino.length];
			for (int i = 0; i < asCorreoDestino.length; i++)
				address[i] = new InternetAddress(asCorreoDestino[i]);
			message.setRecipients(Message.RecipientType.TO, address);
			
			NumberFormat nf = NumberFormat.getInstance();

			String subject = "Reembolso Bonificaciones";
			String body = "Sr(a) " + reembolsoSocioVO.getFullNombre() + "\n\n" +
					"Le informamos que hemos realizado una transferencia de fondos a su cuenta " + (reembolsoSocioVO.getTipoCuenta().equals("4") ? "vista" : "corriente") + " número: " + reembolsoSocioVO.getCuenta() + 
					" por un monto de: $" + nf.format((long)reembolsoSocioVO.getMontoReembolso()) + ", este monto corresponde al reembolso de bonificaciones de Bienestar." + "\n\n" + 
					"\n" +
					"El monto indicado estará disponible en su cuenta a partir del día: " + paramMailVO.getFecha();			
			
			message.setSubject(subject);
			message.setText(body);
			Transport t = session.getTransport("smtp");
			logger.debug("conectando al servidor de correo...");
			t.connect(paramMailVO.getMailHostTo(), Integer.parseInt(paramMailVO.getMailPort()), paramMailVO.getUserMail(), paramMailVO.getPassMail());
			logger.debug("Estado conexión: " + t.isConnected() + ", enviando mensaje...");
			t.sendMessage(message, message.getAllRecipients());

			t.close();
			logger.debug("fin envio mail");
		} catch (Exception e)
		{
			logger.error("problemas envio mail:", e);
		}
		
	}
	
	
	/*
	 * Genera el archivo del banco para los reembolsos
	 */
	public void generarArchivoBanco(long codigoReembolso) throws Exception {
		
		HashMap hashParam = bonificacionesDao.getParametros();
		
		ReembolsoTotalVO reemTot = (ReembolsoTotalVO)(bonificacionesDao.getReembolsoTotal(codigoReembolso)).get(0);

		String pathFile = ((ParametroVO)hashParam.get(Constants.TRANS_REE_PATH_FILE)).getValor();
		String nameFile = ((ParametroVO)hashParam.get(Constants.TRANS_REE_NAME_FILE)).getValor();
		String extensionFile = ((ParametroVO)hashParam.get(Constants.TRANS_REE_EXT_FILE)).getValor();
		String rutAraucana = ((ParametroVO)hashParam.get(Constants.RUT_ARAUCANA)).getValor();
		String dvRutAraucana = ((ParametroVO)hashParam.get(Constants.DV_RUT_ARAUCANA)).getValor();
		String tipoServicio = ((ParametroVO)hashParam.get(Constants.TRANS_REE_TIPO_SERVICIO)).getValor();
		String medioDeRespaldo = ((ParametroVO)hashParam.get(Constants.TRANS_REE_MEDIO_RESPALDO)).getValor();
		String medioDeRespaldoDetalle = ((ParametroVO)hashParam.get(Constants.TRANS_REE_MEDIO_RESPALDO_DET)).getValor();
		String numeroMedioDeRespaldo = ((ParametroVO)hashParam.get(Constants.TRANS_REE_NUM_MEDIO_RESPALDO)).getValor();
		String glosa = ((ParametroVO)hashParam.get(Constants.TRANS_REE_GLOSA)).getValor();
		String metodoDePago = ((ParametroVO)hashParam.get(Constants.TRANS_REE_METODO_PAGO)).getValor();
		String codigoBanco = ((ParametroVO)hashParam.get(Constants.TRANS_REE_COD_BANCO)).getValor();
		String codigoSucursal = ((ParametroVO)hashParam.get(Constants.TRANS_REE_COD_SUCURSAL)).getValor();
		String referenciaCliente = ((ParametroVO)hashParam.get(Constants.TRANS_REE_REF_CLIENTE)).getValor();
		String glosaDelPago = ((ParametroVO)hashParam.get(Constants.TRANS_REE_GLOSA_PAGO)).getValor();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		
		String fechaReembolso = formato.format(reemTot.getFecha());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String fechaPago = formato.format(cal.getTime());
				
		String fileName = pathFile + "/" + nameFile + codigoReembolso + extensionFile;
		PrintWriter out=null;

		
		ReembolsoSocioVO[] reembolsosPorSocio = bonificacionesDao.getDetallesReembolsoPorSocio(codigoReembolso);
		//HashMap hashBancos = this.getListaBancos();
		if(reembolsosPorSocio != null && reembolsosPorSocio.length > 0) {			
			try{
				out=new PrintWriter(new FileOutputStream(fileName),true);
								
				String encabezado = rutAraucana + dvRutAraucana + ",";
				encabezado += reembolsosPorSocio.length + ",";
				encabezado += reemTot.getTotal() + ",";
				encabezado += tipoServicio + ",";
				encabezado += medioDeRespaldo + ",";
				encabezado += numeroMedioDeRespaldo + ",";
				encabezado += glosa + " " + fechaReembolso;
				
				//Registra encabezado en el archivo
				out.println(encabezado);
				
				for(int x=0; x<reembolsosPorSocio.length; x++){
					ReembolsoSocioVO reembolsoSocioVO = reembolsosPorSocio[x];
					String fila = Long.parseLong(reembolsoSocioVO.getRut()) + reembolsoSocioVO.getDv() + ",";
					fila += reembolsoSocioVO.getFullNombre() + ",";
					fila += reembolsoSocioVO.getCorreo() + ",";
					fila += metodoDePago + ",";
					fila += reembolsoSocioVO.getBanco() + ",";
					
					String tipoCtaCte = ((ParametroVO)hashParam.get(Constants.TRANS_REE_TIPO_CTA_+ "1")).getValor();
					ParametroVO param = (ParametroVO)hashParam.get(Constants.TRANS_REE_TIPO_CTA_ + reembolsoSocioVO.getTipoCuenta());
					String tipoCta = param != null ? param.getValor() : tipoCtaCte;
					fila += tipoCta + ",";
					
					fila += reembolsoSocioVO.getCuenta() + ",";
					fila += fechaPago + ",";
					fila += ","; //Referencia1 Descontinuado
					fila += ","; //Referencia2 Descontinuado
					fila += (long)reembolsoSocioVO.getMontoReembolso() + ",";
					fila += medioDeRespaldoDetalle + ",";
					fila += numeroMedioDeRespaldo + ",";
					fila += reembolsoSocioVO.getBanco().equals(codigoBanco) ? codigoSucursal + "," : "" + ",";
					fila += referenciaCliente + ""+ codigoReembolso + ",";
					fila += glosaDelPago + " " + fechaReembolso;
					
					//Registra fila en el archivo
					out.println(fila);

				}
				
			} finally {
				if (out != null)
					out.close();
			}
		}
		
	}
	
	/**
	 * Revision: copia de metodo generarAsientosContables que no guarda registro en la BD.
	 * Genera Los Asientos contables y los envía a contabilidad, los
	 * asientos contables generados son:
	 * Asiento Contable: Bonificaciones Convenios
	 * Asiento Contable: Descuentos del Mes
	 * @param fecha
	 * @throws Exception
	 * @throws BusinessException
	 */
//	public void revisionGenerarAsientosContablesXML(ContabilidadPendienteVO contabilizar) throws Exception, BusinessException {
//		
//		//long secuenciaAsientoBonificacionesConvenios=0;
//		//long secuenciaAsientoDescuentosMes=0;
//		
//		// Servicio de ContabilidadEJB
//		ServicesContabilidadDelegate contabilidad = new ServicesContabilidadDelegate();
//			
//		logger.debug(">> revisionGerenarAsientosContablesXML");	
//		
//		
//		XStream xstream = new XStream();
//		xstream.alias("asiento", Asiento.class);
//		xstream.alias("InformeDescuentos",InformeDescuentos.class);
//		
//		SimpleDateFormat formato =
//			new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//		SimpleDateFormat formatoFechaPase =
//			new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
//		SimpleDateFormat formatoMes =
//			new SimpleDateFormat("MMMM", Locale.getDefault());
//		
//		
//		GregorianCalendar fec = new GregorianCalendar();
//		fec.setTime(contabilizar.getFechaDescuento());
//				
//		// Fecha Pase
//		Date fechaPase = formato.parse(
//							fec.get(Calendar.DAY_OF_MONTH)+
//							"/"+
//							(fec.get(Calendar.MONTH)+1)+
//							"/"+
//							fec.get(Calendar.YEAR));
//		
//		// Fecha Pase con Formato
//		String fechaPaseFormato = formatoFechaPase.format(fechaPase);
//		logger.debug("Fecha Pase: "+fechaPaseFormato);
//		
//		// Fecha Inicio
//		Date fechaInicio = formato.parse(
//						"01/"+
//						(fec.get(Calendar.MONTH)+1)+
//						"/"+
//						fec.get(Calendar.YEAR));
//		logger.debug("Fecha Inicio: "+fechaInicio);
//						
//		// Fecha Fin
//		Date fechaFin = formato.parse(
//						"01/"+
//						(fec.get(Calendar.MONTH)+2)+
//						"/"+
//						fec.get(Calendar.YEAR));
//		logger.debug("Fecha Fin"+fechaFin);
//		
//		// Mes Contabilizacion
//		String mesContabilizado = formatoMes.format(fechaPase);
//		logger.debug("Mes Contabilizado"+mesContabilizado);
//		
//		// Preparación de Asientos Contables
//		// Asiento Contable: Bonificaciones Convenios
//		// BHLDES
//		String bhldesBonificacionesConvenio = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//				 "/application-settings/contabilidad/param/bienestar/BHLDES-BON-CONV");
//		if (bhldesBonificacionesConvenio == null || bhldesBonificacionesConvenio.length() == 0)
//			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
//				"Debe definir el valor de la variable: BHLDES-BON-CONV en el Sistema");
//				
//		//BHPGM Nombre de Actividad Asiento Bonificaciones Convenios
//		String bhpgmBonificacionesConvenio = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//				 "/application-settings/contabilidad/param/bienestar/BHPGM-BON-CONV");
//		if (bhpgmBonificacionesConvenio == null || bhpgmBonificacionesConvenio.length() == 0)
//			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
//				"Debe definir el valor de la variable: BHPGM-BON-CONV en el Sistema");			
//		
//		bhldesBonificacionesConvenio=bhldesBonificacionesConvenio+" "+mesContabilizado;
//		
//		Asiento asientoBonificacionesConvenios = preparaAsiento(fechaPaseFormato,bhldesBonificacionesConvenio,bhpgmBonificacionesConvenio);
//		// Asiento Contable: Descuentos del Mes
//		String bhldesDescuentosMes = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//				 "/application-settings/contabilidad/param/bienestar/BHLDES-DES-MES");
//				 
//		bhldesDescuentosMes=bhldesDescuentosMes+" "+mesContabilizado;
//				 
//		if (bhldesDescuentosMes == null || bhldesDescuentosMes.length() == 0)
//			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
//				"Debe definir el valor de la variable: BHLDES-DES-MES en el Sistema");
//				
//		//BHPGM Nombre de Actividad Asiento Descuentos del Mes
//		String bhpgmDescuentosMes = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//				 "/application-settings/contabilidad/param/bienestar/BHPGM-DES-MES");
//		if (bhpgmDescuentosMes == null || bhpgmDescuentosMes.length() == 0)
//			throw new BusinessException("CCAF_CONTA_VARIABLENODEFINIDA",
//				"Debe definir el valor de la variable: BHPGM-DES-MES en el Sistema");
//				
//		Asiento asientoDescuentosMes = preparaAsiento(fechaPaseFormato,bhldesDescuentosMes,bhpgmDescuentosMes);
//		
//		// Codigos de Reembolso a Contabilizar --información de los reembolsos realizados em el periodo dado, obtenido desde BF22F1.
//		ArrayList codigosReembolso = bonificacionesDao.getCodigoReembolsosPorContabilizar(fechaInicio,fechaFin);
//		
//		// Casos cerrados por pago con prestamo
//		ArrayList casosPagadosPrestamo = bonificacionesDao.getPagoConPrestamo(fechaInicio,fechaFin);
//		 
//		// Recuperación de Información
//		
//		// Descuentos	
//		ArrayList descuentos = bonificacionesDao.getDescuentosMes(contabilizar.getCodigoDescuento());
//		
//		//2006.03.17 asepulveda
//		//Se agregan los casos pagados totalmente en forma anticipada con prestamo y/o abono del socio
//		ArrayList casosPagadosAnticipadamente = bonificacionesDao.getCasosPagadosAnticipadamente(fechaInicio, fechaFin);
//		descuentos.addAll(casosPagadosAnticipadamente);
//		
//		// Monto Total Descuentos
//		//total por uso convenios
//		int montoTotalDescuentosUsoConvenios = bonificacionesDao.getMontoTotalDescuento(contabilizar.getCodigoDescuento());
//				
//		/*
//		 * (asepulveda) Septiembre 2005 ahora se debe tener la siguiente consideración
//		 * 
//		 * La información de los préstamos que se deben enviar a contabilidad
//		 * debe ser la que corresponde al mes de la cuota descontada en el mes que se está
//		 * contabilizando, por lo tanto, corresponde al descuento que se realizo justo al
//		 * mes anterior al codigo de descuento de bonificaciones 
//		 */
//		//long codigoDescuentoPrestamos=contabilizar.getCodigoDescuento()-1;		 
//		 //17.03.2006 esto ya no es asì a partir de ahora por instrucción de Contabilidad (Manuel Zamorano)
//		 //debe ser la del mismo mes, tal como se realizaba antes de septiembre del 2005 
//		long codigoDescuentoPrestamos=contabilizar.getCodigoDescuento();		
//		
//		//total por uso prestamos
//		int montoTotalDescuentosPorPrestamos = bonificacionesDao.getMontoTotalDescuentoPrestamos(codigoDescuentoPrestamos);
//		
//		//Total en suma de uso convenios más cuotas prestamos
//		int montoTotalDescuentos = montoTotalDescuentosUsoConvenios + montoTotalDescuentosPorPrestamos;
//		// Total a pagar a acreedores
//		ArrayList acreedoresConvenios = bonificacionesDao.getAcreedoresConvenios(contabilizar.getCodigoDescuento());
//		// Reembolsos
//		ArrayList reembolsos = bonificacionesDao.getReembolsosMes(fechaInicio,fechaFin);	
//		
//		// Cuotas de Préstamos
//		ArrayList cuotasPrestamos = bonificacionesDao.getPrestamoMes(codigoDescuentoPrestamos);
//		// Aportes Bienestar 
//		ArrayList aportesBienestar = bonificacionesDao.getAportesBienestar(contabilizar.getCodigoDescuento(),codigosReembolso,fechaInicio,fechaFin);
//						
//		// Preparación de la información
//		
//		// Asiento Contable: Bonificaciones Convenios
//		asientoBonificacionesConvenios.getLineas().addAll(preparaLineasAsientoBonificacionesConvenios(descuentos,acreedoresConvenios,aportesBienestar, reembolsos, casosPagadosPrestamo));
//		// Asiento Contable: Descuentos del Mes
//		asientoDescuentosMes.getLineas().addAll(preparaLineasAsientoDescuentosMes(descuentos,montoTotalDescuentos,cuotasPrestamos));
//		
//		 // Generación de Asientos Contables
//		 
//		 // Asiento Contable: Bonificaciones Convenios
//		//secuenciaAsientoBonificacionesConvenios = revisionCreaAsientoContableBienestar(asientoBonificacionesConvenios); 
//		// Asiento Contable: Descuentos del Mes
//		//secuenciaAsientoDescuentosMes = contabilidad.creaAsientoContableBienestar(asientoDescuentosMes);
//		
//		// Registra Información de las secuencias generadas por contabilidad
//		
//		// Reembolsos
//		for (int x=0;x<codigosReembolso.size();x++) {
//			InformacionAsiento informacionAsiento = (InformacionAsiento) codigosReembolso.get(x);
//			informacionAsiento.setTipo(InformacionAsiento.TIPO_REEMBOLSO);
//			informacionAsiento.setFecha(new Date());
//			//informacionAsiento.setSecuencia(secuenciaAsientoBonificacionesConvenios);
//			informacionAsiento.setUsuario(contabilizar.getUsuario());
//			
//			//bonificacionesDao.insertInformacionAsientos(informacionAsiento);
//		}
//		// Descuentos
//		InformacionAsiento informacionAsiento = new InformacionAsiento();
//		informacionAsiento.setCodigo(contabilizar.getCodigoDescuento());
//		informacionAsiento.setTipo(InformacionAsiento.TIPO_DESCUENTO);
//		informacionAsiento.setFecha(new Date());
//		//informacionAsiento.setSecuencia(secuenciaAsientoBonificacionesConvenios);
//		informacionAsiento.setUsuario(contabilizar.getUsuario());
//		//bonificacionesDao.insertInformacionAsientos(informacionAsiento);
//		//informacionAsiento.setSecuencia(secuenciaAsientoDescuentosMes);
//		//bonificacionesDao.insertInformacionAsientos(informacionAsiento);
//		
//		
//		String xml = "";
//		xml += xstream.toXML(asientoDescuentosMes);
//		
//		File file = new File("gbh.xml");
//		file.createNewFile();
//		logger.debug("archivo GBH creado en : "+file.getAbsolutePath());
//		BufferedWriter out = new BufferedWriter(new FileWriter(file));
//		out.write(xml);
//		out.close();
//
//		
//		/**
//		 * cuadratura Contabilización
//		 */
//		
//		InformeDescuentos informeDesc = new InformeDescuentos();
//		List listaDesc = new ArrayList();
//		Map mapDebe = new HashMap();
//		Map mapHaber = new HashMap();
//		
//		for(Iterator i = asientoDescuentosMes.getLineas().iterator() ; i.hasNext(); ){
//			Linea linea = (Linea) i.next();
//
//			//Debe
//	 
//			if(mapDebe.containsKey(linea.getBlUs03())){
//				Integer debe = (Integer) mapDebe.get(linea.getBlUs03());
//				mapDebe.put(linea.getBlUs03(),new Integer(linea.getBlAf01() + debe.intValue()));
//			}
//			else{
//				mapDebe.put(linea.getBlUs03(),new Integer(linea.getBlAf01()));
//			}
//			//Haber
//			
//			if(mapHaber.containsKey(linea.getBlUs03())){
//				Integer haber = (Integer) mapDebe.get(linea.getBlUs03());
//				mapHaber.put(linea.getBlUs03(),new Integer(linea.getBlAf02() + haber.intValue()));
//			}
//			else{
//				mapHaber.put(linea.getBlUs03(),new Integer(linea.getBlUs02()));
//			}			
//		}
//		
//		/*
//		for(mapHaber.
//		
//		DetalleInformeDescuentos detInf = new DetalleInformeDescuentos();
//
//		detInf.setCuenta(linea.getBlUs03());
//		detInf.setDebe(linea.getBlUs01());
//		detInf.setHaber(linea.getBlUs02());
//		listaDesc.add(detInf);
//		*/
//		/*
//		informeDesc.setDetalleInformeDescuentos(listaDesc);
//		
//		File fileCuadratura = new File("cuadratura.xml");
//		fileCuadratura.createNewFile();
//		String xmlCuadratura = xstream.toXML(informeDesc);
//	
//		BufferedWriter outCuadratura = new BufferedWriter(new FileWriter(fileCuadratura));
//		outCuadratura.write(xmlCuadratura);
//		outCuadratura.close();
//		logger.debug("archivo CUADRATURA creado en : "+fileCuadratura.getAbsolutePath());
//*/
//		
//		logger.debug("<< revisionGerenarAsientosContables");
//	}
	

}
