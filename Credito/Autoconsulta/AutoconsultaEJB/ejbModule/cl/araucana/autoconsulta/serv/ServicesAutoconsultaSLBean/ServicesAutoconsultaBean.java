package cl.araucana.autoconsulta.serv.ServicesAutoconsultaSLBean;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
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
import cl.araucana.autoconsulta.common.Constants;
import cl.araucana.autoconsulta.common.Funciones;
import cl.araucana.autoconsulta.dao.AutoconsultaDAO;
import cl.araucana.autoconsulta.dao.DAOFactory;
import cl.araucana.autoconsulta.vo.ActividadVO;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.AhorranteVO;
import cl.araucana.autoconsulta.vo.AuditoriaVO;
import cl.araucana.autoconsulta.vo.CantidadChequesVO;
import cl.araucana.autoconsulta.vo.CantidadEstadoVO;
import cl.araucana.autoconsulta.vo.CargaFamiliarVO;
import cl.araucana.autoconsulta.vo.CartolaAhorroVO;
import cl.araucana.autoconsulta.vo.CertificadoAsignacionFamiliarVO;
import cl.araucana.autoconsulta.vo.CertificadoDeudaVigenteVO;
import cl.araucana.autoconsulta.vo.CertificadoLicenciasMedicasVO;
import cl.araucana.autoconsulta.vo.ChequeVO;
import cl.araucana.autoconsulta.vo.ClaveVO;
import cl.araucana.autoconsulta.vo.ComisionVO;
import cl.araucana.autoconsulta.vo.ConceptoChequeVO;
import cl.araucana.autoconsulta.vo.ConsultaCreditoVO;
import cl.araucana.autoconsulta.vo.CreditoCuotasVO;
import cl.araucana.autoconsulta.vo.CuentaAhorroVO;
import cl.araucana.autoconsulta.vo.CuentasAhorroRutVO;
import cl.araucana.autoconsulta.vo.CuotaCreditoVO;
import cl.araucana.autoconsulta.vo.DateVO;
import cl.araucana.autoconsulta.vo.DatosAsignacionFamiliarVO;
import cl.araucana.autoconsulta.vo.DatosComplementariosVO;
import cl.araucana.autoconsulta.vo.DatosTitularCreditoVO;
import cl.araucana.autoconsulta.vo.DatosValidacionVO;
import cl.araucana.autoconsulta.vo.DetalleCartolaVO;
import cl.araucana.autoconsulta.vo.DeudaIntercajaVO;
import cl.araucana.autoconsulta.vo.DoubleVO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.EmpresaPublicaVO;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import cl.araucana.autoconsulta.vo.InformacionCreditoVO;
import cl.araucana.autoconsulta.vo.IntVO;
import cl.araucana.autoconsulta.vo.LicenciaMedicaCertificadoVO;
import cl.araucana.autoconsulta.vo.LicenciaMedicaDetalleVO;
import cl.araucana.autoconsulta.vo.LicenciaMedicaResumenVO;
import cl.araucana.autoconsulta.vo.LicenciaMedicaVO;
import cl.araucana.autoconsulta.vo.LiquidacionesVO;
import cl.araucana.autoconsulta.vo.MontoVO;
import cl.araucana.autoconsulta.vo.OficinasSucursalesVO;
import cl.araucana.autoconsulta.vo.PensionadoVO;
import cl.araucana.autoconsulta.vo.PublicoVO;
import cl.araucana.autoconsulta.vo.ReajusteVO;
import cl.araucana.autoconsulta.vo.StringVO;
import cl.araucana.autoconsulta.vo.TipVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.autoconsulta.vo.ValorValidableVO;
import cl.araucana.autoconsulta.vo.ValorCuotaActualVO;

import cl.araucana.common.BusinessException;
import cl.araucana.common.env.AppConfig;
import cl.laaraucana.utils.FechaUtil;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.AS400ZonedDecimal;
import com.ibm.as400.access.ProgramCall;
import com.ibm.as400.access.ProgramParameter;
import com.ibm.as400.access.QSYSObjectPathName;
import com.schema.util.FileSettings;

/**
 * @author asepulveda Bean implementation class for Enterprise Bean: ServicesAutoconsulta Servicios de Consulta a
 *         Información de La Araucana
 */
public class ServicesAutoconsultaBean implements javax.ejb.SessionBean {
	Logger logger = Logger.getLogger(ServicesAutoconsultaBean.class);

	private AutoconsultaDAO autoconsultaDao;

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
		// Recurso DAO de Autoconsulta
		int daoType = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/autoconsulta/dao-type"));
		try {
			DAOFactory daoFactory = (DAOFactory) DAOFactory.getDAOFactory(daoType);
			autoconsultaDao = daoFactory.getAutoconsultaDAO();
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

	// Inicio Traductores
	/**
	 * Recupera Collection de Instituciones Previcionales
	 * 
	 * @return Collection de InstitucionPrevicionalVO
	 */
	public Collection getListaInstitucionesPrevicionales() throws Exception, BusinessException {
		String letra = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/autoconsulta/param/instituciones-previsionales/letra");
		return autoconsultaDao.getListaInstitucionesPrevicionales(letra);
	}

	/**
	 * Recupera Collection de Oficinas de pago existentes
	 * 
	 * @return Collection de OficinaVO
	 */
	public Collection getListaOficinasPago() throws Exception, BusinessException {
		return autoconsultaDao.getListaOficinasPago();
	}

	/**
	 * Recupera Collection de Observaciones predefinidas existentes
	 * 
	 * @return Collection de CodigoDescripcionVO
	 */
	public Collection getListaObservaciones() throws Exception, BusinessException {
		return autoconsultaDao.getListaObservaciones();
	}

	// Fin Traductores
	// Inicio Validadores de certificados
	/**
	 * Registra en BD los valores que permiten validar el cetificado generado posteriormente
	 * 
	 * @param datosValidacion
	 */
	public void registrarDatosValidacion(DatosValidacionVO datosValidacion) throws Exception, BusinessException {
		autoconsultaDao.insertDatosValidacion(datosValidacion);
		Collection listaValores = datosValidacion.getListaValores();
		Iterator iListaValores = listaValores.iterator();
		while (iListaValores.hasNext()) {
			ValorValidableVO variableValor = (ValorValidableVO) iListaValores.next();
			autoconsultaDao.insertValorValidable(datosValidacion.getId(), variableValor);
		}
	}

	/**
	 * Genera un Id para cada certificado solicitado
	 * 
	 * @return long id certificado
	 */
	public long generarIdCertificado() throws Exception, BusinessException {
		long id = (new Date()).getTime() + Math.round(Math.random() * 1000);
		logger.debug("id generado: " + id);
		return id;
	}

	/**
	 * Devuelve los datos para realizar la validación si el certificado buscado existe, si no existe devuelve null
	 * 
	 * @param id
	 * @return DatosValidacionVO si existe, null si NO existe
	 * @throws Exception
	 * @throws BusinessException
	 */
	public DatosValidacionVO getDatosValidacionCertificado(long id) throws Exception, BusinessException {
		DatosValidacionVO datosValidacion = null;
		Collection datosBasicos = autoconsultaDao.getDatosValidacion(id);
		if (!datosBasicos.isEmpty()) {
			Iterator iDatosBasicos = datosBasicos.iterator();
			datosValidacion = (DatosValidacionVO) iDatosBasicos.next();
			datosValidacion.setListaValores(autoconsultaDao.getValoresValidables(id));
		}
		return datosValidacion;
	}

	// Fin Validadores de certificados
	/**
	 * Retorna la información para emitir el certificado de licencias medicas de un rut consultado
	 * 
	 * @param rut
	 * @return CertificadoLicenciasMedicasVO con indicador tieneLicencias en true y con indicador tieneLicencias en
	 *         false si no tiene licencias Médicas
	 * @param long
	 *            rut
	 * @param String
	 *            dispositivo
	 * @param String
	 *            nombreConsulta
	 * @param String
	 *            rutConsulta
	 * @throws Exception
	 * @throws BusinessException
	 */
	public CertificadoLicenciasMedicasVO getCertificadoLicenciasMedicas(long rut, String dispositivo, String nombreConsulta, String rutConsulta) throws Exception, BusinessException {
		UsuarioVO usrVo = new UsuarioVO();
		usrVo.setRutAfiliado(rut);
		usrVo.setIpConexion(dispositivo);
		return getCertificadoLicenciasMedicas(usrVo, nombreConsulta,  rutConsulta);
	}

	private Collection juntaGlosas(Collection glosa, Collection glosa2) {
		java.util.Iterator it = glosa.iterator();
		java.util.Iterator it2 = glosa2.iterator();
		Collection res = new ArrayList();
		// List l = new ArrayList();
		StringVO glosaRes = new StringVO();
		StringVO glo = null, glo2 = null;
		String g1 = "";
		String g2 = "";
		if (it.hasNext()) {
			glo = (StringVO) it.next();
			g1 = (glo.getTexto().trim().length() == 0 ? "" : g1 + glo.getTexto() + " ");
			// g1 = g1 + glo.getTexto();
		}
		if (it2.hasNext()) {
			glo2 = (StringVO) it2.next();
			g2 = (glo2.getTexto().trim().length() == 0 ? "" : glo2.getTexto() + " ");
			// g2 = g2 + glo2.getTexto();
		}
		glosaRes.setTexto(g1.trim() + " " + g2.trim());
		res.add(glosaRes);
		// l.add(glosaRes);
		// res = l;
		return res;
	}

	private Collection traduceObservaciones(Collection observaciones) {
		ArrayList observacionesTradudicas = new ArrayList();
		String estado1 = "PENDIENTE RESOLUCION";
		String estado2 = "RECHAZADA";
		String estado3 = "REDUCIDA";
		String estado4 = "PERTENECE A ISAPRE";
		String estado5 = "BLOQUEADA POR FONASA";
		String separador = "@";
		String estadoAux = "";
		String glosaAux = "";
		String obsAux = "";
		int cuentaObservaciones = 0;
		Iterator it = observaciones.iterator();
		while (it.hasNext()) {
			cuentaObservaciones++;
			obsAux = ((StringVO) it.next()).getTexto();
			estadoAux = obsAux.substring(0, obsAux.indexOf(separador));
			glosaAux = obsAux.substring(obsAux.indexOf(separador) + 1);
			switch (Integer.parseInt(estadoAux)) {
			case 1:
				estadoAux = estado1;
				break;
			case 2:
				estadoAux = estado2;
				break;
			case 3:
				estadoAux = estado3;
				break;
			case 4:
				estadoAux = estado4;
				break;
			case 5:
				estadoAux = estado5;
				break;
			}
			observacionesTradudicas.add(new StringVO());
			((StringVO) observacionesTradudicas.get(cuentaObservaciones - 1)).setTexto(estadoAux + ": " + glosaAux);
		}
		return observacionesTradudicas;
	}

	/**
	 * Retorna listas con Liciencias Medicas de un rut consultado
	 * 
	 * @param long rut
	 * @param String dispositivo
	 * @return Collection de LicenciasVO
	 */
	public Collection getConsultaLicenciasMedicas(long rut, String dispositivo) throws Exception, BusinessException {

		UsuarioVO usrVo = new UsuarioVO();
		usrVo.setRutAfiliado(rut);
		usrVo.setIpConexion(dispositivo);
        return getConsultaLicenciasMedicas(usrVo);
        
	}

	/**
	 * Revisa si existe monto en lista 2, si existe lo reemplaza sino deja el de la lista 1.
	 * 
	 * @param lista1L
	 * @param lista2L
	 * @return
	 */
	private Collection mergeMontos(Collection lista1L, Collection lista2L) {
		Collection res = new ArrayList();
		// List l = new ArrayList();
		if (!lista1L.isEmpty()) {
			logger.debug("lista 1 :" + lista1L.size());
			java.util.Iterator it = lista1L.iterator();
			boolean put = false;
			while (it.hasNext()) {
				put = false;
				LicenciaMedicaDetalleVO lme = (LicenciaMedicaDetalleVO) it.next();
				if (!lista1L.isEmpty()) {
					java.util.Iterator it2 = lista2L.iterator();
					while (it2.hasNext()) {
						LicenciaMedicaResumenVO lme2 = (LicenciaMedicaResumenVO) it2.next();
						logger.debug("Monto 2 :" + lme2.getMontoAPagar());
						if ((lme2.getNumeroLicencia() == lme.getNumeroLicencia()) && lme2.getFolio().equals(lme.getFolio())) {
							if (lme2.getMontoAPagar() > 0) {
								lme.setMontoAPagar(lme2.getMontoAPagar());
								res.add(lme);
								logger.debug("Monto :" + lme.getMontoAPagar() + " Cambiado a :" + lme2.getMontoAPagar());
								put = true;
								break;
							}
						}
					}
					if (!put) {
						res.add(lme);
						put = false;
					}
				}
			}
		}
		return res;
	}

	/**
	 * Obtiene Collection de empleadores de un empleado Si no lo encuentra en las tablas lo busca en el historico
	 * 
	 * @param long,
	 *            rut del empleado
	 * @param String
	 *            dispositivo
	 * @return Collection de EmpresaVO
	 */
	public Collection getEmpleadoresByEmpleado(long rut, String dispositivo) throws Exception, BusinessException {
		UsuarioVO usrVo = new UsuarioVO();
		usrVo.setRutAfiliado(rut);
		usrVo.setIpConexion(dispositivo);
    
		return getEmpleadoresByEmpleado(usrVo);

	}

	/**
	 * Obtiene Collection de ultimo empleadores historicos de un empleado
	 * 
	 * @param long,
	 *            rut del empleado
	 * @return Collection de EmpresaVO
	 */
	public Collection getUltimaEmpresaHistorica(long rut) throws Exception, BusinessException {
		return autoconsultaDao.getUltimaEmpresaHistorica(rut);
	}

	/**
	 * Obtiene información de la deuda vigente que registra un rut, tanto la deuda directa (como titular) como la deuda
	 * indirecta (como aval)
	 * 
	 * @param rut
	 * @param String
	 *            dispositivo
	 * @param String
	 *            nombreConsulta
	 * @param String
	 *            rutConsulta
	 * @return CertificadoDeudaVigenteVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public CertificadoDeudaVigenteVO getCertificadoDeudaVigenteByRut(long rut, String dispositivo, String nombreConsulta, String rutConsulta) throws Exception, BusinessException {
		UsuarioVO usrVo = new UsuarioVO();
		usrVo.setRutAfiliado(rut);
		usrVo.setIpConexion(dispositivo);
    
		return getCertificadoDeudaVigenteByRut(usrVo, nombreConsulta, rutConsulta);

	}

	/**
	 * Recupera la información de las cuotas de un credito y las deja en el objeto pasado como parametro
	 * (InformacionCreditoVO)
	 * 
	 * @param InformacionCreditoVO,
	 *            credito
	 * @param Collection
	 *            de CuotaCreditoVO, cuotasCreditos
	 * @return InformacionCreditoVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public InformacionCreditoVO getCuotas(InformacionCreditoVO credito, Collection cuotasCreditos, int tipoCredito) throws Exception, BusinessException {
		// Recupera información de las cuotas del crédito
		Collection cuotasCredito = new ArrayList();
		Iterator ic = cuotasCreditos.iterator();
		while (ic.hasNext()) {
			CuotaCreditoVO cuota = (CuotaCreditoVO) ic.next();
			if (credito.getFolio().equals(cuota.getFolio())) {
				if ((cuota.getEstado() == 0) || (cuota.getEstado() == 2) || (cuota.getEstado() == 3) || (cuota.getEstado() == 4) || (cuota.getEstado() == 5)

				/*
				 * ADDED REQ5141 - 20/04/10 despinosa@laaraucana.cl
				 */
				|| (cuota.getEstado() == 6)) {

					credito.setCuotasImpagas(credito.getCuotasImpagas() + 1);
					credito.setTotalMontoAbonado(credito.getTotalMontoAbonado() + cuota.getAbono());
					credito.setVencimiento(cuota.getVencimiento().substring(4, 6) + "/" + cuota.getVencimiento().substring(0, 4));
				}
				if (cuota.getEstado() == 1) {
					credito.setCuotasVigentes(credito.getCuotasVigentes() + 1);
					credito.setTotalMontoAbonado(credito.getTotalMontoAbonado() + cuota.getAbono());
					credito.setVencimiento(cuota.getVencimiento().substring(4, 6) + "/" + cuota.getVencimiento().substring(0, 4));
					if (credito.getCuotaInicial() == 0) {
						credito.setCuotaInicial(cuota.getCuotaNumero());
						credito.setCuotaFinal(cuota.getCuotaNumero());
					} else
						credito.setCuotaFinal(cuota.getCuotaNumero());
				}
				cuotasCredito.add(cuota);
			}
		}
		// Obtiene total de saldos
		if (((credito.getMontoCuota() * credito.getCuotasImpagas()) - credito.getTotalMontoAbonado()) < 0) {
			credito.setSaldoImpago(0);
			credito.setSaldoVigente((credito.getMontoCuota() * credito.getCuotasVigentes()) + ((credito.getMontoCuota() * credito.getCuotasImpagas()) - credito.getTotalMontoAbonado()));
		} else {
			credito.setSaldoImpago((credito.getMontoCuota() * credito.getCuotasImpagas()) - credito.getTotalMontoAbonado());
			credito.setSaldoVigente(credito.getMontoCuota() * credito.getCuotasVigentes());
		}
		credito.setSaldoTotal(credito.getSaldoImpago() + credito.getSaldoVigente());
		credito.setCuotas(cuotasCredito);
		return credito;
	}

	/**
	 * Indica el estado de las cargas de un rut consultado Devuelve: 0= si no tiene cargas o el estado de pago es N 1=
	 * si sólo tiene cargas activas 2= si sólo tiene cargas inactivas 3= si tiene cargas activas e inactivas
	 * 
	 * @param long
	 *            rutAfiliado
	 * @param long
	 *            rutEmpleador
	 * @return int
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int getTieneCargasByRut(long rutAfiliado, long rutEmpleador) throws Exception, BusinessException {
		int respuesta = 0;
		boolean consultarCantidadCargas = false;
		Collection estados = autoconsultaDao.getEstadoPagoCarga(rutAfiliado, rutEmpleador);
		if (!estados.isEmpty()) {
			Iterator iestados = estados.iterator();
			StringVO estado = (StringVO) iestados.next();
			logger.debug("Estado: " + estado.getTexto());
			if (!estado.getTexto().equals(Constants.STD_PAGO_CARGA_NO)) {
				consultarCantidadCargas = true;
			}
		}
		logger.debug("consultar Cantidad Cargas: " + consultarCantidadCargas);
		if (consultarCantidadCargas) {
			Collection cantidad = autoconsultaDao.getCantidadCargasPorEstado(rutAfiliado);
			if (!cantidad.isEmpty()) {
				logger.debug("Tiene: " + cantidad.size() + " registros");
				Iterator icantidad = cantidad.iterator();
				while (icantidad.hasNext()) {
					CantidadEstadoVO estadoCantidad = (CantidadEstadoVO) icantidad.next();
					if (estadoCantidad.getEstado().equals(CargaFamiliarVO.STD_ACTIVA)) {
						if (respuesta <= Constants.CARGAS_ACTIVAS_INACTIVAS) {
							respuesta = respuesta + Constants.CARGAS_ACTIVAS;
							logger.debug("Tiene cargas Activas");
						}
					} else {
						if (respuesta <= Constants.CARGAS_ACTIVAS_INACTIVAS) {
							respuesta = respuesta + Constants.CARGAS_INACTIVAS;
							logger.debug("Tiene cargas Inactivas");
						}
					}
				}
			}
		}
		return respuesta;
	}

	/**
	 * Obtiene la información de las cargas familiares de un rut consultado
	 * 
	 * @param long,
	 *            rutAfiliado
	 * @param int
	 *            estadoCarga
	 * @return Collection de CargaFamiliarVO
	 */
	public Collection getCargasFamiliares(long rutAfiliado, String estadoCarga) throws Exception, BusinessException {
		Collection cargas = autoconsultaDao.getCargasFamiliares(rutAfiliado, estadoCarga);
		Date currentDate = new Date();
		GregorianCalendar today = new GregorianCalendar();
		GregorianCalendar fechaVencimiento = new GregorianCalendar();
		today.setTime(currentDate);
		if (!cargas.isEmpty()) {
			Iterator icargas = cargas.iterator();
			while (icargas.hasNext()) {
				CargaFamiliarVO carga = (CargaFamiliarVO) icargas.next();
				GregorianCalendar fechaAutorizacion = new GregorianCalendar();
				GregorianCalendar fechaAnulacion = new GregorianCalendar();
				Collection datos = autoconsultaDao.getParentescoCarga(rutAfiliado, carga.getNumeroCarga());
				if (!datos.isEmpty()) {
					Iterator idatos = datos.iterator();
					StringVO parentesco = (StringVO) idatos.next();
					if (parentesco.getTexto() != null && parentesco.getTexto().length() > 0)
						carga.setCodigoParentezco(parentesco.getTexto());
				}
				// *****
				if (carga.getCodigoEstado().equals(CargaFamiliarVO.STD_INACTIVA)) {
					if (carga.getFechaVencimiento() != null) {
						fechaVencimiento.setTime(carga.getFechaVencimiento());
						logger.debug("Fecha Vencimiento Gregorian: " + fechaVencimiento.getTime());
						if (today.after(fechaVencimiento)) {
							if (carga.getFechaProceso().compareTo(carga.getFechaVencimiento()) == 0) {
								fechaAnulacion.setTime(carga.getFechaVencimiento());
								fechaAnulacion.add(Calendar.DATE, +1);
								carga.setFechaAnulacion(fechaAnulacion.getTime());
							}
						}
					}
					if (carga.getFechaAnulacion() == null) {
						fechaAnulacion = fechaVencimiento;
						fechaAnulacion.add(Calendar.DAY_OF_MONTH, 1);
						logger.debug("Fecha Anulacion nueva Gregorian: " + fechaAnulacion);
						carga.setFechaAnulacion(fechaAnulacion.getTime());
						logger.debug("Fecha Anulacion nueva Carga: " + carga.getFechaAnulacion());
					} else {
						GregorianCalendar fechaUltimaAutorizacion = new GregorianCalendar();
						GregorianCalendar fechaHoy = new GregorianCalendar();
						fechaHoy.setTime(new Date());
						logger.debug("Fecha Anulacion Date: " + carga.getFechaAnulacion());
						fechaAnulacion.setTime(carga.getFechaAnulacion());
						fechaUltimaAutorizacion.setTime(carga.getFechaAutorizacion());
						if (fechaAnulacion.before(fechaUltimaAutorizacion)) {
							logger.debug("Fecha Vencimiento es Mayor");
							fechaAnulacion = fechaVencimiento;
							fechaAnulacion.add(Calendar.DAY_OF_MONTH, 1);
							logger.debug("Fecha Anulacion nueva Gregorian: " + fechaAnulacion);
							carga.setFechaAnulacion(fechaAnulacion.getTime());
							logger.debug("Fecha Anulacion nueva Carga: " + carga.getFechaAnulacion());
						}
					}
				}
			}
		}
		return cargas;
	}

	/**
	 * Obtiene información de la asignación familiar
	 * 
	 * @param long
	 *            rutAfiliado
	 * @param long
	 *            rutEmpleador
	 * @param int
	 *            tipoConsulta
	 * @return CertificadoAsignacionFamiliarVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public CertificadoAsignacionFamiliarVO getCertificadoAsignacionFamiliarByRut(long rutAfiliado, long rutEmpleador, int tipoConsulta, String nombreCertificado, String rutCertificado)
			throws Exception, BusinessException {
		CertificadoAsignacionFamiliarVO certificado = new CertificadoAsignacionFamiliarVO();
		logger.debug("rutAfiliado: " + rutAfiliado);
		logger.debug("rutEmpleador: " + rutEmpleador);
		// Datos básicos de la asignación Familiar
		Collection datos = autoconsultaDao.getDatosAsignacionFamiliar(rutAfiliado, rutEmpleador);
		if (!datos.isEmpty()) {
			logger.debug("Tiene datos. . . .size: " + datos.size());
			Iterator id = datos.iterator();
			DatosAsignacionFamiliarVO datosAF = (DatosAsignacionFamiliarVO) id.next();
			certificado.setCodigoEstadoEmpresa(datosAF.getCodigoEstadoEmpresa());
			certificado.setFechaAfiliacion(datosAF.getFechaAfiliacion());
			certificado.setNombreEmpresa(datosAF.getNombreEmpresa());
			logger.debug("CodigoEstadoEmpresa: " + certificado.getCodigoEstadoEmpresa() + "...");
			logger.debug("Fecha Afiliacion: " + certificado.getFechaAfiliacion());
		}
		certificado.setTipoCargaConsultado("");
		if (!datos.isEmpty() && tipoConsulta != 0) {
			// Recupera las cargas familiares del afiliado
			if (tipoConsulta == Constants.CARGAS_ACTIVAS)
				certificado.setTipoCargaConsultado(CargaFamiliarVO.STD_ACTIVA);
			else
				certificado.setTipoCargaConsultado(CargaFamiliarVO.STD_INACTIVA);
			certificado.setCargas(getCargasFamiliares(rutAfiliado, certificado.getTipoCargaConsultado()));
			// Recupera Fecha Inicio Tramo Asignacion Familiar
			Collection fechas = autoconsultaDao.getFechaInicioTramoAsignacionFamiliar();
			if (!fechas.isEmpty()) {
				logger.debug("Tiene fechas. . . .size: " + fechas.size());
				Iterator ifecha = fechas.iterator();
				DateVO fechaInicioTramo = (DateVO) ifecha.next();
				Collection codigos = autoconsultaDao.getCodigoTramoAsignacionFamiliar(rutAfiliado, fechaInicioTramo.getFecha());
				if (!codigos.isEmpty()) {
					logger.debug("Tiene codigos. . . .size: " + codigos.size());
					Iterator icodigo = codigos.iterator();
					IntVO codigo = (IntVO) icodigo.next();
					certificado.setTramoVigente(codigo.getValor());
					Collection tramos = autoconsultaDao.getValorTramoAsignacionFamiliar(fechaInicioTramo.getFecha(), codigo.getValor());
					if (!tramos.isEmpty()) {
						logger.debug("Tiene tramos. . . .size: " + tramos.size());
						Iterator itramos = tramos.iterator();
						StringVO tramo = (StringVO) itramos.next();
						certificado.setValorCarga(Integer.parseInt(tramo.getTexto()));
					}
				}
			}
		}
		certificado.setFechaHoy(new Date());
		// Registra los datos para poder validar el certificado
		certificado.setId(generarIdCertificado());
		certificado.setRut(rutAfiliado);
		certificado.setFullNombreAfiliado(nombreCertificado);
		certificado.setFullRutAfiliado(rutCertificado);
		registrarDatosValidacion(certificado.getDatosValidacion());
		return certificado;
	}

	/**
	 * Obtiene la lista de cuentas activas que posee un rut, además devuelve: cantidad de cuentas que tiene el rut
	 * (todas) cantidad de cuentas activas que tiene el rut cantidad de cuentas inactivas que tiene el rut
	 * 
	 * @param long
	 *            rut
	 * @return CuentasAhorroRutVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public CuentasAhorroRutVO getCuentasAhorroByRut(long rut) throws Exception, BusinessException {
		CuentasAhorroRutVO cuentasAhorroRut = new CuentasAhorroRutVO();
		Collection listaCuentasActivas = new ArrayList();
		Collection cuentas = autoconsultaDao.getListaCuentaAhorroByRut(rut, null);
		if (!cuentas.isEmpty()) {
			logger.debug("Son: " + cuentas.size() + " Cartolas");
			Iterator icuentas = cuentas.iterator();
			while (icuentas.hasNext()) {
				CuentaAhorroVO cuenta = (CuentaAhorroVO) icuentas.next();
				if (cuenta.getEstadoCuenta() != CuentaAhorroVO.STD_NO_ACTIVA4 && cuenta.getEstadoCuenta() != CuentaAhorroVO.STD_NO_ACTIVA5 && cuenta.getEstadoCuenta() != CuentaAhorroVO.STD_NO_ACTIVA6) {
					cuentasAhorroRut.setCantidadCuentasActivas(cuentasAhorroRut.getCantidadCuentasActivas() + 1);
					listaCuentasActivas.add(cuenta);
				} else {
					cuentasAhorroRut.setCantidadCuentasInactivas(cuentasAhorroRut.getCantidadCuentasInactivas() + 1);
				}
			}
		}
		if (listaCuentasActivas.size() > 0)
			cuentasAhorroRut.setCuentas(listaCuentasActivas);
		cuentasAhorroRut.setCantidadCuentas(cuentasAhorroRut.getCantidadCuentasActivas() + cuentasAhorroRut.getCantidadCuentasInactivas());
		return cuentasAhorroRut;
	}

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	public double getSaldoEnCuotas(String numC, String ultFechaCuat) {

		long ultFechaCuatrimestre = new Long(ultFechaCuat).longValue();
		long numCuenta = new Long(numC).longValue();

		String sistema = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/leasing/saldo-maximo-giro/sistema");
		// Ruta y nombre del programa saldo máximo de giro
		String programa = "/QSYS.LIB/LSGPGM.LIB/PLH106ACL.PGM";

		// Usuario para obtener saldo máximo de giro
		String user = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/leasing/saldo-maximo-giro/user");
		// Contraseña para ejecutar función saldo máximo de giro
		String psw = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/leasing/saldo-maximo-giro/pass");

		AS400 as400 = new AS400(sistema, user, psw);

		ProgramParameter pList[] = new ProgramParameter[3];

		AS400ZonedDecimal cuenta = new AS400ZonedDecimal(6, 0);
		AS400ZonedDecimal ultFecha = new AS400ZonedDecimal(8, 0);
		AS400ZonedDecimal saldo = new AS400ZonedDecimal(13, 0);

		pList[0] = new ProgramParameter(cuenta.toBytes(numCuenta), 6);
		pList[1] = new ProgramParameter(ultFecha.toBytes(ultFechaCuatrimestre), 8);
		pList[2] = new ProgramParameter(saldo.toBytes(000000000000000l), 13);

		double saldoCuota = 0d;

		QSYSObjectPathName programName = new QSYSObjectPathName(programa);
		ProgramCall llamaPrograma = new ProgramCall(as400, programName.getPath(), pList);

		try {
			if (!llamaPrograma.run()) {
				AS400Message[] msgList = llamaPrograma.getMessageList();
				for (int i = 0; i < msgList.length; i++) {
					System.out.println(msgList[i].getText());
				}
				saldoCuota = 0d;
			} else {

				byte[] respuesta = pList[2].getOutputData();

				BigDecimal bd = (BigDecimal) saldo.toObject(respuesta);
				saldoCuota = bd.doubleValue() / 100000d;
			}

		} catch (Exception e) {
			System.out.println("Excepción en invocación a Servicio OS/400: " + e.getMessage());
			e.printStackTrace();
			as400.disconnectAllServices();
		}
		as400.disconnectAllServices();
		return saldoCuota;
	}

	/**
	 * Obtiene información de la cartola de ahorro
	 * 
	 * @param long
	 *            rut
	 * @param String
	 *            cuentaAhorro
	 * @param String
	 *            dispositivo
	 * @return CartolaAhorroVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public CartolaAhorroVO getCartolaAhorro(long rut, String cuentaAhorro, String dispositivo) throws Exception, BusinessException {
		UsuarioVO usrVo = new UsuarioVO();
		usrVo.setRutAfiliado(rut);
		usrVo.setIpConexion(dispositivo);
    
		return getCartolaAhorro(usrVo, cuentaAhorro);

	}

	/**
	 * Si encuentra datos devuelve AfiliadoVO y quiere decir que el rut del empleado pertenece a la empresa. Si no
	 * encuentra datos devuelve null y quiere decir que el rut del empleado no pertenece a la empresa.
	 * 
	 * @param long
	 *            rutAfiliado
	 * @param long
	 *            rutEmpresa
	 * @return AfiliadoVO si encuentra relacion
	 * @return null si no encuentra relacion
	 * @throws Exception
	 * @throws BusinessException
	 */
	public AfiliadoVO getDatosEmpleado(long rutAfiliado, long rutEmpresa) throws Exception, BusinessException {
		AfiliadoVO persona = new AfiliadoVO();
		Collection lista = autoconsultaDao.getEmpleadoPerteneceEmpresa(rutAfiliado, rutEmpresa);
		if (!lista.isEmpty()) {
			// Si pertenece a la empresa, entonces recupera sus datos personales
			Collection datos = autoconsultaDao.getDatosAfiliado(rutAfiliado);
			Iterator idatos = datos.iterator();
			persona = (AfiliadoVO) idatos.next();
		} else {
			lista = autoconsultaDao.getEmpleadoPerteneceEmpresaPublica(rutAfiliado, rutEmpresa);
			if (!lista.isEmpty()) {
				Collection datos = autoconsultaDao.getDatosPublico(rutAfiliado);
				Iterator idatos = datos.iterator();
				PublicoVO publico = (PublicoVO) idatos.next();
				persona.setApellidoMaterno(publico.getApellidoMaterno());
				persona.setApellidoPaterno(publico.getApellidoPaterno());
				persona.setCodigoOficina(publico.getCodigoOficina());
				persona.setCodigoSucursal(publico.getCodigoSucursal());
				persona.setDv(publico.getDv());
				persona.setEstadoEmpresa(publico.getEstadoEmpresa());
				persona.setNombre(publico.getNombre());
				persona.setRut(publico.getRut());
			} else {
				return null;
			}
		}
		return persona;
	}

	/**
	 * Recupera la información del rut consultado, siempre busca el rut en los siguientes lugares (todos): Afiliados
	 * Pensionados Ahorrantes Empresa Devuelve los datos desde el primer lugar que los encuentra dado el orden anterior.
	 * Simpre informa si lo encontro o no en los lugares que lo busco a traves de boolean Si no lo encuentra en ningún
	 * lugar retorna null
	 * 
	 * @param long
	 *            rutUsuario
	 * @return UsuarioVO si lo encuentra
	 * @return null si no lo encuentra
	 * @throws Exception
	 * @throws BusinessException
	 */
	public UsuarioVO getDatosUsuario(long rutUsuario) throws Exception, BusinessException {
		UsuarioVO usuario = new UsuarioVO();
		boolean loEncontro = false;
		logger.debug("Busca Rut: " + rutUsuario);
		logger.debug("Busca en Afiliados");
		Collection datosAfiliado = autoconsultaDao.getDatosAfiliado(rutUsuario);
		if (!datosAfiliado.isEmpty()) {
			Iterator idatos = datosAfiliado.iterator();
			AfiliadoVO afiliado = (AfiliadoVO) idatos.next();
			usuario.setRut(afiliado.getRut());
			usuario.setDv(afiliado.getDv());
			usuario.setNombre(afiliado.getFullNombre());
			if (afiliado.getEstadoEmpresa().equals(AfiliadoVO.STD_ACTIVO)) {
				usuario.setEsAfiliadoActivo(true);
				logger.debug("Es Afiliado Activo");
			} else {
				usuario.setEsAfiliadoCesado(true);
				logger.debug("Es Afiliado Cesado");
			}
			loEncontro = true;
		}
		logger.debug("Busca en Empleados públicos");
		Collection datosPublico = autoconsultaDao.getDatosPublico(rutUsuario);
		if (!datosPublico.isEmpty()) {
			usuario.setEsPublico(true);
			logger.debug("Es Empleado público");
			if (!loEncontro) {
				Iterator idatos = datosPublico.iterator();
				PublicoVO publico = (PublicoVO) idatos.next();
				usuario.setRut(publico.getRut());
				usuario.setDv(publico.getDv());
				usuario.setNombre(publico.getFullNombre());
				loEncontro = true;
			}
		}
		logger.debug("Busca en Pensionados");
		Collection datosPensionado = autoconsultaDao.getDatosPensionado(rutUsuario);
		if (!datosPensionado.isEmpty()) {
			usuario.setEsPensionado(true);
			logger.debug("Es Pensionado");
			if (!loEncontro) {
				Iterator idatos = datosPensionado.iterator();
				PensionadoVO pensionado = (PensionadoVO) idatos.next();
				usuario.setRut(pensionado.getRut());
				usuario.setDv(pensionado.getDv());
				usuario.setNombre(pensionado.getFullNombre());
				loEncontro = true;
			}
		}
		logger.debug("Busca en Ahorrantes");
		Collection datosAhorrante = autoconsultaDao.getDatosAhorrante(rutUsuario);
		if (!datosAhorrante.isEmpty()) {
			usuario.setEsAhorrante(true);
			logger.debug("Es Ahorrante");
			if (!loEncontro) {
				Iterator idatos = datosAhorrante.iterator();
				AhorranteVO ahorrante = (AhorranteVO) idatos.next();
				usuario.setRut(ahorrante.getRut());
				usuario.setDv(ahorrante.getDv());
				usuario.setNombre(ahorrante.getFullNombre());
				loEncontro = true;
			}
		}
		logger.debug("Busca en Empresas");
		Collection datosEmpresa = autoconsultaDao.getDatosEmpresa(rutUsuario);
		if (!datosEmpresa.isEmpty()) {
			usuario.setEsEmpresa(true);
			logger.debug("Es Empresa");
			if (!loEncontro) {
				Iterator idatos = datosEmpresa.iterator();
				EmpresaVO empresa = (EmpresaVO) idatos.next();
				usuario.setRut(empresa.getRut());
				usuario.setDv(empresa.getDv());
				usuario.setNombre(empresa.getNombre());
				loEncontro = true;
			}
		}
		logger.debug("Busca en Empresas públicas");
		Collection datosEmpresaPublica = autoconsultaDao.getDatosEmpresaPublica(rutUsuario);
		if (!datosEmpresaPublica.isEmpty()) {
			usuario.setEsEmpresaPublica(true);
			logger.debug("Es Empresa pública");
			if (!loEncontro) {
				Iterator idatos = datosEmpresaPublica.iterator();
				EmpresaPublicaVO empresa = (EmpresaPublicaVO) idatos.next();
				usuario.setRut(empresa.getRut());
				usuario.setDv(empresa.getDv());
				usuario.setNombre(empresa.getNombre());
				loEncontro = true;
			}
		}
		// 09-08-2006 OTG
		if (!loEncontro) {
			logger.debug("Busca en Encargados");
			Collection datosEncargado = autoconsultaDao.getEmpresaACargo(rutUsuario);
			if (!datosEncargado.isEmpty()) {
				Iterator idatos = datosEncargado.iterator();
				EmpresaACargoVO encargado = (EmpresaACargoVO) idatos.next();
				usuario.setRut(encargado.getRutEncargado());
				usuario.setDv("");
				usuario.setNombre(encargado.getFullNombre());
				usuario.setEsEncargadoEmpresa(true);
				logger.debug("Es Encargado");
				loEncontro = true;
			}
		}
		//
		if (loEncontro)
			return usuario;
		else
			return null;
	}

	/**
	 * Retorna código con el resultado de la autenticación, los resultados pueden ser: No tiene clave registrada
	 * Tieneclave inicial clave personal
	 * 
	 * @param long
	 *            rut
	 * @return int de acuerdo a descripcion de Constants AUT_CLAVE_XXX
	 * @throws Exception
	 * @throws BusinessException
	 */
	private int verificaPrimerLoginUsuario(long rut) throws Exception, BusinessException {
		int respuesta = Constants.AUT_CLAVE_PERSONAL_CORRECTA;
		boolean validar = true;
		Collection datos = autoconsultaDao.getDatosClaveAcceso(rut);
		if (!datos.isEmpty()) {
			// Tiene Clave
			Iterator idatos = datos.iterator();
			ClaveVO clave = (ClaveVO) idatos.next();
			if (validar) {
				if (clave.getClavePersonal() > 0) {
					// Tiene Clave Personal
					respuesta = Constants.AUT_CLAVE_PERSONAL_CORRECTA;
				} else {
					respuesta = Constants.AUT_CLAVE_INICIAL_CORRECTA;
				}
			}
		}
		return respuesta;
	}

	/**
	 * Retorna código con el resultado de la autenticación, los resultados pueden ser: No tiene clave registrada La
	 * clave ingresada es incorrecta La clave se encuentra bloqueda Autenticado OK contra clave inicial Autenticado OK
	 * contra clave personal
	 * 
	 * @param long
	 *            rut
	 * @param int
	 *            pasword
	 * @return int de acuerdo a descripcion de Constants AUT_CLAVE_XXX
	 * @throws Exception
	 * @throws BusinessException
	 */
	public int autenticarUsuarioPasword(long rut, int password) throws Exception, BusinessException {
		int horasBloqueo = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/autoconsulta/param/horas-bloqueo"));
		GregorianCalendar fechaHoraActual = new GregorianCalendar();
		GregorianCalendar fechaBloqueo = new GregorianCalendar();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
		// SimpleDateFormat formatoCompleto = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
		int respuesta = Constants.AUT_CLAVE_NO_TIENE;
		boolean validar = true;
		Collection datos = autoconsultaDao.getDatosClaveAcceso(rut);
		if (!datos.isEmpty()) {
			// Tiene Clave
			Iterator idatos = datos.iterator();
			ClaveVO clave = (ClaveVO) idatos.next();
			// Verifica bloqueo
			if (clave.getFechaUltBloqueo() != null) {
				logger.debug("Fecha Bloqueo: " + clave.getFechaUltBloqueo());
				logger.debug("Hora Bloqueo: " + clave.getHoraUltBloqueo());
				logger.debug("Fecha/Hora Actual: " + fechaHoraActual);
				System.out.println("---> ENTRA A AUTOCONSULTABEAN************************************************** ");
				System.out.println("---> autenticarUsuarioPasword **** Fecha Bloqueo: " + clave.getFechaUltBloqueo());
				System.out.println("---> autenticarUsuarioPasword **** Hora Bloqueo: " + clave.getHoraUltBloqueo());
				System.out.println("---> autenticarUsuarioPasword **** Fecha/Hora Actual: " + fechaHoraActual);
				fechaBloqueo.set(Integer.parseInt(formatoFecha.format(clave.getFechaUltBloqueo()).substring(6, 10)), // AÑO
						Integer.parseInt(formatoFecha.format(clave.getFechaUltBloqueo()).substring(3, 5)) - 1, // MES
						Integer.parseInt(formatoFecha.format(clave.getFechaUltBloqueo()).substring(0, 2)), // DIA
						Integer.parseInt(formatoHora.format(clave.getHoraUltBloqueo()).substring(0, 2)), // HORA
						Integer.parseInt(formatoHora.format(clave.getHoraUltBloqueo()).substring(3, 5)), // MINUTOS
						Integer.parseInt(formatoHora.format(clave.getHoraUltBloqueo()).substring(6, 8)));
				// SEGUNDOS
				fechaBloqueo.add(Calendar.HOUR, horasBloqueo);
				// Sumar X horas a la hora de bloqueo... son X horas las que permanece bloqueada una cuenta
				if (fechaHoraActual.before(fechaBloqueo)) {
					respuesta = Constants.AUT_CLAVE_BLOQUEADA;
					validar = false;
				}
			}
			logger.debug("validar: " + validar);
			if (validar) {
				if (clave.getClavePersonal() > 0) {
					System.out.println("TIENE CLAVE PERSONAL");
					// Tiene Clave Personal
					if (clave.getClavePersonal() == password) {
						System.out.println(" TIENE CLAVE PERSONAL CORRECTA");
						respuesta = Constants.AUT_CLAVE_PERSONAL_CORRECTA;
					} else {
						System.out.println(" TIENE CLAVE PERSONAL INCORRECTA");
						respuesta = Constants.AUT_CLAVE_INCORRECTA;
					}
				} else {
					System.out.println("NO TIENE CLAVE PERSONAL");
					// Tiene clave inicial
					if (clave.getClaveInicial() == password) {
						System.out.println(" TIENE CLAVE INICIAL CORRECTA");
						respuesta = Constants.AUT_CLAVE_INICIAL_CORRECTA;
					} else {
						System.out.println(" TIENE CLAVE INICIAL INCORRECTA");
						respuesta = Constants.AUT_CLAVE_INCORRECTA;
					}
				}
			}
		}
		System.out.println("---> SALE DESDE SERVICES AUTOCONSULTABEAN *****************************************");
		logger.debug("Respuesta Autenticacion: " + respuesta);
		return respuesta;
	}

	/**
	 * Si el rut ingresado no existe retorna null Si el rut ingresado existe, devuelve UsuarioVO que cotiene: datos
	 * básicos flag's para determinar perfil resultado de la autenticacion (código)
	 * 
	 * @return UsuarioVO si existe
	 * @return null si no existe
	 * @throws Exception
	 * @throws BusinessException
	 */
	public UsuarioVO getAutenticacion(long rut, int password) throws Exception, BusinessException {
		UsuarioVO usuario = getDatosUsuario(rut);
		if (usuario != null) {
			usuario.setAutenticacion(autenticarUsuarioPasword(rut, password));
		}
		return usuario;
	}

	/**
	 * Si el rut ingresado no existe retorna null Si el rut ingresado existe, devuelve UsuarioVO que cotiene: datos
	 * básicos flag's para determinar perfil resultado de la autenticacion (código)
	 * 
	 * @return UsuarioVO si existe
	 * @return null si no existe
	 * @throws Exception
	 * @throws BusinessException
	 */
	public UsuarioVO getAutenticacion(long rut) throws Exception, BusinessException {
		UsuarioVO usuario = getDatosUsuario(rut);
		if (usuario != null) {
			usuario.setAutenticacion(verificaPrimerLoginUsuario(rut));
		}
		return usuario;
	}

	/**
	 * Actualiza la clave personal
	 * 
	 * @param long
	 *            rut
	 * @param int
	 *            password
	 * @return void
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void modificarClavePersonal(long rut, int password) throws Exception, BusinessException {
		autoconsultaDao.updateClave(rut, password);
	}

	/**
	 * Realiza el bloqueo de la clave
	 * 
	 * @param long
	 *            rut
	 * @return void
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void bloquearClave(long rut) throws Exception, BusinessException {
		SimpleDateFormat formatoFechaBD = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		SimpleDateFormat formatoHoraBD = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
		int claveInicial = 0;
		int clavePersonal = 0;
		Date fechaActual = new Date();
		String fecha = formatoFechaBD.format(fechaActual);
		String hora = formatoHoraBD.format(fechaActual);
		logger.debug("-------- fecha: " + fecha);
		logger.debug("-------- hora: " + hora);
		// Recupera datos de la clave para poder enviar
		// clave inicial y clave personal al registro de auditoria
		Collection datos = autoconsultaDao.getDatosClaveAcceso(rut);
		if (!datos.isEmpty()) {
			Iterator idatos = datos.iterator();
			ClaveVO clave = (ClaveVO) idatos.next();
			claveInicial = clave.getClaveInicial();
			clavePersonal = clave.getClavePersonal();
		}
		// Bloquea Clave
		autoconsultaDao.bloqueaClave(rut, formatoFechaBD.parse(fecha), new java.sql.Time(formatoHoraBD.parse(hora).getTime()));
		// Crea registro de auditoria
		AuditoriaVO auditoria = new AuditoriaVO();
		auditoria.setRut(rut);
		auditoria.setFechaTransaccion(formatoFechaBD.parse(fecha));
		auditoria.setHoraTransaccion(new java.sql.Time(formatoHoraBD.parse(hora).getTime()));
		auditoria.setCodigoTransaccion(0);
		auditoria.setRutOperador(null);
		auditoria.setIdOperador(null);
		auditoria.setClaveInicio(claveInicial);
		auditoria.setClavePersonal(clavePersonal);
		autoconsultaDao.insertAuditoria(auditoria);
	}

	/**
	 * Realiza el registro de una actividad
	 * 
	 * @param long
	 *            rut
	 * @return void
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void registrarActividad(String dispositivo, int funcion) throws Exception, BusinessException {
		SimpleDateFormat formatoFechaBD = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		SimpleDateFormat formatoHoraBD = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
		Date fechaActual = new Date();
		String fecha = formatoFechaBD.format(fechaActual);
		String hora = formatoHoraBD.format(fechaActual);
		logger.debug("-------- fecha: " + fecha);
		logger.debug("-------- hora: " + hora);
		ActividadVO actividad = new ActividadVO();
		actividad.setDispositivo(dispositivo);
		actividad.setFuncion(funcion);
		actividad.setFechaTransaccion(formatoFechaBD.parse(fecha));
		actividad.setHoraTransaccion(new java.sql.Time(formatoHoraBD.parse(hora).getTime()));
		autoconsultaDao.insertActividad(actividad);
	}

	/**
	 * Obtiene el saldo máximo de giro
	 * 
	 * @param String
	 *            sistema String programa String user String psw
	 * @return String saldoMaximoGiro, Saldo máximo de giro
	 */
	private String getSaldoMaximoGiro(String cuentaAhorro) throws Exception, BusinessException {
		// DUMMY if (true) return "10000";
		// Sistema donde se encuentra la función saldo máximo de giro
		String sistema = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/leasing/saldo-maximo-giro/sistema");
		// Ruta y nombre del programa saldo máximo de giro
		String programa = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/leasing/saldo-maximo-giro/programa");
		// Usuario para obtener saldo máximo de giro
		String user = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/leasing/saldo-maximo-giro/user");
		// Contraseña para ejecutar función saldo máximo de giro
		String psw = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/leasing/saldo-maximo-giro/pass");
		String saldoMaximoGiro = new String();
		String flags = "0";
		String giro = "00000000000";
		String cuenta = ("000000" + String.valueOf(cuentaAhorro)).substring(String.valueOf(cuentaAhorro).length());
		try {
			AS400 as400 = new AS400(sistema, user, psw);
			QSYSObjectPathName programName = new QSYSObjectPathName(programa);
			ProgramCall getSystemStatus = new ProgramCall(as400);
			ProgramParameter[] parmlist = new ProgramParameter[3];
			// aqui se pasa el numero de cuenta
			AS400Text numCuenta = new AS400Text(6, as400);
			byte[] bNumCuenta = numCuenta.toBytes(cuenta);
			parmlist[0] = new ProgramParameter(bNumCuenta, 6);
			AS400Text maxGiro = new AS400Text(11, as400);
			byte[] bMaxGiro = maxGiro.toBytes(giro);
			parmlist[1] = new ProgramParameter(bMaxGiro, 11);
			AS400Text flag = new AS400Text(1, as400);
			byte[] bFlag = flag.toBytes(flags);
			parmlist[2] = new ProgramParameter(bFlag, 1);
			getSystemStatus.setProgram(programName.getPath(), parmlist);
			if (getSystemStatus.run() != true) {
				AS400Message[] msgList = getSystemStatus.getMessageList();
				logger.debug("The program did not run.  Server messages: ");
				for (int i = 0; i < msgList.length; i++) {
					logger.debug(msgList[i].getText());
				}
				as400.disconnectService(AS400.COMMAND);
				return null;
			} else {
				byte[] rbFlags = parmlist[2].getOutputData();
				byte[] rbMaxGiro = parmlist[1].getOutputData();
				String IDflags = (String) flag.toObject(rbFlags);
				saldoMaximoGiro = (String) maxGiro.toObject(rbMaxGiro);
				as400.disconnectService(AS400.COMMAND);
				if (IDflags.charAt(0) == '0') {
					return saldoMaximoGiro;
				} else {
					return null;
				}
			}
		} catch (Exception error) {
			error.printStackTrace();
			return null;
		}
	}

	/**
	 * Obtiene información de los cheques de una empresa
	 * 
	 * @param long
	 *            rut
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public Collection getChequesEmpresa(long rut, String dispositivo) throws Exception, BusinessException {
		UsuarioVO usrVo = new UsuarioVO();
		usrVo.setRutAfiliado(rut);
		usrVo.setIpConexion(dispositivo);
    
		return getChequesEmpresa(usrVo);

	}

	/**
	 * Obtiene información de los créditos del rut consultado
	 * 
	 * @param long
	 *            rut
	 * @return Collection, ConsultaCreditoVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public Collection getCreditosByRut(long rut, String dispositivo) throws Exception, BusinessException {
		UsuarioVO usrVo = new UsuarioVO();
		usrVo.setRutAfiliado(rut);
		usrVo.setIpConexion(dispositivo);
    
		return getCreditosByRut(usrVo);

	}

	/**
	 * Obtiene los datos de una empresa
	 * 
	 * @param long
	 *            rutEmpresa
	 * @return Collection de EmpresaVO
	 */
	public Collection getDatosEmpresa(long rutEmpresa) throws Exception, BusinessException {
		return autoconsultaDao.getDatosEmpresa(rutEmpresa);
	}

	/**
	 * Obtiene los datos de una empresa pública
	 * 
	 * @param long
	 *            rutEmpresaPublica
	 * @return Collection de EmpresaPublicaVO
	 */
	public Collection getDatosEmpresaPublica(long rutEmpresaPublica) throws Exception, BusinessException {
		return autoconsultaDao.getDatosEmpresaPublica(rutEmpresaPublica);
	}

	/**
	 * Obtiene la lista de empresas de las cuales está a cargo el usuario
	 * 
	 * @param long
	 *            rut
	 * @return Collection
	 */
	public Collection getEmpresaACargo(long rut) throws Exception, BusinessException {
		return autoconsultaDao.getEmpresaACargo(rut);
	}

	/**
	 * Obtiene la lista de encargados
	 * 
	 * @param long
	 *            rut empresa
	 * @return Collection
	 */
	public Collection getEncargados(long rut) throws Exception, BusinessException {
		Collection listaEncargados = autoconsultaDao.getEncargados(rut);
		if (!listaEncargados.isEmpty()) {
			Iterator i = listaEncargados.iterator();
			while (i.hasNext()) {
				EmpresaACargoVO encargado = (EmpresaACargoVO) i.next();
				// Busco en Afiliados la información del encargado
				ArrayList resultAfiliado = (ArrayList) autoconsultaDao.getDatosAfiliado(encargado.getRutEncargado());
				if (!resultAfiliado.isEmpty()) {
					logger.debug("Es afiliado");
					AfiliadoVO afiliadoVo = (AfiliadoVO) resultAfiliado.get(0);
					encargado.setNombre(afiliadoVo.getNombre());
					encargado.setApellidoPaterno(afiliadoVo.getApellidoPaterno());
					encargado.setApellidoMaterno(afiliadoVo.getApellidoMaterno());
				} else {
					// Busco en Pensionados la información del encargado
					ArrayList resultPensionado = (ArrayList) autoconsultaDao.getDatosPensionado(encargado.getRutEncargado());
					if (!resultPensionado.isEmpty()) {
						logger.debug("Es pensionado");
						PensionadoVO pensionadoVo = (PensionadoVO) resultPensionado.get(0);
						encargado.setNombre(pensionadoVo.getNombre());
						encargado.setApellidoPaterno(pensionadoVo.getApellidos());
					} else {
						// Busco en Ahorrantes la información del encargado
						ArrayList resultAhorrante = (ArrayList) autoconsultaDao.getDatosAhorrante(encargado.getRutEncargado());
						if (!resultAhorrante.isEmpty()) {
							logger.debug("Es ahorrante");
							AhorranteVO ahorranteVo = (AhorranteVO) resultAhorrante.get(0);
							encargado.setNombre(ahorranteVo.getNombre());
							encargado.setApellidoPaterno(ahorranteVo.getApellidoPaterno());
							encargado.setApellidoMaterno(ahorranteVo.getApellidoMaterno());
							;
						} else {
							// Busco en empleados públicos la información del encargado
							ArrayList resultPublico = (ArrayList) autoconsultaDao.getDatosPublico(encargado.getRutEncargado());
							if (!resultPublico.isEmpty()) {
								logger.debug("Es empleado público");
								PublicoVO publicoVo = (PublicoVO) resultPublico.get(0);
								encargado.setNombre(publicoVo.getNombre());
								encargado.setApellidoPaterno(publicoVo.getApellidoPaterno());
								encargado.setApellidoMaterno(publicoVo.getApellidoMaterno());
							}
						}
					}
				}
			}
		}
		return listaEncargados;
	}

	/**
	 * Obtiene datos del cliente
	 * 
	 * @param long
	 *            rut cliente
	 * @return EmpresaACargoVO
	 */
	public EmpresaACargoVO getCliente(long rut) throws Exception, BusinessException {
		EmpresaACargoVO encargado = new EmpresaACargoVO();
		// Busco en Afiliados la información del encargado
		ArrayList resultAfiliado = (ArrayList) autoconsultaDao.getDatosAfiliado(rut);
		if (!resultAfiliado.isEmpty()) {
			AfiliadoVO afiliadoVo = (AfiliadoVO) resultAfiliado.get(0);
			encargado.setNombre(afiliadoVo.getNombre());
			encargado.setApellidoPaterno(afiliadoVo.getApellidoPaterno());
			encargado.setApellidoMaterno(afiliadoVo.getApellidoMaterno());
		} else {
			ArrayList resultPublico = (ArrayList) autoconsultaDao.getDatosPublico(rut);
			if (!resultPublico.isEmpty()) {
				PublicoVO publicoVO = (PublicoVO) resultPublico.get(0);
				encargado.setNombre(publicoVO.getNombre());
				encargado.setApellidoPaterno(publicoVO.getApellidoPaterno());
				encargado.setApellidoMaterno(publicoVO.getApellidoMaterno());
			} else {
				// Busco en Pensionados la información del encargado
				ArrayList resultPensionado = (ArrayList) autoconsultaDao.getDatosPensionado(rut);
				if (!resultPensionado.isEmpty()) {
					PensionadoVO pensionadoVo = (PensionadoVO) resultPensionado.get(0);
					encargado.setNombre(pensionadoVo.getNombre());
					encargado.setApellidoPaterno(pensionadoVo.getApellidos());
				} else {
					// Busco en Ahorrantes la información del encargado
					ArrayList resultAhorrante = (ArrayList) autoconsultaDao.getDatosAhorrante(rut);
					if (!resultAhorrante.isEmpty()) {
						AhorranteVO ahorranteVo = (AhorranteVO) resultAhorrante.get(0);
						encargado.setNombre(ahorranteVo.getNombre());
						encargado.setApellidoPaterno(ahorranteVo.getApellidoPaterno());
						encargado.setApellidoMaterno(ahorranteVo.getApellidoMaterno());
						;
					}
				}
			}
		}
		return encargado;
	}

	/**
	 * Obtiene la lista de RUTs de usuarios
	 * 
	 * @return Collection
	 */
	public Collection getUsuarios() throws Exception, BusinessException {
		return autoconsultaDao.getUsuarios();
	}

	/**
	 * Registra un encargado
	 * 
	 * @param EmpresaACargoVO
	 *            VO con el encargado
	 * @param String []
	 *            indiceOficinasSeleccionadas
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void registrarEncargado(EmpresaACargoVO empresaACargoVO, String[] indiceOficinasSeleccionadas, String usuarioModificador) throws Exception, BusinessException {
		ArrayList listaOficinasSucursalesEncargado = (ArrayList) empresaACargoVO.getListaOficinasSucursalesACargo();
		if (indiceOficinasSeleccionadas == null || indiceOficinasSeleccionadas.equals(""))
			throw new BusinessException("CCAF_AUTO_NOVALOR", "Debe seleccionar al menos una oficina-sucursal");
		if (listaOficinasSucursalesEncargado.size() == 0)
			throw new BusinessException("CCAF_AUTO_NOVALOR", "Empresa no tiene oficina-sucursal");
		logger.debug("indiceOficinasSeleccionadas.size: " + indiceOficinasSeleccionadas.length);
		logger.debug("Lista.size: " + listaOficinasSucursalesEncargado.size());
		// Si todas las oficinas y sucursales están seleccionadas, setea la marca correspondiente
		if (indiceOficinasSeleccionadas.length == listaOficinasSucursalesEncargado.size())
			empresaACargoVO.setAllOficinasSucursales(EmpresaACargoVO.ALL_OFICINAS_SI);
		else
			empresaACargoVO.setAllOficinasSucursales(EmpresaACargoVO.ALL_OFICINAS_NO);
		Collection respuesta = autoconsultaDao.getEncargadoEmpresa(empresaACargoVO);
		if (respuesta.isEmpty()) {
			// No existe previamente
			autoconsultaDao.insertEncargado(empresaACargoVO, usuarioModificador);
			crearOficinasSucursalesACargo(empresaACargoVO, indiceOficinasSeleccionadas);
		} else {
			// Existe previamente
			autoconsultaDao.updateEncargado(empresaACargoVO);
			autoconsultaDao.deleteOficinaSucursalesEncargado(empresaACargoVO.getRutEncargado(), empresaACargoVO.getRut());
			crearOficinasSucursalesACargo(empresaACargoVO, indiceOficinasSeleccionadas);
		}
	}

	/**
	 * Crea los registros de Oficina-sucursal del encargado
	 * 
	 * @param empresaACargoVO
	 * @param String []
	 *            indiceOficinasSeleccionadas
	 * @throws Exception
	 * @throws BusinessException
	 */
	private void crearOficinasSucursalesACargo(EmpresaACargoVO empresaACargoVO, String[] indiceOficinasSeleccionadas) throws Exception, BusinessException {
		if (empresaACargoVO.getAllOficinasSucursales().equals(EmpresaACargoVO.ALL_OFICINAS_NO)) {
			boolean seleccinoAlmenosUna = false;
			ArrayList listaOficinasSucursalesEncargado = (ArrayList) empresaACargoVO.getListaOficinasSucursalesACargo();
			// Desmarca todas las oficinas y sucursales
			for (int x = 0; x < listaOficinasSucursalesEncargado.size(); x++) {
				OficinasSucursalesVO oficinasSucursalesVO = (OficinasSucursalesVO) listaOficinasSucursalesEncargado.get(x);
				oficinasSucursalesVO.setACargo(false);
			}
			// Ahora marca las oficinas y sucursales seleccionadas
			for (int t = 0; t < indiceOficinasSeleccionadas.length; t++) {
				OficinasSucursalesVO oficinasSucursalesVO = (OficinasSucursalesVO) listaOficinasSucursalesEncargado.get((int) Integer.parseInt(indiceOficinasSeleccionadas[t]));
				oficinasSucursalesVO.setACargo(true);
			}
			// Como no está a cargo de todas las sucursales, entonces se insertan una a una cada oficina sucursal
			Collection listaOficinasSucursales = empresaACargoVO.getListaOficinasSucursalesACargo();
			if (!listaOficinasSucursales.isEmpty()) {
				Iterator i = listaOficinasSucursales.iterator();
				while (i.hasNext()) {
					OficinasSucursalesVO oficinasSucursalesVO = (OficinasSucursalesVO) i.next();
					if (oficinasSucursalesVO.isACargo()) {
						seleccinoAlmenosUna = true;
						autoconsultaDao.insertOficinaSucursalesEncargado(empresaACargoVO.getRutEncargado(), empresaACargoVO.getRut(), oficinasSucursalesVO.getCodigoOficina(), oficinasSucursalesVO
								.getCodigoSucursal());
					}
				}
				if (!seleccinoAlmenosUna)
					throw new BusinessException("CCAF_AUTO_NOVALOR", "Debe seleccionar al menos una oficina-sucursal");
			} else
				throw new BusinessException("CCAF_AUTO_NOVALOR", "Debe seleccionar al menos una oficina-sucursal");
		}
	}

	/**
	 * Elimina un encargado
	 * 
	 * @param EmpresaACargoVO
	 *            vo del encargado
	 */
	public void deleteEncargado(EmpresaACargoVO empresaACargoVO, String usuarioModificador) throws Exception, BusinessException {
		// Primero elimina de la tabla at04f1
		autoconsultaDao.deleteOficinaSucursalesEncargado(empresaACargoVO.getRutEncargado(), empresaACargoVO.getRut());
		// Luego elimina de la tabla at03f1
		autoconsultaDao.deleteEncargado(empresaACargoVO, usuarioModificador);
	}

	/**
	 * @param rut
	 * @return Lista de liquidaciones de un trabajador LiquidacionesVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public Collection getLiquidacionReembolsosByRut(long rut) throws Exception, BusinessException {
		Collection resultado = autoconsultaDao.getLiquidacionesReembolsosByRut(rut);
		Collection resConReajuste = new ArrayList();
		if (!resultado.isEmpty()) {
			Iterator i = resultado.iterator();
			while (i.hasNext()) {
				logger.debug("paso por aqui.....................");
				LiquidacionesVO liquidacionesVO = (LiquidacionesVO) i.next();
				String fechaLiq = liquidacionesVO.getFechaLiquidacion();
				logger.debug("Fecha sin Formater : " + fechaLiq);
				logger.debug("Fecha Formateda : " + formatFecha(fechaLiq));
				Collection resjuste = autoconsultaDao.getReajuste(rut, formatFecha(fechaLiq));
				Collection comision = autoconsultaDao.getComision(rut, formatFecha(fechaLiq));
				if (!resjuste.isEmpty()) {
					Iterator ir = resjuste.iterator();
					while (ir.hasNext()) {
						ReajusteVO objReajuste = (ReajusteVO) ir.next();
						liquidacionesVO.setSaldoPrevioLiquidacion(liquidacionesVO.getSaldoPrevioLiquidacion() - objReajuste.getReajuste());
						liquidacionesVO.setReajuste(objReajuste.getReajuste());
						// aqio quede
					}
				}
				if (!comision.isEmpty()) {
					Iterator ic = comision.iterator();
					while (ic.hasNext()) {
						ComisionVO objComision = (ComisionVO) ic.next();
						liquidacionesVO.setSaldoPrevioLiquidacion(liquidacionesVO.getSaldoPrevioLiquidacion() + objComision.getComision());
						liquidacionesVO.setComision(objComision.getComision());
						// aqio quede
					}
				}
				resConReajuste.add(liquidacionesVO);
			}
		}
		return resConReajuste;
		// return autoconsultaDao.getLiquidacionesReembolsosByRut(rut);
	}

	/**
	 * @param rut
	 * @return Lista de liquidaciones de un trabajador LiquidacionesVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public Collection getLiquidacionReembolsosByRut(UsuarioVO usr) throws Exception, BusinessException {
		long rut = usr.getRutAfiliado();
		Collection resultado = autoconsultaDao.getLiquidacionesReembolsosByRut(rut);
		Collection resConReajuste = new ArrayList();
		if (!resultado.isEmpty()) {
			Iterator i = resultado.iterator();
			while (i.hasNext()) {
				logger.debug("paso por aqui.....................");
				LiquidacionesVO liquidacionesVO = (LiquidacionesVO) i.next();
				String fechaLiq = liquidacionesVO.getFechaLiquidacion();
				logger.debug("Fecha sin Formater : " + fechaLiq);
				logger.debug("Fecha Formateda : " + formatFecha(fechaLiq));
				Collection resjuste = autoconsultaDao.getReajuste(rut, formatFecha(fechaLiq));
				Collection comision = autoconsultaDao.getComision(rut, formatFecha(fechaLiq));
				if (!resjuste.isEmpty()) {
					Iterator ir = resjuste.iterator();
					while (ir.hasNext()) {
						ReajusteVO objReajuste = (ReajusteVO) ir.next();
						liquidacionesVO.setSaldoPrevioLiquidacion(liquidacionesVO.getSaldoPrevioLiquidacion() - objReajuste.getReajuste());
						liquidacionesVO.setReajuste(objReajuste.getReajuste());
						// aqio quede
					}
				}
				if (!comision.isEmpty()) {
					Iterator ic = comision.iterator();
					while (ic.hasNext()) {
						ComisionVO objComision = (ComisionVO) ic.next();
						liquidacionesVO.setSaldoPrevioLiquidacion(liquidacionesVO.getSaldoPrevioLiquidacion() + objComision.getComision());
						liquidacionesVO.setComision(objComision.getComision());
						// aqio quede
					}
				}
				resConReajuste.add(liquidacionesVO);
			}
		}
		return resConReajuste;
		// return autoconsultaDao.getLiquidacionesReembolsosByRut(rut);
	}

	
	/**
	 * Obtiene Collection de los datos de un empleado
	 * 
	 * @param long,
	 *            rut del empleado
	 * @return Collection de DatosTrabajadoresLiquidacionesVO
	 */
	public Collection getDatosTrabajadorLiquidaciones(long rut) throws Exception, BusinessException {
		return autoconsultaDao.getDatosTrabajadorLiquidaciones(rut);
	}

	/**
	 * Obtiene Collection de los movimientos de una liquidacion
	 * 
	 * @param long,
	 *            rut del empleado
	 * @return Collection de DatosTrabajadoresLiquidacionesVO
	 */
	public Collection getMovimientosLiquidacion(long rut, String nroliq) throws Exception, BusinessException {
		UsuarioVO usrVo = new UsuarioVO();
		usrVo.setRutAfiliado(rut);
		usrVo.setIpConexion("");

		return getMovimientosLiquidacion(usrVo, nroliq);
	}

	/**
	 * Obtiene Collection de los resumenes por trabajador de un aempresa
	 * 
	 * @param long,
	 *            rut del empleado
	 * @param nroConvenio
	 * @param periodo
	 * @return Collection de ResMensualPrestCompVO
	 */
	public Collection getResumenMensualByEmpresa(long rutEmp, long nroConvenio, String periodo) throws Exception, BusinessException {
		return autoconsultaDao.getResumenMensualByEmpresa(rutEmp, nroConvenio, periodo);
	}

	/**
	 * @param rut
	 * @return
	 */
	public Collection getNumeroConvenio(long rut) throws Exception, BusinessException {
		return autoconsultaDao.getNumeroConvenio(rut);
	}

	public Collection getTip(long agno, long mes, int tipFija, int tipValor) throws Exception, BusinessException {
		double tipVTabla = 0, tipPTabla = 0;
		double tipV, tipP;
		Collection retTipColl = new ArrayList();
		TipVO retTip = new TipVO();
		double exp = 1 / 12.0;
		tipV = tipValor / 100.0;
		Collection tip = autoconsultaDao.getTip(agno, mes);
		Iterator itTip = tip.iterator();
		if (itTip.hasNext()) {
			TipVO tipVO = (TipVO) itTip.next();
			tipPTabla = Double.parseDouble(tipVO.getTipValor());
			tipVTabla = Double.parseDouble(tipVO.getTipPropia());
		} else {
			return tip;
		}
		try {
			if (tipFija == 1) { // TRUE
				retTip.setTipValor(String.valueOf(tipV));
				retTip.setTipPropia(formatDouble(((Math.pow(1 + tipV, exp)) - 1) / 100.0, "#.########"));
			} else {
				retTip.setTipValor(String.valueOf(((tipVTabla > tipPTabla ? tipPTabla : tipVTabla) < tipV ? tipV : (tipVTabla > tipPTabla ? tipPTabla : tipVTabla))));
				retTip.setTipPropia(formatDouble(((Math.pow(1 + tipV, exp)) - 1) / 100.0, "#.########"));
			}
			retTipColl.add(retTip);
			return retTipColl;
		} catch (Exception e) {
			return tip;
		}
	}

	private String formatFecha(String fec) {
		SimpleDateFormat dateFormatter;
		DateFormat timeFormatter;
		String fecha_proc = "1900-01-01";
		dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date dateOut = dateFormatter.parse(fec);
			dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			fecha_proc = dateFormatter.format(dateOut);
		} catch (ParseException e) {
			// TODO Bloque catch generado automáticamente
			return fecha_proc;
		}
		return fecha_proc;
	}

	private String formatDouble(double num, String pattern) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		DecimalFormat df = (DecimalFormat) nf;
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		df.setDecimalSeparatorAlwaysShown(true);
		// String pattern = "#.########";
		df.applyPattern(pattern);
		// Double.valueOf(df.format(num).toString()).doubleValue();
		return df.format(num);
	}

	public Collection getPublicidad() throws Exception, BusinessException {
		return autoconsultaDao.getPublicidad();
	}

	public void putPublicidad(String texto) throws Exception, BusinessException {
		if (texto.length() > 255) {
			autoconsultaDao.putPublicidad(texto.substring(0, 255));
		} else {
			autoconsultaDao.putPublicidad(texto);
		}
	}

	/**
	 * Recupera Collection de oficinas y sucursales de la empresa, además indica si el encargado tiene la oficina o
	 * susursal a su cargo
	 * 
	 * @param long,
	 *            rut encargado long, rut de la empresa
	 * @return Collection de OficinasSucursalesVO
	 */
	public Collection getListaOficinasSucursalesByEmpresaEncargado(long rutEncargado, long rutEmpresa) throws Exception, BusinessException {
		EmpresaACargoVO empresaACargoQuery = new EmpresaACargoVO();
		empresaACargoQuery.setRut(rutEmpresa);
		empresaACargoQuery.setRutEncargado(rutEncargado);
		Collection resultado = autoconsultaDao.getListaOficinasSucursalesByEmpresaEncargado(rutEncargado, rutEmpresa);
		resultado.addAll(autoconsultaDao.getListaOficinasSucursalesByEmpresaPublicaEncargado(rutEncargado, rutEmpresa));
		Collection respuesta = autoconsultaDao.getEncargadoEmpresa(empresaACargoQuery);
		if (!respuesta.isEmpty()) {
			Iterator i = respuesta.iterator();
			EmpresaACargoVO empresaACargoVO = (EmpresaACargoVO) i.next();
			if (empresaACargoVO.getAllOficinasSucursales().equals(EmpresaACargoVO.ALL_OFICINAS_SI)) {
				if (!resultado.isEmpty()) {
					Iterator j = resultado.iterator();
					while (j.hasNext()) {
						OficinasSucursalesVO oficinasSucursalesVO = (OficinasSucursalesVO) j.next();
						oficinasSucursalesVO.setACargo(true);
					}
				}
			}
		}
		return resultado;
	}

	/**
	 * Retorna boolean indicando si el encargado puede o no consultar por el afiliado
	 * 
	 * @param rutEncargado
	 * @param afiliado
	 * @param rutEmpresa
	 * @return boolean
	 * @throws Exception
	 * @throws BusinessException
	 */
	public boolean usuarioPuedeConsultarPorAfiliado(long rutEncargado, long rutEmpresa, AfiliadoVO afiliado) throws Exception, BusinessException {
		boolean resultado = false;
		logger.debug("RutEncargado: " + rutEncargado);
		logger.debug("rutEmpresa: " + rutEmpresa);
		logger.debug("oficina: " + afiliado.getCodigoOficina());
		logger.debug("sucursal: " + afiliado.getCodigoSucursal());
		EmpresaACargoVO empresaACargoQuery = new EmpresaACargoVO();
		empresaACargoQuery.setRut(rutEmpresa);
		empresaACargoQuery.setRutEncargado(rutEncargado);
		Collection respuesta = autoconsultaDao.getEncargadoEmpresa(empresaACargoQuery);
		if (!respuesta.isEmpty()) {
			Iterator i = respuesta.iterator();
			EmpresaACargoVO empresaACargoVO = (EmpresaACargoVO) i.next();
			if (empresaACargoVO.getAllOficinasSucursales().equals(EmpresaACargoVO.ALL_OFICINAS_SI)) {
				resultado = true;
			} else {
				Collection lista = autoconsultaDao.getOficinaSucursalByFiltro(rutEncargado, rutEmpresa, afiliado.getCodigoOficina(), afiliado.getCodigoSucursal());
				if (!lista.isEmpty())
					resultado = true;
			}
		}
		return resultado;
	}

	/**
	 * Recupera Collection de Licencias de un afiliado
	 * 
	 * @param long,
	 *            rut del afiliado
	 * @param long,
	 *            numLicencia
	 * @return Collection de StringVO
	 */
	public Collection listaObservacionesCompin(long rutAfiliado, long numLicencia) throws Exception, BusinessException {
		return autoconsultaDao.listaObservacionesCompin(rutAfiliado, numLicencia);
	}

	/**
	 * Recupera Collection de Licencias de un afiliado
	 * 
	 * @param long,
	 *            rut del afiliado
	 * @param long,
	 *            numLicencia
	 * @return Collection de StringVO
	 */
	public Collection periodosPrestCompl(long rutEmpresa) throws Exception, BusinessException {
		System.out.println("rutEmpresa: " + rutEmpresa);
		return autoconsultaDao.getPeriodosPrestCompl(rutEmpresa);
	}

	/**
	 * obtiene el monto (si es que tiene) pre aprobado de credito para un usuario en particular
	 * 
	 * @param rutCliente
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public long montoCreditoPreaprobado(long rutCliente) throws Exception, SQLException {
		Collection col = autoconsultaDao.montoCreditoPreaprobado(rutCliente);
		long aux = 0;
		if (col.size() > 0) {
			Iterator iter = col.iterator();
			MontoVO monto = (MontoVO) iter.next();
			aux = monto.getMonto();
		}
		return aux;
	}

	/**
	 * registra la simulación en el sistema (siempre la última de un usuario por día)
	 * 
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
	public void setRegistroSimulacionCredito(long rutCliente, String dvCliente, long remuneracionLiq, long montoSolicitado, int numeroCuotas, Date fechaNac, Date fechaIngEmp,
			boolean seguroDegravamen, boolean seguroVida, boolean seguroCesantia, long montoPrepactado, String nombresCliente, long telefono, String correoElectronico, long rutEmpresa,
			String nombreEmpresa) throws Exception, SQLException {
		autoconsultaDao.setRegistroSimulacionCredito(rutCliente, dvCliente, remuneracionLiq, montoSolicitado, numeroCuotas, (fechaNac != null ? new java.sql.Date(fechaNac.getTime()) : null),
				(fechaIngEmp != null ? new java.sql.Date(fechaIngEmp.getTime()) : null), seguroDegravamen, seguroVida, seguroCesantia, montoPrepactado, nombresCliente, telefono, correoElectronico,
				rutEmpresa, nombreEmpresa);
	}

	/**
	 * Trae el email y el telefono del cliente que realiza la simulacion
	 * 
	 * @param rutCliente
	 * @return
	 */
	public Collection datosComplClienteSimulacion(long rutCliente) throws Exception, SQLException {
		// NOTA: puede devolver de [0,2] registros, debo obtener algun email y el primer telefono en caso
		// de que existan 2 registros
		Collection col = autoconsultaDao.datosComplClienteSimulacion(rutCliente);
		long telefono = 0;
		String email = null;
		if (col.size() > 0) {
			Iterator iter = col.iterator();
			while (iter.hasNext()) {
				DatosComplementariosVO vo = (DatosComplementariosVO) iter.next();
				telefono = ((telefono == 0) ? vo.getTelefono() : telefono);
				email = ((email == null) ? vo.getEmail() : email);
			}
		}
		Collection colAux = new ArrayList();
		colAux.add(new DatosComplementariosVO(telefono, email));
		return colAux;
	}

	public Collection datosEmpresaAfiliadoSimulacion(long rutCliente) throws Exception, SQLException {
		return autoconsultaDao.datosEmpresaAfiliadoSimulacion(rutCliente);
	}

	public void setSimulacionEstadistica(String ipSimulacion, int codFuncion) throws Exception, SQLException {
		autoconsultaDao.setSimulacionEstadistica(ipSimulacion, codFuncion);
	}

	/**
	 * Recupera sexo de un afiliado
	 * 
	 * @param long,
	 *            rut del afiliado
	 * @return Collection de StringVO
	 */
	public Collection sexoAfiliadoBaseComun(long rutAfiliado) throws Exception, BusinessException {
		return autoconsultaDao.getSexoAfiliadoBaseComun(rutAfiliado);
	}

	/**
	 * Este método obtiene la DeudaInterCaja del afiliado pasado por parámetro
	 * 
	 * @param rut
	 *            long
	 * @return DeudaIntercajaVO deuVO
	 * @throws Exception
	 *             ex
	 * @throws BusinessException
	 *             bex
	 * @author sebastian.helguera (Neoris Argentina)
	 * @version 30/09/2009
	 */
	public DeudaIntercajaVO getDeudaIntercaja(long rut) throws BusinessException, Exception {

		DeudaIntercajaVO deuVO = null;

		Collection col = autoconsultaDao.getDeudaIntercaja(rut);
		if (null != col && !col.isEmpty()) {
			Iterator it = col.iterator();
			deuVO = (DeudaIntercajaVO) it.next();
		}

		return deuVO;
	}

	/**
	 * Consulta por los cheques caducos de una licencia.
	 * 
	 * @param Collection,
	 *            detalles de licencias
	 * @param long,
	 *            numeroLicencia
	 * @return boolean true en caso de que la licencia tenga cheques caducos. boolean false en caso de que la licencia
	 *         no tenga cheques caducos.
	 * @throws BusinessException
	 * @throws Exception
	 */
	// LPC 2011-03-17 Se agrego parametro rutAfiliado
	private boolean tieneChequesCaducos(Collection detalles, long numeroLicencia, long rutAfiliado) throws BusinessException, Exception {
		boolean resultado = false;
		if (detalles != null && !detalles.isEmpty()) {
			Iterator idetalle = detalles.iterator();
			String folioPago = "";
			// Recorro la lista de detalles
			while (idetalle.hasNext()) {
				LicenciaMedicaDetalleVO detalle = (LicenciaMedicaDetalleVO) idetalle.next();
				// Busco el detalle de la licencia.
				if (detalle.getNumeroLicencia() == numeroLicencia) {
					folioPago = detalle.getFolio();
					break;
				}
			}
			if (!folioPago.equals("")) {
				Long numFolio = new Long(folioPago);
				// Consulto los cheques caducos con el numero de folio de pago de la licencia.
				// LPC 2011-03-17 Se agrego parametro rutAfiliado
				Collection chequesCaducos = autoconsultaDao.getChequesCaducados(numFolio.longValue(), rutAfiliado);

				IntVO cantChequesCaducos = null;
				if (chequesCaducos != null && !chequesCaducos.isEmpty()) {
					Iterator icheques = chequesCaducos.iterator();
					cantChequesCaducos = (IntVO) icheques.next();
				}
				if (cantChequesCaducos != null && cantChequesCaducos.getValor() > 0) {
					resultado = true;
				}
			}
		}
		return resultado;
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
		System.out.println("asm_ getCertificadoLicenciasMedicas()");
		CertificadoLicenciasMedicasVO certificado = new CertificadoLicenciasMedicasVO();
		int totalSubsidioPagado = 0;
		int totalCotizacionPension = 0;
		int totalSalud = 0;
		int totalSubsidioCesantia = 0;
		SimpleDateFormat formatoBD = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
		int mesesAtras = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/autoconsulta/param/licencia-meses-atras"));
		String seguroCesantiaLetra = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/autoconsulta/param/porcentaje-Seguro-Cesantia/letra");
		int seguroCesantiaCodigo = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/autoconsulta/param/porcentaje-Seguro-Cesantia/codigo"));
		GregorianCalendar hoy = new GregorianCalendar();
		hoy.add(Calendar.MONTH, -(mesesAtras));
		String textfecha = formatoBD.format(hoy.getTime());
		logger.debug("fecha: " + textfecha);
		// Consulta licencias medicas del empleado
		Collection listaLicencias = autoconsultaDao.getListaLicenciasMedicasCertificadoByEmpleado(usrVo.getRutAfiliado(), textfecha);
		if (!listaLicencias.isEmpty()) {
			certificado.setTieneLicencias(true);
			double tempRentaImpCot = 0.0;
			double truncRentaImpCot = 0.0;
			double porcentajeSeguroCesantia = 0.0;
			// Calculo de Seguro de Cesantia.
			Collection listaPorcentajeSeguro = autoconsultaDao.getPorcentajeSeguroCesantia(seguroCesantiaLetra, seguroCesantiaCodigo);
			if (!listaPorcentajeSeguro.isEmpty()) {
				Iterator it = listaPorcentajeSeguro.iterator();
				DoubleVO vo = (DoubleVO) it.next();
				porcentajeSeguroCesantia = ((double) vo.getValor()) / 1000.0;
			} else
				throw new BusinessException("CCAF_AUTO_NOVALOR", "No se pudo obtener el Porcentaje del Seguro de Cesantia");
			Iterator iteLicencias = listaLicencias.iterator();
			while (iteLicencias.hasNext()) {
				LicenciaMedicaCertificadoVO licencia = (LicenciaMedicaCertificadoVO) iteLicencias.next();
				licencia.setRentaImponibleCotizacion(Math.round((licencia.getRentaImponible() / 30.0) * licencia.getDiasLicencia()));
				truncRentaImpCot = Math.round((licencia.getRentaImponible() * licencia.getDiasLicencia()) / 30.0);
				tempRentaImpCot = Math.floor((licencia.getRentaImponible() * licencia.getDiasPagados()) / 30.0);
				if (licencia.getLetraAmpliacion().compareTo(" ") != 0) {
					// calculo con PAGLICDIA => numeroDias.
					if ((licencia.getCodInstitutoPrevisional() > 1000) || (licencia.getCodInstitutoPrevisional() == 5))
						licencia.setCotizacionSalud(Math.round((truncRentaImpCot * 7.0) / 100.0));
					else
						licencia.setCotizacionSalud(Math.round(((truncRentaImpCot * 7.0) / 100.0)));
					licencia.setCotizacionPension(Math.round((truncRentaImpCot * licencia.getPorcentajeCotizacion() / 100.0) - licencia.getCotizacionSalud()));
				} else if (licencia.getDiasLicencia() < 11) {
					if ((licencia.getCodInstitutoPrevisional() > 1000) || (licencia.getCodInstitutoPrevisional() == 5))
						licencia.setCotizacionSalud(Math.round((truncRentaImpCot * 7.0) / 100.0));
					else
						licencia.setCotizacionSalud(Math.round(((truncRentaImpCot * 7.0) / 100.0)));
					licencia.setCotizacionPension(Math.round((truncRentaImpCot * licencia.getPorcentajeCotizacion()) / 100.0) - licencia.getCotizacionSalud());
				} else {
					if ((licencia.getCodInstitutoPrevisional() > 1000) || (licencia.getCodInstitutoPrevisional() == 5))
						licencia.setCotizacionSalud(Math.round((tempRentaImpCot * 7.0) / 100.0));
					else
						licencia.setCotizacionSalud(Math.round(((tempRentaImpCot * 7.0) / 100.0)));
					licencia.setCotizacionPension(Math.round((tempRentaImpCot * licencia.getPorcentajeCotizacion()) / 100.0) - licencia.getCotizacionSalud());
				}
				Calendar fechaTemporal = new GregorianCalendar(Integer.parseInt(licencia.getFechaDesde().substring(0, 4)), Integer.parseInt(licencia.getFechaDesde().substring(4, 6)) - 1, Integer
						.parseInt(licencia.getFechaDesde().substring(6, 8)));
				if (licencia.getFechaDesde().length() == 8)
					licencia.setFechaDesde(licencia.getFechaDesde().substring(6, 8) + "/" + licencia.getFechaDesde().substring(4, 6) + "/" + licencia.getFechaDesde().substring(0, 4));
				if (licencia.getFechaHasta().length() == 8)
					licencia.setFechaHasta(licencia.getFechaHasta().substring(6, 8) + "/" + licencia.getFechaHasta().substring(4, 6) + "/" + licencia.getFechaHasta().substring(0, 4));
				Calendar fechaPeriodo = new GregorianCalendar(Integer.parseInt(licencia.getTemporalPeriodo().substring(0, 4)), Integer.parseInt(licencia.getTemporalPeriodo().substring(4, 6)) - 1,
						Integer.parseInt(licencia.getTemporalPeriodo().substring(6, 8)));
				fechaPeriodo.add(Calendar.MONTH, 1);
				licencia.setFechaMesCotizacion(String.valueOf(fechaPeriodo.get(Calendar.MONTH) + 1) + "/" + String.valueOf(fechaPeriodo.get(Calendar.YEAR)));
				Collection listaDatosCalculo = autoconsultaDao.getDatosCalculoSeguroCesantiaByEmpleado(usrVo.getRutAfiliado(), licencia.getLicImpNum(), Constants.SI_TIENE_SEGURO_CESANTIA);
				if (!listaDatosCalculo.isEmpty()) {
					Iterator it = listaDatosCalculo.iterator();
					DoubleVO vo = (DoubleVO) it.next();
					licencia.setRentaImp90(vo.getValor());
					licencia.setCotizacionCesantia(Math.round(licencia.getRentaImp90() * licencia.getDiasLicencia() * porcentajeSeguroCesantia / 30.0));
					totalSubsidioCesantia = totalSubsidioCesantia + (int) licencia.getCotizacionCesantia();
				}
				// CALCULO DE TOTALES
				totalSubsidioPagado = totalSubsidioPagado + (int) licencia.getSubsidioPagado();
				totalCotizacionPension = totalCotizacionPension + (int) licencia.getCotizacionPension();
				totalSalud = totalSalud + (int) licencia.getCotizacionSalud();
			}
			certificado.setLicencias(listaLicencias);
			certificado.setTotalCotizacionPension(totalCotizacionPension);
			certificado.setTotalSalud(totalSalud);
			certificado.setTotalSubsidioCesantia(totalSubsidioCesantia);
			certificado.setTotalSubsidioPagado(totalSubsidioPagado);
			certificado.setSumaTotal(totalCotizacionPension + totalSalud + totalSubsidioCesantia + totalSubsidioPagado);
			certificado.setFechaHoy(new Date());
		}
		logger.debug("Antes de registrar Actividad");
		registrarActividad(usrVo, ActividadVO.CERTIFICADO_LICENCIA_MEDICA);
		logger.debug("Despues de registrar Actividad");
		// Registra los datos para poder validar el certificado
		certificado.setId(generarIdCertificado());
		certificado.setRut(usrVo.getRutAfiliado());
		certificado.setFullNombreAfiliado(nombreConsulta);
		certificado.setFullRutAfiliado(rutConsulta);
		registrarDatosValidacion(certificado.getDatosValidacion());
		return certificado;

	}

	/** 
	 * Obtiene Collection de empleadores de un empleado
	 * Si no lo encuentra en las tablas lo busca en el historico
	 * @param long, rut del empleado
	 * @param String dispositivo
	 * @return Collection de EmpresaVO
	 */
	public Collection getEmpleadoresByEmpleado(UsuarioVO usrVo) throws Exception, BusinessException {
		Collection retorno = new ArrayList();
		logger.debug("Antes de registrar Actividad");
		registrarActividad(usrVo , ActividadVO.CONSULTA_EMPLEADORES);
		logger.debug("Despues de registrar Actividad");
		// Primero se verifica que sea afiliado, en caso contrario, es empleado público
		Collection afiliado = autoconsultaDao.getDatosAfiliado(usrVo.getRutAfiliado());
		if (!afiliado.isEmpty()) {
			retorno = autoconsultaDao.getListaEmpleadoresByEmpleado(usrVo.getRutAfiliado());
			if (retorno.isEmpty()) {
				retorno = autoconsultaDao.getListaHistoricoEmpleadoresByEmpleado(usrVo.getRutAfiliado());
			}
		} else {
			// Es empleado público
			retorno = autoconsultaDao.getListaEmpleadoresByEmpleadoPublico(usrVo.getRutAfiliado());
			if (retorno.isEmpty())
				retorno = autoconsultaDao.getListaHistoricoEmpleadoresByEmpleadoPublico(usrVo.getRutAfiliado());
		}
		return retorno;
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
	public CertificadoDeudaVigenteVO getCertificadoDeudaVigenteByRut(UsuarioVO usrVo, String nombreConsulta, String rutConsulta) throws Exception, BusinessException {
		CertificadoDeudaVigenteVO certificado = new CertificadoDeudaVigenteVO();
		int saldoTotalTitular = 0;
		int saldoTotalAval = 0;
		Collection creditosDirectos = new ArrayList();
		Collection creditosIndirectos = new ArrayList();
		Collection listaTitulares = new ArrayList();
		Collection listaDirectos = autoconsultaDao.getCreditosByRutTitular(usrVo.getRutAfiliado());
		// Esto es para dejar sólo créditos diferentes
		if (!listaDirectos.isEmpty()) {
			String folioAnterior = null;
			Iterator ilistaDirectos = listaDirectos.iterator();
			while (ilistaDirectos.hasNext()) {
				InformacionCreditoVO credito = (InformacionCreditoVO) ilistaDirectos.next();
				if (!credito.getFolio().equals(folioAnterior)) {
					creditosDirectos.add(credito);
					folioAnterior = credito.getFolio();
				}
			}
		}
		Collection listaIndirectos = autoconsultaDao.getCreditosByRutAval(usrVo.getRutAfiliado());
		// Esto es para dejar sólo créditos diferentes
		if (!listaIndirectos.isEmpty()) {
			String folioAnterior = null;
			Iterator ilistaIndirectos = listaIndirectos.iterator();
			while (ilistaIndirectos.hasNext()) {
				InformacionCreditoVO credito = (InformacionCreditoVO) ilistaIndirectos.next();
				if (!credito.getFolio().equals(folioAnterior)) {
					creditosIndirectos.add(credito);
					folioAnterior = credito.getFolio();
				}
			}
		}
		// Si tiene creditos directos o indirectos pregunta por las cuotas
		if (!creditosDirectos.isEmpty() || !creditosIndirectos.isEmpty()) {
			Collection cuotasCreditos = autoconsultaDao.getCuotasCreditosByRut(usrVo.getRutAfiliado());
			// Créditos Directos
			if (!creditosDirectos.isEmpty()) {
				logger.debug("Son: " + creditosDirectos.size() + " Créditos Directos");
				Iterator icd = creditosDirectos.iterator();
				while (icd.hasNext()) {
					InformacionCreditoVO credito = (InformacionCreditoVO) icd.next();
					logger.debug("Folio Crédito: " + credito.getFolio());
					logger.debug("Saldo Impago Antes: " + credito.getSaldoImpago());
					// Recupera información de las cuotas del crédito
					if (!cuotasCreditos.isEmpty()) {
						credito = getCuotas(credito, cuotasCreditos, InformacionCreditoVO.CREDITO_DIRECTO);
					}
					logger.debug("Saldo Impago Despues: " + credito.getSaldoImpago());
					saldoTotalTitular = saldoTotalTitular + (credito.getMontoCuota() * credito.getCuotasImpagas()) + (credito.getMontoCuota() * credito.getCuotasVigentes())
							- credito.getTotalMontoAbonado();
				}
			}
			// Créditos indirectos
			if (!creditosIndirectos.isEmpty()) {
				logger.debug("Son: " + creditosIndirectos.size() + " Créditos Indirectos");
				Iterator ici = creditosIndirectos.iterator();
				while (ici.hasNext()) {
					InformacionCreditoVO credito = (InformacionCreditoVO) ici.next();
					logger.debug("Folio Crédito: " + credito.getFolio());
					logger.debug("Saldo Impago Antes: " + credito.getSaldoImpago());
					// Recupera información de las cuotas del crédito
					if (!cuotasCreditos.isEmpty()) {
						credito = getCuotas(credito, cuotasCreditos, InformacionCreditoVO.CREDITO_INDIRECTO);
					}
					logger.debug("Saldo Impago Despues: " + credito.getSaldoImpago());
					saldoTotalAval = saldoTotalAval + (credito.getMontoCuota() * credito.getCuotasImpagas()) + (credito.getMontoCuota() * credito.getCuotasVigentes()) - credito.getTotalMontoAbonado();
					Collection titular = autoconsultaDao.getDatosTitularCreditoByTitular(credito.getRutTitular());
					if (titular.isEmpty())
						titular = autoconsultaDao.getDatosTitularCreditoByTitularPublico(credito.getRutTitular());
					if (!titular.isEmpty()) {
						Iterator idatos = titular.iterator();
						DatosTitularCreditoVO datosTitular = (DatosTitularCreditoVO) idatos.next();
						credito.setDatosTitular(datosTitular);
					}
				}
			}
		}
		certificado.setCreditosDirectos(creditosDirectos);
		certificado.setCreditosIndirectos(creditosIndirectos);
		certificado.setSaldoTotalAval(saldoTotalAval);
		certificado.setSaldoTotalTitular(saldoTotalTitular);
		certificado.setFechaHoy(new Date());
		logger.debug("Antes de registrar Actividad");
		registrarActividad(usrVo, ActividadVO.CERTIFICADO_DEUDA_VIGENTE);
		logger.debug("Despues de registrar Actividad");
		// Registra los datos para poder validar el certificado
		certificado.setId(generarIdCertificado());
		certificado.setRut(usrVo.getRutAfiliado());
		certificado.setFullNombreAfiliado(nombreConsulta);
		certificado.setFullRutAfiliado(rutConsulta);
		registrarDatosValidacion(certificado.getDatosValidacion());
		return certificado;
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
	public CartolaAhorroVO getCartolaAhorro(UsuarioVO usrVo, String cuentaAhorro) throws Exception, BusinessException {
		logger.debug("En getCartolaAhorro con rut: " + usrVo.getRutAfiliado() + " y cuenta: " + cuentaAhorro);
		Collection cuentas = autoconsultaDao.getListaCuentaAhorroByRut(usrVo.getRutAfiliado(), cuentaAhorro);
		logger.debug("Son: " + cuentas.size() + " Cartolas");
		Iterator icuentas = cuentas.iterator();
		CuentaAhorroVO cuenta = (CuentaAhorroVO) icuentas.next();
		CartolaAhorroVO cartola = new CartolaAhorroVO();
		cartola.setDireccion(cuenta.getDireccion());
		cartola.setNumeroCuenta(cuenta.getNumeroCuenta());
		cartola.setDvNumeroCuenta(cuenta.getDvNumeroCuenta());
		cartola.setComuna(cuenta.getComuna());
		cartola.setCiudad(cuenta.getCiudad());
		cartola.setFechaUltCartola(cuenta.getFechaUltCartola());
		cartola.setTipoCuenta(cuenta.getTipoCuenta());
		cartola.setIndicadorPromesaArriendo(cuenta.getIndicadorPromesaArriendo());
		logger.debug("Fecha Ult. Cartola: " + cartola.getFechaUltCartola());

		cartola.setUltSaldoCuotas(getSaldoEnCuotas(cuenta.getNumeroCuenta(), cuenta.getFechaUltCartola()));
		cartola.setUltValorCuota(cuenta.getUltValorCuota());
		cartola.setEstadoCuenta(cuenta.getEstadoCuenta());
		logger.debug("Direccion Cartola: " + cartola.getDireccion());
		Collection detallesDesplegar = new ArrayList();
		Collection detalles = autoconsultaDao.getDetalleCuentaAhorro(cuenta);
		if (!detalles.isEmpty()) {
			logger.debug("Tiene: " + detalles.size() + " detalles");
			Iterator idetalles = detalles.iterator();
			while (idetalles.hasNext()) {
				DetalleCartolaVO detalle = (DetalleCartolaVO) idetalles.next();
				logger.debug("Fecha detalle: " + detalle.getFechaDetalle());
				detalle.setFechaDetalle(detalle.getFechaDetalle().substring(6, 8) + "/" + detalle.getFechaDetalle().substring(4, 6) + "/" + detalle.getFechaDetalle().substring(0, 4));
				cartola.setFechaBDUltValorCuota(detalle.getFechaBDUltValorCuota().substring(6, 8) + "/" + detalle.getFechaBDUltValorCuota().substring(4, 6) + "/"
						+ detalle.getFechaBDUltValorCuota().substring(0, 4));
				cartola.setValorCuotaActual(detalle.getValorCuotaActual());
				if (detalle.getEstadoMovimiento() == 5 || detalle.getEstadoMovimiento() == 7 || detalle.getEstadoMovimiento() == 8) {
					detallesDesplegar.add(detalle);
					if (detalle.getCodigoMovimiento() < 20) {
						cartola.setSubtotalDepositos(cartola.getSubtotalDepositos() + detalle.getTotalValor());
						cartola.setSubtotalCuotas(cartola.getSubtotalCuotas() + detalle.getCuotas());
					} else {
						cartola.setSubtotalCargos(cartola.getSubtotalCargos() + detalle.getTotalValor());
						cartola.setSubtotalCuotas(cartola.getSubtotalCuotas() - detalle.getCuotas());
					}
				} else if (detalle.getEstadoMovimiento() != 9) {
					if (detalle.getCodigoMovimiento() < 20) {
						// Depósitos
						cartola.setDepositosValorizar(cartola.getDepositosValorizar() + detalle.getTotalValor());
					} else
						cartola.setGirosValorizar(cartola.getGirosValorizar() + detalle.getTotalValor());
					// Giros
				}
			}
			cartola.setDetalles(detallesDesplegar);
		} else {
			logger.debug("No Tiene detalles");
			Collection arrUltValorCuota = autoconsultaDao.getValorCuotaActual();
			Iterator itr = arrUltValorCuota.iterator();
			if (arrUltValorCuota.size() > 0 && arrUltValorCuota.size() < 2) {

				while (itr.hasNext()) {

					ValorCuotaActualVO utv = new ValorCuotaActualVO();
					utv = (ValorCuotaActualVO) itr.next();
					cartola.setValorCuotaActual(utv.getUltimoValorCuota());

				}

			}

		}

		
		//REQ-8000000405
		String factor = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/leasing/cartola-ahorro/factor-valor-cuota");
		float factorF = Float.parseFloat(factor);
		String fechaFiltro = FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/leasing/cartola-ahorro/fecha-filtro");
		if(fechaEsMayorA(fechaFiltro, cartola.getFechaUltCartola())){
			cartola.setSaldoCuotas(cartola.getUltSaldoCuotas()/factorF + cartola.getSubtotalCuotas());
			cartola.setSaldoPesos((int) Math.round(cartola.getSaldoCuotas() * cartola.getValorCuotaActual()));
		}else{
			// El valor cuota ya esta dado.
			cartola.setSaldoCuotas(cartola.getUltSaldoCuotas() + cartola.getSubtotalCuotas());
			cartola.setSaldoPesos((int) Math.round(cartola.getSaldoCuotas() * cartola.getValorCuotaActual()));
		}
		
		cartola.setRentabilidadPeriodo(cartola.getSaldoPesos() - (cartola.getUltSaldoPesos() + cartola.getSubtotalDepositos() - cartola.getSubtotalCargos()));
		cartola.setTotalSaldoContable(cartola.getSaldoPesos() + cartola.getDepositosValorizar() - cartola.getGirosValorizar());
		// Obtiene el saldo máximo de giro
		logger.debug("-- Saldo max Giro Inicio: " + new Date());
		String saldoMaximodeGiro = getSaldoMaximoGiro(cartola.getNumeroCuenta());
		logger.debug("-- Saldo max Giro Fin: " + new Date());
		logger.debug("-- Saldo max Giro: " + saldoMaximodeGiro);
		if (saldoMaximodeGiro != null)
			cartola.setSaldoMaximoGiro(Integer.parseInt(saldoMaximodeGiro));
		else
			throw new BusinessException("CCAF_AUTO_CARTOLAMAXIMOGIRO", "Su cartola no podrá ser emitida por esta vía. Solicítelo directamente en nuestras oficinas");
		cartola.setFecha(new Date());
		logger.debug("Antes de registrar Actividad");
		registrarActividad(usrVo, ActividadVO.CONSULTA_CARTOLA_LEASING);
		logger.debug("Despues de registrar Actividad");
		return cartola;
	}


	/**
	 * Realiza el registro de una actividad
	 * @param long rut
	 * @return void
	 * @throws Exception
	 * @throws BusinessException
	 */
	public void registrarActividad(UsuarioVO usrVo, int funcion)
		throws Exception, BusinessException {
		SimpleDateFormat formatoFechaBD =
			new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		SimpleDateFormat formatoHoraBD =
			new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
		Date fechaActual = new Date();
		String fecha = formatoFechaBD.format(fechaActual);
		String hora = formatoHoraBD.format(fechaActual);
		logger.debug("-------- fecha: " + fecha);
		logger.debug("-------- hora: " + hora);
		ActividadVO actividad = new ActividadVO();
		actividad.setRutAfiliado(usrVo.getRutAfiliado());
		actividad.setRutEmpresa(usrVo.getRutEmpresa());
		actividad.setRutUsuario(usrVo.getRutusuarioAutenticado());
		actividad.setDispositivo(usrVo.getIpConexion());
		actividad.setFuncion(funcion);
		actividad.setFechaTransaccion(formatoFechaBD.parse(fecha));
		actividad.setHoraTransaccion(
			new java.sql.Time(formatoHoraBD.parse(hora).getTime()));
		autoconsultaDao.insertActividad(actividad);
	}


	/**
	 * Obtiene información de los cheques de una empresa
	 * @param long rut
	 * @return 
	 * @throws Exception
	 * @throws BusinessException
	 */
	public Collection getChequesEmpresa(UsuarioVO usrVo) throws Exception, BusinessException {
		logger.debug("En getChequesEmpresa con rut: " + usrVo.getRutAfiliado());
		int mesesAtras = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/autoconsulta/param/cheques-meses-atras"));
		// SimpleDateFormat formatoBD = new SimpleDateFormat ("yyyy-MM-dd", Locale.getDefault());
		GregorianCalendar hoy = new GregorianCalendar();
		hoy.add(Calendar.MONTH, -(mesesAtras));
		java.sql.Date fechaDesde = new java.sql.Date((hoy.getTime()).getTime());
		logger.debug("fecha: " + hoy);
		Collection chequesResultado = new ArrayList();
		// Consulta cheques
		Collection cheques = autoconsultaDao.getChequesEmpresaByRutEmpresa(usrVo.getRutAfiliado(), fechaDesde);
		logger.debug("Son: " + cheques.size() + " Cheques");
		if (!cheques.isEmpty()) {
			Collection conceptos = new ArrayList();
			Collection registros = new ArrayList();
			boolean buscarConceptos = true;
			boolean buscarCantidadRegistros = true;
			Iterator icheques = cheques.iterator();
			while (icheques.hasNext()) {
				ChequeVO chequeVo = (ChequeVO) icheques.next();
				logger.debug("Folio: " + chequeVo.getFolio());
				logger.debug("Fecha Pago: " + chequeVo.getFechaPago());
				if (chequeVo.getMonto() > 0 && !chequeVo.getCodigoEstadoCheque().equals(ChequeVO.ESTD_ANULADO)) {
					if (buscarCantidadRegistros) {
						// Buscar cantidad de registros
						registros = autoconsultaDao.getCantidadChequesEmpresa(usrVo.getRutAfiliado(), fechaDesde);
						buscarCantidadRegistros = false;
					}
					if (!registros.isEmpty()) {
						Iterator iregistros = registros.iterator();
						while (iregistros.hasNext()) {
							CantidadChequesVO cantidadChequesVO = (CantidadChequesVO) iregistros.next();
							if (chequeVo.getFolio() == cantidadChequesVO.getFolio()) {
								chequeVo.setNumeroRegistros(cantidadChequesVO.getCantidad());
								logger.debug("Numero Registros: " + chequeVo.getNumeroRegistros());
								break;
							}
						}
					}
					if (buscarConceptos) {
						// Consulta conceptos cheques
						conceptos = autoconsultaDao.getConceptoChequesEmpresa(usrVo.getRutAfiliado(), fechaDesde);
						logger.debug("Son: " + conceptos.size() + " Conceptos");
						buscarConceptos = false;
					}
					logger.debug("Folio Cheque: " + chequeVo.getFolio());
					if (!conceptos.isEmpty()) {
						Iterator iconceptos = conceptos.iterator();
						while (iconceptos.hasNext()) {
							ConceptoChequeVO conceptoChequeVO = (ConceptoChequeVO) iconceptos.next();
							logger.debug("Concepto Folio:" + conceptoChequeVO.getFolio());
							if (chequeVo.getFolio() == conceptoChequeVO.getFolio()) {
								logger.debug("Observacion Detalle: " + conceptoChequeVO.getObservacionDetalle());
								logger.debug("Item Gasto: " + conceptoChequeVO.getItemGasto());
								logger.debug("Codigo Concepto: " + conceptoChequeVO.getCodigoConcepto());
								chequeVo.setConceptoCheque(conceptoChequeVO);
								break;
							}
						}
					}
					chequesResultado.add(chequeVo);
				}
			}
		} else {
		}
		logger.debug("Antes de registrar Actividad");
		registrarActividad(usrVo, ActividadVO.CONSULTA_CHEQUES_EMPRESA);
		logger.debug("Despues de registrar Actividad");
		return chequesResultado;
	}



	/**
	 * Obtiene información de los créditos del rut consultado
	 * @param long rut
	 * @return Collection, ConsultaCreditoVO
	 * @throws Exception
	 * @throws BusinessException
	 */
	public Collection getCreditosByRut(UsuarioVO usrVo) throws Exception, BusinessException {
		Collection creditos = autoconsultaDao.getCreditosByRut(usrVo.getRutAfiliado());
		Collection cuotasImpagas = autoconsultaDao.getCantidadCuotasImpagasCreditoByRut(usrVo.getRutAfiliado());
		if (!creditos.isEmpty()) {
			logger.debug("Son: " + creditos.size() + " créditos");
			Iterator i = creditos.iterator();
			while (i.hasNext()) {
				ConsultaCreditoVO cre = (ConsultaCreditoVO) i.next();
				// verifica si el crédito es repactado
				Collection repactado = autoconsultaDao.getCreditoRepactado(cre.getFolio(), cre.getOficinaProceso());
				if (!repactado.isEmpty()) {
					Iterator ir = repactado.iterator();
					IntVO valor = (IntVO) ir.next();
					if (valor.getValor() > 0)
						cre.setRepactado(true);
				}
				// Setea información de cuotas impagas
				if (!cuotasImpagas.isEmpty()) {
					logger.debug("Son: " + cuotasImpagas.size() + " cuotas impagas");
					Iterator ic = cuotasImpagas.iterator();
					while (ic.hasNext()) {
						CreditoCuotasVO cuotas = (CreditoCuotasVO) ic.next();
						if (cre.getFolio() == cuotas.getFolio()) {
							cre.setCuotasImpagas(cuotas.getCuotasImpagas());
							break;
						}
					}
				}
			}
		}
		logger.debug("Antes de registrar Actividad");
		registrarActividad(usrVo, ActividadVO.CONSULTA_CREDITOS_VIGENTES);
		logger.debug("Despues de registrar Actividad");
		return creditos;

	}

	/** 
	 * Obtiene Collection de los movimientos de una liquidacion
	 * @param long, rut del empleado
	 * @return Collection de DatosTrabajadoresLiquidacionesVO
	 */
	public Collection getMovimientosLiquidacion(UsuarioVO usrVo, String nroliq)
		throws Exception, BusinessException {
		registrarActividad(usrVo, ActividadVO.CONSULTA_LICENCIA_MEDICA); 
		return autoconsultaDao.getMovimientosLiquidacion(usrVo.getRutAfiliado(), nroliq);
	}

	public void setSimulacionEstadistica(UsuarioVO usrVo)
		throws Exception, SQLException {
		registrarActividad(usrVo,ActividadVO.CONSULTA_EVALUACION_CREDITO);
		//autoconsultaDao.setSimulacionEstadistica(ipSimulacion, codFuncion);
	}
	
	public void insertarActividad(UsuarioVO usrVo, int codFuncion)
		throws Exception, SQLException {
		registrarActividad(usrVo,codFuncion);
	}
	
	
	public LicenciaMedicaVO getBitacoraLicenciaMedica(long rutAfiliado, long nroLicencia, String fechaHasta) throws Exception{
		LicenciaMedicaVO licencia = null;
		
		try {
			SimpleDateFormat formatoBD = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
			int mesesAtras = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/autoconsulta/param/licencia-meses-atras"));
			GregorianCalendar hoy = new GregorianCalendar();
			hoy.add(Calendar.MONTH, -(mesesAtras));
			String fechaDesde = formatoBD.format(hoy.getTime());
			/* NumeroLicencia()
			 * Visada()
			 * EstadoLicencia()
			 * FechaRecepcion(
			 */

			//REQ-8540
			//Fecha de recepción (ingreso) 
			/**
			 * (LICFECING)	K Rut Afiliado (parámetro)
				K Fecha Hasta (99999999)
				K Numero licencia (0)
				ILF8600 Envío a COMPIN
				ILF1000 Registro control S.I.L. (F-92)	
				Si Existe (8600) 
				    Cargar Campo 05 = Fecha de recepción (FECRECEP .8600).
				 
				Si Existe (1000) 
				    Cargar Campo 05 = Fecha de ingreso (LICFECING.1000). 
			 */
			List licencias = (List) autoconsultaDao.getLicenciaMedicaByNro(rutAfiliado,nroLicencia,fechaDesde);
			
			if(licencias == null || licencias.get(0)==null){
				logger.debug("No se encontró licencia para el nro. de licencia y rut consultado");
				return licencia;
			}
			
			//Asignar fecha de ingreso
			licencia = (LicenciaMedicaVO)licencias.get(0);
			String fechaRecepcion = licencia.getFechaRecepcion();
			licencia.setFechaRecepcion(Funciones.pasaFechaAsToWeb(licencia.getFechaRecepcion()));
			
			
			//Calcular fecha hasta
			//Validar estado de licencia
			System.out.println("Estado licencia: " + licencia.getCodigoEstadoLicencia() + ", Visada: " + + licencia.getVisada());
			if(licencia.getVisada()==2 && (licencia.getCodigoEstadoLicencia().equals(Constants.STD_LICENCIA_AUTORIZADA) 
				|| licencia.getCodigoEstadoLicencia().equals(Constants.STD_LICENCIA_PENDIENTE))){
				licencia.setFechaEnvioCompin(Constants.TXT_LICENCIA_ANTES_COMPIN);
				licencia.setFechaRecepcionCompin(Constants.TXT_LICENCIA_ANTES_COMPIN);
				licencia.setFechaDePago(Constants.TXT_LICENCIA_ANTES_COMPIN);
			}else{
				//Obtener fechaRecepcionCompin
				String fechaResolucion = autoconsultaDao.obtenerFechaResolucion(rutAfiliado, 
														nroLicencia, 
														fechaHasta);
				
				if(fechaResolucion.equals("00/00/0000")){
					licencia.setFechaRecepcionCompin(fechaResolucion);
				}else{
					licencia.setFechaRecepcionCompin(Funciones.pasaFechaAsToWeb(fechaResolucion));
				}
				
				//Obtener region segun oficina de origen
				String codRegion = licencia.getCodRegionOrigen();
				
				//Si es de region metropolitana
				if(codRegion.trim().equals(Constants.COD_REGION_METROPOLITANA)){
					//Sumar 3 dias habiles descontando feriados
					String periodo = fechaRecepcion.substring(0, 6);
					Date fechaIngreso = Funciones.parseFechaWebToDate(licencia.getFechaRecepcion());
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(fechaIngreso);
					calendar.add(Calendar.DAY_OF_YEAR, 1);
					List feriadosPeriodo = (List) ((List) autoconsultaDao.obtenerFeriadosPeriodo(periodo)).get(0);
					
					/**
					 * Validar si el mismo periodo, 
					 */
					int cont = 0;
					while(cont<3){
						//Si no encuentra el periodo, setea la fecha en 00/00/0000
						if(feriadosPeriodo==null){
							licencia.setFechaEnvioCompin(Constants.TXT_LICENCIA_ANTES_COMPIN);
							break;
						}
						
						String periodoNuevo = Funciones.getPeriodo(calendar.getTime());
						//Validar si es el mismo periodo
						int diaDelMes = calendar.get(Calendar.DAY_OF_MONTH)-1;//empieza desde 0
						//Verificar si no es el mismo periodo y consultar
						if(!periodo.equals(periodoNuevo)){
							feriadosPeriodo = (List) ((List) autoconsultaDao.obtenerFeriadosPeriodo(periodoNuevo)).get(0);
						}
						if(String.valueOf(feriadosPeriodo.get(diaDelMes)).equals("1")){
							cont++;
							//Establecer nueva fecha
							licencia.setFechaEnvioCompin(Funciones.parseDateToWeb(calendar.getTime()));
						}
						calendar.add(Calendar.DAY_OF_YEAR, 1);
					}
				}else{//No es Region metropolitana
					licencia.setFechaEnvioCompin(Constants.FECHA_ENVIO_COMPIN_NO_RM);
				}
					try {
						licencia.setFechaDePago(Funciones.pasaFechaAsToWeb(licencia.getFechaDePago()));
					} catch (Exception e) {
						licencia.setFechaDePago(Constants.TXT_LICENCIA_ANTES_COMPIN);
					}
			}
			
		} catch (Exception e) {
			logger.error("Se produjo un error al obtener la licencia",e);
			throw new Exception("Se produjo un error al obtener la licencia consultada: ",e);
		}
		return licencia;
	}
	
	/**
	 * Retorna listas con Liciencias Medicas de un rut consultado
	 * @param long rut
	 * @param String dispositivo
	 * @return Collection de LicenciasVO
	 */

	public Collection getConsultaLicenciasMedicas(UsuarioVO usrVo) throws Exception, BusinessException {
		Collection ret = new ArrayList();
		SimpleDateFormat formatoBD = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
		int mesesAtras = Integer.parseInt(FileSettings.getValue(AppConfig.getInstance().settingsFileName, "/application-settings/autoconsulta/param/licencia-meses-atras"));
		GregorianCalendar hoy = new GregorianCalendar();
		hoy.add(Calendar.MONTH, -(mesesAtras));
		String textfecha = formatoBD.format(hoy.getTime());
		logger.debug("fecha: " + textfecha);
		ArrayList resultado = new ArrayList();
		// Consulta licencias medicas del empleado
		System.out.println("getListaLicenciasMedicasByEmpleado");
		Collection listaLicencias = autoconsultaDao.getListaLicenciasMedicasByEmpleado(usrVo.getRutAfiliado(), textfecha);
		// Consulta licencias medicas detalladas
		System.out.println("getListaDetallesLicenciasMedicasByEmpleado");
		Collection lista1L = autoconsultaDao.getListaDetallesLicenciasMedicasByEmpleado(usrVo.getRutAfiliado());
		System.out.println("getListaDetallesLicenciasMedicasByEmpleadoLiquidacion");
		Collection lista2L = autoconsultaDao.getListaDetallesLicenciasMedicasByEmpleadoLiquidacion(usrVo.getRutAfiliado());
		// Collection listaDetallesLicenciasMedicas =
		// autoconsultaDao.getListaDetallesLicenciasMedicasByEmpleado(rut);
		Collection listaDetallesLicenciasMedicas = mergeMontos(lista1L, lista2L);
		if (!listaLicencias.isEmpty()) {
			logger.debug("listaLicencias: " + listaLicencias.size());
			Iterator it = listaLicencias.iterator();
			while (it.hasNext()) {
				LicenciaMedicaVO licencia = (LicenciaMedicaVO) it.next();
				logger.debug("licencia numero: " + licencia.getNumeroLicencia());
				System.out.println("licencia.getNumeroLicencia();" + licencia.getNumeroLicencia());
				licencia.setFechaHasta(calcularFechaHasta(licencia));

				licencia.setFechaDesde(licencia.getFechaDesde().substring(6, 8) + "/" + licencia.getFechaDesde().substring(4, 6) + "/" + licencia.getFechaDesde().substring(0, 4));
				System.out.println("fecha desde: " + licencia.getFechaDesde().substring(6, 8) + "/" + licencia.getFechaDesde().substring(4, 6) + "/" + licencia.getFechaDesde().substring(0, 4));
				// Modificacion Neoris 17/12/2009
				// Consulto los pagos liquidados para cada licencia
				Collection pagosLiquidados = autoconsultaDao.getPagosLiquidados(licencia.getNumeroLicencia(), usrVo.getRutAfiliado());
				IntVO cantidadPagosLiq = null;
				if (pagosLiquidados != null && !pagosLiquidados.isEmpty()) {
					Iterator ipagos = pagosLiquidados.iterator();
					cantidadPagosLiq = (IntVO) ipagos.next();
				}
				System.out.println("Num Lic: " + licencia.getNumeroLicencia());
				System.out.println("Visada: " + licencia.getVisada());
				System.out.println("Estado Lic: " + licencia.getCodigoEstadoLicencia());
				System.out.println("Codigo Observacion: " + licencia.getCodigoObservacion1());
				System.out.println("Observacion: " + licencia.getObservacion1());

				// REQ-8540cambio de lugar de Validacion de visada = 2
				String licofipag = "Datos de Licencia no Disponibles";
				licencia.setOficinaPago(licofipag);

				boolean licenciaRegistrada = false;
				if (licencia.getVisada() == LicenciaMedicaVO.VISADA) {
					if (licencia.getCodigoEstadoLicencia().equals(Constants.STD_LICENCIA_AUTORIZADA)) {
						// Licencias Autorizadas
						// Verifica si la licencia tiene detalles 
						if (!listaDetallesLicenciasMedicas.isEmpty()) {
							logger.debug("Lista Detalles Licencias: " + listaDetallesLicenciasMedicas.size());
							//Iterator iDet = listaDetallesLicenciasMedicas.iterator();
							
							List listaLicDet = new ArrayList(listaDetallesLicenciasMedicas);//NUEVO
							//boolean primerDetalle = true;
							// Agregado 29-06-2006
							String licefecpag = "--------";
							licencia.setFechaDePago(licefecpag);
							String diaspago = "*";
							licencia.setDiasDePago(diaspago);
							int montopagar = 0;
							licencia.setMontoAPagar(montopagar);
							//System.out.println("fecha pago: " + licefecpag);
							//System.out.println("dias de pago: " + diaspago);
							//System.out.println("monto a pagar: " + montopagar);
							//while (iDet.hasNext()) {
							System.out.println("DETALLE NUEVA LISTA: " + listaLicDet.size());
							for(int i =0;i<listaLicDet.size();i++){
								//LicenciaMedicaDetalleVO licenciaDetallada = (LicenciaMedicaDetalleVO) iDet.next();
								LicenciaMedicaDetalleVO licenciaDetallada = (LicenciaMedicaDetalleVO) listaLicDet.get(i);
								
								//if (licenciaDetallada.getNumeroLicencia() == licencia.getNumeroLicencia()) {
								if (licenciaDetallada.getNumeroLicencia() == licencia.getNumeroLicencia() &&
										esFechaEntre(licenciaDetallada.getFechaDesde(), licenciaDetallada.getFechaHasta(), licencia.getFechaDesde(), licencia.getFechaHasta())) {
									logger.debug("Autorizada Con Detalle");
							/*		if (primerDetalle) {
										System.out.println("*primerDetalle");
										primerDetalle = false;
										LicenciaMedicaVO newLicencia = new LicenciaMedicaVO(licenciaDetallada);
										licencia.setFechaDesde(newLicencia.getFechaDesde().substring(6, 8) + "/" + newLicencia.getFechaDesde().substring(4, 6) + "/"
												+ newLicencia.getFechaDesde().substring(0, 4));
										licencia.setFechaHasta(newLicencia.getFechaHasta().substring(6, 8) + "/" + newLicencia.getFechaHasta().substring(4, 6) + "/"
												+ newLicencia.getFechaHasta().substring(0, 4));
										licencia.setDiasLicencia(newLicencia.getDiasLicencia());
										licencia.setFechaDePago(newLicencia.getFechaDePago());
										licencia.setDiasDePago(newLicencia.getDiasDePago());
										licencia.setMontoAPagar(newLicencia.getMontoAPagar());
										licencia.setOficinaPago(newLicencia.getOficinaPago());
										if (newLicencia.getCodigoObservacion1() == 17) {
											logger.debug("newLicencia.getObservacion1() : " + newLicencia.getObservacion1());
											licencia.setCodigoObservacion1(newLicencia.getCodigoObservacion1());
											licencia.setObservacion1(newLicencia.getObservacion1());
											System.out.println("licencia " + newLicencia.getCodigoObservacion1());
											System.out.println("licencia " + newLicencia.getObservacion1());
										}
										if (newLicencia.getCodigoObservacion2() == 17) {
											logger.debug("newLicencia.getObservacion2() : " + newLicencia.getObservacion2());
											licencia.setCodigoObservacion2(newLicencia.getCodigoObservacion2());
											licencia.setObservacion2(newLicencia.getObservacion2());
											System.out.println("licencia " + newLicencia.getCodigoObservacion2());
											System.out.println("licencia " + newLicencia.getObservacion2());
										}
										if (newLicencia.getCodigoObservacion3() == 17) {
											logger.debug("newLicencia.getObservacion3() : " + newLicencia.getObservacion3());
											licencia.setCodigoObservacion3(newLicencia.getCodigoObservacion3());
											licencia.setObservacion3(newLicencia.getObservacion3());
											System.out.println("licencia " + newLicencia.getCodigoObservacion3());
											System.out.println("licencia " + newLicencia.getObservacion3());
										}
										// licencia.setListaObservacionesCompin(
										// newLicencia
										// .getListaObservacionesCompin());
										// fin Raúl
									}// else {
*/									LicenciaMedicaVO newLicencia = new LicenciaMedicaVO(licenciaDetallada);
									LicenciaMedicaVO addLicencia = new LicenciaMedicaVO();
									addLicencia.setNumeroLicencia(licencia.getNumeroLicencia());
									addLicencia.setCodigoEstadoLicencia(licencia.getCodigoEstadoLicencia());
									addLicencia.setCodOficinaPago(licencia.getCodOficinaPago());
									//addLicencia.setOficinaPago(licencia.getOficinaPago());
									licencia.setOficinaPago(newLicencia.getOficinaPago());
									
									addLicencia.setCodigoFormaDePago(licencia.getCodigoFormaDePago());
									addLicencia.setCodigoObservacion1(licencia.getCodigoObservacion1());
									addLicencia.setCodigoObservacion2(licencia.getCodigoObservacion2());
									addLicencia.setCodigoObservacion3(licencia.getCodigoObservacion3());
									addLicencia.setObservacion1(licencia.getObservacion1());
									addLicencia.setObservacion2(licencia.getObservacion2());
									addLicencia.setObservacion3(licencia.getObservacion3());
									addLicencia.setListaObservacionesCompin(licencia.getListaObservacionesCompin());
									addLicencia.setVisada(licencia.getVisada());
									
									//addLicencia.setFechaDesde(newLicencia.getFechaDesde().substring(6, 8) + "/" + newLicencia.getFechaDesde().substring(4, 6) + "/"+ newLicencia.getFechaDesde().substring(0, 4));
									//addLicencia.setFechaHasta(newLicencia.getFechaHasta().substring(6, 8) + "/" + newLicencia.getFechaHasta().substring(4, 6) + "/"+ newLicencia.getFechaHasta().substring(0, 4));
									addLicencia.setFechaDesde(FechaUtil.fechaAs400ToWeb(newLicencia.getFechaDesde()));
									addLicencia.setFechaHasta(FechaUtil.fechaAs400ToWeb(newLicencia.getFechaHasta()));
									addLicencia.setDiasLicencia(newLicencia.getDiasLicencia());
									addLicencia.setDiasDePago(newLicencia.getDiasDePago());
									addLicencia.setMontoAPagar(newLicencia.getMontoAPagar());
									//addLicencia.setFechaDePago(newLicencia.getFechaDePago());
									//addLicencia.setFechaDePago(substring(6, 8) + "/" + addLicencia.getFechaDePago().substring(4, 6) + "/"+ addLicencia.getFechaDePago().substring(0, 4));
									addLicencia.setFechaDePago(FechaUtil.fechaAs400ToWeb(newLicencia.getFechaDePago()));
									resultado.add(addLicencia);
									licenciaRegistrada = true;
									
									//Eliminar la licencia ya mapeada
									listaDetallesLicenciasMedicas.remove(licenciaDetallada);
									// }
								}
							}
						}
						//licencia.setFechaDePago(licencia.getFechaDePago().substring(6, 8) + "/" + licencia.getFechaDePago().substring(4, 6) + "/" + licencia.getFechaDePago().substring(0, 4));
						licencia.setFechaDePago(FechaUtil.fechaAs400ToWeb(licencia.getFechaDePago()));
					} else {
						System.out.println("Licencias No Autorizadas");
						// Licencias No Autorizadas
						// Busca las observaciones que la licencia puede tener
						// en el compin
						Collection lista1 = autoconsultaDao.getListaObservacionesCompinByEmpleado(usrVo.getRutAfiliado(), licencia.getNumeroLicencia());
						logger.debug("lista1 : " + lista1.toString());
						Collection lista2 = autoconsultaDao.getListaObservacionesCompinByEmpleado2(usrVo.getRutAfiliado(), licencia.getNumeroLicencia());
						logger.debug("lista2 : " + lista2.toString());
						Collection observaciones = juntaGlosas(lista1, lista2);
						// Collection observaciones =
						//autoconsultaDao.getListaObservacionesCompinByEmpleado(
						// rut,licencia.getNumeroLicencia());
						if (!observaciones.isEmpty()) {
							System.out.println("****observaciones compin: " + observaciones);
							licencia.setListaObservacionesCompin(observaciones);
						}
						// licencia.getListaObservacionesCompin().add(
						// observaciones);
						logger.debug("licenciasNoAutorizadas");
					}
				} else {
					System.out.println("Licencias No Visada");
					// Licencias No VIsada
					// Busca las observaciones que la licencia puede tener en el
					// compin
					Collection lista1 = autoconsultaDao.getListaObservacionesCompinByEmpleado(usrVo.getRutAfiliado(), licencia.getNumeroLicencia());
					logger.debug("lista1 : " + lista1.toString());
					Collection lista2 = autoconsultaDao.getListaObservacionesCompinByEmpleadoNoVisada(usrVo.getRutAfiliado(), licencia.getNumeroLicencia());
					logger.debug("lista2 : " + lista2.toString());
					Collection observaciones = juntaGlosas(lista1, lista2);
					// Collection observaciones =
					//autoconsultaDao.getListaObservacionesCompinByEmpleado(rut,
					// licencia.getNumeroLicencia());
					Collection lista3 = null;
					Iterator lista3It = null;
					String ultObsComp = null;
					String estadoLicenciaAux = null;
					String glosaLicenciaAux = null;
					if (licencia.getVisada() == LicenciaMedicaVO.NOVISADA_COMPINOCCIDENTE) {
						if (Integer.parseInt(licencia.getCodigoEstadoLicencia()) == 6) {
							System.out.println("NOVISADA_COMPINOCCIDENTE");
							lista3 = autoconsultaDao.getListaObservacionesCompinOcc(usrVo.getRutAfiliado(), licencia.getNumeroLicencia());
							if (!lista3.isEmpty()) {
								lista3 = traduceObservaciones(lista3);
								observaciones.addAll(lista3);
								// juntaGlosas(observaciones, lista3);
							}
						} else if (Integer.parseInt(licencia.getCodigoEstadoLicencia()) == 7) {
							System.out.println("getCodigoEstadoLicencia()) == 7");
							lista3 = autoconsultaDao.getUltimaObsCompinOcc(usrVo.getRutAfiliado(), licencia.getNumeroLicencia());
							if (!lista3.isEmpty()) {
								lista3It = lista3.iterator();
								while (lista3It.hasNext()) {
									ultObsComp = ((StringVO) lista3It.next()).getTexto();
									estadoLicenciaAux = ultObsComp.substring(0, 1);
									glosaLicenciaAux = ultObsComp.substring(2);
									if (Integer.parseInt(estadoLicenciaAux) == LicenciaMedicaVO.PERTENECE_ISAPRE) {
										licencia.setCodigoSubEstadoLicencia(LicenciaMedicaVO.PERTENECE_ISAPRE);
										licencia.setObsIsapre(glosaLicenciaAux);
									} else if (Integer.parseInt(estadoLicenciaAux) == LicenciaMedicaVO.BLOQUEADA_FONASA)
										licencia.setCodigoSubEstadoLicencia(LicenciaMedicaVO.BLOQUEADA_FONASA);
								}
							}
						}
					}
					if (!observaciones.isEmpty())
						licencia.setListaObservacionesCompin(observaciones);
					//licencia.getListaObservacionesCompin().add(observaciones);
					logger.debug("licenciasNoVisadas");
				}

				// ASM: todo: analizar si es correcto agregar una licencia al
				// listado en esta linea.
				if (!licenciaRegistrada) {
					resultado.add(licencia);
					System.out.println("2. " + licencia.getNumeroLicencia() + ", Monto: " + licencia.getMontoAPagar());
				}

				// Para las licencias en estado 2 o 3 se pone la fecha de pago
				// en ""
				if (licencia.getCodigoEstadoLicencia().equals(Constants.STD_LICENCIA_PENDIENTE) || licencia.getCodigoEstadoLicencia().equals(Constants.STD_LICENCIA_SIN_DERECHO)) {
					System.out.println("STD_LICENCIA_PENDIENTE");
					licencia.setFechaDePago("");
				}
				// Para las licencias con cheques caducos se pone la fecha de
				// pago en ""
				// LPC 2011-03-17 se le agrego el rut del afiliado.
				if (tieneChequesCaducos(lista1L, licencia.getNumeroLicencia(), usrVo.getRutAfiliado())) {
					System.out.println("STD_LICENCIA_CADUCADA");
					licencia.setCodigoEstadoLicencia(Constants.STD_LICENCIA_CADUCADA);
				}
				// Para las licencias con dias de pago igual a = 0 se pone la
				// fecha de pago en ""
				if (licencia.getDiasDePago() == null || licencia.getDiasDePago().equals("0")) {
					System.out.println("setFechaDePago: null");
					licencia.setFechaDePago("");
				}
				// Modificacion Neoris 21/10/2009
				// Modificacion Neoris 17/12/2009
				// Se removio esta condicion del if: (pagaux3 != null &&
				// pagaux3.getValor() == 1)
				if (cantidadPagosLiq != null && cantidadPagosLiq.getValor() > 0) {
					System.out.println("setVisada: 2");
					licencia.setVisada(2);
					// Modificacion Neoris 19/11/2009
					// licencia.setCodigoEstadoLicencia("1");
					System.out.println("setCodigoEstadoLicencia: 3");
					licencia.setCodigoEstadoLicencia("3");
					// FIN Modificacion Neoris 19/11/2009
				}
				// FIN Modificacion Neoris 21/10/2009
			}// End while
		}
		logger.debug("Antes de registrar Actividad");
		registrarActividad(usrVo, ActividadVO.CONSULTA_LICENCIA_MEDICA);
		logger.debug("Despues de registrar Actividad");
		// asm_ validador de estado de licencias
		Iterator itr = resultado.iterator();
		while (itr.hasNext()) {
			LicenciaMedicaVO lic = (LicenciaMedicaVO) itr.next();
			System.out.println("licencia visada: " + lic.getVisada());
			if (lic.getVisada() == 2) {
				if (null != lic.getFechaDePago() && lic.getFechaDePago() != "0" && lic.getFechaDePago().trim() != "") {
					//System.out.println("*** fecha de pago: " + lic.getFechaDePago());
					//System.out.println("*** tiene fecha de pago y codigo visada 2, reseteando visada 1");
					lic.setVisada(1);
					if (lic.getCodigoEstadoLicencia().equals("3") && lic.getMontoAPagar() > 0) {
						//System.out.println("*** tiene fecha de pago y codigo, reseteando codigo 1");
						lic.setCodigoEstadoLicencia("1");
					}
				}
			}
		}
		return resultado; // listaLicencias;
	}		

	
	private String calcularFechaHasta(LicenciaMedicaVO licencia){
		Calendar tempFechaHasta = new GregorianCalendar();
		tempFechaHasta = new GregorianCalendar(Integer.parseInt(licencia.getFechaDesde().substring(0, 4)), Integer.parseInt(licencia.getFechaDesde().substring(4, 6)) - 1, Integer
				.parseInt(licencia.getFechaDesde().substring(6, 8)));
		tempFechaHasta.add(Calendar.DAY_OF_MONTH, licencia.getDiasLicencia() - 1);
		// AUMENTAR LA FECHA HASTA DE ACUERDO A LOS DIAS DE LICENCIA.
		licencia.setFechaHasta(Funciones.llenaConCeros(String.valueOf(tempFechaHasta.get(Calendar.DAY_OF_MONTH)),2) + "/" + Funciones.llenaConCeros(String.valueOf(tempFechaHasta.get(Calendar.MONTH) + 1), 2) + "/"
				+ String.valueOf(tempFechaHasta.get(Calendar.YEAR)));
		System.out.println("fecha hasta: " + String.valueOf(tempFechaHasta.get(Calendar.DAY_OF_MONTH)) + "/" + String.valueOf(tempFechaHasta.get(Calendar.MONTH) + 1) + "/"
				+ String.valueOf(tempFechaHasta.get(Calendar.YEAR)));
		return licencia.getFechaHasta();
	}
	
	public static boolean fechaEsMayorA(String fechaUno, String fechaDos) throws ParseException {
		DateFormat formatoAs400 = new SimpleDateFormat("yyyyMMdd");
		if (fechaDos != null && fechaDos.length() == 0) {
			return true;//Primera ejecucion, evita comprobar fechas en null o de largo 0
		}

		if (formatoAs400.parse(fechaUno).after(formatoAs400.parse(fechaDos))) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean esFechaEntre(String fechaDesdeDet, String fechaHastaDet,String fechaDesdeCab, String fechaHastaCab){
		boolean res=false;
		try {
			Date fecDesdeDet = FechaUtil.parseFechaAs400(fechaDesdeDet);
			Date fecDesdeCab = FechaUtil.parseFechaWeb(fechaDesdeCab);
			Date fecHasDet = FechaUtil.parseFechaAs400(fechaHastaDet);
			Date fecHasCab = FechaUtil.parseFechaWeb(fechaHastaCab);
			
			//Valida que las fechas de detalle estén entre las fechas de cabecera
			if(fecDesdeDet.compareTo(fecDesdeCab)>=0 &&
					fecHasDet.compareTo(fecHasCab)<=0){
				res = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}
		return res;
	}
}
