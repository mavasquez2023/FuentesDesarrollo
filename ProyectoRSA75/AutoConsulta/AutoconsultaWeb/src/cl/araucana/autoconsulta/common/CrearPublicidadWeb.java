package cl.araucana.autoconsulta.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @version 	1.0
 * @author
 */
public class CrearPublicidadWeb extends HttpServlet implements Servlet {

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
			performTask(req,resp);	
	}

	/**
	* @see javax.servlet.http.HttpServlet#void (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
			performTask(req,resp);
	}

	/**
	 * Manejador de metodos GET y POST
	 * @param req
	 * @param resp
	 */
	private void performTask(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	
			ServletContext sctx = getServletContext();
	
			RequestDispatcher dispatcher;
			
			HttpSession session = req.getSession();
			cl.araucana.autoconsulta.vo.UsuarioVO usuario = (cl.araucana.autoconsulta.vo.UsuarioVO)session.getAttribute("datosUsuario");
			cl.araucana.autoconsulta.vo.AfiliadoVO afiliado = null;
			
			if(session.getAttribute("pubSimulacionAfiliado") != null)
				afiliado = (cl.araucana.autoconsulta.vo.AfiliadoVO) session.getAttribute("pubSimulacionAfiliado");

			String appnameAux = (String)session.getAttribute("struts.application");
			String fullappnameAux =  appnameAux + ((String)session.getAttribute("struts.subapplication")==null ? "" : "/"+(String)session.getAttribute("struts.subapplication"));

			String urlPdf;

			String path = session.getAttribute("pathPublicidad") != null ? (String) session.getAttribute("pathPublicidad") : "";
			Long monto = (Long) session.getAttribute("montoPreAprobado");
			session.removeAttribute("montoPreAprobado");
			if(usuario.isEsAfiliadoActivo() || (afiliado != null && afiliado.getRut() != 0)){
				 urlPdf = "/"+fullappnameAux+"/pdf/SolicitudesAfiliados.pdf";
			
				ByteArrayOutputStream buffer;		
				
				try{
				/*
					if(afiliado == null) //si estamos en el caso comun (caso 1)	 
						buffer = ImageProcessing.addTextToImg(path,"Credito_Afiliados.jpg",usuario.getNombre(),monto.toString(),session.getId());	 
					else //(caso 2) SOLO AFILIADOS
						buffer = ImageProcessing.addTextToImg(path,"Credito_Afiliados.jpg",afiliado.getNombre()+" "+afiliado.getApellidoPaterno()+" "+afiliado.getApellidoMaterno(),monto.toString(),session.getId());	 
				
					resp.setContentType("text/html");

					PrintWriter writer = resp.getWriter();

					if (buffer == null) {
						writer.println(
							  "<HTML><HEAD>"
							+ "<TITLE>La Araucana C.C.A.F. - Servicio No Disponible</TITLE>"
							+ "</HEAD><BODY>");

						writer.println("<BR><H1>Servicio No Disponible</H1><BR>");
						writer.println("</BODY></HTML>");
					} else {

						session.setAttribute("image", buffer);

						writer.println(
							  "<HTML><HEAD>"
							+ "<TITLE>La Araucana C.C.A.F. - Campaña de Crédito</TITLE>"
							+ "</HEAD><BODY>");

						writer.println("<CENTER><IMG SRC=image.jpg></CENTER>");
						writer.println("</BODY></HTML>");
					}

					writer.close();
					
					dispatcher = req.getRequestDispatcher("/web/MostrarPublicidadWeb");
					dispatcher.forward(req,resp);*/
				}
				catch(Exception e){
					
				}
			
			}
	}
}
