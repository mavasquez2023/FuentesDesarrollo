package cl.araucana.autoconsulta.ui.actions.afiliacion;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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
import org.apache.struts.util.MessageResources;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.autoconsulta.ws.CertificadoProxy;
import cl.araucana.autoconsulta.ws.to.ResultadoTO;
import cl.araucana.core.util.UserPrincipal;

/**
 * @version 1.0
 * @author advise
 */
public class GetCertificadoAfiliacionAction extends Action {

	private static Logger logger = Logger.getLogger(GetCertificadoAfiliacionAction.class);
	public static final String GLOBAL_FORWARD_certificadoAfiliacion = "certificadoAfiliacionSAP";
	public static final String GLOBAL_FORWARD_definirEmpleado = "definirEmpleado";
	public static final String MESSAGE_PAGE = "message";
	public static final String ERROR_MESSAGE_WS = "Error al obtener información del Certificadox";

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("Ingresa Action GetCertificadoAfiliacionAction");

		HttpSession session = request.getSession(true);
		// LPC 2011-03-17
		ServicesAutoconsultaDelegate delegate = new ServicesAutoconsultaDelegate();

		boolean Afiliado = false;
		boolean EmpleadoP = false;
		boolean Pensionado = false;
		String nombreCertificado = null;
		long rutCertificado = 0;
		String textRutCertificado = null;
		String dvCertificado = null;
		String target = null;
		DynaValidatorActionForm daf = (DynaValidatorActionForm) form;
		// LPC 2011-03-17 nueva variable
		boolean empleadoEncontrado = false;
		String rutAfiDV="";
		
		if (!daf.get("rut").equals("")) {
			session.setAttribute("rutDelEmpleado", daf.get("rut"));
			if (!daf.get("dv").equals(""))
				session.setAttribute("rutDVDelEmpleado", daf.get("dv"));
			rutAfiDV=daf.get("rut")+"-"+daf.get("dv");
		}

		// ado: cl.azurian.sce.manager.SimulacionManager sm;
		// String dispositivo = request.getRemoteAddr();
		// logger.debug("IP: " + dispositivo);

		session.removeAttribute("validation.message");

		// LPC 2011-03-17 nueva variable
		String textRutAfiliado = null;
		String textRutEmpresa = null;
		String subapp = (String) session.getAttribute("struts.subapplication");
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

			// LPC 2011-03-17 nuevo
			// Se itera si es que fuese encargado de empresa
			// Se hace al menos una vez, que es el caso de las empresas
			// System.out.println("bandera 1");
			do {
				// Si entra, es encargado empresa
				// System.out.println("bandera 2");
				if (empresaSeleccionada != null) {
					// System.out.println("bandera 3");
					textRutEmpresa = "" + ((EmpresaACargoVO) empresaSeleccionada.next()).getRut();
					// sino, es una empresa
				} else {
					// System.out.println("bandera 4");
					textRutEmpresa = "" + usuario.getRut();
				}
				textRutAfiliado = (String) daf.get("rut");

				logger.debug("textRutEmpresa: " + textRutEmpresa);
				logger.debug("textRutAfiliado: " + textRutAfiliado);
				// System.out.println("bandera 5");
				if (textRutAfiliado != null && textRutAfiliado.length() > 0) {
					// Esto ocurre cuando ya indico el rut del empleado
					// para el cual está sacando el certificado
					// System.out.println("bandera 6");
					logger.debug("textRutAfiliado: " + textRutAfiliado);
					// Ajusta Rut si es que viene desde el Módulo

					if (subapp != null && subapp.equals("modulo") && textRutAfiliado != null && textRutAfiliado.length() > 3) {
						// System.out.println("bandera 7");
						logger.debug("Ajustando rut por canal 'modulo': " + textRutAfiliado);
						specialDv = textRutAfiliado.substring(textRutAfiliado.length() - 1);
						textRutAfiliado = textRutAfiliado.substring(0, textRutAfiliado.length() - 1);
						logger.debug("Nuevo rut: " + textRutAfiliado);
					}
					// System.out.println("bandera 8");
					AfiliadoVO afiliado = delegate.getDatosEmpleado(Long.parseLong(textRutAfiliado), Long.parseLong(textRutEmpresa));

					if (afiliado != null) {
						// System.out.println("bandera 9");
						empleadoEncontrado = true;
						usuario.setRutEmpresa(Long.parseLong(textRutEmpresa));

						if (usuario.isEsEmpresa() && !usuario.isEsEncargadoEmpresa()) {
							nombreCertificado = afiliado.getFullNombre();
							textRutCertificado = afiliado.getFullRut();

							// LPC nuevo
							rutCertificado = afiliado.getRut();
							dvCertificado = afiliado.getDv();
							//usuario.setRutusuarioAutenticado(Long.parseLong(textRutEmpresa));

						} else if (usuario.isEsEncargadoEmpresa() && delegate.usuarioPuedeConsultarPorAfiliado(usuario.getRutusuarioAutenticado(), Long.parseLong(textRutEmpresa), afiliado)) {
							// System.out.println("bandera 10");
							nombreCertificado = afiliado.getFullNombre();
							textRutCertificado = afiliado.getFullRut();

							// LPC nuevo
							rutCertificado = afiliado.getRut();
							dvCertificado = afiliado.getDv();

						} else {
							// Si el rut del empleado pertenece a la empresa,
							// pero el encargado no
							// puede consultar por el
							// envía mensaje de error y solicita nuevo rut
							// System.out.println("bandera 11");
							target = GLOBAL_FORWARD_definirEmpleado;
							session.setAttribute("validation.message", "errors.validation.noPertenceSucursalAutorizada");
							session.setAttribute("volverA", "getCertificadoAfiliacion");
							return mapping.findForward(target);
						}
						// System.out.println("bandera 12");
						break;
					} else {
						// System.out.println("bandera 13");
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
			// System.out.println("bandera 14");
			if (!empleadoEncontrado && textRutAfiliado.length() != 0) {
				// Si el rut del empleado no pertenece a la empresa
				// envía mensaje de error y solicita nuevo rut
				// System.out.println("bandera 15");
				target = GLOBAL_FORWARD_definirEmpleado;
				session.setAttribute("validation.message", "errors.validation.noPertenceEmpresa");
				session.setAttribute("volverA", "getCertificadoAfiliacion");
				return mapping.findForward(target);
			}
			// System.out.println("bandera 16");

			// LPC 2011-03-17 el codigo original del if (usuario.isEsEmpresa())
			/*
			 * target = GLOBAL_FORWARD_definirEmpleado;
			 * session.setAttribute("validation.message"
			 * ,"errors.validation.noPertenceSucursalAutorizada");
			 * session.setAttribute("volverA", "getCertificadoAfiliacion");
			 * return mapping.findForward(target);
			 */

			// LPC 2011-03-17
			if (textRutAfiliado == null || textRutAfiliado.length() == 0) {
				// Esto ocurre si es empresa y no ha indicado para quien
				// es el el certificado
				logger.debug("Debe indicar rut del empleado");
				session.setAttribute("volverA", "getCertificadoAfiliacion");
				target = GLOBAL_FORWARD_definirEmpleado;
				return mapping.findForward(target);
			}

		} else {
			// System.out.println("bandera 17");
			// El usuario es persona
			// Los datos del usuario son los mismos que los datos del
			// certificado
			nombreCertificado = usuario.getNombre();
			rutCertificado = usuario.getRut();
			dvCertificado = usuario.getDv();
			textRutCertificado = usuario.getFullRut();
			System.out.println("4.-: " + rutCertificado);
			usuario.setRutAfiliado(usuario.getRut());
			usuario.setRutusuarioAutenticado(usuario.getRut());
			Collection empleadores = delegate.getEmpleadoresByEmpleado(usuario);
			if (!empleadores.isEmpty()) {
				Iterator iempleadores = empleadores.iterator();
				EmpresaVO empleador = (EmpresaVO) iempleadores.next();
				usuario.setRutEmpresa(empleador.getRut());
			}

		}

		/*System.out.println("6.-: " + rutCertificado);

		ResultadoTO afiliacion = null;
		System.out.println("7.-: ");
		CertificadoProxy proxy = new CertificadoProxy();

		System.out.println("Antes del WS RUT: " + rutCertificado);
		System.out.println("Antes del WS DV: " + dvCertificado);

		int wsErrorCode = 0;
		String wsErrorMsg = null;

		try {
			MessageResources messageResources = getResources(request);
			proxy.setEndpoint(messageResources.getMessage("ws.certificado.afiliacion"));
			// daf.get("rut").toString(), daf.get("dv").toString()
			usuario.setRutAfiliado(rutCertificado);
			delegate.insertarActividad(usuario, cl.araucana.autoconsulta.vo.ActividadVO.CERTIFICADO_AFILIACION);
			afiliacion = proxy.obtenerDataCertificado(String.valueOf(rutCertificado), dvCertificado);

			logger.debug("afiliacion=" + afiliacion);
			logger.debug("afiliacion.getCodigo()=" + afiliacion.getCodigo());
			logger.debug("afiliacion.getError()=" + afiliacion.getError());
			logger.debug("afiliacion.getCertificadoTO()=" + afiliacion.getCertificadoTO());

			wsErrorCode = afiliacion.getCodigo();
			wsErrorMsg = afiliacion.getError();

			if (afiliacion.getCertificadoTO().getTipo().equals("AF")) {
				Afiliado = true;
			} else if (afiliacion.getCertificadoTO().getTipo().toString().equals("EP")) {
				EmpleadoP = true;
			} else if (afiliacion.getCertificadoTO().getTipo().toString().equals("PE")) {
				Pensionado = true;
			}
			logger.debug("resultado :" + afiliacion.getCertificadoTO().getNombre());
			logger.debug("resultado :" + afiliacion.getCertificadoTO().getTipo());
			logger.debug("resultado :" + afiliacion.getCertificadoTO().getFechaAfiliacion());
			logger.debug("resultado :" + afiliacion.getCertificadoTO().getRut());
			logger.debug("resultado :" + afiliacion.getCertificadoTO().getDv());

		} catch (Exception be) {
			if (wsErrorCode != 0 && !wsErrorMsg.equals("")) {
				request.setAttribute("message", ERROR_MESSAGE_WS);
				request.setAttribute("info", wsErrorMsg);
			} else {
				request.setAttribute("message", ERROR_MESSAGE_WS);
				// request.setAttribute("info", be.toString() );
				request.setAttribute("info", "No se ha podido establecer la conexión, por favor intente nuevamente");
			}
			target = MESSAGE_PAGE;
			return mapping.findForward(target);
		}
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		SimpleDateFormat unformat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

		Date fechaDesdeDate = unformat.parse(afiliacion.getCertificadoTO().getFechaAfiliacion());
		String fechaDesde = formatoFecha.format(fechaDesdeDate);

		logger.debug("Fecha Desde: " + fechaDesde);
		session.setAttribute("fechaDesde", fechaDesde);
		logger.debug("*******************************************");
		System.out.println("Fecha Afiliacion: " + fechaDesde);

		// Decide a que pagina enviar
		if (Afiliado) {
			System.out.println("Es afiliado: ");
			logger.debug("Es Afiliado");
			session.setAttribute("afiliacion", afiliacion);
			session.setAttribute("tipo", "Afiliado");
		} else if (EmpleadoP) {
			logger.debug("Es Empleado Publico");
			session.setAttribute("afiliacion", afiliacion);
			session.setAttribute("tipo", "Afiliado");
		} else if (Pensionado) {
			logger.debug("Es Pensionado");
			session.setAttribute("pensionado", afiliacion);
			session.setAttribute("tipo", "Pensionado");
		}

		target = GLOBAL_FORWARD_certificadoAfiliacion;

		System.out.println("Es afiliado 7.-: ");
		session.setAttribute("nombre", nombreCertificado);
		session.setAttribute("rut", textRutCertificado);

		System.out.println("nombreCertificado: " + nombreCertificado);
		System.out.println("rutCertificado: " + textRutCertificado);
		session.setAttribute("fechaHoy", new Date());

		session.setAttribute("afiliacion", afiliacion);
		logger.debug("A desplegar Afiliacion");
*/
		UserPrincipal userPrincipal =new UserPrincipal(rutAfiDV, "1234");
		String uc=userPrincipal.encode();
		session.setAttribute("uc", uc);
		
		target = GLOBAL_FORWARD_certificadoAfiliacion;
		
		return mapping.findForward(target);
	}
}
