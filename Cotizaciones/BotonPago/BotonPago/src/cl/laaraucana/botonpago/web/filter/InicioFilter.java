package cl.laaraucana.botonpago.web.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.laaraucana.botonpago.web.vo.CreditoVO;

/**
 * Servlet Filter implementation class InicioFilter
 */
public class InicioFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public InicioFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();

		@SuppressWarnings("unchecked")
		List<CreditoVO> listaCreditos = (List<CreditoVO>) session.getAttribute("listaCreditos");

		if (listaCreditos != null) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect("welcome.do");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
