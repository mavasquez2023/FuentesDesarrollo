/**
 * 
 */
package cl.araucana.cheque.servlet;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cl.araucana.cheque.dao.EstadisticasDAO;
import cl.araucana.cheque.dao.TesoDAO;
import cl.araucana.cheque.to.ParamURL;
import cl.araucana.cheque.util.Encripta;

/**
 * @author usist199
 *
 */
public class Reinyectar extends HttpServlet{
	private static Logger log = Logger.getLogger(Reinyectar.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		TesoDAO dao=null;
		try{
			HttpSession session = request.getSession();
			Principal principal = request.getUserPrincipal();
			String username="";
			if (principal != null && principal.getName() != null) {
				username= principal.getName();
				log.info("Principal username:" + username);
			}
			//Rescatando parametros de url
			String id= request.getParameter("id");
			log.info("Reprocesando Cheque ID:" + id);
			
			dao= new TesoDAO();
			EstadisticasDAO estadisdao= new EstadisticasDAO(dao.getConnection());
			ParamURL params= (ParamURL)estadisdao.selectID(Integer.parseInt(id));
			String paramsencode= Encripta.encode(params.getCajero(), String.valueOf(params.getFolio()));
			//request.setAttribute("folio", paramsencode);
			//request.setAttribute("cola", params.getCola());
			//request.setAttribute("dispositivo", params.getDispositivo());
			response.sendRedirect(request.getContextPath() + "/Cheque?folio="+ paramsencode + "&cola=" + params.getCola() + "&dispositivo=" + params.getDispositivo()+ "&username=" + username);
			//request.getRequestDispatcher("/" + forward).forward(request,response);
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Mensaje=" + e.getMessage());
		}
		finally{
			if(dao!=null){
				dao.closeConnectionDAO();
			}
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}
}
