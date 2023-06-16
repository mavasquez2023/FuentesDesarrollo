package cl.araucana.ctasfam.presentation.struts.actions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import cl.araucana.ctasfam.presentation.struts.resources.ServiceLocatorWeb;
import cl.araucana.ctasfam.resources.util.ExplorerManagerAs400;

public class DescargaArchivosAction extends Action{
	
	private static final Log log = LogFactory
	.getLog(DescargaArchivosAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionMessages errors=new ActionMessages();
		String mensaje=null;
		try {
			 Properties Param = new Properties();
			 Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
			  String servidor=Param.getProperty("SERVIDOR");
			  String user=Param.getProperty("USER");
			  String pass=Param.getProperty("PASS");
			   
			ServiceLocatorWeb service = new ServiceLocatorWeb(request);
			String rutEmpresa = request.getParameter("rutEmpresa");
			String formato = request.getParameter("formato");
			String ruta = service.getRutaArchivo(rutEmpresa) + "." + formato;
			
			System.out.println(">>rutawin " + ruta);
			ExplorerManagerAs400 as400=new ExplorerManagerAs400(servidor,user,pass);
			System.out.println("AS400 Conectado...");
			File file=new File(ruta.substring(0, ruta.lastIndexOf("/")));
			if(!file.exists())
			file.mkdir();
			as400.leerArchivoBintemp(ruta,ruta);
			int zipBufferSize = Integer.parseInt(service
					.getApplicationProperties().getProperty(
							"initialZipBufferSize"));
			ByteArrayOutputStream bufferedOutput = new ByteArrayOutputStream(
					zipBufferSize);
			byte[] buf = new byte[zipBufferSize];
			ZipOutputStream out = new ZipOutputStream(bufferedOutput);
			try {
				FileInputStream in = new FileInputStream(ruta);
				String temp = ruta.substring(23);
				out.putNextEntry(new ZipEntry(temp));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			} catch (FileNotFoundException e) {
				mensaje="La sesión expiró o el sistema encontro una excepción";
				errors.add("name", new ActionMessage("id"));
				log.error("Error: archivo no encontrado, "
						+ e.getLocalizedMessage(), e);
			}
			out.close();
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition", "inline; filename="
					+ service.getApplicationProperties().getProperty(
							"zippedDocPrefix") + "_" + rutEmpresa + ".zip");
			response.setContentLength(bufferedOutput.size());
			OutputStream output = response.getOutputStream();
			bufferedOutput.writeTo(output);
			output.close();
			bufferedOutput.close();
			as400.disconect();
			as400.estatusAS400();
		} catch (IOException e) {
			mensaje="Error en la descarga de archivos.";
			log.error("Error: " + e.getLocalizedMessage(), e);
			request.setAttribute("mensaje", mensaje);
			return mapping.findForward("onError");
		} catch (Exception e) {
			mensaje="Error en la descarga de archivos.";
			errors.add("name", new ActionMessage("id"));
			mensaje="Error en la descarga de archivos.";
			log.error("Error: " + e.getLocalizedMessage(), e);
			request.setAttribute("mensaje", mensaje);
			return mapping.findForward("onError");
		}
		return null;
	}

}
