package cl.araucana.ctasfam.presentation.struts.actions;

 
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

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
import cl.araucana.ctasfam.business.to.FlujoTO;
import cl.araucana.ctasfam.business.to.VerificadorTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.resources.util.ExplorerManagerAs400;
import cl.araucana.ctasfam.resources.util.Utils;

public class CargarAction extends Action{
	
	 //--alexis advise 15-06-2012--//
	  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
     throws Exception {

 ActionMessages errors = new ActionMessages();
 ActionForward forward = new ActionForward(); // return value
 String mensaje=null;
 AraucanaJdbcDao dao=new AraucanaJdbcDao();
 FlujoTO flujo=new FlujoTO();
 
 Properties Config = new Properties();
 Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
 String periodo=Config.getProperty("PERIODO");
 String proceso=Config.getProperty("PROCESO");
 AceptaPropuestaForm acepta=new AceptaPropuestaForm();
 acepta.setProceso(proceso);
 
 List listcomun=new ArrayList();
 List listaerror=new ArrayList();
 List listaerrors=new ArrayList();
 List listaerrorColum=new ArrayList();  
 List listafiles=new ArrayList();
 ArchivosTO oarchivo=new ArchivosTO();
 AfiliadosErrorTO oerror=new AfiliadosErrorTO();
 List error=new ArrayList();
 List well=new ArrayList();
 VerificadorTO ocase= new VerificadorTO();
	 
	 String descripcion=null;
  
 Utils util=new Utils();
 CargaArchivoTO Me=(CargaArchivoTO)form;
 FormFile archivo=Me.getArchivo();
 String extension=null;
 File file= new File(archivo.getFileName());
 extension=util.extencion(file);
  
 
  

 try{ 
 	
	SimpleDateFormat sdf5 = new SimpleDateFormat("HH:mm:ss");
   Date r=new Date();
	System.out.println("begin " + sdf5.format(r));   
	   
	String rutestado=(String)request.getSession().getAttribute("rutestado");  
	System.out.println(">>rutestado "+ rutestado);
	request.getSession().setAttribute("file", archivo);
	if(rutestado!=null){
   if(!rutestado.equalsIgnoreCase(archivo.getFileName().substring(0,archivo.getFileName().lastIndexOf(".")))&&!extension.equalsIgnoreCase("zip"))
   {
   	  mensaje="El nombre del archivo que esta subiendo no coincide con el RUT de la empresa que ha seleccionado, porfavor intente de nuevo.";
		  errors.add("name", new ActionMessage("id"));
   }else{
	  
	   
	  Date hoy=new Date();
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd", new Locale("cl"));
	  SimpleDateFormat sdf2=new SimpleDateFormat("HHmmss", new Locale("cl"));
	  String hora=sdf2.format(hoy);
	  String fecha=sdf.format(hoy);
	  String ext=fecha + "_" + hora;
	  Encargado enc =new Encargado();
	  enc=(Encargado)request.getSession().getAttribute("edocs_encargado");
	  String rutenc=String.valueOf(enc.getRut());
	  if(archivo.getFileSize()==0)
	  {
		  mensaje=dao.getValores("003","ERROR")[0];
		  errors.add("name", new ActionMessage("id"));
	  }
	
	  String rutaArchivos=Config.getProperty("RESPALDO");
	  String rutaZip=Config.getProperty("DESCOMPRES");
	  
	  
	 
	  
	  
	
	  
	 
		  
		  
		  
		  
		 if(!extension.equalsIgnoreCase("zip")){
		
			 String tipo="NORMAL";
		 
			 System.out.println("Begin process " + sdf2.format(new Date()));
			 
			 
		listcomun=util.procesaArchivos(extension, archivo,rutaArchivos,ext,tipo,request); 
		
		for (int i = 0; i < listcomun.size(); i++) {
			
			if ((i%2) == 0) {
				listaerror.add(listcomun.get(i));	
			}
			
		}
		
       for (int i = 0; i < listcomun.size(); i++) {
			
			if ((i%2) != 0) {
				listaerrorColum.add(listcomun.get(i));	
			}
			
		 }
		
		
		 for(int i=0;i<listaerror.size()-1;i++)
		listaerrors.add(listaerror.get(i) + "/" + archivo.getFileName() + "/" + "9");
		 
		 ocase.setTipo("nozip");
		 ocase.setNombre(archivo.getFileName());
		 
		 
		 
		 
		 }
		 
	 
		 else{
	   
					  ocase.setTipo("zip");
					  ocase.setNombre(archivo.getFileName());
					  
					   
					 
					  File filer=util.creaArchivo(rutaArchivos, archivo, ext);
					 
					  System.out.println(filer.getAbsolutePath());
					  File filez=util.creaArchivo(rutaZip, archivo, ext);
					  
					  System.out.println(filez.getAbsolutePath());
					 if(util.unZip(filer.getAbsolutePath(), rutaZip + ext + "/"))
					{ 	  
						  
					  File zip=new File(filez.getAbsolutePath());
					  if(zip.exists())
					  zip.delete();
					  
					 listaerrors=util.procesaFicheros(rutaZip+"\\"+ext, request);
					 
					 
					 
					
				  }
		  }
	 
			
		 
		
		 
		 String d[]=new String[11];
		 
		 d[0]=dao.getValores("004","ERROR")[0];
		 d[1]=dao.getValores("005","ERROR")[0];
		 d[2]=dao.getValores("006","ERROR")[0];
		 d[3]=dao.getValores("007","ERROR")[0];
		 d[4]=dao.getValores("008","ERROR")[0];
		 d[5]=dao.getValores("009","ERROR")[0];
		 d[6]=dao.getValores("010","ERROR")[0];
		 d[7]=dao.getValores("011","ERROR")[0];
		 d[8]=dao.getValores("012","ERROR")[0];
		 d[9]=dao.getValores("013","ERROR")[0];
		 d[10]=dao.getValores("014","ERROR")[0];
 
	   for(int j=0;j<listaerrors.size();j++)
	   {
		   String v[]=listaerrors.get(j).toString().split("/");
		   if(v[0].equals("file"))
		   {
			   well.add(v[1]);
			   
		   }
	   }
	  
	     if(!well.isEmpty()){
	    	 
	    	 ocase.setValor2("2");
	     }
	   
		 for(int j=0;j<well.size();j++){
			 oarchivo=new ArchivosTO();
			 System.out.println("well " + well.get(j).toString());
		  oarchivo.setNombre(well.get(j).toString()); 
		 listafiles.add(oarchivo);
		 
		  }
		
		 
		 ArchivosTO arc=new ArchivosTO();
		 for(int i=0;i<listafiles.size();i++)
		 {
			 arc=(ArchivosTO)listafiles.get(i);
			 System.out.println(arc.getNombre());
		 } 
		 

		 
		 
		  if(listaerrors.size()>0&&listafiles.size()==0){
			  
			  oarchivo.setNombre(archivo.getFileName());
			   
		 for(int i=0;i<listaerrors.size();i++){
			 
			 
			 
			 
			 if(!listaerrors.get(i).toString().split("/")[0].equalsIgnoreCase("error")&&
					 !listaerrors.get(i).toString().split("/")[0].equalsIgnoreCase("file")){
			 
			 String temp1[]=listaerrors.get(i).toString().split(";");
			 
			 
			 String param[]=temp1[1].split("/");
			 
			// for(int k=0;k<param.length;k++)
			//	arc[k]=param[4];
			 int errores=0;
			 String temp2[]=temp1[0].split("/");
			 int largo=temp2.length;
			 
			
			 
			 for(int j=0;j<largo;j++){
				 if(temp2[j].length()==0||temp2[j].trim().equals(""))
				 {
					 errores=0;
				 }
				 else{
				  
				 errores=Integer.parseInt(temp2[j]);
				 }
				 switch(errores){
				 case 4: descripcion=d[0];break;
				 case 5: descripcion=d[1];break;
				 case 6: descripcion=d[2];break;
				 case 7: descripcion=d[3];break;
				 case 8: descripcion=d[4];break;
				 case 9: descripcion=d[5];break;
				 case 10:descripcion=d[6];break;
				 case 11:descripcion=d[7];break;
				 case 12:descripcion=d[8];break;
				 case 13:descripcion=d[9];break;
				 case 14:descripcion=d[10];break;
				 }
				 
			 
		 	 if(largo>1&&param[4].equals("0")&&j>0)
		 	 { param[4]="1";
		 	 }
		 	if(i>0&&!extension.equalsIgnoreCase("zip"))
		 	{
		 		param[4]="1";
		 	}
			  
		 
			 oerror=new AfiliadosErrorTO();
			 oerror.setDescripcionerror(descripcion);
				if(errores==13)
			 	{
					 oerror.setRuttrabajador("");
					 oerror.setNumerolinea("0");
					 oerror.setNumeroColumna("0");
			 	 
			 	}else
			 	{
			 String columnas = listaerrorColum.get(j).toString().replace("[", "");	
			 columnas = columnas.replace("]", "");
			 oerror.setRuttrabajador(Integer.parseInt(param[0])+"-" + param[1]);
			 oerror.setNumerolinea(param[2]);
			 oerror.setNumeroColumna(columnas);
			 	}
			
			 oerror.setNombrearchivo(param[3]);
			 oerror.setPar(param[4]); 
			 oerror.setCodigoerror(String.valueOf(errores));
			 error.add(oerror);
			 
			 
			 
		 }
			 
		 }
			 
		 }
		
		  } 
		  
		  if(!error.isEmpty())
		  {
			  
				  ocase.setValor1("1");
			  		  
		  }
		 
		 
		  System.out.println("End process" + sdf2.format(new Date()));	 
		 
		 d=null;
		 
		 
		 
		 System.out.println(">> ne" + archivo.getFileName().substring(0, archivo.getFileName().indexOf(".")));
		 flujo.setRutencargado(rutenc);
		 flujo.setEtapa("3");
		 flujo.setISAJKM92("CTADMIN");
		 flujo.setRutempresa(archivo.getFileName().substring(0, archivo.getFileName().indexOf(".")));
		 flujo.setPeriodo(periodo);
		 flujo.setISAJKM94("");
		 dao.updateFlujo(flujo);
		 
		 
		 
		 
		 
 }
	}else
	{
		 mensaje="La sesión expiró o el sistema encontró una excepción";
	      errors.add("name", new ActionMessage("id"));
	     
	}
 }
 catch(Exception ex)
	 {
	 

     // Report the error using the appropriate name and ID.
	  mensaje="La sesión expiró o el sistema encontró una excepción";
     errors.add("name", new ActionMessage("id"));
     ex.printStackTrace();
	 }

 


 if (!errors.isEmpty()) {
    
    request.setAttribute("mensaje", mensaje);
     // Forward control to the appropriate 'failure' URI (change name as desired)
     forward = mapping.findForward("onError");

 } else {
 	
	  ArchivosTO arc=new ArchivosTO();
		 for(int i=0;i<listafiles.size();i++)
		 {
			 arc=(ArchivosTO)listafiles.get(i);
			 System.out.println(arc.getNombre());
		 } 
		 
	 //new code 27-06-2013***
		 //creamos una carpeta en donde estarán los archivos subidos por el usuario
		/* String dirPath = System.getProperty("");
		 String dirName = "Archivos";
		 
		 File dir = new File(dirPath+"/"+dirName);
		 
		 if(!dir.exists()){
			 dir.mkdir(); 
		 }*/
	 //***********	 
	 
	 request.getSession().setAttribute("lista",error);
	 request.getSession().setAttribute("listafiles",listafiles);
	 request.setAttribute("case",ocase);
	 request.getSession().setAttribute("proceso", acepta);
	 
	 
	  
	 
	 
	 listaerror=null;
	  

	 
     forward = mapping.findForward("cargado");

 }

 // Finish with
 return (forward);

}


}


 
