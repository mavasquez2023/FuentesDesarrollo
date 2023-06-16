package cl.araucana.cotfonasa.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.util.Properties;


import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.IFSFile;
import com.ibm.as400.access.IFSFileInputStream;
import com.ibm.as400.access.IFSFileOutputStream;
import com.ibm.as400.access.IFSFileReader;
import com.ibm.as400.access.IFSFileWriter;
import com.ibm.as400.access.ObjectAlreadyExistsException;

import java.io.FileOutputStream;


public class AccesoAS400 {
	
	private static String HOST_NAME;
	private static String USER;
	private static String PWD;
	
	
	public AccesoAS400() {
		super();
		
		try {
			
			Properties props = new Properties();
			 
			
			props.load(AccesoAS400.class.getClassLoader().getResourceAsStream("cl/araucana/cotfonasa/properties/parametros.properties"));
			HOST_NAME = props.getProperty("HOST_NAME");
			USER = props.getProperty("USER");
			PWD  = props.getProperty("PWD");
			
		} catch (FileNotFoundException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		// TODO Apéndice de método generado automáticamente
		AS400 system = new AS400 ("146.83.1.5","schema", "schema");
		//AccesoAS400.copiarArchivo();
		
		AccesoAS400 as400 = new AccesoAS400();
		
		
		// se copia el archivo desde as400 hacia el server donde esta la app
		try {
			//as400.readFileCopyToWin("/ARCHTXT.txt", "C:\\testeando.txt");
			
			String sourceAS400 = "/ARCHTXT.txt";
			String destinoServ ="C:\\testing.txt";
			as400.copyFileAS400toServer(sourceAS400, destinoServ);
			
			// borrar archivo de paso en as400
			String fileToDeleted = sourceAS400;
			as400.deleteFromAS400(fileToDeleted);
			
			
			//as400.exportArchiveFromTable("cotfonasa/inputfon", "/testFile1.txt");
			
			
			
			
		} catch (AS400SecurityException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch(NullPointerException e){
			e.printStackTrace();
			e.getMessage();
			System.out.println("error null pointer exception");
		}
		//as400.copiarArchivoDesdeTabla();
	}
	
	/*
	public void writeFileFromServerToAS400(String pathSourceServer, String pathDestAS400) throws IOException,
	 AS400SecurityException {
		 
		AS400 system = new AS400 (HOST_NAME,USER,PWD);
		
		
		
		System.out.println("Se copia el archivo de salida en as400");
		
		IFSFile file = new IFSFile(system, pathDestAS400);
	      
	    IFSFileOutputStream fos = new IFSFileOutputStream (file);
	     
	      
	      FileInputStream fis = new FileInputStream(pathSourceServer);
	       
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        byte[] buf = new byte[1024 * 64];
	        try {
	            for (int readNum; (readNum = fis.read(buf)) != -1;) {
	                bos.write(buf, 0, readNum); //no doubt here is 0
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        byte[] bytes = bos.toByteArray();
	      

	        
	      fos.write(bytes);
	      fos.close();
	      
	      system.disconnectService(AS400.COMMAND);

	 }*/
	
	public void writeFileFromServerToAS400(String pathSourceServer, String pathDestAS400) throws IOException,
	 AS400SecurityException {
		 
		AS400 system = new AS400 (HOST_NAME,USER,PWD);
		
		
		
		System.out.println("Se copia el archivo de salida en as400");
		
		IFSFile file = new IFSFile(system, pathDestAS400);
	      /** creates a file output stream */
	    IFSFileOutputStream fos = new IFSFileOutputStream (file);
	      /** Writes bytes to file */
	      
	      FileInputStream fis = new FileInputStream(pathSourceServer);
	       
	      byte[] buffer= new byte[4096];
		    while (true) {
		      int n= fis.read(buffer);
		      if (n < 0)
		        break;
		      fos.write(buffer, 0, n);
		    }
	        
	        /*try {
	        	
	        	 int c;

	             while ((c = fis.read()) != -1) {
	                fos.write(c);
	             }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }*/
	  
	      fis.close();
	      fos.close();
	      
	      system.disconnectService(AS400.COMMAND);

	 }
	
	
	public  int exportArchiveFromTable(String SchemaTable, String destPathAS400) throws Exception
	{
		 
		 AS400 system = new AS400 (HOST_NAME,USER,PWD);
		 
		 CommandCall command = new CommandCall(system);
		
		     // Run the command 
		     if ( !(command.run("CPYTOIMPF FROMFILE("+SchemaTable+") TOSTMF('"+destPathAS400+"') MBROPT(*REPLACE) STMFCODPAG(*PCASCII) RCDDLM(*CRLF) DTAFMT(*DLM) STRDLM(*NONE) FLDDLM(';') ")) )
		     {
		         // Note that there was an error.
		         System.out.println("Command failed!");
		      // Done with the system.
				 System.out.println("End failed!");
				 system.disconnectService(AS400.COMMAND);
		         return 0;
		     }else{

		        System.out.println("archivo copiado OK");
		     // Done with the system.
				 System.out.println("End!");
				 system.disconnectService(AS400.COMMAND);
		        return 1;

		     }
		     
		     


	}
	
	public int insertTableFromArchive()
	{
		 
		 AS400 system = new AS400 (HOST_NAME,USER,PWD);
		 
		 CommandCall command = new CommandCall(system);
		 try
		 {
		     // Run the command 
		     if ( !(command.run("CPYFRMIMPF FROMSTMF('/mytextfile.TXT') TOFILE(MYLIB/TEXTO) MBROPT(*REPLACE) RCDDLM(*CRLF) DTAFMT(*DLM) STRDLM(*NONE) RMVBLANK(*NONE) FLDDLM(';')")) )
		     {
		         // Note that there was an error.
		         System.out.println("Command failed!");
		     }else{

		  

		     }
		     
		     
		     

		 }
		 catch (Exception e)
		 {
		     System.out.println("Command " + command.getCommand() + " issued an exception!");
		     e.printStackTrace();
		 }
		 // Done with the system.
		 System.out.println("End!");
		 system.disconnectService(AS400.COMMAND);
		
		 
		 
		return 1;
	}
	
	
	
	public String copyFileAS400toServer(String sourceAS400, String destinoServ) throws AS400SecurityException,
	 IOException
	{
		
		 AS400 system = new AS400 (HOST_NAME,USER,PWD);
		 
         System.out.println("Copiamos el archivo desde AS400 al servidor");
			InputStream in = (InputStream)(new IFSFileInputStream(system,
					sourceAS400));
			
			
		    OutputStream out= new FileOutputStream(destinoServ);
		    byte[] buffer= new byte[4096];
		    while (true) {
		      int n= in.read(buffer);
		      if (n < 0)
		        break;
		      out.write(buffer, 0, n);
		    }
		    in.close();
		    out.close();
		    
		    System.out.println("Transfer Completed to:"+destinoServ);
		    system.disconnectService(AS400.COMMAND);
		    
			return destinoServ;
	
		
	}
	
	public int deleteFromAS400(String fileToDeleted) throws IOException{
		
		AS400 system = new AS400 (HOST_NAME,USER,PWD);
		
		IFSFile file = new IFSFile(system, fileToDeleted);
	      
			if (file.exists())
			  file.delete();
			
			System.out.println("File as400 deleted:"+fileToDeleted);
			system.disconnectService(AS400.COMMAND);
			return 1;
		
	}
	
	public void copy(AS400 system, String srcPath, String destPath
	   ) throws IOException, AS400SecurityException, ObjectAlreadyExistsException{
	      IFSFile file = new IFSFile(system, srcPath);
	      file.copyTo(destPath);
	   }
	
	   public boolean verificarDir(String path) throws IOException{
		   
		      AS400 system = new AS400 (HOST_NAME,USER,PWD);
		      IFSFile dir = new IFSFile (system, path);
		      
		      boolean exist = dir.exists();
		      system.disconnectService(AS400.COMMAND);
		      return exist;
		         
		   
		 }
	
	/*
	 public int readFileCopyToWin(String pathAS, String destPathWin) throws AS400SecurityException,
	 IOException{
		 
		 AS400 system = new AS400 (HOST_NAME,USER,PWD);
		 
	      IFSFile file = new IFSFile(system, pathAS);
	    
	      IFSFileReader fr = new IFSFileReader (file);
	      BufferedReader br = new BufferedReader(fr);
	      StringBuffer sb = new StringBuffer();
	      
	      File fNew = new File(destPathWin);
	       FileWriter w = new FileWriter(fNew);
	       BufferedWriter bw = new BufferedWriter(w);
	       PrintWriter wr = new PrintWriter(bw);
	       String lineNew ="";
	       
	
	       
	       String line = br.readLine();
		      while (line != null){
		           line = br.readLine();
		           
		           lineNew = line.replaceAll(" ", "");
		           lineNew = lineNew.replaceAll("\"", "");
		           sb.append(lineNew +"\n");
		      }
	      
		      wr.println   (sb.toString());
	    
	      fr.close();
	      wr.close();
	      return 1;
	 }
	 */

	
	

}
