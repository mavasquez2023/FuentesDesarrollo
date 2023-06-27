package cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosSLBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.log4j.Logger;

import cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO;
import cl.araucana.bienestar.bonificaciones.dao.DAOFactory;
import cl.araucana.bienestar.bonificaciones.model.Carga;
import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.model.Cobertura;
import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.Producto;
import cl.araucana.bienestar.bonificaciones.model.Rango;
import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.model.Talonario;
import cl.araucana.bienestar.bonificaciones.model.Vale;
import cl.araucana.bienestar.bonificaciones.model.VigenciaRango;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCoberturasDelegate;
import cl.araucana.bienestar.bonificaciones.vo.TalonarioVO;
import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;

import com.schema.util.FileSettings;
import com.schema.util.XmlUtils;


/**
 * @author aituarte
 * Bean implementation class for Enterprise Bean: ServicesSocios
 * Servicios de Consulta a Información de Socios de Bienestar de La Araucana
 */
public class ServicesConveniosBean implements javax.ejb.SessionBean {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	private BonificacionesDAO bonificacionesDao;
	
	Logger logger = Logger.getLogger(ServicesConveniosBean.class);
	
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
		int daoType = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/bonificaciones/dao-type"));	
		try {
			DAOFactory daoFactory = (DAOFactory)DAOFactory.getDAOFactory(daoType);
			bonificacionesDao = daoFactory.getBonificacionesDAO();
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
	 * Obtiene la lista de Convenios existentes
	 * @param Convenio
	 * @return ArrayList de Convenio
	 * @throws Exception
	 */
	public ArrayList getConvenios(Convenio convenio) throws Exception, BusinessException {
		return bonificacionesDao.getConvenios(convenio,0);
	}
	
	/**
	 * Obtiene la lista de Convenios relacionados con una cobertura
	 * @param codigo de Cobertura
	 * @return ArrayList de Convenio
	 * @throws Exception
	 */
	public ArrayList getConvenios( long codigoCobertura) throws Exception, BusinessException {
		logger.debug("Codigo Cobertura: " + codigoCobertura);
		Convenio convenio = new Convenio();
		convenio.setEstado(Convenio.STD_ACTIVO);
		return bonificacionesDao.getConvenios(convenio,codigoCobertura);
	}
	
	/** 
	 * Obtiene un Convenio existente
	 * @param codigo de Convenio
	 * @return Convenio
	 * @throws Exception
	 */
	public Convenio getConvenio(long codigoConvenio) throws Exception, BusinessException {
		return bonificacionesDao.getConvenio(codigoConvenio);
	}
	
	/** 
	 * Obtiene la lista de Talonarios existentes y los carga en el objeto
	 * convenio
	 * @param convenio
	 * @param talonario con los filtros
	 * @return 
	 * @throws Exception
	 */
	public Convenio getTalonarios(Convenio convenio, Talonario talonarioFiltro) throws Exception, BusinessException {
		convenio.removeAllTalonarios();
		convenio.setTalonarios(bonificacionesDao.getTalonarios(talonarioFiltro,convenio.getCodigo()));
		logger.debug("Talonarios: " + convenio.getTalonarios().size());
		return convenio;

	}
	
	/**
	 * Registra un nuevo Talonario asociado a un Convenio en Bienestar
	 * Al crear un talonario, el vale disponible corresponde al inicio de la secuencia
	 * @param convenio: el Objeto Convenio y talonario: el Objeto Talonario
	 */
	public void registraTalonario(Convenio convenio, Talonario talonario) throws Exception, BusinessException {

		logger.debug("Codigo Talonario: " + talonario.getCodigoTalonario());
		logger.debug("Estado Talonario: " + talonario.getEstado());
		logger.debug("inicio Secuencia: " + talonario.getInicioSecuencia());
		logger.debug("Fin Secuencia: " + talonario.getFinSecuencia());
		logger.debug("Vale Disponible: " + talonario.getValeDisponible());
		
		//Valido que la secuencia fina sea mayor que la secuencia incial
		if (talonario.getInicioSecuencia() >= talonario.getFinSecuencia())
			throw new BusinessException("CCAF_BONIF_TALONARIOINVALIDO",
							   "La secuencia final debe ser mayor a la secuencia inicial");

		if (bonificacionesDao.getTalonarios(talonario, convenio.getCodigo()).size() > 0)
			throw new BusinessException("CCAF_BONIF_TALONARIOINVALIDO",
						   "Las secuencias de inicio y fin ya existen para este convenio");

		//Inicializo la secuencia disponible con el inicio de secuencia
		talonario.setValeDisponible(talonario.getInicioSecuencia());
		bonificacionesDao.insertTalonario(talonario,convenio.getCodigo());
	}
	
	/**
	 * Actualiza la informacion de un talonario
	 * @param convenio: el Objeto Convenio y talonario: el Objeto Talonario
	 */
	public void actualizaTalonario(Convenio convenio, Talonario talonario) throws Exception,BusinessException {

		//Valido que la secuencia final sea mayor que la secuencia inicial
		if (talonario.getInicioSecuencia() >= talonario.getFinSecuencia())
			throw new BusinessException("CCAF_BONIF_TALONARIOINVALIDO",
							   "La secuencia final debe ser mayor a la secuencia inicial");

		/*
		 * Recupero la informacion del talonario desde la BD
		 * para realizar validaciones
		 */								   
		Talonario tal = bonificacionesDao.getTalonario(talonario.getCodigoTalonario());

		if (!tal.getEstado().equals(Talonario.ACTIVO))
			throw new BusinessException("CCAF_BONIF_TALONARIOINVALIDO",
								   "El estado del talonario no es correcto");
								   
		/*
		 * Valido el cambio de secuencia inicial
		 * Si existen vales asignados, ya no es posible cambiar la secuencia inicial
		 */
		 if (talonario.getInicioSecuencia() != tal.getInicioSecuencia() &&
			tal.getInicioSecuencia() < tal.getValeDisponible())
				throw new BusinessException("CCAF_BONIF_TALONARIOINVALIDO",
						"La secuencia de inicio no se puede modificar, ya que, existen vales asignados");
							   
		/*
		 * Valido el cambio de secuencia final
		 * La secuencia final no puede ser menor que el último vale asignado
		 */
		 if (talonario.getFinSecuencia() != tal.getFinSecuencia() &&
			talonario.getFinSecuencia() < tal.getValeDisponible())	
			throw new BusinessException("CCAF_BONIF_TALONARIOINVALIDO",
					"La secuencia final no se puede modificar, ya que, existen vales asignados con un número de secuencia mayor");

		/*
		 * Valido el cambio de secuencia disponible
		 */
		if (talonario.getValeDisponible() != tal.getValeDisponible()) {
			/*
			 * Valido que la nueva secuencia disponible éste dentro de los rangos final
			 * e inicial 
			 */
			if (talonario.getValeDisponible() < talonario.getInicioSecuencia() ||
				talonario.getValeDisponible() > talonario.getFinSecuencia())
					throw new BusinessException("CCAF_BONIF_TALONARIOINVALIDO",
						"La secuencia especificada como disponible esta fuera de los rangos validos");
						
			/*
			 * Valido que la nueva secuencia disponible no este utilizada (vales asignados)
			 */
			Vale valFiltro = new Vale();
			valFiltro.setCodigoTalonario(talonario.getCodigoTalonario());
			ArrayList vales = bonificacionesDao.getVales(valFiltro,null,0);
			for (int x = 0; x < vales.size(); x++) {
				Vale val = (Vale)vales.get(x);
				if (val.getCodigoVale() == talonario.getValeDisponible())
					throw new BusinessException("CCAF_BONIF_TALONARIOINVALIDO",
							"La secuencia especificada como disponible ya esta asignada"); 
			}
		}
		
		bonificacionesDao.updateTalonario(talonario);
	}
	
	/**
	 * Cambia el estado de un talonario a Eliminado
	 * @param convenio: el Objeto Convenio y talonario: el Objeto Talonario
	 */
	public void eliminaTalonario(Convenio convenio, Talonario talonario) throws  Exception, BusinessException {
		Talonario tal = bonificacionesDao.getTalonario(talonario.getCodigoTalonario());
		if (!tal.getEstado().equals(Talonario.ACTIVO))
			throw new BusinessException("CCAF_BONIF_TALONARIOINVALIDO",
					"El estado del talonario es incorrecto");	
		tal.setEstado(Talonario.ELIMINADO);
		bonificacionesDao.updateTalonario(tal);
	}
	
	
	/**
	 * Crea un nuevo Convenio en Bienestar
	 * @param convenio: el Objeto Convenio
	 */
	public void registraConvenio(Convenio convenio) throws Exception,BusinessException {
		
		//valido que el convenio este en estado "BORRADOR"
		if (!convenio.getEstado().equals(Convenio.STD_BORRADOR)){
			throw new BusinessException("CCAF_BONIF_CONVENIOINVALIDO",
									   "El estado del Convenio es incorrecto");
		}

		//Formateo el rut del convenio a 8 digitos
		convenio.setRut(Carga.formateaRut(convenio.getRut()));
		convenio.setEstado(Convenio.STD_BORRADOR);
		convenio.setFecInicio(null);
		convenio.setFecFin(null);
		bonificacionesDao.insertConvenio(convenio);

	}
	
	/**
	 * Modifica un Convenio en Bienestar
	 * @param convenio: el Objeto Convenio
	 */
	public void actualizaConvenio(Convenio convenio) throws Exception, BusinessException {
		
		Convenio conv = bonificacionesDao.getConvenio(convenio.getCodigo());
		
		//valido que el convenio no este este en estado "ELIMINADO"
		if (conv.getEstado().equals(Convenio.STD_ELIMINADO)){
			throw new BusinessException("CCAF_BONIF_CONVENIOINVALIDO",
									   "El Convenio se encuentra Eliminado");
		}
		
		convenio.setFecInicio(conv.getFecInicio());
		convenio.setFecFin(conv.getFecFin());
		bonificacionesDao.updateConvenio(convenio);
	}
	
	/**
	 * Activa un Convenio en Bienestar
	 * @param convenio: el Objeto Convenio
	 */
	public void activaConvenio(Convenio convenio) throws Exception,BusinessException {
		
		SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy", Locale.getDefault());
		GregorianCalendar hoy = new GregorianCalendar();
		
		int dia = hoy.get(Calendar.DAY_OF_MONTH);
		int mes = hoy.get(Calendar.MONTH)+1;
		int anio = hoy.get(Calendar.YEAR);
		
		//valido que la carga este en estado "BORRADOR"
		if (!convenio.getEstado().equals(Convenio.STD_BORRADOR)){
			throw new BusinessException("CCAF_BONIF_CONVENIOINVALIDO",
									   "El estado del Convenio es incorrecto");
		}
		Convenio conActivar = bonificacionesDao.getConvenio(convenio.getCodigo());
		conActivar.setEstado(Convenio.STD_ACTIVO);
		conActivar.setFecInicio(formato.parse(dia + "/" + mes + "/" + anio));
		bonificacionesDao.updateConvenio(conActivar);
		convenio.setEstado(Convenio.STD_ACTIVO);
	}
	
	/**
	 * Desactiva un Convenio en Bienestar
	 * @param convenio: el Objeto Convenio
	 */
	public void desactivaConvenio(Convenio convenio) throws Exception,BusinessException {
		
		SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy", Locale.getDefault());
		GregorianCalendar hoy = new GregorianCalendar();

		int dia = hoy.get(Calendar.DAY_OF_MONTH);
		int mes = hoy.get(Calendar.MONTH)+1;
		int anio = hoy.get(Calendar.YEAR);
		
		Convenio conDesactivar = bonificacionesDao.getConvenio(convenio.getCodigo());
		
		//valido que el Convenio no éste actualmente Eliminado
		if (conDesactivar.getEstado().equals(Convenio.STD_ELIMINADO)){
			throw new BusinessException("CCAF_BONIF_CONVENIOINVALIDO",
									   "El Convenio ya se encuentra Eliminado");
		}
		
		conDesactivar.setEstado(Convenio.STD_ELIMINADO);
		conDesactivar.setFecFin(formato.parse(dia + "/" + mes + "/" + anio));
		bonificacionesDao.updateConvenio(conDesactivar);
		//convenio.setEstado(Convenio.STD_ELIMINADO);
	}
	
	/** 
	 * Obtiene la lista de Productos asociados a un convenio
	 * @param producto Producto
	 * @return ArrayList de Producto
	 * @throws Exception
	 */
	public Convenio getProductos(long casoId, Convenio convenio,Producto productoFiltro) throws Exception, BusinessException {
		
		if (casoId > 0) {
			Caso caso = bonificacionesDao.getCasoVO(casoId);
			convenio.setCodigo(caso.getCodigoConvenio());
		}
		convenio.removeAllProductos();
		productoFiltro.setEstado(Producto.STD_ACTIVO);
		convenio.setProductos(bonificacionesDao.getProductos(productoFiltro,convenio.getCodigo()));
		return convenio;
	}

	/** 
	 * Obtiene la lista de Productos activos asociados a un convenio
	 * @param long codigoConvenio,Producto productoFiltro
	 * @return ArrayList de Producto
	 * @throws Exception
	 */
	public ArrayList getProductosByConvenio(long codigoConvenio,Producto productoFiltro) throws Exception, BusinessException {
	
		productoFiltro.setEstado(Producto.STD_ACTIVO);
		return bonificacionesDao.getProductos(productoFiltro,codigoConvenio);
	}
	
	/** 
	 * Obtiene un Producto asociado a un convenio
	 * @param codigo de producto y codigo de convenio
	 * @return producto
	 * @throws Exception
	 */
	public Producto getProducto(long codProducto, long codConvenio) throws Exception, BusinessException {
		
		Producto producto = bonificacionesDao.getProducto(codProducto, codConvenio);
						
		return producto;
	}
	
	/**
	 * Crea un nuevo Producto asociado a un Convenio en Bienestar
	 * @param producto: el Objeto Producto
	 */
	public void registraProducto(Producto producto,long codigoConvenio) throws Exception, BusinessException {
		
		if (producto.getDescuento() > 100) 
			throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
						   "El porcentaje de descuento no puede ser mayor a 100%");
						   
		if (producto.getPorcentajeAporteConvenio() > 100) 
			throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
						   "El porcentaje de aporte convenio no puede ser mayor a 100%");
						   
		//ServicesCoberturasDelegate delegate = new ServicesCoberturasDelegate();
		//Cobertura cobertura = delegate.getAllRangosCobertura(producto.getCobertura());
		//ArrayList listaRangosVigentes = new ArrayList();
		//Rango rango = new Rango();
		//int porcentajeCoberturaBienestar=0;
		//try{
			//listaRangosVigentes = cobertura.getRangoVigente().getRangos();
			//rango = (Rango)listaRangosVigentes.get(listaRangosVigentes.size()-1);
			//porcentajeCoberturaBienestar= (int)rango.getRangoPorcentajeCobertura();
		//}catch (Exception e) {
		//}
		
		//asepulveda 2013-08-22
		//Al parecer es una validación erronea, ya que los % actuan por separado sobre el monto que va quedando por bonificar
//		if (producto.getPorcentajeAporteConvenio() + porcentajeCoberturaBienestar > 100) 
//			throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
//						   "El porcentaje del aporte del convenio más el aporte de bienestar no puede ser mayor a 100%");								   						   
		 
		Producto pro = bonificacionesDao.getProducto(producto.getCobertura().getCodigo(),codigoConvenio);
		//Si el producto existía previamente lo activo, si no existía lo creo
		if (pro.getCobertura() != null && pro.getCobertura().getCodigo() > 0) {
			bonificacionesDao.deleteRangosProducto(producto.getCobertura().getCodigo(),codigoConvenio);
			producto.setEstado(Producto.STD_ACTIVO);
			bonificacionesDao.updateProducto(producto, codigoConvenio);
		}
		else
			bonificacionesDao.insertProducto(producto,codigoConvenio);
	}
	
	/**
	 * Actualiza un Producto asociado a un Convenio en Bienestar
	 * @param producto: el Objeto Producto y codigo de convenio
	 */
	public void actualizaProducto(Producto producto, long codigoConvenio) throws Exception, BusinessException {

		if (producto.getDescuento() > 100) 
			throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
						   "El porcentaje de descuento no puede ser mayor a 100%");
						   
		if (producto.getPorcentajeAporteConvenio() > 100) 
			throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
						   "El porcentaje de aporte convenio no puede ser mayor a 100%");
						   
		ServicesCoberturasDelegate delegate = new ServicesCoberturasDelegate();
		Cobertura cobertura = delegate.getAllRangosCobertura(producto.getCobertura());
		ArrayList listaRangosVigentes = new ArrayList();
		Rango rango = new Rango();
		int porcentajeCoberturaBienestar=0;
		try{
			listaRangosVigentes = cobertura.getRangoVigente().getRangos();
			rango = (Rango)listaRangosVigentes.get(listaRangosVigentes.size()-1);
			porcentajeCoberturaBienestar= (int)rango.getRangoPorcentajeCobertura();
		}catch (Exception e) {
			
		}
		
		if (producto.getPorcentajeAporteConvenio() + porcentajeCoberturaBienestar > 100) 
			throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
						   "El porcentaje del aporte del convenio más el aporte de bienestar no puede ser mayor a 100%");						   
						   
		bonificacionesDao.updateProducto(producto, codigoConvenio);
	}
	
	/**
	 * Elimina un Producto asociado a un Convenio en Bienestar
	 * @param producto: el Objeto Producto y codigo de convenio
	 */
	public void eliminaProducto(Producto producto, long codigoConvenio) throws Exception, BusinessException {
		producto.setEstado(Producto.STD_INACTIVO);
		bonificacionesDao.updateProducto(producto, codigoConvenio);
	}

	/** 
	 * Obtiene los Rangos de un Producto existente
	 * @param codigo convenio y codigo de producto
	 * @return ArrayList de Rangos
	 * @throws Exception
	 */
	public Producto getRangosProducto(long codigoConvenio, Producto producto) throws Exception, BusinessException {
		
		ServicesCoberturasDelegate coberturasDelegate = new ServicesCoberturasDelegate();
		logger.debug("Parametros Codigo: " + codigoConvenio + "codigo producto: " + producto.getCobertura().getCodigo());
		ArrayList rangosProducto = bonificacionesDao.getRangosProducto(codigoConvenio,producto.getCobertura().getCodigo());
		if (rangosProducto.size() > 0 )
			producto.setRango(rangosProducto);
		else{
			producto.setCobertura(coberturasDelegate.getAllRangosCobertura(producto.getCobertura()));
			VigenciaRango rangoVigente = producto.getCobertura().getRangoVigente();
			if(rangoVigente!=null)
				producto.setRango(rangoVigente.getRangos());
		}
			
			
		logger.debug("Rangos: " + producto.getRango().size());
		return producto;
	}
	
	/**
	 * Actualiza un rango asociado a un Producto
	 * @param producto: el Objeto Producto
	 */
	public void actualizaRangoProducto(Producto producto,long codigoConvenio) throws Exception,BusinessException {
				
		if (producto.getRango().size() == 0) {
			//Elimino los rangos existentes
			bonificacionesDao.deleteRangosProducto(codigoConvenio,producto.getCobertura().getCodigo());
			//Actualizo el producto sin cuenta de de gastos (propia)
			producto.setCuentaGasto(0);
			bonificacionesDao.updateCuentaProducto(producto,codigoConvenio);
		} else {
			
			//validacion de cuenta de gastos
			if (producto.getCuentaGasto() == 0)
				throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
							   "Debe seleccionar una Cuenta de Gastos previamente");
			
			//validacion de nuevos rangos definidos
			for (int x = 0; x < producto.getRango().size();x++) {
				logger.debug("Rango: " + (x+1) + " Inicio Rango: " + ((Rango)producto.getRango().get(x)).getRangoInicio());
				if (((Rango)producto.getRango().get(x)).getRangoPorcentajeCobertura() > 100)
					throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
									   "La bonificación no puede ser mayor a 100%, Rango: " + (x+1));
				//validacion del primer rango
				if (x==0) {
					//El primer rango debe comenzar con 0 (cero)
					if (((Rango)producto.getRango().get(x)).getRangoInicio() != 0)
						throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
											   "El rango inicial debe comenzar con 0 (cero)");
					//El fin de rango debe ser siempre mayor o igual al inicio de rango
					if (((Rango)producto.getRango().get(x)).getRangoInicio() > ((Rango)producto.getRango().get(x)).getRangoFin())
						throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
											   "El Fin de Rango debe ser mayor al inicio de Rango, Rango: " + (x+1));
				}
			
				//validacion de rangos (despues del primero)
				if (x>0 && x < (producto.getRango().size())) {
				
					/* Validacion de inicio de rango
					 * El inicio de rango debe ser igual al rango final del rango anterior
					 */
					logger.debug("Rango: " + x + " Inicio Rango: " + ((Rango)producto.getRango().get(x)).getRangoInicio() + " Inicio Rango: " + ((Rango)producto.getRango().get(x-1)).getRangoFin());
					if ((((Rango)producto.getRango().get(x)).getRangoInicio()) != ((Rango)producto.getRango().get(x-1)).getRangoFin())	
						throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
												   "El inicio del rango: " + (x+1) + " es incorrecto");
				
					/*validacion de fin de rango
					 * El fin de rango debe ser mayor a cero
					 * y mayor al inicio de rango (del mismo rango)
					 */
					if ((((Rango)producto.getRango().get(x)).getRangoFin() == 0) || (((Rango)producto.getRango().get(x)).getRangoFin() < ((Rango)producto.getRango().get(x)).getRangoInicio()))	
						throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
												   "El fin del rango: " + (x+1) + " es incorrecto");											
				}
			} 
		
			//Elimino los rangos existentes
			bonificacionesDao.deleteRangosProducto(codigoConvenio,producto.getCobertura().getCodigo());
			/*
			 * Comienzo a insertar los rangos, uno a uno
			 */
			for (int x = 0; x < producto.getRango().size();x++) {
				((Rango)producto.getRango().get(x)).setRangoID(x+1);
				bonificacionesDao.insertRangoProducto((Rango)producto.getRango().get(x),codigoConvenio,producto.getCobertura().getCodigo()); 
			}
			//Registro la cuenta de gastos
			bonificacionesDao.updateCuentaProducto(producto,codigoConvenio);
		}
	}
	
	
	/**
	 * Obtiene información de un Vale
	 * @param long codigo de vale
	 * @return Vale
	 */ 

	public Vale getVale(long codigoVale) throws Exception, BusinessException{
		//Trae solo los vales no anulados
		Vale valeFiltro = new Vale();
		valeFiltro.setValeAnulado(Vale.NO_ANULADO);
		valeFiltro.setCaso_id(-1);
		valeFiltro.setCodigoVale(codigoVale);
		ArrayList retorno = bonificacionesDao.getVales(valeFiltro,null,0);
		if (retorno.size() == 1)
			return (Vale)retorno.get(0);
		else
			throw new BusinessException("CCAF_BONIF_VALEINVALIDO",
				   "No se pudo Obtener Información del Vale: " + codigoVale);
	}
	
	/** 
	 * Obtiene la lista de Talonarios disponibles
	 * @param talonario, Objeto talonario para opciones de filtro
	 * @return ArrayList de Talonario
	 * @throws Exception
	 */
	public ArrayList getTalonariosDisponibles(TalonarioVO talonarioVO) throws Exception, BusinessException {
		talonarioVO.setEstado(Talonario.ACTIVO);
		talonarioVO.setEstadoConvenio(Convenio.STD_ACTIVO);
		return bonificacionesDao.getTalonariosVO(talonarioVO);
	}
	
	/** 
	 * Obtiene informacion del ultimo vale disponible
	 * @param codigo de convenio y codigo de talonario
	 * @return TalonarioVO
	 * @throws Exception
	 */
	public Vale getValeDisponible(long codigoTalonario) throws Exception, BusinessException{
		TalonarioVO tal = new TalonarioVO();
		tal.setCodigoTalonario(codigoTalonario);
		ArrayList resultado = new ArrayList();
		resultado = bonificacionesDao.getTalonariosVO(tal);
		if (resultado.size() == 1) {
			return new Vale((TalonarioVO)resultado.get(0));
		} else {
				throw new BusinessException("CCAF_BONIF_VALEINVALIDO",
										   "No se pudo obtener el ultimo vale disponible");
		}
	}
	
	/**
	 * Obtiene los vales asociados a un Rut de Socio
	 * @param socio Objeto Socio
	 * @return Socio con ArrayList de Vale
	 */ 

	public Socio getValesByRut(Socio socio) throws Exception, BusinessException{
		//Trae solo los vales no anulados
		Vale valeFiltro = new Vale();
		valeFiltro.setValeAnulado(Vale.NO_ANULADO);
		valeFiltro.setCaso_id(-1);
		socio.removeAllVales();
		socio.setVale(bonificacionesDao.getVales(valeFiltro,socio.getRut(),0));
		return socio;
	}
	
	/**
	 * Obtiene los vales asociados a un Convenio
	 * @param
	 * @return
	 */ 

	public ArrayList getValesByConvenio(long codigoConvenio) throws Exception, BusinessException{
		//Trae solo los vales no anulados
		Vale valeFiltro = new Vale();
		valeFiltro.setValeAnulado(Vale.NO_ANULADO);
		valeFiltro.setCaso_id(-1);
		return bonificacionesDao.getVales(valeFiltro,null,codigoConvenio);
	}
	
	/** 
	 * Obtiene los vales mo Usados asociados a un Rut de Socio
	 * @param socio Objeto Socio
	 * @return ArrayList de Vale
	 * @throws Exception
	 */
	public ArrayList getValesNoUsadosByRut(String rutSocio, long codigoConvenio) throws Exception, BusinessException{
		//Trae solo los vales no anulados y que no esten asociados a un caso
		Vale valeFiltro = new Vale();
		valeFiltro.setValeAnulado(Vale.NO_ANULADO);
		valeFiltro.setCaso_id(0);
		
		return bonificacionesDao.getVales(valeFiltro,rutSocio, codigoConvenio);
	}
	
	/**
	 * Asocia un Vale con un Socio
	 * @param vale, Objeto Vale y rut de Socio
	 */
	public void asociaValeSocio(Socio socio,Vale vale) throws Exception,BusinessException {
		// Rescata informacion del talonario correspondiente al vale
		Talonario tal = bonificacionesDao.getTalonario(vale.getCodigoTalonario());
		/*
		 * Valida que el vale asignado corresponda al ultimo disponible
		 * del talonario correspondiente al vale
		 */
		if (vale.getCodigoVale() != tal.getValeDisponible()) {
			throw new BusinessException("CCAF_BONIF_VALEINVALIDO",
									   "El vale asignado ya no está disponible");
		}		
		// Se actualiza la informacion del vale disponible en el Talonario 
		tal.setValeDisponible(vale.getCodigoVale() + 1);
		/*
		 * Si el nuevo vale disponible es igual a la secuencia final del talonario,
		 * entonces se debe cambiar el estado del talonario a "UTILIZADO"
		 */
		if (tal.getValeDisponible() == tal.getFinSecuencia())
			tal.setEstado(Talonario.UTILIZADO);
		// Se actualiza la informacion del talonario en la BD	
		bonificacionesDao.updateTalonario(tal);
		// Se crea el Vale en la BD
		vale.setFechaEntrega(new Date());
		
		logger.debug("Codigo Talonario: " + vale.getCodigoTalonario());
		logger.debug("Codigo Vale: " + vale.getCodigoVale());
		logger.debug("Socio Rut: " + socio.getRut());
		logger.debug("Caso Id: " + vale.getCaso_id());
		logger.debug("Monto: " + vale.getMonto());
		bonificacionesDao.insertVale(vale,socio.getRut());
	}
	
	/**
	 * Actualiza la informacion de un Vale
	 * @param vale, Objeto Vale y rut de Socio
	 */
	public void anulaVale(Socio socio, Vale vale) throws Exception,BusinessException {
		if (vale.getValeAnulado().equals(Vale.ANULADO))
		throw new BusinessException("CCAF_BONIF_VALEINVALIDO",
							   "El estado del vale es incorrecto");
		if (vale.getCaso_id() > 0)
			throw new BusinessException("CCAF_BONIF_VALEINVALIDO",
								   "El vale no puede ser eliminado, debido a que esta asociado a un caso");
		vale.setValeAnulado(Vale.ANULADO);
		vale.setFechaRecepcion(new Date());
		bonificacionesDao.updateVale(vale,socio.getRut());
	}
	
	public ArrayList getConveniosRe(Convenio convenio) throws Exception, BusinessException {
		String convenios="";
//		boolean exists=false;
		XmlUtils xu = new XmlUtils(AppConfig.getInstance().settingsFileName);
	
	 
		convenios=xu.getNodeValue("/application-settings/bonificaciones/param/convenios");
		//System.out.println(" convenios"+convenios);
				
		return bonificacionesDao.getConveniosRe(convenio,0,  convenios);
	}	
}
