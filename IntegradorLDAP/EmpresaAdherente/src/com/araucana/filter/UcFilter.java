package com.araucana.filter;

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

import org.apache.log4j.Logger;

import java.nio.file.attribute.UserPrincipal;


public class UcFilter implements Filter {
	private static final Logger logger = Logger.getLogger(UcFilter.class);
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		logger.info("Ejecutando UcFilter.");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		//Obtener la sesion
		HttpSession sesion = request.getSession(true);
		
		String usuario;
		String password;
		sesion.removeAttribute("uc");
		String uc = null;
		if(request.getParameter("uc") != null){
			uc = request.getParameter("uc");
			sesion.setAttribute("uc", uc);
		}else{
			if(request.getParameter("j_username")!= null && request.getParameter("j_password") != null){
				usuario = request.getParameter("j_username");
				password = request.getParameter("j_password");
				/*String uc = encode( usuario, password);//Mantener codificaci�n antigua */
				//Nuevo m�todo de encriptaci�n
/***				UserPrincipal userPrincipal = new UserPrincipal(usuario,password);
				uc = userPrincipal.encode(); */
				
				sesion.setAttribute("uc", uc);
			}
		}
		
		logger.info("Credenciales: " + uc);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
