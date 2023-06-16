package cl.araucana.ctasfam.presentation.servlet.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cl.araucana.common.Profile;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;

public class EncargadoLoaderFilter implements Filter {
	
	private static final Log log = LogFactory.getLog(EncargadoLoaderFilter.class);
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		try {
			HttpServletRequest request = (HttpServletRequest) arg0;
			Profile profile = (Profile) request.getSession().getAttribute("ea_user_profile");
			String rut = (String) profile.getId();
			String fullName = (String) profile.getAttribute("nombreCompleto");
			List empresas = (List) profile.getAttribute("empresas");
			request.getSession().setAttribute("edocs_encargado", new Encargado(rut, fullName, empresas));
		} catch (Exception e) {
			log.error("Error: En filtrado de usuarios, " + e.getLocalizedMessage(), e);
		}
		arg2.doFilter(arg0, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
}