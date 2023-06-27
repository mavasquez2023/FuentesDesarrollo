package cl.araucana.autoconsulta.ui.processors;

import java.security.Principal;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.RequestProcessor;

import cl.araucana.autoconsulta.serv.ServicesAutoconsultaDelegate;
import cl.araucana.autoconsulta.vo.UsuarioVO;

public class FlexibleRequestProcessor extends RequestProcessor {

	private static Logger logger =
		Logger.getLogger(
			cl
				.araucana
				.autoconsulta
				.ui
				.processors
				.FlexibleRequestProcessor
				.class);

	public FlexibleRequestProcessor() {
	}

	public boolean processPreprocess(
		HttpServletRequest request,
		HttpServletResponse response) {
		String uri = request.getRequestURI();

		// System.out.println("FlexibleRequestProcessor: URI=" + uri);

		// Logout page is out of control.
		if (uri.endsWith("/logout.do")) {
			return true;
		}

		HttpSession session = request.getSession(true);
		String marker = (String) request.getAttribute("internal.anonPage");

		// Checks anonymous page.
		if (marker != null && marker.equals("yes")) {
			return true;
		}

		Principal principal = request.getUserPrincipal();
		String userName = null;

		Principal urPrincipal =
			(Principal) session.getAttribute("userPrincipal");
		String urUserName = null;

		/*
		 * Unauthenticated user request or sessions are rejected.
		 */
		/* ALFONSOLEIVA:20080512 Antes el if preguntaba si era nulo cualquiera de los valores
		 * Se modifica para que pueda considerar como autenticado al usuario estando al menos uno de
		 * los valores no nulos*/
		//		 if (principal == null || (userName = principal.getName()) == null || urPrincipal == null || (urUserName = urPrincipal.getName()) == null) {
		try {
			if (principal == null && urPrincipal == null){
				try {
					ActionMapping errorActionMapping =
						processMapping(request, response, "/SecurityPageError");
	
					doForward(errorActionMapping.getParameter(), request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
	
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/* ALFONSOLEIVA:20080512 Se agrega el código para que iguale los nombres de usuario*/
		if (urPrincipal != null){
			urUserName = urPrincipal.getName();
			userName= urUserName;
		}else{
			userName = principal.getName();
			urUserName= userName;
		}

		logger.debug("Username = " + userName);

		String appElement = request.getContextPath().substring(1);
		String url = request.getServletPath();
		String preActionElement = url.substring(0, url.indexOf(".do"));
		StringTokenizer st = new StringTokenizer(preActionElement, "/");
		String actionElement = preActionElement;
		String subApplication = null;

		if (st.countTokens() > 1) {
			subApplication = st.nextToken();
			actionElement = "/" + st.nextToken();
		}

		System.out.println("struts.application = " + appElement);
		System.out.println("struts.subapplication = " + subApplication);

		logger.debug("struts.application = " + appElement);
		logger.debug("struts.subapplication = " + subApplication);

		session.setAttribute("application.username", userName);
		session.setAttribute("struts.application", appElement);
		session.setAttribute("struts.subapplication", subApplication);

		UsuarioVO usuario = (UsuarioVO) session.getAttribute("datosUsuario");

		UsuarioVO usuarioEncargadoEmpresa;
		if (userName.equals(urUserName)
			&& url.endsWith("Welcome.do")
			&& usuario != null
			&& usuario.getRut()
				!= Integer.parseInt(
					userName.substring(0, userName.indexOf("-")))) {
			((UsuarioVO) session.getAttribute("datosUsuario")).setEsEmpresa(
				false);
			usuarioEncargadoEmpresa =
				(UsuarioVO) session.getAttribute("usuarioEncargadoEmpresa");
			session.setAttribute("datosUsuario", usuarioEncargadoEmpresa);
			session.removeAttribute("rutDelEmpleado");
			session.removeAttribute("rutDVDelEmpleado");
		}

		boolean firstTime = false;

		if (usuario == null) {
			try {
				ServicesAutoconsultaDelegate delegate =
					new ServicesAutoconsultaDelegate();
				long rut =
					Long.parseLong(
						userName.substring(0, userName.length() - 2));

				usuario = delegate.getAutenticacion(rut);
				firstTime = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		boolean authorized = usuario != null;

		logger.debug(
			"Username = "
				+ userName
				+ " authorized = "
				+ authorized
				+ " firstTime = "
				+ firstTime);

		System.out.println("FlexibleRequestProcessor: Username = " + userName + " authorized = " + authorized + " firstTime = " + firstTime);

		if (authorized) {
			if (firstTime) {
				session.setAttribute("datosUsuario", usuario);

				logger.debug("Usuario empresa?: " + usuario.isEsEmpresa());
				logger.debug(
					"Usuario AfilAct?: " + usuario.isEsAfiliadoActivo());
				logger.debug(
					"Usuario AfilPas?: " + usuario.isEsAfiliadoCesado());
				logger.debug(
					"Usuario Pensionado?: " + usuario.isEsPensionado());
				logger.debug("Usuario Ahorrante?: " + usuario.isEsAhorrante());
				logger.debug("Usuario EmpleadoPublico?: " + usuario.isEsPublico());
				logger.debug("Usuario EmpresaPublica?: " + usuario.isEsEmpresaPublica());

				// Checks an possible restart.
				if (!uri.endsWith("/Welcome.do")) {
					try {
						ActionMapping errorActionMapping =
							processMapping(
								request,
								response,
								"/SecurityRestart");

						doForward(
							errorActionMapping.getParameter(),
							request,
							response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			try {
				ActionMapping errorActionMapping =
					processMapping(request, response, "/SecurityPageError");

				doForward(errorActionMapping.getParameter(), request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return authorized;
	}
}
