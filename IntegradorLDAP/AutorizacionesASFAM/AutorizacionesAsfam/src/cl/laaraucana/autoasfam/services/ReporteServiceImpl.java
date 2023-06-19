package cl.laaraucana.autoasfam.services;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import cl.laaraucana.autoasfam.dto.AutorizacionesMODDto;
import cl.laaraucana.autoasfam.dto.CargasAUTDto;
import cl.laaraucana.autoasfam.dto.PendientesMODDto;
import cl.laaraucana.autoasfam.dto.SuspensionesMODDto;
import cl.laaraucana.autoasfam.dto.TotalesAUTDto;
import cl.laaraucana.autoasfam.utils.Configuraciones;
import cl.laaraucana.autoasfam.utils.Utils;
import cl.laaraucana.autoasfam.vo.ModificacionesVO;
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

	private static final String TEMPLATE_AUT = carpeta + "//AutorizacionASFM.jrxml";
	private static final String TEMPLATE_MOD_AUTO = carpeta + "//MODIFICACIONES_autorizaciones.jrxml";
	private static final String TEMPLATE_MOD_SUSP = carpeta + "//MODIFICACIONES_suspensiones.jrxml";
	private static final String TEMPLATE_MOD_PEND = carpeta + "//MODIFICACIONES_pendientes.jrxml";
	 
	public void generarReportAUT(HttpServletRequest request, HttpServletResponse response, List<CargasAUTDto> datos, TotalesAUTDto totales)
			throws Exception {

		Map<String, Object> param_map = new HashMap<String, Object>();

		JasperPrint jPrint = null;

		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		


		param_map = Utils.parametrosAUT(totales);

		JasperDesign design = JRXmlLoader.load(TEMPLATE_AUT);
		JasperReport jReport = JasperCompileManager.compileReport(design);
		//jPrint = JasperFillManager.fillReport(jReport, param_map, new JREmptyDataSource());

		jPrint= JasperFillManager.fillReport(jReport, param_map,  new JRBeanCollectionDataSource(datos));
		//byte[] bites = JasperExportManager.exportReportToPdf(jPrint);

		jasperPrintList.add(jPrint);

		
		String PDF= carpeta + "//PDF//autorizaciones_" + totales.getPeriodo() + "_" + totales.getRutAfiliado()+"-" + totales.getDvAfiliado()+ ".pdf";
		
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
		response.setHeader("Content-Disposition", "attachment;filename=autorizaciones_" + totales.getPeriodo() + "_" + totales.getRutAfiliado() + "-" + totales.getDvAfiliado()+  ".pdf");

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
		
		param_map = Utils.parametrosMOD(modificacionesVO);
		
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
		
		String PDF= carpeta + "//PDF//modificaciones_" + modificacionesVO.getCabecera().getPeriodo()+ "_" + modificacionesVO.getCabecera().getRutEmpresa() + "_" + modificacionesVO.getCabecera().getSucursal()+ ".pdf";
		
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
		response.setHeader("Content-Disposition", "attachment;filename=modificaciones_" + modificacionesVO.getCabecera().getPeriodo()+ "_" + modificacionesVO.getCabecera().getRutEmpresa() + "_" + modificacionesVO.getCabecera().getSucursal()+  ".pdf");

		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(data);
		ouputStream.flush();
		ouputStream.close();
	}
}
