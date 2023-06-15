package cl.araucana.autoconsulta.ui.actions.asignacionFamiliar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
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
import cl.araucana.autoconsulta.vo.CargaFamiliarVO;
import cl.araucana.autoconsulta.vo.CertificadoAsignacionFamiliarVO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.common.BusinessException;

/**
 * @version 1.0
 * @author asepulveda
 */
public class GetAsignacionFamiliarAction extends Action {

	private static Logger logger = Logger.getLogger(GetAsignacionFamiliarAction.class);

	public static final String GLOBAL_FORWARD_certificadoAsignacionFamiliar = "certificadoAsignacionFamiliar";
	public static final String GLOBAL_FORWARD_seleccionTipoConsulta = "seleccionTipoConsulta";
	public static final String GLOBAL_FORWARD_listaEmpleadores = "listaEmpleadores";
	public static final String GLOBAL_FORWARD_definirEmpleado = "definirEmpleado";

	public static final String MESSAGE_PAGE = "message";

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();
		boolean mostrarListaEmpleadores = false;
		boolean desplegarSeleccionTipoConsulta = false;
		boolean pedirRutAfiliado = false;
		long rutEmpresa = 0;
		long rutAfiliado = 0;
		Collection empleadores = null;
		int tipoConsulta = 0;
		String nombreCertificado = null;
		String rutCertificado = null;
		String target = null;
		DynaValidatorActionForm daf = (DynaValidatorActionForm) form;
		boolean empleadoEncontrado = false;

		String dvConsultado = "";

		if (!daf.get("rut").equals("")) {
			session.setAttribute("rutDelEmpleado", daf.get("rut"));
			if (!daf.get("dv").equals(""))
				session.setAttribute("rutDVDelEmpleado", daf.get("dv"));
		}

		// cl.azurian.sce.manager.SimulacionManager sm;
		// String dispositivo = request.getRemoteAddr();
		// logger.debug("IP: " + dispositivo);

		String textRutAfiliado = null;
		String textRutEmpresa = null;

		String subapp = (String) session.getAttribute("struts.subapplication");

		session.removeAttribute("validation.message");

		String specialDv = "?";

		UsuarioVO usuario = (UsuarioVO) session.getAttribute("datosUsuario");
		usuario.setIpConexion(request.getRemoteAddr());

		logger.debug("usuario.isEsEmpresa: " + usuario.isEsEmpresa());

		Collection listaDeEmpresas = (Collection) session.getAttribute("empresasACargo");

		Iterator empresaSeleccionada = null;

		// Solo se entra cuando es un encargado de empresa
		if (listaDeEmpresas != null)
			empresaSeleccionada = listaDeEmpresas.iterator();

		if (usuario.isEsEmpresa()) {
			// Se itera si es que fuese encargado de empresa
			// Se hace al menos una vez, que es el caso de las empresas
			do {
				// Si entra, es encargado empresa
				if (empresaSeleccionada != null)
					textRutEmpresa = "" + ((EmpresaACargoVO) empresaSeleccionada.next()).getRut();
				else
					textRutEmpresa = "" + usuario.getRut();

				usuario.setRutEmpresa(Long.valueOf(textRutEmpresa).longValue());

				textRutAfiliado = (String) daf.get("rut");

				logger.debug("textRutEmpresa: " + textRutEmpresa);
				logger.debug("textRutAfiliado: " + textRutAfiliado);

				if (textRutAfiliado != null && textRutAfiliado.length() > 0) {
					// Esto ocurre cuando ya indico el rut del empleado
					// para el cual está sacando el certificado

					logger.debug("textRutAfiliado: " + textRutAfiliado);
					// Ajusta Rut si es que viene desde el Módulo

					if (subapp != null && subapp.equals("modulo") && textRutAfiliado != null && textRutAfiliado.length() > 3) {
						logger.debug("Ajustando rut por canal 'modulo': " + textRutAfiliado);
						specialDv = textRutAfiliado.substring(textRutAfiliado.length() - 1);
						textRutAfiliado = textRutAfiliado.substring(0, textRutAfiliado.length() - 1);
						logger.debug("Nuevo rut: " + textRutAfiliado);
					}

					AfiliadoVO afiliado = delegate.getDatosEmpleado(Long.parseLong(textRutAfiliado), Long.parseLong(textRutEmpresa));
					if (afiliado != null) {
						empleadoEncontrado = true;
						if (usuario.isEsEmpresa() && !usuario.isEsEncargadoEmpresa()) {
							nombreCertificado = afiliado.getFullNombre();
							rutCertificado = afiliado.getFullRut();
						} else if (usuario.isEsEncargadoEmpresa() && delegate.usuarioPuedeConsultarPorAfiliado(usuario.getRutusuarioAutenticado(), Long.parseLong(textRutEmpresa), afiliado)) {
							nombreCertificado = afiliado.getFullNombre();
							rutCertificado = afiliado.getFullRut();
						} else {
							// Si el rut del empleado pertenece a la empresa,
							// pero el encargado no
							// puede consultar por el
							// envía mensaje de error y solicita nuevo rut
							target = GLOBAL_FORWARD_definirEmpleado;
							session.setAttribute("validation.message", "errors.validation.noPertenceSucursalAutorizada");
							session.setAttribute("volverA", "getAsignacionFamiliar");
							return mapping.findForward(target);
						}
						break;
					} else {
						continue;
						// Si el rut del empleado no pertenece a la empresa
						// envía mensaje de error y solicita nuevo rut
						// target = GLOBAL_FORWARD_definirEmpleado;
						// session.setAttribute(
						// "validation.message",
						// "errors.validation.noPertenceEmpresa");
						// session.setAttribute(
						// "volverA",
						// "getAsignacionFamiliar");
						// return mapping.findForward(target);
					}
				}
			} while (!empleadoEncontrado && empresaSeleccionada != null && empresaSeleccionada.hasNext());
			if (!empleadoEncontrado && textRutAfiliado.length() != 0) {
				// Si el rut del empleado no pertenece a la empresa
				// envía mensaje de error y solicita nuevo rut
				target = GLOBAL_FORWARD_definirEmpleado;
				session.setAttribute("validation.message", "errors.validation.noPertenceEmpresa");
				session.setAttribute("volverA", "getAsignacionFamiliar");
				return mapping.findForward(target);
			}
		} else {
			// El usuario es persona
			textRutAfiliado = String.valueOf(usuario.getRut());
			textRutEmpresa = (String) daf.get("rut");
			// Los datos del usuario son los mismos que los datos del
			// certificado
			nombreCertificado = usuario.getNombre();
			rutCertificado = usuario.getFullRut();

			usuario.setRutEmpresa(0);
			usuario.setRutusuarioAutenticado(usuario.getRut());
			usuario.setRutAfiliado(usuario.getRut());
			empleadores = delegate.getEmpleadoresByEmpleado(usuario);
			if (!empleadores.isEmpty()) {
				Iterator iempleadores = empleadores.iterator();
				EmpresaVO empleador = (EmpresaVO) iempleadores.next();
				usuario.setRutEmpresa(empleador.getRut());
			}

		}

		// En esta variable se indica que tipo de cargas necesita
		// incluir en el certificado: Cargas Activas, inactivas o todas
		String textTipoConsulta = (String) daf.get("tipoConsulta");

		if (textRutAfiliado == null || textRutAfiliado.length() == 0) {
			// Esto ocurre si es empresa y no ha indicado para quien
			// es el el certificado
			pedirRutAfiliado = true;
		} else {
			rutAfiliado = Long.parseLong(textRutAfiliado);
			logger.debug("Rut afiliado: " + rutAfiliado);

			// Rut Empresa
			if (textRutEmpresa == null || textRutEmpresa.length() == 0) {
				logger.debug("Busca Empleadores");
				usuario.setRutAfiliado(rutAfiliado);
				empleadores = delegate.getEmpleadoresByEmpleado(usuario);
				if (!empleadores.isEmpty()) {
					if (empleadores.size() > 1) {
						mostrarListaEmpleadores = true;
					} else {
						Iterator iempleadores = empleadores.iterator();
						EmpresaVO empleador = (EmpresaVO) iempleadores.next();
						rutEmpresa = empleador.getRut();
						usuario.setRutEmpresa(empleador.getRut());
					}
				}
			} else {
				rutEmpresa = Long.parseLong(textRutEmpresa);
			}

			// Tipo consulta: cargas activas, inactivas, ambas
			if (!mostrarListaEmpleadores) {
				logger.debug("Tipo consulta: " + textTipoConsulta);
				if (textTipoConsulta == null || textTipoConsulta.length() == 0) {
					int tipo = delegate.getTieneCargasByRut(rutAfiliado, rutEmpresa);
					logger.debug("Tipo: " + tipo);

					if (tipo == 0) // caso especial
						tipo = delegate.getTieneCargasByRut(rutAfiliado, 0);

					if (tipo == Constants.CARGAS_ACTIVAS_INACTIVAS) {
						desplegarSeleccionTipoConsulta = true;
					} else {
						tipoConsulta = tipo;
					}
					textTipoConsulta = null;
				} else {
					tipoConsulta = Integer.parseInt(textTipoConsulta);
				}
			}
		}

		// Decide a que pagina enviar
		if (pedirRutAfiliado) {
			logger.debug("Debe indicar rut del empleado");
			session.setAttribute("volverA", "getAsignacionFamiliar");
			target = GLOBAL_FORWARD_definirEmpleado;
		} else {
			if (mostrarListaEmpleadores) {
				logger.debug("Muestra Empleadores");
				session.setAttribute("lista.empleadores", empleadores);
				target = GLOBAL_FORWARD_listaEmpleadores;
			} else {
				if (desplegarSeleccionTipoConsulta) {
					logger.debug("Muestra Tipo Consulta");
					daf.set("tipoConsulta", String.valueOf(Constants.CARGAS_ACTIVAS_INACTIVAS));
					if (!usuario.isEsEmpresa()) {
						daf.set("rut", textRutEmpresa);
					} else if (subapp != null && subapp.equals("modulo")) {
						daf.set("rut", textRutAfiliado + specialDv);
					} else {
						daf.set("rut", textRutAfiliado);
						daf.set("dv", dvConsultado);
					}

					target = GLOBAL_FORWARD_seleccionTipoConsulta;
				} else {
					logger.debug("rutAfiliado: " + rutAfiliado);
					logger.debug("rutEmpresa: " + rutEmpresa);
					logger.debug("tipoConsulta: " + tipoConsulta);

					session.setAttribute("nombre", nombreCertificado);
					session.setAttribute("rut", rutCertificado);
					CertificadoAsignacionFamiliarVO asignacionFamiliar = null;
					try {
						usuario.setRutAfiliado(rutAfiliado);
						usuario.setRutEmpresa(rutEmpresa);
						if (usuario.getRutusuarioAutenticado() == 0) {
							usuario.setRutusuarioAutenticado(usuario.getRut());
						}

						delegate.insertarActividad(usuario, cl.araucana.autoconsulta.vo.ActividadVO.CERTIFICADO_ASIGNACION_FAMILIAR);
						asignacionFamiliar = delegate.getCertificadoAsignacionFamiliarByRut(rutAfiliado, rutEmpresa, tipoConsulta, nombreCertificado, rutCertificado);
					} catch (BusinessException be) {
						if (be.getCode().equalsIgnoreCase("CCAF_AUTO_ASFAMINCONSISTENTE")) {
							request.setAttribute("message", be.getMessage());
							request.setAttribute("info", be.getMsgAdic());
							target = MESSAGE_PAGE;
							return mapping.findForward(target);

						} else {
							throw be;
						}
					}

					// Tipo de carga a desplegar
					if (asignacionFamiliar.getTipoCargaConsultado().equals(CargaFamiliarVO.STD_ACTIVA))
						session.setAttribute("activa", "yes");
					else
						session.removeAttribute("activa");

					// Si el afiliado es cesado y el tipo de certificado
					// consultado
					// es de cargas activas, entonces enviar fecha
					// de afiliación menos un día
					if (!asignacionFamiliar.getCodigoEstadoEmpresa().equals(AfiliadoVO.STD_ACTIVO) && asignacionFamiliar.getTipoCargaConsultado().equals(CargaFamiliarVO.STD_ACTIVA)) {

						GregorianCalendar fechaAfiliacion = new GregorianCalendar();
						SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
						int dia = Integer.parseInt(asignacionFamiliar.getFechaAfiliacion().substring(8, 10));
						int mes = Integer.parseInt(asignacionFamiliar.getFechaAfiliacion().substring(5, 7));
						int anio = Integer.parseInt(asignacionFamiliar.getFechaAfiliacion().substring(0, 4));
						fechaAfiliacion.set(anio, mes - 1, dia);
						fechaAfiliacion.add(Calendar.DAY_OF_MONTH, -1);
						Date fechaDesde = formatoFecha.parse(fechaAfiliacion.get(Calendar.DAY_OF_MONTH) + "/" + (fechaAfiliacion.get(Calendar.MONTH) + 1) + "/" + fechaAfiliacion.get(Calendar.YEAR));
						logger.debug("Fecha Desde: " + fechaDesde);
						session.setAttribute("fechaDesde", fechaDesde);
					} else {
						session.removeAttribute("fechaDesde");
					}

					logger.debug("*******************************************");
					logger.debug("Check para nombre de empresa");
					if (asignacionFamiliar != null) {
						if (asignacionFamiliar.getCodigoEstadoEmpresa().equalsIgnoreCase("S")) {
							asignacionFamiliar.setNombreEmpresa("");
							logger.debug("Se ha limpiado nombre de empresa");
						} else if (asignacionFamiliar.getCodigoEstadoEmpresa().equalsIgnoreCase("C")) {
							// El certificado refiere a estado CESADO
							Collection historico = delegate.getUltimaEmpresaHistorica(rutAfiliado);
							logger.debug("Se encontro: " + (historico != null ? historico.size() : 0) + " registros");
							if (historico != null && historico.size() > 0) {
								EmpresaVO emp = (EmpresaVO) historico.iterator().next();
								logger.debug("***************** Empresa Encontrada!! => ");
								logger.debug(emp.getRut() + "/" + emp.getEstado() + "/" + emp.getNombre());
								if (emp.getEstado().equalsIgnoreCase("S"))
									asignacionFamiliar.setNombreEmpresa("");
								logger.debug("Se ha limpiado nombre de empresa");
							}
						}

					}

					session.setAttribute("asignacionFamiliar", asignacionFamiliar);
					logger.debug("A desplegar Asignación Familiar");
					target = GLOBAL_FORWARD_certificadoAsignacionFamiliar;
				}
			}
		}
		return mapping.findForward(target);
	}
}
