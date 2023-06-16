package cl.araucana.cotfonasa.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.IFSFileInputStream;

import cl.araucana.cotfonasa.util.AccesoAS400;

public class DownloadServlet extends HttpServlet {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 221962082668348427L;

	/*
	protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        AccesoAS400 as400 = new AccesoAS400();
        
        // se traspasa el archivo desde as400 antes de ser visto
        String destinoServ = "C://descargaTemporal.txt";
        
        try {
			as400.copyFileAS400toServer(id, destinoServ);
		} catch (AS400SecurityException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
        
        String fileName = destinoServ;
        String fileType = "text/plain";
        // Find this file id in database to get file name, and file type

        // You must tell the browser the file type you are going to send
        // for example application/pdf, text/plain, text/html, image/jpg
        response.setContentType(fileType);

        // Make sure to show the download dialog
        response.setHeader("Content-disposition","attachment; filename="+id);

        // Assume file name is retrieved from database
        // For example D:\\file\\test.pdf

        File my_file = new File(fileName);

        // This should send the file to browser
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(my_file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0){
           out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
   }*/
	
	protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        Properties props = new Properties();
        
        props.load(DownloadServlet.class.getClassLoader().getResourceAsStream("cl/araucana/cotfonasa/properties/parametros.properties"));
		String HOST_NAME = props.getProperty("HOST_NAME");
		String USER = props.getProperty("USER");
		String PWD  = props.getProperty("PWD");
        
        AS400 system = new AS400 (HOST_NAME,USER,PWD);
        
        // se traspasa el archivo desde as400 antes de ser visto
        String destinoServ = "C://descargaTemporal.txt";
    
        
        String fileName = destinoServ;
        String fileType = "text/plain";
        // Find this file id in database to get file name, and file type

        // You must tell the browser the file type you are going to send
        // for example application/pdf, text/plain, text/html, image/jpg
        response.setContentType(fileType);

        // Make sure to show the download dialog
        response.setHeader("Content-disposition","attachment; filename="+fileName);

        // Assume file name is retrieved from database
        // For example D:\\file\\test.pdf

        
        //File my_file = new File(fileName);

        // This should send the file to browser
        
        try{
        OutputStream out = response.getOutputStream();
        InputStream in = (InputStream)(new IFSFileInputStream(system,
					id));
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0){
           out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
        
        }catch(Exception e)
        {
          e.printStackTrace();	
        }
          
        
        
   }

}
