/**
 * 
 */
package cl.araucana.ldap.servlet;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.log4j.Logger;


/**
 * @author usist24
 *
 */
public class Salir extends HttpServlet {
	private static Logger log = Logger.getLogger(Salir.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request, response);			
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		try{
			HttpSession session = request.getSession();
			String forward= request.getParameter("destino").toString();
			log.info("Invalidando sesión");
			session.removeAttribute("user");
			session.invalidate();
			log.info("Redireccionando a:" + request.getContextPath() + "/" + forward);
			response.sendRedirect(request.getContextPath() + "/" + forward);
		}catch (Exception e) {
				e.printStackTrace();
		}
	}
}
