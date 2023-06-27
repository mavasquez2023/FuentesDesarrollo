package cl.araucana.autoconsulta.ui.actions.simulacionCredito;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.autoconsulta.common.Constants;
import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.DatosComplementariosVO;
import cl.araucana.autoconsulta.vo.DeudaIntercajaVO;
import cl.araucana.autoconsulta.vo.EmpresaAfiliadoVO;
import cl.araucana.autoconsulta.vo.ResultadoValidacionSolicitudVO;
import cl.araucana.autoconsulta.vo.StringVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.common.BusinessException;
import cl.azurian.sce.Constantes;
import cl.azurian.sce.dao.FactoryDao;
import cl.azurian.sce.dao.MQDao;
import cl.azurian.sce.dto.DatosSimulacionTO;
import cl.azurian.sce.dto.ResultadosSimulacionTO;
import cl.azurian.sce.dto.UsuarioTO;
import cl.azurian.sce.dto.servicios.Crc414;
import cl.azurian.sce.dto.servicios.Crc430;
import cl.azurian.sce.dto.servicios.Crc440;
import cl.azurian.sce.dto.servicios.Crc468;
import cl.azurian.sce.dto.servicios.SubCrc403;
import cl.azurian.sce.dto.servicios.SubCrc414;
import cl.azurian.sce.dto.servicios.SubCrc468;
import cl.azurian.sce.exceptions.MQException;
import cl.azurian.sce.manager.FactoryManager;
import cl.azurian.sce.manager.SimulacionManager;
/**
 * @version 	1.0
 * @author asepulveda
 */
public class PrepareSimulacionCreditoAction extends Action {
	private static Logger logger =
		Logger.getLogger(PrepareSimulacionCreditoAction.class);
	public static final String FORWARD_ingresoDatos = "ingresoDatos";
	//public static final String FORWARD_ingresoFechas = "ingresoFechas";
	public static final String FORWARD_seleccionSeguros = "seleccionSeguros";
	public static final String FORWARD_creditos = "creditos";
	public static final String FORWARD_resultado = "resultado";
	public static final String FORWARD_erroresSimulacion = "erroresSimulacion";
	public static final String GLOBAL_FORWARD_definirEmpleado = "definirEmpleado";
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
			
		HttpSession session = request.getSession(true);
		Locale locale = new Locale("ES", "CL");
		System.out.println("Configuracion regional Kiosco : " + locale);
		session.setAttribute("RegSettingSrv", locale);
		DynaValidatorActionForm daf = (DynaValidatorActionForm) form;
		ServicesAutoconsultaDelegate delegate =
			new ServicesAutoconsultaDelegate();
		ResultadosSimulacionTO results = null;
		Calendar fechaIngresoEmpresa = Calendar.getInstance();
		Calendar fechaNacimiento = Calendar.getInstance();
		String target = null;
		String textRut = null;
		String textRutEmpresa = null;
		String textRutAfiliado = null;
		String nombreConsulta = null;
		String rutConsulta = null;
		int rut = 0;
		
		if (!daf.get("rut").equals("")) {
			session.setAttribute("rutDelEmpleado", daf.get("rut"));
			session.setAttribute("rutDVDelEmpleado", daf.get("dv"));
		}
		// Obtiene datos del usuario
		UsuarioVO usuario = (UsuarioVO) session.getAttribute("datosUsuario");
		int tipoUsuario = 0;
		// Valido tipo de usuario, ya que, sólo está habilitada para 
		// afiliado activo y pensionado en los métodos provistos por la clase
		// desarrollada por azurian
		if (usuario.isEsAfiliadoActivo()) {
			tipoUsuario = Constantes.TIPO_USUARIO_AFILIADO;
			session.setAttribute("afiliado", "yes");
		} else if (usuario.isEsPensionado()) {
			tipoUsuario = Constantes.TIPO_USUARIO_PENSIONADO;
			session.removeAttribute("afiliado");
		} else if (usuario.isEsEmpresa() || usuario.isEsEmpresaPublica()) {
			logger.debug("El usuario es Empresa");
			// El usuario es empresa
			textRutEmpresa = String.valueOf(usuario.getRut());
			textRutAfiliado = (String) daf.get("rut");
			if (textRutAfiliado != null && textRutAfiliado.length() > 0) {
				// Esto ocurre cuando ya indico el rut del empleado
				// Ajusta Rut si es que viene desde el Módulo
				String subapp =
					(String) session.getAttribute("struts.subapplication");
				if (subapp != null
					&& subapp.equals("modulo")
					&& textRutAfiliado != null
					&& textRutAfiliado.length() > 3) {
					logger.debug(
						"Ajustando rut por canal 'modulo': " + textRutAfiliado);
					textRutAfiliado = textRutAfiliado.substring( 0,
							textRutAfiliado.length() - 1);
					logger.debug("Nuevo rut: " + textRutAfiliado);
				}
				AfiliadoVO afiliado = delegate.getDatosEmpleado(
							Long.parseLong(textRutAfiliado),
							Long.parseLong(textRutEmpresa));
				tipoUsuario = Constantes.TIPO_USUARIO_AFILIADO;
				session.setAttribute("afiliado", "yes");
				if (afiliado != null) {
					if ((usuario.isEsEmpresa() || usuario.isEsEmpresaPublica())
						&& !usuario.isEsEncargadoEmpresa()) {
						nombreConsulta = afiliado.getFullNombre();
						rutConsulta = afiliado.getFullRut();
					} else if (
						usuario.isEsEncargadoEmpresa()
							&& delegate.usuarioPuedeConsultarPorAfiliado(
								usuario.getRutusuarioAutenticado(),
								Long.parseLong(textRutEmpresa),
								afiliado)) {
						nombreConsulta = afiliado.getFullNombre();
						rutConsulta = afiliado.getFullRut();
					} else {
						// Si el rut del empleado pertenece a la empresa, pero el encargado no
						// puede consultar por el
						// envía mensaje de error y solicita nuevo rut
						target = GLOBAL_FORWARD_definirEmpleado;
						session.setAttribute(
							"validation.message",
							"errors.validation.noPertenceSucursalAutorizada");
						session.setAttribute(
							"volverA",
							"prepareSimulacionCredito");
						return mapping.findForward(target);
					}
				} else {
					// Si el rut del empleado no pertenece a la empresa
					// envía mensaje de error y solicita nuevo rut
					target = GLOBAL_FORWARD_definirEmpleado;
					session.setAttribute(
						"validation.message",
						"errors.validation.noPertenceEmpresa");
					session.setAttribute("volverA", "prepareSimulacionCredito");
					return mapping.findForward(target);
				}
			} else {
				logger.debug("A definir empleado");
				session.removeAttribute("erroresSimulacion");
				session.removeAttribute("validation.message");
				target = GLOBAL_FORWARD_definirEmpleado;
				session.setAttribute("volverA", "prepareSimulacionCredito");
				return mapping.findForward(target);
			}
		} else {
			String glosaTipoUsuario = null;
			if (usuario.isEsAfiliadoCesado())
				glosaTipoUsuario = "Afiliado Cesado";
			if (usuario.isEsAhorrante())
				glosaTipoUsuario = "Ahorrante";
			//			if(usuario.isEsEmpresa())
			//				glosaTipoUsuario="Empresa";
			if (usuario.isEsEncargadoEmpresa())
				glosaTipoUsuario = "Encargado Empresa";
			if (usuario.isEsPublico())
				glosaTipoUsuario = "Empleado público";
			throw new BusinessException(
				"CCAF_AUTO_SIMULACIONCREDITO",
				"Opción no disponible para el tipo de usuario seleccionado: "
					+ glosaTipoUsuario);
		}
		String dispositivo = request.getRemoteAddr();
		logger.debug("IP: " + dispositivo);
		logger.debug("Afiliado Activo: " + usuario.isEsAfiliadoActivo());
		logger.debug("Pensionado: " + usuario.isEsPensionado());
		if (usuario.isEsEmpresa() || usuario.isEsEmpresaPublica()) {
			textRut = textRutAfiliado;
			rut = Integer.parseInt(textRut);
		} else {
			textRut = String.valueOf(usuario.getRut());
			nombreConsulta = usuario.getNombre();
			rutConsulta = usuario.getFullRut();
			rut = Integer.parseInt(textRut);
		}
		//		textRut = String.valueOf(usuario.getRut());
		//		nombreConsulta = usuario.getNombre();
		//		rutConsulta = usuario.getFullRut();
		//		rut = Integer.parseInt(textRut);
		logger.debug("Rut: " + rut);
		// Limpio mensajes de validación
		session.removeAttribute("validation.message");
		session.setAttribute("nombre", usuario.getNombre());
		session.setAttribute("rut", usuario.getFullRut());
		// El dato "campoAnterior" lo utilizo para saber que dato perdio el foco
		// y por lo tanto que dato debo validar
		String campo = (String) daf.get("campo");
		String campoAnterior = (String) daf.get("campoAnterior");
		logger.debug("campo: " + campo);
		logger.debug("campoAnterior: " + campoAnterior);
		if (campo == null || campo.length() == 0) {
			// Esto ocurre cuando se llama a este action desde el menu
			campo = Constants.DATOS_SC_INGRESOS_LIQUIDOS;
			daf.set("ingresosLiquidos", null);
		} else if (
			campo.equals(Constants.DATOS_SC_INGRESOS_LIQUIDOS)
				|| campo.equals(Constants.DATOS_SC_MONTO_SOLICITADO)
				|| campo.equals(Constants.DATOS_SC_CANTIDAD_CUOTAS)
				|| campo.equals(Constants.DATOS_SC_FECHA_NACIMIENTO)
				|| campo.equals(Constants.DATOS_SC_FECHA_INGRESO_EMPRESA)
				|| campo.equals(Constants.DATOS_SC_SEGUROS)) {
			// Validando datos ingresados en cada oportunidad
			// Siempre valida el dato que pierde el foco, ya que ese es el recien ingresado
			logger.debug("Validar datos");
			if (campoAnterior.equals(Constants.DATOS_SC_INGRESOS_LIQUIDOS)
				|| campo.equals(Constants.DATOS_SC_SEGUROS)) {
				// Valido ingresos liquidos
				//TODO ver 
				Integer ingresos = (Integer) daf.get("ingresosLiquidos");
				logger.debug("ingresosLiquidos: " + ingresos.intValue());
				int ingresosLiquidos = 0;
				if (ingresos != null && ingresos.intValue() > 0) {
					ingresosLiquidos = ingresos.intValue();
					if (ingresosLiquidos < Constantes.MONTO_MIN_RENTA_LIQUIDA
						|| ingresosLiquidos
							> Constantes.MONTO_MAX_RENTA_LIQUIDA) {
						session.setAttribute(
							"validation.message",
							"errors.simulacion.ingresoLiquidoInvalido");
						campo = Constants.DATOS_SC_INGRESOS_LIQUIDOS;
						daf.set("ingresosLiquidos", null);
					}
				} else {
					//					session.setAttribute(
					//						"validation.message",
					//						"errors.simulacion.ingresoLiquidoInvalido");
					campo = Constants.DATOS_SC_INGRESOS_LIQUIDOS;
					daf.set("ingresosLiquidos", null);
				}
			}
			if (campoAnterior.equals(Constants.DATOS_SC_MONTO_SOLICITADO)
				|| campo.equals(Constants.DATOS_SC_SEGUROS)) {
				// Valido Monto Solicitado 
				//String monto = (String) daf.get("montoSolicitado");
				Integer monto = (Integer) daf.get("montoSolicitado");
				int montoSolicitado = 0;
				//if (monto != null && monto.length() > 0) {
				if (monto != null && monto.intValue() > 0) {
					montoSolicitado = monto.intValue();
					if (montoSolicitado < Constantes.MONTO_MIN_SOLICITUD
						|| montoSolicitado > Constantes.MONTO_MAX_SOLICITUD) {
						session.setAttribute(
							"validation.message",
							"errors.simulacion.montoSolicitadoInvalido");
						campo = Constants.DATOS_SC_MONTO_SOLICITADO;
					}
				}
				//TODO ver
				//				else {
				//					session.setAttribute(
				//						"validation.message",
				//						"errors.simulacion.montoSolicitadoInvalido");
				//					campo = Constants.DATOS_SC_MONTO_SOLICITADO;
				//				}
			}
			if (campoAnterior.equals(Constants.DATOS_SC_CANTIDAD_CUOTAS)
				|| campo.equals(Constants.DATOS_SC_SEGUROS)) {
				// Valido número de cuotas
				Integer cuotas = (Integer) daf.get("cantidadCuotas");
				int cantidadCuotas = 0;
				if (cuotas != null && cuotas.intValue() > 0) {
					cantidadCuotas = cuotas.intValue();
					if (cantidadCuotas < Constantes.CANT_MIN_CUOTAS_CREDITO
						|| cantidadCuotas > Constantes.CANT_MAX_CUOTAS_CREDITO) {
						session.setAttribute(
							"validation.message",
							"errors.simulacion.cantidadCuotasInvalida");
						campo = Constants.DATOS_SC_CANTIDAD_CUOTAS;
					}
				}
				//TODO ver
				//				else {
				//					session.setAttribute(
				//						"validation.message",
				//						"errors.simulacion.cantidadCuotasInvalida");
				//					campo = Constants.DATOS_SC_CANTIDAD_CUOTAS;
				//				}
			}
			//			fechaNacimiento=null;
			//			if (campoAnterior.equals(Constants.DATOS_SC_FECHA_NACIMIENTO)
			//				|| campo.equals(Constants.DATOS_SC_SEGUROS)) {
			//						 	
			//				// Valido fecha de nacimiento			
			//				fechaNacimiento = formarFecha((String) daf.get("fechaNacimiento"));
			//						
			//				if (fechaNacimiento == null) {
			//					session.setAttribute(
			//						"validation.message",
			//						"errors.simulacion.fechaNacimientoInvalida");
			//					campo = Constants.DATOS_SC_FECHA_NACIMIENTO;
			//				}
			//			}	
			//			
			//			fechaIngresoEmpresa=null;
			//			if (campoAnterior.equals(Constants.DATOS_SC_FECHA_INGRESO_EMPRESA)
			//				|| campo.equals(Constants.DATOS_SC_SEGUROS)) {
			//			
			//				// Valido fecha de ingreso empresa
			//				if (usuario.isEsAfiliadoActivo()){
			//											
			//					fechaIngresoEmpresa = formarFecha((String) daf.get("fechaIngresoEmpresa"));
			//							
			//					if (fechaIngresoEmpresa == null) {
			//							session.setAttribute(
			//								"validation.message",
			//								"errors.simulacion.fechaIngresoEmpresaInvalida");
			//						campo = Constants.DATOS_SC_FECHA_INGRESO_EMPRESA;	
			//					}
			//				}		
			//			}
			//			
			//			if(fechaNacimiento!=null && fechaIngresoEmpresa!=null){
			//				if (fechaNacimiento.after(fechaIngresoEmpresa)) {
			//					session.setAttribute(
			//						"validation.message",
			//						"errors.simulacion.trabajaAntesDeNacer");
			//					campo = Constants.DATOS_SC_FECHA_INGRESO_EMPRESA;
			//				}
			//			}			
		}
		daf.set("campoAnterior", campo);
		daf.set("campo", campo);
		// Seteo el campo que se debe activar para ingreso de datos
		session.setAttribute("columna", campo);
		// Por defecto a ingreso datos
		target = FORWARD_ingresoDatos;
		logger.debug("campo: " + campo);
		logger.debug("campoAnterior: " + campoAnterior);
		logger.debug(
			"ingresosLiquidos: " + (Integer) daf.get("ingresosLiquidos"));
		logger.debug(
			"montoSolicitado: " + (Integer) daf.get("montoSolicitado"));
		logger.debug("cantidadCuotas: " + (Integer) daf.get("cantidadCuotas"));
		logger.debug("fechaNacimiento: " + (String) daf.get("fechaNacimiento"));
		logger.debug(
			"fechaIngresoEmpresa: " + (String) daf.get("fechaIngresoEmpresa"));
		logger.debug(
			"seguroDesgravamen: " + (String) daf.get("seguroDesgravamen"));
		logger.debug("seguroVida: " + (String) daf.get("seguroVida"));
		logger.debug("seguroCesantia: " + (String) daf.get("seguroCesantia"));
		logger.debug("rut: " + (String) daf.get("rut"));
		if (daf.get("montoSolicitado") != null
			&& ((Integer) daf.get("montoSolicitado")).intValue() == 0)
			daf.set("montoSolicitado", null);
		if (daf.get("cantidadCuotas") != null
			&& ((Integer) daf.get("cantidadCuotas")).intValue() == 0)
			daf.set("cantidadCuotas", null);
		// A seleccionar seguros		
		if (campo.equals(Constants.DATOS_SC_SEGUROS)) {
			logger.debug("Seleccion de seguros");
			//TODO ver
			//Aquí validaciones!!!!
			boolean errores = false;
			fechaIngresoEmpresa = null;
			if (campoAnterior.equals(Constants.DATOS_SC_FECHA_INGRESO_EMPRESA)
				|| campo.equals(Constants.DATOS_SC_SEGUROS)) {
				// Valido fecha de ingreso empresa
				if (usuario.isEsAfiliadoActivo()) {
					fechaIngresoEmpresa =
						formarFecha((String) daf.get("fechaIngresoEmpresa"));
					if (fechaIngresoEmpresa == null) {
						session.setAttribute(
							"validation.message",
							"errors.simulacion.fechaIngresoEmpresaInvalida");
						campo = Constants.DATOS_SC_FECHA_INGRESO_EMPRESA;
						errores = true;
					}
				}
			}
			fechaNacimiento = null;
			if (campoAnterior.equals(Constants.DATOS_SC_FECHA_NACIMIENTO)
				|| campo.equals(Constants.DATOS_SC_SEGUROS)) {
				// Valido fecha de nacimiento			
				fechaNacimiento =
					formarFecha((String) daf.get("fechaNacimiento"));
				if (fechaNacimiento == null) {
					session.setAttribute(
						"validation.message",
						"errors.simulacion.fechaNacimientoInvalida");
					campo = Constants.DATOS_SC_FECHA_NACIMIENTO;
					errores = true;
				}
			}
			if (fechaNacimiento != null && fechaIngresoEmpresa != null) {
				if (fechaNacimiento.after(fechaIngresoEmpresa)) {
					session.setAttribute(
						"validation.message",
						"errors.simulacion.trabajaAntesDeNacer");
					campo = Constants.DATOS_SC_FECHA_INGRESO_EMPRESA;
					errores = true;
				}
			}
			Integer cuotas = (Integer) daf.get("cantidadCuotas");
			if (!(cuotas != null && cuotas.intValue() > 0)) {
				session.setAttribute(
					"validation.message",
					"errors.simulacion.cantidadCuotasInvalida");
				campo = Constants.DATOS_SC_CANTIDAD_CUOTAS;
				daf.set("cantidadCuotas", null);
				errores = true;
			}
			Integer monto = (Integer) daf.get("montoSolicitado");
			if (!(monto != null && monto.intValue() > 0)) {
				session.setAttribute(
					"validation.message",
					"errors.simulacion.montoSolicitadoInvalido");
				campo = Constants.DATOS_SC_MONTO_SOLICITADO;
				daf.set("montoSolicitado", null);
				errores = true;
			}
			Integer ingresos = (Integer) daf.get("ingresosLiquidos");
			if (!(ingresos != null && ingresos.intValue() > 0)) {
				session.setAttribute(
					"validation.message",
					"errors.simulacion.ingresoLiquidoInvalido");
				campo = Constants.DATOS_SC_INGRESOS_LIQUIDOS;
				daf.set("ingresosLiquidos", null);
				errores = true;
			}
			session.setAttribute("columna", campo);
			daf.set("campoAnterior", campo);
			daf.set("campo", campo);
			if (!errores) {
				// Inicializo selección de seguros
				daf.set("seguroDesgravamen", "1");
				session.removeAttribute("seguroDeCesantia");
				if ((usuario.isEsEmpresa() || usuario.isEsAfiliadoActivo())
					&& ((Integer) daf.get("cantidadCuotas")).intValue()
						> Constantes.CUOTAS_MINIMAS_SEGURO_CESANTIA) {
					session.setAttribute("seguroDeCesantia", "yes");
					daf.set("seguroCesantia", "1");
				} else if (usuario.isEsPensionado()) {
					// Los pensionados no tienen seguro de cesantia
					daf.set("seguroCesantia", "0");
				}
				// Depende de la edad, tiene una edad tope
				if (edad(fechaNacimiento)
					<= Constantes.EDAD_MAXIMA_SEGURO_VIDA) {
					daf.set("seguroVida", "1");
					session.setAttribute("seguroDeVida", "yes");
				} else {
					daf.set("seguroVida", "0");
					session.removeAttribute("seguroDeVida");
				}
				target = FORWARD_seleccionSeguros;
			}
		}
		session.removeAttribute("results");
		session.removeAttribute("creditoRepactar");
		// Si tiene crédito(s) va a selección de créditos a descontar, sino va directo al resultado
		if (campo.equals(Constants.DATOS_SC_CREDITOS)) {
			logger.debug("Creditos");
			DatosSimulacionTO creditosVigentes;
			Collection datos = null;
			creditosVigentes = getCreditos(session, rut, tipoUsuario);
			datos = creditosVigentes.getCreditosVigentes();
			if (datos != null) {
				/*
				 * Modificación del día 29/09/2009 (NEORIS Argentina)
				 * 
				 * Se comprueba la deuda intercaja del Afiliado
				 * de contener deuda se informara.
				 */
				comprobarDeudaIntercaja(session, delegate, rut);
				/*
				 * Fin Modificacion
				 */
				 
				// Tiene créditos, entonces se despliegan para saber si quiere descontarlos
				logger.debug("Con créditos");
				session.setAttribute("creditoRepactar", datos);
				target = FORWARD_creditos;
			} else {
				// No tiene créditos, entonces, directo al resultado
				logger.debug("Sin créditos");
				fechaNacimiento =
					formarFecha((String) daf.get("fechaNacimiento"));
				fechaIngresoEmpresa =
					formarFecha((String) daf.get("fechaIngresoEmpresa"));
				results =
					simular(
						request,
						daf,
						fechaIngresoEmpresa,
						fechaNacimiento,
						rut);
				/*
					26/05/2006 Asepulveda
					Luego de tener el resultado de la simulacion
					se realizaran validaciones que tiene la solicitud de credito al momento de "solicitar"
					Si todo OK, entonces desplegamos el resultado, en caso de problemas
					desplegamos los problemas
				*/
				ResultadoValidacionSolicitudVO resultadoValidacionSolicitudVO =
					validarSolicitud(
						request,
						daf,
						fechaIngresoEmpresa,
						fechaNacimiento,
						rut,
						results);
				if (resultadoValidacionSolicitudVO.isOk()) {
					/*
					 *Modificación del día 29/09/2009 (NEORIS Argentina)
					 * 
					 * Se comprueba la deuda intercaja del Afiliado
					 * de contener deuda se informara.
					 */
					comprobarDeudaIntercaja(session, delegate, rut);
					/*
					 * Fin Modificacion
					 */
					//Despacha a la pagina de resultado de la simulacion
					session.setAttribute("results", results);
					session.setAttribute("fechaHoy", new java.util.Date());
					target = FORWARD_resultado;
					/**
					 * INICIO NUEVO
					 */
					long rutEmpresa = 0;
					String nombreEmpresa = "";
					String email = "";
					long telefono = 0;
					if (usuario.isEsAfiliadoActivo()) {
						Collection emp =
							delegate.datosEmpresaAfiliadoSimulacion(
								usuario.getRut());
						if (emp != null && emp.size() > 0) {
							Iterator iter = emp.iterator();
							while (iter.hasNext()) {
								EmpresaAfiliadoVO empresa =
									(EmpresaAfiliadoVO) iter.next();
								rutEmpresa = empresa.getRut();
								nombreEmpresa = empresa.getNombre();
							}
						}
					}
					Collection compl =
						delegate.datosComplClienteSimulacion(usuario.getRut());
					if (compl != null && compl.size() > 0) {
						Iterator iter = compl.iterator();
						while (iter.hasNext()) {
							DatosComplementariosVO datosComp =
								(DatosComplementariosVO) iter.next();
							telefono = datosComp.getTelefono();
							email = datosComp.getEmail();
						}
					}
					//se setea en el metodo Simular
					long montoTotalRepactado =
						(session.getAttribute("montoTotalRepactado") != null)
							? ((Long) session
								.getAttribute("montoTotalRepactado"))
								.longValue()
							: 0;
					session.removeAttribute("montoTotalRepactado");
					fechaIngresoEmpresa =
						formarFecha((String) daf.get("fechaIngresoEmpresa"));
					delegate
						.setRegistroSimulacionCredito(
							usuario.getRut(),
							usuario.getDv(),
							results.getMontoRenta(),
							results.getMontoSolicitado(),
							results.getNumeroCuotas(),
							results.getFechaNacimiento(),
							fechaIngresoEmpresa != null
								? fechaIngresoEmpresa.getTime()
								: null,					//results.getFechaIngresoEmpresa(),
					results.getSeguroDesgravamen() == 0 ? false : true,
						results.getSeguroVida() == 0 ? false : true,
						results.getSeguroCesantia() == 0 ? false : true,
						montoTotalRepactado,
						usuario.getNombre(),					//nombre usuario (COMPLETO)
					telefono, email, rutEmpresa, nombreEmpresa);
					usuario.setRutAfiliado(usuario.getRut());
					usuario.setIpConexion(request.getRemoteAddr());
					delegate.setSimulacionEstadistica(usuario);

						
					//5 = simulacion
					/**
					 * FIN NUEVO
					 */
				} else {
					//No paso algunas validaciones, va a pagina de errores
					session.setAttribute(
						"erroresSimulacion",
						resultadoValidacionSolicitudVO.getMensajes());
					target = FORWARD_erroresSimulacion;
				}
			}
		}
		// Va por el resultado de la simulación
		if (campo.equals(Constants.DATOS_SC_RESULTADO)) {
			logger.debug("Resultado");
			//Sin problemas hasta aquí (simulacion), va por el resultado de la simulacion
			fechaNacimiento = formarFecha((String) daf.get("fechaNacimiento"));
			fechaIngresoEmpresa =
				formarFecha((String) daf.get("fechaIngresoEmpresa"));
			results =
				simular(
					request,
					daf,
					fechaIngresoEmpresa,
					fechaNacimiento,
					rut);
			/*
				26/05/2006 Asepulveda
				Luego de tener el resultado de la simulacion
				se realizaran validaciones que tiene la solicitud de credito al momento de "solicitar"
				Si todo OK, entonces desplegamos el resultado, en caso de problemas
				desplegamos los problemas
			*/
			ResultadoValidacionSolicitudVO resultadoValidacionSolicitudVO =
				validarSolicitud(
					request,
					daf,
					fechaIngresoEmpresa,
					fechaNacimiento,
					rut,
					results);
			if (resultadoValidacionSolicitudVO.isOk()) {
				/*
				 * Modificación del día 29/09/2009 (NEORIS Argentina)
				 * 
				 * Se comprueba la deuda intercaja del Afiliado
				 * de contener deuda se informara.
				 */
				comprobarDeudaIntercaja(session, delegate, rut);
				/*
				 * Fin Modificacion
				 */
				
				//Despacha a la pagina de resultado de la simulacion
				session.setAttribute("results", results);
				session.setAttribute("fechaHoy", new java.util.Date());
				target = FORWARD_resultado;
				/**
				 * INICIO NUEVO
				 */
				long rutEmpresa = 0;
				String nombreEmpresa = "";
				String email = "";
				long telefono = 0;
				if (usuario.isEsAfiliadoActivo()) {
					Collection emp =
						delegate.datosEmpresaAfiliadoSimulacion(
							usuario.getRut());
					if (emp != null && emp.size() > 0) {
						Iterator iter = emp.iterator();
						while (iter.hasNext()) {
							EmpresaAfiliadoVO empresa =
								(EmpresaAfiliadoVO) iter.next();
							rutEmpresa = empresa.getRut();
							nombreEmpresa = empresa.getNombre();
						}
					}
				}
				Collection compl =
					delegate.datosComplClienteSimulacion(usuario.getRut());
				if (compl != null && compl.size() > 0) {
					Iterator iter = compl.iterator();
					while (iter.hasNext()) {
						DatosComplementariosVO datosComp =
							(DatosComplementariosVO) iter.next();
						telefono = datosComp.getTelefono();
						email = datosComp.getEmail();
					}
				}
				//se setea en el metodo Simular
				long montoTotalRepactado =
					(session.getAttribute("montoTotalRepactado") != null)
						? ((Long) session.getAttribute("montoTotalRepactado"))
							.longValue()
						: 0;
				session.removeAttribute("montoTotalRepactado");
				fechaIngresoEmpresa =
					formarFecha((String) daf.get("fechaIngresoEmpresa"));
				delegate
					.setRegistroSimulacionCredito(
						usuario.getRut(),
						usuario.getDv(),
						results.getMontoRenta(),
						results.getMontoSolicitado(),
						results.getNumeroCuotas(),
						results.getFechaNacimiento(),
						fechaIngresoEmpresa != null
							? fechaIngresoEmpresa.getTime()
							: null,
						results.getSeguroDesgravamen() == 0 ? false : true,
						results.getSeguroVida() == 0 ? false : true,
						results.getSeguroCesantia() == 0 ? false : true,
						montoTotalRepactado,
						usuario.getNombre(),				//nombre usuario (COMPLETO)
				telefono, email, rutEmpresa, nombreEmpresa);
				usuario.setRutAfiliado(usuario.getRut());
				usuario.setIpConexion(request.getRemoteAddr());
				delegate.setSimulacionEstadistica(usuario);
				//5 = simulacion
				/**
				 * FIN NUEVO
				 */
			} else {
				//No paso algunas validaciones, va a pagina de errores
				session.setAttribute(
					"erroresSimulacion",
					resultadoValidacionSolicitudVO.getMensajes());
				target = FORWARD_erroresSimulacion;
			}
		}
		return mapping.findForward(target);
	}
	
	/**
	 * Este método se encarga de comprobar la deuda intercaja del afiliado,
	 * de contener deuda la setea en sesión para su posterior visualización
	 * 
	 * @param session HttpSession
	 * @param delegate ServicesAutoconsultaDelegate
	 * @param rut String
	 * @author sebastian.helguera
	 * @version 01/10/2009
	 */
	private void comprobarDeudaIntercaja(HttpSession session,
		ServicesAutoconsultaDelegate delegate, int rut){
		
		logger.debug("Comienza comprobación de DeudaIntercaja para el RUT "
			+ "de Afiliado:" + rut);
		// Creo el objeto a ser tratado y lo inicializo NULL
		DeudaIntercajaVO deuVO = null;
		try {
			// Consulto la DB, para comprobar si el afiliado que se 
			// encuentra simulando un crédito contiene deuda intercaja
			deuVO = delegate.getDeudaIntercaja(Long.parseLong("" + rut));
			// Compruebo que el objeto deuVO no se NULL.
			// Si el objeto es NULL NO se realizan operaciones y se continua
			if (null != deuVO) {
				logger.debug("DeudaIntercaja Obtenida para el afiliado");
				// Formateo el Monto obtenido en la consulta
				DecimalFormat df = new DecimalFormat("###,###.###");
				df.setMinimumFractionDigits(0);
				df.setMaximumFractionDigits(0);
				String monto = df.format(deuVO.getMonto());
				logger.debug("Monto Deuda: " + monto);
				// Seteo en session el valor del monto de la DeudaIntercaja
				session.setAttribute("montoDeudaIntercaja", monto);
				logger.debug("Caja Deuda: " + deuVO.getCaja());
				// Seteo en session el valor del nombre de la Caja
				session.setAttribute("cajaDeudaIntercaja", deuVO.getCaja());
			}
		} catch (NumberFormatException nfex) {
			logger.error(
				"[PrepareSimulacionCreditoAction.comprobarDeudaIntercaja]"
				+ "Error al formatear monto", nfex);
		} catch (BusinessException bex) {
			logger.error(
				"[PrepareSimulacionCreditoAction.comprobarDeudaIntercaja]"
				+ "Error al negociar consulta", bex);
		} catch (Exception ex) {
			logger.error(
				"[PrepareSimulacionCreditoAction.comprobarDeudaIntercaja]"
				+ "Error al consultar", ex);
		}
		logger.debug("Finaliza comprobación de DeudaIntercaja");
	}
	
	/**
	 * Calcula la edad, dada la fecha de nacimiento
	 * @param Calendar, fechaNacimiento
	 * @return int, edad (años)
	 */
	private int edad(Calendar fechaNacimiento) {
		Calendar calendar = Calendar.getInstance();
		int annoActual = calendar.get(Calendar.YEAR);
		int mesActual = calendar.get(Calendar.MONTH);
		int diaActual = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.setTime(fechaNacimiento.getTime());
		int annoNac = calendar.get(Calendar.YEAR);
		int mesNac = calendar.get(Calendar.MONTH);
		int diaNac = calendar.get(Calendar.DAY_OF_MONTH);
		int edad = annoActual - annoNac;
		if (mesActual < mesNac)
			edad--;
		else if (mesActual == mesNac) {
			if (diaActual < diaNac)
				edad--;
		}
		return edad;
	}
	/**
	 * Verifica si los datos de la fecha ingresada son una fecha valida
	 * @param int, anio
	 * @param int, mes
	 * @param int, dia
	 * @return
	 * 			Calendar, si puede formar la fecha
	 * 			null si la fecha no es valida
	 */
	private Calendar formarFecha(String fecIngresoEmpresa) {
		Calendar fecha = Calendar.getInstance();
		boolean fechaOK = true;
		int intAnio = 0;
		int intMes = 0;
		int intDia = 0;
		try {
			if (fecIngresoEmpresa != null && fecIngresoEmpresa.length() == 8) {
				intDia = Integer.parseInt(fecIngresoEmpresa.substring(0, 2));
				logger.debug("intDia: " + intDia);
				intMes = Integer.parseInt(fecIngresoEmpresa.substring(2, 4));
				logger.debug("intMes: " + intMes);
				intAnio = Integer.parseInt(fecIngresoEmpresa.substring(4));
				logger.debug("intAnio: " + intAnio);
			} else
				return null;
			if (intAnio == 0 || intMes == 0 || intDia == 0)
				fechaOK = false;
			else if (intDia > 31 || intMes > 12)
				fechaOK = false;
			else if (
				intMes == 4 || intMes == 6 || intMes == 9 || intMes == 11) {
				if (intDia > 30)
					fechaOK = false;
			} else if (intMes == 2) {
				boolean anioBisiesto = (intAnio % 4 == 0 ? true : false);
				if (anioBisiesto) {
					if (intDia > 29)
						fechaOK = false;
				} else if (intDia > 28)
					fechaOK = false;
			}
			if (fechaOK)
				fecha.set(intAnio, intMes - 1, intDia);
			else
				fecha = null;
		} catch (Exception ex) {
			fecha = null;
		}
		logger.debug("fecha: " + fecha);
		return fecha;
	}
	/**
	 * Devuelve los créditos vigentes del rut consultado
	 * @param HttpSession, session
	 * @param int, rut
	 * @param int, tipoUsuario
	 * @return 
	 * 		DatosSimulacionTO, los créditos vigentes del rut consultado
	 * 		null, si no tiene créditos vigentes
	 * @throws MQException
	 */
	private DatosSimulacionTO getCreditos(
		HttpSession session,
		int rut,
		int tipoUsuario)
		throws MQException {
		SimulacionManager simulacionManager;
		simulacionManager = FactoryManager.getInstance().getSimulacionManager();
		DatosSimulacionTO creditosVigentes = null;
		MQDao mq;
		mq = FactoryDao.getInstance().getMQDao();
		Crc440 srv = null;
		srv = (Crc440) session.getAttribute("Crc440");
		if (srv == null) {
			srv = (Crc440) mq.getFechaColocacionObj();
			session.setAttribute("Crc440", srv);
		}
		session.removeAttribute("creditoRepactar");
		creditosVigentes =
			simulacionManager.getDatosSimulacion(
				rut,
				tipoUsuario,
				srv.getFechaHabilSiguiente());
		return creditosVigentes;
	}
	/**
	 * Devuelve el resultado de la simulación
	 * @param HttpServletRequest request
	 * @param DynaValidatorActionForm daf
	 * @return ResultadosSimulacionTO, el resultado de la simulación
	 * @throws NumberFormatException
	 * @throws MQException
	 */
	private ResultadosSimulacionTO simular(
		HttpServletRequest request,
		DynaValidatorActionForm daf,
		Calendar fechaIngresoEmpresa,
		Calendar fechaNacimiento,
		int rutSimulacion)
		throws NumberFormatException, MQException {
		HttpSession session = request.getSession(true);
		UsuarioVO usuario = (UsuarioVO) session.getAttribute("datosUsuario");
		//int rut = Integer.parseInt(String.valueOf(usuario.getRut()));
		SimulacionManager simulacionManager;
		simulacionManager = FactoryManager.getInstance().getSimulacionManager();
		MQDao mq;
		mq = FactoryDao.getInstance().getMQDao();
		Crc440 srv = null;
		srv = (Crc440) session.getAttribute("Crc440");
		if (srv == null) {
			try {
				srv = (Crc440) mq.getFechaColocacionObj();
				session.setAttribute("Crc440", srv);
			} catch (MQException e1) {
				e1.printStackTrace();
			}
		}
		logger.debug("rutSimulacion: " + rutSimulacion);
		SubCrc414 perfil = null;
		Crc414 svc = null;
		svc = (Crc414) mq.getDatosAfiliado(rutSimulacion);
		Iterator iterMQ = svc.getDatosAfiliado().iterator();
		iterMQ.hasNext();
		perfil = (SubCrc414) iterMQ.next();
		long montoDescontar = 0;
		//		long sumaDescuentoCreditosAnteriores = 0;
		long sumaCuotaDescuentoCreditosAnterioresNoRepactados = 0;
		int nCreditosSeleccionados = 0;
		ResultadosSimulacionTO results;
		UsuarioTO usuarioTO;
		usuarioTO = new UsuarioTO();
		usuarioTO.setRut(rutSimulacion);
		// Se suman los descuentos anteriores
		String[] creditosMarcados = (String[]) daf.get("marcado");
		// Si hay creditos marcados, entonces los suma
		if (creditosMarcados != null && creditosMarcados.length > 0) {
			montoDescontar =
				simulacionManager.calculaMontoDescontar(
					creditosMarcados,
					usuarioTO,
					perfil.getTipoAfiliado(),
					srv.getFechaHabilSiguiente());
			/**
			 * INICIO NUEVO
			 */
			session.setAttribute(
				"montoTotalRepactado",
				new Long(montoDescontar));
			/**
			 * FIN NUEVO
			 */
			//			sumaDescuentoCreditosAnteriores =
			//				simulacionManager.calculaMontoRepactar(
			//					creditosMarcados,
			//					usuarioTO,
			//					perfil.getTipoAfiliado(),
			//					srv.getFechaHabilSiguiente());
		}
		//		logger.debug("sumaDescuentoCreditosAnteriores: " + sumaDescuentoCreditosAnteriores);
		logger.debug("montoDescontar: " + montoDescontar);
		sumaCuotaDescuentoCreditosAnterioresNoRepactados =
			simulacionManager.calculaSumaCuotaNoRepactar(
				creditosMarcados,
				usuarioTO,
				perfil.getTipoAfiliado(),
				srv.getFechaHabilSiguiente());
		logger.debug(
			"sumaCuotaDescuentoCreditosAnterioresNoRepactados: "
				+ sumaCuotaDescuentoCreditosAnterioresNoRepactados);
		nCreditosSeleccionados =
			simulacionManager.getCantidadCreditosSeleccionadosParaRepactar(
				creditosMarcados,
				usuarioTO,
				perfil.getTipoAfiliado(),
				srv.getFechaHabilSiguiente());
		logger.debug("nCreditosSeleccionados: " + nCreditosSeleccionados);
		int accion = 0;
		if (usuario.isEsEmpresa() || usuario.isEsAfiliadoActivo())
			accion = Constantes.ACCION_SIMULA_AFILIADO;
		else if (usuario.isEsPensionado())
			accion = Constantes.ACCION_SIMULA_PENSIONADO;
		logger.debug("accion: " + accion);
		logger.debug("FechaNacimiento: " + fechaNacimiento);
		
		//TODO asepulveda 03-06-2010
		Crc468 srv468 = null;
		SubCrc468 oficina = null;
		try {
			mq = FactoryDao.getInstance().getMQDao();					
			srv468 = (Crc468) mq.oficinasCaja();

			Iterator it = srv468.getOficinas().iterator();
			if ( it.hasNext() )
				oficina = (SubCrc468)it.next();
			
		} catch (MQException e1) {
			e1.printStackTrace();
		}		
		
		results =
			simulacionManager
				.getResultadosSimulacion(
					rutSimulacion,
					perfil.getRutEmpresa(),
					accion,
					((Integer) daf.get("montoSolicitado")).intValue(),
					((Integer) daf.get("cantidadCuotas")).intValue(),
					fechaNacimiento,
					fechaIngresoEmpresa,
					((String) daf.get("seguroCesantia")).equals("1")
						? true
						: false,
					((String) daf.get("seguroDesgravamen")).equals("1")
						? true
						: false,
					((String) daf.get("seguroVida")).equals("1") ? true : false,
					montoDescontar,			//sumaDescuentoCreditosAnteriores,
	((Integer) daf.get("ingresosLiquidos")).intValue(),
		perfil,
		srv.getFechaHabilSiguiente(),
		sumaCuotaDescuentoCreditosAnterioresNoRepactados,
		nCreditosSeleccionados,
		creditosMarcados
		//TODO asepulveda 03-06-2010
		,"F",
		true,
		oficina,
		0,
		0		
		);
		return results;
	}
	/**
	 * Se encarga de llamar al metodo que realiza las validaciones deseadas "getControlSimulacion()"
	 * de la clase 'cl.azurian.sce.manager.SimulacionManager'
	 * @param HttpServletRequest request
	 * @param DynaValidatorActionForm daf
	 * @return ResultadosSimulacionTO, el resultado de la simulación
	 * @throws NumberFormatException
	 * @throws MQException
	 */
	private ResultadoValidacionSolicitudVO validarSolicitud(
		HttpServletRequest request,
		DynaValidatorActionForm daf,
		Calendar fechaIngresoEmpresa,
		Calendar fechaNacimiento,
		int rutSimulacion,
		ResultadosSimulacionTO results)
		throws NumberFormatException, MQException, Exception {
		HttpSession session = request.getSession(true);
		ServicesAutoconsultaDelegate delegate =
			new ServicesAutoconsultaDelegate();
		UsuarioVO usuario = (UsuarioVO) session.getAttribute("datosUsuario");
		//int rut = Integer.parseInt(String.valueOf(usuario.getRut()));
		SimulacionManager simulacionManager;
		simulacionManager = FactoryManager.getInstance().getSimulacionManager();
		MQDao mq;
		mq = FactoryDao.getInstance().getMQDao();
		Crc440 srv = null;
		srv = (Crc440) session.getAttribute("Crc440");
		if (srv == null) {
			try {
				srv = (Crc440) mq.getFechaColocacionObj();
				session.setAttribute("Crc440", srv);
			} catch (MQException e1) {
				e1.printStackTrace();
			}
		}
		SubCrc414 perfil = null;
		Crc414 svc = null;
		svc = (Crc414) mq.getDatosAfiliado(rutSimulacion);
		Iterator iterMQ = svc.getDatosAfiliado().iterator();
		iterMQ.hasNext();
		perfil = (SubCrc414) iterMQ.next();
		Crc430 srvSituacionEmpresa = null;
		if (usuario.isEsEmpresa() || usuario.isEsAfiliadoActivo()) {
			SubCrc403 subSrv =
				mq.getNominaParaEmpresa(
					perfil.getOficina(),
					perfil.getRutEmpresa());
			srvSituacionEmpresa =
				(Crc430) mq.getSituacionEmpresa(
					perfil.getOficina(),
					perfil.getRutEmpresa(),
					subSrv.getCodigoNomina());
		} else if (usuario.isEsPensionado()) {
			fechaIngresoEmpresa = Calendar.getInstance();
		}
		long montoRepactar = 0;
		long montoTotalVigente = 0;
		UsuarioTO usuarioTO;
		usuarioTO = new UsuarioTO();
		usuarioTO.setRut(rutSimulacion);
		StringVO sexoAfiliadoStringVO = null;
		if ((((Collection) delegate.sexoAfiliadoBaseComun(rutSimulacion))
			.iterator())
			.hasNext())
			sexoAfiliadoStringVO =
				(StringVO) (((Collection) delegate
					.sexoAfiliadoBaseComun(rutSimulacion))
					.iterator())
					.next();
		//Se cambia la letra del sexo del afiliado ya que así es como funciona
		//el simulador
		if (sexoAfiliadoStringVO != null)
			usuarioTO.setSexo(sexoAfiliadoStringVO.getTexto());
		// Se suman los descuentos anteriores
		String[] creditosMarcados = (String[]) daf.get("marcado");
		// Si hay creditos marcados, entonces los suma
		if (creditosMarcados != null && creditosMarcados.length > 0) {
			montoRepactar =
				simulacionManager.calculaMontoRepactar(
					creditosMarcados,
					usuarioTO,
					perfil.getTipoAfiliado(),
					srv.getFechaHabilSiguiente());
		}
		montoTotalVigente =
			simulacionManager.montoTotalVigente(
				usuarioTO,
				perfil.getTipoAfiliado(),
				srv.getFechaHabilSiguiente());
		//		2006.08.23 Ya no existe este método, se reemplaza por:
		//		Xxxx y Yyyy		
		//		montoTotalVigente = simulacionManager.calculaMontoCreditosAnteriores(
		//												creditosMarcados,
		//												usuarioTO,
		//												perfil.getTipoAfiliado(),
		//												srv.getFechaHabilSiguiente());
		logger.debug("rutSimulacion: " + rutSimulacion);
		logger.debug("perfil: " + fechaNacimiento);
		logger.debug(
			"((Integer) daf.get montoSolicitado).intValue(): "
				+ ((Integer) daf.get("montoSolicitado")).intValue());
		logger.debug(
			"((Integer) daf.get(cantidadCuotas)).intValue(): "
				+ ((Integer) daf.get("cantidadCuotas")).intValue());
		logger.debug("FechaNacimiento: " + fechaNacimiento);
		logger.debug("fechaIngresoEmpresa: " + fechaIngresoEmpresa);
		logger.debug("montoRepactar: " + montoRepactar);
		logger.debug("montoTotalVigente: " + montoTotalVigente);
		logger.debug(
			"((Integer) daf.get(ingresosLiquidos)).intValue(): "
				+ ((Integer) daf.get("ingresosLiquidos")).intValue());
		logger.debug(
			"results.getFechaColocacion(): " + results.getFechaColocacion());
		logger.debug(
			"results.getSumaMontoCreditoVigente(): "
				+ results.getSumaMontoCreditoVigente());
		logger.debug(
			"results.getMontoMaxCuota(): " + results.getMontoMaxCuota());
		logger.debug("results.getValorCuota(): " + results.getValorCuota());
		logger.debug("results.getLiquidoPago(): " + results.getLiquidoPago());
		logger.debug("creditosMarcados: " + creditosMarcados);
		logger.debug(
			"results.getMontoMaxCuota(): " + results.getMontoMaxCuota());
		logger.debug("results.getValorCuota(): " + results.getValorCuota());
		ResultadoValidacionSolicitudVO resultado =
			new ResultadoValidacionSolicitudVO();
		//			
		Collection mensajes =
			simulacionManager.getControlSimulacion(
				rutSimulacion,
				perfil,
				((Integer) daf.get("montoSolicitado")).intValue(),
				((Integer) daf.get("cantidadCuotas")).intValue(),
				fechaNacimiento,
				fechaIngresoEmpresa,
				montoRepactar,
				montoTotalVigente,
				((Integer) daf.get("ingresosLiquidos")).intValue(),
				results.getFechaColocacion(),
				results.getSumaMontoCreditoVigente(),
				results.getMontoMaxCuota(),
				results.getValorCuota(),
				results.getLiquidoPago(),
				results.getTipoTrabajador(),
				creditosMarcados,
				((String) daf.get("seguroVida")).equals("1") ? true : false,
				results.getSumaCuotaCreditoVigente(),
				srvSituacionEmpresa,
				usuarioTO,
				results.getCreditosARepactar()
				//TODO asepulveda 03-06-2010
				,"F",
				1
				);
		if (mensajes.isEmpty())
			resultado.setOk(true);
		else
			resultado.setMensajes(mensajes);
		session.setAttribute("perfil", perfil);
		return resultado;
	}
}
