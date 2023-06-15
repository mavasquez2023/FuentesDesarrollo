package cl.laaraucana.satelites.certificados.finiquito.utils;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.Finishings;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.Sides;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class UtilPrint {

	public static void imprimirPdfWEB(byte[] bites, HttpServletResponse response) {

		try {
			response.setContentType("application/pdf");  
			PdfReader reader = new PdfReader(bites);
			PdfStamper stamper = new PdfStamper(reader, response.getOutputStream());
			PdfWriter writer = stamper.getWriter();

			StringBuffer javascript = new StringBuffer();

			javascript.append("var params = this.getPrintParams();");

			javascript.append("params.interactive = params.constants.interactionLevel.silent;");
			javascript.append("params.pageHandling = params.constants.handling.shrink;");

			javascript.append("this.print(params);");

			PdfAction pdfAction = PdfAction.javaScript(javascript.toString(), writer);
			writer.addJavaScript(pdfAction);

			stamper.close();

		} catch (DocumentException de) {
			de.printStackTrace();
			System.err.println("document: " + de.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void imprimirHtmlWEB(HttpServletResponse response) {

		try {
			response.setContentType("text/html");  

			StringBuffer javascript = new StringBuffer();
			javascript.append("var params = this.getPrintParams();");

			javascript.append("params.interactive = params.constants.interactionLevel.silent;");
			javascript.append("params.pageHandling = params.constants.handling.shrink;");

			javascript.append("this.print(params);");
			response.getWriter().write(javascript.toString());

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void imprimirPdfLocal(byte[] bites) {

		List<PDDocument> docs = new ArrayList<PDDocument>();
		InputStream myInputStream = new ByteArrayInputStream(bites);
		try {
			docs.add(PDDocument.load(myInputStream));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (!docs.isEmpty()) {
				for (PDDocument doc : docs) {
					System.out.println("se deberia imprimir");
					System.out.println("numero de paginas " + doc.getNumberOfPages());
					doc.silentPrint();
				}
			} else {
				System.out.println("no se imprimio");
			}
		} catch (PrinterException e) {
			e.printStackTrace();
		} finally {
			for (PDDocument doc : docs) {
				if (doc != null) {
					try {
						doc.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void imprimirPdfs() {

		List<PDDocument> docs = new ArrayList<PDDocument>();
		try {
			docs.add(PDDocument.load("c://test/test.pdf"));
			docs.add(PDDocument.load("c://test/test2.pdf"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
			aset.add(MediaSizeName.ISO_A4);
			aset.add(new Copies(1));
			aset.add(Sides.ONE_SIDED);
			aset.add(Finishings.STAPLE);

			PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
			PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
			PrintService service = ServiceUI.printDialog(null, 200, 200, printService, defaultService, flavor, pras);

			if (service != null && !docs.isEmpty()) {
				for (PDDocument doc : docs) {
					PrinterJob printJob = PrinterJob.getPrinterJob();
					printJob.setPrintService(service);
					doc.silentPrint(printJob);
				}
			}
		} catch (PrinterException e) {
			e.printStackTrace();
		} finally {
			for (PDDocument doc : docs) {
				if (doc != null) {
					try {
						doc.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
