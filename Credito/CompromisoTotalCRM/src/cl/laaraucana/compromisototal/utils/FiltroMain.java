package cl.laaraucana.compromisototal.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltroMain
 */
public class FiltroMain implements Filter {

	/**
	 * Default constructor.
	 */
	public FiltroMain() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		String rutReq = (String) ((HttpServletRequest) request).getParameter("rut");
		String rut = (String) session.getAttribute("rut");
		if (rut != null || rutReq != null) {
			chain.doFilter(request, response);
		} else {
			String msgError = "Se ha terminado la sesión. Intente ingresar nuevamente";
			((HttpServletResponse) response).sendRedirect("../main/error.do?errorMsg=" + msgError);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
