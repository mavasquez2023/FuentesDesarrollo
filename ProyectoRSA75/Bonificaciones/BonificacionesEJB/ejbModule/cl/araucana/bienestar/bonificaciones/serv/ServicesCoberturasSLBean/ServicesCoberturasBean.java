package cl.araucana.bienestar.bonificaciones.serv.ServicesCoberturasSLBean;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import cl.araucana.bienestar.bonificaciones.common.Constants;
import cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO;
import cl.araucana.bienestar.bonificaciones.dao.DAOFactory;
import cl.araucana.bienestar.bonificaciones.model.Cobertura;
import cl.araucana.bienestar.bonificaciones.model.CoberturaAdicional;
import cl.araucana.bienestar.bonificaciones.model.Producto;
import cl.araucana.bienestar.bonificaciones.model.Rango;
import cl.araucana.bienestar.bonificaciones.model.VigenciaRango;
import cl.araucana.bienestar.bonificaciones.vo.AgrupacionCobertura;
import cl.araucana.bienestar.bonificaciones.vo.CoberturaEspecialVO;
import cl.araucana.bienestar.bonificaciones.vo.RangoCoberturaSinFormatoVO;
import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;

import com.schema.util.FileSettings;


/**
 * @author aituarte
 * Bean implementation class for Enterprise Bean: ServicesSocios
 * Servicios de Consulta a Información de Socios de Bienestar de La Araucana
 */
public class ServicesCoberturasBean implements javax.ejb.SessionBean {
	
	/** Serial */
	private static final long serialVersionUID = 1L;

	
	private BonificacionesDAO bonificacionesDao;
	
	Logger logger = Logger.getLogger(ServicesCoberturasBean.class);
	
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
	 * Obtiene la lista de Coberturas (Bonificaciones) de tipo adicional existentes
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getListaCoberturasAdicionales() throws Exception, BusinessException {
		Cobertura cobQuery = new Cobertura();
		Cobertura coberturaAdicional = new Cobertura();
		cobQuery.setEstado(Cobertura.STD_ACTIVO);
		cobQuery.setTipoCobertura(Cobertura.TIPO_ADICIONAL);
		ArrayList listaCoberturasAdicionales = bonificacionesDao.getListaCoberturasPorTipoAndEstado(cobQuery);
		coberturaAdicional.setCodigo(0);
		coberturaAdicional.setDescripcion("NINGUNA");
		coberturaAdicional.setTipoCobertura(Cobertura.TIPO_ADICIONAL);
		listaCoberturasAdicionales.add(coberturaAdicional);
		
		return listaCoberturasAdicionales;
	}
	
	/** 
	 * Obtiene la lista de Coberturas (Bonificaciones) de tipo especial existentes
	 * @return ArrayList de CoberturaEspecialVO
	 * @throws Exception
	 */
	public ArrayList getListaCoberturasEspeciales() throws Exception, BusinessException {
		
		ArrayList listaCoberturasEspeciales = new ArrayList();
		Cobertura coberturaQuery = new Cobertura();
		coberturaQuery.setTipoCobertura(Cobertura.TIPO_ESPECIAL);
		coberturaQuery.setEstado(Cobertura.STD_ACTIVO);
		ArrayList lista = bonificacionesDao.getListaCoberturasPorTipoAndEstado(coberturaQuery);
		
		for(int x=0;x<lista.size();x++){
			Cobertura cobertura = (Cobertura)lista.get(x);
			cobertura=getAllRangosCobertura(cobertura);
			CoberturaEspecialVO coberturaEspecialVO = new CoberturaEspecialVO();
			coberturaEspecialVO.setCodigo(cobertura.getCodigo());
			coberturaEspecialVO.setDescripcion(cobertura.getDescripcion());
			if(cobertura.getRangoVigente()!=null){
				Rango rango = (Rango)cobertura.getRangoVigente().getRangos().get(0);
				coberturaEspecialVO.setPorcentajeCobertura(rango.getRangoPorcentajeCobertura());
				listaCoberturasEspeciales.add(coberturaEspecialVO);
			}
		}
				
		return listaCoberturasEspeciales; //bonificacionesDao.getListaCoberturasEspeciales();
	}	
	
	/** 
	 * Obtiene la lista de Coberturas (Bonificaciones) de tipo DIRECTAS existentes (REQ-5088)
	 * @return ArrayList de CoberturaEspecialVO
	 * @throws Exception
	 */
	public ArrayList getListaCoberturasDirectas() throws Exception, BusinessException {
		
		ArrayList listaCoberturasEspeciales = new ArrayList();
		Cobertura coberturaQuery = new Cobertura();
		coberturaQuery.setTipoCobertura(Cobertura.TIPO_DIRECTA);
		coberturaQuery.setEstado(Cobertura.STD_ACTIVO);
		ArrayList lista = bonificacionesDao.getListaCoberturasPorTipoAndEstado(coberturaQuery);
		
		for(int x=0;x<lista.size();x++){
			Cobertura cobertura = (Cobertura)lista.get(x);
			cobertura=getAllRangosCobertura(cobertura);
			CoberturaEspecialVO coberturaEspecialVO = new CoberturaEspecialVO();
			coberturaEspecialVO.setCodigo(cobertura.getCodigo());
			coberturaEspecialVO.setDescripcion(cobertura.getDescripcion());
			if(cobertura.getRangoVigente()!=null){
				Rango rango = (Rango)cobertura.getRangoVigente().getRangos().get(0);
				coberturaEspecialVO.setPorcentajeCobertura(rango.getRangoPorcentajeCobertura());
				listaCoberturasEspeciales.add(coberturaEspecialVO);
			}
		}
				
		return listaCoberturasEspeciales; 
	}		
	
	/** 
	 * Obtiene la lista de Coberturas (Bonificaciones) existentes
	 * @param Cobertura
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getCoberturas(Cobertura cobertura) throws Exception, BusinessException {
		//cobertura.setEstado(Cobertura.STD_ACTIVO);
		return bonificacionesDao.getCoberturas(cobertura);
	}
	
	/** 
	 * Obtiene la lista de Coberturas que no esten en la lista de productos de un convenio
	 * @param codigo Convenio 
	 * @param Cobertura
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getComplementoProductos(long codigoConvenio) throws Exception, BusinessException {
		Cobertura coberturaQuery = new Cobertura();
		coberturaQuery.setEstado(Cobertura.STD_ACTIVO);
		coberturaQuery.setTipoCobertura(Cobertura.TIPO_DIRECTA);
		return bonificacionesDao.getComplementoProductos(codigoConvenio, coberturaQuery);
	}
	
	/** 
	 * Obtiene una Cobertura (Bonificacion) existente
	 * @param codigo de cobertura
	 * @return Cobertura
	 * @throws Exception
	 */
	public Cobertura getCobertura(long codigoCobertura) throws Exception,BusinessException {
		Cobertura cobertura = bonificacionesDao.getCobertura(codigoCobertura);
		cobertura.setCoberturaAdicional(bonificacionesDao.getRelacionCoberturaAdicional(codigoCobertura));
		return cobertura;
	}
	
	/**
	 * Crea una nueva cobertura en Bienestar
	 * @param Cobertura
	 */
	public void registraCobertura(Cobertura cobertura) throws Exception,BusinessException {
		
		if (!cobertura.getEstado().equals(Cobertura.STD_BORRADOR))
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
								   "El estado de la cobertura es incorrecto");
		
		if(!cobertura.getTipoTope().equals(Cobertura.TOPE_EVENTO) && cobertura.getTieneOcurrencias().equals(Cobertura.TIENE_OCURRENCIAS_SI))
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
							   "Una cobertura con tipo de tope distinto a Por Evento, no puede permitir más de una ocurrencia");
							   
		if(cobertura.getTieneOcurrencias().equals(Cobertura.TIENE_OCURRENCIAS_NO))
			cobertura.setEtiquetaOcurrencia(null);
									   
		bonificacionesDao.insertCobertura(cobertura);
		
		// Verifica si esta creando una relación cobertura adicional
		if(cobertura.getCoberturaAdicional() != null && cobertura.getCoberturaAdicional().size()>0) {
			for (int x=0; x<cobertura.getCoberturaAdicional().size();x++) {
				bonificacionesDao.registraRelacionCoberturaAdicional((CoberturaAdicional) cobertura.getCoberturaAdicional().get(x));	
			}
		}
	}
	
	/**
	 * Activa una nueva cobertura en Bienestar
	 * Valida que la cobertura tenga rangos definidos para poder activarla
	 * @param Cobertura
	 */
	public void activaCobertura(Cobertura cobertura) throws Exception,BusinessException {
		if (!cobertura.getEstado().equals(Cobertura.STD_BORRADOR))
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
								   "El estado de la cobertura es incorrecto");
								   
		cobertura.setEstado(Cobertura.STD_ACTIVO);
		bonificacionesDao.updateCobertura(cobertura);
			
	}
	
	
	/**
	 * Actualiza una cobertura en Bienestar
	 * @param Cobertura
	 */
	public void actualizaCobertura(Cobertura cobertura) throws  Exception, BusinessException {

		if (cobertura.getEstado().equals(Cobertura.STD_ELIMINADO))
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
								   "El estado de la cobertura es incorrecto");
								   
		if(!cobertura.getTipoTope().equals(Cobertura.TOPE_EVENTO) && cobertura.getTieneOcurrencias().equals(Cobertura.TIENE_OCURRENCIAS_SI))
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
							   "Una cobertura con tipo de tope distinto a Por Evento, no puede permitir más de una ocurrencia");
							   
		Cobertura coberturaQuery=new Cobertura();
		coberturaQuery.setCodigo(cobertura.getCodigo());
		getAllRangosCobertura(coberturaQuery);
		if(cobertura.getTipoCobertura().equals(Cobertura.TIPO_ESPECIAL) && coberturaQuery.getRangoVigente().getRangos().size()>1)
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
								"Una cobertura de tipo Especial, no puede tener más de un rango");
							   
		if(cobertura.getTieneOcurrencias().equals(Cobertura.TIENE_OCURRENCIAS_NO))
			cobertura.setEtiquetaOcurrencia(null);							   
		
			
		// Busca Información de la cobertura que se encuentra registrada para realizar validaciones 
		Cobertura coberturaOld = getCobertura(cobertura.getCodigo());
		
		// Verifica si esta cambiendo de tipo directa a tipo adicional
		if(cobertura.getTipoCobertura().equals(Cobertura.TIPO_ADICIONAL) && 
			coberturaOld.getTipoCobertura().equals(Cobertura.TIPO_DIRECTA)) {
				if(coberturaOld.getCoberturaAdicional().size()>0)
					bonificacionesDao.deleteRelacionCoberturaAdicional(cobertura.getCodigo());
		}
		// Si la cobertura sigue siendo de tipo directa
		else if(cobertura.getTipoCobertura().equals(Cobertura.TIPO_DIRECTA) && 
			coberturaOld.getTipoCobertura().equals(Cobertura.TIPO_DIRECTA)) {
			// Verifica si está eliminando la cobertura adicional,
			// si es así elimina el registro de relación coberturaAdicional				
			if(cobertura.getCoberturaAdicional().size()==0 && coberturaOld.getCoberturaAdicional().size()>0) {
				bonificacionesDao.deleteRelacionCoberturaAdicional(cobertura.getCodigo());
			}
			// Verifica si esta creando la relación cobertura adicional
			else if(cobertura.getCoberturaAdicional().size()>0 && coberturaOld.getCoberturaAdicional().size()==0) {
				for (int x=0;x<cobertura.getCoberturaAdicional().size();x++) {
					bonificacionesDao.registraRelacionCoberturaAdicional((CoberturaAdicional)cobertura.getCoberturaAdicional().get(x));	
				}
			}
			//Verifica si esta cambiando la coberturaAdicional definida actualmente
			else if(cobertura.getCoberturaAdicional().size()>0 && coberturaOld.getCoberturaAdicional().size()>0) {
				bonificacionesDao.deleteRelacionCoberturaAdicional(cobertura.getCodigo());
				for (int x=0;x<cobertura.getCoberturaAdicional().size();x++) {
					bonificacionesDao.registraRelacionCoberturaAdicional((CoberturaAdicional)cobertura.getCoberturaAdicional().get(x));	
				}
			} 
		}
		// Verifica si esta cambiendo de tipo adicional a tipo directa
		else if(cobertura.getTipoCobertura().equals(Cobertura.TIPO_DIRECTA) && 
		coberturaOld.getTipoCobertura().equals(Cobertura.TIPO_ADICIONAL)) {
			bonificacionesDao.deleteRelacionesCoberturaAdicionalByAdicional(cobertura.getCodigo());
		}
								   
		bonificacionesDao.updateCobertura(cobertura);
	}
	
	
	/**
	 * Elimina una cobertura en Bienestar
	 * @param Cobertura
	 */
	public void eliminaCobertura(Cobertura cobertura) throws Exception,BusinessException {
		if (cobertura.getEstado().equals(Cobertura.STD_ELIMINADO))
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
								   "El estado de la cobertura es incorrecto");

		// Desactiva los productos del tipo de cobertura que se esta eliminando
		bonificacionesDao.updateEstadoProductos(cobertura.getCodigo(), Producto.STD_ACTIVO,Producto.STD_INACTIVO);
		// Elimina la cobertura
		cobertura.setEstado(Cobertura.STD_ELIMINADO);
		bonificacionesDao.updateCobertura(cobertura);		
	}
		
	/**
	 * Actualiza los Rangos de una cobertura en Bienestar
	 * @param Cobertura
	 * @param int estadoRango
	 */
	public void actualizaRangosCobertura(Cobertura cobertura, int estadoRango) throws Exception,BusinessException{
		
		VigenciaRango vigenciaRango = null;
		
		switch (estadoRango) {
		  case Constants.RANGO_VIGENTE:
				vigenciaRango=cobertura.getRangoVigente();
				break;
		  case Constants.RANGO_FUTURO:
				vigenciaRango=cobertura.getRangoFuturo();
				break;
		  default:
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
				 "Los parámetros entregados son incorrectos");
		}
				
		if (vigenciaRango== null)
			throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
					"La información del Rango entregado es incorrecta");
				 	
		if(vigenciaRango.getCodigo()==0 && vigenciaRango.getCodigoCobertura()==0)
			vigenciaRango.setCodigoCobertura(cobertura.getCodigo());
			
			
		//verifica que las nuevas fechas no se traslapen con rangos previamente definidos
		if(isTraslapeDeFechas(vigenciaRango))
				throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
					   "Las fechas de este rango, se traslapan con las fechas de otro rangos previamente definido");
		 
		if (vigenciaRango.getRangos().size()==0) {
			//Elimino los rangos existentes
			bonificacionesDao.deleteRangosCobertura(cobertura.getCodigo(),vigenciaRango.getCodigo());
			bonificacionesDao.deleteVigenciaRango(vigenciaRango);			
		} else {
		
			//validacion de cuenta de gastos
			if (cobertura.getCuentaGasto() == 0)
				throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
							   "Debe seleccionar una Cuenta de Gastos previamente");
		
			//validacion de nuevos rangos definidos
			for (int x = 0; x < vigenciaRango.getRangos().size();x++) {
				logger.debug("Rango: " + (x+1) + " Inicio Rango: " + ((Rango)vigenciaRango.getRangos().get(x)).getRangoInicio());
				if (((Rango)vigenciaRango.getRangos().get(x)).getRangoPorcentajeCobertura() > 100)
					throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
									   "La bonificación no puede ser mayor a 100%, Rango: " + (x+1));
			
				//validacion del primer rango
				if (x==0) {
					//El primer rango debe comenzar con 0 (cero)
					if (((Rango)vigenciaRango.getRangos().get(x)).getRangoInicio() != 0)
						throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
											   "El rango inicial debe comenzar con 0 (cero)");
					//El fin de rango debe ser siempre mayor o igual al inicio de rango
					if (((Rango)vigenciaRango.getRangos().get(x)).getRangoInicio() > ((Rango)vigenciaRango.getRangos().get(x)).getRangoFin())
						throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
											   "El Fin de Rango debe ser mayor al inicio de Rango, Rango: " + (x+1));
				}
		
				//validacion de rangos (despues del primero)
				if (x>0 && x < (vigenciaRango.getRangos().size())) {
			
					/* Validacion de inicio de rango
					 * El inicio de rango debe ser igual al rango final del rango anterior
					 */
					logger.debug("Rango: " + x + " Inicio Rango: " + ((Rango)vigenciaRango.getRangos().get(x)).getRangoInicio() + " Inicio Rango: " + ((Rango)vigenciaRango.getRangos().get(x-1)).getRangoFin());
					if ((((Rango)vigenciaRango.getRangos().get(x)).getRangoInicio()) != ((Rango)vigenciaRango.getRangos().get(x-1)).getRangoFin())	
						throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
												   "El inicio del rango: " + (x+1) + " es incorrecto");
			
					/*validacion de fin de rango
					 * El fin de rango debe ser mayor a cero
					 * y mayor al inicio de rango (del mismo rango)
					 */
					if ((((Rango)vigenciaRango.getRangos().get(x)).getRangoFin() == 0) || (((Rango)vigenciaRango.getRangos().get(x)).getRangoFin() < ((Rango)vigenciaRango.getRangos().get(x)).getRangoInicio()))	
						throw new BusinessException("CCAF_BONIF_COBERTURAINVALIDA",
												   "El fin del rango: " + (x+1) + " es incorrecto");											
				}
			} 
	
			//Elimino los rangos existentes
			bonificacionesDao.deleteRangosCobertura(cobertura.getCodigo(),vigenciaRango.getCodigo());
			
			logger.debug("Código Actual: "+vigenciaRango.getCodigo());
			
			if(vigenciaRango.getCodigo()==0){							   
				int maximoActual = bonificacionesDao.getMaximoRangoByCobertura(cobertura.getCodigo());
				vigenciaRango.setCodigo(maximoActual+1);
				vigenciaRango.setCodigoCobertura(cobertura.getCodigo());
				bonificacionesDao.insertVigenciaRango(vigenciaRango);
			}else
				bonificacionesDao.updateVigenciaRango(vigenciaRango);
			
			//Comienzo a insertar los rangos, uno a uno
			logger.debug("Rangos: " + vigenciaRango.getRangos().size());
			for (int x = 0; x < vigenciaRango.getRangos().size();x++) {
				((Rango)vigenciaRango.getRangos().get(x)).setRangoID(x+1);
					bonificacionesDao.insertRangoCobertura((Rango)vigenciaRango.getRangos().get(x),cobertura.getCodigo(),vigenciaRango.getCodigo());
			}
		
			//Registro la cuenta de gastos
			Cobertura cob = bonificacionesDao.getCobertura(cobertura.getCodigo());
			cob.setCuentaGasto(cobertura.getCuentaGasto());
			bonificacionesDao.updateCobertura(cob);
		
			/*
			//Actualiza Tope de Cobertura
			Cobertura cob = bonificacionesDao.getCobertura(cobertura.getCodigo());
			cob.setTope(((Rango)cobertura.getRango().get(cobertura.getRango().size() - 1)).getRangoFin() * (((Rango)cobertura.getRango().get(cobertura.getRango().size() - 1)).getRangoPorcentajeCobertura() / 100)); 
			bonificacionesDao.updateCobertura(cob);
			*/
		}
	}

	/** 
	 * Obtiene todos los Rangos de una Cobertura (Bonificacion) existentes
	 * Obtiene el rango vigente si existe, los rangos historicos y el rango futuro si existe
	 * @param Cobertura
	 * @return Cobertura con los rangos
	 * @throws Exception
	 */
	public Cobertura getAllRangosCobertura(Cobertura cobertura) throws Exception, BusinessException {

		Date hoy = new Date();
		ArrayList rangos = bonificacionesDao.getAllRangosCobertura(cobertura.getCodigo());
		logger.debug("Rangos: " + rangos.size());
		
		VigenciaRango rangoVigente = null;
		VigenciaRango rangoFuturo = null;
//		VigenciaRango rangoHistorico=null;
		ArrayList rangosHistoricos=new ArrayList();
		
		VigenciaRango vigenciaRangoTemp=null;
		long codigoAnterior=0;
		for(int x=0;x<rangos.size();x++) {
			RangoCoberturaSinFormatoVO rangoSinFormato = (RangoCoberturaSinFormatoVO)rangos.get(x);
			if(codigoAnterior!=rangoSinFormato.getCodigo()){
				vigenciaRangoTemp = new VigenciaRango(rangoSinFormato);
				vigenciaRangoTemp.getRangos().add(new Rango(rangoSinFormato));
				if(vigenciaRangoTemp.getFinVigencia()!=null){
					if(vigenciaRangoTemp.getFinVigencia().before(hoy)) {
						rangosHistoricos.add(vigenciaRangoTemp);
					}else {
						if(vigenciaRangoTemp.getInicioVigencia().before(hoy)){
							rangoVigente=vigenciaRangoTemp;
						}else{
							rangoFuturo=vigenciaRangoTemp;
						}
					}
				}else{
					if(vigenciaRangoTemp.getInicioVigencia().before(hoy)){
						rangoVigente=vigenciaRangoTemp;
					}else{
						rangoFuturo=vigenciaRangoTemp;
					}
				}
			}else{
				vigenciaRangoTemp.getRangos().add(new Rango(rangoSinFormato));
			}
			codigoAnterior=rangoSinFormato.getCodigo();
		}

		cobertura.setRangoVigente(rangoVigente);
		cobertura.setRangosHistoricos(rangosHistoricos);
		cobertura.setRangoFuturo(rangoFuturo);
		return cobertura;
	}
	
	/** 
	 * Obtiene el Rango que se encuentra vigente en la fecha pasada en los parámetros
	 * @param Cobertura
	 * @param Date fecha
	 * @return Cobertura con VigenciaRango rangoVigente si tiene y null si no tiene
	 * @throws Exception
	 */
	public Cobertura getRangoCoberturaVigenteByFecha(Cobertura cobertura, Date fecha) throws Exception, BusinessException {
		
		ArrayList rangos = bonificacionesDao.getAllRangosCobertura(cobertura.getCodigo());
		logger.debug("Rangos: " + rangos.size());
		
		VigenciaRango rangoVigente = null;		
		VigenciaRango vigenciaRangoTemp=null;
		boolean encontroRango=false;
			
		long codigoAnterior=0;
		for(int x=0;x<rangos.size();x++) {
			RangoCoberturaSinFormatoVO rangoSinFormato = (RangoCoberturaSinFormatoVO)rangos.get(x);
			
			if(encontroRango){
				if(codigoAnterior==rangoSinFormato.getCodigo())
					vigenciaRangoTemp.getRangos().add(new Rango(rangoSinFormato));
				else
					break;
			}else if(codigoAnterior!=rangoSinFormato.getCodigo()){
				vigenciaRangoTemp = new VigenciaRango(rangoSinFormato);
				vigenciaRangoTemp.getRangos().add(new Rango(rangoSinFormato));
				if(vigenciaRangoTemp.getInicioVigencia().compareTo(fecha)<=0){
					if(vigenciaRangoTemp.getFinVigencia()==null || (vigenciaRangoTemp.getFinVigencia().compareTo(fecha)>=0)) {
						rangoVigente=vigenciaRangoTemp;
						encontroRango=true;
					}
				}
			}	
			codigoAnterior=rangoSinFormato.getCodigo();
		}
		logger.debug("Resultado Rango Vigente: "+rangoVigente);
		cobertura.setRangoVigente(rangoVigente);
		return cobertura;
	}
	
	/** 
	 * Retorna true si se traslapa con uno que ya existe.
	 * Retorna false si no se traslapa con uno que ya existe.
	 * @param VigenciaRango 
	 * @return boolean
	 * @throws Exception
	 */
	private boolean isTraslapeDeFechas(VigenciaRango vigenciaRango) throws Exception, BusinessException {
		
		logger.debug("Codigo Cobertura: "+vigenciaRango.getCodigoCobertura());
		ArrayList rangos = bonificacionesDao.getAllRangosCobertura(vigenciaRango.getCodigoCobertura());
		logger.debug("Rangos: " + rangos.size());
		
		boolean isTraslape=false;
		
		long codigoAnterior=0;
		for(int x=0;x<rangos.size();x++) {
			RangoCoberturaSinFormatoVO rangoSinFormato = (RangoCoberturaSinFormatoVO)rangos.get(x);
			
			//verifica que no se valide contra si mismo
			if(!(vigenciaRango.getCodigo()!=0 && vigenciaRango.getCodigo()==rangoSinFormato.getCodigo())) {
				
				if(codigoAnterior!=rangoSinFormato.getCodigo()){
					//Verifica traslape de fecha de inicio
					if(rangoSinFormato.getInicioVigencia().compareTo(vigenciaRango.getInicioVigencia())<=0){
						if(rangoSinFormato.getFinVigencia()==null || rangoSinFormato.getFinVigencia().after(vigenciaRango.getInicioVigencia())) {
							logger.debug("Fecha de Inicio se traslapa con Rango código: "+rangoSinFormato.getCodigo());
							isTraslape=true;
							break;
						}
					}
					//Verifica traslape de fecha de fin
					else if(rangoSinFormato.getInicioVigencia().compareTo(vigenciaRango.getFinVigencia())<=0){
						logger.debug("Fecha de Fin se traslapa con Rango código: "+rangoSinFormato.getCodigo());
						isTraslape=true;
						break;
					}
				}
			}
			codigoAnterior=rangoSinFormato.getCodigo();
		}

		return isTraslape;
	}
	
	/** 
	 * Busca si la cobertura pasada como parámetro
	 * tiene la definición de rangos en otra cobertura,
	 * en caso de ser así, significa que ambas coberturas
	 * utilizan los mismos aportes de bienestar. Si n o tiene devuelve cero
	 * @param long codigoCobertura 
	 * @return AgrupacionCobertura
	 * 	!= null si existe la relación
	 *  = null si no existe la relación
	 * @throws Exception
	 */
	public AgrupacionCobertura getRelacionAgrupacionCobertura(long codigoCobertura) throws Exception, BusinessException {
		return bonificacionesDao.getRelacionAgrupacionCobertura(codigoCobertura);
	}
	
	/** 
	 * Busca todas la relaciones que posee una cobertura maestra
	 * @param long codigoCoberturaMaestra 
	 * @return ArrayList de AgrupacionCobertura
	 * @throws Exception
	 */
	public ArrayList getRelacionAgrupacionCoberturaByCoberturaMaestra(long codigoCobertura) throws Exception, BusinessException {
		return bonificacionesDao.getRelacionAgrupacionCoberturaByCoberturaMaestra(codigoCobertura);	
	}
	
	/** 
	 * Busca todas la relaciones que posee una cobertura maestra y devuelve
	 * un ArrayList de Cobertura
	 * @param long codigoCoberturaMaestra 
	 * @return ArrayList de Cobertura
	 * @throws Exception
	 */
	public ArrayList getCoberturasByCoberturaMaestra(long codigoCobertura) throws Exception, BusinessException {
		ArrayList retorno = new ArrayList();
		ArrayList resultado = bonificacionesDao.getRelacionAgrupacionCoberturaByCoberturaMaestra(codigoCobertura);
		for(int x=0;x<resultado.size();x++){
			AgrupacionCobertura agrupacionCobertura = (AgrupacionCobertura)resultado.get(x);
			retorno.add(bonificacionesDao.getCobertura(agrupacionCobertura.getCodigoCobertura()));
		}
		
		return retorno;	
	}		
	
}
