package cl.laaraucana.contratocr.services;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cl.laaraucana.contratocr.util.Configuraciones;
import cl.laaraucana.contratocr.util.UtilsPDF;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@Service
public class ReporteServiceImpl implements ReporteService {
	private static final Logger logger = Logger.getLogger(ReporteServiceImpl.class);
	private static final String TEMPLATE1 = Configuraciones.getConfig("carpeta.report") + "\\report1.jrxml";
	private static final String TEMPLATE2 = Configuraciones.getConfig("carpeta.report") + "\\report2.jrxml";
	private static final String TEMPLATE3 = Configuraciones.getConfig("carpeta.report") + "\\report3.jrxml";
	private static final String TEMPLATE4 = Configuraciones.getConfig("carpeta.report") + "\\report4.jrxml";
	
	
	public String generarReport(HttpServletRequest request, HttpServletResponse response, String rut, String nombre)
			throws Exception {

		String PDF = Configuraciones.getConfig("carpeta.report") + "Contrato_uso_canales_remotos_" + rut + ".pdf";
		logger.info("Ruta PDF Mandato:" + PDF);
		
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();

		//HOJA 1
		Map<String, Object> param_map = UtilsPDF.hoja1();

		JasperDesign design = JRXmlLoader.load(TEMPLATE1);
		JasperReport jReport = JasperCompileManager.compileReport(design);
		JasperPrint jPrint = JasperFillManager.fillReport(jReport, param_map, new JREmptyDataSource());

		jasperPrintList.add(jPrint);
		
		//HOJA 2
		Map<String, Object> param_map2 = new HashMap<String, Object>();

		JasperDesign design2 = JRXmlLoader.load(TEMPLATE2);
		JasperReport jReport2 = JasperCompileManager.compileReport(design2);
		JasperPrint jPrint2 = JasperFillManager.fillReport(jReport2, param_map2, new JREmptyDataSource());

		jasperPrintList.add(jPrint2);
		
		//HOJA 3
		Map<String, Object> param_map3 = new HashMap<String, Object>();

		JasperDesign design3 = JRXmlLoader.load(TEMPLATE3);
		JasperReport jReport3 = JasperCompileManager.compileReport(design3);
		JasperPrint jPrint3 = JasperFillManager.fillReport(jReport3, param_map3, new JREmptyDataSource());

		jasperPrintList.add(jPrint3);
		
		//HOJA 4
		Map<String, Object> param_map4 = UtilsPDF.hoja2(rut, nombre);

		JasperDesign design4 = JRXmlLoader.load(TEMPLATE4);
		JasperReport jReport4 = JasperCompileManager.compileReport(design4);
		JasperPrint jPrint4 = JasperFillManager.fillReport(jReport4, param_map4, new JREmptyDataSource());

		jasperPrintList.add(jPrint4);
		
		//Exportando PDF
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));

		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(PDF));
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setCreatingBatchModeBookmarks(true);

		exporter.setConfiguration(configuration);
		exporter.exportReport();
		logger.info("Reporte generado");
		
		// JasperExportManager.exportReportToPdfFile(jasperPrintList, PDF);

		FileInputStream archivo = new FileInputStream(PDF);
		int longitud = archivo.available();
		byte[] datos = new byte[longitud];
		archivo.read(datos);
		archivo.close();

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename=" + "Contrato_uso_canales_remotos_" + rut + ".pdf");

		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(datos);
		ouputStream.flush();
		ouputStream.close();
		logger.info("Reporte Descargado");
		return PDF;
	}

}
