package cl.laaraucana.simulacion.filters;

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

import cl.laaraucana.simulacion.VO.AfliadoVO;

/**
 * Servlet Filter implementation class CertificadosFilter
 */
public class SessionFilter implements Filter {
	/**
	 * Default constructor. 
	 */
	public SessionFilter() {
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
		try {
			HttpSession session = request.getSession();
			Principal principal = request.getUserPrincipal();
			if (principal != null && principal.getName() != null && !principal.equals("null")) {
				String username = principal.getName();
				session.setAttribute("user", username);
				chain.doFilter(request, response);
			} else {
				response.sendRedirect("/SimulacionCreditoReprogramacion/login.jsp");
			}
		} catch (Exception e) {
			response.sendRedirect("/SimulacionCreditoReprogramacion/login.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
