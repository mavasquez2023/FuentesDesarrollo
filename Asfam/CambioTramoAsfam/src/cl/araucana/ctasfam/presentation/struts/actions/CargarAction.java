package cl.araucana.ctasfam.presentation.struts.actions;

 
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

import cl.araucana.core.util.Rut;
import cl.araucana.ctasfam.business.to.AceptaPropuestaForm;
import cl.araucana.ctasfam.business.to.AfiliadosErrorTO;
import cl.araucana.ctasfam.business.to.AfiliadosTO;
import cl.araucana.ctasfam.business.to.ArchivosTO;
import cl.araucana.ctasfam.business.to.CargaArchivoTO;
import cl.araucana.ctasfam.business.to.FlujoTO;
import cl.araucana.ctasfam.business.to.VerificadorTO;
import cl.araucana.ctasfam.integration.jdbc.dao.AraucanaJdbcDao;
import cl.araucana.ctasfam.presentation.struts.vo.Encargado;
import cl.araucana.ctasfam.resources.util.Parametros;
import cl.araucana.ctasfam.resources.util.Utils;

public class CargarAction extends Action{
	
	  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
      throws Exception {


  ActionForward forward = new ActionForward(); // return value
  String mensaje="";
  AraucanaJdbcDao dao=new AraucanaJdbcDao();
  FlujoTO flujo=new FlujoTO();
  
 String periodo= Parametros.getInstance().getParam().getPeriodoProceso();
  
  List listcomun=new ArrayList();

 
  List listaerrors=new ArrayList();
  List listafiles=new ArrayList();
  //ArchivosTO oarchivo=new ArchivosTO();
 // AfiliadosErrorTO oerror=new AfiliadosErrorTO();
  List error=new ArrayList();
  VerificadorTO ocase= new VerificadorTO();
	 
  String descripcion=null;
   
  Utils util=new Utils();
  CargaArchivoTO Me=(CargaArchivoTO)form;
  FormFile archivo=Me.getArchivo();
  String rutEmpresa=Me.getRutEmpresa();
  String extension=null;
  File file= new File(archivo.getFileName());
  extension=util.extencion(file);
  boolean conerror=false;
 
  try{ 

	  SimpleDateFormat sdf5 = new SimpleDateFormat("HH:mm:ss");
	  Date r=new Date();
	  //System.out.println("begin " + sdf5.format(r));   

	  //String rutestado=(String)request.getSession().getAttribute("rutestado");  
	  //System.out.println(">>rutestado "+ rutEmpresa);
	  request.getSession().setAttribute("file", archivo);
	  String rol= (String)request.getSession().getAttribute("rol");
	  if(rol.equals("Ejecutivo")){
		  rutEmpresa= archivo.getFileName().substring(0,archivo.getFileName().lastIndexOf("."));
		  Rut rutfull= new Rut(rutEmpresa);
		  rutEmpresa= rutfull.getNumber() + "-" + rutfull.getDV();
	  }
	  if(rutEmpresa!=null){
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
				  mensaje="Tamaño del archivo vacío";
			  }

			  String rutaArchivos=Parametros.getInstance().getParam().getCarpeta_respaldo();
			  String rutaZip=Parametros.getInstance().getParam().getCarpeta_descompres();

			  if(!extension.equalsIgnoreCase("zip")){
				  String tipo="NORMAL";
				  //System.out.println("Begin process " + sdf2.format(new Date()));

				  listcomun=util.procesaArchivos(extension, archivo,rutaArchivos,ext,tipo,request); 
				  ocase.setTipo("nozip");
				  ocase.setNombre(archivo.getFileName());

			  }else{
				  
				  ocase.setTipo("zip");
				  ocase.setNombre(archivo.getFileName());

				  File filer=util.creaArchivo(rutaArchivos, archivo, ext);

				  //System.out.println(filer.getAbsolutePath());
				  File filez=util.creaArchivo(rutaZip, archivo, ext);

				  //System.out.println(filez.getAbsolutePath());
				  if(util.unZip(filer.getAbsolutePath(), rutaZip + ext + "/"))
				  { 	  

					  File zip=new File(filez.getAbsolutePath());
					  if(zip.exists())
						  zip.delete();
					  List ficheros=util.procesaFicheros(rutaZip+"\\"+ext, request);
					  for (Iterator iterator = ficheros.iterator(); iterator
							.hasNext();) {
						String filecsv = (String) iterator.next();
						//listcomun=util.procesaArchivos(filecsv.substring(filecsv.lastIndexOf("\\.")), filecsv,rutaArchivos,ext,"NORMAL",request);
					}
					  
				  }
			  }
			  
			  if (listcomun.get(listcomun.size() - 1).equals("error")) {
				  conerror= true;
				  //listcomun.remove(listcomun.size() - 1);
			  }
			  if (!conerror) {
				  String rutempInt= (rutEmpresa.replaceAll("\\.", "").split("-"))[0];		  
				  flujo.setRutencargado(rutenc);
				  flujo.setEtapa("3");
				  flujo.setISAJKM92("CTADMIN");
				  flujo.setRutempresa(rutempInt);
				  flujo.setPeriodo(periodo);
				  flujo.setCantregistros(listcomun.size());
				  flujo.setOperacion("ENVIA ARCHIVO");
				  flujo.setISAJKM92("");
				  flujo.setISAJKM94("CTADMIN");
				  flujo.setEtapa("3");
				  flujo.setNombrearchivo((archivo.getFileName()));

				  dao.InsertaBitacora(flujo);
				  
				  //System.out.println(">> ne" + archivo.getFileName().substring(0, archivo.getFileName().indexOf(".")));
				  flujo.setRutencargado(rutenc);
				  flujo.setEtapa("3");
				  flujo.setISAJKM92("CTADMIN");
				  flujo.setRutempresa(rutempInt);
				  flujo.setPeriodo(periodo);
				  flujo.setISAJKM94("");
				  dao.updateFlujo(flujo);
			  }

			  //System.out.println("End process" + sdf2.format(new Date()));	 


	 }else{
		  mensaje="La sesión expiró o el sistema encontró una excepción";
	  }
  }
  catch(Exception ex)
  {


	  // Report the error using the appropriate name and ID.
	  mensaje="La sesión expiró o el sistema encontró una excepción";
	  ex.printStackTrace();
  }

  


  if (!mensaje.equals("")) {
     
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

	  if (!conerror) {
		  request.getSession().setAttribute("lista", new ArrayList());
		  request.getSession().setAttribute("registros", listcomun.get(0));
	  }else{
		  request.getSession().setAttribute("lista",listcomun.get(0));
	  }
	  //request.getSession().setAttribute("lista",listcomun);
	  request.getSession().setAttribute("listafiles",listafiles);
	  request.setAttribute("case",ocase);
	  request.getSession().setAttribute("rutestado", rutEmpresa);

	  forward = mapping.findForward("cargado");

  }

  // Finish with
  return (forward);

}


}


 
