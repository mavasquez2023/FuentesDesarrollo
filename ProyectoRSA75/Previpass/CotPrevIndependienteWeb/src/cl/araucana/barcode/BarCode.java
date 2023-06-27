package cl.araucana.barcode;

import java.awt.Component;
import java.io.ByteArrayOutputStream;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import org.apache.struts.Globals;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

public class BarCode extends Action
{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		
		Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(doc, baos);
        String codigo = request.getParameter("codigo");
		//PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("algo.pdf"));
		//PdfWriter writer = PdfWriter.GetInstance(doc, stream);
		doc.open();
		HttpSession session = request.getSession(true);
		
		//PdfContentByte cb = writer.DirectContent;				
		String tmp = "" + codigo;
		Barcode barcode = BarcodeFactory.parseEAN128("(" + tmp.substring(0, 2) + ")" + tmp.substring(2, tmp.length()));
		barcode.setBarHeight(50);
		barcode.setAlignmentY(Component.LEFT_ALIGNMENT);
		barcode.setDrawingText(false);
	
		Image img2 = Image.getInstance(BarcodeImageHandler.getImage(barcode), null);
		doc.add(img2);
		doc.close();
		
		session.setAttribute(Globals.LOCALE_KEY, new Locale("es", "ES"));

        response.setHeader("Expires", "0");
    	response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
    	response.setHeader("Pragma", "public");
    	// setting the content type
    	response.setContentType("application/pdf");
    	// the contentlength is needed for MSIE!!!
    	response.setContentLength(baos.size());
    	// write ByteArrayOutputStream to the ServletOutputStream
    	ServletOutputStream out = response.getOutputStream();
    	baos.writeTo(out);
    	out.flush();
		
		
		return null;
	}
}
