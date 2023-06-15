package cl.jfactory.planillas.web;

import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.liv.export.comun.util.Mensajes;
import cl.sbs.util.reflection.impl.UtilReflectionImpl;


public class CommandServlet extends HttpServlet {
	
	static ResourceBundle configuraciones = ResourceBundle.getBundle("etc/config_web");
	
	/** Initializes the servlet. */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/** Destroys the servlet. */
	public void destroy() {
		
		ejecutarComando(":scheduler;;:eliminar;;:proceso_output_libesa");
	}
	String ACTION_LIMPIAR_SESSION = "limpiar_datos_session";
	
	
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			java.io.IOException {

		String comando = request.getParameter("comando");
	
		if(comando != null ){
			ejecutarComando(comando);
			
		}
	}
	
	public static HashMap ejecutarComando(String comando){
		String[] partes = comando.split(";;");
		try{
			if(partes[0].split(":")[1].equalsIgnoreCase("scheduler")){
				String action = partes[1].split(":")[1];
				String id = partes[2].split(":")[1];
				UtilReflectionImpl reflection = new UtilReflectionImpl();
				Object result = reflection.executeReflection(configuraciones.getString("clase.impl.schedule"), action, new Class[]{String.class}, new Object[]{id});
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			java.io.IOException {
			Mensajes.info("get request");
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			java.io.IOException {
			Mensajes.info("post request");
		processRequest(request, response);
	}

}