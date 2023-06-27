package cl.araucana.ea.edocs.re.servlets;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.araucana.common.Profile;
import cl.araucana.ea.ctacte.dto.EmpresaTO;
import cl.araucana.ea.edocs.re.Empresa;
import cl.araucana.ea.edocs.re.Encargado;
import cl.araucana.ea.edocs.util.Padder;


/**
 * @version 1.0
 * @author David Espinosa
 */
public class RentasServlet extends HttpServlet implements Servlet {

	private ServletContext servletContext;
	private String documentBaseDir;
	private String separadorCarpetas;


	public void init() throws ServletException {
		
		servletContext = getServletContext();
		
		documentBaseDir =
				servletContext.getInitParameter("edocs.re.documentBaseDir");
				
		separadorCarpetas =
				servletContext.getInitParameter("edocs.re.separadorCarpetas");
		
		System.out.println("init()");
		System.out.println("    documentBaseDir      = " + documentBaseDir);
		System.out.println("    separadorCarpetas      = " + separadorCarpetas);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		try {
			processRequest(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		
//			RequestDispatcher dispatcher =
//					request.getRequestDispatcher(
//							"/WEB-INF/edocs/userNotify.jsp");
//						
//			dispatcher.forward(request, response);
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		try {
			processRequest(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		
//			RequestDispatcher dispatcher =
//					request.getRequestDispatcher(
//							"/WEB-INF/edocs/userNotify.jsp");
//						
//			dispatcher.forward(request, response);
		}
	}
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				ServletContext servletContext = getServletContext();
				String documentBaseDir =
						servletContext.getInitParameter("edocs.re.documentBaseDir");
				String separadorCarpetas =
						servletContext.getInitParameter("edocs.re.separadorCarpetas");
				String forwardPage = "/WEB-INF/edocs/rentas.jsp";
				Encargado encargado = null;
				boolean invalidatedSession = false;
				HttpSession session = request.getSession();
				Principal principal = (Principal) session.getAttribute("userPrincipal");


				//Checks if user principal is authenticated.
				
				if (principal == null || principal.getName() == null) {
					session.invalidate();
		
					invalidatedSession = true;
					forwardPage = "logon.jsp";
				} else {
					encargado =	(Encargado) session.getAttribute("ea_encargado");

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
							
							Iterator iterator = encargado.getEmpresas().iterator();
							
							while (iterator.hasNext()) {
								Empresa empresa = (Empresa) iterator.next();
								String rutEmpresa = "" + empresa.getRut();
								rutEmpresa = Padder.pad(rutEmpresa, 8, '0', true);
								File file =	new File(documentBaseDir + separadorCarpetas + rutEmpresa + separadorCarpetas + rutEmpresa + ".txt");

								//System.out.println("BonoExtraordinarioServlet: Se buscó el archivo: " + documentBaseDir + separadorCarpetas + rutEmpresa + separadorCarpetas + rutEmpresa + ".txt");
								if (file.exists()){
									empresa.setFlag(1);
									//System.out.println("BonoExtraordinarioServlet: Se encontró el archivo");
								}
							}
							
							session.setAttribute("edocs_encargado", encargado);
						} else {
							session.invalidate();
							invalidatedSession = true;
							forwardPage = "/logon.jsp";
						}
					}
				}
		
				if (!invalidatedSession) {

					/*
					 * Prepares contents to JSP page.
					 */
					/*
					 * Injects parameter "source" to JSP page.
					 */
					String source = request.getParameter("source");

					if (source != null) {
						source = source.trim();
			
						if (source.length() == 0) {
							source = "EA";
						}
					} else {
						source = "EA";
					}

					forwardPage += "?source=" + source;
				}
		
				RequestDispatcher dispatcher =
						request.getRequestDispatcher(forwardPage);

				dispatcher.forward(request, response);
	}
}
