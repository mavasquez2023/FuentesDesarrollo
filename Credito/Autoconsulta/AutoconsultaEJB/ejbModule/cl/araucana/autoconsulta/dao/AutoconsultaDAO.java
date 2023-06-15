package cl.araucana.autoconsulta.dao;

import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.schema.util.GeneralException;

import cl.araucana.autoconsulta.vo.ActividadVO;
import cl.araucana.autoconsulta.vo.AuditoriaVO;
import cl.araucana.autoconsulta.vo.CuentaAhorroVO;
import cl.araucana.autoconsulta.vo.DatosValidacionVO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.ValorValidableVO;
import cl.araucana.autoconsulta.vo.PeriodoVO;
import cl.araucana.common.BusinessException;

/**
 * @author asepulveda
 * Metodos expuestos por AutoconsultaDAO
 */
public interface AutoconsultaDAO {

	/** 
	 * Registra los datos de identificación de un certificado
	 * @param DatosValidacionVO datosValidacion
	 * @return void
	 */
	public void insertDatosValidacion(DatosValidacionVO datosValidacion)
		throws Exception, BusinessException;

	/** 
	 * Registra los valores validables de un certificado
	 * @param DatosValidacionVO datosValidacion
	 * @return void
	 */
	public void insertValorValidable(long id, ValorValidableVO variableValor)
		throws Exception, BusinessException;

	/** 
	 * Obtiene Collection con un Objeto con la información básica del certificado
	 * @param long, id del certificado
	 * @return Collection de DatosValidacionVO
	 */
	public Collection getDatosValidacion(long id)
		throws Exception, BusinessException;

	/** 
	 * Obtiene Collection con los valores validables de un certificado
	 * @param long, id del certificado
	 * @return Collection de ValorValidableVO
	 */
	public Collection getValoresValidables(long id)
		throws Exception, BusinessException;

	/** 
	 * Obtiene Collection de licencias medicas de un empleado
	 * @param long, rut del empleado
	 * @param String, fechaDesde Fecha que indica a partir de cuando se comienza la busqueda
	 * @return Collection de LicenciaMedicaVO
	 */
	public Collection getListaLicenciasMedicasByEmpleado(
		long rut,
		String fechaDesde)
		throws Exception, BusinessException;

	/** 
	 * Recupera Collection de Observaciones predefinidas existentes
	 * @return Collection de CodigoDescripcionVO
	 */

	public Collection getListaObservaciones()
		throws Exception, BusinessException;

	/** 
	 * Recupera Collection de Oficinas de pago existentes
	 * @return Collection de OficinaVO
	 */

	public Collection getListaOficinasPago()
		throws Exception, BusinessException;

	/** 
	 * Obtiene Collection de empleadores de un empleado
	 * @param long, rut del empleado
	 * @return Collection de EmpresaVO
	 */

	public Collection getListaEmpleadoresByEmpleado(long rut)
		throws Exception, BusinessException;
		
	/** 
	 * Obtiene Collection de empleadores de un empleado público
	 * @param long, rut del empleado público
	 * @return Collection de EmpresaVO
	 */

	public Collection getListaEmpleadoresByEmpleadoPublico(long rut)
		throws Exception, BusinessException;

	/** 
	 * Obtiene Collection de empleadores de un empleado
	 * Los datos los obtiene desde el historico
	 * @param long, rut del empleado
	 * @return Collection de EmpresaVO
	 */

	public Collection getListaHistoricoEmpleadoresByEmpleado(long rut)
		throws Exception, BusinessException;
		
	/** 
	 * Obtiene Collection de empleadores de un empleado público
	 * Los datos los obtiene desde el historico
	 * @param long, rut del empleado público
	 * @return Collection de EmpresaVO
	 */

	public Collection getListaHistoricoEmpleadoresByEmpleadoPublico(long rut)
		throws Exception, BusinessException;

	/** 
	 * Recupera Collection de Observaciones que un empleado tiene en Compin
	 * @param long, rut del empleado
	 * @param long, numLicencia
	 * @return Collection de ObservacionCompinVO
	 */

	public Collection getListaObservacionesCompinByEmpleado(
		long rut,
		long numLicencia)
		throws Exception, BusinessException;

	/** 
	 * Recupera Collection de Observaciones que un empleado tiene en Compin
	 * @param long, rut del empleado
	 * @param long, numLicencia
	 * @return Collection de ObservacionCompinVO
	 */

	public Collection getListaObservacionesCompinByEmpleado2(
		long rut,
		long numLicencia)
		throws Exception, BusinessException;

	/** 
	 * Recupera Collection de Observaciones que un empleado tiene en Compin en LIEXP/ILF8660
	 * @param long, rut del empleado
	 * @param long, numLicencia
	 * @return Collection de ObservacionCompinVO
	 */

	public Collection getListaObservacionesCompinOcc(
		long rut,
		long numLicencia)
		throws Exception, BusinessException;

	/** 
	* Recupera Collection con última observación que un empleado tiene en Compin de LIEXP/ILF8660
	* para las licencias devueltas por Fonasa
	* @param long, rut del empleado
	* @param long, numLicencia
	* @return Collection de ObservacionCompinVO
	*/

	public Collection getUltimaObsCompinOcc(long rut, long numLicencia)
		throws Exception, BusinessException;

	/** 
	 * Recupera Collection de Observaciones que un empleado tiene Licencia No VIsada
	 * @param long, rut del empleado
	 * @param long, numLicencia
	 * @return Collection de ObservacionCompinVO
	 */

	public Collection getListaObservacionesCompinByEmpleadoNoVisada(
		long rut,
		long numLicencia)
		throws Exception, BusinessException;

	/** 
	 * Recupera Collection de Detalles de las licencias medicas de un empleado
	 * @param long, rut del empleado
	 * @return Collection de LicenciaMedicaDetalleVO
	 */

	public Collection getListaDetallesLicenciasMedicasByEmpleado(long rut)
		throws Exception, BusinessException;

	/** 
	 * Recupera Collection de Detalles de las licencias medicas de un empleado
	 * @param long, rut del empleado
	 * @return Collection de LicenciaMedicaDetalleVO
	 */

	public Collection getListaDetallesLicenciasMedicasByEmpleadoLiquidacion(long rut)
		throws Exception, BusinessException;

	/** 
	 * Recupera Collection de Instituciones Previcionales
	 * @param String letra
	 * @return Collection de InstitucionPrevicionalVO
	 */

	public Collection getListaInstitucionesPrevicionales(String letra)
		throws Exception, BusinessException;

	/** 
	 * Obtiene Collection de liscencias medicas con información para desplegar
	 * en el certificado
	 * @param long, rut del empleado
	 * @param String, fechaDesde Fecha que indica a partir de cuando se comienza la busqueda
	 * @return Collection de LicenciaMedicaCertificadoVO
	 */

	public Collection getListaLicenciasMedicasCertificadoByEmpleado(
		long rut,
		String fechaDesde)
		throws Exception, BusinessException;

	/** 
	 * Recupera el valor del porcentaje de Seguro Cesantia registrado
	 * @param String letra, int codigo
	 * @return Collection de PorcentajeSeguroCesantiaVO con porcentaje de Seguro Cesantia
	 */

	public Collection getPorcentajeSeguroCesantia(String letra, int codigo)
		throws Exception, BusinessException;

	/** 
	 * Obtiene información para realizar el calclo del seguro de cesantia
	 * @param long, rut del empleado
	 * @param long, el numero de Licencia
	 * @param String, indicador si tiene o no seguro de cesantia
	 * @return Collection de DatosCalculoSeguroCesantiaVO
	 */
	public Collection getDatosCalculoSeguroCesantiaByEmpleado(
		long rut,
		long numLicencia,
		String tieneSeguro)
		throws Exception, BusinessException;

	/** 
	 * Obtiene información de los créditos donde el rut consultado es el titular
	 * @param long, rut
	 * @return Collection de InformacionCreditoVO
	 */
	public Collection getCreditosByRutTitular(long rut)
		throws Exception, BusinessException;

	/** 
	 * Obtiene información de los créditos donde el rut consultado es el aval
	 * @param long, rut del empleado
	 * @return Collection de InformacionCreditoVO
	 */
	public Collection getCreditosByRutAval(long rut)
		throws Exception, BusinessException;

	/** 
	 * Obtiene datos de los titulares de los créditos a partir del rut del aval
	 * @param long rut del aval
	 * @return Collection de DatosTitularCreditoVO
	 */
	public Collection getDatosTitularCreditoByAval(long rut)
		throws Exception, BusinessException;

	/** 
	 * Obtiene datos de los titulares de los créditos
	 * @param long rut
	 * @return Collection de DatosTitularCreditoVO
	 */
	public Collection getDatosTitularCreditoByTitular(long rut)
		throws Exception, BusinessException;
		
	/** 
	 * Obtiene datos de los titulares públicos de los créditos
	 * @param long rut
	 * @return Collection de DatosTitularCreditoVO
	 */
	public Collection getDatosTitularCreditoByTitularPublico(long rut)
		throws Exception, BusinessException;

	/** 
	 * Obtiene las cuotas de los créstitos del rut consultado,
	 * tanto de los creditos en los cuales es el titular como en los que es aval
	 * @param long rut del aval
	 * @return Collection de CuotaCreditoVO
	 */
	public Collection getCuotasCreditosByRut(long rut)
		throws Exception, BusinessException;

	/** 
	 * Obtiene información de los créditos del rut consultado
	 * @param long, rut
	 * @return Collection de ConsultaCreditoVO
	 */
	public Collection getCreditosByRut(long rut)
		throws Exception, BusinessException;

	/** 
	 * Si obtiene información es porque el crédito es repactado, en caso contrario no
	 * @param long, folio
	 * @return Collection de IntVO
	 */
	public Collection getCreditoRepactado(long folio, int ofipro)
		throws Exception, BusinessException;

	/** 
	 * Obtiene las cuotas impagas de los créditos del rut consultado
	 * @param long, rut
	 * @return Collection de CreditoCuotasVO
	 */
	public Collection getCantidadCuotasImpagasCreditoByRut(long rut)
		throws Exception, BusinessException;

	/** 
	 * Obtiene informació de una asignación familiar, los datos que obtiene son:
	 * 	fecha de afiliacion, nombre empresa y estado empresa
	 * @param long, rutAfiliado
	 * @param long, rutEmpleador
	 * @return Collection de DatosAsignacionFamiliarVO
	 */
	public Collection getDatosAsignacionFamiliar(
		long rutAfiliado,
		long rutEmpleador)
		throws Exception, BusinessException;

	/** 
	 * Obtiene el parentesco que tiene registrado una carga famiiar
	 * @param long, rutAfiliado
	 * @param int numeroCarga
	 * @return Collection de CargaFamiliarVO
	 */
	public Collection getParentescoCarga(long rutAfiliado, int numeroCarga)
		throws Exception, BusinessException;

	/** 
	 * Obtiene la información de las cargas familiares de un rut consultado
	 * @param long, rutAfiliado
	 * @param String estado carga
	 * @return Collection de CargaFamiliarVO
	 */
	public Collection getCargasFamiliares(long rutAfiliado, String estadoCarga)
		throws Exception, BusinessException;

	/** 
	 * Rescata maximo valor de la fecha de inicio de tramo AF2GA, siempre que sea
	 * menor o igual a la fecha del sistema
	 * @return Collection de DateVO
	 */
	public Collection getFechaInicioTramoAsignacionFamiliar()
		throws Exception, BusinessException;

	/** 
	 * Rescata codigo del tramo. Para obtenerlo lo hace con el rut del afiliado y la
	 * fecha de inicio de tramo rescatada en el punto anterior
	 * @param long rutAfiliado
	 * @param Date fechaInicioTramo
	 * @return Collection de IntVO
	 */
	public Collection getCodigoTramoAsignacionFamiliar(
		long rutAfiliado,
		Date fechaInicioTramo)
		throws Exception, BusinessException;

	/** 
	 * Si existe registro en AF09L1, rescata el valor del tramo AF2KA de 
	 * la tabla AF11L1, caso contrario limpiar valor tramo
	 * @param Date fechaTramo
	 * @param int tramoVigente
	 * @return Collection de StringVO
	 */
	public Collection getValorTramoAsignacionFamiliar(
		Date fechaTramo,
		int tramoVigente)
		throws Exception, BusinessException;

	/** 
	 * Devuelve el estado del pago de las cargas familiares dado el rut del afiliado 
	 * y el rut de la empresa
	 * @param long rutAfiliado
	 * @param long rutEmpleador
	 * @return Collection de StringVO
	 */
	public Collection getEstadoPagoCarga(long rutAfiliado, long rutEmpleador)
		throws Exception, BusinessException;

	/** 
	 * Devuelve la cantidad de cargas que se encuentran en cada estado definido 
	 * @param long rutAfiliado
	 * @return Collection de CantidadEstadoVO
	 */
	public Collection getCantidadCargasPorEstado(long rutAfiliado)
		throws Exception, BusinessException;

	/** 
	 * Obtiene información de las cuentas de ahorro de un rut
	 * @param long rut
	 * @param String cuentaAhorro, si viene null no filtra por cuenta
	 * @return Collection de CuentaAhorroVO
	 */
	public Collection getListaCuentaAhorroByRut(long rut, String cuentaAhorro)
		throws Exception, BusinessException;

	/** 
	 * Obtiene el detalle de una cartola de ahorro
	 * @param CuentaAhorroVO cuenta
	 * @return Collection de DetalleCartolaVO
	 */
	public Collection getDetalleCuentaAhorro(CuentaAhorroVO cuenta)
		throws Exception, BusinessException;

	/** 
	 * Si encuentra datos quiere decir que el rut del empleado pertenece a la empresa
	 * Si no encuentra datos es porque no pertenece a la empresa
	 * @param long rutAfiliado
	 * @param long rutEmpresa
	 * @return Collection de IntVO
	 */
	public Collection getEmpleadoPerteneceEmpresa(
		long rutAfiliado,
		long rutEmpresa)
		throws Exception, BusinessException;
		
	/** 
	 * Si encuentra datos quiere decir que el rut del empleado pertenece a la empresa pública
	 * Si no encuentra datos es porque no pertenece a la empresa pública
	 * @param long rutPublico
	 * @param long rutEmpresaPublica
	 * @return Collection de IntVO
	 */
	public Collection getEmpleadoPerteneceEmpresaPublica(
		long rutPublico,
		long rutEmpresaPublica)
		throws Exception, BusinessException;

	/** 
	 * Obtiene los datos de un afiliado
	 * @param long rutAfiliado
	 * @return Collection de AfiliadoVO
	 */
	public Collection getDatosAfiliado(long rutAfiliado)
		throws Exception, BusinessException;

	/** 
	 * Obtiene los datos de un empleado público
	 * @param long rutEmpleadoPublico
	 * @return Collection de PublicoVO
	 */
	public Collection getDatosPublico(long rutEmpleadoPublico)
		throws Exception, BusinessException;

	/** 
	 * Obtiene los datos de un pensionado
	 * @param long rutPensionado
	 * @return Collection de PensionadoVO
	 */
	public Collection getDatosPensionado(long rutPensionado)
		throws Exception, BusinessException;

	/** 
	 * Obtiene los datos de un ahorrante
	 * @param long rutAhorrante
	 * @return Collection de AhorranteVO
	 */
	public Collection getDatosAhorrante(long rutAhorrante)
		throws Exception, BusinessException;

	/** 
	 * Obtiene los datos de una empresa
	 * @param long rutEmpresa
	 * @return Collection de EmpresaVO
	 */
	public Collection getDatosEmpresa(long rutEmpresa)
		throws Exception, BusinessException;
		
	/** 
	 * Obtiene los datos de una empresa
	 * @param long rutEmpresa
	 * @return Collection de EmpresaVO
	 */
	public Collection getDatosEmpresaPublica(long rutEmpresa)
		throws Exception, BusinessException;
		
		/** 
	 * Obtiene los datos de la clave de acceso del rut consultado
	 * @param long rut
	 * @return Collection de ClaveVO
	 */
	public Collection getDatosClaveAcceso(long rut)
		throws Exception, BusinessException;

	/** 
	 * Realiza el cambio de clave personal
	 * @param long rut
	 * @param int nueva password
	 * @return void
	 */
	public void updateClave(long rut, int password)
		throws Exception, BusinessException;

	/** 
	 * Realiza el bloqueo de la clave
	 * @param long rut
	 * @param int nueva password
	 * @return void
	 */
	public void bloqueaClave(long rut, Date fecha, Time hora)
		throws Exception, BusinessException;

	/** 
	 * Registra una transacción en la tabla de auditoria
	 * @param AuditoriaVO auditoria
	 * @return void
	 */
	public void insertAuditoria(AuditoriaVO auditoria)
		throws Exception, BusinessException;

	/** 
	 * Registra una actividad en la tabla de actividades
	 * @param AuditoriaVO auditoria
	 * @return void
	 */
	public void insertActividad(ActividadVO actividad)
		throws Exception, BusinessException;

	/** 
	 * Obtiene los cheques del rut de la empresa consultado a partir de la fecha indicada
	 * @param long rut
	 * @param java.sql.Date  fechaDesde, fechaa partir desde la cual se consultan los cheques
	 * @return Collection ChequeVO
	 */
	public Collection getChequesEmpresaByRutEmpresa(
		long rut,
		java.sql.Date fechaDesde)
		throws Exception, BusinessException;

	/** 
	 * Obtiene los cheques del rut de la empresa consultado a partir de la fecha indicada
	 * @param long rut
	 * @param java.sql.Date  fechaDesde, fechaa partir desde la cual se consultan los cheques
	 * @return Collection 
	 */
	public Collection getConceptoChequesEmpresa(
		long rut,
		java.sql.Date fechaDesde)
		throws Exception, BusinessException;

	/** 
	 * Obtiene la cantidad de cheques del rut de la empresa consultado a partir de la fecha indicada
	 * @param long rut
	 * @param java.sql.Date  fechaDesde, fecha partir desde la cual se consultan los cheques
	 * @return Collection 
	 */
	public Collection getCantidadChequesEmpresa(
		long rut,
		java.sql.Date fechaDesde)
		throws Exception, BusinessException;

	/** 
	 * Obtiene la lista de empresas de las cuales está a cargo el usuario
	 * @param long rut
	 * @return Collection 
	 */
	public Collection getEmpresaACargo(long rut)
		throws Exception, BusinessException;

	/** 
	 * Obtiene la lista de encargados
	 * @param long rut empresa
	 * @return Collection 
	 */
	public Collection getEncargados(long rut)
		throws Exception, BusinessException;
		
	/** 
	 * Obtiene la lista de RUTs de usuarios
	 * @return Collection 
	 */
	public Collection getUsuarios() throws Exception, BusinessException;

	/**  
	 * Indica si el encargado con el rut de la empresa ya esta en la BD
	 * @param	long, rut encargado
	 * 			long, rut de la empresa
	 * @return Collection de EmpresaACargoVO
	 */

	public Collection getEncargadoEmpresa(EmpresaACargoVO empresaACargoVO)
		throws Exception, BusinessException;

	/** 
	 * Registra un encargado
	 * @param EmpresaACargoVO VO con el encargado
	 */
	public void insertEncargado(EmpresaACargoVO vo, String usuarioModificador)
		throws Exception, BusinessException;

	/** 
	 * Elimina un encargado
	 * @param EmpresaACargoVO vo
	 */
	public void deleteEncargado(EmpresaACargoVO vo, String usuarioModificador)
		throws Exception, BusinessException;

	/** 
	 * Obtiene Collection de ultimo empleadores de un empleado
	 * Los datos los obtiene desde el historico
	 * @param long, rut del empleado
	 * @return Collection de EmpresaVO
	 */
	public Collection getUltimaEmpresaHistorica(long rut)
		throws Exception, BusinessException;

	/** 
	 * Obtiene Collection de las liquidaciones de un empleado
	 * @param long, rut del empleado
	 * @return Collection de LiquidacionesVO
	 */
	public Collection getLiquidacionesReembolsosByRut(long rut)
		throws Exception, BusinessException;

	public Collection getReajuste(long rut, String fechaLiq)
		throws Exception, BusinessException;
	public Collection getComision(long rut, String fechaLiq)
		throws Exception, BusinessException;
	/** 
	 * Obtiene Collection de los datos de un empleado
	 * @param long, rut del empleado
	 * @return Collection de DatosTrabajadoresLiquidacionesVO
	 */
	public Collection getDatosTrabajadorLiquidaciones(long rut)
		throws Exception, BusinessException;

	/** 
	 * Obtiene Collection de los movimientos de una liquidacion
	 * @param long, rut del empleado
	 * @return Collection de DatosTrabajadoresLiquidacionesVO
	 */
	public Collection getMovimientosLiquidacion(long rut, String nroliq)
		throws Exception, BusinessException;

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
		throws Exception, BusinessException;

	/**
	 * @param rut
	 * @return
	 */
	public Collection getNumeroConvenio(long rut)
		throws Exception, BusinessException;

	public Collection getTip(long agno, long mes)
		throws Exception, BusinessException;

	public Collection getPublicidad() throws Exception, BusinessException;

	public void putPublicidad(String texto)
		throws Exception, BusinessException;

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
		throws Exception, BusinessException;
		
	/** 
	 * Recupera Collection de oficinas y sucursales de la empresa pública, además
	 * indica si el encargado tiene la oficina o susursal a su cargo
	 * @param	long, rut encargado
	 * 			long, rut de la empresa pública
	 * @return Collection de OficinasSucursalesVO
	 */
	public Collection getListaOficinasSucursalesByEmpresaPublicaEncargado(
		long rutEncargado,
		long rutEmpresaPublica)
		throws Exception, BusinessException;

	/** 
	 * 
	 * indica si el encargado tiene la oficina o susursal del ifiliado a su cargo
	 * @param	long, rut encargado
	 * 			long, rut de la empresa
	 * 			long codigoOficina
	 * 			long codigoSucursal
	 * @return Collection de OficinasSucursalesVO
	 */

	public Collection getOficinaSucursalByFiltro(
		long rutEncargado,
		long rutEmpresa,
		long codigoOficina,
		long codigoSucursal)
		throws Exception, BusinessException;

	/** 
	 * Realiza actualizacion de encargago
	 * @param empresaACargoVO
	 * @return void
	 */
	public void updateEncargado(EmpresaACargoVO vo)
		throws Exception, BusinessException;

	/** 
	 * Elimina la informacion del encargado en la tabla AT04F1
	 * @param long rutEncargado, long rutEmpresa
	 */
	public void deleteOficinaSucursalesEncargado(
		long rutEncargado,
		long rutEmpresa)
		throws Exception, BusinessException;

	/** 
	 * Registra sucursales y oficinas de un encargado según empresa
	 * @param long rutEncargado,long rutEmpresa, long codigoOficina, long codigoSucursa
	 */
	public void insertOficinaSucursalesEncargado(
		long rutEncargado,
		long rutEmpresa,
		long codigoOficina,
		long codigoSucursa)
		throws Exception, BusinessException;

	/** 
	 * Recupera Collection de Licencias de un afiliado
	 * @param long, rut del afiliado
	 * @param long, numLicencia
	 * @return Collection de ObservacionCompinVO
	 */

	public Collection listaObservacionesCompin(
		long rutAfiliado,
		long numLicencia)
		throws Exception, BusinessException;

	/** 
	 * Recupera Collection de Periodos para prestaciones Complementarias
	 * @param long, rut de Empresa
	 * @return Collection de PeriodoVO
	 */

	public Collection getPeriodosPrestCompl(long rutEmpresa)
		throws Exception, BusinessException;

	/**
	 * obtiene el monto (si es que tiene) pre aprobado de credito para un 
	 * usuario en particular
	 * @param rutCliente
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public Collection montoCreditoPreaprobado(long rutCliente)
		throws Exception, SQLException;

	/**
	 * 
	 * @param rutCliente
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public Collection existeRegistroSimulacion(long rutCliente)
		throws Exception, SQLException;

	/**
	 * registra una simulación en el sistema, internamente llama a existeRegistroSimulacion para saber
	 * @param rutCliente
	 * @param dv
	 * @param remuneracionLiq
	 * @param montoSolicitado
	 * @param numeroCuotas
	 * @param fechaNac
	 * @param fechaIngEmp
	 * @param seguroDegravamen
	 * @param seguroVida
	 * @param seguroCesantia
	 * @throws Exception
	 * @throws SQLException
	 */
	public void setRegistroSimulacionCredito(
		long rutCliente,
		String dvCliente,
		long remuneracionLiq,
		long montoSolicitado,
		int numeroCuotas,
		java.sql.Date fechaNac,
		java.sql.Date fechaIngEmp,
		boolean seguroDegravamen,
		boolean seguroVida,
		boolean seguroCesantia,
		long montoPrepactado,
		String nombresCliente,
		long telefono,
		String correoElectronico,
		long rutEmpresa,
		String nombreEmpresa)
		throws Exception, SQLException;
	/**
	 * Trae el email y el telefono del cliente que realiza la simulacion
	 * @param rutCliente
	 * @return
	 */
	public Collection datosComplClienteSimulacion(long rutCliente)
		throws Exception, SQLException;

	public Collection datosEmpresaAfiliadoSimulacion(long rutCliente)
		throws Exception, SQLException;

	public void setSimulacionEstadistica(String ipSimulacion, int funcion)
		throws Exception, SQLException;

	/** 
	* Recupera sexo de un afiliado
	* @param long, rut del afiliado		 
	* @return Collection de StringVO
	*/
	public Collection getSexoAfiliadoBaseComun(long rutAfiliado)
		throws Exception, BusinessException;

	/**
	 * Este método obtiene la DeudaInterCaja del afiliado pasado por parámetro 
	 * 
	 * @param rutAfiliado long
	 * @return Collection resultado
	 * @throws Exception ex
	 * @throws BusinessException bex
	 * @author sebastian.helguera (Neoris Argentina)
	 * @version 30/09/2009
	 */
	public Collection getDeudaIntercaja(long rutAfiliado)
		throws Exception, BusinessException;

	/**
	 * Recupera el valor del campo PAGAUX3 del archivo LIEXP/ILL9005
	 * 
	 * @param  long, numero de licencia
	 * @return Collection de Integer.
	 * @throws Exception
	 * @throws BusinessException
	 */
	public Collection getPagosComplementarios(long numLicencia)
		throws Exception, BusinessException;

	/**
	 * Recupera la cantidad de pagos liquidados para la licencia.
	 * 
	 * @param long, numero de licencia
	 * @return Collection de IntVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public Collection getPagosLiquidados(long numLicencia, long rut) throws Exception, BusinessException;

	/**
	 * Retorna la cantidad de cheques en estado Caducado para la licencia dada.
	 *  
	 * @param long, numero del folio de pago.
	 * @return Collection de IntVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	//LPC 2011-03-17
	public Collection getChequesCaducados(long folioPago, long rutAfiliado) throws Exception, BusinessException;
	//public Collection getChequesCaducados(long folioPago) throws Exception, BusinessException;
	
	

	public Collection getValorCuotaActual() throws Exception, BusinessException;
	
	/**
	 * REQ8540 - Obtener dias feriados del periodo
	 * @param periodo
	 * @return
	 * @throws GeneralException
	 * @throws SQLException
	 */
	public Collection obtenerFeriadosPeriodo(String periodo) throws GeneralException, SQLException;
	
	/**
	 * Obtiene fecha de resolución de la licencia consultada
	 * @param rutAfiliado
	 * @param nroLicencia
	 * @param fechaHasta
	 * @return
	 * @throws SQLException
	 * @throws GeneralException
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public String obtenerFechaResolucion(long rutAfiliado, long nroLicencia, String fechaHasta) throws SQLException, GeneralException, NumberFormatException, ParseException;
	
	/**
	 * Obtiene licencia por nro y rut afiliado.
	 * Visada = 1 desde ILF1000
	 * Visada = 2 desde ILF8600
	 * @param rutAfiliado
	 * @param nroLicencia
	 * @param fechaDesde
	 * @return
	 * @throws GeneralException
	 * @throws SQLException
	 */
	public Collection getLicenciaMedicaByNro(long rutAfiliado, long nroLicencia, String fechaDesde) throws GeneralException, SQLException;
	
}
