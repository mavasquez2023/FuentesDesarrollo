package cl.laaraucana.autoconsulta.ui.actions.modulo2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
 
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.lowagie.text.DocumentException;

import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.InputSource;


public class RequestHtmlToPdf extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
  
        String File_To_Convert = (String)request.getParameter("fileHtml");
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition","inline; filename=\"ConvertedFile.pdf\"");
        
        BufferedHttpResponseWrapper wrapper = new BufferedHttpResponseWrapper(response);  
        ServletContext context = request.getSession().getServletContext();  
        String url = response.encodeRedirectURL(File_To_Convert);  
        RequestDispatcher dispatcher = context.getRequestDispatcher(url);  
        dispatcher.include(request, wrapper);  

        File_To_Convert = new String(wrapper.getOutput());  


//        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        System.out.println("preparando xhtml [" + File_To_Convert + "]");
        ITextRenderer renderer = new ITextRenderer();

        renderer.setDocumentFromString(File_To_Convert);

        renderer.layout();
        renderer.createPDF(response.getOutputStream());
        renderer.finishPDF();

    	return null;
    }

    
    public class BufferedHttpResponseWrapper extends HttpServletResponseWrapper {  
    	   
        PrintWriter writer = null;  
        ByteArrayOutputStream baos = null;  
       
        /** 
         * Constructor for BufferedHttpResponseWrapper. 
         * Create a new buffered Writer 
         *  
         * @param response The response object to wrap 
         */  
        public BufferedHttpResponseWrapper(HttpServletResponse response) {  
            super(response);  
            baos = new ByteArrayOutputStream();  
            writer = new PrintWriter(baos);  
        }  
       
        /** 
         * Return the buffered Writer 
         *   
         * @see javax.servlet.ServletResponse#getWriter() 
         */  
        public PrintWriter getWriter() throws IOException {  
            return writer;  
        }  
       
        /** 
         * Return the output written to the Writer. 
         * To get the output, the Writer must be flushed and closed. 
         * The content is captured by the ByteArrayOutputStream. 
         *   
         * @return 
         */  
        public String getOutput() {  
            writer.flush();  
            writer.close();  
            return baos.toString();  
        }  
    }
    
}