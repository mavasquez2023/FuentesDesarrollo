package cl.laaraucana.copagocredito.services;

import java.io.FileInputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.laaraucana.copagocredito.model.CreditoVo;
import cl.laaraucana.copagocredito.util.Configuraciones;
import cl.laaraucana.copagocredito.util.Utils;
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
	
	private static final String TEMPLATE1 = Configuraciones.getConfig("copago.report") + "\\report1.jrxml";

	private static String PDF;

	public String generarReport(HttpServletRequest request, HttpServletResponse response, CreditoVo vo, Connection con, boolean salida)
			throws Exception {

		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();

		PDF = Configuraciones.getConfig("copago.report") + "copago_"
				+ vo.getRutCliente().replace(".", "").replace("-", "") + ".pdf";
		logger.info("Generando PDF Copago, ruta:" + PDF);
		
		Map<String, Object> param_map = new HashMap<String, Object>();

		param_map = Utils.hoja1(vo);

		JasperDesign design = JRXmlLoader.load(TEMPLATE1);
		JasperReport jReport = JasperCompileManager.compileReport(design);
		JasperPrint jPrint = JasperFillManager.fillReport(jReport, param_map, con);

		jasperPrintList.add(jPrint);

		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));

		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(PDF));
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setCreatingBatchModeBookmarks(true);

		exporter.setConfiguration(configuration);
		exporter.exportReport();
		logger.info("Reporte Generado");
		// JasperExportManager.exportReportToPdfFile(jasperPrintList, PDF);
		
		if(salida){
			FileInputStream archivo = new FileInputStream(PDF);
			int longitud = archivo.available();
			byte[] datos = new byte[longitud];
			archivo.read(datos);
			archivo.close();

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment;filename=Copago_Cr�dito_" + vo.getRutCliente().replace(".", "") + ".pdf");

			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(datos);
			ouputStream.flush();
			ouputStream.close();
			logger.info("Descargando PDF");
		}
		return PDF;
	}

}
