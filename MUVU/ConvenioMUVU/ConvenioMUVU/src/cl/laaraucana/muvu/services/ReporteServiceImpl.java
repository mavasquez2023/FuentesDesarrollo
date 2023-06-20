package cl.laaraucana.muvu.services;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cl.laaraucana.muvu.entities.Resumen;
import cl.laaraucana.muvu.util.Configuraciones;
import cl.laaraucana.muvu.util.UtilsFecha;
import cl.laaraucana.muvu.util.UtilsPDF;
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

	private static final String TEMPLATE1 = Configuraciones.getConfig("muvu.carpeta") + "\\report\\report1.jrxml";
	private static final String TEMPLATE2 = Configuraciones.getConfig("muvu.carpeta") + "\\report\\report2.jrxml";
	private static final String TEMPLATE3 = Configuraciones.getConfig("muvu.carpeta") + "\\report\\report3.jrxml";
	private static final String TEMPLATE4 = Configuraciones.getConfig("muvu.carpeta") + "\\report\\report4.jrxml";
	private static final String TEMPLATE5 = Configuraciones.getConfig("muvu.carpeta") + "\\report\\report5.jrxml";

	private static String PDF;

	public String generarReport(Resumen resumen, String path) throws Exception {

		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();

		PDF = path + "PDF\\" + resumen.getRutAfiliado()
				+ "_Poliza_Seguro_Vida_Sana.pdf";

		//Hoja 1
		Map<String, Object> param_map = new HashMap<String, Object>();
		String fechaInicio= UtilsFecha.dateToString(resumen.getFechaAlta());
		String fechaTermino=UtilsFecha.dateToString(UtilsFecha.sumMonths(resumen.getFechaAlta(), 12));
		param_map = UtilsPDF.hoja1(resumen.getRutAfiliado()+"-"+resumen.getDvAfiliado(), resumen.getNombreAfiliado(), fechaInicio, fechaTermino);

		JasperDesign design = JRXmlLoader.load(TEMPLATE1);
		JasperReport jReport = JasperCompileManager.compileReport(design);
		JasperPrint jPrint = JasperFillManager.fillReport(jReport, param_map, new JREmptyDataSource());

		jasperPrintList.add(jPrint);
		
		//Hoja 2
		Map<String, Object> param_map2 = new HashMap<String, Object>();

		param_map2 = UtilsPDF.hoja2(resumen.getEmail());

		JasperDesign design2 = JRXmlLoader.load(TEMPLATE2);
		JasperReport jReport2 = JasperCompileManager.compileReport(design2);
		JasperPrint jPrint2 = JasperFillManager.fillReport(jReport2, param_map2, new JREmptyDataSource());

		jasperPrintList.add(jPrint2);
		
		//Hoja 3
		JasperDesign design3 = JRXmlLoader.load(TEMPLATE3);
		JasperReport jReport3 = JasperCompileManager.compileReport(design3);
		JasperPrint jPrint3 = JasperFillManager.fillReport(jReport3, null, new JREmptyDataSource());

		jasperPrintList.add(jPrint3);
		
		//Hoja 4
		JasperDesign design4 = JRXmlLoader.load(TEMPLATE4);
		JasperReport jReport4 = JasperCompileManager.compileReport(design4);
		JasperPrint jPrint4 = JasperFillManager.fillReport(jReport4, null, new JREmptyDataSource());

		jasperPrintList.add(jPrint4);

		//Hoja 5
		JasperDesign design5 = JRXmlLoader.load(TEMPLATE5);
		JasperReport jReport5 = JasperCompileManager.compileReport(design5);
		JasperPrint jPrint5 = JasperFillManager.fillReport(jReport5, null, new JREmptyDataSource());

		jasperPrintList.add(jPrint5);
				
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));

		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(PDF));
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setCreatingBatchModeBookmarks(true);

		exporter.setConfiguration(configuration);
		exporter.exportReport();

		// JasperExportManager.exportReportToPdfFile(jasperPrintList, PDF);

		FileInputStream archivo = new FileInputStream(PDF);
		int longitud = archivo.available();
		byte[] datos = new byte[longitud];
		archivo.read(datos);
		archivo.close();

		/*
		 * response.setContentType("application/pdf");
		 * response.setHeader("Content-Disposition",
		 * "attachment;filename=diferimiento.pdf");
		 * 
		 * ServletOutputStream ouputStream = response.getOutputStream();
		 * ouputStream.write(datos); ouputStream.flush(); ouputStream.close();
		 */
		return PDF;
	}

}
