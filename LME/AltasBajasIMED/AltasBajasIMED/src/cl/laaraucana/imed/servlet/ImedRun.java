/**
 * 
 */
package cl.laaraucana.imed.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cl.laaraucana.imed.business.ServicioImedThread;
import cl.laaraucana.imed.business.ServiciosImed;
import cl.laaraucana.imed.config.Configuraciones;

/**
 * @author IBM Software Factory
 *
 */
public class ImedRun extends HttpServlet {
	protected Logger logger = Logger.getLogger(this.getClass());
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("iniciando Envio Cargas IMED");
		String correo= Configuraciones.getConfig("imed.correo.to.usuario");
		String tipo=request.getParameter("tipo");
		ServicioImedThread threadimed= new ServicioImedThread(tipo);
		threadimed.run();
		
		PrintWriter out;
		String title = "Servicio Imed Manual";
		response.setContentType("text/html");
		out = response.getWriter();
		out.println("<HTML><HEAD><TITLE>");
		out.println(title);
		out.println("</TITLE></HEAD><BODY>");
		out.println("<H1>" + title + "</H1>");
		out.println("<P>Ejecutando Proceso Imed, se enviará correo al finalizar a : " + correo);
		out.println("</BODY></HTML>");
		out.close();
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
