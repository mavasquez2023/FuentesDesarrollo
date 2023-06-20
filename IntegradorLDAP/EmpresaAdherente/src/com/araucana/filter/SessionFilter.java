package com.araucana.filter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

import org.apache.log4j.Logger;

import com.araucana.controller.Rutas;
import com.araucana.legacy.EmpresaVO;
import com.araucana.legacy.EmpresasUtils;

import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserInfo;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;

/**
 * Servlet Filter implementation class SessionFilter
 */
public class SessionFilter implements Filter {
	private static final Logger logger = Logger.getLogger(SessionFilter.class);

	// ***@Value("${LDAP_APLICACION}")
	private String ldapApplication;

	// ***@Value("${ROLES}")
	private List<String> roles;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info(">>>SessionFilter ejecutandose.");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		Principal userPrincipal = httpRequest.getUserPrincipal();
		String userID = null;
		
		String urlRequerida = httpRequest.getRequestURI();
		logger.info("URL: " + urlRequerida);
		logger.info("userPrincipal: " + userPrincipal);
		
		if (urlRequerida.equals("/EmpresaAdherente/UnificarNominas") || (urlRequerida.contains(".jpg")) || (urlRequerida.contains(".css"))) {			
			chain.doFilter(request, response);
			
			return;
		} else {
			if (urlRequerida.equals("/EmpresaAdherente/")) {			
				chain.doFilter(request, response);
				return;
			}
		}
		
		
		/*
		else {
			if (!urlRequerida.equals("/EmpresaAdherente/ControladorServlet1")) {
				//Leer properties donde esta la URL a redirigir
				Properties properties = new Properties();
				
				try (InputStream input = Rutas.class.getClassLoader().getResourceAsStream("application.properties")) {
					if (input == null) {
						logger.info("Archivo 'application.properties' no encontrado");
					}
					properties.load(input);
					
			        String xURL = properties.getProperty("URL_redirigida");
			        logger.debug("Redirigida a '" + xURL + "'");
					
					try {
						httpResponse.sendRedirect(xURL);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				//Fin
				
				return;
			}
		}
		*/

		// Verifica si el usuario este autorizado
		if (userPrincipal == null || (userID = userPrincipal.getName()) == null) {
			logger.debug("Usuario no autenticado.");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp?ms=errornoautenticado");
			requestDispatcher.forward(request, response);
			return;
		}
	
		logger.debug("Continuamos proceso de autenticación del Usuario.");
		
		HttpSession session = httpRequest.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		UserRegistryConnection urConnection = null;
		User user = null;
		
		// Si usuario no esta autenticado
		if (userInfo == null) {
			// Autentica usuario en Ldap
			try {
				logger.debug("userID: " + userID);
	
				urConnection = new UserRegistryConnection();
				logger.debug("Paso por 'urConnection = new UserRegistryConnection();'");
				
				userInfo = urConnection.getUserInfo(userID);
				logger.debug("userInfo: " + userInfo.toString());
				
				user = urConnection.getUser(userID.toUpperCase());

				if (userInfo.mustChangePassword()) {
					// Redirecciona a cambio de clave obligatorio
					logger.debug("Usuario '" + userID + "' debe cambiar password obligatoriamente.");
					request.setAttribute("obligatorio", "true");
					// RequestDispatcher requestDispatcher =
					// request.getRequestDispatcher("/router.do?service=CC");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/cambiarClave.do");
					requestDispatcher.forward(request, response);
					return;
				}
				
				logger.debug("Usuario no debia cambiar Password.");

				try {
					@SuppressWarnings("unchecked")
					List<String> roles = new ArrayList<String>(
							urConnection.getUserRoles(user.getID(), ldapApplication));
					/*
					 * boolean isDeudor= isDeudor(userID); if(isDeudor){ roles.add("deudor"); }
					 */
					if (roles.size() == 0) {
						roles.add("deudor");
					}
					String rol = null;

					/**
					 * <code>					
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
					</code>
					 */
					
					if (roles.isEmpty()) {
						logger.debug("Usuario '" + userID + "' sin roles en BotonPago LDAP.");
						session.invalidate();
						RequestDispatcher requestDispatcher = request
								.getRequestDispatcher("/error.jsp?ms=errorrolesempty");
						requestDispatcher.forward(request, response);
						return;
					} else {
						if (roles.size() > 1) {
							logger.debug("Usuario '" + userID + "' con mas de un rol.");
							session.setAttribute("rutDeudor", user.getID());
							session.setAttribute("nombreDeudor", user.getFullName(true));
							session.setAttribute("roles", roles);
							session.setAttribute("listaPermisos", roles);
							/*
							 * RequestDispatcher requestDispatcher =
							 * request.getRequestDispatcher("/web/selectRol.do?parameter=muestra");
							 * requestDispatcher.forward(request, response); return;
							 */
						} else if (roles.size() == 1) {
							logger.debug("Usuario '" + userID + "' con rol unico.");
							rol = (String) roles.get(0);
							session.setAttribute("rol", rol);
							session.setAttribute("rutDeudor", user.getID());
							session.setAttribute("nombreDeudor", user.getFullName(true));

							for (String r : roles) {
								if (r.equals(rol)) {
									session.setAttribute("permisos", rol);
								}
							}
						}
						session.setAttribute("userInfo", userInfo);
						session.setAttribute("user", user);
						session.setAttribute("nombreUsuario", user.getFullName(true));
						/*** session.setAttribute("Empresas", getEmpresas(userID)); */
						logger.debug("Usuario autenticado!!!");
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Error al iniciar sesion: " + e);
					RequestDispatcher requestDispatcher = request
							.getRequestDispatcher("/error.jsp?ms=erroriniciosession");
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
				// Obtener mensaje desde archivo propertie
				// request.setAttribute("mensaje", "Error al iniciar sesiï¿½n");
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
			// Si usuario ya esta autenticado
			if (userInfo.mustChangePassword()) {
				// Redirecciona a cambio de clave obligatorio
				logger.debug("Usuario '" + userID + "' debe cambiar password.");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/router.do?service=CC");
				requestDispatcher.forward(request, response);
				return;
			}

		}

		chain.doFilter(request, response);
	}
	
	  private List<EmpresaVO> getEmpresas(String user) {
	  EmpresasUtils eu = new EmpresasUtils(); 
	  Map<String, String> empresas = EmpresasUtils.getEmpresasLDAP(user);
	  
	  List<EmpresaVO> out = new ArrayList<EmpresaVO>(); EmpresaVO emp; for (String
	  rutEmpresa : empresas.keySet()) { String nombreEmpresa =
	  empresas.get(rutEmpresa); emp = new EmpresaVO();
	  emp.setRutEmpresa(rutEmpresa); emp.setNombreEmpresa(nombreEmpresa);
	  
	  out.add(emp); }
	  
	  return out; }
	  
	  public void init(FilterConfig fConfig) throws ServletException { }
	  
	  public SessionFilter() { }
	  
	  public void destroy() { }
	
}
