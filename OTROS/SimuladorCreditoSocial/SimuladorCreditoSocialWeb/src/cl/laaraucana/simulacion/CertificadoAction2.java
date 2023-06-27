package cl.laaraucana.simulacion;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cl.laaraucana.servicios.simuladorCreditoSocial.CreditoVO;
import cl.laaraucana.simulacion.utils.ConstantesFormalizar;

public class CertificadoAction2 extends Action {
	private static String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
	// private static String source = "resultado.html";
	private static String basedir = "C:\\a\\resources";
	private static String source = basedir + "resultado.html";
	private static String style = basedir + "resultado.css";
	private static URL url;

	public static String getPDFOutput() {
		return basedir + "Test-" + timestamp + ".pdf";
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		//return an application file instead of html page
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=certificado.pdf");
		try {
			url = CertificadoAction.class.getClassLoader().getResource("WEB-INF/templates/");
			HttpSession session = request.getSession();
			cl.laaraucana.servicios.simuladorCreditoSocial.CreditoVO value = (CreditoVO) session.getAttribute("credito");
			ArrayList detalle = (ArrayList) session.getAttribute("detalleCuotas");
			createPdf(getPDFOutput());
			Map<String, Object> hash = new HashMap<String, Object>();
			hash.put("logo", "");
			hash.put("nombre", "Nombre");
			hash.put("rut", "1");
			hash.put("listaCreditoPrepagoFolios", "");
			hash.put("fechasFuturasPagoAfi", "");
			hash.put("fechasFuturasPagoPen", "");
			hash.put("fechaCreacion", "Fecha emisión ");
			//hash.put("fechaCreacion", certificadoPrepago.getFechaCompleta());
			hash.put("codValidacion", "1");
			hash.put("firma", "");
			hash.put("imgPath", "");
			String ruta = ConstantesFormalizar.SIMULACION_RESULTADO;
			cl.laaraucana.simulacion.ReporteUtil ru = new ReporteUtil(null, hash, ruta);
			byte[] bytes = ru.exportCompilePdf();
			InputStream in = new ByteArrayInputStream(bytes);
			ServletOutputStream out = response.getOutputStream();
			//byte[] outputByte = new byte[4096];
			//copy binary content to output stream
			while (in.read(bytes, 0, 4096) != -1) {
				out.write(bytes, 0, 4096);
			}
			in.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void generatePDFFromHTML(String input, String output) throws IOException, DocumentException {
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(output));
		document.open();
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(input));
		//XMLWorkerHelper.getInstance().parseXHtml(writer, document,  PDF.class.getClassLoader().getResourceAsStream(input));
		document.close();
	}
	public static final URL[] HTML = { CertificadoAction.class.getClassLoader().getResource("WEB-INF/templates/page1.html"),
		CertificadoAction.class.getClassLoader().getResource("WEB-INF/templates/page2.html") };

	public static void createPdf(String file) throws IOException, DocumentException {
		Document document = new Document();
		PdfCopy copy = new PdfCopy(document, new FileOutputStream(file));
		document.open();
		PdfReader reader;
		for (URL html : HTML) {
			reader = new PdfReader(parseHtml(html));
			copy.addDocument(reader);
			reader.close();
		}
		document.close();
	}

	public static byte[] parseHtml(URL html) throws DocumentException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// step 1
		Document document = new Document();
		// step 2
		PdfWriter writer = PdfWriter.getInstance(document, baos);
		// step 3
		document.open();
		// step 4 
		XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(url.getFile()));
		// step 5
		document.close();
		// return the bytes of the PDF
		return baos.toByteArray();
	}
}