package cl.laaraucana.mandatopublico.services;

import java.io.FileInputStream;
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

import cl.laaraucana.mandatopublico.controller.ReportController;
import cl.laaraucana.mandatopublico.ibatis.vo.CuentaVo;
import cl.laaraucana.mandatopublico.ibatis.vo.MandatoAS400Vo;
import cl.laaraucana.mandatopublico.util.Configuraciones;
import cl.laaraucana.mandatopublico.util.Utils;
import cl.laaraucana.mandatopublico.vo.DatosMandatoVo;
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
	private static final String TEMPLATE1 = Configuraciones.getConfig("mandato.report") + "\\report1.jrxml";
	private static final String TEMPLATE2 = Configuraciones.getConfig("mandato.report") + "\\report2.jrxml";
	private static final String TEMPLATE3 = Configuraciones.getConfig("mandato.report") + "\\report3.jrxml";

	private static String PDF;

	private static final String PDFE = Configuraciones.getConfig("mandato.report");

	@Autowired
	private MandatoAS400Service mandatosas400Service;

	@Autowired
	private CuentaService cuentaService;
	
	@Autowired
	private BancoService bancoService;

	public String generarReport(HttpServletRequest request, HttpServletResponse response, DatosMandatoVo vo)
			throws Exception {

		PDF = Configuraciones.getConfig("mandato.report") + "mandato_" + vo.getIdMandato() + ".pdf";
		logger.info("Ruta PDF Mandato:" + PDF);
		
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();

		Map<String, Object> param_map = new HashMap<String, Object>();

		param_map = Utils.hoja1(vo);

		JasperDesign design = JRXmlLoader.load(TEMPLATE1);
		JasperReport jReport = JasperCompileManager.compileReport(design);
		JasperPrint jPrint = JasperFillManager.fillReport(jReport, param_map, new JREmptyDataSource());

		jasperPrintList.add(jPrint);

		Map<String, Object> param_map2 = new HashMap<String, Object>();

		param_map2 = Utils.hoja2_1(vo);

		JasperDesign design2 = JRXmlLoader.load(TEMPLATE2);
		JasperReport jReport2 = JasperCompileManager.compileReport(design2);
		JasperPrint jPrint2 = JasperFillManager.fillReport(jReport2, param_map2, new JREmptyDataSource());

		jasperPrintList.add(jPrint2);

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
		response.setHeader("Content-Disposition", "attachment;filename=Mandato.pdf");

		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(datos);
		ouputStream.flush();
		ouputStream.close();
		logger.info("Reporte Descargado");
		return PDF;
	}

	public void generarReport(HttpServletRequest request, HttpServletResponse response, long id) throws Exception {

		MandatoAS400Vo vo = mandatosas400Service.consultaMandatosById(id);

		String banco = bancoService.findBancoById(vo.getCodbanco()).getNombre();
		String cuenta = bancoService.findCuentaById(vo.getIdtipcta()).getDescripcion();

		PDF = Configuraciones.getConfig("mandato.report") + "mandato_" + id + ".pdf";
		logger.info("Ruta PDF Mandato:" + PDF);
		
		DatosMandatoVo datos = new DatosMandatoVo();

		datos.setCelular(vo.getCelular());
		datos.setEmail(vo.getEmail());
		datos.setNombre(vo.getNombre());
		datos.setRut(vo.getRutafi() + "-" + vo.getDvafi());
		datos.setTelefono(vo.getTelefono());
		datos.setCuenta(vo.getNumcuenta());
		datos.setNameBanco(banco);
		datos.setNameCuenta(cuenta);
		datos.setIdMandato(id);
		datos.setFechaVigencia(vo.getFechavig());

		Map<String, Object> param_map = new HashMap<String, Object>();

		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();

		param_map = Utils.hoja1(datos);

		JasperDesign design = JRXmlLoader.load(TEMPLATE1);
		JasperReport jReport = JasperCompileManager.compileReport(design);
		JasperPrint jPrint = JasperFillManager.fillReport(jReport, param_map, new JREmptyDataSource());

		jasperPrintList.add(jPrint);

		Map<String, Object> param_map2 = new HashMap<String, Object>();

		param_map2 = Utils.hoja2(datos);

		JasperDesign design2 = JRXmlLoader.load(TEMPLATE2);
		JasperReport jReport2 = JasperCompileManager.compileReport(design2);
		JasperPrint jPrint2 = JasperFillManager.fillReport(jReport2, param_map2, new JREmptyDataSource());

		jasperPrintList.add(jPrint2);

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
		byte[] data = new byte[longitud];
		archivo.read(data);
		archivo.close();

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename=Mandato.pdf");

		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(data);
		ouputStream.flush();
		ouputStream.close();
		logger.info("Reporte Descargado");
	}

	public String generarReportejecutivo(DatosMandatoVo vo) throws Exception {

		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();

		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		
		PDF = Configuraciones.getConfig("mandato.report") + "mandato_" + vo.getIdMandato() + ".pdf";
		logger.info("Ruta PDF Mandato:" + PDF);
		
		Map<String, Object> param_map = new HashMap<String, Object>();

		param_map = Utils.hoja1(vo);

		JasperDesign design = JRXmlLoader.load(TEMPLATE1);
		JasperReport jReport = JasperCompileManager.compileReport(design);
		JasperPrint jPrint = JasperFillManager.fillReport(jReport, param_map, new JREmptyDataSource());

		jasperPrintList.add(jPrint);

		Map<String, Object> param_map2 = new HashMap<String, Object>();

		param_map2 = Utils.hojaEjec(vo);

		JasperDesign design2 = JRXmlLoader.load(TEMPLATE2);
		JasperReport jReport2 = JasperCompileManager.compileReport(design2);
		JasperPrint jPrint2 = JasperFillManager.fillReport(jReport2, param_map2, new JREmptyDataSource());

		jasperPrintList.add(jPrint2);

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

		return PDF;

	}

	@Override
	public void generarReportRevocado(HttpServletRequest request, HttpServletResponse response, long id)
			throws Exception {

		MandatoAS400Vo vo = mandatosas400Service.consultaMandatosRevById(id);
		String banco = bancoService.findBancoById(vo.getCodbanco()).getNombre();
		String cuenta = bancoService.findCuentaById(vo.getIdtipcta()).getDescripcion();

		//Date fechaTermino = new SimpleDateFormat("dd/MM/yyyy").parse(vo.getFechater());
		Date fechaTermino = vo.getFechater();
		
		PDF = Configuraciones.getConfig("mandato.report") + "mandato_revocado_" + id + ".pdf";
		logger.info("Ruta PDF Mandato_Revocado:" + PDF);
		
		DatosMandatoVo datos = new DatosMandatoVo();

		datos.setCelular(vo.getCelular());
		datos.setEmail(vo.getEmail());
		datos.setNombre(vo.getNombre());
		datos.setRut(vo.getRutafi() + "-" + vo.getDvafi());
		datos.setTelefono(vo.getTelefono());
		datos.setCuenta(vo.getNumcuenta());
		datos.setNameBanco(banco);
		datos.setNameCuenta(cuenta);
		datos.setFechaRevocacion(fechaTermino);
		datos.setObservaciones(vo.getObservaciones());
		
		Map<String, Object> param_map = new HashMap<String, Object>();

		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();

		param_map = Utils.hoja3(datos, id);

		JasperDesign design = JRXmlLoader.load(TEMPLATE3);
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
		logger.info("Reporte generado");
		
		// JasperExportManager.exportReportToPdfFile(jasperPrintList, PDF);

		FileInputStream archivo = new FileInputStream(PDF);
		int longitud = archivo.available();
		byte[] data = new byte[longitud];
		archivo.read(data);
		archivo.close();

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename=Revocación_Mandato.pdf");

		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(data);
		ouputStream.flush();
		ouputStream.close();
		logger.info("Reporte descargado");
	}

}
