package cl.araucana.bienestar.bonificaciones.serv.ServicesCasosSLBean;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

import cl.araucana.bienestar.bonificaciones.common.Constants;
import cl.araucana.bienestar.bonificaciones.dao.BonificacionesDAO;
import cl.araucana.bienestar.bonificaciones.dao.DAOFactory;
import cl.araucana.bienestar.bonificaciones.model.AporteCobertura;
import cl.araucana.bienestar.bonificaciones.model.Carga;
import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.model.Cobertura;
import cl.araucana.bienestar.bonificaciones.model.CoberturaAdicional;
import cl.araucana.bienestar.bonificaciones.model.Concepto;
import cl.araucana.bienestar.bonificaciones.model.Convenio;
import cl.araucana.bienestar.bonificaciones.model.Cuota;
import cl.araucana.bienestar.bonificaciones.model.DataLine;
import cl.araucana.bienestar.bonificaciones.model.DetalleCaso;
import cl.araucana.bienestar.bonificaciones.model.Evento;
import cl.araucana.bienestar.bonificaciones.model.Producto;
import cl.araucana.bienestar.bonificaciones.model.Profesional;
import cl.araucana.bienestar.bonificaciones.model.Rango;
import cl.araucana.bienestar.bonificaciones.model.Socio;
import cl.araucana.bienestar.bonificaciones.model.Vale;
import cl.araucana.bienestar.bonificaciones.model.VigenciaRango;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCoberturasDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesConveniosDelegate;
import cl.araucana.bienestar.bonificaciones.serv.ServicesSociosDelegate;
import cl.araucana.bienestar.bonificaciones.vo.AgrupacionCobertura;
import cl.araucana.bienestar.bonificaciones.vo.CalculoBonificacionVO;
import cl.araucana.bienestar.bonificaciones.vo.CasoAbiertoVO;
import cl.araucana.bienestar.bonificaciones.vo.CasoVO;
import cl.araucana.bienestar.bonificaciones.vo.CuotaPendienteVO;
import cl.araucana.bienestar.bonificaciones.vo.DatosInconsistenciaVO;
import cl.araucana.bienestar.bonificaciones.vo.DatosMovimientoTesoreriaVO;
import cl.araucana.bienestar.bonificaciones.vo.DatosProfesionalesVO;
import cl.araucana.bienestar.bonificaciones.vo.DescuentosVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleMovimientoPreCasoVO;
import cl.araucana.bienestar.bonificaciones.vo.InfoMovimientoPreCasoVO;
import cl.araucana.bienestar.bonificaciones.vo.InputUpLoadFileVO;
import cl.araucana.bienestar.bonificaciones.vo.PagoConvenioVO;
import cl.araucana.bienestar.bonificaciones.vo.ParamAporteEspecialVO;
import cl.araucana.bienestar.bonificaciones.vo.ParamOperacionesVO;
import cl.araucana.bienestar.bonificaciones.vo.ParamResumenMovimientosVO;
import cl.araucana.bienestar.bonificaciones.vo.ParametrosBonificacionCoberturaVO;
import cl.araucana.bienestar.bonificaciones.vo.ReembolsoVO;
import cl.araucana.bienestar.bonificaciones.vo.ResultLineVO;
import cl.araucana.bienestar.bonificaciones.vo.ResultUpLoadFileVO;
import cl.araucana.bienestar.bonificaciones.vo.ResumenMovimientosBeneficiarioVO;
import cl.araucana.bienestar.bonificaciones.vo.ResumenMovimientosConvenioVO;
import cl.araucana.bienestar.bonificaciones.vo.UsuarioVO;
import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.araucana.tesoreria.model.Comprobante;
import cl.araucana.tesoreria.model.Detalle;
import cl.araucana.tesoreria.serv.ServicesTesoreriaDelegate;

import com.schema.util.FileSettings;
import com.schema.util.GeneralException;


/**
 * @author aituarte
 * Bean implementation class for Enterprise Bean: ServicesSocios
 * Servicios de Consulta a Información de Socios de Bienestar de La Araucana
 */
public class ServicesCasosBean implements javax.ejb.SessionBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5731305202034785152L;

	private BonificacionesDAO bonificacionesDao;
	
	Logger logger = Logger.getLogger(ServicesCasosBean.class);
	
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
	
	
	public int mesesSocio(Date fecha) {
		GregorianCalendar fecHoy = new GregorianCalendar();
		GregorianCalendar fecSocio = new GregorianCalendar();
		fecSocio.setTime(new Date(fecha.getTime()));
		
		int anioHoy = fecHoy.get(GregorianCalendar.YEAR);
		int anioSocio = fecSocio.get(GregorianCalendar.YEAR);
		int mesHoy = fecHoy.get(GregorianCalendar.MONTH);
		int mesSocio = fecSocio.get(GregorianCalendar.MONTH);
		int diaHoy = fecHoy.get(GregorianCalendar.DAY_OF_MONTH);
		int diaSocio = fecSocio.get(GregorianCalendar.DAY_OF_MONTH);
		
		int meses = (anioHoy - anioSocio) * 12;
		meses = meses + mesHoy - mesSocio;
		
		if (diaHoy < diaSocio)
			meses = meses - 1;

		return meses;
				
	}

	/** 
	 * Obtiene una lista de Eventos
	 * @param codigo de caso (caso ID)
	 * @param evento, Objeto Evento con filtros posibles
	 * @return ArrayList de Evento Dentro de un Caso
	 * @throws Exception
	 */
	public Caso getEventos(Caso caso) throws Exception, BusinessException {
		caso.setEvento(bonificacionesDao.getEventos(caso.getCasoID(),null));
		return caso;
	}
	
	/**
	 * Registra un Evento asociado a un Caso
	 * @param cado Id y Evento con los datos
	 */
	public void registraEvento(long casoId, Evento evento) throws Exception, BusinessException {		
		evento.setFechaIngreso(new Date());
		bonificacionesDao.insertEvento(casoId, evento);
	}
	
	/** 
	 * Obtiene una lista de Detalle de Caso
	 * @param Caso
	 * @return Caso con ArrayList de Detalle Caso
	 * @throws Exception
	 */
	public Caso getDetallesCaso(Caso caso) throws Exception, BusinessException  {
		caso.removeAllDetalleCaso();
		caso.setDetalleCaso(bonificacionesDao.getDetallesCaso(caso.getCasoID()));
		return caso;
	}
	
	/** 
	 * Obtiene una lista de Detalle de Caso con los montos de los detalles de egresos previos ya restados
	 * @param long casoId
	 * @return ArrayList de DetalleCaso
	 * @throws Exception, BusinessException
	 */
	public ArrayList getDetallesCasoPreCasos(long casoId) throws Exception, BusinessException  {

		ArrayList resultado = bonificacionesDao.getDetallesCaso(casoId);
		
//		for(int x=0; x<resultado.size(); x++){
//			DetalleCaso detalle = (DetalleCaso)resultado.get(x);
//			long coberturaCodigo = detalle.getProducto().getCobertura().getCodigo();
//			int montoEgresosPrevios = bonificacionesDao.getMontoCoberturaMovimientosPrevios(casoId, coberturaCodigo, detalle.getIdDetalle());
			//Se restan los egresos previos
			//detalle.setMonto(detalle.getMonto() - montoEgresosPrevios);
//		}

		return resultado;

	}	
	
	/** 
	 * Obtiene el monto total generado previamente para un detalle
	 * @param long casoId
	 * @return long
	 * @throws Exception, BusinessException
	 */
	public double getMontoPrevioGeneradoDetalle(long casoId,long idDetalle) throws Exception, BusinessException  {

		ArrayList resultado = bonificacionesDao.getDetallesCaso(casoId);
		double retorno = 0;
		
		for(int x=0; x<resultado.size(); x++){
			
			DetalleCaso detalle = (DetalleCaso)resultado.get(x);
			if(detalle.getIdDetalle() == idDetalle){

				long coberturaCodigo = detalle.getProducto().getCobertura().getCodigo();
				int montoEgresosPrevios = bonificacionesDao.getMontoCoberturaMovimientosPrevios(casoId, coberturaCodigo, detalle.getIdDetalle());
				
				retorno = montoEgresosPrevios;
			}
		}

		return retorno;

	}	
	
	/** 
	 * Cierra un caso de tipo descuento por pago con prestamo Y/O abono del socio
	 * y registra un evento con el cierre
	 * @param Caso
	 * @param Evento
	 * @throws Exception
	 */
	public void cerrarCaso(Caso caso, Evento evento) throws Exception, BusinessException  {
	
		Caso casoBD = (Caso)bonificacionesDao.getCasoVO(caso.getCasoID());
	
		//validaciones
		if (!casoBD.getTipoCaso().equals(Caso.TIPO_DESCUENTO))
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					   "El Caso debe ser de tipo: " + Caso.TIPO_DESCUENTO);
		if (casoBD.getIndicadorBonificacion().equals(Caso.ESTADOINDICADOR_NO))
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					   "El Caso debe estar Bonificado previamente");
		if (!casoBD.getIndicadorDescontado().equals(Caso.ESTADOINDICADOR_NO))
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					   "El Caso no debe tener descuentos realizados");
		if(caso.getAbono()+ caso.getPrestamo() != caso.getAporteSocio())
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
				   "El monto del abono más el monto del préstamo debe corresponder al aporte del Socio");
		
		casoBD.setEstado(Caso.STD_CERRADO);
		casoBD.setFechaEstado(new Date());
		casoBD.setIndicadorPagoAnticipado(Caso.ESTADOINDICADOR_SI);
		casoBD.setPrestamo(caso.getPrestamo());
		casoBD.setAbono(caso.getAbono());
		casoBD.setNumeroPrestamo(caso.getNumeroPrestamo());
		//Actualizo el caso
		bonificacionesDao.updateIndicadorPagoAnticipado(casoBD);
		//Registro evento ingresado por el usuario
		evento.setFechaIngreso(new Date());
		evento.setTipo(Evento.TIPO_MANUAL);
		registraEvento(caso.getCasoID(),evento);
		
	}
	
	/** 
	 * Registra la información de un aporte especial para un caso
	 * y registra un evento con el comentario dl usuario
	 * @param Caso
	 * @param Evento
	 * @throws Exception
	 */
	public void registrarAporteEspecialCaso(ParamAporteEspecialVO param, Evento evento) throws Exception, BusinessException  {
	
		Caso casoBD = (Caso)bonificacionesDao.getCasoVO(param.getCasoID());
	
		//validaciones
					   
		if (casoBD.getTipoCaso().equals(Caso.TIPO_DESCUENTO)
			&& !casoBD.getIndicadorDescontado().equals(Caso.ESTADOINDICADOR_NO))
			throw new BusinessException(
				"CCAF_BONIF_CASOINVALIDO",
				"El Caso no debe estar descontado");

		if (casoBD.getTipoCaso().equals(Caso.TIPO_REEMBOLSO)
			&& !casoBD.getIndicadorReembolso().equals(Caso.ESTADOINDICADOR_NO))
			throw new BusinessException(
				"CCAF_BONIF_CASOINVALIDO",
				"El Caso no debe estar reembolsado");

		boolean bonificadoPreviamente = casoBD.getAporteSocio()!=0 ? true: false;
		
		logger.debug("param-MontoAporte: "+param.getMontoAporte());
		logger.debug("param-CoPagoSocio: "+param.getCoPagoSocio());
		logger.debug("casoBD.AporteSocio: "+casoBD.getAporteSocio());
		logger.debug("casoBD.AporteSocio (int): "+(int)casoBD.getAporteSocio());
		logger.debug("casoBD.Total: "+casoBD.getTotal());
		logger.debug("casoBD.Total(int): "+(int)casoBD.getTotal());
		
		int aporteMasCoPagoSocio = param.getMontoAporte() + param.getCoPagoSocio();
							   
		if (bonificadoPreviamente) {
			logger.debug("1");
			if(aporteMasCoPagoSocio != (int)casoBD.getAporteSocio())
					throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
						   "El monto del aporte especial más el monto del co-pago del socio debe corresponder a la deuda del Socio");			
		} else {
			logger.debug("2");
			if (aporteMasCoPagoSocio != (int)casoBD.getTotal())
						throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
							   "El monto del aporte especial más el monto del co-pago del socio debe corresponder a la deuda del Socio");			
		}

 				
		casoBD.setAporteBienestar(casoBD.getAporteBienestar()+param.getMontoAporte());
		casoBD.setAporteSocio(param.getCoPagoSocio());
		casoBD.setIndicadorBonificacion(Caso.ESTADOINDICADOR_SI);
		
		AporteCobertura aporteCobertura = new AporteCobertura();
		aporteCobertura.setCasoID(param.getCasoID());
		aporteCobertura.setCodigoCobertura(param.getCodigoCobertura());
		
		//Distribuye el aporte especial entre los distintos detalles
		ArrayList detalles = bonificacionesDao.getDetallesCaso(param.getCasoID());
		int aporteAdicionalRestante = param.getMontoAporte();
		for(int x=0; x<detalles.size();x++){
			DetalleCaso detalle = (DetalleCaso)detalles.get(x);
			if(bonificadoPreviamente){
				if(detalle.getAporteSocio()>aporteAdicionalRestante){
					aporteCobertura.setAporteBienestar(aporteAdicionalRestante);
					detalle.setAporteBienestar(detalle.getAporteBienestar() + aporteAdicionalRestante);
					detalle.setAporteSocio(detalle.getAporteSocio() - aporteAdicionalRestante);
					aporteAdicionalRestante=0;
				}else {
					aporteCobertura.setAporteBienestar((int)detalle.getAporteSocio());
					aporteAdicionalRestante=aporteAdicionalRestante - (int)detalle.getAporteSocio();
				
					detalle.setAporteBienestar(detalle.getAporteBienestar() + (int)detalle.getAporteSocio());
					detalle.setAporteSocio(0);
				}
			}else{
				if(detalle.getTotal()>aporteAdicionalRestante){
					aporteCobertura.setAporteBienestar(aporteAdicionalRestante);
					detalle.setAporteBienestar(detalle.getAporteBienestar() + aporteAdicionalRestante);
					detalle.setAporteSocio(detalle.getTotal() - aporteAdicionalRestante);
					aporteAdicionalRestante=0;
				}else {
					aporteCobertura.setAporteBienestar((int)detalle.getTotal());
					aporteAdicionalRestante=aporteAdicionalRestante - (int)detalle.getTotal();
				
					detalle.setAporteBienestar((int)detalle.getTotal());
					detalle.setAporteSocio(0);
				}
			}


			bonificacionesDao.updateDetalle(param.getCasoID(),detalle);
			aporteCobertura.setIdDetalle(detalle.getIdDetalle());
			bonificacionesDao.registraAporteCobertura(aporteCobertura);
		}
		//Actualizo el caso
		bonificacionesDao.updateCaso(casoBD);
		
		//Registro evento ingresado por el usuario
		evento.setFechaIngreso(new Date());
		evento.setTipo(Evento.TIPO_MANUAL);
		registraEvento(param.getCasoID(),evento);
		
	}
	
	/** 
	 * Registra la información de un aporte especial para un caso
	 * y registra un evento con el comentario del usuario sin utilizar tope personal de cobertura REQ-5088
	 * @param Caso
	 * @param Evento
	 * @throws Exception
	 */
	public void registrarAporteEspecialSinTopeCobertura(ParamAporteEspecialVO param, Evento evento) throws Exception, BusinessException  {
	
		Caso casoBD = (Caso)bonificacionesDao.getCasoVO(param.getCasoID());
		
		//validaciones
					   
		if (casoBD.getTipoCaso().equals(Caso.TIPO_DESCUENTO)
			&& !casoBD.getIndicadorDescontado().equals(Caso.ESTADOINDICADOR_NO))
			throw new BusinessException(
				"CCAF_BONIF_CASOINVALIDO",
				"El Caso no debe estar descontado");

		if (casoBD.getTipoCaso().equals(Caso.TIPO_REEMBOLSO)
			&& !casoBD.getIndicadorReembolso().equals(Caso.ESTADOINDICADOR_NO))
			throw new BusinessException(
				"CCAF_BONIF_CASOINVALIDO",
				"El Caso no debe estar reembolsado");

		boolean bonificadoPreviamente = casoBD.getAporteSocio()!=0 ? true: false;
		
		logger.debug("param-MontoAporte: "+param.getMontoAporte());
		logger.debug("param-CoPagoSocio: "+param.getCoPagoSocio());
		logger.debug("casoBD.AporteSocio: "+casoBD.getAporteSocio());
		logger.debug("casoBD.AporteSocio (int): "+(int)casoBD.getAporteSocio());
		logger.debug("casoBD.Total: "+casoBD.getTotal());
		logger.debug("casoBD.Total(int): "+(int)casoBD.getTotal());
		
		int aporteMasCoPagoSocio = param.getMontoAporte() + param.getCoPagoSocio();
							   
		if (bonificadoPreviamente) {
			logger.debug("1");
			if(aporteMasCoPagoSocio != (int)casoBD.getAporteSocio())
					throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
						   "El monto del aporte especial más el monto del co-pago del socio debe corresponder a la deuda del Socio");			
		} else {
			logger.debug("2");
			if (aporteMasCoPagoSocio != (int)casoBD.getTotal())
						throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
							   "El monto del aporte especial más el monto del co-pago del socio debe corresponder a la deuda del Socio");			
		}

 				
		casoBD.setAporteBienestar(casoBD.getAporteBienestar()+param.getMontoAporte());
		casoBD.setAporteSocio(param.getCoPagoSocio());
		casoBD.setIndicadorBonificacion(Caso.ESTADOINDICADOR_SI);
		
		AporteCobertura aporteCobertura = new AporteCobertura();
		aporteCobertura.setCasoID(param.getCasoID());
		aporteCobertura.setCodigoCobertura(param.getCodigoCobertura());
		
		//Distribuye el aporte especial entre los distintos detalles
		ArrayList detalles = bonificacionesDao.getDetallesCaso(param.getCasoID());
		int aporteAdicionalRestante = param.getMontoAporte();
		for(int x=0; x<detalles.size();x++){
			DetalleCaso detalle = (DetalleCaso)detalles.get(x);
			if(bonificadoPreviamente){
				if(detalle.getAporteSocio()>aporteAdicionalRestante){
					aporteCobertura.setAporteBienestar(aporteAdicionalRestante);
					detalle.setAporteBienestar(detalle.getAporteBienestar() + aporteAdicionalRestante);
					detalle.setAporteSocio(detalle.getAporteSocio() - aporteAdicionalRestante);
					aporteAdicionalRestante=0;
				}else {
					aporteCobertura.setAporteBienestar((int)detalle.getAporteSocio());
					aporteAdicionalRestante=aporteAdicionalRestante - (int)detalle.getAporteSocio();
				
					detalle.setAporteBienestar(detalle.getAporteBienestar() + (int)detalle.getAporteSocio());
					detalle.setAporteSocio(0);
				}
			}else{
				if(detalle.getTotal()>aporteAdicionalRestante){
					aporteCobertura.setAporteBienestar(aporteAdicionalRestante);
					detalle.setAporteBienestar(detalle.getAporteBienestar() + aporteAdicionalRestante);
					detalle.setAporteSocio(detalle.getTotal() - aporteAdicionalRestante);
					aporteAdicionalRestante=0;
				}else {
					aporteCobertura.setAporteBienestar((int)detalle.getTotal());
					aporteAdicionalRestante=aporteAdicionalRestante - (int)detalle.getTotal();
				
					detalle.setAporteBienestar((int)detalle.getTotal());
					detalle.setAporteSocio(0);
				}
			}


			bonificacionesDao.updateDetalle(param.getCasoID(),detalle);
			aporteCobertura.setIdDetalle(detalle.getIdDetalle());
			bonificacionesDao.registraAporteBienestarBonificacionEspecial(aporteCobertura, evento.getUsuario());
			
		}
		//Actualizo el caso
		bonificacionesDao.updateCaso(casoBD);
		
		//Registro evento ingresado por el usuario
		evento.setFechaIngreso(new Date());
		evento.setTipo(Evento.TIPO_MANUAL);
		registraEvento(param.getCasoID(),evento);
		
	}	
	
	/*
	 * Actualiza los totales de un caso
	 * Es utilizado por los metodos:
	 * 		registraDetalle
	 * 		actualizaDetalle
	 * 		eliminaDetalle
	 */
	public Caso actualizaTotalesCaso(Caso caso) throws Exception, BusinessException {
		double aporteBienestar = 0;
		double aporteIsapre  = 0;
		double aporteSocio = 0;
		double monto  = 0;
		double montoDescuento = 0;
		ArrayList vales = new ArrayList();
		Vale vale = new Vale();
		
		ArrayList detalles = bonificacionesDao.getDetallesCaso(caso.getCasoID());
		if (caso.getIndicadorBonificacion().equals(Caso.ESTADOINDICADOR_SI)) {
			
			bonificacionesDao.deleteAporteCobertura(caso.getCasoID());
			
			if (detalles.size() > 0) {
				//si ya estaba bonificado, entonces debo rebonificar
				Caso newCaso = calcularBonificacionCaso(caso.getCasoID(), false);
				
				//Actualizo detalle Caso
				for (int x = 0; x < newCaso.getDetalleCaso().size(); x++) {
					DetalleCaso detalle = (DetalleCaso)newCaso.getDetalleCaso().get(x);
					bonificacionesDao.updateDetalle(caso.getCasoID(),detalle);
					logger.debug("Actualiza Detalle: " + detalle.getIdDetalle());
					for (int z=0;z<detalle.getAporteCobertura().size();z++){
						AporteCobertura aporteCobertura = (AporteCobertura)detalle.getAporteCobertura().get(z);
						aporteCobertura.setAporteBienestar(aporteCobertura.getAporteBienestar()*detalle.getCantidadOcurencias());
						if (aporteCobertura.getAporteBienestar()>0) {
							bonificacionesDao.registraAporteCobertura(aporteCobertura);
							logger.debug("Crea aporte Bienestar: " + detalle.getIdDetalle());
						}
					}
				}				
				
				//Actualizo Caso
				bonificacionesDao.updateCaso(newCaso);
				//Actualizo Vale
				vale.setCaso_id(caso.getCasoID());
				vales = bonificacionesDao.getVales(vale,null,0);
				if (vales.size() >0) {
					vale = (Vale)vales.get(0);
					vale.setMonto(newCaso.getMonto()-newCaso.getMontoDescuento());
					bonificacionesDao.updateVale(vale,caso.getRutSocio());
				}
				
				//Genero Cuotas
				if (newCaso.getCuota().size() > 1) {
					logger.debug("Elimina Cuotas previas");
					bonificacionesDao.deleteCuotasCaso(newCaso.getCasoID());
					
					for (int y = 0; y < newCaso.getCuota().size(); y++) {
						logger.debug("Creando Cuota: " + y + " para el Caso Id: " + newCaso.getCasoID());
						bonificacionesDao.insertCuotaCaso(newCaso.getCasoID(),(Cuota)newCaso.getCuota().get(y));
					}
					logger.debug("Finaliza creacion de cuotas para el Caso Id: " + newCaso.getCasoID());
				}
		
				return newCaso;
			}else {
				//El caso queda no bonificado
				caso.setIndicadorBonificacion(Caso.ESTADOINDICADOR_NO);
				caso.setAporteBienestar(0);
				caso.setAporteSocio(0);
				caso.setAporteIsapre(0);
				caso.setMonto(0);
				caso.setMontoDescuento(0);
				caso.setEstado(Caso.STD_BORRADOR);
				bonificacionesDao.updateCaso(caso);
				//Elimino cuotas
				bonificacionesDao.deleteCuotasCaso(caso.getCasoID());
				//Actualizo Vale
				vale.setCaso_id(caso.getCasoID());
				vales = bonificacionesDao.getVales(vale,null,0);
				if (vales.size() >0) {
					vale = (Vale)vales.get(0);
					vale.setMonto(caso.getMonto()-caso.getMontoDescuento());
					bonificacionesDao.updateVale(vale,caso.getRutSocio());
				}
				
				return caso;
			}	
		}
		//No bonificado aún
		else {	
			//suma los detalles registrados (del caso)
			for (int x = 0; x < detalles.size(); x++) {
				DetalleCaso detalle = (DetalleCaso)detalles.get(x);
				aporteBienestar = aporteBienestar + detalle.getAporteBienestar();
				aporteIsapre = aporteIsapre + detalle.getAporteIsapre();
				aporteSocio = aporteSocio + detalle.getAporteSocio();
				monto = monto + detalle.getMonto();
				montoDescuento = montoDescuento + detalle.getMontoDescuento();
			}
	
			//actualza información de los totales en el caso
			caso.setAporteBienestar(aporteBienestar);
			caso.setAporteIsapre(aporteIsapre);
			caso.setAporteSocio(aporteSocio);
			caso.setMonto(monto);
			caso.setMontoDescuento(montoDescuento);
		
			bonificacionesDao.updateCaso(caso);
	
			caso.setDetalleCaso(detalles);
			//Actualizo Vale
			vale.setCaso_id(caso.getCasoID());
			vales = bonificacionesDao.getVales(vale,null,0);
			if (vales.size() >0) {
				vale = (Vale)vales.get(0);
				vale.setMonto(caso.getMonto()-caso.getMontoDescuento());
				bonificacionesDao.updateVale(vale,caso.getRutSocio());
			}
		
			return caso;	
		}
	}
	
	/**
	 * Crea un Detalle asociado a un Caso
	 * @param caso
	 * @param detalleCaso
	 * @return Caso con los totales actualizados
	 * @throws Exception
	 */
	public Caso registraDetalle(Caso caso,  DetalleCaso detalleCaso) throws Exception, BusinessException {
		
		int idDetalle=0;
		
		//Realizo validaciones previas
		
		if (caso == null || detalleCaso == null)
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
						   "Los parametros son incorrectos");
						   
		logger.debug("Caso Id: " + caso.getCasoID());
		
		if (detalleCaso.getProducto().getCobertura() == null)
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
						   "Debe seleccionar un Producto previamente");
						   
		if (detalleCaso.getProducto().getCobertura().getTieneOcurrencias().equals(Cobertura.TIENE_OCURRENCIAS_SI) && detalleCaso.getCantidadOcurencias()<1)
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
						   "Debe indicar una cantidad mayor que cero para la cantidad de "+detalleCaso.getProducto().getCobertura().getEtiquetaOcurrencia());						   
		
		logger.debug("Detalle Caso: " + detalleCaso.getProducto().getCobertura().getCodigo());
		
		//rescato información del caso desde la BD
		CasoVO casoBd = bonificacionesDao.getCasoVO(caso.getCasoID());
		
		if (casoBd.getEstado().equals(Caso.STD_CERRADO) || casoBd.getEstado().equals(Caso.STD_ELIMINADO))
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
							   "El estado es incorrecto");
		
		if (!casoBd.getIndicadorDescontado().equals(Caso.ESTADOINDICADOR_NO) ||!casoBd.getIndicadorPago().equals(Caso.ESTADOINDICADOR_NO) || !casoBd.getIndicadorReembolso().equals(Caso.ESTADOINDICADOR_NO))
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
							   "El estado actual del Caso no permite actualizar la información");

		if (detalleCaso.getMonto() <= 0)
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
					   "El Monto del Detalle debe ser mayor que cero");

		if ((detalleCaso.getAporteIsapre() + detalleCaso.getMontoDescuento()) > detalleCaso.getMonto())
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
						   "El Descuento más el Aporte de la Isapre no pueden ser superiores al Valor de la Prestación");

		//Recorro los detalles existentes para determinar el siguiente Id
		ArrayList detallesExistentes = bonificacionesDao.getDetallesCaso(caso.getCasoID());
		
		for (int x=0; x < detallesExistentes.size(); x++) {
			DetalleCaso det = (DetalleCaso)detallesExistentes.get(x);
			if (det.getIdDetalle() > idDetalle)
				idDetalle = det.getIdDetalle();
		}
		
		//Asigno Id
		detalleCaso.setIdDetalle(idDetalle+1);		
		//registro el detalle
		bonificacionesDao.insertDetalle(caso.getCasoID(),casoBd.getCodigoConvenio(), detalleCaso);
		
		//Actualiza la infomación en el Caso
		return actualizaTotalesCaso((Caso)casoBd);
		
	}
	
	/**
	 * Modifica un Detalle asociado a un Caso
	 * @param caso con detalles de caso
	 * @param index con posicion de detalle de caso a actualizar 
	 * @param Detalle caso con los datos actualizados 
	 */
	public Caso actualizaDetalle(Caso caso, int index, DetalleCaso detalleCaso) throws Exception, BusinessException {
		
		//Realizo validaciones previas
		
		if (caso == null || detalleCaso == null)
			throw new BusinessException( "CCAF_BONIF_DETALLEINVALIDO",
						   "Los parametros son incorrectos");
						   		
		//rescato información del caso desde la BD
		CasoVO casoBd = bonificacionesDao.getCasoVO(caso.getCasoID());
		
		if (casoBd.getEstado().equals(Caso.STD_CERRADO) || casoBd.getEstado().equals(Caso.STD_ELIMINADO))
			throw new BusinessException( "CCAF_BONIF_DETALLEINVALIDO",
							   "El estado es incorrecto");
		
		if (!casoBd.getIndicadorDescontado().equals(Caso.ESTADOINDICADOR_NO) ||!casoBd.getIndicadorPago().equals(Caso.ESTADOINDICADOR_NO) || !casoBd.getIndicadorReembolso().equals(Caso.ESTADOINDICADOR_NO))
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
							   "El estado actual del Caso no permite actualizar la información");
							   
		if (detalleCaso.getProducto().getCobertura().getTieneOcurrencias().equals(Cobertura.TIENE_OCURRENCIAS_SI) && detalleCaso.getCantidadOcurencias()<1)
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
						   "Debe indicar una cantidad mayor que cero para la cantidad de "+detalleCaso.getProducto().getCobertura().getEtiquetaOcurrencia());						   							   
							   
		if ((detalleCaso.getAporteIsapre() + detalleCaso.getMontoDescuento()) > detalleCaso.getMonto())
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
						   "El Descuento más el Aporte de la Isapre no pueden ser superiores al Valor de la Prestación");
		
		//actualizo el detalle
		DetalleCaso detCaso = (DetalleCaso)caso.getDetalleCaso().get(index);
		detalleCaso.setIdDetalle(detCaso.getIdDetalle());
		
		//Si los codigos de producto (Cobertura) son iguales, actualizo
		if (detCaso.getProducto().getCobertura().getCodigo() == detalleCaso.getProducto().getCobertura().getCodigo()) {
			bonificacionesDao.updateDetalle(caso.getCasoID(), detalleCaso);			
		}
		//Si los codigos de producto (Cobertura) son distintos, elimino e inserto
		else {
			//Elimino el antiguo
			bonificacionesDao.deleteDetalle(caso.getCasoID(),detCaso);
			//Creo el nuevo
			bonificacionesDao.insertDetalle(caso.getCasoID(),casoBd.getCodigoConvenio(),detalleCaso);
		}
		
		//Actualiza la infomación en el Caso
		return actualizaTotalesCaso((Caso)casoBd);
	}
	
	/**
	 * Elimina un Detalle asociado a un Caso
	 * @param caso con detalle de caso
	 * @param index con posicion de Detalle caso a eliminar 
	 */
	public Caso eliminaDetalle(Caso caso, int index) throws Exception, BusinessException {
		
		//Realizo validaciones previas
		
		if (caso == null)
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
						   "Los parametros son incorrectos");
						   
		logger.debug("Caso Id: " + caso.getCasoID());
		logger.debug("Detalle Caso (Index): " + index);
		
		//rescato información del caso desde la BD
		CasoVO casoBd = bonificacionesDao.getCasoVO(caso.getCasoID());
		
		if (casoBd.getEstado().equals(Caso.STD_CERRADO) || casoBd.getEstado().equals(Caso.STD_ELIMINADO))
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
							   "El estado es incorrecto");
		
		if (!casoBd.getIndicadorDescontado().equals(Caso.ESTADOINDICADOR_NO) ||!casoBd.getIndicadorPago().equals(Caso.ESTADOINDICADOR_NO) || !casoBd.getIndicadorReembolso().equals(Caso.ESTADOINDICADOR_NO))
			throw new BusinessException("CCAF_BONIF_DETALLEINVALIDO",
							   "El estado actual del Caso no permite actualizar la información");
		
		
		//elimino el detalle
		logger.debug("Codigo Cobertura : " + ((DetalleCaso)caso.getDetalleCaso().get(index)).getProducto().getCobertura().getCodigo());
		
		bonificacionesDao.deleteAporteCoberturaByDetalle(caso.getCasoID(),((DetalleCaso)caso.getDetalleCaso().get(index)).getIdDetalle());
		bonificacionesDao.deleteDetalle(caso.getCasoID(), (DetalleCaso)caso.getDetalleCaso().get(index));
		
		//Actualiza la infomación en el Caso
		return actualizaTotalesCaso((Caso)casoBd);
	}
	
	
	/**
	 * Crea un Caso
	 * @param caso
	 * @return caso id
	 */
	public long registraCaso(Caso caso) throws Exception,BusinessException {
		
		SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy", Locale.getDefault());
		long idCaso = 0;
		
		//Maxma cantidad de dias para presentar un caso, desde su fecha de ocurrencia
		int  diasVigencia = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/dias-vigencia"));
		
		GregorianCalendar fechaOcu = new GregorianCalendar ();
		fechaOcu.setTime(caso.getFechaDeOcurrencia());
		int dia = fechaOcu.get(Calendar.DAY_OF_MONTH); 
		int mes = fechaOcu.get(Calendar.MONTH)+1;
		int anio = fechaOcu.get(Calendar.YEAR);
		caso.setFechaDeOcurrencia(formato.parse(dia + "/" + mes + "/" + anio));
		
		if (!caso.getEstado().equals(Caso.STD_BORRADOR) && !caso.getEstado().equals(Caso.STD_PRECASO))
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
							   "El estado del caso es incorrecto");
		if (caso.getCodigoConvenio() == 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
							   "El Caso debe estar asociado a un Convenio");
		if (caso.getRutSocio() == null ||  caso.getRutSocio().length() == 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
							   "El Caso debe estar asociado a un Socio");
			
		//se valida que el tipo de bono corresponda con el tipo de cuotas ingresadas
		//"no" => cuota externas.
		//"si" => cuota internas.
		if(caso.getTipoBono().equals(Caso.TIPOBONO_NO) && caso.getCuotasBienestar() > 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"El caso no puede tener indicador tipo bono 'NO' con cuotas internas");
		if(caso.getTipoBono().equals(Caso.TIPOBONO_CONVENIO) && caso.getCuotasConvenio() > 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"El caso no puede tener indicador tipo bono 'CONVENIO' con cuotas externas");
		if(caso.getTipoBono().equals(Caso.TIPOBONO_SOCIO) && caso.getCuotasConvenio() > 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"El caso no puede tener indicador tipo bono 'SOCIO' con cuotas externas");
		
		//Valido que fecha de ocurrencia no sea futura
		if (caso.getFechaDeOcurrencia().after(new Date()))
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
						   "La Fecha de ocurrencia del Caso no puede ser futura");

		//Valido que la fecha de ocurrencia no sea anterior a la fecha de inicio de 
		//beneficios de la carga						   
		if (caso.getRutCarga() != null && caso.getRutCarga().length() > 0) {
			Carga car = bonificacionesDao.getCarga(caso.getRutCarga(),caso.getRutSocio());
			if (car.getFecIngCarga().compareTo(caso.getFechaDeOcurrencia()) > 0)
				throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					   "La Fecha de ocurrencia del Caso no puede ser anterior a la fecha de Ingreso de la Carga");				   
		}
		
		//Valido que la fecha de ocurrencia no sea anterior a la fecha de inicio de 
		//beneficios del socio
		Socio soc = bonificacionesDao.getSocio(caso.getRutSocio());
		if (soc.getFecIng().compareTo(caso.getFechaDeOcurrencia()) > 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					   "La Fecha de ocurrencia del Caso no puede ser anterior a la fecha de Ingreso del Socio");
					   
		//Valido que la fecha de ocurrencia no sea anterior a los dias de vigencia definidos para
		//los casos de tipo reembolso
		if (caso.getTipoCaso().equals(Caso.TIPO_REEMBOLSO)) {
			GregorianCalendar hoy = new GregorianCalendar();
			hoy.add(Calendar.DAY_OF_MONTH,-diasVigencia);
			Date fechaTope = formato.parse(hoy.get(Calendar.DAY_OF_MONTH) + "/" +  (hoy.get(Calendar.MONTH)+1) + "/" + hoy.get(Calendar.YEAR));
			
			logger.debug("****Resultado: "+ caso.getFechaDeOcurrencia().compareTo(fechaTope));
			if (caso.getFechaDeOcurrencia().compareTo(fechaTope)==-1)
				throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
							   "La Fecha de ocurrencia del Caso no puede ser anterior al " + formato.format(fechaTope));
		}
	
							   
		//Valida datos del Convenio
		Convenio convBD = bonificacionesDao.getConvenio(caso.getCodigoConvenio());
		
		if (convBD.getEstado().equals(Convenio.STD_BORRADOR))
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
							   "No es posible asociar un convenio en estado Borrador");
		
		//Valido fecha de finalización del Convenio
		if (convBD.getEstado().equals(Convenio.STD_ELIMINADO)
			&& (convBD.getFecFin().compareTo(caso.getFechaDeOcurrencia()) < 0))
			throw new BusinessException(
				"CCAF_BONIF_CASOINVALIDO",
				"La Fecha de ocurrencia del Caso no puede ser posterior a la fecha de finalización del Convenio");
				
		GregorianCalendar fechaInicioConvenio = new GregorianCalendar ();
		fechaInicioConvenio.setTime(convBD.getFecInicio());
		int d = fechaInicioConvenio.get(Calendar.DAY_OF_MONTH); 
		int m = fechaInicioConvenio.get(Calendar.MONTH)+1;
		int a = fechaInicioConvenio.get(Calendar.YEAR);
		convBD.setFecInicio(formato.parse(d + "/" + m + "/" + a));
						
		logger.debug("Convenio: "+convBD.getFecInicio()+" Ocurrecia: "+caso.getFechaDeOcurrencia());
		
		//Valido fecha de Activación del Convenio	   
		if (convBD.getFecInicio().compareTo(caso.getFechaDeOcurrencia()) > 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					   "La Fecha de ocurrencia del Caso no puede ser anterior a la fecha de Activación del Convenio");
							   
		//Valido numero de cuotas externas permitidas pora el convenio
		if (convBD.getNumeroMaximoCuotas() < caso.getCuotasConvenio())
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
						   "El número de cuotas externas supera las permitidas por el convenio");
		
		if (caso.getCuotasBienestar() > 0 && caso.getCuotasConvenio() > 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
						   "No se pueden definir cuotas internas y externas a la vez");
						   
		if (caso.getTipoCaso().equals(Caso.TIPO_REEMBOLSO)) {
			if (caso.getCuotasBienestar() > 0 || caso.getCuotasConvenio() > 0)
				throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					  "En el tipo de caso reembolso no se pueden definir cuotas");
			if (caso.getNumeroDocumento() == null || caso.getNumeroDocumento().equals(""))
				throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					   "El Nº de documento es obligatorio en los casos tipo reembolso");
		}
	
		
		//EJB DE SOCIOS
		ServicesSociosDelegate sociosDelegate = new ServicesSociosDelegate();
					   
		//Actualizo datos del Socio y de sus cargas familiares
		//sociosDelegate.actualizarSocio(caso.getRutSocio());
			
		if(caso.getRutCarga() != null && !caso.getRutCarga().equals("")){
			Carga c = sociosDelegate.getCarga(caso.getRutCarga(),caso.getRutSocio());
			if(c != null && c.getEstadoCarga().equals(Carga.STD_INACTIVO)){
					throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
							   "La carga seleccionada se encuentra inactiva");
			}
		}
		//Setea valores del nuevo caso
		caso.setFechaIngreso(new Date());
		//caso.setEstado(Caso.STD_BORRADOR);
		caso.setFechaEstado(new Date());
		idCaso = bonificacionesDao.insertCaso(caso);
		
		if (idCaso > 0) {
			//actualiza vale, en caso de existir un vale asociado
			if (caso.getVale() != null && caso.getVale().getCodigoVale() > 0) {
				//recupera informacion del vale, para luego actualizarlo
				ArrayList retorno = bonificacionesDao.getVales(caso.getVale(),caso.getRutSocio(),caso.getCodigoConvenio());
				if (retorno.size() != 1)
					throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
								   "No se pudo obtener información del Vale: " + caso.getVale().getCodigoVale());
				else {
					//actualiza información del caso Id y de la fecha de recepción
					Vale val = (Vale)retorno.get(0);
					val.setCaso_id(caso.getCasoID());
					val.setFechaRecepcion(new Date());
					bonificacionesDao.updateVale(val,caso.getRutSocio());
				}	
			}
		}
		return idCaso;
	}
	
	
	/**
	 * Actualiza un Caso
	 * @param caso
	 */
	public void actualizarCaso(Caso caso) throws Exception, BusinessException {
		
		SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy", Locale.getDefault());
		
		if (caso.getCodigoConvenio() == 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
							   "El Caso debe estar asociado a un Convenio");
		else if (caso.getRutSocio() == null ||  caso.getRutSocio().length() == 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
							   "El Caso debe estar asociado a un Socio");
							   
		GregorianCalendar fechaOcu = new GregorianCalendar ();
		fechaOcu.setTime(caso.getFechaDeOcurrencia());
		int dia = fechaOcu.get(Calendar.DAY_OF_MONTH); 
		int mes = fechaOcu.get(Calendar.MONTH)+1;
		int anio = fechaOcu.get(Calendar.YEAR);
		caso.setFechaDeOcurrencia(formato.parse(dia + "/" + mes + "/" + anio));
		logger.debug("+ + + + Fecha: "+dia + "/" + mes + "/" + anio);
		
							   
		//Valido que la fecha de ocurrencia no sea anterior a la fecha de inicio de 
		//beneficios de la carga						   
		if (caso.getRutCarga() != null && caso.getRutCarga().length() > 0) {
			Carga car = bonificacionesDao.getCarga(caso.getRutCarga(),caso.getRutSocio());
			if (car.getFecIngCarga().compareTo(caso.getFechaDeOcurrencia()) > 0)
				throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					   "La Fecha de ocurrencia del Caso no puede ser anterior a la fecha de Ingreso de la Carga");				   
		}
		
		//Valido que la fecha de ocurrencia no sea anterior a la fecha de inicio de 
		//beneficios del socio
		Socio soc = bonificacionesDao.getSocio(caso.getRutSocio());
		if (soc.getFecIng().compareTo(caso.getFechaDeOcurrencia()) > 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					   "La Fecha de ocurrencia del Caso no puede ser anterior a la fecha de Ingreso del Socio");
							   
		//Valida datos del Convenio
		Convenio convBD = bonificacionesDao.getConvenio(caso.getCodigoConvenio());
		
		if (convBD.getEstado().equals(Convenio.STD_BORRADOR))
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
							   "No es posible asociar un convenio en estado Borrador");
		
		if (convBD.getEstado().equals(Convenio.STD_ELIMINADO)
			&& (convBD.getFecFin().compareTo(caso.getFechaDeOcurrencia()) < 0))
			throw new BusinessException(
				"CCAF_BONIF_CASOINVALIDO",
				"La Fecha de ocurrencia del Caso no puede ser posterior a la fecha de finalización del Convenio");

		GregorianCalendar fechaInicioConvenio = new GregorianCalendar ();
		fechaInicioConvenio.setTime(convBD.getFecInicio());
		int d = fechaInicioConvenio.get(Calendar.DAY_OF_MONTH); 
		int m = fechaInicioConvenio.get(Calendar.MONTH)+1;
		int a = fechaInicioConvenio.get(Calendar.YEAR);
		convBD.setFecInicio(formato.parse(d + "/" + m + "/" + a));
						
		logger.debug("Convenio: "+convBD.getFecInicio()+" Ocurrecia: "+caso.getFechaDeOcurrencia());							   

		if (convBD.getFecInicio().compareTo(caso.getFechaDeOcurrencia()) > 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					   "La Fecha de ocurrencia del Caso no puede ser anterior a la fecha de Ingreso del Convenio");
							   
		if (convBD.getNumeroMaximoCuotas() < caso.getCuotasConvenio())
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
						   "El número de cuotas externas supera las permitidas por el convenio");
		
		if (caso.getCuotasBienestar() > 1 && caso.getCuotasConvenio() > 1)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
						   "No se pueden definir cuotas internas y externas a la vez");
						   
		if (caso.getTipoCaso().equals(Caso.TIPO_REEMBOLSO) && (caso.getCuotasBienestar() > 1 || caso.getCuotasConvenio() > 1))
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					   "En el tipo de caso reembolso no se pueden definir cuotas");	   
							   
		//Obtiene la informacion del caso que se encuentra registrada en la BD					   
		CasoVO casoBD = bonificacionesDao.getCasoVO(caso.getCasoID());
		
		if (casoBD.getEstado().equals(Caso.STD_ELIMINADO))
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
							   "El estado del caso es incorrecto");
							   
		//Validar Indicadores
		if (casoBD.getIndicadorDescontado().equals(Caso.ESTADOINDICADOR_SI)
			|| casoBD.getIndicadorDescontado().equals(
				Caso.ESTADOINDICADOR_PROCESO)
			|| casoBD.getIndicadorPago().equals(Caso.ESTADOINDICADOR_SI)
			|| casoBD.getIndicadorReembolso().equals(Caso.ESTADOINDICADOR_SI))
			throw new BusinessException(
				"CCAF_BONIF_CASOINVALIDO",
				"El Caso No puede ser Modificado");
		
		//Si cambió el código de convenio, elimino los detalles existentes previamente
		if (caso.getCodigoConvenio() != casoBD.getCodigoConvenio()) {
			bonificacionesDao.deleteDetalles(caso.getCasoID());
			casoBD.removeAllDetalleCaso();
			//El caso queda no bonificado
			casoBD.setIndicadorBonificacion(Caso.ESTADOINDICADOR_NO);
			casoBD.setAporteBienestar(0);
			casoBD.setAporteSocio(0);
			casoBD.setAporteIsapre(0);
			casoBD.setMonto(0);
			casoBD.setMontoDescuento(0);
			casoBD.setEstado(Caso.STD_BORRADOR);
			casoBD.setFechaEstado(new Date());
			//Elimino cuotas
			bonificacionesDao.deleteCuotasCaso(caso.getCasoID());
			casoBD.removeAllCuotas();
		}
		
		/*
		 * Actualiza información del vale
		 * Si viene un codigo de vale, debo verificar si ya estaba asociado 
		 * anteriormente, en cuyo caso no debo actualzar la información del vale, 
		 * en caso de no existir debo verificar si existía otro vale asociado al caso y 
		 * desasociarlo, para luego asociarlo el vale nuevo. 
		 */

		//verifico si existen vales registrados asociados al caso
		Vale valeFiltro = new Vale();
		valeFiltro.setCaso_id(caso.getCasoID());
		valeFiltro.setValeAnulado(Vale.NO_ANULADO);
		ArrayList retorno = bonificacionesDao.getVales(valeFiltro,null,0);
		logger.debug("Cantidad de Vales: " + retorno.size());
		if (retorno.size() == 1) {
			Vale val = (Vale)retorno.get(0);
			logger.debug("Vale: " + val.getCodigoVale());
			if (caso.getVale() != null) {
				//Si los vales son distintos, debo liberar el vale antiguo
				if (caso.getVale().getCodigoVale() != val.getCodigoVale()) {
					logger.debug("Debe liberar Vale: " + val.getCodigoVale());
					val.setCaso_id(0);			
					val.setFechaRecepcion(formato.parse("00/00/0000"));
					bonificacionesDao.updateVale(val,caso.getRutSocio());
				}
				if (caso.getVale().getCodigoVale() > 0) {
					ArrayList nuevoRetorno = bonificacionesDao.getVales(caso.getVale(),caso.getRutSocio(),caso.getCodigoConvenio());
					if (nuevoRetorno.size() == 0 || nuevoRetorno.size() != 1)
						throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
									   "No se pudo obtener información del Vale: " + caso.getVale().getCodigoVale());
					else if (nuevoRetorno.size() == 1){
						//actualiza información del caso Id y de la fecha de recepción
						Vale valNuevo = (Vale)nuevoRetorno.get(0);
						valNuevo.setCaso_id(caso.getCasoID());
						valNuevo.setFechaRecepcion(new Date());
						logger.debug("Actualiza nuevo Vale: " + valNuevo.getCodigoVale());
						bonificacionesDao.updateVale(valNuevo,caso.getRutSocio());
					}	
				}
			} else {
				logger.debug("Debe liberar Vale: " + val.getCodigoVale());
				val.setCaso_id(0);
				val.setFechaRecepcion(formato.parse("00/00/0000"));
				bonificacionesDao.updateVale(val,caso.getRutSocio());
			}
		} else if (retorno.size() == 0 && caso.getVale() != null){
			if (caso.getVale().getCodigoVale() > 0) {
				ArrayList resultado = bonificacionesDao.getVales(caso.getVale(),caso.getRutSocio(),caso.getCodigoConvenio());
				if (resultado.size() != 1)
					throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
								   "No se pudo obtener información del Vale: " + caso.getVale().getCodigoVale());
				else {
					//actualiza información del caso Id y de la fecha de recepción
					Vale val = (Vale)resultado.get(0);
					val.setCaso_id(caso.getCasoID());
					val.setFechaRecepcion(new Date());
					logger.debug("Actualiza Vale: " + val.getCodigoVale());
					bonificacionesDao.updateVale(val,caso.getRutSocio());
				}
			}
		} else if (retorno.size() > 1)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
						   "La información del Caso (Vale) está inconsistente");
		
		
		caso.setAporteBienestar(casoBD.getAporteBienestar());
		caso.setAporteIsapre(casoBD.getAporteIsapre());
		caso.setAporteSocio(casoBD.getAporteSocio());
		caso.setFechaEstado(casoBD.getFechaEstado());
		caso.setFechaIngreso(casoBD.getFechaIngreso());
		caso.setIndicadorBonificacion(casoBD.getIndicadorBonificacion());
		caso.setIndicadorDescontado(casoBD.getIndicadorDescontado());
		caso.setIndicadorPago(casoBD.getIndicadorPago());
		caso.setIndicadorReembolso(casoBD.getIndicadorReembolso());
		caso.setMonto(casoBD.getMonto());
		caso.setMontoDescuento(casoBD.getMontoDescuento());
		
		//VALIDAR NUMERO DE CUOTAS	
		//Si ahora es sin cuotas y tenia cuotas previamente, las elimino	
		if ((caso.getCuotasBienestar() <= 1 || caso.getCuotasConvenio() <= 1) && (casoBD.getCuotasBienestar() > 1 || casoBD.getCuotasConvenio() > 1)) {
			logger.debug("Elimina Cuotas previas");
			bonificacionesDao.deleteCuotasCaso(caso.getCasoID());
			caso.removeAllCuotas();
		} 
		
		//Determinar nuevas cuotas
		int cantidadCuotas = 0;
		
		if (caso.getCuotasConvenio() > 1)
			cantidadCuotas = caso.getCuotasConvenio();
		else if (caso.getCuotasBienestar() > 1)
			cantidadCuotas = caso.getCuotasBienestar();
		
		//Si existen cuotas las genero
		if (cantidadCuotas > 0) {
			
			//Elimino las cuotas existentes previamente
			if (casoBD.getCuotasConvenio() > 0 || casoBD.getCuotasBienestar() > 0) {
				logger.debug("Elimina Cuotas previas");
				bonificacionesDao.deleteCuotasCaso(caso.getCasoID());
				caso.removeAllCuotas();
			}
			
			if (casoBD.getAporteSocio() > 0) {
				//Genero nuevas cuotas
				ArrayList cuotas = generarCuotas(casoBD.getAporteSocio(),cantidadCuotas);
				
				for (int x = 0; x < cuotas.size(); x++) {
					Cuota cuota = (Cuota)cuotas.get(x);
					logger.debug("Num Cuota : " + cuota.getCuotaNumero() + " fecVencimineto : " + cuota.getFechaVencimiento());
					bonificacionesDao.insertCuotaCaso(caso.getCasoID(),cuota);
				}
				caso.setCuota(cuotas);
			}
		}
		bonificacionesDao.updateCaso(caso);
	}
	
	
	/**
	 * Deja en estado BORRADOR un Caso
	 * @param caso
	 */
	private void cambiarABorradorCaso(long casoId) throws Exception, BusinessException {
		
		CasoVO casoBD = bonificacionesDao.getCasoVO(casoId);
			
		if (!casoBD.getEstado().equals(Caso.STD_PRECASO))
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
							   "El estado del caso es incorrecto");
							   							   							   							   
		casoBD.setEstado(Caso.STD_BORRADOR);
		casoBD.setFechaEstado(new Date());
		
		bonificacionesDao.updateCaso((Caso)casoBD);
	}	
	
	/**
	 * Activa un Caso
	 * @param caso
	 */
	public void activarCaso(long casoId) throws Exception, BusinessException {
		
		CasoVO casoBD = bonificacionesDao.getCasoVO(casoId);
			
		if (!casoBD.getEstado().equals(Caso.STD_BORRADOR) && !casoBD.getEstado().equals(Caso.STD_PRECASO))
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
							   "El estado del caso es incorrecto");
							   							   
		if (casoBD.getMonto() == 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
						   "El Caso no tiene detalles, no puede activarlo");
							   							   
		casoBD.setEstado(Caso.STD_ACTIVO);
		casoBD.setFechaEstado(new Date());
		
		bonificacionesDao.updateCaso((Caso)casoBD);
	}
	
	/**
	 * Elimina un Caso
	 * Si el caso fue o es precaso, verifica previamnete los estados
	 * de los folios que se hayan generado en tesoreria de Bienestar
	 * @param long casoId
	 * @param String usuario
	 */
	public void eliminarCaso(long casoId, String usuario) throws Exception, BusinessException  {
		
		CasoVO casoBD = bonificacionesDao.getCasoVO(casoId);
		
		if (casoBD.getEstado().equals(Caso.STD_ELIMINADO))
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
							   "El Caso ya se encuentra Eliminado");
							   
		if (casoBD.getIndicadorDescontado().equals(Caso.ESTADOINDICADOR_SI)
			|| casoBD.getIndicadorDescontado().equals(
				Caso.ESTADOINDICADOR_PROCESO)
			|| casoBD.getIndicadorPago().equals(Caso.ESTADOINDICADOR_SI)
			|| casoBD.getIndicadorReembolso().equals(Caso.ESTADOINDICADOR_SI))
			throw new BusinessException(
				"CCAF_BONIF_CASOINVALIDO",
				"El Caso No puede ser Eliminado");
				
				
		if(casoBD.getIndicadorPreCaso().equals(Caso.ESTADOINDICADOR_SI)){

			//Servicio de TesoreriaEJB
			ServicesTesoreriaDelegate tesoreria = new ServicesTesoreriaDelegate();
			
			//Recuperar folios de egresos e ingresos generados
			ArrayList listaFolios = getMovimientosTesoreriaPreCaso(casoBD.getCasoID());
			if(listaFolios.size()==0){
				//En esta situacion solo se debe eliminar el caso
				logger.debug("No tiene folios");
			}else{
				//Consultar por estado de cada folio en tesoreria
				boolean tieneIngresosImpresos=false;
				boolean tieneIngresosGenerados=false;
				boolean tieneIngresosAnulados=false;
				boolean tieneIngresosOtroEstado=false;
				
				boolean tieneEgresosImpresos=false;
				boolean tieneEgresosGenerados=false;
				boolean tieneEgresosAnulados=false;
				boolean tieneEgresosOtroEstado=false;
				
				for(int x=0; x<listaFolios.size();x++){
					InfoMovimientoPreCasoVO infoMovimientoPreCasoVO = (InfoMovimientoPreCasoVO)listaFolios.get(x);
					String estado = tesoreria.getEstadoComprobanteTesoreriaBienestar(infoMovimientoPreCasoVO.getFolioTesoreria());
					if(infoMovimientoPreCasoVO.getTipoMovimiento().equals(Constants.MOVI_INGRESO_ISAPRE) ||
						infoMovimientoPreCasoVO.getTipoMovimiento().equals(Constants.MOVI_INGRESO_OTROS)){
						logger.debug("Movimiento Ingreso");
						if(estado.equals(Comprobante.STD_MOV_CAJA_IMPRESO))
							tieneIngresosImpresos=true;
						else if(estado.equals(Comprobante.STD_MOV_CAJA_GENERADO))
							tieneIngresosGenerados=true;
							else if(estado.equals(Comprobante.STD_MOV_CAJA_ANULADO))
								tieneIngresosAnulados=true;
								else
									tieneIngresosOtroEstado=true;						
					}else{
						logger.debug("Movimiento Egreso");
						if(estado.equals(Comprobante.STD_MOV_CAJA_IMPRESO))
							tieneEgresosImpresos=true;
						else if(estado.equals(Comprobante.STD_MOV_CAJA_GENERADO))
							tieneEgresosGenerados=true;
							else if(estado.equals(Comprobante.STD_MOV_CAJA_ANULADO))
								tieneEgresosAnulados=true;
								else
									tieneEgresosOtroEstado=true;						
					}
				}
				//Si tiene comprobantes en estado distinto a: Impreso, Generado o Anulado, no puede elimianr caso
				if(tieneIngresosOtroEstado && tieneEgresosOtroEstado)
					throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
						"El Caso No puede ser Eliminado, ya que tiene Ingreso(s) y Egreso(s) en estados que no permiten su eliminación");
						
				if(tieneIngresosOtroEstado)
					throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
						"El Caso No puede ser Eliminado, ya que tiene Ingreso(s) en estado(s) que no permite(n) su eliminación");
								
				if(tieneEgresosOtroEstado)
					throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
						"El Caso No puede ser Eliminado, ya que tiene Egreso(s) en estado(s) que no permite(n) su eliminación");

				//Si llegamos hasta aquí, es porque no existen comprobantes en estados no validos para la eliminacion 
				if((tieneIngresosAnulados || tieneEgresosAnulados) && 
					!tieneIngresosImpresos && !tieneIngresosGenerados &&
					!tieneEgresosImpresos && !tieneEgresosGenerados){
						//Como solo tiene comprobantes Anulados se debe eliminar el caso directamente
						logger.debug("Solo tiene comprobantes anulados");					
				}else {
					//Si llegamos hasta aqui es porque todos los comprobantes se encuentran
					//en estado que permite su anulacion, entonces, los anulamos
					logger.debug("Tiene Comprobantes que se pueden eliminar");
					
					//Recorro todo los comprobantes 
					for(int x=0; x<listaFolios.size();x++){
						InfoMovimientoPreCasoVO infoMovimientoPreCasoVO = (InfoMovimientoPreCasoVO)listaFolios.get(x);
						String estado = tesoreria.getEstadoComprobanteTesoreriaBienestar(infoMovimientoPreCasoVO.getFolioTesoreria());
						if(infoMovimientoPreCasoVO.getTipoMovimiento().equals(Constants.MOVI_INGRESO_ISAPRE) ||
							infoMovimientoPreCasoVO.getTipoMovimiento().equals(Constants.MOVI_INGRESO_OTROS)){
							if(estado.equals(Comprobante.STD_MOV_CAJA_IMPRESO) || 
								estado.equals(Comprobante.STD_MOV_CAJA_GENERADO)){
									//Se anula comprobante de ingreso, via metodo de EJB de Tesoreria
									int filasActualizadas = tesoreria.anulaComprobanteIngresoTesoreriaBienestar(infoMovimientoPreCasoVO.getFolioTesoreria(), usuario);
									if(filasActualizadas!=1)
										throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
											"Hubo problemas al intentar anular el comprobante de Ingreso número: "+infoMovimientoPreCasoVO.getFolioTesoreria()
											+ ", Verifique en Tesoreria de Bienestar (filas actualizadas: "+filasActualizadas+")");									
								}
						}else{
							if(estado.equals(Comprobante.STD_MOV_CAJA_IMPRESO) || 
								estado.equals(Comprobante.STD_MOV_CAJA_GENERADO)){
									//Se anula comprobante de engreso, via metodo de EJB de Tesoreria que utiliza programa Cobol
									boolean resultadoOK = tesoreria.anulaComprobanteEgresoTesoreriaBienestar(infoMovimientoPreCasoVO.getFolioTesoreria());
									if(!resultadoOK)
										throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
											"Hubo problemas al intentar anular el comprobante de Egreso número: "+infoMovimientoPreCasoVO.getFolioTesoreria()
											+ ", Verifique en Tesoreria de Bienestar");									
								}							
						}
					}							
				}
			}
		}
		
		casoBD.setEstado(Caso.STD_ELIMINADO);
		casoBD.setFechaEstado(new Date());
	
		bonificacionesDao.updateCaso((Caso)casoBD);					   							   


	}
	
	/**
	 * Genera Las cuotas a partir del aporte del Socio y el número de cuotas
	 * @param aporteSocioTotalCaso
	 * @param numeroCuotas
	 * @return
	 */
	public ArrayList generarCuotas(double aporteSocioTotalCaso, int numeroCuotas)  throws Exception, BusinessException  {
		
		int resto = 0;
		int valorCuota = 0;
		
		SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy", Locale.getDefault());
		
		int  diaVencimientoCuotas = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/dia-vencimiento-Cuota"));
		
		ArrayList cuotas = new ArrayList();
				
		logger.debug("Aporte Socio: " + aporteSocioTotalCaso);
		logger.debug("Num Cuotas: " + numeroCuotas);
		valorCuota = (int)aporteSocioTotalCaso / numeroCuotas;
		resto = (int)aporteSocioTotalCaso % numeroCuotas;
				
		logger.debug("resto: " + resto);
		logger.debug("valorCuota: " + valorCuota);
		
		Date fechaUltimoDescuento = bonificacionesDao.getFechaUltimoDescuento();
				
		for (int x = 1; x <= numeroCuotas;x++) {
			GregorianCalendar fechaVencimiento = new GregorianCalendar();
			fechaVencimiento.setTime(fechaUltimoDescuento);
			
			Cuota cuota = new Cuota();
			cuota.setCuotaEstado(Cuota.STD_NO_DESCONTADA);
			cuota.setCuotaNumero(x);
			fechaVencimiento.set(GregorianCalendar.DAY_OF_MONTH,diaVencimientoCuotas);
			fechaVencimiento.add(GregorianCalendar.MONTH,x+1);
			Date fecVcto = formato.parse(fechaVencimiento.get(Calendar.DAY_OF_MONTH)+"/"+fechaVencimiento.get(Calendar.MONTH)+"/"+fechaVencimiento.get(Calendar.YEAR));
			cuota.setFechaVencimiento(fecVcto);
			logger.debug("Fecha Vencimiento :" + cuota.getFechaVencimiento());
			cuota.setValorCuota(valorCuota);	
			//ultima Cuota
			if ( x == numeroCuotas) {
				valorCuota = valorCuota + resto;
				cuota.setValorCuota(valorCuota);
				logger.debug("ultima cuota: " + valorCuota);	
			}
			logger.debug("Cuota " + x + " Valor: " + valorCuota);
			cuotas.add(cuota);
		}
		return cuotas;	
	}
	
	
	/** 
	 * Obtiene informacion de un Caso
	 * @param codigo de caso (caso ID)
	 * @return CasoVO
	 * @throws Exception
	 */
	public CasoVO getCasoVO(long casoId) throws Exception, BusinessException {
		return bonificacionesDao.getCasoVO(casoId);
	}
	
	/** 
	 * Obtiene una lista de casos que corresponden a compra de bonos
	 * @param rut Socio
	 * @return ArrayList de Caso
	 * @throws Exception
	 */
	public ArrayList getBonos(String rutSocio) throws Exception, BusinessException {
		//setear valores para obtener casos bonos
		Caso casoFiltro = new Caso();
		casoFiltro.setRutSocio(rutSocio);
		casoFiltro.setEstado(null);
		casoFiltro.setTipoCaso(Caso.TIPO_DESCUENTO);
		casoFiltro.setTipoBono(Caso.TIPOBONO_SOCIO);
		casoFiltro.setIndicadorBonificacion(null);
		casoFiltro.setIndicadorDescontado(null);
		casoFiltro.setIndicadorPago(null);
		casoFiltro.setIndicadorReembolso(null);
		return bonificacionesDao.getCasos(casoFiltro,0);
	}
	
	/** 
	 * Obtiene una lista de casos que corresponden a compra de bonos
	 * @param rut Socio
	 * @return ArrayList de Caso
	 * @throws Exception
	 */
	public ArrayList getReembolsos(String rutSocio) throws Exception, BusinessException{
		//setear valores para obtener casos bonos
		Caso casoFiltro = new Caso();
		casoFiltro.setRutSocio(rutSocio);
		casoFiltro.setEstado(null);
		casoFiltro.setTipoCaso(Caso.TIPO_REEMBOLSO);
		casoFiltro.setIndicadorBonificacion(null);
		casoFiltro.setIndicadorDescontado(null);
		casoFiltro.setIndicadorPago(null);
		casoFiltro.setIndicadorReembolso(null);
		casoFiltro.setTipoBono(null);
		return bonificacionesDao.getCasos(casoFiltro,0);
	}
	
	/** 
	 * Obtiene una lista de casos no bonificados
	 * @param
	 * @return ArrayList de Caso
	 * @throws Exception
	 */
	public ArrayList getCasosNoBonificados(String tipoCaso) throws Exception, BusinessException {
		//setear valores para obtener casos no bonificados
		Caso casoFiltro = new Caso();
		casoFiltro.setEstado(Caso.STD_ACTIVO);
		casoFiltro.setTipoCaso(tipoCaso);
		casoFiltro.setTipoBono(null);
		casoFiltro.setIndicadorBonificacion(Caso.ESTADOINDICADOR_NO);
		casoFiltro.setIndicadorDescontado(null);
		casoFiltro.setIndicadorPago(null);
		casoFiltro.setIndicadorReembolso(null);
		casoFiltro.setIndicadorPreCaso(null);
		casoFiltro.setIndicadorPreCasoEgresoGenerado(null);
		casoFiltro.setIndicadorPreCasoIngresoGenerado(null);
		return bonificacionesDao.getCasos(casoFiltro,0);
	}
	

	/** 
	 * Obtiene una lista con los Casos Por Reembolsar
	 * segun las fecha indicadas
	 * @param ParamOperacionesVO 
	 * @return ArrayList de ReembolsosVO
	 * @throws Exception
	 */
	public ArrayList getCasosPorReembolsar(ParamOperacionesVO param, String rut) throws Exception, BusinessException {
		
		if(param.getFechaFin()==null)
			throw new BusinessException("CCAF_BONIF_DATOSINCORRECTOS",
				"La fecha de fin de busqueda no puede ser nula");
				
		return bonificacionesDao.getCasosPorReembolsar(param, rut);
	}
	
	/** 
	 * Obtiene una lista con el Caso a descontar
	 * segun las fecha indicadas
	 * @param ParamOperacionesVO
	 * @return ArrayList de DescuentoVO
	 * @throws Exception
	 */
	public ArrayList getCasoPorDescontar(long casid) throws Exception, BusinessException {
		
		GregorianCalendar fec = new GregorianCalendar();
		fec.setTime(new Date());
		int mes = fec.get(Calendar.MONTH);
		int anio = fec.get(Calendar.YEAR);
		
		ArrayList casos = bonificacionesDao.getCasoPorDescontar(casid);
		ArrayList retorno = new ArrayList(); 
		
		for (int x=0;x<casos.size();x++) {
			DescuentosVO descuento = (DescuentosVO) casos.get(x);
			if (descuento.getNumeroCuotasBienestar() > 1 || descuento.getNumeroCuotasConvenio() > 1) {
				if (descuento.getNumeroCuotasBienestar() > 1)
					descuento.setNumeroCuotas(descuento.getNumeroCuotasBienestar());
				else
					descuento.setNumeroCuotas(descuento.getNumeroCuotasConvenio());
				//Se agruparan todas las cuotas NO COBRADAS (Por cobrar) en una sola que se marcara como pagada
				//debe ser asi porque sino se deberia modificar en gran medida el proceso pago de convenios
				
				//mescla de cosas: reutilización de metodo getCasosPorDescontar (asocio ultima cuota no descontada a todos los campos de 
				//cuota pendiente VO menos el monto que sera el total de las cuotas no cobradas.
				CuotaPendienteVO cuotaPendiente = bonificacionesDao.getCuotaNoCobrada(descuento.getCasoId());
				List cuotasNoCob = bonificacionesDao.getCuotasNoCobradas(descuento.getCasoId());
				
				Iterator iterCuotasNoCob = cuotasNoCob.iterator();
				double totalCuota = 0d;
				while(iterCuotasNoCob.hasNext()){
					CuotaPendienteVO cuota = (CuotaPendienteVO) iterCuotasNoCob.next();
					totalCuota += cuota.getMonto();
				}
				descuento.setMontoDescuento(totalCuota);//---total
				descuento.setCuotaActual(cuotaPendiente.getCuota());
				descuento.setFechaDescuento(cuotaPendiente.getFecha());
				GregorianCalendar fecDes = new GregorianCalendar();
				fecDes.setTime(cuotaPendiente.getFecha());
				logger.debug("Caso ID: "+descuento.getCasoId());
				logger.debug("Fecha Cuota: "+cuotaPendiente.getFecha());
				logger.debug("mes: "+mes);
				logger.debug("mesDes: "+fecDes.get(Calendar.MONTH));
				logger.debug("anio: "+anio);
				logger.debug("anioDes: "+fecDes.get(Calendar.YEAR));
				retorno.add(descuento);
			} 
		}
		return retorno;
	}	
	
	/** 
	 * Obtiene una lista con los Casos Por Descontar
	 * segun las fecha indicadas
	 * @param ParamOperacionesVO
	 * @return ArrayList de DescuentoVO
	 * @throws Exception
	 */
	public ArrayList getCasosPorDescontar(ParamOperacionesVO param) throws Exception, BusinessException {
		
		GregorianCalendar fec = new GregorianCalendar();
		fec.setTime(new Date());
		int mes = fec.get(Calendar.MONTH);
		int anio = fec.get(Calendar.YEAR);
		
		if(param.getFechaFin()==null)
			throw new BusinessException("CCAF_BONIF_DATOSINCORRECTOS",
				"La fecha de fin de busqueda no puede ser nula");
		
		ArrayList casos = bonificacionesDao.getCasosPorDescontar(param);
		ArrayList retorno = new ArrayList(); 
		
		for (int x=0;x<casos.size();x++) {
			logger.debug("casos procesados: "+x);
			DescuentosVO descuento = (DescuentosVO) casos.get(x);
			logger.debug("Caso ID: "+descuento.getCasoId());
			if (descuento.getNumeroCuotasBienestar() > 1 || descuento.getNumeroCuotasConvenio() > 1) {
				
				if (descuento.getNumeroCuotasBienestar() > 1)
					descuento.setNumeroCuotas(descuento.getNumeroCuotasBienestar());
				else
					descuento.setNumeroCuotas(descuento.getNumeroCuotasConvenio());	

				CuotaPendienteVO cuotaPendiente = bonificacionesDao.getCuotaNoCobrada(descuento.getCasoId());
				descuento.setMontoDescuento(cuotaPendiente.getMonto());
				descuento.setCuotaActual(cuotaPendiente.getCuota());
				descuento.setFechaDescuento(cuotaPendiente.getFecha());
				GregorianCalendar fecDes = new GregorianCalendar();

				fecDes.setTime(cuotaPendiente.getFecha());
				
				logger.debug("Fecha Cuota: "+cuotaPendiente.getFecha());
				logger.debug("mes: "+mes);
				logger.debug("mesDes: "+fecDes.get(Calendar.MONTH));
				logger.debug("anio: "+anio);
				logger.debug("anioDes: "+fecDes.get(Calendar.YEAR));
				if (fecDes.get(Calendar.MONTH) == mes && fecDes.get(Calendar.YEAR) == anio)
					retorno.add(descuento);
			} else {
				retorno.add(descuento);
			}
		}
		return retorno;
	}
	
	
	/** 
	 * Obtiene una lista con los Casos Por Pagar
	 * @return ArrayList de PagoConvenioVO
	 * @throws Exception
	 */
	public ArrayList getCasosPorPagar(long codigoDescuento) throws Exception, BusinessException {
		
		GregorianCalendar fec = new GregorianCalendar();
		fec.setTime(new Date());
		
		ArrayList casos = bonificacionesDao.getCasosPorPagar(codigoDescuento);
		ArrayList retorno = new ArrayList(); 
		
		for (int x=0;x<casos.size();x++) {
			
		
			int cuotaAporteBienestar=0;
			PagoConvenioVO pago = (PagoConvenioVO) casos.get(x);
			System.out.println("ID CASO: "+pago.getCasoId());

			
			if (pago.getNumeroCuotasBienestar() >= 1 || pago.getNumeroCuotasConvenio() >= 1) {//TODO: evaluar que implicancia tiene dejar >=1 (inicialmente estaba > 1) OJO! cuotas bienestar aquí?? WTF?!
				if (pago.getNumeroCuotasBienestar() >= 1){
					pago.setNumeroCuotas(pago.getNumeroCuotasBienestar());
				}
				else{
					pago.setNumeroCuotas(pago.getNumeroCuotasConvenio());
					//dentro de lo que se paga al convenio, tambien hay que añadir la parte de la cuota pagada por
					//bienestar.
					if(pago.getAporteBienestar()>0){
						cuotaAporteBienestar = getCuota(pago.getAporteBienestar(), pago.getNumeroCuotasConvenio(), pago.getCuotaActual());
												
						/**
						 * modificación req 4353 para saldar deuda total
						 */
						/*if(pago.getAporteBienestar() > cuotaAporteBienestar){
							cuotaAporteBienestar = pago.getAporteBienestar();
						}*/
					}
				}
					
				CuotaPendienteVO cuotaPendiente = bonificacionesDao.getCuotaNoPagada(pago.getCasoId());
				pago.setMontoPago(cuotaPendiente.getMonto()+cuotaAporteBienestar);
				pago.setCuotaActual(cuotaPendiente.getCuota());
				retorno.add(pago);
			} else {
				retorno.add(pago);
			}
			
		}
		return retorno;
	}
	
	/**
	 * Se utiliza para calcular el monto de una cuota en particular
	 * @param monto
	 * @param cuotas
	 * @param cuotaActual
	 * @return int montoCuotaActual
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int getCuota(int monto, int cuotas, int cuotaActual) throws Exception, BusinessException {

		int montoCuotaActual=0;
		
		ArrayList listaCuotas = generarCuotas(monto, cuotas);
		for(int x=0;x<listaCuotas.size();x++){
			Cuota cuota = (Cuota)listaCuotas.get(x);
			if(cuota.getCuotaNumero()== cuotaActual){
				montoCuotaActual=(int)cuota.getValorCuota();
				break;
			}
		}
		logger.debug("Monto Cuota: "+montoCuotaActual);
		return montoCuotaActual;
	}
	
	/**
	 * Se utiliza para calcular el monto a pagar por bienestar para una cuota en cuestion.
	 * @param monto
	 * @param cuotas
	 * @param cuotaActual
	 * @return int montoCuotaActual
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int getMontoCuotaPagadoPorBienestar(long casid, int cuotaActual) throws Exception, BusinessException {
		logger.info(">>getMontoCuotaPagadoPorBienestar");
		int montoCuotaActual=0;
		
		//Cuota cuotaFilter = new Cuota();
		//cuotaFilter.setCuotaEstado("");
		//cuotaFilter.setCuotaNumero(cuotaActual);
		Caso casoFilter = new Caso();
		casoFilter.setCasoID(casid);
		casoFilter.setEstado("");
		casoFilter.setIndicadorBonificacion("");
		casoFilter.setIndicadorReembolso("");
		casoFilter.setIndicadorDescontado("");
		casoFilter.setIndicadorPago("");
		casoFilter.setTipoBono("");
		casoFilter.setIndicadorPreCaso("");
		casoFilter.setIndicadorPreCasoEgresoGenerado("");
		casoFilter.setIndicadorPreCasoIngresoGenerado("");
		List casoList = bonificacionesDao.getCasos(casoFilter,0);
		if(casoList.size() == 1){
			Caso caso = (Caso) casoList.get(0);			
			List cuotaList = bonificacionesDao.getCuotasCaso(casid,null);
			
			if(cuotaList.size() > 0){
				
				int numCuotasTentativas = 0;
				double aporteBienestarTentativoAcum = 0d;
				double aporteBienestarTentativoPorCuota = 0d;
				double valorCuotaAnterior = ((Cuota) cuotaList.get(0)).getValorCuota();
				for(Iterator i = cuotaList.iterator();i.hasNext(); ){
					
					Cuota cuota = (Cuota)i.next();
					//obtenemos el valor original de las cuotas
					if(numCuotasTentativas == 0){
						aporteBienestarTentativoAcum = caso.getAporteBienestar();
						numCuotasTentativas = new Double( caso.getAporteSocio() / cuota.getValorCuota()).intValue(); 
						aporteBienestarTentativoPorCuota = caso.getAporteBienestar() / numCuotasTentativas;
						
						logger.debug("\taporteBienestarTentativoAcum = "+aporteBienestarTentativoAcum);
						logger.debug("\tnumCuotasTentativas = "+numCuotasTentativas+" (calculo: "+caso.getAporteSocio() +"/"+ cuota.getValorCuota()+")");
						logger.debug("\taporteBienestarTentativoPorCuota = "+aporteBienestarTentativoPorCuota);
						
					}
					
					//se ha pagado el mismo valor en la cuota actual que la anterior
					//luego no hubo saldo total de la deuda.
					if(valorCuotaAnterior == cuota.getValorCuota()){
						
						//si es la cuota buscada.
						if(cuotaActual == cuota.getCuotaNumero()){
							logger.debug("\tPago cuota B: aporte bienestar = "+aporteBienestarTentativoPorCuota);
							return new Double(aporteBienestarTentativoPorCuota).intValue();
						}
						aporteBienestarTentativoAcum -= aporteBienestarTentativoPorCuota;
						logger.debug("\taporteBienestarTentativoAcum = "+aporteBienestarTentativoAcum);
					}else{
						//si hubo pago deuda anticipada hay que añadir todo el monto que falta del aporte total de bienestar.
						logger.debug("\tPago cuota anticipado: aporte bienestar = "+aporteBienestarTentativoAcum);
						return new Double(aporteBienestarTentativoAcum).intValue();
					}
				}
			}
		}
		
		
		logger.debug("\tcaso: "+casid+" Monto Cuota: "+montoCuotaActual);
		logger.info("<<getMontoCuotaPagadoPorBienestar");
		return montoCuotaActual;
	}	
	
	/** 
	 * Obtiene lista de casos
	 * @param Caso con los filtros
	 * @return ArrayList de Caso
	 * @throws Exception
	 */
	public ArrayList getListaCasos(Caso caso) throws BusinessException, Exception {
		caso.setIndicadorBonificacion(null);
		caso.setIndicadorDescontado(null);
		caso.setIndicadorPago(null);
		caso.setIndicadorReembolso(null);
		caso.setTipoBono(null);
		caso.setIndicadorPreCaso(null);
		caso.setIndicadorPreCasoEgresoGenerado(null);
		caso.setIndicadorPreCasoIngresoGenerado(null);

		return bonificacionesDao.getCasos(caso,0);
	}

	/** 
	 * Obtiene lista de casos
	 * @param Caso con los filtros
	 * @return ArrayList de Caso
	 * @throws Exception
	 */
	public ArrayList getListaCasosSocioInactivo(Caso caso) throws BusinessException, Exception {
		caso.setIndicadorBonificacion(null);
		caso.setIndicadorDescontado(null);
		caso.setIndicadorPago(null);
		caso.setIndicadorReembolso(null);
		caso.setTipoBono(null);
		caso.setIndicadorPreCaso(null);
		caso.setIndicadorPreCasoEgresoGenerado(null);
		caso.setIndicadorPreCasoIngresoGenerado(null);

		return bonificacionesDao.getCasosSocioInactivo(caso,0);
	}
	
	/** 
	 * Obtiene lista de casos asociados a una cobertura
	 * @param codigoCobertura
	 * @return ArrayList de Caso
	 * @throws Exception
	 */
	public ArrayList getListaCasosCobertura(long codigoCobertura) throws Exception, BusinessException {
		return bonificacionesDao.getCasos(null,codigoCobertura);
	}
	
	/** 
	 * Obtiene una lista de Cuotas
	 * @param codigo de caso (caso ID)
	 * @return Caso con ArrayList de Cuota
	 * @throws Exception
	 */
	public Caso getCuotasCaso(Caso caso) throws BusinessException, Exception {
		caso.setCuota(bonificacionesDao.getCuotasCaso(caso.getCasoID(),null));
		return caso;
	}
	
	/**
	 * Calcula el aporte de Bienestar para una cobertura en particular
	 * Además del aporte de bienestar, retorna el monto que es susceptible para
	 * ser bonificado por alguna cobertura adicional
	 * @throws Exception
	 * @param  ParametrosBonificacionCoberturaVO 
	 * @return CalculoBonificacionVO
	 * @throws Exception
	 */
	public CalculoBonificacionVO calcularBonificacionCobertura(ParametrosBonificacionCoberturaVO parametros) throws BusinessException, Exception {
		int valorReferencial = 0;
		String tipoTope = null;
		int valorBase = 0;
		int topeCobertura  = 0;
		int valorBonificado = 0;
		int aporteBienestar = 0;
		int aporteBienestarAcumulado = 0; 
		int montoBonificableNuevamente=0;

		//Se determina el Valor base
		valorReferencial =
			(int) parametros.getCobertura().getValorReferencial();
		//Tiene Valor Referencial
		if (valorReferencial > 0
			&& valorReferencial < parametros.getMonto()) {

			valorBase =
				(int) (valorReferencial
					- parametros.getMontoDescuento()
					- parametros.getMontoAporteIsapre());
			//Si el aporte de la Isapre + el descuento es mayor al valor referencial
			if (valorBase < 0) {
				valorBase =parametros.getMonto()-parametros.getMontoDescuento()-parametros.getMontoAporteIsapre();
				if (valorReferencial < valorBase)
					valorBase = valorReferencial;
			}
		} else {
			valorBase =parametros.getMonto()-parametros.getMontoDescuento()-parametros.getMontoAporteIsapre();
		}
					 	
		//Obtiene la informacion desde la BD de los rangos para el producto
		boolean tieneRangos = false;

		//aquí debo obtener el rango de cobertura vigente en la fecha de ocurrencia del caso
		ServicesCoberturasDelegate coberturasDelegate = new ServicesCoberturasDelegate();
		
		parametros.setCobertura(coberturasDelegate.getRangoCoberturaVigenteByFecha(parametros.getCobertura(),parametros.getFechaDeOcurrencia()));
		
		VigenciaRango rangoVigente = parametros.getCobertura().getRangoVigente();
		if (rangoVigente!=null) {
			parametros.getCobertura().setRangoVigente(rangoVigente);		
			
			//ServicesConveniosDelegate delegate = new ServicesConveniosDelegate();
			//Producto producto = delegate.getProducto(parametros.getCobertura().getCodigo(), parametros.getCodigoConvenio());
			//ArrayList listaRangosVigentes = new ArrayList();
			//Rango rango = new Rango();
			//int porcentajeCoberturaBienestar=0;
			//listaRangosVigentes = rangoVigente.getRangos();
			//rango = (Rango)listaRangosVigentes.get(listaRangosVigentes.size()-1);
			//porcentajeCoberturaBienestar= (int)rango.getRangoPorcentajeCobertura();
	
			//asepulveda 2013-08-22
			//Al parecer es una validación erronea, ya que los % actuan por separado sobre el monto que va quedando por bonificar
//			if (producto.getPorcentajeAporteConvenio() + porcentajeCoberturaBienestar > 100) 
//				throw new BusinessException("CCAF_BONIF_PRODUCTOINVALIDO",
//							   "El porcentaje del aporte del convenio más el aporte de bienestar no puede ser mayor a 100%. Corrija datos del convenio: "+
//								parametros.getCodigoConvenio()+ " con Producto: " +parametros.getCobertura().getCodigo());
			
			tieneRangos = true;
		}else {
			//si no tiene rangos, entonces la bonificación es cero
			aporteBienestar = 0;
		}

		if (tieneRangos) {
			//Se debe determinar en que rango esta contenido el valorBase
			boolean encontroRango = false;
			for (int y = 0;
				y < parametros.getCobertura().getRangoVigente().getRangos().size();
				y++) {
				Rango rango =
					((Rango) parametros.getCobertura().getRangoVigente().getRangos().get(y));
				if (valorBase > rango.getRangoInicio()
					&& valorBase <= rango.getRangoFin()) {
					//Si encuentra un rango
					encontroRango = true;
					valorBonificado =
						(int) ((valorBase * rango.getRangoPorcentajeCobertura()) / 100); 
					if (y>0) {
						if (valorBonificado < rango.getRangoInicio() * (((Rango) parametros.getCobertura().getRangoVigente().getRangos().get(y - 1)).getRangoPorcentajeCobertura() / 100)) {
							valorBonificado = (int) (rango.getRangoInicio() * (((Rango) parametros.getCobertura().getRangoVigente().getRangos().get(y - 1)).getRangoPorcentajeCobertura() / 100));
						}
					}
					break;
				}
			}
			montoBonificableNuevamente = valorBonificado;
			//Si no encuentra Rango aún
			if (!encontroRango) {
				//valorBonificado = valorBase * porcentajeDeCobertura (del ultimo rango)
				int posicionUltimoRango =parametros.getCobertura().getRangoVigente().getRangos().size()- 1;
				Rango rango = (Rango)parametros.getCobertura().getRangoVigente().getRangos().get(posicionUltimoRango);
				valorBonificado =
					(int) ((valorBase * rango.getRangoPorcentajeCobertura()) / 100);
				montoBonificableNuevamente = valorBonificado;
				if (valorBonificado > rango.getRangoFin()) {
					valorBonificado = (int) rango.getRangoFin();
				}
			}
			logger.debug("Valor Bonificado según Rango: "+valorBonificado);
			logger.debug("El valor esta en un rango: " + encontroRango);
			topeCobertura = (int) parametros.getCobertura().getTope();
			tipoTope = parametros.getCobertura().getTipoTope();
			
			ArrayList listaCoberturasRedefinidas = bonificacionesDao.getRelacionAgrupacionCoberturaByCoberturaMaestra(parametros.getCobertura().getCodigo());

			//Si tipoTope = POR PRESTACION
			if (tipoTope.equals(Cobertura.TOPE_EVENTO)) {
				aporteBienestar = valorBonificado > topeCobertura ? topeCobertura:valorBonificado;
			} else {								
				//Si tipoTope = TOPE ANUAL BENEFICIARIO
				if (tipoTope.equals(Cobertura.TOPE_ANUALBENEFICIARIO)) {
					//Aporte Bienestar para el beneficiario dentro del año			
					
					aporteBienestarAcumulado =
						(int) (parametros.getAporteBienestarPrevioMismoCaso() +
						bonificacionesDao.calculaAporteBienestar(
							parametros.getRutSocio(),
							parametros.getRutCarga(),
							tipoTope,
							parametros.getCobertura().getCodigo(),
							parametros.getFechaInicio(),
							parametros.getFechaFin(),
							parametros.getCasoIdNoConsiderado()));
							
					for(int x=0; x<listaCoberturasRedefinidas.size();x++){
						AgrupacionCobertura agrupacionCobertura = (AgrupacionCobertura)listaCoberturasRedefinidas.get(x);
						aporteBienestarAcumulado = aporteBienestarAcumulado +
							(int) (parametros.getAporteBienestarPrevioMismoCaso() +
							bonificacionesDao.calculaAporteBienestar(
								parametros.getRutSocio(),
								parametros.getRutCarga(),
								tipoTope,
								agrupacionCobertura.getCodigoCobertura(),
								parametros.getFechaInicio(),
								parametros.getFechaFin(),
								parametros.getCasoIdNoConsiderado()));						
					}
				}
				//Si tipoTope = TOPE ANUAL GRUPO
				else if (tipoTope.equals(Cobertura.TOPE_ANUALGRUPO)) {
					//Aporte Bienestar para el grupo dentro del año
							
					aporteBienestarAcumulado =
						(int) (parametros.getAporteBienestarPrevioMismoCaso() +
						bonificacionesDao.calculaAporteBienestar(
							parametros.getRutSocio(),
							null,
							tipoTope,
							parametros.getCobertura().getCodigo(),
							parametros.getFechaInicio(),
							parametros.getFechaFin(),
							parametros.getCasoIdNoConsiderado()));
							
					for(int x=0; x<listaCoberturasRedefinidas.size();x++){
						AgrupacionCobertura agrupacionCobertura = (AgrupacionCobertura)listaCoberturasRedefinidas.get(x);
						aporteBienestarAcumulado = aporteBienestarAcumulado +
							(int) (parametros.getAporteBienestarPrevioMismoCaso() +
							bonificacionesDao.calculaAporteBienestar(
								parametros.getRutSocio(),
								null,
								tipoTope,
								agrupacionCobertura.getCodigoCobertura(),
								parametros.getFechaInicio(),
								parametros.getFechaFin(),
								parametros.getCasoIdNoConsiderado()));				
					}							
				}
				//Si tipoTope = TOPE MENSUAL GRUPO
				else if (tipoTope.equals(Cobertura.TOPE_MENSUALGRUPO)) {
					//Aporte Bienestar para el grupo dentro del mes))

					GregorianCalendar fecIni = new GregorianCalendar();
					fecIni.setTime(parametros.getFechaDeOcurrencia());
					fecIni.set(GregorianCalendar.DAY_OF_MONTH,1);
					logger.debug("Fecha Inicio: " + fecIni);
					GregorianCalendar fecFin = new GregorianCalendar();
					fecFin.setTime(parametros.getFechaDeOcurrencia());
					fecFin.add(Calendar.MONTH,1);
					fecFin.set(GregorianCalendar.DAY_OF_MONTH,1);
					
					logger.debug("Fecha Fin" + fecFin);
					aporteBienestarAcumulado =
						(int) (parametros.getAporteBienestarPrevioMismoCaso() +
						bonificacionesDao.calculaAporteBienestar(
							parametros.getRutSocio(),
							null,
							tipoTope,
							parametros.getCobertura().getCodigo(),
							(Date)fecIni.getTime(),
							(Date)fecFin.getTime(),
							parametros.getCasoIdNoConsiderado()));
							
					for(int x=0; x<listaCoberturasRedefinidas.size();x++){
						AgrupacionCobertura agrupacionCobertura = (AgrupacionCobertura)listaCoberturasRedefinidas.get(x);
						aporteBienestarAcumulado = aporteBienestarAcumulado +
							(int) (parametros.getAporteBienestarPrevioMismoCaso() +
							bonificacionesDao.calculaAporteBienestar(
								parametros.getRutSocio(),
								null,
								tipoTope,
								agrupacionCobertura.getCodigoCobertura(),
								(Date)fecIni.getTime(),
								(Date)fecFin.getTime(),
								parametros.getCasoIdNoConsiderado()));			
					}							
				}

				if (aporteBienestarAcumulado > topeCobertura){

					registrarInconsistenciaAportesBienestar(parametros);
				}
				else {
					if (valorBonificado > (topeCobertura - aporteBienestarAcumulado)){
							aporteBienestar = topeCobertura - aporteBienestarAcumulado;
					}
					else{
							aporteBienestar = valorBonificado;
					}
				}
			}
		}

		montoBonificableNuevamente=montoBonificableNuevamente-aporteBienestar;
		CalculoBonificacionVO calculoBonificacion = new CalculoBonificacionVO();
		calculoBonificacion.setAporteBienestar(aporteBienestar);
		calculoBonificacion.setMontoBonificableNuevamente(montoBonificableNuevamente);
		logger.debug("******** Cobertura: "+parametros.getCobertura().getCodigo());
		logger.debug("******** aporteBienestar: "+aporteBienestar);
		logger.debug("******** montoBonificableNuevamente: "+montoBonificableNuevamente);
		return calculoBonificacion;
		
	}
	
	/**
	 * Registra una inconsistencia e los aportes de bienestar
	 * En caso que exista una excepcion por clave duplicada la captura y no informa nada
	 * la idea es no insertar información repetida pero basat con que la BD reclame por clave duplicada 
	 * y aquí se captura la exception
	 * @param parametros
	 */
	private void registrarInconsistenciaAportesBienestar(ParametrosBonificacionCoberturaVO parametros){
		
		DatosInconsistenciaVO datosInconsistenciaVO = new DatosInconsistenciaVO(); 
		Calendar fecha = Calendar.getInstance();
		fecha.setTime(parametros.getFechaDeOcurrencia());

		datosInconsistenciaVO.setRutSocio(parametros.getRutSocio());
		datosInconsistenciaVO.setCodigoCobertura(parametros.getCobertura().getCodigo());
		datosInconsistenciaVO.setTipoTope(parametros.getCobertura().getTipoTope());
		if(datosInconsistenciaVO.getTipoTope().equals(Cobertura.TOPE_ANUALBENEFICIARIO) || datosInconsistenciaVO.getTipoTope().equals(Cobertura.TOPE_ANUALGRUPO))
			datosInconsistenciaVO.setMes(0);
		else
			datosInconsistenciaVO.setMes(fecha.get(Calendar.MONTH));
		
		datosInconsistenciaVO.setAnio(fecha.get(Calendar.YEAR));
		datosInconsistenciaVO.setFecha(new java.sql.Timestamp((new Date()).getTime()));
		
		try{
			bonificacionesDao.insertInconsistenciaAportesBienestar(datosInconsistenciaVO);
		}catch(Exception e){
						
		}
	}

	/**
	 * Calcula la bonificacion de un caso (sólo en memoria)
	 * @param casoId
	 * @return Caso con informacion de la bonificacion realizada
	 * @throws Exception
	 */
	public CasoVO calcularBonificacionCaso(long casoId, boolean simulando) throws Exception, BusinessException {
		
		CasoVO casoVO = new CasoVO();
		double aporteSocioTotalCaso = 0;
		double aporteConvenioTotalCaso=0;
		double aporteBienestarTotalCaso = 0;
		double aporteIsapreTotalCaso = 0;
		double montoTotalCaso = 0;
		double montoDescuentoTotalCaso = 0;
		ArrayList listaCoberturasAdicionales = new ArrayList();
		
		String  inicioAnioBienestar = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/inicio-agno-bienestar");
		
		int diaInicioBienestar =
			Integer.parseInt(inicioAnioBienestar.substring(0, 2));
		int mesInicioBienestar =
			Integer.parseInt(inicioAnioBienestar.substring(3, 5));
		
		logger.debug("diaInicioBienestar: " + diaInicioBienestar);
		logger.debug("mesInicioBienestar" + mesInicioBienestar);
		
		if (casoId == 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
			   "El Caso Id es incorrecto: " + casoId);
		else {
			//Obtiene informacion del caso (CasoVO) desde la BD
			logger.debug("Consulta Caso en BD, Caso Id: " + casoId);
			casoVO = bonificacionesDao.getCasoVO(casoId);
			if (casoVO.getCasoID() == 0 )
				throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
							   "No se pudo Obtener información del Caso: " + casoId);
		}

		/*
		 * Valida el estado del Caso:
		 * Estado = ACTIVO
		 * indicador de bonificacion = (NO IMPORTA EL ESTADO DE ESTE INDICADOR)
		 * indicador de reembolso = no
		 * indicador de descuento = no
		 * indicador de pago = no
		 */
		if (!simulando && !casoVO.getEstado().equals(Caso.STD_ACTIVO))
				throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"El estado del Caso: " + casoId + " es invalido");
					
		if (!simulando && !casoVO.getIndicadorReembolso().equals(Caso.ESTADOINDICADOR_NO))
				throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"El Caso: " + casoId + " está inconsistente");
			   		
		if (!simulando && !casoVO.getIndicadorDescontado().equals(Caso.ESTADOINDICADOR_NO))
				throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"El Caso: " + casoId + " no se puede rebonificar");
		
		if (!simulando && !casoVO.getIndicadorPago().equals(Caso.ESTADOINDICADOR_NO))
				throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"El Caso: " + casoId + " está inconsistente");
		 
		//Obtiene los detalles del caso desde la BD
		logger.debug("Consulta Detalles del Caso en BD");
		casoVO.setDetalleCaso(bonificacionesDao.getDetallesCaso(casoVO.getCasoID()));
		
		GregorianCalendar fecOcu = new GregorianCalendar();
		fecOcu.setTime(new Date(casoVO.getFechaDeOcurrencia().getTime()));
		
		GregorianCalendar fechaInicio = new GregorianCalendar();
		//Aqui seteo el año de ocurrencia, luego el dia y mes de "Año bienestar"
		//Aqui seteo el año de ocurrencia, luego el dia y mes de "Año bienestar" 
		
		fechaInicio.setTime(new Date(casoVO.getFechaDeOcurrencia().getTime()));
		
		int mesOcurrencia = fechaInicio.get(GregorianCalendar.MONTH);
		logger.debug("mesOcurrencia: " + mesOcurrencia);
		logger.debug("mesInicioBienestar: " + mesInicioBienestar);
		 
		fechaInicio.set(GregorianCalendar.DAY_OF_MONTH,diaInicioBienestar);
		fechaInicio.set(GregorianCalendar.MONTH,mesInicioBienestar);
		GregorianCalendar fechaFin = new GregorianCalendar();
		fechaFin.setTime(new Date(casoVO.getFechaDeOcurrencia().getTime()));
		fechaFin.set(GregorianCalendar.DAY_OF_MONTH,diaInicioBienestar);
		fechaFin.set(GregorianCalendar.MONTH,mesInicioBienestar);		
		if(mesOcurrencia < mesInicioBienestar){
			fechaInicio.set(GregorianCalendar.YEAR,fechaInicio.get(GregorianCalendar.YEAR)-1);

			fechaFin.set(GregorianCalendar.YEAR,fechaFin.get(GregorianCalendar.YEAR));
		}else{
			fechaFin.set(GregorianCalendar.YEAR,fechaFin.get(GregorianCalendar.YEAR)+1);			
		}
		
		logger.debug("Fecha Inicio: " + (Date)fechaInicio.getTime());
		logger.debug("Fecha Fin: " + (Date)fechaFin.getTime());
				
		if (casoVO.getDetalleCaso().size() == 0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
				"El Caso: " + casoId + " No tiene detalles que bonificar");
		
		//Por cada detalle Caso
		for (int x=0; x < casoVO.getDetalleCaso().size(); x++) {
			DetalleCaso detalleCaso = (DetalleCaso) casoVO.getDetalleCaso().get(x);
			
			AgrupacionCobertura agrupacionCobertura = bonificacionesDao.getRelacionAgrupacionCobertura(detalleCaso.getProducto().getCobertura().getCodigo());
			Cobertura coberturaMaestra = null;
			if(agrupacionCobertura!=null) {
				logger.debug("Cod Cob Maestra: "+agrupacionCobertura.getCodigoCoberturaMaestra());
				coberturaMaestra = bonificacionesDao.getCobertura(agrupacionCobertura.getCodigoCoberturaMaestra());
				detalleCaso.getProducto().getCobertura().setCoberturaAdicional(bonificacionesDao.getRelacionCoberturaAdicional(coberturaMaestra.getCodigo()));
									
			}else {
				detalleCaso.getProducto().getCobertura().setCoberturaAdicional(bonificacionesDao.getRelacionCoberturaAdicional(detalleCaso.getProducto().getCobertura().getCodigo()));			
			}
			
			int montoPorBonificar = 0;
			int aporteBienestar = 0;
			int aporteSocio = 0;
			int aporteConvenio=0;
			int aporteBienestarAdicional=0;
			ArrayList listaAportesCoberturas = new ArrayList();
			
			montoPorBonificar =
				(int) (
					detalleCaso.getMonto()
					- detalleCaso.getMontoDescuento()
					- detalleCaso.getAporteIsapre()
					)/detalleCaso.getCantidadOcurencias(); 
				
			logger.debug("Ingreso Socio: " + casoVO.getFecIngSocio());
			logger.debug("Meses Socio: " + mesesSocio(casoVO.getFecIngSocio()));
			logger.debug("Cobertura: " + detalleCaso.getProducto().getCobertura().getCodigo());
			logger.debug("Periodo Carencia: " + detalleCaso.getProducto().getCobertura().getPeriodoCarencia());
			logger.debug("montoPorBonificar: " + montoPorBonificar);
							
			//Validar si el socio está en periodo de carencia
			if (mesesSocio(casoVO.getFecIngSocio()) < detalleCaso.getProducto().getCobertura().getPeriodoCarencia())
				//esta en periodo de carencia, entonces el aporte de bienestar es cero
				aporteBienestar = 0;	
			else {			
				/*
				 * Si el caso esta con indicador de bonificacion "SI", entonces estoy
				 * rebonificando, por lo tanto, no debo considerarlo en los aportes
				 * previos de bienestar en el periodo
				 */
				long casoIdNoConsiderado = 0;
				if (casoVO.getIndicadorBonificacion().equals(Caso.ESTADOINDICADOR_SI))
					casoIdNoConsiderado = casoVO.getCasoID();
					
				
				/*
				 * Como el producto puede estar más de una vez en el mismo caso,
				 * debo verificar si ya se ha bonificado en este mismo calculo 
				 * en memoria previamente
				 */
				double aporteBienestarPrevioMismoCaso = 0;
				for (int y=0; y < x; y++) {
					DetalleCaso detalleRevision = (DetalleCaso) casoVO.getDetalleCaso().get(y);
					if (detalleRevision.getProducto().getCobertura().getCodigo() ==  ((DetalleCaso) casoVO.getDetalleCaso().get(x)).getProducto().getCobertura().getCodigo()) {
						aporteBienestarPrevioMismoCaso = aporteBienestarPrevioMismoCaso + detalleRevision.getAporteBienestar();
					}
				}
				logger.debug("Aporte Bienestar Previo Mismo Caso: " +aporteBienestarPrevioMismoCaso);
				
				//No esta en periodo de carencia o el periodo de carencia es cero
				ParametrosBonificacionCoberturaVO param = new ParametrosBonificacionCoberturaVO();
				param.setCasoId(casoId);
				param.setFechaDeOcurrencia(casoVO.getFechaDeOcurrencia());
				param.setFechaInicio((Date)fechaInicio.getTime());
				param.setFechaFin((Date)fechaFin.getTime());
				param.setCasoIdNoConsiderado(casoIdNoConsiderado);
				param.setRutSocio(casoVO.getRutSocio());
				param.setRutCarga(casoVO.getRutCarga());
				param.setAporteBienestarPrevioMismoCaso(aporteBienestarPrevioMismoCaso);
				param.setCodigoConvenio(casoVO.getCodigoConvenio());
				param.setMonto((int)detalleCaso.getMonto()/detalleCaso.getCantidadOcurencias());
				param.setMontoDescuento((int)detalleCaso.getMontoDescuento()/detalleCaso.getCantidadOcurencias());
				param.setMontoAporteIsapre((int)detalleCaso.getAporteIsapre()/detalleCaso.getCantidadOcurencias());
				
				if(coberturaMaestra!=null) {
					param.setCobertura(coberturaMaestra);					
				}
				else {
					param.setCobertura(detalleCaso.getProducto().getCobertura());
				}
				
				CalculoBonificacionVO calculoBonificacion = calcularBonificacionCobertura(param);
						 
				aporteBienestar = calculoBonificacion.getAporteBienestar();
				logger.debug("AporteBienestar Cobertura Directa: "+aporteBienestar);
				
				AporteCobertura aporte = new AporteCobertura();
				aporte.setAporteBienestar(aporteBienestar);
				aporte.setCasoID(casoId);
				aporte.setCodigoCobertura(detalleCaso.getProducto().getCobertura().getCodigo());
				//aporte.setCodigoCobertura(param.getCobertura().getCodigo());

				aporte.setIdDetalle(detalleCaso.getIdDetalle());
				
				listaAportesCoberturas.add(aporte);
					
				aporteSocio = montoPorBonificar - aporteBienestar;
				
				
				int montoBonificableNuevamente=calculoBonificacion.getMontoBonificableNuevamente();
				logger.debug("montoBonificableNuevamente: "+montoBonificableNuevamente);
				logger.debug("Tiene: "+detalleCaso.getProducto().getCobertura().getCoberturaAdicional().size()+" Coberturas Adicionales");
				
				if (montoBonificableNuevamente>0 &&
					detalleCaso.getProducto().getCobertura().getCoberturaAdicional().size()>0) {
					// Como el montoBonificableNuevamente > 0 entonces debe verificar si tiene cobertura adicional
					for (int a=0;a< detalleCaso.getProducto().getCobertura().getCoberturaAdicional().size();a++) {
						if (montoBonificableNuevamente>0) {
							/*
							 * Como la cobertura adicional puede estar más de una vez en el mismo caso,
							 * debo verificar si ya se ha bonificado en este mismo calculo 
							 * en memoria previamente
							 */
							int aporteBienestarAdicionalPrevioMismoCaso=0;	
				
							for(int z=0;z<listaCoberturasAdicionales.size();z++){
								AporteCobertura aporteAdicional = (AporteCobertura)listaCoberturasAdicionales.get(z);
								if(aporteAdicional.getCodigoCobertura() == ((CoberturaAdicional)detalleCaso.getProducto().getCobertura().getCoberturaAdicional().get(a)).getCodigoCoberturaAdicional()) {
									aporteBienestarAdicionalPrevioMismoCaso=aporteBienestarAdicionalPrevioMismoCaso+aporteAdicional.getAporteBienestar();
								}
							}
						
							param.setAporteBienestarPrevioMismoCaso(aporteBienestarAdicionalPrevioMismoCaso);
							param.setMonto(montoBonificableNuevamente);
							param.setMontoDescuento(0);
							param.setMontoAporteIsapre(0);
							
//							if(coberturaMaestra!=null) {
//								Cobertura cob = bonificacionesDao.getCobertura(agrupacionCobertura.getCodigoCoberturaMaestra());
//								
//								param.setCobertura(cob);
//								
//							}else {
								param.setCobertura(bonificacionesDao.getCobertura(((CoberturaAdicional)detalleCaso.getProducto().getCobertura().getCoberturaAdicional().get(a)).getCodigoCoberturaAdicional()));
//							}
						
							//SE CALCULA LA COBERTURA ADICIONAL.
							CalculoBonificacionVO calculoBonificacionAdicional=calcularBonificacionCobertura(param);

							aporteBienestarAdicional = calculoBonificacionAdicional.getAporteBienestar();
							montoBonificableNuevamente=calculoBonificacionAdicional.getMontoBonificableNuevamente();
						
							if (aporteBienestarAdicional>0) {						
								logger.debug("aporteBienestarAdicional: "+aporteBienestarAdicional);
								AporteCobertura aporteAdicional = new AporteCobertura();
								aporteAdicional.setAporteBienestar(aporteBienestarAdicional);
								aporteAdicional.setCasoID(casoId);
								aporteAdicional.setCodigoCobertura(param.getCobertura().getCodigo());
								aporteAdicional.setIdDetalle(detalleCaso.getIdDetalle());
								listaCoberturasAdicionales.add(aporteAdicional);
								listaAportesCoberturas.add(aporteAdicional);
							}
						}
					}
				}
		
			}
			
			((DetalleCaso) casoVO.getDetalleCaso().get(x)).setAporteCobertura(listaAportesCoberturas);
			aporteSocio = montoPorBonificar - aporteBienestar - aporteBienestarAdicional;			
			
			int porcentajeAporteConvenio=((DetalleCaso) casoVO.getDetalleCaso().get(x)).getProducto().getPorcentajeAporteConvenio();				
			if(porcentajeAporteConvenio>0) {
				aporteConvenio = montoPorBonificar * porcentajeAporteConvenio / 100;						
				aporteSocio = aporteSocio - aporteConvenio;
				if(aporteSocio==1){ //Por temas de redondeo da un peso a pagar por el socio, por eso
									//ese peso se le agrega al convenio y se le resta a la deuda del socio
					aporteConvenio=aporteConvenio+1;
					aporteSocio=0;
				}
			}
					
			logger.debug("Aporte Bienestar: "+aporteBienestar);
			logger.debug("Cantidad Ocu: "+(((DetalleCaso) casoVO.getDetalleCaso().get(x)).getCantidadOcurencias()));
			((DetalleCaso) casoVO.getDetalleCaso().get(x)).setAporteBienestar(aporteBienestar*(((DetalleCaso) casoVO.getDetalleCaso().get(x)).getCantidadOcurencias()));			
			((DetalleCaso) casoVO.getDetalleCaso().get(x)).setAporteSocio(aporteSocio*(((DetalleCaso) casoVO.getDetalleCaso().get(x)).getCantidadOcurencias()));
			((DetalleCaso) casoVO.getDetalleCaso().get(x)).setAporteConvenio(aporteConvenio*(((DetalleCaso) casoVO.getDetalleCaso().get(x)).getCantidadOcurencias()));
			logger.debug("Aporte Total: "+((DetalleCaso) casoVO.getDetalleCaso().get(x)).getAporteBienestar());
			
			aporteBienestarTotalCaso = aporteBienestarTotalCaso + ((aporteBienestar + aporteBienestarAdicional)*((DetalleCaso) casoVO.getDetalleCaso().get(x)).getCantidadOcurencias());
			aporteSocioTotalCaso = aporteSocioTotalCaso + (aporteSocio*((DetalleCaso) casoVO.getDetalleCaso().get(x)).getCantidadOcurencias());
			aporteConvenioTotalCaso = aporteConvenioTotalCaso + (aporteConvenio*((DetalleCaso) casoVO.getDetalleCaso().get(x)).getCantidadOcurencias());
			aporteIsapreTotalCaso = aporteIsapreTotalCaso + ((DetalleCaso) casoVO.getDetalleCaso().get(x)).getAporteIsapre();
			montoTotalCaso = montoTotalCaso + ((DetalleCaso) casoVO.getDetalleCaso().get(x)).getMonto();  
			montoDescuentoTotalCaso = montoDescuentoTotalCaso + ((DetalleCaso) casoVO.getDetalleCaso().get(x)).getMontoDescuento();
			
			logger.debug("Siguiente Detalle.");
		}

		for (int x=0; x < casoVO.getDetalleCaso().size(); x++) {
			DetalleCaso detalleCaso = (DetalleCaso) casoVO.getDetalleCaso().get(x);
			int aporteBienestarTotalDetalle=0;
			for (int i=0; i <detalleCaso.getAporteCobertura().size(); i++) {
				AporteCobertura aporte = (AporteCobertura)detalleCaso.getAporteCobertura().get(i);
				aporteBienestarTotalDetalle=aporteBienestarTotalDetalle+(aporte.getAporteBienestar()*detalleCaso.getCantidadOcurencias());
			}
			detalleCaso.setAporteBienestar(aporteBienestarTotalDetalle);
		}
		
		casoVO.setAporteIsapre(aporteIsapreTotalCaso);
		casoVO.setMonto(montoTotalCaso);
		casoVO.setMontoDescuento(montoDescuentoTotalCaso);
		casoVO.setAporteBienestar(aporteBienestarTotalCaso);
		casoVO.setAporteSocio(aporteSocioTotalCaso);
		casoVO.setAporteConvenio(aporteConvenioTotalCaso);
		casoVO.setIndicadorBonificacion(Caso.ESTADOINDICADOR_SI);
		
		//Verifico si el caso es en cuotas
		if (casoVO.getCuotasBienestar() > 1 || casoVO.getCuotasConvenio() > 1) {
			int numeroCuotas = 0;
			
			numeroCuotas = casoVO.getCuotasBienestar() > 1 ? casoVO.getCuotasBienestar(): casoVO.getCuotasConvenio();
				
			//Genero nuevas cuotas
			casoVO.setCuota(generarCuotas(aporteSocioTotalCaso,numeroCuotas));	
		}
		logger.debug("Se generaron: " + casoVO.getCuota().size() + " cuotas");
		return casoVO; 
	}
	
	/**
	 * Calcula la bonificacion de un caso
	 * actualiza la información del caso bonificado
	 * @param casoId
	 * @return
	 * @throws Exception
	 */
	public void bonificarCaso(long casoId) throws Exception, BusinessException {
		
		if ( casoId > 0) {
			CasoVO casoVo = bonificacionesDao.getCasoVO(casoId);
			if (!casoVo.getEstado().equals(Caso.STD_ACTIVO))
				throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
					"El estado del Caso Id: " + casoId + "  es incorrecto");
		
			
			ArrayList caso = new ArrayList();
			caso.add(new Long(casoId));
			bonificar(caso);
		}
		else
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
				"El caso Id : " + casoId + " es incorrecto");
		
	}

	

	
	/**
	 * Calcula la bonificacion de los casos solicitados
	 * actualiza la información de los casos bonificados
	 * @param ArrayList con casoId
	 * @return
	 * @throws Exception
	 */
	public void bonificar(ArrayList casos) throws Exception, BusinessException {
		//calcularBonificacionCaso
		long casoId = 0;
		
		logger.debug(">> bonificar");
		
		if (casos == null)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
				"La información es nula");
				
		
		for (int x=0; x < casos.size(); x++) {
			logger.debug("Dato: " + x + " Valor: " + casos.get(x));
			casoId = ((Long)casos.get(x)).longValue();
			logger.debug("Caso Id: " + casoId);
			if (casoId > 0) {
				logger.debug("Inicia Calculo Bonificacion, Caso Id: " + casoId);
				Caso caso = calcularBonificacionCaso(casoId, false);
				logger.debug("Finaliza Calculo Bonificacion, Caso Id: " + casoId);
				
				bonificacionesDao.deleteAporteCobertura(caso.getCasoID());
				
				// No TX. this.mySessionCtx.getRollbackOnly();
				
				logger.debug("Borra aportes Bienestar");
				//Actualizo detalle Caso
				for (int y = 0; y < caso.getDetalleCaso().size(); y++) {
					DetalleCaso detalle = (DetalleCaso)caso.getDetalleCaso().get(y);
					bonificacionesDao.updateDetalle(caso.getCasoID(),detalle);
					logger.debug("Actualiza Detalle: " + detalle.getIdDetalle());
					for (int z=0;z<detalle.getAporteCobertura().size();z++){
						AporteCobertura aporteCobertura = (AporteCobertura)detalle.getAporteCobertura().get(z);
						aporteCobertura.setAporteBienestar(aporteCobertura.getAporteBienestar()*detalle.getCantidadOcurencias());
						if (aporteCobertura.getAporteBienestar()>0) {
							bonificacionesDao.registraAporteCobertura(aporteCobertura);
							logger.debug("Crea aporte Bienestar: " + detalle.getIdDetalle());
						}
					}
				}
				
				//Actualizo Caso
				logger.debug("Actualizando, Caso Id: " + casoId);
				bonificacionesDao.updateCaso(caso);
				logger.debug("Finaliza actualización, Caso Id: " + casoId);
				
				//Genero Cuotas
				if (caso.getCuota().size() > 1) {
					logger.debug("Elimina Cuotas previas");
					bonificacionesDao.deleteCuotasCaso(caso.getCasoID());
					
					for (int y = 0; y < caso.getCuota().size(); y++) {
						logger.debug("Creando Cuota: " + y + " para el Caso Id: " + casoId);
						bonificacionesDao.insertCuotaCaso(caso.getCasoID(),(Cuota)caso.getCuota().get(y));
					}
					logger.debug("Finaliza creacion de cuotas para el Caso Id: " + casoId);
				}
			}
		}
		
		logger.debug("<< bonificar");
	}
	
	
	
	/**
	 * Recupera la lista de topes y disponibles que un Socio tiene. El método se
	 * encarga de recuperar los Casos del Beneficiario y la Definición de
	 * Coberturas de Bienestar, agrupando la información y definiendo, por cada
	 * cobertura, cuales son las sumatorias de los montos utilizados y los topes
	 * que le quedan a dicho Beneficiario en la Cobertura. El método considera
	 * solo los tipo de topes que tiene sentido. Esto es: Anual por Familia,
	 * Mensual por Familia y Anual por Beneficiario.
	 * @param ParamResumenMovimientosVO parametros
	 * @param ResumenMovimientosBeneficiarioVO resumenFiltro 
	 */
	public ArrayList resumenMovimientosBeneficiario(ParamResumenMovimientosVO parametros, ResumenMovimientosBeneficiarioVO resumenFiltro) throws Exception, BusinessException {
				
		Date fechaInicio = parametros.getFechaInicio();
		Date fechaFin = parametros.getFechaFin();
		logger.debug("Fecha Inicio: "+ fechaInicio);
		logger.debug("Fecha Fin: "+fechaFin);
		
		GregorianCalendar fecha = new GregorianCalendar();
		GregorianCalendar fechaPaso = new GregorianCalendar();
		fecha.setTime(fechaFin);
		fechaPaso.set(fecha.get(Calendar.YEAR),fecha.get(Calendar.MONTH),1);	
		Date fechaInicioMensual = fechaPaso.getTime();
		logger.debug("Fecha Inicio Mensual: "+fechaInicioMensual);
		fechaPaso.set(fecha.get(Calendar.YEAR),fecha.get(Calendar.MONTH)+1,1);
		fechaPaso.add(Calendar.DAY_OF_MONTH,-1);
		Date fechaFinMensual = fechaPaso.getTime();
		logger.debug("Fecha Fin Mensual: "+fechaFinMensual);
		
		//Recupero Coberturas Activas
		Cobertura coberturaFiltro = new Cobertura();
		coberturaFiltro.setEstado(Cobertura.STD_ACTIVO);
		coberturaFiltro.setDescripcion(resumenFiltro.getNombreCobertura());
		ArrayList listaCoberturas =
			bonificacionesDao.getCoberturas(coberturaFiltro);
		logger.debug("Cantidad: " + listaCoberturas.size());
			
		if (parametros.getRutSocio() != null)
			logger.debug("Rut Socio: " + parametros.getRutSocio());
		else
			logger.debug("Rut Socio: null");
		
		if (parametros.getRutCarga() != null)
			logger.debug("Rut Carga: " + parametros.getRutCarga());
		else
			logger.debug("Rut Carga: null");
				
		ArrayList resultado = new ArrayList();

		for (int x = 0; x < listaCoberturas.size(); x++) {
			double aporteBienestarAcumulado = 0;
			Cobertura cobertura = (Cobertura) listaCoberturas.get(x);
			//Solo se consideran las coberturas de tipo:
			//ANUALBENEFICIARIO, ANUALGRUPO, MENSUALGRUPO 
			if (cobertura
				.getTipoTope()
				.equals(Cobertura.TOPE_ANUALBENEFICIARIO)
				|| cobertura.getTipoTope().equals(Cobertura.TOPE_ANUALGRUPO)
				|| cobertura.getTipoTope().equals(Cobertura.TOPE_MENSUALGRUPO)) {
					
				if (cobertura.getTipoTope().equals(resumenFiltro.getTipoTope())
					|| resumenFiltro.getTipoTope().equals("")) {
					
					Date fechaInicioBusqueda=null;
					Date fechaFinBusqueda=null;
					if(cobertura.getTipoTope().equals(Cobertura.TOPE_MENSUALGRUPO)){
						fechaInicioBusqueda=fechaInicioMensual;
						fechaFinBusqueda=fechaFinMensual;						
					}else{
						fechaInicioBusqueda=fechaInicio;
						fechaFinBusqueda=fechaFin;									
					}
					
					aporteBienestarAcumulado =
						bonificacionesDao.calculaAporteBienestar(
							parametros.getRutSocio(),
							parametros.getRutCarga(),
							cobertura.getTipoTope(),
							cobertura.getCodigo(),
							fechaInicioBusqueda,
							fechaFinBusqueda,
							0);
					ResumenMovimientosBeneficiarioVO resumenMovimientos = new ResumenMovimientosBeneficiarioVO();
					resumenMovimientos.setAporteBienestarAcumulado(aporteBienestarAcumulado);
					resumenMovimientos.setCodigoCobertura(cobertura.getCodigo());
					resumenMovimientos.setNombreCobertura(cobertura.getDescripcion());
					resumenMovimientos.setTipoTope(cobertura.getTipoTope());
					resumenMovimientos.setTope(cobertura.getTope());
					resumenMovimientos.setValorReferencial(cobertura.getValorReferencial());
					
					resultado.add(resumenMovimientos);
					
				}
			}
		}

		return resultado;
		
	}
	
	
	/**
	 * Con la información de los Casos asociados al Convenio, genera información
	 * de los aportes que a debido realizar Bienestar por la utilización del
	 * Convenio por parte de los Socios
	 * Genera Sumas de los aportes de Bienestar, de la Isapre, de los descuentos
	 * y de los aportes de los Socios  para las distintas coberturas  (productos)
	 * que tiene el Convenio.
	 * @param ParamResumenMovimientosVO
	 * @param ResumenMovimientosConvenioVO
	 */
	public ArrayList resumenMovimientosConvenio(ParamResumenMovimientosVO parametros, ResumenMovimientosConvenioVO resumenFiltro) throws Exception, BusinessException {
				
		if(parametros.getFechaInicio()==null)
			throw new BusinessException("CCAF_BONIF_DATOSINCORRECTOS",
				"La fecha de inicio de busqueda no puede ser nula");
				
		if(parametros.getFechaFin()==null)
			throw new BusinessException("CCAF_BONIF_DATOSINCORRECTOS",
				"La fecha de fin de busqueda no puede ser nula");
						
		if(parametros.getFechaInicio().after(parametros.getFechaFin()))
			throw new BusinessException("CCAF_BONIF_DATOSINCORRECTOS",
					"La fecha de inicio no puede ser mayor que la fecha de fin");
			
		Date fechaInicio = parametros.getFechaInicio();
		Date fechaFin = parametros.getFechaFin();
		
		return bonificacionesDao.movimientosConvenio(parametros.getCodigoConvenio(),fechaInicio,fechaFin,resumenFiltro);

	}
	
	/**
	 * Actualiza los casos con estado e indicador de reembolso 
	 * @param reembolsos
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void actualizaIndicadorReembolso(ArrayList reembolsos) throws Exception, BusinessException {
		
		String estadoPrevio = Caso.STD_ACTIVO;
		String indicadorPrevio = Caso.ESTADOINDICADOR_NO;

		int filasActualizadas=0;
		
		for (int x=0; x < reembolsos.size(); x++) {
			ReembolsoVO casoReembolso = new ReembolsoVO();
			casoReembolso = (ReembolsoVO)reembolsos.get(x);
			if (casoReembolso.getCasoId() > 0) {				
				//Actualiza indicadores del Caso
				casoReembolso.setEstado(Caso.STD_CERRADO);
				casoReembolso.setFechaEstado(new Date());
				casoReembolso.setIndicadorReembolso(Caso.ESTADOINDICADOR_SI);
				filasActualizadas = bonificacionesDao.updateIndicadorReembolso(casoReembolso, estadoPrevio, indicadorPrevio);
				if (filasActualizadas==0)
					throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
						"El Estado del Caso ID: " + casoReembolso.getCasoId() + " no es Correcto");
			}
		}
	}
	
	/**
	 * Actualiza los casos con estado e indicador de descuento
	 * Actualiza estado de las cuotas en caso de existir 
	 * @param descuentos
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void actualizaIndicadorDescuento(ArrayList descuentos) throws Exception, BusinessException {
			
		for (int x=0; x < descuentos.size(); x++) {
			DescuentosVO casoDescuento = (DescuentosVO)descuentos.get(x); 
			if (casoDescuento.getCasoId() > 0) {
				String indicadorPrevio = casoDescuento.getIndicadorDescuento();
				String estadoPrevio = casoDescuento.getEstado();				
				//El caso es en cuotas
				logger.debug("Cuotas Bienestar: "+casoDescuento.getNumeroCuotasBienestar());
				logger.debug("Cuotas Convenio: "+casoDescuento.getNumeroCuotasConvenio());
				logger.debug("Cuota: "+casoDescuento.getCuotaActual());
				logger.debug("Cuotas: "+casoDescuento.getNumeroCuotas());
				
				if (casoDescuento.getNumeroCuotas() > 1) {
					if (casoDescuento.getCuotaActual() == casoDescuento.getNumeroCuotas()) {
						//Es la última Cuota
						logger.debug("Es la ultima cuota");
						casoDescuento.setIndicadorDescuento(Caso.ESTADOINDICADOR_SI);
						if (!casoDescuento.getTipoBono().equals(Caso.TIPOBONO_NO)) {
							casoDescuento.setEstado(Caso.STD_CERRADO);
							casoDescuento.setFechaEstado(new Date());
						}		
					}		
					else{
						//No es la última Cuota
						logger.debug("No es la ultima cuota");
						casoDescuento.setIndicadorDescuento(Caso.ESTADOINDICADOR_PROCESO);
					}
					
					//Cambio el Estado de la Cuota ->cuotaDescontada
					Cuota cuota = new Cuota();
					cuota.setCuotaEstado(Cuota.STD_DESCONTADA);
					cuota.setCuotaNumero(casoDescuento.getCuotaActual());
					bonificacionesDao.updateCuotaCaso(casoDescuento.getCasoId(),cuota);	
				} else {
					//El caso es sin cuotas
					logger.debug("Es sin cuotas");
					casoDescuento.setIndicadorDescuento(Caso.ESTADOINDICADOR_SI);
					if (!casoDescuento.getTipoBono().equals(Caso.TIPOBONO_NO)) {
						casoDescuento.setEstado(Caso.STD_CERRADO);
						casoDescuento.setFechaEstado(new Date());
					}
				}
					
				//Actualiza indicadores del Caso 
				int filasActualizadas = bonificacionesDao.updateIndicadorDescuento(casoDescuento, indicadorPrevio,estadoPrevio);
				if (filasActualizadas==0)
					throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
						"El Estado del Caso ID: " + casoDescuento.getCasoId() + " no es Correcto");
			}
		}
	}

	/**
	 * Actualiza los casos (cerrandolos) y cada una de sus cuotas.
	 * Actualiza estado de las cuotas en caso de existir 
	 * @param descuentos
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void actualizaIndicadorDescuentoSaldoTotal(ArrayList descuentos) throws Exception, BusinessException {
			
		for (int x=0; x < descuentos.size(); x++) {
			DescuentosVO casoDescuento = (DescuentosVO)descuentos.get(x); 
			if (casoDescuento.getCasoId() > 0) {
				String indicadorPrevio = casoDescuento.getIndicadorDescuento();
				String estadoPrevio = casoDescuento.getEstado();				
				//El caso es en cuotas
				logger.debug("Cuotas Bienestar: "+casoDescuento.getNumeroCuotasBienestar());
				logger.debug("Cuotas Convenio: "+casoDescuento.getNumeroCuotasConvenio());
				logger.debug("Cuota: "+casoDescuento.getCuotaActual());
				logger.debug("Cuotas: "+casoDescuento.getNumeroCuotas());
				
				//Se agrupan todas las cuotas por pagar en una sola
				List listCuotasNoCobradas = (List) bonificacionesDao.getCuotasNoCobradas(casoDescuento.getCasoId());
				Iterator iterCuotasNoCobradas = listCuotasNoCobradas.iterator();
				
				double totalCuotaAcumulada = 0;
				while(iterCuotasNoCobradas.hasNext()){
						CuotaPendienteVO c = (CuotaPendienteVO) iterCuotasNoCobradas.next();
						totalCuotaAcumulada += c.getMonto();
						
				}
				
				bonificacionesDao.deleteCuotasCasoNoCobradas(casoDescuento.getCasoId());
				
				Cuota cuotaAcum = new Cuota();
				cuotaAcum.setValorCuota(totalCuotaAcumulada);
				cuotaAcum.setCuotaEstado(Cuota.STD_NO_DESCONTADA);
				cuotaAcum.setCuotaNumero(casoDescuento.getCuotaActual());
				cuotaAcum.setFechaVencimiento(casoDescuento.getFechaDescuento());//TODO: validar que sea la fecha de la cuota actual.
				bonificacionesDao.insertCuotaCaso(casoDescuento.getCasoId(),cuotaAcum);
				
				//se actualiza el caso (debo disminuir el numero de cuotas y dejarlo en el numero actual)
				int cantCuotasEliminadas = listCuotasNoCobradas.size();
			
				Caso casoBD = (Caso)bonificacionesDao.getCasoVO(casoDescuento.getCasoId());
				
				if(casoBD.getCuotasBienestar() > 0){
					//cuotas internas
					int cantCuotaBienestar = casoBD.getCuotasBienestar();
					//total cuotas - las no cobradas (recientemente eliminadas) + la nueva
					casoBD.setCuotasBienestar(cantCuotaBienestar - cantCuotasEliminadas + 1);
				}
				else if(casoBD.getCuotasConvenio() > 0){
					//cuotas externas
					int cantCuotasConvenio = casoBD.getCuotasConvenio();
//					total cuotas - las no cobradas (recientemente eliminadas) + la nueva
					casoBD.setCuotasConvenio(cantCuotasConvenio - cantCuotasEliminadas + 1);
				}

				bonificacionesDao.updateCaso(casoBD);
						
				//salda la deuda total
				casoDescuento.setIndicadorDescuento(Caso.ESTADOINDICADOR_SI);
				if (!casoDescuento.getTipoBono().equals(Caso.TIPOBONO_NO)) {//TODO evaluar impacto de tener esto asi
					casoDescuento.setEstado(Caso.STD_CERRADO);
					casoDescuento.setFechaEstado(new Date());
				}			
				
				//Cambio el Estado de la Cuota ->cuotaDescontada
				Cuota cuota = new Cuota();
				cuota.setCuotaEstado(Cuota.STD_DESCONTADA);
				cuota.setCuotaNumero(casoDescuento.getCuotaActual());
				bonificacionesDao.updateCuotaCaso(casoDescuento.getCasoId(),cuota);	
			
					
				//Actualiza indicadores del Caso 
				int filasActualizadas = bonificacionesDao.updateIndicadorDescuento(casoDescuento, indicadorPrevio,estadoPrevio);
				if (filasActualizadas==0)
					throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
						"El Estado del Caso ID: " + casoDescuento.getCasoId() + " no es Correcto");
			}
		}
	}
	
	/**
	 * Actualiza los casos con estado e indicador de pago
	 * Actualiza estado de las cuotas en caso de existir 
	 * @param pagos
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void actualizaIndicadorPago(ArrayList pagos) throws Exception, BusinessException {
			
		int filasActualizadas=0;
			
		for (int x=0; x < pagos.size(); x++) {
			PagoConvenioVO casoPago = new PagoConvenioVO();
			casoPago = (PagoConvenioVO)pagos.get(x);
			if (casoPago.getCasoId() > 0) {
				String indicadorPrevio = casoPago.getIndicadorPago();
				String estadoPrevio = casoPago.getEstado();	
				//El caso es en cuotas
				if (casoPago.getNumeroCuotas() >= 1) {//TODO: evaluar que implicancia tiene dejar >=1 (inicialmente estaba > 1)
					if (casoPago.getCuotaActual() == casoPago.getNumeroCuotas()) {
						//Es la última Cuota
						casoPago.setIndicadorPago(Caso.ESTADOINDICADOR_SI);
						casoPago.setEstado(Caso.STD_CERRADO);
						casoPago.setFechaEstado(new Date());
					} else {
						//No es la última Cuota
						casoPago.setIndicadorPago(Caso.ESTADOINDICADOR_PROCESO);
					}
					
					//Cambio el Estado de la Cuota ->cuotaPagada
					Cuota cuota = new Cuota();
					cuota.setCuotaEstado(Cuota.STD_PAGADA);
					cuota.setCuotaNumero(casoPago.getCuotaActual());
					bonificacionesDao.updateCuotaCaso(casoPago.getCasoId(),cuota);	
				} else {
					//El caso es sin cuotas o bien es pago anticipado (req 4353)
					casoPago.setIndicadorPago(Caso.ESTADOINDICADOR_SI);
					casoPago.setEstado(Caso.STD_CERRADO);
					casoPago.setFechaEstado(new Date());
				}
					
				//Actualiza indicadores del Caso 
				filasActualizadas = bonificacionesDao.updateIndicadorPago(casoPago, indicadorPrevio, estadoPrevio);
				if (filasActualizadas==0)
					throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
						"El Estado del Caso ID: " + casoPago.getCasoId() + " no es Correcto");
			}
		}
	}
	
	/**
	 * Cierra o Elimina los casos del rut pasado como parametro
	 * Actualiza estado y fecha de estado
	 * Genera evento automatico 
	 * @param rut socio
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void cierraCasosAbiertos(String rut) throws Exception, BusinessException {
		
		//Comentario cierre caso automatico
		String cometarioCasoCerrado = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/comentario-cierre-caso");
		
		//Comentario eliminacion caso automatico			  
		String cometarioCasoEliminado = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/comentario-eliminacion-caso");
					  
		//Usuario que Cierra o Elimina caso automaticamente			  
		String usuario = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
					  "/application-settings/bonificaciones/param/usuarior-comentario-caso");

		
		ArrayList casos = bonificacionesDao.getCasosAbiertos(rut);
		for (int x=0;x<casos.size();x++) {
			CasoAbiertoVO caso = (CasoAbiertoVO) casos.get(x);
			if (caso.getEstadoActual().equals(Caso.STD_ACTIVO)){
				Evento evento = new Evento();
				//Cierra caso que estaba activo
				caso.setEstadoNuevo(Caso.STD_CERRADO);
				caso.setFechaEstadoNuevo(new Date());
				bonificacionesDao.finalizaCaso(caso);
				//genera evento
				evento.setComentario(cometarioCasoCerrado);
				evento.setFechaIngreso(new Date());
				evento.setTipo(Evento.TIPO_AUTOMATICO);
				evento.setUsuario(usuario);
				registraEvento(caso.getCasoId(), evento);
				
			} else if (caso.getEstadoActual().equals(Caso.STD_BORRADOR)) {
				Evento evento = new Evento();
				//Elimina caso que estaba en borrador
				caso.setEstadoNuevo(Caso.STD_ELIMINADO);
				caso.setFechaEstadoNuevo(new Date());
				bonificacionesDao.finalizaCaso(caso);
				//genera evento
				evento.setComentario(cometarioCasoEliminado);
				evento.setFechaIngreso(new Date());
				evento.setTipo(Evento.TIPO_AUTOMATICO);
				evento.setUsuario(usuario);
				registraEvento(caso.getCasoId(),evento);
			}
		}
	}
	
	/**
	 * Crea casos en forma automática,
	 * a partir de un Collection de lineas producto de un archivo de carga
	 * @param InputUpLoadFileVO inputData
	 * @return ResultUpLoadFileVO, con el resultado del proceso completo
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ResultUpLoadFileVO updLoadFile(InputUpLoadFileVO inputData) throws Exception, BusinessException {
		
		ResultUpLoadFileVO resultUpLoadFileVo = new ResultUpLoadFileVO();
		resultUpLoadFileVo.setFechaProceso(new Date());
		resultUpLoadFileVo.setFileName(inputData.getFilename());
		resultUpLoadFileVo.setUsuario(inputData.getUser());
		resultUpLoadFileVo.setConvenio(inputData.getNombreConvenio());
		resultUpLoadFileVo.setProducto(inputData.getNombreProducto());
		
		Collection resultado = new ArrayList(); //ResultLineVO
		
		
		if (inputData.getCodigoConvenio()==0)
			throw new BusinessException("CCAF_BONIF_UPLOADFILE", "Debe especificar un Convenio");
			
		if (inputData.getCodigoProducto()==0)
			throw new BusinessException("CCAF_BONIF_UPLOADFILE", "Debe especificar un Producto");
			
		if (inputData.getFilename()==null || inputData.getFilename().length()==0)
			throw new BusinessException("CCAF_BONIF_UPLOADFILE", "Debe especificar un Archivo");

		if (inputData.getLines().size()==0)
			throw new BusinessException("CCAF_BONIF_UPLOADFILE", "El Archivo se encuentra vacio");

		if (inputData.getUser()==null || inputData.getUser().length()==0)
			throw new BusinessException("CCAF_BONIF_UPLOADFILE", "debe especificar el username");

		
		int numLine=0;
		for (Iterator i = inputData.getLines().iterator(); i.hasNext(); ) {
			String line = (String) i.next();
			numLine++;
			ResultLineVO resultLine = new ResultLineVO();
			String rut=null;
			String dv=null;
			String boleta=null;
			
			try {
				resultLine.setMensaje(ResultLineVO.MSG_OBSERVACIONES0);
				resultLine.setResultado(ResultLineVO.STD_NOK_CREADO);
				logger.debug("Antes de DataLine");
				DataLine dataLine = new DataLine(line,numLine);
				logger.debug("Despues de DataLine");
								
				rut=dataLine.getRut();
				dv=dataLine.getDv();
				boleta=dataLine.getBoleta();
				
				if(!dataLine.isRutValido()) {
					resultLine.setMensaje(ResultLineVO.MSG_RUT_INVALIDO);
					resultLine.setResultado(ResultLineVO.STD_NOK_CREADO);
				}else if(dataLine.getMonto()==0) {
					resultLine.setMensaje(ResultLineVO.MSG_MONTO_INVALIDO);
					resultLine.setResultado(ResultLineVO.STD_NOK_CREADO);
				}else if(dataLine.getBoleta()==null) {
					resultLine.setMensaje(ResultLineVO.MSG_BOLETA_INVALIDA);
					resultLine.setResultado(ResultLineVO.STD_NOK_CREADO);
				}else {
					Caso casoQuery = new Caso();
					casoQuery.setRutSocio(dataLine.getRut());
					casoQuery.setCodigoConvenio(inputData.getCodigoConvenio());
					casoQuery.setNumeroDocumento(dataLine.getBoleta());
					Caso casoExistente = bonificacionesDao.getCasoByUpLoadFile(casoQuery);

					if (casoExistente==null) {
						//No Existe el caso previamente
						Caso caso = new Caso();
						DetalleCaso detalleCaso = new DetalleCaso();
						Producto producto = new Producto();
						Cobertura cobertura = new Cobertura();
				
						// Prepara información del caso
						caso.setRutSocio(dataLine.getRut());
						caso.setCodigoConvenio(inputData.getCodigoConvenio());
						caso.setFechaDeOcurrencia(dataLine.getFechaOcurrencia());
						caso.setTipoCaso(Caso.TIPO_DESCUENTO);
						caso.setTipoDocumento(Caso.TIPO_DOCUMENTO_BOLETA);
						caso.setNumeroDocumento(dataLine.getBoleta());
						caso.setUsuario(inputData.getUser());
				
						// Prepara información del detalle
						cobertura.setCodigo(inputData.getCodigoProducto());
						producto.setCobertura(cobertura);		
						detalleCaso.setProducto(producto);
						detalleCaso.setMonto(dataLine.getMonto());
						detalleCaso.setDocumento(dataLine.getBoleta());
				
						// Crea caso
						logger.debug("Antes de registrar caso");
						caso.setCasoID(registraCaso(caso));
						logger.debug("Despues de registrar caso: "+caso.getCasoID());
						resultLine.setCasoId(caso.getCasoID());
						resultLine.setMensaje(ResultLineVO.MSG_OBSERVACIONES1);
				
						// Registra detalle del caso
						logger.debug("Antes de registrar detalle");
						caso = registraDetalle(caso,detalleCaso);
						logger.debug("Despues de registrar detalle");
						resultLine.setMensaje(ResultLineVO.MSG_OBSERVACIONES2);
				
						// Activa caso
						logger.debug("Antes de activar caso");
						activarCaso(caso.getCasoID());
						logger.debug("Despues de activar caso");

						resultLine.setMensaje(ResultLineVO.MSG_SIN_OBSERVACIONES);
						resultLine.setResultado(ResultLineVO.STD_OK_CREADO);
						

					} else {
						// El caso ya existía
						ArrayList detalles = bonificacionesDao.getDetallesCaso(casoExistente.getCasoID());
						if(casoExistente.getEstado().equals(Caso.STD_BORRADOR) && detalles.size()==0) {
							//El caso estaba en estado borrador, por lo tanto se puede reprocesar
							DetalleCaso detalleCaso = new DetalleCaso();
							Producto producto = new Producto();
							Cobertura cobertura = new Cobertura();
						
							// Prepara información del detalle
							cobertura.setCodigo(inputData.getCodigoProducto());
							producto.setCobertura(cobertura);		
							detalleCaso.setProducto(producto);
							detalleCaso.setMonto(dataLine.getMonto());
							detalleCaso.setDocumento(dataLine.getBoleta());
							
							// Registra detalle del caso
							logger.debug("Antes de registrar detalle");
							casoExistente = registraDetalle(casoExistente,detalleCaso);
							logger.debug("Despues de registrar detalle");
							resultLine.setMensaje(ResultLineVO.MSG_OBSERVACIONES2);
										
							// Activa caso
							logger.debug("Antes de activar caso");
							activarCaso(casoExistente.getCasoID());
							logger.debug("Despues de activar caso");

							resultLine.setMensaje(ResultLineVO.MSG_ACTUALIZADO);
							resultLine.setResultado(ResultLineVO.STD_OK_CREADO);
							resultLine.setCasoId(casoExistente.getCasoID());
						}else {
							//El caso ya existía en estado distinto a borrador y tenia detalles, por lo tanto no se puede reprocesar
							//casoExistente
							String mensaje = ResultLineVO.MSG_CREADO_POR_USUARIO + ": " + casoExistente.getUsuario();
							resultLine.setMensaje(mensaje);
							resultLine.setResultado(ResultLineVO.STD_YA_CREADO);
							resultLine.setCasoId(casoExistente.getCasoID());
						}
					}
				}
				resultLine.setBoleta(dataLine.getBoleta());
				resultLine.setFila(dataLine.getNumLine());
				resultLine.setRut(dataLine.getRut());
				resultLine.setDv(dataLine.getDv());
				
				resultado.add(resultLine);
				
			}
			catch (BusinessException be){
				resultLine.setMensaje(be.getMsgAdic());
				resultLine.setResultado(ResultLineVO.STD_NOK_CREADO);
				resultLine.setCasoId(0);
				resultLine.setBoleta(boleta);
				resultLine.setFila(numLine);
				resultLine.setRut(rut);
				resultLine.setDv(dv);
				resultado.add(resultLine);
			}
			catch (Exception e) {
				resultLine.setMensaje(e.getMessage());
				resultLine.setResultado(ResultLineVO.STD_NOK_CREADO);
				resultLine.setCasoId(0);
				resultLine.setBoleta(boleta);
				resultLine.setFila(numLine);
				resultLine.setRut(rut);
				resultLine.setDv(dv);
				resultado.add(resultLine);
			}		
		}
		
		resultUpLoadFileVo.setResultado(resultado);
		return resultUpLoadFileVo;
	}
	
	/** 
	 * Obtiene lista de pre-casos que tienen pendiente la generación del primer
	 * egreso en tesoreria
	 * @return ArrayList de CasoVO
	 * @throws Exception
	 */
	public ArrayList getListaPreCasosPorGenerarEgreso() throws Exception, BusinessException {
		
		Caso casoFiltro = new Caso();
		casoFiltro.setEstado(Caso.STD_PRECASO);
		casoFiltro.setIndicadorPreCaso(Caso.ESTADOINDICADOR_SI);
		casoFiltro.setIndicadorPreCasoEgresoGenerado(Caso.ESTADOINDICADOR_NO);
		casoFiltro.setIndicadorPreCasoIngresoGenerado(Caso.ESTADOINDICADOR_NO);
		return bonificacionesDao.getListaPreCasosByFiltro(casoFiltro);
	}
	
	/** 
	 * Cambia el estado de cada "pre-caso" (caso) desde el estado:
	 * STD_PRECASO a STD_BORRADOR
	 * @param ArrayList listaPreCasos
	 * @return void
	 * @throws Exception
	 */
	public void activarListaPreCasos(ArrayList listaPreCasos)  throws Exception, BusinessException {
		
		long casoId = 0;
		
		if (listaPreCasos == null)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
				"La información es nula");
				
		for (int x=0; x < listaPreCasos.size(); x++) {
			casoId = ((Long)listaPreCasos.get(x)).longValue();
			logger.debug("Dato: " + x + " Valor: " + casoId);
			if (casoId > 0) {
				cambiarABorradorCaso(casoId);
			}
		}
	}
	
	/**
	 * Genera los egresos en tesoreria correspondientes a los profesionales  
	 * @param DatosMovimientoTesoreriaVO
	 * @param UsuarioVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void registrarEgresoProfesionalesTesoreriaPreCaso(DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO, UsuarioVO user) throws Exception, BusinessException {
		
		if(datosMovimientoTesoreriaVO.getMontoTotalProfesionales()==0)
				throw new BusinessException("CCAF_BONIF_PROCESOINVALIDO",
						   "El monto del Egreso debe ser mayor que cero");		
		
		ArrayList listaProfesionales = datosMovimientoTesoreriaVO.getListaProfesionales();
		
		boolean sonVariosEnElMismoActo = listaProfesionales.size() >1 ? true : false;
		
		for(int x=0;x<listaProfesionales.size();x++){
			DatosProfesionalesVO datosProfesional = (DatosProfesionalesVO)listaProfesionales.get(x);
			DatosMovimientoTesoreriaVO datosMovimientoTesoreria = new DatosMovimientoTesoreriaVO();
			datosMovimientoTesoreria.setTipoPago(datosProfesional.getTipoPago());
			datosMovimientoTesoreria.setMonto(datosProfesional.getMonto());
			datosMovimientoTesoreria.setRut(datosProfesional.getRut());
			datosMovimientoTesoreria.setDigito(datosProfesional.getDigito());
			datosMovimientoTesoreria.setNombre(datosProfesional.getNombre());
			datosMovimientoTesoreria.setListaCasos(datosMovimientoTesoreriaVO.getListaCasos());
			datosMovimientoTesoreria.setDetalles(datosProfesional.getDetalles());			
		
			boolean validarEstadoPrevio=true;
			if(x>0)
				validarEstadoPrevio=false;
							
			registrarEgresoTesoreriaPreCaso(datosMovimientoTesoreria, user, validarEstadoPrevio, sonVariosEnElMismoActo);	
		}
	}
	
	
	/**
	 * Genera un Egreso en tesoreria 
	 * @param DatosMovimientoTesoreriaVO
	 * @param UsuarioVO
	 * @param boolean validarEstadoPrevio
	 * @param boolean sonVariosAlMismoCaso
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void registrarEgresoTesoreriaPreCaso(DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO, UsuarioVO user, boolean validarEstadoPrevio, boolean sonVariosEnElMismoActo) throws Exception, BusinessException {	
		
		//Servicio de TesoreriaEJB
		ServicesTesoreriaDelegate tesoreria = new ServicesTesoreriaDelegate();
		
		InfoMovimientoPreCasoVO infoMovimientoPreCasoVO = new InfoMovimientoPreCasoVO();
		infoMovimientoPreCasoVO.setRut(datosMovimientoTesoreriaVO.getRut());
		infoMovimientoPreCasoVO.setDigito(datosMovimientoTesoreriaVO.getDigito());
		infoMovimientoPreCasoVO.setNombre(datosMovimientoTesoreriaVO.getFullNombre());	
		infoMovimientoPreCasoVO.setFecha(new Date());
		infoMovimientoPreCasoVO.setMonto(datosMovimientoTesoreriaVO.getMonto());
		infoMovimientoPreCasoVO.setUsuario(user.getUsuario());
		infoMovimientoPreCasoVO.setTipoMovimiento(Constants.MOVI_EGRESO);
		infoMovimientoPreCasoVO.setDetalles(datosMovimientoTesoreriaVO.getDetalles());
		
		//Obtiene informacion del convenio y del concepto
		CasoVO caso = (CasoVO)datosMovimientoTesoreriaVO.getListaCasos().get(0);
		Convenio convenio = bonificacionesDao.getConvenio(caso.getCodigoConvenio());
		Concepto concepto = bonificacionesDao.getConcepto(convenio.getCodigoConcepto());
		datosMovimientoTesoreriaVO.setAreaNegocio(concepto.getTesoreriaArea());
		datosMovimientoTesoreriaVO.setObservacionMovCaja(concepto.getDescripcion());
				
		ArrayList listaCasos  = datosMovimientoTesoreriaVO.getListaCasos();
		//TODO revisar para precasos
		Comprobante comprobante = prepararComprobanteEgresoPreCaso(datosMovimientoTesoreriaVO, user, listaCasos);
		
//		//Carga los detalles de cada caso seleccionado en el egreso
//		for(int y=0;y<listaCasos.size();y++) {
//			CasoVO casoVO = (CasoVO)listaCasos.get(y);
//			casoVO.setDetalleCaso(bonificacionesDao.getDetallesCaso(casoVO.getCasoID()));			
//		}
//		
//		//Esto ocurre cuando es para mas de un profesional
//		if(sonVariosEnElMismoActo){
//			// Solo envio un caso y un detalle con el monto del egreso
//			// Esto es debido a que no se puede determinar que % deberìa tener 
//			// cada caso y cada detalle al generar varios egresos en un solo acto
//			CasoVO casoVO = new CasoVO();
//			casoVO = (CasoVO)listaCasos.get(0);
//			listaCasos.clear();
//			listaCasos.add(casoVO);
//			
//			DetalleCaso detalleCaso = new DetalleCaso();
//			detalleCaso = (DetalleCaso)casoVO.getDetalleCaso().get(0);	
//			casoVO.getDetalleCaso().clear();
//			casoVO.addDetalleCaso(detalleCaso);
//			logger.debug("Detalle Monto: " + detalleCaso.getTotalMenosIsapreYDescuento());
//		}
		
		//Prepara detalles para tesoreria
		comprobante.getDatosComunes().addAll(
				preparaDetalleEgresoPreCaso(
								datosMovimientoTesoreriaVO.getDetalles(),
								user,
								concepto,
								datosMovimientoTesoreriaVO.getFullRut()));
		
		//comprobante.getDatosComunes().addAll(preparaDetalleEgresoPreCaso(concepto, listaCasos, user, sonVariosEnElMismoActo, infoMovimientoPreCasoVO.getMonto()));		
		
		infoMovimientoPreCasoVO.setFolioTesoreria(tesoreria.registrarMovimientoTesoreriaBienestar(comprobante));		
	
		// Registra informacion en BD de Bienestar
		for(int y=0;y<listaCasos.size();y++) {
			CasoVO casoVO = (CasoVO)listaCasos.get(y);
		
			infoMovimientoPreCasoVO.setCasoId(casoVO.getCasoID());
			if(!sonVariosEnElMismoActo)
				infoMovimientoPreCasoVO.setMonto(casoVO.getMontoEgresoTesoreria());
			
			bonificacionesDao.insertMovimientoTesoreriaPreCaso(infoMovimientoPreCasoVO);

			// cambia el estado del indicador de egreso del caso			
			if(validarEstadoPrevio)
				actualizaIndicadorEgresoPreCaso(casoVO);
		}	
		
		for(int y=0;y<listaCasos.size();y++) {
			CasoVO casoVO = (CasoVO)listaCasos.get(y);
		
			infoMovimientoPreCasoVO.setCasoId(casoVO.getCasoID());	
				
			if(!sonVariosEnElMismoActo)
				infoMovimientoPreCasoVO.setMonto(casoVO.getMontoEgresoTesoreria());
				
			//Registra informacion de los detalles del egreso	
			Collection listaDetalles = infoMovimientoPreCasoVO.getDetalles();
			if(!listaDetalles.isEmpty()){
				Iterator i = listaDetalles.iterator();
				while(i.hasNext()){
					DetalleMovimientoPreCasoVO detalleMovimientoPreCasoVO =
										(DetalleMovimientoPreCasoVO)i.next();
											
					detalleMovimientoPreCasoVO.setFolioTesoreria(infoMovimientoPreCasoVO.getFolioTesoreria());										
					bonificacionesDao.insertDetalleEgreso(detalleMovimientoPreCasoVO);
				}
			}		
		}
	}
	
	/**
	 * Información de los egresos y/o ingresos generados en tesoreria
	 * @param long casoId
	 * @return ArrayList de InfoMovimientoPreCasoVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getMovimientosTesoreriaPreCaso(long casoId) throws Exception, BusinessException {
		return bonificacionesDao.getMovimientosTesoreriaPreCaso(casoId);
	}	
	
	
	/**
	 * Prepara un comprobante de tesoreria 
	 * @param DatosMovimientoTesoreriaVO
	 * @param UsuarioVO
	 * @param ArrayList
	 * @throws Exception
	 * @throws BusinessException
	 */	
	private Comprobante prepararComprobanteEgresoPreCaso(DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO, UsuarioVO user, ArrayList listaCasos)throws Exception, BusinessException {
	
		//Código de Oficina para Tesoreria Bienestar
		int oficinaBienestar = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/param/bienestar/oficina"));

		if (oficinaBienestar == 0)
			throw new BusinessException("CCAF_TESO_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: bienestar/oficina en el Sistema");
					
		Comprobante comprobante = new Comprobante();
				
		//Comprobante
		comprobante.setTipoMovimiento(Comprobante.TPO_MOVI_EGRESO);
		comprobante.setEstadoMovimientoCaja(Comprobante.STD_MOV_CAJA_GENERADO);
		comprobante.setFormaPago(Comprobante.FORMA_PAGO_CAJA);
		comprobante.setRut1(Integer.parseInt(datosMovimientoTesoreriaVO.getRut()));
		comprobante.setDv1(datosMovimientoTesoreriaVO.getDigito());
		comprobante.setNombreRut1(datosMovimientoTesoreriaVO.getFullNombre());
		
		if(listaCasos.size()==1){
			CasoVO casoUnico = (CasoVO)listaCasos.get(0);
			if(!datosMovimientoTesoreriaVO.getRut().equals(casoUnico.getRutSocio())){
				comprobante.setRut2(Integer.parseInt(casoUnico.getRutSocio()));
				comprobante.setDv2(casoUnico.getDvRutSocio());
				comprobante.setNombreRut2(casoUnico.getNombreSocio());				
			}			
		}	
		
		comprobante.setMontoInformado((int)datosMovimientoTesoreriaVO.getMonto());
		comprobante.setMontoEmitido((int)datosMovimientoTesoreriaVO.getMonto());
		comprobante.setObservaciónMovimientoCaja(datosMovimientoTesoreriaVO.getObservacionMovCaja());
		comprobante.setSucursal(0);
		comprobante.setEstadoAutorizacion(Comprobante.STD_AUTORIZACION_AUTORIZADO);
		
		logger.debug("Forma Pago: " +datosMovimientoTesoreriaVO.getTipoPago());
		logger.debug("Codigo oficinaBienestar: " +oficinaBienestar);
		logger.debug("Codigo Oficina: " +user.getCodigoOficina());
		
		if(datosMovimientoTesoreriaVO.getTipoPago().equals(Constants.PAGO_CHEQUE))
			comprobante.setTipoPago(Comprobante.TIPO_PAGO_CHEQUE);
		else
			comprobante.setTipoPago(Comprobante.TIPO_PAGO_EFECTIVO);
		
		comprobante.setEmiteFactura(Comprobante.EMITE_FACTURA_NO);
		comprobante.setCodigoOficina(oficinaBienestar);
		comprobante.setCorrelativoPago(0);
		comprobante.setCodigoAreaNegocio((int)datosMovimientoTesoreriaVO.getAreaNegocio());
		comprobante.setSerPagadoPorCodigoOficina(Integer.parseInt(user.getCodigoOficina()));
		comprobante.setUsuarioCreoRegistro(user.getUsuario());	
		
		return comprobante;
			
	}
	
	/**
	 * Prepara el detalle de un egreso de un pre caso
	 * @param ArrayList de CasoVO
	 * @param UsuarioVO
	 * @param boolean indica si son varios egresos al Al Mismo Caso
	 * @param double monto
	 * @return ArrayList de Detalle
	 */
	public ArrayList preparaDetalleEgresoPreCaso(
								Collection detalles,
								UsuarioVO user,
								Concepto concepto,
								String fullRut)
										throws Exception, BusinessException {
		
		ArrayList detallesTesoreria = new ArrayList();

		//Genero los Datos Comunes (Detalles Tesoreria)
		if(!detalles.isEmpty()){
			Iterator i = detalles.iterator();
			while(i.hasNext()){
				DetalleMovimientoPreCasoVO detalleMovimientoPreCasoVO =
						(DetalleMovimientoPreCasoVO) i.next();
				Detalle datosComunes = new Detalle();
				if (detalleMovimientoPreCasoVO.getMonto() > 0) {
					datosComunes.setMontoDetalle((int)detalleMovimientoPreCasoVO.getMonto());
					String observacion = detalleMovimientoPreCasoVO.getDescripcion();
					observacion = observacion + "--" +
								detalleMovimientoPreCasoVO.getCasoId() + "--" +
								fullRut;
					datosComunes.setObservaciónMovimientoDetalle(observacion);
					datosComunes.setDocumentoRespaldo("O");
					datosComunes.setCantidadDocumentos(1);
					datosComunes.setMontoPagoCheque(0);
					datosComunes.setNumeroCaratula(0);
					datosComunes.setCreationUser(user.getUsuario());
					datosComunes.setMontoPagoEfectivo(0);
					datosComunes.setCodigoConcepto((int)concepto.getTesoreriaConceptoEgreso());
					
					detallesTesoreria.add(datosComunes);
				}						
			}
		}

		return detallesTesoreria;
	}	
	
//	/**
//	 * Prepara el detalle de un egreso de un pre caso
//	 * @param ArrayList de CasoVO
//	 * @param UsuarioVO
//	 * @param boolean indica si son varios egresos al Al Mismo Caso
//	 * @param double monto
//	 * @return ArrayList de Detalle
//	 * @deprecated
//	 */
//	public ArrayList preparaDetalleEgresoPreCaso(Concepto concepto,ArrayList listaCasos, UsuarioVO user, boolean sonVariosEnElMismoActo, double monto) throws Exception, BusinessException {
//		
//		//ArrayList detallesCaso
//		ArrayList detalles = new ArrayList();
//
//
//
//		for (int x=0;x<listaCasos.size();x++) {
//			CasoVO casoVO = (CasoVO)listaCasos.get(x); 
//			ArrayList detallesCaso = casoVO.getDetalleCaso();
//			for (int y=0;y<detallesCaso.size();y++) {
//				DetalleCaso detalle = (DetalleCaso)detallesCaso.get(y);
//				Detalle datosComunes = new Detalle();
//				double montoDetalle=0;
//				
//				if(sonVariosEnElMismoActo)
//					montoDetalle=monto;
//				else
//					montoDetalle = detalle.getTotalMenosIsapreYDescuento();
//					
//				//Genero los Datos Comunes (Detalles)
//				if (montoDetalle > 0) {
//					datosComunes.setMontoDetalle((int)montoDetalle);
//					String observacion = detalle.getProducto().getCobertura().getDescripcion();
//					observacion = observacion + "--" + casoVO.getCasoID()+"--"+casoVO.getFullRutSocio();
//					datosComunes.setObservaciónMovimientoDetalle(observacion);
//					datosComunes.setDocumentoRespaldo("O");
//					datosComunes.setCantidadDocumentos(1);
//					datosComunes.setMontoPagoCheque(0);
//					datosComunes.setNumeroCaratula(0);
//					datosComunes.setCreationUser(user.getUsuario());
//					datosComunes.setMontoPagoEfectivo(0);
//					datosComunes.setCodigoConcepto((int)concepto.getTesoreriaConceptoEgreso());
//					
//					detalles.add(datosComunes);
//				}
//			}
//		}
//		return detalles;
//	}	
		
	/**
	 * Genera un Ingreso en tesoreria 
	 * @param ArrayList de DatosMovimientoTesoreriaVO
	 * @param UsuarioVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void registrarIngresoTesoreriaPreCaso(ArrayList listaMovimientos, UsuarioVO user) throws Exception, BusinessException {
		
		for(int x=0;x<listaMovimientos.size();x++){
			DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO = (DatosMovimientoTesoreriaVO)listaMovimientos.get(x);

			InfoMovimientoPreCasoVO infoMovimientoPreCasoVO = new InfoMovimientoPreCasoVO();
			infoMovimientoPreCasoVO.setRut(datosMovimientoTesoreriaVO.getRut());
			infoMovimientoPreCasoVO.setDigito(datosMovimientoTesoreriaVO.getDigito());
			infoMovimientoPreCasoVO.setNombre(datosMovimientoTesoreriaVO.getFullNombre());	
			infoMovimientoPreCasoVO.setFecha(new Date());
			infoMovimientoPreCasoVO.setMonto(datosMovimientoTesoreriaVO.getMonto());
			infoMovimientoPreCasoVO.setUsuario(user.getUsuario());
			infoMovimientoPreCasoVO.setTipoMovimiento(datosMovimientoTesoreriaVO.getTipoMovimiento());
			
			CasoVO caso = (CasoVO)datosMovimientoTesoreriaVO.getListaCasos().get(0);
			Convenio convenio = bonificacionesDao.getConvenio(caso.getCodigoConvenio());
			Concepto concepto = bonificacionesDao.getConcepto(convenio.getCodigoConcepto());		
		
			datosMovimientoTesoreriaVO.setAreaNegocio(concepto.getTesoreriaArea());
			datosMovimientoTesoreriaVO.setObservacionMovCaja(concepto.getDescripcion());
			
			//Servicio de TesoreriaEJB
			ServicesTesoreriaDelegate tesoreria = new ServicesTesoreriaDelegate();	
			Comprobante comprobante = prepararComprobanteIngresoPreCaso(datosMovimientoTesoreriaVO, user);
			
			ArrayList detallesCaso=new ArrayList();
			ArrayList listaCasos  = datosMovimientoTesoreriaVO.getListaCasos();
			
			for(int y=0;y<listaCasos.size();y++) {
				CasoVO casoVO = (CasoVO)listaCasos.get(y);
				detallesCaso.addAll(getDetallesCasoPreCasos(casoVO.getCasoID()));			
			}
						
			comprobante.getDatosComunes().addAll(preparaDetalleIngresoPreCaso(comprobante.getMontoEmitido(), concepto, detallesCaso, user, datosMovimientoTesoreriaVO.getTipoMovimiento()));
		 
			infoMovimientoPreCasoVO.setFolioTesoreria(tesoreria.registrarMovimientoTesoreriaBienestar(comprobante));		
		
			// Registra informacion en BD de Bienestar
			for(int y=0;y<listaCasos.size();y++) {
				CasoVO casoVO = (CasoVO)listaCasos.get(y);
			
				infoMovimientoPreCasoVO.setCasoId(casoVO.getCasoID());
				bonificacionesDao.insertMovimientoTesoreriaPreCaso(infoMovimientoPreCasoVO);
				
				// cambia el estado del indicador de ingreso del caso
				actualizaIndicadorIngresoPreCaso(casoVO);
			}			
		}	
		
	}
	
	/**
	 * Prepara un comprobante de tesoreria 
	 * @param DatosMovimientoTesoreriaVO
	 * @param UsuarioVO
	 * @throws Exception
	 * @throws BusinessException
	 */	
	private Comprobante prepararComprobanteIngresoPreCaso(DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO, UsuarioVO user)throws Exception, BusinessException {
	
		//Código de Oficina para Tesoreria Bienestar
		int oficinaBienestar = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName,
				 "/application-settings/tesoreria/param/bienestar/oficina"));

		if (oficinaBienestar == 0)
			throw new BusinessException("CCAF_TESO_VARIABLENODEFINIDA",
				"Debe definir el valor de la variable: bienestar/oficina en el Sistema");

		Comprobante comprobante = new Comprobante();
				
		//Comprobante
		comprobante.setTipoMovimiento(Comprobante.TPO_MOVI_INGRESO);
		comprobante.setEstadoMovimientoCaja(Comprobante.STD_MOV_CAJA_GENERADO);
		comprobante.setFormaPago(Comprobante.FORMA_PAGO_CAJA);
		comprobante.setRut1(Integer.parseInt(datosMovimientoTesoreriaVO.getRut()));
		comprobante.setDv1(datosMovimientoTesoreriaVO.getDigito());
		comprobante.setNombreRut1(datosMovimientoTesoreriaVO.getFullNombre());
		comprobante.setMontoInformado((int)datosMovimientoTesoreriaVO.getMonto());
		comprobante.setMontoEmitido((int)datosMovimientoTesoreriaVO.getMonto());
		comprobante.setObservaciónMovimientoCaja(datosMovimientoTesoreriaVO.getObservacionMovCaja());
		comprobante.setSucursal(0);
		comprobante.setEstadoAutorizacion(Comprobante.STD_AUTORIZACION_AUTORIZADO);
		if(datosMovimientoTesoreriaVO.getTipoPago().equals(Constants.PAGO_CHEQUE))
			comprobante.setTipoPago(Comprobante.TIPO_PAGO_CHEQUE);
		else
			comprobante.setTipoPago(Comprobante.TIPO_PAGO_EFECTIVO);
		comprobante.setEmiteFactura(Comprobante.EMITE_FACTURA_NO);
		comprobante.setCodigoOficina(oficinaBienestar);
		comprobante.setCorrelativoPago(0);
		comprobante.setCodigoAreaNegocio((int)datosMovimientoTesoreriaVO.getAreaNegocio());
		comprobante.setSerPagadoPorCodigoOficina(Integer.parseInt(user.getCodigoOficina()));
		comprobante.setUsuarioCreoRegistro(user.getUsuario());	
		
		return comprobante;
			
	}
	
	/**
	 * Prepara el detalle de un ingreso de un pre caso
	 * @param int montoComprobante
	 * @param ArrayList de DetalleCaso
	 * @param UsuarioVO
	 * @param String tipoIngreso
	 * @return ArrayList de Detalle
	 */
	public ArrayList preparaDetalleIngresoPreCaso(int montoComprobante, Concepto concepto, ArrayList detallesCaso, UsuarioVO user, String tipoIngreso) throws Exception, BusinessException {
		
		ArrayList detalles = new ArrayList();

		int totalDetalles=0;
		for (int x=0;x<detallesCaso.size();x++) {
			DetalleCaso detalle = (DetalleCaso)detallesCaso.get(x);
			if(tipoIngreso.equals(Constants.MOVI_INGRESO_ISAPRE) && detalle.getAporteIsapre() > 0){
				totalDetalles=totalDetalles+ (int)detalle.getAporteIsapre();
			}else if(tipoIngreso.equals(Constants.MOVI_INGRESO_OTROS) && detalle.getMontoDescuento() > 0){
				totalDetalles=totalDetalles+ (int)detalle.getMontoDescuento();
			}			
		}
		
		boolean informarTodosLosDetalles = montoComprobante == totalDetalles ? true:false;
		
		for (int x=0;x<detallesCaso.size();x++) {
			DetalleCaso detalle = (DetalleCaso)detallesCaso.get(x);
			Detalle datosComunes = new Detalle();
			//Genero los Datos Comunes (Detalles)
			boolean existeDetalle=false;
			if(tipoIngreso.equals(Constants.MOVI_INGRESO_ISAPRE) && detalle.getAporteIsapre() > 0){
				existeDetalle=true;
				datosComunes.setMontoDetalle((int)detalle.getAporteIsapre());
			}else if(tipoIngreso.equals(Constants.MOVI_INGRESO_OTROS) && detalle.getMontoDescuento() > 0){
				existeDetalle=true;
				datosComunes.setMontoDetalle((int)detalle.getMontoDescuento());				
			}
			
			if (existeDetalle) {
				if(informarTodosLosDetalles){
					datosComunes.setObservaciónMovimientoDetalle(detalle.getProducto().getCobertura().getDescripcion());
					datosComunes.setDocumentoRespaldo("O");
					datosComunes.setCantidadDocumentos(1);
					datosComunes.setMontoPagoCheque(0);
					datosComunes.setNumeroCaratula(0);
					datosComunes.setCreationUser(user.getUsuario());
					datosComunes.setMontoPagoEfectivo(0);
					datosComunes.setCodigoConcepto((int)concepto.getTesoreriaConceptoIngreso());
				
					detalles.add(datosComunes);					
				}else{
					datosComunes.setObservaciónMovimientoDetalle(detalle.getProducto().getCobertura().getDescripcion());
					datosComunes.setDocumentoRespaldo("O");
					datosComunes.setCantidadDocumentos(1);
					datosComunes.setMontoPagoCheque(0);
					datosComunes.setNumeroCaratula(0);
					datosComunes.setCreationUser(user.getUsuario());
					datosComunes.setMontoPagoEfectivo(0);
					datosComunes.setCodigoConcepto((int)concepto.getTesoreriaConceptoIngreso());
					//Solo informa un detalle con el monto total
					datosComunes.setMontoDetalle( montoComprobante );
					
					detalles.add(datosComunes);	
					break;				
				}
			}
		}		

		return detalles;
	}
	
	/**
	 * Actualiza el idicador de egreso 
	 * @param CasoVO casoVo
	 * @throws Exception
	 * @throws BusinessException
	 */
	private void actualizaIndicadorEgresoPreCaso(CasoVO casoVo) throws Exception, BusinessException {
		
		String estadoPrevio = Caso.STD_PRECASO;

		int filasActualizadas=0;
		
		casoVo.setIndicadorPreCasoEgresoGenerado(Caso.ESTADOINDICADOR_SI);
		filasActualizadas = bonificacionesDao.updateIndicadorEgresoPreCaso(casoVo, estadoPrevio);
		if (filasActualizadas==0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
				"El Estado del Caso ID: " + casoVo.getCasoID() + " no es Correcto");
	}
	
	/**
	 * Actualiza el idicador de ingreso  
	 * @param CasoVO casoVo
	 * @throws Exception
	 * @throws BusinessException
	 */
	private void actualizaIndicadorIngresoPreCaso(CasoVO casoVo) throws Exception, BusinessException {
		
		String estadoPrevio = Caso.STD_PRECASO;

		int filasActualizadas=0;
		
		casoVo.setIndicadorPreCasoIngresoGenerado(Caso.ESTADOINDICADOR_SI);
		filasActualizadas = bonificacionesDao.updateIndicadorIngresoPreCaso(casoVo, estadoPrevio);
		if (filasActualizadas==0)
			throw new BusinessException("CCAF_BONIF_CASOINVALIDO",
				"El Estado del Caso ID: " + casoVo.getCasoID() + " no es Correcto");
	}	

	/** 
	 * Obtiene la lista de Profesionales
	 * @param Profesional
	 * @return ArrayList de Profesional
	 * @throws Exception
	 */
	public ArrayList getListaProfesionales(Profesional profesional) throws Exception, BusinessException{
			
			return bonificacionesDao.getListaProfesionales(profesional);
			
	}
	
	/**
	 * Crea o actualiza profesional en Bienestar
	 * @param Profesional
	 */
	public void registrarProfesional(Profesional profesional) throws Exception, BusinessException {
	
		if(bonificacionesDao.getProfesionalByRut(profesional.getRut()).getRut()==null)
			bonificacionesDao.insertProfesional(profesional);
		else
			bonificacionesDao.updateProfesional(profesional);
			
	}
	
	/** 
	 * Obtiene lista de pre-casos que ya tienen por lo menos un egreso generado
	 * @return ArrayList de CasoVO
	 * @throws Exception
	 */
	public ArrayList getListaPreCasosConPorLoMenosUnEgresoGenerado() throws Exception, BusinessException {

		Caso casoFiltro = new Caso();
		casoFiltro.setEstado(Caso.STD_PRECASO);
		casoFiltro.setIndicadorPreCaso(Caso.ESTADOINDICADOR_SI);
		casoFiltro.setIndicadorPreCasoEgresoGenerado(Caso.ESTADOINDICADOR_SI);
		casoFiltro.setIndicadorPreCasoIngresoGenerado(null);		
		return bonificacionesDao.getListaPreCasosByFiltro(casoFiltro);
	}
	
	/**
	 * Devuelve la suma de los egresos previos del mismo caso
	 * @param casoId
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int getMontoEgresosPrevios(long casoId) throws Exception, BusinessException{
	
		return bonificacionesDao.getMontoMovimientosPrevios(casoId, Constants.MOVI_EGRESO);
	
	}
	
	/**
	 * Devuelve la suma de los ingresos previos del mismo caso
	 * @param casoId
	 * @param String tipoIngreso
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int getMontoIngresosPrevios(long casoId, String tipoIngreso) throws Exception, BusinessException{
	
		return bonificacionesDao.getMontoMovimientosPrevios(casoId, tipoIngreso);
	
	}

	/**
	 * retorna el grupo del usuario para determinar si es admin o socio
	 * @param rut
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public int getGrupoUsuario(long rut) throws Exception,SQLException{
		return bonificacionesDao.getGrupoUsuario(rut);
	}		


   public ArrayList getCasosEstadoBorrador(String rut) throws Exception, BusinessException {
	   //TODO: ESTE METODO ORIGINALMENTE RETORNABA UN COLLECTION,NO DEBERIA FALLAR CON ArrayList...
	 ArrayList result= new ArrayList();
		
	   //Comentario cierre caso automatico
//		   String cometarioCasoCerrado = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//						 "/application-settings/bonificaciones/param/comentario-cierre-caso");
		
		   //Comentario eliminacion caso automatico			  
//		   String cometarioCasoEliminado = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//						 "/application-settings/bonificaciones/param/comentario-eliminacion-caso");
					  
		   //Usuario que Cierra o Elimina caso automaticamente			  
//		   String usuario = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
//						 "/application-settings/bonificaciones/param/usuarior-comentario-caso");

		
	   ArrayList casos = bonificacionesDao.getCasosAbiertos(rut);
	   for (int x=0;x<casos.size();x++) {
		   CasoAbiertoVO caso = (CasoAbiertoVO) casos.get(x);
		   if (caso.getEstadoActual().equals(Caso.STD_ACTIVO)){
			   /*Evento evento = new Evento();
			   //Cierra caso que estaba activo
			   caso.setEstadoNuevo(Caso.STD_CERRADO);
			   caso.setFechaEstadoNuevo(new Date());
			   bonificacionesDao.finalizaCaso(caso);
			   //genera evento
			   evento.setComentario(cometarioCasoCerrado);
			   evento.setFechaIngreso(new Date());
			   evento.setTipo(Evento.TIPO_AUTOMATICO);
			   evento.setUsuario(usuario);
			   registraEvento(caso.getCasoId(), evento);  */
				
		   } else if (caso.getEstadoActual().equals(Caso.STD_BORRADOR)) {
//			   Evento evento = new Evento();
				
			   System.out.println("caso id        "+caso.getCasoId());
			   System.out.println("estadop actual "+caso.getEstadoActual());
			   System.out.println("estado nuevo   "+caso.getEstadoNuevo());
			   System.out.println("fecha nueva"+caso.getFechaEstadoNuevo());
			   long pa = caso.getCasoId();
			   CasoVO casos1= new CasoVO();
			   casos1 =bonificacionesDao.getCasoVO(pa);
			   caso.setIngreso(casos1.getFechaIngreso());
			   caso.setMonto(casos1.getMonto());
				
		     
		   //monto
		   // ingreso caso
			
		 
			   //Elimina caso que estaba en borrador
			   //caso.setEstadoNuevo(Caso.STD_BORRADOR);
			   //caso.setFechaEstadoNuevo(new Date());
			   //bonificacionesDao.finalizaCaso(caso);
			   //genera evento
			   //evento.setComentario(cometarioCasoEliminado);
		   //	evento.setFechaIngreso(new Date());
	   //		evento.setTipo(Evento.TIPO_AUTOMATICO);
	   //		evento.setUsuario(usuario);
	   //		registraEvento(caso.getCasoId(),evento);
		  result.add(caso);
		   }
	   }
		
		 
	
   return result;
   }
   
   /**
	 * REQ 5030: elimina reembolsos y sus egresos (sólo en estado generado)
	 * No existen reembolsos que previamente sean PRECASOS
	 * @param casoId
	 * @throws Exception
	 * @throws BusinessException
	 */
	public boolean liberarTopeCobertura(long casoId, String user) throws Exception, BusinessException{
		
//		ServicesTesoreriaDelegate servTesoreria = new ServicesTesoreriaDelegate();

		boolean exito = false;
		
//		CasoVO casoBD = bonificacionesDao.getCasoVO(casoId);
		List apList = bonificacionesDao.getAporteCobertura(casoId);
		if(apList != null && apList.size() > 0 && bonificacionesDao.deleteAporteCobertura(casoId)){
			
			for(Iterator i = apList.iterator(); i.hasNext(); ){
				AporteCobertura ap = (AporteCobertura) i.next();
				bonificacionesDao.registraBitacoraElimCobertura(ap.getCasoID(),ap.getAporteBienestar(),ap.getCodigoCobertura(), ap.getIdDetalle(),user);
			}
			exito = true;
		}
	
		
		/**
		 * 
		//se revisa si el caso tiene egresos (BF18F1 de Bonificaciones) relacionados 1:1 con el caso
		List reembolsos = bonificacionesDao.getDetallesCasoReembolso(casoId);
		
		//si no tiene egresos
		if(reembolsos.size() == 0){
			CasoVO casoBD = bonificacionesDao.getCasoVO(casoId);
			casoBD.setEstado(CasoVO.STD_ELIMINADO);
			bonificacionesDao.updateCaso(casoBD);
			
			//liberamos el tope utilizado en el caso de que exista.
			bonificacionesDao.deleteAporteCobertura(casoId);
			exito = true;
		}
		
		else{
			for(Iterator i = reembolsos.iterator() ; i.hasNext() ; ){
				ReembolsoVO r = (ReembolsoVO) i.next();
				
				String estadoBienestar = servTesoreria.getEstadoComprobanteTesoreriaBienestar(r.getFolioTesoreriaBienestar());
				
//				si existe 1 reembolso con un solo caso, se revisa el estado de este.
				if(estadoBienestar != null && 
						(estadoBienestar.equals(Comprobante.STD_MOV_CAJA_IMPRESO) || 
								estadoBienestar.equals(Comprobante.STD_MOV_CAJA_GENERADO))){
					if(servTesoreria.anulaComprobanteEgresoTesoreriaBienestar(r.getFolioTesoreriaBienestar()))
						exito = true;
				}
				
				if(exito == true){
					String estadoAraucana = servTesoreria.getEstadoComprobanteTesoreriaAraucana(r.getFolioTesoreriaAraucana());
					
					if(estadoAraucana != null && 
							(estadoAraucana.equals(Comprobante.STD_MOV_CAJA_IMPRESO) || 
									estadoAraucana.equals(Comprobante.STD_MOV_CAJA_GENERADO))){
						if(servTesoreria.anulaComprobanteEgresoTesoreriaAraucana(r.getFolioTesoreriaAraucana()))
							exito = true;
					}
				}
				
				if(exito == true){
					CasoVO casoBD = bonificacionesDao.getCasoVO(casoId);
					casoBD.setEstado(CasoVO.STD_ELIMINADO);
					bonificacionesDao.updateCaso(casoBD);
					
					//liberamos el tope utilizado en el caso de que exista.
					bonificacionesDao.deleteAporteCobertura(casoId);
					exito = true;
				}
				else{
					exito = false;
					break;
				}
			}
			
		}*/
		
		return exito;
	}
	
	/**
	 * REQ 5030
	 * @param casoId
	 * @param aporteBienestar
	 * @param codigoCobertura
	 * @param idDetalle
	 * @param user
	 * @return
	 * @throws GeneralException 
	 * @throws BusinessException 
	 * @throws Exception
	 * @throws BusinessException
	 */
	public List getBitacoraElimCobertura(long casoId) throws Exception, BusinessException{
		return bonificacionesDao.getBitacoraElimCobertura(casoId);
	}
	
	/**
	 * REQ 5265 (Validar documento)
	 * Valida si el documento ya se encuentra registrado previamente.
	 * Retorna true si el documento se encuentra registrado previamente, false en caso contrario.
	 * @param caso
	 * @return boolean
	 * @throws BusinessException
	 * @throws Exception
	 */
	public boolean documentoYaRegistrado(Caso caso) throws BusinessException, Exception {
		
		String numDoc = bonificacionesDao.getNumeroDocumento(caso.getRutSocio(), caso.getCasoID(), caso.getNumeroDocumento(), caso.getTipoDocumento() );
		
		return numDoc != null ? true : false;
	}
	
	/**
	 * Cierra o Elimina los casos del rut pasado como parametro
	 * Actualiza estado y fecha de estado
	 * Genera evento automatico 
	 * @param Caso caso
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void cierraCasosUsuerDesvinculados(Caso casoOriginal) throws Exception, BusinessException {
		double sumatoriaValorCuota = 0;
		Cuota primeraCuota = null;
		
		String cometarioCasoCerrado = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
		  "/application-settings/bonificaciones/param/comentario-cierre-caso");

		//Usuario que Cierra o Elimina caso automaticamente			  
		String usuario = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
		  "/application-settings/bonificaciones/param/usuarior-comentario-caso");
		
		//Verificar usuario disvinculado
		if(bonificacionesDao.isSocioInactivosConCasosAbiertosByRut(casoOriginal.getRutSocio())){
			
			Cuota cuota =	new Cuota();
			cuota.setCuotaEstado(Cuota.STD_NO_DESCONTADA);
			ArrayList arrayCuotasNoDescontadas = bonificacionesDao.getCuotasCaso(casoOriginal.getCasoID(), cuota);
			
			if(arrayCuotasNoDescontadas.size() > 0){//Solo para cuando tenga cuotas por descontar
				for(Iterator it = arrayCuotasNoDescontadas.iterator() ; it.hasNext();){
					Cuota cuotaIt = (Cuota)it.next();
					sumatoriaValorCuota = sumatoriaValorCuota + cuotaIt.getValorCuota();
					
					if(primeraCuota == null)
						primeraCuota = cuotaIt;
				}
				
				primeraCuota.setValorCuota(sumatoriaValorCuota);
				primeraCuota.setCuotaEstado(Cuota.STD_PAGADA);
				
				bonificacionesDao.deleteCuotasCasoNoCobradas(casoOriginal.getCasoID());
				bonificacionesDao.insertCuotaCaso(casoOriginal.getCasoID(),primeraCuota);

			}
			
			casoOriginal.setEstado(Caso.STD_CERRADO);
			casoOriginal.setFechaEstado(new Date());
			casoOriginal.setIndicadorDescontado(Caso.ESTADOINDICADOR_NO);
			casoOriginal.setIndicadorBonificacion(Caso.ESTADOINDICADOR_NO);
			casoOriginal.setIndicadorPago(Caso.ESTADOINDICADOR_SI);
			bonificacionesDao.finalizaCasoEnProceso(casoOriginal);

			Evento evento = new Evento();
			evento.setComentario(cometarioCasoCerrado);
			evento.setFechaIngreso(new Date());
			evento.setTipo(Evento.TIPO_AUTOMATICO);
			evento.setUsuario(usuario);			
		}
	}	
}