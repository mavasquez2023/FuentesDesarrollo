package cl.araucana.cp.afbr;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LimpiarGrilla extends HttpServlet{

	 
	private static final long serialVersionUID = -3439635232668694772L;

	public LimpiarGrilla() {
		 
	}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
	response.sendRedirect("fblank.htm");
}
	
	
}
