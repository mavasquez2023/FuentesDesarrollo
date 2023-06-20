package cl.laaraucana.licenciascompin.services;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import cl.laaraucana.licenciascompin.entities.RegistroLicencias;
import cl.laaraucana.licenciascompin.util.Configuraciones;
import cl.laaraucana.licenciascompin.util.Utils;
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

	private static final String TEMPLATE1 = Configuraciones.getConfig("conpin.carpeta") + "\\report1.jrxml";

	private static String PDF;

	public String generarReport(HttpServletRequest request, HttpServletResponse response, RegistroLicencias vo,
			String rut, String path) throws Exception {

		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();

		PDF = path + rut.replace(".", "").split("-")[0] + "_" + vo.getFolioLicencia()
				+ "_AutorizacionAfiliado.pdf";

		Map<String, Object> param_map = new HashMap<String, Object>();

		param_map = Utils.hoja1(vo, rut);

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
