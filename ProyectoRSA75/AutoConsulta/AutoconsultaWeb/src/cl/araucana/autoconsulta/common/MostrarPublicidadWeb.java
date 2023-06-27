package cl.araucana.autoconsulta.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @version 	1.0
 * @author
 */
public class MostrarPublicidadWeb extends HttpServlet implements Servlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				System.out.println("DEBUG: get --------------------------------");
				performTask(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				System.out.println("DEBUG: post --------------------------------");
				performTask(request, response);	
	}
	
	private void performTask(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		System.out.println("DEBUG: ENTRE --------------------------------");
		HttpSession session = request.getSession();
	
		ByteArrayOutputStream buffer =
				(ByteArrayOutputStream) session.getAttribute("image");
				
		if (buffer == null) {
			return;
		}
		
		response.setContentType("image/jpeg");
		response.setContentLength(buffer.size());

		OutputStream output = response.getOutputStream();

		buffer.writeTo(output);
		output.close();
	}


}
