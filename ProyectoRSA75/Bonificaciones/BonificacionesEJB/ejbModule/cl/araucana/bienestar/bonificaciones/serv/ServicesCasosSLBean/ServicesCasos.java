package cl.araucana.bienestar.bonificaciones.serv.ServicesCasosSLBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.araucana.bienestar.bonificaciones.model.Caso;
import cl.araucana.bienestar.bonificaciones.model.DetalleCaso;
import cl.araucana.bienestar.bonificaciones.model.Evento;
import cl.araucana.bienestar.bonificaciones.model.Profesional;
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

import com.schema.util.GeneralException;

/**
 * Remote interface for Enterprise Bean: ServicesSocios
 */
public interface ServicesCasos extends javax.ejb.EJBObject {
	
	/** 
	 * Obtiene una lista de Eventos
	 * @param codigo de caso (caso ID)
	 * @param evento, Objeto Evento con filtros posibles
	 * @return ArrayList de Evento Dentro de un Caso
	 * @throws Exception
	 */
	public Caso getEventos(Caso caso) throws Exception, BusinessException;
	
	/**
	 * Registra un Evento asociado a un Caso
	 * @param cado Id y Evento con los datos
	 */
	public void registraEvento(long casoId, Evento evento) throws Exception, BusinessException;
	
	/** 
	 * Obtiene una lista de Detalle de Caso
	 * @param Caso
	 * @return Caso con ArrayList de Detalle Caso
	 * @throws Exception
	 */
	public Caso getDetallesCaso(Caso caso) throws Exception, BusinessException;
	
	/** 
	 * Obtiene una lista de Detalle de Caso con los montos de los detalles de egresos previos ya restados
	 * @param long casoId
	 * @return ArrayList de DetalleCaso
	 * @throws Exception, BusinessException
	 */
	public ArrayList getDetallesCasoPreCasos(long casoId) throws Exception, BusinessException;
	
	/** 
	 * Cierra un caso de tipo descuento por pago con prestamo Y/O abono del socio
	 * y registra un evento con el cierre
	 * @param Caso
	 * @param Evento
	 * @throws Exception
	 */
	public void cerrarCaso(Caso caso, Evento evento) throws Exception, BusinessException;
	
	/** 
	 * Registra la información de un aporte especial para un caso
	 * y registra un evento con el comentario dl usuario
	 * @param Caso
	 * @param Evento
	 * @throws Exception
	 */
	public void registrarAporteEspecialCaso(ParamAporteEspecialVO param, Evento evento) throws Exception, BusinessException;
	
	/*
	 * Actualiza los totales de un caso
	 * Es utilizado por los metodos:
	 * 		registraDetalle
	 * 		actualizaDetalle
	 * 		eliminaDetalle
	 */
	public Caso actualizaTotalesCaso(Caso caso) throws Exception, BusinessException;
	
	/**
	 * Crea un Detalle asociado a un Caso
	 * @param caso
	 * @param detalleCaso
	 * @return Caso con los totales actualizados
	 * @throws Exception
	 */
	public Caso registraDetalle(Caso caso,  DetalleCaso detalleCaso) throws Exception, BusinessException;
	
	
	/**
	 * Modifica un Detalle asociado a un Caso
	 * @param caso con detalles de caso
	 * @param index con posicion de detalle de caso a actualizar 
	 * @param Detalle caso con los datos actualizados 
	 */
	public Caso actualizaDetalle(Caso caso, int index, DetalleCaso detalleCaso) throws Exception, BusinessException;
	
	
	/**
	 * Elimina un Detalle asociado a un Caso
	 * @param caso con detalle de caso
	 * @param index con posicion de Detalle caso a eliminar 
	 */
	public Caso eliminaDetalle(Caso caso, int index) throws Exception, BusinessException;
	
	
	/**
	 * Crea un Caso
	 * @param caso
	 * @return caso id
	 */
	public long registraCaso(Caso caso) throws Exception, BusinessException;
	
	/**
	 * Activa un Caso
	 * @param caso
	 */
	public void activarCaso(long casoId) throws Exception, BusinessException;
	
	/**
	 * Elimina un Caso
	 * Si el caso fue o es precaso, verifica previamnete los estados
	 * de los folios que se hayan generado en tesoreria de Bienestar
	 * @param long casoId
	 * @param String usuario
	 */
	public void eliminarCaso(long casoId, String usuario) throws Exception, BusinessException;
	
	/**
	 * Actualiza un Caso
	 * @param caso
	 */
	public void actualizarCaso(Caso caso) throws Exception, BusinessException;
	
	/** 
	 * Obtiene informacion de un Caso
	 * @param codigo de caso (caso ID)
	 * @return CasoVO
	 * @throws Exception
	 */
	public CasoVO getCasoVO(long casoId) throws Exception, BusinessException;
	
	/** 
	 * Obtiene una lista de casos que corresponden a compra de bonos
	 * @param rut Socio
	 * @return ArrayList de Caso
	 * @throws Exception
	 */
	public ArrayList getBonos(String rutSocio) throws Exception, BusinessException;
	
	/** 
	 * Obtiene una lista de casos que corresponden a compra de bonos
	 * @param rut Socio
	 * @return ArrayList de Caso
	 * @throws Exception
	 */
	public ArrayList getReembolsos(String rutSocio) throws Exception, BusinessException;
	
	/** 
	 * Obtiene una lista de casos no bonificados
	 * @param
	 * @return ArrayList de Caso
	 * @throws Exception
	 */
	public ArrayList getCasosNoBonificados(String tipoCaso) throws Exception, BusinessException;
	
	/** 
	 * Obtiene una lista con los Casos Por Reembolsar
	 * segun las fecha indicadas
	 * @param ParamOperacionesVO 
	 * @return ArrayList de ReembolsosVO
	 * @throws Exception
	 */
	public ArrayList getCasosPorReembolsar(ParamOperacionesVO param, String rut) throws Exception, BusinessException;
	
	/** 
	 * Obtiene una lista con los Casos Por Descontar
	 * segun las fecha indicadas
	 * @param ParamOperacionesVO
	 * @return ArrayList de DescuentoVO
	 * @throws Exception
	 */
	public ArrayList getCasosPorDescontar(ParamOperacionesVO param) throws Exception, BusinessException;

	/** 
	 * Obtiene una lista con los Casos Por Descontar
	 * segun las fecha indicadas
	 * @param ParamOperacionesVO
	 * @return ArrayList de DescuentoVO
	 * @throws Exception
	 */
	public ArrayList getCasoPorDescontar(long casid) throws Exception, BusinessException;
		
	/** 
	 * Obtiene una lista con los Casos Por Pagar
	 * @return ArrayList de PagoConvenioVO
	 * @throws Exception
	 */
	public ArrayList getCasosPorPagar(long codigoDescuento) throws Exception, BusinessException;
	
	/** 
	 * Obtiene lista de casos
	 * @param Caso con los filtros
	 * @return ArrayList de Caso
	 * @throws Exception
	 */
	public ArrayList getListaCasos(Caso caso) throws Exception, BusinessException;
	
	/** 
	 * REQ 4969
	 * Obtiene lista de casos
	 * @param Caso con los filtros
	 * @return ArrayList de Caso
	 * @throws Exception
	 */
	public ArrayList getListaCasosSocioInactivo(Caso caso) throws Exception, BusinessException;
		
	/** 
	 * Obtiene lista de casos asociados a una cobertura
	 * @param codigoCobertura
	 * @return ArrayList de Caso
	 * @throws Exception
	 */
	public ArrayList getListaCasosCobertura(long codigoCobertura) throws Exception, BusinessException;
	
	/** 
	 * Obtiene una lista de Cuotas
	 * @param codigo de caso (caso ID)
	 * @return Caso con ArrayList de Cuota
	 * @throws Exception
	 */
	public Caso getCuotasCaso(Caso caso) throws Exception, BusinessException;
	
	/**
	 * Calcula la bonificacion de un caso (sólo en memoria)
	 * @param casoId
	 * @return Caso con informacion de la bonificacion realizada
	 * @throws Exception
	 */
	public CasoVO calcularBonificacionCaso(long casoId, boolean simulando) throws Exception, BusinessException;
	
	/**
	 * Calcula la bonificacion de un caso
	 * actualiza la información del caso bonificado
	 * @param casoId
	 * @return
	 * @throws Exception
	 */
	public void bonificarCaso(long casoId) throws Exception, BusinessException;
	
	/**
	 * Calcula la bonificacion de los casos solicitados
	 * actualiza la información de los casos bonificados
	 * @param ArrayList con casoId
	 * @return
	 * @throws Exception
	 */
	public void bonificar(ArrayList casos) throws Exception, BusinessException;
	
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
	public ArrayList resumenMovimientosBeneficiario(ParamResumenMovimientosVO parametros, ResumenMovimientosBeneficiarioVO resumenFiltro) throws Exception, BusinessException;
	
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
	public ArrayList resumenMovimientosConvenio(ParamResumenMovimientosVO parametros, ResumenMovimientosConvenioVO resumenFiltro) throws Exception, BusinessException;
	
	/**
	 * Actualiza los casos con estado e indicador de reembolso 
	 * @param reembolsos
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void actualizaIndicadorReembolso(ArrayList reembolsos) throws Exception, BusinessException;
	
	/**
	 * Actualiza los casos con estado e indicador de descuento
	 * Actualiza estado de las cuotas en caso de existir 
	 * @param descuentos
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void actualizaIndicadorDescuento(ArrayList descuentos) throws Exception, BusinessException;
	
	/**
	 * Actualiza los casos con estado e indicador de pago
	 * Actualiza estado de las cuotas en caso de existir 
	 * @param pagos
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void actualizaIndicadorPago(ArrayList pagos) throws Exception, BusinessException;
	
	/**
	 * Cierra o Elimina el Caso pasado como parametro
	 * Actualiza estado y fecha de estado 
	 * @param casoId y nuevo estado (cerrado o Eliminado)
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void cierraCasosAbiertos(String rut) throws Exception, BusinessException;
	
	/**
	 * Crea casos en forma automática,
	 * a partir de un Collection de lineas producto de un archivo de carga
	 * @param InputUpLoadFileVO inputData
	 * @return ResultUpLoadFileVO, con el resultado del proceso completo
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ResultUpLoadFileVO updLoadFile(InputUpLoadFileVO inputData) throws Exception, BusinessException;
	

	/**
	 * Se utiliza para calcular el monto de una cuota en particular
	 * @param monto
	 * @param cuotas
	 * @param cuotaActual
	 * @return int montoCuotaActual
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int getCuota(int monto, int cuotas, int cuotaActual) throws Exception, BusinessException;
	
	/** 
	 * Obtiene lista de pre-casos que tienen pendiente la generación del primer
	 * egreso en tesoreria
	 * @return ArrayList de CasoVO
	 * @throws Exception
	 */
	public ArrayList getListaPreCasosPorGenerarEgreso() throws Exception, BusinessException;


	/** 
	 * Cambia el estado de cada "pre-caso" (caso) desde el estado:
	 * STD_INGRESADO a STD_ACTIVO
	 * @param ArrayList listaPreCasos
	 * @return void
	 * @throws Exception
	 */
	public void activarListaPreCasos(ArrayList listaPreCasos) throws Exception, BusinessException;
	
	/**
	 * Genera los egresos en tesoreria correspondientes a los profesionales  
	 * @param DatosMovimientoTesoreriaVO
	 * @param UsuarioVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void registrarEgresoProfesionalesTesoreriaPreCaso(DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO, UsuarioVO user) throws Exception, BusinessException;	
	
	/**
	 * Genera un Egreso en tesoreria 
	 * @param DatosMovimientoTesoreriaVO
	 * @param UsuarioVO
	 * @param boolean validarEstadoPrevio
	 * @param boolean sonVariosAlMismoCaso
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void registrarEgresoTesoreriaPreCaso(DatosMovimientoTesoreriaVO datosMovimientoTesoreriaVO, UsuarioVO user, boolean validarEstadoPrevio, boolean sonVariosAlMismoCaso) throws Exception, BusinessException;
	
	/**
	 * Información de los egresos y/o ingresos generados en tesoreria
	 * @param long casoId
	 * @return ArrayList de InfoMovimientoPreCasoVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getMovimientosTesoreriaPreCaso(long casoId) throws Exception, BusinessException;
	
	/**
	 * Genera un Ingreso en tesoreria 
	 * @param ArrayList de DatosMovimientoTesoreriaVO
	 * @param UsuarioVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void registrarIngresoTesoreriaPreCaso(ArrayList listaMovimientos, UsuarioVO user) throws Exception, BusinessException;
	
	/** 
	 * Obtiene la lista de Profesionales
	 * @param Profesional
	 * @return ArrayList de Profesional
	 * @throws Exception
	 */
	public ArrayList getListaProfesionales(Profesional profesional) throws Exception, BusinessException;		

	/**
	 * Crea o actualiza profesional en Bienestar
	 * @param Profesional
	 */
	public void registrarProfesional(Profesional profesional) throws Exception, BusinessException;

	/** 
	 * Obtiene lista de pre-casos que ya tienen por lo menos un egreso generado
	 * @return ArrayList de CasoVO
	 * @throws Exception
	 */
	public ArrayList getListaPreCasosConPorLoMenosUnEgresoGenerado() throws Exception, BusinessException;

	/**
	 * Devuelve la suma de los egresos previos del mismo caso
	 * @param casoId
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int getMontoEgresosPrevios(long casoId) throws Exception, BusinessException;

	/**
	 * Devuelve la suma de los ingresos previos del mismo caso
	 * @param casoId
	 * @param String tipoIngreso
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int getMontoIngresosPrevios(long casoId, String tipoIngreso) throws Exception, BusinessException;


	/**
	 * retorna el grupo del usuario para determinar si es admin o socio
	 * @param rut
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public int getGrupoUsuario(long rut) throws Exception,SQLException;

	public ArrayList getCasosEstadoBorrador(String rut) throws Exception, BusinessException ;
	
	public void actualizaIndicadorDescuentoSaldoTotal(ArrayList descuentos) throws Exception, BusinessException;

	/**
	 * @param casid
	 * @param cuotaActual
	 * @return
	 */
	public int getMontoCuotaPagadoPorBienestar(long casid, int cuotaActual) throws Exception, BusinessException;
	
	/**
	 * 
	 * @param casoId
	 * @param idDetalle
	 * @return double
	 * @throws Exception
	 * @throws BusinessException
	 */
	public double getMontoPrevioGeneradoDetalle(long casoId,long idDetalle) throws Exception, BusinessException;
	
	/**
	 * REQ 5030
	 * @param casoId
	 * @param user
	 * @throws Exception
	 * @throws BusinessException
	 */
	public boolean liberarTopeCobertura(long casoId, String user) throws Exception, BusinessException;
	
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
	public List getBitacoraElimCobertura(long casoId) throws Exception, BusinessException;
	
	/** 
	 * Registra la información de un aporte especial para un caso
	 * y registra un evento con el comentario del usuario sin utilizar tope personal de cobertura (REQ-5088)
	 * @param Caso
	 * @param Evento
	 * @throws Exception
	 */
	public void registrarAporteEspecialSinTopeCobertura(ParamAporteEspecialVO param, Evento evento) throws Exception, BusinessException;
	
	/**
	 * REQ 5265 (Validar documento)
	 * Valida si el documento ya se encuentra registrado previamente.
	 * Retorna true si el documento se encuentra registrado previamente, false en caso contrario.
	 * @param caso
	 * @return boolean
	 * @throws BusinessException
	 * @throws Exception
	 */
	public boolean documentoYaRegistrado(Caso caso) throws BusinessException, Exception;
	
	/**
	 * Cierra o Elimina los casos del rut pasado como parametro
	 * Actualiza estado y fecha de estado
	 * Genera evento automatico 
	 * @param Caso caso
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void cierraCasosUsuerDesvinculados(Caso casoOriginal) throws Exception, BusinessException;
	
}

