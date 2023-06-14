package cl.araucana.autoconsulta.serv;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsulta;
import cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean.ServicesAutoconsultaHome;
import cl.araucana.autoconsulta.vo.CertificadoAsignacionFamiliarVO;
import cl.araucana.autoconsulta.vo.CartolaAhorroVO;
import cl.araucana.autoconsulta.vo.CertificadoLicenciasMedicasVO;
import cl.araucana.autoconsulta.vo.CuentasAhorroRutVO;
import cl.araucana.autoconsulta.vo.CertificadoDeudaVigenteVO;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.DatosValidacionVO;
import cl.araucana.autoconsulta.vo.DeudaIntercajaVO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.LicenciaMedicaVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;

import com.schema.patterns.BusinessDelegate;
import com.schema.util.FileSettings;
import com.schema.util.GeneralException;

/**
 * @author asepulveda
 * Business Delegate para Servicios de Empresas
 */
public class ServicesAutoconsultaDelegate extends BusinessDelegate {
	
	private static Logger logger =
			Logger.getLogger(ServicesAutoconsultaDelegate.class);
	// Home y Remote del EJB
	private final static Class homeClass = ServicesAutoconsultaHome.class;
	private ServicesAutoconsulta services = null;

	/**
	 * Constructor del BDelegate. Establece la conexion con el EJB
	 * @throws GeneralException
	 */
	public ServicesAutoconsultaDelegate() throws GeneralException {
		
		// Nombre JNDI del EJB
		logger.info("Archivo configuración EJB: " + AppConfig.getInstance().settingsFileName);
		String jndi =
			FileSettings.getValue(
				AppConfig.getInstance().settingsFileName,
				"/application-settings/ejbs/autoconsulta/autoconsulta-services");
		logger.info("jndi EJB:" + jndi);
		try {
			InitialContext ic = new InitialContext();
			services =
				(ServicesAutoconsulta) super.getServiceBean(
					ic,
					jndi,
					homeClass);
		} catch (Exception e) {
			logger.error("Error mensaje: " + e.getMessage());
			e.printStackTrace();
			throw new GeneralException(this, e);
		}
	}

	/**
	 * Devuelve los datos para realizar la validación si 
	 * el certificado buscado existe, si no existe devuelve null
	 * @param id
	 * @return DatosValidacionVO si existe, null si NO existe
	 * @throws Exception
	 * @throws BusinessException
	 */
	public DatosValidacionVO getDatosValidacionCertificado(long id)
		throws Exception, BusinessException {
		return services.getDatosValidacionCertificado(id);
	}

	/** 
	 * Recupera Collection de Instituciones Previcionales
	 * @return Collection de InstitucionPrevicionalVO
	 */
	public Collection getListaInstitucionesPrevicionales()
		throws Exception, BusinessException {
		return services.getListaInstitucionesPrevicionales();
	}

	/** 
	 * Recupera Collection de Oficinas de pago existentes
	 * @return Collection de OficinaVO
	 */
	public Collection getListaOficinasPago()
		throws Exception, BusinessException {
		return services.getListaOficinasPago();
	}

	/** 
	 * Recupera Collection de Observaciones predefinidas existentes
	 * @return Collection de CodigoDescripcionVO
	 */
	public Collection getListaObservaciones()
		throws Exception, BusinessException {
		return services.getListaObservaciones();
	}

	/**
	 * Retorna la información para emitir el certificado de licencias medicas de un rut consultado
	 * @param rut
	 * @return CertificadoLicenciasMedicasVO con indicador tieneLicencias en true
	 *  y  con indicador tieneLicencias en false si no tiene licencias Médicas
	 * @param long rut
	 * @param String dispositivo
	 * @param String nombreConsulta
	 * @param String rutConsulta
	 * @throws Exception
	 * @throws BusinessException
	 */
	public CertificadoLicenciasMedicasVO getCertificadoLicenciasMedicas(
		long rut,
		String dispositivo,
		String nombreConsulta,
		String rutConsulta)
		throws Exception, BusinessException {
		return services.getCertificadoLicenciasMedicas(
			rut,
			dispositivo,
			nombreConsulta,
			rutConsulta);
	}

	/**
	 * Retorna la información para emitir el certificado de licencias medicas de un rut consultado
	 * @param rut
	 * @return CertificadoLicenciasMedicasVO con indicador tieneLicencias en true
	 *  y  con indicador tieneLicencias en false si no tiene licencias Médicas
	 * @param UsuarioVO usrVo
	 * @param String nombreConsulta
	 * @param String rutConsulta
	 * @throws Exception
	 * @throws BusinessException
	 */
	public CertificadoLicenciasMedicasVO getCertificadoLicenciasMedicas(
		UsuarioVO usrVo,
		String nombreConsulta,
		String rutConsulta)
		throws Exception, BusinessException {
		return services.getCertificadoLicenciasMedicas(
			usrVo,
			nombreConsulta,
			rutConsulta);
	}

	
	/**
	 * Retorna listas con Liciencias Medicas de un rut consultado
	 * @param long rut
	 * @param String dispositivo
	 * @return Collection de LicenciasVO
	 */
	public Collection getConsultaLicenciasMedicas(long rut, String dispositivo)
		throws Exception, BusinessException {
		return services.getConsultaLicenciasMedicas(rut, dispositivo);
	}

	/**
	 * Retorna listas con Liciencias Medicas de un rut consultado
	 * @param UsuarioVO usrVo
	 * @return Collection de LicenciasVO
	 */
	public Collection getConsultaLicenciasMedicas(UsuarioVO usrVo)
		throws Exception, BusinessException {
		return services.getConsultaLicenciasMedicas(usrVo);
	}

	
	/** 
	 * Obtiene Collection de empleadores de un empleado
	 * Si no lo encuentra en las tablas lo busca en el historico
	 * @param long, rut del empleado
	 * @param String dispositivo
	 * @return Collection de EmpresaVO
	 */
	public Collection getEmpleadoresByEmpleado(long rut, String dispositivo)
		throws Exception, BusinessException {
		return services.getEmpleadoresByEmpleado(rut, dispositivo);
	}

	/** 
	 * Obtiene Collection de empleadores de un empleado
	 * Si no lo encuentra en las tablas lo busca en el historico
	 * @param UsuarioVO usrVo
	 * @return Collection de EmpresaVO
	 */
	public Collection getEmpleadoresByEmpleado(UsuarioVO usrVo)
		throws Exception, BusinessException {
		return services.getEmpleadoresByEmpleado(usrVo);
	}

	/**
	 * Obtiene información de la deuda vigente que registra un rut, 
	 * tanto la deuda directa (como titular) como la deuda indirecta (como aval)
	 * @param rut
	 * @param String dispositivo
	 * @param String nombreConsulta
	 * @param String rutConsulta
	 * @return CertificadoDeudaVigenteVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public CertificadoDeudaVigenteVO getCertificadoDeudaVigenteByRut(
		long rut,
		String dispositivo,
		String nombreConsulta,
		String rutConsulta)
		throws Exception, BusinessException {
		return services.getCertificadoDeudaVigenteByRut(
			rut,
			dispositivo,
			nombreConsulta,
			rutConsulta);
	}

	/**
	 * Obtiene información de la deuda vigente que registra un rut, 
	 * tanto la deuda directa (como titular) como la deuda indirecta (como aval)
	 * @param UsuarioVO usrVo
	 * @param String nombreConsulta
	 * @param String rutConsulta
	 * @return CertificadoDeudaVigenteVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public CertificadoDeudaVigenteVO getCertificadoDeudaVigenteByRut(
		UsuarioVO usrVo,
		String nombreConsulta,
		String rutConsulta)
		throws Exception, BusinessException {
		return services.getCertificadoDeudaVigenteByRut(
			usrVo,
			nombreConsulta,
			rutConsulta);
	}

	/**
	 * Indica el estado de las cargas de un rut consultado
	 * Devuelve:
	 * 		0= si no tiene cargas o el estado de pago es N
	 * 		1= si sólo tiene cargas activas
	 * 		2= si sólo tiene cargas inactivas
	 * 		3= si tiene cargas activas e inactivas
	 * @param long rutAfiliado
	 * @param long rutEmpleador
	 * @return int
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int getTieneCargasByRut(long rutAfiliado, long rutEmpleador)
		throws Exception, BusinessException {
		return services.getTieneCargasByRut(rutAfiliado, rutEmpleador);
	}

	/**
	 * Obtiene información de la asignación familiar
	 * @param long rutAfiliado
	 * @param long rutEmpleador
	 * @param int tipoConsulta
	 * @return CertificadoAsignacionFamiliarVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public CertificadoAsignacionFamiliarVO getCertificadoAsignacionFamiliarByRut(
		long rutAfiliado,
		long rutEmpleador,
		int tipoConsulta,
		String nombreCertificado,
		String rutCertificado)
		throws Exception, BusinessException {
		return services.getCertificadoAsignacionFamiliarByRut(
			rutAfiliado,
			rutEmpleador,
			tipoConsulta,
			nombreCertificado,
			rutCertificado);
	}

	/**
	 * Obtiene la lista de cuentas activas que posee un rut, además devuelve:
	 * 		cantidad de cuentas que tiene el rut (todas)
	 * 		cantidad de cuentas activas que tiene el rut
	 * 		cantidad de cuentas inactivas que tiene el rut
	 * @param long rut
	 * @return CuentasAhorroRutVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public CuentasAhorroRutVO getCuentasAhorroByRut(long rut)
		throws Exception, BusinessException {
		return services.getCuentasAhorroByRut(rut);
	}

	/**
	 * Obtiene información de la cartola de ahorro
	 * @param long rut
	 * @param String cuentaAhorro
	 * @param String dispositivo
	 * @return CartolaAhorroVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public CartolaAhorroVO getCartolaAhorro(
		long rut,
		String cuentaAhorro,
		String dispositivo)
		throws Exception, BusinessException {
		return services.getCartolaAhorro(rut, cuentaAhorro, dispositivo);
	}

	/**
	 * Obtiene información de la cartola de ahorro
	 * @param UsuarioVO usr
	 * @param String cuentaAhorro
	 * @return CartolaAhorroVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public CartolaAhorroVO getCartolaAhorro(
		UsuarioVO usrVo,
		String cuentaAhorro)
		throws Exception, BusinessException {
		return services.getCartolaAhorro(usrVo, cuentaAhorro);
	}

	/**
	 * Si encuentra datos devuelve AfiliadoVO y quiere decir que el rut del 
	 * empleado pertenece a la empresa.
	 * Si no encuentra datos devuelve null y quiere decir  que el rut del
	 * empleado no pertenece a la empresa.
	 * @param long rutAfiliado
	 * @param long rutEmpresa
	 * @return AfiliadoVO si encuentra relacion
	 * @return null si no encuentra relacion
	 * @throws Exception
	 * @throws BusinessException
	 */
	public AfiliadoVO getDatosEmpleado(long rutAfiliado, long rutEmpresa)
		throws Exception, BusinessException {
		return services.getDatosEmpleado(rutAfiliado, rutEmpresa);
	}

	/**
	 * Si el rut ingresado no existe retorna null
	 * Si el rut ingresado existe, devuelve UsuarioVO que cotiene:
	 * 		datos básicos
	 *		flag's para determinar perfil
	 *		resultado de la autenticacion (código)
	 * @return UsuarioVO si existe
	 * @return null si no existe
	 * @throws Exception
	 * @throws BusinessException
	 */
	public UsuarioVO getAutenticacion(long rut, int password)
		throws Exception, BusinessException {
		return services.getAutenticacion(rut, password);
	}

	/**
	 * Si el rut ingresado no existe retorna null
	 * Si el rut ingresado existe, devuelve UsuarioVO que cotiene:
	 * 		datos básicos
	 *		flag's para determinar perfil
	 *		resultado de la autenticacion (código)
	 * @return UsuarioVO si existe
	 * @return null si no existe
	 * @throws Exception
	 * @throws BusinessException
	 */
	public UsuarioVO getAutenticacion(long rut)
		throws Exception, BusinessException {
		return services.getAutenticacion(rut);
	}

	/**
	 * Actualiza la clave personal
	 * @param long rut
	 * @param int password
	 * @return void
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void modificarClavePersonal(long rut, int password)
		throws Exception, BusinessException {
		services.modificarClavePersonal(rut, password);
	}

	/**
	 * Realiza el bloqueo de la clave
	 * @param long rut
	 * @return void
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void bloquearClave(long rut) throws Exception, BusinessException {
		services.bloquearClave(rut);
	}

	/**
	 * Obtiene información de los cheques de una empresa
	 * @param long rut
	 * @return 
	 * @throws Exception
	 * @throws BusinessException
	 */
	public Collection getChequesEmpresa(long rut, String dispositivo)
		throws Exception, BusinessException {
		return services.getChequesEmpresa(rut, dispositivo);
	}

	/**
	 * Obtiene información de los cheques de una empresa
	 * @param UsuarioVO usrVo
	 * @return 
	 * @throws Exception
	 * @throws BusinessException
	 */
	public Collection getChequesEmpresa(UsuarioVO usrVo)
		throws Exception, BusinessException {
		return services.getChequesEmpresa(usrVo);
	}

	/**
	 * Obtiene información de los créditos del rut consultado
	 * @param long rut
	 * @return Collection, ConsultaCreditoVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public Collection getCreditosByRut(long rut, String dispositivo)
		throws Exception, BusinessException {
		return services.getCreditosByRut(rut, dispositivo);
	}
	/**
	 * Obtiene información de los créditos del rut consultado
	 * @param UsuarioVO usrVo
	 * @return Collection, ConsultaCreditoVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public Collection getCreditosByRut(UsuarioVO usrVo)
		throws Exception, BusinessException {
		return services.getCreditosByRut(usrVo);
	}

	/** 
	 * Obtiene la lista de empresas de las cuales está a cargo el usuario
	 * @param long rut
	 * @return Collection 
	 */
	public Collection getEmpresaACargo(long rut)
		throws Exception, BusinessException {
		return services.getEmpresaACargo(rut);

	}

	/** 
	 * Obtiene los datos de una empresa
	 * @param long rutEmpresa
	 * @return Collection de EmpresaVO
	 */
	public Collection getDatosEmpresa(long rutEmpresa)
		throws Exception, BusinessException {
		return services.getDatosEmpresa(rutEmpresa);
	}
	
	/** 
	 * Obtiene los datos de una empresa pública
	 * @param long rutEmpresaPublica
	 * @return Collection de EmpresaPublicaVO
	 */
	public Collection getDatosEmpresaPublica(long rutEmpresaPublica)
		throws Exception, BusinessException {
		return services.getDatosEmpresaPublica(rutEmpresaPublica);
	}

	/** 
	 * Obtiene la lista de encargados
	 * @param long rut empresa
	 * @return Collection 
	 */
	public Collection getEncargados(long rut)
		throws Exception, BusinessException {

		return services.getEncargados(rut);

	}

	/** 
	 * Obtiene datos del cliente (futuro Encargado)
	 * @param long rut empresa
	 * @return EmpresaACargoVO 
	 */
	public EmpresaACargoVO getCliente(long rut)
		throws Exception, BusinessException {

		return services.getCliente(rut);

	}

	/** 
	 * Obtiene la lista de RUTs de usuarios
	 * @return Collection 
	 */
	public Collection getUsuarios() throws Exception, BusinessException {

		return services.getUsuarios();

	}

	/** 
	 * Registra un encargado
	 * @param EmpresaACargoVO VO con el encargado
	 * @param String [] indiceOficinasSeleccionadas
	 * @throws Exception
	 * @throws BusinessException 
	 */
	public void registrarEncargado(
		EmpresaACargoVO empresaACargoVO,
		String[] indiceOficinasSeleccionadas,
		String usuarioModificador)
		throws Exception, BusinessException {
		services.registrarEncargado(
			empresaACargoVO,
			indiceOficinasSeleccionadas,
			usuarioModificador);
	}

	/** 
	 * Elimina un encargado
	 * @param EmpresaACargoVO vo del encargado
	 */
	public void deleteEncargado(EmpresaACargoVO vo, String usuarioModificador)
		throws Exception, BusinessException {
		services.deleteEncargado(vo, usuarioModificador);
	}

	/** 
	 * Obtiene Collection de ultimo empleadores historicos de un empleado
	 * @param long, rut del empleado
	 * @return Collection de EmpresaVO
	 */
	public Collection getUltimaEmpresaHistorica(long rut)
		throws Exception, BusinessException {
		return services.getUltimaEmpresaHistorica(rut);
	}

	/** 
	 * Recupera Collection de Liquidaciones de reembolsos de un trabajador
	 * @return Collection de LiquidacionesVO
	 */
	public Collection getLiquidacionReembolsosByRut(long rut)
		throws Exception, BusinessException {
		return services.getLiquidacionReembolsosByRut(rut);
	}
	/** 
	 * Recupera Collection de Liquidaciones de reembolsos de un trabajador
	 * @return Collection de LiquidacionesVO
	 */
	public Collection getLiquidacionReembolsosByRut(UsuarioVO usrVo)
		throws Exception, BusinessException {
		return services.getLiquidacionReembolsosByRut(usrVo);
	}
	/** 
	 * Obtiene Collection de los datos de un empleado
	 * @param long, rut del empleado
	 * @return Collection de DatosTrabajadoresLiquidacionesVO
	 */
	public Collection getDatosTrabajadorLiquidaciones(long rut)
		throws Exception, BusinessException {
		return services.getDatosTrabajadorLiquidaciones(rut);

	}

	/** 
	 * Obtiene Collection de los movimientos de una liquidacion
	 * @param long, rut del empleado
	 * @return Collection de DatosTrabajadoresLiquidacionesVO
	 */
	public Collection getMovimientosLiquidacion(long rut, String nroliq)
		throws Exception, BusinessException {
		return services.getMovimientosLiquidacion(rut, nroliq);
	}

	/** 
	 * Obtiene Collection de los movimientos de una liquidacion
	 * @param UsuarioVO usrVo del empleado
	 * @return Collection de DatosTrabajadoresLiquidacionesVO
	 */
	public Collection getMovimientosLiquidacion(UsuarioVO usrVo, String nroliq)
		throws Exception, BusinessException {
		return services.getMovimientosLiquidacion(usrVo, nroliq);
	}
	
	
	/** 
	 * Obtiene Collection de los resumenes por trabajador de un aempresa
	 * @param long, rut del empleado
	 * @param nroConvenio
	 * @param periodo
	 * @return Collection de ResMensualPrestCompVO
	 */
	public Collection getResumenMensualByEmpresa(
		long rutEmp,
		long nroConvenio,
		String periodo)
		throws Exception, BusinessException {
		return services.getResumenMensualByEmpresa(
			rutEmp,
			nroConvenio,
			periodo);
	}

	/**
	 * @param rut
	 * @return
	 */
	public Collection getNumeroConvenio(long rut)
		throws Exception, BusinessException {
		return services.getNumeroConvenio(rut);
	}

	public Collection getTip(long agno, long mes, int tipFija, int tipValor)
		throws Exception, BusinessException {
		return services.getTip(agno, mes, tipFija, tipValor);
	}

	public Collection getPublicidad() throws Exception, BusinessException {
		return services.getPublicidad();
	}

	/**
	 * @param texto
	 */
	public void putPublicidad(String texto)
		throws Exception, BusinessException {
		// TODO Apéndice de método generado automáticamente
		services.putPublicidad(texto);
	}

	/** 
	 * Recupera Collection de oficinas y sucursales de la empresa, además
	 * indica si el encargado tiene la oficina o susursal a su cargo
	 * @param	long, rut encargado
	 * 			long, rut de la empresa
	 * @return Collection de OficinasSucursalesVO
	 */

	public Collection getListaOficinasSucursalesByEmpresaEncargado(
		long rutEncargado,
		long rutEmpresa)
		throws Exception, BusinessException {

		return services.getListaOficinasSucursalesByEmpresaEncargado(
			rutEncargado,
			rutEmpresa);
	}

	/**
	 * Retorna boolean indicando si el encargado puede o no consultar por el afiliado
	 * @param rutEncargado
	 * @param afiliado
	 * @param rutEmpresa
	 * @return boolean
	 * @throws Exception
	 * @throws BusinessException
	 */
	public boolean usuarioPuedeConsultarPorAfiliado(
		long rutEncargado,
		long rutEmpresa,
		AfiliadoVO afiliado)
		throws Exception, BusinessException {
		return services.usuarioPuedeConsultarPorAfiliado(
			rutEncargado,
			rutEmpresa,
			afiliado);
	}

	/** 
	 * Recupera Collection de Licencias de un afiliado
	 * @param long, rut del afiliado
	 * @param long, numLicencia
	 * @return Collection de StringVO
	 */

	public Collection listaObservacionesCompin(
		long rutAfiliado,
		long numLicencia)
		throws Exception, BusinessException {

		return services.listaObservacionesCompin(rutAfiliado, numLicencia);
	}

	/** 
	 * Recupera Collection de Licencias de un afiliado
	 * @param long, rut del afiliado
	 * @param long, numLicencia
	 * @return Collection de StringVO
	 */

	public Collection getPeriodosPrestCompl(long rutEmpresa)
		throws Exception, BusinessException {

		return services.periodosPrestCompl(rutEmpresa);
	}

	/**
	* obtiene el monto (si es que tiene) pre aprobado de credito para un 
	* usuario en particular
	* @param rutCliente
	* @return
	* @throws Exception
	* @throws BusinessException
	*/
	public long getMontoCreditoPreaprobado(long rutCliente) throws Exception {
		return services.montoCreditoPreaprobado(rutCliente);
	}

	/**
		* registra la simulación en el sistema (siempre la última de un usuario por día)
		* @param rutCliente
		* @param dvCliente
		* @param remuneracionLiq
		* @param montoSolicitado
		* @param numeroCuotas
		* @param fechaNac
		* @param fechaIngEmp
		* @param seguroDegravamen
		* @param seguroVida
		* @param seguroCesantia
		* @param numFolio
		* @param montoPrepactado
		* @param nombresCliente
		* @param telefono
		* @param correoElectronico
		* @param rutEmpresa
		* @param nombreEmpresa
		* @throws Exception
		* @throws SQLException
		*/
	public void setRegistroSimulacionCredito(
		long rutCliente,
		String dvCliente,
		long remuneracionLiq,
		long montoSolicitado,
		int numeroCuotas,
		Date fechaNac,
		Date fechaIngEmp,
		boolean seguroDegravamen,
		boolean seguroVida,
		boolean seguroCesantia,
		long montoPrepactado,
		String nombresCliente,
		long telefono,
		String correoElectronico,
		long rutEmpresa,
		String nombreEmpresa)
		throws Exception {
		services.setRegistroSimulacionCredito(
			rutCliente,
			dvCliente,
			remuneracionLiq,
			montoSolicitado,
			numeroCuotas,
			fechaNac,
			fechaIngEmp,
			seguroDegravamen,
			seguroVida,
			seguroCesantia,
			montoPrepactado,
			nombresCliente,
			telefono,
			correoElectronico,
			rutEmpresa,
			nombreEmpresa);
	}

	/**
		* Trae el email y el telefono del cliente que realiza la simulacion
		* @param rutCliente
		* @return
		*/
	public Collection datosComplClienteSimulacion(long rutCliente)
		throws Exception {
		return services.datosComplClienteSimulacion(rutCliente);
	}

	public Collection datosEmpresaAfiliadoSimulacion(long rutCliente)
		throws Exception {
		return services.datosEmpresaAfiliadoSimulacion(rutCliente);
	}

	public void setSimulacionEstadistica(String ipSimulacion, int codFuncion)
		throws Exception {
		services.setSimulacionEstadistica(ipSimulacion, codFuncion);
	}

	public void insertarActividad(UsuarioVO usrVo, int codFuncion)
	throws Exception {
	services.insertarActividad(usrVo, codFuncion);
}

	
	public void setSimulacionEstadistica(UsuarioVO usrVo)
	throws Exception {
	services.setSimulacionEstadistica(usrVo);
}

	
	public UsuarioVO getDatosUsuario(long rutAfiliado)
		throws Exception, BusinessException {
		return services.getDatosUsuario(rutAfiliado);
	}

	/** 
	 * Recupera sexo de un afiliado
	 * @param long, rut del afiliado		 
	 * @return Collection de StringVO
	 */
	public Collection sexoAfiliadoBaseComun(long rutAfiliado)
		throws Exception, BusinessException {
			return services.sexoAfiliadoBaseComun(rutAfiliado);
	}
	
	/**
	 * Este método obtiene la DeudaInterCaja del afiliado pasado por parámetro 
	 * 
	 * @param rutAfiliado long
	 * @return DeudaIntercajaVO deuVO
	 * @throws Exception ex
	 * @throws BusinessException bex
	 * @author sebastian.helguera (Neoris Argentina)
	 * @version 30/09/2009
	 */
	public DeudaIntercajaVO getDeudaIntercaja(long rutAfiliado)
		throws Exception, BusinessException {
			return services.getDeudaIntercaja(rutAfiliado);
	}
	
	public LicenciaMedicaVO getBitacoraLicenciaMedica(long rutAfiliado, long nroLicencia, String fechaHasta) throws RemoteException, Exception{
		return services.getBitacoraLicenciaMedica(rutAfiliado, nroLicencia, fechaHasta);
	}
	
}
