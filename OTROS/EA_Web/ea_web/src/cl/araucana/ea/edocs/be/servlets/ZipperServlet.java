package cl.araucana.ea.edocs.be.servlets;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.araucana.common.Profile;
import cl.araucana.ea.edocs.be.Empresa;
import cl.araucana.ea.edocs.be.Encargado;
import cl.araucana.ea.edocs.util.Padder;

public class ZipperServlet extends HttpServlet implements Servlet {
	
	private ServletContext servletContext;
	private String documentBaseDir;
	private String zippedDocPrefix;
	private String initialZipBufferSizeParam;
	private String separadorCarpetas;
	
	public void init() throws ServletException {
		
		servletContext = getServletContext();
		
		documentBaseDir =
				servletContext.getInitParameter("edocs.be.documentBaseDir");
				
		separadorCarpetas =
				servletContext.getInitParameter("edocs.be.separadorCarpetas");
		
		zippedDocPrefix =
				servletContext.getInitParameter("edocs.be.zippedDocPrefix");
		
		initialZipBufferSizeParam =
				servletContext.getInitParameter("edocs.be.initialZipBufferSize");
		
		System.out.println("init()");
		System.out.println("    documentBaseDir      = " + documentBaseDir);
		System.out.println("    zippedDocPrefix      = " + zippedDocPrefix);
		System.out.println("    separadorCarpetas      = " + separadorCarpetas);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			
		try {
			processRequest(request, response);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ServletException e) {
			System.out.println(e.getMessage());
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			
		try {
			processRequest(request, response);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ServletException e) {
			System.out.println(e.getMessage());
		}
	}

	public void processRequest(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
		
		Encargado encargado = null;
		boolean invalidatedSession = false;
		HttpSession session = request.getSession();
		Principal principal = (Principal) session.getAttribute("userPrincipal");
		
		/*
		 * Checks if user principal is authenticated.
		 */
		if (principal == null || principal.getName() == null) {
			session.invalidate();
	
			invalidatedSession = true;
		} else {
			encargado =	(Encargado) session.getAttribute("edocs_encargado");

			if (encargado == null) {
				Profile profile =
						(Profile) session.getAttribute("ea_user_profile");
						
				if (profile != null) {
					String rut = (String) profile.getId();

					String fullName =
							(String) profile.getAttribute("nombreCompleto");
				
					Collection empresas =
							(Collection) profile.getAttribute("empresas");

					encargado = new Encargado(rut, fullName, empresas);
					session.setAttribute("edocs_encargado", encargado);
				} else {
					session.invalidate();

					invalidatedSession = true;
				}			
			}
		}

		if (invalidatedSession) {
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/logon.jsp");

			dispatcher.forward(request, response);
			
			return;
		}

		try {
			
			Collection enterprises = encargado.getEmpresas();

			Iterator iterator = encargado.getEmpresas().iterator();
			String[] source = new String[encargado.getEmpresas().size() * 3];

			// index para source
			int k = 0;
			
			// index para empresa
			int p = 0; 
			
			
			while (iterator.hasNext()) {
				
				Empresa empresa = (Empresa) iterator.next();
				p++;
				String rutEmpresa = "" + empresa.getRut();
				rutEmpresa = Padder.pad(rutEmpresa, 8, '0', true);
				
				if (empresa.getFlag() != 0 && getOption(request, "BONO", p)){

					source[k] = documentBaseDir + separadorCarpetas + rutEmpresa + separadorCarpetas + rutEmpresa + ".txt";
					//System.out.println("ZipperServlet: Se buscará archivo " + source[k]);
					k++;
					source[k] = documentBaseDir + separadorCarpetas + rutEmpresa + separadorCarpetas + rutEmpresa + ".CSV";
					//System.out.println("ZipperServlet: Se buscará archivo " + source[k]);
					k++;
					source[k] = documentBaseDir + separadorCarpetas + rutEmpresa + separadorCarpetas + rutEmpresa + ".xls";
					//System.out.println("ZipperServlet: Se buscará archivo " + source[k]);	
					k++;
				}
			}

			// Variables Zip
			int zipBufferSize = Integer.parseInt(initialZipBufferSizeParam);

		
			// These are the files to include in the ZIP file
			ByteArrayOutputStream bufferedOutput = new ByteArrayOutputStream(zipBufferSize);
			byte[] buf = new byte[zipBufferSize];

			ZipOutputStream out = new ZipOutputStream(bufferedOutput);

			// Compress the files
			for (int i=0; i<source.length; i++) {
				try{
					
					if (source[i] != null){
						FileInputStream in = new FileInputStream(source[i]);
						//Cambiamos el nombre con que debe aparecer en el .zip
						String temp = source[i].substring(6);
						// Add ZIP entry to output stream.
						out.putNextEntry(new ZipEntry(temp));
	
						// Transfer bytes from the file to the ZIP file
						int len;
						while ((len = in.read(buf)) > 0) {
							out.write(buf, 0, len);
						}
	
						// Complete the entry
						out.closeEntry();
						in.close();
					}

				}catch(FileNotFoundException e){
					e.getMessage();
				}
			}

			// Complete the ZIP file
			out.close();


			//	Send zipped data.
			String zipFileName =  zippedDocPrefix + "_" + encargado.getRut() + ".zip";		

			response.setContentType("application/zip");
			response.setHeader(
					 "Content-Disposition", "inline; filename=" + zipFileName);		

			response.setContentLength(bufferedOutput.size());

			OutputStream output = response.getOutputStream();

			bufferedOutput.writeTo(output);
			output.close();
			bufferedOutput.close();

		} catch (IOException e) {
			System.err.println("Error al generar el zip: " + e.getMessage());
		}
	}
	
	public boolean getOption(HttpServletRequest request, String optionName,
			int index) {
				
		String optionValue = request.getParameter(optionName + index);
		
		return optionValue != null && optionValue.equals("ALL");
	}
}
