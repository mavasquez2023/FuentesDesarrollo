package cl.laaraucana.simat.filter;


import java.io.IOException;
import java.security.Principal;

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

import cl.araucana.core.registry.UserInfo;
import cl.araucana.core.registry.UserRegistryConnection;
import cl.araucana.core.registry.exception.UserRegistryException;


public class ValidateFilter implements Filter
{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String userID = null;
		//userID =  "10104927-2";
		
		HttpSession sesion = ((HttpServletRequest) request).getSession();
		//String sourceURL = String.valueOf(sesion.getAttribute("forwardPage"));
/*		
		if(sesion == null || sesion.getAttribute("nombre") == null){
			httpResponse.sendRedirect(request.getServletContext().getInitParameter("urlMenuDinamico"));
			return;
		}*/
		
		Principal userPrincipal = httpRequest.getUserPrincipal();
		
		//Verifica si el usuario está autorizado
		if (userPrincipal == null || (userID = userPrincipal.getName()) == null) {
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/errorLogin.jsp");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/loginRedirect.jsp");
			request.setAttribute("mensaje", "El usuario no está autenticado");
			requestDispatcher.forward(request, response);
			return;
		}

		HttpSession session = httpRequest.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
		
		//Si usuario no está autenticado
		if (userInfo == null) {		
			//Autentica usuario en Ldap
			UserRegistryConnection urConnection = null;
			try {
				urConnection = new UserRegistryConnection();
				userInfo = urConnection.getUserInfo(userID);
				session.setAttribute("userInfo", userInfo);
			} catch (UserRegistryException e) {
				e.printStackTrace();
					try {
						urConnection.close();
					} catch (Exception a) {}
					//Obtener mensaje desde archivo propertie
				request.setAttribute("mensaje", "Error al iniciar sesión");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/loginRedirect.jsp");
				requestDispatcher.forward(request, response);
				return;
			}
		}
		// Si usuario ya esta autenticado
		if (userInfo.isBlocked()) {
			System.out.println("Usuario se encuentra bloqueado");
			// Redirecciona al sistema anterior
			request.setAttribute("mensaje", "El usuario se encuentra bloqueado");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/loginRedirect.jsp");
			requestDispatcher.forward(request, response);
			return;
		} 
		
        sesion.setAttribute("login", userID);
		
		chain.doFilter(request, response);		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
