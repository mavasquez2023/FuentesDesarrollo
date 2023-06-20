package cl.laaraucana.silmsil.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

public class UtilJaspert 
{
	/*
	 * Método para escritura de archivo Excel.
	 */
	public boolean writeJasper(String ruta,String periodo,String template,ArrayList listageneral){	
		System.out.println("Entro al jaspert -> writeJasper");
		boolean keyEscritura=false;
		JasperReport jr = null;
		JasperPrint jp = null;
		Map<String, Object> auxMap=new HashMap<String, Object>();
		auxMap.put("periodo", periodo);
		 try { 					
			 jr= JasperCompileManager.compileReport(template);			
			// jp = JasperFillManager.fillReport(jr,null,new JRBeanCollectionDataSource(listageneral));
			 jp = JasperFillManager.fillReport(jr,auxMap,new JRBeanCollectionDataSource(listageneral));
			  			
			 OutputStream output = new FileOutputStream(new File(ruta));
			 JRXlsExporter exporterXLS = new JRXlsExporter();
			
			 exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jp);
			 //exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, arrayOutputStream);
			 exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
			 exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			 exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			 exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			 exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			 
			 exporterXLS.exportReport();
			 output.flush();
			 output.close();
			 keyEscritura=true;
		 } catch(Exception e)  {
			 e.printStackTrace();
			 System.out.println("writeJasper catch : "+ e.toString());
			 keyEscritura=false;
		 }
		 return keyEscritura;
	}//end writeJasper
}//end class

