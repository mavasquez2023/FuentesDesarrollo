package cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesSLBean;

import java.rmi.RemoteException;
import java.util.ArrayList;

import cl.araucana.bienestar.bonificaciones.model.Concepto;
import cl.araucana.bienestar.bonificaciones.vo.AporteBienestarVO;
import cl.araucana.bienestar.bonificaciones.vo.ContabilidadPendienteVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleAporteBienestarVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleDescuentosConveniosVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleDescuentosOficinaVO;
import cl.araucana.bienestar.bonificaciones.vo.DetalleDescuentosSocioVO;
import cl.araucana.bienestar.bonificaciones.vo.InformeDescuentosConveniosVO;
import cl.araucana.bienestar.bonificaciones.vo.InformeDescuentosVO;
import cl.araucana.bienestar.bonificaciones.vo.InformePagoConvenioVO;
import cl.araucana.bienestar.bonificaciones.vo.InformeReemBancoVO;
import cl.araucana.bienestar.bonificaciones.vo.InformeReembolsosVO;
import cl.araucana.bienestar.bonificaciones.vo.ParamResumenMovimientosVO;
import cl.araucana.bienestar.bonificaciones.vo.ReembolsoTotalVO;
import cl.araucana.bienestar.bonificaciones.vo.UsuarioVO;
import cl.araucana.common.BusinessException;

/**
 * Remote interface for Enterprise Bean: ServicesSocios
 */
public interface ServicesOperaciones extends javax.ejb.EJBObject {
	
	/** 
	 * Obtiene la lista de conceptos existentes
	 * @param tipo de concepto
	 * @return ArrayList de Concepto
	 * @throws Exception
	 */
	public ArrayList getConceptos() throws Exception, BusinessException;
	
	/** 
	 * Obtiene un concepto existente
	 * @param codigo y tipo de concepto
	 * @return Concepto
	 * @throws Exception
	 */
	public Concepto getConcepto(long codigo) throws Exception, BusinessException;
	
	/**
	 * Crea un nuevo concepto en Bienestar
	 * @param concepto: el Objeto Concepto
	 */
	public void creaConcepto(Concepto concepto) throws RemoteException,BusinessException,Exception;
	
	/**
	 * Actualiza un concepto de Bienestar
	 * @param concepto: el Objeto Concepto
	 */
	public void actualizaConcepto(Concepto concepto) throws Exception, RemoteException,BusinessException;
	
	/**
	 * Elimina un concepto de Bienestar
	 * @param concepto: el Objeto Concepto
	 */
	public void eliminaConcepto(Concepto concepto) throws Exception, BusinessException;

	/**
	 * Recupera un Parametro Existente
	 * @return Parametro
	 * @throws Exception
	 * @throws BusinessException
	 */
	//public ArrayList getParametro(String codigoParametro) throws Exception, BusinessException;
	
	/**
	 * Recupera la lista de Parametros Existentes
	 * @return Parametro
	 * @throws Exception
	 * @throws BusinessException
	 */
	//public ArrayList getListaParametros() throws Exception, BusinessException;

	/**
	 * Actualiza un Parametro
	 * @param parametro
	 */
	//public void actualizaParametro(Parametro parametro) throws Exception, BusinessException;
	
	/**
	 * Genera los reembolsos para los casos pasados en el array de casos ID
	 * @param casos
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void generarReembolsos(ArrayList reembolsos,UsuarioVO usuario) throws Exception, BusinessException;
	
	/**
	 * Genera los descuentos para los casos pasados en el array de casos ID
	 * @param casos
	 * @param UsuarioVO usuario
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void generarDescuentos(ArrayList descuentos,UsuarioVO usuario) throws Exception, BusinessException;
	
	/**
	 * Genera los pagos que se deben realizar a los Convenios, para los casos
	 * pasados en el array de casos ID
	 * @param pagos (casos ID)
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void generarPagoConvenios(long codigoDescuento,UsuarioVO usuario) throws Exception, BusinessException;
	
	/**
	 * Genera una lista con los periodos que aún no se han contabilizado
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPeriodosPorContabilizar() throws Exception, BusinessException;
	
	/**
	 * Genera Los Asientos contables que requiere Contabilidad
	 * @param fecha
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void generarAsientosContables(ContabilidadPendienteVO contabilizar) throws Exception, BusinessException;
	
	/**
	 * Obtiene información los reembolsos generados semanalmente
	 * @param reembolsoCodigo, si es 0 (cero) los trae todos
	 * @return 
	 * @throws BusinessException
	 * @throws Exception
	 */
	public ArrayList getReembolsosTotales(long reembolsoCodigo) throws BusinessException,Exception;
	
	/**
	 * Obtiene información los aportes realizados por bienestar en las diferentes coberturas
	 * @param ParamResumenMovimientosVO
	 * @param AporteBienestarVO con filtros
	 * @return AporteBienestarVO
	 * @throws BusinessException
	 * @throws Exception
	 */
	public AporteBienestarVO getResumenAportesBienestar(ParamResumenMovimientosVO parametros,DetalleAporteBienestarVO detalleAporteBienestar) throws BusinessException,Exception;
	
	/**
	 * Genera el informe de los reembolsos generados semanalmente
	 * @param reembolsoCodigo
	 * @return 
	 * @throws BusinessException
	 * @throws Exception
	 */
	public InformeReembolsosVO getInformeReembolsos(long reembolsoCodigo) throws BusinessException,Exception;
	
	/** 
	 * Obtiene información de los codigos de descuento generados y la fecha de
	 * descuento. (Los cuales se realizan en forma mensual)
	 * @param 
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getDescuentosRealizados() throws Exception, BusinessException;
	
	/** 
	 * Obtiene información de los codigos de descuento en los cuales el socio ha enido
	 * descuento. (Los cuales se realizan en forma mensual)
	 * @param String rutSocio
	 * @return ArrayList de InformeDescuentosVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getDescuentosRealizadosBySocio(String rutSocio) throws Exception, BusinessException;	
	
	
	/**
	 * Genera el informe de los descuentos generados mensualmente
	 * @param fecha
	 * @return 
	 * @throws BusinessException
	 * @throws Exception
	 */
	public InformeDescuentosVO getInformeDescuentos(InformeDescuentosVO informe) throws BusinessException,Exception;
	
	/**
	 * Genera el detalle del descuento realizado a una oficina
	 * @param codigo de Descuento, codigo de oficina
	 * @return 
	 * @throws BusinessException
	 * @throws Exception
	 */
	public DetalleDescuentosOficinaVO getDetalleDescuentosOficina(String codigoOficina, long codigoDescuento) throws BusinessException,Exception;
	
	/**
	 * Genera el detalle del descuento realizado a un socio
	 * @param codigo de Descuento
	 * @return 
	 * @throws BusinessException
	 * @throws Exception
	 */
	public DetalleDescuentosSocioVO getDetalleDescuentoSocio(String rut, long codigoDescuento) throws BusinessException,Exception;
	
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
	public ArrayList getDescuentosConvenios() throws Exception, BusinessException;
	
	/**
	 * Genera el informe de los descuentos que se deben realizar a los convenios mensualmente
	 * @param long codigoConvenio, long codigoDescuento
	 * @return InformeDescuentosConveniosVO
	 * @throws BusinessException
	 * @throws Exception
	 */
	public InformeDescuentosConveniosVO getInformeDescuentosConvenios(long codigoConvenio, long codigoDescuento) throws BusinessException,Exception;
	
	/**
	 * Genera el detalle del descuento que se debe realizar a un convenio
	 * @param DetalleDescuentosConveniosVO, codigo de convenio
	 * @return 
	 * @throws BusinessException
	 * @throws Exception
	 */
	public DetalleDescuentosConveniosVO getDetalleDescuentosConvenio(DetalleDescuentosConveniosVO reporte, long codigoDescuento) throws BusinessException,Exception;

	
	/** 
	 * Obtiene información de los codigos de pagos generados y la fecha de
	 * pago. (Los cuales se realizan en forma mensual)
	 * @param 
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPagosRealizados() throws Exception, BusinessException;
	
	/** 
	 * Obtiene información de los convenios que presentan pago 
	 * para el codigo de pago pasado como parametro
	 * @param 
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public InformePagoConvenioVO getInformePagoConvenio(InformePagoConvenioVO informe) throws Exception, BusinessException;
	
	/** 
	 * Obtiene información de los codigos de descuento
	 * que aún no se han pagado
	 * @param 
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPagosPorRealizar() throws Exception, BusinessException;

	/** 
	 * Obtiene información de los pagos que se deben realizar a 
	 * los convenios
	 * @param 
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPagoConvenioPendientes(long codigoDescuento) throws Exception, BusinessException;
	
	/** 
	 * Obtiene información de las cuotas de los prestamos que
	 * se encuentran vigentes
	 * 
	 * @return ArrayList de CuotaPrestamo
	 * @throws Exception
	 */
	public ArrayList getCuotasPrestamoVigenteSocio(String rut) throws Exception, BusinessException;
	
	/** 
	 * Obtiene información de los descuentos aplicados a un Socio
	 * 
	 * @return ArrayList de CuotaPrestamo
	 * @throws Exception
	 */
	public ArrayList getDescuentosAplicadosSocio(String rut) throws Exception, BusinessException;
	
	/**
	 * Retorna una lista que contiene:
	 * Tipo, codigo y descripción de un parametro utilizado en la aplicación
	 * @param tipo: india si es de tipo cuenta contable o concepto de tesoreria
	 * @param subTipo: se utliza en caso de cuenta contable para solicitar un tipo de cuenta especial
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getParametros(int tipo,String subTipo) throws Exception,BusinessException;
	
	/**
	 * req 4353
	 * @param rutSocio
	 * @param descuentos
	 * @param usuario
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void generarProcesoCasoIndividual(String rutSocio,ArrayList descuentos, UsuarioVO usuario) throws Exception, BusinessException;

	/**
	 * req. 4353 encargado de generar el detalle del comprobante e invocar al metodo registrarMovimientoTesoreriaBienestar de tesoreriaEJB
	 */
	public long generarComprobanteIngresoBienestar(long idCaso,int montoDescuento,String usuarioCreador,String rutSocio,long codConvenio) throws Exception,BusinessException;
	
	
	/**
	 * Genera el informe de los reembolsos con detale por banco  (asepulveda 2013-04-22)
	 * @param reembolsoCodigo
	 * @return InformeReemBancoVO
	 * @throws BusinessException
	 * @throws Exception
	 */
	public InformeReemBancoVO getInformeReemBanco(long reembolsoCodigo) throws BusinessException,Exception;
	
	/**
	 * Obtiene la información del ultimo reembolso
	 * 
	 * @return ReembolsoTotalVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ReembolsoTotalVO getUltimoReembolsoTotal() throws Exception, BusinessException;
	
	/*
	 * Se encarga de enviar un e-mail de aviso de los reembolsos a cada uno de los socios que tengan un reembolso en el proceso de reembolso indicado y que tengan registrado un e-mail 
	 */
	public void informarReembolsosViaEMail(long codigoReembolso) throws Exception;
	
	/*
	 * Genera el archivo de reembolsos para el banco
	 */
	public void generarArchivoBanco(long codigoReembolso) throws Exception;
	
}

