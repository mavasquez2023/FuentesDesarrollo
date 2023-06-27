package descargarPDF;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.araucana.cp.hibernate.beans.MesesbeanVO;
import cl.araucana.cp.hibernate.dao.monthDAO;

import utilMonth.CreatePDF;
import utilMonth.Fecha;
import utilMonth.Zippeo;
import utilMonth.descargarArchivo;
import java.util.*;

/**
 * Servlet implementation class for Servlet: DescargarPdf
 *
 */
public class DescargarPdf extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    
	 private String rutaZip=null;
	 private String rutaJasper=null;
	 private String rutaPdf=null;
	 private String rutaBarra=null;
		
		public void init() throws ServletException {
		    getServletContext().log("getinit init");
		    // Get the value of an initialization parameter
		     rutaZip=getServletConfig().getInitParameter("rutaZip");
		     rutaJasper=getServletConfig().getInitParameter("rutaJasper");
		     rutaPdf=getServletConfig().getInitParameter("rutaPdf");
		     rutaBarra=getServletConfig().getInitParameter("rutaBarra");
		}
	
	private static final long serialVersionUID = 1L;
	public DescargarPdf() {
		super();
	}   	
	
	//compress folder and download
	 
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String meses=request.getParameter("ListaMeses");
		String rutaf=request.getParameter("listaRut");
		String rutem=request.getParameter("RutEmpresa");
		String sucursal=request.getParameter("sucursal");
		String convenio=request.getParameter("Convenio");
		String tipoproceso=request.getParameter("TipoProceso");
		String holding=request.getParameter("holdingA");
		String rutaf1=request.getParameter("RutTrabajador");
		String nombreTrabajador=request.getParameter("NombreTrabajador");
		 
		
		String listaRut="";
		String Convenio=""; 
		String rutEmpresa="";
		String csucursal="";
		String cholding="";
		 
		if(rutaf.indexOf(" ")>0){
			rutaf=rutaf.replaceAll("  ", " ");
		    rutaf=rutaf.trim().replaceAll(" ",",");
		
		    /*String lista[]=rutaf.split(",");
		    for(int i=0;i<lista.length;i++){
		    	
		    	 
		    	
		    	rutaf2=rutaf2 + lista[i] + ",";
		    	
		    	
		    }
		       
		    rutaf2=rutaf2.substring(0,rutaf2.length()-3);
		    listaRut=rutaf2;*/
		}
		/*else{
			 
			listaRut=rutaf;
		}*/
		listaRut=rutaf;
		
		if(sucursal.trim().indexOf(" ")>0){
			sucursal=sucursal.replaceAll("  ", " ");
		    csucursal=sucursal.trim().replaceAll(" ","','");
		    
		    
			}
			else
			
			csucursal= sucursal;
		
		if(holding.trim().indexOf(" ")>0){
			holding=holding.replaceAll("  ", " ");
		    cholding=holding.trim().replaceAll(" ","','");
		    
		    
			}
			else
			
			cholding= holding;
		cholding=cholding.trim();
	
		if(rutem.indexOf(" ")>0){
			rutem=rutem.replaceAll("  ", " ");
			rutem=rutem.trim().replaceAll(" ",",");
		/*
		    String lista[]=rutEmpresa.split(",");
		    for(int i=0;i<lista.length;i++){
		    	
		    	 
		    	
		    	rutEmpresa2=rutEmpresa2 + lista[i] + ",";
		    	
		    	
		    }
		       
		    rutEmpresa2=rutEmpresa2.substring(0,rutEmpresa2.length()-3);
		    rutEmpresa=rutEmpresa2;
		    */
		}
		/*else{
			 
			rutEmpresa=rutem;
		}*/
		rutEmpresa=rutem;
		System.out.println(">>rut empresa" + rutEmpresa);
		
		if(convenio.trim().indexOf(" ")>0)
		Convenio=convenio.trim().replaceAll(" ", "','");

		else
			Convenio=convenio;

		
		
		if(rutaf1==null) 
			rutaf1="";
		if(listaRut==null || listaRut.equals("")){
			listaRut= rutaf1;
		}
		 if(rutem==null)
			 rutem=""; 
			
		Fecha fecha=new Fecha();
		MesesbeanVO omeses=new MesesbeanVO();
		List lista=new ArrayList();
		Integer fechaNow=fecha.getFechaNow();
		Integer fechaDesde=fecha.getFecha(meses);
		String carpeta=null;
		
		monthDAO mdao=new  monthDAO();
		omeses.setCentid(csucursal);
		omeses.setConvenio(Convenio);
		omeses.setRutEmpresa(rutEmpresa);
		omeses.setRutTrabajador(listaRut);
		omeses.setFecha(fecha.getFecha(meses));
		omeses.setTipoProceso(tipoproceso);
		omeses.setListaRut(listaRut);
		omeses.setNombreTrabajador(nombreTrabajador);
		omeses.setHolding(cholding);
		
		lista=mdao.getDatosDescarga(omeses);
		CreatePDF cpdf=new CreatePDF();
	    Date date=new Date();
		long serie=date.getTime();
		carpeta="Folder".concat(String.valueOf(serie));
		
	    Connection con=mdao.coneccionDatasource();
	    String rutAfiliado="";
	    
		File folder=new File(rutaZip + carpeta);
		if(!folder.exists())
			folder.mkdir();
		
		if(lista!=null){
			
		 System.out.println("size " + lista.size());
		for(int i=0;i<lista.size();i+=11){
		String rutaf11=lista.get(i+2).toString();
		 
			 
		
		String rutem1=lista.get(i).toString();
		String barra= rutem1.concat(rutaf11).concat(fechaNow.toString());
		String digem=lista.get(i+6).toString().trim();
		String digaf=lista.get(i+5).toString().trim();
		String empleador=lista.get(i+4).toString().toUpperCase() + ", " + rutem1 + " - " + digem;
		String trabajador=lista.get(i+1).toString().toUpperCase() + ", " + rutaf11 + " - " + digaf;
		String nombrepdf=rutem1.concat(rutaf11).concat(String.valueOf(i)); 
	    String tipoProceso=lista.get(i+9).toString();
	    String dconvenio=lista.get(i+7).toString();
	    String dholding=lista.get(i+10).toString();
		 
	    
	    
	    monthDAO dao=new monthDAO();
		Integer fecpa=dao.fechaEmision(tipoProceso, rutaf11, rutem1,dconvenio);
		if(fecpa==null)
			response.sendRedirect("emptyreport.html");
		
		String strfecpa=fecpa.toString();
		strfecpa=strfecpa.substring(6,8) + "-" + strfecpa.substring(4, 6) + "-" + strfecpa.substring(0, 4); 
		 
 cpdf.createPDFLote(dholding, dconvenio, rutem1,rutaf11,barra,empleador,trabajador,meses,nombrepdf,carpeta, tipoProceso,strfecpa, rutaJasper,rutaZip, rutaBarra,con);
		
		}
		try{
		con.close();}
		catch(SQLException ex)
		{ex.printStackTrace();}
		descargarArchivo download=new descargarArchivo();
		String dir= rutaZip + carpeta;
		//Zippeo.zipDirectory(dir,folder.getAbsolutePath() + ".zip");
		Zippeo zip=new Zippeo();
		zip.zipFolder(dir, folder.getAbsolutePath() + ".zip");
		download.downloadZipp(request, response, carpeta.concat(".zip"),rutaZip);
		}
		else
			response.sendRedirect("emptyreport.html");
		}
		
				
		
	}  

	
