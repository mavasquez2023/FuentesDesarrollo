package cl.jfactory.planillas.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cl.jfactory.planillas.service.helper.GeneradorNominasHelper;
import cl.lib.export.txt.impl.GenerarTXT;
import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.core.request.tipos.TiposMetodoHttp;
import cl.liv.export.txt.util.PropertiesTXTUtil;

public class PlanillasServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9061711754376149030L;

	Logger log = Logger.getLogger(PlanillasServlet.class);
	public static String ID_REQUEST_EXPORTAR_TXT = "exportar_txt";
	public static String ID_REQUEST_GENERAR_NOMINA = "generar_nomina";
	public static String ID_REQUEST_GENERAR_TODAS = "generar_todas";
	public static String ID_REQUEST_DESCARGAR_NOMINA = "descargar_nomina";
	public static String ID_REQUEST_DESCARGAR_CONFIGURACION = "descargar_configuracion";
	public static String ID_REQUEST_DESCARGAR_ARCHIVO = "descargar_archivo";
	public static String ID_REQUEST_MOVER = "mover";
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPeticion(request, response, TiposMetodoHttp.GET);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPeticion(request, response, TiposMetodoHttp.POST);
	}
	public void procesarPeticion(HttpServletRequest request, HttpServletResponse response, int metodoEntrada) throws ServletException, IOException  {
	
		String idReq = request.getParameter("req");
		String tipo = request.getParameter("tipo");
		String reporte = request.getParameter("reporte");
		String params = request.getParameter("params");

		if(ID_REQUEST_EXPORTAR_TXT.equals( idReq )){
			
			String pathArchivoGenerado = null;
			
			if(tipo.equals("txt")){
				
				pathArchivoGenerado = new GenerarTXT().generar(reporte, params,"","", null);
				if (pathArchivoGenerado != null) {

					FileInputStream fileToDownload = new FileInputStream(
							pathArchivoGenerado);
					ServletOutputStream out = response.getOutputStream();
					response.setHeader(
							"Content-Disposition",
							"attachment; filename="
									+ pathArchivoGenerado.substring(pathArchivoGenerado
											.lastIndexOf("/")));
					response.setContentLength(fileToDownload.available());
					int c;
					while ((c = fileToDownload.read()) != -1) {
						out.write(c);
					}
					out.flush();
					out.close();
					fileToDownload.close();
				}
				else{
					PrintWriter out;
					try {
						out = response.getWriter();
						out.print("HA OCURRIDO UN ERROR EN LA GENERACION ");
						out.flush();
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
				
			
			
		}
		
		else if(ID_REQUEST_GENERAR_NOMINA.equals( idReq )){
				if(!GeneradorNominasHelper.generar(reporte, params)){
					throw new ServletException("Error en generacion ");
				}
			
		}

		else if(ID_REQUEST_GENERAR_TODAS.equals( idReq )){
				if(!GeneradorNominasHelper.generarTodas( params)){
					throw new ServletException("Error en generacion ");
				}
			
		}
		
		else if(ID_REQUEST_DESCARGAR_NOMINA.equals( idReq )){
			String path = request.getParameter("periodo")+"/"+request.getParameter("entidad")+"/"+request.getParameter("archivo");
			String pathCompleto = PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+path;
			FileInputStream fileToDownload = new FileInputStream(
					pathCompleto);
			ServletOutputStream out = response.getOutputStream();
			response.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ request.getParameter("archivo") );
			response.setContentLength(fileToDownload.available());
			int c;
			while ((c = fileToDownload.read()) != -1) {
				out.write(c);
			}
			out.flush();
			out.close();
			fileToDownload.close();
			
		}
		
		else if(ID_REQUEST_DESCARGAR_CONFIGURACION.equals( idReq )){
			String pathCompleto = PropertiesTXTUtil.getProperty("export.path.resources.txt")+"/txts/"+reporte+"/conf.xml";
			FileInputStream fileToDownload = new FileInputStream(
					pathCompleto);
			ServletOutputStream out = response.getOutputStream();
			response.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ reporte+".xml" );
			response.setContentLength(fileToDownload.available());
			int c;
			while ((c = fileToDownload.read()) != -1) {
				out.write(c);
			}
			out.flush();
			out.close();
			fileToDownload.close();
			
		}

		else if(ID_REQUEST_DESCARGAR_ARCHIVO.equals( idReq )){
			String pathCompleto = params;
			FileInputStream fileToDownload = new FileInputStream(
					pathCompleto);
			ServletOutputStream out = response.getOutputStream();
			response.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ pathCompleto );
			response.setContentLength(fileToDownload.available());
			int c;
			while ((c = fileToDownload.read()) != -1) {
				out.write(c);
			}
			out.flush();
			out.close();
			fileToDownload.close();
			
		}

		else if(ID_REQUEST_MOVER.equals( idReq )){
			String to = request.getParameter("to");
			String from = PropertiesUtil.workflowProperties.getString("config.path")+ "tmp/" + request.getParameter("from");
			if(new File(to).exists()){
				new File(to).delete();
			}
			
			ManejoArchivoTXT.copyFileUsingChannel(new File( from), new File(to));
			new File(from).delete();
		}
		
	}

	

	
}
