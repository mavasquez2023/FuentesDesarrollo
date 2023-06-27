package cl.laaraucana.simulacion;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.servicios.simuladorCreditoSocial.CreditoVO;
import cl.laaraucana.servicios.simuladorCreditoSocial.DetalleVO;
import cl.laaraucana.simulacion.utils.ConstantesFormalizar;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;

public class CertificadoAction extends Action {
	private static String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
	// private static String source = "resultado.html";
	private static String basedir = "C:\\a\\resources\\";
	private static String source = basedir + "resultado.html";
	private static String style = basedir + "resultado.css";

	public static String getPDFOutput() {
		return basedir + "Test-" + timestamp + ".pdf";
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		//return an application file instead of html page
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=Resultado_Simulación.pdf");
		try {
			HttpSession session = request.getSession();
			cl.laaraucana.servicios.simuladorCreditoSocial.CreditoVO value = (CreditoVO) session.getAttribute("credito");
			ArrayList detalle = (ArrayList) session.getAttribute("detalleCuotas");
			String rut = (String) session.getAttribute("rut");
			TestForm testForm = (TestForm) session.getAttribute("testForm");
			String file = getPDFOutput();
			String filename = generatePDFwithJasper(testForm, value);
			if (!filename.equals("error")) {
				OutputStream out = response.getOutputStream();
				FileInputStream in = new FileInputStream(filename);
				byte[] buffer = new byte[4096];
				int length;
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
				in.close();
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String convertFormatStringCurrency(int value) {
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
		String output = format.format(value).replaceAll("\\,", "\\.");
		return output.substring(0, output.length() - 3);
	}

	public static String generatePDFwithJasper(TestForm testForm, CreditoVO value) {
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile("C:\\a\\ireport\\simulacion.jasper");
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			// Datos de tabla dinamica7			
			DetalleVO[] cuotas = value.getDETALLE_CUOTA();
			List<DetalleCuota> cuotas_ = new ArrayList<DetalleCuota>();
			int ncuota = 1;
			for (DetalleVO detalleVO : cuotas) {
				DetalleCuota cuota = new DetalleCuota();
				cuota.setIndiceCuota(String.valueOf(ncuota));
				String fechaVencimiento = String.valueOf(detalleVO.getFECHA_VENCIMIENTO());
				String fechaVencimientoFinal = fechaVencimiento.substring(6, 8) + "-" + fechaVencimiento.substring(4, 6) + "-"
					+ fechaVencimiento.substring(0, 4);
				cuota.setVencimiento(fechaVencimientoFinal);
				cuota.setSaldoCapital(convertFormatStringCurrency(detalleVO.getSALDO_CAPITAL()));
				cuota.setMontoInteres(convertFormatStringCurrency(detalleVO.getMONTO_INTERES()));
				cuota.setSeguroDesgravamen(convertFormatStringCurrency(detalleVO.getSEGURO_DESGRAVAMEN()));
				cuota.setSeguroCesantia(convertFormatStringCurrency(detalleVO.getSEGURO_CESANTIA()));
				cuota.setValorCuota(convertFormatStringCurrency(detalleVO.getMONTO_CUOTA()));
				cuotas_.add(cuota);
				ncuota++;
			}
			parameters.put("cuotas", cuotas_);
			// Generacion PDF
			JRDataSource dataSource = new JREmptyDataSource();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			int pages = jasperPrint.getPages().size();
			// JasperReport jasperReport = JasperCompileManager.compileReport("C:\\a\\ireport\\simulacion.jrxml");
			JasperReport jasperReport1 = (JasperReport) JRLoader.loadObjectFromFile("C:\\a\\ireport\\simulacion.jasper");
			HashMap<String, Object> parameters1 = new HashMap<String, Object>();
			parameters1.put("rut", testForm.getRut());
			parameters1.put("impuesto", testForm.getIMPUESTO());
			parameters1.put("sucursal", testForm.getSucursal());
			parameters1.put("gastoNotarial", testForm.getGASTO_NOTARIAL());
			parameters1.put("tipoAfiliado", testForm.getTipoAfiliado());
			parameters1.put("valorCuotaMensual", testForm.getMONTO_CUOTA());
			parameters1.put("monto", testForm.getMonto());
			parameters1.put("tasaInteresMensual", testForm.getTASA_INT_MENSUAL() + "%");
			parameters1.put("numeroCuota", testForm.getCuotas());
			parameters1.put("pagoPrimeraCuota", testForm.getFECHA_PRIMER_VENCIMIENTO());
			parameters1.put("seguroCesantia", testForm.getSeguroCesantia());
			parameters1.put("seguroDesgravamen", testForm.getSeguroDesgravamen());
			parameters1.put("cae", testForm.getCAE() + "%");
			parameters1.put("ctc", testForm.getCTC());
			parameters1.put("FechaHoraSimulacion", "<style isBold='true' pdfFontName='Helvetica-Bold'>"
				+ new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(new Date()) + " hrs.</style>");
			// JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(cuotas);
			parameters1.put("cuotas", cuotas_);
			// Generacion PDF
			JRDataSource dataSource1 = new JREmptyDataSource();
			List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
			parameters1.put("numeroPagina", pages);
			parameters1.put("numeroPaginas", pages + 1);
			JasperPrint jasperPrint1 = JasperFillManager.fillReport(jasperReport1, parameters1, dataSource1);
			jasperPrints.add(jasperPrint1);
			pages = jasperPrint1.getPages().size();
			JasperReport jasperReport2 = (JasperReport) JRLoader.loadObjectFromFile("C:\\a\\ireport\\resumen.jasper");
			HashMap<String, Object> parameters2 = new HashMap<String, Object>();
			String SEGURO_DESGRAVAMEN_COSTO_MENSUAL = convertFormatStringCurrency(value.getCOSTO_MENSUAL_DESGRAVAMEN());
			String SEGURO_DESGRAVAMEN_COSTO_TOTAL = convertFormatStringCurrency(value.getCOSTO_TOTAL_DESGRAVAMEN());
			String SEGURO_CESANTIA_COSTO_MENSUAL = convertFormatStringCurrency(value.getCOSTO_MENSUAL_CESANTIA());
			String SEGURO_CESANTIA_COSTO_TOTAL = convertFormatStringCurrency(value.getCOSTOS_TOTAL_CESANTIA());
			parameters2.put("fecha", testForm.getFecha().split("\\|")[0]);
			parameters2.put("unidadMonetaria", "Pesos");
			parameters2.put("montoLiquido", testForm.getMonto());
			parameters2.put("plazo", testForm.getCuotas());
			parameters2.put("valorCuota", testForm.getMONTO_CUOTA());
			parameters2.put("tasaInteresMensual", testForm.getTASA_INT_MENSUAL() + "%");
			parameters2.put("tasaInteresAnual", ("" + value.getTASA_INT_ANUAL()).replaceAll("\\.", "\\,") + "%");
			parameters2.put("ctc", testForm.getCTC());
			parameters2.put("cae", testForm.getCAE() + "%");
			parameters2.put("impuesto", testForm.getIMPUESTO());
			parameters2.put("gastoNotarial", testForm.getGASTO_NOTARIAL());
			parameters2.put("seguroDesgravamenCuota", SEGURO_DESGRAVAMEN_COSTO_MENSUAL);
			parameters2.put("seguroDesgravamenTotal", SEGURO_DESGRAVAMEN_COSTO_TOTAL);
			parameters2.put("seguroCesantiaCuota", SEGURO_CESANTIA_COSTO_MENSUAL);
			parameters2.put("seguroCesantiaTotal", SEGURO_CESANTIA_COSTO_TOTAL);
			parameters2.put("numeroPagina", pages + 1);
			parameters2.put("numeroPaginas", pages + 1);
			parameters2.put("FechaHoraSimulacion", "<style isBold='true' pdfFontName='Helvetica-Bold'>"
				+ new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(new Date()) + " hrs.</style>");
			// Generacion PDF
			JRDataSource dataSource2 = new JREmptyDataSource();
			JasperPrint jasperPrint2 = JasperFillManager.fillReport(jasperReport2, parameters2, dataSource2);
			jasperPrints.add(jasperPrint2);
			JRPdfExporter exporter = new JRPdfExporter();
			//Create new FileOutputStream or you can use Http Servlet Response.getOutputStream() to get Servlet output stream
			// Or if you want bytes create ByteArrayOutputStream
			// ByteArrayOutputStream out = new ByteArrayOutputStream();
			String pdfname = System.getProperty("java.io.tmpdir") + "resultado " + testForm.getRut() + " .pdf"; //  "C:\\a\\ireport\\resultado " + testForm.getRut() + " .pdf";
			FileOutputStream output = new FileOutputStream(pdfname);
			//exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
			exporter.exportReport();
			return pdfname;
			/*
			File pdf = new File("C:\\a\\ireport\\resultado " + testForm.getRut() +" .pdf");
			FileInputStream in = new FileInputStream(pdf);
			//ServletOutputStream out = response.getOutputStream();
			byte[] outputByte = new byte[4096];
			//copy binary content to output stream
			while (in.read(outputByte, 0, 4096) != -1) {
				out.write(outputByte, 0, 4096);
			}
			in.close();
			out.flush();
			out.close();
			pdf.delete();
			
			return null;
			*/
			//JasperExportManager.exportReportToPdfFile(jasperPrints, "C:\\a\\ireport\\resumen.pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

	public static String generatePDFwithJasperTest(TestForm testForm) {
		try {
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile("C:\\a\\ireport\\simulacion.jasper");
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			// Datos de tabla dinamica
			List<DetalleCuota> cuotas = new ArrayList<DetalleCuota>();
			for (int i = 1; i < 60; i++) {
				DetalleCuota cuota = new DetalleCuota();
				cuota.setIndiceCuota(String.valueOf(i));
				cuota.setVencimiento("01-10-2018");
				cuota.setSaldoCapital("1.007.222");
				cuota.setMontoInteres("28.554");
				cuota.setSeguroDesgravamen("1.566");
				cuota.setSeguroCesantia("4.454");
				cuota.setValorCuota("65.692");
				cuotas.add(cuota);
			}
			parameters.put("cuotas", cuotas);
			// Generacion PDF
			JRDataSource dataSource = new JREmptyDataSource();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			int pages = jasperPrint.getPages().size();
			// JasperReport jasperReport = JasperCompileManager.compileReport("C:\\a\\ireport\\simulacion.jrxml");
			JasperReport jasperReport1 = (JasperReport) JRLoader.loadObjectFromFile("C:\\a\\ireport\\simulacion.jasper");
			HashMap<String, Object> parameters1 = new HashMap<String, Object>();
			parameters1.put("rut", "12345678-K");
			parameters1.put("impuesto", "$ 8.307");
			parameters1.put("sucursal", "HUERFANOS");
			parameters1.put("gastoNotarial", "$ 700");
			parameters1.put("tipoAfiliado", "TRABAJADOR");
			parameters1.put("valorCuotaMensual", "$ 65.902");
			parameters1.put("monto", "$ 1.000.000");
			parameters1.put("tasaInteresMensual", "2,99%");
			parameters1.put("numeroCuota", "24");
			parameters1.put("pagoPrimeraCuota", "01/10/2018");
			parameters1.put("seguroCesantia", "SI");
			parameters1.put("seguroDesgravamen", "SI");
			parameters1.put("cae", "42,62%");
			parameters1.put("ctc", "$ 1.581.648");
			parameters1.put("FechaHoraSimulacion", "<style isBold='true' pdfFontName='Helvetica-Bold'>"
				+ new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(new Date()) + " hrs.</style>");
			// JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(cuotas);
			parameters1.put("cuotas", cuotas);
			// Generacion PDF
			JRDataSource dataSource1 = new JREmptyDataSource();
			List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
			parameters1.put("numeroPagina", pages);
			parameters1.put("numeroPaginas", pages + 1);
			JasperPrint jasperPrint1 = JasperFillManager.fillReport(jasperReport1, parameters1, dataSource1);
			jasperPrints.add(jasperPrint1);
			pages = jasperPrint1.getPages().size();
			JasperReport jasperReport2 = (JasperReport) JRLoader.loadObjectFromFile("C:\\a\\ireport\\resumen.jasper");
			HashMap<String, Object> parameters2 = new HashMap<String, Object>();
			parameters2.put("fecha", "04/09/2018");
			parameters2.put("unidadMonetaria", "Pesos");
			parameters2.put("montoLiquido", "$ 1.000.000");
			parameters2.put("plazo", "24");
			parameters2.put("valorCuota", "$ 65.902");
			parameters2.put("tasaInteresMensual", "2,99%");
			parameters2.put("tasaInteresAnual", "2,99%");
			parameters2.put("ctc", "$ 1.581.648");
			parameters2.put("cae", "42,62%");
			parameters2.put("impuesto", "$ 8.307");
			parameters2.put("gastoNotarial", "$ 700");
			parameters2.put("seguroDesgravamenCuota", "$ 1.566");
			parameters2.put("seguroDesgravamenTotal", "$ 37.584");
			parameters2.put("seguroCesantiaCuota", "$ 4.454");
			parameters2.put("seguroCesantiaTotal", "$ 106.896");
			parameters2.put("numeroPagina", pages + 1);
			parameters2.put("numeroPaginas", pages + 1);
			parameters2.put("FechaHoraSimulacion", "<style isBold='true' pdfFontName='Helvetica-Bold'>"
				+ new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(new Date()) + " hrs.</style>");
			// Generacion PDF
			JRDataSource dataSource2 = new JREmptyDataSource();
			JasperPrint jasperPrint2 = JasperFillManager.fillReport(jasperReport2, parameters2, dataSource2);
			jasperPrints.add(jasperPrint2);
			JRPdfExporter exporter = new JRPdfExporter();
			//Create new FileOutputStream or you can use Http Servlet Response.getOutputStream() to get Servlet output stream
			// Or if you want bytes create ByteArrayOutputStream
			// ByteArrayOutputStream out = new ByteArrayOutputStream();
			String pdfname = "C:\\a\\ireport\\resultado " + testForm.getRut() + " .pdf";
			FileOutputStream output = new FileOutputStream(pdfname);
			//exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
			exporter.exportReport();
			return pdfname;
			/*
			File pdf = new File("C:\\a\\ireport\\resultado " + testForm.getRut() +" .pdf");
			FileInputStream in = new FileInputStream(pdf);
			//ServletOutputStream out = response.getOutputStream();
			byte[] outputByte = new byte[4096];
			//copy binary content to output stream
			while (in.read(outputByte, 0, 4096) != -1) {
				out.write(outputByte, 0, 4096);
			}
			in.close();
			out.flush();
			out.close();
			pdf.delete();
			
			return null;
			*/
			//JasperExportManager.exportReportToPdfFile(jasperPrints, "C:\\a\\ireport\\resumen.pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

	public static void generatePDFwithVelocity() {
		/*
		
		// Velocity
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
		Document document = new Document(PageSize.LETTER);
		PdfCopy copy = new PdfCopy(document, new FileOutputStream(file));
		document.open();
		PdfReader reader;
		VelocityContext context1 = new VelocityContext();
		VelocityContext context2 = new VelocityContext();
		context1.put("RUT", rut);
		context1.put("IMPUESTO", testForm.getIMPUESTO());
		context1.put("SUCURSAL", testForm.getSucursal());
		context1.put("GASTO_NOTARIAL", testForm.getGASTO_NOTARIAL());   
		context1.put("TIPO_AFILIADO", testForm.getTipoAfiliado());
		context1.put("VALOR_CUOTA_MENSUAL", testForm.getMONTO_CUOTA());
		context1.put("MONTO", testForm.getMonto());
		context1.put("TASA_INTERES_MENSUAL", testForm.getTASA_INT_MENSUAL()); 
		context1.put("CUOTAS", testForm.getCuotas());
		context1.put("PAGO_PRIMERA_CUOTA", testForm.getFECHA_PRIMER_VENCIMIENTO());
		context1.put("SEGURO_CESANTIA", testForm.getSeguroCesantia());
		context1.put("CAE", testForm.getCAE());
		context1.put("SEGURO_DESGRAVAMEN", testForm.getSeguroDesgravamen());
		context1.put("CTC", testForm.getCTC());
		context2.put("CAE", testForm.getCAE());
		context2.put("FECHA", testForm.getFecha().split("\\|")[0]);
		context2.put("MONTO", testForm.getMonto());
		context2.put("CUOTAS", testForm.getCuotas());
		context2.put("VALOR_CUOTA", testForm.getMONTO_CUOTA());
		context2.put("TASA_INTERES_MENSUAL", testForm.getTASA_INT_MENSUAL());
		context2.put("TASA_INTERES_ANUAL", ("%" + value.getTASA_INT_ANUAL()).replaceAll("\\.", "\\,"));
		context2.put("COSTO_TOTAL_CREDITO", testForm.getCTC());
		context2.put("IMPUESTOS", testForm.getIMPUESTO());
		context2.put("GASTOS_NOTARIALES", testForm.getGASTO_NOTARIAL());
		
		String SEGURO_DESGRAVAMEN_COSTO_MENSUAL = format.format(value.getCOSTO_MENSUAL_DESGRAVAMEN()).replaceAll("\\,", "\\.");
		String SEGURO_DESGRAVAMEN_COSTO_TOTAL = format.format(value.getCOSTO_TOTAL_DESGRAVAMEN()).replaceAll("\\,", "\\.");
		String SEGURO_CESANTIA_COSTO_MENSUAL = format.format(value.getCOSTO_MENSUAL_CESANTIA()).replaceAll("\\,", "\\.");
		String SEGURO_CESANTIA_COSTO_TOTAL = format.format(value.getCOSTOS_TOTAL_CESANTIA()).replaceAll("\\,", "\\.");
		
		context2.put("SEGURO_DESGRAVAMEN_COSTO_MENSUAL", SEGURO_DESGRAVAMEN_COSTO_MENSUAL.substring(0, SEGURO_DESGRAVAMEN_COSTO_MENSUAL.length() - 3));
		context2.put("SEGURO_DESGRAVAMEN_COSTO_TOTAL",SEGURO_DESGRAVAMEN_COSTO_TOTAL.substring(0, SEGURO_DESGRAVAMEN_COSTO_TOTAL.length() - 3));
		context2.put("SEGURO_CESANTIA_COSTO_MENSUAL", SEGURO_CESANTIA_COSTO_MENSUAL.substring(0, SEGURO_CESANTIA_COSTO_MENSUAL.length() - 3));
		context2.put("SEGURO_CESANTIA_COSTO_TOTAL", SEGURO_CESANTIA_COSTO_TOTAL.substring(0, SEGURO_CESANTIA_COSTO_TOTAL.length() - 3));
					
				
		DetalleVO[] cuotas = value.getDETALLE_CUOTA();	
		String tablaCuotas = "";
		String tablaFechas = "";
		String tablaSaldoCapital = "";
		String tablaMontoInteres = "";
		String tablaSeguroDesgravamen = "";
		String tablaSeguroCesantia = "";
		String tablaValorCuota = "";
		for (DetalleVO detalleVO : cuotas) {	
			String fechaVencimiento = String.valueOf(detalleVO.getFECHA_VENCIMIENTO());
			String fechaVencimientoFinal = fechaVencimiento.substring(6, 8) + "-" + fechaVencimiento.substring(4, 6) + "-" + fechaVencimiento.substring(0, 4);
			String saldoCapital = format.format(detalleVO.getSALDO_CAPITAL()).replaceAll("\\,", "\\.");
			String montoInteres = format.format(detalleVO.getMONTO_INTERES()).replaceAll("\\,", "\\.");
			String seguroDesgravame = format.format(detalleVO.getSEGURO_DESGRAVAMEN()).replaceAll("\\,", "\\.");
			String seguroCesantia = format.format(detalleVO.getSEGURO_CESANTIA()).replaceAll("\\,", "\\.");
			String valorCuota = format.format(detalleVO.getMONTO_CUOTA()).replaceAll("\\,", "\\.");
			
			tablaCuotas += "<p class=\"c3\"><span class=\"c6\">" + detalleVO.getNUM_CUOTA() + "</span></p>";
			tablaFechas += "<p class=\"c3\"><span class=\"c6\">" + fechaVencimientoFinal + "</span></p>";
			tablaSaldoCapital += "<p class=\"c3\"><span class=\"c6\">" + saldoCapital.substring(0, saldoCapital.length() - 3) + "</span></p>";
			tablaMontoInteres += "<p class=\"c3\"><span class=\"c6\">" + montoInteres.substring(0, montoInteres.length() - 3)+ "</span></p>";
			tablaSeguroDesgravamen += "<p class=\"c3\"><span class=\"c6\">" +seguroDesgravame.substring(0, seguroDesgravame.length() - 3) + "</span></p>";
			tablaSeguroCesantia += "<p class=\"c3\"><span class=\"c6\">" +seguroCesantia.substring(0, seguroCesantia.length() - 3) + "</span></p>";
			tablaValorCuota += "<p class=\"c3\"><span class=\"c6\">" + valorCuota.substring(0, valorCuota.length() - 3)+ "</span></p>";
		}
		
		context1.put("TABLA_CUOTAS", tablaCuotas);
		context1.put("TABLA_FECHAS", tablaFechas);
		context1.put("TABLA_SALDO_CAPITAL", tablaSaldoCapital);
		context1.put("TABLA_MONTO_INTERES", tablaMontoInteres);
		context1.put("TABLA_SEGURO_DESGRAVAMEN", tablaSeguroDesgravamen);
		context1.put("TABLA_SEGURO_CESANTIA", tablaSeguroCesantia);
		context1.put("TABLA_VALOR_CUOTA", tablaValorCuota);
		
		StringWriter writer1 = new StringWriter();
		StringWriter writer2 = new StringWriter();  
		InputStream is1 = new FileInputStream(basedir + "page1.html");
		InputStream is2 = new FileInputStream(basedir + "page2.html");
		String html1 = getStringFromInputStream(is1);
		String html2 = getStringFromInputStream(is2);
		Velocity.evaluate(context1, writer1, "TemplateName", html1);
		Velocity.evaluate(context2, writer2, "TemplateName", html2);
		InputStream stream1 = new ByteArrayInputStream(writer1.toString().getBytes());
		InputStream stream2 = new ByteArrayInputStream(writer2.toString().getBytes());
		reader = new PdfReader(parseHtml(stream1));
		copy.addDocument(reader);
		reader.close();
		reader = new PdfReader(parseHtml(stream2));
		copy.addDocument(reader);
		reader.close();
		document.close();
		
		File pdf = new File(file);
		FileInputStream in = new FileInputStream(pdf);
		ServletOutputStream out = response.getOutputStream();
		byte[] outputByte = new byte[4096];
		//copy binary content to output stream
		while (in.read(outputByte, 0, 4096) != -1) {
			out.write(outputByte, 0, 4096);
		}
		in.close();
		out.flush();
		out.close();
		pdf.delete();
		*/
	}

	public static void generatePDFFromHTML(String input, String output) throws IOException, DocumentException {
		Document document = new Document(PageSize.LETTER);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(output));
		document.open();
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(input));
		//XMLWorkerHelper.getInstance().parseXHtml(writer, document,  PDF.class.getClassLoader().getResourceAsStream(input));
		document.close();
	}

	public static byte[] parseHtml(InputStream html) throws DocumentException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// step 1
		Document document = new Document(PageSize.LETTER);
		// step 2
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		// step 3
		document.open();
		// step 4
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, html);
		// step 5
		document.close();
		// return the bytes of the PDF
		return baos.toByteArray();
	}

	private static String getStringFromInputStream(InputStream is) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
}