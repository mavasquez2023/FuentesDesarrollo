package cl.araucana.ctasfam.presentation.struts.actions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
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

import com.ibm.as400.access.AS400;

import cl.araucana.ctasfam.business.to.FlujoTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.presentation.struts.resources.ServiceLocatorWeb;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.presentation.struts.vo.PropuestasZipForm;
import cl.araucana.ctasfam.resources.util.ExplorerManagerAs400;
import cl.araucana.ctasfam.resources.util.Utils;

public class DescargaPropuestasZipAction extends Action{
	private static final Log log = LogFactory
	.getLog(DescargaPropuestasZipAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	 
		ActionMessages errors=new ActionMessages();
		ActionForward forward=new ActionForward();
		ServiceLocatorWeb service=new ServiceLocatorWeb(request);
		String mensaje="";
		 
		  Properties Config = new Properties();
		  Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		
		PropuestasZipForm opropuesta=(PropuestasZipForm)form;
		String servidor=Config.getProperty("SERVIDOR");
		String user=Config.getProperty("USER");
		String pass=Config.getProperty("PASS");
		String rutaraiz=Config.getProperty("RUTAZIP");
		String rutaAs400=Config.getProperty("RUTAAS400");
		AS400 system=new AS400(servidor,user,pass);
		ExplorerManagerAs400 as400=new ExplorerManagerAs400(system);
		System.out.println("AS400 Conectado...");
		Encargado enc=(Encargado)request.getSession().getAttribute("edocs_encargado");
		 Properties Param = new Properties();
		 Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		String periodo=Param.getProperty("PERIODO");
		AraucanaJdbcDao dao=new AraucanaJdbcDao();
		String encargado=String.valueOf(enc.getRut());
		
		try{
			
			
		
		String propuesta=opropuesta.getRutt();
		
		String ruts[]=propuesta.split(";");
		String rut="";
		Vector source=new Vector();
		String rutEmpresa="";
		File rutacarpeta=null;
		File rutfile=null;
		String rute="";
		 
		 
	    Vector v=new Vector();
	    
	    for(int j=0;j<ruts.length;j++){
	    rute=ruts[j];
	    String temp[]=rute.split("-");
	    System.out.println(temp[0]);
	    rut="";
		for(int i=0;i<temp[0].length();i++)
		{
			if(temp[0].charAt(i)!='.'&&temp[0].charAt(i)!=';')
			{
				rut+=temp[0].charAt(i);
			}
			
		}
		System.out.println(rut);
		 
		while(rut.length()<8)
		{
			rut="0".concat(rut);
		}
		v.add(rut);
	    }
	    
	    FlujoTO flujo=new FlujoTO();
	    System.out.println(">>" + v.size());
		for(int i=0;i<v.size();i++){
			
			rutEmpresa=v.get(i).toString();
			System.out.println(rutEmpresa);
			String ruta=rutaAs400 + rutEmpresa;
			String rutatxt=ruta + "/" + rutEmpresa + ".txt";
			String rutacsv=ruta + "/" + rutEmpresa + ".csv";
			String rutaxls=ruta + "/" + rutEmpresa + ".xls";
			
			flujo.setRutempresa(v.get(i).toString());
			  flujo.setPeriodo(periodo);
			  flujo.setEtapa("1");
			  flujo.setISAJKM94("");
			  flujo.setISAJKM92("CTADMIN");
			  flujo.setRutencargado(String.valueOf(enc.getRut()));
			  dao.InsertaFlujo(flujo);
			  flujo.setCantregistros(0); //revisar 
			  flujo.setOperacion("DESCARGA ARCHIVO");
			  flujo.setISAJKM92("");
			  flujo.setISAJKM94("CTADMIN");
			  flujo.setNombrearchivo("");
			  dao.InsertaBitacora(flujo);
				 
				
			  
			 
			 
			 
			 rutacarpeta=new File(rutaraiz);
			 rutfile=new File(rutaraiz + rutEmpresa);
			 
			 
			 
			if(!rutacarpeta.exists()){
				rutacarpeta.mkdir();
				
			}
			if(!rutfile.exists()){
				rutfile.mkdir();
				
			}
		 
			
			 
				 
			
		
		 
		 
		if(as400.existFile(rutacsv))
				{
		 
			as400.leerArchivoBintemp(rutacsv, rutfile.getAbsolutePath()  + "/" + rutEmpresa +  ".csv");
			source.add(rutfile.getAbsolutePath()  + "/" +  rutEmpresa +  ".csv");
				}
		
		if(as400.existFile(rutatxt))
		{
		 
	      as400.leerArchivoBintemp(rutatxt,  rutfile.getAbsolutePath() + "/" + rutEmpresa +  ".txt");
	       source.add(rutfile.getAbsolutePath() + "/" + rutEmpresa +  ".txt");
		}
		
		if(as400.existFile(rutaxls))
		{
			 
	     as400.leerArchivoBintemp(rutaxls, rutfile.getAbsolutePath()  + "/" + rutEmpresa +  ".xls");
	      source.add(rutfile.getAbsolutePath()  + "/" + rutEmpresa +  ".xls");
		}
		

		}
		
		
		 
		//if(rutacarpeta.exists()){
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
		response.setHeader("Content-Disposition", "inline; filename=" + "LaAraucana_" + encargado + ".zip");
		response.setContentLength(bufferedOutput.size());
		OutputStream output = response.getOutputStream();
		bufferedOutput.writeTo(output);
		output.close();
		bufferedOutput.close();
	 
		as400.disconect();
		as400.estatusAS400();
		 
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
}
