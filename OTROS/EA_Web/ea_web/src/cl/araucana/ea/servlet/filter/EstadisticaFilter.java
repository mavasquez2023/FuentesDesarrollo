package cl.araucana.ea.servlet.filter;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cl.araucana.common.Profile;
import cl.araucana.ea.ctacte.dto.EmpresaTO;
import cl.araucana.ea.ctacte.dto.RutTO;
import cl.araucana.ea.edocs.Empresa;
import cl.araucana.ea.edocs.Encargado;
import cl.araucana.ea.edocs.EstadisticaBean;
import cl.araucana.ea.edocs.dao.EstadisticaDAO;

/**
 * @version 	1.0
 * @author
 */
public class EstadisticaFilter implements Filter {
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

		HttpServletRequest request = (HttpServletRequest) req;

		HttpSession session = request.getSession();
		Principal principal = (Principal) session.getAttribute("userPrincipal");
		try{
			System.out.println("Ingresando al Filter Estadistica.");
			
			EstadisticaBean estadistica = new EstadisticaBean();
			
			Encargado	encargado =	(Encargado) session.getAttribute("edocs_encargado");
	
			if (encargado == null) {
				Profile profile =
						(Profile) session.getAttribute("ea_user_profile");
						
					if (profile != null) {
						String rut = (String) profile.getId();
		
						String fullName =
								(String) profile.getAttribute("nombreCompleto");
					
						Collection empresas =
								(Collection) profile.getAttribute("empresas");
		
						encargado = new Encargado(rut, fullName, empresas);
					}			
				
			}
			
			
			String periodo = (String) request.getParameter("period");
			String formato = request.getParameter("FORMATO");
			estadistica.setId(104);
			estadistica.setPeriodo(Integer.parseInt(periodo));
			estadistica.setRutUsuario(encargado.getRut());
			estadistica.setIP(request.getRemoteAddr());
			estadistica.setFormato(formato);
			System.out.println("Cantidad de Empresas: "+encargado.getEmpresas().size());
			
			for (Iterator iter = encargado.getEmpresas().iterator(); iter.hasNext();) {
				Empresa empresa = (Empresa) iter.next();
				int rutEmpresa = empresa.getRut();
				String nombreEmpresa = empresa.getName();
				estadistica.setRutEmpresa(rutEmpresa);
				estadistica.setNombreEmpresa(nombreEmpresa);
				estadistica.setFechaCreacion(new Date(new java.util.Date().getTime()));
				estadistica.setHoraCreacion(new Time(new java.util.Date().getTime()));
				EstadisticaDAO.guardarEstadistica(estadistica);
			}
			
			
		
		}
		catch(Exception e){
			System.out.println("Problemas Generando estadistica");
			e.printStackTrace();
		}
		
		
		
		chain.doFilter(req, resp);

	}

	/**
	* Method init.
	* @param config
	* @throws javax.servlet.ServletException
	*/
	public void init(FilterConfig config) throws ServletException {

	}
	
}
