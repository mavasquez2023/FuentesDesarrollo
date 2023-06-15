package cl.liv.archivos.as400.impl;

import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Vector;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.CharConverter;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.IFSFile;
import com.ibm.as400.access.IFSFileOutputStream;
import com.ibm.as400.access.IFSFileWriter;

import cl.jfactory.planillas.service.util.UtilLogWorkflow;
import cl.liv.archivos.comun.txt.ManejoArchivoTXT;
import cl.liv.comun.utiles.PropertiesUtil;
import cl.liv.comun.utiles.UtilLogComun;

public class ArchivosAS400 {


	static boolean terminado = false;
	
	
	public static void eliminarArchivosExistentes(AS400 conexion, String path) throws IOException{
		IFSFile file = new IFSFile(conexion, path);
		UtilLogWorkflow.debug("folder-> "+ file.getPath());
		IFSFile[] archivosEncontrados = file.listFiles();
		if(archivosEncontrados != null && archivosEncontrados.length > 0){
			UtilLogWorkflow.debug("archivos encontrados: "+ archivosEncontrados.length);
			for(int i=0; i< archivosEncontrados.length; i++){
				UtilLogComun.debug("eliminando "+ i +" de "+ archivosEncontrados.length);
				if(i==0 || i % 100 == 0){
					UtilLogWorkflow.debug("eliminando "+ i +" de "+ archivosEncontrados.length);
				}
				archivosEncontrados[i].delete();
			}
		}
	}
	
	public static void obtenerArchivosPorFolder(AS400 conexion, String path) throws IOException{
		IFSFile file = new IFSFile(conexion, path);
		UtilLogWorkflow.debug("folder-> "+ file.getPath());
		IFSFile[] archivosEncontrados = file.listFiles();
		for(int i=0; i< archivosEncontrados.length; i++){
			UtilLogWorkflow.debug("archivo-> "+ archivosEncontrados[i].getPath() +" , "+ archivosEncontrados[i].length());
		}
		
	}
	
	public static boolean existeArchivo(AS400 conexion, String path) throws IOException{
		IFSFile file = new IFSFile(conexion, path);
		UtilLogWorkflow.debug("existe ? "+ file.getPath() + " , "+ file.exists());
		return file.exists();
	}
	
	public static void crearFolderRecursivos (AS400 conexion, String path) throws IOException{
		UtilLogWorkflow.debug("path: "+path);
		String[] directorios = path.split("/");
		String directorio = "";
		for(int i=0; i< directorios.length - 1; i++){
			if(directorios[i].length() > 0){
				directorio = directorio +"/"+ directorios[i];
				IFSFile f = new IFSFile(conexion, directorio);
				UtilLogWorkflow.debug("directorio: "+ directorio + ", existe: "+ f.exists());
				if(!f.exists()){
					f.mkdirs();
				}
				
				
			}
		}
		return;
	}
	AS400 systemout = null;
	public  boolean crearArchivo(String pathfile, Vector texto) throws IOException{
		IFSFile file=null;
		OutputStream out;
		CharConverter conv=null;
		byte[] buff;
		try{
			if (systemout==null){
				out = new FileOutputStream(pathfile);
			}else{
				file = new IFSFile(systemout, pathfile);
				out = new IFSFileOutputStream(systemout, pathfile, getCCSID());
				conv = new CharConverter(getCCSID());
			}
			int numlineas= texto.size();
			for (int i=0; i< numlineas; i++){
				if (systemout==null){
					out.write(texto.elementAt(i).toString().getBytes());
					if (i<numlineas-1){
						out.write(13);
					}
				}else{
					out.write(conv.stringToByteArray(texto.elementAt(i).toString()));
					if (i<numlineas-1){
						out.write(conv.stringToByteArray("\n"));
					}
				}
			}
			out.flush();
			out.close();
			return true;
		  } catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	private static int ccsid=284;
	public static int getCCSID(){
		return ccsid;
	}
	
	public static boolean copiarArchivo(AS400 conexion, String pathInput, String pathOutput){
		char[] buffer = new char[1024];  
		IFSFile file;
		BufferedWriter out;
		try{
			
			crearFolderRecursivos(conexion, pathOutput);
			
			file = new IFSFile(conexion, pathOutput);
			if(!file.exists()){
				file.createNewFile();
			}
			else{
				file.delete();
				file.createNewFile();
			}
			out = new BufferedWriter(new IFSFileWriter(file));
			
			
			String contenidoArchivo= ManejoArchivoTXT.getFileContentAsString(pathInput);
			out.write(contenidoArchivo);
			
			out.flush();
			
			out.close();
			return true;
		  } catch(Exception e) {
			//e.printStackTrace();
			return true;
		}
		
	}
	
	
	
	public static boolean copiarArchivoConEncoding(AS400 conexion, String pathInput, String pathOutput){
		IFSFile file=null;
		OutputStream out;
		CharConverter conv=null;
		byte[] buff;
		try{
			
			file = new IFSFile(conexion, pathOutput);
			out = new IFSFileOutputStream(conexion, pathOutput, getCCSID());
			conv = new CharConverter(getCCSID());

			BufferedReader br = ManejoArchivoTXT.getOpenedFileToRead(pathInput, true, null);		
			String registro = ManejoArchivoTXT.getLineFromFileOpened(br);			
			while(registro != null && registro.length() > 0){			
				out.write(registro.getBytes());
				out.write("\n".getBytes());
				registro = ManejoArchivoTXT.getLineFromFileOpened(br);
			}
			out.write(13);
			ManejoArchivoTXT.closeFileToRead(br);
			out.flush();
			out.close();
			return true;
		  } catch(Exception e) {
			  e.printStackTrace();
			return false;
		}
		
	}
	
}
