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
import cl.araucana.cheque.to.ParamURL;
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
			log.debug("Url:" + request.getQueryString());
			
			//Rescatando parametros de url
			String username=request.getParameter("username");
			String encode= request.getParameter("folio");
			UserPrincipal credential= Encripta.decode(encode);
			String usuario= credential.getName();
			folio= credential.getPassword();
			
			log.info("Folio recibido:" + folio);
			String cola= request.getParameter("cola");
			String dispositivo= request.getParameter("dispositivo");
			ParamURL param= new ParamURL();
			param.setCola(cola);
			param.setDispositivo(dispositivo);
			param.setCajero(usuario);
			param.setIpRemota(request.getRemoteHost());
			param.setUsername(username);
			log.info("/Cola/Dispositivo/Usuario: "  + param.getRuta());
			
			//Verificando si existe ruta carpeta para dejar el PDF, sino se crea
			carpetas= new EstructuraCarpeta(Parametros.getInstance().getServer(), Parametros.getInstance().getUsuario(), Parametros.getInstance().getPassword());
			boolean iscarpeta= carpetas.existFile(Parametros.getInstance().getRutaPDF() + param.getRuta());
			log.debug("resultado:" + iscarpeta);
			if (!iscarpeta){
				iscarpeta= carpetas.crearCarpeta(Parametros.getInstance().getRutaPDF() + param.getRuta());
				log.debug("resultado:" + iscarpeta);
			}
			
			//Se invoca la generación del PDF
			if(iscarpeta){
				log.debug("Carpeta OK, instanciando Report");
				//Se invoca generación de PDF
				Report pdf = new Report();
				resultado= pdf.createReport(Integer.parseInt(folio), param, carpetas);
			}else{
				log.warn("No se pudo crear carpeta:" + Parametros.getInstance().getRutaPDF() + param.getRuta());
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
