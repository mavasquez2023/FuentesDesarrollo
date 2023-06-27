package month;

 
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.araucana.cp.hibernate.dao.monthDAO;

import utilMonth.CreatePDF;
import utilMonth.Fecha;
import utilMonth.descargarArchivo;


/**
 * Servlet implementation class for Servlet: GenerarDocumentoPDF
 *
 */
 public class GenerarDocumentoPDF extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    
	 
	private static final long serialVersionUID = 1402974394559166641L;

	public GenerarDocumentoPDF() {
		super();
	}   
	
	 private String rutaPdf=null;
	 private String rutaJasper=null;
	 private String rutaZip=null;
	 private String rutaBarra=null;
	
	public void init() throws ServletException {
	    getServletContext().log("getinit init");
	    // Get the value of an initialization parameter
	     rutaJasper = getServletConfig().getInitParameter("rutaJasper");
	     rutaPdf=getServletConfig().getInitParameter("rutaPdf");
	     rutaZip=getServletConfig().getInitParameter("rutaZip");
	     rutaZip=getServletConfig().getInitParameter("rutaBarra"); 
	}
	
	 //download files on demand of users
	 
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
		 HttpSession sesion=request.getSession();
			
			
				 
			
			 int valori=Integer.parseInt(request.getParameter("valori"));
	    	  
	    	 	    	
	    	Fecha fecha=new Fecha();
	    	 
	    	List lista=(List)sesion.getAttribute("parametros");
	    	 
	    	 
	    	 
			if(lista!=null)
			{
			
			CreatePDF cpdf=new CreatePDF();
			Date date=new Date();
		    long serie=date.getTime();
            String rutAfiliado="";
            String meses = "";
            String fecha1 = "";
            String fecha2 = "";
            String convenio = "";
            String holding="";
		    
			Integer fechaNow=fecha.getFechaNow();
			if (null == lista.get(valori + 8)) {
				fecha1 = lista.get(valori + 13).toString();
				fecha2 = lista.get(valori + 14).toString();
				meses = null;
				convenio = lista.get(valori + 11).toString();
				holding = lista.get(valori + 12).toString();
			} else {
				Integer fechaDesde=fecha.getFecha(lista.get(valori + 8).toString());
				meses=lista.get(valori + 8).toString();
				convenio = lista.get(valori + 11).toString();
				holding = lista.get(valori + 12).toString();
			}
			
			String rutaf1=lista.get(valori + 2).toString().trim();
			String rutem=lista.get(valori).toString().trim();
			String barra= rutem + rutaf1 +  fechaNow.toString();
			String digem=lista.get(valori+6).toString().trim();
			
			while(rutaf1.length()<9){
		    rutaf1 = " ".concat(rutaf1);
			}
			
			while(rutem.length()<9){
				rutem=" ".concat(rutem);
				
			}
			
			String digaf=lista.get(valori+5).toString().trim();
			String empleador=lista.get(valori+4).toString().toUpperCase() + ", " + rutem + " - " + digem;
			String trabajador=lista.get(valori+1).toString().toUpperCase() + ", " + rutaf1 + " - " + digaf;
			String nombrepdf="Temp".concat(String.valueOf(serie));
			String tipoProceso=lista.get(valori + 10).toString();
			 
			monthDAO dao=new monthDAO();
			Integer fecpa=dao.fechaEmision(tipoProceso, rutaf1, rutem,convenio);
			if(fecpa!=null)
			{
			String strfecpa=fecpa.toString();
			
			strfecpa=strfecpa.substring(6,8) + "-" + strfecpa.substring(4, 6) + "-" + strfecpa.substring(0, 4); 
			  
			 System.out.println("Certificado, fecha emisión:>>" + strfecpa);
			
			
			cpdf.createPDF(holding, convenio, rutem,rutaf1,barra,empleador,trabajador,meses,nombrepdf, tipoProceso,strfecpa,rutaPdf,rutaZip,rutaJasper, rutaBarra, fecha1, fecha2);
			
			descargarArchivo download=new descargarArchivo();
			
			download.downloadFile(request, response, nombrepdf.concat(".pdf"), rutaPdf);
			}
			else
				response.sendRedirect("emptyreport.html");
			}
			else
				response.sendRedirect("emptyList.html");
			
			
		 
	}    	  	    
}