package cl.liv.export.comun.util.file;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.channels.FileChannel;

import org.apache.commons.io.FileUtils;

public class ManejoArchivos {

	public static void copyFileUsingChannel(File source, File dest) throws IOException {
		/*FileChannel sourceChannel = null;
	    FileChannel destChannel = null;
	    try {
	        sourceChannel = new FileInputStream(source).getChannel();
	        destChannel = new FileOutputStream(dest).getChannel();
	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	       }finally{
	           sourceChannel.close();
	           destChannel.close();
	   }*/
		
		FileUtils.copyFile(source, dest);
	}
	
	public static String getFileAsString(String filePath) throws IOException{
		 BufferedReader br = new BufferedReader(new FileReader(filePath));
		 String everything = "";
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		            sb.append("\n");
		            //sb.append(System.lineSeparator());
		            line = br.readLine();
		        }
		        everything = sb.toString();
		    } finally {
		        br.close();
		    }
		    return everything;
	}
	
	public static boolean writeFileFromString(String filePath, String texto){
		
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(filePath), "utf-8"));
		    writer.write(texto);
		} catch (IOException ex) {
		  // report
			ex.printStackTrace();
			return false;
		} finally {
		   try {writer.close();} catch (Exception ex) {}
		}
		return true;
	}
	
	public BufferedWriter openFileToWrite(String filePath, String encoding){
		
		BufferedWriter writer = null;

		try {
			if(encoding != null && encoding.length()>0){
				writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(filePath), encoding));
			}
			else{

				writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(filePath)));
			}
		    return writer;
		} catch (IOException ex) {
		  // report
			ex.printStackTrace();
		} 
		return null;
	}
	
	public void addLineToFileOpened(BufferedWriter writer, String texto){
		try {
			String separador = System.getProperty("line.separator") ;
			separador = "\r\n";
			writer.write(texto+ separador);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeFileOpened(Writer writer){
		try {writer.close();} catch (Exception ex) {
			
		}
	}
	
	public static boolean editFile(final File file) {
		  if (!Desktop.isDesktopSupported()) {
		    return false;
		  }

		  Desktop desktop = Desktop.getDesktop();
		  

		  try {
		    desktop.open(file);
		  } catch (IOException e) {
		    // Log an error
		    e.printStackTrace();
		  }

		  return true;
		}
	
	
}
