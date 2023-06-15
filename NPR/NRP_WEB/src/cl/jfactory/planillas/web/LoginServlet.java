package cl.jfactory.planillas.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.jfactory.planillas.service.helper.AutenticacionHelper;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2529223587949598257L;
	
	public static boolean AUTENTICACION_LOCAL = true; 
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String codigo = request.getParameter("codigo").trim();
		String password = request.getParameter("password").trim();
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession miSession= (HttpSession) request.getSession();
		
		
		
		SessionUsuario session = AutenticacionHelper.autenticarUsuario(codigo, password);
		
		if(session != null){

			miSession.setAttribute("usuario", session );
			res.sendRedirect("index.jsp");
		}
		else{
			res.sendRedirect("login.jsp?e=le");
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
