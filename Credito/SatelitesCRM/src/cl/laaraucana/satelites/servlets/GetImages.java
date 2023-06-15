package cl.laaraucana.satelites.servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import cl.laaraucana.satelites.certificados.utils.CertificadoConst;
/**
 * Servlet implementation class GetImages
 */
public class GetImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected Logger logger = Logger.getLogger(this.getClass());
       
    public GetImages() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProccesRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProccesRequest(request, response);
	}
	
	protected void ProccesRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombreImg");
		try{
			File file = new File(CertificadoConst.RES_CERTIFICADOS.getString("certificados.imgPath")+nombre);
			byte[] bytes = FileUtils.readFileToByteArray(file);
			logger.info("Imagen "+nombre+" obtenida correctamente");
			response.setContentType("image/jpeg");
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes);
		}catch(FileNotFoundException ffe){
			logger.error("Error al obtener img con nombre: "+nombre,ffe);
		}

	}

}
