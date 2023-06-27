/**
 * 
 */
package cl.araucana.cheque.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cl.araucana.cheque.report.Report;
import cl.araucana.cheque.to.Parametros;
import cl.araucana.cheque.util.Encripta;
import cl.araucana.cheque.util.EstructuraCarpeta;
import cl.araucana.core.util.UserPrincipal;

/**
 * @author usist199
 *
 */
public class Cheque extends HttpServlet {

	private static Logger log = Logger.getLogger(Cheque.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String resultado="0";
		String folio="0";
		EstructuraCarpeta carpetas=null;
		try{
			HttpSession session = request.getSession();
			/*Principal principal = request.getUserPrincipal();
			if (principal != null && principal.getName() != null) {
				String username= principal.getName();
				session.setAttribute("user", username);
			}*/
			log.debug("*****>>> Nuevo Cheque, Url:" + request.getQueryString());
			
			//Rescatando parametros de url
			String encode= request.getParameter("folio");
			UserPrincipal credential= Encripta.decode(encode);
			String usuario= credential.getName();
			folio= credential.getPassword();
			
			log.info("Folio recibido:" + folio);
			String cola= request.getParameter("cola");
			String dispositivo= request.getParameter("dispositivo");
			String rutasalida= "/" + cola + "/" + dispositivo + "/" + usuario + "/";
			log.info("/Cola/Dispositivo/Usuario: "  + rutasalida);
			
			//Verificando si existe ruta carpeta para dejar el PDF, sino se crea
			carpetas= new EstructuraCarpeta(Parametros.getInstance().getServer(), Parametros.getInstance().getUsuario(), Parametros.getInstance().getPassword());
			boolean iscarpeta= carpetas.existFile(Parametros.getInstance().getRutaPDF() + rutasalida);
			log.debug("resultado:" + iscarpeta);
			if (!iscarpeta){
				iscarpeta= carpetas.crearCarpeta(Parametros.getInstance().getRutaPDF() + rutasalida);
				log.debug("resultado:" + iscarpeta);
			}
			
			//Se invoca la generación del PDF
			if(iscarpeta){
				log.info("Invocando Report folio:" + Integer.parseInt(folio) );
				//Se invoca generación de PDF
				Report pdf = new Report();
				carpetas.setRutasalida(rutasalida);
				resultado= pdf.createReport(Integer.parseInt(folio), carpetas);
			}else{
				log.warn("No se pudo crear carpeta:" + Parametros.getInstance().getRutaPDF() + rutasalida);
				log.error("Cheque PDF no creado, folio: "  + Integer.parseInt(folio));
			}
			
			//request.getRequestDispatcher("/" + forward).forward(request,response);
		}catch (Exception e) {
				e.printStackTrace();
				resultado=e.getMessage();
				log.error("Mensaje=" + e.getMessage());
		}
		finally{
			PrintWriter print= response.getWriter();
			print.print("F." + folio + ":" + resultado);
			print.flush();
			print.close();
			if(carpetas != null){
				carpetas.closeConnection();
			}
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}

}
