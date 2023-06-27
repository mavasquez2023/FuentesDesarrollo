package utilMonth;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class descargarArchivo {
	
	 /**
	 * 
	 */
	 
	
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, String file, String rutaPdf) throws ServletException, IOException {
		
		try{
					
		    FileInputStream archivo = new FileInputStream(rutaPdf + file); 
		    int longitud = archivo.available();
		    byte[] datos = new byte[longitud];
		    archivo.read(datos);
		    archivo.close();
		    
		    response.setContentType("application/pdf");
		    response.setHeader("Content-Disposition","attachment;filename=" + file);    
		    
		    ServletOutputStream ouputStream = response.getOutputStream();
		    ouputStream.write(datos);
		    ouputStream.flush();
		    ouputStream.close();
		
		  
			}catch(Exception ex)
			{ex.printStackTrace();}
			
		     
		 
		
	}
	
public void downloadZipp(HttpServletRequest request, HttpServletResponse response, String file, String rutaZip) throws ServletException, IOException {
		
		try{
					
		    FileInputStream archivo = new FileInputStream(rutaZip + file); 
		    int longitud = archivo.available();
		    byte[] datos = new byte[longitud];
		    archivo.read(datos);
		    archivo.close();
		    
		    response.setContentType("binary/data");
		    response.setHeader("Content-Disposition","attachment;filename=" + file);    
		    
		    ServletOutputStream ouputStream = response.getOutputStream();
		    ouputStream.write(datos);
		    ouputStream.flush();
		    ouputStream.close();
		
		  
			}catch(Exception ex)
			{ex.printStackTrace();}
			
		     
		 
		
	}


}
