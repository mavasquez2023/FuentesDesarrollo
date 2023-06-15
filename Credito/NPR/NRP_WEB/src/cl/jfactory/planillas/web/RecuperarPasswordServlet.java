package cl.jfactory.planillas.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.jfactory.planillas.service.helper.AutenticacionHelper;
import cl.liv.mail.impl.MailImpl;

public class RecuperarPasswordServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2529223587949598257L;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String codigo = request.getParameter("codigo");
		String password = fill ( (int) (Math.random() * 10000 )+"", 5 );
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession miSession= (HttpSession) request.getSession();

		boolean resultado = new AutenticacionHelper().resetearPassword(codigo, password);
		
		res.sendRedirect("resultadoRecuperacion.jsp?r="+resultado);
		
		MailImpl.enviarMail("idConfiguracion","aplica@laaraucana.cl",codigo,"Recuperaci�n de Contraseña","<html>Estimad@ Usuari@,<br/><br/> Su nueva contraseña de acceso es: "+password+" <br/> Saludos </html>");
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public static String fill(String texto, int largo){
		if(texto != null && texto.length() < largo){
			for(int i=texto.length(); i<largo; i++){
				texto = texto + "0";
			}
			return texto;
		}
		
		return "00000";
	}
}
