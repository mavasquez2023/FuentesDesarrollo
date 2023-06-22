package cl.laaraucana.satelites.Utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRPrintImage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

public class ReporteUtil {

	
	private List datos; //

	public List getDatos() {
		return datos;
	}

	private Map hash; //
	private String ruta;//

	public ReporteUtil(List datos, Map hash, String ruta) {
		this.datos = datos;
		this.hash = hash;
		this.ruta = ruta;
	}

	/*
	 * Exportar reporte a PDF
	 * 
	 * @return Los bites del archivo pdf generado
	 */
	
	public byte[] exportPdf() throws IOException, JRException {
		JasperReport js;
		JasperPrint jp;
		byte[] bites = null;
		js = (JasperReport) JasperCompileManager.compileReport(ruta);
		
		if(datos != null){
			jp = JasperFillManager.fillReport(js, hash,
					new JRBeanCollectionDataSource(datos));
		}else{
			jp = JasperFillManager.fillReport(js, hash,
					new JREmptyDataSource());
		}
		// jp = JasperFillManager.fillReport(js, hash);
		bites = JasperExportManager.exportReportToPdf(jp);

		return bites;
	}
	
	public byte[] exportCompilePdf() throws IOException, JRException{
		JasperReport js;
		JasperPrint jp;
		byte[] bites = null;
		
        FileInputStream fis;
        BufferedInputStream bufferedInputStream;
        
        fis = new FileInputStream(this.ruta);
        bufferedInputStream = new BufferedInputStream(fis);
        js = (JasperReport) JRLoader.loadObject(bufferedInputStream);
        
        if(datos != null){
			jp = JasperFillManager.fillReport(js, hash,
					new JRBeanCollectionDataSource(datos));
		}else{
			jp = JasperFillManager.fillReport(js, hash,
					new JREmptyDataSource());
		}
		// jp = JasperFillManager.fillReport(js, hash);
		bites = JasperExportManager.exportReportToPdf(jp);
		
        return bites;
	}


	/*
	 * Exportar reporte a formato xls
	 * 
	 * @return Los bites del archivo xls generado
	 */
	public byte[] exportXls() throws IOException, JRException {
		JasperReport js;
		JasperPrint jp;
		byte[] bites = null;
		
			js = (JasperReport) JasperCompileManager.compileReport(ruta);
			jp = JasperFillManager.fillReport(js, hash, new JRBeanCollectionDataSource(datos));

			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			JRXlsExporter exporterXLS = new JRXlsExporter();

			exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jp);
			exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
					arrayOutputStream);
			exporterXLS
					.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
							Boolean.FALSE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporterXLS.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporterXLS.exportReport();
			bites = arrayOutputStream.toByteArray();

		return bites;
	}
	
	public void exportHtml(HttpServletRequest request ,HttpServletResponse response) throws IOException {
		  Map imagesMap = new HashMap(); 
		response.setContentType("text/html");
		
		JasperReport js;
		JasperPrint jp;
		JRExporter exporter = null;
		try {
	        FileInputStream fis;
	        BufferedInputStream bufferedInputStream;
	        
	        fis = new FileInputStream(this.ruta);
	        bufferedInputStream = new BufferedInputStream(fis);
	        js = (JasperReport) JRLoader.loadObject(bufferedInputStream);
	        
	        if(datos != null){
				jp = JasperFillManager.fillReport(js, hash, new JRBeanCollectionDataSource(datos));
			}else{
				jp = JasperFillManager.fillReport(js, hash, new JREmptyDataSource());
			}
			exporter = new JRHtmlExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
			request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jp);
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "/SatelitesCRM/kiosco/image?image=");
			exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP, imagesMap); 
			 
			exporter.setParameter(JRExporterParameter.OUTPUT_WRITER,response.getWriter());
			exporter.exportReport();
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setDatos(List datos) {
		this.datos = datos;
	}

	public Map getHash() {
		return hash;
	}

	public void setHash(Map hash) {
		this.hash = hash;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
}
