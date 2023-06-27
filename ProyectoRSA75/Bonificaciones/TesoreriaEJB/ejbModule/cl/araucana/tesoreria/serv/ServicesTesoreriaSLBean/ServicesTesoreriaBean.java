package cl.araucana.tesoreria.serv.ServicesTesoreriaSLBean;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.log4j.Logger;

import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.tesoreria.dao.ComprobanteDAO;
import cl.araucana.tesoreria.dao.DAOFactory;
import cl.araucana.tesoreria.dao.DB2ComprobanteDAO;
import cl.araucana.tesoreria.dao.FolioDAO;
import cl.araucana.tesoreria.model.Comprobante;
import cl.araucana.tesoreria.model.Detalle;

import com.schema.util.FileSettings;


/**
 * @author asepulveda
 * Bean implementation class for Enterprise Bean: ServicesTesoreria
 * Servicios de Tesoreria de La Araucana
 */
public class ServicesTesoreriaBean implements javax.ejb.SessionBean {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	private ComprobanteDAO comprobanteDao;
	private FolioDAO folioDao;
	private String sistema=null;
	private String programaGeneraFolio=null;
	private String programaAnulaFolioEgreso=null;
	private String userBienestar=null;
	private String pswBienestar=null;
	private String userAraucana=null;
	private String pswAraucana=null;
	private int largoParamFolio=0;
	private int largoParamRespuesta=0;
	
	Logger logger = Logger.getLogger(ServicesTesoreriaBean.class);
	
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
		// Recurso DAO de Tesoreria
		int comprobanteDaoType = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/comprobante-dao-type"));	
		// Recurso DAO COBOL de Tesoreria
		int folioDaoType = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/folio-dao-type"));
		// Sistema donde se encuentra la función Folio
		sistema= FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/folio-sistema");
		// Ruta y nombre del programa Folio
		programaGeneraFolio= FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/funciones-cobol/genera-folio/funcion");
				 
		// Ruta y nombre del programa Folio
		programaAnulaFolioEgreso= FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/funciones-cobol/anula-folio-egreso/funcion");
		// Largo maximo de parametro folio
		largoParamFolio = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/funciones-cobol/anula-folio-egreso/parametros/largo-folio"));
		// Largo maximo de parametro folio
		largoParamRespuesta = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/funciones-cobol/anula-folio-egreso/parametros/largo-respuesta"));				 
				 
		// Usuario para obtener folios de Bienestar
		userBienestar = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				"/application-settings/tesoreria/param/bienestar/folio-user");
		// Contraseña del Usuario de Binestar
		pswBienestar = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				"/application-settings/tesoreria/param/bienestar/folio-pass");
		// Usuario para obtener folios de La Araucana
		userAraucana = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				"/application-settings/tesoreria/param/araucana/folio-user");
		// Contraseña del Usuario de La Araucana
		pswAraucana = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				"/application-settings/tesoreria/param/araucana/folio-pass");
				 
		try {
			//DAO Comprobante
			DAOFactory daoFactory = (DAOFactory)DAOFactory.getDAOFactory(comprobanteDaoType);
			comprobanteDao = daoFactory.getComprobanteDAO();
			//DAO Folio
			daoFactory = (DAOFactory)DAOFactory.getDAOFactory(folioDaoType);
			folioDao = daoFactory.getFolioDAO();
			
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
	 * Registra un movimiento en tesoreria de Bienestar
	 * utiliza el metodo registrarMovimiento
	 * @param Comprobante, comprobante
	 * @return long, número de folio generado
	 * @throws Exception
	 * @throws BusinessException
	 */
	public long registrarMovimientoTesoreriaBienestar(Comprobante comprobante) throws Exception, BusinessException {
		return registrarMovimiento(comprobante, DB2ComprobanteDAO.TESORERIA_BIENESTAR);
	}
	
	/**
	 * Registra un movimiento en tesoreria de Araucana
	 * utiliza el metodo registrarMovimiento
	 * @param Comprobante comprobante
	 * @return long, número de folio generado
	 * @throws Exception
	 * @throws BusinessException
	 */
	public long registrarMovimientoTesoreriaAraucana(Comprobante comprobante) throws Exception, BusinessException {
		return registrarMovimiento(comprobante, DB2ComprobanteDAO.TESORERIA_ARAUCANA);
	}
	
	/**
	 * Obtiene un numero de Folio
	 * Crea un Comprobante de Reembolso
	 * Crea los Detalle de Reembolso (Datos comunes)
	 * Crea los Detalle de Reembolso (Líneas de Detalle)
	 * @param Comprobante comprobante
	 * @param int, sistemaTesoreria
	 * @return long, número de folio generado
	 * @throws Exception
	 * @throws BusinessException
	 */
	public long registrarMovimiento(Comprobante comprobante, int sistemaTesoreria) throws Exception, BusinessException {
		
		long folio=0;
//		int montoInformadoDatosComunes=0;
//		int montoInformadoLineasDetalle=0;
		
		
		GregorianCalendar hoy = new GregorianCalendar();
		
		SimpleDateFormat formatoFecha =
			new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

		SimpleDateFormat formatoHora =
			new SimpleDateFormat("hh:mm:ss", Locale.getDefault());
			
		Date fechaHoy = formatoFecha.parse(hoy.get(Calendar.DAY_OF_MONTH)+"/"+(hoy.get(Calendar.MONTH)+1)+"/"+hoy.get(Calendar.YEAR));
		Date horaActual = formatoHora.parse(hoy.get(Calendar.HOUR)+":"+hoy.get(Calendar.MINUTE)+":"+hoy.get(Calendar.SECOND));
		
	
		//Validaciones
		
		if (comprobante == null)
			throw new BusinessException("CCAF_TESO_COMPROBANTEINVALIDO",
				"No se puede crear un Movimiento con Comprobante Nulo");
		
		String usu=null;
		String psw=null;
		switch (sistemaTesoreria) {
			case DB2ComprobanteDAO.TESORERIA_BIENESTAR:
				usu=userBienestar;
				psw=pswBienestar;
				break;
			case DB2ComprobanteDAO.TESORERIA_ARAUCANA:
				usu=userAraucana;
				psw=pswAraucana;
				break;
		}
				
		//Obtiene un Folio
		logger.debug("Ini Folio: "+folio);
		folio = Integer.parseInt(folioDao.obtenerFolio(sistema, programaGeneraFolio,usu,psw));
		if (folio==0)
			throw new BusinessException("CCAF_TESO_FOLIOINVALIDO",
			   "No se pudo obtener número de Folio");
			
		logger.debug("Fin Folio: "+folio);
		//Prepara el Comprobante
		comprobante.setFolioMovimiento(folio);
		
		comprobante.setFechaEmision(fechaHoy);
		comprobante.setHoraEmision(horaActual);
		comprobante.setFechaRecaudacion(formatoFecha.parse("01/01/01"));
		comprobante.setHoraRecaudacion(formatoHora.parse("00:00:00"));
		comprobante.setFechaDisponibilidad(fechaHoy);
		comprobante.setFechaApertura(formatoFecha.parse("01/01/01"));
		comprobante.setFechaCreacion(fechaHoy);
		comprobante.setHoraCreacion(horaActual);
		comprobante.setFechaCambio(formatoFecha.parse("01/01/01"));
		comprobante.setHoraCambio(formatoHora.parse("00:00:00"));
		
		//Registra el Comprobante
		comprobanteDao.insertComprobante(comprobante,sistemaTesoreria);
		
		if (comprobante.getDatosComunes() != null) {
			//Registra Datos de Detalle Común
			for (int x=0;x<comprobante.getDatosComunes().size();x++) {
				Detalle detalleComun = (Detalle) comprobante.getDatosComunes().get(x);
			
				//Prepara Detalle Común
				detalleComun.setItem(x+1);
				detalleComun.setFolioMovimiento(folio);
			
				detalleComun.setCreationDate(fechaHoy);
				detalleComun.setCreationTime(horaActual);
				detalleComun.setLastChangeDate(formatoFecha.parse("01/01/01"));
				detalleComun.setLastChangeTime(formatoHora.parse("00:00:00"));
			
				//Registra Detalle Común
				comprobanteDao.insertDetalle(detalleComun,sistemaTesoreria);
			}
		}

		
		if (comprobante.getLineasDetalle() != null) {
			//Registra Líneas de Detalle
			for (int x=0;x<comprobante.getLineasDetalle().size();x++) {
				Detalle lineaDetalle = (Detalle) comprobante.getLineasDetalle().get(x);
			
				//Prepara Línea Detalle
				lineaDetalle.setItem(x+1);
				lineaDetalle.setFolioMovimiento(folio);
			
				lineaDetalle.setCreationDate(fechaHoy);
				lineaDetalle.setCreationTime(horaActual);
				lineaDetalle.setLastChangeDate(formatoFecha.parse("01/01/01"));
				lineaDetalle.setLastChangeTime(formatoHora.parse("00:00:00"));

				//Registra Línea Detalle
				comprobanteDao.insertDetalle(lineaDetalle,sistemaTesoreria);
			}
		}
		
		return folio;
	}
	
	/**
	 * Consulta por el estado de un comprobante dado un número de folio
	 * utiliza el metodo getEstadoComprobante
	 * @param long folio
	 * @return String:
	 * 				estado si encuentra el folio
	 * 				null si no encuentra el folio
	 * @throws Exception
	 * @throws BusinessException
	 */
	public String getEstadoComprobanteTesoreriaBienestar(long folio) throws Exception, BusinessException{
					
		return getEstadoComprobante(folio, DB2ComprobanteDAO.TESORERIA_BIENESTAR);
	}
	
	/**
	 * Consulta por el estado de un comprobante dado un número de folio
	 * utiliza el metodo getEstadoComprobante
	 * @param long folio
	 * @return String:
	 * 				estado si encuentra el folio
	 * 				null si no encuentra el folio
	 * @throws Exception
	 * @throws BusinessException
	 */
	public String getEstadoComprobanteTesoreriaAraucana(long folio) throws Exception, BusinessException{
					
		return getEstadoComprobante(folio, DB2ComprobanteDAO.TESORERIA_ARAUCANA);
	}		
	
	
	/**
	 * Consulta por el estado de un comprobante dado un número de folio
	 * Consulta por el folio en la Tesoreria indicada en el parametro
	 * @param long folio
	 * @param int tesoreria
	 * @return String:
	 * 				estado si encuentra el folio
	 * 				null si no encuentra el folio
	 * @throws Exception
	 * @throws BusinessException
	 */
	private String getEstadoComprobante(long folio, int sistemaTesoreria) throws Exception, BusinessException{
					
		return comprobanteDao.getEstadoComprobante(folio, sistemaTesoreria);
	}


	/**
	 * Anula un comprobante de ingreso
	 * utiliza el metodo anulaComprobanteIngreso
	 * @param long folio
	 * @param String usuario
	 * @return int que indica la cantidad de filas actualizadas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int anulaComprobanteIngresoTesoreriaBienestar(long folio, String usuario) throws Exception, BusinessException {
	
		return anulaComprobanteIngreso(folio, DB2ComprobanteDAO.TESORERIA_BIENESTAR, usuario);	
	}
	
	/**
	 * Anula un comprobante de ingreso
	 * utiliza el metodo anulaComprobanteIngreso
	 * @param long folio
	 * @param String usuario
	 * @return int que indica la cantidad de filas actualizadas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int anulaComprobanteIngresoTesoreriaAraucana(long folio, String usuario) throws Exception, BusinessException {
	
		return anulaComprobanteIngreso(folio, DB2ComprobanteDAO.TESORERIA_ARAUCANA, usuario);	
	}	

	
	/**
	 * Anula un comprobante de ingreso
	 * @param long folio
	 * @param int tesoreria
	 * @param String usuario
	 * @return int que indica la cantidad de filas actualizadas
	 * @throws Exception
	 * @throws BusinessException
	 */
	private int anulaComprobanteIngreso(long folio, int tesoreria, String usuario) throws Exception, BusinessException {
		
		if (folio==0)
			throw new BusinessException("CCAF_TESO_FOLIOINVALIDO",
			   "El número de Folio es incorrecto");

		if (usuario==null || usuario.length()==0)
			throw new BusinessException("CCAF_TESO_FOLIOINVALIDO",
			   "El Usuario mo puede ser nulo");
			   			   
		return comprobanteDao.anulaComprobanteIngreso(folio, tesoreria, usuario);
		
	}
		
	/**
	 * Anula un comprobante de egreso
	 * utiliza el metodo anulaComprobanteEgreso
	 * @param long folio
	 * @return boolean:
	 * 		true: indica exito
	 * 		false: indica fracaso 
	 * @throws Exception
	 * @throws BusinessException
	 */
	public boolean anulaComprobanteEgresoTesoreriaBienestar(long folio) throws Exception, BusinessException {
	
		return anulaComprobanteEgreso(folio, DB2ComprobanteDAO.TESORERIA_BIENESTAR);	
	}
	
	/**
	 * Anula un comprobante de engreso
	 * utiliza el metodo anulaComprobanteEgreso 
	 * @param long folio
	 * @return boolean:
	 * 		true: indica exito
	 * 		false: indica fracaso
	 * @throws Exception
	 * @throws BusinessException
	 */
	public boolean anulaComprobanteEgresoTesoreriaAraucana(long folio) throws Exception, BusinessException {
	
		return anulaComprobanteEgreso(folio, DB2ComprobanteDAO.TESORERIA_ARAUCANA);	
	}
	
	/**
	 * Anula un comprobante de Egreso utilizando programa cobol
	 * @param int tesoreria
	 * @return boolean:
	 * 		true: indica exito
	 * 		false: indica fracaso 
	 * @throws Exception
	 * @throws BusinessException
	 */
	private boolean anulaComprobanteEgreso(long folio, int tesoreria) throws Exception, BusinessException {
		
		boolean resultadoOK = false;
		String usu=null;
		String psw=null;
		
		if (folio==0)
			throw new BusinessException("CCAF_TESO_FOLIOINVALIDO",
			   "El número de Folio es incorrecto");
	
		switch (tesoreria) {
			case DB2ComprobanteDAO.TESORERIA_BIENESTAR:
				usu=userBienestar;
				psw=pswBienestar;
				break;
			case DB2ComprobanteDAO.TESORERIA_ARAUCANA:
				usu=userAraucana;
				psw=pswAraucana;
				break;
		}

		logger.debug("userBienestar: "+userBienestar);
		logger.debug("pswBienestar: "+pswBienestar);
		logger.debug("userAraucana: "+userAraucana);
		logger.debug("pswAraucana: "+pswAraucana);
		logger.debug("largoParamRespuesta: "+largoParamRespuesta);
		logger.debug("largoParamFolio: "+largoParamFolio);
		
				
		//ejecuta programa anulador
		logger.debug("Folio a anular: "+folio);
		
		String respuesta = folioDao.anularComprobanteEgreso(folio, sistema, programaAnulaFolioEgreso, usu, psw, largoParamFolio, largoParamRespuesta);
		
		logger.debug("Respuesta: "+respuesta);
		
		if(respuesta.trim().length()==0)
			resultadoOK=true;

		return resultadoOK;
		
	}	
		
}
