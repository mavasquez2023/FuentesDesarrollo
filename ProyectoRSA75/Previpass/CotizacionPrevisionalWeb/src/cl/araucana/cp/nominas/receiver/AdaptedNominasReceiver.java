

package cl.araucana.cp.nominas.receiver;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;

import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bh.talon.User;


public class AdaptedNominasReceiver extends HttpServlet {

	private static final long serialVersionUID = 5061139515095238781L;
	
	private static final Logger logger = Logger.getLogger(AdaptedNominasReceiver.class);
	
	private ServletContext servletContext;
	
	public void init(ServletConfig config) throws ServletException {
		
		servletContext = config.getServletContext();
	}
	
	public long getLastModified(HttpServletRequest request) {
		return System.currentTimeMillis();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 * Check HTTP session expiration.
		 */
		HttpSession session = request.getSession();
		User user =	(User) session.getAttribute("currentUser");
		
		if (user == null)  {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.html");

			requestDispatcher.forward(request, response);
			
			logger.info("AdaptedNominasReceiver: Expired session '" + session.getId() + "'");
			
			return;
		}
		
		/*
		 * Request validation.
		 */		
		
		String contentType = request.getContentType();
		int contentLength = request.getContentLength();
		
		logger.info("AdaptedNominasReceiver: process request");
		logger.info("    contentType   = " + contentType);
		logger.info("    contentLength = " + contentLength);

		if (contentType == null || !contentType.startsWith("multipart/form-data") || contentLength == 0) {
			return;
		}

		logger.info("AdaptedNominasReceiver: sessionID=" + session.getId());

		/*
		 * Get list of nominas from multipart input stream.
		 */
		InputStream input = null;
		NominasMultiPartInputStream multiPartInput = null;
		List nominas = null;
		String accion = "";
		String subAccion = "";
		String formato= "";
		byte[] zippedData=null;
		try {
			input = request.getInputStream();

			multiPartInput = new NominasMultiPartInputStream(input, contentType, contentLength);

			accion = multiPartInput.getParameter("accion");
			subAccion = multiPartInput.getParameter("subAccion");
			formato = multiPartInput.getParameter("formato");
			if(formato.equalsIgnoreCase("zip")){
				zippedData= multiPartInput.getZippedNomina();
			}else{
				nominas = multiPartInput.getAttachedNominas();
			}
		} catch (IOException e) {
			e.printStackTrace();
			
			// TODO
			return;
		} finally {
			if (multiPartInput != null) {
				try {
					multiPartInput.close();
				} catch (IOException e) {}
			} else if (input != null) {
				try {
					input.close();
				} catch (IOException e) {}				
			}
		}

		/*
		 * Zips Nóminas.
		 */
		if(!formato.equalsIgnoreCase("zip")){
			zippedData = zipNominas(nominas);
		}
		/*
		 * Forwards adapted HTTP Servlet request.
		 */
		AdaptedHttpServletRequestWrapper adaptedRequest = new AdaptedHttpServletRequestWrapper(servletContext, request, zippedData);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/receiver.do" + "?accion=" + accion + "&subAccion=" + subAccion);

		adaptedRequest.setAttribute("adaptedReceiver", Boolean.TRUE);
		
		logger.info("AdaptedNominasReceiver: forwarding zipped nominas ...");
		
		requestDispatcher.forward(adaptedRequest, response);
		
		logger.info("AdaptedNominasReceiver: zipped nominas forwarded.");		
	}
	
	private byte[] zipNominas(List nominas) throws IOException {
		
		/*
		 * Calculates reference content length.
		 */
		final int ZIP_ENTRY_SIZE = 128;
		
		int referenceContentLength = 0;
		
		logger.info(">> Nominas:");
		
		for (int i = 0; i < nominas.size(); i++) {
			NominasMultiPartInputStream.AttachedNomina nomina = (NominasMultiPartInputStream.AttachedNomina) nominas.get(i);
			
			byte[] content = nomina.getContent();
			
			logger.info("    nomina=" + nomina.getID() + " "
					+ "empresa=" + nomina.getIDEmpresa() + " "
					+ "fileName=" + nomina.getFileName() + " "
					+ "size=" + content.length);
			
			logger.info("--------------------------------------");
			logger.info(new String(content, 0, Math.min(128, content.length)));
			logger.info("--------------------------------------");
			
			referenceContentLength += content.length;
		}
		
		ByteArrayOutputStream output = new ByteArrayOutputStream(referenceContentLength + ZIP_ENTRY_SIZE * nominas.size());
		
		ZipOutputStream zipOutput = new ZipOutputStream(output);

		for (int i = 0; i < nominas.size(); i++) {
			NominasMultiPartInputStream.AttachedNomina nomina = (NominasMultiPartInputStream.AttachedNomina) nominas.get(i);
			
			byte[] content = nomina.getContent();
			ZipEntry zipEntry = new ZipEntry(nomina.getID());

			zipOutput.putNextEntry(zipEntry);
			zipOutput.write(content);
			zipOutput.closeEntry();
		}
		
		zipOutput.close();
		output.close();
		
		return output.toByteArray();
	}
}