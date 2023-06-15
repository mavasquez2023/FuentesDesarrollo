package cl.jfactory.planillas.web;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cl.liv.comun.utiles.PropertiesUtil;
public class UploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2163694344795078433L;

	/**
	 * 
	 * 
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * 
	 * @param request
	 *            servlet request
	 * 
	 * 
	 * @param response
	 *            servlet response
	 */

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		getNSaveFile(request);
	}

	public boolean getNSaveFile(HttpServletRequest request) {

		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4096);
			String path = PropertiesUtil.workflowProperties.getString("config.path")+ "tmp/";
			if(!new File(path).exists()){
				new File(path).mkdir();
			}
			factory.setRepository(new File(path));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(10000000);
			List fileItems = upload.parseRequest(request);
			Iterator i = fileItems.iterator();
			FileItem fi = (FileItem) i.next();
			String fileName = Math.random()+"";
			File tmp = new File(path+"/"+fileName);
			
			fi.write(new File(path, fileName));

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
}
