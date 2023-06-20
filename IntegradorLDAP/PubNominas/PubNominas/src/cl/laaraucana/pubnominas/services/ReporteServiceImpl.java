package cl.laaraucana.pubnominas.services;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import cl.laaraucana.pubnominas.dto.asfam.CargasAUTDto;
import cl.laaraucana.pubnominas.dto.asfam.TotalesAUTDto;
import cl.laaraucana.pubnominas.dto.cotizacion.NormalDto;
import cl.laaraucana.pubnominas.dto.pex.CuotaPEXDto;
import cl.laaraucana.pubnominas.utils.Configuraciones;
import cl.laaraucana.pubnominas.utils.Utils;
import cl.laaraucana.pubnominas.utils.UtilsReport;
import cl.laaraucana.pubnominas.vo.ModificacionesVO;


import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@Service
public class ReporteServiceImpl implements ReporteService {
	
	private static final String carpeta = Configuraciones.getConfig("carpeta.jasper");

	private static final String TEMPLATE_AUT = carpeta + "//Report//AUTORIZACION_ASFAM.jrxml";
	private static final String TEMPLATE_MOD_AUTO = carpeta + "//Report//MODIFICACIONES_autorizaciones.jrxml";
	private static final String TEMPLATE_MOD_SUSP = carpeta + "//Report//MODIFICACIONES_suspensiones.jrxml";
	private static final String TEMPLATE_MOD_PEND = carpeta + "//Report//MODIFICACIONES_pendientes.jrxml";
	private static final String TEMPLATE_COT = carpeta + "//Report//COTIZACIONES.jrxml";
	private static final String TEMPLATE_PEX = carpeta + "//Report//CARTA_PEX.jrxml";

	 
	 
	public void generarReportAUT(HttpServletRequest request, HttpServletResponse response, List<CargasAUTDto> datos, TotalesAUTDto totales)
			throws Exception {

		Map<String, Object> param_map = new HashMap<String, Object>();

		JasperPrint jPrint = null;

		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		


		param_map = UtilsReport.parametrosAUT(totales);

		JasperDesign design = JRXmlLoader.load(TEMPLATE_AUT);
		JasperReport jReport = JasperCompileManager.compileReport(design);
		//jPrint = JasperFillManager.fillReport(jReport, param_map, new JREmptyDataSource());

		jPrint= JasperFillManager.fillReport(jReport, param_map,  new JRBeanCollectionDataSource(datos));
		//byte[] bites = JasperExportManager.exportReportToPdf(jPrint);

		jasperPrintList.add(jPrint);
		
		//Guardando PDF por periodo
		File file = new File(carpeta + "PDF//" + Utils.getFechaPeriodo());
		if (!file.exists()) {
			file.mkdir();
		}
		
		String namePDF= "autorizaciones_" + totales.getPeriodo() + "_" + totales.getRutAfiliado()+"-" + totales.getDvAfiliado()+ ".pdf";
		String PDF= carpeta + "PDF//" + Utils.getFechaPeriodo() + "//" + namePDF;
		
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
		response.setHeader("Content-Disposition", "attachment;filename=" + namePDF);

		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(data);
		ouputStream.flush();
		ouputStream.close();
	}
	
	public void generarReportMOD(HttpServletRequest request, HttpServletResponse response, ModificacionesVO modificacionesVO)
			throws Exception {

		Map<String, Object> param_map = new HashMap<String, Object>();

		JasperPrint jPrint = null;

		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		
		param_map = UtilsReport.parametrosMOD(modificacionesVO);
		
		//Página Autorizaciones
		if(modificacionesVO.getAutorizaciones()!=null && modificacionesVO.getAutorizaciones().size()>0){
			JasperDesign design = JRXmlLoader.load(TEMPLATE_MOD_AUTO);
			JasperReport jReport = JasperCompileManager.compileReport(design);
			//jPrint = JasperFillManager.fillReport(jReport, param_map, new JREmptyDataSource());

			jPrint= JasperFillManager.fillReport(jReport, param_map,  new JRBeanCollectionDataSource(modificacionesVO.getAutorizaciones()));
			//byte[] bites = JasperExportManager.exportReportToPdf(jPrint);

			jasperPrintList.add(jPrint);
		}
		
		//Página Suspensiones
		if(modificacionesVO.getSuspensiones()!=null && modificacionesVO.getSuspensiones().size()>0){
			JasperDesign design = JRXmlLoader.load(TEMPLATE_MOD_SUSP);
			JasperReport jReport = JasperCompileManager.compileReport(design);
			//jPrint = JasperFillManager.fillReport(jReport, param_map, new JREmptyDataSource());

			jPrint= JasperFillManager.fillReport(jReport, param_map,  new JRBeanCollectionDataSource(modificacionesVO.getSuspensiones()));
			//byte[] bites = JasperExportManager.exportReportToPdf(jPrint);

			jasperPrintList.add(jPrint);
		}
		
		//Página Pendientes
		if(modificacionesVO.getPendientes()!=null && modificacionesVO.getPendientes().size()>0){
			JasperDesign design = JRXmlLoader.load(TEMPLATE_MOD_PEND);
			JasperReport jReport = JasperCompileManager.compileReport(design);
			//jPrint = JasperFillManager.fillReport(jReport, param_map, new JREmptyDataSource());

			jPrint= JasperFillManager.fillReport(jReport, param_map,  new JRBeanCollectionDataSource(modificacionesVO.getPendientes()));
			//byte[] bites = JasperExportManager.exportReportToPdf(jPrint);

			jasperPrintList.add(jPrint);
		}
		
		//Guardando PDF por periodo
		File file = new File(carpeta + "PDF//" + Utils.getFechaPeriodo());
		if (!file.exists()) {
			file.mkdir();
		}
		
		String namePDF="modificaciones_" + modificacionesVO.getCabecera().getPeriodo()+ "_" + modificacionesVO.getCabecera().getRutEmpresa() + "_" + modificacionesVO.getCabecera().getSucursal()+ ".pdf";
		String PDF= carpeta + "PDF//" + Utils.getFechaPeriodo() + "//"+ namePDF;
		
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
		response.setHeader("Content-Disposition", "attachment;filename=" + namePDF);

		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(data);
		ouputStream.flush();
		ouputStream.close();
	}
	
	public void generarReportCOT(HttpServletRequest request, HttpServletResponse response, List<NormalDto> datos)
			throws Exception {

		Map<String, Object> param_map = new HashMap<String, Object>();

		JasperPrint jPrint = null;

		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		
		for (NormalDto dato : datos) {

			param_map = UtilsReport.parametrosCOT(dato);
			
			JasperDesign design = JRXmlLoader.load(TEMPLATE_COT);
			JasperReport jReport = JasperCompileManager.compileReport(design);
			jPrint = JasperFillManager.fillReport(jReport, param_map, new JREmptyDataSource());

			jasperPrintList.add(jPrint);
		}
		//Guardando PDF por periodo
		File file = new File(carpeta + "PDF//" + Utils.getFechaPeriodo());
		if (!file.exists()) {
			file.mkdir();
		}

		String namePDF="cotizaciones_" + datos.get(0).getFolio() + ".pdf";
		String PDF= carpeta + "PDF//" + Utils.getFechaPeriodo() + "//" + namePDF;
		
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
		response.setHeader("Content-Disposition", "attachment;filename=" + namePDF);

		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(data);
		ouputStream.flush();
		ouputStream.close();
	}
	
	public void generarReportPEX(HttpServletRequest request, HttpServletResponse response, List<CuotaPEXDto> datos)
			throws Exception {

		Map<String, Object> param_map = new HashMap<String, Object>();

		JasperPrint jPrint = null;

		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		
		param_map = UtilsReport.parametrosPex(datos.get(0));
		datos.add(0, new CuotaPEXDto());
		
		JasperDesign design = JRXmlLoader.load(TEMPLATE_PEX);
		JasperReport jReport = JasperCompileManager.compileReport(design);
		jPrint = JasperFillManager.fillReport(jReport, param_map,  new JRBeanCollectionDataSource(datos));

		jasperPrintList.add(jPrint);
		
		//Guardando PDF por periodo
		File file = new File(carpeta + "PDF//" + Utils.getFechaPeriodo());
		if (!file.exists()) {
			file.mkdir();
		}

		String namePDF="cartaPEX_" + datos.get(1).getRutEmpresa() + "_" + datos.get(1).getOficina()  + ".pdf";
		String PDF= carpeta + "PDF//" + Utils.getFechaPeriodo() + "//" + namePDF;
		
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
		response.setHeader("Content-Disposition", "attachment;filename=" +  namePDF);

		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(data);
		ouputStream.flush();
		ouputStream.close();
	}
}
