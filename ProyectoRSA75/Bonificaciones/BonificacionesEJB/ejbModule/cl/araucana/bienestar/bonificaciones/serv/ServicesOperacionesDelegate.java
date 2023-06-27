package cl.araucana.bienestar.bonificaciones.serv;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import cl.araucana.bienestar.bonificaciones.model.Concepto;
import cl.araucana.bienestar.bonificaciones.model.Parametro;
import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesSLBean.ServicesOperaciones;
import cl.araucana.bienestar.bonificaciones.serv.ServicesOperacionesSLBean.ServicesOperacionesHome;
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
import cl.araucana.common.env.AppConfig;

import com.schema.patterns.BusinessDelegate;
import com.schema.util.FileSettings;
import com.schema.util.GeneralException;

/**
 * @author asepulveda
 * Business Delegate para Servicios de Operaciones de Bienestar
 */
public class ServicesOperacionesDelegate extends BusinessDelegate {
	// Home y Remote del EJB
	private final static Class homeClass=ServicesOperacionesHome.class;
	private ServicesOperaciones services=null;
	
	Logger logger = Logger.getLogger(ServicesOperacionesDelegate.class);
	
	/**
	 * Constructor del BDelegate. Establece la conexion con el EJB
	 * @throws GeneralException
	 */
	public ServicesOperacionesDelegate() throws GeneralException {
		// Nombre JNDI del EJB
		String jndi = FileSettings.getValue(AppConfig.getInstance().settingsFileName,
		              "/application-settings/ejbs/bonificaciones/operaciones-services");
		              
		try {
			InitialContext ic=new InitialContext();
			services = (ServicesOperaciones)super.getServiceBean(ic, jndi,homeClass);
		} catch (Exception e) {
			throw new GeneralException(this,e);
		}
	}

	/** 
	 * Obtiene la lista de conceptos existentes
	 * @param tipo de concepto
	 * @return ArrayList de Concepto
	 * @throws Exception
	 */
	public ArrayList getConceptos() throws RemoteException, Exception, BusinessException {
		return services.getConceptos();
	}
	
	/** 
	 * Obtiene un concepto existente
	 * @param codigo y tipo de concepto
	 * @return Concepto
	 * @throws Exception
	 */
	public Concepto getConcepto(long codigo) throws RemoteException, Exception , BusinessException{
		return services.getConcepto(codigo);
	}
	
	/**
	 * Crea un nuevo concepto en Bienestar
	 * @param concepto: el Objeto Concepto
	 */
	public void creaConcepto(Concepto concepto) throws RemoteException, BusinessException,Exception {
		services.creaConcepto(concepto);
	}
	
	/**
	 * Actualiza un concepto de Bienestar
	 * @param concepto: el Objeto Concepto
	 */
	public void actualizaConcepto(Concepto concepto) throws RemoteException, BusinessException, Exception {
		services.actualizaConcepto(concepto);
	}
	
	/**
	 * Elimina un concepto de Bienestar
	 * @param concepto: el Objeto Concepto
	 */
	public void eliminaConcepto(Concepto concepto) throws RemoteException, Exception, BusinessException {
		services.eliminaConcepto(concepto);
	}
	

	/**
	 * Genera los reembolsos para los casos pasados en el array de casos ID
	 * @param casos
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void generarReembolsos(ArrayList reembolsos,UsuarioVO usuario) throws RemoteException, Exception, BusinessException {
		services.generarReembolsos(reembolsos,usuario);
	}
	
	/**
	 * Genera los descuentos para los casos pasados en el array de casos ID
	 * @param casos
	 * @param UsuarioVO usuario
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void generarDescuentos(ArrayList descuentos,UsuarioVO usuario) throws RemoteException, Exception, BusinessException {
		services.generarDescuentos(descuentos,usuario);
	}
	
	/**
	 * Genera los pagos que se deben realizar a los Convenios, para los casos
	 * pasados en el array de casos ID
	 * @param pagos (casos ID)
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void generarPagoConvenios(long codigoDescuento,UsuarioVO usuario) throws RemoteException, Exception, BusinessException {
		services.generarPagoConvenios(codigoDescuento,usuario);
	}
	
	/**
	 * Genera una lista con los periodos que aún no se han contabilizado
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPeriodosPorContabilizar() throws RemoteException, Exception, BusinessException {
		return services.getPeriodosPorContabilizar();
	}
	
	/**
	 * Genera Los Asientos contables que requiere Contabilidad
	 * @param fecha
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void generarAsientosContables(ContabilidadPendienteVO contabilizar) throws RemoteException, Exception, BusinessException {
		services.generarAsientosContables(contabilizar);
	}
	
	/**
	 * Obtiene información los reembolsos generados semanalmente
	 * @param reembolsoCodigo, si es 0 (cero) los trae todos
	 * @return 
	 * @throws BusinessException
	 * @throws Exception
	 */
	public ArrayList getReembolsosTotales() throws RemoteException, BusinessException,Exception{
		return services.getReembolsosTotales(0);
	}
	
	/**
	 * Obtiene información los aportes realizados por bienestar en las diferentes coberturas
	 * @param ParamResumenMovimientosVO
	 * @param AporteBienestarVO con filtros
	 * @return AporteBienestarVO
	 * @throws BusinessException
	 * @throws Exception
	 */
	public AporteBienestarVO getResumenAportesBienestar(ParamResumenMovimientosVO parametros,DetalleAporteBienestarVO detalleAporteBienestar) throws RemoteException, BusinessException,Exception {
		return services.getResumenAportesBienestar(parametros, detalleAporteBienestar);
	}

	
	/**
	 * Genera el informe de los reembolsos generados semanalmente
	 * @param reembolsoCodigo
	 * @return 
	 * @throws BusinessException
	 * @throws Exception
	 */
	public InformeReembolsosVO getInformeReembolsos(long reembolsoCodigo) throws RemoteException, BusinessException,Exception{
		return services.getInformeReembolsos(reembolsoCodigo);
	}
	
	/** 
	 * Obtiene información de los codigos de descuento generados y la fecha de
	 * descuento. (Los cuales se realizan en forma mensual)
	 * @param 
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getDescuentosRealizados() throws RemoteException, Exception, BusinessException {
		return services.getDescuentosRealizados();	
	}
	
	/** 
	 * Obtiene información de los codigos de descuento en los cuales el socio ha enido
	 * descuento. (Los cuales se realizan en forma mensual)
	 * @param String rutSocio
	 * @return ArrayList de InformeDescuentosVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getDescuentosRealizadosBySocio(String rutSocio) throws RemoteException, Exception, BusinessException{
		return services.getDescuentosRealizadosBySocio(rutSocio);		
	}	
	
	/**
	 * Genera el informe de los descuentos generados mensualmente
	 * @param fecha
	 * @return 
	 * @throws BusinessException
	 * @throws Exception
	 */
	public InformeDescuentosVO getInformeDescuentos(InformeDescuentosVO informe) throws RemoteException, BusinessException,Exception{
		return services.getInformeDescuentos(informe);	
	}
	
	/**
	 * Genera el detalle del descuento realizado a una oficina
	 * @param codigo de Descuento, codigo de oficina
	 * @return 
	 * @throws BusinessException
	 * @throws Exception
	 */
	public DetalleDescuentosOficinaVO getDetalleDescuentosOficina(String codigoOficina, long codigoDescuento) throws RemoteException, BusinessException,Exception{
		return services.getDetalleDescuentosOficina(codigoOficina,codigoDescuento);
	}
	
	/**
	 * Genera el detalle del descuento realizado a un socio
	 * @param codigo de Descuento
	 * @return 
	 * @throws BusinessException
	 * @throws Exception
	 */
	public DetalleDescuentosSocioVO getDetalleDescuentoSocio(String rut, long codigoDescuento) throws RemoteException, BusinessException,Exception{
		return services.getDetalleDescuentoSocio(rut,codigoDescuento);
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
	public ArrayList getDescuentosConvenios() throws RemoteException, Exception, BusinessException {
		return services.getDescuentosConvenios();
	}
	
	/**
	 * Genera el informe de los descuentos que se deben realizar a los convenios mensualmente
	 * @param long codigoConvenio, long codigoDescuento
	 * @return InformeDescuentosConveniosVO
	 * @throws BusinessException
	 * @throws Exception
	 */
	public InformeDescuentosConveniosVO getInformeDescuentosConvenios(long codigoDescuento) throws RemoteException, BusinessException,Exception{
		return services.getInformeDescuentosConvenios(0, codigoDescuento);
	}
	
	/**
	 * Genera el informe de los descuentos que se deben realizar a los convenios mensualmente
	 * @param long codigoConvenio, long codigoDescuento
	 * @return InformeDescuentosConveniosVO
	 * @throws BusinessException
	 * @throws Exception
	 */
	public InformeDescuentosConveniosVO getInformeDescuentosConveniosByConvenio(long codigoConvenio, long codigoDescuento) throws RemoteException, BusinessException,Exception{
		return services.getInformeDescuentosConvenios(codigoConvenio, codigoDescuento);
	}
	
	/**
	 * Genera el detalle del descuento que se debe realizar a un convenio
	 * @param DetalleDescuentosConveniosVO, codigo de convenio
	 * @return 
	 * @throws BusinessException
	 * @throws Exception
	 */
	public DetalleDescuentosConveniosVO getDetalleDescuentosConvenio(DetalleDescuentosConveniosVO reporte, long codigoDescuento) throws RemoteException,BusinessException,Exception{
		return services.getDetalleDescuentosConvenio(reporte, codigoDescuento);
	}
	
	/** 
	 * Obtiene información de los codigos de pagos generados y la fecha de
	 * pago. (Los cuales se realizan en forma mensual)
	 * @param 
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPagosRealizados() throws RemoteException, Exception, BusinessException {
		return services.getPagosRealizados();
	}
	
	/** 
	 * Obtiene información de los convenios que presentan pago 
	 * para el codigo de pago pasado como parametro
	 * @param 
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public InformePagoConvenioVO getInformePagoConvenio(InformePagoConvenioVO informe) throws RemoteException, Exception, BusinessException {
		return services.getInformePagoConvenio(informe);
	}
	
	/** 
	 * Obtiene información de los codigos de descuento
	 * que aún no se han pagado
	 * @param 
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPagosPorRealizar() throws RemoteException, Exception, BusinessException {
		return services.getPagosPorRealizar();
	}

	/** 
	 * Obtiene información de los pagos que se deben realizar a 
	 * los convenios
	 * @param 
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getPagoConvenioPendientes(long codigoDescuento) throws RemoteException, Exception, BusinessException {
		return services.getPagoConvenioPendientes(codigoDescuento);
	}
	
	/** 
	 * Obtiene información de las cuotas de los prestamos que
	 * se encuentran vigentes
	 * 
	 * @return ArrayList de CuotaPrestamo
	 * @throws Exception
	 */
	public ArrayList getCuotasPrestamoVigenteSocio(String rut) throws RemoteException, Exception, BusinessException {
		return services.getCuotasPrestamoVigenteSocio(rut);	
	}
	
	/** 
	 * Obtiene información de los descuentos aplicados a un Socio
	 * 
	 * @return ArrayList de CuotaPrestamo
	 * @throws Exception
	 */
	public ArrayList getDescuentosAplicadosSocio(String rut) throws RemoteException, Exception, BusinessException {
		return services.getDescuentosAplicadosSocio(rut);	
	}
	
	/**
	 * Obtiene la Lista de Cuentas Acreedoras
	 * @param 
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getCuentasAcreedores() throws RemoteException, Exception,BusinessException {
		return services.getParametros(Parametro.TIPO_CUENTA_CONTABLE,Parametro.SUB_TIPO_CUENTAS_ACREEDORES);
	}
	
	/**
	 * Obtiene la Lista de Cuentas Deudoras
	 * @param 
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getCuentasDeudores() throws RemoteException, Exception,BusinessException {
		return services.getParametros(Parametro.TIPO_CUENTA_CONTABLE,Parametro.SUB_TIPO_CUENTAS_DEUDORES);
	}
	
	/**
	 * Obtiene la Lista de Cuentas de Gasto
	 * @param 
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getCuentasGasto() throws RemoteException, Exception,BusinessException {
		return services.getParametros(Parametro.TIPO_CUENTA_CONTABLE,Parametro.SUB_TIPO_CUENTAS_EGRESOS);
	}
	
	/**
	 * Obtiene la Lista de Conceptos de bonificaciones de tesoreria de Bienestar relacionados con los convenios
	 * @param 
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getConceptosTesoreriaBienestarConveniosIngresos() throws RemoteException, Exception,BusinessException {
		return services.getParametros(Parametro.TIPO_CONCEPTO_TESORERIA_BIENESTAR_CONVENIOS_INGRESOS,Parametro.SUB_TIPO_TODOS);
	}
	
	/**
	 * Obtiene la Lista de Conceptos de bonificaciones de tesoreria de Bienestar relacionados con los convenios
	 * @param 
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getConceptosTesoreriaBienestarConveniosEgresos() throws RemoteException, Exception,BusinessException {
		return services.getParametros(Parametro.TIPO_CONCEPTO_TESORERIA_BIENESTAR_CONVENIOS_EGRESOS,Parametro.SUB_TIPO_TODOS);
	}	
	
	/**
	 * Obtiene la Lista de Conceptos de bonificaciones de tesoreria de Bienestar relacionados con la salud
	 * @param 
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getConceptosTesoreriaBienestarSaludIngresos() throws RemoteException, Exception,BusinessException {
		return services.getParametros(Parametro.TIPO_CONCEPTO_TESORERIA_BIENESTAR_SALUD_INGRESOS,Parametro.SUB_TIPO_TODOS);
	}
	
	/**
	 * Obtiene la Lista de Conceptos de bonificaciones de tesoreria de Bienestar relacionados con la salud
	 * @param 
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getConceptosTesoreriaBienestarSaludEgresos() throws RemoteException, Exception,BusinessException {
		return services.getParametros(Parametro.TIPO_CONCEPTO_TESORERIA_BIENESTAR_SALUD_EGRESOS,Parametro.SUB_TIPO_TODOS);
	}	
	
	/**
	 * Obtiene la Lista de Conceptos de bonificaciones de tesoreria de Bienestar relacionados con la salud
	 * @param 
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ArrayList getAreasTesoreria() throws RemoteException, Exception,BusinessException {
		return services.getParametros(Parametro.TIPO_AREA_TESORERIA,Parametro.SUB_TIPO_AREA_SELECCIONABLE);
	}

	/**
	 * req 4353
	 */
	public void generarProcesoCasoIndividual(String rutSocio,ArrayList descuentos,UsuarioVO usuario) throws BusinessException, Exception {
		services.generarProcesoCasoIndividual(rutSocio,descuentos,usuario);	
	}

	/**
	 * req. 4353 encargado de generar el detalle del comprobante e invocar al metodo registrarMovimientoTesoreriaBienestar de tesoreriaEJB
	 */
	public long generarComprobanteIngresoBienestar(long idCaso,long montoDescuento,String usuarioCreador,String rutSocio,long codConvenio)  throws BusinessException, Exception {
		return services.generarComprobanteIngresoBienestar(idCaso,(int) montoDescuento,usuarioCreador,rutSocio,codConvenio);
	}
	
	/**
	 * Genera el informe de los reembolsos con detale por banco  (asepulveda 2013-04-22)
	 * @param reembolsoCodigo
	 * @return InformeReemBancoVO
	 * @throws BusinessException
	 * @throws Exception
	 */
	public InformeReemBancoVO getInformeReemBanco(long reembolsoCodigo) throws BusinessException,Exception{
		return services.getInformeReemBanco(reembolsoCodigo);
	}
	
	/**
	 * Obtiene la información del ultimo reembolso
	 * 
	 * @return ReembolsoTotalVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public ReembolsoTotalVO getUltimoReembolsoTotal() throws Exception, BusinessException {
		return services.getUltimoReembolsoTotal();
	}
	
	/*
	 * Se encarga de enviar un e-mail de aviso de los reembolsos a cada uno de los socios que tengan un reembolso en el proceso de reembolso indicado y que tengan registrado un e-mail 
	 */
	public void informarReembolsosViaEMail(long codigoReembolso) throws Exception {
		services.informarReembolsosViaEMail(codigoReembolso);
	}

	/*
	 * Genera el Archivo de Reembolso Que Se Envía al Banco 
	 */
	public void generarArchivoBanco(long codigoReembolso) throws Exception {
		services.generarArchivoBanco(codigoReembolso);
	}

	
}
