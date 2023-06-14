package cl.araucana.autoconsulta.ui.actions.consultaCreditos;

import java.util.ArrayList;
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
import cl.araucana.autoconsulta.vo.EmpresaACargoVO;
import cl.araucana.autoconsulta.vo.EmpresaVO;
import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.core.util.UserPrincipal;
import cl.araucana.core.util.UserPrincipal_Original;

/**
 * @version 	1.0
 * @author asepulveda
 */
public class GetCargaFinancieraAction extends Action {

	private static Logger logger =
		Logger.getLogger(GetCargaFinancieraAction.class);

	public static final String GLOBAL_FORWARD_consultaCargaFinacieraSAP =
		"consultaCargaFinancieraSAP";
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
		long rutAfiliado = 0;
		boolean empleadoEncontrado = false;
		String rutAfiDV="";

		if (!daf.get("rut").equals("")) {
			session.setAttribute("rutDelEmpleado", daf.get("rut"));
			session.setAttribute("rutDVDelEmpleado", daf.get("dv"));
			rutAfiDV=daf.get("rut")+"-"+daf.get("dv");
		}

		//String dispositivo = request.getRemoteAddr();
		//logger.debug("IP: " + dispositivo);

		session.removeAttribute("validation.message");

		UsuarioVO usuario = (UsuarioVO) session.getAttribute("datosUsuario");
		usuario.setIpConexion(request.getRemoteAddr());

		Collection listaDeEmpresas =
			(Collection) session.getAttribute("empresasACargo");
		Iterator empresaSeleccionada = null;
		if (listaDeEmpresas != null)
			empresaSeleccionada = listaDeEmpresas.iterator();
		if (usuario.isEsEmpresa() || usuario.isEsEmpresaPublica()) {
			usuario.setRutEmpresa(usuario.getRut());
			//usuario.setRutusuarioAutenticado(usuario.getRut());
			do {
				logger.debug("El usuario es Empresa");
				// El usuario es empresa				
				//textRutEmpresa = String.valueOf(usuario.getRut());
				if (empresaSeleccionada != null)
					textRutEmpresa =
						""
							+ ((EmpresaACargoVO) empresaSeleccionada.next())
								.getRut();
				else
					textRutEmpresa = "" + usuario.getRut();
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
								"getCargaFinanciera");
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
					logger.debug("A definir empleado");
					target = GLOBAL_FORWARD_definirEmpleado;
					session.setAttribute("volverA", "getCargaFinanciera");
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
				session.setAttribute("volverA", "getCargaFinanciera");
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
			
			Collection empleadores = delegate.getEmpleadoresByEmpleado(usuario);
			if (!empleadores.isEmpty()) {
					Iterator iempleadores = empleadores.iterator();
					EmpresaVO empleador = (EmpresaVO) iempleadores.next();
					usuario.setRutEmpresa(empleador.getRut());
			}

		}
		
		//usuario.setRutusuarioAutenticado(usuario.getRut());

		rutAfiliado = Long.parseLong(textRutAfiliado);
		logger.debug("Rut afiliado: " + rutAfiliado);

		session.setAttribute("nombre", nombreConsulta);
		session.setAttribute("rut", rutConsulta);

		System.out.println("rut aut: " + usuario.getRutusuarioAutenticado());
		usuario.setRutAfiliado(rutAfiliado);
		UserPrincipal userPrincipal =new UserPrincipal(rutAfiDV, "1234");
		String uc=userPrincipal.encode();
		session.setAttribute("uc", uc);
		
		/*session.setAttribute(
			"creditosVigentes",
			delegate.getCreditosByRut(usuario));
		session.setAttribute("fechaHoy", new java.util.Date());

		Collection empleadores = new ArrayList();
		Collection lista =
			delegate.getEmpleadoresByEmpleado(usuario);
		if (usuario.isEsEmpresa()) {
			if (!lista.isEmpty()) {
				Iterator i = lista.iterator();
				while (i.hasNext()) {
					EmpresaVO empresaVO = (EmpresaVO) i.next();
					if (empresaVO.getRut() == usuario.getRut()) {
						empleadores.add(empresaVO);
						break;
					}
				}
			}
		} else
			empleadores = lista;

		session.setAttribute("empleadores", empleadores);
*/
		logger.debug("A desplegar Consulta Carga Financiera");
		
		target = GLOBAL_FORWARD_consultaCargaFinacieraSAP;
		return mapping.findForward(target);
	}
}
