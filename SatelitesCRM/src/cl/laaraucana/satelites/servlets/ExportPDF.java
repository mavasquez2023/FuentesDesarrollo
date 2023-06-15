package cl.laaraucana.satelites.servlets;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("/ExportPDF")
public class ExportPDF extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -387343909829181466L;
	protected Logger logger = Logger.getLogger(this.getClass());

	public ExportPDF() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProccesRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProccesRequest(request, response);
	}

	protected void ProccesRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			logger.debug("<< Ingreso a ExportPDF");
			byte[] bites = (byte[]) request.getAttribute("bites");
			String nombreArchivo = (String) request.getAttribute("nombreArchivo");
			logger.debug("Datos obtenidos correctamente");
			
			if (bites == null || bites.length == 0) {
				throw new Exception("Problemas al generar el documento");
			}
			if (nombreArchivo == null || nombreArchivo.length() == 0) {
				throw new Exception("No se ha especificado un nombre para el documento");
			}
			// Enviar el archivo generado a la página.
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "attachment;filename=" + nombreArchivo + ".pdf");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
			
			logger.debug("Datos de respuesta (application/pdf) seteados correctamente.");
			
			OutputStream op = response.getOutputStream();
			op.write(bites, 0, bites.length);
			op.flush();
			op.close();
			logger.debug(">> Reporte Creado y desplegado Exitosamente");
			
		} catch (Exception e) {
			e.printStackTrace();
			response.reset();
			logger.error("Error en " + this.getClass().getName() + ":" + e.getMessage(), e);
			RequestDispatcher rd = request.getRequestDispatcher("/main/error.do?errorMsg=" + e.getMessage());
			rd.forward(request, response);
		}

	}

}
