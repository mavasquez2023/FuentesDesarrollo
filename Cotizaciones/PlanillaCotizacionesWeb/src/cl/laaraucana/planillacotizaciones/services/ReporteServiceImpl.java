package cl.laaraucana.planillacotizaciones.services;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import cl.laaraucana.planillacotizaciones.dto.NormalDto;
import cl.laaraucana.planillacotizaciones.utils.Configuraciones;
import cl.laaraucana.planillacotizaciones.utils.Utils;
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
	
	private static final String carpeta = Configuraciones.getConfig("carpeta.jasper");

	private static final String TEMPLATE = carpeta + "//report1.jrxml";

	 
	public void generarReport(HttpServletRequest request, HttpServletResponse response, List<NormalDto> datos)
			throws Exception {

		Map<String, Object> param_map = new HashMap<String, Object>();

		JasperPrint jPrint = null;

		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		
		for (NormalDto dato : datos) {

			param_map = Utils.parametros(dato);
			
			JasperDesign design = JRXmlLoader.load(TEMPLATE);
			JasperReport jReport = JasperCompileManager.compileReport(design);
			jPrint = JasperFillManager.fillReport(jReport, param_map, new JREmptyDataSource());

			jasperPrintList.add(jPrint);
		}
		
		String PDF= carpeta + "//cotizaciones_" + datos.get(0).getFolio() + ".pdf";
		
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
		byte[] data = new byte[longitud];
		archivo.read(data);
		archivo.close();

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename=planillaCotizaciones_" + datos.get(0).getPeriodo() + "_" + datos.get(0).getRutEmpleador().replaceAll("\\.", "") +  ".pdf");

		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(data);
		ouputStream.flush();
		ouputStream.close();
	}

}
