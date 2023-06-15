package cl.jfactory.planillas.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.liv.export.comun.util.LectorConfigInit;

public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2529223587949598257L;
	
	public static void inicializar() {
		ArrayList comandos = LectorConfigInit.getDataXML();
		if(comandos != null) {
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(int i=0; i< comandos.size(); i++){
			CommandServlet.ejecutarComando(comandos.get(i).toString());
		}
		
	}
	
	static {
		inicializar();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	public static void main(String[] args) {
		inicializar();
	}
	
}
