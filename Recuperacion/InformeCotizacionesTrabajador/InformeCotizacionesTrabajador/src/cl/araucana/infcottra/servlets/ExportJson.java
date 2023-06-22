package cl.araucana.infcottra.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class ExportJson
 */
public class ExportJson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExportJson() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProccesRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProccesRequest(request, response);
	}
	
	protected void ProccesRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String json = gson.toJson(request.getAttribute("hash"));
		PrintWriter out = response.getWriter();
		out.print(json);
	}

}
