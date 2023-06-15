package cl.araucana.ctasfam.presentation.struts.actions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import com.ibm.as400.access.AS400;

import cl.araucana.ctasfam.business.to.AceptaPropuestaForm;
import cl.araucana.ctasfam.business.to.AfiliadosErrorTO;
import cl.araucana.ctasfam.business.to.ArchivosTO;
import cl.araucana.ctasfam.business.to.CargaArchivoTO;
import cl.araucana.ctasfam.business.to.EstadoTO;
import cl.araucana.ctasfam.business.to.FlujoTO;
import cl.araucana.ctasfam.business.to.RentaproTO;
import cl.araucana.ctasfam.business.to.VerificadorTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.presentation.struts.vo.Empresa;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.resources.util.ExplorerManagerAs400;
import cl.araucana.ctasfam.resources.util.Utils;

public class DeclaracionJuradaAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward=new ActionForward();
		ActionMessages errors=new ActionMessages();
		
		 AceptaPropuestaForm form1=(AceptaPropuestaForm)form;
		 
		 String options=form1.getOpcion();
		 String ruts=form1.getRutt();
		 String estado=form1.getEtapa();
		 String temp1[]=ruts.split(";");
		 String rute=null;
		 String rut=null;
		 Vector v=new Vector();
		 AraucanaJdbcDao dao=new AraucanaJdbcDao();
	 
		 EstadoTO oestado=new EstadoTO();
		 Empresa empresa=new Empresa();
		 FlujoTO flujo=new FlujoTO();
		 String mensaje=null;
		 List lista=null;
		  lista=new ArrayList();
		  List listaerror=new ArrayList();
		  List listaerrors=new ArrayList();
		  List listaarchivos=new ArrayList();
		  List listafiles=new ArrayList();
		  ArchivosTO oarchivo=new ArchivosTO();
		  AfiliadosErrorTO oerror=new AfiliadosErrorTO();
		  List error=new ArrayList();
		  List well=new ArrayList();
		  VerificadorTO ocase= new VerificadorTO();
		  RentaproTO renta=new RentaproTO();
			 
			 String descripcion=null;
		   
		  Utils util=new Utils();
		 
		  String extension=null;

		
		 Properties Param = new Properties();
		 Param.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
		  Date hoy=new Date();
       	  SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd", new Locale("cl"));
       	  SimpleDateFormat sdf2=new SimpleDateFormat("HHmmss", new Locale("cl"));
       	  String hora=sdf2.format(hoy);
       	  String fecha=sdf.format(hoy);
       	  String ext=fecha+hora;
       	  Encargado enc1 =new Encargado();
       	  enc1=(Encargado)request.getSession().getAttribute("edocs_encargado");
       	  String rutenc=String.valueOf(enc1.getRut());
      	  String rutaArchivos=Param.getProperty("RESPALDO");
       	  String rutaZip=Param.getProperty("DESCOMPRES");
       	  String rutaas400respaldo=Param.getProperty("RUTAAS400RESPALDO");
          String periodo=Param.getProperty("PERIODO");
          String servidor=Param.getProperty("SERVIDOR");
          String user=Param.getProperty("USER");
          String pass=Param.getProperty("PASS");
          String nombrearchivo=null;
		
		 
		
		
		 
		try{
			
			
			 System.out.println("estado= " + estado);

			
             FormFile archivo=(FormFile)request.getSession().getAttribute("file");
             
         if(archivo!=null){    
        	 
        	 File file= new File(archivo.getFileName());
             String pathZip=null;
       	
       	  
       	  if(archivo.getFileSize()==0)
       	  {
       		  mensaje=dao.getValores("003","ERROR")[0];
       		  errors.add("name", new ActionMessage("id"));
       	  }
       
       	  
       	  extension=util.extencion(file);
       	  
       	  
       	
       	  
       	 
       		  
       		 
       		  
       		  
       		  
       		 if(!extension.equalsIgnoreCase("zip")){
       			 
       			 
       		  
       			 String tipo="NORMAL";
       		 
       			 System.out.println("Begin process " + sdf2.format(new Date()));
       			 
       			request.getSession().setAttribute("edocs_encargado", enc1);
       			 
       		listaerror=util.procesaArchivosgrava(extension, archivo,rutaArchivos,ext,tipo , request); 
       		 for(int i=0;i<listaerror.size()-1;i++)
       		listaerrors.add(listaerror.get(i) + "/" + archivo.getFileName() + "/" + "9");
       		 
       		 ocase.setTipo("nozip");
       		 ocase.setNombre(archivo.getFileName());
       		 
       		
       		 }
       		 
       	 
       		 else{
       	   
       		  ocase.setTipo("zip");
       		  ocase.setNombre(archivo.getFileName());
       		  
       		  request.getSession().setAttribute("edocs_encargado", enc1);
       		 
       		  File filer=util.creaArchivoas400(rutaArchivos, archivo, ext);
       		  System.out.println(filer.getAbsolutePath());
       		  File filez=util.creaArchivoas400(rutaZip, archivo, ext);
       		  System.out.println(filez.getAbsolutePath());
       		  if(util.unZip(filer.getAbsolutePath(), rutaZip + ext + "/"))
       		{ 	  
       			  
       		  File zip=new File(filez.getAbsolutePath());
       		  if(zip.exists())
       		  zip.delete();
       		  
       		 listaerrors=util.procesaFicherosgrava(rutaZip+"\\"+ext , request);
       		 
       		
       	  }
       		  }
       	 
       			 
       		
       	
			 
			 System.out.println("filesize " + archivo.getFileSize());
			 System.out.println("filename " + archivo.getFileName());
			 
				 
			 
         }
			 
			  for(int j=0;j<temp1.length;j++){
				    rute=temp1[j];
				    System.out.println(rute);
				    String temp[]=rute.split("-");
				    rut="";
					for(int i=0;i<temp[0].length();i++)
					{
						if(temp[0].charAt(i)!='.'&&temp[0].charAt(i)!=';')
						{
							rut+=temp[0].charAt(i);
						}
						
					}
					 
					System.out.println("aqui");
					
					 
					while(rut.length()<8){
						rut="0".concat(rut);
					}
					v.add(rut);
				    }
				
			  for(int i=0;i<v.size();i++)
			  {
				  System.out.println(v.get(i).toString());
				  
				  flujo.setPeriodo(periodo);
				  flujo.setRutempresa(v.get(i).toString());
				  flujo.setRutencargado(rutenc);
				  flujo.setEtapa("4");
				  flujo.setOperacion("ACEPTA DECLARACION");
				  flujo.setNombrearchivo("");
				  flujo.setCantregistros(0);
				  flujo.setISAJKM94("CTADMIN");
				  flujo.setISAJKM92("");
				  dao.InsertaBitacora(flujo);
				  flujo.setISAJKM92("CTADMIN");
				  
				  dao.updateFlujo(flujo);
				  if(estado.equalsIgnoreCase("1")){
					  
				  dao.updateEstado(periodo,v.get(i).toString());
				  
				  }
				  if(archivo==null)
				  {
					  nombrearchivo="";
					  extension="";
				  }
				  else{
				   nombrearchivo=archivo.getFileName().substring(0, archivo.getFileName().lastIndexOf("."));
				  }
				  renta.setArchivo(nombrearchivo);
		       	  renta.setCantarchivos("1");
		       	  renta.setRutEmpresa(v.get(i).toString());
		       	  renta.setRutencargado(rutenc);
		       	  char dvempresa=util.getDigito(v.get(i).toString());
		       	  char dvencargado=util.getDigito(rutenc);
		       	  renta.setDvrutempresa(String.valueOf(dvempresa));
		       	  renta.setDvencargado(String.valueOf(dvencargado));
		       	  renta.setEtapa("3");
		       	  renta.setExtencion(extension);
		       	  renta.setCantreg("0");
		       	  renta.setMail1("");
		       	  renta.setMail2("");
		       	  renta.setMail3("");
		       	   
		       	   
		       	  if(dao.InsertaRenta(renta)){
		       		  System.out.println("insert Successful");
		       	  }
			  }
			 
			
			  
			  
		}catch(Exception ex)
		{
			 mensaje="La sesión expiró o el sistema encontro una excepción";
			errors.add("name", new ActionMessage("id"));
			ex.printStackTrace();
		}
		
		if(!errors.isEmpty()){
			
			request.setAttribute("mensaje", mensaje);
			forward=mapping.findForward("onError");
			
		}
		else
		{
			
			forward=mapping.findForward("declaracion");
		}
		
		return forward;
		
	}

}
