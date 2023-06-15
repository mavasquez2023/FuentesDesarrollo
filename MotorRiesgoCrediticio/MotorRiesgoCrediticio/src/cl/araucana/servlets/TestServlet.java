package cl.araucana.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.araucana.manager.RespuestaEquifax;

public class TestServlet extends HttpServlet {
	public TestServlet() {
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		RespuestaEquifax mgr = new RespuestaEquifax();
		mgr.procesarRespuestaEquifax();
	}
}