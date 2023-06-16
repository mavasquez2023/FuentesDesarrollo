package cl.previpass.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cl.previpass.logic.UserBusiness;
import cl.previpass.logic.UserBusinessImplementation;
import cl.previpass.vo.PerfilAuthorization;

/**
 * 
 * En el caso todavía no exista un perfil para el usuario lo crea según sus permisos<p>
 *
 * Registro de Versiones:<ul>
 * <li>Dec 2, 2011 [ccappelletti - schema ltda.]: Versión Inicial</li>
 * </ul><p>
 * 
 * <b>Todos los derechos reservados - La Araucana</b><p>
 *
 */
public final class PerfilFilter implements Filter {

	private static Logger logger = Logger.getLogger(PerfilFilter.class);
	private UserBusiness business; 
	public void destroy() {
		logger.info("Destruyendo el filtro");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest r = (HttpServletRequest) request;
		HttpSession session = r.getSession();
		logger.debug("AuthorizationFilter.doFilter... ");
		System.out.println("PerfilFilter.doFilter... ");
		if (r!= null && session.getAttribute("perfil") == null && r.getUserPrincipal() != null) {
			logger.info("Obteniendo el perfil para el usuario autenticado >" + r.getUserPrincipal().toString() + "<");
			// SUPUESTO: username siempre string que rapresenta un rut (ej: 11111111-1)
			long rut = 0;			
			try {
				rut = Long.valueOf(r.getUserPrincipal().toString().split("-")[0]).longValue();
			} catch (Exception e) {
				logger.error("El username con el que está autenticado no es un long (supuesto): >" + r.getUserPrincipal().toString() + "<"); 
			}
			
			PerfilAuthorization perfilAuthorization = business.getPerfil(rut);
			session.setAttribute("perfil", perfilAuthorization);
		} else {
			logger.debug("El usuario con rut >" + r.getUserPrincipal() + "< ya tiene cargado el perfil de permisos");
		}

		// sigue con su trabajo ...
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		logger.info("Inicializando el filtro");
		business = new UserBusinessImplementation();
	}
	

}
