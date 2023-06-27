package cl.araucana.bienestar.bonificaciones.serv;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import cl.araucana.bienestar.bonificaciones.common.Constants;
import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.model.DetalleCaso;
import cl.araucana.bienestar.bonificaciones.model.Evento;
import cl.araucana.bienestar.bonificaciones.model.Profesional;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosSLBean.ServicesCasos;
import cl.araucana.bienestar.bonificaciones.serv.ServicesCasosSLBean.ServicesCasosHome;
import cl.araucana.bienestar.bonificaciones.vo.CasoVO;
import cl.araucana.bienestar.bonificaciones.vo.DatosMovimientoTesoreriaVO;
import cl.araucana.bienestar.bonificaciones.vo.InputUpLoadFileVO;
import cl.araucana.bienestar.bonificaciones.vo.ParamAporteEspecialVO;
import cl.araucana.bienestar.bonificaciones.vo.ParamOperacionesVO;
import cl.araucana.bienestar.bonificaciones.vo.ParamResumenMovimientosVO;
import cl.araucana.bienestar.bonificaciones.vo.ResultUpLoadFileVO;
import cl.araucana.bienestar.bonificaciones.vo.ResumenMovimientosBeneficiarioVO;
import cl.araucana.bienestar.bonificaciones.vo.ResumenMovimientosConvenioVO;
import cl.araucana.bienestar.bonificaciones.vo.UsuarioVO;
import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;

import com.schema.patterns.BusinessDelegate;
import com.schema.util.FileSettings;
import com.schema.util.GeneralException;

/**
 * @author asepulveda
 * Business Delegate para Servicios de Casos de Bienestar
 */
public class ServicesCasosDelegate extends BusinessDelegate {
	// Home y Remote del EJB
	private final static Class homeClass=ServicesCasosHome.class;
	private ServicesCasos services=null;
	
	Logger logger = Logger.getLogger(ServicesCasosDelegate.class);
	
	/**
	 * Constructor del BDelegate. Establece la conexion con el EJB
	 * @throws GeneralException
	 */
	public ServicesCasosDelegate() throws GeneralException {
		// Nombre JNDI del EJB
		String jndi = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
		              "/application-settings/ejbs/bonificaciones/casos-services");
		
		try {
			InitialContext ic=new InitialContext();
			services = (ServicesCasos)super.getServiceBean(ic, jndi,homeClass);
		} catch (Exception e) {
			throw new GeneralException(this,e);
		}              
	}

	/** 
	 * Obtiene una lista de Eventos
	 * @param codigo de caso (caso ID)
	 * @param evento, Objeto Evento con filtros posibles
	 * @return ArrayList de Evento Dentro de un Caso
	 * @throws Exception
	 */
	public Caso getEventos(Caso caso) throws Exception {
		return services.getEventos(caso);
	}
	
	/**
	 * Registra un Evento asociado a un Caso
	 * @param cado Id y Evento con los datos
	 */
	public void registraEvento(long casoId, Evento evento,UsuarioVO usuario) throws RemoteException, Exception, BusinessException {
		evento.setTipo(Evento.TIPO_MANUAL);
		evento.setUsuario(usuario.getUsuario());
		services.registraEvento(casoId, evento);
	}
	
	/** 
	 * Obtiene una lista de Detalle de Caso
	 * @param Caso
	 * @return Caso con ArrayList de Detalle Caso
	 * @throws Exception
	 */
	public Caso getDetallesCaso(Caso caso) throws RemoteException, Exception, BusinessException {
		return services.getDetallesCaso(caso);
	}
	
	/** 
	 * Obtiene una lista de Detalle de Caso con los montos de los detalles de egresos previos ya restados
	 * @param long casoId
	 * @return ArrayList de DetalleCaso
	 * @throws Exception, BusinessException
	 */
	public ArrayList getDetallesCasoPreCasos(long casoId) throws RemoteException, Exception, BusinessException  {

		return services.getDetallesCasoPreCasos(casoId);

	}		
	
	/**
	 * Crea un Detalle asociado a un Caso
	 * @param caso
	 * @param detalleCaso
	 * @return Caso con los totales actualizados
	 * @throws Exception
	 */
	public Caso registraDetalle(Caso caso,  DetalleCaso detalleCaso) throws RemoteException, Exception, BusinessException {
		return services.registraDetalle(caso, detalleCaso);
	}
	
	/** 
	 * Cierra un caso de tipo descuento por pago con prestamo Y/O abono del socio
	 * y registra un evento con el cierre
	 * @param Caso
	 * @param Evento
	 * @throws Exception
	 */
	public void cierraCaso(Caso caso, Evento evento) throws RemoteException, Exception, BusinessException  {
		services.cerrarCaso(caso,evento);
	}
	
	/** 
	 * Registra la información de un aporte especial para un caso
	 * y registra un evento con el comentario dl usuario
	 * @param Caso
	 * @param Evento
	 * @throws Exception
	 */
	public void registrarAporteEspecialCaso(ParamAporteEspecialVO param, Evento evento) throws RemoteException, Exception, BusinessException  {
		services.registrarAporteEspecialCaso(param,evento);	
	}
	
	/** 
	 * Registra la información de un aporte especial para un caso
	 * y registra un evento con el comentario dl usuario si utilizar su tope de cobertura
	 * @param Caso
	 * @param Evento
	 * @throws Exception
	 */
	public void registrarAporteEspecialSinTopeCobertura(ParamAporteEspecialVO param, Evento evento) throws RemoteException, Exception, BusinessException  {
		services.registrarAporteEspecialSinTopeCobertura(param,evento);	
	}
	
	/**
	 * Modifica un Detalle asociado a un Caso
	 * @param caso con detalles de caso
	 * @param index con posicion de detalle de caso a actualizar 
	 * @param Detalle caso con los datos actualizados 
	 */
	public Caso actualizaDetalle(Caso caso, int index, DetalleCaso detalleCaso) throws RemoteException, Exception, BusinessException {
		return services.actualizaDetalle(caso,index,detalleCaso);
	}
	
	
	/**
	 * Elimina un Detalle asociado a un Caso
	 * @param caso con detalle de caso
	 * @param index con posicion de Detalle caso a eliminar 
	 */
	public Caso eliminaDetalle(Caso caso, int index) throws RemoteException, Exception, BusinessException {
		return services.eliminaDetalle(caso, index);
	}
	
	
	/**
	 * Crea un Caso
	 * @param caso
	 * @return caso id
	 */
	public long registraCaso(Caso caso) throws RemoteException, Exception, BusinessException {
		return services.registraCaso(caso);
	}
	
	/**
	 * Activa un Caso
	 * @param caso
	 */
	public void activarCaso(long casoId) throws RemoteException, Exception, BusinessException {
		services.activarCaso(casoId);
	}
	
	/**
	 * Elimina un Caso
	 * Si el caso fue o es precaso, verifica previamnete los estados
	 * de los folios que se hayan generado en tesoreria de Bienestar
	 * @param long casoId
	 * @param String usuario
	 */
	public void eliminarCaso(long casoId, String usuario) throws RemoteException, Exception, BusinessException {
		services.eliminarCaso(casoId, usuario);
	}
	
	/**
	 * Actualiza un Caso
	 * @param caso
	 */
	public void actualizarCaso(Caso caso) throws RemoteException, Exception, BusinessException {
		services.actualizarCaso(caso);
	}
		
	/** 
	 * Obtiene informacion de un Caso
	 * @param codigo de caso (caso ID)
	 * @return CasoVO
	 * @throws Exception
	 */
	public CasoVO getCasoVO(long casoId) throws RemoteException, Exception, BusinessException {
		return services.getCasoVO(casoId);
	}
	
	/** 
	 * Obtiene una lista de casos que corresponden a compra de bonos
	 * @param rut Socio
	 * @return ArrayList de Caso
	 * @throws Exception
	 */
	public ArrayList getBonos(String rutSocio) throws RemoteException, Exception, BusinessException {
		return services.getBonos(rutSocio);
	}
	
	/** 
	 * Obtiene una lista de casos que corresponden a compra de bonos
	 * @param rut Socio
	 * @return ArrayList de Caso
	 * @throws Exception
	 */
	public ArrayList getReembolsos(String rutSocio) throws RemoteException, Exception, BusinessException {
		return services.getReembolsos(rutSocio);
	}
	
	/** 
	 * Obtiene una lista de casos no bonificados
	 * @param
	 * @return ArrayList de Caso
	 * @throws Exception
	 */
	public ArrayList getCasosNoBonificados(String tipoCaso) throws RemoteException, Exception, BusinessException {
		return services.getCasosNoBonificados(tipoCaso);
	}
	
	/** 
	 * Obtiene una lista con los Casos Por Reembolsar
	 * segun las fecha indicadas
	 * @param ParamOperacionesVO 
	 * @return ArrayList de ReembolsosVO
	 * @throws Exception
	 */
	public ArrayList getCasosPorReembolsar(ParamOperacionesVO param) throws RemoteException, Exception, BusinessException {
		return services.getCasosPorReembolsar(param, null);
	}
	
	/** 
	 * Obtiene una lista con los Casos Por Reembolsar
	 * segun las fecha indicadas
	 * @param ParamOperacionesVO 
	 * @return ArrayList de ReembolsosVO
	 * @throws Exception
	 */
	public ArrayList getCasosPorReembolsar(ParamOperacionesVO param, String rut) throws RemoteException, Exception, BusinessException {
		return services.getCasosPorReembolsar(param, rut);
	}
	
	/** 
	 * Obtiene una lista con los Casos Por Descontar
	 * segun las fecha indicadas
	 * @param ParamOperacionesVO
	 * @return ArrayList de DescuentoVO
	 * @throws Exception
	 */
	public ArrayList getCasosPorDescontar(ParamOperacionesVO param) throws RemoteException, Exception, BusinessException {
		return services.getCasosPorDescontar(param);
	}
	
	/** 
	 * Obtiene una lista con los Casos Por Descontar
	 * segun las fecha indicadas
	 * @param ParamOperacionesVO
	 * @return ArrayList de DescuentoVO
	 * @throws Exception
	 */
	public ArrayList getCasoPorDescontar(long casid) throws RemoteException, Exception, BusinessException {
		return services.getCasoPorDescontar(casid);
	}
		
	/** 
	 * Obtiene una lista con los Casos Por Pagar
	 * @return ArrayList de PagoConvenioVO
	 * @throws Exception
	 */
	public ArrayList getCasosPorPagar(long codigoDescuento) throws RemoteException, Exception, BusinessException {
		return services.getCasosPorPagar(codigoDescuento);
	}
	
	/** 
	 * Obtiene lista de casos
	 * @param Caso con los filtros
	 * @return ArrayList de Caso
	 * @throws Exception
	 */
	public ArrayList getListaCasos(Caso caso) throws RemoteException, Exception, BusinessException{
		return services.getListaCasos(caso);
	}
	
	/** 
	 * Obtiene lista de casos asociados a una cobertura
	 * @param codigoCobertura
	 * @return ArrayList de Caso
	 * @throws Exception
	 */
	public ArrayList getListaCasosCobertura(long codigoCobertura) throws RemoteException, Exception, BusinessException {
		return services.getListaCasosCobertura(codigoCobertura);
	}
	
	/** 
	 * Obtiene una lista de Cuotas
	 * @param codigo de caso (caso ID)
	 * @return Caso con ArrayList de Cuota
	 * @throws Exception
	 */
	public Caso getCuotasCaso(Caso caso) throws RemoteException, Exception, BusinessException {
		return services.getCuotasCaso(caso);
	}
	
	/**
	 * Calcula la bonificacion de un caso (sólo en memoria)
	 * @param casoId
	 * @return Caso con informacion de la bonificacion realizada
	 * @throws Exception
	 */
	public CasoVO simularBonificacion(long casoId) throws RemoteException, Exception, BusinessException {
		return services.calcularBonificacionCaso(casoId, true);
	}
	
	/**
	 * Calcula la bonificacion de un caso
	 * actualiza la información del caso bonificado
	 * @param casoId
	 * @return
	 * @throws Exception
	 */
	public void bonificarCaso(long casoId) throws RemoteException, Exception , BusinessException{
		services.bonificarCaso(casoId);
	}
	
	
	/**
	 * Calcula la bonificacion de los casos solicitados
	 * actualiza la información de los casos bonificados
	 * @param ArrayList con casoId
	 * @return
	 * @throws Exception
	 */
	public void bonificar(ArrayList casos) throws RemoteException, Exception, BusinessException {
		services.bonificar(casos);
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
	public ArrayList resumenMovimientosBeneficiario(ParamResumenMovimientosVO parametros, ResumenMovimientosBeneficiarioVO resumenFiltro) throws RemoteException, Exception, BusinessException {
		return services.resumenMovimientosBeneficiario(parametros, resumenFiltro);
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
	public ArrayList resumenMovimientosConvenio(ParamResumenMovimientosVO parametros, ResumenMovimientosConvenioVO resumenFiltro) throws RemoteException, Exception, BusinessException {
		return services.resumenMovimientosConvenio(parametros, resumenFiltro);
	}
	
	/**
	 * Actualiza los casos con estado e indicador de reembolso 
	 * @param reembolsos
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void actualizaIndicadorReembolso(ArrayList reembolsos) throws RemoteException, Exception, BusinessException {
		services.actualizaIndicadorReembolso(reembolsos);
	}
	
	/**
	 * Actualiza los casos con estado e indicador de descuento
	 * Actualiza estado de las cuotas en caso de existir 
	 * @param descuentos
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void actualizaIndicadorDescuento(ArrayList descuentos) throws RemoteException, Exception, BusinessException {
		services.actualizaIndicadorDescuento(descuentos);
	}
	
	/**
	 * Actualiza los casos con estado e indicador de pago
	 * Actualiza estado de las cuotas en caso de existir 
	 * @param pagos
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void actualizaIndicadorPago(ArrayList pagos) throws RemoteException, Exception, BusinessException {
		services.actualizaIndicadorPago(pagos);
	}
	
	/**
	 * Cierra o Elimina el Caso pasado como parametro
	 * Actualiza estado y fecha de estado 
	 * @param casoId y nuevo estado (cerrado o Eliminado)
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void cierraCasosAbiertos(String rut) throws RemoteException, Exception, BusinessException {
		services.cierraCasosAbiertos(rut);	
	}
	
	/**
	 * Crea casos en forma automática,
	 * a partir de un Collection de lineas producto de un archivo de carga
	 * @param InputUpLoadFileVO inputData
	 * @return ResultUpLoadFileVO, con el resultado del proceso completo
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ResultUpLoadFileVO updLoadFile(InputUpLoadFileVO inputData) throws RemoteException, Exception, BusinessException {
		return services.updLoadFile(inputData);
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
	public int getCuota(int monto, int cuotas, int cuotaActual) throws RemoteException, Exception, BusinessException {
		return services.getCuota(monto, cuotas, cuotaActual);
	}
	
	/** 
	 * Obtiene lista de pre-casos que tienen pendiente la generación del primer
	 * egreso en tesoreria
	 * @return ArrayList de CasoVO
	 * @throws Exception
	 */
	public ArrayList getListaPreCasosPorGenerarEgreso() throws RemoteException, Exception, BusinessException {
		
		return services.getListaPreCasosPorGenerarEgreso();
	}


	/** 
	 * Cambia el estado de cada "pre-caso" (caso) desde el estado:
	 * STD_INGRESADO a STD_ACTIVO
	 * @param ArrayList listaPreCasos
	 * @return void
	 * @throws Exception
	 */
	public void activarListaPreCasos(ArrayList listaPreCasos) throws RemoteException, Exception, BusinessException {
		
		services.activarListaPreCasos(listaPreCasos);
	}
	
	/**
	 * Genera los egresos en tesoreria correspondientes a los profesionales  
	 * @param DatosMovimientoTesoreriaVO
	 * @param UsuarioVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void registrarEgresoProfesionalesTesoreriaPreCaso(DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO, UsuarioVO user) throws RemoteException, Exception, BusinessException{
		services.registrarEgresoProfesionalesTesoreriaPreCaso(datosMovimientoTesoreriaVO, user);
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
	public void registrarEgresoTesoreriaPreCaso(DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO, UsuarioVO user) throws RemoteException, Exception, BusinessException {
	
		services.registrarEgresoTesoreriaPreCaso(datosMovimientoTesoreriaVO, user, true, false);	
	}
	
	/**
	 * Información de los egresos y/o ingresos generados en tesoreria
	 * @param long casoId
	 * @return ArrayList de InfoMovimientoPreCasoVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getMovimientosTesoreriaPreCaso(long casoId) throws RemoteException, Exception, BusinessException {
	
		return services.getMovimientosTesoreriaPreCaso(casoId);
	}
	
	/**
	 * Genera un Ingreso en tesoreria 
	 * @param ArrayList de DatosMovimientoTesoreriaVO
	 * @param UsuarioVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void registrarIngresoTesoreriaPreCaso(ArrayList listaMovimientos, UsuarioVO user) throws RemoteException, Exception, BusinessException {
		services.registrarIngresoTesoreriaPreCaso(listaMovimientos, user);
	}
	
	/** 
	 * Obtiene la lista de Profesionales
	 * @param Profesional
	 * @return ArrayList de Profesional
	 * @throws Exception
	 */
	public ArrayList getListaProfesionales(Profesional profesional) throws RemoteException, Exception, BusinessException{
		
		return services.getListaProfesionales(profesional);
		
	}	
	
	/**
	 * Crea o actualiza profesional en Bienestar
	 * @param Profesional
	 */
	public void registrarProfesional(Profesional profesional) throws RemoteException,Exception, BusinessException{
		services.registrarProfesional(profesional);
	
	}
	
	/** 
	 * Obtiene lista de pre-casos que ya tienen por lo menos un egreso generado
	 * @return ArrayList de CasoVO
	 * @throws Exception
	 */
	public ArrayList getListaPreCasosConPorLoMenosUnEgresoGenerado() throws RemoteException,Exception, BusinessException{
		return services.getListaPreCasosConPorLoMenosUnEgresoGenerado();
	}
	/**
	 * Devuelve la suma de los egresos previos del mismo caso
	 * @param casoId
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int getMontoEgresosPrevios(long casoId) throws RemoteException,Exception, BusinessException{
		return services.getMontoEgresosPrevios(casoId);
	}
	
	/**
	 * Devuelve la suma de los ingresos previos de la isapre del mismo caso
	 * @param casoId
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int getMontoIngresosPreviosIsapre(long casoId) throws RemoteException,Exception, BusinessException{
		return services.getMontoIngresosPrevios(casoId, Constants.MOVI_INGRESO_ISAPRE);
	}	
	
	/**
	 * Devuelve la suma de los ingresos previos "otros" del mismo caso
	 * @param casoId
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int getMontoIngresosPreviosOtros(long casoId) throws RemoteException,Exception, BusinessException{
		return services.getMontoIngresosPrevios(casoId, Constants.MOVI_INGRESO_OTROS);
	}
	
	/**
	 * retorna el grupo del usuario para determinar si es admin o socio
	 * @param rut
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public int getGrupoUsuario(long rut)  throws RemoteException,Exception{
		return services.getGrupoUsuario(rut);
	}	
	
	public ArrayList listaCasosBorradorPorRut(String rut) throws RemoteException, Exception, BusinessException {
		return services.getCasosEstadoBorrador(rut);	
	}	
	
	public void actualizaIndicadorDescuentoSaldoTotal(ArrayList descuentos) throws Exception, BusinessException{
		services.actualizaIndicadorDescuentoSaldoTotal(descuentos);
	}

	/**
	 * @param l
	 * @param i
	 * @param j
	 * @return
	 */
	public int getMontoCuotaPagadoPorBienestar(long casid, int cuotaActual) throws RemoteException, Exception, BusinessException {
		
		return services.getMontoCuotaPagadoPorBienestar(casid, cuotaActual);
	}
	
	public double getMontoPrevioGeneradoDetalle(long casoId,long idDetalle) throws Exception, BusinessException{
		return services.getMontoPrevioGeneradoDetalle(casoId,idDetalle);
	}

	public ArrayList getCasosSocioInactivo(Caso caso) throws BusinessException, Exception {
		return services.getListaCasosSocioInactivo(caso);
	}
	
	/**
	 * REQ 5030
	 * @param casoId
	 * @param user
	 * @throws Exception
	 * @throws BusinessException
	 */
	public boolean liberarTopeCobertura(long casoId, String user) throws Exception, BusinessException{
		return services.liberarTopeCobertura(casoId, user);
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
		return services.getBitacoraElimCobertura(casoId);
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
		return services.documentoYaRegistrado(caso);
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
		services.cierraCasosUsuerDesvinculados(casoOriginal);
	}
		
}
