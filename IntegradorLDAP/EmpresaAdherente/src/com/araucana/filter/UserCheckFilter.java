package com.araucana.filter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cl.araucana.core.registry.UserInfo;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;

/**
 * Valida Cambio de clave
 * 
 * @author LaAraucana
 *
 */


public class UserCheckFilter implements Filter {
	private static final Logger logger = Logger.getLogger(SessionFilter.class);

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {		
		logger.debug(">>>UserCheckFilter ejecutandose.");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Principal userPrincipal = httpRequest.getUserPrincipal();
		String userID = null;
		
		String urlRequerida = httpRequest.getRequestURI();
		logger.info(">>>URL: " + urlRequerida);		
		if (urlRequerida.equals("/EmpresaAdherente/UnificarNominas") || (urlRequerida.contains(".jpg")) || (urlRequerida.contains(".css"))) {
			logger.info(">>>Proceso Unificar Nóminas no es necesario autenticar.");
			chain.doFilter(request, response);
			
			return;
		} else {
			if (urlRequerida.equals("/EmpresaAdherente/")) {			
				chain.doFilter(request, response);
				return;
			}
		}		
		
		// Verifica si el usuario estï¿½ autorizado
		if (userPrincipal == null || (userID = userPrincipal.getName()) == null) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/loginError.jsp");
			request.setAttribute("mensaje", "El usuario no esta autenticado");
			requestDispatcher.forward(request, response);
			return;
		}
		HttpSession session = httpRequest.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		// Si usuario no esta autenticado
		if (userInfo == null) {
			// Autentica usuario en Ldap
			UserRegistryConnection urConnection = null;
			try {
				urConnection = new UserRegistryConnection();
				userInfo = urConnection.getUserInfo(userID);
				session.setAttribute("userInfo", userInfo);
			} catch (UserRegistryException e) {
				e.printStackTrace();
				logger.debug("Error al autenticar al usuario: " + userID);
				try {
					urConnection.close();
				} catch (Exception a) {
				}
				// Obtener mensaje desde archivo propertie
				request.setAttribute("mensaje", "Error al iniciar sesiï¿½n");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/loginError.jsp");
				requestDispatcher.forward(request, response);
				return;
			}
		}
		// Si usuario esta bloqueado
		if (userInfo.isBlocked()) {
			logger.debug("El '" + userID + "' esta bloqueado. ");
			// Redirecciona al sistema anterior
			request.setAttribute("mensaje", "El usuario se encuentra bloqueado");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/loginError.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
		logger.debug("User '" + userID + "' OK. ");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public UserCheckFilter() {
	}

	public void destroy() {
	}
}
