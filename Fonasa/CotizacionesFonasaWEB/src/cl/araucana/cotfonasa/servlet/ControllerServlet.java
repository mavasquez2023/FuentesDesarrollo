package cl.araucana.cotfonasa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.araucana.cotfonasa.impl.ProcesoFonasaImpl;

public class ControllerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6104731085050978762L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {        
      
        processRequest(request, response);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {        
     
        processRequest(request, response);
    }
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
        String id = request.getParameter("id").toString();
        
        Properties props = new Properties();
		
		props.load(ControllerServlet.class.getClassLoader().getResourceAsStream("cl/araucana/cotfonasa/properties/parametros.properties"));
		String idProperty = props.getProperty("LLAVE");
		
		
		
		if(id != null)
		{
				System.out.println("id:"+id);
				
				String url = request.getRequestURI();
				
				String pages[] = url.split("/");
				String pagina = pages[pages.length -1];
				
				System.out.println("pagina: "+pagina);
				
				System.out.println("url: "+url);
				
				// verifico que las llaves sean identicas
				if(id.equals(idProperty))
				{
					RequestDispatcher requestDispatcher; 
					requestDispatcher = request.getRequestDispatcher("/bitacora.jsp");
					requestDispatcher.forward(request, response);
					
					
				}else{
					response.setContentType("text/html;charset=UTF-8");
			        PrintWriter out = response.getWriter();
			       
		
			        out.println("<html>");
			        out.println("<head>");
			        out.println("<title>Acceso Indebido</title>");
			        out.println("</head>");
			        out.println("<body>");
			  
			        out.println("<p>Acceso no permitido!</p>");
			        out.println("</body>");
			        out.println("</html>");
		
			        out.close();
					
					
				}
		
		}
        
    }

}
