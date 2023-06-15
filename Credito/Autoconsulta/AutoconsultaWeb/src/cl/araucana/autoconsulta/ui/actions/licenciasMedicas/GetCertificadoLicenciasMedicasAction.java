package cl.araucana.autoconsulta.ui.actions.licenciasMedicas;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.AfiliadoVO;
import cl.araucana.autoconsulta.vo.CertificadoLicenciasMedicasVO;
import cl.araucana.autoconsulta.vo.CodigoDescripcionVO;
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import cl.araucana.autoconsulta.vo.LicenciaMedicaCertificadoVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;

/**
 * @version 	1.0
 * @author asepulveda
 */
public class GetCertificadoLicenciasMedicasAction extends Action {

	private static Logger logger =
		Logger.getLogger(GetCertificadoLicenciasMedicasAction.class);

	public static final String GLOBAL_FORWARD_certificadoLicencias =
		"certificadoLicencias";
	public static final String GLOBAL_FORWARD_definirEmpleado =
		"definirEmpleado";

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		HttpSession session = request.getSession(true);
		DynaValidatorActionForm daf = (DynaValidatorActionForm) form;
		ServicesAutoconsultaDelegate delegate =
			new ServicesAutoconsultaDelegate();
		String target = null;
		String textRutAfiliado = null;
		String textRutEmpresa = null;
		String nombreConsulta = null;
		String rutConsulta = null;
		boolean pedirRutAfiliado = false;
		long rutAfiliado = 0;
		boolean empleadoEncontrado = false;

		if (!daf.get("rut").equals("")) {
			session.setAttribute("rutDelEmpleado", daf.get("rut"));
			session.setAttribute("rutDVDelEmpleado", daf.get("dv"));
		}

		//String dispositivo = request.getRemoteAddr();
		//logger.debug("IP: " + dispositivo);

		session.removeAttribute("validation.message");

		UsuarioVO usuario = (UsuarioVO) session.getAttribute("datosUsuario");
		usuario.setIpConexion(request.getRemoteAddr());

		Collection listaDeEmpresas =
			(Collection) session.getAttribute("empresasACargo");

		Iterator empresaSeleccionada = null;

		//Solo se entra cuando es un encargado de empresa
		if (listaDeEmpresas != null)
			empresaSeleccionada = listaDeEmpresas.iterator();

		if (usuario.isEsEmpresa()) {

			//Se itera si es que fuese encargado de empresa
			//Se hace al menos una vez, que es el caso de las empresas
			do {
				logger.debug("El usuario es Empresa");

				// El usuario es empresa				
				//textRutEmpresa = String.valueOf(usuario.getRut());				
				if (empresaSeleccionada != null) {
					textRutEmpresa =
						""
							+ ((EmpresaACargoVO) empresaSeleccionada.next())
								.getRut();
					usuario.setRutEmpresa(Long.parseLong(textRutEmpresa));
				//sino, es una empresa
				} else {
					textRutEmpresa = "" + usuario.getRut();
				}

				textRutAfiliado = (String) daf.get("rut");
				if (textRutAfiliado != null && textRutAfiliado.length() > 0) {
					// Esto ocurre cuando ya indico el rut del empleado
					// para el cual está sacando el certificado

					// Ajusta Rut si es que viene desde el Módulo
					String subapp =
						(String) session.getAttribute("struts.subapplication");
					if (subapp != null
						&& subapp.equals("modulo")
						&& textRutAfiliado != null
						&& textRutAfiliado.length() > 3) {
						logger.debug(
							"Ajustando rut por canal 'modulo': "
								+ textRutAfiliado);
						textRutAfiliado =
							textRutAfiliado.substring(
								0,
								textRutAfiliado.length() - 1);
						logger.debug("Nuevo rut: " + textRutAfiliado);
					}

					AfiliadoVO afiliado =
						delegate.getDatosEmpleado(
							Long.parseLong(textRutAfiliado),
							Long.parseLong(textRutEmpresa));
					if (afiliado != null) {
						empleadoEncontrado = true;
						if (usuario.isEsEmpresa()
							&& !usuario.isEsEncargadoEmpresa()) {
							nombreConsulta = afiliado.getFullNombre();
							rutConsulta = afiliado.getFullRut();
							usuario.setRutAfiliado(afiliado.getRut());
							usuario.setRutEmpresa(usuario.getRut());
							usuario.setRutusuarioAutenticado(usuario.getRut());
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
								"getAsignacionFamiliar");
							return mapping.findForward(target);
						}
						break;
					} else {
						continue;
						// Si el rut del empleado no pertenece a la empresa
						// envía mensaje de error y solicita nuevo rut
						//					target = GLOBAL_FORWARD_definirEmpleado;
						//					session.setAttribute(
						//						"validation.message",
						//						"errors.validation.noPertenceEmpresa");
						//					session.setAttribute("volverA", "getAsignacionFamiliar");
						//					return mapping.findForward(target);
					}
				} else {
					logger.debug("----- A definir empleado");
					target = GLOBAL_FORWARD_definirEmpleado;
					session.setAttribute(
						"volverA",
						"getCertificadoLicenciasMedicas");
					return mapping.findForward(target);
				}
			} while (
				!empleadoEncontrado
					&& empresaSeleccionada != null
					&& empresaSeleccionada.hasNext());
			if (!empleadoEncontrado && textRutAfiliado.length() != 0) {
				//				Si el rut del empleado no pertenece a la empresa
				// envía mensaje de error y solicita nuevo rut
				target = GLOBAL_FORWARD_definirEmpleado;
				session.setAttribute(
					"validation.message",
					"errors.validation.noPertenceEmpresa");
				session.setAttribute("volverA", "getAsignacionFamiliar");
				return mapping.findForward(target);
			}
		} else {
			logger.debug("El usuario es Persona");
			// El usuario es persona
			textRutAfiliado = String.valueOf(usuario.getRut());
			//Los datos del usuario son los mismos que los datos del certificado
			nombreConsulta = usuario.getNombre();
			rutConsulta = usuario.getFullRut();
			usuario.setRutAfiliado(usuario.getRut());
			usuario.setRutusuarioAutenticado(usuario.getRut());
			Collection empleadores = delegate.getEmpleadoresByEmpleado(usuario);
			if (!empleadores.isEmpty()) {
					Iterator iempleadores = empleadores.iterator();
					EmpresaVO empleador = (EmpresaVO) iempleadores.next();
					usuario.setRutEmpresa(empleador.getRut());
			}


		}

		rutAfiliado = Long.parseLong(textRutAfiliado);
		logger.debug("Rut afiliado: " + rutAfiliado);

		usuario.setRutAfiliado(rutAfiliado);
		CertificadoLicenciasMedicasVO certificado =
			delegate.getCertificadoLicenciasMedicas(
				usuario,
				nombreConsulta,
				rutConsulta);
		if (certificado.isTieneLicencias()) {

			// Busca lista para traducir codigo AFP
			Collection listaAFP =
				(Collection) session.getAttribute(
					"lista.instituciones.previsionales");
			if (listaAFP == null || listaAFP.isEmpty()) {
				listaAFP = delegate.getListaInstitucionesPrevicionales();
				session.setAttribute(
					"lista.instituciones.previsionales",
					listaAFP);
			}

			certificado.setLicencias(
				traducirAFP(listaAFP, certificado.getLicencias()));
			session.setAttribute("tieneLicencias", "yes");
		} else
			session.removeAttribute("tieneLicencias");

		session.setAttribute("certificado", certificado);
		session.setAttribute("nombre", nombreConsulta);
		session.setAttribute("rut", rutConsulta);
		logger.debug("A desplegar certificado licencias");
		target = GLOBAL_FORWARD_certificadoLicencias;
		return mapping.findForward(target);
	}

	// Traduce los códigos de Instituciones Previcionales
	public Collection traducirAFP(Collection listaAFP, Collection licencias) {

		int codigoParaTraducir = 0;
		logger.debug("Son: " + listaAFP.size() + " AFP");
		logger.debug("Son: " + licencias.size() + " licencias");
		Iterator ilicencias = licencias.iterator();
		while (ilicencias.hasNext()) {
			logger.debug("Otra de la lista de licencias");
			LicenciaMedicaCertificadoVO licencia =
				(LicenciaMedicaCertificadoVO) ilicencias.next();
			logger.debug(
				"Código Licencia Original: "
					+ licencia.getCodInstitutoPrevisional());
			if (licencia.getCodInstitutoPrevisional() == 2000) {
				licencia.setNomInstitutoPrevisional("");
			} else {
				if (licencia.getCodInstitutoPrevisional() < 1000) {
					codigoParaTraducir = licencia.getCodInstitutoPrevisional();
				} else {
					codigoParaTraducir =
						licencia.getCodInstitutoPrevisional() - 900;
				}
				Iterator iAFP = listaAFP.iterator();
				while (iAFP.hasNext()) {
					CodigoDescripcionVO traductor =
						(CodigoDescripcionVO) iAFP.next();
					logger.debug("Licencia AFP Código: " + codigoParaTraducir);
					logger.debug(
						"Traductor AFP Código: " + traductor.getCodigo());
					if (codigoParaTraducir == traductor.getCodigo()) {
						licencia.setNomInstitutoPrevisional(
							traductor.getDescripcion());
						logger.debug("Encontro");
						break;
					}
				}
			}
		}
		return licencias;
	}
}
