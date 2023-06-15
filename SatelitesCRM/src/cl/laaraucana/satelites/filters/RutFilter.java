package cl.laaraucana.satelites.filters;

import java.io.IOException;

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

import cl.araucana.autoconsulta.vo.UsuarioVO;
import cl.araucana.core.util.UserPrincipal;

/**
 * Servlet Filter implementation class CertificadosFilter
 */
public class RutFilter implements Filter {

    /**
     * Default constructor. 
     */
    public RutFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
//		 String url = ((HttpServletRequest) request).getRequestURI().toString();
//		if(url.contains("/main/")){
//			chain.doFilter(request, response);
//		}
		HttpSession sesion = request.getSession();
		sesion = request.getSession();
		
		UsuarioVO userCRM ;
		userCRM = (UsuarioVO)sesion.getAttribute("datosUsuario");
		String rutCRM = request.getParameter("rut");
		String uc = request.getParameter("uc");
		if(uc!=null){
			UserPrincipal newUser = UserPrincipal.decodeUserCredentials(uc);
			rutCRM= newUser.getName();
		}
		//Página de error
		if(userCRM == null && rutCRM == null){
			RequestDispatcher rq = request.getRequestDispatcher("/main/error.do?tipo=custom");
			request.setAttribute("title", "Ingrese un rut");
			request.setAttribute("message", "Debe ingresar un rut a consultar");
			rq.forward(request, response);
			return;
		}
		
		//Carga el nuevo rut
		if (rutCRM != null) {
			userCRM = new UsuarioVO();
				try {
					userCRM.setRut(Long.parseLong(rutCRM.substring(0, rutCRM.length() - 2)));
					userCRM.setDv(rutCRM.substring(rutCRM.length() - 1, rutCRM.length()));
				} catch (Exception e) {
					// Rut no valido
					RequestDispatcher rq = request.getRequestDispatcher("/main/error.do");
					request.setAttribute("title", "Usuario CRM");
					request.setAttribute("errorMsg", "Rut no válido");
					rq.forward(request, response);					
				}
				userCRM.setNombre("CRM");
				sesion.setAttribute("datosUsuario", userCRM);	
				request.setAttribute("rut", rutCRM);
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
