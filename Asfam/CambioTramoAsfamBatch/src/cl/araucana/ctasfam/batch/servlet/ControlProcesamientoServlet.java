package cl.araucana.ctasfam.batch.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.araucana.ctasfam.batch.thread.AdministradorHebrasThread;

public class ControlProcesamientoServlet extends HttpServlet {

	public void init() throws ServletException {}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String msg = "Accion no valida";
		String accion = request.getParameter("accion");
		if("start".equalsIgnoreCase(accion)){
			if(AdministradorHebrasThread.getRunning()){
				msg = "El proceso ya esta en ejecucion";
			}else{
				AdministradorHebrasThread process = new AdministradorHebrasThread();
				process.start();
				msg = "Se inicio la ejecucion del proceso";
			}
		}else if("stop".equalsIgnoreCase(accion)){
			if(AdministradorHebrasThread.getRunning()){
				AdministradorHebrasThread.setRunning(false);
				msg = "Se detuvo la ejecucion del proceso";
			}else{
				msg = "El proceso no esta en ejecucion";
			}
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("	<head></head>");
		out.println("<body>");
		
		out.println("	<table border='1'>");
		out.println("		<tr>");
		out.println("			<td colspan='2'><b>" + msg + "</b></td>");
		out.println("		</tr>");
		out.println("	</table>");
				
		out.println("</body>");
		out.println("<html>");
	}

	public void destroy() {	}
	
	
}