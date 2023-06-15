package cl.liv.archivos.comun.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;

public class ManejoArchivoTXT {

	public static String getFileContentAsString(String filePath){
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filePath));
			try {
			    StringBuilder sb = new StringBuilder();
			    String line = br.readLine();

			    while (line != null) {
			        sb.append(line);
			        sb.append("\n");
			        line = br.readLine();
			    }
			    return sb.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			    try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static BufferedReader getOpenedFileToRead(String filePath, int indicePrimeraFila, String encoding){
		BufferedReader br;
		try {
			if(encoding != null && encoding.length() >0){
				br = new BufferedReader(new InputStreamReader(
					    new FileInputStream(filePath), encoding));
			}
			else{
				br = new BufferedReader(new InputStreamReader(
					    new FileInputStream(filePath)));
			}
			for(int i=0; i< indicePrimeraFila; i++){
				String linea = br.readLine();
			}
			return br;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static BufferedReader getOpenedFileToRead(String filePath, boolean includeFirstLine, String encoding){

		BufferedReader br;
		try {
			if(encoding != null && encoding.length() >0){
				br = new BufferedReader(new InputStreamReader(
					    new FileInputStream(filePath), encoding));
			}
			else{
				br = new BufferedReader(new InputStreamReader(
					    new FileInputStream(filePath)));
			}
			if(!includeFirstLine){
				br.readLine();
			}
			return br;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static void closeFileToRead(BufferedReader br){
		
		try {
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getLineFromFileOpened(BufferedReader br){
		String line;
		try {

			line = br.readLine();
			
			return line;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    return null;
	}
	
	public static PrintWriter getOpenedFileToWrite(String filePath){
		
		try{
			File f = new File(filePath);
			f.createNewFile();
			f.setWritable(true);
		    PrintWriter writer = new PrintWriter(filePath);
		    return writer;
		} catch (IOException e) {
		   // do something
		}
		return null;
	}
	public static void putLineFromFileOpened(PrintWriter pw,String texto){
		try{
		    pw.println(texto  );
		    pw.flush();
		} catch (Exception e) {
		   // do something
		}
	}
	public static void closeFileToWrite(PrintWriter pw){
		
		try {
			if(pw != null) {
				pw.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static boolean copyFileUsingChannel(File source, File dest) throws IOException {
	    FileChannel sourceChannel = null;
	    FileChannel destChannel = null;
	    try {
	        sourceChannel = new FileInputStream(source).getChannel();
	        destChannel = new FileOutputStream(dest).getChannel();
	        destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	        return true;
	       }finally{
	           sourceChannel.close();
	           destChannel.close();
	       }
	}
	
}
