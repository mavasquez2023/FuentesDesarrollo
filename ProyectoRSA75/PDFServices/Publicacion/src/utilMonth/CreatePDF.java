package utilMonth;

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import utilPub.UtilPub;

import cl.araucana.cp.hibernate.dao.monthDAO;

import com.ibm.ws.webcontainer.servlet.ServletConfig;

 

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.charts.fill.*;

 
 
public class CreatePDF extends  HttpServlet{
	
	 
	private static final long serialVersionUID = 5200799638211802992L;
	
	 
	
	
	public  void createPDF(String holding, String convenio, String rutEmpresa, String crutaf, String barra, String empleador, String trabajador, String meses, String nombrepdf, String tipoproceso,String strfecpa, String rutaPdf,String rutaZip,String rutaJasper, String rutaBarra, String fecha1, String fecha2) throws ServletException{
	
		 
		try {
			
		 
			
			String fileJasper=null;
			if(tipoproceso.equals("R"))
				fileJasper="Remuneraciones.jrxml";
			else if(tipoproceso.equals("G"))
				fileJasper="Gratificaciones.jrxml";
			else if(tipoproceso.equals("D"))
				fileJasper="DepositosConvenidos.jrxml";
			else if(tipoproceso.equals("L"))
				fileJasper="Reliquidaciones.jrxml";
			else if(tipoproceso.equals("S"))
				fileJasper="SIL.jrxml";
			else
				fileJasper=null;
			
			
			UtilPub util = new UtilPub();
			Connection con= util.getConnection();
	  	monthDAO mdao=new monthDAO();  
	  //	con=mdao.coneccionDatasource();
	  barcode bar=new barcode();
		
	String ruta=bar.barra(barra,rutaBarra);

	int a,m;
	Integer Fdesde, Fhasta;
	
	if (null != meses) {

		switch(Integer.parseInt(meses)){
		case 0: a=0;m=1;break;
		case 1: a=1;m=0;break;
		case 2: a=2;m=0;break;
		case 3: a=3;m=0;break;
		case 4: a=4;m=0;break;
		case 5: a=5;m=0;break;
		default:a=1000;m=0;
		}
		     Integer traeFecha=mdao.fechaHasta(tipoproceso, crutaf, rutEmpresa,con);
		     Fhasta=traeFecha;
		     Fdesde=new Integer(Integer.parseInt(Fhasta.toString())-(a*100+m));
	} else {
		Fdesde = new Integer(Integer.parseInt(fecha1));
		Fhasta = new Integer(Integer.parseInt(fecha2));
	}
         
	     System.out.println("hasta " + Fhasta);
	     System.out.println("desde " + Fdesde);
	   
	     //formateamos la fecha de emisión para colocarla en el header del certificado
	     SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es","CL"));   
		 Date fechaDate = new Date();   
		 String fecha = formateador.format(fechaDate);    
 
		JasperDesign design = JRXmlLoader.load(rutaJasper + fileJasper);
		
		Map param_map = new HashMap(); 

		 param_map.put("empleador",empleador);
		param_map.put("trabajador",trabajador);
		param_map.put("fecha",fecha);
		param_map.put("rutaf", crutaf);
		param_map.put("imagen", ruta);
		param_map.put("rutEmpresa", rutEmpresa);
		if ("".equals(fecha1) && "".equals(fecha2)) {
			param_map.put("desde", Fdesde);
		}
		else
		{
			param_map.put("desde", new Integer(Fdesde.intValue()-1));
		}
		param_map.put("hasta", Fhasta);
		param_map.put("fecha1",strfecpa);
		param_map.put("logoAraucana",rutaJasper + "araucana.jpg");
		param_map.put("Timbre",rutaJasper + "timbre.JPG");
		param_map.put("Firma",rutaJasper + "firma.GIF");
		param_map.put("holding", holding);
		param_map.put("convenio", convenio);
		
		JasperReport jReport = JasperCompileManager.compileReport(design);
		JasperPrint jPrint = JasperFillManager.fillReport(jReport,param_map,con);
		con.close();
		
		JasperExportManager.exportReportToPdfFile(jPrint, rutaPdf + nombrepdf + ".pdf");

		
		} catch (Exception e)
		{
		e.printStackTrace();
		}
	}

	public  void createPDFLote(String holding, String convenio, String rutEmpresa, String crutaf, String barra, String empleador, String trabajador, String meses, String nombrepdf, String carpeta,String tipoproceso,String strfecpa, String rutaJasper, String rutaZip, String rutaBarra, Connection con) {
		try {
			String fileJasper=null;
			if("R".equals(tipoproceso))
				fileJasper="Remuneraciones.jrxml";
			else if("G".equals(tipoproceso))
				fileJasper="Gratificaciones.jrxml";
			else if("D".equals(tipoproceso))
				fileJasper="DepositosConvenidos.jrxml";
			else if(tipoproceso.equals("L"))
				fileJasper="Reliquidaciones.jrxml";
			else if(tipoproceso.equals("S"))
				fileJasper="SIL.jrxml";
			else
				fileJasper=null;
			 
		//Class.forName("com.ibm.as400.access.AS400JDBCDriver");
		 
		//Connection con=null;
		//con=DriverManager.getConnection("jdbc:as400://146.83.1.32;user=SISTEMAS;password=sistemas");
	//monthDAO mdao=new monthDAO();
		
	//con=mdao.coneccionDatasource();
		barcode bar=new barcode();
		
	String ruta=bar.barra(barra,rutaBarra);


		JasperDesign design = JRXmlLoader.load(rutaJasper + fileJasper);
		
		Map param_map = new HashMap(); 
		monthDAO mdao=new monthDAO();

		int a,m;
	
		switch(Integer.parseInt(meses)){
		case 0: a=0;m=1;break;
		case 1: a=1;m=0;break;
		case 2: a=2;m=0;break;
		case 3: a=3;m=0;break;
		case 4: a=4;m=0;break;
		case 5: a=5;m=0;break;
		default:a=1000;m=0;
		}
		     Integer traeFecha=mdao.fechaHasta(tipoproceso, crutaf, rutEmpresa,con);
		     Integer Fhasta=traeFecha;
		     Integer Fdesde=new Integer(Integer.parseInt(Fhasta.toString())-(a*100+m));

		SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es","CL"));   
		Date fechaDate = new Date();   
		String fecha = formateador.format(fechaDate);     
		
		 param_map.put("empleador",empleador);
		param_map.put("trabajador",trabajador);
		param_map.put("fecha",fecha);
		param_map.put("rutaf", crutaf);
		param_map.put("imagen", ruta);
		param_map.put("rutEmpresa", rutEmpresa);
		param_map.put("desde", Fdesde);
		param_map.put("hasta", Fhasta);
		param_map.put("fecha1", strfecpa);
		param_map.put("logoAraucana",rutaJasper + "araucana.jpg");
		param_map.put("Timbre",rutaJasper + "timbre.JPG");
		param_map.put("Firma",rutaJasper + "firma.GIF");
		param_map.put("holding", holding);
		param_map.put("convenio",convenio);
		
		JasperReport jReport = JasperCompileManager.compileReport(design);
		JasperPrint jPrint = JasperFillManager.fillReport(jReport,param_map,con);
		
		JasperExportManager.exportReportToPdfFile(jPrint, rutaZip + carpeta + "/" + nombrepdf + ".pdf");

		
		} catch (Exception e)
		{
		e.printStackTrace();
		}
	}


}
