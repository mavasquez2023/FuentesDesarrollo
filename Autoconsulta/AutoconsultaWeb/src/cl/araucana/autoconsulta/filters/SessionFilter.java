package cl.araucana.autoconsulta.filters;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.araucana.core.util.UserPrincipal;

/**
 * Servlet Filter implementation class SessionFilter
 */
public class SessionFilter implements Filter {
	/**
	 * Obtiene el usuario y password, encripta las credenciales y las guarda en sesion.
	 *  
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		//Obtener la sesion
		HttpSession sesion = request.getSession(true);
		Principal principal= request.getUserPrincipal();

		sesion.removeAttribute("uc");
		String usuario= principal.getName();
		String password="1234";
		UserPrincipal userPrincipal = new UserPrincipal(usuario,password);
		String uc = userPrincipal.encode();
		
		sesion.setAttribute("uc", uc);
		/*
		if(request.getParameter("uc") != null){
			uc = request.getParameter("uc");
			sesion.setAttribute("uc", uc);
		}else{
			if(request.getParameter("j_username")!= null && request.getParameter("j_password") != null){
				usuario = request.getParameter("j_username");
				password = request.getParameter("j_password");
				
				//String uc = encode( usuario, password);//Mantener codificación antigua 
				//Nuevo método de encriptación
				userPrincipal = new UserPrincipal(usuario,password);
				uc = userPrincipal.encode();
				
				sesion.setAttribute("uc", uc);
			}
		}
		
		*/
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	public SessionFilter() {
	}

	public void destroy() {
	}

}
