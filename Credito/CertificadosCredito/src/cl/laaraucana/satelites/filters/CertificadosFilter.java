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
import cl.laaraucana.satelites.webservices.model.UsuarioAfiliadoVO;
import cl.laaraucana.satelites.webservices.utils.UsuarioServiceUtil;
/**
 * Servlet Filter implementation class CertificadosFilter
 */
public class CertificadosFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		HttpSession sesion = request.getSession();
		UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("datosUsuario");
		String tipo= request.getParameter("tipo");
		String rutCRM = request.getParameter("rut");
		System.out.println("Rut parameter=" + rutCRM);
		
		String uc = request.getParameter("uc");
		if(tipo!=null &&tipo.equals("empresa")){
			chain.doFilter(request, response);
		}else{
			if(uc!=null && !uc.equals("")){
				UserPrincipal newUser = UserPrincipal.decodeUserCredentials(uc);
				rutCRM= newUser.getName();
				System.out.println("CertificadoFilter, Rut decode= " + rutCRM);
			}
			String perfilamiento= (String)sesion.getAttribute("perfilMenu");
			if(request.getQueryString()!= null && request.getQueryString().indexOf("perfilarMenu")>-1 || perfilamiento!=null && perfilamiento.equals("true")){
				sesion.setAttribute("perfilMenu", "true");
				chain.doFilter(request, response);

			}else{
				sesion.removeAttribute("perfilMenu");
				if(usuario == null && rutCRM == null){
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main/Welcome.do");
					requestDispatcher.forward(request, response);
					return;
				}else{
					//Carga el nuevo rut
					System.out.println("CertificadoFilter, Rut de request autoc= " + rutCRM);
					if (usuario== null && rutCRM != null || (usuario!= null && rutCRM != null && !(usuario.getRut()+ "-" + usuario.getDv()).equals(rutCRM ))) {
						UsuarioVO userCRM = new UsuarioVO();
						try {
							userCRM.setRut(Long.parseLong(rutCRM.substring(0, rutCRM.length() - 2)));
							userCRM.setDv(rutCRM.substring(rutCRM.length() - 1, rutCRM.length()));
							UsuarioAfiliadoVO user = UsuarioServiceUtil.obtenerAfiliado(rutCRM);
							userCRM.setNombre(user.getNombreAfiliado());
						} catch (Exception e) {
							// Rut no valido
							RequestDispatcher rq = request.getRequestDispatcher("/main/error.do");
							request.setAttribute("title", "Usuario CRM");
							request.setAttribute("errorMsg", "Rut no válido");
							rq.forward(request, response);					
						}
						
						sesion.setAttribute("datosUsuario", userCRM);	
						sesion.setAttribute("rut", rutCRM);
						
					}
					chain.doFilter(request, response);
				}
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	
    public CertificadosFilter() {
    }
    
    public void destroy() {
	}
}
