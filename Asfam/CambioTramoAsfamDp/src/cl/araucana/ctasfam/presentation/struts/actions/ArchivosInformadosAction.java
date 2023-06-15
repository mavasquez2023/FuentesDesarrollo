package cl.araucana.ctasfam.presentation.struts.actions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ibm.as400.access.AS400;

import cl.araucana.ctasfam.presentation.struts.resources.ServiceLocatorWeb;
import cl.araucana.ctasfam.resources.util.ExplorerManagerAs400;


public class ArchivosInformadosAction extends Action {
	 public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
     throws Exception 
     {
		 
		  ActionForward forward=null;
		  String rutEmpresa = request.getParameter("rutEmpresa");
		  
		  //el rut debe ser de largo 10, si no lo es, llenamos con ceros al principio
		  for(;rutEmpresa.length()<10;rutEmpresa="0"+rutEmpresa);
		 
		  if(rutEmpresa != null && !rutEmpresa.equals(""))
		  {
			  //configuración de conexión con el as400//
			   Properties Config = new Properties();
			   Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
			  
			   String servidor = Config.getProperty("SERVIDOR");
			   String user = Config.getProperty("USER");
			   String pass = Config.getProperty("PASS");
			   String rutaTemporal = Config.getProperty("RUTAZIP");
			   String rutaAs400Archivos = Config.getProperty("RUTAAS400RESPALDO");
			  
			   AS400 system=new AS400(servidor,user,pass);
			   ExplorerManagerAs400 as400=new ExplorerManagerAs400(system);
			  //**************************************//
	
			 
			  File[] files = as400.getListaDeArchivos(rutaAs400Archivos+rutEmpresa.split("-")[0]+"/");

			  ServiceLocatorWeb service=new ServiceLocatorWeb(request);
			  
			  int zipBufferSize = Integer.parseInt(service.getApplicationProperties().getProperty("initialZipBufferSize"));
			  
			  ByteArrayOutputStream bufferedOutput = new ByteArrayOutputStream(zipBufferSize);
			  
			  byte[] buf = new byte[zipBufferSize];
			  
			  ZipOutputStream out = new ZipOutputStream(bufferedOutput);

			  
			  for(int i=0;i<files.length;i++)
			  {
				  String pathFile = files[i].getAbsolutePath();
				  String nameFile = "";
				  
				  System.out.println("pathFile: "+pathFile);
				  
				  try{
					  nameFile = pathFile.split("\\\\")[4];
				  }catch(ArrayIndexOutOfBoundsException e){
					  //si se lanza esta excepción, quiere decir que no hay archivos de esta empresa
					  request.setAttribute("mensaje", "La empresa ingresada no cuenta con archivos informados");

					  request.getRequestDispatcher("Consulta1.do").forward(request, response);
					  return null;
				  }
				  
				  as400.leerArchivoBintemp(pathFile , rutaTemporal+nameFile);
	
				  //agregamos un archivo al zip**//
					FileInputStream in = new FileInputStream(rutaTemporal+nameFile);
	
					out.putNextEntry(new ZipEntry(nameFile));
					int len;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
					out.closeEntry();
					in.close();
				  //*****************************//
					
			  }
			  
			  out.close();
			  
			  response.setContentType("application/zip");
			  response.setHeader("Content-Disposition", "inline; filename=" + rutEmpresa+".zip");
			  response.setContentLength(bufferedOutput.size());
			  OutputStream output = response.getOutputStream();
			  bufferedOutput.writeTo(output);
			  output.close();
			  bufferedOutput.close();
			  
			  system.disconnectAllServices();
			  as400.disconect();
		  }
		  else
		  {
			  forward = mapping.findForward("onError");
		  }
		  return forward;
	 }
}
