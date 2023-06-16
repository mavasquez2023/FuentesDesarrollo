package cl.araucana.ctasfam.presentation.struts.actions;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.ibm.as400.access.AS400;

import cl.araucana.ctasfam.business.to.FlujoTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.integration.jdbc.dao.impl.Mejoras2016DaoImpl;
import cl.araucana.ctasfam.presentation.struts.resources.ServiceLocatorWeb;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.presentation.struts.vo.PropuestasZipForm;
import cl.araucana.ctasfam.resources.util.Parametros;
import cl.araucana.ctasfam.resources.util.Utils;

public class DescargaNoDeclaradosZipAction extends Action{
	 
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionMessages errors=new ActionMessages();
		ActionForward forward=new ActionForward();
		ServiceLocatorWeb service=new ServiceLocatorWeb(request);
		String mensaje="";

		String rutaraiz=Parametros.getInstance().getParam().getRutaZip();
		Encargado enc=(Encargado)request.getSession().getAttribute("edocs_encargado");
		String periodo= Parametros.getInstance().getParam().getPeriodoProceso();
		String rutEmpresa= (String)request.getParameter("rutempnd");
		
		Mejoras2016DaoImpl dao=new Mejoras2016DaoImpl();
		String encargado=String.valueOf(enc.getRut());
		Utils util=new Utils();

		try{
	
			Vector<String> source=new Vector<String>();
			File rutacarpeta=null;

			FlujoTO flujo=new FlujoTO();
			rutacarpeta=new File(rutaraiz+encargado);
			if(!rutacarpeta.exists()){
				rutacarpeta.mkdir();
			}
			String periodo_consulta= periodo.substring(0, 4) + "00";
			Vector<String> data= dao.getTrabajadoresNoDeclarados(Integer.parseInt(periodo_consulta), Integer.parseInt(rutEmpresa));
			String rutacsv=rutaraiz + encargado + "/" + rutEmpresa + ".csv";
			
			if(crearArchivoTexto(rutacsv, data)){
				source.add(rutacsv);
			}

			//Generación del ZIP
			int zipBufferSize = Integer.parseInt(service
					.getApplicationProperties().getProperty(
							"initialZipBufferSize"));
			ByteArrayOutputStream bufferedOutput = new ByteArrayOutputStream(
					zipBufferSize);
			byte[] buf = new byte[zipBufferSize];
			ZipOutputStream out = new ZipOutputStream(bufferedOutput);
			for (int i = 0; i < source.size(); i++) {
				if (source.get(i).toString() != null) {
					try {
						FileInputStream in = new FileInputStream(source.get(i).toString());
						System.out.println(source.get(i).toString());
						String temp = source.get(i).toString().substring(rutaraiz.length());
						out.putNextEntry(new ZipEntry(temp));
						int len;
						while ((len = in.read(buf)) > 0) {
							out.write(buf, 0, len);
						}
						out.closeEntry();
						in.close();
					} catch (Exception e) {
						e.printStackTrace();

					}
				}
			}
			out.close();

			response.setContentType("application/zip");
			response.setHeader("Content-Disposition", "inline; filename=" + "LaAraucana_ND_" + encargado + ".zip");
			response.setContentLength(bufferedOutput.size());
			OutputStream output = response.getOutputStream();
			bufferedOutput.writeTo(output);
			output.close();
			bufferedOutput.close();

		}catch(Exception ex)
		{
			ex.printStackTrace();
			mensaje="La sesión expiró o el sistema encontro una excepción";
			errors.add("name", new ActionMessage("id"));
		}
		

		if(!errors.isEmpty()){

			request.setAttribute("mensaje", mensaje);
			forward=mapping.findForward("onError");
		}
		else{

			//forward=mapping.findForward("onSuccess");
			forward=null;
		}

		return forward;
	}
	
	public  boolean crearArchivoTexto(String pathfile, Vector texto) throws IOException{
		BufferedWriter out;
		try{
			out = new BufferedWriter(new FileWriter(pathfile));
			int numlineas= texto.size();
			for (int i=0; i< numlineas; i++){
				out.write(texto.elementAt(i).toString());
				if (i<numlineas-1){
					out.write("\n");
				}
			}
			out.flush();
			out.close();
			return true;
		  } catch(Exception e) {
			System.out.println("CAI en crearArchivo()");
			e.printStackTrace();
			//throw new IOException();
			return false;
		}
	}


}
