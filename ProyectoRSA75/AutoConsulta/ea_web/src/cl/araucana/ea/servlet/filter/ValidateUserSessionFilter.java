package cl.araucana.ea.servlet.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cl.araucana.common.LDAPUserRegistry;
import cl.araucana.common.UserProfile;

/**
 * @version 	1.0
 * @author
 */
public class ValidateUserSessionFilter implements Filter {
	/**
	* @see javax.servlet.Filter#void ()
	*/
	public void destroy() {

	}

	/**
	* @see javax.servlet.Filter#void (javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	*/
	public void doFilter(
		ServletRequest request,
		ServletResponse response,
		FilterChain chain)
		throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		// estado normal
		int status = 0;
		
		// usuario no autenticado
		Principal principal = req.getUserPrincipal();
		if(principal == null) {
			status = 1;		
		}

		// sesion de usuario no valido
		Object profile = session.getAttribute("ea_user_profile");
		if(profile == null) {
			status = 2;
		}
		
		request.setAttribute("status", new Integer(status));
		
		chain.doFilter(request, response);
	}

	/**
	* Method init.
	* @param config
	* @throws javax.servlet.ServletException
	*/
	public void init(FilterConfig config) throws ServletException {

	}

}
