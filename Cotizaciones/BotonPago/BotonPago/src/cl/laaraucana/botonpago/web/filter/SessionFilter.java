package cl.laaraucana.botonpago.web.filter;

import java.io.FileReader;
import java.io.IOException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserInfo;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserNotFoundUserRegistryException;
import cl.araucana.core.registry.exception.UserRegistryException;
import cl.araucana.core.util.Rut;
import cl.laaraucana.botonpago.web.database.dao.DatosDeudorDAO;
import cl.laaraucana.botonpago.web.utils.Constantes;
import cl.laaraucana.botonpago.web.vo.RolXML;
import cl.laaraucana.botonpago.web.vo.RolesXML;

/**
 * Servlet Filter implementation class SessionFilter
 */
public class SessionFilter implements Filter {

	/**
	 * Obtiene el usuario y password, encripta las credenciales y las guarda en
	 * sesion.
	 * 
	 */

	private Logger logger = Logger.getLogger(this.getClass());
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Principal userPrincipal = httpRequest.getUserPrincipal();
		String userID = null;

		//Verifica si el usuario está autorizado
		if (userPrincipal == null || (userID = userPrincipal.getName()) == null) {
			logger.debug("Usuario no autenticado");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp?ms=errornoautenticado");
			requestDispatcher.forward(request, response);
			return;
		}

		HttpSession session = httpRequest.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		UserRegistryConnection urConnection = null;
		User user = null;
		//Si usuario no está autenticado
		if (userInfo == null) {
			//Autentica usuario en Ldap
			try {
				urConnection = new UserRegistryConnection();
				userInfo = urConnection.getUserInfo(userID);
				user = urConnection.getUser(userID.toUpperCase());

				/*if (userInfo.mustChangePassword()) {
					//Redirecciona a cambio de clave obligatorio
					logger.debug("Usuario '" + userID + "' debe cambiar password.");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/router.do?service=CC");
					requestDispatcher.forward(request, response);
					return;
				}*/

				try {
					@SuppressWarnings("unchecked")
					ArrayList<String> roles = new ArrayList<String>(urConnection.getUserRoles(user.getID(), Constantes.getInstancia().LDAP_APLICACION));
					/*boolean isDeudor= isDeudor(userID);
					if(isDeudor){
						roles.add("deudor");
					}*/
					if(roles.size()==0){
						roles.add("deudor");
					}
					String rol = null;
					RolesXML permisos = null;

					try {
						Unmarshaller um;
						JAXBContext jaxbContext;
						jaxbContext = JAXBContext.newInstance(RolesXML.class);
						um = jaxbContext.createUnmarshaller();
						String ruta = ResourceBundle.getBundle("resources.application").getString("ruta.xml.roles");
						permisos = (RolesXML) um.unmarshal(new FileReader(ruta));
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("Error al leer el archivo de configuracion");
						throw new Exception("Error al leer el archivo de configuracion");
					}

					if (roles.isEmpty()) {
						logger.debug("Usuario '" + userID + "' sin roles en BotonPago LDAP");
						session.invalidate();
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp?ms=errorrolesempty");
						requestDispatcher.forward(request, response);
						return;
					} else {
						if (roles.size() > 1) {
							logger.debug("Usuario '" + userID + "' con mas de un rol");
							session.setAttribute("rutDeudor", user.getID());
							session.setAttribute("nombreDeudor", user.getFullName(true));
							session.setAttribute("roles", roles);
							session.setAttribute("listaPermisos", permisos.getRol());
							/*RequestDispatcher requestDispatcher = request.getRequestDispatcher("/web/selectRol.do?parameter=muestra");
										requestDispatcher.forward(request, response);
										return;*/
						} else if (roles.size() == 1) {
							logger.debug("Usuario '" + userID + "' con rol unico");
							rol = (String) roles.get(0);
							session.setAttribute("rol", rol);
							session.setAttribute("rutDeudor", user.getID());
							session.setAttribute("nombreDeudor", user.getFullName(true));

							for (RolXML r : permisos.getRol()) {
								if (r.getNombre().equals(rol)) {
									session.setAttribute("permisos", r.getOpcion());
								}
							}
						}
						session.setAttribute("userInfo", userInfo);
						session.setAttribute("user", user);
						session.setAttribute("nombreUsuario", user.getFullName(true));
						logger.debug("Usuario autenticado!!!");
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Error al iniciar sesión: " + e);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp?ms=erroriniciosession");
					requestDispatcher.forward(request, response);
					return;
				}
				if (session.getAttribute("rol") == null || ((String) session.getAttribute("rol")).isEmpty()) {

					String selectRolURI = ((HttpServletRequest) request).getRequestURI();
					if (!selectRolURI.contains("selectRol.do")) {
						((HttpServletResponse) response).sendRedirect("/BotonPago/web/selectRol.do?parameter=muestra");
					}
					return;
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error al autenticar al usuario: " + userID);
				try {
					urConnection.close();
				} catch (Exception a) {
					e.printStackTrace();
				}
				//Obtener mensaje desde archivo propertie
				//				request.setAttribute("mensaje", "Error al iniciar sesión");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp?ms=errorautenticacion");
				requestDispatcher.forward(request, response);
				return;
			} finally {
				try {
					urConnection.close();
				} catch (UserRegistryException e) {
					e.printStackTrace();
				}
			}
		} else {
			//Si usuario ya esta autenticado
			/*if (userInfo.mustChangePassword()) {
				//Redirecciona a cambio de clave obligatorio
				logger.debug("Usuario '" + userID + "' debe cambiar password.");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/router.do?service=CC");
				requestDispatcher.forward(request, response);
				return;
			}*/

		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public SessionFilter() {
	}

	public void destroy() {
	}

}
