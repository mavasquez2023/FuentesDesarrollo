

package cl.araucana.ea.servlet.filter;


import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;
import java.util.TreeSet;

import javax.naming.InitialContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.araucana.common.Profile;
import cl.araucana.common.Rut;
import cl.araucana.common.RutInvalidoException;
import cl.araucana.common.UserProfile;

import cl.araucana.core.registry.Enterprise;
import cl.araucana.core.registry.User;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;

import cl.araucana.ea.credito.dto.OpcionTO;
import cl.araucana.ea.ctacte.dto.EmpresaTO;
import cl.araucana.ea.ctacte.dto.RutTO;


/**
 * @version 	1.0
 * @author
 */
public class PostLogonFilter implements Filter {
	
	private FilterConfig filterConfig;
	private ServletContext servletContext; 
	
	public void init(FilterConfig filterConfig) throws ServletException {
		
		this.filterConfig = filterConfig;
		servletContext = filterConfig.getServletContext();
	}
		
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain)throws ServletException, IOException {

		// execute chained servlet.		
		chain.doFilter(req, resp);

		/*
		 *		Create a profile for the user
		 *			0. check if profile is present in session
		 * 			1. get user id
		 * 			2. create new instance of profile
		 * 			3. set attributes
		 * 			4. add profile to session
		 */

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		Profile profile = (Profile) session.getAttribute("ea_user_profile");
		 
		if (profile != null) {
			return;
		}
		
		Principal principal = request.getUserPrincipal();
		
		if (principal != null) {
			String userID = principal.getName();

			if (request.isUserInRole("Ejecutivo")){
				session.setAttribute("rol", "Ejecutivo");
			}

			UserRegistryConnection connection = null;
		
			try {
				connection = new UserRegistryConnection();
			
				connection.setUserID(userID);
			
				User user = connection.getUser();
				String fullName = user.getFullName();
				
				Collection enterprises =
						connection.getEnterprises(
								"PorEmpAdhe", "PorEmpAdheEnc");

				profile = createProfile(userID, fullName, enterprises);

				session.setAttribute(
						"opciones",	profile.getAttribute("opcionesEmpresa"));
						
				session.setAttribute("ea_user_profile", profile);
			} catch (UserRegistryException e) {
				e.printStackTrace();
				session.invalidate();

				RequestDispatcher dispatcher =
						request.getRequestDispatcher("/logon.jsp");

				dispatcher.forward(request, response);
				
				return;
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (UserRegistryException ure) {}
				}
			}

			String ctacteDelegateImpl =
					servletContext.getInitParameter("CTACTE_DELEGATE_IMPL");
					
			String creditoDelegateImpl =
					servletContext.getInitParameter("CREDITO_DELEGATE_IMPL");
					
			System.out.println("ctacteDelegateImpl = " + ctacteDelegateImpl);
			System.out.println("creditoDelegateImpl = " + creditoDelegateImpl);
			
			try {
				cl.araucana.ea.ctacte.delegate.BusinessDelegate ctacteDelegate = 
					(cl.araucana.ea.ctacte.delegate.BusinessDelegate)
						cl.araucana.ea.ctacte.delegate.BusinessDelegateFactory
						.singlton()
						.newInstance(ctacteDelegateImpl);

				cl.araucana.ea.credito.delegate.BusinessDelegate creditoDelegate = 
					(cl.araucana.ea.credito.delegate.BusinessDelegate)
						cl.araucana.ea.credito.delegate.BusinessDelegateFactory
						.singlton()
						.newInstance(creditoDelegateImpl);
			} catch (Exception e) {
				e.printStackTrace();
			}
					
			/*
			 * Set user Locale as "es" forcing the numeric values are
			 * represented by Spanish format.
			 *
			 * The decimal format symbol "es" defined in
			 * "sun.text.resources.LocalElements_es_xx.java" has erroneous
			 * pattern definition.
			 * 
			 * Locale can be set at request scope in Action class using
			 * setLocale(request, new java.util.Locale("es"));
			 */
			session.setAttribute(
					org.apache.struts.Globals.LOCALE_KEY,
					new java.util.Locale("es"));
		}
	}

	private Profile createProfile(String userID, String nombreCompleto,
			Collection c) {

		Profile profile = new UserProfile(userID);
		Collection empresas = new ArrayList();
		TreeSet opcionesEmpresa = new TreeSet();
		Iterator it = c.iterator();
		
		while (it.hasNext()) {
			Enterprise enterprise = (Enterprise) it.next();
			Rut rutEmpresa = null;
			
			try {
				rutEmpresa = new Rut(enterprise.getID());
			} catch (RutInvalidoException e) {
				System.out.println(
						  "EA: Rut de empresa '" + enterprise.getID() + "'"
						+ "inválido en 'createProfile'. Empresa fue excluida.");
						
				continue;
			}
			
			EmpresaTO empresa =
					new EmpresaTO(
							new RutTO(rutEmpresa.getId(), rutEmpresa.getDv()),
							enterprise.getName());
				
			empresas.add(empresa);
			
			opcionesEmpresa.add(
					new OpcionTO(
							rutEmpresa.getId() + "",
							  rutEmpresa.getFormattedRut()
							+ "-"
							+ rutEmpresa.getDv()
							+ ": "
							+ enterprise.getName()));
		}
		
		profile.setAttribute("nombreCompleto", nombreCompleto);
		
		if (empresas.size() > 0) {
			OpcionTO firstEmpresa = (OpcionTO) opcionesEmpresa.first();
			Long defaultEmpresaID = new Long(firstEmpresa.getIdValue());
			
			profile.setAttribute("empresa", defaultEmpresaID);
		}
				
		profile.setAttribute("empresas", empresas);
		profile.setAttribute("opcionesEmpresa", opcionesEmpresa);

		profile.setStatus(Profile.VALID_STATUS);
		
		return profile;
	}
	
	public void destroy() {
	}
}
