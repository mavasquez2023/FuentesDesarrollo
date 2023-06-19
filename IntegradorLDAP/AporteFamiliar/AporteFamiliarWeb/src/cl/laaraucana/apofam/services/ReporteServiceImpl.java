package cl.laaraucana.apofam.services;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cl.laaraucana.apofam.entities.Cargas;
import cl.laaraucana.apofam.util.Configuraciones;
import cl.laaraucana.apofam.util.UtilsFecha;
import cl.laaraucana.apofam.util.UtilsPDF;


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
	private static final String TEMPLATE1 = Configuraciones.getConfig("aporte.familiar.carpeta") + "\\report\\report1.jrxml";

	private static String PDF;

	public String generarReport(Cargas resumen, String path, HttpServletResponse response) throws Exception {

		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		String filename= resumen.getRutAfiliado() + "_AporteFamiliarPermanente.pdf";
		PDF = path + "PDF\\" + filename;
		logger.info("Ruta PDF a generar:" + PDF);
		//Hoja 1
		Map<String, Object> param_map = new HashMap<String, Object>();
		String fechaDescarga= UtilsFecha.dateToString(new Date());
		param_map = UtilsPDF.hoja1(resumen.getRutAfiliado()+"-"+resumen.getDvAfiliado(), resumen.getNombreAfiliado(), resumen.getNumeroCargas(), fechaDescarga);

		JasperDesign design = JRXmlLoader.load(TEMPLATE1);
		JasperReport jReport = JasperCompileManager.compileReport(design);
		JasperPrint jPrint = JasperFillManager.fillReport(jReport, param_map, new JREmptyDataSource());
		jasperPrintList.add(jPrint);
				
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));

		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(PDF));
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setCreatingBatchModeBookmarks(true);

		exporter.setConfiguration(configuration);
		exporter.exportReport();

		// JasperExportManager.exportReportToPdfFile(jasperPrintList, PDF);
		
		if(response!= null){
			FileInputStream archivo = new FileInputStream(PDF);
			int longitud = archivo.available();
			byte[] datos = new byte[longitud];
			archivo.read(datos);
			archivo.close();

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);

			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(datos);
			ouputStream.flush();
			ouputStream.close();
			logger.info("Reporte Descargado");
		}
		return PDF;
	}

}
