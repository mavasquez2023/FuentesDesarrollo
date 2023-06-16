package cl.laaraucana.simat.documentos.JasperReport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.LinkedList;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

/*
 * clase que permite escribir datos en los template de ireport(jasperReport)
 * 
 * */

public class escritorJasper {

	public void writeJasper(String ruta, String template, LinkedList listageneral) throws Exception {
		//metodo que escribe los datos en un template jrxml en formato "xls" 
		JasperReport jr = null;
		JasperPrint jp = null;

		jr = JasperCompileManager.compileReport(template);
		jp = JasperFillManager.fillReport(jr, null, new JRBeanCollectionDataSource(listageneral));
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
	}

	public void writePDFJasper(String ruta, String template, LinkedList listageneral) throws Exception {
		//metodo que escribe los datos en un template jrxml en formato "xls" 
		JasperReport jr = null;
		JasperPrint jp = null;

		jr = JasperCompileManager.compileReport(template);
		jp = JasperFillManager.fillReport(jr, null, new JRBeanCollectionDataSource(listageneral));
		OutputStream output = new FileOutputStream(new File(ruta));

		JasperExportManager.exportReportToPdfFile(jp, ruta);
		output.flush();
		output.close();

	}

}
