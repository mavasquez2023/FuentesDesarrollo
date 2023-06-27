package cl.araucana.ea.servlet.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cl.araucana.common.LDAPUserRegistry;
import cl.araucana.common.Profile;
import cl.araucana.common.UserProfile;

/**
 * @version 	1.0
 * @author
 */
public class PutUserProfile implements Filter {
	/**
	* @see javax.servlet.Filter#void ()
	*/
	public void destroy() {

	}

	/**
	* @see javax.servlet.Filter#void (javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	*/
	public void doFilter(
		ServletRequest req,
		ServletResponse resp,
		FilterChain chain)
		throws ServletException, IOException {
		
		chain.doFilter(req, resp);

		try {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpSession session = request.getSession();			
			ServletContext ctx = session.getServletContext();
			
			String userRegistry = ctx.getInitParameter("USER_REGISTRY");

			//session.invalidate();???
			Principal principal = request.getUserPrincipal();
			if(principal != null) {
				// Si la autenticacion es exitosa.
				String principalName = principal.getName();
	
				// crear perfil de usuario llenado atributos correspondientes.
				Profile profile = new UserProfile();
				Collection empresas = null;
				
				if("LDAP".equalsIgnoreCase(userRegistry)) {
					// obtener lista de empresas desde el repositorio de registro de usuario
					LDAPUserRegistry ldap = new LDAPUserRegistry();
					empresas = ldap.getEmpresasDeUsuario(principalName);
				} else if("FILE".equalsIgnoreCase(userRegistry)) {
					empresas = new ArrayList();
					empresas.add(principalName);	 
				}
				
				if((empresas != null) && (empresas.size() != 0)) {
					profile.setAttribute("empresas", empresas);
				}
							
				// agregar el perfil de usuario a la session
				session.setAttribute("userProfile", profile);
				//session.setAttribute(Global);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	* Method init.
	* @param config
	* @throws javax.servlet.ServletException
	*/
	public void init(FilterConfig config) throws ServletException {

	}

}
